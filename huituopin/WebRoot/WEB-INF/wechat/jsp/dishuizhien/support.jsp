<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="<%=path %>/wechat/css/zhichixinxi.css" type="text/css">
<link rel="stylesheet" href="<%=path %>/wechat/css/public_top.css" type="text/css">
<link rel="stylesheet" href="<%=path %>/wechat/css/mui.css" type="text/css">
<script src="<%=path %>/wechat/js/mui.min.js"></script>	
<title>慧脱贫</title>
<style>
	input[type='text']{
		padding: 25px 15px;
	}
	input[placeholder]{
		font-size: 13px;
	}
	input[type='submit']{
		width:100%;
		height: 45px;
		background-color:#40d319;
		color:#ffffff;
		font-family:"黑体";
		font-size: 15px;
		border:none;
		
	}
</style>
</head>
<body style="background-color:#efeff4; padding:0px; margin:0px;">
		<header class="mui-bar mui-bar-nav" >
<a class="mui-action-back mui-pull-left" style="background-image: url(<%=path %>/wechat/img/jiantou.png);background-size:70%;background-repeat:no-repeat ;height: 40px;width:30px;margin-top: 13px;margin-left: 4px;"></a>			<h1 class="mui-title">支持信息</h1>
		</header>
	    <div class="mui-content">
	        <div >
             	<h5 class="center_h51">感谢您的支持</h5>
		    </div>
		    <form class="mui-input-group" method="post"  >
				<div class="mui-input-row" style="height: 50px;">
					<input type="text" placeholder="输入支持金额(不低于1元)" id="supportPrice" name="supportMoney">													
				</div>
				<div class="mui-input-row1" style="height: 50px;">					
					<input type="text"  class="mui-input-clear" placeholder="留言" id="message" name="leaveWord">
				</div>
				<div style="height: 30px;padding:5px 15px;background-color: #efeff4;font-size: 14px;">支持方式</div>
				<div style="padding-left: 15px;padding-right: 15px;background-color: #efeff4;">
					<button  id="chooseWXPay" type="button" class="mui-btn mui-btn-success  mui-btn-block">立即支付</button>
				</div>								
			</form> 
       </div>
</div>

	<script src="<%=basePath%>wechat/js/jquery-1.9.1.js"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript" >
			wx.config({
			debug : false,
			appId : "${map.get('appId')}",
			timestamp : "${map.get('timestamp')}",
			nonceStr : "${map.get('noncestr')}",
			signature : "${map.get('signature')}",
			jsApiList : [ 'chooseWXPay' ]
		});
		


		
		
		wx.ready(function() {
		
		   document.querySelector('#chooseWXPay').onclick = function () {
		   		
		   		var  price =$("#supportPrice").val();
		   		
		   		
		   		if(!isNaN(price)){
		   			if(parseInt(price)==price){
		   				if(price==0){
		   					alert("金额不能为0");
		   					return ;
		   				}
		   			}else{
		   				alert("请输入整数金额");
		   				return ;
		   			}
		   		}else{
		   			alert("请输入整数金额");
		   			return ;
		   		}
		   		
		   		var body="${dsze.dszeName}";
		   		var comment =$("#message").val();
		   		if(comment==""){
		   			//alert("请输入整数金额");
		   			comment="用户无留言";
		   		}
		        var  attach='{"type":"dsze","id":'+${dsze.dszeId}+',"name":"'+'${dsze.dszeName}'+'","userId":'+${user.userId}+',"comment":"'+comment+'"}';
		   
		   
		   
		   		jQuery.ajax({ 
		   			type : "POST",
					url: "/wexinPay/webPay", 
					data: {
                	"body":body,
                	"price" :price,
                	"attach":attach
                	},
					error:function(data){
                    	//alert("出錯了！");
                	},
                	success:function(data){
                	var json = JSON.parse(data);  
			             
			                   wx.chooseWXPay({
								    timestamp: json.timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
								    nonceStr: json.nonceStr, // 支付签名随机串，不长于 32 位
								    package: json.package, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
								    signType: json.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
								    paySign: json.paySign, // 支付签名
								    success: function (res) {
								        // 支付成功后的回调函数
								        	window.location.href="http://www.ackevil.com/register";
									    },
									fail : function(res) {
										alert("支付失败");
									}
								});
						
					    
	                   
                   }
             });
		   

				
		 };
		});
		    
		 
			
			
			
			
		  
	</script>
	










</body>
</html>
