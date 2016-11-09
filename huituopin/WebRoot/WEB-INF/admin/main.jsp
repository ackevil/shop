<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理中心</title>
<link href="<%=path%>/admin/skin/css/style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" charset="utf-8"
	src="<%=path%>/admin/skin/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=path%>/admin/skin/js/layindex.js"></script>
<script src="<%=path%>/admin/skin/js/showDialog.js" charset="gb2312"
	type="text/javascript"></script>
<link href="<%=path%>/admin/skin/css/showDialog.css" rel="stylesheet" />

</head>

<body class="indexbody">
	<form id="form1">
		<!--头部-->
		<jsp:include page="commonPage/commonTop.jsp"></jsp:include>
		<!--/头部-->
		<!-- 左侧 -->
		<jsp:include page="commonPage/commonLeft.jsp"></jsp:include>
		<!-- /左侧 -->
		<!--右侧内容-->
		<div class="main-container">
			<div class="mainbody">
				<!--导航栏-->
				<div class="location">
					<a class="home" href="index.html"><i></i><span>首页</span> </a> <i
						class="arrow"></i><span>信息中心</span>
				</div>
				<!--/导航栏-->

				<!--内容-->
				<div class="line10"></div>
				<div class="nlist-1">
					<ul>
						<li>本次登录IP：112.81.214.63</li>
						<li>上次登录IP：112.81.214.63</li>
						<li>上次登录时间：2015-7-8 17:02:55</li>
					</ul>
				</div>
				<div class="line10"></div>
				<div class="nlist-2">
					<h3>
						<i></i>站点信息
					</h3>
					<ul>
						<li>站点名称：慧脱贫</li>
						<li>公司名称：湖南桐叶粑粑科技有限公司</li>
						<li>网站域名：http://www.tybb.cc</li>
						<li>网站管理目录：y</li>
						<li>服务器名称：XXX</li>
						<li>服务器IP：112.81.214.63</li>
						<li>操作系统：Linux</li>
						<li>服务器端口：81</li>
						<li>CPU：4</li>
						<li>RAM：2G</li>
						<li>当前RAM：1G</li>
						<li>目录物理路径：C://</li>
						<li>系统版本：V2.0.0</li>
						<li>升级通知：<a target="_blank" href="#">暂无升级</a></li>
					</ul>
				</div>
				<div class="line20"></div>
				<div class="nlist-3">
					<ul>
						<li><a onclick="" class="icon-setting" href=""></a><span>系统设置</span>
						</li>
						<li><a onclick="" class="icon-channel" href=""></a><span>站点管理</span>
						</li>
						<li><a onclick="" class="icon-user" href=""></a><span>会员管理</span>
						</li>
						<li><a onclick="" class="icon-manaer" href=""></a><span>管理员</span>
						</li>
						<li><a onclick="" class="icon-log" href=""></a><span>系统日志</span>
						</li>
					</ul>
				</div>
				<!--/内容-->
			</div>
		</div>
	</form>
</body>
</html>

