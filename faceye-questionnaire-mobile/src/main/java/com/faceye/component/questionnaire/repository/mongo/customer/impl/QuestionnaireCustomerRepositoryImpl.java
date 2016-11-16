package com.faceye.component.questionnaire.repository.mongo.customer.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.faceye.component.questionnaire.entity.Questionnaire;
import com.faceye.component.questionnaire.repository.mongo.customer.QuestionnaireCustomerRepository;
import com.faceye.feature.repository.mongo.SequenceRepository;

@Repository
public class QuestionnaireCustomerRepositoryImpl implements QuestionnaireCustomerRepository {
	@Autowired
	private MongoOperations mongoOps = null;

	private Logger logger = LoggerFactory.getLogger(SequenceRepository.class);

	public void increaceQuestionnaireAccessCount(Questionnaire questionnaire) {
		if (questionnaire != null) {
			Query query = new Query();
			query.addCriteria(Criteria.where("id").is(questionnaire.getId()));
			Update update = new Update().inc("accessCount", 1);
			this.mongoOps.updateFirst(query, update, Questionnaire.class);
		}
	}

}
