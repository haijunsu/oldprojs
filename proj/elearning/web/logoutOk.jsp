<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>成功页面</title>
</head>
<script language="javascript">
<!--
alert("成功退出！");
window.opener.location="<%=request.getContextPath()%>/index.jsp";
window.close();
</script>
<body>


</body>
</html>
	