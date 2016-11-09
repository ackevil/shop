<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

		<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
		<html>

		<head>
			<base href="<%=basePath%>">

			<title>首页</title>
			<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
			<meta http-equiv="pragma" content="no-cache">
			<meta http-equiv="cache-control" content="no-cache">
			<meta http-equiv="expires" content="0">
			<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
			<meta http-equiv="description" content="This is my page">
			
			<link href="<%=basePath%>wechat/css/login.css" rel="stylesheet" />
			<link rel="stylesheet" href="<%=basePath%>wechat/css/mui.min.css">
			<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/app.css" />
			
			<style type="text/css">
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
				.mui-table-view:after{
					height:0px;
				}
			</style>
			
			<script src="<%=basePath%>wechat/js/mui.min.js"></script>
			<script src="<%=basePath%>wechat/js/jquery-1.9.1.js"></script>
			<script src="<%=basePath%>wechat/jsme/user.wechat.js"></script>
		</head>

		<body>
			<input id="basePath" value="<%=basePath%>" style="display: none"/>
			
			<div class="mui-content">
				<div id="tabbar" class="mui-control-content mui-active">
					<div class="headimage">
						<img src="<%=basePath%>wechat/images/Main_page.jpg" style="height: 100%;width: 100%;" />
					</div>
					<div class="mui-content">
						<ul class="mui-table-view mui-grid-view mui-grid-9" style="margin-top: 0px;background-color:white;">
							<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3" style="border-right: 1px solid gainsboro;border-bottom: 1px solid gainsboro;width:33.3%;">
								<a href="register/userRegister?moduleName=yijiuyouai">
									<div class="icondiv">
										<img src="<%=basePath%>wechat/images/yijiuyouai_logo.png" class="iconimg" />
									</div>
									<div class="mui-media-body" style="padding-top: 6px;height: 24px;font-size: 14px;">依旧有爱</div>
								</a>
							</li>
							<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3" style="border-right: 1px solid gainsboro;border-bottom: 1px solid gainsboro;width:33.3%;">
								<a href="register/userRegister?moduleName=dishuizhien">
									<div class="icondiv">
										<img src="<%=basePath%>wechat/images/dishuizhien.png" class="iconimg" />
									</div>
									<div class="mui-media-body" style="padding-top: 6px;height: 24px;font-size: 14px;">滴水之恩</div>
								</a>
							</li>
							<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3" style="border-bottom: 1px solid gainsboro;width:33.3%;">
								<a href="register/userRegister?moduleName=zhongdajiuzhu">
									<div class="icondiv">
										<img src="<%=basePath%>wechat/images/zhongdajiuzhu_log.png" class="iconimg" />
									</div>
									<div class="mui-media-body" style="padding-top: 6px;height: 24px;font-size: 14px;">重大救助</div>
								</a>
							</li>
							<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3" style="border-right: 1px solid gainsboro;border-bottom: 1px solid gainsboro;width:33.3%;">
								<a href="register/userRegister?moduleName=aigou">
									<div class="icondiv">
										<img src="<%=basePath%>wechat/images/aigou_log.png" class="iconimg" />
									</div>
									<div class="mui-media-body" style="padding-top: 6px;height: 24px;font-size: 14px;">爱心购</div>
								</a>
							</li>
							<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3" style="border-right: 1px solid gainsboro;border-bottom: 1px solid gainsboro;width:33.3%;">
								<a>
									<div class="icondiv">
										<img src="<%=basePath%>wechat/images/zhongchoukongjian.png" class="iconimg" />
									</div>
									<div class="mui-media-body" style="padding-top: 6px;height: 24px;font-size: 14px;">众筹空间</div>
								</a>
							</li>
							<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3" style="border-bottom: 1px solid gainsboro;width:33.3%;">
								<a>
									<div class="icondiv">
										<img src="<%=basePath%>wechat/images/aixinfengcai_log.png" class="iconimg" />
									</div>
									<div class="mui-media-body" style="padding-top: 6px;height: 24px;font-size: 14px;">爱心风采</div>
								</a>
							</li>
						</ul>
					</div>
				</div>
				<nav class="mui-bar mui-bar-tab">
					<jsp:include page="footer.jsp"></jsp:include>
				</nav>
		</body>

		</html>