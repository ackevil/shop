<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>衣旧有爱规则设定</title>
    <link href="<%=path %>/admin/skin/css/style.css" rel="stylesheet" />
    <link href="<%=path %>/admin/skin/css/pagination.css" rel="stylesheet" type="text/css" />
    
    <script type="text/javascript" charset="utf-8" src="<%=path %>/admin/skin/js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/admin/skin/js/layindex.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/admin/skin/js/laymain.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/admin/skin/js/common.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/admin/skin/js/showDialog.js"></script>
    
    
    <script src="<%=path %>/admin/skin/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=path %>/admin/skin/js/bootstrap-datetimepicker.min.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="<%=path %>/admin/skin/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/admin/skin/css/bootstrap-datetimepicker.min.css"/>
  </head>
<script>
function ShowDIV(thisObjID) {
      $("#BgDiv").css({ display: "block", height: $(document).height() });
      var yscroll = document.documentElement.scrollTop;
      $("#" + thisObjID ).css("top", "100px");
      $("#" + thisObjID ).css("display", "block");
  	 document.documentElement.scrollTop = 0;
}
function closeDiv(thisObjID) {
      $("#BgDiv").css("display", "none");
      $("#" + thisObjID).css("display", "none");
 }
 //点击弹出框《确定》按钮
 function btnOK1(){
	  var goodNum=document.getElementById("GoodNum1");
	  update(1,goodNum.value);
	  document.getElementById("poorReceiveNum1").innerHTML=goodNum.value;
     
 } 
function btnOK3(){
	  var goodNum=document.getElementById("GoodNum3");
	  update(3,goodNum.value);
	  document.getElementById("poorReceiveNum3").innerHTML=goodNum.value;
 } 
 function update(id,value){
 	$.ajax({
			data:{
				"id":id,
				"value":value
			},
			type:"POST",
			url:"<%=basePath%>admin/yjya/updateRule_do",
			error:function(data){
               	btnArray = ["出錯了"],
                   mui.alert('', ' ',btnArray, function() {})
               },
               success:function(data){
                   if (data == "true") {
                   	btnArray = ["修改成功"],
                   	mui.alert('', ' ',btnArray, function() {
                   	});
                   }
                   else {
                       btnArray = ["出错了"];
                   	mui.alert('', ' ',btnArray, function() {});
                   }
               }
		});
 }
</script>
<style>
.col-sm-2 {
    width:10%;
}
.main{
    margin:30px;
}
.rule_table{
width:100%;
border:1px solid #e7e7eb;
}
.rule_table_tr{
border:1px solid #e7e7eb;
}
.rule_table_tr td{
width:33.3%;
height:50px;
}
#BgDiv{
	background-color:#808080; 
	position:absolute;
	z-index:99;
	left:0; 
	top:0;
	display:none;
	width:100%;
	height:100%;
	opacity:0.5;
	filter: alpha(opacity=50);
	-moz-opacity: 0.5;
}
#DialogDiv1{
	position:absolute;
	width:600px; 
	left:50%;
	top:50%;
    margin-left:-250px;
    height:auto; 
    z-index:100;
    background-color:#fff; 
    border:1px #656667 solid; 
}
#DialogDiv1 h2{ 
	height:40px; 
	font-size:20px; 
	background-color:#f4f5f9; 
	position:relative; 
	padding-left:10px; 
	line-height:40px;
	font-family:'黑体';
	}
#DialogDiv2{
	position:absolute;
	width:600px; 
	left:50%;
	top:50%;
    margin-left:-250px;
    height:auto; 
    z-index:100;
    background-color:#fff; 
    border:1px #656667 solid; 
}
#DialogDiv2 h2{ 
	height:40px; 
	font-size:20px; 
	background-color:#f4f5f9; 
	position:relative; 
	padding-left:10px; 
	line-height:40px;
	font-family:'黑体';
	}
#DialogDiv3{
	position:absolute;
	width:600px; 
	left:50%;
	top:50%;
    margin-left:-250px;
    height:auto; 
    z-index:100;
    background-color:#fff; 
    border:1px #656667 solid; 
}
#DialogDiv3 h2{ 
	height:40px; 
	font-size:20px; 
	background-color:#f4f5f9; 
	position:relative; 
	padding-left:10px; 
	line-height:40px;
	font-family:'黑体';
	}
.form{
	padding:10px;
	height:200px;
}
.dialogBtnCancle{
   height:35px;
	width:50px;
	background-color:#d2d2d5;
	border:1px solid #d2d2d5;
	margin-top:8px;
	margin-right:10px;
}
.dialogBtnOK{
	height:35px;
	width:50px;
	background-color:#007aff;
	color:#ffffff;
	border:1px solid #007aff;
	margin-top:8px;
	margin-right:10px;
}
.rule_table_tr td a{
color:#007aff;
text-decoration:none;
}
.rule_table_tr td a:link{
color:#007aff;
text-decoration:none;
}
.rule_table_tr td a:visited{
color:#007aff;
text-decoration:none;
}
.rule_table_tr td a:hover{
color:#007aff;
text-decoration:none;
}
.rule_table_tr td a:active{
color:#007aff;
text-decoration:none;
}
</style>


  <body>
   <!--定义一个覆盖全屏的div,遮挡搜有的页面功能-->
   <div id="BgDiv"></div>
   <!--定义一个覆盖全屏的div,遮挡搜有的页面功能-->
   <!--遮罩层显示的DIV1-->
  <div id="DialogDiv1" style="display:none">
    <h2>修改</h2>
    <div class="form">
      <p>贫困人士每年领取捐赠物品数量</p>
      <input id="GoodNum1" type="text" style="width:100%;"/>
    </div>
    <div style="text-align:right;width:100%;height:50px;background-color:#f4f5f9;">
    <input class="dialogBtnCancle" type="button" onclick="closeDiv('DialogDiv1')" value="取消"/>
    <input class="dialogBtnOK" type="button" onclick="btnOK1();closeDiv('DialogDiv1')" value="确定"/>
    </div>
  </div>
  <!--遮罩层显示的DIV1-->
   <!--遮罩层显示的DIV3-->
  <div id="DialogDiv3" style="display:none">
    <h2>修改</h2>
    <div class="form">
      <p>爱心人士捐赠物品已被领取可获得积分</p>
      <input id="GoodNum3" type="text" style="width:100%;"/>
    </div>
    <div style="text-align:right;width:100%;height:50px;background-color:#f4f5f9;">
    <input class="dialogBtnCancle" type="button" onclick="closeDiv('DialogDiv3')" value="取消"/>
    <input class="dialogBtnOK" type="button" onclick="btnOK3();closeDiv('DialogDiv3')" value="确定"/>
    </div>
  </div>
  <!--遮罩层显示的DIV3-->
    <jsp:include page="../commonPage/commonTop.jsp" />
    <jsp:include page="../commonPage/commonLeft.jsp"></jsp:include>
    
    <div class="main-container">
       <div id="floatHead" class="toolbar-wrap" style="background-color: rgb(244,245,249); margin-bottom: 20px;">
           <div style="margin-left:30px;"><label>规则设定</label></div>
        </div>
        <div class="main">
        <table class="rule_table">
           <tr class="rule_table_tr"><td>贫困人士每年领取捐赠物品数量</td><td id="poorReceiveNum1" style="text-align:center;">${limitNum}</td><td style="text-align:center;"><a href="javascript:;" onclick="ShowDIV('DialogDiv1')">修改</a></td></tr>
            <tr class="rule_table_tr"><td>爱心人士分享可获得积分</td><td id="poorReceiveNum2" style="text-align:center;">${shareCredict}</td><td style="text-align:center;"><a href="javascript:;" onclick="ShowDIV('DialogDiv2')">修改</a></td></tr>
             <tr class="rule_table_tr"><td>爱心人士捐赠物品已被领取可获得积分</td><td id="poorReceiveNum3" style="text-align:center;">${receptedCredict}</td><td style="text-align:center;"><a href="javascript:;" onclick="ShowDIV('DialogDiv3')">修改</a></td></tr>
        </table>
        </div>
    </div>
  </body>
</html>
