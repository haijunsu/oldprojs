<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>新闻管理页面</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/xbz.css" type="text/css">
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


<%
if(s_msg != null)
{
%>
<Font color="red"><%=s_msg%></Font>
<%}%>
<p><span class="xbz">数据库中共有 <%=beanGetdata.getRowCount()%> 条新闻</span><br>

<table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="100%" bgcolor="#FFFFFF" class="xbz">
  <tr align="center">
		
    <td height="20"> 新闻标题 </td>
		
    <td height="20"> 新闻分类 </td>
		
    <td height="20"> 新闻状态 </td>
		
    <td height="20"> 日期 </td>
		
    <td height="20"> 录入人 </td>
		
    <td height="20"> 操作 </td>
	</tr>
	<%
	if(beanGetdata.getRowCount()>0)
	{
		for (int i=0; i<beanGetdata.getRowCount(); i++)
		{
		%>
		<tr>
			
    <td height="20"> <%=beanGetdata.getFieldValue("news_title", i)%> </td>
			
    <td height="20"> 
      <%
			s_Mright = beanGetdata.getFieldValue("type_id", i);
			s_Mright = s_Mright.trim();
			if(beanQueryTypeid.getCodeCount()>0)
			{
				for (int j=0; j<beanQueryTypeid.getCodeCount(); j++)
				{
					s_Gnum = beanQueryTypeid.getCodeValue(2, j);
					s_Gnum = s_Gnum.trim();
					s_Checked = "";
					if(s_Gnum.equals(s_Mright))
					{
				%>
      <%=beanQueryTypeid.getCodeValue(3, j)%> 
      <%
					}
				}
			}
			else
			{
			%>
      <%=s_Mright%> 
      <%}%>
    </td>
			
    <td height="20"> 
      <%
			s_Mright = beanGetdata.getFieldValue("news_status", i);
			s_Mright = s_Mright.trim();
			if(beanQueryNewsStatus.getCodeCount()>0)
			{
				for (int j=0; j<beanQueryNewsStatus.getCodeCount(); j++)
				{
					s_Gnum = beanQueryNewsStatus.getCodeValue(2, j);
					s_Gnum = s_Gnum.trim();
					s_Checked = "";
					if(s_Gnum.equals(s_Mright))
					{
				%>
      <%=beanQueryNewsStatus.getCodeValue(3, j)%> 
      <%
					}
				}
			}
			else
			{
			%>
      <%=s_Mright%> 
      <%}%>
    </td>
			
    <td height="20"> <%=beanElearnTools.FormatDate(beanGetdata.getFieldValue("news_date", i))%> 
    </td>
			
    <td height="20"> <%=beanGetdata.getFieldValue("user_id", i)%> </td>
			
    <td height="20"> <A href='elearn.bean_ManagerNews?action=modify&newsid=<%=beanGetdata.getFieldValue("news_id", i)%>'>修改</A> 
    </td>
		</tr>
		<%
		}
	}
	%>
	<tr align="center">
		
    <td colspan="6" height="20"> <A href='elearn.bean_ManagerNews?action=new' class="xbz">添加新闻</A> 
    </td>
	</tr>
	
</table>
</FORM>
</table>
</FORM>
</body>
</html>
	