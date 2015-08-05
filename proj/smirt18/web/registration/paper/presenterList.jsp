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
	<Script language='javascript'>
		function selectme(partino, firstname, middlename, lastname) {
			document.presentedForm.partiNo.value = partino;
			document.presentedForm.firstName.value = firstname;
			document.presentedForm.middleName.value = middlename;
			document.presentedForm.lastName.value = lastname;
			document.presentedForm.submit();
		}
	</script>
	<body>
<table width='100%' cellspacing='0' cellpadding='0' border='0'>
	<tr valign='center' align='center' width='100%'>
		<td width='10'>
		&nbsp;&nbsp;
		</td>
		<td width='100%'>
			<!-- search rusult -->
			
			<logic:present name="users">
				<table cellpadding='1' cellspacing='1' border='0'>
					<tr>
						<td align='center'>
						<bean:message key="user.list"/>
						</td>
					</tr>
					<tr>
						<td>
						<table cellpadding='0' cellspacing='0' border='0' class='table4'>
							<tr>
								<th align='center' nowrap='true' class='td4'>
									&nbsp;
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.user.name" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.user.email" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.user.company" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.user.address" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.user.country" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.user.isRegistered" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.user.is.biography" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.user.is.granted" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.user.is.mail.author" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.user.is.checkin" bundle="forms"/>
								</th>
							</tr>
							<logic:iterate id="userView" name="users" indexId="index">
							<tr>
								<td class='td4' align='center'>
								<input type='button' value='<bean:message key="form.button.select" bundle="forms"/>' onclick='javascript:selectme("<bean:write name="userView" property="chrPartiNo"/>", "<bean:write name="userView" property="chvPartiFirstName"/>", "<bean:write name="userView" property="chvPartiMiddleName"/>", "<bean:write name="userView" property="chvPartiLastName"/>")'></input>
								</a>
								</td>
								<td class='td4'>
								<a href='<bean:write name="rq" property="contextPath"/>/register.do?<bean:write name="userView" property="urlParams" />' target=_user>
								<bean:write name="userView" property="userName" /></a>&nbsp;
								</td>
								<td class='td4'>
								<bean:write name="userView" property="chvPartiEmail" />&nbsp;
								</td>
								<td class='td4'>
								<bean:write name="userView" property="chvPartiAffiliation" />&nbsp;
								</td>
								<td class='td4'>
								<bean:write name="userView" property="chvPartiAddress" />&nbsp;
								</td>
								<td class='td4'>
								<bean:write name="userView" property="countryName" />&nbsp;
								</td>
								<td class='td4' align='center'>
								<logic:equal value="true" name="userView" property="registered">
									<bean:message key="form.radio.yes" bundle="forms"/>
								</logic:equal>
								<logic:equal value="false" name="userView" property="registered">
									<font color='red'>
									<bean:message key="form.radio.no" bundle="forms"/>
									</font>
								</logic:equal>
								</td>
								<td class='td4' align='center'>
								<logic:equal value="true" name="userView" property="bitIsBiography">
									<bean:message key="form.radio.yes" bundle="forms"/>
								</logic:equal>
								<logic:equal value="false" name="userView" property="bitIsBiography">
									<font color='red'>
									<bean:message key="form.radio.no" bundle="forms"/>
									</font>
								</logic:equal>
								</td>
								<td class='td4' align='center'>
								<logic:equal value="true" name="userView" property="granted">
									<bean:message key="form.radio.yes" bundle="forms"/>
								</logic:equal>
								<logic:equal value="false" name="userView" property="granted">
									<font color='red'>
									<bean:message key="form.radio.no" bundle="forms"/>
									</font>
								</logic:equal>
								</td>
								<td class='td4' align='center'>
								<logic:equal value="true" name="userView" property="mailAuthor">
									<bean:message key="form.radio.yes" bundle="forms"/>
								</logic:equal>
								<logic:equal value="false" name="userView" property="mailAuthor">
									<font color='red'>
									<bean:message key="form.radio.no" bundle="forms"/>
									</font>
								</logic:equal>
								</td>
								<td class='td4' align='center'>
								<logic:equal value="true" name="userView" property="bitIsCheckin">
									<bean:message key="form.radio.yes" bundle="forms"/>
								</logic:equal>
								<logic:equal value="false" name="userView" property="bitIsCheckin">
									<font color='red'>
									<bean:message key="form.radio.no" bundle="forms"/>
									</font>
								</logic:equal>
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
</table>
<center>
<logic:notPresent name="users">
<p class="errMsg">
	<bean:message key="errors.code.130"/>

</logic:notPresent>

<html:form action="presented" >
<html:hidden property="action" value="queryUserByFirstName" />
<html:hidden property="paperTitle"/>
<html:hidden property="paperNumber"/>

<html:hidden property="partiNo"/>
<html:hidden property="firstName"/>
<html:hidden property="middleName"/>
<html:hidden property="lastName"/>

<html:hidden property="presented.intIndex" />
<html:hidden property="presented.chvPaperNumber" />
<html:hidden property="presented.chvPaperLNumber" />
<html:hidden property="presented.chrPresenterPartiNo" />
<html:hidden property="presented.chvPresenterFirstName" />
<html:hidden property="presented.chvPresenterMiddleName" />
<html:hidden property="presented.chvPresenterLastName" />
<html:hidden property="presented.bitIsPersonalBook" />
<html:hidden property="presented.chrEquipmentNo" />
<html:hidden property="presented.chvAudioNeeds" />
<html:hidden property="presented.chvSpecial" />
<html:hidden property="presented.bitIsSubmitDemoFile" />

<html:cancel ><bean:message key="form.button.cancel" bundle="forms"/></html:cancel>
</html:form>
</center>
</body>
</html>