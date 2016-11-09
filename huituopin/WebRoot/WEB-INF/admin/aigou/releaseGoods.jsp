<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>发布商品</title>
    <link href="<%=path %>/admin/skin/css/style.css" rel="stylesheet" />
    <link href="<%=path %>/admin/skin/css/pagination.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" charset="utf-8" src="<%=path %>/admin/skin/js/jquery-1.11.2.min.js"></script>
    
    <script src="<%=path %>/admin/skin/uploadify/jquery.uploadify.min.js" type="text/javascript" ></script>
    <link href="<%=path %>/admin/skin/uploadify/uploadify.css" type="text/css" rel="stylesheet">
    
    <script type="text/javascript" charset="utf-8" src="<%=path %>/admin/baidu_ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/admin/baidu_ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/admin/baidu_ueditor/lang/zh-cn/zh-cn.js"></script>
    
    
    
    <script src="<%=path %>/admin/skin/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=path %>/admin/skin/js/bootstrap-datetimepicker.min.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="<%=path %>/admin/skin/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/admin/skin/css/bootstrap-datetimepicker.min.css"/>
 
	 
 
 
  </head>
<style>
	.row{
		margin-top:15px;
		margin-bottom:15px;
	}
	
</style>

 
  <body style="font:黑体">
      <jsp:include page="../commonPage/commonTop.jsp" />
      <jsp:include page="../commonPage/commonLeft.jsp"/>
       
        <div class="main-container">
        
       <div class="container-fluid" style="background-color: rgb(244,245,249);padding-top:10px;padding-bottom:10px;">
       		<div class="row">
       			<div class=" form-control-static col-md-1 text-center">
       				
       			</div>
       			<div class="col-md-9 text-center">
       				<div class=" form-control-static" style="font-size:16px">发布商品</div>
       			</div>
       			<div class="col-md-2">
       				<button id="releaseProduct" class="btn btn-primary">上架</button>
       			</div>
       		</div>
       </div>
       <div style="height: 15px"></div>
       <form id="form">
       <div class="container-fluid" style="background-color: rgb(244,245,249);margin:10px">
       		<div class="row">
       			<div class="col-md-2">
       				<div class="row">
       					<div class="col-md-12 text-center form-control-static">
       					商品信息
       					</div>
       				</div>
       				
       			</div>
       			<div class="col-md-10">
       				<div class="row">
       					<div class="col-md-3 text-right">
	       					<div class="form-control-static">
	       					<span style="color: rgb(255,0,0)">&nbsp(必填)</span>商品名称
	       					</div>
	       				</div>
       					<div class="col-md-5">
       					<input id="productName"  name="productName" type="text" placeholder="商品名称" class="form-control"/>
       					</div>
       				</div>
       				<div class="row">
       					<div class="col-md-3 text-right">
	       					<div class="form-control-static">
	       					<span style="color: rgb(255,0,0)">&nbsp(必填)</span>所属类别
	       					</div>
	       				</div>
       					<div class="col-md-3">
       						<select id="productCategoryId" name="productCategoryId" class="form-control">
       						 <c:if test="${not empty typeList}">
       						 	<c:forEach items="${typeList}" var="type">
       						 		<option value="${type.productTypeId}">${type.productTypeName}</option>
       						 	</c:forEach>
       						 </c:if>
							  
							</select>
       					</div>
       				</div>
       				<div class="row">
       					<div class="col-md-3 text-right">
	       					<div class="form-control-static">
	       					<span style="color: rgb(255,0,0)">&nbsp(必填)</span>单价
	       					</div>
	       				</div>
       					<div class="col-md-3">
       						<input id="productPrice" name="productPrice" type="text" class="form-control"/>
       					</div>
       					<div class="col-md-1" >
       						<div class="form-control-static">元</div>
       					</div>
       				</div>
       				<div class="row">
       					<div class="col-md-3 text-right">
	       					<div class="form-control-static">
	       					最大兑换积分
	       					</div>
	       				</div>
       					<div class="col-md-3">
       						<input id="productCredit" name="productCredit" value="0" type="text" class="form-control"/>
       					</div>
       				</div>
       				<div class="row">
       					<div class="col-md-3 text-right">
	       					<div class="form-control-static">
	       					图片
	       					</div>
	       				</div>
       					<div class="col-md-3">
       						<img  id="mainPic" class="img-rounded"  src="" style="height:140px;width: 140px"/>
       					</div>
       					<div class="col-md-3" >
       						<input type="file" id="file_upload" class="form-control"/>
       						<input type="text" id="productMainpicPath" placeholder="图片路径" name="productMainpicPath" class="form-control" readonly="readonly"/>
       					</div>
       				</div>
       				<div class="row">
       					<div class="col-md-3 col-md-offset-3">
       						<input type="file" id="mul_file_upload" class="form-control"/>
       					</div>
       					
       				</div>
       				<div class="row">
       					<div class="col-md-3 text-right">
	       					<div class="form-control-static">
	       					轮播图
	       					<input type="hidden" id="picture1Path"  />
	       					<input type="hidden" id="picture2Path"  />
	       					<input type="hidden" id="picture3Path"  />
	       					</div>
	       				</div>
       					<div class="col-md-2">
       						<img  id="picture1" class="img-rounded"  src="" style="height:140px;width: 140px"/>
       					</div>
       					<div class="col-md-2">
       						<img  id="picture2" class="img-rounded"  src="" style="height:140px;width: 140px"/>
       					</div>
       					<div class="col-md-2">
       						<img  id="picture3" class="img-rounded"  src="" style="height:140px;width: 140px"/>
       					</div>
       				</div>
 
       				
       			</div>
       		</div>
       		<div class="row">
       			<div class="col-md-2">
       				<div class="row">
       					<div class="col-md-12 text-center form-control-static">
       					商品详情:图文描述
       					</div>
       				</div>
       			</div>
       			<div class="col-md-8">
       				<input id="productDetail"  type="hidden" value="" />
                		<script type="text/plain" id="myEditor" style="width:100%;height:400px;">
						</script>
						<script type="text/javascript">
									    //实例化编辑器
							var ue = UE.getEditor('myEditor');
									    
						</script>
       			</div>
       		</div>
       </div>
       
       <div class="container-fluid" style="background-color: rgb(244,245,249);margin:10px">
       		<div class="row">
       			<div class="col-md-2">
       				<div class="row">
       					<div class="col-md-12 text-center form-control-static">
       						库存/规格
       					</div>
       				</div>
       				
       			</div>
       			<div class="col-md-10">
       				
       				<div class="row">
       					<div class="col-md-3 text-right">
	       					<div class="form-control-static">
	       					   商品规格
	       					</div>
	       				</div>
       					<div class="col-md-4">
       						<input  id="productSpecificationValue" class="form-control" placeholder="请输入相关规格" />
							 
       					</div>
       				</div>
       				<div class="row">
       					<div class="col-md-3 text-right">
	       					<div class="form-control-static">
	       					<span style="color: rgb(255,0,0)">&nbsp(必填)</span>总库存
	       					</div>
	       				</div>
       					<div class="col-md-3">
       						<input id="productStock" name="productStock" type="text" class="form-control"/>
       					</div>
       					<div class="col-md-3" >
       						<input id="productIsDispalyStock" name="productIsDispalyStock" type="checkbox" value="0" >
       						页面不显示库存数量
       					</div>
       				</div>
       				
       				
       				
       			</div>
       		</div>
       </div>
       
      
       
       <div class="container-fluid" style="background-color: rgb(244,245,249);margin:10px">
       		<div class="row">
       			<div class="col-md-2">
       				<div class="row">
       					<div class="col-md-12 text-center form-control-static">
       					物流/其它
       					</div>
       				</div>
       				
       			</div>
       			<div class="col-md-10">
       				
       				<div class="row">
       					<div class="col-md-3 text-right">
	       					<div class="form-control-static">
	       					   <span style="color: rgb(255,0,0)">&nbsp(必填)</span>运费设置
	       					</div>
	       				</div>
	       				<div class="col-md-2">
	       					<div class="form-control-static">
	       						<input  name="productFreightType" type="radio" value="0" checked/>统一运费
	       					</div>
	       				</div>
	       				<div class="col-md-2">
	       					<input id="productFreightSame" name="productFreightSame" type="text" class="form-control">
	       				</div>
	       				<div class="col-md-1 text-left">
	       					<div class="form-control-static">
	       						元
	       					</div>
	       				</div>
       				</div>
       				<div class="row">
       					<div class="col-md-offset-3 col-md-2">
       						<div class="form-control-static">
	       						<input name="productFreightType" type="radio" value="1"/>运费模板
	       					</div>
       					</div>
       					<div class="col-md-2">
       						<select id="productFreightTemplateId" name="productFreightTemplateId" class="form-control">
							  <c:if test="${not empty LogisticsList }">
							  	<c:forEach items="${LogisticsList }" var="Logistics">
							  		 <option value="${Logistics.logisticsId}">${ Logistics.logisticsName}</option>
							  	</c:forEach>
							  </c:if>
							  <c:if test="${empty LogisticsList }">
							  		 <option value="0" checked>0</option>
							  </c:if>
							</select>
       					</div>
       					<div class="col-md-1">
       						<div class="form-control-static">
       							<a class="text text-info">新建</a>
       						</div>
       					</div>
       				</div>
       				<div class="row">
       					<div class="col-md-3 text-right">
	       					<div class="form-control-static">
	       					   每人限购
	       					</div>
	       				</div>
       					<div class=" col-md-2">
	       					<input id="productStopbuyNum" value="0" type="text" class="form-control" placeholder="0"/>
       					</div>
       					
       					<div class="col-md-2">
       						<div class="form-control-static">
       							<p class="text-muted">0代表不限购</p>
       						</div>
       					</div>
       				</div>
       				<div class="row">
       					<div class="col-md-3 text-right">
	       					<div class="form-control-static">
	       					   <span style="color: rgb(255,0,0)">&nbsp(必填)</span>开售时间
	       					</div>
	       				</div>
	       				<div class="col-md-2">
	       					<div class="form-control-static">
	       						<input name="productOnsaleTimeType"  type="radio" value="1" checked/>立即开售
	       					</div>
	       				</div>
	       				
       				</div>
       				<!-- 
       				<div class="row">
       					
	       				<div class="col-md-2 col-md-offset-3">
	       					<div class="form-control-static">
	       						<input name="productOnsaleTimeType" type="radio" value="2"/>定时开售
	       					</div>
	       				</div>
	       				<div class="col-md-3">
	       					<div class="input-group date" id="datetimeSail">
							      <input id="productOnsaleTiming" type="text" class="form-control"/>
							      <span class="input-group-addon">
							        <span class="glyphicon glyphicon-calendar"></span>
							      </span>
							 </div>
	       				</div>
	       				
       				</div>
       				 --> 
       				<div class="row">
       					
	       				<div class="col-md-2 col-md-offset-3">
	       					<div class="form-control-static">
	       						<input name="productOnsaleTimeType"  type="radio" value="3"/>放入仓库
	       					</div>
	       				</div>
	       				
       				</div>
       				
       				
       				
       			</div>
       		</div>
       		<div style="height: 50px"></div>
       </div>
  
  		
  		</form>
  	</div>
  	<script type="text/javascript">
		$(function() {
			$('#file_upload').uploadify({
				'swf'      : '<%=path %>/admin/skin/uploadify/uploadify.swf',
				'uploader' : '<%=path %>/admin/uploadify',
				'buttonText': '上传主图片（单选）',
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
							   $("#productMainpicPath").val(data.filePath);
						   } 
						   upImage();
          			 },
			});
			var i=1;
			$('#mul_file_upload').uploadify({
				'swf'      : '<%=path %>/admin/skin/uploadify/uploadify.swf',
				'uploader' : '<%=path %>/admin/uploadify',
				'buttonText': '上传轮播图（多选）',
				'fileTypeDesc': 'Image Files',
                //允许上传的文件后缀
                'fileTypeExts': '*.gif; *.jpg; *.png',
                'auto': true,
                'multi': true,
                 onUploadError:function() {
        	  			 alert("上传失败");   
           			},
           		 onUploadSuccess:function(file, data, response){
							//alert("上传成功,data="+data+",file="+file+",response="+response);      
			//				ajaxLoadImgList();
						   data = $.parseJSON(data);
						   if(data.error == '0') {
							   $("#picture"+i).attr("src",data.filePath);
							   $("#picture"+i+"Path").val(data.filePath);
							   i=i+1;
							   if(i>3){
							   	i=1
							   }
						   } 
          			 },
			});
			
			
			
			$("#datetimeSail").datetimepicker({
			    format: "yyyy-mm-dd hh:ii:ss",
			    autoclose: true,
			    minView: "hour",
			    maxView: "decade",
			    todayBtn: true,
			    pickerPosition: "top-left"
			});
			
			$("#releaseProduct").click(function(){
			
				 if ($("#productIsDispalyStock").is(":checked"))
				 {
				 	$("#productIsDispalyStock").val(0); //0 不显示
				 }else{
				 	$("#productIsDispalyStock").val(1); //1显示
				 }
				 
				
				 
				 
				if($.trim($("#productName").val())=="")
					{alert("产品名称不能为空");
						$("#productName").focus();
						return;
					}
				if($("#productCategoryId").val()=="")
					{alert("产品分类不能为空");
						$("#productCategoryId").focus();
						return;
					}
					if($.trim($("#productPrice").val())=="")
					{alert("产品价格不能为空");
						$("#productPrice").focus();
						return;
					}
					if($.trim($("#productStock").val())=="")
					{alert("产品库存不能为空");
						$("#productStock").focus();
						return;
					}
					if($("input[name='productFreightType']:checked").val()==0)
					{
						
						if($.trim($("#productFreightSame").val())=="")
						{
							alert("请输入运费");
							return;
						}
					}
					if($("input[name='productFreightType']:checked").val()==1)
					{
						$("#productFreightSame").val(0);
						if($("#productFreightTemplateId").val()=="")
						{
							alert("请选择运费模板");
							return;
						}
					}
					/*
					if($("input[name='productOnsaleTimeType']:checked").val()==2)
					{
						if($("#productOnsaleTiming").val()=="")
						{
							alert("请选择定时开售时间");
							return;
						}
					}
					*/
					
				$('#productDetail').val(ue.getContent());
				
				
				
		   		jQuery.ajax({ 
		   			type : "POST",
					url: "/admin/aigou/saveGoods", 
					data: {
                	"productName":$("#productName").val(),
                	"productCategoryId" :$("#productCategoryId").val(),
                	"productPrice" :$("#productPrice").val(),
                	"productCredit" :$("#productCredit").val(),
                	"productMainpicPath" :$("#productMainpicPath").val(),
                	"productPic1Path" :$("#picture1Path").val(),
                	"productPic2Path" :$("#picture2Path").val(),
                	"productPic3Path" :$("#picture3Path").val(),
                	"productSpecificationValue" :$("#productSpecificationValue").val(),
                	"productDetail" : $("#productDetail").val(),
                	"productStock" :$("#productStock").val(),
                	"productIsDispalyStock" :$("#productIsDispalyStock").val(),
                	"productFreightType":$("input[name='productFreightType']:checked").val(),
                	"productFreightSame" :$("#productFreightSame").val(),
                	"productFreightTemplateId" :$("#productFreightTemplateId").val(),
                	"productStopbuyNum" :$("#productStopbuyNum").val(),
                	"productOnsaleTimeType" :$("input[name='productOnsaleTimeType']:checked").val()
                	//"productOnsaleTiming" :$("#productOnsaleTiming").val()
                	},
					error:function(data){
                    	alert("出錯了！");
                	},
                	success:function(data){
                		var json = JSON.parse(data); 
                		if(json.error=="0"){
                			alert("发布成功!");
                			window.location.href="/admin/aigou/goodsManager";
                		}else{
                			alert("出錯了！o");
                		}
                	}
                });
			});
			
			
		});
	</script>
  </body>
</html>
