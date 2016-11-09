//验证手机号
//按照手机号码的规则去验证 
function validatePhone(lablePhone){
	var flag=true;
    reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
    var name = "#"+lablePhone;
    var phone = $(name).val();
    if (phone == "") {
    	alert("手机号码不能为空");
        flag=false;
    }else if (!reg.test(phone)) {
        alert("手机号码不合法");
        flag=false;
    } else {
    }
    return flag;
}