<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
		<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
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
		<jsp:useBean id="menuBean" scope="session" class="com.idn.menu.XPDropDownMenuBean"/>
		<jsp:setProperty name="menuBean" property="reload" value="<%=isReload%>"/>
		<jsp:setProperty name="menuBean" property="purview" value="<%=strPurview%>"/>
		<jsp:setProperty name="menuBean" property="contextPath" value="<%=strContextPath%>"/>
		<jsp:setProperty name="menuBean" property="localeCode" value="<%=strLocaleCode%>"/>
		<jsp:setProperty name="menuBean" property="urlTarget" value="_self"/>
		<script language="javascript">
			var NC6=(navigator.userAgent.indexOf("Netscape6")>0)?true:false;
			var IE=(document.all)?true:false;
			if(IE){
			document.write("<script language=\"javascript\" src=\"<%=request.getContextPath()%>/commjs/XpDropDownmenu.js\"><\/script>");
			}else if(NC6){
			document.write("<script language=\"javascript\" src=\"<%=request.getContextPath()%>/commjs/XpDropDownNCmenu.js\"><\/script>");
			}
		</script>
		<script language="javascript">
			<bean:write name="menuBean" property="menuHtml" filter="false"/>
			mwritetodocument();
			if(NC6){
				mpopOut();
			}
		</script>
	</body>
</html>
