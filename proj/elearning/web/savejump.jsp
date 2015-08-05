<%@ page contentType="text/html;charset=GB2312"%>
<%@page language="java" import="com.htyz.*"%>
<html>
<head>
<jsp:useBean id="sqlbean" scope="request" class="com.htyz.beanGetdata"/>
<title>保存</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body bgcolor="#FFFFFF" text="#000000">
<%sqlbean.execute("insert into t_user_daily(user_id,user_day,title,notes)values('"+session.getAttribute("userid")+"','"+sqlbean.getDbDate()+"','"+request.getParameter("title")+"','"+request.getParameter("content")+"')");
out.print("添加成功！");
%>
</body>
</html>
