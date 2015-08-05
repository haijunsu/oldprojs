<%@ include file="/common/include.jsp" %>

<html>
	<head>
		<title><bean:message key="index.title"/></title>
		<link href="<bean:write name="req" property="contextPath"/>/html/css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body topmargin="0" leftmargin="0">
	  <div align="center">
		<table border="0" width="100%" height='100%' cellpadding='0' cellspacing='0'>
		  <tbody>
			<tr>
				<td valign='top' height='180' width='100%'>
					<div id="toppage">
						<tiles:insert attribute="top"/>
					</div>
				</td>
			</tr>
			<tr>
				<td align="middle" valign='top' width='100%'>
					<div id="mainpage">
						<tiles:insert attribute="body"/>
					</div>
				</td>
			</tr>
			<tr>
				<td valign='top' height='40' width='100%'>
					<div id="bottompage">
						<tiles:insert attribute="copyright"/>
					</div>
				</td>
			</tr>
		  </tbody>
		 </table>
	   </div>
	</body>
</html>
