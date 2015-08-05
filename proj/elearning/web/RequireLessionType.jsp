<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>用户已经选择的课程</title>
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
<jsp:useBean id="beanGetdataClass" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="bqc" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="beanElearnTools" scope="request" class="com.htyz.beanElearnTools"/>

<%@ include file="/MsgNotify.jsp"%>
<%
String s_msg, s_Gnum, s_Gright, s_Mright, s_Checked, s_Group;
String s_user = (String)request.getAttribute("user");
String s_action = (String)request.getAttribute("action");
s_msg = (String)request.getAttribute("message");
s_Group =  (String)request.getAttribute("group");
bqc.QueryCode("1000");
%>
<%
if(s_msg != null)
{
%>
<Font color="red"><%=s_msg%></Font> 
<%}%>
<p><br>
  用户已经选择了 <%=beanGetdata.getRowCount()%> 个课程 
<P>


<%
if(beanGetdata.getRowCount()>0)
{
	for (int i=0; i<beanGetdata.getRowCount(); i++)
	{
	%>
	<%=beanGetdata.getFieldValue(1, i)%>&nbsp;&nbsp;&nbsp;<%=beanGetdata.getFieldValue("class_name", i)%><BR>
	<%
	}
}
%>
<P>选择下面要添加的课程类型: 
<P>
<%
if(bqc.getCodeCount()>0)
{
	for (int i=0; i<bqc.getCodeCount(); i++)
	{
	%>
	<A href=elearn.bean_ManagerRequireLession?action=list&listtype=class&user=<%=s_user%>&classgroup=<%=bqc.getCodeValue("code_value", i)%>><%=bqc.getCodeValue("code_namec", i)%></A><BR>
	<%
	}
}
%>
</body>
</html>
	