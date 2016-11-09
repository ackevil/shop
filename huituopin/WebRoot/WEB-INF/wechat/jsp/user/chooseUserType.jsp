<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>登录</title>
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=basePath%>wechat/css/choose.css" />
	<link rel="stylesheet" href="<%=basePath%>wechat/css/mui.css"  />
		<style>
			.mui-table-view:before{
				background-color:transparent;
			}
			.mui-table-view:after{
				background-color:transparent;
			}
			.mui-table-view-cell>a:not(.mui-btn){
				margin:auto;
			}
		</style>
  </head>
  
  <body>
  		<input id="basePath" value="<%=basePath%>" style="display: none"/>
		<div class="choose-header">
			<p class="choose-text"><span>请选择账户类型</span></p>
		</div>
		<div class="choose-content" style="text-align: center">			
			<ul class="mui-table-view "style="height: 70px;margin: 15px;">
				<li id="aixin3" class="mui-table-view-cell" style="padding:0;border:1px solid #fc5959">
					<a id="aixin" href="#account"  style="margin-top: -14px;height: 86px;background-color: white;">
						<div id="aixin1" style="font-size: 19px;padding-top: 40px;margin: 0 auto;color:#fc5959;">我是爱心人士</div>
						<img id="aixin2" class="mui-media-object mui-pull-right head-img" src="<%=basePath%>wechat/images/click.png" style="height: 30px;margin-top: -5px;"/>
						<div class="xiangxi" >
							
						</div>
					</a>
				</li>
				<div style="height: 12px;"></div>
				<li id="pinkun3"class="mui-table-view-cell" style="padding:0;border:1px solid gainsboro">
					<a id="pinkun" href="#account"  style="margin-top: -14px;height: 86px;background-color: white;">
						<div id="pinkun1"style="font-size: 19px;padding-top: 40px;margin: 0 auto;color:gainsboro">我是贫困人士</div>
						<img id="pinkun2"class="mui-media-object mui-pull-right head-img" src="<%=basePath%>wechat/images/unclick.png" style="height: 30px;margin-top: -5px;"/>
						<div class="xiangxi" >
							
						</div>
					</a>
				</li>
			</ul>
		</div>
		
		<div class="choose-foot">
			<form action="userLogin" method="post" >
				<input id="userPhone" name="userPhone" style="display: none" value="${phone}" />
				<input id="userPwd" name="userPwd" style="display: none" value="123456"/>
				<input id="userWcId" name="userWcId" style="display: none" value="${sessionScope.userWcId}"/>
				<input id="userWcNickname" name="userWcNickname" style="display: none" value="${sessionScope.userWcNickname}"/>
				<input id="userWcAvatar" name="userWcAvatar" style="display: none" value="${sessionScope.userWcAvatar}"/>
				<input id="userWcGender" name="userWcGender" style="display: none" value="${sessionScope.userWcGender}"/>
				<input id="userType" name="userType" style="display: none" value="false">
				<input id="userIsDelete" name="userIsDelete" style="display: none" value="true"/>
				<input id="userIsOnline" name="userIsOnline" style="display: none" value="true"/>
				<button type="submit" class="choose-button" style="padding:0px">确 定</button>
			</form>
		</div>
		
		<script src="<%=basePath%>wechat/js/mui.min.js"></script>
		<script src="<%=basePath%>wechat/js/jquery-1.9.1.js"></script>
		<script src="<%=basePath%>wechat/jsme/user.wechat.js"></script>
		<script type="text/javascript">
		var basePath = $("#basePath").val();
		document.getElementById("aixin").addEventListener("click",function(end){
			$("#userType").val("false");
			document.getElementById("pinkun1").style.color="gainsboro";
			document.getElementById("pinkun2").src=basePath+"wechat/images/unclick.png";
			document.getElementById("pinkun3").style.border="1px solid gainsboro";
			document.getElementById("aixin1").style.color="#fc5959";
			document.getElementById("aixin2").src=basePath+"wechat/images/click.png";
			document.getElementById("aixin3").style.border="1px solid #fc5959";
		});
		document.getElementById("pinkun").addEventListener("click",function(end){
			$("#userType").val("true");
			document.getElementById("aixin1").style.color="gainsboro";
			document.getElementById("aixin2").src=basePath+"wechat/images/unclick.png";
			document.getElementById("aixin3").style.border="1px solid gainsboro";
			document.getElementById("pinkun1").style.color="#fc5959";
			document.getElementById("pinkun2").src=basePath+"wechat/images/click.png";
			document.getElementById("pinkun3").style.border="1px solid #fc5959";
		});
		
		</script>
	</body>
</html>
