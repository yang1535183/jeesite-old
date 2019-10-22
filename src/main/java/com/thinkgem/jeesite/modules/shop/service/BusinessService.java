/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.shop.entity.Business;
import com.thinkgem.jeesite.modules.shop.dao.BusinessDao;

/**
 * 商家管理Service
 * @author 史良浩
 * @version 2019-10-17
 */
@Service
@Transactional(readOnly = true)
public class BusinessService extends CrudService<BusinessDao, Business> {

	public Business get(String id) {
		return super.get(id);
	}
	
	public List<Business> findList(Business business) {
		return super.findList(business);
	}
	
	public Page<Business> findPage(Page<Business> page, Business business) {
		return super.findPage(page, business);
	}
	
	@Transactional(readOnly = false)
	public void save(Business business) {
		super.save(business);
	}
	
	@Transactional(readOnly = false)
	public void delete(Business business) {
		super.delete(business);
	}
	
}