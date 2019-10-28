/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.carmanage.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 车辆信息Entity
 * @author 杨炜
 * @version 2019-10-28
 */
public class Car extends DataEntity<Car> {
	
	private static final long serialVersionUID = 1L;
	private String parkNumber;		// 车位号
	private String carType;		// 车辆类型
	private String owner;		// 车主姓名
	private String phone;		// 联系方式
	private String address;		// 住址
	private String photo;		// 车辆照片
	
	public Car() {
		super();
	}

	public Car(String id){
		super(id);
	}

	@Length(min=0, max=255, message="车位号长度必须介于 0 和 255 之间")
	public String getParkNumber() {
		return parkNumber;
	}

	public void setParkNumber(String parkNumber) {
		this.parkNumber = parkNumber;
	}
	
	@Length(min=0, max=255, message="车辆类型长度必须介于 0 和 255 之间")
	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}
	
	@Length(min=0, max=255, message="车主姓名长度必须介于 0 和 255 之间")
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	@Length(min=0, max=255, message="联系方式长度必须介于 0 和 255 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=255, message="住址长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=255, message="车辆照片长度必须介于 0 和 255 之间")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}