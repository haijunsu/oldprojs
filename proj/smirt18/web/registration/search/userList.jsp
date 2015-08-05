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
			
			<logic:present name="smallUserView">
				<table cellpadding='1' cellspacing='1' border='0'>
					<tr>
						<td align='center'>
						<bean:message key="search.list.title"/>
						</td>
					</tr>
					<tr>
						<td>
						<table cellpadding='0' cellspacing='0' border='0' class='table4'>
							<tr>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.result.serial" bundle="forms"/>
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
							<logic:iterate id="userView" name="smallUserView" indexId="index">
							<tr class=<bean:write name="userView" property="styleName"/>>
								<td class='td4' align='center'>
								<a href='<bean:write name="rq" property="contextPath"/>/register.do?<bean:write name="userView" property="urlParams" />'>
								<%=index.intValue() + 1%>
								</a>
								</td>
								<td class='td4'>
								<a href='<bean:write name="rq" property="contextPath"/>/register.do?<bean:write name="userView" property="urlParams" />'>
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
</body>
</html>