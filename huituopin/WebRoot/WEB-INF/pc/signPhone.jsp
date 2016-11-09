<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="<%=basePath %>pc/css/login.css">
		<link rel="stylesheet" href="<%=basePath %>pc/css/mui.css">
		<script src="<%=basePath%>wechat/js/jquery-1.9.1.js"></script>
		<script src="<%=basePath%>wechat/js/validate.js"></script>
		<title>验证手机</title>
		<style>
			body{
				background-color: white;
				font:"黑体";
			}
			.mui-control-content {
				background-color: white;
				min-height: 500px;
			}
			.mui-control-content .mui-loading {
				margin-top: 50px;
			}
			a{
				font-size: 18px;
				color: gray;
			}
		</style>
  </head>
  
 <body>
 		<input id="basePath" value="<%=basePath%>" style="display: none"/>
 		<input id="type" value="${type}" style="display: none"/>
		<div class="box">
			<div class="middle_top">
				<img name="logo" src="<%=basePath %>pc/images/logo.jpg" style="width: 250px;height: 90px;border: 0;margin-top: 120px;margin-left: 50px;"/> 
 			</div>
 			<p class="mui-control-item" href="#item1mobile" style="border-color: white;font-size: 16px;margin-top: 30px;text-align: center;">
				<c:if test="${type == 'false' }">爱心人士</c:if>
				<c:if test="${type == 'true' }">贫困人士</c:if>
			</p>
					
				
				
					<div id="item1mobile" class="mui-slider-item mui-control-content mui-active">
						<div id="scroll1" class="mui-scroll-wrapper">
							<div class="mui-scroll">
								<input type="text" placeholder="请输入手机号码" id="userPhoneNb" name="phoneNmb" style="margin-top: 30px;margin-bottom: 0px;height: 50px;">				
								<input onfocus="changealter()" type="text" placeholder="请输入短信验证码" id="code" name="phoneNmb" style="margin-top: 30px;height: 50px;margin-bottom: 5px">	
									<button onclick="checkPhone()" type="button" class="mui-btn mui-btn-primary" id="checkPhoneBtn" style="height: 35px;border-radius: 25px;color: #00BCE4;background-color: white;border: 1px solid #D0D0D0;position: absolute;z-index: 10;margin-left: 240px;margin-top: -48px;">
										获取验证码
									</button>
								<p id="CheckCodeErrror" style=" display:none; color: red;font-size: 15px;padding-left: 15px;height: 25px;">短信验证码错误</p>
								<button onclick="checkCode()" style="width: 100%;height: 50px;background-color: #00BCE4;border: 0px;font-size: 20px;color: white;">确认注册</button>
							</div>
						</div>
					</div>
					
					
			
			</div>
			<script type="text/javascript">
			function changealter(){
				$("#CheckCodeErrror").hide();
			}
			
			function checkPhone(){
				
				var basePath = $("#basePath").val();
				if(!validatePhone("userPhoneNb")){
					return false;
				}
				var userPhoneNb = $("#userPhoneNb").val();
				$.ajax({
					type:"POST",
					url:basePath+"/registerCheckPhone",
					data:{
						phone:userPhoneNb
					},
					success:function(msg){
						if(msg=="success"){//返回是true  说明号码可用 ，否则号码已经被注册
							alert("验证码发送成功，请注意查收");
							//改变按钮状态
							time();
						}else if(msg=="error1"){
							alert("该手机号码已经注册，请直接登录");				
						}else if(msg=="error2"){
							alert("验证码发送失败，请重新点击发送");
						}else if(msg=="error3"){
							alert("对不起，您今日获取的次数已达到上限···");
						}
					}
				});
			}
			var wait = 60;
			function time() {
				var btn = $("#checkPhoneBtn");
			    if (wait == 0) {
			        btn.removeAttr("disabled");
			        btn.text("获取验证码");
			        wait = 60;
			    } else {
			        btn.attr("disabled", true);
			        btn.text("重新获取"+wait);
			        wait--;
			        setTimeout(function () {
			            time(btn);
			        },
			        1000);
			    }
			}
			function checkCode(){
				var code = $("#code").val();
				var type = $("#type").val();
				var basePath = $("#basePath").val();
				var phone = $("#userPhoneNb").val();
				if(code == "" || code == null){
					$("#CheckCodeErrror").html("请输入验证码");
					$("#CheckCodeErrror").show();
					return false;
				}
				var reg = /\d{6}$/;
				if(!reg.test(code) || code.length>6){
					$("#CheckCodeErrror").html("您输入验证码的格式不正确···");
					$("#CheckCodeErrror").show();
					return false;
				}
				var flag = false;
				$.ajax({
					type:"POST",
					url:basePath+"/registerCheckCodeAction",
					data:{
						code:code
					},
					success:function(msg){
						if(!msg){
							$("#CheckCodeErrror").html("您输入的验证码不正确，请重新输入");
							$("#CheckCodeErrror").show();
							return false;
						}else{
							registeraction(basePath+"signAction",phone,type);
						}
					}
				});
			}
			
			function registeraction(action,phone,type){
				form = $("<form></form>");
			     form.attr('action',action);
			     form.attr('method','post');
			     input1 = $("<input type='hidden' name='phone' />");
			     input1.attr('value',phone);
			     input2 = $("<input type='hidden' name='type' />");
			     input2.attr('value',type);
			     form.append(input1);
			     form.append(input2);
			     form.appendTo("body");
			     form.css('display','none');
			     form.submit();
			}
			</script>
	</body>
</html>
