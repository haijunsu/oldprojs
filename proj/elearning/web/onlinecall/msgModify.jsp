<%@page contentType="text/html;charset=GB2312"%>
<HTML>
<HEAD>
<TITLE>���ű༭ҳ��</TITLE>
<LINK rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
</HEAD>
<%
//��ֹCache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<SCRIPT language="javascript">
<!--


function msgSubmit(s_action)
{
if (document.msgForm.receiptid.value.length==0){
alert("������Ҫ���͵��û���id�ţ������ܷ��ͳɹ�����");
document.msgForm.receiptid.focus();
return;
}
	
if(document.msgForm.msgtitle.value.length==0){
alert("���ⲻ��Ϊ�գ���");
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
<A href='Messager?action=new<% %>'>д����</A> 
 | <A href='Messager?action=list&group=inbox<% %>'>�ռ���</A> 
 | <A href='Messager?action=list&group=outbox<% %>'>������</A>
 | <A href='Messager?action=list&group=draftbox<% %>'>�ݸ���</A> 
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
		
      <TD height="20"> �ռ��� </TD>
		
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
		
      <TD height="20"> �ʼ����� </TD>
		
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
				s_title = "�ظ���" + beanGetdata.getFieldValue("oc_title", 0);
			}
			if(s_action.equals("forward"))
			{
				s_title = "ת����" +  beanGetdata.getFieldValue("oc_title", 0);
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
				s_content = "\n\nԭʼ����\n-----------------------\n" + beanGetdata.getFieldValue("oc_content", 0);
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
        		<A href=javascript:msgSubmit("reply")>�ظ�</A> |
        		<%
				}
				%>
        		<A href=javascript:msgSubmit("forward")>ת��</A> 
        	<%
			}
			else
			{
			%>
			<A href=javascript:msgSubmit("send")>����</A> &nbsp;&nbsp;|&nbsp;&nbsp;<A href=javascript:msgSubmit("save")>���浽�ݸ���</A> 
			<%
			}
			if(!beanGetdata.getFieldValue("oc_id", 0).equals(""))
			{
			%>
			| <A href=javascript:msgSubmit("delete")>ɾ��</A> 
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
	