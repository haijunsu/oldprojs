<%@page contentType="text/html;charset=GBK"%>
<html>
<head>
<title>�γ��������</title>
</head>
<%
String classid = (String)request.getParameter("classid");
if (classid == null)
{
	out.println("<FONT color=red>������û���ṩҪ���ԵĿγ�ID��</FONT>");
}
{
%>
<frameset rows="0,*">
  <frame name="refresh" scrolling="no" noresize src="refresh.jsp" border = "0">
  <frame name="exam" src="/servlet/exam/topicExam?classid=<%=classid%>">
  <noframes>
  <body>

  <p>����ҳʹ���˿�ܣ��������������֧�ֿ�ܡ�</p>

  </body>
  </noframes>
</frameset>
<%}%>
</html>
