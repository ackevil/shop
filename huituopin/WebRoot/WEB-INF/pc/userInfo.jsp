<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="<%=basePath %>pc/css/css.css">
		<link rel="stylesheet" href="<%=basePath %>pc/css/mui.css" />
		<title>个人中心</title>

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
					<a href=""><span class="user">${user.userWcNickname }${user.userPhone }</span></a>
					<button class="exitButton"><a href="/exit">退出</a></button>
				</div>
			</div>
			<div class="middle">
				<div class="middle_box">
					<img src="<%=path%>/${sessionScope.user.userWcAvatar}" style="height: 80px;width: 80px;margin:50px 560px 20px 560px;"/>
					<p style="font-size: 18px;text-align: center;color: #000000;">${user.userWcNickname }${user.userPhone }</p>
					<img src="<%=basePath %>pc/images/erweima.jpg" style="height: 300px;width: 300px;margin:20px 450px 20px 450px;"/>
					<p style="font-size: 17px;text-align: center;color: #000000;">请使用微信扫一扫，在手机上查看更多信息</p>
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
