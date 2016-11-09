<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>爱购评价管理</title>
    <link href="<%=path %>/admin/skin/css/style.css" rel="stylesheet" />
    <link href="<%=path %>/admin/skin/css/pagination.css" rel="stylesheet" type="text/css" />
    
    <script type="text/javascript" charset="utf-8" src="<%=path %>/admin/skin/js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/admin/skin/js/layindex.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/admin/skin/js/laymain.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/admin/skin/js/common.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/admin/skin/js/showDialog.js"></script>
    
    
    <script src="<%=path %>/admin/skin/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="<%=path %>/admin/skin/css/bootstrap.min.css"/>
  </head>

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
            <ul id="myTab" class="nav nav-tabs">
                <li class="active"><a href="#selling" data-toggle="tab">全部评论</a></li>
                <!-- <li><a href="#selled" data-toggle="tab">好评</a></li>
                <li><a href="#sell" data-toggle="tab">中评</a></li>
                 <li><a href="#sell" data-toggle="tab">差评</a></li> -->
            </ul>
            <div id="myTabContent" class="tab-content">
               <div class="tab-pane fade in active" id="selling">
                  <div class="totalCount">
                    <span>共有${num}条记录。</span>
                  </div>
                    <div class="content_table" style="width:100%; margin-top:20px;">
                         <div class="table_thead">
                                <div class="table_title"  style="width:25%;text-align:center;">评价时间</div >
                                <div class="table_title" style="width:25%;text-align:center;" ><span style="text-align:center;">评价内容</span></div>
                                <div class="table_title" style="width:25%;text-align:center;"><span style="text-align: center;">评价人</span> </div>
                                <div class="table_title"  style="width:25%;text-align:center;"><span style="text-align: center;">产品名称</span></div>
                                <!-- <div class="table_title"  style="width:20%;text-align:center;"><span style="text-align: center;">操作</span></div> -->
                          </div>
                          <div style="margin-top:30px;">
                                <c:forEach items="${list}" var="commentInfo">
	                                <div class="single_goods">
	                                   <div class="content_content">
	                                        <table style="width:100%;">
	                                            <tr>
	                                                <td style="width:25%;text-align:center;">
	                                                    <lable>${commentInfo[0]}</lable>
	                                                </td>
	                                                <td style="width:25%;text-align:center;">
	                                                    <span>${commentInfo[1]}</span><br>
	                                                </td>
	                                                <td style="width:25%;text-align:center;">
	                                                    <span style="color:#0b80fe;">${commentInfo[2]}</span>
	                                                </td>
	                                                <td style="width:25%;text-align:center;">
	                                                    <span style="color:#007aff">${commentInfo[3]}</span><br>
	                                                    <span style="color:#f74848;text-align:left;">${commentInfo[4]}元</span>
	                                                </td>
	                                                <!-- <td style="width:20%;text-align:center;">
	                                                    <span><button type="button" class="btn btn-default" style="background-color:#3dc700;color:#ffffff;">回复</button></button></span>
	                                                </td> -->
	                                            </tr>
	                                        </table>      
	                                   </div>
	                                </div>
                                </c:forEach>
                         </div>
                           <!--内容底部-->
                           <div class="line20"></div>
                            <jsp:include page="../commonPage/pagination.jsp">
                             <jsp:param name="url" value="../../admin/aigou/commentManager.do" />
                            </jsp:include>
                            <!--/内容底部-->
                     </div>
                  </div>
                  <div></div>
                  <div></div>
               </div>
            </div>
    </div>
  </body>
</html>
