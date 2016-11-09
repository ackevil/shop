<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="<%=basePath%>wechat/css/mui.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/app.css"/>
		<style type="text/css">
			.headimage{
				height: 180px;
				width: 100%;
			}
			.icondiv{
				height: 30px;
				width: 30px;
				margin: 0 auto;
			}
			.iconimg{
				max-height: 35px;
				max-width: 35px;
				padding-top: 5px;
			}
			.footer{
				height: 30px;
				width: 15px;
				margin: 0 auto;
				padding-top: 10px;
			}
			.footerimg{
				max-height: 15px;
				max-width: 15px;
				padding-top: 10px;
				
			}
			.mui-navigate-right:after, .mui-push-right:after{
				right:0px;
			}
			.mui-table-view-cell .mui-navigate-right>.mui-badge{
				right:15px;
			}
		</style>
  </head>
 <body>	
		<ul class="mui-table-view "style="height: 64px;">
			<li class="mui-table-view-cell" >
				<a href="<%=path%>/lp/lpDetailInfo?userId=${sessionScope.user.userId}" class="mui-navigate-right" style="padding-top: 8px;height: 56px;">
					<img class="mui-media-object mui-pull-left head-img" src="<%=path%>/${sessionScope.user.userWcAvatar}" style="height: 48px;max-width: 50px;"/>
					<div class="xiangxi" >
						<span style="font-size: 13px;float: left;">${sessionScope.user.userWcNickname}</span>
						<div style="height:21px;margin-left:15px;float: left;"><img src="<%=basePath%>wechat/images/aixinrenshi.png" style="max-height: 21px;max-width: 55px;"/></div>
						<br/>
						<p class='mui-ellipsis' style="font-size: small;line-height: 1;">
						${sessionScope.user.userPhone}
						</p>
						<!-- 
						<p class='mui-ellipsis' style="font-size: small;line-height: 1.3;">
						积分：998
						</p>
						 -->
					</div>
				</a>
			</li>
		</ul>
		<div style="height:10px;"></div>
		<ul class="mui-table-view mui-table-view-chevron" style="font-size: 13px;">
			<li class="mui-table-view-cell" style="height: 35px;">
				<a href="<%=path%>/lp/shopCart?userId=${sessionScope.user.userId}" class="mui-navigate-right" style="padding-top: 8px;">我的购物车
					<span id="gouWC" class="mui-pull-right mui-badge mui-badge-danger" style=" color: white;">
						${scNum}
					</span>
				</a>
			</li>
			<li class="mui-table-view-cell"style="height: 35px;">
				<a href="<%=path%>/lp/lpReciveInfo?type=other&userId=${sessionScope.user.userId}" class="mui-navigate-right"style="padding-top: 8px;">收件信息</a>
			</li>
			<li class="mui-table-view-cell"style="height: 35px;">
				<a href="<%=path%>/lp/donate" class="mui-navigate-right"style="padding-top: 8px;">我的捐助</a>
			</li>
			<li class="mui-table-view-cell"style="height: 35px;">
				<a href="<%=path%>/lp/orders?userId=${sessionScope.user.userId}" class="mui-navigate-right"style="padding-top: 8px;">我的订单</a>
			</li>
			<li class="mui-table-view-cell"style="height: 35px;">
				<a href="<%=path%>/lp/waitPost" class="mui-navigate-right"style="padding-top: 8px;">待邮寄
					<span id="daiYJ" class="mui-pull-right mui-badge mui-badge-danger" style=" color: white;">
						${waitPostNum} 
					</span>
				</a>
			</li>
			<li class="mui-table-view-cell"style="height: 35px;">
				<a href="<%=path%>/lp/wantDonate" class="mui-navigate-right"style="padding-top: 8px;">我要捐赠</a>
			</li>
			<li class="mui-table-view-cell"style="height: 35px;">
				<a href="<%=path%>/lp/goodCollect?userId=${sessionScope.user.userId}" class="mui-navigate-right"style="padding-top: 8px;">我收藏的商品</a>
			</li>
			<li class="mui-table-view-cell"style="height: 35px;">
				<a href="<%=path%>/lp/lpComment?userId=${sessionScope.user.userId}" class="mui-navigate-right"style="padding-top: 8px;">我的评论</a>
			</li>
		</ul>			
		<nav class="mui-bar mui-bar-tab">
			<jsp:include page="../footer.jsp"></jsp:include>
		</nav>
		<script type="text/javascript">
			//获得购物车内账单的数量,带邮寄的数量
			//购物车内账单数量为0
			if(document.getElementById("gouWC").innerHTML==0){
				document.getElementById("gouWC").style.background="white";
			}
			else document.getElementById("gouWC").style.background="#fc5959";
			if(document.getElementById("daiYJ").innerHTML==0){
				document.getElementById("daiYJ").style.background="white";
			}
			else document.getElementById("daiYJ").style.background="#fc5959";
		</script>
	</body>
</html>
