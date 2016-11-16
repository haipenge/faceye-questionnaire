package com.faceye.component.questionnaire.entity;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * AnswerStat ORM 实体<br>
 * 数据库表:questionnaire_answerStat<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection="questionnaire_answerStat")
public class AnswerStat implements Serializable {
	private static final long serialVersionUID = 8926119711730830203L;
	@Id
	private  Long id=null;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

   /**
    * 说明:标题<br>
    * 属性名: title<br>
    * 类型: String<br>
    * 数据库字段:title<br>
    * @author haipenge<br>
    */
    
	private  String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

	
   /**
    * 说明:子标题<br>
    * 属性名: stitle<br>
    * 类型: String<br>
    * 数据库字段:stitle<br>
    * @author haipenge<br>
    */
    
	private  String stitle;
	public String getStitle() {
		return stitle;
	}
	public void setStitle(String stitle) {
		this.stitle = stitle;
	}
	

	
   /**
    * 说明:备注<br>
    * 属性名: remark<br>
    * 类型: String<br>
    * 数据库字段:remark<br>
    * @author haipenge<br>
    */
    
	private  String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@DBRef
	private Questionnaire questionnaire=null;
	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}
	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}
	
	
	
}/**@generate-entity-source@**/
	
