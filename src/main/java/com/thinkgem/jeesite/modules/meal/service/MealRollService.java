/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.meal.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.meal.dao.MealRollDao;
import com.thinkgem.jeesite.modules.meal.entity.MealRoll;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 餐卷管理Service
 * @author 杨炜
 * @version 2019-10-21
 */
@Service
@Transactional(readOnly = true)
public class MealRollService extends CrudService<MealRollDao, MealRoll> {

	public MealRoll get(String id) {
		return super.get(id);
	}
	
	public List<MealRoll> findList(MealRoll mealRoll) {
		return super.findList(mealRoll);
	}
	
	public Page<MealRoll> findPage(Page<MealRoll> page, MealRoll mealRoll) {
		return super.findPage(page, mealRoll);
	}
	
	@Transactional(readOnly = false)
	public void save(MealRoll mealRoll) {
		super.save(mealRoll);
	}
	
	@Transactional(readOnly = false)
	public void delete(MealRoll mealRoll) {
		super.delete(mealRoll);
	}
	
}