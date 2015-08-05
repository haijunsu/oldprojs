<!-- page encoding -->
<%@ page 
language="java"
isErrorPage="false"
contentType="text/html; charset=utf-8"
pageEncoding="utf-8"
%>

<%@ include file="/common/include.jsp" %>

<html:html>
<head>
<meta http-equiv="Content-Style-Type" content="text/css">
<link href="<bean:write name="rq" property="contextPath"/>/common/css/Master.css" rel="stylesheet" type="text/css">
<title>
<bean:message key="success.page.title"/></title>

</head>
<body>
<table><tr><td>
<html:img  alt="success image" page="/common/success/imgs/success.jpg" />
</td>
<td>
<p class="successTitle"><bean:message key="success.title"/></p>
<p class="successMsg">
<html:messages message="true" id="msg" header="messages.header" footer="messages.footer">
	<bean:write name="msg" />
</html:messages>
</td>
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
</tr></table>

</body>
</html:html>