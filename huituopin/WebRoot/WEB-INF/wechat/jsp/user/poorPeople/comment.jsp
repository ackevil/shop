<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
		<meta charset="utf-8">
		<title>慧脱贫</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<script src="<%=basePath%>wechat/js/mui.min.js"></script>
		<link rel="stylesheet" href="<%=basePath%>wechat/css/mui1.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/app.css" />
		<style type="text/css">
			.name{
				font-size: 13px;
				float: left;
				color:cornflowerblue;
			}
			.pingjia{
				font-size: small;
				padding-top:5px;
				line-height: 1.2;
				white-space :normal;
				color: black;
			}
			.time{
				line-height: 2;
				color: #9B9B9B;
			}
		</style>
	</head>
	<body>
		<header class="mui-bar mui-bar-nav" >
			<a class="mui-action-back mui-pull-left" style="background-image: url(<%=basePath%>wechat/images/jiantou.png);background-size:70%;background-repeat:no-repeat ;height: 40px;width:30px;margin-top: 13px;margin-left: 4px;"></a>
			<h1 class="mui-title">全部评价</h1>
		</header>
		<div class="mui-content">
			<c:forEach items="${commentInfoList}" var="comment" varStatus="i">
				<ul class="mui-table-view ">
					<li class="mui-table-view-cell">
						<a style="margin-top: -14px;">
							<img class="mui-media-object mui-pull-left head-img" src="<%=basePath %>${user.userWcAvatar}" style="width:48px;height:48px;max-width:50px;max-height:50px"/>
							<div class="xiangxi" >
								<span class="name">${user.userWcNickname}</span>
								<br />
								<p class='mui-ellipsis pingjia'>
									${comment.clothComContent}<br />
									<c:forEach items="${list[i.index]}" var="picture">
										<img style="height: 50px;width: 50px;margin-top: 10px;" src="<%=path%>${picture.clothPicPath}"/>
									</c:forEach>
									<br />
									<span class="time">${comment.clothComIntime}</span>
								</p>							
							</div>
						</a>
					</li>
				</ul>
			</c:forEach>
	</body>
</html>