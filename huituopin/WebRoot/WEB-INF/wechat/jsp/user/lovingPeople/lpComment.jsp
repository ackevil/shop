<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>慧脱贫</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<script src="<%=basePath%>wechat/js/mui.min.js"></script>
		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=basePath%>wechat/css/mui1.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/app.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/QuanBuPingLun.css" />
		<style>
			.login-lastline{
				height:80px;
				width:100%;
				background-color: #efeff4;
				padding-top: 20px;
				padding-left: 15px;
				padding-right: 15px;
			}
			.login-button{
				width:100%;
				height:45px;
				outline: none;
				font-size: 14px;
				color: white;
				border-color: transparent;
				background-color:  #fc5959;
			}
		</style>
	</head>
	<body>
		<header class="mui-bar mui-bar-nav" >
			<a class="mui-action-back mui-pull-left" style="background-image: url(<%=basePath%>wechat/images/jiantou.png);background-size:70%;background-repeat:no-repeat ;height: 40px;width:30px;margin-top: 13px;margin-left: 4px;"></a>
			<h1 class="mui-title">全部评论</h1>
		</header>
		<div class="mui-content">
			<c:forEach items="${list}" var="pc">
				<ul class="mui-table-view" >
					<li class="mui-table-view-cell">
						<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=path%>${pc.pcInfo[1]}" style="width:48px;height:48px;max-width:50px;max-height:50px">
						<div class="mui-media-body">
							<span class="mui-media-body-title" style="display:block;width:80%;font-size: 12px;overflow: hidden;text-overflow:ellipsis;white-space: nowrap;">${pc.pcInfo[0]}</span>
							<p style="color: #232323;font-size: 13px;line-height: 1.2;">${pc.pcInfo[4]}</p>
							<c:forEach items="${pc.ppList}" var="pp">
								<img src="<%=path%>${pp.productPicPath}"/>
							</c:forEach>
							<p class='mui-ellipsis' style="letter-spacing: 0px;font-size: 12px;">
								<span>${pc.pcInfo[5]}</span>
								<span>${pc.pcInfo[2]}</span>
							</p>
						</div>
					</li>
				</ul>
			</c:forEach>
		</div>
	</body>
</html>
