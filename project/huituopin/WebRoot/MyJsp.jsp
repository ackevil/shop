<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
     <form action="<%=basePath %>/user/login" method="post" name="loginForm">
        <div class="line">
          User name:<input type="text" name="username"/>
        </div>
        <div class="line">
          Password: <input type="password" name="password" />
        </div>
        <div class="line">
          <input class="button black submitbtn" type="submit" value="Submit" />
          <input class="button black cancelbtn" type="button" value="Cancel" />
        </div>
      </form>
  </body>
</html>
