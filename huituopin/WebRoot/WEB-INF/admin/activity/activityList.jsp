<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>重大救助列表管理</title>
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
  	<link rel="stylesheet" type="text/css" href="<%=path %>/admin/skin/css/bootstrapValidator.min.css"/>
	<script src="<%=path %>/admin/skin/js/bootstrapValidator.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body class="indexbody">
        <!--头部-->
        <jsp:include page="../commonPage/commonTop.jsp" />
        <!--/头部--> 
        
        <!--左侧导航-->
          <jsp:include page="../commonPage/commonLeft.jsp"></jsp:include>
          <!--/左侧导航--> 
        <!--右侧内容-->
        <div class="main-container">
          
	         <div class="mainbody">
	                <!--导航栏-->
	                <div class="location" style="height: 30px;">
	                     <a href="" class="home"><i></i><span >重大救助</span></a>
	                    <i class="arrow"></i><span>列表</span>
	                </div>
	            <!--/导航栏-->
	            <div class="line10"></div>
	            <!--工具栏-->
	            
	            <div id="floatHead" class="toolbar-wrap" style="background-color: rgb(244,245,249); margin-bottom: 20px;">
	            	<div class="container-flud">
	            		 <form id="selectForm" action="/admin/zdjz/activityList" method="post">
	            		<div class="row form-group">
	            			<div class="form-control-static col-sm-1 text-right">创建时间</div>
		                			<div class="col-sm-3 ">
		                				 <div class="input-group date" id="datetimeStart">
		                				 	<input type="text" id="activityLaunchTime" class="form-control" name="activityLaunchTime" value="<fmt:formatDate value='${activityLaunchTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
							                <span class="input-group-addon">
							                    <span class="glyphicon glyphicon-calendar"></span>
							                </span>
							            </div>
		                			</div>
									
									<div class="form-control-static col-sm-1">至</div>
		                			<div class="col-sm-3">
		                				<div class="input-group date" id="datetimeStop">
							                <input type="text" id="activityStopTime" class="form-control" name="activityStopTime" value="<fmt:formatDate value='${activityStopTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
							              
							                <span class="input-group-addon">
							                    <span class="glyphicon glyphicon-calendar"></span>
							                </span>
							            </div>
		                			</div>
	            		</div>
	            		<div class="row form-group">
	            			<div class="col-sm-1 form-control-static text-right">
	            				状态
	            			</div>
	            			<div class="col-sm-3">
	            				<select id="state" name="state" class="form-control">
								  <option  value="" ${state==null?"selected":"" }>所有</option>
								  <option value="0" ${state==0?"selected":"" } >未发布</option>
								  <option value="1" ${state==1?"selected":"" } >进行中</option>
								  <option value="2" ${state==2?"selected":"" } >已结束</option>
								</select>
	            			</div>
	            		</div>
	            		<div class="row form-group">
	            			<div class="col-sm-1 form-control-static text-right">
	            				关键字
	            			</div>
	            			<div class="col-sm-3">
	            				<input id="keyWord" name="key"  type="text" id="" value='${key==null?"":key }' class="form-control" placeholder="关键字搜索"/>
	            			</div>
	            		</div>
	            		<div class="row form-group">
	            			<div class="col-sm-1 col-sm-offset-1">
	            				<input type="submit" class="btn btn-primary form-control" value="筛选"/>
	            			</div>
	            		</div>
	            		 </form>
	            	</div>
	            </div>
	            <!--/工具栏-->
				 	<div class="container-flud" style="margin-bottom: 7px;">
				 		<div class="row">
				 			<div class="col-sm-2">
				 				<a href="/admin/zdjz/addActivity"> <span class="btn btn-primary">新建图文项目</span></a>
				 			</div>
				 		</div>
				 	</div>
	            <!--列表-->
	            <div class="table-container" style="margin-top: 5px;">
	            	
				 		<!--文字列表-->
	                <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ltable">
	                    <tbody>
	                        
	                                <tr class="odd_bg">
	                                   
	                                    <th align="left" width="10%"><span style="margin-left: 10px;">图片</span> </th>
	                                    <th align="left" width="10%">标题</th>
	                                    <th align="left" width="10%"><div style="text-align: center;">筹款目标</div></th>
	                                    <th align="left" width="10%"><div style="text-align: center;">已筹金额</div> </th>
	                                    <th align="left" width="4%">支持者</th>
	                                    <th align="left" width="10%">开始时间</th>
	                                    <th align="left" width="10%">结束时间</th>
	                                    <th align="left" width="4%">状态</th>
	                                    <th width="10%" style="text-align: center;" >操作 </th>
	                                </tr>
	                                
	                                <c:forEach items="${activityList}" var="activity">
		                                <tr style="background-color: rgb(245,251,255);">
		                                	<td colspan="8"><span style="margin-left:10px;"></span>创建时间：<span name="activityLaunchTime">${activity.activityLaunchTime}</span></td>
		                                	<td align="center">
		                                        <a href="<%=basePath%>admin/zdjz/editActivity?id=${activity.activityId}" > <span class="text-primary">修改</span> </a>
		                                        <a href="<%=basePath%>admin/zdjz/delActivity?id=${activity.activityId}"><span class="text-danger">删除</span> </a>
		                                	</td>
		                                </tr>
		                             
		                                <tr style="background-color: rgb(255,255,255);">
		                                    <td align="center">
		                                        <img src="${activity.activityMainPic}" style="width: 60px; height: 60px;"/>
		                                        <input type="hidden" value="48" />
		                                    </td>
		                                    <td>${activity.activityName}</td>
		                                    <td><div style="text-align: center;">${activity.activityTotalFund} 元</div></td>
		                                    <td><div style="text-align: center;">${activity.activityRaised} 元</div></td>
		                                    <td><a href="/admin/zdjz/activitySupportList?id=${activity.activityId}">${activity.activitySuportNum}</a> 人</td>
		                                    <td><fmt:formatDate value="${activity.activityLaunchTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		                                    <td><fmt:formatDate value="${activity.activityStopTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		                                    <td>${activity.activityState==0?"未发布":(activity.activityState==1?"进行中":"已结束")}</td>
		                                    <td align="center">
		                                    	<a href="<%=basePath%>admin/zdjz/zhidingActivity?id=${activity.activityId}">
		                                    	${activity.activityIsOrder==true ? '<span class="btn btn-danger">取消置顶</span>':'<span class="btn btn-primary">置顶</span>'}
		                                    	
		                                    	</a>
		                                    </td>
		                                </tr>
	                                </c:forEach>
	                                
	                                
	                                
	                                
	                               
	                    </tbody>
	                </table>
				 	</div>
	            
	        </div>
			 <div class="line20"></div>
			 <!--内容底部-->
		     <div style="margin-left:5px">
			           <jsp:include page="activityListPagination.jsp">
						<jsp:param name="url" value="/admin/zdjz/activityList" />
						<jsp:param name="pages" value="${pages}" />
						</jsp:include>
		     </div>
           
         </div>
       
     <script type="text/javascript">
     
    	//日期时间选择器
$("#datetimeStart").datetimepicker({
    format: "yyyy-mm-dd hh:ii:ss",
    autoclose: true,
    minView: "hour",
    maxView: "decade",
    todayBtn: true,
    pickerPosition: "bottom-left"
}).on("click",function(ev){
    $("#datetimeStart").datetimepicker("setEndDate", $("#activityStopTime").val());
});

$("#datetimeStop").datetimepicker({
    format: "yyyy-mm-dd hh:ii:ss",
    autoclose: true,
    minView: "hour",
    maxView: "decade",
    todayBtn: true,
    pickerPosition: "bottom-left"
}).on("click", function (ev) {
    $("#datetimeStop").datetimepicker("setStartDate", $("#activityLaunchTime").val());
});

$('#datetimeStart')
    .on('changeDate show', function(e) {
        // Revalidate the date when user change it
        $('#selectForm').bootstrapValidator('revalidateField', 'activityLaunchTime');
});
$('#datetimeStop')
    .on('changeDate show', function(e) {
        // Revalidate the date when user change it
        $('#selectForm').bootstrapValidator('revalidateField', 'activityStopTime');
});
 




    </script>
    
    	<script type="text/javascript">
    $('#selectForm').bootstrapValidator({
        
        fields: {
            activityLaunchTime:{
            	validators: {
                    date: {
                        format: 'YYYY-MM-DD h:m:s',
                        message: '日期不合法'
                    }
                  
                }
            },
            activityStopTime:{
            	validators:{
            		  date: {
                        format: 'YYYY-MM-DD h:m:s',
                        message: '日期不合法'
                   	  }
                   	 
            	}
            }
        }
    });
	
		
</script>	
</body>
</html>
