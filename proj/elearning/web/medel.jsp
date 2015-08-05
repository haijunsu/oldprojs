<%@page contentType="text/html;charset=GB2312"%>
<jsp:useBean id="ust" scope="request" class="system.userStyle" />
<%ust.setUserStyle(session.getAttribute("userid"));%
<head>
<title><font color=<%=ust.getTitlecolor()%> size=<%=ust.getDefaulttitlefont()%> >Untitled Document</font></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<style type="text/css">
a:link {
	font-family: "宋体";
	font-size: 9pt;
	color: <%=ust.getLinkcolor()%>;
	text-decoration: none;
}
a:hover {
	font-family: "宋体";
	font-size: 9pt;
	color: <%=ust.getOnmousecolor()%>;
	text-decoration: underline;
}
a:visited {
	font-family: "宋体";
	font-size: 9pt;
	color: <%=ust.getLinkstyle()%>;
	text-decoration: none;
}

</style>
</head>
>
<body bgcolor="<%=ust.getBgcolor()%>">
<table width="<%=ust.getTabelwidth()%>" border="1" cellspacing="0" cellpadding="0">
  <tr bgcolor=<%=ust.getFirstrowcolor()%>>
    <td ><font color=<%=ust.getFontcolor()%>>&nbsp;</font></td>
    <td><font face=<%=ust.Font()%> size=<%=ust.getDefaultfont()%>>&nbsp;</font></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
</html>
