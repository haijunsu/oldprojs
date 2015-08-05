<!-- page encoding -->
<%@ page 
language="java"
isErrorPage="true"
contentType="text/html; charset=GBK"
pageEncoding="GBK"
%>

<%@ include file="/application/common/include.jsp" %>

<html:html>
<head>
<meta http-equiv="Content-Style-Type" content="text/css">
		<link href='<bean:write name="rq" property="contextPath"/>/style.css' rel="stylesheet" type="text/css">
<title>
<bean:message key="errors.page.title"/></title>

</head>
<body bgcolor="#FFFFFF">
<table><tr><td>
<html:img  alt="error image" page="/application/common/errors/imgs/error.gif" />
</td>
<td>
<p class="errTitle"><bean:message key="errors.title"/></p>
<p class="errMsg">
<html:errors/></td>
</tr>
<tr>
<td colspan='2' align='center'>
	<logic:present name="backBtn">
		<bean:write name="backBtn" property="button" filter="false"/>
	</logic:present>
	<logic:notPresent name="backBtn">
	<html:link page="/"><bean:message key="return.home" /></html:link>
	</logic:notPresent>
</td>
</tr>
</table>
</body>
</html:html>