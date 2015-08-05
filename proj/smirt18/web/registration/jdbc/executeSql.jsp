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
	<html:form action="executeSql" >
	<table width='600' cellspacing='0' cellpadding='0' border='0'>
		<tr valign='center' align='center' width='100%'>
			<td width='100%'>
			<h3>
				<bean:message key="jdbc.page.title"/></h3>
			</td>
		</tr>
		<tr valign='center' align='center' width='100%'>
			<td width='100%'>
				<html:hidden property="action" value="runSql"/>
				<html:textarea property="sqls" cols="60" rows="10"></html:textarea>
				<html:submit>
					<bean:message key="form.button.execute" bundle="forms"/>
				</html:submit>
			</td>
		</tr>
	</table>
	</html:form>

	</body>
</html>