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
			.mui-numbox [class*=btn-numbox]{
				width:35px;
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
		<input id="pCredict" type="hidden" value="${pCredict}"/>
		<input id="uCredict" type="hidden" value="${uCredict}">
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
				<c:if test="${resource == 'duihuan' }">
					<li class="mui-table-view-cell" style="height:38px;padding-top: 9px;">
						<a id="head" style="font-size: 13px;">使用积分
							<span class="mui-pull-right" style="margin-top:-3px;">
								<div class="mui-numbox mui-pull-right" data-numbox-min='0' style="height:30px;width:105px;padding: 0 35px 0 35px;">
									<button class="mui-btn mui-btn-numbox-minus" type="button">-</button>
									<input id="credictNumUsed" class="mui-input-numbox" type="number" value="1" style="font-size:13px" onchange="credictChange()"/>
									<button class="mui-btn mui-btn-numbox-plus" type="button">+</button>
								</div>
							</span>
						</a>
					</li>
				</c:if>
				<li class="mui-table-view-cell" style="height:38px;padding-top: 9px;">
					<a id="head" style="font-size: 13px;">
							应付金额
							<span id="realCount" class="mui-pull-right" style="color: #fc5959;">¥${count}</span>
					</a>
				</li>
			</ul>
			<div style="height: 15px;"></div>
			<c:if test="${resource != 'duihuan' }">
				<ul class="mui-table-view"style="height: 62px;">
					<li class="mui-table-view-cell">
						<a id="head" href="" style="text-align: center;color:#fc5959 ;padding-top: 14px;font-size: 15px;">需付:¥${count}
						</a>
				</ul>
			
				<div class="login-lastline">
						<input id="submit" type="submit" class="login-button" onclick="submit()" value="微信支付" />
				</div>	
			</c:if>
			<c:if test="${resource == 'duihuan' }">
				<div class="login-lastline">
						<input id="duihuan_submit" type="submit" class="login-button" onclick="duihuan()" value="微信支付" />
				</div>
			</c:if>
			<script type="text/javascript">
				function floatSub(arg1,arg2){    
				    var r1,r2,m,n;    
				    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}    
				    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}    
				    m=Math.pow(10,Math.max(r1,r2));    
				    //动态控制精度长度    
				    n=(r1>=r2)?r1:r2;    
				    return ((arg1*m-arg2*m)/m).toFixed(n);    
				}  
				$(function(){
					var p = parseInt($("#pCredict").val());
					var u = parseInt($("#uCredict").val());
					if($("#resource").val() == "duihuan"){
						if(u==0){
							var btnArray = ["您还没有积分"];
				            mui.alert(' ',' ',btnArray, function() {
				            	window.location.href="<%=basePath%>wechat/aigou";
				            });
						}else{
							if(p>u){
								$("#credictNumUsed").val(u);
								var temp = floatSub($("#pCount").val(),u);
								$("#realCount").text("¥"+temp);
							}else{
								$("#credictNumUsed").val(p);
								var temp = floatSub($("#pCount").val(),p);
								$("#realCount").text("¥"+temp);
							}
						}
					}
					if($("#realCount").text()=="¥0.0"){
						$("#duihuan_submit").val("确认兑换");
					}
				});
				function credictChange(){
					console.log($("#credictNumUsed").val());
					var credictUsed = parseInt($("#credictNumUsed").val());
					var p = parseInt($("#pCredict").val());
					var u = parseInt($("#uCredict").val());
					var flag = 0;//1表示用户积分太少；0表示可用积分太少
					var min;//用户剩余积分和商品可用积分中最小的那个
					if(p>u){
						flag=1;
						min=u;
					}else{
						flag=0;
						min=p;
					}
					if(credictUsed>min){
						if(flag==1){
							var btnArray = ["您的剩余积分不足"];
				            mui.alert(' ',' ',btnArray, function() {
				            	$("#credictNumUsed").val(min);
				            	var temp = floatSub($("#pCount").val() ,min);
				            	$("#realCount").text("¥"+temp);
				            });
			            }else{
			            	var btnArray = ["超出最大使用积分数"];
				            mui.alert(' ',' ',btnArray, function() {
				            	$("#credictNumUsed").val(min);
				            	var temp = floatSub($("#pCount").val() ,min);
				            	$("#realCount").text("¥"+temp);
				            });
			            }
					}else if(credictUsed<0){
						/* var btnArray = ["最少使用1积分"];
				            mui.alert(' ',' ',btnArray, function() {
				            	$("#credictNumUsed").val(1);
				            	var temp = $("#pCount").val() - 1;
				            	$("#realCount").text("¥"+temp);
				            }); */
			            	$("#credictNumUsed").val(0);
			            	var temp = $("#pCount").val();
			            	$("#realCount").text("¥"+temp);
					}
					else{
						var temp = floatSub($("#pCount").val(),credictUsed);
						$("#realCount").text("¥"+temp);
					}
					if($("#realCount").text()=="¥0.0"){
						$("#duihuan_submit").val("确认兑换");
					}else{
						$("#duihuan_submit").val("微信支付");
					}
				}
				function duihuan(){
					//检查是否填写收货地址
					if(!$("#saId").val()){
						var btnArray = ["您没有选择收货地址"];
	                    mui.alert(' ',' ',btnArray, function() {});
	                	return;
					}
					//兑换分两种情况1：完全使用积分兑换商品    2：使用积分抵一部分现金
					if($("#realCount").text()=="¥0.0"){
						//情况1
						$.ajax({
							data:{
								"saId":$("#saId").val(),
								"pId":$("#pId").val(),
								"pPrice":$("#pCount").val(),
								"pRealCount":$("#realCount").text().substring(1),
								"pCredict":$("#credictNumUsed").val(),
							},
					        type:"POST",
					        url:"<%=basePath%>wechat/aigou/duihuan_credictdo",
					        error:function(data){
					            var btnArray = ["出錯了！"];
				                mui.alert(' ',' ',btnArray, function() {});
					        },
					        success:function(data){
					        	data = $.parseJSON(data);
					        	if(data.code == -1){
					        		var btnArray = ["您的积分不足"];
				                    mui.alert(' ',' ',btnArray, function() {});
					        	}else if(data.code == 0){
					        		var btnArray = ["出错了"];
				                    mui.alert(' ',' ',btnArray, function() {});
					        	}else{
					        		var btnArray = ["兑换成功"];
				                    mui.alert(' ',' ',btnArray, function() {
				                    	window.location.href="<%=basePath%>wechat/aigou";
				                    });
					        	}
					        }
					    })
				    }else{
					    //情况2
						$.ajax({
							data:{
								"saId":$("#saId").val(),
								"pId":$("#pId").val(),
								"pPrice":$("#pCount").val(),
								"pRealCount":$("#realCount").text().substring(1),
								"pCredict":$("#credictNumUsed").val(),
							},
					        type:"POST",
					        url:"<%=basePath%>wechat/aigou/duihuan_do",
					        error:function(data){
					            var btnArray = ["出錯了！"];
				                mui.alert(' ',' ',btnArray, function() {});
					        },
					        success:function(data){
					        	data = $.parseJSON(data);
					        	if(data.code == -1){
					        		var btnArray = ["您的积分不足"];
				                    mui.alert(' ',' ',btnArray, function() {});
					        	}else if(data.code == 0){
					        		var btnArray = ["出错了"];
				                    mui.alert(' ',' ',btnArray, function() {});
					        	}else{
					           		var  price=$("#realCount").text().substring(1);
					           		var  productOrderId=data.code;
					           		var  attach='{"type":"aigou","id":'+productOrderId+'}';
					           		//请求支付
					           		jQuery.ajax({ 
								   			type : "POST",
											url: "/wexinPay/webPay", 
											data: {
						                	"body":"爱购商品",
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
												    	var btnArray = ["支付成功"];
									                    mui.alert(' ',' ',btnArray, function() {
									                    	window.location.href="<%=basePath%>wechat/aigou";
									                    });
													    },
													cancel: function(res){
														$.ajax({
															data:{
																"pId":$("#pId").val(),
															},
													        type:"POST",
													        url:"<%=basePath%>wechat/aigou/duihuan_payCancel",
													        error:function(data){
													            alert("出錯了！");
													        },
													        success:function(data){
													        	window.location.href="<%=basePath%>wechat/aigou";
													        }
													    })
													},
													fail : function(res) {
														var btnArray = ["支付失败"];
									                    mui.alert(' ',' ',btnArray, function() {
									                    	$.ajax({
																data:{
																	"pId":$("#pId").val(),
																},
														        type:"POST",
														        url:"<%=basePath%>wechat/aigou/duihuan_payCancel",
														        error:function(data){
														            alert("出錯了！");
														        },
														        success:function(data){
														        	window.location.href="<%=basePath%>wechat/aigou";
														        }
														    })
									                    });
													}
												 }); 
						                   	}
						             });
					        	}
					        }
				        });
			        }
				}
				
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
			        	if(data.code == 0){
			        		alert("出錯了！");
			        	}else if(data.code < 0){
			        		alert("您购买的第"+data.code+"件商品卖光啦");
			        	}else{
			           		var  price=${count };
			           		var  productOrderId=data.code;
			           		var  attach='{"type":"aigou","id":'+productOrderId+'}';
			           		//请求支付
			           		jQuery.ajax({ 
						   			type : "POST",
									url: "/wexinPay/webPay", 
									data: {
				                	"body":"爱购商品",
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
												        	window.location.href="<%=basePath%>register";
													    },
													fail : function(res) {
														$.ajax({
															data:{
																"pId":$("#pId").val(),
																"pNum":$("#pNum").val(),
															},
													        type:"POST",
													        url:"<%=basePath%>wechat/aigou/payment_cancel",
													        error:function(data){
													            alert("出錯了！");
													        },
													        success:function(data){
													        	window.location.href="<%=basePath%>wechat/aigou";
													        }
													     })
														alert("支付失败");
													},
													cancel : function(res){
														$.ajax({
															data:{
																"pId":$("#pId").val(),
																"pNum":$("#pNum").val(),
															},
													        type:"POST",
													        url:"<%=basePath%>wechat/aigou/payment_cancel",
													        error:function(data){
													            alert("出錯了！");
													        },
													        success:function(data){
													        	window.location.href="<%=basePath%>wechat/aigou";
													        }
													     })
													},
												});
				                   }
				             });
			        		//alert("购买成功！")
			        	}
			        }
			    });
				}
			</script>	
	</body>
</html>
