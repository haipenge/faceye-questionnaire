package com.faceye.component.questionnaire.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * AnswerRecord ORM 实体<br>
 * 数据库表:questionnaire_answerRecord<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection="questionnaire_answer_record")
public class AnswerRecord implements Serializable {
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
    * 说明:用户<br>
    * 属性名: rabbitId<br>
    * 类型: Long<br>
    * 数据库字段:rabbit_id<br>
    * @author haipenge<br>
    */
    
	private  Long rabbitId;
	public Long getRabbitId() {
		return rabbitId;
	}
	public void setRabbitId(Long rabbitId) {
		this.rabbitId = rabbitId;
	}
	

	
   /**
    * 说明:问卷编号<br>
    * 属性名: questionnaireId<br>
    * 类型: Long<br>
    * 数据库字段:questionnaire_id<br>
    * @author haipenge<br>
    */
    
	private  Long questionnaireId;
	public Long getQuestionnaireId() {
		return questionnaireId;
	}
	public void setQuestionnaireId(Long questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	

	
   /**
    * 说明:问题编号<br>
    * 属性名: questionId<br>
    * 类型: Long<br>
    * 数据库字段:question_id<br>
    * @author haipenge<br>
    */
    
	private  Long questionId;
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	

	
   /**
    * 说明:答案编号<br>
    * 属性名: answerId<br>
    * 类型: Long<br>
    * 数据库字段:answer_id<br>
    * @author haipenge<br>
    */
    
	private  Long answerId;
	public Long getAnswerId() {
		return answerId;
	}
	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}
	

	
   /**
    * 说明:回答日期<br>
    * 属性名: createDate<br>
    * 类型: Date<br>
    * 数据库字段:create_date<br>
    * @author haipenge<br>
    */
    
	private  Date createDate=new Date();
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 是否选中
	 */
	private Boolean isChecked=false;
	public Boolean getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}
	
	private String answerText="";
	public String getAnswerText() {
		return answerText;
	}
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	
	
	

}/**@generate-entity-source@**/
	
