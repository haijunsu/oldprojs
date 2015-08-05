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
	String strWelcomeMsg = "";
	String strName = (String)session.getAttribute("username");
	String strId = (String)session.getAttribute("userid");
	if (strId == null) strId = "";
	if (strName == null) strName = "";
	if (!(strId.equals("") && strName.equals("")))
		strWelcomeMsg = "ÄãºÃ£¡" + strId + "/" +strName;
%>
<link href="<%=request.getContextPath()%>/theme/link1.css" type=text/css rel=stylesheet>
<body leftMargin=0 topMargin=1>
<jsp:include page="/welcome/title.jsp" />
<table width="771" height="2" border="0" cellpadding="0" cellspacing="0" bgcolor="#000000">
  <tr>
    <td bgcolor="#000000" class="f20">&nbsp;<font color=#ffffff style='font-size:9pt;'><%=strWelcomeMsg%></font></td>
  </tr>
</table>
</body>