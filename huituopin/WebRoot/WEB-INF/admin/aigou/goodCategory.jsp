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
    <title>类别管理</title>
    <link href="<%=path%>/admin/skin/css/style.css" rel="stylesheet" />
    <link href="<%=path%>/admin/skin/css/pagination.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" charset="utf-8" src="<%=path%>/admin/skin/js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/admin/skin/js/layindex.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/admin/skin/js/laymain.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/admin/skin/js/common.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/admin/skin/js/showDialog.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path %>/admin/skin/css/bootstrap.min.css"/>
	<script type="text/javascript">
		 $(function(){
	        $('#txtKeywords').bind('keypress',function(event){
	            if(event.keyCode == "13")    
	            {
	                $("#selectForm").submit();
	            }
	        });
	    });
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
		function deleteSelected(){
			var str="";
			$(":checkbox:checked").each(function(){
				str+=this.value+";";
			})
			$.ajax({
			       data: {
			       	"productTypesIds":str,
			       	},
			       type:"POST",
			       url:"<%=basePath%>admin/aigou/goodCategorysDelete",
			       error:function(data){
			           alert("出錯了！");
			       },
			       success:function(data){
			           if (data == "true") {
			           	alert("操作完成"),
			                window.location.href="<%=basePath%>admin/aigou/goodCategory";
			           }
			           else {
			                alert("出錯了！");
			           }
			       }
			   });
		}
	</script>
</head>
<body class="indexbody">
        <!--头部-->
		<jsp:include page="../commonPage/commonTop.jsp"></jsp:include>
		<!--/头部-->
		<!-- 左侧 -->
		<jsp:include page="../commonPage/commonLeft.jsp"></jsp:include>
		<!-- /左侧 -->
        <!--右侧内容-->
        <div class="main-container">
            <div class="mainbody">
                <div id="floatHead" class="toolbar-wrap" style="background-color: rgb(244,245,249); margin-bottom: 20px;">
		           <div style="margin-left:30px;"><label>类别管理</label></div>
		        </div>
            <!--工具栏-->
            <div id="floatHead" class="toolbar-wrap" style="height: 52px;">
                <div class="toolbar">
                    <div class="box-wrap">
                        <a class="menu-btn"></a>
                        <div class="l-list">
                            <ul class="icon-list">
                            	<li><input class="btn btn-primary" type="button" onclick="window.location.href='<%=path%>/admin/aigou/addProductType'" value="新增"/></li>
                                <li><input class="btn btn-primary" type="button" onclick="deleteSelected()" value="删除"/></li>
                            </ul>
                        </div>
                        <form id="selectForm" action="<%=basePath%>admin/aigou/goodCategory" method="post">
	                        <div class="r-list">
	                            <input name="txtKeywords" type="text" id="txtKeywords" value="${txtKeywords}" class="keyword"  />
	                            <a id="lbtnSearch" class="btn-search" onclick="$('#selectForm').submit();">查询</a>
	                        </div>
                        </form>
                    </div>
                </div>
            </div>
            <!--/工具栏-->

            <!--列表-->
            <div class="table-container">
                <!--文字列表-->
                <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ltable">
                    <tbody>
                        
                                <tr class="odd_bg">
                                    <th width="6%"><input type="checkbox" onclick="checkAll(this)"value="全选"/></th>
                                    <th align="left" width="25%">产品类别</th>
                                    <th align="left" width="10%">产品数</th>
                                    <th width="10%">操作</th>
                                </tr>
                                 <c:forEach items="${productTypeList}" var="productType">
	                                <tr>
		                                <td align="center">
	                                        <span class="checkall" style="vertical-align: middle;"><input type="checkbox" value="${productType.productTypeId }" /></span>
	                                    </td>
	                                    <td>${productType.productTypeName}</td>
	                                    <td>${productType.productTypeNum}</td>
	                                     <td align="left" id="td_operate">
	                                    	<input class="btn btn-primary" type="button" onclick="window.location.href='<%=path%>/admin/aigou/updateGoodCategory?id=${productType.productTypeId}'" value="查看"/>
	                                    </td>
	                                </tr>
                                </c:forEach>
                    </tbody>
                </table>
            </div>
            <!--/文字列表-->
            <!--内容底部-->
            <div class="line20"></div>
            <jsp:include page="../commonPage/pagination.jsp">
                <jsp:param name="url" value="../../admin/aigou/goodCategory.do?txtKeywords=${txtKeywords}" />
            </jsp:include>
            <!--/内容底部-->
        </div>
        </div>
</body>
</html>
