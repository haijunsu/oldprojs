<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>课程试题管理</title>
</head>
<%
String reload = (String)request.getAttribute("reload");
%>
<frameset cols="150,*">
	<frame name="left" src="topicList?reload=<%=reload%>">
	<frame name="right" src="about:blank">
</frameset>
</html>
