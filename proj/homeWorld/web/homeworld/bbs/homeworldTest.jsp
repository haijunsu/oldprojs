
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312" %>
<html:html locale="true">
	
	<%
	response.setHeader("Pragma","No-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0); 
	%>
	<HEAD>
		<META HTTP-EQUIV="Cache-Control" CONTENT="No-cache">
		<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
		<META HTTP-EQUIV="Content-Type" content="text/html; charset=GB2312">
		<META HTTP-EQUIV="Content-Style-Type" content="text/css">
		<META name="GENERATOR" content="IBM WebSphere Studio">
		<LINK href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css">
		<TITLE>
				执行SQL语句
		</TITLE>
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
			<html:form action="/homeworldTest">

		    <table align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
				<tbody >
					<tr align="center">
						<td class="table1title" nowrap>SQL语句</td>
					</tr>
					<tr align="center">
						<td class="table1text" nowrap >
						<textarea  name="contentc" COLS="80" ROWS="20" ><bean:write name='homeworldTestForm' property='contentc'/></textarea>
					</tr>
		 		</tbody>
			</table>			
			<br>			
			<center>
			<html:button property="butcommit" onclick ="conmitform(this.form)">提交</html:button>

			</center>
			
			&nbsp;&nbsp;
		    <table align="center" cellspacing="0" cellpadding="1" class="table1border">
				<tbody >
					<tr align="center">
						<td class="table1title" nowrap>执行结果</td>
					</tr>
					<tr align="center">
						<td class="table1text" nowrap >
						<textarea  name="returns" readonly="true" COLS="80" ROWS="5" ><bean:write name='homeworldTestForm' property='returns'/></textarea></td>
					</tr>
		 		</tbody>
			</table>			
			<br>

		</html:form>
				      
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			
		<script language="JavaScript">
			function conmitform(thisform){
				thisform.submit();
			}
			function searchform(thisform){

			}
		</script>

		
	</BODY>
</html:html>
