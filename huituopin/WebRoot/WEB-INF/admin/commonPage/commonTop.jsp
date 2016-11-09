<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="main-top">
			<div id="main-nav" class="main-nav">
				<a href="<%=path%>/admin/yjya/commodityManager"><img src="<%=path%>/admin/skin/images/top1.png" width="13" height="42">衣旧有爱</a>
				<a href="<%=path%>/admin/dsze/dszeList"><img src="<%=path%>/admin/skin/images/top1.png" width="13" height="42">滴水之恩</a>
			    <a href="<%=path%>/admin/zdjz/activityList"><img src="<%=path%>/admin/skin/images/top1.png" width="13" height="42">重大救助</a>
			    <a href="<%=path%>/admin/aigou/goodsManager"><img src="<%=path%>/admin/skin/images/top2.png" width="13" height="42">爱购</a>
				<a href="<%=path%>/admin/getAllLP"><img src="<%=path%>/admin/skin/images/top2.png" width="13" height="42">爱心人士</a>
				<a href="<%=path%>/admin/getAllPP"><img	src="<%=path%>/admin/skin/images/top4.png" width="13" height="42">贫困人士</a>
				<a href="<%=path%>/admin/bank/lovingBankList"><img	src="<%=path%>/admin/skin/images/top4.png" width="13" height="42">爱心银行</a>
				<a href="<%=path%>/admin/changePassword"><img src="<%=path%>/admin/skin/images/top5.png" width="13" height="42">设置</a>
			</div>
			<div class="nav-right">
				<div class="info">
					<i></i> <span> 您好，${sessionScope.adminuser.adminuserName}<br> 管理员</span>
					
				</div>
			</div>
		</div>