$(document).ready(function(){
//$(function(){
	var basePath = $("#basePath").val();
	mui.init({
		swipeBack: true
			//启用右滑关闭功能
	});
	$("#home").click(function(){
		homeClick("#img1","#img2",basePath+"wechat/images/htp_",basePath+"wechat/images/me_");
	});
	$("#me").click(function(){
		homeClick("#img2","#img1",basePath+"wechat/images/me_",basePath+"wechat/images/htp_");
		gotoNextPage(basePath+"register/userInfo");
	});
});


function typeChange(clothId,type,type2,userId,shipId){
	var basePath = $("#basePath").val();
	if(type=='common'){
		gotoNextPageWithParam(basePath+type2+"/deleteDefaultShipAddress",userId,shipId,clothId);
	}
	if(type=='default'){
		gotoNextPageWithParam(basePath+type2+"/changeShipAddressToDefault",userId,shipId,clothId);
	}
		
}


/**
 * 
 * @param {Object} userId
 * @param {Object} shipId
 */
function shipAddressDelete(clothId,type,userId,shipId){
	var basePath = $("#basePath").val();
	gotoNextPageWithParam(basePath+type+"/"+type+"AddressDelete",userId,shipId,clothId);
}
/**
 * 
 * @param {Object} userId
 * @param {Object} shipId
 */
function shipAddressEdit(clothId,type,shipId){
	var basePath = $("#basePath").val();
	gotoNextPageWithParam2(basePath+type+"/"+type+"AddressEdit",shipId,clothId);
}

function gotoNextPageWithParam2(action,shipId,clothId){
	form = $("<form></form>");
     form.attr('action',action);
     form.attr('method','post');
     input1 = $("<input type='hidden' name='shipId' />");
     input1.attr('value',shipId);
     input2 = $("<input type='hidden' name='type' />");
     input2.attr('value',clothId);
     form.append(input1);
     form.append(input2);
     form.appendTo("body");
     form.css('display','none');
     form.submit();
}



function gotoNextPageWithParam(action,userId,shipId,clothId){
	form = $("<form></form>");
     form.attr('action',action);
     form.attr('method','post');
     input1 = $("<input type='hidden' name='userId' />");
     input1.attr('value',userId);
     input2 = $("<input type='hidden' name='shipId' />");
     input2.attr('value',shipId);
     input3 = $("<input type='hidden' name='type' />");
     input3.attr('value',clothId);
     form.append(input1);
     form.append(input2);
     form.append(input3);
     form.appendTo("body");
     form.css('display','none');
     form.submit();
}



//---------------
/**
 * 检查和校验验证码
 */
function checkCode(){
	var code = $("#code").val();
	var basePath = $("#basePath").val();
	var phone = $("#userPhoneNb").val();
	if(code == "" || code == null){
		alert("请输入验证码");
		return false;
	}
	var reg = /\d{6}$/;
	if(!reg.test(code) || code.length>6){
		alert("您输入验证码的格式不正确···");
		return false;
	}
	var flag = false;
	$.ajax({
		type:"POST",
		url:basePath+"register/checkCodeAction",
		data:{
			code:code
		},
		success:function(msg){
			if(!msg){
				alert("您输入的验证码不正确，请重新输入");
				return false;
			}else{
				registeraction(basePath+"register/checkCode",phone);
			}
		}
	});
}

function registeraction(action,phone){
	form = $("<form></form>");
     form.attr('action',action);
     form.attr('method','post');
     input1 = $("<input type='hidden' name='phone' />");
     input1.attr('value',phone);
     form.append(input1);
     form.appendTo("body");
     form.css('display','none');
     form.submit();
}

/**
 * 检查和校验电话号码
 * @returns {Boolean}
 */
function checkPhone(){
	var basePath = $("#basePath").val();
	if(!validatePhone("userPhoneNb")){
		return false;
	}
	var userPhoneNb = $("#userPhoneNb").val();
	$.ajax({
		type:"POST",
		url:basePath+"register/checkPhone",
		data:{
			phone:userPhoneNb
		},
		success:function(msg){
			if(msg=="success"){//返回是true  说明号码可用 ，否则号码已经被注册
				alert("验证码发送成功，请注意查收");
				//改变按钮状态
				time();
			}else if(msg=="error1"){
				alert("手机号码已被注册不可用");				
			}else if(msg=="error2"){
				alert("验证码发送失败，请重新点击发送");
			}else if(msg=="error3"){
				alert("对不起，您今日获取的次数已达到上限···");
			}
		}
	});
	
}

var wait = 60;
function time() {
	var btn = $("#checkPhoneBtn");
    if (wait == 0) {
        btn.removeAttr("disabled");
        btn.text("获取验证码");
        wait = 60;
    } else {
        btn.attr("disabled", true);
        btn.text("重新获取"+wait);
        wait--;
        setTimeout(function () {
            time(btn);
        },
        1000);
    }
}

/**
 * 跳转页面
 * @param action
 */
function gotoNextPage(action){
     form = $("<form></form>");
     form.attr('action',action);
     form.attr('method','post');
     input1 = $("<input type='hidden' name='input1' />");
     input1.attr('value','input1 value');
     input2 = $("<input type='text' name='textinput' value='text input' />");
     form.append(input1);
     form.append(input2);
     form.appendTo("body");
     form.css('display','none');
     form.submit();
}
function homeClick(meImgId,otherImgId,meImgUrl,otherImgUrl){
	changeImg(meImgId,meImgUrl+"light.png");
	changeImg(otherImgId,otherImgUrl+"dark.png");
}
/**
 * 更换字体颜色=
 * @param TextId
 * @param color
 */
function changeTextColor(TextId,color){
	$(TextId).css("color",color);
}
/**
 * 更换图片
 * @param imgId
 * @param imgUrl
 */
function changeImg(imgId,imgUrl){
	$(imgId).attr("src",imgUrl);
}




