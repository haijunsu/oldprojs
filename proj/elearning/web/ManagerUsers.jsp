<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>用户管理页面</title>
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
function userSubmit(s_action, s_change, s_userid)
{
	document.userForm.action.value = s_action;
	document.userForm.changenum.value = s_change;
	if(s_userid == "group")
	{
		document.userForm.group.value = document.userForm.groupvalue.value;
	}

	document.userForm.submit();
	return false;
}
-->
</Script>
<body>
<jsp:useBean id="beanGetdata" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="bqcrole" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="bqcgroup" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="bqcstatus" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="beanElearnTools" scope="request" class="com.htyz.beanElearnTools"/>

<%@ include file="/MsgNotify.jsp"%>
<%
String s_msg, s_Gnum, s_Gright, s_Mright, s_Checked, s_Group;
s_msg = (String)request.getAttribute("message");
s_Group =  (String)request.getAttribute("group");
bqcrole.QueryCode("0003");
bqcgroup.QueryCode("0005");
bqcstatus.QueryCode("0010");
%>
<h2> 用户管理页面 </h2>

<%
if(s_msg != null)
{
%>
<Font color="red"><%=s_msg%></Font>
<%}%>
<p>数据库中共有 <%=beanGetdata.getRowCount()%> 个用户<br>

<FORM id="userForm" name="userForm" method="Get" action="elearn.bean_ManagerUsers">
<input type="hidden" id="action" name="action" value="list">
<input type="hidden" id="rightnum" name="rightnum" value="<%=bqcrole.getCodeCount()%>">
<input type="hidden" id="changenum" name="changenum" value="">
<input type="hidden" id="group" name="group" value="<%=s_Group%>">
<table border="0" align="center" BORDERCOLOR="blue">
	<tr align="center">
		<td> 用户ID </td>
		<td> 姓名 </td>
		<td> 出生日期 </td>
		<td> 单位 </td>
		<td> 信箱 </td>
		<td> 用户组 </td>
		<td> 状态 </td>
		<td> 操作 </td>
	</tr>
	<%
	if(beanGetdata.getRowCount()>0)
	{
		for (int i=0; i<beanGetdata.getRowCount(); i++)
		{
		%>
		<tr align="center">
			<td rowspan='2'> <input id='userid<%=i%>' name='userid<%=i%>' type='hidden' value='<%=beanGetdata.getFieldValue("user_id", i)%>' size='4' maxlength='4'>
			<%=beanGetdata.getFieldValue("user_id", i)%>
			</td>
			<td><%=beanGetdata.getFieldValue("user_name", i)%> </td>
			<td><%=beanElearnTools.FormatDate(beanGetdata.getFieldValue("birthday", i).substring(0,10))%> </td>
			<td><%=beanGetdata.getFieldValue("company", i)%> </td>
			<td><%=beanGetdata.getFieldValue("email", i)%> </td>
			<td>  
			<%
			s_Mright = beanGetdata.getFieldValue("group_id", i);
			s_Mright = s_Mright.trim();
			if(bqcgroup.getCodeCount()>0)
			{
			%>
			<select class="input1" id='usergroup<%=i%>' name='usergroup<%=i%>' size='1'>
			<%
				for (int j=0; j<bqcgroup.getCodeCount(); j++)
				{
					s_Gnum = bqcgroup.getCodeValue(2, j);
					s_Gnum = s_Gnum.trim();
					s_Checked = "";
					if(s_Gnum.equals(s_Mright))
						s_Checked = "selected";
				%>
				<option <%=s_Checked%> value='<%=s_Gnum%>'><%=bqcgroup.getCodeValue(3, j)%></option>
				<%}%>
			</select>
			<%
			}
			else
			{
			%>
			<input type='text' value='<%=s_Mright%>' id='usergroup<%=i%>' name='usergroup<%=i%>' size='4' maxlength='4'>
			<%}%>
			</td>
			<td>  
			<%
			s_Mright = beanGetdata.getFieldValue("user_status", i);
			s_Mright = s_Mright.trim();
			if(bqcstatus.getCodeCount()>0)
			{
			%>
			<select id='userstatus<%=i%>' name='userstatus<%=i%>' size='1'>
			<%
				for (int j=0; j<bqcstatus.getCodeCount(); j++)
				{
					s_Gnum = bqcstatus.getCodeValue(2, j);
					s_Gnum = s_Gnum.trim();
					s_Checked = "";
					if(s_Gnum.equals(s_Mright))
						s_Checked = "selected";
				%>
				<option <%=s_Checked%> value='<%=s_Gnum%>'><%=bqcstatus.getCodeValue(3, j)%></option>
				<%}%>
			</select>
			<%
			}
			else
			{
			%>
			<input type='text' value='<%=s_Mright%>' id='userstatus<%=i%>' name='userstatus<%=i%>' size='4' maxlength='4'>
			<%}%>
			</td>
			<td rowspan='2' nowrap> 
				<A href=javascript:userSubmit('update','<%=i%>','')>确认修改</A>  
			</td>
		</tr>
		<tr align="center">
			<td colspan="5">
			<%
			s_Mright = beanGetdata.getFieldValue("user_right", i);
			if(bqcrole.getCodeCount()>0)
			{
				for (int j=0; j<bqcrole.getCodeCount(); j++)
				{
					s_Gnum = bqcrole.getCodeValue(2, j);
					s_Gright = beanElearnTools.parseRight(s_Gnum);
					s_Checked = "";
					if(beanElearnTools.isMenuShow(s_Gright, s_Mright))
						s_Checked = "checked";
				%>
				<input id='userright<%=i%><%=j%>' name='userright<%=i%><%=j%>' type='checkbox' <%=s_Checked%> value='<%=s_Gnum%>'><%=bqcrole.getCodeValue(3, j)%>
				<%
				}
			}
			%>
			</td>
		</tr>
		<tr align="center">
			<td colspan="7">  <hr>  </td>
		</tr>
		<%
		}
	}
	%>
	<tr align="center">
		<td colspan="5"> <input class='input1' type='text' size='20' maxlength='20' id='groupvalue' name='groupvalue' value=""> 			<A href=javascript:userSubmit('list','','group')> 按输入的前几位分组显示 </A>  &nbsp;
</td>
	</tr>
</table>
</FORM>
</table>
</FORM>
</body>
</html>
	