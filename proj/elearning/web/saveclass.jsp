<%@page contentType="text/html;charset=GB2312"%>
<%@ page  language="java" import="com.htyz.*" %>
<%String s_UserId = (String)session.getAttribute("userid");%>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<jsp:useBean id="beanGetdata" scope="request" class="com.htyz.beanGetdata"/>
<body bgcolor="#FFFFFF" text="#000000">
<%beanGetdata.execute("insert into t_user_class(User_id,Class_id,Status)values('"+s_UserId+"',"+session.getAttribute("id")+",'1')");
out.print("添加成功！");
%>
</body>
