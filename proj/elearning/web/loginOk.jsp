<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>�ɹ�ҳ��</title>
<%
//��ֹCache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<link rel="stylesheet" href="xbz.css" type="text/css">
</head>
<body><Center>
<span class="xbz"> ��¼�ɹ� </span> 
<p>
<script language="javascript">
	window.open("../index.jsp");
	self.close();
</script>

</body>
</html>
	