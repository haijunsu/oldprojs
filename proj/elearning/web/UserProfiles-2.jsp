<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>�û�����</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/3.css" type="text/css">
</head>
<Script language="javascript">
<!--
function menuSubmit(s_action, s_change, s_menuid)
{
	document.menuForm.action.value = s_action;
	document.menuForm.changenum.value = s_change;
	if(s_action == "delete")
	{
		if(!confirm("ȷ����ɾ��IDΪ��" + s_menuid + "���Ĳ˵���"))
		{
			return;
		}
	}
	if(s_menuid == "group")
	{
		document.menuForm.group.value = document.menuForm.groupvalue.value;
	}

	document.menuForm.submit();
	return false;
}
-->
</Script>
<body>

<jsp:include page="/MsgNotify.jsp"/>
<jsp:useBean id="beanUserInfo" scope="request" class="elearn.bean_UserInfo"/>
<jsp:useBean id="beanQueryCodes" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="beanElearnTools" scope="request" class="com.htyz.beanElearnTools"/>
<%
String s_msg, s_action, s_Title, s_checked1, s_checked2, s_btnSubmit;
s_msg = (String)request.getAttribute("message");
s_action =  (String)request.getAttribute("action");
s_Title =  (String)request.getAttribute("title");
%>
<h2> <%=s_Title%> </h2>

<%
if(s_msg != null)
{
%>
<Font color="red"><%=s_msg%></Font>
<%}
if(s_action.equals("changepass")||s_action.equals("updatepass"))
{
%>
<FORM id="userForm" name="userForm" method="Get" action="elearn.bean_UserProfiles">
<input type="hidden" id="action" name="action" value="<%=s_action%>">
    <table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" class="xbz" width="100%" bgcolor="#FFFFFF">
    <tr>
		
      <td nowrap width="157"> �û������� :</td>
		
      <td width="586">
<input class="input2"  type='password' id='oldpass' name='oldpass' size='20' maxlength='20' value='<%=beanUserInfo.getPassword()%>'> </td>
	</tr>
	<tr>
		
      <td nowrap width="157"> �û�������: </td>
		
      <td width="586">
<input class="input2" type='password' id='pass' name='pass' size='20' maxlength='20' value='<%=beanUserInfo.getPassword()%>'> </td>
	</tr>
	<tr>
		
      <td nowrap width="157"> �ٴ�ȷ������ :</td>
		
      <td width="586">
<input class="input2" type='password' id='repass' name='repass' size='20' maxlength='20' value=''> </td>
	</tr>
	<tr>
		<td colspan='2'>
		<input class="input1" type='submit' id='btnsubmit' name='btnsublit' value='�ύ'> </td>
	</tr>
</table>
</FORM>
<%
}
else
{
%>
<FORM id="userForm" name="userForm" method="Get" action="elearn.bean_UserProfiles">
<input type="hidden" id="action" name="action" value="<%=s_action%>">
  <table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" class="xbz" width="100%" bgcolor="#FFFFFF">
	<tr>
		<td width="24%" nowrap> �û�ID/��¼�� </td>
		<%
		if(s_action.equals("add"))
		{
		%>
			<td width="70%"><input class="input2" type='text' id='userid' name='userid' size='20' maxlength='20' value='<%=beanUserInfo.getUserid()%>'> </td>
		<%
		}
		else
		{
		%>
			<td width="6%"><%=beanUserInfo.getUserid()%></td>
		<%}%>
	</tr>
	<tr>
		<td nowrap> ���� </td>
		<td><input class="input2" type='text' id='username' name='username' size='30' maxlength='30' value='<%=beanUserInfo.getUserName()%>'></td>
	</tr>
	<tr>
		<td nowrap> �������� </td>
		<td><input class="input2" type='text' id='birthday' name='birthday' size='10' maxlength='10' value='<%=beanUserInfo.getBirthday()%>'>��ʽ��YYYY-MM-DD</td>
	</tr>
	<%
	if(s_action.equals("apply")||s_action.equals("add"))
	{
	%>
	<tr>
		<td nowrap> �û����� </td>
		<td><input class="input2" type='password' id='pass' name='pass' size='20' maxlength='20' value='<%=beanUserInfo.getPassword()%>'> </td>
	</tr>
	<tr>
		<td nowrap> ȷ������ </td>
		<td><input class="input2" type='password' id='repass' name='repass' size='20' maxlength='20' value=''> </td>
	</tr>
	<%
	}
	else
	{
	%>
	<tr>
		<td colspan="2">
		<input class="input1" type='button' id='changepass' name='changepass' value='�޸�����' onClick="window.location.href='elearn.bean_UserProfiles?action=changepass'"> </td>
	</tr>
	<%
	}
	%>
	<tr>
		<td nowrap> �Ա� </td>
		<%
		s_checked1 = "checked";
		s_checked2 = "";
		if(beanUserInfo.getSex().equals("2"))
		{
			s_checked1 = "";
			s_checked2 = "checked";
		}
		%>
		<td><input class="input2" type='radio' id='sex' name='sex' <%=s_checked1%> value='1'>��  <input type='radio' id='sex' name='sex' <%=s_checked2%> value='2'>Ů
		</td>
	</tr>
	<tr>
		<td nowrap> �ʼ���ַ </td>
		<td><input class="input2" type='text' id='email' name='email' size='30' maxlength='50' value='<%=beanUserInfo.getEmail()%>'></td>
	</tr>
	<tr>
		<td nowrap> �绰 </td>
		<td><input class="input2" type='text' id='phone' name='phone' size='20' maxlength='20' value='<%=beanUserInfo.getPhone()%>'></td>
	</tr>
	<tr>
		<td nowrap> ��λ </td>
		<td><input class="input2" type='text' id='company' name='company' size='30' maxlength='50' value='<%=beanUserInfo.getCompany()%>'></td>
	</tr>
	<tr>
		<td nowrap> ���� </td>
		<td><input class="input2" type='text' id='area' name='area' size='20' maxlength='20' value='<%=beanUserInfo.getArea()%>'></td>
	</tr>
	<tr>
		<td nowrap> ǩ�� </td>
		<td><textarea class="testarea1" id='signature' name='signature' rows='3' cols='15'><%=beanUserInfo.getSignature()%></textarea></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<%
			s_btnSubmit = "����";
			if(s_action.equals("update"))
			{
				s_btnSubmit = "�޸�";
			}
			%>
			<input class="input1" type="submit" id='btnsubmit' name='btnsubmit' value="<%=s_btnSubmit%>">
			<input class="input1" type="reset" id='btnreset' name='btnreset' value="����">
		</td>
	</tr>
</table>
</FORM>
<%}%>
</body>
</html>
	