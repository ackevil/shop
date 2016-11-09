<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<script src="<%=basePath%>wechat/js/mui.min.js"></script>
	<script src="<%=basePath%>wechat/js/jquery-1.9.1.js"></script>
	<script src="<%=basePath%>wechat/jsme/user.wechat.js"></script>
	<style type="text/css">
		.icondiv {
			height: 30px;
			width: 30px;
			margin: 0 auto;
			text-align: center;
		}	
		.footer {
			height: 30px;
			width: 40px;
			margin: 0 auto;
			padding-top: 10px;
		}
		.footer1 {
			height: 30px;
			width: 13px;
			margin: 0 auto;
			padding-top: 10px;
		}
		</style>
</head>
<body>
	<input id="basePath" value="<%=basePath%>" style="display: none"/>
		<a href="<%=basePath%>register/" style="float: left; width: 50%;" id="home">
			<div class="icondiv" style="height:40px">
				<img id="img1" src="<%=basePath%>wechat/images/htp_dark.png" class="footer" style="height:40px"/>
			</div>
		</a>
		<a id="me"  style="float: right;width: 50%;">
			<div class="icondiv" style="height:40px">
				<img id="img2" src="<%=basePath%>wechat/images/me_light.png" class="footer1" style="height:40px"/>
			</div>
		</a>	
</body>
</html>