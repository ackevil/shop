<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<title>登录</title>
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
   <div class="box">
			<div class="middle_top">
				<img name="logo" src="<%=basePath %>pc/images/logo.jpg" style="width: 250px;height: 90px;border: 0;margin-top: 120px;margin-left: 50px;"/> 
 			</div>
 			<div id="slider" class="mui-slider" style="margin-top:30px ;">
				<div id="sliderSegmentedControl" class="mui-slider-indicator mui-segmented-control mui-segmented-control-inverted">
					<a class="mui-control-item mui-active" href="#item1mobile">
						手机账号登录
					</a>
					<a class="mui-control-item" href="#item2mobile">
						微信扫码登录
					</a>
				</div>
				<!--<div id="sliderProgressBar" class="mui-slider-progress-bar mui-col-xs-2"></div>-->
				<div class="mui-slider-group">
					<div id="item1mobile" class="mui-slider-item mui-control-content mui-active">
						<div id="scroll1" class="mui-scroll-wrapper">
							<div class="mui-scroll">
								<input type="text" placeholder="请输入手机号码" id="userPhoneNb" name="phoneNmb" style="margin-top: 30px;margin-bottom: 0px;height: 50px;">				
								<input onfocus="changealter()"  type="text" placeholder="请输入短信验证码" id="code" name="phoneNmb" style="margin-top: 30px;height: 50px;margin-bottom: 5px">	
									<button onclick="checkPhone()" type="button" class="mui-btn mui-btn-primary" id="checkPhoneBtn" style="height: 35px;border-radius: 25px;color: #00BCE4;background-color: white;border: 1px solid #D0D0D0;position: absolute;z-index: 10;margin-left: 240px;margin-top: -48px;">
										获取验证码
									</button>
								<p id="CheckCodeErrror" style="display:none; color: red;font-size: 15px;padding-left: 15px;height: 25px; display: none;">短信验证码错误</p>
								<button onclick="checkCode()" style="width: 100%;height: 50px;background-color: #00BCE4;border: 0px;font-size: 20px;color: white;">登录</button>
								<div style="margin-top:20px; text-align:center"><a href="/sign" style="text-decoration:underline;font-size:14px;color:#00bce4">还没有账号?立即注册</a></div>
							</div>
						</div>
					</div>
					<div id="item2mobile" class="mui-slider-item mui-control-content">
						<div id="scroll2" class="mui-scroll-wrapper">
							<div class="mui-scroll">
								<p style="margin-top: 30px;font-size: 21px;text-align: center;color: black;">Log In to WeChat</p>
								<img src="<%=basePath %>pc/images/erweima.jpg" style="height: 300px;width: 300px;margin-left: 25px;"/>
								<p style="margin-top: 15px;font-size: 19px;text-align: center;color: black;">Scan QR Code in WeChat to log in</p>
								<p style="font-size: 19px;text-align: center;color: black;">“慧脱贫”</p>
								<div style="margin-top:20px; text-align:center"><a href="chooseType.html" style="text-decoration:underline;font-size:14px;color:#00bce4">还没有账号?立即注册</a></div>
							</div>
						</div>

					</div>
					
				</div>
			</div>
 			
 			
		</div>
		<script src="<%=basePath %>pc/js/mui.min.js"></script>
		<script>
			mui.init({
				swipeBack: false
			});
			(function($) {
				$('.mui-scroll-wrapper').scroll({
					indicators: true //是否显示滚动条
				});
			})(mui);
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
					url:basePath+"/checkPhone",
					data:{
						phone:userPhoneNb
					},
					success:function(msg){
						if(msg=="success"){//返回是true  说明号码可用 ，否则号码已经被注册
							alert("验证码发送成功，请注意查收");
							//改变按钮状态
							time();
						}else if(msg=="error1"){
							alert("手机号码还未注册，请先注册");				
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
					url:basePath+"/checkCodeAction",
					data:{
						code:code,
						phone:phone
					},
					success:function(msg){
						if(!msg){
							$("#CheckCodeErrror").html("您输入的验证码不正确，请重新输入");
							$("#CheckCodeErrror").show();
							return false;
						}else{
							registeraction(basePath,phone);
						}
					}
				});
			}
			
			function registeraction(action,phone){
				form = $("<form></form>");
			     form.attr('action',action);
			     form.attr('method','get');
			     input1 = $("<input type='hidden' name='phone' />");
			     input1.attr('value',phone);
			     form.append(input1);
			     form.appendTo("body");
			     form.css('display','none');
			     form.submit();
			}
		</script>
  </body>
</html>
