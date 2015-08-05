<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page 
language="java"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>
<%
//½ûÖ¹Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<html:html locale="true">

<head>
	<meta name="GENERATOR" content="ËÕº£¾ü">
	<meta http-equiv="Content-Style-Type" content="text/css">
	<link href="<%=request.getContextPath()%>/theme/link1.css" rel="stylesheet" type="text/css">
	<title>
	<bean:message key="logon.title"/>
	</title>
</head>
<body>
	<html:errors property="system.logon.error"/>
	<center> 
	<%
		String strUsername = (String)session.getAttribute("username");
		String strUserid = (String) session.getAttribute("userid");
		String urlname = (String) session.getAttribute("urlname");
		session.removeAttribute("urlname");
	%>
		<table cellspacing="0" cellpadding="0" width="177" align="center" border="0">
			<html:form action="/logon" focus="userid" method="post" onsubmit="return validateLogonForm(this);" target="_top"> 
				<tr>
					<td colspan="3"><img height="9" src="<%=request.getContextPath()%>/img/newb2b_l1.gif" width="177"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#a7a7a7"></td>
					<td width="175" bgcolor="#ffffff">
					<table width="175" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="42%" height="27">
								<div class="huibianhong" align="right">
									<font size="2"><bean:message key="vendor.id"/></font></div>
								</td>
								<td width="58%">
								<div align="center">
									<font size="2">
									<html:hidden property="ignoreCase" value="true"/>
							       	<html:text property="userid" 
							       			style="font-size: 12px; font-family:Arial"
								        	size="10" 
								        	maxlength="10" 
								        	/> 
									</font></div>
								</td>
							</tr>
							<tr>
								<td class="huibianhong" height="27">
								<div align="right">
									<font size="2"><bean:message key="vendor.passowrd"/></font></div>
								</td>
								<td>
								<div align="center">
									<font size="2">
						        	<html:password property="password" 
						        			style="font-size: 12px; font-family:Arial"
											size="10" 
											maxlength="10"
							        		redisplay="false"/>
									</font></div>
								</td>
							</tr>
							<tr>
								<td valign="bottom" colspan="2" height="20">
								<div align="center">
									<input type="image" height="19" width="45" src="<%=request.getContextPath()%>/img/newb2b_d1.gif" name="Submit">&nbsp;&nbsp;
									<input name="image" type="image" onclick="document.logonForm.reset();return false;" src="<%=request.getContextPath()%>/img/newb2b_d2.gif" width="45" height="19">
								</div>
								</td>
							</tr>
					</table>
					</td>
					<td width="1" bgcolor="#a7a7a7"></td>
				</tr>
				<tr>
					<td colspan="3"><img height="9" src="<%=request.getContextPath()%>/img/newb2b_l2.gif" width="177"></td>
				</tr>
			</html:form> 
		</table>
	<html:javascript formName="logonForm"
	        dynamicJavascript="true"
	        staticJavascript="false"/>
	<script language="Javascript1.1" src="<%=request.getContextPath()%>/staticJavascript.jsp"></script>
	</center> 
</body>
</html:html>
