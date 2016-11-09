<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登陆后台管理系统</title>
<link href="<%=path%>/admin/skin/css/style.css" rel="stylesheet"
	type="text/css" />
<script src="<%=basePath%>admin/skin/js/jquery-1.11.2.min.js"></script>
<script src="<%=basePath%>admin/skin/js/showDialog.js" charset="gb2312"
	type="text/javascript"></script>
	<script src="<%=basePath%>admin/skin/js/user.admin.js" charset="gb2312"
	type="text/javascript"></script>
<link href="<%=basePath%>admin/skin/css/showDialog.css" rel="stylesheet" />
<script type="text/javascript">
</script>
<script type="text/javascript">
	$(function(){
		var type = $("#type").val();
		if(type == 1){
			alert("用户名或密码错误，请重新登录！");
		}
		if(type == 2){
			alert("对不起，您的登录已经失效，请重新登录！");
		}
		if(type == 3){
			alert("修改成功，请重新登录");
		}
	});	
</script>
</head>
<body style="background:#f0f0f0;">
	<input id="type" value="${sessionScope.type}" style="display: none"/>
	<input id="basePath" value="<%=basePath%>" style="display: none"/>
	<div class="login">
		<div class="login-top">
			<img src="skin/images/loginlogo.jpg" width="468" height="240" />
		</div>
		<div class="login-nr">
			<div class="login-text">
				<!-- 
					<div class="qiehuan">
						
						<a href="#" class="hover">中文</a> <a href="#">英文</a>
						 
					</div>
					-->
					<div class="denglu">
						<div class="dlk">
							<input name="adminuserName" type="text" id="txtUserName"
								placeholder="请输入用户名"
								onfocus="if(value==defaultValue){value='';this.style.color='#000'}"
								onblur="if(!value){value=defaultValue;this.style.color='#999'}"
								style="color: rgb(153, 153, 153);" />
						</div>
						<div class="mima">
							<input name="adminuserPwd" type="password" id="txtPsw"
								onfocus="if(value==defaultValue){value='';this.style.color='#000'}"
								onblur="if(!value){value=defaultValue;this.style.color='#999'}"
								style="color: rgb(153, 153, 153);" />
						</div>
						<!-- 
						<div class="member">
							<input id="CheckBox1" type="checkbox" name="CheckBox1" />
							<label>记住密码</label>
							<a href="#">忘记密码？</a>
						</div>
						 -->
						<div class="dlan">
							<input onclick="check()" type="button" name="btnLogin" value="" id="btnLogin" class="tijiao" /> 
							<input onclick="resetValue()" type="button" name="btnReset" value="" id="btnReset" class="chongzhi" />
						</div>
					</div>
				<div class="banquan">
					©2016 慧脱贫 All rights reserved <a href="#">公司客服</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>