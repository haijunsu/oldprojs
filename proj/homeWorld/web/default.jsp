<!-- Copyright (c) 2002 by ObjectLearn. All Rights Reserved. -->
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
long _lltime = System.currentTimeMillis();
System.out.println(_lltime + " - default.jsp start");
%>


<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<tiles:insert page="/layouts/crisIndexLayout.jsp" flush="true">
<tiles:put name="titlePage" value="/welcome/title.jsp"/>
<tiles:put name="menuPage" value="/menus/crisStaticMenu.jsp"/>
<tiles:put name="bodyPage" value="/welcome/body.jsp"/>
<tiles:put name="announcePage" value="/searchbbsFirst.do?queryid=DQ_TOPIC&actiondo=homeworldTopicLook.do"/>
<tiles:put name="logonPage" value="/welcome/logon.jsp"/>
<tiles:put name="copyrightPage" value="/welcome/copyright.jsp"/>
</tiles:insert>
<%
System.out.println(_lltime + " - default.jsp end. total time(ms): " + (System.currentTimeMillis() - _lltime));
%>
