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
    <title>爱心银行</title>
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
 	<style>
		.col-sm-2 {
		    width:10%;
		}
		.main{
		    margin:10px;
		}
		.multi-radio a {
		height:35px;
		margin-right:10px;
		background-color:#ffffff;
		}
		.multi-radio a:visited {
			text-decoration:none;
		}
		.multi-radio a:link {
			text-decoration:none;
		}
		.table_title{
		dispaly:inline;
		background-color:#f4f5f9;
		float:left;
		font-size:15px;
		height:30px;
		text-align:center;
		padding-top:5px;
		}
		.table_tr{
		height:60px;
		border:1px solid #e7e7eb;
		}
		</style>
 
  </head>
<script type="text/javascript">
	$(function(){
		//把上一页的筛选值传递到本页面

		$("#gender option[value='"+$('#setgender').val() +"']").attr("selected",true);
		$("#state option[value='"+$('#setstate').val() +"']").attr("selected",true);
	});
	
</script>
<script type="text/javascript">
 		$(function(){
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
		$(document).keydown(function(event){
		if(event.keyCode ==13){
			generateParam();
			$("#selectForm").submit();
		}
	});
	function generateParam(){
		var paramStr = $("#activityLaunchTime").val()+"+";
		paramStr += $("#activityStopTime").val()+"+";
		paramStr += $("#gender").val()+"+";
		paramStr += $("#state").val()+"+";
		paramStr += $("#keyWord").val()+"+";
		$("#param").val(paramStr);
}
		
</script>			


  <body>
    <jsp:include page="../commonPage/commonTop.jsp" />
    <jsp:include page="../commonPage/commonLeft.jsp"></jsp:include>
    <input id="setgender" type="hidden" value="${gender}">
    <input id="setstate" type="hidden" value="${state}">
	<!--右侧内容-->
	<div class="main-container">
       <div id="floatHead" class="toolbar-wrap" style="background-color: rgb(244,245,249); margin-bottom: 20px;">
           	<form id="selectForm" action="<%=basePath%>admin/bank/lovingBankList" method="post">
            <input id="param" name="param" type="hidden"/>
            <div class="container-flud">
                <div class="row form-group">
                    <div class="col-sm-1 form-control-static text-right">创建时间</div>
                    <div class="col-sm-3" >
                        <div class="input-group date" id="datetimeStart">
              				 	<input type="text" id="activityLaunchTime" class="form-control" value="${activityLaunchTime}"/>
			                <span class="input-group-addon">
			                    <span class="glyphicon glyphicon-calendar"></span>
			                </span>
			            </div>
                    </div>
                    <div class="col-sm-1 form-control-static" style="width:3%;">至</div>
                    <div class="col-sm-3">
                        <div class="input-group date" id="datetimeStop">
			                <input type="text" id="activityStopTime" class="form-control"  value="${activityStopTime}" />
			              
			                <span class="input-group-addon">
			                    <span class="glyphicon glyphicon-calendar"></span>
			                </span>
			            </div>
                    </div>
                </div>
                <div class="row form-group">
                     <div class="col-sm-1 form-control-static text-right">性别</div>
                      <div class="col-sm-3">
                      <select id="gender"  class="form-control">
                          <option value="3" selected="selected">所有</option>
                          <option value="0">女</option>
                          <option value="1">男</option>
                      </select>
                  </div> 
                </div>
                 <div class="row form-group">
                     <div class="col-sm-1 form-control-static text-right" >状态</div>
                     <div class="col-sm-3">
	                      <select id="state"  class="form-control">
	                          <option value="0" selected="selected">所有</option>
	                          <option value="1">正常</option>
	                          <option value="2">黑名单</option>
	                          <option value="2">销户</option>
	                      </select>
                  	 </div> 
                </div>
                <div class="row form-group">
                    <div class="col-sm-1 form-control-static text-right">关键字</div>
                    <div class="col-sm-3" style="width:80%;">
                        <input id="keyWord" type="text" id="" value="${keyWord}" class="form-control" placeholder="关键字搜索"/>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-sm-1 col-sm-offset-1">
                        <input type="submit" class="btn btn-primary form-control" value="筛选" onclick="generateParam()" />
                    </div>
                </div>
            </div>
       		</form>
        </div>
        <div class="main">
            <div id="myTabContent" class="tab-content" style="border:0px;">
               <div class="tab-pane fade in active" id="selling">
                  <div class="totalCount" style="margin-bottom:10px;">
                    <span>共有${count}条记录</span>
                  </div>
                  <div class="bankPeopleTable">
                    <div style="width:100%;">
                      <div class="table_title" style="width:6%;text-align:center;"><span>头像</span></div>
                      <div class="table_title" style="width:19%;"><span>会员</span></div>
                      <div class="table_title" style="width:25%;text-align:center;"><span>创建时间</span></div>
                      <div class="table_title" style="width:25%;text-align:center;"><span>积分</span></div>
                      <div class="table_title" style="width:25%;text-align:center;"><span>操作</span></div>
                    </div>
                    <table style="width:100%;border:1px solid #e7e7eb;">
                    	<c:forEach items="${userList}" var="user">
                       <tr class="table_tr">
                       		<td style="width:6%;text-align:center;"><img src="<%=path %>/${user[0]}" style="height:50px; width:50px;"/></td>
                       		<td style="width:19%;text-align: center;">${user[1]}</td>
                       		<td style="width:25%;text-align:center;">${user[2]}</td>
                       		<td style="width:25%;text-align:center;">${user[3]}</td>
                       		<td style="width:25%;text-align:center;"><a style="color:#FFFFFF" class="btn btn-primary" href="<%=path%>/admin/bank/huiYanJiFenList?userId=${user[4]}">查看</a></td>
                       	</tr>
                       	</c:forEach>
                    </table>
                  </div>
                   <!--内容底部-->
	            <div class="line20"></div>
	            <jsp:include page="../commonPage/pagination.jsp">
	            	<jsp:param name="url" value="../../admin/getAllLP" />
	            </jsp:include>
	            <!--/内容底部-->
                      
              </div>
            </div>
              
             
       </div>
    </div>
    
  </body>
</html>
