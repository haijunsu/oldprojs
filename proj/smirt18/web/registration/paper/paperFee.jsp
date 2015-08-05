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
	
	<html:form action="paperFee" >
	<table width='100%' cellspacing='0' cellpadding='0' border='0'>
		<tr valign='center' align='center' width='100%'>
			<td width='100%'>
			<h2>
				<bean:message key="paper.registration.fee"/>
			</h2>
			</td>
		</tr>
		<tr>
			<td>
				<bean:message key="form.paper.title" bundle="forms"/>:&nbsp;
				<b><i>
				<bean:write name="paperView" property="chvPaperTitle"/>&nbsp;
				</b></i>&nbsp;
				<hr>
		
				<html:hidden property="action" value="updatePaperFee" />
				<html:hidden property="paperFee.chrPaperRegNo" />
				<html:hidden property="paperFee.chvPaperNumber" />
			</td>
		</tr>
		<tr valign='center' align='center' width='100%'>
			<td width='100%'>
			<p class="errMsg">
				<html:errors property="paperFeeError"/>
			</td>
		</tr>
		<tr valign='center' align='center' width='100%'>
			<td width='100%' nowrap='true'>
				<table width='100%' cellspacing='0' cellpadding='3' border='0' class='table4'>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.paper.reg.table.no" bundle="forms"/>
							<html:text property="paperFee.chrPaperRegTableNo" size="15" ></html:text>
						</td>
						<td class='td4' nowrap='true'> 
							<bean:message key="form.paper.registration.paper.Lnumber" bundle="forms"/>
							<html:text property="paperFee.chvPaperLNumber" size="15" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.paper.reg.wish.pay" bundle="forms"/>
							<html:text property="paperFee.mnyPaperRegWishPay" size="20" ></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.paper.reg.fee" bundle="forms"/>
							<html:text property="paperFee.mnyPaperRegFee" size="20" ></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.paper.reg.fee.received" bundle="forms"/>
							<html:text property="paperFee.mnyPaperRegFeeReceived" size="20" ></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.money.type" bundle="forms"/>
				            <html:select property="paperFee.chrMoneyType" size="1">
				              <html:optionsCollection name="paperFeeForm" property="moneyTypes" />
				            </html:select>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.payment.method" bundle="forms"/>
				            <html:select property="paperFee.chrPaymentMethodNo" size="1">
				              <html:optionsCollection name="paperFeeForm" property="paymentMethods" />
				            </html:select>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.received.paper.reg.fee" bundle="forms"/>
							<html:radio property="paperFee.bitIsReceivedPaperRegFee" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="paperFee.bitIsReceivedPaperRegFee" value="false" >
								<bean:message key="form.radio.no" bundle="forms"/>
							</html:radio>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.received.paper.reg.fee.date" bundle="forms"/>
							<html:text property="paperFee.dtmReceivedPaperRegFeeDate" size="30" ></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.is.app.jaeger.prize" bundle="forms"/>
							<html:radio property="paperFee.bitIsAppJaegerPrize" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="paperFee.bitIsAppJaegerPrize" value="false" >
								<bean:message key="form.radio.no" bundle="forms"/>
							</html:radio>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.is.app.junior.award" bundle="forms"/>
							<html:radio property="paperFee.bitIsAppJuniorAward" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="paperFee.bitIsAppJuniorAward" value="false" >
								<bean:message key="form.radio.no" bundle="forms"/>
							</html:radio>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.is.app.support" bundle="forms"/>
							<html:radio property="paperFee.bitIsAppSupport" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="paperFee.bitIsAppSupport" value="false" >
								<bean:message key="form.radio.no" bundle="forms"/>
							</html:radio>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.granted.paint.no" bundle="forms"/>
							<html:text property="paperFee.chrGrantedPartiNo" size="15" readonly="true"></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.paper.reg.date" bundle="forms"/>
							<html:text property="paperFee.dtmPaperRegDate" size="30" ></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.paper.reg.title" bundle="forms"/>
							<html:text property="paperFee.chvPaperRegJobTitle" size="15" ></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.paper.reg.first.name" bundle="forms"/>
							<html:text property="paperFee.chvPaperRegFirstName" size="30" ></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.paper.reg.middle.name" bundle="forms"/>
							<html:text property="paperFee.chvPaperRegMiddleName" size="15" ></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.paper.reg.last.name" bundle="forms"/>
							<html:text property="paperFee.chvPaperRegLastName" size="30" ></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.paper.reg.tele.number" bundle="forms"/>
							<html:text property="paperFee.chvPaperRegTeleNumber" size="30" ></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.paper.reg.fax" bundle="forms"/>
							<html:text property="paperFee.chvPaperRegFax" size="30" ></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.paper.reg.email" bundle="forms"/>
							<html:text property="paperFee.chvPaperRegEmail" size="30" ></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.paper.reg.account.number" bundle="forms"/>
							<html:text property="paperFee.chvPaperRegAccountNmuber" size="30" ></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.paper.reg.bank.name" bundle="forms"/>
							<html:text property="paperFee.chvPaperRegBankName" size="30" ></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.paper.reg.account.number" bundle="forms"/>
							<html:text property="paperFee.chvPaperRegAccountName" size="30" ></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.paper.reg.trans.date" bundle="forms"/>
							<html:text property="paperFee.dtmPaperRegTransDate" size="30" ></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.paper.reg.signature" bundle="forms"/>
							<html:text property="paperFee.chvPaperRegSignature" size="30" ></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.is.paper.reg.faxed" bundle="forms"/>
							<html:radio property="paperFee.bitIsPaperRegFaxed" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="paperFee.bitIsPaperRegFaxed" value="false" >
								<bean:message key="form.radio.no" bundle="forms"/>
							</html:radio>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.is.all.payed" bundle="forms"/>
							<html:radio property="paperFee.bitIsAllPayed" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="paperFee.bitIsAllPayed" value="false" >
								<bean:message key="form.radio.no" bundle="forms"/>
							</html:radio>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.paper.reg.cheque.number" bundle="forms"/>
							<html:text property="paperFee.chvPaperRegChequeNumber" size="30" ></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.paper.registration.is.informed.paper.reg" bundle="forms"/>
							<html:radio property="paperFee.bitIsInformedPaperReg" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="paperFee.bitIsInformedPaperReg" value="false" >
								<bean:message key="form.radio.no" bundle="forms"/>
							</html:radio>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.paper.registration.is.informed.paper.fee" bundle="forms"/>
							<html:radio property="paperFee.bitIsInformedPaperFee" value="true" >
								<bean:message key="form.radio.yes" bundle="forms"/>	
							</html:radio>
							<html:radio property="paperFee.bitIsInformedPaperFee" value="false" >
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
					<bean:message key="form.button.update" bundle="forms"/>
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

			<logic:present name="authors">
	<html:form action="paperFee" >
				<table cellpadding='1' cellspacing='1' border='0' width='100%'>
					<tr>
						<td align='center'>
						<bean:message key="author.list"/>
				<html:hidden property="action" value="updatePaperGranted" />
				<html:hidden property="paperFee.chrPaperRegNo" />
				<html:hidden property="paperFee.chvPaperNumber" />
				<html:hidden property="paperFee.chvPaperLNumber" />
						</td>
					</tr>
					<tr>
						<td>
						<table width='100%' cellpadding='0' cellspacing='0' border='0' class='table4'>
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
									<bean:message key="form.user.is.granted" bundle="forms"/>
								</th>
							</tr>
							<logic:iterate id="userView" name="authors" indexId="index">
							<tr>
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
								<logic:equal value="-1" name="userView" property="grantedStatus">
									<bean:message key="granted.not.registed" />
								</logic:equal>
								<logic:equal value="0" name="userView" property="grantedStatus">
									<input type="radio" name="partiNo" value="<bean:write name="userView" property="chrPartiNo" />">
								</logic:equal>
								<logic:equal value="1" name="userView" property="grantedStatus">
									<bean:message key="granted.with.other.paper" />(<bean:write name="userView" property="grantedPaperLNumber" />)
								</logic:equal>
								<logic:equal value="2" name="userView" property="grantedStatus">
									<input type="hidden" name="oldPartiNo" value="<bean:write name="userView" property="chrPartiNo" />">
									<input type="radio" name="partiNo" value="<bean:write name="userView" property="chrPartiNo" />" checked="checked">
								</logic:equal>
								<logic:equal value="3" name="userView" property="grantedStatus">
									<input type="hidden" name="regFeeIsRecieved" value="true">
									<bean:message key="granted.already.pay" />
								</logic:equal>
								</td>
							</tr>	
							</logic:iterate>
						</table>
						
						</td>
					</tr>
					<logic:present name="presented">
						<logic:present name="presented" property="chrPartiNo">
					<tr>
						<td align='center'>
						<bean:message key="paper.presented.title"/>
						</td>
					</tr>
					<tr>
						<td>
						<table width='100%' cellpadding='0' cellspacing='0' border='0' class='table4'>
							<tr>
								<th align='center' nowrap='true' class='td4'>
									<bean:message key="form.paper.presented.presenter.parti.no" bundle="forms"/>
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
									<bean:message key="form.user.is.granted" bundle="forms"/>
								</th>
							</tr>
							<tr>
								<td class='td4' align='center'>
								<bean:write name="presented" property="chrPartiNo" />&nbsp;
								</td>
								<td class='td4'>
								<a href='<bean:write name="rq" property="contextPath"/>/register.do?<bean:write name="presented" property="urlParams" />'>
								<bean:write name="presented" property="userName" /></a>&nbsp;
								</td>
								<td class='td4'>
								<bean:write name="presented" property="chvPartiEmail" />&nbsp;
								</td>
								<td class='td4'>
								<bean:write name="presented" property="chvPartiAffiliation" />&nbsp;
								</td>
								<td class='td4'>
								<bean:write name="presented" property="chvPartiAddress" />&nbsp;
								</td>
								<td class='td4'>
								<bean:write name="presented" property="countryName" />&nbsp;
								</td>
								<td class='td4' align='center'>
								<logic:equal value="-1" name="presented" property="grantedStatus">
									<bean:message key="granted.not.registed" />
								</logic:equal>
								<logic:equal value="0" name="presented" property="grantedStatus">
									<input type="radio" name="partiNo" value="<bean:write name="presented" property="chrPartiNo" />">
								</logic:equal>
								<logic:equal value="1" name="presented" property="grantedStatus">
									<bean:message key="granted.with.other.paper" />(<bean:write name="presented" property="grantedPaperLNumber" />)
								</logic:equal>
								<logic:equal value="2" name="presented" property="grantedStatus">
									<input type="hidden" name="oldPartiNo" value="<bean:write name="presented" property="chrPartiNo" />">
									<input type="radio" name="partiNo" value="<bean:write name="presented" property="chrPartiNo" />" checked="checked">
								</logic:equal>
								<logic:equal value="3" name="presented" property="grantedStatus">
									<input type="hidden" name="regFeeIsRecieved" value="true">
									<bean:message key="granted.already.pay" />
								</logic:equal>
								</td>
							</tr>	
						</table>
						
						</td>
					</tr>
						</logic:present>
					</logic:present>
					
					
					<tr>
						<td align='center'>
						<logic:equal value="      " name="paperFeeForm" property="paperFee.chrGrantedPartiNo">
						<input type="radio" name="partiNo" value="none" checked="checked">
						</logic:equal>
						
						<logic:notEqual value="      " name="paperFeeForm" property="paperFee.chrGrantedPartiNo">
						<input type="radio" name="partiNo" value="none">
						</logic:notEqual>
						<bean:message key="granted.none" />
						</td>
					</tr>
					<tr>
						<td align='center'>
				<html:submit>
					<bean:message key="form.button.update.granted" bundle="forms"/>
				</html:submit>
						</td>
					</tr>
				</table>
	</html:form>
			</logic:present>
	</body>
</html>