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
		<link rel="stylesheet" href="<%=basePath %>pc/css/chooseType.css">
		
		<script src="<%=basePath%>wechat/js/jquery-1.9.1.js"></script>
		<script src="<%=basePath%>wechat/js/validate.js"></script>
		<title>选择账户类型</title>
	</head>
	<body>
		<input id="basePath" value="<%=basePath%>" style="display: none"/>
		
		<div class="box">
			<div class="middle_top">
				<img name="logo" src="<%=basePath %>pc/images/logo.jpg" style="width: 250px;height: 90px;border: 0;margin-top: 120px;margin-left: 90px;"/> 
				<p class="main_title">选择账号类型</p>
				<ul style="height: 100px;width: 100%;margin-top: 40px;">
					<a>
						<li id="aixin" style="width: 200px;height: 60px;background-color: #00BCE4;border-radius: 10px;font-size: 18px;color: #FFFFFF;float: left;list-style-type:none;border: 1px solid #FFFFFF;">						
							<p style="margin-top: 20px;text-align: center;">爱心人士</p>
						</li>
					</a>
					<a>
						<li id="pinkun" style="width: 200px;height: 60px;background-color: #FFFFFF;border-radius: 10px;font-size: 18px;color: #9B9B9B;float: right;border: 1px solid #D0D0D0;list-style-type:none;">
							<p style="margin-top: 20px;text-align: center;">贫苦人士</p>
						</li>
					</a>
				</ul>
				<form action="/userType" method="post">
					<input id="userType" name="userType" style="display: none" value="false">
					<button type="submit">下一步</button>
				</form>
 			</div>	
		</div>
		<script type="text/javascript">
			document.getElementById("aixin").addEventListener("click",function(end){
				$("#userType").val("false");
				document.getElementById("pinkun").style.color="#9B9B9B";
				document.getElementById("pinkun").style.borderColor="#D0D0D0";
				document.getElementById("pinkun").style.backgroundColor="#FFFFFF";
				document.getElementById("aixin").style.color="#FFFFFF";
				document.getElementById("aixin").style.backgroundColor="#00BCE4";
				document.getElementById("aixin").style.borderColor="#FFFFFF";
			});
			document.getElementById("pinkun").addEventListener("click",function(end){
				$("#userType").val("true");
				document.getElementById("aixin").style.color="#9B9B9B";
				document.getElementById("aixin").style.borderColor="#D0D0D0";
				document.getElementById("aixin").style.backgroundColor="#FFFFFF";
				document.getElementById("pinkun").style.color="#FFFFFF";
				document.getElementById("pinkun").style.backgroundColor="#00BCE4";
				document.getElementById("pinkun").style.borderColor="#FFFFFF";
			});
		</script>
  </body>
</html>
