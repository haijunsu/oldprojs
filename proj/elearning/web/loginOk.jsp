<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>成功页面</title>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<link rel="stylesheet" href="xbz.css" type="text/css">
</head>
<body><Center>
<span class="xbz"> 登录成功 </span> 
<p>
<script language="javascript">
	window.open("../index.jsp");
	self.close();
</script>

</body>
</html>
	