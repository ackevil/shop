<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>物流工具</title>
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
function showDiv(content,selfText){
   if(document.getElementById(content).style.display=="none"){
       document.getElementById(content).style.display="block";
       document.getElementById(selfText).innerHTML="取消查看";
   }
   else{
      document.getElementById(content).style.display="none";
      document.getElementById(selfText).innerHTML="查看";
   }
}
</script>
<style>
.col-sm-2 {
    width:10%;
}
.main{
    margin:30px;
}
.a_style{
color:#007aff;
}
.a_style:visited{
text-decoration: none;
color:#007aff;
}
.a_style:hover{
text-decoration: none;
color:#007aff;
}
.a_style:link{
text-decoration: none;
color:#007aff;
}
.province_nei{
width:100%;
}
.logistics-title{
	width:100%;
	height:40px;
	background-color:#f4f5f9;
	font-size:10px;
	line-height: 40px;
	border-top: 1px solid #EBEBEE;
	border-left: 1px solid #EBEBEE;
	border-right: 1px solid #EBEBEE;
}
.logistics-title label{
	margin-bottom: 0px;
}
.logistics-content table tr{
	font-size:11px;
	color:#000000;
	height:40px;
	border-bottom: 1px solid #EBEBEE;
	border-left: 1px solid #EBEBEE;
	border-right: 1px solid #EBEBEE;
}

</style>
<script type="text/javascript">
	function addLogistics(){
		var path = $("#path").val();
		gotoNextPageWith(path+"/admin/aigou/addLogisticsTool");
	}
	function gotoNextPageWith(action){
	form = $("<form></form>");
     form.attr('action',action);
     form.attr('method','post');
     form.appendTo("body");
     form.css('display','none');
     form.submit();
}
</script>

  <body>
    <jsp:include page="../commonPage/commonTop.jsp" />
    <jsp:include page="../commonPage/commonLeft.jsp"></jsp:include>
    <input style="display: none" id="path" value="<%=path%>">
    <div class="main-container">
       <div id="floatHead" class="toolbar-wrap" style="background-color: rgb(244,245,249); margin-bottom: 20px;">
           <lable style="margin-left:35px;">物流工具</lable>
        </div>
        <div class="main">
            <ul id="myTab" class="nav nav-tabs">
                <li class="active"><a href="#selling" data-toggle="tab">运费模板</a></li>
                <li style="float:right; line-height: 32px;"><button onclick="addLogistics()" type="button" class="btn btn-primary">新建运费模板</button></li>
            </ul>
            <div id="myTabContent" class="tab-content">
            
            
            <!-- start -->
            <c:forEach items="${logisticsList}" var="logistics">
               <div class="logistics">
                 <div class="logistics-title">
                   <label style="width:33%; padding-left: 20px">${logistics.logisticsName}</label>
                   <label style="width:33%;text-align:center;color:#9b9b9b;">最后编辑时间<span>${logistics.logisticsDatatime}</span></label>
                   <label style="width:33%;text-align:center;">
                   		<a class="a_style" href="<%=path%>/admin/aigou/editLogisticsTool?logisticsId=${logistics.logisticsId}" >修改</a>
                   		<span>-</span>
                   		<a class="a_style" href="<%=path%>/admin/aigou/deleteLogisticsTool?logisticsId=${logistics.logisticsId}" >删除</a>
                   		<span>-</span>
                   		<a id="look${logistics.logisticsId}" class="a_style" href="javascript:;" onclick="showDiv('${logistics.logisticsId}','look${logistics.logisticsId}')">查看</a>
                   	</label>
                 </div>
                 <div class="logistics-content logistics-content+${logistics.logisticsId}" id="${logistics.logisticsId}" style="display:none;">
                   <table style="width:100%;">
                      <tr>
                     		<td style="padding-left: 20px;width:40%; " >可配送至</td>
	                     	<td style="width:10%;text-align: center;">首件(个)</td>
	                     	<td style="width:15%;text-align: center; ">运费(元)</td>
	                     	<td style="width:10%;text-align: center; ">续件(个)</td>
	                     	<td style="width:15%;text-align: center; ">续费(元)</td>
                     	</tr>
                     <tr>
	                     <td style="padding-left: 20px;width:40%; ">${logistics.logisticsAreas}</td>
	                     <td style="width:10%;text-align: center; ">${logistics.logisticsFirstcount}</td>
	                     <td style="width:15%;text-align: center; ">${logistics.logisticsFirstprice}</td>
	                     <td style="width:10%;text-align: center; ">${logistics.logisticsLastconut}</td>
	                     <td style="width:15%;text-align: center; ">${logistics.logisticsLastprice}</td>
                     </tr>
                   </table>
                 </div>
              </div>
              <div style="width: 100%;height: 10px;"></div>	
              </c:forEach>
              <!-- end -->	
            </div>
        </div>
   </div>
  </body>
</html>
