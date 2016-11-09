<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" href="<%=basePath%>wechat/css/mui.min.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/app.css"/>
		<style type="text/css">
			.headimage{
				height: 180px;
				width: 100%;
			}
			.icondiv{
				height: 30px;
				width: 30px;
				margin: 0 auto;
			}
			.iconimg{
				max-height: 35px;
				max-width: 35px;
				padding-top: 5px;
			}
			.footer{
				height: 30px;
				width: 15px;
				margin: 0 auto;
				padding-top: 10px;
			}
			.footerimg{
				max-height: 15px;
				max-width: 15px;
				padding-top: 10px;
				
			}
		</style>

  </head>
  
  <body>	
		<ul class="mui-table-view "style="height: 64px;">
			<li class="mui-table-view-cell" >
				<a href="<%=path%>/pp/ppDetailInfo?userId=${sessionScope.user.userId}" class="mui-navigate-right" style="padding-top:8px;height: 56px;">
					<img class="mui-media-object mui-pull-left head-img" src="<%=path%>/${sessionScope.user.userWcAvatar}" style="height: 48px;max-width: 50px;"/>
					<div class="xiangxi" >
						<span style="font-size: 13px;float: left;">${sessionScope.user.userWcNickname}</span>
						<div style="height:21px;margin-left:5px;float: left;"><img src="<%=basePath%>wechat/images/pinkun.png" style="max-height: 21px;max-width: 55px;"/></div>
						<div style="margin-left:8px;float: left;"><img src="<%=basePath%>wechat/images/correct.png" style="max-height: 15px;max-width: 55px;"/></div>
						<br/>
						<!-- 
						<p class='mui-ellipsis' style="font-size: small;line-height: 1;">
						湖南省吉首市大庙村第二小学
						</p>
						 -->
					</div>
				</a>
			</li>
		</ul>
		<div style="height:10px;"></div>
		<ul class="mui-table-view mui-table-view-chevron" style="font-size: 13px;">
			<li class="mui-table-view-cell" style="height: 35px;">
				<a href="<%=path%>/pp/reciveInfo?type=other&userId=${sessionScope.user.userId}" class="mui-navigate-right" style="padding-top: 8px;">收件信息</a>
			</li>
			<!-- 
			<li class="mui-table-view-cell"style="height: 35px;">
				<a href="<%=path%>/pp/helps?userId=a" class="mui-navigate-right"style="padding-top: 8px;">我的获助</a>
			</li>
			 -->
			<li class="mui-table-view-cell"style="height: 35px;">
				<a href="<%=path%>/pp/comment" class="mui-navigate-right"style="padding-top: 8px;">我的评价</a>
			</li>
			<li class="mui-table-view-cell"style="height: 35px;">
				<a href="<%=path%>/pp/getting" class="mui-navigate-right"style="padding-top: 8px;">我的领取</a>
			</li>
		</ul>
		<nav class="mui-bar mui-bar-tab">
					<jsp:include page="../footer.jsp"></jsp:include>
		</nav>
	</body>
</html>
