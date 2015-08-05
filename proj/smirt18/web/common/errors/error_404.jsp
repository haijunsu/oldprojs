<!-- page encoding -->
<%@ page 
language="java"
isErrorPage="true"
contentType="text/html; charset=utf-8"
pageEncoding="utf-8"
%>

<%@ include file="/common/include.jsp" %>
<html>


<head>
<meta name="GENERATOR" content="su_haijun">
<meta http-equiv="Content-Style-Type" content="text/css">
<link href="<bean:write name="rq" property="contextPath"/>/common/css/Master.css" rel="stylesheet" type="text/css">
<title>
<bean:message key="errors.page.title" />
</title>

</head>
<body bgcolor="#FFFFFF">
<table><tr><td>
<html:img  alt="error image" page="/common/errors/imgs/error.gif" />
</td>
<td>
<p class="errTitle"><bean:message key="errors.title"/></p>
<p class="errMsg">
<bean:message key="errors.404.msg"/>
</p>
</td>
</tr>
</table>
<p align='center'>
	<html:link page="/"><bean:message key="return.home" /></html:link>
</p>

<jsp:include page="/common/copyright.jsp" flush="true" />
</body>
</html>