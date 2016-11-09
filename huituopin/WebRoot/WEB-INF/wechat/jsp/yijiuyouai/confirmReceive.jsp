<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<title>慧脱贫</title>
		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=path%>/wechat/css/mui1.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="<%=path%>/wechat/css/app.css" />
		<script src="<%=path %>/wechat/js/mui.min.js"></script>
		<script type="text/javascript" src="<%=path %>/wechat/js/jquery-1.9.1.js"></script>
		<style type="text/css">
			.choose{
				width: 100%;
				background-color: white;
			}
			.address{
				height: 100px;
				width: 100%;
				background-color: white;
			}
			.xiangxi{
				width:80%;
				overflow: visible;
				white-space :normal;
				
				font-size:15px;
				
			}
			.login-button{
				width:100%;
				height:45px;
				outline: none;
				font-size: 14px;
				color: white;
				border-color: transparent;
				background-color:  #fc5959;
			}
		</style>
	</head>
	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-pull-left" style="background-image: url(<%=basePath%>wechat/images/jiantou.png);background-size:70%;background-repeat:no-repeat ;height: 40px;width:30px;margin-top: 13px;margin-left: 4px;"></a>
			<h1 class="mui-title">确认领取</h1>
		</header>
		<input id="userId" type="hidden" value="${user.userId }"/>
		<input id="clothId" type="hidden" value="${cloth.clothId }"/>
		<input id="saId" type="hidden" value="${msa.shippingAddId }"/>
		<div class="mui-content">
			<ul class="mui-table-view">
				<li class="mui-table-view-cell">
					<c:choose>
						<c:when test="${msa!=''}">
							<a id="head" class="mui-navigate-right" href="<%=path%>/pp/reciveInfo?type=${cloth.clothId }&userId=${user.userId}">收件人：${msa.shippingAddName}
								<span class="mui-pull-right" style="padding-right:25px ;">
								${msa.shippingAddPhone}
								</span>
								<div style="padding-right: 25px;padding-top: 5px;">
									<span style="font-size: 12px;white-space :normal;line-height: 0.5em;">收货地址：${msa.shippingAddProvince}
									</span>
								</div>
							</a>
						</c:when>
						<c:when test="${msa==''}">
							<a id="head" class="mui-navigate-right" href="<%=path%>/pp/reciveInfo?type=${cloth.clothId}&userId=${user.userId}">
								<span class="mui-pull-right" style="padding-right:25px ;">
								请添加收货地址
								</span>
							</a>
						</c:when>
					</c:choose>					
				</ul>
				
			
			<div style="height:10px;">
				
			</div>
			<div class="choose">
				<ul class="mui-table-view ">
					<li class="mui-table-view-cell ">
						<a href="">
							<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=basePath%>${cloth.clothMainpicPath}" style="width:60px;height:60px;margin-top:3px;max-width:77px;max-height:77px">
								<div class="xiangxi" >
									<span style="display:block;width:150px;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${cloth.clothName}</span>
									<p class='mui-ellipsis' style="font-size: small;">
											衣服类别：
											${clothType}
											<br />
											性&nbsp&nbsp&nbsp&nbsp&nbsp  别：
											<c:if test="${cloth.clothType2==1}">
												男
											</c:if>
											<c:if test="${cloth.clothType2==2}">
												女
											</c:if>
											<br />
											适合年龄：
											<c:if test="${cloth.clothType3==1}">
												童年
											</c:if>
											<c:if test="${cloth.clothType3==2}">
												青少年
											</c:if>
											<c:if test="${cloth.clothType3==3}">
												壮年
											</c:if>
											<c:if test="${cloth.clothType3==4}">
												老年
											</c:if>
											<br />
									</p>
							</div>
						</a>
					</li>
				</ul>
			</div>	
			
			<div style="height:20px;"></div>
			<div><input type="submit" class="login-button" value="提交" onclick="receive()" /></div>
		</div>
		<script type="text/javascript" >
			function receive(){
				if(!$("#saId").val()){
					var btnArray = ["您没有选择收货地址"];
                    mui.alert(' ',' ',btnArray, function() {
                    	if (self.callback) {
			                var rs = self.callback(self.getSelectedItems());
			                if (rs !== false) {
			                  self.hide();
			                }
		                }
                	});
                	return;
				}
				 $.ajax({
			       data: {
			       	"clothId":$("#clothId").val(),
			       	"saId":$("#saId").val()
			       	},
			       type:"POST",
			       url:"<%=basePath%>wechat/yjya/receiveCloth_do",
			       error:function(data){
			           alert("出錯了！");
			       },
			       success:function(data){
			           if (data == "true") {
			           		alert("领取成功"),
			                window.location.href="<%=basePath%>/wechat/yjya";
			           }
			           else if(data == "empty"){
			                alert("您来晚了，该衣物已被别人领走啦！！");
			           }
			           else{
			           		alert("出錯了！");
			           }
			       }
			   });
			}
		</script>
	</body>
</html>
