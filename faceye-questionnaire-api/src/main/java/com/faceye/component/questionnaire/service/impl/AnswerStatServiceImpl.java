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

import com.faceye.component.questionnaire.entity.AnswerStat;
import com.faceye.component.questionnaire.repository.mongo.AnswerStatRepository;
import com.faceye.component.questionnaire.service.AnswerStatService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.querydsl.core.types.Predicate;
@Service
public class AnswerStatServiceImpl extends BaseMongoServiceImpl<AnswerStat, Long, AnswerStatRepository> implements AnswerStatService {

	@Autowired
	public AnswerStatServiceImpl(AnswerStatRepository dao) {
		super(dao);
	}
	
	
	@Override
	public Page<AnswerStat> getPage(Map<String, Object> searchParams, int page, int size)   {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<AnswerStat> entityPath = resolver.createPath(entityClass);
		// PathBuilder<AnswerStat> builder = new PathBuilder<AnswerStat>(entityPath.getType(), entityPath.getMetadata());
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
		Page<AnswerStat> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<AnswerStat>("id") {
			// })
			List<AnswerStat> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<AnswerStat>(items);

		}
		return res;
	}
	
}/**@generate-service-source@**/
