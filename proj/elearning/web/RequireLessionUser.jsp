<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>�û�ҳ��</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/3.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/xbz.css" type="text/css">
</head>
<%
//��ֹCache.
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
s_msg = (String)request.getAttribute("message");
s_Group =  (String)request.getAttribute("group");
%>
<%
if(s_msg != null)
{
%>
<Font color="red"><%=s_msg%></Font>
<%}%>
<p>���ݿ��й��� <%=beanGetdata.getRowCount()%> ���û�
<P>

<FORM id="userForm" name="userForm" method="Get" action="elearn.bean_ManagerRequireLession">
  <p class="xbz"> 
    <input type="hidden" id="action" name="action" value="list">
    <input type="hidden" id="listtype" name="listtype" value="user">
    �������û�ID�������ַ��� 
    <input class="input1" type="text" id="group" name="group" value="">
    <input class="input2" type="submit" id="btnsubmit" name="btnsubmit" value="�����б�">
  </p>
</FORM>

<span class="xbz"> ��ע����û���<br>
<%
if(beanGetdata.getRowCount()>0)
{
	for (int i=0; i<beanGetdata.getRowCount(); i++)
	{
	%>
������<A href=elearn.bean_ManagerRequireLession?action=list&listtype=classtype&user=<%=beanGetdata.getFieldValue("user_id", i)%>><%=beanGetdata.getFieldValue("user_id", i)%></A><BR>
<%
	}
}
%>
</span> 
</body>
</html>
	