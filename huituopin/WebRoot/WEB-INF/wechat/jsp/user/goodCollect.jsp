<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
	<head>
		<meta charset="utf-8">
		<title>收藏的商品</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<script src="<%=basePath%>wechat/js/mui.min.js"></script>
		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=basePath%>wechat/css/mui1.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/app.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/footer.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/SouCangShangPin.css" />
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
			</style>
	</head>

	<body>
		<div class="product-collected-list">
		<c:forEach items="${collectionList}" var="collection" >
			<div class="product-collected-list-item">
				<a href="<%=basePath %>wechat/aigou/detail?pId=${collection[1]}">
					<div class="left">
						<img src="<%=basePath%>${collection[2]}"/>
					</div>
					<div class="right">
						<div style="overflow: hidden;"></div>
						<div class="right-top">
							<span>${collection[3]}</span>
						</div>
						<div class="right-buttom">
							<span>¥${collection[4]}</span>
						</div>
					</div>
				</a>
			</div>
		</c:forEach>	
			<div class="product-end">
				<span>已经没有更多了</span>
			</div>
			<div style="height:50px"></div>
	    </div>
	  <nav class="mui-bar mui-bar-tab">
					<jsp:include page="footer.jsp"></jsp:include>
				</nav>
	</body>

</html>