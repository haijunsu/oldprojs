<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
<html:html locale="true">

<HEAD>

<META HTTP-EQUIV="Cache-Control" CONTENT="No-cache">
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META http-equiv="Content-Type" content="text/html; charset=GB2312">
<META name="XIAOAI" content="Eclipse">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>当前SESSION数</TITLE>

<script language="Javascript1.2">

	
</script>

</HEAD>
<BODY>
<center><H2>当前SESSION数</H2></center>
<%java.util.Calendar c =java.util.Calendar.getInstance();%>
<%String s_now = "";%>
<%s_now = Integer.toString(c.get(java.util.Calendar.YEAR));%>
<%s_now = s_now + "-";%>
<%s_now = s_now + Integer.toString(c.get(java.util.Calendar.MONTH) + 1);%>
<%s_now = s_now + "-";%>
<%s_now = s_now + Integer.toString(c.get(java.util.Calendar.DATE));%>
<%s_now = s_now + " ";%>
<%s_now = s_now + Integer.toString(c.get(java.util.Calendar.HOUR_OF_DAY));%>
<%s_now = s_now + ":";%>
<%s_now = s_now + Integer.toString(c.get(java.util.Calendar.MINUTE));%>
<%s_now = s_now + ":";%>
<%s_now = s_now + Integer.toString(c.get(java.util.Calendar.SECOND));%>
<%s_now = s_now + ".";%>
<%s_now = s_now + Integer.toString(c.get(java.util.Calendar.MILLISECOND));%>
<CENTER><% out.println(s_now); %></CENTER>

当前的SESSION数为:
<% 
int count=commsearch.session.SessionCount.getCount(); 
out.println(count); 
%>

</BODY>
</html:html>
