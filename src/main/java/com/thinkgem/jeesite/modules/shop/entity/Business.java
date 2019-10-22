/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.shop.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 商家管理Entity
 * @author 史良浩
 * @version 2019-10-17
 */
public class Business extends DataEntity<Business> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 商家名称
	private String phone;		// 商家电话
	private String address;		// 商家地址
	private String description;		// 经验范围
	private String photo;		// 商家照片
	private String license;		// 营业执照
	private String hygiene;		// 卫生许可证
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	private String jwd; // 经纬度

	public String getJwd() {
		return jwd;
	}

	public void setJwd(String jwd) {
		this.jwd = jwd;
	}
	
	public Business() {
		super();
	}

	public Business(String id){
		super(id);
	}

	@Length(min=0, max=20, message="商家名称长度必须介于 0 和 20 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=20, message="商家电话长度必须介于 0 和 20 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=100, message="商家地址长度必须介于 0 和 100 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=1000, message="经验范围长度必须介于 0 和 1000 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=150, message="商家照片长度必须介于 0 和 150 之间")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Length(min=0, max=150, message="营业执照长度必须介于 0 和 150 之间")
	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}
	
	@Length(min=0, max=150, message="卫生许可证长度必须介于 0 和 150 之间")
	public String getHygiene() {
		return hygiene;
	}

	public void setHygiene(String hygiene) {
		this.hygiene = hygiene;
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