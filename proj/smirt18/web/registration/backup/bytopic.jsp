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
<html:form action="/topic">
<table width='100%' cellspacing='0' cellpadding='0' border='0'>
	<tr valign='center' align='center' width='100%'>
		<td width='100%'>
			<html:hidden property="action" value="queryTopicByKey"/>
			<bean:message key="form.search.topic" bundle="forms"/>
			<html:text property="searchKey" size="40" />
			<html:submit>
				<bean:message key="form.button.search" bundle="forms"/>
			</html:submit>
		</td>
	</tr>
</table>
</html:form>
<hr size='1' width='70%' noshade='true' align='center'>
<div align='center'>
<html:form action="/topic" >
<html:hidden property="action" value="updateTopic" />
<table width='80%' cellspacing='0' cellpadding='0' border='0'>
	<tr valign='center' align='left' width='100%'>
		<td width='100%'>
			<bean:message key="form.topic.no" bundle="forms"/>
			<html:text property="topicNo" size="15"></html:text>
			&nbsp;&nbsp;
			<bean:message key="form.contact.author" bundle="forms"/>
			<html:text property="contactAuthor" size="20"></html:text>
			&nbsp;&nbsp;
			<bean:message key="form.report.author" bundle="forms"/>
			<html:text property="reportAuthor" size="20"></html:text>
			<br>
			<bean:message key="form.other.author" bundle="forms"/>
			<html:text property="otherAuthor" size="95"></html:text>
			<br>
			<bean:message key="form.accept.fee" bundle="forms"/>
			<html:text property="acceptFee" size="20"></html:text>
			&nbsp;&nbsp;
			<bean:message key="form.enjoy.perferential.author" bundle="forms"/>
			<html:text property="enjoyPerferentialAuthor" size="20"></html:text>
			<br>
			<bean:message key="form.projection.require" bundle="forms"/>
			<html:text property="projectionRequire" size="40"></html:text>
			<br>
			<bean:message key="form.topic.abstract" bundle="forms"/><br>
			<html:textarea property="topicAbstrace" cols="80" rows="15" ></html:textarea>
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