package com.faceye.component.questionnaire.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.questionnaire.entity.QuestionnaireType;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * QuestionnaireType 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface QuestionnaireTypeRepository extends BaseMongoRepository<QuestionnaireType,Long> {
	
	
}/**@generate-repository-source@**/
