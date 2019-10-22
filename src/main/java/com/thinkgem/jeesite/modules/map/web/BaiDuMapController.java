/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MapController
 * Author:   Administrator
 * Date:     2019/10/17 11:04
 * Description: 地图控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.thinkgem.jeesite.modules.map.web;

import com.thinkgem.jeesite.common.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 百度地图Controller
 * @author 杨炜
 * @version 2019-10-17
 */
@Controller
@RequestMapping(value = "${adminPath}/map/baiDuMap")
public class BaiDuMapController extends BaseController {

    @RequestMapping(value = {"baseMap", ""})
    public String list(String jwd,HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("jwd",jwd);
        return "modules/baiDuMap/baseMap";
    }
}
