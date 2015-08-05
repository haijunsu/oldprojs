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
			
			<logic:present name="visas">
				<table width='100%' cellpadding='1' cellspacing='1' border='0'>
					<tr>
						<td align='center'>
						<h2>
						<bean:message key="visa.list"/>
						</h2>
						</td>
					</tr>
					<tr>
						<td>
						<table width='100%' cellpadding='0' cellspacing='0' border='0' class='table4'>
							<tr>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.result.serial" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.user.title" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.user.name" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.user.passort.number" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.user.country" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									&nbsp;
								</th>
							</tr>
							<logic:iterate id="visa" name="visas" indexId="index">
							<tr>
								<td class='td4' align='center'>
								<%=index.intValue() + 1%>
								</td>
								<td class='td4'>
								<bean:write name="visa" property="title" />&nbsp;
								</td>
								<td class='td4'>
								<bean:write name="visa" property="userName" />&nbsp;
								</td>
								<td class='td4'>
								<bean:write name="visa" property="passportNumber" />&nbsp;
								</td>
								<td class='td4' align='center'>
								<bean:write name="visa" property="countryName" />&nbsp;
								</td>
								<td class='td4' align='center'>
								<logic:notEmpty property="chrVisaNo" name="visa">
									<bean:write name="visa" property="updateButton" filter="false" />
									&nbsp;|&nbsp;
									<bean:write name="visa" property="removeButton" filter="false" />
								</logic:notEmpty>
								<logic:empty property="chrVisaNo" name="visa">
									<bean:write name="visa" property="addButton" filter="false" />
								</logic:empty>
								</td>
								
							</tr>	
							</logic:iterate>
						</table>
						</td>
					</tr>
				</table>
			</logic:present>
		</td>
	</tr>
	<tr align='center'>
	<td colspan='2'>
		<logic:present name="backBtn">
			<bean:write name="backBtn" property="button" filter="false" />
		</logic:present>
	</td>
	</tr>
</table>
</body>
</html>