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
	
	<html:form action="adminUser" >
	<table width='100%' cellspacing='0' cellpadding='0' border='0'>
		<tr valign='center' align='center' width='100%'>
			<td width='100%'>
			<h2>
				<logic:notEmpty property="id" name="userForm">
					<bean:message key="user.modify"/>
				</logic:notEmpty>
				<logic:empty property="id" name="userForm">
					<bean:message key="user.add"/>
				</logic:empty>
			</h2>
				<logic:empty property="id" name="userForm">
					<html:hidden property="action" value="add" />
				</logic:empty>
				<logic:notEmpty property="id" name="userForm">
					<html:hidden property="action" value="update" />
				</logic:notEmpty>
				<html:hidden property="id" />
			</td>
		</tr>
		<tr valign='center' align='center' width='100%'>
			<td width='100%'>
			<p class="errMsg">
				<html:errors property="userError"/>
			</td>
		</tr>
		<tr valign='center' align='center' width='100%'>
			<td width='100%' nowrap='true'>
				<table width='100%' cellspacing='0' cellpadding='3' border='0' class='table4'>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.user.id" bundle="forms"/>
							<html:text property="userid" size="30" ></html:text>
						</td>
						<td class='td4' nowrap='true'> 
							<bean:message key="form.user.name" bundle="forms"/>
							<html:text property="userName" size="30" ></html:text>
						</td>
					</tr>
					<logic:empty property="id" name="userForm">
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.user.pass" bundle="forms"/>
				            <html:password property="userPass" size="31"></html:password>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.user.repass" bundle="forms"/>
				            <html:password property="rePass" size="31"></html:password>
						</td>
					</tr>
					</logic:empty>
					<tr>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.is.manager" bundle="forms"/>
							<html:radio property="manager" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="manager" value="false" >
								<bean:message key="form.radio.no" bundle="forms"/>
							</html:radio>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr valign='center' align='center' width='100%'>
			<td width='100%'>
				<html:submit>
					<logic:empty property="id" name="userForm">
						<bean:message key="form.button.add" bundle="forms"/>
					</logic:empty>
					<logic:notEmpty property="id" name="userForm">
						<bean:message key="form.button.update" bundle="forms"/>
					</logic:notEmpty>
				</html:submit>
				<html:reset >
					<bean:message key="form.button.reset" bundle="forms"/>
				</html:reset>
				<html:cancel >
					<bean:message key="form.button.cancel" bundle="forms"/>
				</html:cancel>
		</tr>
	</table>
	</html:form>
	</body>
</html>