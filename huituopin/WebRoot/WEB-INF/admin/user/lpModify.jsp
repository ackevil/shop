<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Hello MUI</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="../skin/css/style.css" rel="stylesheet" />
	    <script type="text/javascript" charset="utf-8" src="../skin/js/jquery-1.11.2.min.js"></script>
	    <script type="text/javascript" charset="utf-8" src="../skin/js/Validform_v5.3.2_min.js"></script>
	    <script type="text/javascript" src="../skin/js/jsAddress.js"></script>
	    <script type="text/javascript" charset="utf-8" src="../skin/datepicker/WdatePicker.js"></script>
	    <script type="text/javascript" charset="utf-8" src="../skin/js/laymain.js"></script>
	    <script type="text/javascript" charset="utf-8" src="../skin/js/common.js"></script>
	    <script src="../skin/js/showDialog.js" charset="gb2312" type="text/javascript"></script>  
	    <script src="../skin/js/mui.min.js"></script>
	    <link href="../skin/css/showDialog.css" rel="stylesheet" />
	    <link href="../skin/css/webuploader.css" rel="stylesheet" />
	    <link rel="stylesheet" href="../skin/css/mui.min.css">
	    <script src="../skin/js/webuploader.js"></script>
		<!--标准mui.css-->
		<link rel="stylesheet" href="../skin/css/mui.min.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="../css/app.css" />
<style>
.mui-control-content {
  background-color: white;
  min-height:700px;
}
.mui-control-content .mui-loading {
	margin-top: 50px;
}
.multi-radio a {
    height: 35px;
    margin-right: 10px;
    background-color: #ffffff;
}
.single-select .select-tit{
height:35px;
}
</style>
	</head>
	<body>
	<!--头部-->
		<jsp:include page="../commonPage/commonTop.jsp"></jsp:include>
		<!--/头部-->
		<!-- 左侧 -->
		<jsp:include page="../commonPage/commonLeft.jsp"></jsp:include>
		<!-- /左侧 -->
		<div class="main-container">
		<div id="slider" class="mui-slider">
			<div id="sliderSegmentedControl" class=" mui-segmented-control">
				<a id="one1" class="mui-control-item off" href="#item1mobile">
					基本信息
				</a>
                <a class="mui-control-item" href="#item2mobile">
					捐赠信息
				</a>
				<a class="mui-control-item" href="#item3mobile">
					消费记录
				</a>
				<a class="mui-control-item" href="#item4mobile">
					订单
				</a>							
				<a class="mui-control-item" href="#item5mobile">
					收藏的商品
				</a>
				<a class="mui-control-item" href="#item6mobile">
					兑换记录
				</a>
				<a class="mui-control-item" href="#item7mobile">
					评价
				</a>
			</div>	
			<div id="item1mobile" class=" mui-control-content mui-active">
				<div id="scroll1" class="mui-scroll-wrapper">
					<div class="mui-scroll tab-content" style="display: block;">
						<dl>
                        <dt style="padding-top: 2px;">用户类型</dt>
                        <dd>
                            <div class="rule-single-select">
                                <select name="ddlCategoryID" id="ddlCategoryID" datatype="*" sucmsg=" ">
                                    <option value="">请选择类型...</option>
                                    <option value="1">爱心人士</option>
                                    <option value="2">贫困人士</option>     
                                </select>
                            </div>
                        </dd>
	                    </dl>
						<dl>
	                        <dt>显示状态</dt>
	                        <dd>
	                            <div class="rule-multi-radio multi-radio">
	                                <div class="boxwrap"><a href="" class="selected">正常</a><a href="">待审核</a></div>
	                                <span id="rblStatus" style="display: none;">
	                                    <table id="rbtStatus" border="0">
	                                        <tr>
	                                            <td><input id="rbtStatus0" type="radio" name="rbtStatus0" value="正常" checked="checked" /><label for="rbtStatus0">正常</label></td>
	                                        </tr><tr>
	                                            <td><input id="rbtStatus1" type="radio" name="rbtStatus0" value="待审核" /><label for="rbtStatus1">待审核</label></td>
	                                        </tr>
	                                    </table>
	                                </span>
	                            </div>
	                        </dd>
	                    </dl>
	                    <dl>
	                        <dt>真实姓名</dt>
	                        <dd>
	                            <input name="txtName" type="text" id="txtName" value="王二" class="input normal" datatype="*2-4" sucmsg=" " />
	                            <span class="Validform_checktip">*名字最多4个字符</span> </dd>
	                    </dl>
	                    <dl>
	                        <dt>性别</dt>
	                        <dd>
	                            <div class="rule-multi-radio multi-radio">
	                                <div class="boxwrap"><a href="" class="selected">男</a><a href="">女</a></div>
	                                <span id="rblStatus1" style="display: none;">
	                                    <table id="rbtStatus1" border="0">
	                                        <tr>
	                                            <td><input id="rbt0" type="radio" name="rbtStatus" value="男" checked="checked" /><label for="rbt0">男</label></td>
	                                        </tr><tr>
	                                            <td><input id="rbt1" type="radio" name="rbtStatus" value="女" /><label for="rbt1">女</label></td>
	                                        </tr>
	                                    </table>
	                                </span>
	                            </div>
	                        </dd>
	                    </dl>
	                    <dl>
	                        <dt>年龄</dt>
	                        <dd>
	                            <input name="txtName" type="text" id="txtName" value="27" class="input normal" datatype="n" sucmsg=" " />
	                            <span class="Validform_checktip">*年龄格式不对</span> 
	                        </dd>
	                    </dl>
	                    <dl>
	                        <dt>籍贯</dt>
	                        <dd>
	                            <input name="txtName" type="text" id="txtName" value="湖南长沙" class="input normal" datatype="*2-10" sucmsg=" " />
	                            <span class="Validform_checktip">*籍贯最多10个字符</span> </dd>
	                    </dl>
	                    <dl>
	                        <dt>民族</dt>
	                        <dd>
	                            <input name="txtName" type="text" id="txtName" value="汉族" class="input normal" datatype="*2-10" sucmsg=" " />
	                            <span class="Validform_checktip">*民族格式不对</span> 
	                        </dd>
	                    </dl>
	                    <dl>
	                        <dt>家庭住址</dt>
	                        <dd>
	                        	<div class="rule-single-select">
					                             省<select id="province" name="province"></select>
					            </div>
					            <div class="rule-single-select">
					                             市<select name="city" id="city"></select>
					                             
					            </div>  
					            <div class="rule-single-select">
	                             	区<select name="area" id="area"></select>
	                        	</div>
	                            <script type="text/javascript">
	                             addressInit('province', 'city', 'area', '湖南', '长沙市', '岳麓区');
	                             addressInit('Select1', 'Select2', 'Select3');
	                            </script>
	                        </dd>
	                    </dl>
	                    <dl>
	                        <dt>手机号码</dt>
	                        <dd>
	                            <input name="txtName" type="text" id="txtName" value="15084945438" class="input normal" datatype="n11" sucmsg=" " />
	                            <span class="Validform_checktip">*手机号码格式不对</span>
	                        </dd>
	                    </dl>
	                    <div class="page-footer">
			                <div class="btn-wrap" style="position: static;padding-left: 200px;">
			                    <input type="submit" name="btnSave" value="提交保存" id="btnSave" class="btn" />
			                    <a href="userList.html" class="btn yellow">返回上一页</a>
			                </div>
			            </div>
					</div>					
				</div>
			</div>
	           
			<div id="item2mobile" class="mui-slider-item mui-control-content">
				<div id="scroll2" class="mui-scroll-wrapper">
					<div class="mui-scroll">			
						<div class="mui-scroll tab-content" style="display: block;text-align: left;">
							<div id="sliderSegmentedControl" class=" mui-segmented-control" style="width: 300px;">
								<a id="one1" class="mui-control-item off" href="#item1mobile">
									捐赠的物品
								</a>
				                <a class="mui-control-item" href="#item2mobile">
									捐助的金额
								</a>
							</div>
							<div style="height: 50px;">
								<span style="display: block;padding-top: 20px;"> 共有<span>7</span>条记录</span>
							</div>
							
							<table style="width: 100%;">
								<tr style="height: 40px;background-color: #F4F5F9;">
									<th style="width: 10%;padding-left: 20px;">图片</th>
									<th style="width: 25%;">项目名称</th> 
									<th style="width: 25%;text-align: center;">捐助金额（元）</th> 
									<th style="width: 20%;">捐赠时间</th> 
									<th style="width: 12%;">获助者</th> 
									<th style="width: 5%;text-align: center;">操作</th> 
								</tr>
								<tr style="height: 60px;">
									<td style="width: 10%;padding-left: 20px;"><img src="../skin/images/tupian.jpg" style="max-width:50px;height: 50px;"/></td>
									<td style="width: 25%;">灵魂洗涤 淳净未来------为谱写灵魂洗涤 淳净未来------为谱写灵魂洗涤 淳净未来------为谱写</td> 
									<td style="width: 25%;text-align: center;">500.00</td> 
									<td style="width: 20%;">2016-05-02 09:32</td> 
									<td style="width: 12%;color: #61ADFF;">李俊</td> 
									<td style="width: 5%;">
										<div style="height: 30px;width: 100%;background-color: #F4F5F9;text-align: center;padding-top: 3px;"><a style="color: black;">查看</a></div>
									</td> 
								</tr>
								<tr style="height: 60px;">
									<td style="width: 10%;padding-left: 20px;"><img src="../skin/images/tupian.jpg" style="max-width:50px;height: 50px;"/></td>
									<td style="width: 25%;">灵魂洗涤 淳净未来------为谱写灵魂洗涤 淳净未来------为谱写灵魂洗涤 淳净未来------为谱写</td> 
									<td style="width: 25%;text-align: center;">500.00</td> 
									<td style="width: 20%;">2016-05-02 09:32</td> 
									<td style="width: 12%;color: #61ADFF;">李俊</td> 
									<td style="width: 5%;">
										<div style="height: 30px;width: 100%;background-color: #F4F5F9;text-align: center;padding-top: 3px;"><a style="color: black;">查看</a></div>					
									</td> 
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div id="item3mobile" class="mui-slider-item mui-control-content">
				<div id="scroll3" class="mui-scroll-wrapper">
					<div class="mui-scroll">
						<div class="mui-scroll tab-content" style="display: block;text-align: left;">
							<div style="height: 50px;">
								<span style="display: block;padding-top: 20px;">共有<span>7</span>条记录,有<span>45</span>个订单，订单总额<span>1404.00</span>元</span>
							</div>						
							<table style="width: 100%;">
								<tr style="height: 40px;background-color: #F4F5F9;width:100%">
									<th style="width: 15%;padding-left: 20px;">支付时间</th>
									<th style="width: 20%;">名称</th> 
									<th style="width: 20%;text-align: center;">订单号/支付流水号</th> 
									<th style="width: 10%;">单价（元）</th> 
									<th style="width: 10%;">数量</th> 
									<th style="width: 10%;">金额（元）</th>
									<th style="width: 10%;">状态</th>
									<th style="width: 5%;text-align:center">操作</th> 
								</tr>
								<tr style="height: 60px;">
									<th style="width: 15%;padding-left: 20px;">2016-04-29 09:28</th>
									<th style="width: 20%;">多鲜 牛奶蛋羹为夹心多鲜 牛奶蛋羹为夹心多鲜 牛奶蛋羹为夹心多鲜 牛奶蛋羹为夹心</th> 
									<th style="width: 20%;text-align: center;">w201012645178256<br/>										S1265417825125</th> 
									<th style="width: 10%;">36.80</th> 
									<th style="width: 10%;">1</th> 
									<th style="width: 10%;color: #3DC700;">36.80</th>
									<th style="width: 10%;">交易成功</th>
									<th style="width: 5%;">										
										<div style="height: 30px;width: 100%;background-color: #F4F5F9;text-align: center;padding-top: 3px;"><a style="color: black;">查看</a></div>					
									</th> 
								</tr>
								<tr style="height: 60px;">
									<th style="width: 15%;padding-left: 20px;">2016-04-29 09:28</th>
									<th style="width: 20%;">多鲜 牛奶蛋羹为夹心多鲜 牛奶蛋羹为夹心多鲜 牛奶蛋羹为夹心多鲜 牛奶蛋羹为夹心</th> 
									<th style="width: 20%;text-align: center;">w201012645178256<br/>										S1265417825125</th> 
									<th style="width: 10%;">36.80</th> 
									<th style="width: 10%;">1</th> 
									<th style="width: 10%;color: #3DC700;">36.80</th>
									<th style="width: 10%;">交易成功</th>
									<th style="width: 5%;">										
										<div style="height: 30px;width: 100%;background-color: #F4F5F9;text-align: center;padding-top: 3px;"><a style="color: black;">查看</a></div>					
									</th> 
								</tr>
							</table>	
						</div>
					</div>
				</div>
			</div>
			<div id="item4mobile" class="mui-slider-item mui-control-content">
				<div id="scroll4" class="mui-scroll-wrapper">
					<div class="mui-scroll">
						<div class="mui-scroll tab-content" style="display: block;text-align: left;">
							<div style="height: 50px;">
								<span style="display: block;padding-top: 20px;">共有<span>7</span>条记录</span>
							</div>
							<ul>
								<li style="height: 40px;background-color: #F4F5F9;width:100%;">
									<div style="width: 10%;padding:8px 0 0 25px;font-size: 15px;float: left;">图片</div>
									<div style="width: 25%;padding-top: 8px;font-size: 15px;float: left;">产品名称</div>
									<div style="width: 20%;text-align: center;padding-top: 8px;font-size: 15px;float: left;">单价</div>
									<div style="width: 15%;text-align: center;padding-top: 8px;font-size: 15px;float: left;">数量</div>
									<div style="width: 15%;text-align: center;padding-top: 8px;font-size: 15px;float: left;">交易状态</div>
									<div style="width: 15%;text-align: center;padding-top: 8px;font-size: 15px;float: left;">实收款（元）</div>
								</li>
								<div style="height: 15px;"></div>
								<li style="height: 40px;background-color: #F4F5F9;width:100%;">
									<div style="width: 60%;padding: 8px 0 0 25px;font-size: 15px;float: left;">订单号：W215871568715</div>
									<div style="width: 40%;padding-top: 8px;font-size: 15px;float: left;">成交时间：2016-04-29 09:39</div>
								</li>
								<li style="height: 60px;width:100%;">
									<div style="width: 10%;padding:8px 0 0 25px;font-size: 15px;float: left;"><img src="../skin/images/tupian.jpg" style="max-width:50px;height: 50px;"/></div>
									<div style="width: 25%;padding-top: 8px;font-size: 15px;float: left;">多鲜牛奶多鲜牛奶多鲜牛奶多鲜牛奶多鲜牛奶多鲜牛奶多鲜牛奶</div>
									<div style="width: 20%;text-align: center;padding-top: 8px;font-size: 15px;float: left;">36.80</div>
									<div style="width: 15%;text-align: center;padding-top: 8px;font-size: 15px;float: left;">1</div>
									<div style="width: 15%;text-align: center;padding-top: 8px;font-size: 15px;float: left;">等待买家付款</div>
									<div style="width: 15%;text-align: center;padding-top: 8px;font-size: 15px;float: left;">36.80<br/>(含快递0.00元)</div>
								</li>
								
								<div style="height: 15px;"></div>
								<li style="height: 40px;background-color: #F4F5F9;width:100%;">
									<div style="width: 60%;padding: 8px 0 0 25px;font-size: 15px;float: left;">订单号：W215871568715</div>
									<div style="width: 40%;padding-top: 8px;font-size: 15px;float: left;">成交时间：2016-04-29 09:39</div>
								</li>
								<li style="height: 60px;width:100%;">
									<div style="width: 10%;padding:8px 0 0 25px;font-size: 15px;float: left;"><img src="../skin/images/tupian.jpg" style="max-width:50px;height: 50px;"/></div>
									<div style="width: 25%;padding-top: 8px;font-size: 15px;float: left;">多鲜牛奶多鲜牛奶多鲜牛奶多鲜牛奶多鲜牛奶多鲜牛奶多鲜牛奶</div>
									<div style="width: 20%;text-align: center;padding-top: 8px;font-size: 15px;float: left;">36.80</div>
									<div style="width: 15%;text-align: center;padding-top: 8px;font-size: 15px;float: left;">1</div>
									<div style="width: 15%;text-align: center;padding-top: 8px;font-size: 15px;float: left;">等待买家付款</div>
									<div style="width: 15%;text-align: center;padding-top: 8px;font-size: 15px;float: left;">36.80<br/>(含快递0.00元)</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div id="item5mobile" class="mui-slider-item mui-control-content">
				<div id="scroll5" class="mui-scroll-wrapper">
					<div class="mui-scroll">
						<div class="mui-scroll tab-content" style="display: block;text-align: left;">
							<div style="height: 50px;">
								<span style="display: block;padding-top: 20px;">共有<span>7</span>条记录,有<span>45</span>个订单，订单总额<span>1404.00</span>元</span>
							</div>						
							<table style="width: 100%;">
								<tr style="height: 40px;background-color: #F4F5F9;width:100%">
									<th style="width: 10%;padding-left: 20px;">图片</th>
									<th style="width: 25%;">产品名称</th> 
									<th style="width: 25%;text-align: center;">数量</th> 
									<th style="width: 15%;">单价（元）</th> 
									<th style="width: 15%;">收藏时间</th> 
									<th style="width: 10%;text-align:center">操作</th> 
								</tr>
								<tr style="height: 40px;width:100%">
									<th style="width: 10%;padding-left: 20px;"><img src="../skin/images/tupian.jpg" style="max-width:50px;height: 50px;"/></th>
									<th style="width: 25%;">七格格七格格七格格七格格七格格七格格七格格格格七格格七格格七格格七格格七格格七</th> 
									<th style="width: 25%;text-align: center;">1</th> 
									<th style="width: 15%;">119.00</th> 
									<th style="width: 15%;">2016-02-01 11:09</th> 
									<th style="width: 5%;text-align:center">
										<div style="height: 30px;background-color: #F4F5F9;text-align: center;padding-top: 3px;margin:0 15px 0 15px;"><a style="color: black;">查看</a></div>		
									</th> 
								</tr>
								<tr style="height: 40px;width:100%">
									<th style="width: 10%;padding-left: 20px;"><img src="../skin/images/tupian.jpg" style="max-width:50px;height: 50px;"/></th>
									<th style="width: 25%;">七格格七格格七格格七格格七格格七格格七格格格格七格格七格格七格格七格格七格格七</th> 
									<th style="width: 25%;text-align: center;">1</th> 
									<th style="width: 15%;">119.00</th> 
									<th style="width: 15%;">2016-02-01 11:09</th> 
									<th style="width: 5%;text-align:center">
										<div style="height: 30px;background-color: #F4F5F9;text-align: center;padding-top: 3px;margin:0 15px 0 15px;"><a style="color: black;">查看</a></div>		
									</th> 
								</tr>
							</table>	
						</div>
					</div>
				</div>
			</div>
			<div id="item6mobile" class="mui-slider-item mui-control-content">
				<div id="scroll6" class="mui-scroll-wrapper">
					<div class="mui-scroll">
						<div class="mui-scroll tab-content" style="display: block;text-align: left;">
							<div style="height: 50px;">
								<span style="display: block;padding-top: 20px;">共有<span>7</span>条记录</span>
							</div>						
							<table style="width: 100%;">
								<tr style="height: 40px;background-color: #F4F5F9;width:100%">
									<th style="width: 10%;padding-left: 20px;">图片</th>
									<th style="width: 25%;">产品名称</th> 
									<th style="width: 35%;text-align: center;">数量</th> 
									<th style="width: 15%;">兑换积分</th> 
									<th style="width: 15%;">兑换时间</th> 
									
								</tr>
								<tr style="height: 60px;width:100%">
									<th style="width: 10%;padding-left: 20px;"><img src="../skin/images/tupian.jpg" style="max-width:50px;height: 50px;"/></th>
									<th style="width: 25%;">七格格七格格七格格七格格七格格七格格七格格格格七格格七格格七格格七格格七格格七</th> 
									<th style="width: 35%;text-align: center;">1</th> 
									<th style="width: 15%;">30</th> 
									<th style="width: 15%;">2016-05-01 11:39</th> 
									
								</tr>
								<tr style="height: 60px;width:100%">
									<th style="width: 10%;padding-left: 20px;"><img src="../skin/images/tupian.jpg" style="max-width:50px;height: 50px;"/></th>
									<th style="width: 25%;">七格格七格格七格格七格格七格格七格格七格格格格七格格七格格七格格七格格七格格七</th> 
									<th style="width: 35%;text-align: center;">1</th> 
									<th style="width: 15%;">30</th> 
									<th style="width: 15%;">2016-05-01 11:39</th> 
									
								</tr>
							</table>	
						</div>
					</div>
				</div>
			</div>
			<div id="item7mobile" class="mui-slider-item mui-control-content">
				<div id="scroll7" class="mui-scroll-wrapper">
					<div class="mui-scroll">
						<div class="mui-scroll tab-content" style="display: block;text-align: left;">
							<div style="height: 50px;">
								<span style="display: block;padding-top: 20px;">共有<span>7</span>条记录</span>
							</div>						
							<table style="width: 100%;">
								<tr style="height: 40px;background-color: #F4F5F9;width:100%">
									<th style="width: 15%;padding-left: 20px;">评价时间</th>
									<th style="width: 30%;">评价内容</th> 
									<th style="width: 15%;"></th> 
									<th style="width: 25%;">产品名称</th> 
									<th style="width: 15%;text-align: center;">操作</th> 							
								</tr>
								<tr style="height: 60px;width:100%">
									<th style="width: 15%;padding-left: 20px;">评价时间</th>
									<th style="width: 30%;">包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨</th> 
									<th style="width: 15%;"></th> 
									<th style="width: 25%;">多鲜牛奶包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨</th> 
									<th style="width: 15%;">
										<div style="height: 30px;background-color: #3DC700;text-align: center;padding-top: 3px;margin:0 45px 0 45px;"><a style="color: white;">查看</a></div>		
									</th> 							
								</tr>
								<tr style="height: 60px;width:100%">
									<th style="width: 15%;padding-left: 20px;">评价时间</th>
									<th style="width: 30%;">包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨</th> 
									<th style="width: 15%;"></th> 
									<th style="width: 25%;">多鲜牛奶包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨包装严谨</th> 
									<th style="width: 15%;">
										<div style="height: 30px;background-color: #3DC700;text-align: center;padding-top: 3px;margin:0 45px 0 45px;"><a style="color: white;">查看</a></div>		
									</th> 							
								</tr>	
							</table>	
						</div>
					</div>
				</div>
			</div>
		</div>
			
</div>
		
		<script src="../skin/js/mui.min.js"></script>
		

	</body>

</html>
