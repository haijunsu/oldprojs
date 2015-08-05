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
			<tr valign='top'  align='left' width='100%'>
				<td width='10'>
				&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
				<td width='100%'>
				<html:image page="/common/imgs/title.jpg" alt="SMiRT 18 title image"></html:image>
				</td>
			</tr>
			<tr valign='top'  align='left' width='100%'>
				<td>
				&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
				<td width='100%'>
					<table cellspacing='2' cellpadding='2' border='0'>
						<tr align='left'>
								<td  bgcolor='#CCCCCC' width='200' nowrap>
									Welcome <bean:write name="rq" property="remoteUser"/>!
								</td>
								<td  bgcolor='#CCCCCC' width='120' nowrap>
								&nbsp;
								</td>
								<td  bgcolor='#CCCCCC' width='120' nowrap>
								&nbsp;
								</td>
								<td  bgcolor='#CCCCCC' width='120' nowrap>
								&nbsp;
								</td>
								<td  bgcolor='#CCCCCC' width='120' nowrap>
								&nbsp;
								</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
