// JavaScript Document
//���ȫѡ��ť
//var basePath = $("#basePath").val();
function AllSelect() {
	var basePath = $("#basePath").val();
    //var aLi = getByClass($("allItem"), "my_item"); //�������Ƕ�λ�ĺ����һ������
    var aLi = $("#allItem .my_item");
	//��һ�������е�ͼ��仯
    if (document.getElementById("all_select").getAttribute("src") == basePath+"wechat/images/circle.png") {
        document.getElementById("all_select").setAttribute("src",  basePath+"wechat/images/choose.png");
        for (var i = 0; i < aLi.length; i++) {
        	$("#item_img"+i).attr("src",basePath+"wechat/images/choose.png");
        }
        //�ڶ���������ı仯
        var sumPrice=0;
        for (var i = 0; i < aLi.length; i++) {
            sumPrice += parseFloat(document.getElementById("price" + i).innerHTML) *parseInt(document.getElementById("amount" + i).innerHTML);
        }
        document.getElementById("sumMoney").innerHTML = sumPrice.toFixed(2).toString();
        //�������ͼ���������ֱ仯
        document.getElementById("jiesuan_num").innerHTML = aLi.length.toString();
    }
    else {
        document.getElementById("all_select").setAttribute("src",  basePath+"wechat/images/circle.png");
        for (var i = 0; i < aLi.length; i++) {
        	$("#item_img"+i).attr("src",basePath+"wechat/images/circle.png");
        }
        document.getElementById("sumMoney").innerHTML = "0";
        document.getElementById("jiesuan_num").innerHTML ="0";
    }
}
//function $(id) {
//    return document.getElementById(id);//ͨ��ID����һ��Ԫ��
//}
function getByClass(oParent, sClass) {
    var aResult = []; //����һ��������
    var aEle = oParent.getElementsByTagName("*");//��oParent������Ԫ�ؽ��в�׽�γ�һ����������aEle������
    for (var i = 0; i < aEle.length; i++) {
        if (aEle[i].className == sClass) {
            aResult.push(aEle[i]);//���forѭ����oParent������Ԫ�ر������������sClass��ͬ������֮ǰ����������С�
        }
    }
    return aResult;//������
}
//�����ѡ��ť
function signalSelect(str) {
	var basePath = $("#basePath").val();
	var sumMoney = 0;
	var jiesuan_num=0;
    //var aLi = getByClass($("allItem"), "my_item"); //�������Ƕ�λ�ĺ����һ������
	var aLi = $("#allItem .my_item");
    if (document.getElementById(str).getAttribute("src") ==  basePath+"wechat/images/circle.png") {
        document.getElementById(str).setAttribute("src",  basePath+"wechat/images/choose.png");
        for (var i = 0; i < aLi.length; i++) {
            if (document.getElementById("item_img" + i).getAttribute("src") == basePath+"wechat/images/choose.png") {
                jiesuan_num++;
                sumMoney += parseFloat(document.getElementById("price" + i).innerHTML) * parseInt(document.getElementById("amount" + i).innerHTML);
            }
        }
        if (jiesuan_num == aLi.length) {
            document.getElementById("all_select").setAttribute("src",  basePath+"wechat/images/choose.png");
            document.getElementById("all_select_word").style.color="#fc5959";
        } 
        else {
            document.getElementById("all_select").setAttribute("src",  basePath+"wechat/images/circle.png");
            document.getElementById("all_select_word").style.color="#CFCFCF";
        }
        document.getElementById("sumMoney").innerHTML = parseFloat(sumMoney).toFixed(2).toString();
        document.getElementById("jiesuan_num").innerHTML = jiesuan_num.toString();   
    }
    else {
        document.getElementById(str).setAttribute("src",  basePath+"wechat/images/circle.png");
        for (var i = 0; i < aLi.length; i++) {
            if (document.getElementById("item_img" + i).getAttribute("src") == basePath+"wechat/images/choose.png") {
                jiesuan_num++;
                sumMoney += parseFloat(document.getElementById("price" + i).innerHTML) * parseInt(document.getElementById("amount" + i).innerHTML);
            }
        }
        if (jiesuan_num == aLi.length) {
            document.getElementById("all_select").setAttribute("src", basePath+"wechat/images/choose.png");
            document.getElementById("all_select_word").style.color="#fc5959";
        }
        else {
            document.getElementById("all_select").setAttribute("src",  basePath+"wechat/images/circle.png");
            document.getElementById("all_select_word").style.color="#CFCFCF";
        }
        document.getElementById("sumMoney").innerHTML = parseFloat(sumMoney).toFixed(2).toString();
        document.getElementById("jiesuan_num").innerHTML = jiesuan_num.toString();
    }
}
//����༭��ǩ
function showNum(str) {
    document.getElementById('content' + str).style.display = 'none'; //����  
    document.getElementById("selectNum"+str).style.display = 'block'; //��ʾ
}
//����Ӻ��¼�
function addNum(str) {
    var goodNum = document.getElementById('GoogNum' + str).value;
    if (parseInt(goodNum) >=1 && parseInt(goodNum) < 99) {
        goodNum = parseInt(goodNum) + 1;
        document.getElementById('GoogNum' + str).value = goodNum.toString();
    }
}
//��������¼�
function subduce(str) {
    var goodNum = document.getElementById('GoogNum' + str).value;
    if (parseInt(goodNum) > 1 && parseInt(goodNum) <= 99) {
        goodNum = parseInt(goodNum) - 1;
        document.getElementById('GoogNum' + str).value = goodNum.toString();
    }
}
//�����ɰ�ť
function completeNum(str) {
    var goodNum = document.getElementById('GoogNum' + str).value;
    document.getElementById("amount" + str).innerHTML = goodNum;
    document.getElementById('content' +str).style.display = 'block'; //��ʾ
    document.getElementById("selectNum" + str).style.display = 'none'; //����
    //ÿ�ε���ɰ�ťҪ�����ܽ��
    countSumMoney();
}
function countSumMoney() {
	var basePath = $("#basePath").val();
    //var aLi = getByClass($("allItem"), "my_item"); //�������Ƕ�λ�ĺ����һ������
    var aLi = $("#allItem .my_item");
	var sumMoney = 0;
    var jiesuan_num = 0;
    for (var i = 0; i < aLi.length; i++) {
        if (document.getElementById("item_img" + i).getAttribute("src") ==  basePath+"wechat/images/choose.png") {
            jiesuan_num++;
            sumMoney += parseFloat(document.getElementById("price" + i).innerHTML) * parseInt(document.getElementById("amount" + i).innerHTML);
        }
    }
    document.getElementById("sumMoney").innerHTML = parseFloat(sumMoney).toFixed(2).toString();
    document.getElementById("jiesuan_num").innerHTML = jiesuan_num.toString();
}