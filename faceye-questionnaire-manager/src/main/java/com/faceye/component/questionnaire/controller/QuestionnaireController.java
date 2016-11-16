package com.faceye.component.questionnaire.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.faceye.component.questionnaire.entity.Questionnaire;
import com.faceye.component.questionnaire.entity.QuestionnaireType;
import com.faceye.component.questionnaire.model.MQuestion;
import com.faceye.component.questionnaire.service.QuestionService;
import com.faceye.component.questionnaire.service.QuestionnaireService;
import com.faceye.component.questionnaire.service.QuestionnaireTypeService;
import com.faceye.component.weixin.entity.Account;
import com.faceye.component.weixin.service.AccountService;
import com.faceye.component.weixin.util.WeixinConstants;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.entity.UploadFile;
import com.faceye.feature.service.UploadFileService;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.GlobalEntity;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.view.MessageBuilder;

/**
 * 模块:questionnaire<br>
 * 实体:Questionnaire<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年12月10日<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/questionnaire/questionnaire")
public class QuestionnaireController extends BaseController<Questionnaire, Long, QuestionnaireService> {
	@Autowired
	private QuestionService questionService = null;
	@Autowired
	private AccountService accountService = null;
	@Autowired
	private QuestionnaireTypeService questionnaireTypeService = null;
	@Autowired
	private UploadFileService uploadFileService = null;
	@Autowired
	public QuestionnaireController(QuestionnaireService service) {
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
	public String home(HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		Page<Questionnaire> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		List<Questionnaire> questionnaires=this.wrapQuestionnaires(page.getContent());
		model.addAttribute("questionnaires", questionnaires);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		GlobalEntity global = new GlobalEntity();
		global.setTitle(this.getI18N("questionnaire.questionnaire"));
		model.addAttribute("global", global);
		List<Account> accounts = this.accountService.getAll();
		model.addAttribute("accounts", accounts);
		List<QuestionnaireType> questionnaireTypes = this.questionnaireTypeService.getAll();
		model.addAttribute("questionnaireTypes", questionnaireTypes);
		return "questionnaire.questionnaire.manager";
	}
	
	private List<Questionnaire> wrapQuestionnaires(List<Questionnaire> questionnaires) {
		List<Questionnaire> result = new ArrayList<Questionnaire>(0);
		if (CollectionUtils.isNotEmpty(questionnaires)) {
			for (Questionnaire questionnaire : questionnaires) {
				questionnaire=this.wrapQuestionnaire(questionnaire);
				result.add(questionnaire);
			}
		}
		return result;
	}
	
	private Questionnaire wrapQuestionnaire(Questionnaire questionnaire){
		if(questionnaire!=null){
			List<UploadFile> uploadFiles = this.uploadFileService.getUploadFilesByTargetEntityIdAndTargetEntityClass(questionnaire.getId(), Questionnaire.class.getName());
			if (CollectionUtils.isNotEmpty(uploadFiles)) {
				logger.debug(">>FaceYe get uploadFiles size is:"+uploadFiles.size()+",url ++ is:"+uploadFiles.get(0).getUrl()+"store path is:"+uploadFiles.get(0).getStorePath());
				questionnaire.setUploadFiles(uploadFiles);
//				logger.debug(">>Is -1 Empty?"+CollectionUtils.isEmpty(questionnaire.getUploadFiles()));
//				this.service.save(questionnaire);
//				logger.debug(">>FaceYe get uploadFiles size is:"+uploadFiles.size()+",url is:"+uploadFiles.get(0).getUrl());
//				logger.debug(">>Is Empty?"+CollectionUtils.isEmpty(questionnaire.getUploadFiles()));
//				logger.debug(">>FaceYe get uploadFiles size is:"+uploadFiles.size()+",url-- is:"+questionnaire.getUploadFiles().get(0).getUrl());
			}
		}
		return questionnaire;
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
			Questionnaire entity = this.service.get(id);
			model.addAttribute("questionnaire", entity);
		}
		GlobalEntity global = new GlobalEntity();
		global.setTitle(this.getI18N("questionnaire.questionnaire.edit"));
		model.addAttribute("global", global);
		return "questionnaire.questionnaire.update";
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
	public String input(Questionnaire questionnaire, Model model, HttpServletRequest request) {
		beforeInput(model, request);
		GlobalEntity global = new GlobalEntity();
		global.setTitle(this.getI18N("questionnaire.questionnaire.add"));
		model.addAttribute("global", global);
		return "questionnaire.questionnaire.update";
	}

	/**
	 * 数据保存<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid Questionnaire questionnaire, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			beforeInput(model, request);
			return "questionnaire.questionnaire.update";
		} else {
			this.beforeSave(questionnaire, request);
			this.service.save(questionnaire);
			return "redirect:/questionnaire/questionnaire/detail/" + questionnaire.getId();
		}
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
			Questionnaire questionnaire = this.service.get(id);
			if (questionnaire != null) {
				if (beforeRemove(questionnaire, model)) {
					this.service.remove(questionnaire);
					// MessageBuilder.getInstance().setMessage(model,questionnaire+" "+ this.getI18N("global.remove.success"));
				}
			}
		}
		return "redirect:/questionnaire/questionnaire/home";
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
				Questionnaire questionnaire = this.service.get(Long.parseLong(id));
				if (questionnaire != null) {
					if (beforeRemove(questionnaire, model)) {
						this.service.remove(questionnaire);
						// MessageBuilder.getInstance().setMessage(model,questionnaire+" "+ this.getI18N("global.remove.success"));
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
			Questionnaire questionnaire = this.service.get(id);
			model.addAttribute("questionnaire", questionnaire);
			List<MQuestion> mQuestions = this.questionService.getMQuestions(questionnaire.getId());
			model.addAttribute("mQuestions", mQuestions);
		}
		return "questionnaire.questionnaire.detail";
	}

	@RequestMapping("/lock")
	// @ResponseBody
	public String lock(HttpServletRequest request) {
		Map params = HttpUtil.getRequestParams(request);
		Long id = MapUtils.getLong(params, "id");
		Boolean isLocked = MapUtils.getBoolean(params, "isLocked");
		Questionnaire questionnaire = this.service.get(id);
		questionnaire.setIsLocked(isLocked);
		this.service.save(questionnaire);
		return "redirect:/questionnaire/questionnaire/home";
	}

	/**
	 * 关闭问卷
	 * 
	 * @param request
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年6月2日 下午3:43:01
	 */
	@RequestMapping("/close")
	public String close(HttpServletRequest request) {
		Map params = HttpUtil.getRequestParams(request);
		Long id = MapUtils.getLong(params, "id");
		Boolean isClosed = MapUtils.getBoolean(params, "isClosed");
		Questionnaire questionnaire = this.service.get(id);
		questionnaire.setIsClosed(isClosed);
		this.service.save(questionnaire);
		return "redirect:/questionnaire/questionnaire/home";
	}

	@RequestMapping("/pushQuestionnaire2Weixin")
	@ResponseBody
	public String pushQuestionnaire2Weixin(@RequestParam(required = true) String accountIds, @RequestParam(required = true) String questionnaireIds, HttpServletRequest request) {
		this.service.pushQuestionnaire2Weixin(accountIds, questionnaireIds);
		return AjaxResult.getInstance().buildDefaultResult(true, "");
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
	protected void beforeSave(Questionnaire questionnaire, HttpServletRequest request) {
		Account account = (Account) request.getSession().getAttribute(WeixinConstants.WEIXIN_ACCOUNT_SESSION_KEY);
		if (account != null) {
			// questionnaire.setAccount(account);
		}
	}

	/**
	 * 删除前 数据回调
	 */
	protected boolean beforeRemove(Questionnaire questionnaire, Model model) {
		boolean res = true;

		return res;
	}

}
