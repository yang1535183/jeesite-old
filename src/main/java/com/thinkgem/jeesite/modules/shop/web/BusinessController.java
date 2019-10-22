/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.shop.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.shop.entity.Business;
import com.thinkgem.jeesite.modules.shop.service.BusinessService;

/**
 * 商家管理Controller
 * @author 史良浩
 * @version 2019-10-17
 */
@Controller
@RequestMapping(value = "${adminPath}/shop/business")
public class BusinessController extends BaseController {

	@Autowired
	private BusinessService businessService;
	
	@ModelAttribute
	public Business get(@RequestParam(required=false) String id) {
		Business entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = businessService.get(id);
		}
		if (entity == null){
			entity = new Business();
		}
		return entity;
	}
	
	@RequiresPermissions("shop:business:view")
	@RequestMapping(value = {"list", ""})
	public String list(Business business, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Business> page = businessService.findPage(new Page<Business>(request, response), business); 
		model.addAttribute("page", page);
		return "modules/shop/businessList";
	}

	@RequiresPermissions("shop:business:view")
	@RequestMapping(value = "form")
	public String form(Business business, Model model) {
		model.addAttribute("business", business);
		return "modules/shop/businessForm";
	}

	@RequiresPermissions("shop:business:edit")
	@RequestMapping(value = "save")
	public String save(Business business, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, business)){
			return form(business, model);
		}
		businessService.save(business);
		addMessage(redirectAttributes, "保存商家成功");
		return "redirect:"+Global.getAdminPath()+"/shop/business/?repage";
	}
	
	@RequiresPermissions("shop:business:edit")
	@RequestMapping(value = "delete")
	public String delete(Business business, RedirectAttributes redirectAttributes) {
		businessService.delete(business);
		addMessage(redirectAttributes, "删除商家成功");
		return "redirect:"+Global.getAdminPath()+"/shop/business/?repage";
	}

}