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
	
	<html:form action="visa" >
	<table width='100%' cellspacing='0' cellpadding='0' border='0'>
		<tr valign='center' align='center' width='100%'>
			<td width='100%'>
			<h2>
				<logic:notEmpty property="visa.chrVisaNo" name="visaForm">
					<bean:message key="visa.manager.title"/>
				</logic:notEmpty>
				<logic:empty property="visa.chrVisaNo" name="visaForm">
					<bean:message key="visa.add.title"/>
				</logic:empty>
			</h2>
				<logic:empty property="visa.chrVisaNo" name="visaForm">
					<html:hidden property="action" value="createVisa" />
				</logic:empty>
				<logic:notEmpty property="visa.chrVisaNo" name="visaForm">
					<html:hidden property="action" value="updateVisa" />
				</logic:notEmpty>
				<html:hidden property="visa.chrVisaNo" />
				<html:hidden property="visa.chrPartiNo" />
				<html:hidden property="visa.chrAccPersonNo" />
			</td>
		</tr>
		<tr valign='center' align='center' width='100%'>
			<td width='100%'>
			<p class="errMsg">
				<html:errors property="visaError"/>
			</td>
		</tr>
		<tr valign='center' align='center' width='100%'>
			<td width='100%' nowrap='true'>
				<table width='100%' cellspacing='0' cellpadding='3' border='0' class='table4'>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.user.title" bundle="forms"/>
							<html:text property="visa.title" size="5" readonly="true"></html:text>
						</td>
						<td class='td4' nowrap='true'> 
							<bean:message key="form.user.name" bundle="forms"/>
							<html:text property="visa.userName" size="30" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.user.passort.number" bundle="forms"/>
							<html:text property="visa.passportNumber" size="20" readonly="true"></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.user.country" bundle="forms"/>
				            <html:select property="visa.chvVisaNationality" size="1">
				              <html:optionsCollection name="visaForm" property="countries" />
				            </html:select>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.visa.birth.place" bundle="forms"/>
							<html:text property="visa.chvBirthPlace" size="20"></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.visa.birth.date" bundle="forms"/>
							<html:text property="visa.dtmBirthDate" size="20"></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.visa.arrival.date" bundle="forms"/>
							<html:text property="visa.dtmArrivalDate" size="20"></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.visa.departure.date" bundle="forms"/>
							<html:text property="visa.dtmDepartureDate" size="20"></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.visa.visaPlace" bundle="forms"/>
							<html:text property="visa.chvVisaPlace" size="20"></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.visa.is.need.org" bundle="forms"/>
							<html:radio property="visa.bitIsNeedOrgVisa" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="visa.bitIsNeedOrgVisa" value="false" >
								<bean:message key="form.radio.no" bundle="forms"/>
							</html:radio>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.visa.post.org.date" bundle="forms"/>
							<html:text property="visa.dtmVisa" size="20"></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.visa.is.ok" bundle="forms"/>
							<html:radio property="visa.bitIsVisaOk" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="visa.bitIsVisaOk" value="false" >
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
					<logic:empty property="visa.chrVisaNo" name="visaForm">
						<bean:message key="form.button.add" bundle="forms"/>
					</logic:empty>
					<logic:notEmpty property="visa.chrVisaNo" name="visaForm">
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