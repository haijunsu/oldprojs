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

	<center>
    <logic:present name="queryTitle">
		<h3><bean:write name="queryTitle" /></h3>
	</logic:present>
    <logic:present name="results">
	<bean:size id="resultSize" name="results"/>
		<table cellspacing='0' cellpadding='0' border='0' class='table4'>
		<logic:iterate id="row" name="results"  indexId="index">
				<tr valign='center' align='left' width='100%'>
					<logic:equal value="true" name="isSelect">
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
					</logic:equal>
				<logic:iterate id="col" name="row">
					<logic:equal value="true" name="isSelect">
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
					</logic:equal>
					<logic:equal value="false" name="isSelect">
					
							<td class='td4'>
								<bean:write name="col" />&nbsp;
							</td>
				    </logic:equal>
				</logic:iterate>
				</tr>
		</logic:iterate>
			</table>
	</logic:present>
	</center>
	</body>
</html>