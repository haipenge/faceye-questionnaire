package com.faceye.component.questionnaire.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.questionnaire.entity.AnswerStat;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * AnswerStat 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface AnswerStatRepository extends BaseMongoRepository<AnswerStat,Long> {
	
	
}/**@generate-repository-source@**/
