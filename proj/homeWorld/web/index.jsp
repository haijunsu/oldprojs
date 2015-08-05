<!-- Copyright (c) 2002 by ObjectLearn. All Rights Reserved. -->
<%@ page 
language="java"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>
<%
//��ֹCache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%
long _lltime = System.currentTimeMillis();
System.out.println(_lltime + " - index.jsp start");
%>

<%
	String strPosition = request.getParameter("urlname");
	if (strPosition != null) {
		session.setAttribute("urlname", strPosition);
	}
	String mainPage = request.getParameter("body");
	if (mainPage == null)
		mainPage = "/welcome/logon.jsp";
%>

<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<tiles:insert page="/layouts/crisRightLayout.jsp" flush="true">
<tiles:put name="bodyPage" value="<%=mainPage%>"/>
<tiles:put name="copyrightPage" value="/welcome/copyright.jsp"/>
</tiles:insert>
<%
System.out.println(_lltime + " - index.jsp end. total time(ms): " + (System.currentTimeMillis() - _lltime));
%>
