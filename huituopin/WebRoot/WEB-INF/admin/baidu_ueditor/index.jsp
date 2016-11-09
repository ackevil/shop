<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>å®æ´demo</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/admin/baidu_ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/admin/baidu_ueditor/ueditor.all.min.js"> </script>
    <!--å»ºè®®æå¨å å¨è¯­è¨ï¼é¿åå¨ieä¸ææ¶å ä¸ºå è½½è¯­è¨å¤±è´¥å¯¼è´ç¼è¾å¨å è½½å¤±è´¥-->
    <!--è¿éå è½½çè¯­è¨æä»¶ä¼è¦çä½ å¨éç½®é¡¹ç®éæ·»å çè¯­è¨ç±»åï¼æ¯å¦ä½ å¨éç½®é¡¹ç®ééç½®çæ¯è±æï¼è¿éå è½½çä¸­æï¼é£æåå°±æ¯ä¸­æ-->
    <script type="text/javascript" charset="utf-8" src="<%=path %>/admin/baidu_ueditor/lang/zh-cn/zh-cn.js"></script>

    <style type="text/css">
        div{
            width:100%;
        }
    </style>
</head>
<body>
<div>
    <h1>å®æ´demo</h1>
    <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
</div>
<div id="btns">
    <div>
        <button onclick="getAllHtml()">è·å¾æ´ä¸ªhtmlçåå®¹</button>
        <button onclick="getContent()">è·å¾åå®¹</button>
        <button onclick="setContent()">åå¥åå®¹</button>
        <button onclick="setContent(true)">è¿½å åå®¹</button>
        <button onclick="getContentTxt()">è·å¾çº¯ææ¬</button>
        <button onclick="getPlainTxt()">è·å¾å¸¦æ ¼å¼ççº¯ææ¬</button>
        <button onclick="hasContent()">å¤æ­æ¯å¦æåå®¹</button>
        <button onclick="setFocus()">ä½¿ç¼è¾å¨è·å¾ç¦ç¹</button>
        <button onmousedown="isFocus(event)">ç¼è¾å¨æ¯å¦è·å¾ç¦ç¹</button>
        <button onmousedown="setblur(event)" >ç¼è¾å¨å¤±å»ç¦ç¹</button>

    </div>
    <div>
        <button onclick="getText()">è·å¾å½åéä¸­çææ¬</button>
        <button onclick="insertHtml()">æå¥ç»å®çåå®¹</button>
        <button id="enable" onclick="setEnabled()">å¯ä»¥ç¼è¾</button>
        <button onclick="setDisabled()">ä¸å¯ç¼è¾</button>
        <button onclick=" UE.getEditor('editor').setHide()">éèç¼è¾å¨</button>
        <button onclick=" UE.getEditor('editor').setShow()">æ¾ç¤ºç¼è¾å¨</button>
        <button onclick=" UE.getEditor('editor').setHeight(300)">è®¾ç½®é«åº¦ä¸º300é»è®¤å³é­äºèªå¨é¿é«</button>
    </div>

    <div>
        <button onclick="getLocalData()" >è·åèç¨¿ç®±åå®¹</button>
        <button onclick="clearLocalData()" >æ¸ç©ºèç¨¿ç®±</button>
    </div>

</div>
<div>
    <button onclick="createEditor()">
    åå»ºç¼è¾å¨</button>
    <button onclick="deleteEditor()">
    å é¤ç¼è¾å¨</button>
</div>

<script type="text/javascript">

    //å®ä¾åç¼è¾å¨
    //å»ºè®®ä½¿ç¨å·¥åæ¹æ³getEditoråå»ºåå¼ç¨ç¼è¾å¨å®ä¾ï¼å¦æå¨æä¸ªé­åä¸å¼ç¨è¯¥ç¼è¾å¨ï¼ç´æ¥è°ç¨UE.getEditor('editor')å°±è½æ¿å°ç¸å³çå®ä¾
    var ue = UE.getEditor('editor');


    function isFocus(e){
        alert(UE.getEditor('editor').isFocus());
        UE.dom.domUtils.preventDefault(e)
    }
    function setblur(e){
        UE.getEditor('editor').blur();
        UE.dom.domUtils.preventDefault(e)
    }
    function insertHtml() {
        var value = prompt('æå¥htmlä»£ç ', '');
        UE.getEditor('editor').execCommand('insertHtml', value)
    }
    function createEditor() {
        enableBtn();
        UE.getEditor('editor');
    }
    function getAllHtml() {
        alert(UE.getEditor('editor').getAllHtml())
    }
    function getContent() {
        var arr = [];
        arr.push("ä½¿ç¨editor.getContent()æ¹æ³å¯ä»¥è·å¾ç¼è¾å¨çåå®¹");
        arr.push("åå®¹ä¸ºï¼");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
    }
    function getPlainTxt() {
        var arr = [];
        arr.push("ä½¿ç¨editor.getPlainTxt()æ¹æ³å¯ä»¥è·å¾ç¼è¾å¨çå¸¦æ ¼å¼ççº¯ææ¬åå®¹");
        arr.push("åå®¹ä¸ºï¼");
        arr.push(UE.getEditor('editor').getPlainTxt());
        alert(arr.join('\n'))
    }
    function setContent(isAppendTo) {
        var arr = [];
        arr.push("ä½¿ç¨editor.setContent('æ¬¢è¿ä½¿ç¨ueditor')æ¹æ³å¯ä»¥è®¾ç½®ç¼è¾å¨çåå®¹");
        UE.getEditor('editor').setContent('æ¬¢è¿ä½¿ç¨ueditor', isAppendTo);
        alert(arr.join("\n"));
    }
    function setDisabled() {
        UE.getEditor('editor').setDisabled('fullscreen');
        disableBtn("enable");
    }

    function setEnabled() {
        UE.getEditor('editor').setEnabled();
        enableBtn();
    }

    function getText() {
        //å½ä½ ç¹å»æé®æ¶ç¼è¾åºåå·²ç»å¤±å»äºç¦ç¹ï¼å¦æç´æ¥ç¨getTextå°ä¸ä¼å¾å°åå®¹ï¼æä»¥è¦å¨éåæ¥ï¼ç¶ååå¾åå®¹
        var range = UE.getEditor('editor').selection.getRange();
        range.select();
        var txt = UE.getEditor('editor').selection.getText();
        alert(txt)
    }

    function getContentTxt() {
        var arr = [];
        arr.push("ä½¿ç¨editor.getContentTxt()æ¹æ³å¯ä»¥è·å¾ç¼è¾å¨ççº¯ææ¬åå®¹");
        arr.push("ç¼è¾å¨ççº¯ææ¬åå®¹ä¸ºï¼");
        arr.push(UE.getEditor('editor').getContentTxt());
        alert(arr.join("\n"));
    }
    function hasContent() {
        var arr = [];
        arr.push("ä½¿ç¨editor.hasContents()æ¹æ³å¤æ­ç¼è¾å¨éæ¯å¦æåå®¹");
        arr.push("å¤æ­ç»æä¸ºï¼");
        arr.push(UE.getEditor('editor').hasContents());
        alert(arr.join("\n"));
    }
    function setFocus() {
        UE.getEditor('editor').focus();
    }
    function deleteEditor() {
        disableBtn();
        UE.getEditor('editor').destroy();
    }
    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }
    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        }
    }

    function getLocalData () {
        alert(UE.getEditor('editor').execCommand( "getlocaldata" ));
    }

    function clearLocalData () {
        UE.getEditor('editor').execCommand( "clearlocaldata" );
        alert("å·²æ¸ç©ºèç¨¿ç®±")
    }
</script>
</body>
</html>