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
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
		<title>慧脱贫</title>
		<link rel="stylesheet" href="<%=path %>/wechat/css/zhongdajiuzhu_detail.css" type="text/css">
		<link rel="stylesheet" href="<%=path %>/wechat/css/mui.css" type="text/css">
		<script src="<%=path %>/wechat/js/mui.min.js"></script>	
		<style>
			p{
				color: #4B4B4B;
			}
			.mui-table-view:after{
				background-color:white;
			}
			.mui-ellipsis{
				white-space:normal;
			}
		</style>
	</head>
	<body >
        <header class="mui-bar mui-bar-nav" >
			<a class="mui-action-back mui-pull-left" style="background-image: url(<%=path %>/wechat/img/jiantou.png);background-size:70%;background-repeat:no-repeat ;height: 40px;width:30px;margin-top: 13px;margin-left: 4px;"></a>			<h1 class="mui-title">项目详情</h1>
		</header>
	    <div class="mui-content">      	
            <article>
            	
                <h5 style="text-align:center;padding-top: 15px;color: #4B4B4B;font-size: 15px;">${activity.activityName}</h1>
	            <section class="detail">
	           		 ${activity.activityDetailInfo}
					<div style="height: 30px;"></div>
	            </section>
           	</article>
           	

		 	<div style="height: 60px;"></div>
		 	<div style="height: 50px;position:fixed;bottom:0px;width:100%;background-color:#efeff4">
			    <div style="float: left;width: 25%;height: 50px;padding-left: 8px;border-right: 1px solid #CFCFCF;">
					<span style="color: #fc5959;font-size: 15px;margin-top: -10px;">${activity.activityRaised} 元</span><br />
					<span style="font-size: 12px;">已筹款</span>
				</div>
				<div style="float: left;width: 25%;height:50px;border-right: 1px solid #CFCFCF;padding-left:8%;">
					<span style="color: #fc5959;font-size: 15px;">${activity.activitySuportNum} 人</span><br />
					<span style="font-size: 12px;">支持数</span>
				</div>
				<div style="float: left;width: 20%;height: 50px;padding-left: 5%">
					<span style="color: #fc5959;font-size: 15px;">${day} 天</span><br />
					<span style="font-size: 12px;">剩余时间</span>
				</div>	
				<div style="float: left;width: 30%;height: 50px;">
					<c:if test="${day==0 }">
						<button style="height: 30px;width: 90px;float: right;margin-right: 8px;background-color: #cccccc;color: black;border: 0;margin-top: 8px;">活动已结束</button>
					</c:if>
					<c:if test="${day!=0 }">
						<button style="height: 30px;width: 90px;float: right;margin-right: 8px;background-color: #fc5959;color: white;border: 0;margin-top: 8px;"onclick='window.location.href="/wechat/pay/activitySupport?id=${activity.activityId}"'>立即支持</button>
					</c:if>
				</div>	
			</div>
		</div>	
	</body>
</html>
