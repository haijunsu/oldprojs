<html>
<%@ page session="true"%>
<%@ page import="java.util.*" %>
<%@ page import="chat.*" %>
<%@ page import="chat.dbbean" %>
<%@ page contentType="text/html; charset=GB2312" %>
<jsp:useBean id="beanTools" scope="session" class="com.htyz.beanElearnTools" />

<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta name="GENERATOR" content="Microsoft Frontpage 4.0">
<meta name="ProgId" content="Frontpage.Editor.Document">


<title>inputWindow</title>
<style>
<!--
-->
</style>


<script language="javascript">
function deffocus()
{	
	document.inputform.t_usermessage.focus();
	parent.mainframe.window.scroll(0,60000);
		parent.mainframe.document.bgcolor="#ffffff";
		
}
function logoutconfirm()
{
	if(confirm("确定要退出聊天室 ?"))
	{
		//window.open("quit.jsp","screen","width=800,height=500",true);
		top.close();
	}
	return false;
}
function talk()
{	
	if(document.inputform.t_usermessage.value=="")
	{
	 confirm("您什么也没说呀?");
	 return false;
	}
   return true
   document.loaction="top.jsp";
   document.inputform.submit();
}
function reloc()
{
	window.moveTo(50,350);
}
-->
</script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/3.css" type="text/css">
</head>
<body onload="deffocus()"  background="di.jpg">
<jsp:useBean id="reg" scope="page" class="chat.chatbean"/>
<%
	

	reg.setcontext(request,session);
	String peer=reg.getrequestparam("t_peer");
	String sec=reg.getrequestparam("secret");
	String username=reg.getsessionvalue("userid");
	
	String check=" ";
	if(sec.equals("yes"))
		check="checked";
	else
		check="no";
	reg.reset("s_peer");
	reg.reset("s_fontcolor");
	String color=reg.getrequestparam("t_fontcolor");
	reg.reset("s_fontstyle");
	String style=reg.getrequestparam("t_fontstyle");
	reg.reset("s_face");
	String face= reg.getrequestparam("t_face");
	reg.reset("s_secret");
	String defaultstring=" ";
	//String msg= reg.getrequestparam("t_usermessage");
	String msg=request.getParameter("t_usermessage");
	if(msg==null)
       msg="";
	
	Vector message=null;
	synchronized(application)
	{
	//System.out.println("display the msg"+msg.toString());
		message=(Vector)application.getAttribute("a_message");
		if(message==null)
			message=new Vector(30,10);
		if(message.size()>200)
			message.removeAllElements();
		if(!msg.equals(defaultstring))
		{
			chatmessage themessage=new chatmessage(username,msg,peer);
			themessage.setsecret(sec);
			themessage.setfontcolor(color);
			themessage.setface(face);
			themessage.setfontstyle(style);
			message.addElement(themessage);

		}
		String name=(String)session.getAttribute("userid");
		
		application.setAttribute("a_message",message);
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
				//String msg;
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
					
					 msg=beanTools.strReplace(msg,"defaultstring"," 进入论坛");
					 
					
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
	
	}
	%>
<form name="inputform" method="post" action="input.jsp" >
		
  <table border="0" width="100%" cellpadding="0" cellspacing="0">
    <tr> 
      <td width="15" height="24"></td>
      <td width="638" valign="top">对象: 
        <select size="1" name="t_peer">
          <option value="all">所有人</option>
          <%
				synchronized(application)
				{
					Vector listuser=null;
					listuser=(Vector)application.getAttribute("a_users");
					
					if(listuser!=null)
					{
						String othername;
						String sld=" ";
						for(int i=0;i<listuser.size();i++)
						{
							//othername=((user)listuser.get(i)).getname();
							othername=(String)listuser.elementAt(i);
							//othername=(listuser.get(i);
							if(peer.equals(othername))		
							sld="selected";
							%>
          <option value="<%=othername%>" <%=sld%>><%=othername%></option>
          <%}
					}
				}%>
        </select>
        <input type="checkbox" name="secret" value="<%=sec%>" <%=check%>>
        悄悄话 字体 
        <select name="t_fontcolor" size="1">
          <option style="color:#000000" value="defaultstring">字体颜色 
          <option style="color:#000000" value="#000000"<%=color.equals("#000000")?" selected":" "%>>黑色 
          <option style="color:#008800" value="#008800"<%=color.equals("#008800")?" selected":" "%>>橄榄 
          <option style="color:#008888" value="#008888"<%=color.equals("#008888")?" selected":" "%>>灰兰 
        </select>
        <select name="t_fontstyle" style="fonte-size: 9pt" size="1">
          <option value="defaultstring">字体样式</option>
          <option value="span" <%=style.equals("span")?"selected":" "%> >规则 </option>
          <option value="I" <%=style.equals("I")?"selected":" "%> >斜体 </option>
          <option value="B" <%=style.equals("B")?"selected":" "%> >粗体 </option>
          <option value="U" <%=style.equals("U")?"selected":" "%> >下划线 </option>
          <option value="TT" <%=style.equals("TT")?"selected":" "%> >等宽体 </option>
        </select>
		 <input class="input2" type="submit" value="刷新" name="ok" onclick="">
      </td>
      <td width="348"><br><br></td>
	 

    </tr>
    <tr> 
      <td height="29" colspan="2" valign="top"> <%=username%>: 
        <input class="input1" type="text" name="t_usermessage" size="60">
        <input class="input2" type="submit" value="发送" name="b_ok" onclick="return talk();">
        <input class="input2" type="button" value="退出" name="b_logout" onClick="return logoutconfirm();">
        &nbsp; </td>
      <td></td>
    </tr>
  </table>
</form>
</body>
</html>
				