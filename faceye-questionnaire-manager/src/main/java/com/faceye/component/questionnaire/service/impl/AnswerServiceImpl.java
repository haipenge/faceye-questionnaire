package com.faceye.component.questionnaire.service.impl;

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
import com.faceye.component.questionnaire.repository.mongo.AnswerRepository;
import com.faceye.component.questionnaire.service.AnswerRecordService;
import com.faceye.component.questionnaire.service.AnswerService;
import com.faceye.component.questionnaire.service.QuestionService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.ServiceException;
import com.querydsl.core.types.Predicate;

@Service
public class AnswerServiceImpl extends BaseMongoServiceImpl<Answer, Long, AnswerRepository> implements AnswerService {
	@Autowired
	private AnswerRecordService answerRecordService = null;
	@Autowired
	private QuestionService questionService = null;

	@Autowired
	public AnswerServiceImpl(AnswerRepository dao) {
		super(dao);
	}

	@Override
	public Page<Answer> getPage(Map<String, Object> searchParams, int page, int size)  {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Answer> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Answer> builder = new PathBuilder<Answer>(entityPath.getType(), entityPath.getMetadata());
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
		Page<Answer> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Answer>("id") {
			// })
			List<Answer> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Answer>(items);

		}
		return res;
	}

	@Override
	public void saveInitAnswerSupportCount(Long questionnaireId) {
		Map searchParams = new HashMap();
		searchParams.put("EQ|questionnaire.$id", questionnaireId);
		List<Question> questions = this.questionService.getPage(searchParams, 1, 0).getContent();
		if (CollectionUtils.isNotEmpty(questions)) {
			for (Question question : questions) {
				Map answerSearchParams = new HashMap();
				answerSearchParams.put("EQ|question.$id", question.getId());
				List<Answer> answers = this.getPage(answerSearchParams, 1, 0).getContent();
				if (CollectionUtils.isNotEmpty(answers)) {
					for (Answer answer : answers) {
						answer.setSupportCount(0);
						this.save(answer);
					}
				}
			}
		}
		Map searchAnswerRecordsParams = new HashMap();
		searchAnswerRecordsParams.put("EQ|questionnaireId", questionnaireId);
		List<AnswerRecord> answerRecords = this.answerRecordService.getPage(searchAnswerRecordsParams, 1, 0).getContent();
		if (CollectionUtils.isNotEmpty(answerRecords)) {
			this.answerRecordService.removeInBatch(answerRecords);
		}
	}

	@Override
	public void remove(Long id) {
		Answer answer = this.get(id);
		if (answer != null) {
			Map searchAnswerRecordParms = new HashMap();
			searchAnswerRecordParms.put("EQ|answerId", answer.getId());
			List<AnswerRecord> answerRecords = this.answerRecordService.getPage(searchAnswerRecordParms, 1, 0).getContent();
			if (CollectionUtils.isNotEmpty(answerRecords)) {
				this.answerRecordService.removeInBatch(answerRecords);
			}
			super.remove(answer);
		}
		
	}

}/** @generate-service-source@ **/
