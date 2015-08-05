<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
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
<%
	String strBody = "/index.jsp?body=" + com.idn.util.ServletTools.getParameter(request, "body", false, "");
%>
<html:html locale="true">
<html:base/>
<head>
	<meta name="GENERATOR" content="ËÕº£¾ü">
	<meta http-equiv="Content-Style-Type" content="text/css">
	<title>
	<bean:message key="index.title"/>
	</title>
</head>
<frameset rows="*, 0" frameborder="NO" border="0" framespacing="0">
	<html:frame page="<%=strBody%>" frameName="body" scrolling="auto"/>
    <html:frame page="/welcome/checkState.jsp" frameName="check" scrolling="no" noresize="true"/>
</frameset>
<noframes>
</noframes> 
</html:html>