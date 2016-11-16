package com.faceye.component.questionnaire.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.faceye.component.questionnaire.entity.Questionnaire;
import com.faceye.component.questionnaire.model.MAnswer;
import com.faceye.component.questionnaire.model.MQuestion;
import com.faceye.component.questionnaire.repository.mongo.QuestionRepository;
import com.faceye.component.questionnaire.service.AnswerRecordService;
import com.faceye.component.questionnaire.service.AnswerService;
import com.faceye.component.questionnaire.service.QuestionService;
import com.faceye.component.questionnaire.service.QuestionnaireService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.ServiceException;
import com.querydsl.core.types.Predicate;

@Service
public class QuestionServiceImpl extends BaseMongoServiceImpl<Question, Long, QuestionRepository> implements QuestionService {

	@Autowired
	private AnswerService answerService = null;
	@Autowired
	private QuestionnaireService questionnaireService = null;
	@Autowired
	private AnswerRecordService answerRecordService = null;

	@Autowired
	public QuestionServiceImpl(QuestionRepository dao) {
		super(dao);
	}

	@Override
	public Page<Question> getPage(Map<String, Object> searchParams, int page, int size) throws ServiceException {
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
	public Question saveQuestion(Map params) {
		Integer answerIndex = 0;
		Long questionnaireId = MapUtils.getLong(params, "questionnaireId");
		String name = MapUtils.getString(params, "name");
		Boolean isMultiSelect = MapUtils.getBoolean(params, "isMultiSelect");
		Integer type = MapUtils.getInteger(params, "type");
		String answer0 = MapUtils.getString(params, "answer0");
		String answer1 = MapUtils.getString(params, "answer1");
		String answer2 = MapUtils.getString(params, "answer2");
		String answer3 = MapUtils.getString(params, "answer3");
		String answer4 = MapUtils.getString(params, "answer4");
		String answer5 = MapUtils.getString(params, "answer5");
		String answer6 = MapUtils.getString(params, "answer6");
		String answer7 = MapUtils.getString(params, "answer7");
		String answer8 = MapUtils.getString(params, "answer8");
		String answer9 = MapUtils.getString(params, "answer9");
		String answer10 = MapUtils.getString(params, "answer10");
		Questionnaire questionnaire = this.questionnaireService.get(questionnaireId);
		if (questionnaire != null) {
			Question question = new Question();
			// question.setIsMultiSelect(isMultiSelect);
			question.setType(type);
			question.setName(name);
			question.setQuestionnaire(questionnaire);
			this.save(question);
			this.saveAnswer(question, answer0);
			this.saveAnswer(question, answer1);
			this.saveAnswer(question, answer2);
			this.saveAnswer(question, answer3);
			this.saveAnswer(question, answer4);
			this.saveAnswer(question, answer5);
			this.saveAnswer(question, answer6);
			this.saveAnswer(question, answer7);
			this.saveAnswer(question, answer8);
			this.saveAnswer(question, answer9);
			this.saveAnswer(question, answer10);
		}

		return null;
	}

	/**
	 * 保存问题
	 * 
	 * @param question
	 * @param answerName
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年5月31日 下午5:01:50
	 */
	private void saveAnswer(Question question, String answerName) {
		if (StringUtils.isNotEmpty(answerName)) {
			Answer answer = new Answer();
			answer.setQuestion(question);
			answer.setName(answerName);
			this.answerService.save(answer);
		}
	}

	@Override
	public List<MQuestion> getMQuestions(Long questionnaireId) {
		List<MQuestion> mQuestions = new ArrayList<MQuestion>(0);
		Map searchQuestionParams = new HashMap();
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
						manswers.add(manswer);
					}
				}
				MQuestion mQuestion = new MQuestion();
				mQuestion.setQuestion(question);
				mQuestion.setManswers(manswers);
				if (question.getType().compareTo(2) == 0) {
					Map searchAnswerRecordParams = new HashMap();
					searchAnswerRecordParams.put("EQ|questionId", question.getId());
					// searchAnswerRecordParams.put("EQ|rabbitId", rabbit.getId());
					List<AnswerRecord> answerRecords = this.answerRecordService.getPage(searchAnswerRecordParams, 1, 0).getContent();
					mQuestion.setAnswerTextRecords(answerRecords);
				}

				mQuestions.add(mQuestion);
			}
		}
		return mQuestions;
	}

	@Override
	public void remove(Question question) {
		Map searchAnswerParams = new HashMap();
		searchAnswerParams.put("EQ|question.$id", question.getId());
		List<Answer> answers = this.answerService.getPage(searchAnswerParams, 1, 0).getContent();
		if (CollectionUtils.isNotEmpty(answers)) {
			for (Answer answer : answers) {
				this.answerService.remove(answer.getId());
			}
		}
		super.remove(question);
	}
}/** @generate-service-source@ **/
