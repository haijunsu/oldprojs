<%@ page contentType="text/html;charset=GB2312" language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>

<html>
<head>
<title><tiles:getAsString name="title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel=stylesheet href="<%=request.getContextPath()%>/css/public.css" type="text/css">
<LINK REL="SHORTCUT ICON" HREF="<%=request.getContextPath()%>/g-roller.ico">
</head>

<body>

<table cellpadding="0" cellspacing="1" class="template">
  <tr >
    <td colspan="2"  class="top"><tiles:insert attribute="top"/></td>
  </tr>
  <tr>
    <td colspan="2" class="header"><tiles:insert attribute="header"/></td>
  </tr>
  <tr>
    <td class="menu"><tiles:insert attribute="menu"/></td>
    <td class="content"><tiles:insert attribute="content"/></td>
  </tr>
  <tr>
    <td colspan="2" class="footer"><tiles:insert attribute="footer"/></td>
  </tr>
</table>

</body>
</html>