<%@ page language="java" isErrorPage="false"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="/common/include.jsp"%>

<html:base />
<html:html>
<title><bean:message key="index.title" /></title>
<body>
<html:form action="/login">
	<html:hidden property="actionType" value="login" />
	<table width="98%">
		<tr>
			<td align="center" colspan="2">
			<h3><bean:message key="label.login.welcome" /></h3>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2"><html:errors /></td>
		</tr>
		<tr>
			<td width="50%" align="right">&nbsp;<bean:message key="label.login.userName" />:
			</td>
			<td width="50%"><html:text property="userid" style="width: 120"></html:text>
			</td>
		</tr>
		<tr>
			<td align="right">&nbsp;<bean:message key="label.login.password" />:
			</td>
			<td>
			<html:password property="password" style="width: 120"></html:password>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2"><html:submit>
				<bean:message key="button.login" />
			</html:submit></td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
