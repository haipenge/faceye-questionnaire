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

import com.faceye.component.questionnaire.entity.AnswerStat;
import com.faceye.component.questionnaire.service.AnswerStatService;
import com.faceye.component.questionnaire.service.model.MAnswerStat;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.http.HttpUtil;

/**
 * 模块:questionnaire<br>
 * 实体:AnswerStat<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年12月10日<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/questionnaire/answerStat")
public class AnswerStatController extends BaseController<AnswerStat, Long, AnswerStatService> {

	@Autowired
	public AnswerStatController(AnswerStatService service) {
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
	public List<MAnswerStat> home(HttpServletRequest request, Model model) {
		List<MAnswerStat> stats = new ArrayList<MAnswerStat>(0);
		Map searchParams = HttpUtil.getRequestParams(request);
		Long questionnaireId = MapUtils.getLong(searchParams, "questionnaireId");
		if (null != questionnaireId) {
			searchParams.put("EQ|questionnaire.$id", questionnaireId);
			Page<AnswerStat> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
			if(page!=null && CollectionUtils.isNotEmpty(page.getContent())){
				for(AnswerStat stat:page.getContent()){
					MAnswerStat mstat=new MAnswerStat();
					mstat.setId(stat.getId());
					mstat.setQuestionnaireId(stat.getQuestionnaire().getId());
					mstat.setRemark(stat.getRemark());
					mstat.setTitle(stat.getTitle());
					mstat.setStitle(stat.getStitle());
					stats.add(mstat);
				}
			}
		}
		return stats;
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
			AnswerStat entity = this.service.get(id);
			model.addAttribute("answerStat", entity);
		}
		return "questionnaire.answerStat.update";
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
	public String input(AnswerStat answerStat, Model model, HttpServletRequest request) {
		beforeInput(model, request);
		return "questionnaire.answerStat.update";
	}

	/**
	 * 数据保存<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid AnswerStat answerStat, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			beforeInput(model, request);
			return "questionnaire.answerStat.update";
		} else {
			this.beforeSave(answerStat, request);
			this.service.save(answerStat);
			return "redirect:/questionnaire/answerStat/home";
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
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if (id != null) {
			this.service.remove(id);
		}
		return "redirect:/questionnaire/answerStat/home";
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
	public String remove(@RequestParam(required = true) String ids, RedirectAttributes redirectAttributes) {
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				this.service.remove(Long.parseLong(id));
			}
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
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
			AnswerStat entity = this.service.get(id);
			model.addAttribute("answerStat", entity);
		}
		return "questionnaire.answerStat.detail";
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
	protected void beforeSave(AnswerStat answerStat, HttpServletRequest request) {

	}

}
