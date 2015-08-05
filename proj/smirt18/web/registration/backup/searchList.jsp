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
<jsp:include page="/registration/search.jsp" flush="true" />
<table width='100%' cellspacing='0' cellpadding='0' border='0'>
	<tr valign='center' align='center' width='100%'>
		<td width='10'>
		&nbsp;&nbsp;
		</td>
		<td width='100%'>
			<!-- search rusult -->
			
			<logic:present name="resultlist">
				<table cellpadding='1' cellspacing='1' border='0'>
					<tr>
						<td align='center'>
						<bean:message key="author.search.list"/>
						</td>
					</tr>
					<tr>
						<td>
							<ol>
							<logic:iterate id="authorVo" name="resultlist">
								<li><a href='<bean:write name="authorVo" property="url" />'>
								<bean:write name="authorVo" property="urlName" />
								</a>
								</li>
							</logic:iterate>
							</ol>
						</td>
					</tr>
				</table>
			</logic:present>
			<p class="errMsg">
			<html:errors bundle="errors"/>
		</td>
	</tr>
</table>
</body>
</html>