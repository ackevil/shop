<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<head>
		<meta charset="utf-8">
		<title>我的领取</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="<%=basePath%>wechat/css/mui.css">
		<script src="<%=basePath%>wechat/js/footer.js"></script>
		<script src="<%=basePath%>wechat/js/mui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>wechat/js/jquery-1.9.1.js"></script>
		<link rel="stylesheet" href="<%=basePath%>wechat/css/footer.css">
		<style>
			html,
			body {
				background-color: #efeff4;
			}
			.mui-bar~.mui-content .mui-fullscreen {
				top: 44px;
				height: auto;
			}		

			.mui-slider-indicator.mui-segmented-control {
				background-color: white;
				
			}			
			.button{
				float: right;
				height: 30px;
				width:45px;
				margin-top: 12px;
				border: 1px solid darkgray;
				color: #6C6C6C;
			}
			.mui-table-view li{
				height: 92px;
				padding-top: 16px;
				border-bottom: 1px solid #CFCFCF;
				border-top: 1px solid #CFCFCF;
			}
			.mui-control-content .mui-loading {
				margin-top: 50px;
			}
			.pingjiabtn{
				float: right;
				height: 30px;
				width:50px;
				margin-top: 12px;
				background-color: #fc5959;
				color: white;
				border: 0;
				padding:5px 0 0 10px;
			}
			.shanchubtn{
				float: right;
				height: 30px;
				width:75px;
				margin-top: 12px;
				margin-right: 10px;
				color: gray;
				border: 1px solid #CFCFCF;
			}
			.headimage {
				height: 180px;
				width: 100%;
			}
			
			.icondiv {
				height: 30px;
				width: 30px;
				margin: 0 auto;
				text-align: center;
			}
			
			.iconimg {
				max-height: 35px;
				max-width: 35px;
				padding-top: 5px;
			}
			
			.footer {
				height: 30px;
				width: 15px;
				margin: 0 auto;
				padding-top: 10px;
			}
			
			.footerimg {
				max-height: 15px;
				max-width: 15px;
				padding-top: 10px;
			}
			</style>
	</head>
	<body>
		<div class="mui-content">
			<div id="slider" class="mui-slider mui-fullscreen">
				<div id="sliderSegmentedControl" class="mui-slider-indicator mui-segmented-control mui-segmented-control-inverted">
					<div class="mui-slider">
						<a class="mui-control-item mui-active" href="#item1mobile">
							已领取
						</a>
						<a class="mui-control-item" href="#item2mobile">
							已收货						
						</a>				
					</div>
				</div>
				<div id="sliderProgressBar" class="mui-slider-progress-bar" style="width: 50%;padding-left:10%;padding-right: 10%;transform: translate3d(50px, 0px, 0px) translateZ(0px);"></div>
				<div class="mui-slider-group">
					<!--已领取 start -->
					<div id="item1mobile" class="mui-slider-item mui-control-content mui-active">
						<div id="scroll1" class="mui-scroll-wrapper">
							<div class="mui-scroll">
								<c:forEach items="${clothReceived}" var="cloth1">
									<div style="height: 10px;background-color: #efeff4;"></div>
									<ul class="mui-table-view">		
										<li class="mui-table-view-cell" style="height: 40px;padding-top: 16px;margin-right:-10px;border-bottom: 0;">			
											<div class="mui-media-body" style="margin-top: -10px;">
												<div style="float: left;height: 60px;width:100%;font-size: 12px;color: gray;">
													<div style="padding-top: 5px;float: left;">领取时间：${cloth1[3]}</div> 
													<c:if test="${cloth1[6]=='2'}">
														<button onclick = "confirmRecept(${cloth1[0]})" id="confirm" style="float: right;height: 30px;width:80px;background-color: #fc5959;border: 0;color: white;">确认收货</button>
													</c:if>
													<c:if test="${cloth1[6]=='1'}">
														<span style="float: right;margin-top: 8px;color: cornflowerblue;font-size: 13px;">待发货</span>
													</c:if>
												</div>																						
											</div>									
										</li>
										<li class="mui-table-view-cell" style="height: 85px;padding-top: 16px;">
											<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=basePath%>${cloth1[5]}" style="height: 55px;max-width: 55px;">
											<div class="mui-media-body">
												<div style="float: left;height: 60px;">
													${cloth1[4]}
													<p class='mui-ellipsis'style="font-size: 12px;line-height: 1.5em;">
												</div>														
											</div>									
										</li>							
									</ul>
								</c:forEach>
								<div style="height: 50px;background-color: #efeff4;"></div>
							</div>
						</div>
					</div>
					<!--已领取end  -->
					<!--已收货start  -->
					<div id="item2mobile" class="mui-slider-item mui-control-content">
						<div class="mui-scroll-wrapper">
							<div class="mui-scroll">
								<c:forEach items="${clothReception}" var="cloth2">
									<div style="height: 10px;background-color: #efeff4;"></div>
									<ul class="mui-table-view">
										<li class="mui-table-view-cell" style="height: 40px;padding-top: 16px;margin-right:-10px;border-bottom: 0;">			
											<div class="mui-media-body" style="margin-top: -10px;">
												<div style="float: left;height: 60px;width:100%;font-size: 12px;color: gray;">
													<div style="padding-top: 5px;float: left;">领取时间：${cloth2[3]}</div> 
													<button onclick="deleteOrder(${cloth2[0]})" style="float: right;height: 30px;width:80px;background-color: #fc5959;border: 0;color: white;">删除订单</button>
													<c:if test="${cloth2[6]=='3'}">
														<button onclick="window.location.href='<%=basePath%>pp/rating?coId=${cloth2[0]}&clothId=${cloth2[1]}'" style="float: right;height: 30px;width:50px;margin-right: 10px;border:1px solid cornflowerblue;color:cornflowerblue;">评价</button>
													</c:if>	
													<c:if test="${cloth2[6]=='4'}">
														<button style="float: right;height: 30px;width:50px;margin-right: 10px;border:1px solid cornflowerblue;color:cornflowerblue;">已评</button>
													</c:if>	
												</div>																						
											</div>									
										</li>
										<li class="mui-table-view-cell" style="height: 85px;padding-top: 16px;">
											<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=basePath%>${cloth2[5]}" style="height: 55px;max-width: 55px;">
											<div class="mui-media-body">
												<div style="float: left;height: 60px;">
													${cloth2[4]}
													<p class='mui-ellipsis'style="font-size: 12px;line-height: 1.5em;">
												</div>														
											</div>									
										</li>														
									</ul>
								</c:forEach>
								<div style="height: 50px;background-color: #efeff4;"></div>		
							</div>
						</div>
					</div>
					<!--已收货end  -->
				</div>
			</div>
		</div>
		<nav class="mui-bar mui-bar-tab">
			<jsp:include page="../../footer.jsp"></jsp:include>
		</nav>
		<script type="text/javascript">
			function confirmRecept(coId){
				$.ajax({
	                data: {
	                	"coId": coId,
	                	},
	                type:"POST",
	                url:"<%=basePath%>pp/confirmRecept_do",
	                error:function(data){
	                	btnArray = ["出錯了"];
	                    mui.alert('', ' ',btnArray, function() {});
	                },
	                success:function(data){
	                    if (data == "true") {
	                    	btnArray = ["收货成功"];
	                    	mui.alert('', ' ',btnArray, function() {
	                    		window.location.href="<%=basePath%>pp/getting";
	                    	});
	                    }
	                    else {
	                        btnArray = ["出错了"];
	                    	mui.alert('', ' ',btnArray, function() {});
	                    }
	                }
	            });
			};
			function deleteOrder(coId){
				$.ajax({
					data:{
						"coId":coId,
					},
					type:"POST",
					url:"<%=basePath%>pp/deleteOrder_do",
					error:function(data){
	                	btnArray = ["出錯了"],
	                    mui.alert('', ' ',btnArray, function() {})
	                },
	                success:function(data){
	                    if (data == "true") {
	                    	btnArray = ["删除成功"],
	                    	mui.alert('', ' ',btnArray, function() {
	                    		window.location.href="<%=basePath%>pp/getting";
	                    	});
	                    }
	                    else {
	                        btnArray = ["出错了"];
	                    	mui.alert('', ' ',btnArray, function() {});
	                    }
	                }
				});
			};
			//mui初始化
			mui.init({
				swipeBack: true, //启用右滑关闭功能
			});
			(function($){
				$('.mui-scroll-wrapper').scroll({
					indicators:true
				});
			})(mui);
		</script>
	</body>
</html>