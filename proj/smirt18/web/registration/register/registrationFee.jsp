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
		function feeTypeChange() {
			document.registrationFeeForm.action.value="queryRegisterFeeByParticipantNo";
			document.registrationFeeForm.submit();
		}
	</script>
	<body>
	
	<html:form action="registerFee" >
	<table width='100%' cellspacing='0' cellpadding='0' border='0'>
		<tr>
			<td width='100%' align='center'>
			<h2>
				<logic:notEmpty property="fee.chrRegisFeeNo" name="registrationFeeForm">
					<bean:message key="register.fee.modify.title"/>
				</logic:notEmpty>
				<logic:empty property="fee.chrRegisFeeNo" name="registrationFeeForm">
					<bean:message key="register.fee.add.title"/>
				</logic:empty>
			</h2>
			</td>
		</tr>
		<tr>
			<td>
				<b><i>
				<bean:write name="registrationFeeForm" property="title"/>&nbsp;
				<bean:write name="registrationFeeForm" property="name"/>
				</b></i>&nbsp;&nbsp;&nbsp;|&nbsp;
				<bean:message key="form.user.email" bundle="forms"/>:&nbsp;
				<bean:write name="registrationFeeForm" property="email" />&nbsp;&nbsp;&nbsp;|&nbsp;
				<bean:message key="form.user.country" bundle="forms"/>:&nbsp;
				<bean:write name="registrationFeeForm" property="country" />
				<hr>
		
				<logic:empty property="fee.chrRegisFeeNo" name="registrationFeeForm">
					<html:hidden property="action" value="createRegisterFee" />
				</logic:empty>
				<logic:notEmpty property="fee.chrRegisFeeNo" name="registrationFeeForm">
					<html:hidden property="action" value="updateRegisterFee" />
				</logic:notEmpty>
				<html:hidden property="partiNo" />
				<html:hidden property="title" />
				<html:hidden property="name" />
				<html:hidden property="email" />
				<html:hidden property="country" />
				<html:hidden property="fee.chrRegisFeeNo" />
				<html:hidden property="fee.chrPartiNo" />
				<html:hidden property="fee.chrPaymentMethodNo" value="1"/>
			</td>
		</tr>
		<tr>
			<td width='100%'>
			<p class="errMsg">
				<html:errors property="registerFeeError"/>
			</td>
		</tr>
		<tr>
			<td width='100%' nowrap='true'>
				<table width='100%' cellspacing='0' cellpadding='3' border='0' class='table4'>
					<tr>
						<td class='td4' nowrap='true' width='50%'>
							<bean:message key="form.registration.fee.money.type" bundle="forms"/>
				            <html:select property="feeType" size="1" onchange="feeTypeChange()">
				              <html:optionsCollection name="registrationFeeForm" property="feeTypes" />
				            </html:select>&nbsp;
							<bean:write name="registrationFeeForm" property="moneyTypeName"/>
						</td>
						<td class='td4' nowrap='true' width='50%'> 
							<bean:message key="form.registration.fee.paper.number" bundle="forms"/>
							<html:text property="fee.chvPaperNumber" size="15" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.registration.fee.should.registed" bundle="forms"/>
				            <html:text property="shouldPayRegisterFee" size="30" readonly="true"></html:text>
						</td>
						<td bgcolor="#CCCC99" class='td4' nowrap='true' width='50%'>
							<bean:message key="form.registration.fee.grant.support.fee" bundle="forms"/>
							<html:text property="fee.mnyGrantSupportFee" size="30" ></html:text>
							<logic:greaterThan name="registrationFeeForm" property="registerFeeBalance" value="0">
								<font color="green"><bean:write name="registrationFeeForm" property="registerFeeBalance"/></font>
							</logic:greaterThan>
							<logic:lessThan name="registrationFeeForm" property="registerFeeBalance" value="0">
								<font color="red"><bean:write name="registrationFeeForm" property="registerFeeBalance"/></font>
							</logic:lessThan>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'> 
							<bean:message key="form.registration.fee.should.hotel.book" bundle="forms"/>
							<html:text property="hotelFee" size="30" readonly="true"></html:text>
						</td>
						<td class='td4' nowrap='true'> 
							<bean:message key="form.registration.fee.hotel.book" bundle="forms"/>
				            <html:text property="fee.mnyConfFeeBetween" size="30"></html:text>
							<logic:greaterThan name="registrationFeeForm" property="hotelBalance" value="0">
								<font color="green"><bean:write name="registrationFeeForm" property="hotelBalance"/></font>
							</logic:greaterThan>
							<logic:lessThan name="registrationFeeForm" property="hotelBalance" value="0">
								<font color="red"><bean:write name="registrationFeeForm" property="hotelBalance"/></font>
							</logic:lessThan>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.registration.fee.should.acc.person.fee" bundle="forms"/>
				            <html:text property="accPersonFee" size="30" readonly="true"></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.registration.fee.acc.person.fee" bundle="forms"/>
				            <html:text property="fee.mnyAccPersonFee" size="30"></html:text>
							<logic:greaterThan name="registrationFeeForm" property="accPersonFeeBalance" value="0">
								<font color="green"><bean:write name="registrationFeeForm" property="accPersonFeeBalance"/></font>
							</logic:greaterThan>
							<logic:lessThan name="registrationFeeForm" property="accPersonFeeBalance" value="0">
								<font color="red"><bean:write name="registrationFeeForm" property="accPersonFeeBalance"/></font>
							</logic:lessThan>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'> 
							<bean:message key="form.registration.fee.should.total" bundle="forms"/>
							<html:text property="shouldPayTotal" size="30" readonly="true"></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.registration.fee.real.total" bundle="forms"/>
				            <html:text property="realPayTotal" size="30" readonly="true"></html:text>
							<logic:greaterThan name="registrationFeeForm" property="realBalance" value="0">
								<font color="green"><bean:write name="registrationFeeForm" property="realBalance"/></font>
							</logic:greaterThan>
							<logic:lessThan name="registrationFeeForm" property="realBalance" value="0">
								<font color="red"><bean:write name="registrationFeeForm" property="realBalance"/></font>
							</logic:lessThan>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width='100%' nowrap='true'>
				<table width='100%' cellspacing='0' cellpadding='3' border='0' class='table4' bgcolor='#CCCC99'>
					<tr>
						<td class='td4' nowrap='true'> 
							<font color='red'>
							<bean:message key="form.registration.fee.balance" bundle="forms"/>
							<html:text property="balance" size="30" readonly="true"></html:text>
							</font>
						</td>
						<td class='td4' nowrap='true' width='50%'> 
							<bean:message key="form.registration.fee.is.reg.fee.received" bundle="forms"/>
							<html:radio property="fee.bitIsRegFeeReceived" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="fee.bitIsRegFeeReceived" value="false" >
								<bean:message key="form.radio.no" bundle="forms"/>
							</html:radio>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width='100%' nowrap='true'>
				<table width='100%' cellspacing='0' cellpadding='3' border='0' class='table4'>
					<tr>
						<td class='td4' nowrap='true' width='50%'>
							<bean:message key="form.registration.fee.bank.name" bundle="forms"/>
				            <html:text property="fee.chvBankName" size="30"></html:text>
						</td>
						<td class='td4' nowrap='true' width='50%'> 
							<bean:message key="form.registration.fee.account.number" bundle="forms"/>
							<html:text property="fee.chvAccountNumber" size="30"></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.registration.fee.sum.payment" bundle="forms"/>
				            <html:text property="fee.mnySumPayment" size="30"></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.registration.fee.trans.date" bundle="forms"/>
				            <html:text property="fee.dtmTransDate" size="30"></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'> 
							<bean:message key="form.registration.fee.reg.fee.received" bundle="forms"/>
							<html:text property="fee.mnyRegFeeReceived" size="20"></html:text>
						</td>
						<td class='td4' nowrap='true'> 
							<bean:message key="form.registration.fee.account.name" bundle="forms"/>
							<html:text property="fee.chvAccountName" size="30" maxlength="100"></html:text>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width='100%' nowrap='true'>
				<table width='100%' cellspacing='0' cellpadding='3' border='0' class='table4' bgcolor='#CCCC99'>
					<tr>
						<td class='td4' nowrap='true' width='50%'> 
							<bean:message key="form.registration.fee.credit.card.style" bundle="forms"/>
							<html:text property="fee.chvCreditCardStyle" size="30"></html:text>
						</td>
						<td class='td4' nowrap='true' width='50%'> 
							<bean:message key="form.registration.fee.card.no" bundle="forms"/>
							<html:text property="fee.chvCardNo" size="30"></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'> 
							<bean:message key="form.registration.fee.card.holder.first.name" bundle="forms"/>
							<html:text property="fee.chvCardholderFirstName" size="30"></html:text>
						</td>
						<td class='td4' nowrap='true'> 
							<bean:message key="form.registration.fee.expiration.date" bundle="forms"/>
							<html:text property="fee.dtmExpirationData" size="30"></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' colspan='2'> 
							<bean:message key="form.registration.fee.card.holder.address" bundle="forms"/>
							<html:text property="fee.chvCardholderAddress" size="60" maxlength="200"></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'> 
							<bean:message key="form.registration.fee.wish.pay" bundle="forms"/>
							<html:text property="fee.mnyWishPay" size="30"></html:text>
						</td>
						<td class='td4' nowrap='true'> 
							<bean:message key="form.registration.fee.pay.date" bundle="forms"/>
							<html:text property="fee.dtmPayDate" size="30"></html:text>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width='100%' nowrap='true'>
				<table width='100%' cellspacing='0' cellpadding='3' border='0' class='table4'>
					<tr>
						<td class='td4' nowrap='true' width='30%'>
							<bean:message key="form.registration.fee.conf.fee.venue" bundle="forms"/>
				            <html:text property="fee.mnyConfFeeVenue" size="10"></html:text>
						</td>
						<td class='td4' nowrap='true' width='40%'> 
							<bean:message key="form.registration.fee.reg.cheque.number" bundle="forms"/>
							<html:text property="fee.chvRegChequeNumber" size="30"></html:text>
						</td>
						<td class='td4' nowrap='true' width='30%' colspan='2'> 
							<bean:message key="form.registration.fee.reg.fee.received.date" bundle="forms"/>
							<html:text property="fee.dtmRegFeeReceivedDate" size="20"></html:text>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td width='100%' align='center'>
				<html:submit>
					<logic:empty property="fee.chrRegisFeeNo" name="registrationFeeForm">
						<bean:message key="form.button.add" bundle="forms"/>
					</logic:empty>
					<logic:notEmpty property="fee.chrRegisFeeNo" name="registrationFeeForm">
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
	<center>
	<logic:present name="registrationFeeForm" property="personViews">
		<bean:message key="accompany.person.fee.list"/>
		<table cellpadding='0' cellspacing='0' border='0' class='table4' width='100%'>
			<tr>
				<th align='center' nowrap='true' class='td4'>
					<bean:message key="form.result.serial" bundle="forms"/>
				</th>
				<th align='center' nowrap='true' class='td4'>
					<bean:message key="form.user.name" bundle="forms"/>
				</th>
				<th align='center' nowrap='true' class='td4'>
					<bean:message key="form.user.is.attend.reception" bundle="forms"/>
				</th>
				<th align='center' nowrap='true' class='td4'>
					<bean:message key="form.user.is.attend.banquet" bundle="forms"/>
				</th>
				<th align='center' nowrap='true' class='td4'>
					<bean:message key="form.user.is.attend.tech.tour1" bundle="forms"/>
				</th>
				<th align='center' nowrap='true' class='td4'>
					<bean:message key="form.user.is.attend.tech.tour2" bundle="forms"/>
				</th>
				<th align='center' nowrap='true' class='td4'>
					<bean:message key="form.user.acc.person.fee" bundle="forms"/>
				</th>
			</tr>
			<logic:iterate id="person" property="personViews" name="registrationFeeForm" indexId="index">
			<tr>
				<td class='td4' align='center'>
				<%=index.intValue() + 1%>
				</td>
				<td class='td4' align='center' nowrap='true' >
					&nbsp;<bean:write name="person" property="userName" />
				</td>
				<td class='td4' align='center'>
					&nbsp;
					<logic:equal value="true" name="person" property="bitIsAttendReception">
						<bean:message key="form.radio.yes" bundle="forms"/>
					</logic:equal>
					<logic:equal value="false" name="person" property="bitIsAttendReception">
						<font color='red'>
						<bean:message key="form.radio.no" bundle="forms"/>
						</font>
					</logic:equal>
				</td>
				<td class='td4' align='center'>
					&nbsp;
					<logic:equal value="true" name="person" property="bitIsAttendBanquet">
						<bean:message key="form.radio.yes" bundle="forms"/>
					</logic:equal>
					<logic:equal value="false" name="person" property="bitIsAttendBanquet">
						<font color='red'>
						<bean:message key="form.radio.no" bundle="forms"/>
						</font>
					</logic:equal>
				</td>
				<td class='td4' align='center'>
					&nbsp;
					<logic:equal value="true" name="person" property="bitIsAttendTechTour">
						<bean:message key="form.radio.yes" bundle="forms"/>
					</logic:equal>
					<logic:equal value="false" name="person" property="bitIsAttendTechTour">
						<font color='red'>
						<bean:message key="form.radio.no" bundle="forms"/>
						</font>
					</logic:equal>
				</td>
				<td class='td4' align='center'>
					&nbsp;
					<logic:equal value="true" name="person" property="bitIsCheckin">
						<bean:message key="form.radio.yes" bundle="forms"/>
					</logic:equal>
					<logic:equal value="false" name="person" property="bitIsCheckin">
						<font color='red'>
						<bean:message key="form.radio.no" bundle="forms"/>
						</font>
					</logic:equal>
				</td>
				<td class='td4' align='center'>
					&nbsp;<bean:write name="person" property="accFee" />
				</td>
			</tr>
			</logic:iterate>
		</table>
			
		
	</logic:present>
	<logic:present name="registrationFeeForm" property="hotels">
		<bean:message key="hotel.fee.list"/>
		<table width='100%' cellpadding='0' cellspacing='0' border='0' class='table4'>
			<tr>
				<th align='center' nowrap='true' class='td4'>
					<bean:message key="form.result.serial" bundle="forms"/>
				</th>
				<th align='center' nowrap='true' class='td4'>
					<bean:message key="form.hotel.hotelName" bundle="forms"/>
				</th>
				<th align='center' nowrap='true' class='td4'>
					<bean:message key="form.hotel.room.number" bundle="forms"/>
				</th>
				<th align='center' nowrap='true' class='td4'>
					<bean:message key="form.hotel.room.type" bundle="forms"/>
				</th>
				<th align='center' nowrap='true' class='td4'>
					<bean:message key="form.hotel.book.date" bundle="forms"/>
				</th>
				<th align='center' nowrap='true' class='td4'>
					<bean:message key="form.hotel.book.fee" bundle="forms"/>
				</th>
			</tr>
			<logic:iterate id="hotel" property="hotels" name="registrationFeeForm" indexId="index">
			<tr>
				<td class='td4' align='center'>
				<%=index.intValue() + 1%>
				</td>
				<td class='td4'>
				<bean:write name="hotel" property="chvHotalName" />&nbsp;
				</td>
				<td class='td4'>
				<bean:write name="hotel" property="chvRoomNumber" />&nbsp;
				</td>
				<td class='td4'>
				<bean:write name="hotel" property="roomTypeName" />&nbsp;
				</td>
				<td class='td4' align='center'>
				<bean:write name="hotel" property="dtmBookDate" />&nbsp;
				</td>
				<td class='td4' align='center'>
				<bean:write name="hotel" property="mnyBookFee" />&nbsp;
				</td>
			</tr>	
			</logic:iterate>
		</table>
	</logic:present>
	</center>
	</body>
</html>