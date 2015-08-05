<!-- page encoding -->
<%@ page 
language="java"
isErrorPage="false"
contentType="text/html; charset=GB2312
pageEncoding="GB2312
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
<html:html>
<head>
<meta http-equiv="Content-Style-Type" content="text/css">
		<link href='<bean:write name="rq" property="contextPath"/>/style.css' rel="stylesheet" type="text/css">
<title>
<bean:message key="success.page.title"/></title>

</head>
<body>
<table><tr><td>
<html:img  alt="success image" page="/common/success/imgs/success.jpg" />
</td>
<td>
<p class="successTitle"><bean:message key="success.title"/></p>
<p class="successMsg">
<html:messages message="true" id="msg" header="messages.header" footer="messages.footer">
	<bean:write name="msg" />
</html:messages>
</td>
</tr>
<tr>
<td colspan='2' align='center'>
	<logic:present name="backBtn">
		<bean:write name="backBtn" property="button" filter="false"/>
	</logic:present>
	<logic:notPresent name="backBtn">
	<html:link page="/"><bean:message key="return.home" /></html:link>
	</logic:notPresent>
</td>
</tr></table>

</body>
</html:html>