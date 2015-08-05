
<%@ page language="java"
 contentType="text/html; charset=GBK"
 pageEncoding="GBK"%>
 
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<jsp:useBean id="qc" scope="request" class="com.idn.query.QueryContents"/>


<html:html locale="true">
	<HEAD>

<META http-equiv="Content-Type" content="text/html; charset=GBK">
<META HTTP-EQUIV="Cache-Control" CONTENT="No-cache">
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<LINK href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css">
		
		<TITLE><bean:write name="qc" property="htmlHeader" filter="false"/></TITLE>
	</HEAD>

	<BODY>
<H2><bean:write name="qc" property="htmlTitle" filter="false"/></H2>
<bean:write name="qc" property="freeFormHtml" filter="false"/>
	</BODY>
</html:html>
