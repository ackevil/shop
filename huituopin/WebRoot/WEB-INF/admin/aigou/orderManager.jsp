<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>爱购订单管理</title>
<link href="<%=path%>/admin/skin/css/style.css" rel="stylesheet" />
<link href="<%=path%>/admin/skin/css/pagination.css" rel="stylesheet"
	type="text/css" />
<link href="<%=path%>/admin/skin/css/pagination.css" rel="stylesheet"
	type="text/css" />
<!-- 添加弹出对话框的样式定义css-->
<link href="<%=path%>/admin/skin/css/dialogDiv.css" rel="stylesheet"
	type="text/css" />
<!-- 添加弹出对话框的样式定义css-->
<script type="text/javascript" charset="utf-8"
	src="<%=path%>/admin/skin/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=path%>/admin/skin/js/layindex.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=path%>/admin/skin/js/laymain.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=path%>/admin/skin/js/common.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=path%>/admin/skin/js/showDialog.js"></script>


<script src="<%=path%>/admin/skin/js/bootstrap.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/admin/skin/js/bootstrap-datetimepicker.min.js"
	type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/admin/skin/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/admin/skin/css/bootstrap-datetimepicker.min.css" />
</head>
<script>
	$(function() {
		$("#datetimeStart").datetimepicker({
			format : "yyyy-mm-dd hh:ii:ss",
			autoclose : true,
			minView : "hour",
			maxView : "decade",
			todayBtn : true,
			pickerPosition : "bottom-left"
		}).on(
				"click",
				function(ev) {
					$("#datetimeStart").datetimepicker("setEndDate",
							$("#orderStopTime").val());
				});

		$("#datetimeStop").datetimepicker({
			format : "yyyy-mm-dd hh:ii:ss",
			autoclose : true,
			minView : "hour",
			maxView : "decade",
			todayBtn : true,
			pickerPosition : "bottom-left"
		}).on(
				"click",
				function(ev) {
					$("#datetimeStop").datetimepicker("setStartDate",
							$("#orderStartTime").val());
				});
				
		
	
	

	});
	function ShowDIV(thisObjID) {
		//var orderRoot=this.parent().parent().parent().parent().parent();
	
	 
	   
	
		//alert($(this).parent());
	
	
		$("#BgDiv").css({
			display : "block",
			height : $(document).height()
		});
		var html=$("#" + thisObjID).html();
		$("#DialogDiv").append(html);
		var yscroll = document.documentElement.scrollTop;
		$("#DialogDiv").css("top", "100px");
		$("#DialogDiv").css("display", "block");
		 // $(this).parent().find(".DialogDiv").css("top", "100px");
		 // this.parent().find(".DialogDiv").css("display", "block");
		document.documentElement.scrollTop = 0;
	}
	function closeDiv(thisObjID) {
		$("#BgDiv").css("display", "none");
		$("#DialogDiv").empty();
		$("#DialogDiv").css("display", "none");
	}
	
</script>
<style>
.col-sm-2 {
	width: 10%;
}

.main {
	margin: 30px;
	
}

.sell-button {
	margin-left: 80%;
}

.table_title {
	float: left;
}

.table_thead {
	width: 100%;
	height: 40px;
	background-color: #E9E9F5;
	padding: 10px;
	font-size: 15;
}

.single_goods {
	margin-top: 30px;
	width: 100%;
	border: 1px solid;
	border-color: #E9E9F5;
}

.content_title {
	width: 100%;
	height:60px;
	padding-left:15px;
	background-color: rgba(232, 236, 255, 0.53);
}

.content_content {
	padding-left:15px;
	padding-bottom:10px;
}

.btn_1 {
	background-color: #cae8fb;
	border: 1px solid #b1dbf9;
	width: 70px;
	height: 35px;
	color: #007aff;
}

.btn_2 {
	background-color: #ffffff;
	border: 1px solid #e7e7eb;
	width: 70px;
	height: 35px;
}

.title_left {
	width: 33%;
	display: inline;
	float: left;
	line-height: 25px;
}

.title_center {
	width: 33%;
	display: inline;
	float: left;
	line-height: 25px;
	text-align: center;
	padding-top: 10px;
}

.title_right {
	width: 34%;
	display: inline;
	float: right;
	line-height: 25px;
	text-align: center;
	padding-top: 10px;
}

.multi-radio a {
	height: 35px;
	margin-right: 10px;
	background-color: #ffffff;
}

.multi-radio a:visited {
	text-decoration: none;
}

.multi-radio a:link {
	text-decoration: none;
}
</style>


<body>
	<!--定义一个覆盖全屏的div,遮挡搜有的页面功能-->
	<div id="BgDiv"></div>
	<!--定义一个覆盖全屏的div,遮挡搜有的页面功能-->
	<!--遮罩层显示的DIV1-->
	<div id="DialogDiv" style="display:none">
		
	</div>
	<!--遮罩层显示的DIV2-->
	<jsp:include page="../commonPage/commonTop.jsp" />
	<jsp:include page="../commonPage/commonLeft.jsp"></jsp:include>

	<div class="main-container">
		<div id="floatHead" class="toolbar-wrap"
			style="background-color: rgb(244,245,249); margin-bottom: 20px;">
			<div class="container-flud">
				<form action="<%=path%>/admin/aigou/orderManager" method="post">
					<div class="row form-group">

						<div class="col-sm-1 form-control-static text-right">订单时间</div>
						<div class="col-sm-3">
							<div class="input-group date" id="datetimeStart">
								<input type="text" id="orderStartTime" name="orderStartTime" value="<fmt:formatDate value='${option.orderStartTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"
									class="form-control" /> 
									<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span> </span>
							</div>
						</div>
						<div class="col-sm-1 form-control-static" style="width:3%;">至</div>
						<div class="col-sm-3">
							<div class="input-group date" id="datetimeStop">
								<input type="text" id="orderStopTime" name="orderStopTime" value="<fmt:formatDate value='${option.orderStopTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"
									class="form-control" /> <span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span> </span>
							</div>
						</div>
					</div>
					<div class="row form-group">
						<div class="col-sm-1 form-control-static text-right">订单状态</div>
						<div class="col-sm-3">
							<select id="orderState" name="orderState" class="form-control">
								<option value="">所有</option>
								<option value="0" ${option.orderState==0?"selected":""}>待付款</option>
								<option value="1" ${option.orderState==1?"selected":""}>已付款</option>
								<option value="2" ${option.orderState==2?"selected":""}>已发货</option>
								<option value="3" ${option.orderState==3?"selected":""}>已收货</option>
								<option value="4" ${option.orderState==4?"selected":""}>已评价</option>
							</select>
						</div>
					</div>
					<div class="row form-group">
						<div class="col-sm-1 form-control-static text-right">付款方式</div>
						<div class="col-sm-3">
							<select id="payWay" name="payWay" class="form-control">
								<option value="0" ${option.payWay==0?"selected":""}>所有</option>
								<option value="1" ${option.payWay==1?"selected":""}>微信支付</option>
							</select>
						</div>
					</div>


					<div class="row form-group">
						<div class="col-sm-1 form-control-static text-right">关键字</div>
						<div class="col-sm-3" style="width:80%;">
							<input id="keyword" name="keyword" type="text" id="" value='${option.keyword!=null?option.keyword:""}'
								class="form-control" placeholder="关键字搜索" />
						</div>
					</div>
					<div class="row form-group">
						<div class="col-sm-1 col-sm-offset-1">
							<button class="btn btn-primary">筛选</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="main">
			<ul id="myTab" class="nav nav-tabs">
				
				<c:if test="${empty option }">
            		 <li class="active"><a href="#all" data-toggle="tab">全部</a></li>
            	</c:if>
            	<c:if test="${not empty option }">
            		<c:if test="${empty option.orderState}">
            		  <li class="active"><a href="#all" data-toggle="tab">全部</a></li>
            		</c:if>
            		<c:if test="${option.orderState==0 }">
            		  <li class="active"><a href="#all" data-toggle="tab">待付款</a></li>
            		</c:if>
            		<c:if test="${option.orderState==1 }">
            		  <li class="active"><a href="#all" data-toggle="tab">已付款</a></li>
            		</c:if>
            		<c:if test="${option.orderState==2 }">
            		  <li class="active"><a href="#all" data-toggle="tab">已发货</a></li>
            		</c:if>
            		<c:if test="${option.orderState==3 }">
            		  <li class="active"><a href="#all" data-toggle="tab">已收货</a></li>
            		</c:if>
            		<c:if test="${option.orderState==4 }">
            		  <li class="active"><a href="#all" data-toggle="tab">已评价</a></li>
            		</c:if>
            	</c:if>
			</ul>
			<div id="myTabContent" class="tab-content">
				<div class="tab-pane fade in active" id="all">
					<div class="totalCount">
						<span>共有${pages.maxRows}条记录</span>
					</div>
					<div class="content_table" style="width:100%; margin-top:20px;">
						<div class="table_thead">
							<div class="table_title" style="width:10%;">图片</div>
							<div class="table_title" style="width:20%;">产品名称</div>
							<div class="table_title" style="width:10%;">
								<span style="text-align: left;">单价</span>
							</div>
							<div class="table_title" style="width:10%;">
								<span style="text-align: center;">数量</span>
							</div>
							<div class="table_title" style="width:10%;">
								<span style="text-align: center;">总金额</span>
							</div>
							<div class="table_title" style="width:10%;">
								<span style="text-align: center;">买家</span>
							</div>
							<div class="table_title" style="width:10%;">
								<span style="text-align: center;">交易状态</span>
							</div>
							<div class="table_title" style="width:10%;">
								<span style="text-align: center;">使用积分</span>
							</div>
							<div class="table_title" style="width:10%;text-align:center;">
								<span style="text-align:center;">实收款（元）</span>
							</div>
						</div>
						<div style="margin-top:20px;">
							<c:if test="${empty list1 }">
								<span class="text text-info">没有查询的订单</span>
							</c:if>
							<c:if test="${not empty list1 }">
								<c:forEach items="${list1}" var="map">
									<div class="single_goods">
										<div class="content_title">
											<div class="title_left">
												<div>
													订单号：<span>${map.get('po').productOrderNum}</span>
												</div>
												<div>
													支付流水号：<span>${map.get('po').transactionId}</span>
												</div>
											</div>
											<div id="${map.get('po').productOrderNum}" style="display:none">
													<form action="/admin/aigou/releaseOrder" method="post">
													<h2>商品发货</h2>
													<div class="form">
														<div class="dialog_table">
															<table style="width:100%;">
																<tr style="background-color:#f4f5f9;">
																	<td style="width:20%">商品</td>
																	<td style="width:15%;text-align:center;">数量</td>
																	<td style="width:15%;text-align:center;">物流公司</td>
																	<td style="width:15%;text-align:center;">快递单号</td>
																</tr>
																<c:forEach items="${map.get('obejctList')}" var="object">
																<tr>
																	<td style="width:39%"><span class="font_style">
																			${object[3]} </span>
																	</td>
																	<td style="width:20%;text-align:center;">${object[4]}</td>
																	<td style="width:20%;text-align:center;"></td>
																	<td style="width:20%;text-align:center;"></td>
																</tr>
																</c:forEach>
															</table>
														</div>
														<div class="shouhuoAddress">
															收货地址:<span>${map.get('address').shippingAddProvince}${map.get('address').shippingAddDetail}${map.get('address').shippingAddName}</span>
														</div>
														<div style="margin-top:10px;">
																发货方式: <label style="font-weight:400;">
																<input name="IsNeedLogistics" type="radio" value="1" checked/>需要物流</label>
																 <label style="font-weight:400;"><input
																	name="IsNeedLogistics" type="radio" value="0" />不需要物流</label>
														</div>
														<div>
															<label style="font-weight:400;">物流公司:</label> 
															<select name="logisticsCompanyName">
																<option value="默认物流" selected="selected">选择物流公司</option>
																<option value="顺丰物流">顺丰物流公司</option>
																<option value="申通物流">申通物流公司</option>
																<option value="圆通物流">圆通物流公司</option>
															</select> <label style="font-weight:400;margin-left:20px;">快递单号:</label> 
															<input name="logisticsNum" class="logisticsNum"  type="text" />
														</div>
													</div>
													<div
														style="text-align:right;width:100%;height:50px;background-color:#f4f5f9;">
														<input class="dialogBtnCancle" type="button"
															onclick="closeDiv('${map.get('po').productOrderNum}')" value="取消" /> 
														<input class="dialogBtnOK" type="submit" value="确定" />
													</div>
													<input type="hidden" name="productOrderId" value="${map.get('po').productOrderId}"> 
												</form>
												</div>
												
												
											<div class="title_center">
												<div>
													成交时间：<span>${map.get('po').productOrderIntime}</span>
												</div>
												
											</div>
											<div class="title_right">
												<span>
												<c:if test="${map.get('po').productOrderState==0}">
													<input class="btn btn-primary" type="button" value="发货"
													onclick="ShowDIV('${map.get('po').productOrderNum}')" />
												</c:if>
												<c:if test="${map.get('po').productOrderState==1}">
													<input class="btn btn-primary" type="button" value="发货"
													onclick="ShowDIV('${map.get('po').productOrderNum}')" />
												</c:if>
												
												<a href="/admin/aigou/delOrder?id=${map.get('po').productOrderId}"><input class="btn btn-primary" type="button" value="删除"/></a>
												</span>
											</div>
											
										</div>
										<div class="content_content">
											<table style="width:100%;" cellspacing="1">
												
													<c:forEach items="${map.get('obejctList')}" var="object" varStatus="status">
													<tr>
														<td style="width:10%;"><img width="50px"
															height="50px" src="${object[2]}"></td>
														<td style="width:20%;"><span>${object[3]}</span></td>
														<td style="width:10%;text-align: left;"><span>${object[4]}</span>
														</td>
														<td style="width:10%;text-align: left;"><span>${object[1]}</span>
														</td>
														
														<c:if test="${status.count==1}" >
						                                <td style="width:10%;text-align: left;" rowspan="20"><span>共<span style="color:red">${ map.get('po').productOrderPPrice}</span>元</span>
														<td style="width:10%;text-align:left;" rowspan="20"><span>${map.get('orderUser').userWcNickname}</span>
														</td>
														<td style="width:10%;text-align:left;" rowspan="20" >
															<div>
																<c:if test="${map.get('po').productOrderState==0}">
											            		  待付款
											            		</c:if>
											            		<c:if test="${map.get('po').productOrderState==1 }">
											            		  已付款
											            		</c:if>
											            		<c:if test="${map.get('po').productOrderState==2 }">
											            		 已发货
											            		</c:if>
											            		<c:if test="${map.get('po').productOrderState==3 }">
											            		已收货
											            		</c:if>
											            		<c:if test="${map.get('po').productOrderState==4 }">
											            		已评价
											            		</c:if>
															</div></td>
														 <td style="width:10%;text-align: left;" rowspan="20"><span><span style="color:blue">${ map.get('po').productOrderCredictUsed}</span>积分</span>
			                                             <td style="width:10%;text-align: center;" rowspan="20" >
			                                                    <div>${map.get('po').productOrderAllCount}</div>
			                                             </td>
			                                             </c:if>
													</tr>
													</c:forEach>    
													<div  style="height: 10px"></div>
											</table>
											
										</div>
									</div>



								</c:forEach>


							</c:if>


						</div>
					</div>
					<jsp:include page="orderMangerPagination.jsp">
						<jsp:param name="url" value="/admin/aigou/goodsManger" />
						<jsp:param name="pages" value="${pages1}" />
					</jsp:include>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
