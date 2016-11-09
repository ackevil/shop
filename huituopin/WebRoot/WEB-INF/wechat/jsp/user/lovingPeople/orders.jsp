<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>

	<head>
		<meta charset="utf-8">
		<title>我的订单</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=basePath%>wechat/css/mui1.css">
		<link rel="stylesheet" href="<%=basePath%>wechat/css/footer.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/app.css" />	
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
		<script type="text/javascript">
			$(function(){
			
			$(".orderPay").click(function(){
			
			var productOrderId=$(this).parent().find(".productOrderId").val();
			var productOrderPrice=$(this).parent().find(".productOrderPrice").val();
			var  attach='{"type":"aigou","id":'+productOrderId+'}';
			 //支付前检测库存中是否还有该订单中的商品
			 $.ajax({
		           data: {
		           	"poId":productOrderId,
		           	},
		           type:"POST",
		           url:"<%=basePath%>lp/checkStock",
		           error:function(data){
		               var btnArray = ["出錯了！"];
	                   mui.alert(' ',' ',btnArray, function() {
	                });
		           },
		           success:function(data){
		               if (data) {
		               	alert(data);
		                window.location.href="<%=basePath%>lp/orders";
		               }
		               else {
		                    //请求支付
							jQuery.ajax({ 
								type : "POST",
								url: "/wexinPay/webPay", 
								data: {
								"body":"爱购商品",
								"price" :productOrderPrice,
								"attach":attach
								 },
							    error:function(data){
								    alert("出錯了！");
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
												//window.location.reload();
											  	window.location.href="<%=basePath%>lp/orders";
											},
											fail : function(res) {
												$.ajax({
													data:{
														"poId":productOrderId,
													},
											        type:"POST",
											        url:"<%=basePath%>lp/payment_cancel",
											        error:function(data){
											            alert("出錯了！");
											        },
											        success:function(data){
											        	window.location.href="<%=basePath%>lp/orders";
											        }
											     })
												alert("支付失败");
											},
											cancel : function(res){
												$.ajax({
													data:{
														"poId":productOrderId,
													},
											        type:"POST",
											        url:"<%=basePath%>lp/payment_cancel",
											        error:function(data){
											            alert("出錯了！");
											        },
											        success:function(data){
											        	window.location.href="<%=basePath%>lp/orders";
											        }
											     })
											},
										});
								 },
				 				 
							 });
		      			}
		     		}
		     	});
			           		
			});
			});
		
		</script>
		<style>
			.mui-control-content .mui-loading {
				margin-top: 50px;
			}
			
			#sliderSegmentedControl a.mui-active{
				color: red;
			}
			.payBtn{
				font-size: 12px;
				background-color: #fc5959;
				color: white;
			}
			.cancelBtn{
				font-size: 12px;
				border-color:rgba(19,18,18,0.31);
				margin-right:10px;
				border: 1px solid #CFCFCF;
				color:#A2A2A2;
			}
			.mui-popup-inner
			{
			    position: relative;
			
			    padding: 15px;
			
			    background: rgba(255, 255, 255, .95);
			}
			.mui-popup{
				width:270px
			}
			.mui-popup-title{
				height: 35px;
				border-bottom: 1px solid #CFCFCF;
			}
			.mui-popup-title + .mui-popup-text{
				padding-top: 10px;
			}
			.mui-popup-inner:after{
				background-color: transparent;
			}
			.mui-popup-buttons{
				height:55px;
				background-color: white;
			}
			.mui-popup-button:first-child{
				background-color:#04BE02 ;
				border:0;
				color: white;
				margin-left: 20px;
				margin-right: 5px;
				padding-bottom: 10px;
				height: 40px;
			}
			.mui-popup-button:last-child{
				height: 40px;
				color: black;
				margin-right: 20px;
				margin-left: 5px;
				border: 1px solid #CFCFCF;
			}
			.mui-popup-button{		
				line-height: 15px;
				font-size: 15px;				
			}
			
				.headimage {
					height: 180px;
					width: 100%;
				}
				
				.icondiv {
					height: 30px;
					width: 30px;
					margin: 0 auto;
					text-align: center;
				}
				
				.iconimg {
					max-height: 35px;
					max-width: 35px;
					padding-top: 5px;
				}
				
				.footer {
					height: 30px;
					width: 15px;
					margin: 0 auto;
					padding-top: 10px;
				}
				
				.footerimg {
					max-height: 15px;
					max-width: 15px;
					padding-top: 10px;
				}
			</style>	
	</head>
	<body>
		<div class="mui-content">
			<div id="slider" class="mui-slider mui-fullscreen" style="background-color:rgba(49,45,45,0);height:initial">
				<div id="sliderSegmentedControl" class="mui-slider-indicator mui-segmented-control mui-segmented-control-inverted" style="background-color:white;">
					<a id="cir1" class="mui-control-item mui-active" href="#item1mobile">
						全部
					</a>
					<a id="cir2" class="mui-control-item" href="#item2mobile">
						待付款
					</a>
					<a id="cir3" class="mui-control-item" href="#item3mobile">
						待发货
					</a>
					<a id="cir4" class="mui-control-item" href="#item4mobile">
						待收货
					</a>
					<a id="cir5" class="mui-control-item" href="#item5mobile">
						待评价
					</a>
				</div>
				<div id="sliderProgressBar" class="mui-slider-progress-bar mui-col-xs-4" style="background-color:red; width:20%;"></div>
				<div class="mui-slider-group">
					<!-- 全部 start-->
					<div id="item1mobile" class="mui-slider-item mui-control-content mui-active" style="background-color: #efeff4;border: 0;">
						<div id="scroll1" class="mui-scroll-wrapper">
							<div class="mui-scroll">
								<c:forEach items="${list}" var="poInfo">
									<div style="height:10px;background-color: #efeff4;"></div>
									<ul class="mui-table-view">
										<li class="mui-table-view-cell" style="height:43px;padding-top: 15px;">
											<span class="mui-pull-left"style="font-size: 14px;color: #A2A2A2;">${poInfo.po.productOrderIntime }</span>
											<span class="mui-pull-right" style="font-size: 14px;color:red;">
												<c:if test="${poInfo.po.productOrderState==0}">
													等待买家付款
												</c:if>
												<c:if test="${poInfo.po.productOrderState==1}">
													等待卖家发货
												</c:if>
												<c:if test="${poInfo.po.productOrderState==2}">
													等待买家收货
												</c:if>
												<c:if test="${poInfo.po.productOrderState==3 || poInfo.po.productOrderState==4 }">
													买家已收货
												</c:if>
											</span>
										</li>
										<c:forEach items="${poInfo.podInfo}" var="podInfo">
											<li class="mui-table-view-cell" style="height:100px;padding-top: 15px;">
												<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=path%>${podInfo[1]}">
												<div class="mui-media-body" style="width: 190px;font-size: 13px;letter-spacing: -2px;float: left;">
													${podInfo[0]}
													<p class='mui-ellipsis' style="letter-spacing: 0px;font-size: 12px;">${podInfo[5]}</p>
												</div>
												<div class="mui-pull-right" >
													¥${podInfo[2]}<br/>
													<span class="mui-pull-right">x${podInfo[3]}</span>
												</div>
								
											</li>
										</c:forEach>
										<li class="mui-table-view-cell" style="height:38px;padding-top: 9px;">
											<a id="head" style="font-size: 13px;color: #A2A2A2;">共${poInfo.podNum }种商品
												<c:if test="${poInfo.po.productOrderCredictUsed!=0}">
													<span class="mui-pull-center">使用${poInfo.po.productOrderCredictUsed}积分</span>
												</c:if>
												<span class="mui-pull-right">总金额：¥${poInfo.po.productOrderAllCount}</span>
											</a>
										</li>
										<c:if test="${poInfo.po.productOrderState!=1}">
											<li class="mui-table-view-cell" style="height:53px;font-size: 15px ;padding-top: 11px;">
												<c:if test="${poInfo.po.productOrderState==0}">
												<input type="hidden" class="productOrderId" value="${poInfo.po.productOrderId}" />
												<input type="hidden" class="productOrderPrice" value="${poInfo.po.productOrderAllCount}"/>
												<button class="mui-pull-right orderPay" style="font-size: 12px;background-color: #fc5959;color: white;">付款</button>
												<button class="mui-pull-right cancelBtn" onclick="cancelOrder(${poInfo.po.productOrderId})">取消订单</button>
												</c:if>
												<c:if test="${poInfo.po.productOrderState==2}">
														<button class="mui-pull-right" style="font-size: 12px;background-color: #fc5959;color: white;" onclick="recept(${poInfo.po.productOrderId})">收货</button>
												</c:if>
												<c:if test="${poInfo.po.productOrderState==3 || poInfo.po.productOrderState==4 }">
												</c:if>
												
											</li>
										</c:if>
									</ul>
								</c:forEach>	
								<div style="height: 50px;background-color: #efeff4;"></div>						
							</div>
						</div>
					</div>
					<!-- 全部 end -->
					<!-- 代付款 start -->
					<div id="item2mobile" class="mui-slider-item mui-control-content"style="background-color: #efeff4;border: 0;">
						<div id="scroll2" class="mui-scroll-wrapper">
							<div class="mui-scroll">						
								<c:forEach items="${list1}" var="poInfo">
									<div style="height: 10px;background-color: #efeff4;"></div>
									<ul class="mui-table-view">
										<li class="mui-table-view-cell" style="height:50px;padding-top: 15px;">
											<span class="mui-pull-left"style="font-size: 15px;color: #A2A2A2;">${poInfo.po.productOrderIntime }</span>
											<span class="mui-pull-right" style="font-size: 14px;color:red;">等待买家付款</span>
										</li>
										<c:forEach items="${poInfo.podInfo}" var="podInfo">
											<li class="mui-table-view-cell" style="height:100px;padding-top: 15px;">
												<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=path%>${podInfo[1]}">
												<div class="mui-media-body" style="width: 190px;font-size: 13px;letter-spacing: -2px;float: left;">
													${podInfo[0]}
													<p class='mui-ellipsis' style="letter-spacing: 0px;font-size: 12px;">${podInfo[5]}</p>
												</div>
												<div class="mui-pull-right" >
													¥${podInfo[2]}<br/>
													<span class="mui-pull-right">x${podInfo[3]}</span>
												</div>
								
											</li>
										</c:forEach>
										<li class="mui-table-view-cell" style="height:38px;padding-top: 9px;">
											<a id="head" style="font-size: 13px;color: #A2A2A2;">共${poInfo.podNum }种商品
												<c:if test="${poInfo.po.productOrderCredictUsed!=0}">
													<span class="mui-pull-center">使用${poInfo.po.productOrderCredictUsed}积分</span>
												</c:if>
												<span class="mui-pull-right totalPrice">总金额：¥${poInfo.po.productOrderAllCount}</span>
											</a>
										</li>
										<li class="mui-table-view-cell" style="font-size: 15px ;padding-top: 11px;">
											<input type="hidden" class="productOrderId" value="${poInfo.po.productOrderId}" />
											<input type="hidden" class="productOrderPrice" value="${poInfo.po.productOrderAllCount}"/>
											<button class="mui-pull-right orderPay" style="font-size: 12px;background-color: #fc5959;color: white;">付款</button>
											<button class="mui-pull-right cancelBtn" onclick="cancelOrder(${poInfo.po.productOrderId})">取消订单</button>
										</li>
									</ul>
								</c:forEach>
								<div style="height: 50px;background-color: #efeff4;"></div>						
							</div>
						</div>

					</div>
					<!-- 代付款 end -->
					<!-- 待发货 start -->
					<div id="item3mobile" class="mui-slider-item mui-control-content"style="background-color: #efeff4;border: 0;">
						<div id="scroll3" class="mui-scroll-wrapper">
							<div class="mui-scroll">
								
								<c:forEach items="${list2}" var="poInfo">
									<div style="height: 10px;background-color: #efeff4;"></div>
									<ul class="mui-table-view">
										<li class="mui-table-view-cell" style="height:50px;padding-top: 15px;">
											<span class="mui-pull-left"style="font-size: 15px;color: #A2A2A2;">${poInfo.po.productOrderIntime }</span>
											<span class="mui-pull-right" style="font-size: 14px;color:red;">等待卖家发货</span>
										</li>
										<c:forEach items="${poInfo.podInfo}" var="podInfo">
											<li class="mui-table-view-cell" style="height:100px;padding-top: 15px;">
												<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=path%>${podInfo[1]}">
												<div class="mui-media-body" style="width: 190px;font-size: 13px;letter-spacing: -2px;float: left;">
													${podInfo[0]}
													<p class='mui-ellipsis' style="letter-spacing: 0px;font-size: 12px;">${podInfo[5]}</p>
												</div>
												<div class="mui-pull-right" >
													¥${podInfo[2]}<br/>
													<span class="mui-pull-right">x${podInfo[3]}</span>
												</div>
											</li>
										</c:forEach>
										<li class="mui-table-view-cell" style="height:38px;padding-top: 9px;">
											<a id="head" style="font-size: 13px;color: #A2A2A2;">共${poInfo.podNum }种商品
												<c:if test="${poInfo.po.productOrderCredictUsed!=0}">
													<span class="mui-pull-center">使用${poInfo.po.productOrderCredictUsed}积分</span>
												</c:if>
												<span class="mui-pull-right">总金额：¥${poInfo.po.productOrderAllCount}</span>
											</a>
										</li>
										<!-- <li class="mui-table-view-cell" style="font-size: 15px ;padding-top: 11px;">
											<button class="mui-pull-right" style="font-size: 12px;background-color: #fc5959;color: white;">提醒</button>
											<button class="mui-pull-right cancelBtn" >取消订单</button>
										</li> -->
									</ul>
								</c:forEach>
								<div style="height: 50px;background-color: #efeff4;"></div>
							</div>
						</div>
					</div>
					<!-- 待发货 end -->
					<!-- 待收货 start-->
					<div id="item4mobile" class="mui-slider-item mui-control-content"style="background-color: #efeff4;border: 0;">
						<div id="scroll4" class="mui-scroll-wrapper">
							<div class="mui-scroll">
								
								<c:forEach items="${list3}" var="poInfo">
									<div style="height: 10px;background-color: #efeff4;"></div>
									<ul class="mui-table-view">
										<li class="mui-table-view-cell" style="height:50px;padding-top: 15px;">
											<span class="mui-pull-left"style="font-size: 15px;color: #A2A2A2;">${poInfo.po.productOrderIntime }</span>
											<span class="mui-pull-right" style="font-size: 14px;color:red;">等待买家收货</span>
										</li>
										<c:forEach items="${poInfo.podInfo}" var="podInfo">
											<li class="mui-table-view-cell" style="height:100px;padding-top: 15px;">
												<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=path%>${podInfo[1]}">
												<div class="mui-media-body" style="width: 190px;font-size: 13px;letter-spacing: -2px;float: left;">
													${podInfo[0]}
													<p class='mui-ellipsis' style="letter-spacing: 0px;font-size: 12px;">${podInfo[5]}</p>
												</div>
												<div class="mui-pull-right" >
													¥${podInfo[2]}<br/>
													<span class="mui-pull-right">x${podInfo[3]}</span>
												</div>
								
											</li>
										</c:forEach>
										<li class="mui-table-view-cell" style="height:38px;padding-top: 9px;">
											<a id="head" style="font-size: 13px;color: #A2A2A2;">共${poInfo.podNum }种商品
												<c:if test="${poInfo.po.productOrderCredictUsed!=0}">
													<span class="mui-pull-center">使用${poInfo.po.productOrderCredictUsed}积分</span>
												</c:if>
												<span class="mui-pull-right">总金额：¥${poInfo.po.productOrderAllCount}</span>
											</a>
										</li>
										<li class="mui-table-view-cell" style="font-size: 15px ;padding-top: 11px;">
											<button class="mui-pull-right" style="font-size: 12px;background-color: #fc5959;color: white;" onclick="recept(${poInfo.po.productOrderId})">收货</button>
										</li>
									</ul>
									
								</c:forEach>
								<div style="height: 50px;background-color: #efeff4;"></div>
							</div>
						</div>
					</div>
					<!-- 待收货 end-->
					<!-- 待评价 start-->
					<div id="item5mobile" class="mui-slider-item mui-control-content"style="background-color: #efeff4;border: 0;">
						<div id="scroll5" class="mui-scroll-wrapper">
							<div class="mui-scroll">
								<c:forEach items="${list4}" var="poInfo">
									<div style="height: 10px;background-color: #efeff4;"></div>
									<ul class="mui-table-view">
										<li class="mui-table-view-cell" style="height:43px;padding-top: 15px;">
											<span class="mui-pull-left"style="font-size: 14px;color: #A2A2A2;">${poInfo.po.productOrderIntime }</span>
											<span class="mui-pull-right" style="font-size: 14px;color:#fc5959;">交易成功</span>
										</li>
										<c:forEach items="${poInfo.podInfo}" var="podInfo">
											<li class="mui-table-view-cell" style="height:100px;padding-top: 15px;">
												<img class="mui-media-object mui-pull-left head-img" id="head-img" src="<%=path%>${podInfo[1]}">
												<div class="mui-media-body" style="width: 190px;font-size: 13px;letter-spacing: -2px;float: left;">
													${podInfo[0]}
													<p class='mui-ellipsis' style="letter-spacing: 0px;font-size: 12px;">${podInfo[5]}</p>
												</div>
												<div class="mui-pull-right" >
													¥${podInfo[2]}<br/>
													<span class="mui-pull-right">x${podInfo[3]}</span>
												</div>
												
											</li>
											<li class="mui-table-view-cell" style="height:53px;font-size: 15px ;padding-top: 11px;">
											<c:if test="${podInfo[6]==false }">
												<button class="mui-pull-right payBtn" onclick="window.location.href='<%=basePath %>lp/rating?podId=${podInfo[4]}'">评价</button>
											</c:if>
											<c:if test="${podInfo[6]==true }">
												<button class="mui-pull-right payBtn" onclick="window.location.href='<%=basePath %>lp/lpComment'">查看评价</button>
											</c:if>
										</li>
										</c:forEach>
										<li class="mui-table-view-cell" style="height:38px;padding-top: 9px;">
											<a id="head" style="font-size: 13px;color: #A2A2A2;">共${poInfo.podNum }种商品
												<c:if test="${poInfo.po.productOrderCredictUsed!=0}">
													<span class="mui-pull-center">使用${poInfo.po.productOrderCredictUsed}积分</span>
												</c:if>
												<span class="mui-pull-right">总金额：¥${poInfo.po.productOrderAllCount}</span>
											</a>
										</li>
										<li class="mui-table-view-cell" style="height:53px;font-size: 15px ;padding-top: 11px;">
											<button class="mui-pull-right cancelBtn" onclick="deletePO(${poInfo.po.productOrderId})">删除订单</button>
										</li>
									</ul>
								</c:forEach>
								<div style="height: 50px;background-color: #efeff4;"></div>
							</div>
						</div>
					</div>
					<!-- 待评价end -->
				</div>
			</div>

		</div>
		<nav class="mui-bar mui-bar-tab">
			<jsp:include page="../footer.jsp"></jsp:include>
		</nav>
		<script src="<%=basePath%>wechat/js/mui.min.js"></script>
		<script type="text/javascript">
			function cancelOrder(poId){
				var btnArray = ['确认取消','取消'];
				mui.confirm('若取消该订单，即终止交易 ','安全提示 ',btnArray, function(e) {
					if(e.index==1){
						//这里是取消按钮
					}
					else if(e.index == 0 ){
						//这里是确认取消
						mui.ajax("<%=basePath%>lp/cancelPO",{
							data: {
				           		"poId":poId,
				           	},
				           	type:"POST",
				           	async:false,
				           	error:function(data){
				               alert("出錯了！");
				           },
				           success:function(data){
				               if (data == "true") {
				               	alert("订单已取消");
				                window.location.href="<%=basePath%>lp/orders";
				               }
				               else {
				                    alert("出錯了！");
				               }
				           }
						});
					}else{}
				});
			}	
			function deletePO(poId){
				mui.ajax("<%=basePath%>lp/cancelPO",{
					data: {
		           		"poId":poId,
		           	},
		           	type:"POST",
		           	error:function(data){
		               alert("出錯了！");
		           },
		           success:function(data){
		               if (data == "true") {
		               	alert("订单已删除");
		                window.location.href="<%=basePath%>lp/orders";
		               }
		               else {
		                    alert("出錯了！");
		               }
		           }
				});
			}
			function recept(poId){
				$.ajax({
					data: {
		           		"poId":poId,
		           	},
		           	type:"POST",
		           	url:"<%=basePath%>lp/recept",
		           	error:function(data){
		               alert("出錯了！");
		           },
		           success:function(data){
		               if (data == "true") {
		               	alert("已收货");
		                window.location.href="<%=basePath%>lp/orders";
		               }
		               else {
		                    alert("出錯了！");
		               }
		           }
				});
			}
			mui.init({
				swipeBack:false
			});
			(function($){
				$('.mui-scroll-wrapper').scroll({
					indicators:true
				});
			})(mui);	
		</script>
	</body>

</html>