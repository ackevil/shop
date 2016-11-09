<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>评价</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<script type="text/javascript" src="<%=basePath%>wechat/js/footer.js"></script>
		<script src="<%=basePath%>wechat/js/mui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>wechat/js/jquery-1.9.1.js"></script>
		<link rel="stylesheet" href="<%=basePath%>wechat/css/mui1.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/footer.css"/>
		<style type="text/css">
			.mui-control-content .mui-loading {
				margin-top: 50px;
			}
			.pingjiaBtn{
				height: 28px;
				width: 75px;
				background-color: #fc5959;
				margin-top: 10px;
				color: white;
				border: 0;
				float:right;
				margin-right: 10px;
				font-size: 13px;
			}
			.product-detail-img{
				padding-left: 15px;
				margin-top: 3px;
			}
			.product-detail-img img{
				width: 70px;
				height: 70px;
			}
			.mui-table-view-cell.mui-active
			{
			    background-color: white;
			}
		</style>
	</head>
	<body>
		<ul class="mui-table-view">									
			<li class="mui-table-view-cell" style="height: 270px;padding-top: 16px;">
				<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=path%>${clothPic}" style="height: 55px;width: 55px;">
				<div class="mui-media-body">												
					<div class="mui-input-row" style="margin-top:-10px;margin-left: -15px;">
						<textarea id="textarea" rows="5" placeholder="请输入评价..." style="font-size: 12px;border: 0;"></textarea>
					</div>
				</div>
				<div id="imagePreview" class="product-detail-img" >
					<button id="chooseImage1" style="height:70px"><img src="<%=basePath%>wechat/images/addImg.jpg"/></button>
					<button id="chooseImage2" style="height:70px"><img src="<%=basePath%>wechat/images/addImg.jpg"/></button>
					<button id="chooseImage3" style="height:70px"><img src="<%=basePath%>wechat/images/addImg.jpg"/></button>
				</div>
			</li>
		</ul>
		<div style="height: 40px;width: 100%;">
			<div style="width:100px;height:100%;float: right;">
				<button onclick="submmitComment()" class="pingjiaBtn">发表评价</button>
			</div>
		</div>
		<input id="appId" type="hidden" value="${appId}" />
		<input id="timestamp" type="hidden" value="${timestamp}" />
		<input id="noncestr" type="hidden" value="${noncestr}" />
		<input id="signature" type="hidden" value="${signature}" />		
		<nav class="mui-bar mui-bar-tab">
			<jsp:include page="../../footer.jsp"></jsp:include>
		</nav>
		<input id="coId" type="hidden" value="${coId}" />
		<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script type="text/javascript" src="<%=basePath%>wechat/js/wechat.js"></script>
		<script type="text/javascript">
	        function submmitComment(){
	        	if($("#textarea").val().trim() == ""){
	        		var btnArray = ["评论内容不能为空"];
	                mui.alert(' ',' ',btnArray, function() {
	            	});
	        		return;
	        	}
	        	//确保上传三张图片
	       		var i=0;
	       		var pathList = "";
	       		var imgPath = $("#chooseImage1 img").attr('src');
	       		if(imgPath != "<%=basePath%>wechat/images/addImg.jpg")
	       		{
	       			pathList += imgPath+";";
	       			i++;
	       		}
	       		imgPath = $("#chooseImage2 img").attr('src');
	       		if(imgPath != "<%=basePath%>wechat/images/addImg.jpg")
	       		{
	       			pathList += imgPath+";";
	       			i++;
	       		}
	       		imgPath = $("#chooseImage3 img").attr('src');
	       		if(imgPath != "<%=basePath%>wechat/images/addImg.jpg")
	       		{
	       			pathList += imgPath;
	       			i++;
	       		}
				$.ajax({
	                data: {
	                	"coId":$("#coId").val(),
	                	"detailInfo" : $("#textarea").val(),
	                	"picPaths" : pathList
	                	},
	                type:"POST",
	                url:"<%=basePath%>pp/submmitComment_do",
	                error:function(data){
	                    var btnArray = ["出錯了！"];
	                    mui.alert(' ',' ',btnArray, function() {
	                	});
	                },
	                success:function(data){
	                    if (data == "true") {
	                    	var btnArray = ["恭喜你，评论成功"];
		                    mui.alert(' ',' ',btnArray, function() {
		                    	window.location.href="<%=basePath%>pp/getting";
		                	});
	                    }
	                    else {
	                         var btnArray = ["出錯了！"];
		                     mui.alert(' ',' ',btnArray, function() {
		                	 });
	                    }
	                }
	            });
			}
		</script>
	</body>
</html>