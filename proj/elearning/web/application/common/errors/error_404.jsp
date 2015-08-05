<!-- page encoding -->
<%@ page 
language="java"
isErrorPage="true"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>

<%@ taglib uri="/taglibs/struts-html" prefix="html" %>
<%@ taglib uri="/taglibs/struts-bean" prefix="bean" %>
<%@ taglib uri="/taglibs/struts-logic" prefix="logic" %>
<%@ taglib uri="/taglibs/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/taglibs/struts-nested" prefix="nested" %>
<%@ taglib uri="/taglibs/taglibs-log" prefix="log" %>
<%@ taglib uri="/taglibs/taglibs-request" prefix="req" %>
<%
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>

<%
/**
Request Information:
AuthType: authType
ContextPath: contextPath
Method: method
PathInfo: pathInfo
PathTranslated: pathTranslated
QueryString: queryString
RemoteUser: remoteUser
RequestedSessionId: requestedSessionId
RequestURI: requestURI
RequestURL: requestURL
ServletPath: servletPath
CharacterEncoding: characterEncoding
ContentLength: contentLength
ContentType: contentType
Protocol: protocol
RemoteAddr: remoteAddr
RemoteHost: remoteHost
Scheme: scheme
ServerName: serverName
ServerPort: serverPort
**/
%>
<req:request id="rq"/>
<html>


<head>
<meta name="GENERATOR" content="su_haijun">
<meta http-equiv="Content-Style-Type" content="text/css">
		<link href='<bean:write name="rq" property="contextPath"/>/style.css' rel="stylesheet" type="text/css">
<title>
<bean:message key="errors.page.title" />
</title>

</head>
<body bgcolor="#FFFFFF">
<table><tr><td>
<html:img  alt="error image" page="/application/common/errors/imgs/error.gif" />
</td>
<td>
<p class="errTitle"><bean:message key="errors.title"/></p>
<p class="errMsg">
<bean:message key="errors.404.msg"/>
</p>
</td>
</tr>
</table>
<p align='center'>
	<html:link page="/"><bean:message key="return.home" /></html:link>
</p>

<jsp:include page="/application/common/copyright.jsp" flush="true" />
</body>
</html>