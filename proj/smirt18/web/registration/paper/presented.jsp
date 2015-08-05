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
	<script language='javascript'>
		function queryUser() {
			document.presentedForm.action.value="queryUserByFirstName";
			document.presentedForm.submit();
		}
	</script>
	<body>
	
	<html:form action="presented" >
	<table width='100%' cellspacing='0' cellpadding='0' border='0'>
		<tr valign='center' align='center' width='100%'>
			<td width='100%'>
			<h2>
				<logic:notEmpty property="tableKey" name="presentedForm">
					<bean:message key="paper.presented.modify.title"/>
				</logic:notEmpty>
				<logic:empty property="tableKey" name="presentedForm">
					<bean:message key="paper.presented.add.title"/>
				</logic:empty>
			</h2>
			</td>
		</tr>
		<tr>
			<td>
				<bean:message key="form.paper.number" bundle="forms"/>:&nbsp;
				<b><i>
				<bean:write name="presentedForm" property="paperNumber"/>&nbsp;
				</b></i>&nbsp;
				<bean:message key="form.paper.title" bundle="forms"/>:&nbsp;
				<b><i>
				<bean:write name="presentedForm" property="paperTitle"/>&nbsp;
				</b></i>&nbsp;
				<hr>
		
				<logic:empty property="tableKey" name="presentedForm">
					<html:hidden property="action" value="createPresented" />
				</logic:empty>
				<logic:notEmpty property="tableKey" name="presentedForm">
					<html:hidden property="action" value="updatePresented" />
				</logic:notEmpty>
				<html:hidden property="paperTitle"/>
				<html:hidden property="paperNumber"/>
				<html:hidden property="tableKey" />
				<html:hidden property="presented.chvPaperNumber" />
				<html:hidden property="presented.chvPaperLNumber" />
			</td>
		</tr>
		<tr valign='center' align='center' width='100%'>
			<td width='100%'>
			<p class="errMsg">
				<html:errors property="presentedError"/>
			</td>
		</tr>
		<tr valign='center' align='center' width='100%'>
			<td width='100%' nowrap='true'>
				<table width='100%' cellspacing='0' cellpadding='3' border='0' class='table4'>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.presented.presenter.parti.no" bundle="forms"/>
							<html:text property="presented.chrPresenterPartiNo" size="15" readonly="true"></html:text>
						</td>
						<td class='td4' nowrap='true'> 
							<bean:message key="form.paper.presented.presenter.first.name" bundle="forms"/>
							<html:text property="presented.chvPresenterFirstName" size="30" ></html:text>
							<input type='button' value='<bean:message key="form.button.find.user" bundle="forms"/>' onclick='javascript:queryUser()'>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.presented.presenter.middle.name" bundle="forms"/>
							<html:text property="presented.chvPresenterMiddleName" size="15" ></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.presented.presenter.last.name" bundle="forms"/>
							<html:text property="presented.chvPresenterLastName" size="30" ></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.presented.personal.book" bundle="forms"/>
							<html:radio property="presented.bitIsPersonalBook" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="presented.bitIsPersonalBook" value="false" >
								<bean:message key="form.radio.no" bundle="forms"/>
							</html:radio>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.presented.equipment" bundle="forms"/>
				            <html:select property="presented.chrEquipmentNo" size="1">
				              <html:optionsCollection name="presentedForm" property="equipments" />
				            </html:select>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.presented.audio.needs" bundle="forms"/>
							<html:text property="presented.chvAudioNeeds" size="30" ></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.presented.special" bundle="forms"/>
							<html:text property="presented.chvSpecial" size="30" ></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.paper.presented.is.submit.demo.file" bundle="forms"/>
							<html:radio property="presented.bitIsSubmitDemoFile" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="presented.bitIsSubmitDemoFile" value="false" >
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
					<logic:empty property="tableKey" name="presentedForm">
						<bean:message key="form.button.add" bundle="forms"/>
					</logic:empty>
					<logic:notEmpty property="tableKey" name="presentedForm">
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