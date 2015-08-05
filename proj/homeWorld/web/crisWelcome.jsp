<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page 
language="java"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>
<%
long _lltime = System.currentTimeMillis();
System.out.println(_lltime + " - crisWelcome.jsp start");
%>
<%
//½ûÖ¹Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<%
	String strId = (String)session.getAttribute("userid");
	String strReqid = request.getParameter("userid");
	String strMenu = "/menus/crisMenu.jsp?userid=" + strReqid;
	String strRight = "/index.jsp?body=/searchbbsFirst.do?queryid=DQ_TOPIC&actiondo=homeworldTopicLook.do";
	if (strId == null || strReqid == null) {
		strRight = "/index.jsp?body=/welcome/logon.jsp";
	} else {
		String strFailUrl = (String)session.getAttribute("failUrl");
		if (strFailUrl != null) {
			strRight = strFailUrl;
			session.removeAttribute("failUrl");
		}
	}
%>
<%
	if (strReqid == null || strId == null || !strId.equals(strReqid)) { 
%>
		<script language='javascript'>
			window.location='<%=request.getContextPath() + strRight%>';
		</script>
<%
	}
%>
<html:html locale="true">

<head>
	<meta name="GENERATOR" content="ËÕº£¾ü">
	<meta http-equiv="Content-Style-Type" content="text/css">
	<title>
	<bean:message key="index.title"/>
	</title>
</head>
<frameset rows="123, *" frameborder="NO" border="0" framespacing="0">
	<html:frame page="/welcome/title1.jsp" frameName="top" scrolling="no"/>
   <frameset framespacing="0" border="false" cols="180,10,*" frameborder="0" name="main">
  
	<html:frame page="<%=strMenu%>" frameName="left" scrolling="auto" noresize="true"/>
    <html:frame page="/layouts/changewin.html" frameName="change" scrolling="no" noresize="true"/>
	<%
		if (strRight.startsWith("/")) {
	%>
		<html:frame page="<%=strRight%>" frameName="right" scrolling="auto"/>
	<%
		} else {
	%>
		<frame name="right" scrolling="auto" src="<%=strRight%>">
	<%
		}
	%>
  </frameset>
</frameset>
<noframes>
</noframes> 
</html:html>
<%
System.out.println(_lltime + " - crisWelcome.jsp end. total time(ms): " + (System.currentTimeMillis() - _lltime));
%>