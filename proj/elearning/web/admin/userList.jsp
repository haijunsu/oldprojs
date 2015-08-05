<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="bqc" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="ust" scope="session" class="system.userStyle"/>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);


 
%>
<%
String userid = (String)session.getAttribute("userid");
//ust.setUserStyle(userid);
boolean bAdmin = ets.isAdmin(userid);
%>
<HTML>
<HEAD>

<TITLE>用户列表</TITLE>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
<%@ include file="/admin/userSearch.jsp"%>
用户列表
<TABLE  border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="100%">
	<TR bgcolor="#CCCCCC">
		<TD>用户ID</TD>
		<TD>姓名</TD>
		<TD>EMAIL</TD>
		<TD>公司</TD>
		<TD>部门</TD>
		<% if (bAdmin)
		{
		 %>
		 <TD>状态</TD>
		 <% } %>
	</TR>
	<%
	String pageno = (String)request.getAttribute("pageno");
	int listCount = Integer.parseInt(ust.getLines_in_page());
	int pageNum = Integer.parseInt(pageno);
	int maxCount = java.lang.Math.min(bgd.getRowCount(), (pageNum-1)*listCount+listCount);
	for (int i=(pageNum-1)*listCount; i<maxCount; i++)
	{
	%>
	<TR>
		
    <TD><A href='<%=request.getContextPath()%>/servlet/system/userProfiles?action=admin&userid=<%=bgd.getFieldValue("user_id", i)%>'><%=bgd.getFieldValue("user_id", i)%></A>&nbsp;</TD>
		
    <TD><%=bgd.getFieldValue("user_name", i)%>&nbsp;</TD>
		
    <TD> <A href='mailto:<%=bgd.getFieldValue("email", i)%>'><%=bgd.getFieldValue("email", i)%></A>&nbsp;</TD>
		
    <TD><%=bgd.getFieldValue("company", i)%>&nbsp;</TD>
		
    <TD> 
      <%
				bqc.QueryCode("0002", bgd.getFieldValue("group_id", i));
	  %>
      <%=bqc.getCodeValue("code_namec", 0)%>&nbsp;</TD>
      <% if (bAdmin)
		{
			out.println("<TD>");
			bqc.QueryCode("0001", bgd.getFieldValue("user_status", i));
			out.println(bqc.getCodeValue("code_namec", 0));
			out.println("&nbsp;</TD>");
		  }
		  %>
	<%
	}
	%>
	</TR>
</TABLE>
<TABLE class="table003">
<TR>
	<TD>
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
			<A href="userList?pageno=<%=i%>"><%=i%></A>
			<%
			}
			%>
	</TD>
</TR>
</TABLE>
</BODY>
</HTML>
		
		
