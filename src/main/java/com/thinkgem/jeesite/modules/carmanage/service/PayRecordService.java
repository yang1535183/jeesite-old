/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.carmanage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.carmanage.entity.PayRecord;
import com.thinkgem.jeesite.modules.carmanage.dao.PayRecordDao;

/**
 * 缴费记录Service
 * @author 杨炜
 * @version 2019-10-28
 */
@Service
@Transactional(readOnly = true)
public class PayRecordService extends CrudService<PayRecordDao, PayRecord> {

	public PayRecord get(String id) {
		return super.get(id);
	}
	
	public List<PayRecord> findList(PayRecord payRecord) {
		return super.findList(payRecord);
	}
	
	public Page<PayRecord> findPage(Page<PayRecord> page, PayRecord payRecord) {
		return super.findPage(page, payRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(PayRecord payRecord) {
		super.save(payRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(PayRecord payRecord) {
		super.delete(payRecord);
	}
	
}