<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
  	<title>登录</title>
  	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link href="<%=basePath%>wechat/css/login.css" rel="stylesheet" />
    <link rel="stylesheet" href="<%=basePath%>wechat/css/mui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/app.css"/>
	<script src="<%=basePath%>wechat/js/mui.min.js"></script>
	<script src="<%=basePath%>wechat/js/validate.js"></script>
	<script src="<%=basePath%>wechat/js/jquery-1.9.1.js"></script>
	<script src="<%=basePath%>wechat/js/validate.js"></script>
	<script src="<%=basePath%>wechat/jsme/user.wechat.js"></script>
	<style type="text/css">
		input[type='submit'], .mui-btn-primary, .mui-btn-blue{
			background-color:#00bce4;
		}
	</style>
  </head>
  
  
 <body>
 <input id="basePath" value="<%=basePath%>" style="display: none"/>
	<div class="login-header">
			<div class="login-top"></div>
			<div class="login-imageview">
				<img src="<%=basePath%>wechat/images/logo.png" class="login-image" />
				<p class="login-text"><span>慧脱贫</span></p>
			</div>
		</div>
		<div class="mui-content" style="">

			<div class="mui-input-group">
				<div class="mui-input-row">
					<label>手机号码</label>
					<input  name="phone" id="userPhoneNb" type="text" placeholder="请输入手机号码">
					<button type="button" id ="checkPhoneBtn" class="mui-btn mui-btn-primary"  onclick="checkPhone()">
							获取验证码
					</button>
				</div>
				<div class="mui-input-row1">
					<label>验证码</label>
					<input id="code" type="text" class="mui-input-clear" placeholder="请输入短信验证码">
				</div>

				<div class="login-lastline">
					<input type="button" style="background-color: #00bce4;color: #FFFFFF" class="login-button" value="下一步" onclick="checkCode()" />
				</div>
			</div>
		</div>	
</body>
</html>
