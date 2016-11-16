package com.faceye.component.questionnaire.entity;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * Question ORM 实体<br>
 * 数据库表:questionnaire_question<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection="questionnaire_question")
public class Question implements Serializable {
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
    * 说明:题目<br>
    * 属性名: name<br>
    * 类型: String<br>
    * 数据库字段:name<br>
    * @author haipenge<br>
    */
    
	private  String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	
   /**
    * 说明:是否多选<br>
    * 属性名: isMultiSelect<br>
    * 类型: Boolean<br>
    * 数据库字段:is_multi_select<br>
    * @author haipenge<br>
    */
    
	private  Boolean isMultiSelect=false;
	public Boolean getIsMultiSelect() {
		return isMultiSelect;
	}
	public void setIsMultiSelect(Boolean isMultiSelect) {
		this.isMultiSelect = isMultiSelect;
	}
	
	/**
	 * 类型:0,单选，1，多选 ，2，填空
	 */
	private Integer type=0;
	
   public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

/**
    * 说明:编号<br>
    * 属性名: num<br>
    * 类型: Integer<br>
    * 数据库字段:num<br>
    * @author haipenge<br>
    */
    
	private  Integer num;
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
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
	
