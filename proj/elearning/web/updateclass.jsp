<%@page contentType="text/html;charset=GB2312"%>
<%@ page language="java" import="com.htyz.*" %>
<%String s_UserId = (String)session.getAttribute("userid");%>
<%out.print(session.getAttribute("id"));%>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<jsp:useBean id="beanGetdata" scope="request" class="com.htyz.beanGetdata"/>
<body bgcolor="#FFFFFF" text="#000000">
<%beanGetdata.execute("delete from t_user_class where User_id='"+s_UserId+"' and Class_id="+session.getAttribute("id")+"");
out.print("取消成功!");
%>
</body>
</html>
