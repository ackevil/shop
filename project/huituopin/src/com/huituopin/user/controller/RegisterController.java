package com.huituopin.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huituopin.common.utils.StringUtil;
import com.huituopin.user.service.IUserService;

/**
 * 
 * @author guoqingshan
 * 2016-4-20
 *
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private IUserService userService ;
	
	
	
	
	/**
	 * 检查  手机号 是否可用
	 */
	@RequestMapping(value = "/checkPhone", method = RequestMethod.GET)
	public @ResponseBody
	boolean checkPhone(String phone) {
		if(StringUtil.isEmpty(phone)){
			return false;
		}
		if(userService.phoneExists(phone)){
			return false;
		}else{
			return true ;
		}
		
	}

	
	
	/**
	 *  注册页面
	 */
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response) {
		
		return "/register/index";
	}
}
