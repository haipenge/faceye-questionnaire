package com.faceye.component.questionnaire.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.faceye.component.weixin.entity.WeixinUser;
/**
 * Rabbit ORM 实体<br>
 * 数据库表:emergency_rabbit<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection="questionnaire_rabbit")
public class Rabbit implements Serializable {
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
	    * 说明:创建时间<br>
	    * 属性名: createDate<br>
	    * 类型: Date<br>
	    * 数据库字段:create_date<br>
	    * @author haipenge<br>
	    */

	private Date createDate=new Date();

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

   /**
    * 说明:姓名<br>
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
    * 说明:手机<br>
    * 属性名: mobile<br>
    * 类型: String<br>
    * 数据库字段:mobile<br>
    * @author haipenge<br>
    */
    
	private  String mobile;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	private WeixinUser weixinUser=null;
	public WeixinUser getWeixinUser() {
		return weixinUser;
	}
	public void setWeixinUser(WeixinUser weixinUser) {
		this.weixinUser = weixinUser;
	}
	/**
	 * 所在社区
	 */
	private String community="";
	
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 详细地址
	 */
	private String address="";
	
	
}/**@generate-entity-source@**/
	
