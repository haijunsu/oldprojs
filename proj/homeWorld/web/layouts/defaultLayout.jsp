<!-- Copyright (c) 2002 by ObjectLearn. All Rights Reserved. -->
<!-- 家世界显示模板 -->
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<html>
	<head>
		<title><bean:message key="index.title"/></title>
		<link href="<%=request.getContextPath()%>/theme/homeworld.css" rel="stylesheet" type="text/css">
		<script  src='<%=request.getContextPath()%>/commjs/homeworld.js'></script>
	</head>
	<body>
		<table border="0" width="100%" cellpadding='0' cellspacing='0'>
			<tr>
				<td>
					<tiles:insert attribute="mainMenu"/>
				</td>
			</tr>
			<tr>
				<td>
					<tiles:insert attribute="logonPage"/>
				</td>
			</tr>
			<tr>
				<td>
					<tiles:insert attribute="body"/>
				</td>
			</tr>
			<tr>
				<td>
					<tiles:insert attribute="copyright"/>
				</td>
			</tr>
		</table>
	</body>
</html>
