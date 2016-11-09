<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<title>个人信息</title>
		<link rel="stylesheet" href="<%=basePath%>wechat/css/setttingmsg.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/app.css"/>
	</head>
	<body>	
		<div class="mui-scroll-wrapper">
			<div class="mui-scroll">
				<ul class="mui-table-view">
					<li class="mui-table-view-cell">
						<button id='promptBtn' type="button" style="margin:0px;width: 100%;border: 0;">
							<span class="mui-pull-left mui-pull">姓名</span>
							<span class="mui-pull-right mui-pull" id="userName">${sessionScope.user.userWcNickname}</span>
						</button>								
					</li>
				</ul>	
			</div>
				
		<form action="lpInfoSetting" onsubmit="return changeInfoCheck()" method="post">
			<input id="userId" name="userId" type="text" style="display: none;" value="${sessionScope.user.userId}">	
			<input id="name" name="userWcNickname" type="text" style="display: none;"/><!--姓名-->
			<input id="btn" type="submit" value="确定" style="height:42px;position:absolute;bottom: 0px;border:0px;width:100%;background-color: #00bce4;color: white;" />
		</form>
		</div>	
		<script src="<%=basePath%>wechat/js/mui.min.js"></script>
		<script src="<%=basePath%>wechat/js/jquery-1.9.1.js"></script>
		<script type="text/javascript" charset="utf-8">
			
				
			function changeInfoCheck(){
				if(checkNull("#userName","姓名不能为空"))
					return false;
				$("#name").val($("#userName").text());
			}
			function checkNull(id,text){
				value = $(id).text();
				if(value == "" || value == null){
					alert(text);
					return true;
				}
			}
			document.getElementById("promptBtn").addEventListener('tap', function(e) {
				e.detail.gesture.preventDefault(); //修复iOS 8.x平台存在的bug，使用plus.nativeUI.prompt会造成输入法闪一下又没了
				var btnArray = ['取消', '确定'];
				mui.prompt('', '', '请输入姓名', btnArray, function(e) {
					if (e.index == 1) {
						userName.innerText = e.value;
					} else {
						
					}
				});
			});
		</script>
	</body>
</html>
