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
			<html:form action="/homeworldEpyfee" >

			<html:hidden property="selectwhere"/>


			<H2 align="center">家世界连锁商业集团有限公司<BR>已付费用信息栏</H2>
		
			<logic:notPresent name="homeworldEpyfeeForm" property="epjvdr" >
				<br><br>
				<center>数据库无记录</center>
			</logic:notPresent>
			
		    <table  align="center" style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 border=1>
				<logic:present name="homeworldEpyfeeForm" property="epjvdr" >
				    <thead>                             
				  	<th class="printtitle3center"  nowrap>供货商号      </th>    
					<th class="printtitle3center"  nowrap>商店号        </th>  
					<th class="printtitle3center"  nowrap>付款日期      </th>
					<th class="printtitle3center"  nowrap>协议对应的商店</th>
					<th class="printtitle3center"  nowrap>系统协议号    </th>  
					<th class="printtitle3center"  nowrap>手签协议号    </th>
					<th class="printtitle3center"  nowrap>项目名称      </th>  
					<th class="printtitle3center"  nowrap>费用金额      </th>				
					</thead>
					<tbody id="DynData">	
					  <logic:iterate id="seq" name="homeworldEpyfeeForm" property="epjvdr" indexId="index">
						  <logic:notEqual name="homeworldEpyfeeForm" property='<%="epjvdr[" + index + "]"%>' value='sumhj'>
							    <tr align="left">		                              
									<td class="printtextcenter" nowrap><bean:write name="homeworldEpyfeeForm" property='<%="epjvdr[" + index + "]"%>'/></td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldEpyfeeForm" property='<%="epjstr[" + index + "]"%>'/></td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldEpyfeeForm" property='<%="epjrtd[" + index + "]"%>'/></td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldEpyfeeForm" property='<%="epjpst[" + index + "]"%>'/></td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldEpyfeeForm" property='<%="epjspn[" + index + "]"%>'/></td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldEpyfeeForm" property='<%="epjhpn[" + index + "]"%>'/></td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldEpyfeeForm" property='<%="epjpnm[" + index + "]"%>'/></td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldEpyfeeForm" property='<%="epjfee[" + index + "]"%>'/></td>  
								</tr>
						  </logic:notEqual>
						  <logic:equal name="homeworldEpyfeeForm" property='<%="epjvdr[" + index + "]"%>' value='sumhj'>
							    <tr align="left">		                              
									<td class="printtextcenter" nowrap colspan="7" >合计</td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldEpyfeeForm" property='<%="epjfee[" + index + "]"%>'/></td>  
								</tr>	  
						  </logic:equal>
						  										    				
					  </logic:iterate>
					</tbody>	
				</logic:present>
			</table>

				 
<br>	
 
		</html:form>

	</BODY>
</html:html>
