<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>

	<head>
		 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>管理收货地址</title>
     <script src="<%=basePath%>wechat/js/jquery-1.9.1.js"></script>
    <script src="<%=basePath%>wechat/jsme/user.wechat.js"></script>
    <link href="<%=basePath%>wechat/css/login.css" rel="stylesheet" />
    <link rel="stylesheet" href="<%=basePath%>wechat/css/mui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/app.css"/>
	<script src="<%=basePath%>wechat/js/mui.min.js"></script>
	<style>
		.mui-input-group .mui-input-row1{
			height: 100px;
			font-size: 15px;
		}
		.mui-input-row label{
			padding-top: 12px;
			color: gray;
			font-size: 15px;
			width: 20%;
		}
		.mui-input-row label ~ input{
			margin-top: 2px;
			font-size: 15px;
		}
		.mui-input-group .mui-input-row:after{
			background-color: transparent;
		}
	</style>
	<script type="text/javascript">
	function lpSelectreciveInfo(shippingAddId,pId){
		var shippingAddressList = $("#shippingAddressList").val();
		if(pId!="other"&&pId!=""&&shippingAddressList!=null){
			var basePath = $("#basePath").val();
			gotoNextPageWithParam12(basePath+"wechat/aigou/payment",pId,shippingAddId);
		}
	}
	
	function gotoNextPageWithParam12(action,pId,shippingAddId){
		form = $("<form></form>");
		form.attr('action',action);
		form.attr('method','post');
		input1 = $("<input type='hidden' name='pId' />");
		input1.attr('value',pId);
		input2 = $("<input type='hidden' name='shippingAddId' />");
		input2.attr('value',shippingAddId);
		form.append(input1);
		form.append(input2);
		form.appendTo("body");
		form.css('display','none');
		form.submit();
	}
	</script>
</head>
	<body>
		<input id="basePath" value="<%=basePath%>" style="display: none"/>
		<input id="pId" value="${type}" style="display: none"/>
		<input id="shippingAddressList" value="${shippingAddressList}" style="display: none"/>
		<c:choose>
		<c:when test="${empty shippingAddressList}">
			<ul class="mui-table-view" style="background-color:#efeff4;">									
			<li class="mui-table-view-cell" style="height: 205px;padding-top: 16px;border: 0;">
				<div style="height: 30px;width: 128px;margin: 0 auto;padding-top: 90px;font-size: 15px;">
					还没有填写过地址
				</div>
			</li>
		</ul>
		</c:when>
		<c:otherwise>
		<c:forEach items="${shippingAddressList}" var="shippingAddressList">
		<form class="mui-input-group" style="margin-bottom: 17px">
				<div class="mui-input-row1" onclick="lpSelectreciveInfo('${shippingAddressList.shippingAddId}','${type}')">
					<div style="height:15px;"></div>
					<span style="padding-left: 15px;">${shippingAddressList.shippingAddName}</span>
					<span style="padding-left: 15px;">${shippingAddressList.shippingAddPhone}</span>
					<br/>
					<span style="padding-left: 15px;line-height: 2.4em;">${shippingAddressList.shippingAddProvince}${shippingAddressList.shippingAddDetail}</span>								
				</div>
				<div class="mui-input-row" style="height: 44px;">
					<div style="width: 100px;height:100%;float: left;">
						<c:choose>
							<c:when test="${shippingAddressList.shippingAddIsMain == true}">
								<div onclick="typeChange('${type}','common','lp','${shippingAddressList.userId}','${shippingAddressList.shippingAddId}')">
									<img style="padding-top: 13px;max-width: 18px;margin-left: 20px;float: left;" src="<%=basePath%>wechat/images/morendizhi.png"/>
									<div style="font-size: 13px;height:40px;width:80px;padding-top: 13px;margin-left:45px;color:#fc5959;">
										默认地址
									</div>
								</div>
							</c:when>
							
							<c:otherwise>
								<div onclick="typeChange('${type}','default','lp','${shippingAddressList.userId}','${shippingAddressList.shippingAddId}')">
									<img style="padding-top: 13px;max-width: 18px;margin-left: 20px;float: left;" src="<%=basePath%>wechat/images/dizhi.jpg"/>
									<div style="font-size: 13px;height:40px;padding-top: 13px;margin-left:45px;">
										设为默认
									</div>
								</div>
							</c:otherwise>
						</c:choose>
						
					</div>
					
					<div onclick="shipAddressDelete('${type}','lp',${sessionScope.user.userId},${shippingAddressList.shippingAddId})"  style="width: 70px;height:100%;float: right;margin-right: 10px;">
						<img style="padding-top: 13px;max-width: 18px;margin-left: 20px;float: left;" src="<%=basePath%>wechat/images/delete.jpg"/>
						<div  style="font-size:13px;height:40px;padding-top: 13px;margin-left:42px;">
							删除
						</div>
						
					</div>
					<div onclick="shipAddressEdit('${type}','lp','${shippingAddressList.shippingAddId}')" style="width: 70px;height:100%;float:right;">
						<img style="padding-top: 13px;max-width: 18px;margin-left: 20px;float: left;" src="<%=basePath%>wechat/images/pen.jpg"/>
						<div style="font-size:13px;height:40px;padding-top:13px;margin-left:42px;">
							编辑
						</div>
					</div>
				</div>
		</form>
		</c:forEach>
		</c:otherwise>
		</c:choose>
		<div style="height:44px;"></div>
		<nav class="mui-bar mui-bar-tab" style="height:48px;">
			<form action="lpAddressAdd?type=${type}">
				<input name="type" value="${type}" style="display: none"/>
				<input id="btn" type="submit" value="添加新地址" style="height:48px;position:absolute;bottom:0px;border:0px;width:100%;background-color: #fc5959;color: white;"/>
			</form>
		</nav>
		
	</body>
	</body>
</html>