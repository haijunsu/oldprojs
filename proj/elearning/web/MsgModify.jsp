<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>短信编辑页面</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
</head>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<Script language="javascript">
<!--
function msgSubmit(s_action)
{
	document.msgForm.action.value = s_action;
	document.msgForm.submit();
	return false;
}
-->
</Script>
<body>
<p>&nbsp;</p>

<%@ include file="/MsgNotify.jsp"%>
<jsp:useBean id="beanGetdata" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="beanElearnTools" scope="request" class="com.htyz.beanElearnTools"/>
<%
String s_action, s_msg, s_Gnum, s_Gright, s_Mright, s_Checked, s_Group;
String s_title, s_content, s_receipt, s_msgid;
String s_border = "0";
s_action = (String)request.getAttribute("action");
s_msg = (String)request.getAttribute("message");
s_Group =  (String)request.getAttribute("group");
s_msgid=beanGetdata.getFieldValue("msg_id", 0);
if(s_action.equals("reply")||s_action.equals("forward"))
	s_msgid = "";

%>
<FORM id="msgForm" name="msgForm" method="Get" action="elearn.bean_Messager">
<input type="hidden" id="action" name="action" value="<%=s_action%>">
<input type="hidden" id="group" name="group" value="<%=s_Group%>">
<input type="hidden" id="msgid" name="msgid" value="<%=s_msgid%>">
<%
if(s_action.equals("read"))
{
	s_border = "1";
}
%>

  <table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="100%" bgcolor="#FFFFFF">
    <tr>
		
      <td height="20"> 收件人 </td>
		
      <TD height="20"> 
        <%
		if(s_action.equals("read"))
		{
		%>
        <%=beanGetdata.getFieldValue("receipt_id", 0)%> 
        <%
		}
		else
		{
			s_receipt = beanGetdata.getFieldValue("receipt_id", 0);
			if(s_action.equals("reply"))
				s_receipt = beanGetdata.getFieldValue("sender_id", 0);
			if(s_action.equals("forward"))
				s_receipt = "";
		%>
        <input class="input1" type='text' id='receiptid' name='receiptid' value='<%=s_receipt%>' maxlength='20' size='20'>
		<%
		}
		%>
		</TD>
	</tr>
	<tr>
		
      <td height="20"> 邮件标题 </td>
		
      <td height="20"> 
        <%
		if(s_action.equals("read"))
		{
		%>
        <%=beanGetdata.getFieldValue("msg_title", 0)%> 
        <%
		}
		else
		{
			s_title = beanGetdata.getFieldValue("msg_title", 0);
			if(s_action.equals("reply"))
			{
				s_title = "回复：" + beanGetdata.getFieldValue("msg_title", 0);
			}
			if(s_action.equals("forward"))
			{
				s_title = "转发：" +  beanGetdata.getFieldValue("msg_title", 0);
			}
		%>
        <input class="input1" type='text' id='msgtitle' name='msgtitle' value='<%=s_title%>' maxlength='100' size='80'>
		<%
		}
		%>
		</td>
	</tr>
	<tr>
		
      <td height="20"> 邮件内容 </td>
		
      <td height="20"> 
        <%
		if(s_action.equals("read"))
		{
		%>
        <pre>
		<%=beanGetdata.getFieldValue("msg_content", 0)%>
		</pre>
		<%
		}
		else
		{
			s_content = beanGetdata.getFieldValue("msg_title", 0);
			if(s_action.equals("reply")||s_action.equals("forward"))
			{
				s_content = "\n\n原始内容\n-----------------------\n" + beanGetdata.getFieldValue("msg_title", 0);
			}
		%>
		<textarea class="testarea1" id='msgcontent' name='msgcontent' rows='10' cols='80'><%=s_content%></textarea>
		<%
		}
		%>
		</td>
	</tr>
	<tr align="center">
		
      <td colspan="2" height="20"> 
        <%
			if(s_action.equals("read"))
			{
				if(s_Group.equals("inbox"))
				{
				%>
        <A href=javascript:msgSubmit("reply")>回复</A> 
        <%
				}
				%>
        &nbsp;&nbsp;|&nbsp;&nbsp;<A href=javascript:msgSubmit("forward")>转发</A> 
        <%
			}
			else
			{
			%>
        <A href=javascript:msgSubmit("send")>发送</A> &nbsp;&nbsp;|&nbsp;&nbsp;<A href=javascript:msgSubmit("save")>保存到草稿箱</A> 
        <%
			}
			if(!beanGetdata.getFieldValue("msg_id", 0).equals(""))
			{
			%>
        &nbsp;&nbsp;|&nbsp;&nbsp;<A href=javascript:msgSubmit("delete")>删除</A> 
        <%
			}
			%>
      </td>
	</tr>
</table>
</FORM>
</table>
</FORM>
</body>
</html>
	