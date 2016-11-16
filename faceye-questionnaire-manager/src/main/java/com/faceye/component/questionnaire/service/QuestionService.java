package com.faceye.component.questionnaire.service;

import java.util.List;
import java.util.Map;

import com.faceye.component.questionnaire.entity.Question;
import com.faceye.component.questionnaire.model.MQuestion;
import com.faceye.feature.service.BaseService;
/**
 * Question 服务接品<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface QuestionService extends BaseService<Question,Long>{


	public Question saveQuestion(Map params);
	
	/**
	 * 取得一个问卷的问题列表
	 * @param questionnaireId
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年5月31日 下午5:13:25
	 */
	public List<MQuestion> getMQuestions(Long questionnaireId);
}/**@generate-service-source@**/
