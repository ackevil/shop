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
		<title></title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<script src="<%=basePath %>wechat/js/mui.min.js"></script>
		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=basePath %>wechat/css/mui1.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="<%=basePath %>wechat/css/app.css" />
		<script type="text/javascript" src="<%=basePath%>wechat/js/jquery-1.9.1.js"></script>
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
		</script>
		
		<style>
			.login-lastline{
				height:80px;
				width:100%;
				background-color: #efeff4;
				padding-top: 20px;
				padding-left: 15px;
				padding-right: 15px;
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
		<header class="mui-bar mui-bar-nav" >
<a class="mui-action-back mui-pull-left" style="background-image: url<%=basePath %>wechat/img/jiantou.png);background-size:70%;background-repeat:no-repeat ;height: 40px;width:30px;margin-top: 13px;margin-left: 4px;"></a>			<h1 class="mui-title">待付款的订单</h1>
		</header>
		<input id="saId" type="hidden" value="${msa.shippingAddId}"/>
		<input id="pId" type="hidden" value="${pId}"/>
		<input id="pNum" type="hidden" value="${pNum }"/>
		<input id="pCount" type="hidden" value="${count }"/>
		<input id="resource" type="hidden" value="${resource}"/>
		<div class="mui-content">
			<ul class="mui-table-view"style="height: 98px;border-top: 1px solid #CFCFCF;">
				<li class="mui-table-view-cell">
					<c:choose>
						<c:when test="${msa!=''}">
							<a id="head" class="mui-navigate-right" href="<%=path%>/lp/lpReciveInfo?type=${pId}&userId=${user.userId}">收件人：${msa.shippingAddName}
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
							<a id="head" class="mui-navigate-right" href="<%=path%>/lp/lpReciveInfo?type=${pId}&userId=${user.userId}">
								<span class="mui-pull-right" style="padding-right:25px ;">
								请添加收货地址
								</span>
							</a>
						</c:when>
					</c:choose>	
				</li>
			</ul>
			<div style="height: 10px;"></div>
			<ul class="mui-table-view">
				<c:forEach items="${list}" var="pInfo">
					<li class="mui-table-view-cell" style="height:77px;padding-top: 9px;">
						<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=path %>${pInfo.product.productMainpicPath}" style="width:55px;height:55px;margin-top:3px;max-width:65px;max-height:65px">
							<div class="mui-media-body" style="width: 190px;font-size: 13px;letter-spacing: -2px;float: left;">
								${pInfo.product.productName }
								<p class='mui-ellipsis' style="letter-spacing: 0px;font-size: 12px;">${pInfo.product.productSpecificationValue }</p>
							</div>
							<div class="mui-pull-right" >
								¥${pInfo.product.productPrice}<br/>
								<span class="mui-pull-right">x${pInfo.pNum}</span>
							</div>
							
					</li>
				</c:forEach>
				<li class="mui-table-view-cell" style="height:38px;padding-top: 9px;">
					<a id="head" style="font-size: 13px;">运费
						<span class="mui-pull-right">¥0.00</span>
					</a>
				</li>
				<li class="mui-table-view-cell" style="height:38px;padding-top: 9px;">
					<a id="head" style="font-size: 13px;">合计
						<span class="mui-pull-right" style="color: #fc5959;">￥${count}</span>
					</a>
				</li>
			</ul>
			<div style="height: 15px;"></div>
			<ul class="mui-table-view"style="height: 62px;">
				<li class="mui-table-view-cell">
					<a id="head" href="" style="text-align: center;color:#fc5959 ;padding-top: 14px;font-size: 15px;">需付:¥${count}
					</a>
			</ul>
			<div class="login-lastline">
					<input id="submit" type="submit" class="login-button" onclick="submit()" value="微信支付" />
			</div>	
			<script type="text/javascript">
				function submit(){
					//检查是否填写收货地址
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
					data:{
						"saId":$("#saId").val(),
						"pId":$("#pId").val(),
						"pNum":$("#pNum").val(),
						"pCount":$("#pCount").val(),
						"resource":$("#resource").val()
					},
			        type:"POST",
			        url:"<%=basePath%>wechat/aigou/payment_do",
			        error:function(data){
			            alert("出錯了！");
			        },
			        success:function(data){
			        	 data = $.parseJSON(data);
			           if(data.code != 0){
			           		var  price=${count};
			           		var  productOrderId=data.code;
			           		var  attach='{"type":"aigou","id":'+productOrderId+'}';
			           		//请求支付
			           		jQuery.ajax({ 
						   			type : "POST",
									url: "/wexinPay/webPay", 
									data: {
				                	"body":"爱购商品",
				                	"price" :price*100,
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
												        	window.location.href="<%=basePath%>register";
													    },
													fail : function(res) {
														alert("支付失败");
													}
												});
										
									    
					                   
				                   }
				             });
			        		//alert("购买成功！")
			        	}
			            else{
			            	alert("出错了");
			            }
			        }
			    });
				}
			</script>	
	</body>
</html>
