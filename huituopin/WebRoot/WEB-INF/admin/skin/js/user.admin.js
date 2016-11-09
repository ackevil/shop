function check(){
	
	if(checkNull("#txtUserName","用户名不能为空"))
		return false;
	if(checkNull("#txtPsw","密码不能为空"))
		return false;
	var basePath = $("#basePath").val();
	var userName = $("#txtUserName").val();
	var userPwd = $("#txtPsw").val();
	gotoNextPageWithParam("checkUserName",userName,userPwd);
}
function resetValue(){
	$("#txtUserName").val("");
	$("#txtPsw").val("");
}


function gotoNextPageWithParam(action,userName,userPwd){
	form = $("<form></form>");
     form.attr('action',action);
     form.attr('method','post');
     input1 = $("<input type='hidden' name='userName' />");
     input1.attr('value',userName);
     input2 = $("<input type='hidden' name='userPwd' />");
     input2.attr('value',userPwd);
     form.append(input1);
     form.append(input2);
     form.appendTo("body");
     form.css('display','none');
     form.submit();
}
function checkNull(id,text){
				var value = $(id).val();
				if(value == "" || value == null){
					alert(text);
					return true;
				}
			}