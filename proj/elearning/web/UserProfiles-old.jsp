<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>用户属性</title>
<link rel="stylesheet" href="/elearning/xbz.css" type="text/css">
<link rel="stylesheet" href="/elearning/td" type="text/css">
</head>
<Script language="javascript">
<!--
function menuSubmit(s_action,s_change, s_menuid)
{
	document.menuForm.action.value = s_action;
	document.menuForm.changenum.value = s_change;
	if(s_action == "delete")
	{
		if(!confirm("确认在删除ID为“" + s_menuid + "”的菜单吗？"))
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
<jsp:useBean id="beanGetdata" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="beanQueryCodes" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="beanElearnTools" scope="request" class="com.htyz.beanElearnTools"/>
<span class="xbz">
<%
String s_msg, s_action, s_Title, s_checked1, s_checked2, s_btnSubmit;
s_msg = (String)request.getAttribute("message");
s_action =  (String)request.getAttribute("action");
s_Title =  (String)request.getAttribute("title");
%>
</span>
 <span class="xbz"><%=s_Title%> </span>
<span class="xbz">
<%
if(s_msg != null)
{
%>
<Font color="red"><%=s_msg%></Font> 
<%}%>
</span>
<FORM id="userForm" name="userForm" method="Get" action="elearn.bean_UserProfiles">
<input type="hidden" id="action" name="action" value="<%=s_action%>">
  <table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" class="xbz" width="100%" bgcolor="#FFFFFF">
    <tr>
		
      <td nowrap height="20"> 用户ID/登录号 </td>
		<%
		if(s_action.equals("add"))
		{
		%>
			
      <td height="20">
<input type='text' id='userid' name='userid' size='20' maxlength='20' value='<%=beanGetdata.getFieldValue("user_id", 0)%>'> </td>
		<%
		}
		else
		{
		%>
			<td><%=beanGetdata.getFieldValue("user_id", 0)%></td>
		<%}%>
	</tr>
	<tr>
		
      <td nowrap height="20"> 姓名 </td>
		
      <td height="20">
<input type='text' id='username' name='username' size='30' maxlength='30' value='<%=beanGetdata.getFieldValue("user_name", 0)%>'></td>
	</tr>
	<tr>
		
      <td nowrap height="20"> 出生日期 </td>
		
      <td height="20">
<input type='text' id='birthday' name='birthday' size='10' maxlength='10' value='<%=beanGetdata.getFieldValue("birthday", 0)%>'>格式：YYYY-MM-DD</td>
	</tr>
	<tr>
		
      <td nowrap height="20"> 用户密码 </td>
		
      <td height="20">
<input type='password' id='pass' name='pass' size='20' maxlength='20' value='<%=beanGetdata.getFieldValue("user_pass", 0)%>'> </td>
	</tr>
	<tr>
		
      <td nowrap height="20"> 确认密码 </td>
		
      <td height="20">
<input type='password' id='repass' name='repass' size='20' maxlength='20' value='<%=beanGetdata.getFieldValue("user_pass", 0)%>'> </td>
	</tr>
	<tr>
		
      <td nowrap height="20"> 性别 </td>
		<%
		s_checked1 = "checked";
		s_checked2 = "";
		if(beanGetdata.getFieldValue("sex", 0).equals("2"))
		{
			s_checked1 = "";
			s_checked2 = "checked";
		}
		%>
		
      <td height="20">
<input type='radio' id='sex' name='sex' <%=s_checked1%> value='1'>男  <input type='radio' id='sex' name='sex' <%=s_checked2%> value='2'>女  
		</td>
	</tr>
	<tr>
		
      <td nowrap height="20"> 邮件地址 </td>
		
      <td height="20">
<input type='text' id='email' name='email' size='30' maxlength='50' value='<%=beanGetdata.getFieldValue("email", 0)%>'></td>
	</tr>
	<tr>
		
      <td nowrap height="20"> 电话 </td>
		
      <td height="20">
<input type='text' id='phone' name='phone' size='20' maxlength='20' value='<%=beanGetdata.getFieldValue("phone", 0)%>'></td>
	</tr>
	<tr>
		
      <td nowrap height="20"> 单位 </td>
		
      <td height="20">
<input type='text' id='company' name='company' size='30' maxlength='50' value='<%=beanGetdata.getFieldValue("company", 0)%>'></td>
	</tr>
	<tr>
		
      <td nowrap height="20"> 地区 </td>
		
      <td height="20">
<input type='text' id='area' name='area' size='20' maxlength='20' value='<%=beanGetdata.getFieldValue("area", 0)%>'></td>
	</tr>
	<tr>
		
      <td nowrap height="20"> 签名 </td>
		
      <td height="20">
<textarea id='signature' name='signature' rows='3' cols='15'><%=beanGetdata.getFieldValue("signature", 0)%></textarea></td>
	</tr>
	<tr>
		
      <td colspan="2" align="center" height="20"> 
        <%
			s_btnSubmit = "申请";
			if(s_action.equals("update"))
			{
				s_btnSubmit = "修改";
			}
			%>
        <input type="submit" id='btnsubmit' name='btnsubmit' value="<%=s_btnSubmit%>">
			<input type="reset" id='btnreset' name='btnreset' value="重置">
		</td>
	</tr>
</table>
</FORM>
</table>
</FORM>
</body>
</html>
	