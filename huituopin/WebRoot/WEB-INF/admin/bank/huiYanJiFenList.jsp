<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>爱心银行会员积分</title>
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

.table_thead {
    width: 100%;
    height: 40px;
    background-color: #E9E9F5;
    padding: 10px;
    font-size: 15;
}

.single_goods {
    width: 100%;
    height:75px;
    border: 1px solid;
    border-color: #E9E9F5;
}

.content_title {
    width: 100%;
    height: 30%;
    background-color: rgba(232, 236, 255, 0.53);
}

.content_content {
    padding: 15px;
}

</style>


  <body>
    <jsp:include page="../commonPage/commonTop.jsp" />
    <jsp:include page="../commonPage/commonLeft.jsp"></jsp:include>
    
    <div class="main-container">
       <div id="floatHead" class="toolbar-wrap" style="background-color: rgb(244,245,249);padding-left: 20px">
         <label><span style="color:#007aff;">会员积分列表</span>/${user.userWcNickname}</label>
        </div>
        <div style="width:100%;height:100px;border:1px solid #e7e7eb;margin-left: 30px;">
          <div style="width:50%;height:100%;border-right:1px solid #e7e7eb;text-align:center;font-size:20px;padding-top:30px;float:left;">
            <lable>可用的积分&nbsp;<span style="font-size:25px;color:#3dc700;">${lovingPeople.loveCredicts}</span></lable>
          </div>
          <div style="width:50%;height:100%;border-right:1px solid #e7e7eb;text-align:center;font-size:20px;padding-top:30px;float:right;">
             <lable>去年过期的积分&nbsp;<span style="font-size:25px;color:#3dc700;">0</span></lable>
          </div>
        </div>
        <div class="main">
            <ul id="myTab" class="nav nav-tabs">
                <li class="active"><a href="#integral_detail" data-toggle="tab">积分明细</a></li>
                <li><a href="#integral_income" data-toggle="tab">积分收入</a></li>
                <li><a href="#integral_consume" data-toggle="tab">积分支出</a></li>
            </ul>
            <div id="myTabContent" class="tab-content">
            	<!-- 积分明细 -->
               <div class="tab-pane fade in active" id="integral_detail">
                  <div class="totalCount">
                    <span>共有${fn:length(Credictlist)}条记录</span>
                  </div>
                    <div class="content_table" style="width:100%; margin-top:20px;">
                         <div class="table_thead" style="width:100%;">
                                <div class="table_title" style="width:40%;" ><span style="text-align: left;">来源/用途</span></div>
                                <div class="table_title" style="width:20%;"><span style="text-align: left;">积分变化</span> </div>
                                <div class="table_title"  style="width:20%;"><span style="text-align: left;">日期</span></div>
                                <div class="table_title"  style="width:20%;"><span style="text-align: left;">备注</span></div>
                          </div>
                          <div>
                               <c:forEach items="${Credictlist}" var="Credict">
                                <div class="single_goods">
                                   <div class="content_content">
                                        <table style="width:100%;">
                                            <tr>
                                                <td style="width:40%;">
                                                     <img width="50px" height="50px" src="<%=path%>${Credict.credictsImg}" >
                                                     <span>${Credict.credictsName}</span><br>
                                                </td>
                                                <td style="width:20%;">
                                                    <span>${Credict.credictsChanges}</span>
                                                </td>
                                                <td style="width:20%;">
                                                    <span>${Credict.credictsDate}</span>
                                                </td>
                                                <td style="width:20%;">
                                                    <span>${Credict.credictsRemark}</span>
                                                </td>
                                            </tr>
                                        </table>      
                                   </div>
                                </div>
                                </c:forEach>
                         </div>
                     </div>
                  </div>
                  <!-- 积分明细 end -->
                  
                  <!-- 积分收入 -->
                  <div class="tab-pane fade" id="integral_income">
                                       <div class="totalCount">
                    <span>共有${fn:length(CredictlistIn)}条记录</span>
                  </div>
                    <div class="content_table" style="width:100%; margin-top:20px;">
                         <div class="table_thead" style="width:100%;">
                                <div class="table_title" style="width:40%;" ><span style="text-align: left;">来源/用途</span></div>
                                <div class="table_title" style="width:20%;"><span style="text-align: left;">积分变化</span> </div>
                                <div class="table_title"  style="width:20%;"><span style="text-align: left;">日期</span></div>
                                <div class="table_title"  style="width:20%;"><span style="text-align: left;">备注</span></div>
                          </div>
                          <div>
                               <c:forEach items="${CredictlistIn }" var="credictlistIn">
                                <div class="single_goods">
                                   <div class="content_content">
                                        <table style="width:100%;">
                                            <tr>
                                                <td style="width:40%;">
                                                     <img width="50px" height="50px" src="<%=path%>${credictlistIn.credictsImg}" >
                                                    <span>${credictlistIn.credictsName}</span><br>
                                                </td>
                                                <td style="width:20%;">
                                                    <span>${credictlistIn.credictsChanges}</span>
                                                </td>
                                                <td style="width:20%;">
                                                    <span>${credictlistIn.credictsDate}</span>
                                                </td>
                                                <td style="width:20%;">
                                                    <span>${credictlistIn.credictsRemark}</span>
                                                </td>
                                            </tr>
                                        </table>      
                                   </div>
                                </div>
                               </c:forEach>
                         </div>
                     </div>
                 </div>
                 <!-- 积分收入end -->
                 
                 <!-- 积分支出 -->
                 <div class="tab-pane fade" id="integral_consume">
                                      <div class="totalCount">
                    <span>共有${fn:length(CredictlistOut)}条记录</span>
                  </div>
                    <div class="content_table" style="width:100%; margin-top:20px;">
                         <div class="table_thead" style="width:100%;">
                                <div class="table_title" style="width:40%;" ><span style="text-align: left;">来源/用途</span></div>
                                <div class="table_title" style="width:20%;"><span style="text-align: left;">积分变化</span> </div>
                                <div class="table_title"  style="width:20%;"><span style="text-align: left;">日期</span></div>
                                <div class="table_title"  style="width:20%;"><span style="text-align: left;">备注</span></div>
                          </div>
                          <div>
                                <c:forEach items="${CredictlistOut }" var="credictlistOut">
                                <div class="single_goods">
                                   <div class="content_content">
                                        <table style="width:100%;">
                                            <tr>
                                                <td style="width:40%;">
                                                     <img width="50px" height="50px" src="<%=path%>${credictlistOut.credictsImg}" >
                                                    <span>${credictlistOut.credictsName}</span><br>
                                                </td>
                                                <td style="width:20%;">
                                                    <span>${credictlistOut.credictsChanges}</span>
                                                </td>
                                                <td style="width:20%;">
                                                    <span>${credictlistOut.credictsDate}</span>
                                                </td>
                                                <td style="width:20%;">
                                                    <span>${credictlistOut.credictsRemark}</span>
                                                </td>
                                            </tr>
                                        </table>      
                                   </div>
                                </div>
                                </c:forEach>
                         </div>
                     </div>
                 </div>
                 <!--积分支出end-->
                 
                 <!--积分兑换
                 <div class="tab-pane fade" id="integral_record">
                                       <div class="totalCount">
                    <span>共有4条记录</span>
                  </div>
                    <div class="content_table" style="width:100%; margin-top:20px;">
                         <div class="table_thead" style="width:100%;">
                                <div class="table_title" style="width:10%;" ><span style="text-align: left;">图片</span></div>
                                <div class="table_title" style="width:30%;"><span style="text-align: left;">产品名称</span> </div>
                                <div class="table_title"  style="width:20%;"><span style="text-align: left;">数量</span></div>
                                <div class="table_title"  style="width:20%;"><span style="text-align: left;">兑换积分</span></div>
                                <div class="table_title"  style="width:20%;"><span style="text-align: left;">兑换时间</span></div>
                          </div>
                          <div>
                                <div class="single_goods">
                                   <div class="content_content">
                                        <table style="width:100%;">
                                            <tr>
                                                <td style="width:6%;">
                                                     <img width="50px" height="50px" src="" >
                                                </td>
                                                <td style="width:34%;">
                                                    <span>多鲜 牛奶蛋羹味土司面包</span><br>
                                                </td>
                                                <td style="width:20%;">
                                                    <span>2</span>
                                                </td>
                                                <td style="width:20%;">
                                                    <span>100</span>
                                                </td>
                                                <td style="width:20%;">
                                                    <span>2015-09-01 10:40</span>
                                                </td>
                                            </tr>
                                        </table>      
                                   </div>
                                </div>
                         </div>
                     </div>
                 </div>
         积分兑换end -->
                 
                 
               </div>
            </div>
    </div>
  </body>
</html>
