/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.carmanage.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.carmanage.entity.PayRecord;
import com.thinkgem.jeesite.modules.carmanage.service.CarService;
import com.thinkgem.jeesite.modules.carmanage.service.PayRecordService;
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

/**
 * 缴费记录Controller
 * @author 杨炜
 * @version 2019-10-28
 */
@Controller
@RequestMapping(value = "${adminPath}/carmanage/payRecord")
public class PayRecordController extends BaseController {

	@Autowired
	private PayRecordService payRecordService;

	@Autowired
	private CarService carService;
	
	@ModelAttribute
	public PayRecord get(@RequestParam(required=false) String id) {
		PayRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = payRecordService.get(id);
		}
		if (entity == null){
			entity = new PayRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("carmanage:payRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(PayRecord payRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PayRecord> page = payRecordService.findPage(new Page<PayRecord>(request, response), payRecord); 
		model.addAttribute("page", page);
		return "modules/carmanage/payRecordList";
	}

	@RequiresPermissions("carmanage:payRecord:view")
	@RequestMapping(value = "form")
	public String form(PayRecord payRecord, Model model) {
		if (payRecord!= null &&
				payRecord.getCarId() != null &&
				!StringUtils.isEmpty(payRecord.getCarId().getId())){
			// 当车辆id 不为空时
			payRecord.setCarId(carService.get(payRecord.getCarId().getId()));
		}
		model.addAttribute("payRecord", payRecord);
		return "modules/carmanage/payRecordForm";
	}

	@RequiresPermissions("carmanage:payRecord:edit")
	@RequestMapping(value = "save")
	public String save(PayRecord payRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, payRecord)){
			return form(payRecord, model);
		}
		payRecordService.save(payRecord);
		addMessage(redirectAttributes, "保存缴费记录成功");
		return "redirect:"+Global.getAdminPath()+"/carmanage/payRecord/?repage";
	}
	
	@RequiresPermissions("carmanage:payRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(PayRecord payRecord, RedirectAttributes redirectAttributes) {
		payRecordService.delete(payRecord);
		addMessage(redirectAttributes, "删除缴费记录成功");
		return "redirect:"+Global.getAdminPath()+"/carmanage/payRecord/?repage";
	}

	@RequestMapping(value = "carForm")
	public String carForm(PayRecord payRecord, Model model) {

		model.addAttribute("payRecord", payRecord);
		return "modules/carmanage/payRecordForm";
	}
}