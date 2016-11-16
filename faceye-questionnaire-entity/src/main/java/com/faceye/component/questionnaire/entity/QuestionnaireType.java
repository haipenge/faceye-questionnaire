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
 * QuestionnaireType ORM 实体<br>
 * 数据库表:questionnaire_questionnaireType<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection="questionnaire_questionnaire_type")
public class QuestionnaireType implements Serializable {
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
    * 说明:名称<br>
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
    * 说明:排序值<br>
    * 属性名: orderIndex<br>
    * 类型: Integer<br>
    * 数据库字段:排序值<br>
    * @author haipenge<br>
    */
    
	private  Integer orderIndex;
	public Integer getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}
	

	
   /**
    * 说明:测试将分类<br>
    * 属性名: type<br>
    * 类型: String<br>
    * 数据库字段:type<br>
    * @author haipenge<br>
    */
    
	private  String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}/**@generate-entity-source@**/
	
