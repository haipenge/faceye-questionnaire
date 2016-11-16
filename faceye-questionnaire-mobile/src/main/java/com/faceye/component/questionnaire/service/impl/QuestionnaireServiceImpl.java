package com.faceye.component.questionnaire.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.faceye.component.questionnaire.entity.Questionnaire;
import com.faceye.component.questionnaire.repository.mongo.QuestionnaireRepository;
import com.faceye.component.questionnaire.repository.mongo.customer.QuestionnaireCustomerRepository;
import com.faceye.component.questionnaire.service.QuestionnaireService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.ServiceException;
import com.querydsl.core.types.Predicate;

@Service
public class QuestionnaireServiceImpl extends BaseMongoServiceImpl<Questionnaire, Long, QuestionnaireRepository> implements QuestionnaireService {
	@Autowired
	private QuestionnaireCustomerRepository questionnaireCustomerRepository = null;

	@Autowired
	public QuestionnaireServiceImpl(QuestionnaireRepository dao) {
		super(dao);
	}

	@Override
	public Page<Questionnaire> getPage(Map<String, Object> searchParams, int page, int size) throws ServiceException {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Questionnaire> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Questionnaire> builder = new PathBuilder<Questionnaire>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		if (predicate != null) {
			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		}
		Sort sort = new Sort(Direction.ASC, "id");
		Page<Questionnaire> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Questionnaire>("id") {
			// })
			List<Questionnaire> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Questionnaire>(items);

		}
		return res;
	}

	@Override
	public void updateQuestionnaire2IncreateAccessCount(Questionnaire questionnaire) {
		this.questionnaireCustomerRepository.increaceQuestionnaireAccessCount(questionnaire);
//		this.dao.increaceQuestionnaireAccessCount(questionnaire);
	}

}/** @generate-service-source@ **/
