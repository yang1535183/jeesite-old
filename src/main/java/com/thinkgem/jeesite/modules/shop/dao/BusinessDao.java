/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.shop.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.shop.entity.Business;

/**
 * 商家管理DAO接口
 * @author 史良浩
 * @version 2019-10-17
 */
@MyBatisDao
public interface BusinessDao extends CrudDao<Business> {
	
}