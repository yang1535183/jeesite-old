/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.meal.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.meal.entity.MealRoll;
import com.thinkgem.jeesite.modules.meal.service.MealRollService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 餐卷管理Controller
 * @author 杨炜
 * @version 2019-10-21
 */
@Controller
@RequestMapping(value = "${adminPath}/meal/mealRoll")
public class MealRollController extends BaseController {

	@Autowired
	private MealRollService mealRollService;
	
	@ModelAttribute
	public MealRoll get(@RequestParam(required=false) String id) {
		MealRoll entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mealRollService.get(id);
		}
		if (entity == null){
			entity = new MealRoll();
		}
		return entity;
	}
	
	@RequiresPermissions("meal:mealRoll:view")
	@RequestMapping(value = {"list", ""})
	public String list(MealRoll mealRoll, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if ("3".equals(user.getUserType())){
			mealRoll.setReceiver(user);
		}
		Page<MealRoll> page = mealRollService.findPage(new Page<MealRoll>(request, response), mealRoll); 
		model.addAttribute("page", page);
		return "modules/meal/mealRollList";
	}

	@RequiresPermissions("meal:mealRoll:view")
	@RequestMapping(value = "form")
	public String form(MealRoll mealRoll, Model model) {
		model.addAttribute("mealRoll", mealRoll);
		return "modules/meal/mealRollForm";
	}

	@RequiresPermissions("meal:mealRoll:edit")
	@RequestMapping(value = "save")
	public String save(MealRoll mealRoll, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mealRoll)){
			return form(mealRoll, model);
		}
		if (StringUtils.isEmpty(mealRoll.getId())){
			mealRoll.setReceive("0");// 是否领取
		}
		mealRollService.save(mealRoll);
		addMessage(redirectAttributes, "保存餐卷管理成功");
		return "redirect:"+Global.getAdminPath()+"/meal/mealRoll/?repage";
	}
	
	@RequiresPermissions("meal:mealRoll:edit")
	@RequestMapping(value = "delete")
	public String delete(MealRoll mealRoll, RedirectAttributes redirectAttributes) {
		mealRollService.delete(mealRoll);
		addMessage(redirectAttributes, "删除餐卷管理成功");
		return "redirect:"+Global.getAdminPath()+"/meal/mealRoll/?repage";
	}

	@RequiresPermissions("meal:mealRoll:receive")
	@RequestMapping(value = "receive")
	public String receive(MealRoll mealRoll, RedirectAttributes redirectAttributes) {
		mealRoll = mealRollService.get(mealRoll.getId());
		mealRoll.setReceive("1"); // 状态改为已领取
		mealRoll.setReceiver(UserUtils.getUser());	// 设置领取人
		mealRoll.setReceiveDate(new Date());	// 设置领取时间
		mealRollService.save(mealRoll);
		addMessage(redirectAttributes, "餐卷领取成功");
		return "redirect:"+Global.getAdminPath()+"/meal/mealRoll/?repage";
	}

}