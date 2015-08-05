<html>
<head>
<%@ page session="true"%>
<%@ page contentType="text/html;charset=GB2312"%>

<meta http-equiv="refresh" content="1">
<script language="javascript">
	function scrollchange()
	{
		parent.mainframe.window.scroll(0,60000);
		parent.mainframe.document.bgcolor="#ffffff";
		return true;
	}
</script>
<title>outputmessage</title>
<link rel="stylesheet" href="mystyle.css">
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="chat.*"%>

</head>
<jsp:useBean id="reg" scope="page" class="chat.chatbean"/>

<body onload="scrollchange()" bgcolor="#ccffcc" topmargin="0" leftmargin="0">
<form method ="post" name="form1" action="hidden.jsp">
<%
	synchronized (application)
	{	
		String name=(String)session.getAttribute("userid");
			
		if(name==null||name.equals(""))
		{
			session.setAttribute("s_message","Please login first");
			%>
			<script language="javascript">
			parent.location="into.jsp"
			</script>
		<%}
		else
		{
			Properties blist=(Properties)application.getAttribute("a_blacklist");
			if(blist!=null&&blist.getProperty(name)!=null)
				name=(String)blist.getProperty(name);
		}
		if (name.equals("unwelcomed"))
		{
			session.setAttribute("s_message","You're unwelcome ");
			%>
			<script language="javascript">
			parent.location="punish.jsp"
			</script>
		<%}
		Integer iever=(Integer)session.getAttribute("s_iever");
		if(iever==null)
		{
			String agent=request.getHeader("User-Agent");
			if(agent.indexOf("MSID 5.5")>0)
			{	
				iever=new Integer(6);
			}
			else iever=new Integer(4);
			session.setAttribute("s_iever",iever);
								
		}
		Integer messageindex=(Integer)session.getAttribute("s_messageindex");
		if((messageindex==null)||(messageindex.intValue()>=200))
		{
			messageindex=new Integer(0);
		}
		Vector displaymessage=null;
		displaymessage=(Vector)application.getAttribute("a_message");
		if(displaymessage!=null)
		{
			if(displaymessage.size()<messageindex.intValue())
			{
				messageindex=new Integer(displaymessage.size());
			}
			if(messageindex.intValue()!=displaymessage.size())
			{
				Object obj;
				String msg;
				int i=messageindex.intValue();
				for(;i<displaymessage.size();i++)
				{	
					obj=displaymessage.elementAt(i);
					if (obj instanceof chatmessage)
					{
						msg=((chatmessage)obj).tohtml(name);
					}
					else
					{
						msg="<fontcolor=grey>Systeminfo:"+obj.toString()+"</font><br>";
					}
					//System.out.println("xlq: here is the msg"+msg);
					%>
					<Script language="javascript">
						parent.mainframe.document.write("<%=msg%>");
					</script>
				<%} // for
				Integer count=new Integer(i);
				if(iever.intValue()>5)
					count=new Integer(0);
				session.setAttribute("s_messageindex",count);
			}//if
		}//if
	
	}//sync
%>

</form>
</body>
</html>
