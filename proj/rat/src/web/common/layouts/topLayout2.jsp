<%@ include file="/common/include.jsp" %>


<html>
	<head>
		<title><bean:message key="index.title"/></title>
		<link href="<bean:write name="req" property="contextPath"/>/html/css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" src="<bean:write name="req" property="contextPath"/>/html/script/util.js"></script>
	</head>
	<body>
		<table id="Table_01" width="100%" height="86" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="135"><img src="<bean:write name="req" property="contextPath"/>/html/image/logo.gif"></td>
				<td width="100%">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"  background="<bean:write name="req" property="contextPath"/>/html/image/TOP_pic-04.gif">
						<tr>
							<td height="45" align="left" background="<bean:write name="req" property="contextPath"/>/html/image/TOP_pic-01.gif" class="CompanyName"> <tiles:insert attribute="titlePage"/></td>
						    <td height="45" colspan="2" valign="bottom" background="<bean:write name="req" property="contextPath"/>/html/image/TOP_pic-01.gif" class="Date"><div align="right"><script>showIsoColck();</script></div></td>
						</tr>
						<tr>
							<td align="left" background="<bean:write name="req" property="contextPath"/>/html/image/TOP_pic-04.gif">
							<div align="left"><img src="<bean:write name="req" property="contextPath"/>/html/image/TOP_pic-02.gif" width="224" height="39" alt=""></div></td>
						    <td background="<bean:write name="req" property="contextPath"/>/html/image/TOP_pic-04.gif" class="UserName">
						  	<div align="right">
								&nbsp;&nbsp;
							</div>
						  </td>
					      <td valign="middle" background="<bean:write name="req" property="contextPath"/>/html/image/TOP_pic-04.gif" class="UserName">
					      <div align="right">
					      	<span class="Logout">
								&nbsp;
					      	</span>
					      </div>
					      </td>
					   </tr>
			      </table>
		      </td>
		    </tr>
			<tr>
				<td height="69" colspan="2" background="<bean:write name="req" property="contextPath"/>/html/image/TOP_pic-04.gif"><img src="<bean:write name="req" property="contextPath"/>/html/image/TOP_banner.jpg" width="770" height="69" alt=""></td>
			</tr>
		</table>
	</body>
</html>
