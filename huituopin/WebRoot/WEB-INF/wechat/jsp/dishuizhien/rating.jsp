<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.huituopin.dsze.entity.DszeComment" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
		<title>慧脱贫</title>
		<link rel="stylesheet" href="<%=path %>/wechat/css/mui.css" type="text/css">
		<link rel="stylesheet" href="<%=path %>/wechat/css/rating.css" type="text/css">
		<script src="<%=path %>/wechat/js/mui.min.js"></script>
		<script src="<%=path %>/wechat/js/jquery-1.9.1.js"></script>
		<style>
			body{
				background-color: white;
			}
			.mui-content > .mui-table-view:first-child{
				margin: 0;
			}
			.mui-table-view-cell{
				padding: 10px 5px;
			}
			.mui-input-row{
				clear: none;
			}
			input[type='text']{
				padding: 0;
				height: 30px;
				font-size: 15px;
				padding-left: 5px;
			}
		</style>
	</head>
	<script>
	$(function(){
	    var comments='${requestScope.comments}';
	    debugger;
	    var commentObjs = $.parseJSON(comments);
	    var html = "";
	    $.each(commentObjs, function(n, v) {
		       if (v.dszeCommentId == null) {
		            var temple = "<ul class='mui-table-view'>"
	                 + " <li class='mui-table-view-cell'> "
	                 +    " <div class='left'>"
	                 +        " <img class='mui-media-object mui-pull-left head-img' src='<%=basePath%>"+v.userAvatar+"' style='height: 48px;max-width: 50px;'/>"                   
	                 +    " </div>"
	                 +   " <div class='right'>"
	                 +       " <span class='name'>" + v.userName + "</span><br />"
	                 +       " <div style='height: 9px;'></div>"
	                 +       " <span class='mui-ellipsis rating my_Comment'>"
	                 +       v.dszeComment
	                 +		"<input type='hidden' class='id' value='"+v.id+"'/>"
	                 +		"<input type='hidden' class='userId' value='"+v.userId+"'/>"
	                 +		"<input type='hidden' class='userName' value='"+v.userName+"'/>"
	                 +		"<input type='hidden' class='userAvatar' value='"+v.userAvatar+"'/>"
	                 +      " </span>"
	                 +      " <div class='time'>"
	                 +		  v.dszeCommentIntime
	                 +         " <span style='float: right;' class='image_comment'>评论</span>"
	                 +         " <img  src='<%=path %>/wechat/img/pinglun_msg.png' class='rating-image image_comment' />"
	                 +     " </div>";

                     var childComment = getChildComment(commentObjs, v.id);
                     debugger;
                     if (childComment.length > 0) {
                        var childComments = "";
                        $.each(childComment, function(c, com) {
                            if (com.commentUserId == null) {
                                childComments += "<p class='reply my_Comment'><span class='name'>" + com.userName + ":</span>  " + "<span class=''>"
                                +com.dszeComment
                                +"<input type='hidden' class='id' value='"+com.dszeCommentId+"'/>"
                                +"<input type='hidden' class='userId' value='"+com.userId+"'/>"
                                +"<input type='hidden' class='userName' value='"+com.userName+"'/>"
                                +"<input type='hidden' class='userAvatar' value='"+com.userAvatar+"'/>"
                                + "</span></p>";
                            } else {
                                childComments += "<p class='reply my_Comment'><span class='name'>" + com.userName + ":</span>回复<span class='name'>"+ com.commentUserName + "</span>  "+"<span class=''>"
                                + com.dszeComment
                                +"<input type='hidden' class='id' value='"+com.dszeCommentId+"'/>"
                                +"<input type='hidden' class='userId' value='"+com.userId+"'/>"
                                +"<input type='hidden' class='userName' value='"+com.userName+"'/>"
                                +"<input type='hidden' class='userAvatar' value='"+com.userAvatar+"'/>"
                                + "</span></p>";
                            }
                        });
                         temple += " <div style='background-image: url(<%=path %>/wechat/images/dishuizhien_pinglun.png);padding-top:15px'>"
                           +       childComments
                           +  " </div>";
                     }
	                  temple += " </div> "
	                  +   " </li>"
	                  + " </ul>";
	                  html += temple;
		       }
	   });

            $(".mui-content").html(html);
         
	    
	});
	    
	    function getChildComment(comment, id) {
	    debugger;
	       var childComment = new Array();
	       for (var i = 0; i < comment.length; i++) {
	           if (comment[i].dszeCommentId == id) {
	              childComment.push(comment[i]);
	           }
	       }
	       
	       return childComment;
	    }
	</script>

	<body>

		<header class="mui-bar mui-bar-nav" >
			<a class="mui-action-back mui-pull-left" style="background-image: url(<%=path %>/wechat/img/jiantou.png);background-size:70%;background-repeat:no-repeat ;height: 40px;width:30px;margin-top: 13px;margin-left: 4px;"></a>			
			<h1 class="mui-title">全部评论</h1>
		</header>
	    <div class="mui-content">
<!--  	     	<ul class="mui-table-view" style="border-top:1px solid #CFCFCF;">
				<li class="mui-table-view-cell">
					<div class="left">
						<img class="mui-media-object mui-pull-left head-img" src="<%=path %>/wechat/images/pinglu_me.jpg" style="height: 48px;max-width: 50px;"/>					
					</div>
					<div class="right">
						<span class="name">王大五</span><br />
						<div style="height: 9px;"></div>
						<span class='mui-ellipsis rating'>
						希望孩子们能够健康快乐的成长。
						</span>
						<div class="time">
							2016-05-05 14:00
							<span style="float: right;">评论</span>
							<a href=""><img src="<%=path %>/wechat/img/pinglun_msg.png" class="rating-image" /></a>
						</div>
						<div style="background-image: url(<%=path %>/wechat/images/dishuizhien_pinglun.png);">
							<p class="leaveMsg"><span class="name">斑马:</span>每次看到这样的报道就莫名的心酸，希望自己微博的捐款能够帮助他们。</p>
						    <p class="reply"><span class="name">王大五:</span>回复<span class="name">斑马:</span>深有同感。</p>
						</div>
					</div>										
				</li>
			</ul>
			<ul class="mui-table-view">
				<li class="mui-table-view-cell">
					<div class="left">
						<img class="mui-media-object mui-pull-left head-img" src="<%=path %>/wechat/images/pinglu_me.jpg" style="height: 48px;max-width: 50px;"/>					
					</div>
					<div class="right">
						<span class="name">王大五</span><br />
						<div style="height: 9px;"></div>
						<span class='mui-ellipsis rating'>
						希望孩子们能够健康快乐的成长。
						</span>
						<div class="time">
							2016-05-05 14:00
							<span style="float: right;">评论</span>
							<a href=""><img src="<%=path %>/wechat/img/pinglun_msg.png" class="rating-image" /></a>
						</div>
						<div style="background-image: url(<%=path %>/wechat/images/dishuizhien_pinglun.png);">
						</div>
					</div>										
				</li>
			</ul>
				
			<ul class="mui-table-view">
				<li class="mui-table-view-cell">
					<div class="left">
						<img class="mui-media-object mui-pull-left head-img" src="<%=path %>/wechat/images/pinglu_me.jpg" style="height: 48px;max-width: 50px;"/>					
					</div>
					<div class="right">
						<span class="name">王大五</span><br />
						<div style="height: 9px;"></div>
						<span class='mui-ellipsis rating'>
						希望孩子们能够健康快乐的成长。
						</span>
						<div class="time">
							2016-05-05 14:00
							<span style="float: right;">评论</span>
							<a href=""><img src="<%=path %>/wechat/img/pinglun_msg.png" class="rating-image" /></a>
						</div>
						<div style="background-image: url(<%=path %>/wechat/images/dishuizhien_pinglun.png);">
							<p class="leaveMsg"><span class="name">斑马:</span>每次看到这样的报道就莫名的心酸，希望自己微博的捐款能够帮助他们。</p>
						    <p class="reply"><span class="name">王大五</span>回复<span class="name">斑马:</span>深有同感。</p>
						</div>
					</div>										
				</li>
			</ul>
		-->
	   </div>
	   <div class="line" style="height: 50px">
	   </div>
	   <nav class="mui-bar mui-bar-tab">
	   
	   <!--  
			<a href="">
				<div style="height:30px;width: 12%;float: left;margin-top: 7px;padding-left: 5px;">
					<img src="<%=path %>/wechat/img/expression.png" style="height: 30px;"/>
				</div>
			</a>	
			<a href="">
				<div style="height:30px;width: 12%;float: left;margin-top: 7px;">
					<img src="<%=path %>/wechat/img/add.png" style="height: 30px;"/>
				</div>
			</a>
		-->	
				<div style="height:30px;width: 8%;float: left;margin-top: 7px;">
				</div>
				<div class="mui-input-row" style="height:30px;width: 70%;float: left;margin-top: 7px;">
					<input type="text" placeholder="输入内容..." id="input_comment" name="dszeComment">				
				</div>
				<div style="height: 100%;width: 20%;float: left;margin-top: 7px;padding-left: 10px;">
					<input id="send" type="submit" style="height: 30px;width: 50px;background-color:#59ADED ;"value="发送" />
					<input type="hidden" id="dszeId" name="dszeId" value="${dsze.dszeId }">
					<input type="hidden" id="dszeCommentId" name="dszeCommentId" value="">
					<input type="hidden" id="userId" name="userId" value="${user.userId}">
					<input type="hidden" id="userName" name="userName" value="${user.userWcNickname}">
					<input type="hidden" id="userAvatar" name="userAvatar" value="${user.userWcAvatar}">
					<input type="hidden" id="commentUserId" name="commentUserId" value="">
					<input type="hidden" id="commentUserName" name="commentUserName" value="">
					<input type="hidden" id="commentUserAvatar" name="commentUserAvatar" value="">
				</div>
		</nav>
	<script src="<%=basePath%>wechat/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" >
		$(function(){
		
				
		
			$(".my_Comment").click(function(){
				var my_Comment_id=$(this).find(".id").val();
				var my_Comment_userId=$(this).find(".userId").val();
				var my_Comment_userName=$(this).find(".userName").val();
				var my_Comment_userAvatar=$(this).find(".userAvatar").val();
				$("#dszeCommentId").val(my_Comment_id);
				$("#commentUserId").val(my_Comment_userId);
				$("#commentUserName").val(my_Comment_userName);
				$("#commentUserAvatar").val(my_Comment_userAvatar);
				$("#input_comment").attr("placeholder","回复:"+my_Comment_userName);		
			
			});
			$(".image_comment").click(function(){
				var my_Comment_id=$(this).parent().parent().find(".id").val();
				var my_Comment_userId=$(this).parent().parent().find(".userId").val();
				var my_Comment_userName=$(this).parent().parent().find(".userName").val();
				var my_Comment_userAvatar=$(this).parent().parent().find(".userAvatar").val();
				$("#dszeCommentId").val(my_Comment_id);
				$("#commentUserId").val(my_Comment_userId);
				$("#commentUserName").val(my_Comment_userName);
				$("#commentUserAvatar").val(my_Comment_userAvatar);
				$("#input_comment").attr("placeholder","回复:"+my_Comment_userName);		
			
			});
			$("#send").click(function(){
				var dszeComment=$("#input_comment").val();
				var dszeId=$("#dszeId").val();
				var dszeCommentId=$("#dszeCommentId").val();
				var userId=$("#userId").val();
				var userName=$("#userName").val();
				var userAvatar=$("#userAvatar").val();
				var commentUserId=$("#commentUserId").val();
				var commentUserName=$("#commentUserName").val();
				var commentUserAvatar=$("#commentUserAvatar").val();
		   		jQuery.ajax({ 
		   			type : "POST",
					url: "/dsze/saveComment", 
					data: {
					"dszeComment":dszeComment,
					"dszeId":dszeId,
					"dszeCommentId":dszeCommentId,
					"userId":userId,
					"userName":userName,
					"userAvatar":userAvatar,
					"commentUserId":commentUserId,
					"commentUserName":commentUserName,
                	"commentUserAvatar":commentUserAvatar
                	},
					error:function(data){
                    	//alert("出錯了！");
                	},
                	success:function(data){
                		location.reload();
                	}
                });
			
			});
		});
	</script>
	</body>
</html>
