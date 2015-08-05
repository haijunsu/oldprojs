<%@page contentType="text/html;charset=GB2312"%>
<head>
<title></title>
</head>
<body>
<jsp:useBean id="check" scope="request" class="com.htyz.beanGetdata" />
<%
String Sql="select * from t_user where user_id='"+request.getParameter("name")+"' and user_status='1'";
check.executeSelect(Sql);
if(check.getRowCount()>0) {
	out.println("此用户名已被他人申请!<br>您可以选择其它用户名!");
}else{
	check.executeSelect("select *  from t_user where user_id='"+request.getParameter("name")+"'");
	if(check.getRowCount()>0) {
		out.println("此用户名已经被申请但目前仍未被批准或是已处于关闭状态!<br>如果此用户名是您所申请的,请等待系统批准您的申请,或与管理员联系!");
	}else{
	out.println("此用户名为合法用户名!<br>您可以使用!");
	}
}
%>
</body>
</html>
