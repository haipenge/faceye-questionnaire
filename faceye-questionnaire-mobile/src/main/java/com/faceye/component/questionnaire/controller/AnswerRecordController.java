package com.faceye.component.questionnaire.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.faceye.component.questionnaire.entity.AnswerRecord;
import com.faceye.component.questionnaire.service.AnswerRecordService;



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
 * 实体:AnswerRecord<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年12月10日<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/questionnaire/answerRecord")
public class AnswerRecordController extends BaseController<AnswerRecord, Long, AnswerRecordService> {

	@Autowired
	public AnswerRecordController(AnswerRecordService service) {
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
		Page<AnswerRecord> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("questionnaire.answerRecord"));
		model.addAttribute("global",global);
		return "questionnaire.answerRecord.manager";
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
			AnswerRecord entity=this.service.get(id);
			model.addAttribute("answerRecord", entity);
		}
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("questionnaire.answerRecord.edit"));
		model.addAttribute("global",global);
		return "questionnaire.answerRecord.update";
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
	public String input(AnswerRecord answerRecord,Model model,HttpServletRequest request){
		beforeInput(model,request);
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("questionnaire.answerRecord.add"));
		model.addAttribute("global",global);
		return "questionnaire.answerRecord.update";
	}
	
	
    

	/**
	 * 数据保存<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid AnswerRecord answerRecord,BindingResult bindingResult, RedirectAttributes redirectAttributes,Model model,HttpServletRequest request) {
		if(bindingResult.hasErrors()){
			beforeInput(model,request);
			return "questionnaire.answerRecord.update";
		}else{
		   this.beforeSave(answerRecord,request);
		   this.service.save(answerRecord);
		   return "redirect:/questionnaire/answerRecord/home";
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
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes,RedirectAttributesModelMap model) {
		if(id!=null){
			AnswerRecord answerRecord=this.service.get(id);
			if(answerRecord!=null){
				if(beforeRemove(answerRecord,model)){
					this.service.remove(answerRecord);	
					//MessageBuilder.getInstance().setMessage(model,answerRecord+" "+ this.getI18N("global.remove.success"));
				}
			}
		}
		return "redirect:/questionnaire/answerRecord/home";
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
				AnswerRecord answerRecord=this.service.get(Long.parseLong(id));
				if(answerRecord!=null){
					if(beforeRemove(answerRecord,model)){
						this.service.remove(answerRecord);	
						//MessageBuilder.getInstance().setMessage(model,answerRecord+" "+ this.getI18N("global.remove.success"));
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
			AnswerRecord answerRecord=this.service.get(id);
			model.addAttribute("answerRecord", answerRecord);
		}
		return "questionnaire.answerRecord.detail";
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
	protected void beforeSave(AnswerRecord answerRecord,HttpServletRequest request){
		
	}
	/**
	 * 删除前 数据回调
	 */
	protected boolean beforeRemove(AnswerRecord answerRecord,Model model){
		boolean res=true;
		
		return res;
	}

}
