<%@page contentType="text/html;charset=GB2312"%>
<HTML>
<HEAD>
<TITLE>学习课程</TITLE>
</HEAD>
<%
String classid = request.getParameter("classid");
if (classid == null)
{
	out.println("<FONT color=red>错误：<P>没有指定课程！</FONT>");
}
else
{
%>

<FRAMESET cols="150,*" framespacing="0">
  <FRAME src="lessonCatalog.jsp?classid=<%=classid%>" name="left">
  <FRAME src="lessonInfo.jsp?classid=<%=classid%>" name="right">
</FRAMESET>
<NOFRAMES><BODY>
你的浏览器不支持FRAME！
</BODY></NOFRAMES>
<% } %>
</HTML>
