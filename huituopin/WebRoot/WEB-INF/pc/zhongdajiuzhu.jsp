<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="<%=basePath%>pc/css/css.css">
		<link rel="stylesheet" href="<%=basePath%>pc/css/mui.css" />
		<script src="<%=path %>/wechat/js/jquery-1.9.1.js"></script>
		<script src="<%=basePath%>pc/js/mui.js"></script>
		<title>重大救助</title>
		<style>
			body{
				background-color: white;
			}
			.top_3 .user{
				margin-left: 125px;
				float: left;
				display: block;
				width: 150px;
				margin-top: 28px;
				color: #00BCE4;
			}
			.top_3 .exitButton{
				width: 100px;
				height: 40px;
				float: left;
				margin-top: 18px;
				margin-left: 25px;
				font-size: 20px;
				color: #00BCE4;
				background-color: white;
				border: 1px solid #D0D0D0;
				border-radius: 25px 25px 25px 25px;
			}
			.middle{
				height: 1000px;
			}
			.title{
				margin: 20px 50px 0 15px;
				height: 40px;
				width: 300px;
				overflow:hidden;
				display:-webkit-box;
				-webkit-line-clamp:2;
				-webkit-box-orient:vertical; 
				text-overflow: ellipsis;
				line-height: 20px;
				text-align:left;
			}
			.lingqu{
				width: 300px;
				height: 100px;
				float: left;
			}
			.getButton{
				float: left;
				margin: 15px 0 0 16px;
				width: 100px;
				height: 30px;
				background-color: #fc5959;
				color: white;
				border: none;
				font-size: 13px;
			}
			.mui-media-img{
				height:360px;
				width:272px;
			}
			
		</style>
		<script type="text/javascript">
			$(function(){
				displayActivity('${requestScope.activityList}');
				dispalyMyNav(parseInt('${requestScope.pageNum}'),1,1);
			});
			
			function displayActivity(activityInfo){
				//在这里首先remove所有的li标签
				$("#activityList li").remove();
				var activityObjs = $.parseJSON(activityInfo);
				var node = "";
				if(!activityObjs){
					node="<li style='height: 175px; text-align:center; font-size:13px'>没有符合您搜索条件的商品</li>";
  					$("#activityList").append(node);
				}else{
					$.each(activityObjs,function(n,v){
						node="<li class='mui-table-view-cell mui-media mui-col-xs-6'>"
			            +		"<a href='#' onClick='hit()' >"
				        +        	"<img class='mui-media-object mui-media-img' style='height:272px;width:360px;' src='<%=basePath%>" + v[2] + "'>"
				        +        	"<div class='mui-media-body'>"
				        +        		"<div class='title'>"
				        +        		v[1]
				        +        		"</div>"
				        +        		"<div class='lingqu'>";
				        node += "<button class='getButton'>查看详情</button>";
				        node +=  		"</div>"
			            +    		"</div>"
			            +		"</a>"
			        	+	"</li>";
			        	$("#activityList").append(node);
					});
				}
			}
			function dispalyMyNav(totalPage,pageNo,flag){//如果是0，则是上一页，1则为下一页
				$("#ul-nav li").remove();
				var node ="";
				if(totalPage == 0){
					return;
				}
				if(pageNo==1){
					node = "<li id='li-first' class='mui-previous mui-disabled'>"
					+				"<a href='#'>"
					+					"上一页"
					+				"</a>"
					+		"</li>";
				}else{
					node = "<li id='li-first' class='mui-previous'>"
					+				"<a href='#'>"
					+					"上一页"
					+				"</a>"
					+		"</li>";
				}
				$("#ul-nav").append(node);
				if(flag==0){ //如果是上一页，则pageNo是6的倍数
					for (var i=pageNo-5;i<pageNo;i++){
						node = "<li>"
						+			"<a href='#'>"
						+				i
						+			"</a>"
						+		"</li>";
						$("#ul-nav").append(node);
					}
					node = "<li class='mui-active'>"
					+			"<a href='#'>"
					+				pageNo
					+			"</a>"
					+		"</li>";
					$("#ul-nav").append(node);
				}else if(flag==1){ //如果是下一页，则pegeNo是6的倍数+1
					
					node = "<li class='mui-active'>"
					+			"<a href='#'>"
					+				pageNo
					+			"</a>"
					+		"</li>";
					$("#ul-nav").append(node);
					
					for(var i=pageNo+1;i<totalPage && i<=pageNo+5;i++){
						node = "<li>"
						+			"<a href='#'>"
						+				i
						+			"</a>"
						+		"</li>";
						$("#ul-nav").append(node);
						if(i==totalPage){
							break;
						}
					}
				}else{
					alert("出错了！");
				}
				if(pageNo==totalPage){
					node  = "<li class='mui-next mui-disabled'>"
					+			"<a href='#'>"
					+				"下一页"
					+			"</a>"
					+		"</li>";
				}else{
					node  = "<li class='mui-next'>"
					+			"<a href='#'>"
					+				"下一页"
					+			"</a>"
					+		"</li>";
				}
				
				$("#ul-nav").append(node);
			}
			function refreshList(pageNo){
				if(pageNo == null){
					pageNo = 1;
				}
				$.ajax({
			        data: {
			        	"pageNo":pageNo
			        	},
			        type:"POST",
			        url:"<%=basePath%>pc/zdjz/refreshList_do",
			        async:false,
			        error:function(data){
			            alert("出錯了！");
			        },
			        success:function(data){
			        	data = $.parseJSON(data);
			            if (data.activityInfo!="") {
			            	displayActivity(data.activityInfo);
			            }
			            else {
			                 alert("出錯了！");
			            }
			        }
			    });
			}
		</script>
	</head>
	<body>
		<div class="box">
			<div class="top">
				
				<div class="top_1">
					<img name="logo" src="<%=basePath%>pc/images/logo.jpg" style="width: 120px;height: 50px;border: 0;margin-top: 15px;"/> 
				</div>
				<div class="top_2">
					<ul>
						<a href="<%=basePath%>"><li>首页</li></a>
						<a href="<%=basePath%>pc/yjya" ><li>依旧有爱</li></a>
						<a href="<%=basePath%>pc/dsze"><li>滴水之恩</li></a>
						<a href="<%=basePath%>pc/zdjz" style="color: #00BCE4;"><li>重大救助</li></a>
						<a href="<%=basePath%>pc/aigou"><li>爱心购</li></a>
					</ul>
				</div>
				<div class="top_3">
					<c:if test="${empty user }">
						<button class="exitButton"><a href="/sign">注册</a></button>
						<button class="loginButton"><a href="/login">登录</a></button>
					</c:if>
					<c:if test="${!empty user }">
						<a href="/userInfo"><span class="user">${user.userWcNickname}${user.userPhone}</span></a>
						<button class="exitButton"><a href="/exit">退出</a></button>
					</c:if>
				</div>
			</div>
			<div class="middle" style="height: 1100px;padding-top: 50px">
				<div class="middle_box">
					<div class="showarea">
						<ul id="activityList" class="mui-table-view mui-grid-view" style="height: 850px;">
			      		</ul>
					</div>
					<input id="pageNum" type="hidden" value="${pageNum}"/>
		      		<div style="text-align: center;">
						<ul id="ul-nav" class="mui-pagination mui-pagination-lg" style="margin-top: 30px;">
						</ul>
					</div>
				</div>
			</div>
			
			<div class="footer">
				<div class="footer-box">				
					<span class="declare"><label style="font:'宋体';font-size: 18px;">©</label>2016慧脱贫—湖南乐善网络科技有限公司 湘IPC证160503号</span>
					<div style="float: right;height: 100px;width: 80px;">
						<img src="<%=basePath%>pc/images/erweima.jpg" style="float: right;height: 80px;width: 80px;margin-top: 5px;" />
						<label style="font-size: 12px;padding-left: 4px;">微信扫码关注</label>
					</div>
				</div>
			</div>
		</div>
		
		
		<script>
			mui.init({
				swipeBack:true //启用右滑关闭功能
			});
			(function($) {
				$('.mui-pagination').on('tap', 'a', function() {
					var li = this.parentNode;
					var classList = li.classList;
					var pageNum = parseInt(document.getElementById("pageNum").value);
					if (!classList.contains('mui-active') && !classList.contains('mui-disabled')) {
						var active = li.parentNode.querySelector('.mui-active');
						if (classList.contains('mui-previous')) {//previous
							if (active) {
								var previous = active.previousElementSibling;
								console.log('previous', previous);
								if (previous && !previous.classList.contains('mui-previous')) {
									$.trigger(previous.querySelector('a'), 'tap');
								} else {
									//如果当前活动导航的第一个页面是不是1，则意味着可以往前翻页
									if(parseInt(active.innerText) > 1){
										//刷新衣物列表
										refreshList(parseInt(active.innerText)-1);
										//刷新分页
										dispalyMyNav(pageNum,parseInt(active.innerText)-1,0);
									}else{
										classList.add('mui-disabled');
									}
								}
							}
						} else if (classList.contains('mui-next')) {//next
							if (active) {
								var next = active.nextElementSibling;
								if (next && !next.classList.contains('mui-next')) {
									$.trigger(next.querySelector('a'), 'tap');
								} else {
									if(parseInt(active.innerText)<pageNum){
										//刷新衣物列表
										refreshList(parseInt(active.innerText)+1);
										//刷新分页
										dispalyMyNav(pageNum,parseInt(active.innerText)+1,1);
									}else{
										classList.add('mui-disabled');
									}
								}
							}
						} else {//page
							//页面刷新必须在分页刷新之前，因为页面刷新里面默认的有分页刷新
							var page = parseInt(this.innerText);
							refreshList(page);
							active.classList.remove('mui-active');
							classList.add('mui-active');
							
							var previousPageElement = li.parentNode.querySelector('.mui-previous');
							var nextPageElement = li.parentNode.querySelector('.mui-next');
							previousPageElement.classList.remove('mui-disabled');
							nextPageElement.classList.remove('mui-disabled');
							
							if (page <= 1) {
								previousPageElement.classList.add('mui-disabled');
							} else if (page >= pageNum) {
								nextPageElement.classList.add('mui-disabled');
							}
							
						}
					}
				});
			})(mui); 
			function hit(){
				mui.alert('请使用微信扫一扫，在手机上操作', ' ', function() {
					
				});
			}
		</script>
		
	</body>
</html>

