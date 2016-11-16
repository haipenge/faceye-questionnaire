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
 * Discuss ORM 实体<br>
 * 数据库表:questionnaire_discuss<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection="questionnaire_discuss")
public class Discuss implements Serializable {
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
    * 说明:内容<br>
    * 属性名: content<br>
    * 类型: String<br>
    * 数据库字段:content<br>
    * @author haipenge<br>
    */
    
	private  String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

	
   /**
    * 说明:问卷ID<br>
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
	
}/**@generate-entity-source@**/
	
