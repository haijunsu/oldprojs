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
			������������ҵ�������޹�˾
		</TITLE>
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
			<html:form action="/homeworldEpyfee" >

			<html:hidden property="selectwhere"/>


			<H2 align="center">������������ҵ�������޹�˾<BR>�Ѹ�������Ϣ��</H2>
		
			<logic:notPresent name="homeworldEpyfeeForm" property="epjvdr" >
				<br><br>
				<center>���ݿ��޼�¼</center>
			</logic:notPresent>
			
		    <table  align="center" style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 border=1>
				<logic:present name="homeworldEpyfeeForm" property="epjvdr" >
				    <thead>                             
				  	<th class="printtitle3center"  nowrap>�����̺�      </th>    
					<th class="printtitle3center"  nowrap>�̵��        </th>  
					<th class="printtitle3center"  nowrap>��������      </th>
					<th class="printtitle3center"  nowrap>Э���Ӧ���̵�</th>
					<th class="printtitle3center"  nowrap>ϵͳЭ���    </th>  
					<th class="printtitle3center"  nowrap>��ǩЭ���    </th>
					<th class="printtitle3center"  nowrap>��Ŀ����      </th>  
					<th class="printtitle3center"  nowrap>���ý��      </th>				
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
									<td class="printtextcenter" nowrap colspan="7" >�ϼ�</td>    
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
