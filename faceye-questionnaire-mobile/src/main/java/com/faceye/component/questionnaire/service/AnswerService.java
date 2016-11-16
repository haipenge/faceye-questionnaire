package com.faceye.component.questionnaire.service;

import java.util.Map;

import com.faceye.component.questionnaire.entity.Answer;
import com.faceye.feature.service.BaseService;

/**
 * Answer 服务接品<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月20日<br>
 */
public interface AnswerService extends BaseService<Answer, Long> {
	/**
	 * 保存投票
	 * 
	 * @param params
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年6月1日 上午10:18:41
	 */
	public Answer saveAnswer(Map params);
	
	/**
	 * 保存文本回复
	 * @param params
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年6月2日 下午6:20:24
	 */
	public Answer saveAnswerText(Map params);
	
	/**
	 * 初始化支持数
	 * @param questionnaireId
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年6月1日 下午5:40:36
	 */
	public void saveInitAnswerSupportCount(Long questionnaireId);
}/** @generate-service-source@ **/
