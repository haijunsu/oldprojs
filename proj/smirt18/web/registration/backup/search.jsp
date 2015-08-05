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
<html:form action="/search" >
<table width='100%' cellspacing='0' cellpadding='0' border='0'>
	<tr valign='center' align='center' width='100%'>
		<td width='100%'>
			<bean:message key="form.search.type" bundle="forms"/>
			<select id='action' name='action' size='1'>
				<option value='queryByEmail'><bean:message key="form.user.email" bundle="forms"/></option>
				<option value='queryByFirstName'><bean:message key="form.user.first.name" bundle="forms"/></option>
				<option value='queryByLastName'><bean:message key="form.user.last.name" bundle="forms"/></option>
				<option value='queryByPaperNumber'><bean:message key="form.paper.number" bundle="forms"/></option>
				<option value='queryByPaperTitle'><bean:message key="form.paper.title" bundle="forms"/></option>
				<option value='queryByPassportNumber'><bean:message key="form.passport.number" bundle="forms"/></option>
			</select>
			
			<bean:message key="form.search.input" bundle="forms"/>
			<input type='text' id='searchKey' name='searchKey' size='20'></input>
			<html:submit>
				<bean:message key="form.button.search" bundle="forms"/>
			</html:submit>
		</td>
	</tr>
	<tr valign='center' align='center' width='100%'>
		<td width='100%'>
		<p class="errMsg">
			<html:errors bundle="errors"/>
		</td>
	</tr>
</table>
</html:form>
</body>
</html>