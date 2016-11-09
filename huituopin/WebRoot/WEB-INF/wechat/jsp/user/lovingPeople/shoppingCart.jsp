<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="UTF-8">
		<title>我的购物车</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/shopCar.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/weui.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/mui1.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>wechat/css/footer.css"/>
		<script src="<%=basePath%>wechat/js/jquery-1.9.1.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>wechat/js/shopcar.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=basePath%>wechat/js/footer.js"></script>
		<script type="text/javascript" src="<%=path %>/wechat/js/mui.min.js"></script>
		<style>
			.mui-bar{
				box-shadow: 0 0;
			}
		</style>
	</head>
	<body>
		<input id="basePath" value="<%=basePath%>" style="display: none"/>
	  	<div id="allItem">
	  		<c:forEach items="${list}" var="scInfo" varStatus="status">
	  			<input id="pId${status.index}" type="hidden" value="${scInfo[1]}"/>
	  			<input id="pStock${status.index}" type="hidden" value="${scInfo[6]}"/>
	  			<input id="pSaled${status.index}" type="hidden" value="${scInfo[7]}"/>
	  			<input id="pLimit${status.index}" type="hidden" value="${scInfo[8]}"/>
				<div id="good${status.index}" class="my_item">
						<div class="my_item_ico">
							<img id="item_img${status.index}" class="item_img" src="<%=basePath%>wechat/images/circle.png" onClick="signalSelect('item_img${status.index}')"/>
						</div>
						<div class="my_item_goodico">
							<img class="item_img" src="<%=path%>${scInfo[3]}"/>
						</div>
			            <div id="content${status.index}">
						        <div id="my_item_gooddesc${status.index}" class="my_item_gooddesc">
							          ${scInfo[4]}
						       	</div>
						      	<div id="my_item_price${status.index}" class="my_item_price">
							         <div id="">
								            ￥<div id="price${status.index}" style=" display:inline;">${scInfo[5]}</div>
							         </div>
							         <div id="">
								     x<div id="amount${status.index}" style=" display:inline;">${scInfo[2]}</div>
							         </div>
					     		</div>
						        <div class="my_item_opt">
							        <a id="bianji${status.index}"  onclick="showNum(${status.index})">编辑</a>
						        </div>
			           	</div>
		               	<div id="selectNum${status.index}" style=" display:none; z-index:-1;width:65%; height:100px; margin-left:35%;">
		                       <div style=" width:100%; height:50px;">
		                            <div style=" height:50px; background-color:#f8f8f8; display:inline; padding:0px; margin:0px;"><input id="subdece${status.index}" type="text" onClick="subduce(${status.index})" value="-" readonly="readonly" style="height:50px; width:30%; text-align:center; margin:0px; border:0px;"/></div>
		                             <div  style=" height:50px; background-color:#f8f8f8; display:inline; padding:0px; margin:0px;"><input id="GoogNum${status.index}" type="text" value="${scInfo[2]}" style="height:30px; width:30%; text-align:center; margin:0px; margin-top:20px;"/></div>
		                           <div  style="height:50px; background-color:#f8f8f8; display:inline; padding:0px; margin:0px;"><input id="add${status.index}" type="text" onClick="addNum(${status.index})" value="+" readonly="readonly" style="height:50px; width:30%; text-align:center; margin:0px; border:0px;"/></div>
		                       </div>
		                       <div style="">
		                              <div style=" width:50%;height:50px;display:inline; text-align:center;"> <a href="javascript:;" class="weui_btn weui_btn_primary" onClick="completeNum(${status.index})" style="width:40%; float:left; height:30px; line-height:30px; margin:10px 5% 10px 5%; font-family:'黑体';">完成</a></div>
		                              <div style=" width:50%;height:50px; display:inline; text-align:center;"> <a href="javascript:;" class="weui_btn weui_btn_warn" style=" width:40%; height:30px; float:right; margin:10px 5% 10px 5%; line-height:30px;font-family:'黑体';" onclick="return deleteSC(${scInfo[0]})">删除</a></div>
		                       </div>
		               	</div>
				</div>
			</c:forEach>
		<div style="height:95px;"></div>
			<div class="my_bottom">	
			<table class="my_pay">
				<tr>
					<td id="AllSelectImg" class="my_td1">
						<img id="all_select" src="<%=basePath%>wechat/images/circle.png" onClick="AllSelect()"/>
						<span id="all_select_word">全选</span>
					</td>
					<td class="my_td2">
						合计:
						<span >¥<div id="sumMoney" style=" display:inline;">0</div></span>
					</td>
					<td class="my_td3 ">
						<div class="weui_btn weui_btn_warn" style="height: 35px;width: 90px;border-radius:3px;font-size: 15px ;" onclick="submit()">
							结算(<span id="jiesuan_num">0</span>)
						</div>
					</td>
				</tr>
			</table>
				<nav class="mui-bar mui-bar-tab">
					<jsp:include page="../footer.jsp"></jsp:include>
				</nav>
		</div>
		</div>		
		<script type="text/javascript">
			function deleteSC(scId){
				$.ajax({
		           data: {
		           	"scId":scId,
		           	},
		           type:"POST",
		           url:"<%=basePath%>lp/deleteSC",
		           error:function(data){
		               alert("出錯了！");
		           },
		           success:function(data){
		               if (data == "true") {
		                window.location.href="<%=basePath%>lp/shopCart";
		               }
		               else {
		                    alert("出錯了！");
		               }
		           }
		       });
			}
			function submit(){
				var aLi = $("#allItem .my_item");
				var pIds="",pNums="";
			    for (var i = 0; i < aLi.length; i++) {
			        if (document.getElementById("item_img" + i).getAttribute("src") ==  "<%=basePath%>wechat/images/choose.png") {
			            pIds+=$("#pId"+i).val()+"_";
			            if(parseInt($("#pStock"+i).val())==parseInt($("#pSaled"+i).val())){
			            	var btnArray = [$("#my_item_gooddesc"+i).text()+"已售完"];
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
			            if(parseInt($("#pStock"+i).val())-parseInt($("#pSaled"+i).val())<parseInt($("#GoogNum"+i).val())){
			            	var btnArray = [$("#my_item_gooddesc"+i).text()+"库存不足"];
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
		                if($("#pLimit"+i).val()!="0" && parseInt($("#pLimit"+i).val())<parseInt($("#GoogNum"+i).val())){
			            	var btnArray = [$("#my_item_gooddesc"+i).text()+"限购"+$("#pLimit"+i).val()+"件"];
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
			            pNums+=$("#GoogNum"+i).val()+"_";
			        }
			    }
			    if(pNums){
			    	window.location.href="<%=basePath%>wechat/aigou/payment?pId="+pIds+"&pNum="+pNums+"&allNum="+$("#jiesuan_num").html() + "&count="+$("#sumMoney").html()+"&resource=sc";
			    }else{
			    	var btnArray = ["您没有选择任何商品"];
                    mui.alert(' ',' ',btnArray, function() {
                    	if (self.callback) {
			                var rs = self.callback(self.getSelectedItems());
			                if (rs !== false) {
			                  self.hide();
			                }
		                }
                	});
			    }
			}
		</script>
			
	</body>
</html>