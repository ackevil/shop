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
    <title>添加重大救助项目</title>
    <link href="<%=path %>/admin/skin/css/style.css" rel="stylesheet" />
    <script type="text/javascript" charset="utf-8" src="<%=path %>/admin/skin/js/jquery-1.11.2.min.js"></script>
    <!--  -->
    <script src="<%=path %>/admin/skin/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=path %>/admin/skin/js/bootstrap-datetimepicker.min.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="<%=path %>/admin/skin/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/admin/skin/css/bootstrap-datetimepicker.min.css"/>
    
    
    
    <script src="<%=path %>/admin/skin/uploadify/jquery.uploadify.min.js" type="text/javascript" ></script>
    <link href="<%=path %>/admin/skin/uploadify/uploadify.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" charset="utf-8" src="<%=path %>/admin/baidu_ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/admin/baidu_ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/admin/baidu_ueditor/lang/zh-cn/zh-cn.js"></script>
    <!-- 
     -->
	<link rel="stylesheet" type="text/css" href="<%=path %>/admin/skin/css/bootstrapValidator.min.css"/>
	 
	 <script src="<%=path %>/admin/skin/js/bootstrapValidator.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body class="indexbody">
    <form id="form1" action="/admin/zdjz/saveActivity" method="POST">
        <!--头部-->
        <jsp:include page="../commonPage/commonTop.jsp" />
        <!--/头部--> 
        <!--左侧导航-->
        <jsp:include page="../commonPage/commonLeft.jsp"></jsp:include>
        <!--/左侧导航-->  
        <!--右侧内容-->
        <div class=" tab1 main-container mainbody " id="tab1">
                <div class="location" style="height: 30px;">
                     <a href="" class="home"><i></i><span >重大救助</span></a>
                    <i class="arrow"></i><span>添加</span>
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
                <div class="container-flud">
                	<div class="row" style="margin: 10px; ">
	                		<div class=" row form-group">
	                			<div class="form-control-static col-sm-1">筹款目标</div>
	                			<div class="col-sm-2">
	                			<input type="hidden" name="activityId" value="${activity.activityId}"/>
	                			<input type="text" name="activityTotalFund" value="${activity.activityTotalFund}" placeholder="请输入整数" class="form-control" />
	                			
	                			</div>
	                			<div class="form-control-static col-sm-1">元</div>
	                			<div class="form-control-static col-sm-1">筹款时间</div>
	                			<div class="col-sm-2 ">
	                				 <div class="input-group date" id="datetimeStart">
	                				 	<input type="text" id="activityLaunchTime" class="form-control" name="activityLaunchTime" value="<fmt:formatDate value='${activity.activityLaunchTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
						                <span class="input-group-addon">
						                    <span class="glyphicon glyphicon-calendar"></span>
						                </span>
						            </div>
	                			</div>
								
								<div class="form-control-static col-sm-1">结束时间</div>
	                			<div class="col-sm-2">
	                				<div class="input-group date" id="datetimeStop">
						                <input type="text" id="activityStopTime" class="form-control" name="activityStopTime" value="<fmt:formatDate value='${activity.activityStopTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
						              
						                <span class="input-group-addon">
						                    <span class="glyphicon glyphicon-calendar"></span>
						                </span>
						            </div>
	                			</div>
								
	                		</div>
                		
                			<div class="form-group row">
                					<div class="form-control-static col-sm-1">项目标题</div>
			                		<div class="col-sm-3">
			                		
			                			<input type="text" name="activityName"value="${activity.activityName}" placeholder="请输入项目标题" class="form-control" />
			                		</div>
			                		<div class="form-control-static col-sm-1">状态</div>
			                		<div class="col-sm-2">
			                			<select class="form-control" name="activityState">
											  <option value="0" ${activity.activityState==0?"selected='selected'":"" }>未发布</option>
											  <option value="1" ${activity.activityState==1?"selected='selected'":"" } >进行中</option>
											  <option value="2" ${activity.activityState==2?"selected='selected'":"" }>已结束</option>
										</select>
			                		</div>
                			</div>
                			<div class="form-group row">
                					<div class="form-control-static col-sm-1">标题图片</div>
			                		<div class="col-sm-4">
			                			<input type="file" id="file_upload" name=""  class="form-control"  />
			                			<input type="text" id="file_url" value="${activity.activityMainPic}" name="activityMainPic" class="form-control" readonly />
			                		</div>
			                		<div class="form-control-static col-sm-1 text-right" >图片预览</div>
			                		<div class="col-sm-2">
			                			<img id="mainPic" src="${activity.activityMainPic}" style="width: 100px; height: 100px;"/>
			                		</div>
                			</div>
                			<div class="form-group row">
                				<div class="col-sm-1 form-control-static">
                					项目详情：
                				</div>
                				<div class="col-sm-7">
                					<input id="activityDetailInfo"  name="activityDetailInfo" type="hidden" value="" />
                					<script type="text/plain" id="myEditor" style="width:1000px;height:400px;">
								    	${activity.activityDetailInfo}
									</script>
									<script type="text/javascript">
									    //实例化编辑器
									    var ue = UE.getEditor('myEditor');
									    
									</script>
									
                				</div>
                				
                			</div>
                	</div>
                </div>
            </div>
            <div class="page-footer" style="text-align: center;">
                <div class="btn-wrap" style="position: static;">
                	<input type="hidden" name="activitySuportNum" value="${activity.activitySuportNum}">
                	<input type="hidden" name="activityRaised" value="${activity.activityRaised }">
                	<input type="hidden" name="activityIsOrder" value="${activity.activityIsOrder==null?'false':dsze.dszeIsOrder }">
                	<input type="hidden" name="activityIntime" value="<fmt:formatDate value='${activity.activityIntime }' pattern='yyyy-MM-dd HH:mm:ss'/>">
                    <input type="hidden" name="activityOrderTime" value="<fmt:formatDate value='${activity.activityOrderTime }' pattern='yyyy-MM-dd HH:mm:ss'/>">
                   
                    <input type="submit" name="btnSave" value="提交保存" id="btnSave" class="btn btn-primary "  onclick="$('#activityDetailInfo').val(ue.getContent());"/>
                    <input type="button" onclick="window.location.href='<%=basePath %>admin/zdjz/activityList'" class="btn btn-primary " value="返回上一页"/>
                </div>
            </div>
        </div>
        <!--/右侧内容-->
    </form>
     <script type="text/javascript">
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
        $('#form1').bootstrapValidator('revalidateField', 'activityLaunchTime');
});
$('#datetimeStop')
    .on('changeDate show', function(e) {
        // Revalidate the date when user change it
        $('#form1').bootstrapValidator('revalidateField', 'activityStopTime');
});
 

</script>
	<script type="text/javascript">
		$(function() {
			
		
			$('#file_upload').uploadify({
				'swf'      : '<%=path %>/admin/skin/uploadify/uploadify.swf',
				'uploader' : '<%=path %>/admin/uploadify',
				'buttonText': '上传图片',
				'fileTypeDesc': 'Image Files',
                //允许上传的文件后缀
                'fileTypeExts': '*.gif; *.jpg; *.png',
                'auto': true,
                'multi': false,
                 onUploadError:function() {
        	  			 alert("上传失败");   
           			},
           		 onUploadSuccess:function(file, data, response){
							//alert("上传成功,data="+data+",file="+file+",response="+response);      
			//				ajaxLoadImgList();
						   data = $.parseJSON(data);
						   if(data.error == '0') {
							   $("#mainPic").attr("src",data.filePath);
							   $("#file_url").val(data.filePath);
							   
						   } 
						   upImage();
          			 },
			});
		});
		
		
	</script>
	
	<script type="text/javascript">
    $('#form1').bootstrapValidator({
        
        fields: {
            activityTotalFund: {
                message: '输入金额不合法',
                validators: {
                    notEmpty: {
                        message: '金额不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 16,
                        message: '请输入1-16位整数金额'
                    },
                    regexp: {
                        regexp: /^[1-9]\d*$/,
                        message: '请输入正确金额'
                    },
                    between:{
                    	min:1,
                    	max:1000000000,
                    	message:'请数去1-10000000000间的整数'
                    }
                }
            },
            
            activityName:{
            	validators:{
            		notEmpty:{
            			message:'标题不能为空'
            		}
            	}
            },
            activityLaunchTime:{
            	validators: {
                    date: {
                        format: 'YYYY-MM-DD h:m:s',
                        message: '日期不合法'
                    },
                    notEmpty:{
            			message:'日期不能为空'
            		}
                }
            },
            activityStopTime:{
            	validators:{
            		  date: {
                        format: 'YYYY-MM-DD h:m:s',
                        message: '日期不合法'
                   	  },
                   	  notEmpty:{
            			message:'日期不能为空'
            		}
            	}
            },
            activitySuportNum:{
	            validators:{
	            	notEmpty:{
	            		message:'人数不能为空'
	            	}
	            }
            
            }
            
        }
    });

		
	</script>
</body>
</html>
