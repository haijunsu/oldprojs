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
<table width='100%' cellspacing='0' cellpadding='0' border='0'>
	<tr valign='center' align='center' width='100%'>
		<td width='10'>
		&nbsp;&nbsp;
		</td>
		<td width='100%'>
			<!-- search rusult -->
			
				<table width='100%' cellpadding='1' cellspacing='1' border='0'>
					<tr>
						<td align='center'>
						<h2>
						<bean:message key="user.list"/>
						</h2>
						</td>
					</tr>
					<tr>
						<td>
						<logic:present name="smirtUsers">
						<table width='100%' cellpadding='0' cellspacing='0' border='0' class='table4'>
							<tr>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.result.serial" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.user.id" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.user.name" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.user.is.manager" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									&nbsp;
								</th>
							</tr>
							<logic:iterate id="smirtuser" name="smirtUsers" indexId="index">
							<tr>
								<td class='td4' align='center'>
								<%=index.intValue() + 1%>
								</td>
								<td class='td4'>
								<bean:write name="smirtuser" property="userid" />&nbsp;
								</td>
								<td class='td4' nowrap>
								<bean:write name="smirtuser" property="userName" />&nbsp;
								</td>
								<td class='td4' align='center'>
								<logic:equal value="true" name="smirtuser" property="manager">
									<bean:message key="form.radio.yes" bundle="forms"/>
								</logic:equal>
								<logic:equal value="false" name="smirtuser" property="manager">
									<bean:message key="form.radio.no" bundle="forms"/>
								</logic:equal>
								</td>
								<td class='td4' align='center'>
									<a href='<bean:write name="rq" property="contextPath"/>/adminUser.do?action=load&id=<bean:write name="smirtuser" property="id"/>'>
									<bean:message key="form.button.modify" bundle="forms"/></a>
									&nbsp;|&nbsp;
									<a href='<bean:write name="rq" property="contextPath"/>/adminUser.do?action=remove&id=<bean:write name="smirtuser" property="id"/>'>
									<bean:message key="form.button.remove" bundle="forms"/></a>
								</td>
								
							</tr>	
							</logic:iterate>
						</table>
						</logic:present>
						</td>
					</tr>
					<tr>
					<td align='center' colspan='5'>
						<a href='<bean:write name="rq" property="contextPath"/>/newUser.do'>
						<bean:message key="form.button.add" bundle="forms"/></a>
					</td>
					</tr>
				</table>
		</td>
	</tr>
</table>
</body>
</html>