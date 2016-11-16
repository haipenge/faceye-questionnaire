package com.faceye.component.questionnaire.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.questionnaire.entity.Answer;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Answer 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface AnswerRepository extends BaseMongoRepository<Answer,Long> {
	
	
}/**@generate-repository-source@**/
