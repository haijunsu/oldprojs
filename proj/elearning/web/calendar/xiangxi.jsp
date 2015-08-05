<%@ page contentType="text/html;charset=GB2312"%>
<%@page language="java" import="com.htyz.*"%>
<html>
<jsp:useBean id="sqlbean" scope="request" class="com.htyz.beanGetdata"/>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<%sqlbean.execute("update t_user_daily set flag='1' where user_id='"+request.getParameter("userid")+"' and wake_date='"+request.getParameter("wakedate")+"'");
 response.sendRedirect("right.jsp?userid='"+request.getParameter("userid")+"'&wakedate='"+request.getParameter("wakedate")+"'");
%>
<body bgcolor="#FFFFFF" text="#000000">

</body>
</html>
