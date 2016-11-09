<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>会员管理</title>
	<link href="<%=path%>/admin/skin/css/style.css" rel="stylesheet" />
	<link href="<%=path%>/admin/skin/css/pagination.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" charset="utf-8" src="<%=path%>/admin/skin/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=path%>/admin/skin/js/layindex.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=path%>/admin/skin/js/laymain.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=path%>/admin/skin/js/common.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=path%>/admin/skin/js/showDialog.js"></script>
</head>
<body class="indexbody">
	<form id="form1" runat="server">
		<!--头部-->
		<jsp:include page="../commonPage/commonTop.jsp"></jsp:include>
		<!--/头部-->
		<!-- 左侧 -->
		<jsp:include page="../commonPage/commonLeft.jsp"></jsp:include>
		<!-- /左侧 -->
		
		
		<!--右侧内容-->
		<div class="main-container">
		
		</div>
	</form>
</body>
</html>
