package com.huituopin.wechat.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.huituopin.wechat.core.service.MainService;
import com.huituopin.wechat.core.utils.WechatValid;

@Controller
@RequestMapping("/wechat")
public class MainController {

	@RequestMapping(value="/service")
	public  void validWechat(HttpServletRequest request, HttpServletResponse response) throws IOException{
				// 将请求和响应的编码均设置为UTF-8（防止中文乱码）
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				//获取服务器参数	
				String echostr = request.getParameter("echostr");
				String signature =request.getParameter("signature");
				String timeStamp =request.getParameter("timestamp");
				String nonce =request.getParameter("nonce");
				//如果echostr不空说明微信首次验证
				if(echostr!=null){
					boolean flag=new WechatValid().valid(signature, timeStamp, nonce);
					//验证成功返回原echostr
					if(flag){
						response.getWriter().write(echostr);
					}
				}else{
					System.out.println("msg");
					//core 服务类接收处理消息
					MainService wechatService =new MainService();
					String returnXml=wechatService.coreService(request);
					System.out.println(returnXml);
					//响应消息
					response.getWriter().write(returnXml);
				}
	}

	
}
