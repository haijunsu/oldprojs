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
			
				<table width='100%' cellpadding='1' cellspacing='1' border='0'>
					<tr>
						<td align='center'>
						<h2>
						<bean:message key="hotel.list"/>
						</h2>
						</td>
					</tr>
					<tr>
						<td nowrap='true'>
							<b><i>
							<bean:write name="userView" property="chvPartiTitle"/>&nbsp;
							<bean:write name="userView" property="userName"/>
							</b></i>&nbsp;&nbsp;&nbsp;|&nbsp;
							<bean:message key="form.user.email" bundle="forms"/>:&nbsp;
							<bean:write name="userView" property="chvPartiEmail" />&nbsp;&nbsp;&nbsp;|&nbsp;
							<bean:message key="form.user.country" bundle="forms"/>:&nbsp;
							<bean:write name="userView" property="countryName" />
							<hr>
						</td>
					</tr>
					<tr>
						<td>
						<logic:present name="hotels">
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
									&nbsp;
								</th>
							</tr>
							<logic:iterate id="hotel" name="hotels" indexId="index">
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
									<bean:write name="hotel" property="updateButton" filter="false" />
									&nbsp;|&nbsp;
									<bean:write name="hotel" property="removeButton" filter="false" />
								</td>
								
							</tr>	
							</logic:iterate>
						</table>
						</logic:present>
						<logic:notPresent  name="hotels">
							<bean:message key="hotel.empty"/>
						</logic:notPresent>
						</td>
					</tr>
					<tr>
					<td align='center'>
						<table width='100%' cellpadding='1' cellspacing='1' border='0'>
							<tr>
								<td align='right'>
									<logic:present name="newBtn">
										<bean:write name="newBtn" property="button" filter="false" />
									</logic:present>
								</td>
								<td align='left'>
									<logic:present name="backBtn">
										<bean:write name="backBtn" property="button" filter="false" />
									</logic:present>
								</td>
							</tr>
						</table>
					</td>
					</tr>
				</table>
		</td>
	</tr>
</table>
</body>
</html>