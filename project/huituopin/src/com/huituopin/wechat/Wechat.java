package com.huituopin.wechat;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huituopin.wechat.core.service.WechatService;
import com.huituopin.wechat.core.utils.WechatValid;


public class Wechat extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public Wechat() {
		super();
	}
	/* 确认请求来自微信服务 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		}
		
		response.getWriter().write("");
		
	}

	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 将请求和响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//core 服务类接收处理消息
		WechatService wechatService =new WechatService();
		String returnXml=wechatService.coreService(request);
		
		//响应消息
		response.getWriter().write(returnXml);
	}

	public void init() throws ServletException {
	}

}
