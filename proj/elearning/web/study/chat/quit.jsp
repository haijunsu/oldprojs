<html>
<head>
<%@ page session="true"%>
<%@ page contentType="text/html;charset=GB2312"%>
<title>退出 </title>
</head>
<body bgcolor="#CCFFCC">
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="chat.*"%>
<%@ page errorPage="../err.jsp"%>
<br>
<%
synchronized(application)
{
Vector users=null;
users=(Vector)application.getAttribute("a_users");
String name=(String)session.getAttribute("s_name");
if(name!=null)
{
	for(int i=0;i<users.size();i++)
	{	
		user user=(user)users.elementAt(i);
		if(user.getname().equals(name))
		{
			users.remove(user);
			break;
		}
	}
	Vector message=null;
	message=(Vector)application.getAttribute("a_message");
	if(message==null)
			message=new Vector(30,10);
		chatmessage themessage=new chatmessage(name,"886");
		message.addElement(themessage);
		application.setAttribute("a_message",message);
		session.setAttribute("s_message","");
		session.setAttribute("s_name",null);%>
		成功退出
		<BR>
		<%
	}
	
}


%>
<a href="into.jsp">重新登录&gt;&gt;</a>

</BODY>
</html>