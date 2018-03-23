package com.faceye.component.questionnaire.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.faceye.component.questionnaire.entity.Answer;
import com.faceye.component.questionnaire.entity.AnswerRecord;
import com.faceye.component.questionnaire.entity.Question;
import com.faceye.component.questionnaire.entity.Rabbit;
import com.faceye.component.questionnaire.model.MAnswer;
import com.faceye.component.questionnaire.model.MQuestion;
import com.faceye.component.questionnaire.repository.mongo.QuestionRepository;
import com.faceye.component.questionnaire.service.AnswerRecordService;
import com.faceye.component.questionnaire.service.AnswerService;
import com.faceye.component.questionnaire.service.QuestionService;
import com.faceye.component.questionnaire.service.RabbitService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
 
import com.querydsl.core.types.Predicate;

@Service
public class QuestionServiceImpl extends BaseMongoServiceImpl<Question, Long, QuestionRepository> implements QuestionService {
	@Autowired
	private AnswerService answerService = null;
	@Autowired
	private AnswerRecordService answerRecordService = null;
	@Autowired
	private RabbitService rabbitService = null;

	@Autowired
	public QuestionServiceImpl(QuestionRepository dao) {
		super(dao);
	}

	@Override
	public Page<Question> getPage(Map<String, Object> searchParams, int page, int size)   {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Question> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Question> builder = new PathBuilder<Question>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		if (predicate != null) {
			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		}
		Sort sort = new Sort(Direction.DESC, "id");
		Page<Question> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Question>("id") {
			// })
			List<Question> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Question>(items);

		}
		return res;
	}

	@Override
	public List<MQuestion> getMQuestions(Long questionnaireId) {
		List<MQuestion> mQuestions = new ArrayList<MQuestion>(0);
		Map searchQuestionParams = new HashMap();
		Rabbit rabbit = this.rabbitService.getCurrentLoginRabbit();
		searchQuestionParams.put("EQ|questionnaire.$id", questionnaireId);
		List<Question> questions = this.getPage(searchQuestionParams, 1, 0).getContent();
		if (CollectionUtils.isNotEmpty(questions)) {
			for (Question question : questions) {
				Map searchAnswerParams = new HashMap();
				searchAnswerParams.put("EQ|question.$id", question.getId());
				List<Answer> answers = this.answerService.getPage(searchAnswerParams, 1, 0).getContent();
				List<MAnswer> manswers = new ArrayList<MAnswer>(0);
				if (CollectionUtils.isNotEmpty(answers)) {
					for (Answer answer : answers) {
						MAnswer manswer = new MAnswer();
						manswer.setAnswer(answer);
						Map answerRecordSearchParams = new HashMap();
						answerRecordSearchParams.put("EQ|answerId", answer.getId());
						if (rabbit != null) {
							answerRecordSearchParams.put("EQ|rabbitId", rabbit.getId());
						}
						List<AnswerRecord> answerRecords = this.answerRecordService.getPage(answerRecordSearchParams, 1, 0).getContent();
						boolean isChecked = false;
						if (CollectionUtils.isNotEmpty(answerRecords)) {
							AnswerRecord answerRecord = answerRecords.get(0);
							isChecked = answerRecord.getIsChecked();
						}
						manswer.setIsChecked(isChecked);
						manswers.add(manswer);
					}
				}
				MQuestion mQuestion = new MQuestion();
				mQuestion.setQuestion(question);
				mQuestion.setManswers(manswers);
				if (question.getType().compareTo(2) == 0) {
					logger.debug(">>FaceYe trace -------------------------------------------Get answer text record...................................");
					Map searchAnswerRecordParams = new HashMap();
					searchAnswerRecordParams.put("EQ|questionId", question.getId());
					if (rabbit != null) {
						searchAnswerRecordParams.put("EQ|rabbitId", rabbit.getId());
					}
					List<AnswerRecord> answerRecords = this.answerRecordService.getPage(searchAnswerRecordParams, 1, 0).getContent();
					if (CollectionUtils.isNotEmpty(answerRecords)) {
						logger.debug(">>FaceYe answer text record is not empty ,size is:" + answerRecords.size());
						mQuestion.setAnswerTextRecords(answerRecords);
						logger.debug(">>FaceYe answer text record first text is:" + mQuestion.getAnswerTextRecords().get(0).getAnswerText());
					} else {
						logger.debug(">>FaceYe answer text record is emtpy.");
					}
					logger.debug(">>FaceYe trace -------------------------------------------Get answer text record...................................");
				}
				mQuestions.add(mQuestion);
			}
		}
		return mQuestions;
	}

}/** @generate-service-source@ **/
