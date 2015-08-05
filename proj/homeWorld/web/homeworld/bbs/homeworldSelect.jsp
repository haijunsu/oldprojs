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
			执行Select语句
		</TITLE>
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
			<html:form action="/homeworldSelect">

			<center><H2>
				执行Select语句
			</H2></center>
					    
		    <table align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
				<tbody >
					<tr align="center">
						<td class="table1text" nowrap >
						<textarea  name="contentc" COLS="80" ROWS="10" ><bean:write name='homeworldSelectForm' property='contentc'/></textarea>
					</tr>
		 		</tbody>
			</table>			
			<br><br>
			<center>
			<html:button property="butcommit" onclick ="conmitform(this.form)">提交</html:button>
			</center>
			<br><br>

		       <table   width="100%" cellspacing="0" cellpadding="1" class="table1border" border="1">
				<logic:present name="homeworldSelectForm" property="show" >
				
					  <logic:iterate id="columnname" name="homeworldSelectForm" property="columnname" indexId="index">
					  <th class="table2titletd" nowrap><%=columnname%></th>
					  </logic:iterate>				
					  
				<tbody id="DynData">
				
					  <logic:iterate id="rowshow" name="homeworldSelectForm" property="show" indexId="indexrow">
					    <tr align="center">
				       	  <logic:iterate id="col" name="homeworldSelectForm" property='<%="show[" + indexrow + "]"%>' indexId="indexcol">
							<td class="table2textcentertd" nowrap><%=col+""%></td>
		   				  </logic:iterate>		  
					    </tr>
					  </logic:iterate>				
					  
		 		</tbody>
	 			</logic:present>
			</table>
			<br>
			<br>
		      
		
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			
		<script language="JavaScript">

			function conmitform(thisform){
				thisform.submit();
			}
		</script>
		</html:form>
		
	</BODY>
</html:html>
