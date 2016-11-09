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
    <title>衣旧有爱产品管理</title>
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
<script>
	$(function(){
		displayType1($("#ctInfo").val());
		//把上一页的筛选值传递到本页面

		$("#type1 option[value='"+$('#settype1').val() +"']").attr("selected",true);
		$("#state option[value='"+$('#setstate').val() +"']").attr("selected",true);
	});
	function displayType1(msg){
		var node = "<option value='0' >衣服类别</option>";
		$("#type1").append(node);
		var splitMsg = msg.split("|");
	    for (var i = 1; i <=splitMsg[0]; i++) {
	        message = splitMsg[i].split("├");
	        var id = message[0];
            var name = message[1];
	        node = "<option value='"+ id +"' >"+ name +"</option>";
	        $("#type1").append(node);
	    }
	}
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
		function deleteChecked(){
			var str="";
			$(":checkbox:checked").each(function(){
				str+=this.value+";";
			})
			$.ajax({
			       data: {
			       	"clothIds":str,
			       	},
			       type:"POST",
			       url:"<%=basePath%>admin/yjya/deleteCloths",
			       error:function(data){
			           alert("出錯了！");
			       },
			       success:function(data){
			           if (data == "true") {
			           	alert("刪除成功"),
			                window.location.href="<%=basePath%>admin/yjya/commodityManager";
			           }
			           else {
			                alert("出錯了！");
			           }
			       }
			   });
			
    		
    	}
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
	paramStr += $("#type1").val()+"+";
	paramStr += $("#state").val()+"+";
	paramStr += $("#keyWord").val()+"+";
	$("#param").val(paramStr);
}
		
</script>	
		<style>
			.col-sm-2 {
			    width:10%;
			}
			.main{
			    margin:15px;
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
			    background-color:#f5fbff;
			}
			
			.content_content {
			    padding: 15px;
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
			.title_left{
			width:85%;
			float:left;
			padding-top:8px;
			}
			.btn_delete{
			width:50%;
			float:left;
			}
			.btn_delete input{
			width:60px;
			height:30px;
			background-color:#f52323;
			border:1px solid #f52323;
			color:#ffffff;
			margin-top:15px;
			margin-left:10px;
			}
			.commodity_select{
			width:50%;
			float:right;
			}
		</style>
  <body>
    <jsp:include page="../commonPage/commonTop.jsp" />
    <jsp:include page="../commonPage/commonLeft.jsp"></jsp:include>
    <input id="ctInfo" type="hidden" value="${ctInfo}"/>
    <input id="settype1" type="hidden" value="${type1}">
    <input id="setstate" type="hidden" value="${state}">
    <div class="main-container">
       <div id="floatHead" class="toolbar-wrap" style="background-color: rgb(244,245,249); margin-bottom: 20px;">
            <form id="selectForm" action="<%=basePath %>admin/yjya/commodityManager" method="post">
            <input type="hidden" name="param" id="param"/>
            <div class="container-flud">
                <div class="row form-group">
                	
                    <div class="col-sm-1 form-control-static text-right">发布时间</div>
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
                    <div class="col-sm-1 form-control-static text-right">类别</div>
                    <div class="col-sm-3">
                        <select id="type1" class="form-control">
                        </select>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-sm-1 form-control-static text-right">状态</div>
                    <div class="col-sm-3">
                        <select id="state"  class="form-control">
                          <option value="5" selected="selected">所有</option>
                          <option value="0">待领取</option>
                          <option value="1">待邮寄</option>
                          <option value="2">待收货</option>
                          <option value="3">待评价</option>
                          <option value="4">已完成</option>
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
            <div id="myTabContent" class="tab-content">
               <div class="tab-pane fade in active" id="selling">
                    <div class="content_table" style="width:100%; margin-top:20px;">
                         <div class="table_thead">
                                <div class="table_title" style="width:5%;"><input type="checkbox" onclick="checkAll(this)"value="全选"/></div>
                                <div class="table_title"  style="width:10%;">图片</div >
                                <div class="table_title" style="width:25%;" ><span style="text-align: left;">商品名称</span></div>
                                <div class="table_title" style="width:15%;"><span style="text-align: center;">类别</span> </div>
                                <div class="table_title"  style="width:10%;"><span style="text-align: center;">捐赠者</span></div>
                                <div class="table_title" style="width:10%;"><span style="text-align: center;">领取者</span></div>
                                <div class="table_title" style="width:10%;"><span style="text-align: center;">状态</span></div>
                                <div class="table_title"  style="width:15%;"><span style="text-align: center;">排序</span></div>
                          </div>
                          <div style="margin-top:30px;">
                                <c:forEach items="${clothInfoList}" var="clothInfo">
                                	<div class="single_goods">
                                   <div class="content_title">
                                       <div class="title_left"><span>发布时间:${clothInfo[1]}</span></div>
                                       <!-- <div class="title_word" style="width:10%;float:left;padding-top:8px;"><span style="color:blue">评论</span>-<span style="color:blue">查看详情</span></div> -->
                                   </div>
                                   <div class="content_content">
                                        <table style="width:100%;">
                                            <tr>
                                                <td style="width:5%;">
                                                    <span class="checkall" style="vertical-align: middle;"><input type="checkbox" name="checkbox" value="${clothInfo[0]}" /></span>
                                                </td>
                                                <td style="width:10%;">
                                                    <img width="50px" height="50px" src="<%=path%>${clothInfo[2]}" >
                                                </td>
                                                <td style="width:25%;">
                                                    <span>${clothInfo[3]}</span><br>
                                                </td>
                                                <td style="width:15%;">
                                                    <span>${clothInfo[4]}</span>
                                                </td>
                                                <td style="width:10%;">
                                                    <span>${clothInfo[6]}</span>
                                                </td>
                                                <td style="width:10%;">
                                                    <span>${clothInfo[7]}</span>
                                                </td>
                                                 <td style="width:10%;">
                                                    <span>
                                                    <c:if test="${clothInfo[5]==0}">
                                                   	 未领取
                                                    </c:if>
                                                    <c:if test="${clothInfo[5]==1}">
                                                   	 待邮寄
                                                    </c:if>
                                                    <c:if test="${clothInfo[5]==2}">
                                                   	 待收货
                                                    </c:if>
                                                    <c:if test="${clothInfo[5]==3}">
                                                   	 待评价
                                                    </c:if>
                                                    <c:if test="${clothInfo[5]==4}">
                                                   	 已完成
                                                    </c:if>
                                                    </span>
                                                </td>
                                                <td style="width:15%;">
                                                    <span><button type="button" class="btn btn-primary" onclick="stick(${clothInfo[0]})">置顶</button></span>
                                                </td>
                                            </tr>
                                        </table>      
                                   </div>
                                </div>
                             </c:forEach>
                         </div>
                     </div>
                     <div ><input type="button" onclick="deleteChecked()" class="btn btn-primary" value="删除"/></div>
                  </div>
               </div>
             <!--内容底部-->
            <div class="line20"></div>
            <jsp:include page="../commonPage/pagination.jsp">
                <jsp:param name="url" value="../../admin/yjya/commodityManager" />
                
            </jsp:include>
            <!--/内容底部-->
            </div>
            <script type="text/javascript">
            	function stick(clothId){
            		$.ajax({
                        data: {"clothId" : clothId},
                        type:"POST",
                        url:"stick_do",
                        error:function(data){
                            debugger;
                            alert("出錯了！");
                        },
                        success:function(data){
                            if (data == "true") {
                                 window.location.href="../../admin/yjya/commodityManager";
                            }
                            else {
                                 alert("出錯了！");
                            }
                        }
                    });
            	
            	}
            </script>
    </div>
  </body>
</html>
