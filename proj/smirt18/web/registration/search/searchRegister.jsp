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
	<html:form action="search" >
	<table width='100%' cellspacing='0' cellpadding='0' border='0'>
		<tr valign='center' align='center' width='100%'>
			<td width='100%'>
				<bean:message key="form.search.type" bundle="forms"/>
				<html:select property="action" size="1">
					<html:option value="queryRegisterByEmail" ><bean:message key="form.user.email" bundle="forms"/></html:option>
					<html:option value="queryRegisterByFirstName"><bean:message key="form.user.first.name" bundle="forms"/></html:option>
					<html:option value="queryRegisterByLastName" ><bean:message key="form.user.last.name" bundle="forms"/></html:option>
				</html:select>
				
				<bean:message key="form.search.register.input" bundle="forms"/>
				<html:text property="searchKey" size="20"></html:text>
				<html:submit>
					<bean:message key="form.button.search" bundle="forms"/>
				</html:submit>
			</td>
		</tr>
		<tr valign='center' align='center' width='100%'>
			<td width='100%'>
			<p class="errMsg">
				<html:errors/>
			</td>
		</tr>
	</table>
	</html:form>
	</body>
</html>