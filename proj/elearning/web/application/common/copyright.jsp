<!-- page encoding -->
<%@ page 
language="java"
isErrorPage="false"
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
		<title><bean:message key="index.title"/></title>
		<link href='<bean:write name="rq" property="contextPath"/>/style.css' rel="stylesheet" type="text/css">
	</head>
	<body>
		<table width='100%' cellspacing='0' cellpadding='0' border='0'>
			<tr valign='top' align='center' width='100%'>
				<td width='100%'>
					HTYZ, Huantian.com.cn<br>
					Copyright &copy; 2005 All Rights Reserved. 
				</td>
			</tr>
		</table>
	</body>
</html>
