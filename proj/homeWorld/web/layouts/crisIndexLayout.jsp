<!-- Copyright (c) 2002 by ObjectLearn. All Rights Reserved. -->
<!-- 家世界显示模板 -->
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<html>
<head>
<title><bean:message key="index.title"/></title>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<link href="<%=request.getContextPath()%>/theme/link1.css" type=text/css rel=stylesheet>
</head>
<body link=#333333 leftMargin=0 topMargin=1>
<div align=center>
    <table cellSpacing=0 cellPadding=0 width=771 border=0>
      <tbody>
        <tr> 
        	<td>
					<tiles:insert attribute="titlePage"/>
        	</td>
        </tr>
        <tr> 
        	<td>
					<tiles:insert attribute="menuPage"/>
        	</td>
        </tr>
        <tr> 
        	<td>
					<tiles:insert attribute="bodyPage"/>
        	</td>
        </tr>
        <tr> 
        	<td>
        	<table width=771 height=120 border=0 cellPadding=0 cellSpacing=0 background="<%=request.getContextPath()%>/img/di.gif">
		        <tr valign='middle' align='center'> 
		        	<td width="516">
							<tiles:insert attribute="announcePage"/>
		        	</td>
		        	<td width="255">
							<tiles:insert attribute="logonPage"/>
		        	</td>
		        </tr>
             </table>
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
</html>
