<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
		<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
		<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
		<%@ page 
			language="java"
			contentType="text/html; charset=GB2312"
			pageEncoding="GB2312"
		%>
		<% 
			response.setHeader("Pragma","No-cache"); 
			response.setHeader("Cache-Control","no-cache"); 
			response.setDateHeader("Expires", 0); 
		%>
		<meta http-equiv="Cache-Control" content="No-cache">
		<meta http-equiv="Pragma" content="No-cache">
		<meta name="author" content="苏海军">
		<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
		<meta http-equiv="Content-Style-Type" content="text/css">
		<title><bean:message key="menus.title"/></title>
		<link href="<%=request.getContextPath()%>/theme/homeworld.css" rel="stylesheet" type="text/css">
		<script  src='<%=request.getContextPath()%>/commjs/homeworld.js'></script>
	</head>
	<body onmouseover="self.status='家世界集团供货商业务系统 http://vendor.thehomeworld.com';return true" 
	vLink=#333333 aLink=#333333 link=#333333 bgColor=#fefeff leftMargin=0 
	background="" topMargin=0>
		<%
			String strUsername = (String)session.getAttribute("username");
			String strPurview = (String)session.getAttribute("purview");
			String strReload = (String)session.getAttribute("menuReload");
			String strLocaleCode = (String)session.getAttribute("localeCode");
			String strContextPath = request.getContextPath();
			if (strPurview == null) strPurview = "0";
			if (strReload == null) strReload = "true";
			boolean isReload = strReload.equals("true");
			session.setAttribute("menuReload", "false");
			if (strLocaleCode == null) strLocaleCode = "1";
		%>
		<jsp:useBean id="menuBean" scope="session" class="com.idn.menu.StandardMenuBean"/>
		<jsp:setProperty name="menuBean" property="reload" value="<%=isReload%>"/>
		<jsp:setProperty name="menuBean" property="purview" value="<%=strPurview%>"/>
		<jsp:setProperty name="menuBean" property="contextPath" value="<%=strContextPath%>"/>
		<jsp:setProperty name="menuBean" property="localeCode" value="<%=strLocaleCode%>"/>
		<table borderColor=#000033 
			height=25 
			cellSpacing=0 
			cellPadding=0 
			width="97%" 
			align=center 
			bgColor=#8888b0 
			background="" 
			border=1>
			  <tbody>
				  <tr>
					<td>
						  <div align=left>
							  	<font face=宋体>
								  <a href="<%=request.getContextPath()%>/">
								  	<img height=16 src="<%=request.getContextPath()%>/img/homepage.gif" width=16 border=0>
								  </a> 『 
								  <%
								  	if(strPurview.equals("0")) {
								  %>
								 			   <a href="<%=request.getContextPath()%>/help/syshelp.html">帮助</a>
								 	  <% 
								 	  	} else {
								 	  %>	
								 			   <logic:iterate name="menuBean" id="myMenuItem" property="menuArray" indexId="index">
								<a href=<bean:write name="myMenuItem" property="url"/> target=<bean:write name="menuBean" property="urlTarget"/>><bean:write name="myMenuItem" property="name"/></A> | 
								</logic:iterate>
								   			   <a href="<%=request.getContextPath()%>/help/syshelp.html">帮助</a> | 
								  			   <a href="<%=request.getContextPath()%>/logoff.do">退出</a> 
								 <%
								 	}
								 %>
								 』		
							</font>
						</div>
					</td>
					</tr>
			</tbody>
		</table>
	</body>
</html>
