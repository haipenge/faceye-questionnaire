package com.faceye.component.questionnaire.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.questionnaire.entity.Discuss;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Discuss 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface DiscussRepository extends BaseMongoRepository<Discuss,Long> {
	
	
}/**@generate-repository-source@**/
