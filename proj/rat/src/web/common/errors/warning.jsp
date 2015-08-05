<%@ include file="/common/include.jsp" %>


<html:html>
<head>
<meta http-equiv="Content-Style-Type" content="text/css">
<link href="<bean:write name="req" property="contextPath"/>/html/css/style.css" rel="stylesheet" type="text/css">
<title>
<bean:message key="index.title"/></title>

</head>
<body bgcolor="#FFFFFF">
<table width="100%"><tr><td>
<!--html:img  alt="error image" page="/html/image/error.gif" /-->
</td>

<td>
<p class="errTitle"><bean:message key="warning.title"/></p>
<p class="errMsg">
<html:messages message="true" id="msg" header="messages.header" footer="messages.footer">
	<bean:write name="msg" />
</html:messages>
</td>
</tr>
<tr>
<td colspan='2' align='center'>
	<table>
		<tr>
			<td align="right">
				<logic:present name="confirmBtn">
					<bean:write name="confirmBtn" filter="false"/>
				</logic:present>&nbsp;
			</td>
			<td>
				<logic:present name="backBtn">
					<bean:write name="backBtn" filter="false"/>
				</logic:present>
				<logic:notPresent name="backBtn">
				<html:link page="/"><bean:message key="button.home" /></html:link>
				</logic:notPresent>&nbsp;
			</td>
		</tr>
	</table>
</td>
</tr>
</table>
</body>
</html:html>