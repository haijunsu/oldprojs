<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>����ҳ��</title>
</head>
<body><Center>
<h2 align="center"><img src="<%=request.getContextPath()%>/images/false.gif" width="241" height="296"></h2>

<p>���ִ��󣬴�����Ϣ����:<br><br><br>
<FONT color='red'>
<%String message = (String) request.getAttribute("errmsg");%>
<%=message%>
</FONT>
<br><br><br><p>�������:�뷵������,����������,�������Ա��ϵ!<br><br>
<a href=javascript:history.go(-1)>===<����>===</a>
</body>
</html>
	