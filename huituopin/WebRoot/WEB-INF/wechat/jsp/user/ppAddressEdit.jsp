<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>编辑地址</title>
    <link href="<%=basePath%>wechat/css/login.css" rel="stylesheet" />
    <link rel="stylesheet" href="<%=basePath%>wechat/css/mui.css">
    <link href="<%=basePath%>wechat/css/mui.picker.css" rel="stylesheet" />
	<link href="<%=basePath%>wechat/css/mui.poppicker.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/app.css"/>
	<style>
		.mui-input-group .mui-input-row{
			height: 45px;
		}
		.mui-input-row label{
			padding-top: 12px;
			color: #515151;
			font-size: 15px;
			width: 28%;
		}
		.mui-input-row label ~ input{
			margin-top: 2px;
			font-size: 15px;
		}
		.mui-table-view:before{
			background-color: transparent;
		}
		.mui-poppicker-header{
			padding: 6px;
		}
		.mui-poppicker-header .mui-btn{
			padding: 5px 10px;
		}
	</style>
</head>
	<body>
		<form method="post" class="mui-input-group" action="ppAddressEditAction"  onsubmit="return ppAddressAddAction()">
				<div class="mui-input-row">
					<label>收货人</label>
					<input name="type" value="${type}" style="display: none;">
					<input name="shippingAddId" value="${shippingAddressList.shippingAddId}" style="display: none;">
					<input name="userId" value="${shippingAddressList.userId}" style="display: none;">
					<input name="shippingAddName" id="receiver" type="text" value="${shippingAddressList.shippingAddName}"/>				
					</div>
					<div class="mui-input-row" >
						<label>联系电话</label>
						<input name="shippingAddPhone" id="tel" type="text" class="mui-input-clear" value="${shippingAddressList.shippingAddPhone}"/>
					</div>
					<div class="mui-input-row" >
						<label class="mui-navigate-right">所在地区</label>
						<input name="shippingAddProvince" id='showCityPicker' style="width: 72%;height:
						 100%;background-color: transparent;border: 0;text-align:right;padding-right:40px;" 
						 readonly="readonly" value="${shippingAddressList.shippingAddProvince}"/>				
					</div>
					<div class="mui-input-row1" style="height: 120px;" >
						<textarea name="shippingAddDetail" id="textarea" rows="5" style="font-size: 15px;border: 0;margin-top: 10px;"
						 >${shippingAddressList.shippingAddDetail}</textarea>						
					</div>									
				<div class="login-lastline" style="padding-left: 0;padding-right: 0;padding-top: 14px;">
					<input id="save" type="submit" class="login-button" value="确认修改" style="font-size: 15px;background-color:ddarkgray;color:FC5959;"/>			
				</div>				
		</form> 
		<script src="<%=basePath%>wechat/js/city.data.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>wechat/js/mui.min.js"></script>
		<script src="<%=basePath%>wechat/js/picker.js"></script>
		<script src="<%=basePath%>wechat/js/poppicker.js"></script>
		<script src="<%=basePath%>wechat/js/jquery-1.9.1.js"></script>
		<script src="<%=basePath%>wechat/js/validate.js"></script>
		<script type="text/javascript">
			function ppAddressAddAction(){
				if(checkNull("#receiver","收货人不能为空"))
					return false; 
				if(checkNull("#tel","电话号码不能为空"))
					return false; 
				if(!validatePhone("tel"))
					return false;
				if(checkNull("#showCityPicker","请选择地址"))
					return false; 
				if(checkNull("#textarea","详细地址不能为空"))
					return false; 
				
			}
			function checkNull(id,text){
				var value = $(id).val();
				if(value == "" || value == null){
					alert(text);
					return true;
				}
			}
		
			(function($, doc) {
				$.init();
				$.ready(function() {
					var cityPicker = new $.PopPicker({
						layer: 2
					});
					cityPicker.setData(cityData);
					var showCityPickerButton = doc.getElementById('showCityPicker');
					showCityPickerButton.addEventListener('tap', function(event) {
						cityPicker.show(function(items) {
							showCityPickerButton.value = items[0].text + " " + items[1].text;
						});
					}, false);		
				});
			})(mui, document);
		</script>
	</body>
</html>
