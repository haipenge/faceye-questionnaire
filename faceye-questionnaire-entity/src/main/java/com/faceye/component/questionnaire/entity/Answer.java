package com.faceye.component.questionnaire.entity;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Answer ORM 实体<br>
 * 数据库表:questionnaire_answer<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月21日<br>
 */
@Document(collection = "questionnaire_answer")
public class Answer implements Serializable {
	private static final long serialVersionUID = 8926119711730830203L;
	@Id
	private Long id = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 说明:答案<br>
	 * 属性名: name<br>
	 * 类型: String<br>
	 * 数据库字段:name<br>
	 * 
	 * @author haipenge<br>
	 */

	private String name = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 说明:标记（A,B,C...?)<br>
	 * 属性名: mark<br>
	 * 类型: String<br>
	 * 数据库字段:mark<br>
	 * 
	 * @author haipenge<br>
	 */

	private String mark = "";

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	/**
	 * 答案对应的值
	 */
	private String val = "";

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	/**
	 * 说明:支持人数<br>
	 * 属性名: supportCount<br>
	 * 类型: Integer<br>
	 * 数据库字段:support_count<br>
	 * 
	 * @author haipenge<br>
	 */

	private Integer supportCount = 0;

	public Integer getSupportCount() {
		return supportCount;
	}

	public void setSupportCount(Integer supportCount) {
		this.supportCount = supportCount;
	}

	@DBRef
	private Question question = null;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}/** @generate-entity-source@ **/
