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
	
	<html:form action="accompany" >
	<table width='100%' cellspacing='0' cellpadding='0' border='0'>
		<tr valign='center' align='center' width='100%'>
			<td width='100%'>
			<h2>
				<logic:empty property="person.chrAccPersonNo" name="accompanyForm">
					<bean:message key="accompany.person.add.title"/>
				</logic:empty>
				<logic:notEmpty property="person.chrAccPersonNo" name="accompanyForm">
					<bean:message key="accompany.person.modify.title"/>
				</logic:notEmpty>
			</h2>
				<logic:empty property="person.chrAccPersonNo" name="accompanyForm">
					<html:hidden property="action" value="createAccompanyPerson" />
				</logic:empty>
				<logic:notEmpty property="person.chrAccPersonNo" name="accompanyForm">
					<html:hidden property="action" value="updateAccompanyPerson" />
				</logic:notEmpty>
				<html:hidden property="person.chrAccPersonNo" />
				<html:hidden property="person.chrPartiNo" />
			</td>
		</tr>
		<tr valign='center' align='center' width='100%'>
			<td width='100%'>
			<p class="errMsg">
				<html:errors property="accompanyError"/>
			</td>
		</tr>
		<tr valign='center' align='center' width='100%'>
			<td width='100%' nowrap='true'>
				<table width='100%' cellspacing='0' cellpadding='3' border='0' class='table4'>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.user.title" bundle="forms"/>
							<html:text property="person.chvAccPersonTitle" size="5"></html:text>
						</td>
						<td class='td4' nowrap='true'> 
							<bean:message key="form.user.first.name" bundle="forms"/>
							<html:text property="person.chvAccPersonFirstName" size="10"></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.user.middle.name" bundle="forms"/>
							<html:text property="person.chvAccPersonMiddleName" size="2"></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.user.last.name" bundle="forms"/>
							<html:text property="person.chvAccPersonLastName" size="10"></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.passort.number" bundle="forms"/>
							<html:text property="person.chvPassportNumber" size="30"></html:text>
						</td>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.is.attend.reception" bundle="forms"/>
							<html:radio property="person.bitIsAttendReception" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="person.bitIsAttendReception" value="false" >
								<bean:message key="form.radio.no" bundle="forms"/>
							</html:radio>

						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.is.attend.banquet" bundle="forms"/>
							<html:radio property="person.bitIsAttendBanquet" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="person.bitIsAttendBanquet" value="false" >
								<bean:message key="form.radio.no" bundle="forms"/>
							</html:radio>
						</td>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.is.attend.tech.tour1" bundle="forms"/>
							<html:radio property="person.bitIsAttendTechTour" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="person.bitIsAttendTechTour" value="false" >
								<bean:message key="form.radio.no" bundle="forms"/>
							</html:radio>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' colspan='4'>
							<bean:message key="form.user.is.attend.tech.tour2" bundle="forms"/>
							<html:radio property="person.bitIsCheckin" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="person.bitIsCheckin" value="false" >
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
					<logic:empty property="person.chrAccPersonNo" name="accompanyForm">
						<bean:message key="form.button.add" bundle="forms"/>
					</logic:empty>
					<logic:notEmpty property="person.chrAccPersonNo" name="accompanyForm">
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