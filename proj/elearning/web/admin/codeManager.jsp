<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>ґъВл№ЬАн</title>
</head>
<%
String treeroot = (String)request.getAttribute("treeroot");
%>
<frameset cols="150,*">
	<frame name="left" src='codeList?reload=true&root=<%=treeroot%>'>
	<frame name="right" src="about:blank">
</frameset>

</html>
