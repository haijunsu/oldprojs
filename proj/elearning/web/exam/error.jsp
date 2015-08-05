<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>错误页面</title>
</head>
<body>
<Center>

<h2>出错了</h2>

<p>程序执行时出现错误，错误信息如下:<br>
<FONT color='red'>
<%String message = (String) request.getAttribute("errmsg");%>
<%=message%>
</FONT>

</body>
</html>
	