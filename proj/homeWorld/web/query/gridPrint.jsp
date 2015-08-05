
<%@ page language="java"
 contentType="text/html; charset=GBK"
 pageEncoding="GBK"%>
<% 
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
%> 
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<jsp:useBean id="qc" scope="request" class="com.idn.query.QueryContents"/>


<html:html locale="true">
	<HEAD>

<META http-equiv="Content-Type" content="text/html; charset=GBK">
<META HTTP-EQUIV="Cache-Control" CONTENT="No-cache">
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<LINK href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css">
		
		<TITLE><bean:write name="qc" property="htmlHeader" filter="false"/></TITLE>
	</HEAD>
<script language="javascript">
    OLECMDID_PRINT = 6
    OLECMDID_PRINTPREVIEW = 7
    OLECMDID_PAGESETUP = 8
    OLECMDEXECOPT_DONTPROMPTUSER = 2
    OLECMDEXECOPT_PROMPTUSER = 1
    
	function javaPrint(oleCmdId, oleCmdExecopt) {
		document.all.divPrint.style.visibility = "hidden";
		document.body.focus();
		IEWB.ExecWB(oleCmdId, oleCmdExecopt);
		document.all.divPrint.style.visibility = "visible";
	}
	
	function setVariables() {
		object = "divPrint";
		if (navigator.appname=="Netscape") {
			v=".top=";
			dS="document.";
			sD="";
			y="window.pageYOffset";
		} else {
			v=".pixelTop=";
			dS="";
			sD=".style";
			y="document.body.scrollTop";
		}
	}
	
	function checkLocation() {
		yy=eval(y);
		eval(dS + object + sD + v +yy);
		setTimeout("checkLocation()", 10);
		
	}
	
</script>

<Script Language='JavaScript' For='window' event='onbeforeprint'>
   document.all.divPrint.style.visibility = "hidden";
   return true;
</Script>
<Script Language='JavaScript' For='window' event='onafterprint'>
   document.all.divPrint.style.visibility = "visible";
   return true;
</Script>


<BODY onload="setVariables();checkLocation(); this.focus()">
<object id="IEWB" width=0 height=0 classid="clsid:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
<div id="divPrint" style="position:absolute; visibility:visible; right:0px; top:0px; z-index:5">
<form>
<table cellSpacing="0" cellPadding="0" border="1" bgcolor="#3366FF">
<tr>
<td>
<input type="button" onClick="javaPrint(OLECMDID_PAGESETUP, OLECMDEXECOPT_DONTPROMPTUSER)" value="页面设置">
<input type="button" onClick="javaPrint(OLECMDID_PRINTPREVIEW, OLECMDEXECOPT_PROMPTUSER)" value="打印预览">
<input type="button" onClick="javaPrint(OLECMDID_PRINT, OLECMDEXECOPT_DONTPROMPTUSER)" value="打印">
</td>
</tr>
</table>
</form>
</div>

<center>
		<H2 class="printFont"><bean:write name="qc" property="htmlTitle" filter="false"/></H2>
		<bean:write name="qc" property="gridHtml" filter="false"/>
</center>
	</BODY>

</html:html>
