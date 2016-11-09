<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>修改衣服类别</title>
    <link href="<%=path%>/admin/skin/css/style.css" rel="stylesheet" />
    <script type="text/javascript" charset="utf-8" src="<%=path%>/admin/skin/js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/admin/skin/js/Validform_v5.3.2_min.js"></script>
    <script type="text/javascript" src="<%=path%>/admin/skin/js/jsAddress.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/admin/skin/datepicker/WdatePicker.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/admin/skin/js/laymain.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/admin/skin/js/common.js"></script>
    <script src="<%=path%>/admin/skin/js/showDialog.js" charset="gb2312" type="text/javascript"></script>
    <link href="<%=path%>/admin/skin/css/showDialog.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/admin/skin/css/bootstrap.min.css"/>
    <script type="text/javascript">
            	function updateClothType(){
            		$.ajax({
            			data:{
            				clothTypeId:$("#txtId").val(),
            				name:$("#txtName").val(),
            				num:$("#txtNum").val()
            			},
            			type:"GET",
            			dateType:'json',
            			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            			url:"<%=basePath%>admin/yjya/updateClothType_do",
            			error:function(data){
            				alert("出錯了！"+data.msg);
            			},
            			success:function(data){
            				alert("修改成功");
            				window.location.href="<%=path%>/admin/yjya/clothTypeList";
            			}
            		});
            	}
            	function deleteClothType(){
            		$.ajax({
            			data:{
            				clothTypeId:$("#txtId").val(),
            			},
            			type:"GET",
            			dateType:'json',
            			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            			url:"<%=basePath%>admin/yjya/deleteClothType_do",
            			error:function(data){
            				alert("出錯了！"+data.msg);
            			},
            			success:function(data){
            				alert("删除成功");
            				window.location.href="<%=path%>/admin/yjya/clothTypeList";
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
        <div class=" tab1 main-container mainbody " id="tab1">
            <div id="floatHead" class="toolbar-wrap" style="background-color: rgb(244,245,249); margin-bottom: 20px;">
	           <div style="margin-left:30px;"><label>产品类别管理</label></div>
	        </div>
            <div class="line10"></div>
            <div id="floatHead" class="content-tab-wrap">
                <div class="content-tab">
                    <div class="menu content-tab-ul-wrap">
                        <div class="tab-title"><span>基本信息</span><i></i></div>
                        <ul>
                            <li id="one1" class="off"><span>基本信息</span></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="menudiv">
            	<input name="txtId" type="hidden" id="txtId" value="${clothType.clothTypeId}" disabled="disabled"/>
                <div class="tab-content" style="display: block;" id="con_one_1">
                    <dl>
                        <dt>类别名称</dt>
                        <dd>
                            <input name="txtName" type="text" id="txtName" value="${clothType.clothTypeName}" class="input normal" datatype="*2-100" sucmsg=" " />
                            <span class="Validform_checktip">*类别名称最多100个字符</span> </dd>
                    </dl>
                    <dl>
                        <dt>产品数量</dt>
                        <dd>
                            <input name="txtNum" type="text" id="txtNum" value="${clothType.clothTypeNum}" class="input normal" datatype="d" sucmsg=" " />
                            <span class="Validform_checktip">*产品数量必须是数字</span> </dd>
                    </dl>
                    <dl>
                        <dt>添加时间</dt>
                        <dd>
                            <input name="txtIntime" type="text" id="txtIntime" value="${clothType.clothTypeIntime}" class="input normal" datatype="d" sucmsg=" " />
                            <span class="Validform_checktip"></span> </dd>
                    </dl>
                </div>
            </div>
            <div class="page-footer">
                <div class="btn-wrap" style="position: static;">
                	<input class="btn btn-primary" type="button" onclick="updateClothType()" value="提交保存"/>
                	<input class="btn btn-primary" type="button" onclick="deleteClothType()" value="删除"/>
                </div>
            </div>
            
        </div>
        <!--/右侧内容-->
</body>
</html>
