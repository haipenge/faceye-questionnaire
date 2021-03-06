package com.faceye.component.questionnaire.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.questionnaire.entity.Question;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Question 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface QuestionRepository extends BaseMongoRepository<Question,Long> {
	
	
}/**@generate-repository-source@**/
