<%@page contentType="text/html;charset=GBK"%>
<html>
<head>
<title>课程试题管理</title>
</head>
<%
String classid = (String)request.getParameter("classid");
if (classid == null)
{
	out.println("<FONT color=red>错误：您没有提供要考试的课程ID！</FONT>");
}
{
%>
<frameset rows="0,*">
  <frame name="refresh" scrolling="no" noresize src="refresh.jsp" border = "0">
  <frame name="exam" src="/servlet/exam/topicExam?classid=<%=classid%>">
  <noframes>
  <body>

  <p>此网页使用了框架，但您的浏览器不支持框架。</p>

  </body>
  </noframes>
</frameset>
<%}%>
</html>
