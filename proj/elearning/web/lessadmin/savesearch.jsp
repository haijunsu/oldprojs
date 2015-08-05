<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>Untitled Document</title>
</head>
<jsp:useBean id="sqlbean" scope="request" class="com.htyz.beanGetdata"/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<body bgcolor="#FFFFFF" text="#000000">
<%
sqlbean.execute("update t_user_apply set approval_id='"+session.getAttribute("userid")+"',approval_time=getdate(),apply_status='1' where apply_id='"+request.getParameter("checkbox")+"'");
sqlbean.execute("insert into t_user_class(user_id,class_id,start_time,status)values('"+request.getParameter("name")+"','"+request.getParameter("classcode")+"',getdate(),'1')");
out.print("批准成功！！");
%>
</body>
</html>
