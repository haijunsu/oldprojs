<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>≤Àµ•π‹¿Ì</title>
</head>

<frameset cols="150,*">
	<frame name="left" src='menuList?pageno=<%=request.getAttribute("pageno")%>&reload=<%=request.getAttribute("pageno")%>'>
	<frame name="right" src="about:blank">
</frameset>

</html>
