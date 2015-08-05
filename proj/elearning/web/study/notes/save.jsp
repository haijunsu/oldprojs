<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<%@page import="com.htyz.*, elearn.bean_Menus"%>
<%@page language="java" import="com.htyz.*"%>
<%if (session.getAttribute("userid")==null||session.getAttribute("userid").equals("")){%>
<script>
alert("连接超时");
top.location="../../login.jsp";
</script>
<%}
else{%>
<html>
<head>
<jsp:useBean id="sqlbean" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body bgcolor="#FFFFFF" text="#000000">
<%
	String content=new String(request.getParameter("Message").getBytes("ISO8859_1"));
	content=ets.stringToHtml(content);
	sqlbean.executeSelect("select count(*) as yy from t_lesson_log where user_id='"+session.getAttribute("userid")+"' and class_id='"+session.getAttribute("classid")+"' and lesson_id='"+session.getAttribute("lessonid")+"'");
	if (sqlbean.getFieldValue("yy",0).equals("1")){
		sqlbean.execute("update t_lesson_log set notes='"+content+"' where user_id='"+session.getAttribute("userid")+"' and class_id='"+session.getAttribute("classid")+"' and lesson_id='"+session.getAttribute("lessonid")+"'");
		out.print("添加成功！点击<a href='note.jsp'>这里</a>返回");
	}
	else{
		sqlbean.execute("insert into t_lesson_log (user_id,class_id,lesson_id,notes) values ('"+session.getAttribute("userid")+"','"+session.getAttribute("classid")+"','"+session.getAttribute("lessonid")+"','"+content+"')");
		out.print("添加成功！点击<a href='note.jsp'>这里</a>返回");
	}
%>
</body>
</html>
<%}%>
