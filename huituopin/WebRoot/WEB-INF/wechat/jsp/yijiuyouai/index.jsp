<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
		<title>慧脱贫</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="<%=basePath%>wechat/css/mui1.css">
		<script type="text/javascript" src="<%=path %>/wechat/js/jquery-1.9.1.js"></script>
		<script src="<%=path %>/wechat/js/mui.min.js"></script>	
		<script type="text/javascript">
			$(function(){
				displayList($("#clothInfo").val());
				displayType1($("#ctInfo").val());
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
			function displayList(msg){
				$("#clothList div").remove();
				$("#clothList li").remove();
				if(msg=="null"){
					$("#clothList").append("<div style='font-size:15px;text-align:center'>没有符合您筛选条件的衣物</div>");
					return;
				}
				var splitMsg = msg.split("|");
				var basePath = "<%=basePath%>";
			    for (var i = 1; i <=splitMsg[0]; i++) {
			        message = splitMsg[i].split("├");
		        	var id = message[0];
		            var picPath = message[1];
		            var name = message[2];
		            var status = message[3];
			        var node = "<li class='mui-table-view-cell mui-media mui-col-xs-6' style='width:49.5%;padding:0px;background-color:F6F6F6;'><a href='"+basePath+"wechat/yjya/detail?id="+id+"' style='padding-left:2%;margin:5px 0 0 0;'><img class='mui-media-object' src='"+basePath+picPath+"' style='height:200px'><div class='mui-media-body' style='font-size:10px;padding:0px;background-color:white;'><span style='display:-webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 2;overflow: hidden;padding:10px 8px 8px 8px;font-size:10px;'>"+name+"</span></div></a><div style='height:38px;margin-left:2%;background-color:white;'>";    
			        if(status != "0")
			        	node += "<span id='alertBtn' href='' style='height:25px;width:85px;float:left;background-color:#cfcfcf;font-size: 8px;margin:5px 0 0 8px;color: white;border:0;text-align:center;padding-top:2px;border-radius:4px;'>已领取</span>";
			        else
			        	node+="<a id='alertBtn' onclick='receive("+id+")' " + "style='height:25px;width:85px;float:left;background-color:#fc5959;font-size: 8px;margin:5px 0 0 8px;color: white;border:0;text-align:center;padding-top:2px;border-radius:4px;'>领取</a>";
			        node += "</div></li>";
			        $("#clothList").append(node);
			     }
			};
			function refreshList(){
				
				$.ajax({
			        data: {
			        	"type1":$("#type1").val(),
			        	"type2":$("#type2").val(),
			        	"type3":$("#type3").val()
			        	},
			        type:"POST",
			        url:"<%=basePath%>wechat/yjya/refreshList_do",
			        error:function(data){
			            alert("出錯了！");
			        },
			        success:function(data){
			            if (data!="") {
			            	$("#clothList").innerHTML="";
			            	displayList(data);
			            }
			            else {
			                 alert("出錯了！");
			            }
			        }
			    });
			}
			function receive(id){
				$.ajax({
			        type:"POST",
			        url:"<%=basePath%>wechat/yjya/checkUserType",
			        error:function(data){
			            alert("出錯了！");
			        },
			        success:function(data){
			        	if(data == "poor_no"){
			        		alert("您領取衣物的數量已達到上限！")
			        	}
			            else  if (data == "poor") {
			                 window.location.href="<%=basePath%>wechat/yjya/confirmReceive?clothId=" + id;
			            }
			            else if(data == "loving") {
			                 alert("爱心人士不能领取！");
			            }
			            else{
			            	alert("请先注册！");
			            	window.location.href="<%=basePath%>register/signin";
			            }
			        }
			    });
			}
		</script>
	</head>
<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-pull-left" style="background-image: url(<%=basePath%>wechat/images/jiantou.png);background-size:70%;background-repeat:no-repeat ;height: 40px;width:30px;margin-top: 13px;margin-left: 4px;"></a>
			<h1 class="mui-title">依旧有爱</h1>
		</header>
		<div id="slider" class="mui-slider" style="border-top: 1px solid gainsboro;border-bottom: 1px solid gainsboro;height: 50px;top:44px;position:fixed;">
			<div style="width:33.3%;height: 100%;float: left;border-right:1px solid #CFCFCF ;background-color:white;">
				<div style="margin-left:auto;margin-right:auto;width:80px;height:28px;overflow:hidden;margin-top:7px;">
					<select id="type1" onchange="refreshList()" class="mui-btn mui-btn-block" style="font-size: 14px;width:auto;text-align:right;padding:7px 0;display:inline-block;direction:rtl;float:left;" >
					</select>
					<img src="<%=basePath%>wechat/images/xiaLaJianTou.png" style="max-width:8px;height:5px;float:left;margin-top:14px;"/>	
				</div>			
			</div>
			<div style="width:33.3%;height: 100%;float: left;border-right:1px solid #CFCFCF ;background-color:white;">
				<div style="margin-left:auto;margin-right:auto;width:45px;height:28px;overflow:hidden;margin-top:7px;">
					<select id="type2" onchange="refreshList()"  class="mui-btn mui-btn-block" style="font-size: 14px;width:auto;text-align:right;padding:7px 0;display:inline-block;direction:rtl;float:left;">
						<option value="0" dir="rtl">性别</option>
						<option value="1" dir="rtl">男</option>
						<option value="2" dir="rtl">女</option>
					</select>
					<img src="<%=basePath%>wechat/images/xiaLaJianTou.png" style="max-width:8px;height:5px;float:left;margin-top:14px;"/>
				</div>
			</div>
			<div style="width:33.3%;height: 100%;float: left;background-color:white;">
				<div style="margin-left:auto;margin-right:auto;width:65px;height:28px;overflow:hidden;margin-top:7px;">
					<select id="type3" onchange="refreshList()"  class="mui-btn mui-btn-block" style="font-size: 14px;width:auto;text-align:right;padding:7px 0;display:inline-block;direction:rtl;float:left;">
						<option value="0" dir="rtl">年龄段</option>
						<option value="1" dir="rtl">童年</option>
						<option value="2" dir="rtl">青少年</option>
						<option value="3" dir="rtl">壮年</option>
						<option value="4" dir="rtl">老年</option>
					</select>
					<img src="<%=basePath%>wechat/images/xiaLaJianTou.png" style="max-width:8px;height:5px;float:left;margin-top:14px;"/>
				</div>
			</div>
		</div>
		<div class="mui-content" style="padding-top:94px;">
			<!--数据列表-->
			<ul id="clothList" class="mui-table-view mui-grid-view" style="background-color:F6F6F6;" >
			</ul>
		</div>
		<input id="clothInfo" type="hidden" value="${clothInfo}"/>
		<input id="ctInfo" type="hidden" value="${ctInfo}"/>
		<div style="height:50px"></div>
		<nav class="mui-bar mui-bar-tab">
			<c:if test="${userType!='loving' }">
				<jsp:include page="../footer.jsp"></jsp:include>
			</c:if>
			<c:if test="${userType=='loving' }">
				<input id="alertBtn" type="button" onclick="window.location.href='<%=basePath%>lp/wantDonate';" style="width:100%;height:50px;background-color: #fc5959;color: white;" value="我要捐赠"/>
			</c:if>
			
		</nav>
	</body>
</html>
