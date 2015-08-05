 <%@ page 
language="java"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%
//½ûÖ¹Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>       <html>
		<html:base />
        <%
 			String strUsername = (String)session.getAttribute("userid");
			String redirectUrl = request.getContextPath();
 			String strCheckName = com.idn.util.ServletTools.getParameter(request, "checkName", false, "");
 			if (strCheckName.equals("") && strUsername != null) {
 			    strCheckName = strUsername;
 			    redirectUrl += "/welcome/checkState.jsp?checkName=" + strCheckName;
 			} else {
 			    if (!strCheckName.equals(strUsername)) {
 			    %>
                    <script language="Javascript">
					    parent.location = "<%=redirectUrl%>";
					</script>
 			    <%
 			    } else {
 			    	redirectUrl += "/welcome/checkState.jsp?checkName=" + strCheckName;
 			    }
 			}
        %>
		
        <head>
        <style type="text/css">
        <!--
        a {color:#333333;font-size: 9pt;text-decoration:none; }
        a:hover {color:#000066;text-decoration:underline overline;}
        td{FONT-SIZE: 9pt; color:#333333; font-family:ËÎÌå}
        -->
        </style>
        <title>CheckState</title>
        <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
        <!--meta HTTP-EQUIV=REFRESH CONTENT='<bean:message key="check.state.interval"/>; URL=<%=redirectUrl%>'-->
        </head>
        <body>
        <%=strUsername%><br>
        <%=strCheckName%>
        </body>
        </html>