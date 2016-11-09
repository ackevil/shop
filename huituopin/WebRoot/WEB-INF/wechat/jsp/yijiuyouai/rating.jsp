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
		<title></title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<script src="<%=path%>/wechat/js/mui.min.js"></script>
		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=path%>/wechat/css/mui1.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="<%=path%>/wechat/css/app.css" />
		<script src="<%=path %>/wechat/js/mui.min.js"></script>
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
				color: gray;
			}
		</style>
	</head>
	<body>
		<header class="mui-bar mui-bar-nav" >
<a class="mui-action-back mui-pull-left" style="background-image: url(<%=path%>/wechat/img/jiantou.png);background-size:70%;background-repeat:no-repeat ;height: 40px;width:30px;margin-top: 13px;margin-left: 4px;"></a>			<h1 class="mui-title">全部评价</h1>
		</header>
		<div class="mui-content">
			<ul class="mui-table-view "style="">
				<c:if test="${commentInfoList==null}">
					<li class="mui-table-view-cell" style="height: 175px; text-align:center">
					暂无评价
					</li>
				</c:if>
				<c:forEach items="${commentInfoList}" var="commentInfo">
					<li class="mui-table-view-cell">
						<a href=""  style="margin-top: -14px;">
							<img class="mui-media-object mui-pull-left head-img" src="<%=basePath %>${commentInfo[2]}" style="height: 48px;max-width: 50px;"/>
							<div class="xiangxi" >
								<span class="name">${commentInfo[1]}</span>
								<br />
								<p class='mui-ellipsis pingjia'>
								${commentInfo[3]}<br />
								<c:forEach items="${commentPicList}" var="commentPic">
									<img style="height: 50px;width: 50px;margin-top: 10px;" src="<%=path%>${commentPic.clothPicPath}"/>
								</c:forEach>
								<br />
								<span class="time">${commentInfo[4]}</span>
								</p>
							</div>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</body>
</html>


