package com.huituopin.wechat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.huituopin.user.entity.User;
import com.huituopin.user.service.IUserService;
import com.huituopin.wechat.core.service.IWechatService;
import com.huituopin.wechat.core.service.impl.WechatServiceImpl;




@Controller 
public class WechatController {
	
	@Autowired
	private IUserService userService ;
					
	@RequestMapping("/getCode")
	public String getCode(){

		String url= new WechatServiceImpl().getCode();
		return "redirect:"+url;

	}
	@RequestMapping(value = "/getUserInfo",produces="application/json;charset=UTF-8")
	public  void  getUserInfo(HttpServletRequest httpServletRequest , HttpServletResponse httpServletResponse){
		String code= httpServletRequest.getParameter("code");
		IWechatService wechatService= new WechatServiceImpl();
		String accessTokenJson=wechatService.getAccessToken(code);
		JSONObject jsonObject= new JSONObject(accessTokenJson);
		String openID=jsonObject.getString("openid");
		/**
		 * 如果OpenID存在
		 * 返回个人信息
		 *
		 */

		String accessToken=jsonObject.getString("access_token");
		String userInfo=wechatService.getUserInfo(openID,accessToken);
		System.out.print(userInfo);
		JSONObject js=new JSONObject(userInfo);
		User user=new User();
		//User user =userService.getUserByUserId(2);
		user.setUserPhone("10086");
		user.setUserPwd("10086");
		user.setUserWcId(js.getString("openid"));
		//user.setUserWcGender(js.getInt("sex"));
		//user.setUserW(js.getString("city"));
		user.setUserWcNickname(js.getString("nickname"));
		user.setUserWcAvatar(js.getString("headimgurl"));
		/*
		if(userService.updataUserInfo(user)){
			System.out.print(user);
		}
		*/
		if(userService.insertUser(user))
		//httpServletRequest.getSession().setAttribute("openid",openID);
	//	httpServletRequest.getSession().setAttribute("headimgurl",user.getHeadImageUrl());
		System.out.print("ok");
	}


}
