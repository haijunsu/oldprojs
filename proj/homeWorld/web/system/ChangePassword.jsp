<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page 
language="java"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>
<html:html locale="true">

<HEAD>

<META HTTP-EQUIV="Cache-Control" CONTENT="No-cache">
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META http-equiv="Content-Type" content="text/html; charset=GB2312">
<META name="XIAOAI" content="Eclipse">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css">
<TITLE><bean:message key="system.errortitle"/></TITLE>

<script language="Javascript1.2"  >

function callfun(){
  SalaryInputSaveForm.submit();
}
</script>

</HEAD>
<BODY>
<center><H2>用户口令修改画面</H2></center>
<%java.util.Calendar c =java.util.Calendar.getInstance();%>
<%String s_now = "";%>
<%s_now = Integer.toString(c.get(java.util.Calendar.YEAR));%>
<%s_now = s_now + "-";%>
<%s_now = s_now + Integer.toString(c.get(java.util.Calendar.MONTH) + 1);%>
<%s_now = s_now + "-";%>
<%s_now = s_now + Integer.toString(c.get(java.util.Calendar.DATE));%>
<%s_now = s_now + " ";%>
<%s_now = s_now + Integer.toString(c.get(java.util.Calendar.HOUR_OF_DAY));%>
<%s_now = s_now + ":";%>
<%s_now = s_now + Integer.toString(c.get(java.util.Calendar.MINUTE));%>
<%s_now = s_now + ":";%>
<%s_now = s_now + Integer.toString(c.get(java.util.Calendar.SECOND));%>
<%s_now = s_now + ".";%>
<%s_now = s_now + Integer.toString(c.get(java.util.Calendar.MILLISECOND));%>
<% out.println(s_now); %>



<html:form action="/changepasswordsave">
<TAble>
	<TR>
		<TD>用户ID</TD>
		<TD>
			<html:text name="ChangePasswordForm" property="usernameid" 
			size="10" maxlength="10" readonly="true"/>
		</TD>
	</TR>
	<TR>
		<TD>用户名</TD>
		<TD>
			<html:text name="ChangePasswordForm" property="usernamec" 
			size="30" maxlength="30" readonly="true"/>
		</TD>
	</TR>
	<TR>
		<TD>旧口令</TD>
		<TD>
			<html:password name="ChangePasswordForm" property="passwordold" 
			size="10" maxlength="10" redisplay="false"/>
        </TD>
	</TR>
	<TR>
		<TD>新口令</TD>
		<TD>
			<html:password name="ChangePasswordForm" property="passwordnew" 
			size="10" maxlength="10" redisplay="false"/>
        </TD>
	</TR>
	<TR>
		<TD>确认新口令</TD>
		<TD>
			<html:password name="ChangePasswordForm" property="passwordagain" 
			size="10" maxlength="10" redisplay="false"/>
        </TD>
	</TR>
      <tr> 
        <td align="right"> <html:submit><bean:message key="button.submit"/></html:submit> 
        </td>
        <td align="left"> <html:reset><bean:message key="button.reset"/></html:reset> 
        </td>
      </tr>
	
</TAble>
<p>
</p>	
</html:form>
</BODY>
</html:html>
