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
                    <i class="arrow"></i><span>支持页</span>
                </div>
            <!--/导航栏-->
           
            <!--/工具栏-->
			 	<div class="container-flud" style="margin: 10px;">
			 		<div class="row">
			 			<div class="col-sm-2">
			 				<span class="label label-info">支持者</span>
			 			</div>
			 		</div>
			 	</div>
            <!--列表-->
            <div class="table-container" style="margin-top: 5px;">
            	
			 		<!--文字列表-->
                <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ltable">
                    <tbody>
                        
                                <tr class="odd_bg">
                                    <th align="left" width="15%"><span style="margin-left: 10px;">支持者</span> </th>
                                    <th align="left" width="15%">支持项目</th>
                                    <th align="left" width="10%">支持金额(元)</th>
                                    <th align="left" width="20%">评论</th>
                                    <th align="left" width="10%">时间</th>
                                </tr>
                                <c:if test="${ empty list }">
                                	<tr style="background-color: rgb(255,255,255);">
	                                	<td colspan="4">
	                                	<div class="text text-info text-center">暂时没有支持者！</div>
	                                	</td>
                                	</tr>
                                </c:if>
                                <c:if test="${not empty list}">  
                                <c:forEach items="${list}" var="map">
                                	<tr style="background-color: rgb(255,255,255);">
                                	
                                    <td>
                                    	<img style="width:50px;"/>
                                        <img src="<%=basePath %>${map.get('user').getUserWcAvatar()}" style="width: 60px; height: 60px;"/>
                                        <input type="hidden" value="48" />
                                        <span class="text-info">${map.get("user").getUserWcNickname()}</span>
                                    </td>
                                    <td>${map.get("activitySupport").getActivityName()}</td>
                                    <td>${map.get("activitySupport").getActSupMoney()} 元</td>
                                    <td>${map.get("activitySupport").getActSupComment()}</td>
                                    <td><fmt:formatDate value="${map.get('activitySupport').getActSupTime()}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                </tr>
                                </c:forEach>
                                </c:if>
                    </tbody>
                </table>
			 	</div>
                
            </div>
            <!--/文字列表-->
            <!--/列表-->
            <!--内容底部-->
            <!--内容底部-->
	            <div style="margin-left:5px">
		             <jsp:include page="activitySupportPagination.jsp">
						<jsp:param name="url" value="/admin/zdjz/activitySupportList" />
						<jsp:param name="pages" value="${pages}" />
						</jsp:include>
	            </div>
            
            </div>
            <!--/内容底部-->
            

 
</body>
</html>
