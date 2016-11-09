<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>发货</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
		<link rel="stylesheet" href="<%=basePath%>wechat/css/aiGou.css" /> 
		<link rel="stylesheet" href="<%=basePath%>wechat/css/mui1.css"  />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/footer.css"/>
		<script src="<%=basePath%>wechat/js/mui.min.js"></script>
		<style type="text/css">
			.login-lastline{
				height:100px;
				width:100%;
				background-color: #efeff4;
				padding-top: 20px;
				padding-left: 15px;
				padding-right: 15px;
			}
				.login-button{
				width:100%;
				height:50%;
				outline: none;
				font-size: 14px;
				color: white;
			}
			input[type='submit']{
				background-color: #fc5959;
				border: 0;
			}
			.mui-input-group .mui-input-row{
				height: 50px;
			}
			.mui-input-row label{
				padding-top:30px;
				color: black;
				font-size: 14px;
				width: 28%;
			}
			.mui-input-row label ~ input{
				margin-top: 2px;
				font-size: 15px;
			}
			.mui-table-view:before{
				background-color: transparent;
			}
			
			#info {
				padding: 20px 10px;
				}
			.mui-popup{
				top:40%;
			}
		</style>
	</head>
	<body>
		<header class="mui-bar mui-bar-nav" >
			<a class="mui-action-back mui-pull-left" style="background-image: url(<%=basePath%>wechat/img/jiantou.png);background-size:70%;background-repeat:no-repeat ;height: 40px;width:30px;margin-top: 13px;margin-left: 4px;"></a>
			<h1 class="mui-title">快递信息</h1>
		</header>
		<div class="mui-content">
			<div style="height: 10px;"></div>
			<form class="mui-input-group" action="choose.html">
				<div class="mui-input-row">
					<label style="padding-top: 18px;">选择快递</label>
					<div style="width:72%;height: 100%;float: left;">
						<select id="expressNameId" style="padding-left:0px;font-size: 15px;height: 45px;margin-top:2px;width:70%;">
							<option value="0"></option>
							<option value="1">韵达快递</option>
							<option value="2">圆通快递</option>
							<option value="3">中通快递</option>
							<option value="4">百世汇通</option>
							<option value="5">汇通快递</option>
						</select>
						<img src="<%=basePath%>wechat/images/xiaLaJianTou.png" style="max-width:12px;height:10px;float:right;margin-top:20px;margin-right:15px;"/>
						
					</div>
				</div>
				<div class="mui-input-row" style="height: 80px;">
					<label>运单号码</label>
					<div style="width: 72%;height: 100%;float:right;padding-right: 15px;padding-top: 20px;">
						<input id="expressNum" type="text" style="border: 1px solid #CFCFCF;padding-right: 10px;"/>
					</div>		
				</div>				
			</form>
			<div class="login-lastline">
				<input id="alertBtn" type="submit" class="login-button" value="确定" />
			</div>			
		</div>
		<input id="coId" type="hidden" value="${coId }"/>
		<nav class="mui-bar mui-bar-tab">
			<jsp:include page="../../footer.jsp"></jsp:include>
		</nav>
		<script type="text/javascript" charset="utf-8">
			//mui初始化
			mui.init({
				swipeBack: true //启用右滑关闭功能
			});
			//var info = document.getElementById("info");
			document.getElementById("alertBtn").addEventListener('tap', function() {
				var btnArray = ["发货成功"];
				$.ajax({
	                data: {
	                	"coId": $("#coId").val(),
	                	"expressNameId":$("#expressNameId").val(),
	                	"expressNum":$("#expressNum").val()
	                	},
	                type:"POST",
	                url:"expressageMsg_do",
	                error:function(data){
	                	btnArray = ["出錯了"];
	                    mui.alert('', ' ',btnArray, function() {});
	                },
	                success:function(data){
	                    if (data == "true") {
	                    	btnArray = ["发货成功"];
	                    	mui.alert('', ' ',btnArray, function() {
	                    		window.location.href="<%=basePath%>/lp/waitPost";
	                    	});
	                        
	                    }
	                    else {
	                        btnArray = ["出错了"];
	                    	mui.alert('', ' ',btnArray, function() {});
	                    }
	                }
	            });
				
			});
		</script>
	</body>
</html>