<%@ page 
language="java"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>
<!-- Copyright (c) 2002 by ObjectLearn. All Rights Reserved. -->
<!-- 家世界显示模板 -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<html:html>

<head>
<title><bean:message key="index.title"/></title>
<META http-equiv=Content-Type content="text/html; charset=gb2312">

<link href="<%=request.getContextPath()%>/theme/link1.css" type=text/css rel=stylesheet>
</head>
<body link=#333333 leftMargin=0 topMargin=1>
<div align=left>
    <table id="maintable" name="maintable" cellSpacing=0 cellPadding=0 width=590 border=0>
      <tbody>
        <tr> 
        	<td height="300" valign='center' align='center'>
					<tiles:insert attribute="bodyPage"/>
        	</td>
        </tr>
        <tr> 
        	<td>
					<tiles:insert attribute="copyrightPage"/>
        	</td>
        </tr>
      </tbody>
    </table>
</div>    
</body>
</html:html>
