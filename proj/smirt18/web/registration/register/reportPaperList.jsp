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
	<table width='600' cellspacing='0' cellpadding='0' border='0'>
		<tr valign='center' width='100%'>
			<td width='100%'>
			<p class="errMsg">
				<html:errors />
			</td>
		</tr>
	</table>
	Author:&nbsp;<bean:write name="partiNo"/>, <bean:write name="partiName"/>
	<center>
	<h3>Report Papers List</h3>
	<logic:notPresent name="papers">No report paper for this author.<br></logic:notPresent>	
    <logic:present name="papers">
	<bean:size id="resultSize" name="papers"/>
		<table cellspacing='0' cellpadding='0' border='0' class='table4'>
		<logic:iterate id="row" name="papers"  indexId="index">
				<tr valign='center' align='left' width='100%'>
						<logic:equal value="0" name="index">
							<logic:notEqual value="1" name="resultSize">
							<th class='td4'  bgcolor='#CCCC99'>
								<i><bean:message key="form.result.serial" bundle="forms"/></i>
							</th>
							</logic:notEqual>
						</logic:equal>
						<logic:notEqual value="0" name="index">
							<td class='td4' align='center' bgcolor='#DDCC99'>
								<i><bean:write name="index" /></i>
							</td>
						</logic:notEqual>
				<logic:iterate id="col" name="row">
						<logic:equal value="0" name="index">
							<th class='td4'  bgcolor='#CCCC99'>
								<bean:write name="col" />&nbsp;
							</th>
						</logic:equal>
						<logic:notEqual value="0" name="index">
							<td class='td4'>
								<bean:write name="col" />&nbsp;
							</td>
						</logic:notEqual>
				</logic:iterate>
				</tr>
		</logic:iterate>
			</table>
	</logic:present>
	<bean:write name="backBtn" property="button" filter="false"/>
	</center>
	</body>
</html>