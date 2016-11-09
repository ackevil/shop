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
		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=basePath%>wechat/css/mui1.css"/>
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/app.css" />
		<link href="<%=basePath%>wechat/css/mui.picker.css" rel="stylesheet" />
		<link href="<%=basePath%>wechat/css/mui.poppicker.css" rel="stylesheet" />
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
				background-color: #efeff4;
				border-top: 1px solid gainsboro;
				
				font-size: 14px;
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
			#content img{
				width:100%;
				height:auto;
			}
		</style>
		<script type="text/javascript">
		function collection(productId){
			$.ajax({
				type:"POST",
				url:"addCollection",
				data:{
					productId:productId
				},
				success:function(msg){
					if(msg=="null"){
						var btnArray = ["请先注册"];
	                    mui.alert(' ',' ',btnArray, function() {
	                    	window.location.href= "<%=basePath%>register/signin";
	                    });
					}
					else if(msg=="true"){ 
						  var btnArray = ["收藏成功"];
	                      mui.alert(' ',' ',btnArray, function() {
	                      });
					}
					else{
						 var btnArray = ["该产品您已经收藏，请不要重复操作"];
	                      mui.alert(' ',' ',btnArray, function() {
	                      });
					 }
				}
			});
		}
		
		</script>
	</head>

	<body>
		<header class="mui-bar mui-bar-nav" style="border-bottom: 1px solid gainsboro;">
			<a class="mui-action-back mui-pull-left" style="background-image: url(<%=basePath%>wechat/img/jiantou.png);background-size:70%;background-repeat:no-repeat ;height: 40px;width:30px;margin-top: 13px;margin-left: 4px;"></a>			
			<h1 class="mui-title">商品详情</h1>
		</header>
		<div class="mui-content">
		<input id="basePath" type="hidden" value="<%=basePath %>"/>
		<input id="pId" type="hidden" value="${product.productId}"/>
		<input id="pMainPicture" type="hidden" value="<%=path%>${product.productMainpicPath}"/>
		<input id="pPrice" type="hidden" value="${product.productPrice }"/>
		<input id="pName" type="hidden" value="${product.productName}"/>
		<input id="pGuiGe" type="hidden" value="${product.productSpecificationValue}"/>
		<input id="pRemain" type="hidden" value="${productRemain}"/>
		<input id="pLimit" type="hidden" value="${product.productStopbuyNum }"/>
		<input id="pCredict" type="hidden" value="${product.productCredit }"/>
		<div id="slider" class="mui-slider" >
			<div class="mui-slider-group mui-slider-loop">
				<!-- 额外增加的一个节点(循环轮播：第一个节点是最后一张轮播) -->
				<div class="mui-slider-item mui-slider-item-duplicate">
					<a href="#">
						<img src="<%=path%>${product.productPic3Path}" style="height: 360px;">
					</a>
				</div>
				<!-- 第一张 -->
				<div class="mui-slider-item">
					<a href="#">
						<img src="<%=path%>${product.productMainpicPath}" style="height: 360px;">
					</a>
				</div>
				<!-- 第二张 -->
				<div class="mui-slider-item">
					<a href="#">
						<img src="<%=path%>${product.productPic1Path}"style="height: 360px;">
					</a>
				</div>
				<!-- 第三张 -->
				<div class="mui-slider-item">
					<a href="#">
						<img src="<%=path%>${product.productPic2Path}"style="height: 360px;">
					</a>
				</div>
				<!-- 第四张 -->
				<div class="mui-slider-item">
					<a href="#">
						<img src="<%=path%>${product.productPic3Path}"style="height: 360px;">
					</a>
				</div>
				<div class="mui-slider-item mui-slider-item-duplicate">
					<a href="#">
						<img src="<%=path%>${product.productMainpicPath}" style="height: 360px;">
					</a>
				</div>
			</div>
			<div class="mui-slider-indicator">
				<div class="mui-indicator mui-active"></div>
				<div class="mui-indicator"></div>
				<div class="mui-indicator"></div>
				<div class="mui-indicator"></div>
			</div>
		</div>
		<div class="name">
			<!--设置字的长度-->
			<label style="white-space :normal;font-size: 13px;">${product.productName}</label><br />
			<label style="color: #fc5959;">¥${product.productPrice }</label><br />
			<div style="width: 33%;float: left;color: gray;font-size: 11px;">快递：0.00</div>
			<div style="width: 34%;float: left;padding-left: 15px;color: gray;font-size: 11px;">兑换：${product.productCredit }积分</div>
			<div style="width: 33%;float: left;padding-left: 5px;color: gray;font-size: 11px;">
				<span style="float:right;padding-right: 15px;">湖南长沙</span> 
			</div>
		</div>	
		<ul class="mui-table-view "style="">
			<li class="mui-table-view-cell" style="padding:20px 25px;height: 35px;">
				<a href="<%=basePath %>wechat/aigou/rating?pId=${product.productId}" class="mui-navigate-right" style="padding:0;">评论
				</a>
			</li>
		</ul>
			
		<div style="height: 10px ;"></div>
		<div class="content">
			<div id="content" style="background-color: white;padding-top: 25px;padding-left: 10px;padding-right: 10px">
				${product.productDetail }
			</div>
		</div>
		<div style="height: 55px;"></div>
		<c:if test="${userType != 'poor' }">
			<div style="height: 45px;background-color: white;position:fixed;bottom:0px;width:100%;">
				<c:if test="${product.productIsOver == false}">
					<button class="footericon">
						<div class="icondiv"><img src="<%=basePath%>wechat/img/message.png" class="iconimg"/></div>
					    <div class="mui-media-body" style="margin-top: -4px;font-size: 12px;color: gray;">客服</div>
					</button>
					<button class="footericon" onclick="collection('${product.productId}')">
						<div class="icondiv"><img src="<%=basePath%>wechat/img/xingxing.png" class="iconimg"/></div>
					    <div class="mui-media-body" style="margin-top: -4px;font-size: 12px;color: gray;">收藏</div>
					</button>
					<button id='exchange'class="footericon" style="border-right: 0;">
						<div class="icondiv"><img src="<%=basePath%>wechat/img/money.png" class="iconimg"/></div>
					    <div class="mui-media-body" style="margin-top: -4px;font-size: 12px;color: gray;">兑换</div>
					</button>
					<button id='shopCar' style="width: 30.5%;height: 100%;float: left;background-color: #FE6D47;color: white;padding: 12px 11px;font-size: 15px">
						加入购物车
					</button>
					<button id='pay' style="width: 30.5%;height: 100%;float: left;background-color: #fc5959;color: white;padding: 12px 18px;font-size: 15px;">
						立即购买					
					</button>
				</c:if>
				<c:if test="${product.productIsOver == true}">
					<button style="width: 100%;height: 100%;background-color: #dddddd;color: black;padding: 12px 11px;font-size: 15px">
					缺货
					</button>
				</c:if>
			</div>
		</c:if>
		</div>
		<script src="<%=basePath%>wechat/js/mui.js"></script>
		<script src="<%=basePath%>wechat/js/mui.picker.js"></script>
		<script src="<%=basePath%>wechat/js/mui.poppicker.js"></script>
		<script src="<%=basePath%>wechat/js/mui.exchange.js"></script>
		<script src="<%=basePath%>wechat/js/mui.ShopCar.js"></script>	
	
		<script type="text/javascript">
			(function($, doc) {
				$.init();
				$.ready(function() {
					//普通示例
					var userPicker = new $.exchangeGoodPicker();				
					var exchangeBtn = doc.getElementById('exchange');
					var userResult = doc.getElementById('userResult');
					exchangeBtn.addEventListener('tap', function(event) {
						userPicker.show(function(items) {
							//userResult.innerText = JSON.stringify(items[0]);
							//返回 false 可以阻止选择框的关闭
							//return false;
						});
					}, false);
						
					var shopCar = new $.shopCarPicker();					
					var shopCarBtn= doc.getElementById('shopCar');
					var userResult = doc.getElementById('userResult');
					shopCarBtn.addEventListener('tap', function(event) {
						shopCar.show(function(items) {
							//userResult.innerText = JSON.stringify(items[0]);
							//返回 false 可以阻止选择框的关闭
							//return false;
						});
					}, false);
					
					var pay = new $.PopPicker();					
					var payBtn = doc.getElementById('pay');
					var userResult = doc.getElementById('userResult');
					payBtn.addEventListener('tap', function(event) {
						pay.show(function(items) {
							//userResult.innerText = JSON.stringify(items[0]);
							//返回 false 可以阻止选择框的关闭
							//return false;
						});
					}, false);
						});
			})(mui, document);
					</script>
</body>
</html>

