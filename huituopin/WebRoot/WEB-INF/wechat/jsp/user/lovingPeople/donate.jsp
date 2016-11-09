<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>我的捐助</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="<%=basePath%>wechat/css/mui.css">
		<script src="<%=basePath%>wechat/js/footer.js"></script>
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
						<a class="mui-control-item mui-active" href="#yjya">
							衣旧有爱						
						</a>
						<a class="mui-control-item" href="#item2mobile">
							滴水之恩				
						</a>	
						<a class="mui-control-item" href="#item3mobile">
							重大救助				
						</a>	
					</div>
				</div>
				<div id="sliderProgressBar" class="mui-slider-progress-bar" style="width: 33.3%;padding-left:10%;padding-right: 10%;transform: translate3d(50px, 0px, 0px) translateZ(0px);"></div>
				<div class="mui-slider-group">
					<div id="yjya" class="mui-slider-item mui-control-content mui-active">
						<div id="scroll1" class="mui-scroll-wrapper">
							<div class="mui-scroll">
								<c:forEach items="${clothList}" var="cloth">
									<div style="height: 10px;background-color: #efeff4;"></div>
									<ul class="mui-table-view">									
										<li class="mui-table-view-cell" >
											<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=basePath%>${cloth.clothMainpicPath}" style="height: 55px;max-width: 55px;">
											<div class="mui-media-body">
												<div style="float: left;height: 60px;">
													${cloth.clothName}
													<p class='mui-ellipsis'style="font-size: 12px;line-height: 1.5em;">
													<br />
													${cloth.clothIntime }
												</div>	
												<c:if test="${cloth.clothState == 0}">
													<span style="float: right;margin-top: 8px;color: #9B9B9B;font-size: 13px;">未领取</span>
												</c:if>							
												<c:if test="${cloth.clothState == 1}">
													<span style="float: right;margin-top: 8px;color: cornflowerblue;font-size: 13px;">待发货</span>
												</c:if>
												<c:if test="${cloth.clothState == 2}">
													<span style="float: right;margin-top: 8px;color: cornflowerblue;font-size: 13px;">待收货</span>
												</c:if>
												<c:if test="${cloth.clothState == 3}">
													<span style="float: right;margin-top: 8px;color: cornflowerblue;font-size: 13px;">待评价</span>
												</c:if>
												<c:if test="${cloth.clothState == 4}">
													<span style="float: right;margin-top: 8px;color: cornflowerblue;font-size: 13px;">已评价</span>
												</c:if>
											</div>									
										</li>
									</ul>
								</c:forEach>
								<div style="height:50px"></div>
							</div>
						</div>
					</div>
					<div id="item2mobile" class="mui-slider-item mui-control-content">
						<div class="mui-scroll-wrapper">
							<div class="mui-scroll">
								<c:forEach items="${dszeSupportList}" var="dszeSupport">
									<div style="height: 10px;background-color: #efeff4;"></div>
									<ul class="mui-table-view">
										<li class="mui-table-view-cell" style="height: 145px;">
											<img class="mui-media-object mui-pull-left head-img" id="head-img" src="${dszeSupport.get('dsze').getDszeMainPic()}" style="width:100%;height: 115px;max-width: 160px;">
											<div class="mui-media-body" style="font-size: 12px;line-height:1.5;height: 80px;">
												${dszeSupport.get('dszeSupport').getDszeName()}<br/>
												<span style="color:#fc5959;line-height: 3.2;">
													所捐金额：¥${dszeSupport.get('dszeSupport').getDszeSupMoney()}
												</span><br/>
											</div>
											<div class="mui-media-body" style="font-size: 12px;line-height:1.5;">
												<span style="float: left;height: 30px;width:80px;padding-top: 12px;color:#A2A2A2;"><fmt:formatDate value="${dszeSupport.get('dszeSupport').getDszeSupTime()}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
											</div>
										</li>
									</ul>
								</c:forEach>
								<div style="height:50px"></div>
							</div>
						</div>
					</div>
					<div id="item3mobile" class="mui-slider-item mui-control-content">
						<div class="mui-scroll-wrapper">
							<div class="mui-scroll">
								<c:forEach items="${activitySupportList}" var="activitySupport">
									<div style="height: 10px;background-color: #efeff4;"></div>
									<ul class="mui-table-view">
										<li class="mui-table-view-cell" style="height: 145px;">
											<img class="mui-media-object mui-pull-left head-img" id="head-img" src="${activitySupport.get('activity').getActivityMainPic()}" style="width:100%;height: 115px;max-width: 160px;">
											<div class="mui-media-body" style="font-size: 12px;line-height:1.5;height: 80px;">
												${activitySupport.get('activitySupport').getActivityName()}<br/>
												<span style="color:#fc5959;line-height: 3.2;">
													所捐金额：¥${activitySupport.get('activitySupport').getActSupMoney()}
												</span><br/>
											</div>
											<div class="mui-media-body" style="font-size: 12px;line-height:1.5;">
												<span style="float: left;height: 30px;width:80px;padding-top: 12px;color:#A2A2A2;"><fmt:formatDate value="${activitySupport.get('activitySupport').getActSupTime()}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
											</div>
										</li>
									</ul>
								</c:forEach>
								<div style="height:50px"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<nav class="mui-bar mui-bar-tab">
					<jsp:include page="../footer.jsp"></jsp:include>
		</nav>
		<script src="<%=basePath%>wechat/js/mui.min.js"></script>
		<script type="text/javascript">
			mui.init({
				swipeBack:false
			});
			(function($){
				$('.mui-scroll-wrapper').scroll({
					indicators:true
				});
			})(mui);
			document.getElementById("home").addEventListener("click",onchangeinnest);
			document.getElementById("me").addEventListener("click",onchangeinnest1);
		</script>
	</body>
</html>