<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="<%=path %>/wechat//css/public_top.css" type="text/css">
		<link rel="stylesheet" href="<%=path %>/wechat//css/dishuizhien.css" type="text/css">	
		<link rel="stylesheet" href="<%=path %>/wechat//css/mui.css" type="text/css">
		<script src="<%=path %>/wechat//js/mui.min.js"></script>	
		<title>慧脱贫</title>
		<style>
			.mui-content > .mui-table-view:first-child{
				margin: 0;;
			}
			h5{
				color: black;
				padding-left: 8px;
				padding-right: 8px;
				word-spacing: -5px;
			}
			.mui-table-view:after{
				background-color: transparent;
			}
			.mui-table-view-cell > a:not(.mui-btn)
			{
			    position: relative;
			
			    display: block;
			    overflow: hidden;
			
			    margin: auto;
			    padding: inherit;
			
			    white-space: nowrap;
			    text-overflow: ellipsis;
			
			    color: inherit;
			  }
		</style>
	</head>
	<body style="background-color:#efeff4">
 		<header class="mui-bar mui-bar-nav" >
			<a class="mui-action-back mui-pull-left" style="background-image: url(<%=path %>/wechat/img/jiantou.png);background-size:70%;background-repeat:no-repeat ;height: 40px;width:30px;margin-top: 13px;margin-left: 4px;"></a>
			<h1 class="mui-title">重大救助</h1>
		</header>
	    <div class="mui-content">
	    	 <ul class="mui-table-view" style="height: 360px;"> 	
	            <a href="#" style="color: black;">
	           		<li class="mui-table-view-cell" style="height: 275px;width: 100%;padding:0;">
	                	<img src="${activity.activityMainPic}" style="height: 275px;width: 100%;"/>
		            </li>
		            <div style="height: 15px;"></div>
		            <h5>${activity.activityName}</h5>
				    <div style="float: left;width: 33%;height: 44px;padding-left: 8px;border-right: 1px solid #CFCFCF;">
				        <span style="color: #fc5959;font-size: 15px;margin-top: -10px;">${activity.activityTotalFund}元</span><br />
				        <span style="font-size: 12px;">筹款目标</span>
				    </div>
				    <div style="float: left;width: 34%;height:44px;border-right: 1px solid #CFCFCF;padding-left: 8%;">
				        <label style="color: #fc5959;font-size: 15px;">${activity.activityRaised}元</label><br />
				        <span style="font-size: 12px;">已筹金额</span>
				    </div>
				    <div style="float: left;width: 33%;height: 44px;padding-left: 8%">
				        <span style="color: #fc5959;font-size: 15px;">${activity.activitySuportNum}人</span><br />
				        <span style="font-size: 12px;">支持人数</span>
				    </div>				    
		        </a> 
	        </ul>
	        <div style="height: 5px;background-color: white;"></div>
        	<ul class="mui-table-view"> 	
				<li class="mui-table-view-cell"style="height: 35px;padding: 4px;font-size: 14px;">
					<a href="/zdjz/detail?id=${activity.activityId}" class="mui-navigate-right">查看图文详情</a>
				</li>
			</ul> 
			<ul class="mui-table-view"> 	
				<li class="mui-table-view-cell"style="height: 35px;padding: 4px;font-size: 14px;">
					<a href="/zdjz/rating?id=${activity.activityId}" class="mui-navigate-right">评论</a>
				</li>
			</ul> 
			
			<div style="height:10px"></div>
           	<div style="border-bottom:0.5px solid #c8c7cc">
	           	<div style="background-color:white;height:30px;"><span style="font-size:13px;display:block;padding-left:15px;padding-top:5px;">TA的支持者</span></div>
	           	
	           		<c:if test="${ empty list }">
	           		<ul class="mui-table-view" style="border-top:1px solid #CFCFCF;font-size:14px;">
					<li class="mui-table-view-cell">
						暂时没有支持者!来做第一个吧!!!
					</li>
					</ul>
	           	</c:if>
	           	
	           	<c:if test="${not empty list }">
	           		<c:forEach  items="${list}" var="map">
	           			<ul class="mui-table-view" style="border-top:1px solid #CFCFCF;font-size:14px;">
							<li class="mui-table-view-cell">
								
								<img class="mui-media-object mui-pull-left head-img" src="<%=basePath %>${map.get('user').getUserWcAvatar()}" style="height: 48px;max-width: 50px;"/>					
								
								<div class="right" style="margin-left:55px;">
									<span class="name">${map.get('user').getUserWcNickname()}</span><span style="font-size:13px;color:gray">支持了<span style="color:red">${map.get("activitySupport").getActSupMoney()}</span>元</span><br />
									<div style="height: 9px;"></div>
									<span class='mui-ellipsis rating'>
									${map.get("activitySupport").getActSupComment()}
									</span>					
								</div>	
								<div class="time">
										<fmt:formatDate value='${map.get("activitySupport").getActSupTime()}' pattern="yyyy-MM-dd HH:mm:ss"/>
								</div>									
							</li>
						</ul>
	           		
	           		
	           		
	           		
	           		
	           		</c:forEach>
	           	
	           	</c:if>
	           	
	           	
	           	
	           	
			</div>
			
			
			
			
			
			
			
			
			
			
			
			<div style="height:50px"></div>
			
			
			<form action="/wechat/pay/activitySupport">
				<c:if test="${day==0 }">
					<input id="btn" type="button" value="活动已结束" style="height:48px;position:fixed;bottom: 0px;border:0px;width:100%;background-color: #cccccc;color: black;" />
				</c:if>
				<c:if test="${day!=0 }">
					<input id="btn" type="submit" value="立即支持" style="height:48px;position:fixed;bottom: 0px;border:0px;width:100%;background-color: #fc5959;color: white;" onclick="click()" />
				</c:if>
				<input type="hidden" name="id" value="${activity.activityId}">
			</form>
      </div>
</div>
</body>
</html>
