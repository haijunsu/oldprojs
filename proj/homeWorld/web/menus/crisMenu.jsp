<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page 
	language="java"
	contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"
%>
<% 
	response.setHeader("Pragma","No-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0); 
%>

<html:html>

<meta http-equiv="Cache-Control" content="No-cache">
<meta http-equiv="Pragma" content="No-cache">
<meta name="author" content="苏海军">
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<meta http-equiv="Content-Style-Type" content="text/css">
<title><bean:message key="menus.title"/></title>
<style type=text/css>
body  { background:#EE0313; margin:0px; font:9pt 宋体; }
table  { border:0px; }
td  { font:normal 12px 宋体; }
img  { vertical-align:bottom; border:0px; }
a  { font:normal 12px 宋体; color:#000000; text-decoration:none; }
a:hover  { color:#428EFF;text-decoration:underline; }
.sec_menu  { border-left:1px solid white; border-right:1px solid white; border-bottom:1px solid white; overflow:hidden; background:F9C1C5; }
.menu_title  { }
.menu_title span  { position:relative; top:2px; left:8px; color:#215DC6; font-weight:bold; }
.menu_title2  { }
.menu_title2 span  { position:relative; top:2px; left:8px; color:#428EFF; font-weight:bold; }
</style>
<SCRIPT language=javascript1.2>
function showsubmenu(sid)
{
whichEl = eval("submenu" + sid);
if (whichEl.style.display == "none")
{
eval("submenu" + sid + ".style.display=\"\";");
}
else
{
eval("submenu" + sid + ".style.display=\"none\";");
}
}
</SCRIPT>
<%
	String strReqid = request.getParameter("userid");
	String strId = (String)session.getAttribute("userid");
	String strUsername = (String)session.getAttribute("username");
	String strPurview = (String)session.getAttribute("purview");
	String strReload = (String)session.getAttribute("menuReload");
	String strLocaleCode = (String)session.getAttribute("localeCode");
	String strContextPath = request.getContextPath();
	if (strPurview == null) strPurview = "0";
	if (strReload == null) strReload = "true";
	boolean isReload = strReload.equals("true");
	session.setAttribute("menuReload", "false");
	if (strLocaleCode == null) strLocaleCode = "1";
%>
<%
	if (strReqid == null || strId == null || !strId.equals(strReqid)) {
%>
		<script language='javascript'>
			window.location='<%=request.getContextPath()%>/index.jsp?body=/welcome/logon.jsp';
		</script>
<%
	}
%>

<jsp:useBean id="menuBean" scope="session" class="com.idn.menu.CrisMenuBean"/>
<jsp:setProperty name="menuBean" property="reload" value="<%=isReload%>"/>
<jsp:setProperty name="menuBean" property="purview" value="<%=strPurview%>"/>
<jsp:setProperty name="menuBean" property="contextPath" value="<%=strContextPath%>"/>
<jsp:setProperty name="menuBean" property="localeCode" value="<%=strLocaleCode%>"/>
<jsp:setProperty name="menuBean" property="urlTarget" value="right"/>
<BODY leftmargin="0" topmargin="0" marginheight="0" marginwidth="0">
<table cellpadding=0 cellspacing=0 width=158 border='0'>
	<tr>
		  <td valign=top> 
    		<table cellpadding=0 cellspacing=0 width=158 align=center height="42">
      			<tr> 
        			<td height=25 class=menu_title onMouseOver=this.className='menu_title2'; onMouseOut=this.className='menu_title'; background=<%=request.getContextPath()%>/img/menu_title.gif> 
          				<span></span></td>
      			</tr>
    		</table>
    		<bean:write name="menuBean" property="menuHtml" filter="false"/>
    	  </td>
	</tr>
</table>
</body>
</html:html>