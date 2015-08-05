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
	
	<html:form action="register" >
	<table width='100%' cellspacing='0' cellpadding='0' border='0'>
		<tr valign='center' align='center' width='100%'>
			<td width='100%'>
			<h2>
				<logic:empty property="participant.chrPartiNo" name="registerForm">
					<bean:message key="register.add.title"/>
				</logic:empty>
				<logic:notEmpty property="participant.chrPartiNo" name="registerForm">
					<bean:message key="register.modify.title"/>
				</logic:notEmpty>
			</h2>
				<logic:empty property="participant.chrPartiNo" name="registerForm">
					<html:hidden property="action" value="createParticipant" />
				</logic:empty>
				<logic:notEmpty property="participant.chrPartiNo" name="registerForm">
					<html:hidden property="action" value="updateParticipant" />
				</logic:notEmpty>
				<html:hidden property="participant.chrPartiNo" />
				<logic:notEmpty property="participant.chrAuthorNo" name="registerForm">
					<html:hidden property="hidden" property="participant.chrAuthorNo" name="registerForm" />
				</logic:notEmpty>
			</td>
		</tr>
		<tr valign='center' align='center' width='100%'>
			<td width='100%'>
			<p class="errMsg">
				<html:errors property="registerError"/>
			</td>
		</tr>
		<tr valign='center' align='center' width='100%'>
			<td width='100%' nowrap='true'>
				<table width='100%' cellspacing='0' cellpadding='3' border='0' class='table4'>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.user.title" bundle="forms"/>
							<html:text property="participant.chvPartiTitle" size="5"></html:text>
						</td>
						<td class='td4' nowrap='true'> 
							<bean:message key="form.user.first.name" bundle="forms"/>
							<html:text property="participant.chvPartiFirstName" size="10"></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.user.middle.name" bundle="forms"/>
							<html:text property="participant.chvPartiMiddleName" size="2"></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.user.last.name" bundle="forms"/>
							<html:text property="participant.chvPartiLastName" size="10"></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.email" bundle="forms"/>
							<html:text property="participant.chvPartiEmail" size="30"></html:text>
						</td>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.position" bundle="forms"/>
							<html:text property="participant.chvPartiPosition" size="30"></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' colspan='4'>
							<bean:message key="form.user.department" bundle="forms"/>
							<html:text property="participant.chvPartiDepartment" size="100"></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' colspan='4'>
							<bean:message key="form.user.affiliation" bundle="forms"/>
							<html:text property="participant.chvPartiAffiliation" size="100"></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' colspan='4'>
							<bean:message key="form.user.address" bundle="forms"/>
							<html:text property="participant.chvPartiAddress" size="100"></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.city" bundle="forms"/>
							<html:text property="participant.chvPartiCity" size="30"></html:text>
						</td>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.province" bundle="forms"/>
							<html:text property="participant.chvPartiProvince" size="30"></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.country" bundle="forms"/>
				            <html:select property="participant.chvPartiCountry" size="1">
				              <html:optionsCollection name="registerForm" property="countries" />
				            </html:select>

							<!-- html:text property="participant.chvPartiCountry" size="30"><--/html:text -->
						</td>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.postal.code" bundle="forms"/>
							<html:text property="participant.chvPartiPostalCode" size="30"></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.tel" bundle="forms"/>
							<html:text property="participant.chvPartiTeleNumber" size="30"></html:text>
						</td>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.fax" bundle="forms"/>
							<html:text property="participant.chvPartiFax" size="30"></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.home.tel" bundle="forms"/>
							<html:text property="participant.chvPartiHomeTeleNumber" size="30"></html:text>
						</td>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.home.fax" bundle="forms"/>
							<html:text property="participant.chvPartiHomeFax" size="30"></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.home.email" bundle="forms"/>
							<html:text property="participant.chvPartiHomeEmail" size="30"></html:text>
						</td>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.passort.number" bundle="forms"/>
							<html:text property="participant.chvPassportNumber" size="30"></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.is.biography" bundle="forms"/>
							<html:radio property="participant.bitIsBiography" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="participant.bitIsBiography" value="false" >
								<bean:message key="form.radio.no" bundle="forms"/>
							</html:radio>

						</td>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.is.member" bundle="forms"/>
							<html:radio property="participant.bitIsPartiIsMember" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="participant.bitIsPartiIsMember" value="false" >
								<bean:message key="form.radio.no" bundle="forms"/>
							</html:radio>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.is.checkin" bundle="forms"/>
							<html:radio property="participant.bitIsCheckin" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="participant.bitIsCheckin" value="false" >
								<bean:message key="form.radio.no" bundle="forms"/>
							</html:radio>

						</td>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.checkin.time" bundle="forms"/>
							<html:text property="participant.dtmCkeckinTime" size="30"></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.is.need.visa" bundle="forms"/>
							<html:radio property="participant.bitIsNeedVisa" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="participant.bitIsNeedVisa" value="false" >
								<bean:message key="form.radio.no" bundle="forms"/>
							</html:radio>

						</td>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.is.drawn.file" bundle="forms"/>
							<html:radio property="participant.bitIsDrawnFile" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="participant.bitIsDrawnFile" value="false" >
								<bean:message key="form.radio.no" bundle="forms"/>
							</html:radio>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.is.attend.reception" bundle="forms"/>
							<html:radio property="participant.bitIsAttendReception" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="participant.bitIsAttendReception" value="false" >
								<bean:message key="form.radio.no" bundle="forms"/>
							</html:radio>

						</td>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.is.attend.banquet" bundle="forms"/>
							<html:radio property="participant.bitIsAttendBanquet" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="participant.bitIsAttendBanquet" value="false" >
								<bean:message key="form.radio.no" bundle="forms"/>
							</html:radio>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.is.attend.tech.tour" bundle="forms"/>
							<html:radio property="participant.bitIsAttendTechTour" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="participant.bitIsAttendTechTour" value="false" >
								<bean:message key="form.radio.no" bundle="forms"/>
							</html:radio>
						</td>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.user.is.informed" bundle="forms"/>
							<html:radio property="participant.bitIsInformed" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="participant.bitIsInformed" value="false" >
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
					<logic:empty property="participant.chrPartiNo" name="registerForm">
						<bean:message key="form.button.add" bundle="forms"/>
					</logic:empty>
					<logic:notEmpty property="participant.chrPartiNo" name="registerForm">
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
	<logic:notEmpty property="participant.chrPartiNo" name="registerForm">

	<table width='100%' cellspacing='0' cellpadding='0' border='0'>
		<tr>
			<td align='center'>
				<a href='<bean:write name="rq" property="contextPath"/>/accompany.do?action=newAccompanyPerson&partiNo=<bean:write name="registerForm" property="participant.chrPartiNo" />'>
					<bean:message key="accompany.person.add.title"/></a>&nbsp;|&nbsp;
				<a href='<bean:write name="rq" property="contextPath"/>/visa.do?action=queryVisaByParticipantNo&partiNo=<bean:write name="registerForm" property="participant.chrPartiNo" />'>
					<bean:message key="visa.manager.title"/></a>&nbsp;|&nbsp;
				<a href='<bean:write name="rq" property="contextPath"/>/hotel.do?action=queryHotelByParticipantNo&partiNo=<bean:write name="registerForm" property="participant.chrPartiNo" />'>
					<bean:message key="hotel.manager.title"/></a>&nbsp;|&nbsp;
				<a href='<bean:write name="rq" property="contextPath"/>/registerFee.do?action=queryRegisterFeeByParticipantNo&partiNo=<bean:write name="registerForm" property="participant.chrPartiNo" />'>
					<bean:message key="register.fee.manager.title"/></a>&nbsp;|&nbsp;
				<a href='<bean:write name="rq" property="contextPath"/>/registerFee.do?action=queryPresentedPaper&partiNo=<bean:write name="registerForm" property="participant.chrPartiNo" />&fee.chrPartiNo=<bean:write name="registerForm" property="participant.chrPartiNo" />'>
					Report papers list</a>
				<hr />
			</td>
		</tr>
	</table>
	</logic:notEmpty>
	<logic:notEmpty name="papers">
	<table width='100%' cellspacing='0' cellpadding='0' border='0'>
		<tr>
			<td align='center'>
				<bean:message key="paper.list"/>
			</td>
		</tr>
		<tr>
			<td align='center'>
						<table cellpadding='0' cellspacing='0' border='0' class='table4'>
							<tr>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.result.serial" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.paper.last.number" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.paper.title" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.paper.junior.award" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.paper.prize" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.paper.status" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.paper.mail.org.abstract" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.paper.mail.org.paper" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.paper.mail.copy.right" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.paper.mail.paper.reg" bundle="forms"/>
								</th>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.paper.registered" bundle="forms"/>
								</th>
							</tr>
							<logic:iterate id="paperView" name="papers" indexId="index">
							<tr>
								<td class='td4' align='center'>
								<a href='<bean:write name="rq" property="contextPath"/>/search.do?action=queryByPaperNumber&paperNumber=<bean:write name="paperView" property="chvPaperNumber" />' title='<bean:write name="paperView" property="txtAbstract" filter="true"/>'>
								<%=index.intValue() + 1%>
								</a>
								</td>
								<td class='td4'>
								<a href='<bean:write name="rq" property="contextPath"/>/search.do?action=queryByPaperNumber&paperNumber=<bean:write name="paperView" property="chvPaperNumber" />' title='<bean:write name="paperView" property="txtAbstract" filter="true"/>'>
								<bean:write name="paperView" property="chvPaperLNumber" /></a>&nbsp;
								</td>
								<td class='td4'>
								<a href='<bean:write name="rq" property="contextPath"/>/search.do?action=queryByPaperNumber&paperNumber=<bean:write name="paperView" property="chvPaperNumber" />' title='<bean:write name="paperView" property="txtAbstract" filter="true"/>'>
								<bean:write name="paperView" property="chvPaperTitle" /></a>&nbsp;
								</td>
								<td class='td4'>
								<logic:equal value="true" name="paperView" property="bitIsJuniorAward">
									<bean:message key="form.radio.yes" bundle="forms"/>
								</logic:equal>
								<logic:equal value="false" name="paperView" property="bitIsJuniorAward">
									<bean:message key="form.radio.no" bundle="forms"/>
								</logic:equal>
								</td>
								<td class='td4'>
								<logic:equal value="true" name="paperView" property="bitIsPaperPrize">
									<bean:message key="form.radio.yes" bundle="forms"/>
								</logic:equal>
								<logic:equal value="false" name="paperView" property="bitIsPaperPrize">
									<bean:message key="form.radio.no" bundle="forms"/>
								</logic:equal>
								</td>
								<td class='td4' align='center'>
								<bean:write name="paperView" property="statusDescription" />&nbsp;
								</td>
								<td class='td4' align='center'>
								<logic:equal value="true" name="paperView" property="bitIsMailOrgAbstract">
									<bean:message key="form.radio.yes" bundle="forms"/>
								</logic:equal>
								<logic:equal value="false" name="paperView" property="bitIsMailOrgAbstract">
									<font color='red'>
									<bean:message key="form.radio.no" bundle="forms"/>
									</font>
								</logic:equal>
								</td>

								<td class='td4' align='center'>
								<logic:equal value="true" name="paperView" property="bitIsMailOrgPaper">
									<bean:message key="form.radio.yes" bundle="forms"/>
								</logic:equal>
								<logic:equal value="false" name="paperView" property="bitIsMailOrgPaper">
									<font color='red'>
									<bean:message key="form.radio.no" bundle="forms"/>
									</font>
								</logic:equal>
								</td>

								<td class='td4' align='center'>
								<logic:equal value="true" name="paperView" property="bitIsMailCopyright">
									<bean:message key="form.radio.yes" bundle="forms"/>
								</logic:equal>
								<logic:equal value="false" name="paperView" property="bitIsMailCopyright">
									<font color='red'>
									<bean:message key="form.radio.no" bundle="forms"/>
									</font>
								</logic:equal>
								</td>

								<td class='td4' align='center'>
								<logic:equal value="true" name="paperView" property="bitIsMailPaperReg">
									<bean:message key="form.radio.yes" bundle="forms"/>
								</logic:equal>
								<logic:equal value="false" name="paperView" property="bitIsMailPaperReg">
									<font color='red'>
									<bean:message key="form.radio.no" bundle="forms"/>
									</font>
								</logic:equal>
								</td>


								<td class='td4' align='center'>
								<logic:equal value="true" name="paperView" property="bitIsRegisted">
									<bean:message key="form.radio.yes" bundle="forms"/>
								</logic:equal>
								<logic:equal value="false" name="paperView" property="bitIsRegisted">
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
	</logic:notEmpty>
	<logic:notEmpty name="accompanyPerson">
	<table width='100%' cellspacing='0' cellpadding='0' border='0'>
		<tr>
			<td align='center'>
				<bean:message key="accompany.person.list"/>
			</td>
		<tr>
			<td align='center'>
						<table cellpadding='0' cellspacing='0' border='0' class='table4' width='100%'>
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
									&nbsp;
								</th>
							</tr>
							<logic:iterate id="person" name="accompanyPerson" indexId="index">
							<tr>
								<td class='td4' align='center'>
								<%=index.intValue() + 1%>
								</td>
								<td class='td4' align='center'>
									&nbsp;<bean:write name="person" property="chvAccPersonTitle" />
								</td>
								<td class='td4' align='center'>
									&nbsp;<bean:write name="person" property="peronName" />
								</td>
								<td class='td4' align='center'>
									&nbsp;<bean:write name="person" property="chvPassportNumber" />
								</td>
								<td class='td4' align='center'>
								<bean:write name="person" property="updateButton" filter="false"/>
								&nbsp;|&nbsp;
								<bean:write name="person" property="removeButton" filter="false"/>
								</td>
							</tr>
							</logic:iterate>
						</table>
							
		</tr>
	</table>
	</logic:notEmpty>
	</body>
</html>