<!-- page encoding -->
<%@ page 
language="java"
isErrorPage="false"
contentType="text/html; charset=utf-8"
pageEncoding="utf-8"
%>

<%@ include file="/common/include.jsp" %>
<html>
	<head>
		<title><bean:message key="index.title"/></title>
		<link href="<bean:write name="rq" property="contextPath"/>/common/css/Master.css" rel="stylesheet" type="text/css">
	</head>
	<body>
<table width='100%' height='100%' cellspacing='0' cellpadding='0' border='0'>
<tr valign='top' align='center' width='100%'>
<td width='100%'>
Welcome.
</td>
</tr>
</table>
</body>
</html>