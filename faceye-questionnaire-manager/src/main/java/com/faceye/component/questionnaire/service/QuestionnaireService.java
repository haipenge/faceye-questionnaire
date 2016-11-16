package com.faceye.component.questionnaire.service;

import com.faceye.component.questionnaire.entity.Questionnaire;
import com.faceye.feature.service.BaseService;
/**
 * Questionnaire 服务接品<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface QuestionnaireService extends BaseService<Questionnaire,Long>{

	/**
	 * 将问卷推送到指定的微信
	 * @param accountIds
	 * @param questionnaireId
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年7月13日 下午4:37:07
	 */
	public boolean pushQuestionnaire2Weixin(String accountIds,String questionnaireIds);
}/**@generate-service-source@**/
