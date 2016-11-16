package com.faceye.component.questionnaire.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.faceye.component.questionnaire.entity.Answer;
import com.faceye.component.questionnaire.service.AnswerService;
import com.faceye.component.questionnaire.service.model.MAnswer;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.GlobalEntity;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.view.MessageBuilder;

/**
 * 模块:questionnaire<br>
 * 实体:Answer<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年12月10日<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/questionnaire/answer")
public class AnswerController extends BaseController<Answer, Long, AnswerService> {

	@Autowired
	public AnswerController(AnswerService service) {
		super(service);
	}

	/**
	 * 首页<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * 
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/home")
	@ResponseBody
	public List<MAnswer> home(HttpServletRequest request, Model model) {
		List<MAnswer> answers = new ArrayList<MAnswer>(0);
		Map searchParams = HttpUtil.getRequestParams(request);
		Long questionId = MapUtils.getLong(searchParams, "questionId");
		if (questionId != null) {
			searchParams.put("EQ|question.$id", questionId);
			Page<Answer> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
			if (page != null && CollectionUtils.isNotEmpty(page.getContent())) {
				for (Answer answer : page.getContent()) {
					MAnswer manswer = new MAnswer();
					manswer.setId(answer.getId());
					manswer.setMark(answer.getMark());
					manswer.setName(answer.getName());
					manswer.setSupportCount(answer.getSupportCount());
					manswer.setVal(answer.getVal());
					manswer.setQuestionId(answer.getQuestion().getId());
					answers.add(manswer);
				}
			}
		}
		return answers;
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * 
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		if (id != null) {
			beforeInput(model, request);
			Answer entity = this.service.get(id);
			model.addAttribute("answer", entity);
		}
		GlobalEntity global = new GlobalEntity();
		global.setTitle(this.getI18N("questionnaire.answer.edit"));
		model.addAttribute("global", global);
		return "questionnaire.answer.update";
	}

	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * 
	 * @param model<br>
	 * @return<br>
	 * @author:@haipenge<br>
	 * 						haipenge@gmail.com<br>
	 *                       2014年5月27日<br>
	 */
	@RequestMapping(value = "/input")
	public String input(Answer answer, Model model, HttpServletRequest request) {
		beforeInput(model, request);
		GlobalEntity global = new GlobalEntity();
		global.setTitle(this.getI18N("questionnaire.answer.add"));
		model.addAttribute("global", global);
		return "questionnaire.answer.update";
	}

	/**
	 * 数据保存<br>
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request) {
		Map params = HttpUtil.getRequestParams(request);
		Answer answer = this.service.saveAnswer(params);
		return AjaxResult.getInstance().buildDefaultResult(true);
		// if(bindingResult.hasErrors()){
		// beforeInput(model,request);
		// return "questionnaire.answer.update";
		// }else{
		// this.beforeSave(answer,request);
		// this.service.save(answer);
		// return "redirect:/questionnaire/answer/home";
		// }
	}

	/**
	 * 保存文本
	 * 
	 * @param request
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年6月2日 下午6:19:23
	 */
	@RequestMapping("/saveAnswerText")
	@ResponseBody
	public String saveAnswerText(HttpServletRequest request) {
		Map params = HttpUtil.getRequestParams(request);
		this.service.saveAnswerText(params);
		return AjaxResult.getInstance().buildDefaultResult(true);
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * 
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, RedirectAttributesModelMap model) {
		if (id != null) {
			Answer answer = this.service.get(id);
			if (answer != null) {
				if (beforeRemove(answer, model)) {
					this.service.remove(answer);
					// MessageBuilder.getInstance().setMessage(model,answer+" "+ this.getI18N("global.remove.success"));
				}
			}
		}
		return "redirect:/questionnaire/answer/home";
	}

	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * 
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required = true) String ids, RedirectAttributes redirectAttributes, Model model) {
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				Answer answer = this.service.get(Long.parseLong(id));
				if (answer != null) {
					if (beforeRemove(answer, model)) {
						this.service.remove(answer);
						// MessageBuilder.getInstance().setMessage(model,answer+" "+ this.getI18N("global.remove.success"));
					}
				}
			}
		}
		String messages = MessageBuilder.getInstance().getMessages(model);
		return AjaxResult.getInstance().buildDefaultResult(StringUtils.isEmpty(messages), messages);
	}

	/**
	 * 取得数据明细<br>
	 * @todo<br>
	 * 
	 * @param id<br>
	 * @param model<br>
	 * @return<br>
	 * @author:@haipenge<br>
	 * 						haipenge@gmail.com<br>
	 *                       2014年5月26日<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		if (id != null) {
			Answer answer = this.service.get(id);
			model.addAttribute("answer", answer);
		}
		return "questionnaire.answer.detail";
	}

	/////////////////////////////////////////////// 以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * 
	 * @param model<br>
	 * @param request<br>
	 * @author:@haipenge<br>
	 * 						haipenge@gmail.com<br>
	 *                       2015年4月5日<br>
	 */
	protected void beforeInput(Model model, HttpServletRequest request) {

	}

	/**
	 *
	 * 保存数据前的回调函数
	 */
	protected void beforeSave(Answer answer, HttpServletRequest request) {

	}

	/**
	 * 删除前 数据回调
	 */
	protected boolean beforeRemove(Answer answer, Model model) {
		boolean res = true;

		return res;
	}

}
