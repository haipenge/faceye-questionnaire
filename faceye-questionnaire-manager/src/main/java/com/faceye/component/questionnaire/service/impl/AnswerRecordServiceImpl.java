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

import com.faceye.component.questionnaire.entity.AnswerRecord;
import com.faceye.component.questionnaire.repository.mongo.AnswerRecordRepository;
import com.faceye.component.questionnaire.service.AnswerRecordService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
 
import com.querydsl.core.types.Predicate;
@Service
public class AnswerRecordServiceImpl extends BaseMongoServiceImpl<AnswerRecord, Long, AnswerRecordRepository> implements AnswerRecordService {

	@Autowired
	public AnswerRecordServiceImpl(AnswerRecordRepository dao) {
		super(dao);
	}
	
	
	@Override
	public Page<AnswerRecord> getPage(Map<String, Object> searchParams, int page, int size) {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<AnswerRecord> entityPath = resolver.createPath(entityClass);
		// PathBuilder<AnswerRecord> builder = new PathBuilder<AnswerRecord>(entityPath.getType(), entityPath.getMetadata());
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
		Page<AnswerRecord> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<AnswerRecord>("id") {
			// })
			List<AnswerRecord> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<AnswerRecord>(items);

		}
		return res;
	}
	
}/**@generate-service-source@**/
