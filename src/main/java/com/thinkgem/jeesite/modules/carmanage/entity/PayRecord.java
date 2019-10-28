/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.carmanage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 缴费记录Entity
 * @author 杨炜
 * @version 2019-10-28
 */
public class PayRecord extends DataEntity<PayRecord> {
	
	private static final long serialVersionUID = 1L;
	private Car carId;		// 车辆id
	private Date payDate;		// 缴费时间
	private Date endDate;		// 到期时间
	private String payMoney;		// 缴费金额
	private String payName;		// 缴费人
	
	public PayRecord() {
		super();
	}

	public PayRecord(String id){
		super(id);
	}

	public Car getCarId() {
		return carId;
	}

	public void setCarId(Car carId) {
		this.carId = carId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Length(min=0, max=255, message="缴费金额长度必须介于 0 和 255 之间")
	public String getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}
	
	@Length(min=0, max=255, message="缴费人长度必须介于 0 和 255 之间")
	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}
	
}