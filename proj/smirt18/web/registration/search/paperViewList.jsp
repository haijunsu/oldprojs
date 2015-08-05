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
			
			<logic:present name="paperView">
				<table cellpadding='1' cellspacing='1' border='0' width='100%'>
					<tr>
						<td align='center'>
						<h2>
						<bean:message key="paper.title"/>
						</h2>
						</td>
					</tr>
					<tr>
						<td align='left' nowrap='true'>
						<bean:message key="form.paper.last.number" bundle="forms"/>:&nbsp;
						<b><i><bean:write name="paperView" property="smallPaperView.chvPaperLNumber" /></i></b>&nbsp;&nbsp;|&nbsp;&nbsp;
						<bean:message key="form.paper.title" bundle="forms"/>:&nbsp;
						<b><i><bean:write name="paperView" property="smallPaperView.chvPaperTitle" /></i></b>
						</td>
					</tr>
					<tr>
						<td>
						<form method='post' action='<bean:write name="rq" property="contextPath"/>/updatePaperReceivedDetail.do'>
							<input type='hidden' id='action' name='action' value='update'></input>
							<input type='hidden' id='chvPaperNumber' name='chvPaperNumber' value='<bean:write name="paperView" property="smallPaperView.chvPaperNumber" />'></input>

						<table cellpadding='0' cellspacing='0' border='0' class='table4' width='100%'>
							<tr>
								<td nowrap='true' class='td4' width='25%'>
									<b>
									<bean:message key="form.paper.junior.award" bundle="forms"/>
									</b>
									</td><td nowrap='true' class='td4' width='25%'>
									<i>
									<logic:equal value="true" name="paperView" property="smallPaperView.bitIsJuniorAward">
										<bean:message key="form.radio.yes" bundle="forms"/>
									</logic:equal>
									<logic:equal value="false" name="paperView" property="smallPaperView.bitIsJuniorAward">
										<bean:message key="form.radio.no" bundle="forms"/>
									</logic:equal>
									</i>
								</td>
								<td nowrap='true' class='td4' width='25%'>
									<b>
									<bean:message key="form.paper.prize" bundle="forms"/>
									</b>
									</td><td nowrap='true' class='td4' width='25%'>
									<i>
									<logic:equal value="true" name="paperView" property="smallPaperView.bitIsPaperPrize">
										<bean:message key="form.radio.yes" bundle="forms"/>
									</logic:equal>
									<logic:equal value="false" name="paperView" property="smallPaperView.bitIsPaperPrize">
										<bean:message key="form.radio.no" bundle="forms"/>
									</logic:equal>
									</i>
								</td>
							</tr>
							<tr>
								<td nowrap='true' class='td4'>
									<b>
									<bean:message key="form.paper.status" bundle="forms"/>
									</b>
									</td><td nowrap='true' class='td4' width='25%'>
									<i>
									<bean:write name="paperView" property="smallPaperView.statusDescription" />&nbsp;
									</i>
								</td>
								<td nowrap='true' class='td4'>
									<b>
									<bean:message key="form.paper.registered" bundle="forms"/>
									</b>
									</td><td nowrap='true' class='td4' width='25%'>
									<i>
								<logic:equal value="true" name="paperView" property="smallPaperView.bitIsRegisted">
									<bean:message key="form.radio.yes" bundle="forms"/>
								</logic:equal>
								<logic:equal value="false" name="paperView" property="smallPaperView.bitIsRegisted">
									<font color='red'>
									<bean:message key="form.radio.no" bundle="forms"/>
									</font>
								</logic:equal>
									</i>
								</td>
							</tr>
							<tr>
								<td nowrap='true' class='td4'>
									<b>
									<bean:message key="form.paper.mail.paper.reg" bundle="forms"/>
									</b>
									</td><td nowrap='true' class='td4' width='25%'>
									<i>
									<logic:equal value="true" name="paperView" property="smallPaperView.bitIsMailPaperReg">
										<bean:message key="form.radio.yes" bundle="forms"/>
									</logic:equal>
									<logic:equal value="false" name="paperView" property="smallPaperView.bitIsMailPaperReg">
									<font color='red'>
									<bean:message key="form.radio.no" bundle="forms"/>
									</font>
									</logic:equal>
									</i>
								</td>
								<td nowrap='true' class='td4'>
									<b>
									<bean:message key="form.paper.mail.copy.right" bundle="forms"/>
									</b>
									</td><td nowrap='true' class='td4' width='25%'>
									<html:radio property="smallPaperView.bitIsMailCopyright" name="paperView" value="true" >
										<bean:message key="form.radio.yes" bundle="forms"/>
									</html:radio>
									<html:radio property="smallPaperView.bitIsMailCopyright" name="paperView" value="false" >
										<bean:message key="form.radio.no" bundle="forms"/>
									</html:radio>
								</td>
							</tr>
							<tr>
								<td nowrap='true' class='td4'>
									<b>
									<bean:message key="form.paper.mail.org.abstract" bundle="forms"/>
									</b>
									</td><td nowrap='true' class='td4' width='25%'>
									<html:radio property="smallPaperView.bitIsMailOrgAbstract" name="paperView" value="true" >
										<bean:message key="form.radio.yes" bundle="forms"/>
									</html:radio>
									<html:radio property="smallPaperView.bitIsMailOrgAbstract" name="paperView" value="false" >
										<bean:message key="form.radio.no" bundle="forms"/>
									</html:radio>
								</td>
								<td nowrap='true' class='td4'>
									<b>
									<bean:message key="form.paper.mail.org.paper" bundle="forms"/>
									</b>
									</td><td nowrap='true' class='td4' width='25%'>
									<html:radio property="smallPaperView.bitIsMailOrgPaper" name="paperView" value="true" >
										<bean:message key="form.radio.yes" bundle="forms"/>
									</html:radio>
									<html:radio property="smallPaperView.bitIsMailOrgPaper" name="paperView" value="false" >
										<bean:message key="form.radio.no" bundle="forms"/>
									</html:radio>
								</td>
							</tr>
							<tr>
								<td align='center' class='td4' colspan='4'>
			   <html:submit>
					<bean:message key="form.button.update" bundle="forms"/>
				</html:submit>
				<html:reset >
					<bean:message key="form.button.reset" bundle="forms"/>
				</html:reset>
								</td>
							</tr>
						</table>
						</form>
	<table width='100%' cellspacing='0' cellpadding='0' border='0'>
		<tr>
			<td align='center'>
				<a href='<bean:write name="rq" property="contextPath"/>/presented.do?action=queryPresentedByPaperNumber&paperLNumber=<bean:write name="paperView" property="smallPaperView.chvPaperLNumber" />&paperNumber=<bean:write name="paperView" property="smallPaperView.chvPaperNumber" />'>
					<bean:message key="paper.presented.manager"/></a>&nbsp;|&nbsp;
				<a href='<bean:write name="rq" property="contextPath"/>/paperFee.do?action=queryPaperFeeByPaperNumber&paperLNumber=<bean:write name="paperView" property="smallPaperView.chvPaperLNumber" />&paperNumber=<bean:write name="paperView" property="smallPaperView.chvPaperNumber" />'>
					<bean:message key="paper.registration.fee"/></a>
				<hr />
			</td>
		</tr>
	</table>
			<logic:present name="paperView" property="authors">
				<table cellpadding='1' cellspacing='1' border='0' width='100%'>
					<tr>
						<td align='center'>
						<bean:message key="author.list"/>
						</td>
					</tr>
					<tr>
						<td>
						<table cellpadding='0' cellspacing='0' border='0' class='table4' width='100%'>
							<tr>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.user.author.rank" bundle="forms"/>
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
							<logic:iterate id="userView" name="paperView" property="authors" indexId="index">
							<tr class=<bean:write name="userView" property="styleName"/>>
								<td class='td4' align='center'>
								<bean:write name="userView" property="inyAuthorRank" />&nbsp;
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
			<logic:present name="paperView" property="paperPresented">
				<table cellpadding='1' cellspacing='1' border='0' width='100%'>
					<tr>
						<td align='center'>
						<bean:message key="paper.presented.title"/>
						</td>
					</tr>
					<tr>
						<td>
						<table cellpadding='0' cellspacing='0' border='0' class='table4' width='100%'>
							<tr>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.paper.presented.presenter.name" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.user.is.biography" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.user.is.checkin" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.paper.presented.equipment" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.paper.presented.is.submit.demo.file" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.paper.presented.special" bundle="forms"/>
								</th>
							</tr>
							<tr>
								<td class='td4' align='center'>&nbsp;
								<a href='<bean:write name="rq" property="contextPath"/>/register.do?action=loadUserByPartiNo&partiNo=<bean:write name="paperView" property="paperPresented.chrPresenterPartiNo" />'>
								<bean:write name="paperView" property="paperPresentedName" /></a>
								</td>
								<td class='td4' align='center'>&nbsp;
								<logic:equal value="true" name="paperView" property="bitIsBiography">
									<bean:message key="form.radio.yes" bundle="forms"/>
								</logic:equal>
								<logic:equal value="false" name="paperView" property="bitIsBiography">
									<font color='red'>
									<bean:message key="form.radio.no" bundle="forms"/>
									</font>
								</logic:equal>
								</td>
								<td class='td4' align='center'>&nbsp;
								<logic:equal value="true" name="paperView" property="bitIsCheckin">
									<bean:message key="form.radio.yes" bundle="forms"/>
								</logic:equal>
								<logic:equal value="false" name="paperView" property="bitIsCheckin">
									<font color='red'>
									<bean:message key="form.radio.no" bundle="forms"/>
									</font>
								</logic:equal>
								</td>
								<td class='td4' align='center'>
								<bean:write name="paperView" property="equipementName" />&nbsp;
								</td>
								<td class='td4' align='center'>&nbsp;
								<logic:equal value="true" name="paperView" property="paperPresented.bitIsSubmitDemoFile">
									<bean:message key="form.radio.yes" bundle="forms"/>
								</logic:equal>
								<logic:equal value="false" name="paperView" property="paperPresented.bitIsSubmitDemoFile">
									<bean:message key="form.radio.no" bundle="forms"/>
								</logic:equal>
								</td>
								<td class='td4'>
								<bean:write name="paperView" property="paperPresented.chvSpecial" />&nbsp;
								</td>
							</tr>	
						</table>
						</td>
					</tr>
				</table>
			</logic:present>
						</td>
					</tr>
				</table>
			</logic:present>
		</td>
	</tr>
</table>
</body>
</html>