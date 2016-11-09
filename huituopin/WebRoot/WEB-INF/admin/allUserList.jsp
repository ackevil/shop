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
						 <span  class="glyphicon glyphicon-user">
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
													    <li><a href="/admin/addUserList">管理员用户</a></li>
													    <li><a href="#">普通用户</a></li>
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
													
													<div class="btn btn-info col-md-12">
														<div type="button" class=" dropdown-toggle " data-toggle="dropdown">
													    Action <span class="caret"></span>
														</div>
													  <ul class="dropdown-menu" role="menu">
													    <li><a href="#">Action</a></li>
													    <li><a href="#">Another action</a></li>
													    <li><a href="#">Something else here</a></li>
													    <li class="divider"></li>
													    <li><a href="#">Separated link</a></li>
													  </ul>
													</div>
													  
												</div>
											</div>
								</div>
					</div>
					
					<div class="col-md-10">
								
									<div class="panel-heading">
										<div class="panel-title">
											<ol class="breadcrumb">
											  <li class="label label-info ">用户管理</li>
											  <li class="label active">普通用户管理</li>
											</ol>
										</div>
							        </div>
									
								<div class="panel-body">
									
									 <form  class="form" action="/admin/userList">
									 	<div class="row ">
										 	<div class="form-group col-md-4">
										 		<label class="label label-info ">用户列表</label>
										 	</div>
										 	<div class="form-group col-md-1">
										 		<label class="label label-info ">搜索用户:</label>
										 	</div>
						                    <div class="form-group col-md-3 "> 
						                    	<input type="text" name="searchID" id="searchID" class="form-control" placeholder="用户ID"> 
						                    </div>
						                    <div class="form-group col-md-2 ">
						                    	<input class="btn btn-success" type='submit' value='Search'/>
						                    </div>
						                    <div class="form-group col-md-2">
						                    	<input class="btn btn-success" type='submit' value='添加用户'/>
						                    </div>
									 	<div>
					                </form>   
									
									
									
									
									<div class="container-fluid">
										<c:if test="${empty userList}">
						                		<div class="text text-warning">抱歉，找不到用户！</div>
						           		</c:if>
						           		<c:if test="${not empty userList}">   
										<table class="table table-hover table-bordered table-responsive">
					                    <thead style="background-color: #bce8f1;">
					                    <tr>
					                        <th>ID</th>
					                         <th>昵称</th>
					                        <th>用户类型</th>
					                        <th>手机号</th>
					                        <th>登录密码</th>
					                      
					                        <th>注册时间</th>
					                        <th>详细资料</th>
					                        <th>编辑</th>
					                        <th>删除</th>
					                    </tr>
					                    </thead>
					                    <tbody>
					                    <c:forEach items="${userList}" var="user">
					                        <tr>
					                          <th><c:out value="${user.userId}"/></th>
					                            <th><c:out value="${user.userWcNickname}"/></th> 
					                          
					                            <th><c:out value="${user.userType}"/></th>
					                            <th><c:out value="${user.userPhone}"/></th>
					                            <th><c:out value="${user.userPwd}"/></th> 
					                          
					                            <th><c:out value="${user.userSigntime}"/></th> 
					                            <th><a href="editEmployee?id=<c:out value='${user.userId}'/>">查看</a></th>
					                            <th><a href="editEmployee?id=<c:out value='${user.userId}'/>">编辑</a></th>
					                            <th><a href="deleteEmployee?id=<c:out value='${user.userId}'/>">删除</a></th>                          
					                        </tr>
					                    </c:forEach>
					                    </tbody>
					                </table>
					               </c:if>
								</div>
					</div>
				</div>
			</div>
	</body>
	
</html>