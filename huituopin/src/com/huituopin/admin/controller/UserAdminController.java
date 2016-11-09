package com.huituopin.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@RequestMapping("/admin/user")
@Controller
public class UserAdminController {
	@RequestMapping("/lpModify")
	public ModelAndView addLogisticsTool(){
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/user/lpModify");
		return model;
	}
}
