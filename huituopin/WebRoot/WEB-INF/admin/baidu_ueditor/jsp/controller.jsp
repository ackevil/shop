<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baidu.ueditor.*"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	String rootPath = application.getRealPath("/");
	//rootPath+="WEB-INF/admin/upload";
	out.write( new ActionEnter( request, rootPath ).exec() );
%>
