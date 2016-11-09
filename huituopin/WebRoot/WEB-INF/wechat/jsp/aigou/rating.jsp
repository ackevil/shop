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
		<script type="text/javascript" src="<%=basePath%>wechat/js/jquery-1.9.1.js"></script>
		<script src="<%=path%>/wechat/js/mui.min.js"></script>
		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=basePath %>wechat/css/mui1.css">
		<link href="<%=basePath%>wechat/css/mui.picker.css" rel="stylesheet" />
		<link href="<%=basePath%>wechat/css/mui.poppicker.css" rel="stylesheet" />
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="<%=basePath %>wechat/css/app.css" />
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
				line-height: 4;
				color: gray;
			}
			.icondiv{
				height: 25px;
				width: 25px;
				margin: 0 auto;
				
			}
			.iconimg{
				max-height: 20px;
				max-width: 20px;
				margin-left: -2px;
			}
			.footericon{
				width: 13%;
				height: 100%;
				float: left;
				border-right: 1px solid gainsboro;
				border-top:1px solid gainsboro;
			}
			.mui-navigate-right:after, .mui-push-right:after{
				right: 0;
			}
			.mui-popup{
				top:40%;
			}
			.mui-popup-buttons{
				height: 55px;
			}
			.mui-popup-button{
				padding-top: 6px;
			}
			#content img{
				width:100%;
				height:auto;
			}
		</style>
	</head>
	<body>
		<header class="mui-bar mui-bar-nav" >
<a class="mui-action-back mui-pull-left" style="background-image: url(<%=basePath %>wechat/img/jiantou.png);background-size:70%;background-repeat:no-repeat ;height: 40px;width:30px;margin-top: 13px;margin-left: 4px;"></a>			<h1 class="mui-title">全部评价</h1>
		</header>
		<div class="mui-content">
			<ul class="mui-table-view "style="border-top: 1px solid #CFCFCF;">
				<c:if test="${list==null}">
					<li class="mui-table-view-cell" style="height: 175px; text-align:center">
					暂无评价
					</li>
				</c:if>
				<c:forEach items="${list}" var="pc">
					<li class="mui-table-view-cell" style="height: auto;">
						<a href=""  style="margin-top: -14px;">
							<img class="mui-media-object mui-pull-left head-img" src="<%=basePath%>${pc.pcInfo[2]}" style="height: 48px;max-width: 50px;"/>
							<div class="xiangxi" >
								<span class="name">${pc.pcInfo[1]}</span>
								<br />
								<p class='mui-ellipsis pingjia'>
								${pc.pcInfo[3]}<br />
								<c:forEach items="${pc.ppList}" var="pp">
									<img style="height: 50px;width: 50px;margin-top: 10px;" src="<%=path %>${pp.productPicPath}"/>								
								</c:forEach>
								<br />
								<span class="time" >${pc.pcInfo[5]} ${pc.pcInfo[4]}</span>
								</p>
								
							</div>
						</a>
					</li>
				</c:forEach>
			</ul>
			<div style="height:50px"></div>
		</div>
		
	</body>
</html>



