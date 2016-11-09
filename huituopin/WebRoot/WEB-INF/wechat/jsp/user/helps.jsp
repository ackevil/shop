<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta charset="utf-8">
		<title>我的获助</title>
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
				<div id="sliderSegmentedControl" class="mui-slider-indicator mui-segmented-control mui-segmented-control-inverted" style="background-color: white">
					<div class="mui-slider">
						<a class="mui-control-item mui-active" href="#item1mobile">
							获助的物品
							
						</a>
						<a class="mui-control-item" href="#item2mobile">
							获助的金额
							
						</a>				
					</div>
				</div>
				<div id="sliderProgressBar" class="mui-slider-progress-bar" style="width: 50%;padding-left:10%;padding-right: 10%;transform: translate3d(50px, 0px, 0px) translateZ(0px);"></div>
				<div class="mui-slider-group">
					<div id="item1mobile" class="mui-slider-item mui-control-content mui-active">
						<div id="scroll1" class="mui-scroll-wrapper">
							<div class="mui-scroll">
								<div style="height: 8px;background-color: #efeff4;"></div>
								<ul class="mui-table-view">									
									<li class="mui-table-view-cell" >
										<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=basePath%>wechat/images/yijiuyouai1.jpg" style="height: 55px;max-width: 55px;">
										<div class="mui-media-body" style="">
											<div style="float: left;height: 60px;width: 60px;">
												牛仔裤
												<p class='mui-ellipsis'style="font-size: 12px;line-height: 1.5em;">
												数量：1<br />
												2016-04-27
											</div>								
											<button class="button">删除</button>
										</div>									
									</li>
								</ul>								
								<div style="height: 8px;background-color: #efeff4;"></div>
								<ul class="mui-table-view">				
									<li class="mui-table-view-cell" >
										<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=basePath%>wechat/images/yijiuyouai1.jpg" style="height: 55px;max-width: 55px;">
										<div class="mui-media-body" style="">
											<div style="float: left;height: 60px;width: 60px;">
												牛仔裤
												<p class='mui-ellipsis'style="font-size: 12px;line-height: 1.5em;">
												数量：1<br />
												2016-04-27
											</div>								
											<button class="button">删除</button>
										</div>									
									</li>
								</ul>
									<div style="height: 8px;background-color: #efeff4;"></div>
									<ul class="mui-table-view">				
									<li class="mui-table-view-cell" >
										<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=basePath%>wechat/images/yijiuyouai1.jpg" style="height: 55px;max-width: 55px;">
										<div class="mui-media-body" style="">
											<div style="float: left;height: 60px;width: 60px;">
												牛仔裤
												<p class='mui-ellipsis'style="font-size: 12px;line-height: 1.5em;">
												数量：1<br />
												2016-04-27
											</div>								
											<button class="button">删除</button>
										</div>									
									</li>
								</ul>
									<div style="height: 8px;background-color: #efeff4;"></div>
									<ul class="mui-table-view">				
									<li class="mui-table-view-cell">
										<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=basePath%>wechat/images/yijiuyouai1.jpg" style="height: 55px;max-width: 55px;">
										<div class="mui-media-body" style="">
											<div style="float: left;height: 60px;width: 60px;">
												牛仔裤
												<p class='mui-ellipsis'style="font-size: 12px;line-height: 1.5em;">
												数量：1<br />
												2016-04-27
											</div>								
											<button class="button">删除</button>
										</div>									
									</li>
								</ul>
									<div style="height: 8px;background-color: #efeff4;"></div>
									<ul class="mui-table-view">				
									<li class="mui-table-view-cell">
										<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=basePath%>wechat/images/yijiuyouai1.jpg" style="height: 55px;max-width: 55px;">
										<div class="mui-media-body" style="">
											<div style="float: left;height: 60px;width: 60px;">
												牛仔裤
												<p class='mui-ellipsis'style="font-size: 12px;line-height: 1.5em;">
												数量：1<br />
												2016-04-27
											</div>								
											<button class="button">删除</button>
										</div>									
									</li>
								</ul>
									<div style="height: 8px;background-color: #efeff4;"></div>
									<ul class="mui-table-view">				
									<li class="mui-table-view-cell">
										<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=basePath%>wechat/images/yijiuyouai1.jpg" style="height: 55px;max-width: 55px;">
										<div class="mui-media-body" style="">
											<div style="float: left;height: 60px;width: 60px;">
												牛仔裤
												<p class='mui-ellipsis'style="font-size: 12px;line-height: 1.5em;">
												数量：1<br />
												2016-04-27
											</div>								
											<button class="button">删除</button>
										</div>									
									</li>
								</ul>
									<div style="height: 8px;background-color: #efeff4;"></div>
									<ul class="mui-table-view">				
									<li class="mui-table-view-cell">
										<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=basePath%>wechat/images/yijiuyouai1.jpg" style="height: 55px;max-width: 55px;">
										<div class="mui-media-body" style="">
											<div style="float: left;height: 60px;width: 60px;">
												牛仔裤
												<p class='mui-ellipsis'style="font-size: 12px;line-height: 1.5em;">
												数量：1<br />
												2016-04-27
											</div>								
											<button class="button">删除</button>
										</div>									
									</li>
								</ul>
									<div style="height: 8px;background-color: #efeff4;"></div>
									<ul class="mui-table-view">				
									<li class="mui-table-view-cell">
										<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=basePath%>wechat/images/yijiuyouai1.jpg" style="height: 55px;max-width: 55px;">
										<div class="mui-media-body" style="">
											<div style="float: left;height: 60px;width: 60px;">
												牛仔裤
												<p class='mui-ellipsis'style="font-size: 12px;line-height: 1.5em;">
												数量：1<br />
												2016-04-27
											</div>								
											<button class="button">删除</button>
										</div>									
									</li>
								</ul>
								<div style="height:50px;"></div>
							</div>
						</div>
					</div>
					<div id="item2mobile" class="mui-slider-item mui-control-content">
						<div class="mui-scroll-wrapper">
							<div class="mui-scroll">
								<ul class="mui-table-view">
									<div style="height: 10px;background-color: #efeff4;"></div>
									<li class="mui-table-view-cell" style="height: 145px;">
										<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=basePath%>wechat/images/dishuizhien1.jpg" style="width:100%;height: 115px;max-width: 160px;">
										<div class="mui-media-body" style="font-size: 12px;line-height:1.5;height: 80px;color: #262626;">
											灵魂洗涤淳净未来——为菩提增长小学的孩子建一间澡堂<br/>
											<span style="color:#fc5959;line-height: 3.2;">
												获捐金额：¥50
											</span><br/>																						
										</div>
										<div class="mui-media-body" style="font-size: 12px;line-height:1.5;">
											<span style="float: left;height: 30px;width:80px;padding-top: 12px;color: gray;">2016-04-10</span>
											<button style="float: right;height: 30px;width:45px;color: gray;border: 1px solid darkgray">删除</button>
										</div>
									</li>
								</ul>
								<ul class="mui-table-view">
									<div style="height: 10px;background-color: #efeff4;"></div>
									<li class="mui-table-view-cell" style="height: 145px;">
										<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=basePath%>wechat/images/dishuizhien1.jpg" style="width:100%;height: 115px;max-width: 160px;">
										<div class="mui-media-body" style="font-size: 12px;line-height:1.5;height: 80px;color: #262626;">
											灵魂洗涤淳净未来——为菩提增长小学的孩子建一间澡堂<br/>
											<span style="color:#fc5959;line-height: 3.2;">
												获捐金额：¥50
											</span><br/>																						
										</div>
										<div class="mui-media-body" style="font-size: 12px;line-height:1.5;">
											<span style="float: left;height: 30px;width:80px;padding-top: 12px;color: gray;">2016-04-10</span>
											<button style="float: right;height: 30px;width:45px;color: gray;border: 1px solid darkgray">删除</button>
										</div>
									</li>
								</ul>
								<div style="height:50px;"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<nav class="mui-bar mui-bar-tab">
					<jsp:include page="footer.jsp"></jsp:include>
				</nav>
		<script src="<%=basePath%>wechat/js/mui.min.js"></script>
		<script src="<%=basePath%>wechat/js/mui.pullToRefresh.js"></script>
		<script src="<%=basePath%>wechat/js/mui.pullToRefresh.material.js"></script>
		<script src="<%=basePath%>wechat/jsme/user.wechat.js"></script>
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
