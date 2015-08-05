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
<title>显示用户</title>
<link rel="stylesheet" href="../../style.css">
</head>
<body  background="di.jpg" leftmargin="0" >
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="chat.*"%>
<div align=center>
<font color=blue>在线人员列表</font><br><br>
<font color=#669900><a href="userlist.jsp" onclick=performsubmit("all")>[所有人]</a></font><br>
<%
synchronized(application){
	Vector v_listuser=null;
	v_listuser=(Vector)application.getAttribute("a_users");
	//out.println("vec size========="+v_listuser.size());
	if(v_listuser!=null)
	{
		out.println("vec size1=========");
		String username;
		for(int i=0;i<v_listuser.size();i++)
		{
			username=((user)v_listuser.elementAt(i)).getname();
			//out.println("the username========="+username);
		%>	
		<a href="#" onclick=performsubmit("<%=username%>")>
		<font color=#ff6633><%=username%></font></a><br>
		<%}
	}else{%>欢迎来此<%}
}
%>
</div>
</body>



</html>