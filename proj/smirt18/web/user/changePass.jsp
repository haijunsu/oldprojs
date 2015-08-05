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
	
	<html:form action="changePass" >
	<table width='100%' cellspacing='0' cellpadding='0' border='0'>
		<tr valign='center' align='center' width='100%'>
			<td width='100%'>
			<h2>
				<bean:message key="user.change.password"/>
			</h2>
				<html:hidden property="action" value="updatePass" />
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
						<td class='td4' nowrap='true' align='right'>
							<bean:message key="form.user.id" bundle="forms"/>
						</td>
						<td class='td4' nowrap='true'> 
							<html:text property="userid" size="29" readonly="true" value='<%=request.getRemoteUser()%>'></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' align='right'>
							<bean:message key="form.user.pass" bundle="forms"/>
						</td>
						<td class='td4' nowrap='true'> 
							<div class="errMsg"><html:password property="userPass" size="30"></html:password><html:errors property="passError"/></div>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' align='right'>
							<bean:message key="form.user.new.pass" bundle="forms"/>
						</td>
						<td class='td4' nowrap='true'> 
							<div class="errMsg"><html:password property="newPass" size="30"></html:password><html:errors property="matchError"/></div>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' align='right'>
							<bean:message key="form.user.repass" bundle="forms"/>
						</td>
						<td class='td4' nowrap='true'> 
							<html:password property="rePass" size="30"></html:password>
						</td>
					</tr>

				</table>
			</td>
		</tr>
		<tr valign='center' align='center' width='100%'>
			<td width='100%'>
				<html:submit>
					<bean:message key="form.button.modify" bundle="forms"/>
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