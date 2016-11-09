<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>衣旧有爱评论管理</title>
    <link href="../skin/css/style.css" rel="stylesheet" />
    <script type="text/javascript" charset="utf-8" src="../skin/js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="../skin/js/Validform_v5.3.2_min.js"></script>
    <script type="text/javascript" src="../skin/js/jsAddress.js"></script>
    <script type="text/javascript" charset="utf-8" src="../skin/datepicker/WdatePicker.js"></script>
    <script type="text/javascript" charset="utf-8" src="../skin/js/laymain.js"></script>
    <script type="text/javascript" charset="utf-8" src="../skin/js/common.js"></script>
    <script src="../skin/js/showDialog.js" charset="gb2312" type="text/javascript"></script>
    <link href="../skin/css/showDialog.css" rel="stylesheet" />
    <script src="../UEditor/editor_config.js" type="text/javascript"></script>
    <script src="../UEditor/editor_all_min.js" type="text/javascript"></script>
    <link href="../UEditor/themes/default/ueditor.css" rel="stylesheet" />
    <link href="../skin/css/webuploader.css" rel="stylesheet" />
    <script src="../skin/js/webuploader.js"></script>
</head>
<body class="indexbody">
        <jsp:include page="../commonPage/commonTop.jsp" />
    	<jsp:include page="../commonPage/commonLeft.jsp"/>
        <!--右侧内容-->
        <div class=" tab1 main-container mainbody " id="tab1">
            <div class="menudiv">
                <div class="tab-content" style="display: block;" id="con_one_1">
                	<c:if test="${clothInfo == null}">
                		暂无评价
                	</c:if>
                	<c:if test="${clothInfo != null}">
	                    <dl>
	                        <dt>评论时间</dt>
	                        <dd>
	
	                            <input name="txtTime" type="text" id="txtName" value="${clothInfo[4]}" class="input normal"/>
	                    </dl>
	                    <dl>
	                        <dt>评论内容</dt>
	                        <dd>
	
	                            <input name="txtContent" type="text" id="txtName" value="${clothInfo[3]}" class="input normal" />
	                    </dl>
	                    <dl>
	                        <dt>评论者</dt>
	                        <dd>
	
	                            <input name="txtPoorName" type="text" id="txtName" value="${clothInfo[1]}" class="input normal" />
	                    </dl>
	                    <dl>
	                        <dt>产品名称</dt>
	                        <dd>
	
	                            <input name="txtClothName" type="text" id="txtName" value="${clothInfo[5]}" class="input normal"/>
	                    </dl>
                    </c:if>
                </div>
            </div>
            <div class="page-footer">
                <div class="btn-wrap" style="position: static;">
                	<input class="btn btn-primary" type="button" onclick="window.location.href='<%=basePath %>admin/yjya/commodityManager'" value="返回上一页"/>
                </div>
            </div>
        </div>
        <!--/右侧内容-->
</body>
</html>

