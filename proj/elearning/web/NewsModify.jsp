<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>新闻列表页面</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/xbz.css" type="text/css">
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
function menuSubmit(s_action, s_change, s_menuid)
{
	document.newForm.action.value = s_action;
	document.newForm.changenum.value = s_change;
	if(s_menuid == "group")
	{
		document.newForm.group.value = document.newForm.groupvalue.value;
	}

	document.newForm.submit();
	return false;
}
-->
</Script>
<body>

<%@ include file="/MsgNotify.jsp"%>
<jsp:useBean id="beanGetdata" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="beanQueryTypeid" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="beanQueryNewsStatus" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="beanElearnTools" scope="request" class="com.htyz.beanElearnTools"/>
<%
String s_action, s_msg, s_Gnum, s_Gright, s_Mright, s_Checked, s_Group;
s_action = (String)request.getAttribute("action");
s_msg = (String)request.getAttribute("message");
s_Group =  (String)request.getAttribute("group");
beanQueryTypeid.QueryCode("0006");
beanQueryNewsStatus.QueryCode("0004");
%>
<span class="xbz">新闻维护</span> 
<%
if(s_msg != null)
{
%>
<Font color="red"><%=s_msg%></Font> 
<%}%>
<FORM id="newForm" name="newForm" method="post" action="elearn.bean_ManagerNews">
<input type="hidden" id="action" name="action" value="<%=s_action%>">
<input type="hidden" id="group" name="group" value="<%=s_Group%>">
<input type="hidden" id="newsid" name="newsid" value="<%=beanGetdata.getFieldValue("news_id", 0)%>">
  <table border="0" align="center" BORDERCOLOR="blue" width="100%">
    <tr>
		
      <td nowrap> 新闻标题:</td>
      <td width="593"> 
        <input class='input1' type='text' id='newstitle' name='newstitle' value='<%=beanGetdata.getFieldValue("news_title", 0)%>' maxlength='100' size='64'>
         </td>
	</tr>
	<tr>
		
      <td nowrap> 新闻分类:</td>
      <td width="593"> 
        <%
			s_Mright = beanGetdata.getFieldValue("type_id", 0);
			s_Mright = s_Mright.trim();
			if(beanQueryTypeid.getCodeCount()>0)
			{
			%>
        <select id='typeid' name='typeid' size='1'>
			<%
				for (int j=0; j<beanQueryTypeid.getCodeCount(); j++)
				{
					s_Gnum = beanQueryTypeid.getCodeValue(2, j);
					s_Gnum = s_Gnum.trim();
					s_Checked = "";
					if(s_Gnum.equals(s_Mright))
						s_Checked = "selected";
				%>
				<option <%=s_Checked%> value='<%=s_Gnum%>'><%=beanQueryTypeid.getCodeValue(3, j)%></option>
				<%}%>
			</select>
			<%
			}
			else
			{
			%>
			<input type='text' value='<%=s_Mright%>' id='typeid' name='typeid' size='4' maxlength='4'>
			<%}%>
		
		</td>
	</tr>
	<tr>
		
      <td nowrap> 新闻状态: </td>
      <td width="593"> 
        <%
			s_Mright = beanGetdata.getFieldValue("news_status", 0);
			s_Mright = s_Mright.trim();
			if(beanQueryNewsStatus.getCodeCount()>0)
			{
			%>
        <select id='status' name='status' size='1'>
			<%
				for (int j=0; j<beanQueryNewsStatus.getCodeCount(); j++)
				{
					s_Gnum = beanQueryNewsStatus.getCodeValue(2, j);
					s_Gnum = s_Gnum.trim();
					s_Checked = "";
					if(s_Gnum.equals(s_Mright))
						s_Checked = "selected";
				%>
				<option <%=s_Checked%> value='<%=s_Gnum%>'><%=beanQueryNewsStatus.getCodeValue(3, j)%></option>
				<%}%>
			</select>
			<%
			}
			else
			{
			%>
			<input type='text' value='<%=s_Mright%>' id='status' name='status' size='1' maxlength='1'>
			<%}%>
		
		</td>
	</tr>
	<tr>
		
      <td nowrap> 新闻内容:</td>
      <td width="593"> 
        <textarea class="testarea1" id='newscontent' name='newscontent' rows='10' cols='70'><%=beanGetdata.getFieldValue("news_content", 0)%></textarea>
		</td>
	</tr>
	<tr align="center">
		<td colspan="2">
			<%
			String s_btnSubmit = "添加";
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
	