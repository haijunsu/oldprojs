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
sqlbean.execute("update tuser_apply set pass_id='"+session.getAttribute("userid")+"',pass_time=getdate(),status='1' where user_id='"+request.getParameter("name")+"' and class_id='"+request.getParameter("classcode")+"'");
//System.out.print("update tuser_apply set pass_id='"+session.getAttribute("userid")+"',pass_time=getdate(),status='1' where user_id='"+request.getParameter("name")+"' and class_id='"+request.getParameter("classcode")+"'");
sqlbean.execute("insert into tuser_class(user_id,class_id,status)values('"+request.getParameter("name")+"','"+request.getParameter("classcode")+"','1')");
out.print("批准成功！请点击<a href='apply.jsp'>这里</a>再申请！");
%>
</body>
</html>
