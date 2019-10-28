/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.carmanage.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.carmanage.entity.PayRecord;

/**
 * 缴费记录DAO接口
 * @author 杨炜
 * @version 2019-10-28
 */
@MyBatisDao
public interface PayRecordDao extends CrudDao<PayRecord> {
	
}