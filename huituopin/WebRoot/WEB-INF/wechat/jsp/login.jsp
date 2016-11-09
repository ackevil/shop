<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="js/mui.min.js"></script>
	<script type="text/javascript" src="js/validate.js"></script>
	<link href="css/login.css" rel="stylesheet" />
	<script type="text/javascript">
	function checkPhone(){
		if(!validatePhone("userPhoneNb")){
			return false;
		}
		var userPhoneNb = $("#userPhoneNb").val();
		$.ajax({
			type:"POST",
			url:"checkPhone",
			data:{
				phone:userPhoneNb
			},
			success:function(msg){
				if(msg){//返回是true  说明号码可用 ，否则号码已经被注册
				
				}else{
					alert("手机号码已被注册不可用");				
				}
			}
		});
		
	}
	</script>
  </head>
  
  <body>
      <div id="content">
      <!--图标部分-->
		<div class="login-header">
			<div class="login-top"></div>
			<div class="login-imageview">
				<img src="images/logo.png" class="login-image"/>
				<h1 class="login-text"><span>慧脱贫</span></h1>
			</div>
		</div>
		<div id="center">
        <!--表单部分-->
			<form method="post"  class="login-form" action="aiGou_1.html">
				<div class="login-line">
                    <input class="login-text-label" type="text" value="手机号码" readonly="true"/>
					<input type="text" class="login-input" name="userPhoneNb" id="userPhoneNb" placeholder="请输入手机号码"/>
					<input type="button" class="login-first-button" onclick="checkPhone()" value="获取验证码" />
                    <div class="context_span"></div>
				</div>
				
				<div class="login-line">
                    <input class="login-text-label" type="text" value="验证码" readonly="true"/>
					<input type="text" class="login-input" name="yzm" placeholder="请输入短信验证码"/>
				</div>
				
				<div class="login-lastline">
					<input type="submit" class="login-second-button" value="下一步" />
				</div>
			</form> 
          </div>
      </div>
</html>
