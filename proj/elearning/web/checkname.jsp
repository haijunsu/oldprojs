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
	out.println("���û����ѱ���������!<br>������ѡ�������û���!");
}else{
	check.executeSelect("select *  from t_user where user_id='"+request.getParameter("name")+"'");
	if(check.getRowCount()>0) {
		out.println("���û����Ѿ������뵫Ŀǰ��δ����׼�����Ѵ��ڹر�״̬!<br>������û��������������,��ȴ�ϵͳ��׼��������,�������Ա��ϵ!");
	}else{
	out.println("���û���Ϊ�Ϸ��û���!<br>������ʹ��!");
	}
}
%>
</body>
</html>
