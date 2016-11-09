<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>发布捐赠</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<script src="<%=basePath%>wechat/js/mui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>wechat/js/jquery-1.9.1.js"></script>
		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=basePath%>wechat/css/mui1.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/app.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/FaBuJuanZeng.css" />
		<style>
			
			.login-lastline {
				height: 70px;
				width: 100%;
				background-color: #efeff4;
				padding-top: 12px;
				padding-left: 15px;
				padding-right: 15px;
			}
			
			.login-button {
				width: 100%;
				height: 43px;
				outline: none;
				font-size: 14px;
				color: white;
				border-color: transparent;
				background-color: #fc5959;
			}
			.mui-input-group .mui-input-row:after{
				
			}
			.mui-input-group:after{
				background-color: transparent;
			}
			button{
				padding: 0px;
			}
			.mui-input-row{
				background-color:white;
			}
			.mui-input-row:after{
				position:absolute;
				right:0;
				bottom:0;
				left:15px;
				height:1px;
				content:'';
				background-color:#c8c7cc;
			}
			select{
				-webkit-appearance: none;
			}
			.mui-input-row:nth-child(4):after{
				position: absolute;
			    right: 0;
			    bottom: 0;
			    left: 0;
			    height: 1px;
			    content: '';
			    background-color: #c8c7cc;
			}
		</style>
	</head>

	<body>
		<input id="userId" type="hidden" value="${userId}" />
		<div class="mui-content">
				<div class="mui-input-row" style="height: 45px;font-size:15px ;color: #4A4A4A;padding-top: 5px;">
					<label style="width: 28%;">产品名称</label>
					<input id="txtName"style="width: 72%;margin-top: -2px;font-size: 15px;color: black;" type="text" placeholder="">
				</div>
				<div class="mui-input-row" style="height: 45px;font-size:15px ;color: #4A4A4A;padding-top: 5px;">
					<label class="mui-navigate-right" style="width: 28%;">所属类别</label>
					<div style="width:72%;height: 100%;float: left;">
						<select id="type1" style="padding-left:0px;font-size: 15px;height: 45px;margin-top: -5px;">
						</select>
					</div>					
				</div>
				<div class="mui-input-row" style="height: 45px;font-size:15px ;color: #4A4A4A;padding-top: 5px;">
					<label class="mui-navigate-right" style="width: 28%;">性别</label>
					<div style="width:72%;height: 100%;float: left;">
						<select id="type2" style="padding-left:0px;font-size: 15px;height: 45px;margin-top: -5px;">
							<option value="1">男</option>
							<option value="2">女</option>
						</select>
					</div>					
				</div>
				<div class="mui-input-row" style="height: 45px;font-size:15px ;color: #4A4A4A;padding-top: 5px;">
					<label class="mui-navigate-right" style="width: 28%;">年龄段</label>
					<div style="width:72%;height: 100%;float: left;">
						<select id="type3" style="padding-left:0px;font-size: 15px;height: 45px;margin-top: -5px;">
							<option value="1">童年</option>
							<option value="2">青少年</option>
							<option value="3">壮年</option>
							<option value="4">老年</option>
						</select>
					</div>					
				</div>
			<div style="height: 10px;background-color: #EFEFF4;"></div>
			
			<div class="product-detail">
				<div class="product-detail-title">
					<span>捐赠寄语</span>
				</div>
				<div class=" product-detail-content" style="height: 100px;">
					<textarea id="textarea" rows="" 
						placeholder="例：小小心意，希望能帮助到他人，物品有限，爱心无限。"></textarea>
				</div>
				<div id="imagePreview"class="product-detail-img">
					
					<button id="chooseImage1" style="height:70px"><img src="<%=basePath%>wechat/images/addImg.jpg"/></button>
					<button id="chooseImage2" style="height:70px"><img src="<%=basePath%>wechat/images/addImg.jpg"/></button>
					<button id="chooseImage3" style="height:70px"><img src="<%=basePath%>wechat/images/addImg.jpg"/></button>
					<!-- <img src="/resource/image/20160526/dfsaf.jpg"/>
					<img src="/resource/image/20160526/dfqwf.jpg"/> -->
				</div>
			</div>
			<input id="appId" type="hidden" value="${appId}" />
			<input id="timestamp" type="hidden" value="${timestamp}" />
			<input id="noncestr" type="hidden" value="${noncestr}" />
			<input id="signature" type="hidden" value="${signature}" />
			<input id="ctInfo" type="hidden" value="${ctInfo}"/>
			<div class="login-lastline">
				<input type="submit" class="login-button" onclick="donateCloth()" value="确定" />
			</div>
		</div>
	</body>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript" src="<%=basePath%>wechat/js/wechat.js"></script>
	<script type="text/javascript">
		$(function(){
			displayType1($("#ctInfo").val());
		});
		function displayType1(msg){
			var splitMsg = msg.split("|");
		    for (var i = 1; i <=splitMsg[0]; i++) {
		        message = splitMsg[i].split("├");
		        var id = message[0];
	            var name = message[1];
		        var node = "<option value='"+ id +"' >"+ name +"</option>";
		        $("#type1").append(node);
		    }
		}
        function donateCloth(){
        	if(($("#txtName").val()).trim()==""){
        		var btnArray = ["衣物名称不能为空"];
                mui.alert(' ',' ',btnArray, function() {
            	});
	      		return;	
	      	}
	      	if(($("#textarea").val()).trim()==""){
	      		var btnArray = ["还是写点捐赠语吧"];
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
       		if(i==0){
       			var btnArray = ["请至少上传一张图片"];
                mui.alert(' ',' ',btnArray, function() {
            	});
       			return;
       		}
	        $.ajax({
	           data: {
	           	"userId":$("#userId").val(),
	           	"clothName" : $("#txtName").val(),
	           	"type1" : $("#type1").val(),
	           	"type2" : $("#type2").val(),
	           	"type3" : $("#type3").val(),
	           	"detailInfo" : $("#textarea").val(),
	           	"picPaths" : pathList 
	           	},
	           type:"POST",
	           url:"donateCloth_do",
	           error:function(data){
	               var btnArray = ["出錯了！"];
                    mui.alert(' ',' ',btnArray, function() {
                	});
	           },
	           success:function(data){
	               if (data == "true") {
	               		var btnArray = ["恭喜你，捐赠成功"];
	                    mui.alert(' ',' ',btnArray, function() {
	                    	window.location.href="<%=basePath%>wechat/yjya";
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
</html>