<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>�ɹ�ҳ��</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/3.css" type="text/css">
</head>
<body><Center>
<p><br>
  <FONT color='red'> 
  <%String message = (String) request.getAttribute("message");%>
  <br>
  <%=message%> </FONT> 
</body>
</html>
	