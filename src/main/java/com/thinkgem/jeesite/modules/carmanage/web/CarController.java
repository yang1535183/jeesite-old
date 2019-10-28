/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.carmanage.web;

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
import com.thinkgem.jeesite.modules.carmanage.entity.Car;
import com.thinkgem.jeesite.modules.carmanage.service.CarService;

/**
 * 车辆信息Controller
 * @author 杨炜
 * @version 2019-10-28
 */
@Controller
@RequestMapping(value = "${adminPath}/carmanage/car")
public class CarController extends BaseController {

	@Autowired
	private CarService carService;
	
	@ModelAttribute
	public Car get(@RequestParam(required=false) String id) {
		Car entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = carService.get(id);
		}
		if (entity == null){
			entity = new Car();
		}
		return entity;
	}
	
	@RequiresPermissions("carmanage:car:view")
	@RequestMapping(value = {"list", ""})
	public String list(Car car, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Car> page = carService.findPage(new Page<Car>(request, response), car); 
		model.addAttribute("page", page);
		return "modules/carmanage/carList";
	}

	@RequiresPermissions("carmanage:car:view")
	@RequestMapping(value = "form")
	public String form(Car car, Model model) {
		model.addAttribute("car", car);
		return "modules/carmanage/carForm";
	}

	@RequiresPermissions("carmanage:car:edit")
	@RequestMapping(value = "save")
	public String save(Car car, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, car)){
			return form(car, model);
		}
		carService.save(car);
		addMessage(redirectAttributes, "保存车辆信息成功");
		return "redirect:"+Global.getAdminPath()+"/carmanage/car/?repage";
	}
	
	@RequiresPermissions("carmanage:car:edit")
	@RequestMapping(value = "delete")
	public String delete(Car car, RedirectAttributes redirectAttributes) {
		carService.delete(car);
		addMessage(redirectAttributes, "删除车辆信息成功");
		return "redirect:"+Global.getAdminPath()+"/carmanage/car/?repage";
	}

}