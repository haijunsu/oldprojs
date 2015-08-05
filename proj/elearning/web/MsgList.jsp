<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>短信页面</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/3.css" type="text/css">
</head>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<body>

<%@ include file="/MsgNotify.jsp"%>
<jsp:useBean id="beanGetdata" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="beanElearnTools" scope="request" class="com.htyz.beanElearnTools"/>
<%
String s_action, s_msg, s_Gnum, s_Gright, s_Mright, s_Checked, s_Group;
String s_senderid;
String s_senderTitle = "收件人";
String s_Msgaction = "read";
s_action = (String)request.getAttribute("action");
s_msg = (String)request.getAttribute("message");
s_Group =  (String)request.getAttribute("group");
s_msg = s_msg.trim();
if(s_Group.equals("inbox"))
	s_senderTitle = "发件人";
if(s_Group.equals("draftbox"))
	s_Msgaction = "modify";

%>
<H2><%=s_msg%></H2>
<p>共有 <%=beanGetdata.getRowCount()%> 条短信<br>

<table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="100%">
  <tr align="center">
		
    <td colspan="6" height="20"> <A href='elearn.bean_Messager?action=new'>写短信</A>&nbsp;&nbsp;|&nbsp;&nbsp; 
      <A href='elearn.bean_Messager?action=list&group=inbox'>收件箱</A>&nbsp;&nbsp;|&nbsp;&nbsp; 
      <A href='elearn.bean_Messager?action=list&group=outbox'>发件箱</A>&nbsp;&nbsp;|&nbsp;&nbsp; 
      <A href='elearn.bean_Messager?action=list&group=draftbox'>草稿箱</A> </td>
	</tr>
	<tr align="center">
		
    <td height="20"> 序号 </td>
		
    <td height="20"> 状态 </td>
		
    <td height="20"> <%=s_senderTitle%> </td>
		
    <td height="20"> 短信标题 </td>
		
    <td height="20"> 日期 </td>
		
    <td height="20"> 操作 </td>
	</tr>
	<%
	if(beanGetdata.getRowCount()>0)
	{
		for (int i=0; i<beanGetdata.getRowCount(); i++)
		{
		%>
		<tr>
			
    <TD height="20" align="center"> <%=i+1%> </TD>
			
    <TD height="20"> 
      <%
			if(beanGetdata.getFieldValue("msg_status", i).equals("1"))
			{
			%>
      <FONT color=red>新</FONT> 
      <%
			}
			else
			{
			%>
      &nbsp; 
      <%
			}
			%>
    </TD>
			
    <TD height="20"> 
      <%
			s_senderid = beanGetdata.getFieldValue("receipt_id", i);
			if(s_Group.equals("inbox"))
				s_senderid = beanGetdata.getFieldValue("sender_id", i);
			if(s_senderid.equals(""))
				s_senderid = "无";
			%>
      <%=s_senderid%> </TD>
			
    <td height="20"> <A href=elearn.bean_Messager?action=<%=s_Msgaction%>&group=<%=s_Group%>&msgid=<%=beanGetdata.getFieldValue("msg_id", i)%>> 
      <%
			if(beanGetdata.getFieldValue("msg_title", i).equals(""))
			{
			%>
      无 
      <%
			}
			else
			{
			%>
      <%=beanGetdata.getFieldValue("msg_title", i)%> 
      <%}%>
      </A> </td>
			
    <td height="20"> <%=beanElearnTools.FormatDate(beanGetdata.getFieldValue("msg_date", i))%> 
    </td>
			
    <td height="20"> <A href='elearn.bean_Messager?action=delete&group=<%=s_Group%>&msgid=<%=beanGetdata.getFieldValue("msg_id", i)%>'>删除</A> 
    </td>
		</tr>
		<%
		}
	}
	%>
	<tr align="center">
		
    <td colspan="6" height="20"> <A href='elearn.bean_Messager?action=new'>写短信</A>&nbsp;&nbsp;|&nbsp;&nbsp; 
      <A href='elearn.bean_Messager?action=list&group=inbox'>收件箱</A>&nbsp;&nbsp;|&nbsp;&nbsp; 
      <A href='elearn.bean_Messager?action=list&group=outbox'>发件箱</A>&nbsp;&nbsp;|&nbsp;&nbsp; 
      <A href='elearn.bean_Messager?action=list&group=draftbox'>草稿箱</A> </td>
	</tr>
	
</table>
</FORM>
</table>
</FORM>
</body>
</html>
	