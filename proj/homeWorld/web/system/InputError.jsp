<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page 
language="java"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>
<html:html locale="true">

<HEAD>
<META HTTP-EQUIV="Cache-Control" CONTENT="No-cache">
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META http-equiv="Content-Type" content="text/html; charset=GB2312">
<META name="XIAOAI" content="Eclipse">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css">
<TITLE><bean:message key="system.errortitle"/></TITLE>
</HEAD>
<BODY>
<jsp:useBean  id="siev" scope="request" class="system.action.ErrorValue"/>
<%if (siev.getFlag().equals("0")) {%>
	<center><H2>������</H2></center>
	<% out.println((new java.util.Date()).toString()); %>
	<p>�����ܣ�<%=siev.getClassName()%></p>
	<p>�����֣�<%=siev.getFunName()%></p>
	<p>�������ݣ�<%=siev.getErrorDetail()%></p>
<%}else{%>
	<center><H2>�ɹ�����</H2></center>
	<% out.println((new java.util.Date()).toString()); %>
	<p><%=siev.getErrorDetail()%></p>	
<%}%>


</BODY>
</html:html>
