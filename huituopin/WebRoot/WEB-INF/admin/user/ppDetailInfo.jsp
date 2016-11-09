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
    height: 130px;
    border: 1px solid;
    border-color: #E9E9F5;
}

.content_title {
    width: 100%;
    height: 43%;
    background-color: rgba(232, 236, 255, 0.53);
}

.content_content {
    padding: 15px;
}
.btn_1{
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
width:33%;
display:inline;
float:left;
line-height:25px;
}
.title_center{
width:33%;
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
	width: 150px;
	height: 100%;
	float: left;
	background-color:#F4F5F9;
	line-height: 40px;
	text-align: center;
	border: solid 1px #EDEDF0;
	cursor:pointer;
}
.myComment{
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
	$(".get").click(function(){
		$(".getinfo-title").css("background-color","#EDEDF0");
		$(this).css("background-color","#FFFFFF");
		$(".getinfo-text").css("display","none");
		$(".myGet").css("display","block");
	});
	
	$(".comment").click(function(){
		$(".getinfo-title").css("background-color","#EDEDF0");
		$(this).css("background-color","#FFFFFF");
		$(".getinfo-text").css("display","none");
		$(".myComment").css("display","block");
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
		url:path+"/admin/ppUpdataInfo",
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
	<input type="hidden" id="path" value="<%=path%>"/>
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
					<a href="<%=path%>/admin/getAllPP" class="home"><i></i><span>会员</span></a>
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
							<input id="saveName" class="saveName" style="line-height: normal;border: 1px solid #1A81C9;display: none" value="${user.userWcNickname}"/>
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
							<img style="width: 40px;height: 40px" src="<%=path%>/${user.userWcAvatar }">
						</div>
						<div class="content-action">
							<a onclick="change()">修改</a>
							<a onclick="change()" style="display: none">保存</a>
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
							<input id="savePhone" class="savePhone" style="line-height: normal;border: 1px solid #1A81C9;display: none" value="${user.userPhone}"/>
						</div>
						<div class="content-action">
							<a class="changePhone" onclick="change('.changePhone','.savePhone')">修改</a>
							<a class="savePhone" style="display: none" onclick="save('.changePhone','.savePhone','#changePhone','#savePhone')">保存</a>
							<a class="savePhone" style="display: none" onclick="back('.changePhone','.savePhone')">返回</a>
						</div>
					</div>
					
					
					<div class="content">
						<div class="content-title">
							<span>审核</span>
						</div>
						<div class="content-text">
							<span class="changeState" id="changeState">
								<c:if test="${poorPeople.poorState == 1}">待审核</c:if>
								<c:if test="${poorPeople.poorState == 2}">已通过</c:if>
								<c:if test="${poorPeople.poorState == 3}">不通过</c:if>
							</span>
							<select class="saveState" id="saveState" style="display: none">
								<option value="待审核">待审核</option>
								<option value="已通过">已通过</option>
								<option value="不通过">不通过</option>
							</select>
						</div>
						<div class="content-action">
							<a class="changeState" onclick="change('.changeState','.saveState')">修改</a>
							<a class="saveState" style="display: none" onclick="save('.changeState','.saveState','#changeState','#saveState')">保存</a>
							<a class="saveState" style="display: none" onclick="back('.changeState','.saveState')">返回</a>
						</div>	
					</div>
					
					<div class="content">
						<div class="content-title">
							<span>所在省市区</span>
						</div>
						<div class="content-text">
							<span class="changeAddress" id="changeAddress">${poorPeople.poorAddress }</span>
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
							<span id="changeDetailAddress" class="changeDetailAddress">${poorPeople.poorDetailAddress }</span>
							<input id="saveDetailAddress" class="saveDetailAddress" style="line-height: normal;border: 1px solid #1A81C9;display: none" value="${poorPeople.poorDetailAddress}"/>
						</div>
						<div class="content-action">
							<a class="changeDetailAddress" onclick="change('.changeDetailAddress','.saveDetailAddress')">修改</a>
							<a class="saveDetailAddress" style="display: none" onclick="save('.changeDetailAddress','.saveDetailAddress','#changeDetailAddress','#saveDetailAddress')">保存</a>
							<a class="saveDetailAddress" style="display: none" onclick="back('.changeDetailAddress','.saveDetailAddress')">返回</a>
						</div>	
					</div>
				</div>				
				<input type="hidden" id="userId" value="${user.userId }"/>
				<div style="width: 100%; height: 40px;margin-top: 15px" >
					<div class="getinfo-title get" id="1"  style="background-color: #FFFFFF;">领取记录</div>
					<div class="getinfo-title comment" id="2">评价</div>
				</div>
				<!-- 我的领取 -->
				<div style="margin-top: 10px" class="myGet getinfo-text">
					<div class="tab-pane fade in active" id="selling">
	                  <div class="totalCount">
	                    <span>我的领取 共有${getNum}条记录</span>
	                  </div>
	                    <div class="content_table table-hover" style="width:100%; margin-top:20px;">
	                         <div class="table_thead">
	                                <div class="table_title" style="width:10%;">图片</div>
	                                <div class="table_title"  style="width:15%;">产品名称</div >
	                                <div class="table_title" style="width:15%;" >类别</div>
	                                <div class="table_title" style="width:20%;">领取时间</div>
	                                <div class="table_title"  style="width:10%;">捐赠者</div>
	                                <div class="table_title"  style="width:10%;">状态</div>
	                                <!-- 
	                                <div class="table_title"  style="width:15%;">操作</div>
	                                 -->
	                          </div>
	                          
	                          <c:forEach items="${clothInfoList}" var="clothInfo">
	                           <div class="tab-content">
	                          	 	<div class="table_title" style="width:10%;line-height: 50px">
	                          	 		<img width="50px" height="50px" src="<%=path%>${clothInfo[2]}" >
	                          	 	</div>
	                                <div class="table_title"  style="width:15%; line-height: 50px">
	                                	<span>${clothInfo[3]}</span>
	                                </div >
	                                <div class="table_title" style="width:15%; line-height: 50px" >
	                                	<span style="text-align: left;">${clothInfo[4]}</span>
	                                </div>
	                                <div class="table_title" style="width:20%; line-height: 50px" >
	                                	<span style="text-align: left;">${clothInfo[8]}</span>
	                                </div>
	                                <div class="table_title" style="width:10%; line-height: 50px" >
	                                	<span style="text-align: left;">${clothInfo[6]}</span>
	                                </div>
	                                <div class="table_title" style="width:10%;line-height: 50px">
	                                	<span style="text-align: center;">
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
	                                </div>
	                                <!-- 
	                                <div class="table_title"  style="width:15%;line-height: 50px">
	                                	<span style="text-align: center;"><a href="<%=path%>/admin/ppDetailInfo?userId=${user.userId}" class="btn btn-primary">查看</a></span>
	                                </div>
	                                 -->
	                          </div>
	                          </c:forEach>
	                 		 </div>
              		 </div>
				</div>
	
				<!-- 我的评价 -->
				<div style="margin-top: 10px" class="myComment getinfo-text">
					<div class="tab-pane fade in active" id="selling">
	                  <div class="totalCount">
	                    <span>我的评价共有${commentNum}条记录</span>
	                  </div>
	                  <div class="content_table table-hover" style="width:1200px; margin-top:20px;">
	                         <div class="table_thead">
	                                <div class="table_title" style="width:10%"><input type="checkbox" onclick="checkAll(this)"value="全选"/></div>
	                                <div class="table_title"  style="width:15%;">评价时间</div >
	                                <div class="table_title" style="width:25%;" >评价内容</div>
	                                <div class="table_title" style="width:20%;">评价对象</div>
	                                <div class="table_title"  style="width:10%;">审核状态</div>
	                                <!-- -
	                                <div class="table_title"  style="width:20%;">操作</div>
	                                 -->
	                          </div>
	                          
	                          <c:forEach items="${commentList}" var="comment">
	                           <div class="tab-content">
	                           		<div class="table_title" style="width:10%;">
	                          	 		<span class="checkall" style="vertical-align: middle;"><input type="checkbox" name="checkbox" value="${comment[4]}" /></span>
	                          	 	</div>
	                          	 	<div class="table_title" style="width:15%;line-height: 50px">
	                                	<span>${comment[0]}</span>
	                          	 	</div>
	                                <div class="table_title"  style="width:25%; line-height: 50px">
	                                	<span>${comment[1]}</span>
	                                </div >
	                                <div class="table_title" style="width:20%; line-height: 50px" >
	                                	<span style="text-align: left;">${comment[3]}</span>
	                                </div>
	                                <div class="table_title" style="width:10%;line-height: 50px">
	                                	<span style="text-align: center;">
	                                	<c:if test="${comment[5]==0}">
	                                		未审核
	                                	</c:if>
	                                	<c:if test="${comment[5]==1}">
	                                		审核通过
	                                	</c:if>
	                                	<c:if test="${comment[5]==2}">
	                                		审核不通过
	                                	</c:if>
	                                	</span>
	                                </div>
	                                <!-- 
	                                <div class="table_title"  style="width:20%;line-height: 50px">
	                                	<span style="text-align: center;"><a href="" class="btn btn-primary">查看</a></span>
	                                </div>
	                                 -->
	                          </div>
	                          </c:forEach>
	                 		</div>
	                 		<input type="button" class="btn btn-primary" value="通过" onclick="pass()"/>
	                 		<input type="button" class="btn btn-primary" value="不通过" onclick="nopass()"/>
              		  </div>
              		  <script type="text/javascript">
              		  	//全选取消按钮函数
							function checkAll(chkobj) {
							    if ($(chkobj).val() == "全选") {
							        $(chkobj).val("取消");
							        $(".checkall input:enabled").prop("checked", true);
							    } else {
							        $(chkobj).val("全选");
							        $(".checkall input:enabled").prop("checked", false);
							    }
							}
							function pass(){
								var str="";
								$(":checkbox:checked").each(function(){
									str+=this.value+";";
								});
								$.ajax({
								       data: {
								       	"commentIds":str,
								       	},
								       type:"POST",
								       url:"<%=basePath%>admin/commentPass",
								       error:function(data){
								           alert("出錯了！");
								       },
								       success:function(data){
								           if (data == "true") {
								           	alert("操作完成"),
								                window.location.href="<%=basePath%>admin/ppDetailInfo?userId="+$("#userId").val();
								           }
								           else {
								                alert("出錯了！");
								           }
								       }
								   });
					    	}
					    	function nopass(){
								var str="";
								$(":checkbox:checked").each(function(){
									str+=this.value+";";
								})
								$.ajax({
								       data: {
								       	"commentIds":str,
								       	},
								       type:"POST",
								       url:"<%=basePath%>admin/commentNoPass",
								       error:function(data){
								           alert("出錯了！");
								       },
								       success:function(data){
								           if (data == "true") {
								           	alert("操作完成"),
								                window.location.href="<%=basePath%>admin/ppDetailInfo?userId="+$("#userId").val();
								           }
								           else {
								                alert("出錯了！");
								           }
								       }
								   });
					    	}
              		  </script>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
