<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=path%>/admin/skin/css/style.css" rel="stylesheet" />
	<link href="<%=path%>/admin/skin/css/pagination.css" rel="stylesheet"type="text/css" />
	<link rel="stylesheet" href="<%=path%>/admin/skin/css/bootstrap.min.css"type="text/css"></link>
	<link rel="stylesheet" type="text/css"href="<%=path%>/admin/skin/css/bootstrap-datetimepicker.min.css" />
	<script type="text/javascript" charset="utf-8" src="<%=path%>/admin/skin/js/jquery-1.11.2.min.js"></script>
	<style type="text/css">
		.changePassword{
			width: 100%;
			height:50px;
			line-height:35px;
			padding-left:20px;
			margin-top: 20px;
			border: 1px solid #E7E7EB;
		}
		.changePassword input{
			height:30px;
			margin-top:4px;
			padding-left:5px;
			margin-left:20px;
			border: 1px solid #E7E7EB;
		}
		.changePassword a{
			float: right;
			margin-right: 30px;
			text-decoration: none;
		}
		.changePassword a:HOVER {
			cursor: pointer;
		}
	</style>
	<script type="text/javascript">
	function changePassword(){
		var password = $("#changePassword").val();
		var path = $("#path").val();
		gotoNextPageWithParamas(path+"/admin/changePasswordAction",password);
	}
	function gotoNextPageWithParamas(action,password){
		form = $("<form></form>");
	     form.attr('action',action);
	     form.attr('method','post');
	     input1 = $("<input type='hidden' name='password' />");
	     input1.attr('value',password);
	     form.append(input1);
	     form.appendTo("body");
	     form.css('display','none');
	     form.submit();
	}
	</script>
  </head>
  
  <body>
  		 <!--头部-->
		<jsp:include page="../commonPage/commonTop.jsp"></jsp:include>
		<!--/头部-->
		<!-- 左侧 -->
		<jsp:include page="../commonPage/commonLeft.jsp"></jsp:include>
		<!-- /左侧 -->
		<!--右侧内容-->
		<div class="main-container">
		<input type="hidden" id="path" value="<%=path%>"/>
			<div class="mainbody">
				<div class="location" style="height:30px;border-bottom: 0px;line-height: 30px">
					<span>安全中心</span>
				</div>
				<div class="changePassword" style="width: 100%;height:40px; margin-top: 20px">
					<span>修改密码</span>
					<input id="changePassword" type="password"/>
					<a class="saveName" onclick="changePassword()">修改</a>
				</div>
			</div>
		</div>
		
  </body>
</html>
