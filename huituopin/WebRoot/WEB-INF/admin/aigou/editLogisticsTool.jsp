<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物流工具</title>
<link href="<%=path %>/admin/skin/css/style.css" rel="stylesheet" />
<link href="<%=path %>/admin/skin/css/logisticsDialogDiv.css" rel="stylesheet" />
<!-- 添加弹出对话框的样式定义css-->
<script type="text/javascript" charset="utf-8"src="<%=path %>/admin/skin/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" charset="utf-8"src="<%=path %>/admin/skin/js/layindex.js"></script>
<script type="text/javascript" charset="utf-8"src="<%=path %>/admin/skin/js/laymain.js"></script>
<script type="text/javascript" charset="utf-8"src="<%=path %>/admin/skin/js/common.js"></script>
<script src="<%=path %>/admin/skin/js/bootstrap.min.js"type="text/javascript" charset="utf-8"></script>
<script src="<%=path %>/admin/skin/js/bootstrap-datetimepicker.min.js"type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" type="text/css"href="<%=path %>/admin/skin/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"href="<%=path %>/admin/skin/css/bootstrap-datetimepicker.min.css" />
</head>
<script language="javascript">
        function openWindow(){
            document.getElementById('light').style.display='block';
            document.getElementById('fade').style.display='block';
        }
        function closeWindow(){
            document.getElementById('light').style.display='none';
            document.getElementById('fade').style.display='none';
        }
        function confirm(){
        	document.getElementById('light').style.display='none';
            document.getElementById('fade').style.display='none';
            var length = document.getElementById("select-right").options.length;
            var list = document.getElementById("select-right").options;
            var text ="";
            for(var i = 0; i < Number(length)-1; ++i){
            	text = text + list[i].text+"，";
            }
            text = text + list[length-1].text;
           	document.getElementById("text-show").innerHTML=text;
            
            $("#a_area").css("display","none");
            $("#text-show").css("display","inline");
            $("#text-add").css("display","inline");
        }
        $(document).ready(function(){
        	var province = [["44","广东"],["11","北京"],["33","浙江"],["35","福建"],["42","湖北"],["31","上海"],
                       ["32","江苏"],["12","天津"],["13","河北"],["14","山西"],["15","内蒙古"],["21" , "辽宁"],
                       ["22" , "吉林"],["23" , "黑龙江"],["34" , "安徽"],["36" , "江西"],["37" , "山东"],
                       ["41" , "河南"],["43" , "湖南"],["45" , "广西"],["46" , "海南"],["50" , "重庆"],
                       ["51" , "四川"],["52" , "贵州"],["53" , "云南"],["54" , "西藏"],["61" , "陕西"],
                       ["62" , "甘肃"],["63" , "青海"],["64" , "宁夏"],["65" , "新疆"],["71" , "台湾"],
                       ["81" , "香港"],["82" , "澳门"]];
            for(var i = 0; i < province.length; i++){
            	$("#select-left").append("<option value='"+province[i][0]+"'>"+province[i][1]+"</option>");
            }
            $("#add").click(function(){
            	allsel(document.getElementById("select-left"),document.getElementById("select-right"));
            });
            $("#delete").click(function(){
            	allsel(document.getElementById("select-right"),document.getElementById("select-left"));
            });
        });
        
        function allsel(n1,n2) {
			while (n1.selectedIndex != -1) {
				var indx = n1.selectedIndex;
				var t = n1.options[indx].text;
				var v = n1.options[indx].value;
				n2.options.add(new Option(t,v));
				n1.remove(indx);
			}
		}
    </script>

<body>
	<jsp:include page="../commonPage/commonTop.jsp" />
	<jsp:include page="../commonPage/commonLeft.jsp"></jsp:include>
	
	<div id="light" class="white_content">
		<div class="content-title">
			<span>选择可配送区域</span>
		</div>
		<div class="content-body">
			<div class="middle-content" name="form1">
				<div class="middle-content-left">
					 <div>可选省、市、区</div>
					 <select size="14" multiple class="select-left" name="select-left" id="select-left">
					 </select>
				</div>
				<div class="middle-content-center">
					<div class="button-group">
						<button id="add">添加</button>
						<button id="delete">移除</button>
					</div>
				</div>
				<div class="middle-content-right">
					<div>已选省、市、区</div>
					<select size="14" multiple class="select-right" name="select-right" id="select-right">
						
					 </select>
				</div>
			</div>
		</div>
		<div class="content-buttom">
			<div class="content-buttom-group">
				<button class="my-btn my-btn-quit" onClick="closeWindow()">取消</button>
				<button class="my-btn my-btn-submit" onClick="confirm()">确认</button>
				
			</div>
		</div>
	</div>
    <div id="fade" class="black_overlay"></div>
	
	



	<div class="main-container">
		<div id="floatHead" class="toolbar-wrap"style="background-color: rgb(244,245,249); margin-bottom: 20px;">
			<lable style="margin-left:35px;">物流工具</lable>
		</div>
		<div class="main">
			<ul id="myTab" class="nav nav-tabs">
				<li class="active"><a style="background-color:#f4f5f9;">运费模板</a>
				</li>
				<li style="float:right;"><button type="button"
						class="btn btn-primary sell-button"
						style="background:#3dc700;color:#ffffff;" data-toggle="button">新建运费模板</button>
				</li>
			</ul>
			<div id="myTabContent" class="tab-content">
				<form action="<%=path%>/admin/aigou/editLogisticsToolAction" method="post">
				<div style="margin-bottom:20px;">
					<label style="font-weight:300;font-size:15px;">模板名称：</label>
					<input name="logisticsId" type="text" value="${logistics.logisticsId}" style="display: none"/>
					<input name="logisticsName" value="${logistics.logisticsName}" type="text" style="margin-left:4px; padding-left:10px; width:300px;height:35px;border:1px solid #e7e7eb;" />
				</div>
				<div style="width:100%;height: 120px">
					<label style="width:7%;font-weight:300;font-size:15px;">配送区域：</label>
					<div class="div_table">
						<table style="width:100%;">
							<tr style="border:1px solid #e7e7eb;height:40px;background-color:#f4f5f9;">
								<td class="font_position tin">可配送区域</td>
								<td class="font_position in">首件(个)</td>
								<td class="font_position in">运费(元)</td>
								<td class="font_position in">续件(个)</td>
								<td class="font_position in">续费(元)</td>
							</tr>
							<tr style="border:1px solid #e7e7eb;height:40px;">
								<td id="td_addArea" class="font_position tin">
									<a  id="a_area" href="javascript:openWindow();" >指定可配送区域和运费</a>
									<textarea name="logisticsAreas" id="text-show" style="display: none;" readonly="readonly">${logistics.logisticsAreas}</textarea>
									<a style="display: none" style="font-size: 12px;" id="text-add" href="javascript:openWindow()" >修改</a>
								</td>
								<td class="font_position in">
									<input name="logisticsFirstcount" type="text" value="${logistics.logisticsFirstcount}"/>
								</td>
								<td class="font_position in">
									<input name="logisticsFirstprice" type="text" value="${logistics.logisticsFirstprice }"/>
								</td>
								<td class="font_position in">
									<input name="logisticsLastconut" type="text" value="${logistics.logisticsLastconut }"/>
								</td>
								<td class="font_position in">
									<input name="logisticsLastprice" type="text" value="${logistics.logisticsLastprice }"/>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="two_button">
					<input class="btn1" value="保存" type="submit"/>
					<button class="btn2">返回</button>
				</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
