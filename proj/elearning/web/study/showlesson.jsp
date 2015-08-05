<%@ page contentType="text/html;charset=GB2312" %>
<%@page language="java" import="com.htyz.*"%>
<html>
<head>
<jsp:useBean id="sqlbean" scope="request" class="com.htyz.beanGetdata"/>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<%
sqlbean.executeSelect("select * from t_lesson_log where user_id='"+session.getAttribute("userid")+"' and class_id='"+request.getParameter("classid")+"' and lesson_id='"+request.getParameter("lessonid")+"'");
if (sqlbean.getRowCount()<=0){
sqlbean.execute("insert into t_lesson_log(user_id,class_id,lesson_id) values('"+session.getAttribute("userid")+"','"+request.getParameter("classid")+"','"+request.getParameter("lessonid")+"')");
}
response.sendRedirect(request.getParameter("url"));
%>

</body>
</html>
