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
		<title><bean:message key="index.title"/></title>
		<link href='<bean:write name="rq" property="contextPath"/>/style.css' rel="stylesheet" type="text/css">
	</head>
	<body topmargin="0" leftmargin="0">
		<table border="0" width="796" height='100%' cellpadding='0' cellspacing='0'>
		  <tbody>
			<tr>
				<!--
				<td valign='top' height='100%' width='180'>
					<tiles:insert attribute="left"/>
				</td>
				-->
				<td height='5' valign='top' width='100%'>
					<table border="0" width="100%" height='100%' cellpadding='0' cellspacing='0'>
					  <tbody>
					  <!--
						<tr>
							<td valign='top' height='20'>
								<tiles:insert attribute="top"/>
						</tr>
						-->
						<tr height='100%' valign='top'>
							<td>
								<tiles:insert attribute="search"/>
								<tiles:insert attribute="body"/>
							</td>
						</tr>
						<tr>
							<td height='15' valign='top'>
								<tiles:insert attribute="copyright"/>
							</td>
						</tr>
					  </tbody>
					</table>
				</td>
			</tr>
		  </tbody>
		 </table>
	</body>
</html>
