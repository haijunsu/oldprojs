<%@ include file="/common/include.jsp" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title><bean:message key="index.title"/></title>
		<link href="<bean:write name="req" property="contextPath"/>/css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body topmargin="0" leftmargin="0">
	  <div align="center">
		<table border="0" width="100%" height='100%' cellpadding='0' cellspacing='0'>
		  <tbody>
			<tr>
				<td valign='top' height='0' width='100%'>
					<div id="toppage">
						<tiles:insert attribute="top"/>
					</div>
				</td>
			</tr>
			<tr>
				<td valign='top' width='100%'>
					<table border="0" width="100%" height='100%' cellpadding='0' cellspacing='0'>
					  <tbody>
						<tr>
							<td valign='top' height='100%' width="160">
								<div id="menupage">
									<tiles:insert attribute="left"/>
								</div>
							</td>
							<td valign='top' width="100%">
								<table border="0" width="100%" height='100%' cellpadding='0' cellspacing='0'>
								  <tbody>
									<tr>
										<td height='100%' nowrap>
											&nbsp;&nbsp;&nbsp;
										</td>
										<td valign='top' height='100%' width="100%">
											<div id="mainpage">
												<tiles:insert attribute="body"/>
											</div>
										</td>
										<td height='100%' nowrap>
											&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
									<tr>
										<td rowspan="3">
											&nbsp;
										</td>
									</tr>
								  </tbody>
								</table>
							</td>
						</tr>
					  </tbody>
					</table>
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
