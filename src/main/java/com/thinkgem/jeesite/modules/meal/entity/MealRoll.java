/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.meal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 餐卷管理Entity
 * @author 杨炜
 * @version 2019-10-21
 */
public class MealRoll extends DataEntity<MealRoll> {
	
	private static final long serialVersionUID = 1L;
	private String faceValue;		// 面值
	private Office coopBusiness;		// 合作商家
	private Date startDate;		// 有效时间起
	private Date endDate;		// 有效时间止
	private String receive;		// 是否领取
	private User receiver;		// 领取人
	private Date receiveDate;		// 领取时间
	private String special;		// 专用卷
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	
	public MealRoll() {
		super();
	}

	public MealRoll(String id){
		super(id);
	}

	@Length(min=0, max=255, message="面值长度必须介于 0 和 255 之间")
	public String getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(String faceValue) {
		this.faceValue = faceValue;
	}
	
	public Office getCoopBusiness() {
		return coopBusiness;
	}

	public void setCoopBusiness(Office coopBusiness) {
		this.coopBusiness = coopBusiness;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Length(min=0, max=255, message="是否领取长度必须介于 0 和 255 之间")
	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}
	
	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	
	@Length(min=0, max=255, message="专用卷长度必须介于 0 和 255 之间")
	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
		
}