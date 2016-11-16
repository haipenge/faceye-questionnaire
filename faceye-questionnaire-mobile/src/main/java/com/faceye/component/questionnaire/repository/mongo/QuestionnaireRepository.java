package com.faceye.component.questionnaire.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.questionnaire.entity.Questionnaire;
import com.faceye.component.questionnaire.repository.mongo.customer.QuestionnaireCustomerRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Questionnaire 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface QuestionnaireRepository extends BaseMongoRepository<Questionnaire,Long> {
	
	
}/**@generate-repository-source@**/
