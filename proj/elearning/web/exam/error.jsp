<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>����ҳ��</title>
</head>
<body>
<Center>

<h2>������</h2>

<p>����ִ��ʱ���ִ��󣬴�����Ϣ����:<br>
<FONT color='red'>
<%String message = (String) request.getAttribute("errmsg");%>
<%=message%>
</FONT>

</body>
</html>
	