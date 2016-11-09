package com.huituopin.wechat.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSON;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huituopin.user.entity.User;
import com.huituopin.user.service.IUserService;
import com.huituopin.wechat.core.service.IWechatService;
import com.huituopin.wechat.core.service.impl.WechatServiceImpl;
import com.huituopin.wechat.core.utils.JsSdkutil;




@Controller 
public class WechatController {
	@Autowired
	private IUserService userService;
	
	

	/*
	 * 获取OAuth2授权		
	 */
	//主页
	@RequestMapping("/getWcInfoForIndex")
	public String getWcInfoForIndex(HttpServletRequest httpServletRequest){
		String url= new WechatServiceImpl().getCode();
		httpServletRequest.getSession().setAttribute("getWcInfoForType", "a");
		return "redirect:"+url;
	}
	//用户信息
	@RequestMapping("/getWcInfoForUserInfo")
	public String getWcInfoForUserInfo(HttpServletRequest httpServletRequest){
		String url= new WechatServiceImpl().getCode();
		httpServletRequest.getSession().setAttribute("getWcInfoForType", "b");
		return "redirect:"+url;
	}
	/*
	 * 授权回调页
	 */
	@RequestMapping(value = "/getUserInfo",produces="application/json;charset=UTF-8")
	public  void  getUserInfo(HttpServletRequest httpServletRequest , HttpServletResponse httpServletResponse) throws Exception{
		String code= httpServletRequest.getParameter("code");
		IWechatService wechatService= new WechatServiceImpl();
		String accessTokenJson=wechatService.getAccessToken(code);
		JSONObject jsonObject= new JSONObject(accessTokenJson);
		
		String openID = jsonObject.optString("openid");
		if(openID == null || "".equals(openID)){
			httpServletResponse.sendRedirect("/getWcInfoForIndex");
		}else{
		httpServletRequest.getSession().setAttribute("openid", openID);
		/**
		 * 如果OpenID存在
		 * 返回个人信息
		 *
		 */
	
		String accessToken=jsonObject.getString("access_token");
		String userInfo=wechatService.getUserInfo(openID,accessToken);
		System.out.print(userInfo);
		JSONObject js=new JSONObject(userInfo);
		httpServletRequest.getSession().setAttribute("userWcId", js.getString("openid"));
		httpServletRequest.getSession().setAttribute("userWcNickname", js.getString("nickname"));
		httpServletRequest.getSession().setAttribute("userWcAvatar", js.getString("headimgurl"));
		httpServletRequest.getSession().setAttribute("userWcGender",js.getInt("sex")+"");
		//如果该userWcId在数据库中，则在session中set该User对象
		User user = userService.searchByWid(js.getString("openid"));
		if(user != null)
			httpServletRequest.getSession().setAttribute("user",user);
		
		
		String type = httpServletRequest.getSession().getAttribute("getWcInfoForType").toString().trim();
		if("a".equals(type)){
			//httpServletRequest.getSession().removeAttribute("getWcInfoForType");
			httpServletRequest.getRequestDispatcher("/register").forward(httpServletRequest, httpServletResponse);
		}else if("b".equals(type)){
			//httpServletRequest.getSession().removeAttribute("getWcInfoForType");
			httpServletRequest.getRequestDispatcher("/register/userInfo").forward(httpServletRequest, httpServletResponse);
		}
		
	}
	}
		//User user=new User();
		//User user =userService.getUserByUserId(2);
		//user.setUserPhone("10086");
		//user.setUserPwd("10086");
		//user.setUserWcId(js.getString("openid"));
		//user.setUserWcGender(js.getInt("sex"));
		//user.setUserW(js.getString("city"));
		//user.setUserWcNickname(js.getString("nickname"));
		//user.setUserWcAvatar(js.getString("headimgurl"));
		/*
		if(userService.updataUserInfo(user)){
			System.out.print(user);
		}
		*/
		/*
		if(userService.insertUser(user))
		//httpServletRequest.getSession().setAttribute("openid",openID);
	//	httpServletRequest.getSession().setAttribute("headimgurl",user.getHeadImageUrl());
		System.out.print("ok");
		*/
		

    
	/*
	 * 微信js sdk 下载图片接口
	 */
	@RequestMapping("/wechat/downImage")
	@ResponseBody
	public String downImage(HttpServletRequest httpServletRequest){
		//需要传递过来 下载的serverId
		String serverId=httpServletRequest.getParameter("serverId");
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	     String ymd = sdf.format(new Date());
	     JSONObject jsonObject=new JSONObject();
	     String access_token= new WechatServiceImpl().getAccessToken();
	     String ROOT =httpServletRequest.getSession().getServletContext().getRealPath(System.getProperty("file.separator")); 
		 String path =ROOT+"resource/upload/image/"+ymd +"/"+ serverId + ".jpg";
		 String relativePath="/"+"resource/upload/image/"+ymd +"/"+ serverId + ".jpg";
		 try {
			JsSdkutil.downloadImg(serverId, access_token, path);
		} catch (Exception e) {
			 jsonObject.append("error", "error");
			 e.printStackTrace();
			 return jsonObject.toString();
		}
		 jsonObject.append("img", relativePath);
		 jsonObject.append("error", "ok");
		return jsonObject.toString();
	}
	
	
	
	

}
