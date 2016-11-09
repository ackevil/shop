<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="<%=basePath%>pc/css/css.css">
		<link rel="stylesheet" href="<%=basePath %>pc/css/mui.css" />
		<title>慧脱贫</title>
  </head>
  
 <body>
		<div class="box">
			<div class="top">
				
				<div class="top_1">
					<img name="logo" src="<%=basePath %>pc/images/logo.jpg" style="width: 120px;height: 50px;border: 0;margin-top: 15px;"/> 
				</div>
				<div class="top_2">
					<ul>
						<a href="" style="color: #00BCE4;"><li>首页</li></a>
						<a href=""><li>依旧有爱</li></a>
						<a href=""><li>滴水之恩</li></a>
						<a href=""><li>重大救助</li></a>
						<a href=""><li>爱购</li></a>
					</ul>
				</div>
				<div class="top_3">
					<c:if test="${empty user }">
						<button class="exitButton"><a href="/sign">注册</a></button>
						<button class="loginButton"><a href="/login">登录</a></button>
					</c:if>
					<c:if test="${!empty user }">
						<a href="/userInfo"><span class="user">${user.userWcNickname}${user.userPhone}</span></a>
						<button class="exitButton"><a href="/exit">退出</a></button>
					</c:if>
					
				</div>
			</div>
			<div class="middle">
				<div class="main">
			</div>
			</div>
			
			<div class="footer">
				<div class="footer-box">				
					<span class="declare"><label style="font:'宋体';font-size: 18px;">©</label>2016慧脱贫—湖南乐善网络科技有限公司 湘IPC证160503号</span>
					<div style="float: right;height: 100px;width: 80px;">
						<a href=""><img src="<%=basePath %>pc/images/erweima.jpg" style="float: right;height: 80px;width: 80px;margin-top: 5px;" /></a>
						<label style="font-size: 12px;padding-left: 4px;">微信扫码关注</label>
					</div>
				</div>
			</div>
		</div>
		
		
	</body>
</html>
