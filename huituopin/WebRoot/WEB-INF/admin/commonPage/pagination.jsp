<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  
<%-- <script type="text/javascript" charset="utf-8" src="<%=path%>/admin/skin/js/jquery-1.11.2.min.js"></script> --%>

<link href="<%=path%>/admin/skin/css/pagination.css" rel="stylesheet" type="text/css" />
<script>
document.onkeydown = function(e){ 
    var ev = document.all ? window.event : e;
    if(ev.keyCode==13) {
        var currentPage = $("#goPageNum").val();
        $("#currentPageNum").val(getCurrentPage(parseInt(currentPage)));
        $("#pagination_form").submit();
     }
};

function lastPage() {
    var currentPage = $("#currentPageNum").val();
    $("#currentPageNum").val(getCurrentPage(parseInt(currentPage) - 1));
    $("#pagination_form").submit();
}

function nextPage() {
    var currentPage = $("#currentPageNum").val();
    $("#currentPageNum").val(getCurrentPage(parseInt(currentPage) + 1));
    $("#pagination_form").submit();
}

function go() {
    var currentPage = $("#goPageNum").val();
    $("#currentPageNum").val(getCurrentPage(parseInt(currentPage)));
    $("#pagination_form").submit();
}

function getCurrentPage(currentPageNumber) {
    var currnetPage = 1;
    var pageSizes = parseInt($("#pageSizes").val());
    var maxRows = parseInt($("#maxRows").val());

    if (pageSizes > maxRows) {
        pageSizes = maxRows;
    }
    
    if (pageSizes < 1) {
        pageSizes = 1;
    }
    $("#pageSizes").val(pageSizes);
    var totalPageNumber = 1;
    
    if (maxRows % pageSizes == 0) {
        totalPageNumber = maxRows / pageSizes;
    }
    else {
        totalPageNumber = maxRows / pageSizes + 1;
    }

    if (currentPageNumber < 1) {
        currnetPage = 1;
    } else if (currentPageNumber > totalPageNumber) {
        currnetPage = totalPageNumber;
    } else {
        currnetPage = currentPageNumber;
    }
    return currnetPage;
};
</script>
<div class="pagelist">
    <form id="pagination_form" action="${url}" method="POST">
    <div class="l-btns">
        <span>显示</span>
            <input name="pageSizes" type="text" value="${pages.pageSizes}" id="pageSizes" class="pagenum" />
        <span>条/页</span>
    </div>
    <div id="PageContent" class="l-btns">
        <span>共记录</span>
        <input name="maxRows" type="text" value="${pages.maxRows}" id="maxRows" class="pagenum" readonly="readonly" />
        <span>条</span>
    </div>
    <div id="ArticlePager" class="l-btns" style="width:150px;text-align:center;">
        <span><a href="javascript:lastPage()">上一页</a></span>
        <input name="page" type="text" value="${pages.page}" id="currentPageNum" class="pagenum" readonly="readonly" />
        <span><a href="javascript:nextPage()">下一页</a></span>
    </div>
    <div id="PageJump" class="l-btns">
        <span>跳转</span>
        <input type="text" value="${pages.page}" id="goPageNum" class="pagenum" />
        <span>页</span>
    </div>
    <input type="hidden" value="${myparam}" name="param"/>
    </form>
</div>
