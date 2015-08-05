<!-- page encoding -->
<%@ page 
language="java"
isErrorPage="false"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>

<%@ include file="/application/common/include.jsp" %>
<html>
	<head>
		<title><bean:message key="index.title"/></title>
		<link href="<bean:write name="rq" property="contextPath"/>/common/css/Master.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<table cellspacing='0' cellpadding='0' border='0'>
			<tr valign='top'  align='left' width='100%'>
				<td width='10'>
				&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
				<td>
				<html:image page="/common/imgs/title.jpg" alt="SMiRT 18 title image"></html:image>
				</td>
			</tr>
			<tr valign='center' align='center'>
				<td>
				&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
				<td>
				<%
				 session.invalidate();
				%>
					
				<bean:message key="success.logoff"/><br>
				<html:link action="/welcome"><bean:message key="form.button.relogon" bundle="forms"/></html:link>
				</td>
			</tr>
		</table>
	</body>
</html>
