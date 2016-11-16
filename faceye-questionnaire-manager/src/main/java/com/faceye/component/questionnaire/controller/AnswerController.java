package com.faceye.component.questionnaire.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

import com.faceye.component.questionnaire.entity.Answer;
import com.faceye.component.questionnaire.service.AnswerService;



import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.MathUtil;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.regexp.RegexpUtil;
import com.faceye.feature.util.view.MessageBuilder;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.view.MessageBuilder;
import com.faceye.feature.util.GlobalEntity;

/**
 * 模块:questionnaire<br>
 * 实体:Answer<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年12月10日<br>
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
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Map searchParams=HttpUtil.getRequestParams(request);
		Page<Answer> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("questionnaire.answer"));
		model.addAttribute("global",global);
		return "questionnaire.answer.manager";
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		if(id!=null){
			beforeInput(model,request);
			Answer entity=this.service.get(id);
			model.addAttribute("answer", entity);
		}
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("questionnaire.answer.edit"));
		model.addAttribute("global",global);
		return "questionnaire.answer.update";
	}
	
	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * @param model<br>
	 * @return<br>
	 * @author:@haipenge<br>
	 * haipenge@gmail.com<br>
	 * 2014年5月27日<br>
	 */
	@RequestMapping(value="/input")
	public String input(Answer answer,Model model,HttpServletRequest request){
		beforeInput(model,request);
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("questionnaire.answer.add"));
		model.addAttribute("global",global);
		return "questionnaire.answer.update";
	}
	
	
    

	/**
	 * 数据保存<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid Answer answer,BindingResult bindingResult, RedirectAttributes redirectAttributes,Model model,HttpServletRequest request) {
		if(bindingResult.hasErrors()){
			beforeInput(model,request);
			return "questionnaire.answer.update";
		}else{
		   this.beforeSave(answer,request);
		   this.service.save(answer);
		   return "redirect:/questionnaire/answer/home";
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/remove/{id}")
	@ResponseBody
	public String remove(@PathVariable("id") Long id) {
		if(id!=null){
			this.service.remove(id);
//			Answer answer=this.service.get(id);
//			if(answer!=null){
//				if(beforeRemove(answer,model)){
//					this.service.remove(answer);	
//					//MessageBuilder.getInstance().setMessage(model,answer+" "+ this.getI18N("global.remove.success"));
//				}
//			}
		}
//		return "redirect:/questionnaire/answer/home";
		return AjaxResult.getInstance().buildDefaultResult(true);
	}
	
	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required=true) String  ids, RedirectAttributes redirectAttributes,Model model) {
		if(StringUtils.isNotEmpty(ids)){
			String [] idArray=ids.split(",");
			for(String id:idArray){
				Answer answer=this.service.get(Long.parseLong(id));
				if(answer!=null){
					if(beforeRemove(answer,model)){
						this.service.remove(answer);	
						//MessageBuilder.getInstance().setMessage(model,answer+" "+ this.getI18N("global.remove.success"));
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
	 * @param id<br>
	 * @param model<br>
	 * @return<br>
	 * @author:@haipenge<br>
	 * haipenge@gmail.com<br>
	 * 2014年5月26日<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id,Model model){
		if(id!=null){
			Answer answer=this.service.get(id);
			model.addAttribute("answer", answer);
		}
		return "questionnaire.answer.detail";
	}
	
	/**
	 * 
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年6月1日 下午5:39:01
	 */
	@RequestMapping("/initSupportCount")
	@ResponseBody
	public String initSupportCount(HttpServletRequest request){
	     Map params=HttpUtil.getRequestParams(request);
	     Long questionnaireId=MapUtils.getLong(params, "questionnaireId");
	     this.service.saveInitAnswerSupportCount(questionnaireId);
		return AjaxResult.getInstance().buildDefaultResult(true);
	}
	
	///////////////////////////////////////////////以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:@haipenge<br>
	 * haipenge@gmail.com<br>
	 * 2015年4月5日<br>
	 */
	protected void beforeInput(Model model,HttpServletRequest request){
		
	}
	/**
	 *
	 *保存数据前的回调函数
	 */
	protected void beforeSave(Answer answer,HttpServletRequest request){
		
	}
	/**
	 * 删除前 数据回调
	 */
	protected boolean beforeRemove(Answer answer,Model model){
		boolean res=true;
		
		return res;
	}

}
