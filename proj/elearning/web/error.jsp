<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>错误页面</title>
</head>
<body><Center>
<h2 align="center"><img src="<%=request.getContextPath()%>/images/false.gif" width="241" height="296"></h2>

<p>出现错误，错误信息如下:<br><br><br>
<FONT color='red'>
<%String message = (String) request.getAttribute("errmsg");%>
<%=message%>
</FONT>
<br><br><br><p>解决方法:请返回重试,如仍有问题,请与管理员联系!<br><br>
<a href=javascript:history.go(-1)>===<返回>===</a>
</body>
</html>
	