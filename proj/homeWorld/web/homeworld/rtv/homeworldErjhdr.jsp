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
			<html:form action="/homeworldErjhdr"  method="post"  target="_blank" >

			<html:hidden property="selectwhere"/>
			<html:hidden property="queryid"/>

			<table width="100%">
			   <td width="400">
			   	<font size="2">
			       <b>&nbsp�ջ������:</b><bean:write name="homeworldErjhdrForm" property="erjrnm"  />
			    </font>
			   </td>
			   <td width="1500"><H2 align="center">������������ҵ�������޹�˾<BR>�ջ�������</H2></td>
	         <td width="400"><font size="2">
		      <b>����:</b><bean:write name="homeworldErjhdrForm" property="erjcdt" />
		      <br><b>ʱ��:</b><bean:write name="homeworldErjhdrForm" property="erjctm"  />
		      </font></td>
		    </table>   
		    
			 	 		
			<TABLE style="BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; BORDER-BOTTOM-WIDTH: 1px; BORDER-COLLAPSE: collapse; BORDER-RIGHT-WIDTH: 1px" borderColor=#111111 cellSpacing=0 borderColorDark=#000000 cellPadding=0 width=950 bgColor=#ffffff>
			  <tr>			 
			     <td class="printtitle1" nowrap width=17 rowSpan=12>��<br>��</td>
				 <td class="printtitle2left" nowrap width=100 height="15" >�̵�       </td><td class="printtextleft" nowrap width=200><bean:write name="homeworldErjhdrForm" property="erjstr"  /></td>	 
				 <td class="printtitle1" nowrap  width=17 rowSpan=12>��<br>��<br>��<br>��<br>Ϣ</td>	
				 <td class="printtitle2left" nowrap width=100 >��������   </td><td class="printtextleft" nowrap width=200><bean:write name="homeworldErjhdrForm" property="erjvnd"  /></td>		      			 	 
				 <td class="printtitle1" nowrap width=17 rowSpan=12>��<br>��<br>��<br>��<br>Ϣ</td>	
				 <td class="printtitle2left" nowrap width=100 >��������   </td><td class="printtextleft" nowrap width=200><bean:write name="homeworldErjhdrForm" property="erjnum"  /></td>		      			 	 
	    	</tr><tr>
				 <td class="printtitle2left" nowrap height="15" >�̵�����     </td><td class="printtextleft" nowrap><bean:write name="homeworldErjhdrForm" property="erjstn"  /></td>
				 <td class="printtitle2left" nowrap>����������   </td><td class="printtextleft" nowrap><bean:write name="homeworldErjhdrForm" property="erjvdn"  /></td>				 				
				 <td class="printtitle2left" nowrap>��������     </td><td class="printtextleft" nowrap><bean:write name="homeworldErjhdrForm" property="erjjdt"  /></td>
			</tr>
	        </table>	

	    	<br>
	    	
		    <table  style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=972 border=1>
				<logic:present name="homeworldErjhdrForm" property="erjssq" >
				    <thead>
					    <th class="printtitle3center"  nowrap>���</th>
						<th class="printtitle3center"  nowrap>sku����</th>
						<th class="printtitle3center"  nowrap>����˵��</th>
						<th class="printtitle3center"  nowrap>�������ͺ�</th>

						<th class="printtitle3center"  nowrap>���</th>
						<th class="printtitle3center"  nowrap>����</th>
						<th class="printtitle3center"  nowrap>�ۼ�</th>
						
						<th class="printtitle3center"  nowrap>�ɱ�</th>
						<th class="printtitle3center"  nowrap>�ۼ۽��</th>
						<th class="printtitle3center"  nowrap>�ɱ����</th>
						
					</thead>
					<tbody id="DynData">	
					  <logic:iterate id="seq" name="homeworldErjhdrForm" property="erjssq" indexId="index">
						  <logic:equal name="homeworldErjhdrForm" property='<%="erjsku[" + index + "]"%>' value='sumcount'>
							    <tr align="left">
									<td class="printtitle3centerw" nowrap  colspan=5 >�ϼơ�������</td>    
									<td class="printtextright" nowrap><bean:write name="homeworldErjhdrForm" property='<%="erjrqy[" + index + "]"%>'/> </td>    
									<td class="printtextright" nowrap></td>    
									<td class="printtextright" nowrap></td>    
									<td class="printtextright" nowrap><bean:write name="homeworldErjhdrForm" property='<%="erjtrt[" + index + "]"%>'/> </td>    
									<td class="printtextright" nowrap><bean:write name="homeworldErjhdrForm" property='<%="erjtct[" + index + "]"%>'/> </td>    
								</tr>		    	
							</logic:equal>
							<logic:notEqual name="homeworldErjhdrForm" property='<%="erjsku[" + index + "]"%>' value='sumcount'>
							    <tr align="left">				
									<td class="printtextcenter" nowrap><bean:write name="homeworldErjhdrForm" property='<%="erjssq[" + index + "]"%>'/> </td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldErjhdrForm" property='<%="erjsku[" + index + "]"%>'/> </td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldErjhdrForm" property='<%="erjskd[" + index + "]"%>'/> </td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldErjhdrForm" property='<%="erjvds[" + index + "]"%>'/> </td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldErjhdrForm" property='<%="erjmgn[" + index + "]"%>'/> </td>    
									<td class="printtextright" nowrap><bean:write name="homeworldErjhdrForm" property='<%="erjrqy[" + index + "]"%>'/> </td>    
									<td class="printtextright" nowrap><bean:write name="homeworldErjhdrForm" property='<%="erjret[" + index + "]"%>'/> </td>    
									<td class="printtextright"  nowrap><bean:write name="homeworldErjhdrForm" property='<%="erjcst[" + index + "]"%>'/> </td>    
									<td class="printtextright"  nowrap><bean:write name="homeworldErjhdrForm" property='<%="erjtrt[" + index + "]"%>'/> </td>    
									<td class="printtextright"  nowrap><bean:write name="homeworldErjhdrForm" property='<%="erjtct[" + index + "]"%>'/> </td>    
								</tr>		    	
							</logic:notEqual>
					  </logic:iterate>
					</tbody>	
				</logic:present>
			</table>

			<br>
			
						 
<br>		<br>		
			<center>
			<logic:notEqual name="homeworldErjhdrForm" property='selectwhere' value=''>
				<html:button property="butcreatexml" onclick ="createxml(this.form)">����xml�ļ�</html:button> 
				<html:button property="butprint" onclick ="printform(this.form)">��ӡ</html:button> 
			</logic:notEqual>		
			</center>
		
		</html:form>
		
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>	
		<script language="JavaScript">	
		
			function createxml(thisform){		
				var url="homeworldXml.do?queryid="+thisform.queryid.value+"&selectwhere="+thisform.selectwhere.value;
				//var select =  window.open(url);	
				window.location=url;
			}
		
			function printform(thisform){	
				thisform.action="<%=request.getContextPath()%>/homeworldPrintErjhdr.do";
				thisform.submit();
			}
		</script>
	</BODY>
</html:html>
