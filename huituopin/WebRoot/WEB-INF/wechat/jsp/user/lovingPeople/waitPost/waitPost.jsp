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
		<title>待邮寄</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="<%=basePath%>wechat/css/mui.css">
		<script src="<%=basePath%>wechat/js/footer.js"></script>
		<script src="<%=basePath%>wechat/js/mui.min.js"></script>
		<script src="<%=basePath%>wechat/js/mui.pullToRefresh.js"></script>
		<script src="<%=basePath%>wechat/js/mui.pullToRefresh.material.js"></script>
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
							待发货							
						</a>
						<a class="mui-control-item" href="#item2mobile">
							待收货					
						</a>	
						<a class="mui-control-item" href="#item3mobile">
							待评价					
						</a>
						<a class="mui-control-item" href="#item4mobile">
							已完成					
						</a>	
					</div>
				</div>
				<div id="sliderProgressBar" class="mui-slider-progress-bar" style="width: 25%;padding-left:10%;padding-right: 10%;transform: translate3d(50px, 0px, 0px) translateZ(0px);"></div>
				<div class="mui-slider-group">
					<!--待发货 start  -->
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
													<button onclick="window.location.href='<%=basePath%>lp/expressageMsg?coId=${cloth1[0]}'" style="float: right;height: 30px;width:50px;background-color: #fc5959;border: 0;color: white;">发货</button>
													<button onclick="window.location.href='<%=basePath %>lp/receiverMsg?saId=${cloth1[2]}'" style="float: right;height: 30px;width:50px;margin-right: 10px;border:1px solid cornflowerblue;color:cornflowerblue;">详情</button>
												</div>																						
											</div>									
										</li>
										<li class="mui-table-view-cell" style="height: 85px;padding-top: 16px;">
											<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=basePath%>${cloth1[5]}" style="height: 55px;max-width: 55px;">
											<div class="mui-media-body">
												<div style="float: left;height: 60px;">
													${cloth1[4]}
													<p class='mui-ellipsis'style="font-size: 12px;line-height: 1.5em;">
													数量：1							
												</div>														
											</div>									
										</li>
									</ul>
								</c:forEach>
								<div style="height:50px;"></div>
							</div>
						</div>
					</div>
					<!--待发货 end  -->
					<!--待收货 start -->
					<div id="item2mobile" class="mui-slider-item mui-control-content">
						<div class="mui-scroll-wrapper">
							<div class="mui-scroll">
								<c:forEach items="${clothOnPost}" var="cloth2">
									<div style="height: 10px;background-color: #efeff4;"></div>
									<ul class="mui-table-view">		
										<li class="mui-table-view-cell" style="height: 40px;padding-top: 16px;margin-right:-10px;border-bottom: 0;">			
											<div class="mui-media-body" style="margin-top: -10px;">
												<div style="float: left;height: 60px;width:100%;font-size: 12px;color: gray;">
													<div style="padding-top: 5px;float: left;">领取时间：${cloth2[3]}</div> 
													<button id="btn1" style="float: right;height: 30px;width:65px;background-color: #fc5959;border: 0;color: white;">待收货</button>
													<button onclick="window.location.href='<%=basePath %>lp/receiverMsg?saId=${cloth2[2]}'" style="float: right;height: 30px;width:50px;margin-right: 10px;border:1px solid cornflowerblue;color:cornflowerblue  ;">详情</button>
												</div>																						
											</div>									
										</li>
										<li class="mui-table-view-cell" style="height: 85px;padding-top: 16px;">
											<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=basePath%>${cloth2[5]}" style="height: 55px;max-width: 55px;">
											<div class="mui-media-body">
												<div style="float: left;height: 60px;">
													${cloth2[4]}
													<p class='mui-ellipsis'style="font-size: 12px;line-height: 1.5em;">
													数量：1							
												</div>														
											</div>									
										</li>
									</ul>	
								</c:forEach>
								<div style="height:50px;"></div>		
							</div>
						</div>
					</div>
					<!--待收货 end -->
					<!--待评价 start -->
					<div id="item3mobile" class="mui-slider-item mui-control-content">
						<div class="mui-scroll-wrapper">
							<div class="mui-scroll">
								<c:forEach items="${clothReception}" var="cloth3">
									<div style="height: 10px;background-color: #efeff4;"></div>
								<ul class="mui-table-view">		
									<li class="mui-table-view-cell" style="height: 40px;padding-top: 16px;margin-right:-10px;border-bottom: 0;">			
										<div class="mui-media-body" style="margin-top: -10px;">
											<div style="float: left;height: 60px;width:100%;font-size: 12px;color: gray;">
												<div style="padding-top: 5px;float: left;">领取时间：${cloth3[3]}</div> 
												<button onclick="window.location.href='<%=basePath %>lp/receiverMsg?saId=${cloth3[2]}'" style="float: right;height: 30px;width:50px;margin-right: 10px;border:1px solid cornflowerblue;color:cornflowerblue  ;">详情</button>
											</div>																						
										</div>									
									</li>
									<li class="mui-table-view-cell" style="height: 85px;padding-top: 16px;">
										<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=basePath%>${cloth3[5]}" style="height: 55px;max-width: 55px;">
										<div class="mui-media-body">
											<div style="float: left;height: 60px;">
												${cloth3[4]}
												<p class='mui-ellipsis'style="font-size: 12px;line-height: 1.5em;">
												数量：1							
											</div>														
										</div>									
									</li>
								</ul>
								</c:forEach>
								<div style="height:50px;"></div>	
							</div>
						</div>
					</div>
					<!--待评价 end -->
					<!--已完成 start -->
					<div id="item4mobile" class="mui-slider-item mui-control-content">
						<div class="mui-scroll-wrapper">
							<div class="mui-scroll">
								<c:forEach items="${clothCommented}" var="cloth4">
									<div style="height: 10px;background-color: #efeff4;"></div>
								<ul class="mui-table-view">		
									<li class="mui-table-view-cell" style="height: 40px;padding-top: 16px;margin-right:-10px;border-bottom: 0;">			
										<div class="mui-media-body" style="margin-top: -10px;">
											<div style="float: left;height: 60px;width:100%;font-size: 12px;color: gray;">
												<div style="padding-top: 5px;float: left;">领取时间：${cloth4[3]}</div> 
												<button onclick="window.location.href='<%=basePath %>wechat/yjya/rating?clothId=${cloth4[1]}'" id="btn1" style="float: right;height: 30px;width:80px;background-color: #fc5959;border: 0;color: white;">查看评价</button>
												<button onclick="window.location.href='<%=basePath %>lp/receiverMsg?saId=${cloth4[2]}'" style="float: right;height: 30px;width:50px;margin-right: 10px;border:1px solid cornflowerblue;color:cornflowerblue  ;">详情</button>
											</div>																						
										</div>									
									</li>
									<li class="mui-table-view-cell" style="height: 85px;padding-top: 16px;">
										<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=basePath%>${cloth4[5]}" style="height: 55px;max-width: 55px;">
										<div class="mui-media-body">
											<div style="float: left;height: 60px;">
												${cloth4[4]}
												<p class='mui-ellipsis'style="font-size: 12px;line-height: 1.5em;">
												数量：1							
											</div>														
										</div>									
									</li>
								</ul>
								</c:forEach>
								<div style="height:50px;"></div>	
							</div>
						</div>
					</div>
					<!--待收货 end -->
				</div>
			</div>
		</div>
		<nav class="mui-bar mui-bar-tab">
			<jsp:include page="../../footer.jsp"></jsp:include>
		</nav>
		<script type="text/javascript">
			mui.init({
				swipeBack:false
			});
			(function($){
				$('.mui-scroll-wrapper').scroll({
					indicators:true
				});
			})(mui);
		</script>
	</body>
</html>