<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<title>个人信息</title>
		<link rel="stylesheet" href="<%=basePath%>wechat/css/setttingmsg.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/app.css"/>
		<link href="<%=basePath%>wechat/css/mui.picker.css" rel="stylesheet" />
		<link href="<%=basePath%>wechat/css/mui.poppicker.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/mui.picker.min.css" />
		<style>
		.mui-table-view:before{
			background-color: transparent;
		}
		.mui-poppicker-header{
			padding: 6px;
		}
		.mui-poppicker-header .mui-btn{
			padding: 5px 10px;
		}
		.mui-poppicker-btn-ok{
			background-color:#FC5959;
			color:white;
			border:0;
		}
		.mui-dtpicker-header button:last-child{
			background-color:#FC5959;
			color:white;
			border:0;
		}
		input[type='button'], input[type='submit'], input[type='reset'], button, .mui-btn{
			border-radius:0px;
			border-top-left-radius:0px;
			border-top-right-radius:0px;
			border-bottom-right-radius:0px;
			border-bottom-left-radius:0px;
		}
		</style>
	</head>
	<body>	
		<div class="mui-scroll-wrapper">
			<div class="mui-scroll">
				<ul class="mui-table-view">
					<li class="mui-table-view-cell">
						<button id='promptBtn' type="button" style="margin:0px;width: 100%;border: 0;">
							<span class="mui-pull-left mui-pull">姓名</span>
							<span class="mui-pull-right mui-pull" id="userName">${sessionScope.user.userWcNickname}</span>
						</button>								
					</li>
					<li class="mui-table-view-cell">
						<button id='sexBtn' type="button" style="margin:0px;width: 100%;border: 0;">
							<span class="mui-pull-left mui-pull">性别</span>
							<span class="mui-pull-right mui-pull" id="sex">	
								<div style="width: 260px;float: right;height: 30px;margin-top: -15px;">
									<a id="woman" ><img id="women"style="padding-top: 10px;max-width: 24px;float: right;" /></a>
									<span style="float: right;height: 40px;width: 20px;padding-top: 14px;margin-right:10px;">女</span>
									<a id="man"><img id="men"style="padding-top: 10px;max-width: 24px;float: right;margin-right: 30px;"/></a>
									<span style="float: right;height: 40px;width: 20px;padding-top: 14px;margin-right:10px;">男</span>
								</div>
							</span>
						</button>	
					</li>
					<li class="mui-table-view-cell">
						<button id='dateBtn' type="button" class="btn" data-options='{"type":"date","beginYear":1950,"endYear":2020}' style="margin:0px;width: 100%;border: 0;">
							<span class="mui-pull-left mui-pull">出生年月</span>
							<span class="mui-pull-right mui-pull" id="date"></span>
						</button>
					</li>
					<li class="mui-table-view-cell">
						<button id='nativeBtn' type="button" style="margin:0px;width: 100%;border: 0;">
							<span class="mui-pull-left mui-pull">籍贯</span>
							<span class="mui-pull-right mui-pull" id="native"></span>
						</button>								
					</li>
					<li class="mui-table-view-cell">
						<button id='nationBtn' type="button" style="margin:0px;width: 100%;border: 0;">
							<span class="mui-pull-left mui-pull">民族</span>
							<span class="mui-pull-right mui-pull" id="nation"></span>
						</button>								
					</li>
					<li class="mui-table-view-cell1">
						<button id='addressBtn' type="button" style="margin:0px;width: 100%;border: 0;">
							<span class="mui-pull-left mui-pull">家庭住址</span>
							<span class="mui-pull-right mui-pull" id="address"></span>
						</button>								
					</li>
					<li class="mui-table-view-cell1">
						<button id='postalBtn' type="button" style="margin:0px;width: 100%;border: 0;">
							<span class="mui-pull-left mui-pull">邮政编码</span>
							<span class="mui-pull-right mui-pull" id="postal"></span>
						</button>								
					</li>
				</div>
			</ul>	
		<form action="ppInfoSetting" onsubmit="return changeInfoCheck()" method="post">
			<input id="userId" name="userId" type="text" style="display: none;" value="${sessionScope.user.userId}">	
			<input id="name" name="name" type="text" style="display: none;"/><!--姓名-->
			<input id="userSex" name="userSex" type="text" value="${sessionScope.user.userWcGender}" style="display: none;"/><!--性别 -->
			<input id="userBirthDay" name="userBirthDay" type="text" style="display: none;"/><!-- 出生年月-->
			<input id="userNative" name="userNative" type="text" style="display: none;"/><!-- 籍贯-->
			<input id="usernNation" name="userNation" type="text" style="display: none;"/><!-- 民族-->
			<input id="userAddress" name="userAddress" type="text" style="display: none;"/><!-- 家庭住址-->
			<input id="userPostal" name="userPostal" type="text" style="display: none;"/><!-- 邮政编码-->
			<input id="btn" type="submit" value="确定" style="height:42px;position:fixed;bottom: 0px;border:0px;width:100%;background-color: #00bce4;color: white;" />
		</form>
		</div>	
		<script src="<%=basePath%>wechat/js/mui.min.js"></script>
		<script src="<%=basePath%>wechat/js/city.data-3.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>wechat/js/picker.js"></script>
		<script src="<%=basePath%>wechat/js/poppicker.js"></script>
		<script src="<%=basePath%>wechat/js/jquery-1.9.1.js"></script>
		<script src="<%=basePath%>wechat/js/mui.picker.min.js"></script>
		<script type="text/javascript" charset="utf-8">
			if($("#userSex").val()){
				document.getElementById("men").src="<%=basePath%>wechat/images/choose.png";
				document.getElementById("women").src="<%=basePath%>wechat/images/circle.png";
				
			}else{
				document.getElementById("women").src="<%=basePath%>wechat/images/choose.png";
				document.getElementById("men").src="<%=basePath%>wechat/images/circle.png";
			}
				
			function changeInfoCheck(){
				if(checkNull("#userName","姓名不能为空"))
					return false;
				if(checkNull("#date","出生年月不能为空"))
					return false;
				if(checkNull("#native","籍贯不能为空"))
					return false;
				if(checkNull("#nation","民族不能为空"))
					return false;
				if(checkNull("#address","家庭住址不能为空"))
					return false;
				if(checkNull("#postal","邮政编码不能为空"))
					return false;
				if(checkAddressRight("#postal","邮政编码不合法"))
					return false;
					
				$("#name").val($("#userName").text());
				$("#userBirthDay").val($("#date").text());
				$("#userNative").val($("#native").text());
				$("#usernNation").val($("#nation").text());
				$("#userAddress").val($("#address").text());
				$("#userPostal").val($("#postal").text());
				
			}
			function checkNull(id,text){
				value = $(id).text();
				if(value == "" || value == null){
					alert(text);
					return true;
				}
			}
			function checkAddressRight(id,text){
				value=$(id).text();
				if(value.length!=6 && value.length>0){
					alert(text);
					return true;
				}
				
			}
			document.getElementById("promptBtn").addEventListener('tap', function(e) {
				e.detail.gesture.preventDefault(); //修复iOS 8.x平台存在的bug，使用plus.nativeUI.prompt会造成输入法闪一下又没了
				var btnArray = ['取消', '确定'];
				mui.prompt('', '', '请输入姓名', btnArray, function(e) {
					if (e.index == 1) {
						userName.innerText = e.value;
					} else {
						
					}
				})
			});
			(function($) {
				$.init();
				var result = $('#date')[0];
				var btns = $('.btn');
				btns.each(function(i, btn) {
					btn.addEventListener('tap', function() {
						var optionsJson = this.getAttribute('data-options') || '{}';
						var options = JSON.parse(optionsJson);
						var id = this.getAttribute('id');
						var picker = new $.DtPicker(options);
						picker.show(function(rs) {						
							result.innerText =  rs.text;
							picker.dispose();
						});
					}, false);
				});
			})(mui);
			(function($, doc) {
				$.init();
				$.ready(function() {
					var cityPicker = new $.PopPicker({
						layer: 3
					});
					cityPicker.setData(cityData3);
					var showCityPickerButton = doc.getElementById('nativeBtn');	
					var nativeText=doc.getElementById('native');
					showCityPickerButton.addEventListener('tap', function(event) {
						cityPicker.show(function(items) {
							nativeText.innerText = (items[0] || {}).text + " " + (items[1] || {}).text + " " + (items[2] || {}).text;
						});
					}, false);		
				});
			})(mui, document);
			document.getElementById("nationBtn").addEventListener('tap', function(e) {
				e.detail.gesture.preventDefault(); //修复iOS 8.x平台存在的bug，使用plus.nativeUI.prompt会造成输入法闪一下又没了
				var btnArray = ['取消', '确定'];
				mui.prompt('', '', '请输入民族', btnArray, function(e) {
					if (e.index == 1) {
						nation.innerText = e.value;
					} else {
						
					}
				})
			});
			(function($, doc) {
				$.init();
				$.ready(function() {
					var cityPicker = new $.PopPicker({
						layer: 3
					});
					cityPicker.setData(cityData3);
					var showCityPickerButton = doc.getElementById('addressBtn');	
					var nativeText=doc.getElementById('address');
					showCityPickerButton.addEventListener('tap', function(event) {
						cityPicker.show(function(items) {
							nativeText.innerText = (items[0] || {}).text + " " + (items[1] || {}).text + " " + (items[2] || {}).text;
						});
					}, false);		
				});
			})(mui, document);
			
			document.getElementById("postalBtn").addEventListener('tap', function(e) {
				e.detail.gesture.preventDefault(); //修复iOS 8.x平台存在的bug，使用plus.nativeUI.prompt会造成输入法闪一下又没了
				var btnArray = ['取消', '确定'];
				mui.prompt('', '', '请输入邮政编码', btnArray, function(e) {
					if (e.index == 1) {	
						postal.innerText = e.value;
					} else {
						
					}
				})
			});
		document.getElementById("woman").addEventListener("click",function(){
			$("#userSex").val("false");
			document.getElementById("women").src="<%=basePath%>wechat/images/choose.png";
			document.getElementById("men").src="<%=basePath%>wechat/images/circle.png";
			
		});
		document.getElementById("man").addEventListener("click",function(){
			$("#userSex").val("true");
			document.getElementById("men").src="<%=basePath%>wechat/images/choose.png";
			document.getElementById("women").src="<%=basePath%>wechat/images/circle.png";
			
		});
		</script>
	</body>
</html>
