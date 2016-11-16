package com.faceye.component.questionnaire.service;

import com.faceye.component.questionnaire.entity.Answer;
import com.faceye.feature.service.BaseService;
/**
 * Answer 服务接品<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface AnswerService extends BaseService<Answer,Long>{

	public void saveInitAnswerSupportCount(Long questionnaireId);
}/**@generate-service-source@**/
