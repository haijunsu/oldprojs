<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%
String navigation = (String)request.getAttribute("navigation");
%>
<HTML>
<HEAD>
<TITLE>课程详细列表</TITLE>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>

<%=navigation%><br>
<%@ include file="/lessadmin/lessonSearch.jsp" %>
详细分类:<br>
<%
if (bgd.getRowCount()>0)
{
%>
	<TABLE class="table003">
	<%
	for (int i=0; i<bgd.getRowCount(); i++)
	{
	%>
		<TR>
			<TD><A href='topicList?action=showDetail&codeid=<%=bgd.getFieldValue("code_id", i)%>&codevalue=<%=bgd.getFieldValue("code_value",i)%>'><%=bgd.getFieldValue("code_namec",i)%></A></TD>
		</TR>
	<%
	}
	%>
	</TABLE>
<%
}
else
	out.println("没有记录！");
%>
</BODY>
</HTML>
		
		
