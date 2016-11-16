package com.faceye.component.questionnaire.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.faceye.component.weixin.entity.Account;
import com.faceye.feature.entity.UploadFile;
/**
 * Questionnaire ORM 实体<br>
 * 数据库表:questionnaire_questionnaire<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection="questionnaire_questionnaire")
public class Questionnaire implements Serializable {
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
    * 属性名: name<br>
    * 类型: String <br>
    * 数据库字段:name<br>
    * @author haipenge<br>
    */
    
	private  String  name;
	public String  getName() {
		return name;
	}
	public void setName(String  name) {
		this.name = name;
	}
	
	/**
	 * 访问次数
	 */
	private Integer accessCount=0;
	
   public Integer getAccessCount() {
		return accessCount;
	}
	public void setAccessCount(Integer accessCount) {
		this.accessCount = accessCount;
	}

/**
    * 说明:说明<br>
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
	

	
   /**
    * 说明:创建日期<br>
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
    * 说明:是否关闭<br>
    * 属性名: isClosed<br>
    * 类型: Boolean<br>
    * 数据库字段:is_closed<br>
    * @author haipenge<br>
    */
    
	private  Boolean isClosed=false;
	public Boolean getIsClosed() {
		return isClosed;
	}
	public void setIsClosed(Boolean isClosed) {
		this.isClosed = isClosed;
	}
	
	/**
	 * 是否锁定，锁定后问卷不可修改
	 */
	private Boolean isLocked=false;
	public Boolean getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}
	
	@DBRef
	private List<UploadFile> uploadFiles=null;
	public List<UploadFile> getUploadFiles() {
		return uploadFiles;
	}
	public void setUploadFiles(List<UploadFile> uploadFiles) {
		this.uploadFiles = uploadFiles;
	}
	/**
	 * 结果提示
	 */
	private String resultTip="";
	public String getResultTip() {
		return resultTip;
	}
	public void setResultTip(String resultTip) {
		this.resultTip = resultTip;
	}
	@DBRef
	private QuestionnaireType questionnaireType=null;
	public QuestionnaireType getQuestionnaireType() {
		return questionnaireType;
	}
	public void setQuestionnaireType(QuestionnaireType questionnaireType) {
		this.questionnaireType = questionnaireType;
	}
	
	
	
}/**@generate-entity-source@**/
	
