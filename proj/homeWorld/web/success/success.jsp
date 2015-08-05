<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page 
language="java"
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
<bean:message key="success.title"/></title>
</head>
<body bgcolor="#FFFFFF">
  <ul>  
  <html:messages id="message" message="true">     
  <li><bean:write name="message"/></li>  
  </html:messages>  
  </ul>
</body>
</html:html>