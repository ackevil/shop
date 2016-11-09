<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员管理</title>

<script type="text/javascript" charset="utf-8" src="<%=path%>/admin/skin/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/admin/skin/js/jsAddress.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/admin/skin/js/laymain.js"></script>
<script type="text/javascript" charset="utf-8"src="<%=path%>/admin/skin/js/layindex.js"></script>
<script type="text/javascript" charset="utf-8"src="<%=path%>/admin/skin/js/laymain.js"></script>
<script type="text/javascript" charset="utf-8"src="<%=path%>/admin/skin/js/common.js"></script>
<script type="text/javascript" charset="utf-8"src="<%=path%>/admin/skin/js/showDialog.js"></script>
<script type="text/javascript" charset="utf-8"src="<%=path%>/admin/skin/js/bootstrap.min.js"></script>

<link href="<%=path%>/admin/skin/css/style.css" rel="stylesheet" />
<link href="<%=path%>/admin/skin/css/pagination.css" rel="stylesheet"type="text/css" />
<link rel="stylesheet" href="<%=path%>/admin/skin/css/bootstrap.min.css"type="text/css"></link>
<link rel="stylesheet" type="text/css"href="<%=path%>/admin/skin/css/bootstrap-datetimepicker.min.css" />
<style>
input{
	background-color: #fff;
    border: 1px solid #ccc;
    border-radius: 3px;
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
    color: #555;
    font-size: 13px;
    height: 30px;
    width:200px;
    line-height: 1.42857;
    padding: 6px 12px;
    transition: border-color 0.15s ease-in-out 0s, box-shadow 0.15s ease-in-out 0s;
    vertical-align: middle;
    box-shadow:inset 0 1px 1px rgba(0,0,0,.075);
}
input:focus{
	border:1px solid #66AFE9;
	
}
label{
	font-weight: normal;
	text-align:right;
	
}
select{
	height: 30px;
}
input[type="radio"]{
	margin: 0px 0 0;
}
.label-text{
	width:285px; 
	margin-right:20px; 
}
.checkbox-inline{
	padding-left: 0px;
}
</style>
<script type="text/javascript">
	function check(){
		
	}
</script>
</head>
<body class="indexbody">
	<form action="<%=path%>/admin/lpAddUserAction" method="post" onsubmit="return check()">
		<!--头部-->
		<jsp:include page="../commonPage/commonTop.jsp"></jsp:include>
		<!--/头部-->
		<!-- 左侧 -->
		<jsp:include page="../commonPage/commonLeft.jsp"></jsp:include>
		<!-- /左侧 -->

		<!--右侧内容-->
		<div class="main-container">
			<div class="mainbody">
				<!-- 导航栏 -->
				<div class="location" style="height:30px;border-bottom: 0px;line-height: 30px">
					<a href="/huituopin/admin/getAllLP" class="home"><i></i><span>所有会员</span></a>
					<i class="arrow"></i><span>添加会员</span>
				</div>
				<div style="width: 100%; margin-top: 20px">
					<table class="table table-bordered">
						 <tbody>
						    <tr><td  style="height: 55px;">
						      	<div style="margin-top: 6px;">
						      		<label class="label-text"><span style="color: red">(必填)</span>电话号码</label>
						      		<input type="text" name="userPhone" id="userPhone" placeholder="请填电话号码">
						      	</div>
						    </td></tr>
						    
						     <tr><td  style="height: 55px;">
						      	<div style="margin-top: 6px;">
						      		<label class="label-text">姓名</label>
						      		<input type="text" name="userName" id="exampleInputName2" placeholder="请填姓名">
						      	</div>
						    </td></tr>
						    
						    <tr><td  style="height: 55px;">
						      	<div style="margin-top: 6px;">
						      		<label class="label-text" >所在市区</label>
								      	<div style="display: inline;">
								           	<select id="province" name="province"></select>
								            <select name="city" id="city"></select>
								            <select name="area" id="area"></select>
								         </div>
								         <script type="text/javascript">
								                addressInit('province', 'city', 'area', '湖南', '长沙市', '岳麓区');
								                addressInit('Select1', 'Select2', 'Select3');
								         </script>
								</div>
						    </td></tr>
						    
						    <tr><td  style="height: 55px;">
						      	<div style="margin-top: 6px;">
						      		<label class="label-text">详细地址</label>
						      		<input style="width:300px;" type="text" name="userDetailAddr" id="exampleInputName2" placeholder="请填详细地址">
						      	</div>
						    </td></tr>
						    
						    <tr><td  style="height: 55px;">
						      	<div style="margin-top: 6px;">
						      		<label class="label-text">年龄</label>
						      		<input  style="width:100px;" type="text" name="userAge" id="exampleInputName2" placeholder="请填年龄">
						      	</div>
						    </td></tr>
						    
						    <tr><td  style="height: 55px;">
						      	<div style="margin-top: 6px;">
						      		<label class="label-text">性别</label>
						      		<div style="width: 500px;height: 100%;display:inline">
									    <label class="checkbox-inline">
											<input style="width: 20px" type="radio" name="userSex" id="optionsRadios3" value="1" checked>男
										</label>
										<label class="checkbox-inline">
											<input style="width: 20px" type="radio" name="userSex" id="optionsRadios4" value="2">女
										</label>
									</div>
								</div>
						    </td></tr>
						    <!-- 
						     <tr><td  style="height: 55px;">
						      	<div style="margin-top: 6px;">
						      		<label class="label-text">审核</label>
						      		<select name="userType" style="width: 80px">
									  <option value="1">待审核</option>
									  <option value="2">已通过</option>
									  <option value="3">不通过</option>
									</select>
						      	</div>
						    </td></tr>
						     -->
						    
						    <tr><td  style="height: 55px;">
						      	<div style="margin-top: 6px;text-align: center;width: 55%">
						      		<div class="form-group">
					                    <div style="margin-top: 10px">
					                        <input style="width: 50px;padding: 3px 6px;" type="submit" class="btn btn-primary" value="保   存"/>
					                    </div>
					                </div>
						      	</div>
						    </td></tr>
					  	</tbody>
					</table>
				</div>				
			</div>
		</div>
	</form>
</body>
</html>
