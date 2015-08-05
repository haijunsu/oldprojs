<%@page contentType="text/html;charset=GB2312"%>
<%@page language="java" import="com.htyz.*"%>
<html>
<head>
<title>Untitled Document</title>
<link rel="stylesheet" href="xbz.css" type="text/css">
<jsp:useBean id="sqlbean" scope="request" class="com.htyz.beanGetdata"/>
</head>
<body bgcolor="#FFFFFF" text="#000000">
<%
sqlbean.execute("insert into tuser_daliy(user_id,user_day,notes)values('"+request.getParameter("name")+"',getyear(),'"+request.getParameter("content")+"')");
out.print("Ìí¼Ó³É¹¦!");
%> 

</body>
</html>
