<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="eds" scope="request" class="com.htyz.util.BeanDateFormat"/>
<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="ust" scope="session" class="system.userStyle"/>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%
String navigation = (String)request.getAttribute("navigation");
String catalog = (String)request.getAttribute("catalog");
String action = (String)request.getAttribute("action");
String queryItem = (String)request.getAttribute("queryItem");
String order = (String)request.getAttribute("order");
String filter = (String)request.getAttribute("filter");
%>
<HTML>
<HEAD>
<TITLE>课程列表</TITLE>
<SCRIPT language="JavaScript" type="text/JavaScript">
function submitForm(pageNo)
{
	document.logHistory.pageno.value = pageNo;
	document.logHistory.submit();
	return true;
}
function changeOrder()
{
	document.logHistory.order.value= document.logHistory.select.value;
	document.logHistory.submit();
	return true;
}
</script>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">

<%=navigation%>
<form name=logHistory action='query'>
	<table border="0" width="90%">
		<tr><td>
			<%if (bgd.getRowCount()>0)
				{%>
					  <select name="select" onchange="changeOrder()">
					    <option value="login_time">请选择排序方法</option>
					    <option value="user_id">按用户名</option>
					    <option value="login_time">按登录时间</option>
					    <option value="ip_address">按IP</option>
					  </select>
		</td>
		<td align="right">
			  <INPUT name="filter" type="text" id="filter" value="<%= filter%>">
			  <input name="imageField" type="image" src="<%=request.getContextPath()%>/images/go.gif" width="29" height="16" border="0" onclick="searchForm()">
		</td>
		<tr><td colspan="2">
<TABLE  border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" class="table003" width="100%">
	<TR bgcolor="#CCCCCC" align="center" Height="25">
		<TD>登录用户</TD>
		<TD>登录时间</TD>
		<TD>登录IP</TD>
</tr>
	<%
	String pageno = (String)request.getAttribute("pageno");
	if (pageno == null) pageno = "1";
	int listCount = Integer.parseInt(ust.getLines_in_page());
	int pageNum = Integer.parseInt(pageno);
	int maxCount = java.lang.Math.min(bgd.getRowCount(), (pageNum-1)*listCount+listCount);
	for (int i=0; i<maxCount-(pageNum-1)*listCount; i++)
	{
	%>
<tr>
      <TD> <%=bgd.getFieldValue("user_id",i+(pageNum-1)*listCount)%>&nbsp; </TD>

      <TD> <%=eds.format(bgd.getFieldValue("login_time", i+(pageNum-1)*listCount))%>&nbsp;  </TD>

      <TD> <%=bgd.getFieldValue("ip_address", i+(pageNum-1)*listCount)%>&nbsp;  </TD>

	</tr>
<TR>
<%}%>
	<TD COLSPAN='3'>
		<%
			int i_count = (int)java.lang.Math.ceil((double)bgd.getRowCount()/listCount);
		%>
		共<%=i_count%>页&nbsp;
		<%
			for (int i=1; i<=i_count; i++)
			if (i == pageNum)
			{
				out.print(pageno);
			}
			else
			{
			%>
			<A href="javascript:submitForm(<%=i%>)"><%=i%></A>
			<%
			}
			%>
	</TD>
</TR>
<TR>
</table>
<INPUT name="countInPage" type="hidden" id="countInPage" value="<%= maxCount-(pageNum-1)*listCount%>">
<INPUT name="order" type="hidden" id="order" value="<%= order%>">
<INPUT name="action" type="hidden" id="action" value="<%= action%>">
<INPUT name="pageno" type="hidden" id="pageno" value="<%= pageno%>">
<INPUT name="queryItem" type="hidden" id="queryItem" value="<%= queryItem%>">
</form>
<%
}
else
{
	out.println("没有记录！");

}
%>
</td></tr>
</table>