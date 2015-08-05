<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>代码管理页面</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/3.css" type="text/css">
</head>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<Script language="javascript">
<!--
function codeSubmit(s_action, s_value, s_namec, s_namee, s_msg)
{
	document.codeForm.action.value = s_action;
	document.codeForm.CodeValue.value = s_value;
	document.codeForm.CodeNameC.value = s_namec;
	document.codeForm.CodeNameE.value = s_namee;
	if(s_action == "delete")
	{
		if(!confirm("确认在删除代码值为“" + s_msg + "”的记录吗？"))
		{
			return;
		}
	}

	if(s_action == "add"||s_action == "list")
	{
		document.codeForm.CodeId.value = s_msg;
	}

	document.codeForm.submit();
	return false;
}
-->
</Script>
<body>
<jsp:useBean id="beanCodeManager" scope="request" class="com.htyz.beanQueryCodes"/>

<%@ include file="/MsgNotify.jsp"%>

<%
String s_msg;
s_msg = (String)request.getAttribute("message");
%>


<%
if(s_msg != null)
{
%>
<Font color="red"><%=s_msg%></Font>
<%}%>
<p>数据库中共有 <%=beanCodeManager.getCodeCount()%> 种分类<br>

<FORM id="codeForm" name="codeForm" method="Get" action="elearn.bean_ManagerCodes">
<input type="hidden" id="action" name="action" value="list">
<input type="hidden" id="CodeId" name="CodeId" value="oldCodeId">
<input type="hidden" id="oldCodeId" name="oldCodeId" value="<%=beanCodeManager.getCodeValue("Code_id", 0)%>">
<input type="hidden" id="CodeValue" name="CodeValue" value="">
<input type="hidden" id="CodeNameC" name="CodeNameC" value="">
<input type="hidden" id="CodeNameE" name="CodeNameE" value="">
  <table border="1" cellspacing="0" cellpadding="0" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="100%">
    <tr align="center">
		
      <td nowrap height="20"> 代码编号 </td>
		
      <td height="20"> 代码值 </td>
		
      <td height="20"> 代码中文名称 </td>
		
      <td height="20"> 代码英文名称 </td>
		
      <td nowrap height="20"> 操作 </td>
	</tr>
	<%
	for (int i=0; i<beanCodeManager.getCodeCount(); i++)
	{
	%>
	<tr align="center">
		
      <td nowrap height="20"> <strong><%=beanCodeManager.getCodeValue("Code_id", i)%> 
        </strong></td>
		
      <td height="20"> 
        <input id='CodeValue<%=i%>' name='CodeValue<%=i%>' type='text' value='<%=beanCodeManager.getCodeValue("Code_value", i)%>' size="4" maxlength="4">
		<input id='oldCodeValue<%=i%>' name='oldCodeValue<%=i%>' type='hidden' value='<%=beanCodeManager.getCodeValue("Code_value", i)%>'> </td>
		
      <td height="20"> 
        <input class="input1" id='CodeNameC<%=i%>' name='CodeNameC<%=i%>' type='text' value='<%=beanCodeManager.getCodeValue("Code_namec", i)%>' size="30" maxlength="50"> </td>
		
      <td height="20"> 
        <input class="input1" id='CodeNameE<%=i%>' name='CodeNameE<%=i%>' type='text' value='<%=beanCodeManager.getCodeValue("Code_namee", i)%>' size="30" maxlength="50"> </td>
		
      <td nowrap height="20"> <A href=javascript:codeSubmit('update','CodeValue<%=i%>','CodeNameC<%=i%>','CodeNameE<%=i%>','<%=beanCodeManager.getCodeValue("Code_value", i)%>')>修改</A> 
        &nbsp; <A href=javascript:codeSubmit('delete','CodeValue<%=i%>','CodeNameC<%=i%>','CodeNameE<%=i%>','<%=beanCodeManager.getCodeValue("Code_value", i)%>')>删除</A> 
        &nbsp; </td>
	</tr>
	<%
	}
	%>
	<tr align="center">
		
      <td height="20"> 
        <input type="text" id="addCodeId" name="addCodeId" size="4" maxlength="4">
		</td>
		
      <td height="20"> 
        <input type="text" id="addCodeValue" name="addCodeValue" size="4" maxlength="4">
		</td>
		
      <td height="20"> 
        <input class="input1" type="text" id="addCodeNameC" name="addCodeNameC" size="30" maxlength="50">
		</td>
		
      <td height="20"> 
        <input class="input1" type="text" id="addCodeNameE" name="addCodeNameE"  size="30" maxlength="50">
		</td>
		
      <td height="20"> <A href=javascript:codeSubmit('add','addCodeValue','addCodeNameC','addCodeNameE','addCodeId')> 
        添加代码 </A> &nbsp; </td>
	</tr>
	<tr align="center">
		
      <td colspan="5" height="20">请输入代码编号 
        <input type="text" id="QueryCodeId" name="QueryCodeId" size="4" maxlength="4">
		<A href=javascript:codeSubmit('list','','','','QueryCodeId')> 重新查询 </A>
		</td>

	</tr>		
</table>
</FORM>
</body>
</html>
	