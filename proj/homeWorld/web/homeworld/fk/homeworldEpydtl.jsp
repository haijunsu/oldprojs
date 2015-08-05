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
			家世界连锁商业集团有限公司
		</TITLE>
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
			<html:form action="/homeworldEpydtl" >

			<html:hidden property="selectwhere"/>


			<H2 align="center">家世界连锁商业集团有限公司<BR>已付款明细</H2>
			
			<logic:notPresent name="homeworldEpydtlForm" property="epdvdr" >
				<br><br>
				<center>数据库无记录</center>
			</logic:notPresent>
    	
		    <table  align="center" style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 border=1>
				<logic:present name="homeworldEpydtlForm" property="epdvdr" >
				    <thead>
					    <th class="printtitle3center"  nowrap>供货商号</th>
						<th class="printtitle3center"  nowrap>商店号</th>
						<th class="printtitle3center"  nowrap>付款日期</th>
						<th class="printtitle3center"  nowrap>发票子轨</th>
						<th class="printtitle3center"  nowrap>发票号</th>
						<th class="printtitle3center"  nowrap>开票日期</th>					
					</thead>
					<tbody id="DynData">	
					  <logic:iterate id="seq" name="homeworldEpydtlForm" property="epdvdr" indexId="index">
							    <tr align="left">		
									<td class="printtextcenter" nowrap><bean:write name="homeworldEpydtlForm" property='<%="epdvdr[" + index + "]"%>'/></td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldEpydtlForm" property='<%="epdstr[" + index + "]"%>'/></td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldEpydtlForm" property='<%="epdrdt[" + index + "]"%>'/></td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldEpydtlForm" property='<%="epdtrk[" + index + "]"%>'/></td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldEpydtlForm" property='<%="epdivn[" + index + "]"%>'/></td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldEpydtlForm" property='<%="epdivd[" + index + "]"%>'/></td>    
								</tr>		    	
					  </logic:iterate>
					</tbody>	
				</logic:present>
			</table>

				 
<br>	
 


		
		</html:form>
		
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>	
		<script language="JavaScript">	
			
			function allc(form){
				for(var i=0 ;i<form.chacked.length;i++){
					form.chacked[i].checked=form.all.checked;
				}
				form.chacked.checked=form.all.checked;
			}
			

			function commit(thisform){	
				thisform.action="<%=request.getContextPath()%>/homeworldEpydtl.do";
				thisform.flag.value="commit";	
				thisform.submit();
			}
			
		
			function printform(thisform){	
				thisform.action="<%=request.getContextPath()%>/homeworldPrintEipdtl.do";
				thisform.submit();
			}
		</script>
	</BODY>
</html:html>
