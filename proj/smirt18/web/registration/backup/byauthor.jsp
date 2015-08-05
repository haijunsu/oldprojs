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
		<div align='center'>
		<html:form action="/author" >
		<html:hidden property="action" value="updateAuthor" />
		<table width='80%' cellspacing='0' cellpadding='0' border='0'>
			<tr valign='center' align='left' width='100%'>
				<td width='100%'>
					<bean:message key="form.user.name" bundle="forms"/>
					<html:text property="userName" size="15"></html:text>
					&nbsp;&nbsp;
					<bean:message key="form.country" bundle="forms"/>
					<html:text property="country" size="20"></html:text>
					&nbsp;&nbsp;
					<bean:message key="form.company" bundle="forms"/>
					<html:text property="company" size="25"></html:text>
					<br>
					<bean:message key="form.email" bundle="forms"/>
					<html:text property="email" size="30"></html:text>
					&nbsp;&nbsp;
					<bean:message key="form.tel" bundle="forms"/>
					<html:text property="tel" size="15"></html:text>
					&nbsp;&nbsp;
					<bean:message key="form.fax" bundle="forms"/>
					<html:text property="fax" size="15"></html:text>
					<br>
					<bean:message key="form.abstract" bundle="forms"/>
					<html:text property="abstract" size="8"></html:text>
					&nbsp;
					<bean:message key="form.full.text" bundle="forms"/>
					<html:text property="fullText" size="8"></html:text>
					&nbsp;
					<bean:message key="form.visa.require" bundle="forms"/>
					<html:text property="visaRequire" size="2"></html:text>
					&nbsp;
					<bean:message key="form.visa.table" bundle="forms"/>
					<html:text property="visaTable" size="3"></html:text>
					&nbsp;
					<bean:message key="form.post.date" bundle="forms"/>
					<html:text property="postDate" size="5"></html:text>
					<br>
					<bean:message key="form.register.table" bundle="forms"/>
					<html:text property="registerTable" size="6"></html:text>
					&nbsp;&nbsp;
					<bean:message key="form.hotel.destine.table" bundle="forms"/>
					<html:text property="hotelDestineTable" size="6"></html:text>
					&nbsp;&nbsp;
					<bean:message key="form.enjoy.rebate.topic.no" bundle="forms"/>
					<html:text property="enjoyRebateTopicNo" size="6"></html:text>
					&nbsp;&nbsp;
					<bean:message key="form.report.topic.no" bundle="forms"/>
					<html:text property="reportTopicNo" size="6"></html:text>
					<br>
					<bean:message key="form.accept.register.fee" bundle="forms"/>
					<html:text property="acceptRegisterFee" size="6"></html:text>
					&nbsp;&nbsp;
					<bean:message key="form.accept.room.fee" bundle="forms"/>
					<html:text property="acceptRoomFee" size="6"></html:text>
					&nbsp;&nbsp;
					<bean:message key="form.accept.topic.fee" bundle="forms"/>
					<html:text property="acceptTopicFee" size="6"></html:text>
					&nbsp;&nbsp;
					<bean:message key="form.complement.fee" bundle="forms"/>
					<html:text property="complementFee" size="6"></html:text>
					<br>
					<bean:message key="form.register.state" bundle="forms"/>
					<html:text property="registerState" size="2"></html:text>
					&nbsp;&nbsp;
					<bean:message key="form.family.member" bundle="forms"/>
					<html:text property="familyMember" size="10"></html:text>
					<br>
					<bean:message key="form.attend.campaign" bundle="forms"/>
					&nbsp;&nbsp;
					<bean:message key="form.attend.dinner" bundle="forms"/>
					<html:text property="attendDinner" size="2"></html:text>
					&nbsp;&nbsp;
					<bean:message key="form.attend.reception" bundle="forms"/>
					<html:text property="attendReception" size="2"></html:text>
					&nbsp;&nbsp;
					<bean:message key="form.attend.technology.visit" bundle="forms"/>
					<html:text property="attendTechnologyVisit" size="2"></html:text>
					<br>
					<bean:message key="form.author.resume" bundle="forms"/><br>
					<html:textarea property="authorResume" cols="80" rows="15" ></html:textarea>
				</td>
			</tr>
			<tr valign='center' align='center' width='100%'>
				<td width='100%'>
					<html:submit><bean:message key="form.button.submit" bundle="forms"/></html:submit>
					&nbsp;&nbsp;
					<html:reset><bean:message key="form.button.reset" bundle="forms"/></html:reset>
					&nbsp;&nbsp;
					<html:cancel><bean:message key="form.button.cancel" bundle="forms"/></html:cancel>
				</td>
			</tr>
		</table>
		</html:form>
		</div>
	</body>
</html>