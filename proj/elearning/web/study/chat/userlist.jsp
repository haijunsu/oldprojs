<html>
<head>
<%@ page contentType="text/html; charset=gb2312"%>
<%@ page session="true"%>
<script language="javascript">
function performsubmit(user)
{
	parent.bottomFrame.inputform.t_peer.value=user;
}
</script>
<title>��ʾ�û�</title>
<link rel="stylesheet" href="../../style.css">

</head>
<body  background="di.jpg" leftmargin="0" >
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="chat.*"%>
<div align=center>
<font color=red><a href="userlist.jsp">������Աˢ��</a></font><br><br>
<font color=blue>������Ա�б�</font><br><br>

<font color=#669900><a href="userlist.jsp" onclick=performsubmit("all")>[������]</a></font><br>
<%
synchronized(application){
	Vector v_listuser=null;
	v_listuser=(Vector)application.getAttribute("a_users");
	if(v_listuser!=null)
	{
		String username;
		for(int i=0;i<v_listuser.size();i++)
		{
			//username=((user)v_listuser.elementAt(i)).getname();
			username=(String)v_listuser.elementAt(i);
		%>	
		<a href="#" onclick=performsubmit("<%=username%>")>
		<font color=#ff6633><%=username%></font></a><br>
		<%}
	}else{%>��ӭ����<%}
}
%>
</div>
</body>



</html>