package com.faceye.component.questionnaire.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faceye.component.questionnaire.entity.Rabbit;
import com.faceye.component.weixin.entity.WeixinUser;
import com.faceye.feature.service.BaseService;
/**
 * Rabbit 服务接品<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface RabbitService extends BaseService<Rabbit,Long>{

	public Rabbit getRabbitByWeixinUser(WeixinUser weixinUser);
	
	public Rabbit getCurrentLoginRabbit();
	
	/**
	 * 微信授权回调
	 * @param appid
	 * @param code
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年5月17日 下午3:04:22
	 */
	public void onWeixinOAuth(HttpServletRequest request,HttpServletResponse response);
}/**@generate-service-source@**/
