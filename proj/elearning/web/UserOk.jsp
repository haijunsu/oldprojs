<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>�ɹ�ҳ��</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/3.css" type="text/css">
</head>
<body><Center>
<span class="testarea1"> 

<%@ include file="/MsgNotify.jsp"%>
</span><span class="jnfont6">�ɹ��� </span> 
<p><br>
  <FONT color='red' class="jnfont6"> 
  <%String message = (String) request.getAttribute("message");%>
  <%=message%> </FONT> 
</body>
</html>
	