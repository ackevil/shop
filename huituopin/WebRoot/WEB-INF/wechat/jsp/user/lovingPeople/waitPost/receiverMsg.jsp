<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>	
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="<%=basePath%>wechat/css/mui1.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/footer.css"/>
		<link rel="stylesheet" href="<%=basePath%>wechat/css/aiGou.css" /> 
	</head>
	<body>
		<header class="mui-bar mui-bar-nav" >
			<a class="mui-action-back mui-pull-left" style="background-image: url(<%=basePath%>wechat/img/jiantou.png);background-size:70%;background-repeat:no-repeat ;height: 40px;width:30px;margin-top: 13px;margin-left: 4px;"></a>
			<h1 class="mui-title">领取人信息</h1>
		</header>
		<div class="mui-content">
			<ul class="mui-table-view mui-table-view-chevron" style="font-size: 13px;color: gray;">
				<li class="mui-table-view-cell" style="height: 45px;">
					<a href="#notifications" style="padding-top: 8px;">
						收货人：${sa.shippingAddName}
					</a>
				</li>
				<li class="mui-table-view-cell"style="height: 45px;">
					<a href="#privacy" style="padding-top: 8px;">
						联系电话：${sa.shippingAddPhone}
					</a>
				</li>
				<li class="mui-table-view-cell"style="height: 45px;">
					<a href="#general" style="padding-top: 8px;">
						收货地址：${sa.shippingAddProvince}
					</a>
				</li>
				
			</ul>
		</div>
		<nav class="mui-bar mui-bar-tab">
			<jsp:include page="../../footer.jsp"></jsp:include>
		</nav>
	</body>
</html>