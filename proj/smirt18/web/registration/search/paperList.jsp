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
			
			<logic:present name="papers">
				<table cellpadding='1' cellspacing='1' border='0'>
					<tr>
						<td align='center'>
						<bean:message key="paper.list"/>
						</td>
					</tr>
					<tr>
						<td>
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
			</logic:present>
		</td>
	</tr>
</table>
</body>
</html>