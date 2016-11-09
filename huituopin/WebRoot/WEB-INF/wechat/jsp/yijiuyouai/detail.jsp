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
		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=basePath%>wechat/css/mui1.css"/>
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/app.css" />
		<link href="<%=basePath%>wechat/css/mui.picker.css" rel="stylesheet" />
		<link href="<%=basePath%>wechat/css/mui.poppicker.css" rel="stylesheet" />
		<script src="<%=path %>/wechat/js/mui.min.js"></script>	
		<script type="text/javascript" src="<%=basePath%>wechat/js/jquery-1.9.1.js"></script>
		<style type="text/css">
			.des {
				margin: .5em 0;
			}
			.des>li {
				font-size: 14px;
				color: #8f8f94;
			}
			.name{
				
				width:100%;
				height: 95px;
				background-color: white;
				font-size: 80%;
				padding-top: 8px;
				padding-left: 8px;
				border-bottom: 1px solid gainsboro;
			}
			.style{
				height: 70px;
				width: 100%;
				background-color: white;
				font-size: 14px;
				list-style-type :none;
				padding-top: 4px;
				padding-left: 8px;
			}
			.content{
				width: 100%;
				background-color: white;
				border-top: 1px solid gainsboro;
				border-bottom: 1px solid gainsboro;
				padding:5px 10px;
				font-size: 14px;
				word-wrap: break-word;
			}
			.style{
				height: 40px;
				width: 100%;
				background-color: white;
				font-size: 14px;
				list-style-type :none;
				padding-top: 10px;
				padding-left: 8px;
				color: black;
				border-bottom:1px solid gainsboro ;
			}
			.mui-navigate-right:after, .mui-push-left:after, .mui-push-right:after{
				top:50%;
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
		</style>
	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-pull-left" style="background-image: url(<%=basePath%>wechat/img/jiantou.png);background-size:70%;background-repeat:no-repeat ;height: 40px;width:30px;margin-top: 13px;margin-left: 4px;"></a>			
			<h1 class="mui-title">商品详情</h1>
		</header>
		<div class="mui-content">
		</div>
		<div id="slider" class="mui-slider" >
			<div class="mui-slider-group mui-slider-loop">
				<!-- 额外增加的一个节点(循环轮播：第一个节点是最后一张轮播) -->
				<div class="mui-slider-item mui-slider-item-duplicate">
					<a href="#">
						<img src="<%=path%>${detailPics[2].clothPicPath}" style="height: 360px;">
					</a>
				</div>
				<c:forEach items="${detailPics}" var="pic" begin="0" end="3">
					<div class="mui-slider-item">
					<a href="#">
						<img src="<%=path%>${pic.clothPicPath}" style="height: 360px;">
					</a>
				</div>
				</c:forEach>
				<div class="mui-slider-item mui-slider-item-duplicate">
					<a href="#">
						<img src="<%=path%>${detailPics[0].clothPicPath}" style="height: 360px;">
					</a>
				</div>
			</div>
			<div class="mui-slider-indicator">
				<div class="mui-indicator mui-active"></div>
				<div class="mui-indicator"></div>
				<div class="mui-indicator"></div>
			</div>
		</div>
		<div class="name">
			<!--设置字的长度-->
			<label style="word-spacing:10px;font-size: 13px;">${cloth.clothName}</label><br />
			<time style="color: gray;font-size: 10px;line-height: 2.5em;">发布于${cloth.clothIntime}</time>
		</div>
		<ul class="mui-table-view "style="">
			<li class="mui-table-view-cell" style="padding:20px 25px;height: 35px;">
				<a href="<%=basePath%>wechat/yjya/rating?clothId=${cloth.clothId}" class="mui-navigate-right" style="padding:0;">评论
				</a>
			</li>
		</ul>
		<div style="height: 10px;"></div>
		<div class="content">
			<label>${cloth.clothDetailinfo}</label><br />
		</div>
		<div style="height: 20px;"></div>
		<div>
			<c:if test="${sessionScope.user.userType == false}">
				<input id="alertBtn" type="button" onclick="window.location.href='<%=basePath%>lp/wantDonate';" style="width:100%;height:50px;background-color: #fc5959;color: white;" value="我要捐赠"/>
			</c:if >
			<c:if test="${sessionScope.user.userType == true}">
				<c:if test="${cloth.clothState==0}">
					<input id="alertBtn" type="button" onclick="receive()" style="width:100%;height:50px;background-color: #fc5959;color: white;" value="我要领取"/>
				</c:if>
				<c:if test="${cloth.clothState!=0}">
					<input id="alertBtn" type="button"  style="width:100%;height:50px;background-color: #cfcfcf;color: white;" value="已被领取"/>
				</c:if>
			</c:if>
		</div>
		<input type="hidden" id="clothId" value="${cloth.clothId}"/>
		<script type="text/javascript">
			function receive(){
				$.ajax({
			        type:"POST",
			        url:"<%=basePath%>wechat/yjya/checkUserType",
			        error:function(data){
			            alert("出錯了！");
			        },
			        success:function(data){
			           if(data == "poor_no"){
			        		alert("您領取衣物的數量已達到上限！")
			        	}
			            else  if (data == "poor") {
			                 window.location.href="<%=basePath%>wechat/yjya/confirmReceive?clothId=" + $("#clothId").val();
			            }
			            else if(data == "loving") {
			                 alert("爱心人士不能领取！");
			            }
			            else{
			            	alert("请先注册！");
			            	window.location.href="<%=basePath%>register/signin";
			            
			            }
			        }
			    });
			}
		</script>
</body>
</html>

