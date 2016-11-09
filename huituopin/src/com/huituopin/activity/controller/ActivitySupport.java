package com.huituopin.activity.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.huituopin.activity.entity.Activity;
import com.huituopin.activity.service.IActivityService;
import com.huituopin.wechat.core.service.IWechatService;

@RequestMapping("/wechat/pay")
@Controller
public class ActivitySupport {

	@Autowired
	private IActivityService activityService;
	
	@Autowired
	private IWechatService wechatService;
	
	@RequestMapping("/activitySupport")
	public ModelAndView support(int id,HttpServletRequest request){
		/**
		    * 判断是否注册
		    */
		   if(request.getSession().getAttribute("user")==null){
			    ModelAndView model1=new ModelAndView();
		        model1.setViewName("wechat/jsp/user/register");
		        return model1;
		   }
		
		String url = request.getRequestURL().toString();
		if (request.getQueryString() != null){
			url += "?" + request.getQueryString();
		}
		Map<String,String> map = wechatService.getJsConfig(url);
		Activity activity=activityService.getActivityById(id);
		
		ModelAndView model=new ModelAndView();
		model.setViewName("wechat/jsp/zhongdajiuzhu/support");
		model.addObject("activity", activity);
		model.addObject("map", map);
		return model;
	}
	
	
}
