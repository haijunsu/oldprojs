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
	
	<html:form action="hotel" >
	<table width='100%' cellspacing='0' cellpadding='0' border='0'>
		<tr valign='center' align='center' width='100%'>
			<td width='100%'>
			<h2>
				<logic:notEmpty property="hotel.chrAccommodationNo" name="hotelForm">
					<bean:message key="hotel.manager.title"/>
				</logic:notEmpty>
				<logic:empty property="hotel.chrAccommodationNo" name="hotelForm">
					<bean:message key="hotel.add.title"/>
				</logic:empty>
			</h2>
			</td>
		</tr>
		<tr>
			<td>
				<b><i>
				<bean:write name="userView" property="chvPartiTitle"/>&nbsp;
				<bean:write name="userView" property="userName"/>
				</b></i>&nbsp;&nbsp;&nbsp;|&nbsp;
				<bean:message key="form.user.email" bundle="forms"/>:&nbsp;
				<bean:write name="userView" property="chvPartiEmail" />&nbsp;&nbsp;&nbsp;|&nbsp;
				<bean:message key="form.user.country" bundle="forms"/>:&nbsp;
				<bean:write name="userView" property="countryName" />
				<hr>
		
				<logic:empty property="hotel.chrAccommodationNo" name="hotelForm">
					<html:hidden property="action" value="createHotel" />
				</logic:empty>
				<logic:notEmpty property="hotel.chrAccommodationNo" name="hotelForm">
					<html:hidden property="action" value="updateHotel" />
				</logic:notEmpty>
				<html:hidden property="hotel.chrAccommodationNo" />
				<html:hidden property="hotel.chrPartiNo" />
			</td>
		</tr>
		<tr valign='center' align='center' width='100%'>
			<td width='100%'>
			<p class="errMsg">
				<html:errors property="hotelError"/>
			</td>
		</tr>
		<tr valign='center' align='center' width='100%'>
			<td width='100%' nowrap='true'>
				<table width='100%' cellspacing='0' cellpadding='3' border='0' class='table4'>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.hotel.hotelName" bundle="forms"/>
							<html:text property="hotel.chvHotalName" size="30" ></html:text>
						</td>
						<td class='td4' nowrap='true'> 
							<bean:message key="form.hotel.room.number" bundle="forms"/>
							<html:text property="hotel.chvRoomNumber" size="15" ></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.hotel.room.type" bundle="forms"/>
				            <html:select property="hotel.chvRoomtype" size="1">
				              <html:optionsCollection name="hotelForm" property="roomTypes" />
				            </html:select>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.hotel.date.checkin" bundle="forms"/>
							<html:text property="hotel.dtmCheckIn" size="15" ></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true'>
							<bean:message key="form.hotel.date.chechout" bundle="forms"/>
							<html:text property="hotel.dtmCheckout" size="20"></html:text>
						</td>
						<td class='td4' nowrap='true'>
							<bean:message key="form.hotel.book.date" bundle="forms"/>
							<html:text property="hotel.dtmBookDate" size="20"></html:text>
						</td>
					</tr>
					<tr>
						<td class='td4' nowrap='true' colspan='2'>
							<bean:message key="form.hotel.book.fee" bundle="forms"/>
							<html:text property="hotel.mnyBookFee" size="20"></html:text>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr valign='center' align='center' width='100%'>
			<td width='100%'>
				<html:submit>
					<logic:empty property="hotel.chrAccommodationNo" name="hotelForm">
						<bean:message key="form.button.add" bundle="forms"/>
					</logic:empty>
					<logic:notEmpty property="hotel.chrAccommodationNo" name="hotelForm">
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
	</body>
</html>