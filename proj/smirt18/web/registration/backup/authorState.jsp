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
<p>
<jsp:include page="/registration/search.jsp" flush="true" />
<html:form action="/author">
<table width='100%' cellspacing='0' cellpadding='0' border='0'>
	<tr valign='center' align='left' width='100%'>
		<td width='10'>
		&nbsp;&nbsp;&nbsp;
		</td>
		<td width='100%'>
			<!-- author info -->
			<hr width='80%'>
			<html:hidden property="action" value="update"/>
			<bean:message key="form.user.name" bundle="forms"/>
			<html:text property="userProfile.userName" readonly="true" size="30"></html:text>
			&nbsp;&nbsp;
			<bean:message key="form.user.jobTitle" bundle="forms"/>
			<html:text property="userProfile.jobTitle" readonly="true" size="10"></html:text>
			&nbsp;&nbsp;
			<bean:message key="form.user.birthday" bundle="forms"/>
			<html:text property="userProfile.birthday" readonly="true" size="10"></html:text>
			<br>
			<bean:message key="form.country" bundle="forms"/>
			<html:text property="userProfile.country" readonly="true" size="15"></html:text>
			&nbsp;&nbsp;
			<bean:message key="form.company" bundle="forms"/>
			<html:text property="userProfile.company"  readonly="true" size="35"></html:text>
			<br>
			<bean:message key="form.email" bundle="forms"/>
			<html:text property="userProfile.email" readonly="true" size="30"></html:text>
			&nbsp;&nbsp;
			<bean:message key="form.tel" bundle="forms"/>
			<html:text property="userProfile.tel" readonly="true" size="15"></html:text>
			&nbsp;&nbsp;
			<bean:message key="form.fax" bundle="forms"/>
			<html:text property="userProfile.fax" readonly="true" size="15"></html:text>
			<br>
			<hr width='80%'>

			<bean:message key="form.register.table" bundle="forms" />
			<html:text property="register.registerTable" size="15"></html:text>
			&nbsp;&nbsp;
			<bean:message key="form.register.state" bundle="forms"/>
			<html:radio property="register.registerState" value="true" >
				<bean:message key="form.radio.yes" bundle="forms"/>
			</html:radio>
			<html:radio property="register.registerState" value="false" >
				<bean:message key="form.radio.no" bundle="forms"/>
			</html:radio>
			&nbsp;&nbsp;
			<bean:message key="form.draw.data" bundle="forms"/>
			<html:radio property="register.drawData" value="true" >
				<bean:message key="form.radio.yes" bundle="forms"/>
			</html:radio>
			<html:radio property="register.drawData" value="false" >
				<bean:message key="form.radio.no" bundle="forms"/>
			</html:radio>
			<br>
			<bean:message key="form.accept.register.fee" bundle="forms"/>
			<html:select property="register.acceptRegisterFeeType" size="1">
              <html:option value="false"><bean:message key="form.currency.U.S.Dollar" bundle="forms"/></html:option>
              <html:option value="true"><bean:message key="form.currency.Renminbi.Yuan" bundle="forms"/></html:option>
              <html:option value="2"><bean:message key="form.currency.Euro" bundle="forms"/></html:option>
            </html:select>
			<html:text property="register.acceptRegisterFee" size="10"/>
			&nbsp;&nbsp;
			<bean:message key="form.pay.mode" bundle="forms"/>
			<html:text property="register.payMode" size="10"></html:text>
			<br>
			<bean:message key="form.attend.campaign" bundle="forms"/>
			&nbsp;&nbsp;
			<html:checkbox property="register.attendDinner" value="true"></html:checkbox>
			<bean:message key="form.attend.dinner" bundle="forms"/>
			&nbsp;&nbsp;
			<html:checkbox property="register.attendReception" value="true"></html:checkbox>
			<bean:message key="form.attend.reception" bundle="forms"/>
			&nbsp;&nbsp;
			<html:checkbox property="register.attendTechnologyVisit" value="true"></html:checkbox>
			<bean:message key="form.attend.technology.visit" bundle="forms"/>
			&nbsp;&nbsp;
			<bean:message key="form.family.member" bundle="forms"/>
			<html:text property="register.familyMember" size="3"></html:text>
			<br>
			<hr width='80%'>
			<bean:message key="form.hotel.destine.table" bundle="forms"/>
			<html:text property="hotel.hotelDestineTable" size="15"></html:text>
			&nbsp;&nbsp;
			<bean:message key="form.hotel.earnest.money" bundle="forms"/>
			<html:select property="hotel.hotelEarnestMoneyType" size="1">
              <html:option value="false"><bean:message key="form.currency.U.S.Dollar" bundle="forms"/></html:option>
              <html:option value="true"><bean:message key="form.currency.Renminbi.Yuan" bundle="forms"/></html:option>
              <html:option value="2"><bean:message key="form.currency.Euro" bundle="forms"/></html:option>
            </html:select>
			<html:text property="hotel.hotelEarnestMoney" size="15"></html:text>
			<br>
			<bean:message key="form.hotel.checkin.date" bundle="forms"/>
			<html:text property="hotel.hotelCheckinDate" size="10"></html:text>
			&nbsp;&nbsp;
			<bean:message key="form.hotel.checkout.date" bundle="forms"/>
			<html:text property="hotel.hotelCheckoutDate" size="10"></html:text>
			<br>
			<hr width='80%'>
			<bean:message key="form.visa.id" bundle="forms"/>
			<html:text property="visa.visaId" size="15"></html:text>
			&nbsp;&nbsp;
			<bean:message key="form.visa.require" bundle="forms"/>
			<html:radio property="visa.visaRequire" value="true" >
				<bean:message key="form.radio.yes" bundle="forms"/>
			</html:radio>
			<html:radio property="visa.visaRequire" value="false" >
				<bean:message key="form.radio.no" bundle="forms"/>
			</html:radio>
			&nbsp;&nbsp;
			<bean:message key="form.visa.table" bundle="forms"/>
			<html:text property="visa.visaTable" size="15"></html:text>
			<br>
			<bean:message key="form.visa.post.date" bundle="forms"/>
			<html:text property="visa.visaPostDate" size="10"></html:text>
			&nbsp;&nbsp;
			<bean:message key="form.in.china.date" bundle="forms"/>
			<html:text property="visa.inChinaDate" size="10"></html:text>
			<br>
			<logic:present name="authorForm" property="paperInfos">
				<logic:iterate id="paper" name="authorForm" property="paperInfos" indexId="index" type="application.action.topic.jo.PaperInfo">
					<hr width='80%'>
					<bean:message key="form.topic.no" bundle="forms"/>
					<html:text property="topicNo" name="paper" ></html:text>
					&nbsp;&nbsp;
					<bean:message key="form.topic.hard.copy" bundle="forms"/>
					<html:radio property="topicHardCopy" name="paper" value="true" >
						<bean:message key="form.radio.have" bundle="forms"/>
					</html:radio>
					<html:radio property="topicHardCopy" name="paper" value="false" >
						<bean:message key="form.radio.none" bundle="forms"/>
					</html:radio>
					&nbsp;&nbsp;
					<bean:message key="form.topic.full.text" bundle="forms"/>
					<html:radio property="topicFullText" name="paper" value="true" >
						<bean:message key="form.radio.have" bundle="forms"/>
					</html:radio>
					<html:radio property="topicFullText" name="paper" value="false" >
						<bean:message key="form.radio.none" bundle="forms"/>
					</html:radio>
					<br>
					<bean:message key="form.topic.fee" bundle="forms"/>
					<html:select property="topicFeeType" name="paper" size="1">
		              <html:option value="false"><bean:message key="form.currency.U.S.Dollar" bundle="forms"/></html:option>
		              <html:option value="true"><bean:message key="form.currency.Renminbi.Yuan" bundle="forms"/></html:option>
		              <html:option value="2"><bean:message key="form.currency.Euro" bundle="forms"/></html:option>
		            </html:select>
					<html:text property="topicFee" name="paper" size="10"></html:text>
					&nbsp;&nbsp;
					<bean:message key="form.topic.authorization" bundle="forms"/>
					<html:text property="topicAuthorization" name="paper" size="10"></html:text>
					<br>
					<bean:message key="form.topic.title" bundle="forms"/>
					<html:text property="topicTitle" name="paper" size="50"></html:text>
					<br>
					<bean:message key="form.topic.other.author" bundle="forms"/>
					<html:text property="topicOtherAuthor" name="paper" size="30"></html:text>
					<br>
					<bean:message key="form.topic.is.report" bundle="forms"/>
					<html:radio property="topicIsReport" name="paper" value="true" >
						<bean:message key="form.radio.yes" bundle="forms"/>
					</html:radio>
					<html:radio property="topicIsReport" name="paper" value="false" >
						<bean:message key="form.radio.no" bundle="forms"/>
					</html:radio>
					&nbsp;&nbsp;
					<bean:message key="form.topic.report.author" bundle="forms"/>
					<html:text property="topicReportAuthor" name="paper" size="15"></html:text>
					&nbsp;&nbsp;
					<bean:message key="form.topic.demo" bundle="forms"/>
					<html:radio property="topicDemo" name="paper" value="true" >
						<bean:message key="form.radio.have" bundle="forms"/>
					</html:radio>
					<html:radio property="topicDemo" name="paper" value="false" >
						<bean:message key="form.radio.none" bundle="forms"/>
					</html:radio>
		
					<br>
					<bean:message key="form.projection.require" bundle="forms"/>
					<html:select property="projectionRequire" name="paper" size="1">
		              <html:option value="false"><bean:message key="form.projection.equipment.ppt" bundle="forms"/></html:option>
		              <html:option value="true"><bean:message key="form.projection.equipment.lantern.slide" bundle="forms"/></html:option>
		              <html:option value="2"><bean:message key="form.projection.equipment.video" bundle="forms"/></html:option>
		              <html:option value="3"><bean:message key="form.projection.equipment.film" bundle="forms"/></html:option>
		            </html:select>
					&nbsp;&nbsp;
					<bean:message key="form.enjoy.perferential.author" bundle="forms"/>
					<html:text property="enjoyPerferentialAuthor" name="paper" size="15"></html:text>
					&nbsp;&nbsp;
					<bean:message key="form.author.resume" bundle="forms"/>
					<html:radio property="authorResume" name="paper" value="true" >
						<bean:message key="form.radio.have" bundle="forms"/>
					</html:radio>
					<html:radio property="authorResume" name="paper" value="false" >
						<bean:message key="form.radio.none" bundle="forms"/>
					</html:radio>
					<br>
				</logic:iterate>
			</logic:present>
			<logic:notPresent name="authorForm" property="paperInfos"> 
				<bean:message key="author.without.papers"/>
			</logic:notPresent>
		</td>
	</tr>
	<tr valign='center' align='center' width='100%'>
		<td>
		&nbsp;
		</td>
		<td width='100%'>
			<html:submit>
				<bean:message key="form.button.submit" bundle="forms"/>
			</html:submit>
			&nbsp;&nbsp;
			<html:reset >
				<bean:message key="form.button.reset" bundle="forms"/>
			</html:reset>
			&nbsp;&nbsp;
			<html:cancel >
				<bean:message key="form.button.cancel" bundle="forms"/>
			</html:cancel>
		</td>
	</tr>
</table>
</html:form>
</body>
</html>