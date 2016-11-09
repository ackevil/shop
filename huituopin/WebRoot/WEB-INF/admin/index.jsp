<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css" />
		<script src="<%=path%>/js/jquery-1.9.1.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=path%>/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		
				<div class="nav navbar-default" role="nav">
				<div class="container-fluid">
				<div class="navbar-header">
					
					<button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				
					<a href="/admin/index" class="navbar-brand">
						<span class="glyphicon glyphicon-home" style="height: 50px; margin-top: -15px;"/>
					</a>
					<div class=" navbar-text ">
					慧脱贫后台管理系统
					</div>
				</div>
				
				<div class="collapse navbar-collapse">
						
					<div class="navbar-form navbar-right">
						 <span class="glyphicon glyphicon-user">
						 	<span class="text-info">admin</span>
						 </span>
						 <span class="text-justify">|</span>
						 <a  class="glyphicon glyphicon-log-out" href="/admin/login">
						 	<span class="text-warning">注销</span>
						 </a>
						
					</div>
				</div>
			</div>
			</div>
					
				<div class="container-fluid">
				<div class="row" >
					<div class="col-md-2" >
								<div class="panel-body" >
										<div class="container-fluid">
												<div class="row">
													<div class="h3 text-center text-danger">
														管理面板
													</div>
														<div class="btn btn-info  col-md-12">
														<div type="button" class=" dropdown-toggle " data-toggle="dropdown">
													    用户管理 <span class="caret"></span>
														</div>
													  <ul class="dropdown-menu" role="menu">
													    <li><a href="/admin/allUserList">管理员用户</a></li>
													    <li><a href="/admin/addUserList">普通用户</a></li>
													  </ul>
													</div>
													
													<div class=" btn btn-info col-md-12 ">
														爱购订单管理
													</div>
													<div class=" btn btn-info col-md-12">
														依旧有爱
													</div>
													<div class=" btn btn-info col-md-12">
														系统设置
													</div>
												</div>
											</div>
								</div>
					</div>
					
					<div class="col-md-10">
							  <div class="h3">欢迎使用管理系统</div>
					</div>
				</div>
			</div>
	</body>
	
</html>