<!-- page encoding -->
<%@ page 
language="java"
isErrorPage="true"
contentType="text/html; charset=utf-8"
pageEncoding="utf-8"
%>

<%@ include file="/common/include.jsp" %>
<html>
	<head>
		<title><bean:message key="index.title"/></title>
		<link href="<bean:write name="rq" property="contextPath"/>/common/css/Master.css" rel="stylesheet" type="text/css">
	</head>
	<body topmargin="0" leftmargin="0">
		<table border="0" width="796" height='100%' cellpadding='0' cellspacing='0'>
		  <tbody>
			<tr>
				<td valign='top' height='100%' width='180'>
					<tiles:insert attribute="left"/>
				</td>
				<td height='5' valign='top' width='100%'>
					<table border="0" width="100%" height='100%' cellpadding='0' cellspacing='0'>
					  <tbody>
						<tr>
							<td valign='top' height='20'>
								<tiles:insert attribute="top"/>
						</tr>
						<tr height='100%' valign='top'>
							<td>
								<tiles:insert attribute="search"/>
								<tiles:insert attribute="body"/>
							</td>
						</tr>
						<tr>
							<td height='15' valign='top'>
								<tiles:insert attribute="copyright"/>
							</td>
						</tr>
					  </tbody>
					</table>
				</td>
			</tr>
		  </tbody>
		 </table>
	</body>
</html>
