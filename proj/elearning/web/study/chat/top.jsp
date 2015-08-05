<html>
<head>
<title>聊天室</title>
<meta http-equiv="Content-Type" content="text/html;charset=gb2313">
<%@ page contentType="text/html; charset=gb2312"%>
<%@ page session="true"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="chat.*"%>

<%
synchronized(application)
{
Vector vecusername=new Vector();
String name=(String)session.getAttribute("userid");
vecusername=(Vector)application.getAttribute("a_users");
if(vecusername==null)
{
  vecusername=new Vector();
  vecusername.addElement(name);
}
int size=vecusername.size();
String tmpusername="";
boolean flg=false;
if(name!=null)
{
	if(size==0)
	{
		vecusername.addElement(name);
	}
	else
	{
		for(int i=0;i<size;i++)
		{
			tmpusername=(String)vecusername.elementAt(i);
			if(tmpusername.equals(name))
			{
			    flg=true;
				break;
			}
		}
		if(!flg)
			vecusername.addElement(name);

	}
	//vecusername.addElement(name);
	application.setAttribute("a_users",vecusername);
	 
}
}

%>
<script language="javascript">
window.moveTo(0,0);
</script>
</head>
<frameset frameborder="0" broder="0" framespacing="0" rows="*,90" cols="*" border="0"> 
  <frameset frameborder="0" border="0" framespacing="0" cols="100,*"> 
    <frame name="userlistframe" src="userlist.jsp">
    <frame name="mainframe" src="main.jsp">
  </frameset>
  <frame name="bottomFrame" scrolling="NO" noresize src="input.jsp">
  <frameset frameborder="0" border="0" framespaceing="0" rows="0,*" cols="*"> 
    <frame name="hiddenframe" src="hidden.jsp" scrolling="no" >
  </frameset>
  <noframes> 请使用可用框架浏览器 
  </noframes> </frameset>
</html>
