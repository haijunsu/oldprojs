<%@page contentType="text/html;charset=GB2312"%>
<HTML>
<HEAD>
<TITLE>ѧϰ�γ�</TITLE>
</HEAD>
<%
String classid = request.getParameter("classid");
if (classid == null)
{
	out.println("<FONT color=red>����<P>û��ָ���γ̣�</FONT>");
}
else
{
%>

<FRAMESET cols="150,*" framespacing="0">
  <FRAME src="lessonCatalog.jsp?classid=<%=classid%>" name="left">
  <FRAME src="lessonInfo.jsp?classid=<%=classid%>" name="right">
</FRAMESET>
<NOFRAMES><BODY>
����������֧��FRAME��
</BODY></NOFRAMES>
<% } %>
</HTML>
