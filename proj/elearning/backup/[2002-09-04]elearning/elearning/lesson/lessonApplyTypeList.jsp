<%@page contentType="text/html;charset=GBK"%>
<%@page session="true"%>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="bqc" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%
String navigation = (String)request.getAttribute("navigation");
String applyType = (String)request.getAttribute("applyType");
String catalog = (String)request.getAttribute("catalog");
%>
<HTML>
<HEAD>
<TITLE>课程类型列表</TITLE>
<link href="/elearning/style.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
<%=navigation%>
<BR>
<%@ include file="/elearning/lesson/lessonSearch.jsp" %>
<%=catalog%>详细信息:<BR>
<%
if (bgd.getRowCount()>0)
{
%>
	<TABLE  class="table003">
	<%
	for (int i=0; i<bgd.getRowCount(); i++)
	{
	%>
		<TR>
			<TD><A href='lessonApplyList?applyType=<%=applyType%>&action=showDetail&codeid=<%=bgd.getFieldValue("code_id", i)%>&codevalue=<%=bgd.getFieldValue("code_value",i)%>'><%=bgd.getFieldValue("code_namec",i)%></A></TD>
		</TR>
	<%
	}
	%>
	</TABLE>
<%
}
else
	out.println("没有子记录！");
%>
</BODY>
</HTML>
		
		
