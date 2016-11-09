<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>衣旧有爱评价管理</title>
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
      $(function(){
           $('#datetimeStart').datetimepicker({
                 format: 'yyyy-mm-dd'
           });
           $('#datetimeEnd').datetimepicker({
                 format: 'yyyy-mm-dd'
           });
});
  </script>
<style>
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
.tab-content{
border:1px solid #eee;
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
    height: 30%;
    background-color: rgba(232, 236, 255, 0.53);
}

.content_content {
    padding:5px;
}

</style>


  <body>
    <jsp:include page="../commonPage/commonTop.jsp" />
    <jsp:include page="../commonPage/commonLeft.jsp"></jsp:include>
    
    <div class="main-container">
       <div id="floatHead" class="toolbar-wrap" style="background-color: rgb(244,245,249); margin-bottom: 20px;">
           <div style="margin-left:30px;"><label>评价管理</label></div>
        </div>
        <div class="main">
            <div id="myTabContent" class="tab-content">
               <div class="tab-pane fade in active" id="selling">
                  <div class="totalCount">
                  	<span>共有${getNum}条记录</span>
                  </div>
                  <div class="content_table" style="width:100%; margin-top:20px;">
                       <div class="table_thead">
                       		<div class="table_title" style="width:5%;text-align:center;"><input type="checkbox" onclick="checkAll(this)"value="全选"/></div>
                              <div class="table_title"  style="width:25%;text-align:center;">评价时间</div >
                              <div class="table_title" style="width:25%;text-align:center;" ><span style="text-align:center;">评价内容</span></div>
                              <div class="table_title" style="width:15%;text-align:center;"><span style="text-align: center;">评价人</span> </div>
                              <div class="table_title"  style="width:15%;text-align:center;"><span style="text-align: center;">产品名称</span></div>
                              <div class="table_title"  style="width:15%;text-align:center;"><span style="text-align: center;">审核状态</span></div>
                        </div>
                        <div style="margin-top:30px;">
                        		<c:forEach items="${commentInfoList}" var="commentInfo">
                        			<div class="single_goods">
                                 <div class="content_content">
                                      <table style="width:100%;">
                                          <tr>
                                          	<td style="width:5%;text-align:center;">
                                                  <span class="checkall" style="vertical-align: middle;"><input type="checkbox" name="checkbox" value="${commentInfo[5]}" /></span>
                                              </td>
                                              <td style="width:25%;text-align:center;">
                                                  <span>${commentInfo[0]}</span>
                                              </td>
                                              <td style="width:25%;text-align:left;">
                                                  <span>${commentInfo[1]}</span><br>
                                              </td>
                                              <td style="width:15%;text-align:center;">
                                                  <span style="color:#0b80fe;">${commentInfo[2]}</span>
                                              </td>
                                              <td style="width:15%;text-align:center;">
                                                  <span style="color:#007aff">${commentInfo[3]}</span>
                                              </td>
                                               <td style="width:15%;text-align:center;">
                                               	<span>
                                               		<c:if test="${commentInfo[6]==0}">
			                                		未审核
			                                	</c:if>
			                                	<c:if test="${commentInfo[6]==1}">
			                                		审核通过
			                                	</c:if>
			                                	<c:if test="${commentInfo[6]==2}">
			                                		审核不通过
			                                	</c:if>
                                               	</span>
                                              </td>
                                             
                                          </tr>
                                      </table>
                                 </div>
                              </div>
                        		</c:forEach>
                        		<input class="btn btn-primary" type="button" onclick="pass()" value="通过"/>
                        		<input class="btn btn-primary" type="button" onclick="nopass()" value="不通过"/>
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
						})
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
						                window.location.href="<%=basePath%>admin/yjya/commentManager";
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
						                window.location.href="<%=basePath%>admin/yjya/commentManager";
						           }
						           else {
						                alert("出錯了！");
						           }
						       }
						   });
			    	}
            		  </script>
                        
                   </div>
                   <!--内容底部-->
                   <div class="line20"></div>
                 	 <jsp:include page="../commonPage/pagination.jsp">
                    	<jsp:param name="url" value="../../admin/yjya/commentManager.do" />
                   </jsp:include>
                  <!--/内容底部-->
              </div>
            </div>
         </div>
    </div>
  </body>
</html>
