<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

		<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
		<html>

		<head>
			<base href="<%=basePath%>">

			<title>首页</title>
			<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
			<meta http-equiv="pragma" content="no-cache">
			<meta http-equiv="cache-control" content="no-cache">
			<meta http-equiv="expires" content="0">
			<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
			<meta http-equiv="description" content="This is my page">
			
			<link href="<%=basePath%>wechat/css/login.css" rel="stylesheet" />
			<link rel="stylesheet" href="<%=basePath%>wechat/css/mui.min.css">
			<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/app.css" />
			
			<style type="text/css">
				.headimage {
					height: 180px;
					width: 100%;
				}
				
				.icondiv {
					height: 30px;
					width: 30px;
					margin: 0 auto;
					text-align: center;
				}
				
				.iconimg {
					max-height: 35px;
					max-width: 35px;
					padding-top: 5px;
				}
				
				.footer {
					height: 30px;
					width: 15px;
					margin: 0 auto;
					padding-top: 10px;
				}
				
				.footerimg {
					max-height: 15px;
					max-width: 15px;
					padding-top: 10px;
				}
			</style>
			
			<script src="<%=basePath%>wechat/js/mui.min.js"></script>
			<script src="<%=basePath%>wechat/js/jquery-1.9.1.js"></script>
			<script src="<%=basePath%>wechat/jsme/user.wechat.js"></script>
		</head>

		<body>
			<input id="basePath" value="<%=basePath%>" style="display: none"/>
			
			<nav class="mui-bar mui-bar-tab">
				<a href="#" style="float: left; width: 50%;" id="home">
					<div class="icondiv">
						<img id="img1" src="<%=basePath%>wechat/images/lightRed.png" class="footer" />
					</div>
					<span id="home-text"  class="mui-tab-item" style="height: 5px;font-size: 12px;">慧脱贫</span>
				</a>
				<a id="me"  style="float: right;width: 50%;">
					<div class="icondiv" >
						<img id="img2" src="<%=basePath%>wechat/images/me.png" class="footer" />
					</div>
					<span id="me-text" class="mui-tab-item" style="font-size: 12px;height: 5px;">我</span>
				</a>
			</nav>
			<div class="mui-content">
				<div id="tabbar" class="mui-control-content mui-active">
					<div class="headimage">
						<img src="<%=basePath%>wechat/images/Main_page.jpg" style="height: 100%;width: 100%;" />
					</div>
					<div class="mui-content">
						页面开发测试中.......
					</div>
				</div>
		</body>

		</html>