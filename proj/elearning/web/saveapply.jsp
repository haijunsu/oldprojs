<%@page contentType="text/html;chatset=gb2312"%>
<html>
<head>
<%@page language="java" import="com.htyz.*"%>
<jsp:useBean id="sqlbean" scope="request" class="com.htyz.beanGetdata"/>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<%String reason=new String(request.getParameter("reason").getBytes("ISO8859_1"));
sqlbean.execute("insert into tuser_apply(user_id,class_id,apply_reason)values('"+session.getAttribute("userid")+"','"+request.getParameter("select2")+"','"+reason+"')");
out.print("添加成功！请点击<a href='apply.jsp'>这里</a>再申请！");
%>
</body>
</html>
