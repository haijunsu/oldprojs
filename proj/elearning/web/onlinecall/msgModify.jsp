<%@page contentType="text/html;charset=GB2312"%>
<HTML>
<HEAD>
<TITLE>短信编辑页面</TITLE>
<LINK rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
</HEAD>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<SCRIPT language="javascript">
<!--


function msgSubmit(s_action)
{
if (document.msgForm.receiptid.value.length==0){
alert("请输入要发送的用户的id号，否则不能发送成功！！");
document.msgForm.receiptid.focus();
return;
}
	
if(document.msgForm.msgtitle.value.length==0){
alert("标题不能为空！！");
document.msgForm.msgtitle.focus();
return;
}
	document.msgForm.action.value = s_action;
	document.msgForm.submit();

}
-->
</SCRIPT>
<BODY>
<P>
<DIV align="right">
<A href='Messager?action=new<% %>'>写短信</A> 
 | <A href='Messager?action=list&group=inbox<% %>'>收件箱</A> 
 | <A href='Messager?action=list&group=outbox<% %>'>发件箱</A>
 | <A href='Messager?action=list&group=draftbox<% %>'>草稿箱</A> 
</DIV>
</P>
<jsp:useBean id="beanGetdata" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="beanElearnTools" scope="request" class="com.htyz.beanElearnTools"/>
<%
String s_action, s_msg, s_Gnum, s_Gright, s_Mright, s_Checked, s_Group;
String s_title, s_content, s_receipt, s_msgid;
String s_border = "0";
s_action = (String)request.getAttribute("action");
s_msg = (String)request.getAttribute("message");
s_Group =  (String)request.getAttribute("group");
s_msgid=beanGetdata.getFieldValue("oc_id", 0);
if(s_action.equals("reply")||s_action.equals("forward"))
	s_msgid = "";

%>
<FORM id="msgForm" name="msgForm" method="Get" action="Messager">
<INPUT type="hidden" id="action" name="action" value="<%=s_action%>">
<INPUT type="hidden" id="group" name="group" value="<%=s_Group%>">
<INPUT type="hidden" id="msgid" name="msgid" value="<%=s_msgid%>">
<INPUT type="hidden" id="select0" name="select0" value="<%=s_msgid%>">
<INPUT type="hidden" id="countInPage" name="countInPage" value="1">
<%
if(s_action.equals("read"))
{
	s_border = "1";
}
%>

  <TABLE border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="100%" bgcolor="#FFFFFF">
    <TR>
		
      <TD height="20"> 收件人 </TD>
		
      <TD height="20"> 
        <%
		if(s_action.equals("read"))
		{
		%>
        <input class='input1' type='text' name='receiptid' value='<%=beanGetdata.getFieldValue("oc_obj", 0)%>' readonly='true'> 
        <%
		}
		else
		{
			s_receipt = beanGetdata.getFieldValue("oc_name", 0);
			if(s_action.equals("reply"))
				s_receipt = beanGetdata.getFieldValue("oc_name", 0);
			if(s_action.equals("forward"))
				s_receipt = "";
		%>
        <INPUT class="input1" type='text' id='receiptid' name='receiptid' value='<%=s_receipt%>' maxlength='20' size='20'>
		<%
		}
		%>
		</TD>
	</TR>
	<TR>
		
      <TD height="20"> 邮件标题 </TD>
		
      <TD height="20"> 
        <%
		if(s_action.equals("read"))
		{
		%>
       <input class='input1'type='text' name='msgtitle' value='<%=beanElearnTools.stringToHtml(beanGetdata.getFieldValue("oc_title", 0))%>' readonly='true'> 
        <%
		}
		else
		{
			s_title = beanGetdata.getFieldValue("oc_title", 0);
			if(s_action.equals("reply"))
			{
				s_title = "回复：" + beanGetdata.getFieldValue("oc_title", 0);
			}
			if(s_action.equals("forward"))
			{
				s_title = "转发：" +  beanGetdata.getFieldValue("oc_title", 0);
			}
		%>
        <INPUT class="input1" type='text' id='msgtitle' name='msgtitle' value='<%=s_title%>' maxlength='100' size='80'>
		<%
		}
		%>
		</TD>
	</TR>
	<TR>
      <TD colspan="2" height="20"> 
        <%
		if(s_action.equals("read"))
		{
		%>
       <textarea class="testarea1" name='msgcontent' rows='10' cols='80' readonly='true'> <%=beanElearnTools.stringToHtml(beanGetdata.getFieldValue("oc_content", 0))%> </textarea>
        <%
		}
		else
		{
			s_content = beanGetdata.getFieldValue("oc_content", 0);
			if(s_action.equals("reply")||s_action.equals("forward"))
			{
				s_content = "\n\n原始内容\n-----------------------\n" + beanGetdata.getFieldValue("oc_content", 0);
			}
		%>
        <textarea class="testarea1" id='msgcontent' name='msgcontent' rows='10' cols='80'><%=s_content%></textarea>
        <%
		}
		%>
      </TD>
	</TR>
	<TR align="center">
		
      <TD colspan="2" height="20"> 
        <%
			if(s_action.equals("read"))
			{
				if(s_Group.equals("inbox"))
				{
				%>
        		<A href=javascript:msgSubmit("reply")>回复</A> |
        		<%
				}
				%>
        		<A href=javascript:msgSubmit("forward")>转发</A> 
        	<%
			}
			else
			{
			%>
			<A href=javascript:msgSubmit("send")>发送</A> &nbsp;&nbsp;|&nbsp;&nbsp;<A href=javascript:msgSubmit("save")>保存到草稿箱</A> 
			<%
			}
			if(!beanGetdata.getFieldValue("oc_id", 0).equals(""))
			{
			%>
			| <A href=javascript:msgSubmit("delete")>删除</A> 
			<%
			}
			%>
      </TD>
	</TR>
</TABLE>
</FORM>
</table>
</FORM>
</BODY>
</HTML>
	