<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
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
<style type="text/css">
.col-sm-2 {
    width:10%;
}
.main{
    margin:30px;
}
.sell-button{
    margin-left:80%;
}

.table_title {
    float:left;
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
    height: auto;
    border: 1px solid;
    border-color: #E9E9F5;
}

.content_title {
    width: 100%;
    height: 50px;
    background-color: rgba(232, 236, 255, 0.53);
}

.content_content {
    padding: 15px;
}
.btn_1 {
background-color:#cae8fb;
border:1px solid #b1dbf9;
width:70px;
height:35px;
color:#007aff;
}
.btn_2{
background-color:#ffffff;
border:1px solid #e7e7eb;
width:70px;
height:35px;
}
.title_left{
width:25%;
display:inline;
float:left;
line-height:25px;
}
.title_center{
width:25%;
display:inline;
float:left;
line-height:25px;
text-align:center;
padding-top:10px;
}
.title_right{
width:34%;
display:inline;
float:right;
line-height:25px;
text-align:center;
padding-top:10px;
}
.multi-radio a{
	height:35px;
margin-right:10px;
background-color:#ffffff;
	
}
.multi-radio a:visited {
	text-decoration:none;
}
.multi-radio a:link {
	text-decoration:none;
}
















.body-head{
	width: 100%;
	height: 50px;
	line-height:50px;
	background-color: #F4F5F9;
	border: solid 1px #EDEDF0;
	padding-left: 15px;
}
.content{
	width: 100%;
	height: 40px;
	line-height:40px;
	border-left: solid 1px #EDEDF0;
	border-right: solid 1px #EDEDF0;
	border-bottom: solid 1px #EDEDF0;
	padding-left: 15px; 
}
.content-title{
	color: #A1A1A1;
	width: 80px;
	float: left;
}
.content-text{
	color:#212121;
	margin-left: 100px;
	float: left;
}
.getinfo-title{
	width: 100px;
	height: 100%;
	float: left;
	background-color:#F4F5F9;
	line-height: 40px;
	text-align: center;
	border: solid 1px #EDEDF0;
	cursor:pointer;
}
.donate-title{
	width: 150px;
	height: 100%;
	float: left;
	line-height: 40px;
	text-align: center;
	 border: 1px solid #007AFF;
	cursor:pointer;
}
.donate-text{
	display: none;
}
.getinfo-text{
	display: none;
}

.table_title{
	text-align: center;
}
.content-action{
	float: right;
	margin-right: 30px;
	cursor: pointer;
	
}
.content-action a{
	text-decoration:none;
}
select{
	overflow: hidden;
	 border:none;
	 color: #0D81FF;
	 background:none; 
	 border:none;
	 
}
</style>
<script type="text/javascript">
$(function(){
	$(".getinfo-title").click(function(){
		$(".getinfo-title").css("background-color","#EDEDF0");  //所有的都变暗
		$(this).css("background-color","#FFFFFF");  //自己的变白
		var index = $(this).attr("id");
		$(".getinfo-text").css("display","none"); //所有的都隐藏
		$(".getinfo-text-"+index).css("display","block"); //对应的显示
	});
	
	$(".donate-title").click(function(){
		$(".donate-title").css("background-color","#FFFFFF");  //所有的都变白
		$(this).css("background-color","#007AFF");  //自己的变蓝
		var index = $(this).attr("id");
		$(".donate-text").css("display","none"); //所有的都隐藏
		$(".donate-text-"+index).css("display","block"); //对应的显示
	});
	
});
function change(changeClass,saveClass){
	$(changeClass).css("display","none");
	$(saveClass).css("display","inline");
}
function back(changeClass,saveClass){
	$(changeClass).css("display","inline");
	$(saveClass).css("display","none");
}
function save(changeClass,saveClass,changeId,saveId){
	$(saveClass).css("display","none");
	$(changeClass).css("display","inline");
	var userId = $("#userId").val(); //用户Id
	var path = $("#path").val(); //请求路径 
	var saveText = null;
	if(saveId == "#saveAddress"){ 
		saveText = $("#province").val()+$("#city").val()+$("#area").val();
	}else{
		saveText = $(saveId).val();//修改的数据
	}
	$.ajax({
		type:"POST",
		url:path+"/admin/lpUpdataInfo",
		data:{
			type:changeClass,
			param:saveText,
			userId:userId
		},
		success:function(msg){
			if(msg){//返回是true  说明号码可用 ，否则号码已经被注册
				alert("修改成功！");
				$(changeId).text(saveText);
				$(saveClass).css("display","none");
				$(changeClass).css("display","inline");
			}else{
				alert("修改失败！");	
				$(saveClass).css("display","none");
				$(changeClass).css("display","inline");		
			}
		}
	});
}

</script>



</head>
<body class="indexbody">
	<input type="hidden" id="userId" value="${user.userId }"/>
	<input type="hidden" id="path" value="<%=path %>"/>
	<form action="<%=path%>/admin/ppAddUserAction" method="post" onsubmit="return check()">
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
					<a href="<%=path%>/admin/getAllLP" class="home"><i></i><span>会员</span></a>
					<i class="arrow"></i><span>${user.userWcNickname}</span>
				</div>
				<div style="width: 100%; margin-top: 20px">
					<div class="body-head">
						<span>基本信息</span>
					</div>
					
					<div class="content">
						<div class="content-title">
							<span>姓名</span>
						</div>
						<div class="content-text">
							<span id="changeName" class="changeName">${user.userWcNickname }</span>
							<input id="saveName" class="saveName" style="line-height: normal;border: 
							1px solid #1A81C9;display: none" value="${user.userWcNickname}"/>
						</div>
						<div class="content-action">
							<a class="changeName" onclick="change('.changeName','.saveName')">修改</a>
							<a class="saveName" style="display: none" onclick="save('.changeName','.saveName','#changeName','#saveName')">保存</a>
							<a class="saveName" style="display: none" onclick="back('.changeName','.saveName')">返回</a>
						</div>	
					</div>
					
					<div class="content"  style=" height: 50px">
						<div class="content-title">
							<span>头像</span>
						</div>
						<div class="content-text">
							<img style="width: 40px;height: 40px" src="<%=path%>/${user.userWcAvatar}">
						</div>	
					</div>
					
					
					<div class="content">
						<div class="content-title">
							<span>性别</span>
						</div>
						<div class="content-text">
							<span class="changeGender" id="changeGender">
								<c:choose>
									<c:when test="${user.userWcGender==true}">男</c:when>
									<c:otherwise>女</c:otherwise>
								</c:choose>
							</span>
							<select class="saveGender" id="saveGender" style="display: none">
								<option value="男">男</option>
								<option value="女">女</option>
							</select>
						</div>
						<div class="content-action">
							<a class="changeGender" onclick="change('.changeGender','.saveGender')">修改</a>
							<a class="saveGender" style="display: none" onclick="save('.changeGender','.saveGender','#changeGender','#saveGender')">保存</a>
							<a class="saveGender" style="display: none" onclick="back('.changeGender','.saveGender')">返回</a>
						</div>	
					</div>
					
					
					<div class="content">
						<div class="content-title">
							<span>手机号码</span>
						</div>
						<div class="content-text">
							<span id="changePhone" class="changePhone">${user.userPhone }</span>
							<input id="savePhone" class="savePhone" style="line-height: normal;
							border: 1px solid #1A81C9;display: none" value="${user.userPhone}"/>
						</div>
						<div class="content-action">
							<a class="changePhone" onclick="change('.changePhone','.savePhone')">修改</a>
							<a class="savePhone" style="display: none" onclick="save('.changePhone','.savePhone','#changePhone','#savePhone')">保存</a>
							<a class="savePhone" style="display: none" onclick="back('.changePhone','.savePhone')">返回</a>
						</div>
					</div>
					
					
					<div class="content">
						<div class="content-title">
							<span>会员积分</span>
						</div>
						<div class="content-text">
							<span>${lovingPeople.loveCredicts}</span>
						</div>	
					</div>
					
					<div class="content">
						<div class="content-title">
							<span>所在省市区</span>
						</div>
						<div class="content-text">
							<span class="changeAddress" id="changeAddress">${lovingPeople.loveAddress }</span>
							<div style="display: none;" class="saveAddress" id="saveAddress">
								<select id="province" name="province"></select>
								<select name="city" id="city"></select>
								<select name="area" id="area"></select>
							</div>
							<script type="text/javascript">
								 addressInit('province', 'city', 'area', '湖南', '长沙市', '岳麓区');
								 addressInit('Select1', 'Select2', 'Select3');
							</script>
						</div>
						<div class="content-action">
							<a class="changeAddress" onclick="change('.changeAddress','.saveAddress')">修改</a>
							<a class="saveAddress" style="display: none" onclick="save('.changeAddress','.saveAddress','#changeAddress','#saveAddress')">保存</a>
							<a class="saveAddress" style="display: none" onclick="back('.changeAddress','.saveAddress')">返回</a>
						</div>		
					</div>
					
					<div class="content">
						<div class="content-title">
							<span>详细地址</span>
						</div>
						<div class="content-text">
							<span id="changeDetailAddress" class="changeDetailAddress">${lovingPeople.loveDetailAddress }</span>
							<input id="saveDetailAddress" class="saveDetailAddress" style="line-height: normal;border: 1px solid #1A81C9;display: none" value="${poorPeople.poorDetailAddress}"/>
						</div>
						<div class="content-action">
							<a class="changeDetailAddress" onclick="change('.changeDetailAddress','.saveDetailAddress')">修改</a>
							<a class="saveDetailAddress" style="display: none" onclick="save('.changeDetailAddress','.saveDetailAddress','#changeDetailAddress','#saveDetailAddress')">保存</a>
							<a class="saveDetailAddress" style="display: none" onclick="back('.changeDetailAddress','.saveDetailAddress')">返回</a>
						</div>	
					</div>
				</div>				
				
				<div style="width: 100%; height: 40px;margin-top: 15px" >
					<div class="getinfo-title" id = "1"style="background-color: #FFFFFF;">捐助记录</div>
					<div class="getinfo-title" id = "2">消费记录</div>
					<div class="getinfo-title" id = "3">订单</div>
					<div class="getinfo-title" id = "4">收藏的商品</div>
					<div class="getinfo-title" id = "5">兑换记录</div>
					<div class="getinfo-title" id = "6">评价</div>
				</div>
				
				
				<!-- 捐助记录 -->
				<div  class="getinfo-text getinfo-text-1" style="margin-top: 10px;display: block;">
				
						<div style="width: 100%; height: 40px;margin-top: 15px;" >
							<div class="donate-title" id = "1"style="background-color: #007AFF;">捐助的物品</div>
							<div class="donate-title" id = "2">捐助的金额</div>
						</div>
						
						<!-- 捐助的物品 -->
						<div style="margin-top: 10px;display: block;" class="donate-text donate-text-1">
							<div class="tab-pane fade in active" id="selling">
			                  <div class="totalCount">
			                      <span>共有${donateNum}条记录</span>
			                  </div>
			                    <div class="content_table table-hover" style="width:100%; margin-top:20px;">
			                         <div class="table_thead">
			                                <div class="table_title" style="width:15%;text-align:left;">图片</div>
			                                <div class="table_title"  style="width:20%;text-align:left;">产品名称</div >
			                                <div class="table_title" style="width:15%;text-align:left;" >类别</div>
			                                <div class="table_title"  style="width:20%;text-align:left;">捐赠时间</div>
			                                <div class="table_title"  style="width:10%;text-align:left;">领取人</div>
			                                <div class="table_title"  style="width:10%;text-align:left;">状态</div>
			                                <div class="table_title"  style="width:10%;text-align:left;">操作</div>
			                          </div>
			                                <c:forEach items="${clothInfoList}" var="clothInfo">
			                                   <div class="tab-content">
			                                        <table style="width:100%;">
			                                            <tr>
			                                                <td style="width:15%;">
			                                                    <img width="50px" height="50px" src="<%=path%>${clothInfo[2]}" >
			                                                </td>
			                                                <td style="width:20%;">
			                                                    <span>${clothInfo[3]}</span><br>
			                                                </td>
			                                                <td style="width:15%;">
			                                                    <span>${clothInfo[4]}</span>
			                                                </td>
			                                                <td style="width:20%;">
			                                                    <span>${clothInfo[1]}</span>
			                                                </td>
			                                                <td style="width:10%;">
			                                                    <span>${clothInfo[7]}</span>
			                                                </td>
			                                                 <td style="width:10%;">
			                                                    <span>
			                                                    <c:if test="${clothInfo[5]==0}">
			                                                   	 未领取
			                                                    </c:if>
			                                                    <c:if test="${clothInfo[5]==1}">
			                                                   	 待邮寄
			                                                    </c:if>
			                                                    <c:if test="${clothInfo[5]==2}">
			                                                   	 待收货
			                                                    </c:if>
			                                                    <c:if test="${clothInfo[5]==3}">
			                                                   	 待评价
			                                                    </c:if>
			                                                    <c:if test="${clothInfo[5]==4}">
			                                                   	 已完成
			                                                    </c:if>
			                                                    </span>
			                                                </td>
			                                                <td style="width:10%;">
			                                                    <span><button type="button" class="btn btn-primary" onclick="stick(${clothInfo[0]})">置顶</button></button></span>
			                                                </td>
			                                            </tr>
			                                        </table>      
			                                   </div>
			                               </c:forEach>
			                 		 </div>
		              		 </div>
						</div>
						<!-- end -->
						<!-- 捐助的金额 -->
							<div style="margin-top: 10px;" class="donate-text donate-text-2">
							<div class="tab-pane fade in active" id="selling">
			                  <div class="totalCount">
			                      <span>共有${donatemoneyitems }条记录</span>
			                  </div>
			                    <div class="content_table table-hover" style="width:100%; margin-top:20px;">
			                         <div class="table_thead">
			                                <div class="table_title" style="width:70px;">图片</div>
			                                <div class="table_title"  style="width:200px;">项目名称</div >
			                                <div class="table_title" style="width:200px;">捐赠金额（元）</div>
			                                <div class="table_title"  style="width:150px;">捐赠时间</div>
			                                <div class="table_title"  style="width:150px;">获助者</div>
			                            <!-- end     <div class="table_title"  style="width:150px;">操作</div>-->
			                          </div>
			                          <c:forEach items="${maplist1}" var="map">
			                           <div class="tab-content">
			                          	 	<div class="table_title" style="width:70px;">
			                          	 		<img width="50px" height="50px" src="${map.get('activity').activityMainPic}" >
			                          	 	</div>
			                                <div class="table_title"  style="width:200px; line-height: 50px">
			                                	<span>${map.get('activity').activityName}</span>
			                                </div >
			                                <div class="table_title" style="width:200px; line-height: 50px" >
			                                	<span style="text-align: left;">${map.get('activitySupport').actSupMoney} 元</span>
			                                </div>
			                                <div class="table_title" style="width:150px;line-height: 50px">
			                                	<span style="text-align: center;">${map.get('activitySupport').actSupTime}</span>
			                                </div>
			                                <!-- 
			                                <div class="table_title"  style="width:20%;line-height: 50px">
			                                	<span style="text-align: center;"><a href="<%=path%>/admin/ppDetailInfo?userId=${user.userId}" class="btn btn-primary">查看</a></span>
			                                </div>
			                                 -->
			                          </div>
			                          </c:forEach>
			                          <c:forEach items="${maplist2}" var="map">
			                           <div class="tab-content">
			                          	 	<div class="table_title" style="width:70px;">
			                          	 		<img width="50px" height="50px" src="${map.get('dsze').dszeMainPic}" >
			                          	 	</div>
			                                <div class="table_title"  style="width:200px; line-height: 50px">
			                                	<span>${map.get('dsze').dszeName}</span>
			                                </div >
			                                <div class="table_title" style="width:200px; line-height: 50px" >
			                                	<span style="text-align: left;">${map.get('dszeSupport').dszeSupMoney} 元</span>
			                                </div>
			                                <div class="table_title" style="width:150px;line-height: 50px">
			                                	<span style="text-align: center;">${map.get('dszeSupport').dszeSupTime}</span>
			                                </div>
			                                <!-- 
			                                <div class="table_title"  style="width:20%;line-height: 50px">
			                                	<span style="text-align: center;"><a href="<%=path%>/admin/ppDetailInfo?userId=${user.userId}" class="btn btn-primary">查看</a></span>
			                                </div>
			                                 -->
			                          </div>
			                          </c:forEach>
			                         
			                 		</div>
		              		 </div>
						</div>
						<!-- end -->
				</div>
				
				
				
				
				<!-- 消费记录 -->
				<div style="margin-top: 10px" class="getinfo-text getinfo-text-2">
					<div class="tab-pane fade in active" id="selling">
	                  <div class="totalCount">
	                    <span>共有<span style="color:#147DFB">${payListNum}</span>条记录，有<span style="color:#147DFB">${payListNum}</span>个订单，订单总额<span style="color:red">${count}</span>元</span>
	                  </div>
	                    <div class="content_table table-hover" style="width:1200px; margin-top:20px;">
	                         <div class="table_thead">
	                                <div class="table_title" style="width:20%;">支付时间</div>
	                                <div class="table_title" style="width:20%;">订单号/支付流水号</div>
	                                <div class="table_title" style="width:15%;">共计（元）</div>
	                                <div class="table_title"  style="width:15%;">种类数量</div>
	                                <div class="table_title"  style="width:20%;">状态</div>
	                                <!-- 
	                                <div class="table_title"  style="width:10%;">操作</div>
	                                 -->
	                          </div>
	                          
	                          <c:forEach items="${payList}" var="poInfo">
	                           <div class="tab-content" style="width:100%;height:50px">
	                          	 	<div class="table_title" style="width:20%;">
	                          	 		<span style="text-align: center;">${poInfo.productOrderIntime}</span>
	                          	 	</div>
	                                <div class="table_title" style="width:20%" >
	                                	<span style="text-align: center;">${poInfo.productOrderNum}</span>
	                                </div>
	                                <div class="table_title" style="width:15%" >
	                                	<span style="text-align: center;">${poInfo.productOrderAllCount}</span>
	                                </div>
	                                <div class="table_title" style="width:15%" >
	                                	<span style="text-align: center;">${poInfo.productsNum}</span>
	                                </div>
	                                <div class="table_title" style="width:20%">
	                                	<span style="text-align: center;">
											<c:if test="${poInfo.productOrderState==1}">
												等待卖家发货
											</c:if>
											<c:if test="${poInfo.productOrderState==2}">
												等待买家收货
											</c:if>
											<c:if test="${poInfo.productOrderState==3 || poInfo.productOrderState==4 }">
												买家已收货
											</c:if>
	                                	</span>
	                                </div>
	                                <!-- 
	                                <div class="table_title"  style="width:10%;line-height: 50px">
	                                	<span style="text-align: center;"><a href="" class="btn btn-primary">查看</a></span>
	                                </div>
	                                 -->
	                          </div>
	                          </c:forEach>
	                 		 </div>
              		 </div>
				</div>
				
				<!-- 订单 -->
				<div style="margin-top: 10px" class="getinfo-text getinfo-text-3">
					<div class="tab-pane fade in active" id="selling">
	                  <div class="totalCount">
	                    <span>我的订单共有${poListNum}条记录</span>
	                  </div>
	                    <div class="content_table table-hover" style="width:100%; margin-top:20px;">
	                         <div class="table_thead">
	                                <div class="table_title" style="width:25%;">图片</div>
	                                <div class="table_title"  style="width:25%;">产品名称</div >
	                                <div class="table_title" style="width:25%;" >单价</div>
	                                <div class="table_title" style="width:25%;">数量</div>
	                          </div>
	                          <div style="margin-top:30px;">
                                <c:forEach items="${poList}" var="poInfo">
                                <div class="single_goods">
                                   <div class="content_title">
                                     <div class="title_center">
                                        <div>订单号：<span>${poInfo.po.productOrderNum }</span></div>
                                     </div>
                                     <div class="title_center">
                                        <div>成交时间：<span>${poInfo.po.productOrderIntime }</span></div>
                                     </div>
                                     <div class="title_center">
                                        <div>交易状态：<span>
                                        	<c:if test="${poInfo.po.productOrderState==0}">
												等待买家付款
											</c:if>
											<c:if test="${poInfo.po.productOrderState==1}">
												等待卖家发货
											</c:if>
											<c:if test="${poInfo.po.productOrderState==2}">
												等待买家收货
											</c:if>
											<c:if test="${poInfo.po.productOrderState==3 || poInfo.po.productOrderState==4 }">
												买家已收货
											</c:if>
                                        </span></div>
                                     </div>
                                     <div class="title_center">
                                        <div>实收款：<span>${poInfo.po.productOrderAllCount}</span></div>
                                     </div>
                                   </div>
                                   <div class="content_content">
                                        <table style="width:100%;">
                                        	<c:forEach items="${poInfo.podInfo}" var="podInfo">
	                                            <tr>
	                                                <td style="width:25%;text-align: center;">
	                                                    <img width="50px" height="50px" src="<%=path %>${podInfo[1]}" >
	                                                </td>
	                                                <td style="width:25%;text-align: center;">
	                                                    <span>${podInfo[0]}<br>${podInfo[5]}</span>
	                                                </td>
	                                                <td style="width:25%;text-align: center;">
	                                                    <span>${podInfo[2]}</span>
	                                                </td>
	                                                <td style="width:25%;text-align: center;">
	                                                    <span>${podInfo[3]}</span>
	                                                </td>
	                                            </tr>
                                            </c:forEach>
                                        </table>      
                                   </div>
                                </div>
                                </c:forEach>
                         </div>
	                 		 </div>
              		 </div>
				</div>
				<!-- end -->
				
				<!-- 收藏的商品 -->
				<div style="margin-top: 10px" class="getinfo-text getinfo-text-4">
					<div class="tab-pane fade in active" id="selling">
	                  <div class="totalCount">
	                    <span>我的领取 共有${fn:length(collectionList)}条记录</span>
	                  </div>
	                    <div class="content_table table-hover" style="width:100%; margin-top:20px;">
	                         <div class="table_thead">
	                                <div class="table_title" style="width:20%;">图片</div>
	                                <div class="table_title"  style="width:20%;">产品名称</div >
	                                <div class="table_title" style="width:20%;">价格</div>
	                                <div class="table_title"  style="width:20%;">收藏时间</div>
	                                <!--  
	                                <div class="table_title"  style="width:20%;">操作</div>
	                                -->
	                          </div>
	                          
	                          <c:forEach items="${collectionList}" var="collection">
	                           <div class="tab-content">
	                          	 	<div class="table_title" style="width:20%;">
	                          	 		<img width="50px" height="50px" src="${collection[2]}" >
	                          	 	</div>
	                                <div class="table_title"  style="width:20%; line-height: 50px">
	                                	<span>${collection[3]}</span>
	                                </div >
	                                <div class="table_title" style="width:20%; line-height: 50px" >
	                                	<span style="text-align: left;">${collection[4]}</span>
	                                </div>
	                                <div class="table_title" style="width:20%;line-height: 50px">
	                                	<span style="text-align: center;">${collection[5]}</span>
	                                </div>
	                                <!-- 
	                                <div class="table_title"  style="width:20%;line-height: 50px">
	                                	<span style="text-align: center;"><a href="<%=path%>/admin/ppDetailInfo?collectionId=${collection[0]}" class="btn btn-primary">查看</a></span>
	                                </div>
	                                 -->
	                          </div>
	                          </c:forEach>
	                 		 </div>
              		 </div>
				</div>
				<!-- end -->
				
				<!-- 兑换记录 -->
				<div style="margin-top: 10px" class="getinfo-text getinfo-text-5">
					<div class="tab-pane fade in active" id="selling">
	                  <div class="totalCount">
	                    <span>共有${fn:length(CredictlistOut)}条记录</span>
	                  </div>
	                    <div class="content_table table-hover" style="width:100%; margin-top:20px;">
	                         <div class="table_thead">
	                         		<div class="table_title" style="width:20%;">图片</div>
	                                <div class="table_title"  style="width:20%;">产品名称</div >
	                                <div class="table_title" style="width:20%;" >数量</div>
	                                <div class="table_title"  style="width:20%;">兑换积分</div>
	                                <div class="table_title"  style="width:20%;">兑换时间</div>
	                          </div>
	                          
	                          <c:forEach items="${CredictlistOut}" var="CredictlistOut">
	                           <div class="tab-content">
	                          	 	<div class="table_title" style="width:20%;">
	                          	 		<img width="50px" height="50px" src="<%=path%>${CredictlistOut.credictsImg}" >
	                          	 	</div>
	                                <div class="table_title"  style="width:20%; line-height: 50px">
	                                	<span>${CredictlistOut.credictsName}</span>
	                                </div >
	                                <div class="table_title" style="width:20%; line-height: 50px" >
	                                	<span style="text-align: left;">1</span>
	                                </div>
	                                <div class="table_title" style="width:20%;line-height: 50px">
	                                	<span style="text-align: center;">${CredictlistOut.credictsChanges }</span>
	                                </div>
	                                <div class="table_title"  style="width:20%;line-height: 50px">
	                                	<span style="text-align: center;">${CredictlistOut.credictsDate }</span>
	                                </div>
	                          </div>
	                          </c:forEach>
	                 		 </div>
              		 </div>
				</div>
				
				<!-- end -->
				
				<!-- 评价 -->
				<div style="margin-top: 10px" class="getinfo-text getinfo-text-6">
					<div class="tab-pane fade in active" id="selling">
	                  <div class="totalCount">
	                    <span>我的评价共有${pcListNum}条记录</span>
	                  </div>
	                    <div class="content_table table-hover" style="width:100%; margin-top:20px;">
	                         <div class="table_thead">
	                                <div class="table_title" style="width:20%;">评价时间</div>
	                                <div class="table_title"  style="width:30%;">评价内容</div >
	                                <div class="table_title" style="width:30%;" >产品名称</div>
	                                <!-- 
	                                <div class="table_title"  style="width:20%;">操作</div>
	                                 -->
	                          </div>
	                          
	                          <c:forEach items="${pclist}" var="pc">
	                           <div class="tab-content">
	                                <div class="table_title"  style="width:20%; line-height: 50px">
	                                	<span style="text-align: center;">${pc.pcInfo[5]}</span>
	                                </div >
	                                <div class="table_title" style="width:30%; line-height: 50px" >
	                                	<span style="text-align: center;">${pc.pcInfo[4]}</span>
	                                </div>
	                                <div class="table_title" style="width:30%;line-height: 50px">
	                                	<span style="text-align: center;">${pc.pcInfo[0]}<br>${pc.pcInfo[6]}元</span>
	                                </div>
	                                <!-- 
	                                <div class="table_title"  style="width:20%;line-height: 50px">
	                                	<span style="text-align: center;"><a href="" class="btn btn-primary">查看</a></span>
	                                </div>
	                                 -->
	                          </div>
	                          </c:forEach>
	                 		 </div>
              		 </div>
				</div>
				
				<!-- end -->
				
			</div>
			<script type="text/javascript">
            	
            	function stick(clothId){
            		$.ajax({
                        data: {"clothId" : clothId},
                        type:"POST",
                        url:"<%=basePath%>admin/yjya/stick_do",
                        error:function(data){
                            debugger;
                            alert("出錯了！");
                        },
                        success:function(data){
                            if (data == "true") {
                                 window.location.href="<%=basePath%>admin/yjya/commodityManager";
                            }
                            else {
                                 alert("出錯了！");
                            }
                        }
                    });
            	
            	}
            	
            </script>
		</div>
	</form>
</body>
</html>
