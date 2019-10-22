/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.meal.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.meal.entity.MealRoll;

/**
 * 餐卷管理DAO接口
 * @author 杨炜
 * @version 2019-10-21
 */
@MyBatisDao
public interface MealRollDao extends CrudDao<MealRoll> {
	
}