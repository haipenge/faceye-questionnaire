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

	public void updateQuestionnaire2IncreateAccessCount(Questionnaire questionnaire);
}/**@generate-service-source@**/
