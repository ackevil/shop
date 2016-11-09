<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>爱购商品</title>
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
           
		$("#datetimeStart").datetimepicker({
		    format: "yyyy-mm-dd hh:ii:ss",
		    autoclose: true,
		    minView: "hour",
		    maxView: "decade",
		    todayBtn: true,
		    pickerPosition: "bottom-left"
		}).on("click",function(ev){
		    $("#datetimeStart").datetimepicker("setEndDate", $("#productStopTime").val());
		});
		
		$("#datetimeStop").datetimepicker({
		    format: "yyyy-mm-dd hh:ii:ss",
		    autoclose: true,
		    minView: "hour",
		    maxView: "decade",
		    todayBtn: true,
		    pickerPosition: "bottom-left"
		}).on("click", function (ev) {
		    $("#datetimeStop").datetimepicker("setStartDate", $("#productLaunchTime").val());
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
			       	"productIds":str,
			       	},
			       type:"POST",
			       url:"/admin/aigou/goodsDelete",
			       error:function(data){
			           alert("出錯了！");
			       },
			       success:function(data){
			           if (data == "true") {
			           	alert("操作完成"),
			                window.location.href="/admin/aigou/goodsManager";
			           }
			           else {
			                alert("出錯了！");
			           }
			       }
			   });
		} 
		
		function changeSelected(){
			var str="";
			$(":checkbox:checked").each(function(){
				str+=this.value+";";
			});
			$.ajax({
			       data: {
			       	"productIds":str,
			       	},
			       type:"POST",
			       url:"/admin/aigou/goodsChange",
			       error:function(data){
			           alert("出錯了！");
			       },
			       success:function(data){
			           if (data == "true") {
			           	alert("操作完成"),
			                window.location.href="/admin/aigou/goodsManager";
			           }
			           else {
			                alert("出錯了！");
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
    height: 120px;
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
       <div id="floatHead" class="toolbar-wrap" style="background-color: rgb(244,245,249); margin-bottom: 20px;">
            <form action="/admin/aigou/goodsManager" method="post">
            <div class="container-flud">
                 <div class="row form-group">
                	
                    <div class="col-sm-1 form-control-static text-right">发布时间</div>
                    <div class="col-sm-3" >
                        <div class="input-group date" id="datetimeStart">
              				 	<input type="text" id="productLaunchTime" name="productLaunchTime" value="<fmt:formatDate value='${productLaunchTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" class="form-control" />
			                <span class="input-group-addon">
			                    <span class="glyphicon glyphicon-calendar"></span>
			                </span>
			            </div>
                    </div>
                    <div class="col-sm-1 form-control-static" style="width:3%;">至</div>
                    <div class="col-sm-3">
                        <div class="input-group date" id="datetimeStop">
			                <input type="text" id="productStopTime"  name="productStopTime" value="<fmt:formatDate value='${productStopTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" class="form-control" />
			              
			                <span class="input-group-addon">
			                    <span class="glyphicon glyphicon-calendar"></span>
			                </span>
			            </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-sm-1 form-control-static text-right">类别</div>
                    <div class="col-sm-3">
                       <select id="productCategoryId" name="type" class="form-control">
                       		<option value="">所有</option>
       						 <c:if test="${not empty typeList}">
       						 	<c:forEach items="${typeList}" var="type">
       						 		<option value="${type.productTypeId}" ${typeSelect == type.productTypeId?"selected":"" } >${type.productTypeName}</option>
       						 	</c:forEach>
       						 </c:if>
							  
						</select>
                    </div>
                </div>
                  
                <div class="row form-group">
                    <div class="col-sm-1 form-control-static text-right">状态</div>
                    <div class="col-sm-3">
                        <select id="state" name="state" class="form-control">
                          <option value="1" ${state == 1?"selected":"" }>出售中的商品</option>
                          <option value="2" ${state == 2?"selected":"" }>已售完的商品</option>
                          <option value="3" ${state == 3?"selected":"" }>仓库中的商品</option>
                        </select>
                    </div>
                </div>
            
                
                <div class="row form-group">
                    <div class="col-sm-1 form-control-static text-right">关键字</div>
                    <div class="col-sm-3" style="width:80%;">
                        <input id="keyWord" name="key" type="text" id="" value='${key==null?"":key }' class="form-control" placeholder="关键字搜索"/>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-sm-1 col-sm-offset-1">
                        <input type="submit" class="btn btn-primary form-control" value="筛选"  />
                    </div>
                </div>
            </div>
            </form>
        </div>
        <div class="main">
            <ul id="myTab" class="nav nav-tabs">
            	<c:if test="${empty state }">
            		 <li class="active"><a href="#selling" data-toggle="tab"> 出售中的商品</a></li>
            	</c:if>
            	<c:if test="${not empty state }">
            		<c:if test="${state==1 }">
            		  <li class="active"><a href="#selling" data-toggle="tab"> 出售中的商品</a></li>
            		</c:if>
            		<c:if test="${state==2 }">
            		  <li class="active"><a href="#selling" data-toggle="tab"> 已售完的商品</a></li>
            		</c:if>
            		<c:if test="${state==3 }">
            		  <li class="active"><a href="#selling" data-toggle="tab"> 仓库中的商品</a></li>
            		</c:if>
            	</c:if>
            </ul>
            <div id="myTabContent" class="tab-content">
               <div class="tab-pane fade in active" id="selling">
                  <div class="totalCount text-right">
                    <!--  <span>共有${pages.maxRows}条记录，已选择了0条记录</span>     -->
                    <a href="<%=basePath %>admin/aigou/releaseGoods"><span  class="btn btn-primary">发布商品</span></a>
                  </div>
                  <div class="content_table" style="width:100%; margin-top:20px;">
                         <div class="table_thead" style="width:100%;">
                                <div class="table_title" style="width:5%;"><input type="checkbox" onclick="checkAll(this)" value="全选"/>全选</div>
                                <div class="table_title"  style="width:5%;">商品</div >
                                <div class="table_title" style="width:22%;" ><span style="text-align: left;">价格</span></div>
                                <div class="table_title" style="width:16%;"><span style="text-align: center;">类别</span> </div>
                                <div class="table_title"  style="width:16%;"><span style="text-align: center;">库存</span></div>
                                <div class="table_title"  style="width:14%;"><span style="text-align: center;">总销量</span></div>
                                <div class="table_title" style="width:12%;"><span style="text-align: center;">创建时间</span></div>
                                <div class="table_title"  style="width:10%;"><span style="text-align: center;">操作</span></div>
                          </div>
                          <div style="margin-top:30px;">
                          		<c:forEach items="${productList1 }" var="product1">
                                <div class="single_goods">
                                   <div class="content_title">
                                       <div class="title_word" style="width:100px;margin-left:90%;padding:10px;"><span style="color:blue"><a href="<%=basePath%>admin/aigou/editGoods?id=${product1.productId}">编辑</a></span><span style="color:red">   &nbsp;&nbsp;<a href="<%=basePath%>admin/aigou/delGoods?id=${product1.productId}">删除</a></span></div>
                                   </div>
                                   <div class="content_content">
                                        <table style="width:100%;">
                                            <tr>
                                                <td style="width:1%;">
                                                     <span class="checkall" style="vertical-align: middle;"><input type="checkbox" value="${product1.productId }" /></span>
                                                </td>
                                                <td style="width:3%;">
                                                    <img width="50px" height="50px" src="${product1.productMainpicPath}" >
                                                </td>
                                                <td style="width:20%;">
                                                    <span>&nbsp; ${product1.productName}</span><br>
                                                    <span style="color:red;"> &nbsp; ￥${product1.productPrice}</span>
                                                </td>
                                                <td style="width:15%;">
                                                    <span>
                                                    <c:if test="${not empty typeList}">
						       						 	<c:forEach items="${typeList}" var="type">
						       						 		${product1.productCategoryId == type.productTypeId ? type.productTypeName:""}
						       						 	</c:forEach>
						       						 </c:if>
													</span>
                                                </td>
                                                <td style="width:15%;">
                                                    <span>${product1.productStock}</span>
                                                </td>
                                                <td style="width:10%;">
                                                    <span>${product1.productSaleNum}</span>
                                                </td>
                                                <td style="width:10%;">
                                                    <span>${product1.productIntime}</span>
                                                </td>
                                                <td style="width:10%;">
                                                    <span><a href="<%=basePath%>admin/aigou/changeState?id=${product1.productId}"><button type="button" class="btn btn-primary">${state==3?"上架":"下架" }</button></a></span>
                                                </td>
                                            </tr>
                                        </table>      
                                   </div>
                                </div>
                                </c:forEach>
                         </div>
                         <jsp:include page="goodsMangerPagination.jsp">
							<jsp:param name="url" value="/admin/aigou/goodsManger" />
							<jsp:param name="pages" value="${pages1}" />
						 </jsp:include>
                   </div>
                   <div class="container-fluid" style="margin-top:10px;">
                    	<div class="row">
                    		<div class="col-md-1">
                    			<button class="btn btn-primary form-control" onclick="changeSelected()">${state==3?"上架":"下架" }</button>
                    		</div>
                    		<div class="col-md-1">
                    			<button class="btn btn-primary form-control" onclick="deleteSelected()">删除</button>
                    		</div>
                    	</div>
                   </div>

               </div>
               
               </div>
            	
       </div>
    </div>
    
  </body>
</html>
