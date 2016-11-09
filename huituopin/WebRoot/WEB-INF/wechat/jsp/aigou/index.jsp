<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
		<title>慧脱贫</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<link rel="stylesheet" href="<%=basePath%>wechat/css/mui1.css">
		<script src="<%=path %>/wechat/js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="<%=path %>/wechat/js/mui.min.js"></script>
		<style>
			.mui-table-view.mui-grid-view{
				padding: 0px;
			}
			.mui-table-view.mui-grid-view .mui-table-view-cell{
				padding-left: 9px;
				padding-right:px;
			}
			.li{
				width:49.5%;
				padding:0px;
				background-color:#F6F6F6;
			}
			.mui-table-view:after{
				background-color:transparent;
			}
		</style>

  </head>
  <script type="text/javascript">
  	$(function(){
  		//商品类别
  		var productTypes = '${requestScope.ptInfo}';
  		//debugger;
  		var productTypeObjs = $.parseJSON(productTypes);
  		var node = "<option value='0'>商品分类</option>";
  		$("#type").append(node);
  		$.each(productTypeObjs,function(n,v){
  			node = "<option value='"
  			+ v.productTypeId
  			+ "'>"
  			+ v.productTypeName
  			+ "</option>";
  			$("#type").append(node);
  		});
  		$("#type option[value='"+$('#typeB').val() +"']").attr("selected",true);
  		//商品列表
  		var products = '${requestScope.pInfoList}';
  		var pObjs = $.parseJSON(products);
  		var node="";
  		if(!pObjs){
  			node="<li style='height: 175px; text-align:center; font-size:13px'>没有符合您搜索条件的商品</li>";
  			$("#commodityList").append(node);
  		}else{
	  		$.each(pObjs,function(n,v){
	  			node="<li class='mui-table-view-cell mui-media mui-col-xs-6' style='width:49.5%;padding:0px;background-color:#F6F6F6;'>"
	  			+					"<a href='<%=basePath%>wechat/aigou/detail?pId="+ v[0]+"' style='padding-left:2%;margin:5px 0 0 0;'>"
				+					"<img class='mui-media-object' src='<%=basePath%>"+v[3]+"' style='height:200px'>"
				+					"<div class='mui-media-body' style='font-size:10px;padding:0px;background-color:white;margin-top:-4px'>"
				+						"<span style='display:-webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 2;overflow: hidden;padding:10px 8px 8px 8px;font-size:10px;'>"
				+							v[1]
				+						"</span>"
				+					"</div>"
				+				"</a>"
				+				"<div style='height:50px;margin-left:2%;background-color:white;'>"
				+					"<span id='cost' style='height:30px;width:40%;float:left;font-size: 14px;margin:5px 0 0 8px;color: red;border:0;float:left;'>"
				+						"￥"+v[2]
				+					"</span>"
				+					"<span id='exchange' style='height:30px;width:auto;font-size: 13px;margin:5px;color: #cfcfcf;border:0;float:right;'>"
				+						v[4]+"积分兑换"
				+					"</span> "   
				+				"</div>"
				+			"</li>";
	  			$("#commodityList").append(node);
	  		}) 
  		}
  		
  	});	
  	mui.init({
				swipeBack:false
			});
			(function($){
				$('.mui-scroll-wrapper').scroll({
					indicators:true
				});
			})(mui);
  </script>
 <body>		
 		<input id="typeB" type="hidden" value="${type}"/>
		<header class="mui-bar mui-bar-nav" >
			<a class="mui-action-back mui-pull-left" style="background-image: url(<%=basePath%>wechat/img/jiantou.png);background-size:70%;background-repeat:no-repeat ;height: 40px;width:30px;margin-top: 13px;margin-left: 4px;"></a>
			<h1 class="mui-title">爱心购</h1>
		</header>
		<div id="slider" class="mui-slider" style="border-bottom: 1px solid gainsboro;height: 50px;top:44px;position:fixed;background-color:#efeff4;">			
			<form id="selectForm" action="<%=basePath %>wechat/aigou" method="POST">
				<div style="width:27%;height: 100%;float: left;background-color:#efeff4 ;">
					<div style="margin-left:auto;margin-right:auto;width:78px;height:28px;background-color:#efeff4 ;overflow:hidden;margin-top:7px;">
						<select id="type" name="type" class="mui-btn mui-btn-block" onchange="$('#selectForm').submit();" style="font-size: 14px;width:auto;text-align:right;padding:7px 0;display:inline-block;direction:rtl;float:left;background-color:#efeff4">
						</select>
						<img src="<%=basePath%>wechat/images/xiaLaJianTou.png" style="max-width:8px;height:5px;float:left;margin-top:14px;"/>
					</div>
				</div>
				<div style="width:73%;height: 100%;float: right;background-color:#efeff4;">
					<div class="mui-input-row mui-search"style="padding-top: 8px;padding-right:7px ;">
						<input id="keyword" type="search" name="keyword" class="mui-input-clear" placeholder="搜索更多商品" style="border: 1px solid gainsboro;" value="${keyword}">
					</div>
				</div>
			</form>
		</div>
		<div class="mui-content" style="padding-top:94px;">
<!-- 下拉刷新相关		<div id="pullrefresh" class="mui-content mui-scroll-wrapper"style="top: 93px;">
 -->	
		<!--下拉刷新容器-->
			<ul id="commodityList" class="mui-table-view mui-grid-view" style="background-color:#F6F6F6;" >
                	
			</ul>
		<!-- </div> -->
        	<div style="height:50px"></div>
		</div>
		
		<nav class="mui-bar mui-bar-tab">
			<jsp:include page="../footer.jsp"></jsp:include>
		</nav>
		<%-- <script src="<%=basePath%>wechat/js/mui.min.js"></script>
		<script>
			var msg="";
			var count = 0;
			mui.init({
				pullRefresh: {
					container: '#pullrefresh',
					down: {
						//callback: pulldownRefresh
					},
					up: {
						contentrefresh: '正在加载...',
						callback: pullupRefresh
					}
				}
			});
			function getData(start,num){
				$.ajax({
					type:"POST",
					data:{
						"type":$("#type").val(),
						"keyword":$("#keyword").val(),
						"start":start,
						"num":num
					},
					async:false,
					url:"<%=basePath%>wechat/aigou/getProduct_do",
					success:function(map){
						msg=map;
					},
					error:function(map){
         				alert("出錯了！"+map);
         			}						
				});
			};
		
			function addStyle(){
				$("#pullrefresh li").css({width:"49.5%",background:"#F6F6F6",padding:"0px"});
			};
			
			
			/**
			 * 上拉加载具体业务实现
			 */
			function pullupRefresh() {
				setTimeout(function() {
					mui('#pullrefresh').pullRefresh().endPullupToRefresh((++count > 2)); //参数为true代表没有更多数据了。
					var table = document.body.querySelector('.mui-table-view');
					var cells = document.body.querySelectorAll('.mui-table-view-cell');
					getData(cells.length,2);
					if(msg.lenght==4){
						return
					}
					var pObjs = $.parseJSON(msg);
			  		$.each(pObjs,function(n,v){
			  			var node="<li class='li mui-table-view-cell mui-media mui-col-xs-6 '>"
			  			+				"<a href='<%=basePath%>wechat/aigou/detail?pId="+ v[0]+"' style='padding-left:2%;margin:5px 0 0 0;'>"
						+					"<img class='mui-media-object' src='<%=basePath%>"+v[3]+"' style='height:200px'>"
						+				"</a>"
						+					"<div class='mui-media-body' style='font-size:10px;padding:0px;background-color:white;'>"
						+						"<span style='display:-webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 2;overflow: hidden;padding:10px 8px 8px 8px;font-size:10px;'>"
						+							v[1]
						+						"</span>"
						+					"</div>"
						+				"<div style='height:38px;margin-left:2%;background-color:white;'>"
						+					"<span id='cost' style='height:30px;width:40%;float:left;font-size: 14px;margin:5px 0 0 8px;color: red;border:0;float:left;'>"
						+						"￥"+v[2]
						+					"</span>"
						+					"<span id='exchange' style='height:30px;width:auto;float:left;font-size: 13px;margin:5px;color: #cfcfcf;border:0;float:right;'>"
						+						v[4]+"积分兑换"
						+					"</span> "   
						+				"</div>";
						+		 "</li>";
						$("#commodityList").append(node);
			  			var li = document.createElement('li');
			  			li.className = 'li mui-table-view-cell mui-media mui-col-xs-6 ';
			  			li.innerHTML = 	"<a href='<%=basePath%>wechat/aigou/detail?pId="+ v[0]+"' style='padding-left:2%;margin:5px 0 0 0;'>"
						+					"<img class='mui-media-object' src='<%=basePath%>"+v[3]+"' style='height:200px'>"
						+					"<div class='mui-media-body' style='font-size:10px;padding:0px;background-color:white;'>"
						+						"<span style='display:-webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 2;overflow: hidden;padding:10px 8px 8px 8px;font-size:10px;'>"
						+							v[1]
						+						"</span>"
						+					"</div>"
						+				"</a>"
						+				"<div style='height:38px;margin-left:2%;background-color:white;'>"
						+					"<span id='cost' style='height:30px;width:40%;float:left;font-size: 14px;margin:5px 0 0 8px;color: red;border:0;float:left;'>"
						+						"￥"+v[2]
						+					"</span>"
						+					"<span id='exchange' style='height:30px;width:auto;float:left;font-size: 13px;margin:5px;color: #cfcfcf;border:0;float:right;'>"
						+						v[4]+"积分兑换"
						+					"</span> "   
						+				"</div>";
			  			table.appendChild(li);
			  			addStyle();
			  		});
				}, 500);
			}
			if (mui.os.plus) {
				mui.plusReady(function() {
					setTimeout(function() {
						mui('#pullrefresh').pullRefresh().pullupLoading();
					}, 1000);

				});
			} else {
				mui.ready(function() {
					mui('#pullrefresh').pullRefresh().pullupLoading();
				});
			}
		</script> --%>
	</body>
</html>
