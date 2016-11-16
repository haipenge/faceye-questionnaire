package com.faceye.component.questionnaire.repository.mongo;

import com.faceye.component.questionnaire.entity.Rabbit;
import com.faceye.component.weixin.entity.WeixinUser;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Rabbit 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface RabbitRepository extends BaseMongoRepository<Rabbit,Long> {
	
	public Rabbit getRabbitByWeixinUser(WeixinUser weixinUser);
}/**@generate-repository-source@**/
