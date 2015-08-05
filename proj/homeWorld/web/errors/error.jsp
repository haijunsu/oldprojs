<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page 
language="java"
isErrorPage="true"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<html:html locale="true">


<head>
<meta http-equiv="Cache-Control" content="No-cache">
<meta http-equiv="Pragma" content="No-cache">
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<meta name="GENERATOR" content="苏海军">
<meta http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css">
<title>
<bean:message key="errors.title"/></title>

</head>
<body bgcolor="#FFFFFF">
<table><tr><td><img height="228" src="<%=request.getContextPath()%>/img/error.gif" width="180"></td>
<td><html:errors/></td>
</tr></table>
</body>
</html:html>