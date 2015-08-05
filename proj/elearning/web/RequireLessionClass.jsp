<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>用户页面</title>
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
<jsp:useBean id="bqc" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="beanElearnTools" scope="request" class="com.htyz.beanElearnTools"/>

<%@ include file="/MsgNotify.jsp"%>
<%
String s_msg, s_Gnum, s_Gright, s_Mright, s_Checked, s_Group;
String s_user = (String)request.getAttribute("user");
s_msg = (String)request.getAttribute("message");
s_Group =  (String)request.getAttribute("group");
%>
<h2> 注册用户列表 </h2>

<%
if(s_msg != null)
{
%>
<Font color="red"><%=s_msg%></Font>
<%}%>
<p>数据库中共有 <%=beanGetdata.getRowCount()%> 个课程
<P>
请选择要指定的课程：
<p>
<FORM id="userForm" name="userForm" method="Get" action="elearn.bean_ManagerRequireLession">
<input type="hidden" id="action" name="action" value="end">
<input type="hidden" id="user" name="user" value="<%=s_user%>">
<input type="hidden" id="classcount" name="classcount" value="<%=beanGetdata.getRowCount()%>">

<%
if(beanGetdata.getRowCount()>0)
{
	for (int i=0; i<beanGetdata.getRowCount(); i++)
	{
	%>
	<input type='checkbox' id='classchecked<%=i%>' name='classchecked<%=i%>' value='1'>
	<input type='hidden' id='classid<%=i%>' name='classid<%=i%>' value='<%=beanGetdata.getFieldValue("class_id", i)%>'>
	<%=beanGetdata.getFieldValue("class_name", i)%><BR>
	<%
	}
}
%>
<input type="submit" id="btnsubmit" name="btnsubmit" value="指定课程">
</FORM>
</body>
</html>
	