<%@ page contentType="text/html; charset=gb2312"%>
<html>
<head><title>登陆检查 </title>
<%@ page session="true"%>

<script language="javascript">
function goto(reurl){
window.location=reurl;
}
function chat()
{
	
	window.open("top.jsp","top");
	window.close();
}
</script>
</head>
<body>

<%@ page language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="chat.*"%>

<jsp:useBean id="reg" scope="page" class="chat.chatbean" />
<jsp:useBean id="x_data" scope="page" class="chat.dbbean" />
<%

boolean isguest=false;
String t_regname=(String)session.getAttribute("userid");
if (t_regname.equals("")||t_regname==null)
{%>
<script language="javascript">
window.alert("请先登陆");
</script>
<%
}
else
{
String regname=null;

String name="";
if(t_regname!=null&&!t_regname.equals(""))
{
	t_regname=new String(t_regname.trim().getBytes("ISO8859_1"));
	regname=t_regname;
}
System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwww"+regname);
String msg;
synchronized(application)
{
	Vector v_username=null;
	Vector v_users=null;
	v_users=(Vector)application.getAttribute("a_users");
	if(v_username==null)
	{
		v_users=new Vector(30,10);
		v_username=new Vector(30,10);
	}
	else
	{
		for(int i=1;i<v_users.size();i++)
		{
			v_username.addElement(((user)v_users.elementAt(i)).getname());
		}
	}
	if (regname!=null)
	{
		if(v_username.contains(regname))
		{
			String str=" 名字已被使用，请用其他名字";
			session.setAttribute("s_message",str);
		
		}
		else
		{
			v_username.addElement(regname);
			v_users.addElement(new chat.user(request,regname));
			session.setAttribute("s_name",regname);
		}
	}
		application.setAttribute("a_users",v_users);
%>
<script language="javascript">
chat();
</script>

<%}}%>


</body>
</html>
