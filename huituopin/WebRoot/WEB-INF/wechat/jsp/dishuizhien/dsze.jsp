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
		<title>慧脱贫</title>
		<link rel="stylesheet" href="<%=path %>/wechat/css/public_top.css" type="text/css">
		<link rel="stylesheet" href="<%=path %>/wechat/css/dishuizhien.css" type="text/css">
		<link rel="stylesheet" href="<%=path %>/wechat/css/mui.css" type="text/css">
		<script src="<%=path %>/wechat/js/mui.min.js"></script>	
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
		</style>
	</head>
	<body>
		<header class="mui-bar mui-bar-nav" >
			<a class="mui-action-back mui-pull-left" style="background-image: url(<%=path %>/wechat/img/jiantou.png);background-size:70%;background-repeat:no-repeat ;height: 40px;width:30px;margin-top: 13px;margin-left: 4px;"></a>
			<h1 class="mui-title">滴水之恩</h1>
		</header>
	    <div class="mui-content">
	    
	    	<c:forEach items="${dszeList}" var="dsze">
	    	
	        <ul class="mui-table-view" style="height: 315px;"> 	
	            <a href="/dsze/dsze_sub?id=${dsze.dszeId}" style="color: black;">
	           		<li class="mui-table-view-cell" style="height: 240px;width: 100%;padding:10px 8px;">
	                	<img src="${dsze.dszeMainPic}" style="height: 240px;width: 100%;"/>
		            </li>
		            <h5>${dsze.dszeName}</h5>
				    <div style="float: left;width: 33%;height: 44px;padding-left: 8px;border-right: 1px solid #CFCFCF;">
				        <span style="color: #fc5959;font-size: 15px;margin-top: -10px;">${dsze.dszeTotalFund}元</span><br />
				        <span style="font-size: 12px;">筹款目标</span>
				    </div>
				    <div style="float: left;width: 34%;height:44px;border-right: 1px solid #CFCFCF;padding-left: 8%;">
				        <label style="color: #fc5959;font-size: 15px;">${dsze.dszeRaised}元</label><br />
				        <span style="font-size: 12px;">已筹金额</span>
				    </div>
				    <div style="float: left;width: 33%;height: 44px;padding-left: 8%">
				        <span style="color: #fc5959;font-size: 15px;">${dsze.dszeSuportNum}人</span><br />
				        <span style="font-size: 12px;">支持人数</span>
				    </div>
		        </a> 
	        </ul>
	        <div style="height: 10px;"></div>
	        </c:forEach>
	      	
	  	</div>
	</body>
</html>
