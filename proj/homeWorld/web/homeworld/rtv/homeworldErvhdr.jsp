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
			<html:form action="/homeworldErvhdr" >

			<html:hidden property="selectwhere"/>
			<html:hidden property="queryid"/>

			<table width="100%">
			   <td width="400">
			   	<font size="2">
			       <b>&nbsp�ջ������:</b><bean:write name="homeworldErvhdrForm" property="ervnum"  />
			    </font>
			   </td>
			   <td width="1500"><H2 align="center">������������ҵ�������޹�˾<BR>�ջ��� </H2></td>
	         <td width="400"><font size="2">
		      <b>����:</b><bean:write name="homeworldErvhdrForm" property="ervcdt" />
		      <br><b>ʱ��:</b><bean:write name="homeworldErvhdrForm" property="ervctm"  />
		      </font></td>
		    </table>   
		    
			 	 		
			<TABLE style="BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; BORDER-BOTTOM-WIDTH: 1px; BORDER-COLLAPSE: collapse; BORDER-RIGHT-WIDTH: 1px" borderColor=#111111 height=336 cellSpacing=0 borderColorDark=#000000 cellPadding=0 width=950 bgColor=#ffffff>
			  <tr>			 
			     <td class="printtitle1" nowrap width=17 rowSpan=12>��<br>��</td>
				 <td class="printtitle2left" nowrap>�̵�       </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervstr"  /></td>	 
				 <td class="printtitle1" nowrap  width=17 rowSpan=12>��<br>��<br>��<br>��<br>Ϣ</td>	
				 <td class="printtitle2left" nowrap>��������   </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervvnd"  /></td>		      			 	 
				 <td class="printtitle1" nowrap width=17 rowSpan=12>��<br>��<br>��<br>��<br>Ϣ</td>	
				 <td class="printtitle2left" nowrap>�ջ�����   </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervnum"  /></td>		      			 	 
	    	</tr><tr>
				 <td class="printtitle2left" nowrap>�̵�����     </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervstn"  /></td>
				 <td class="printtitle2left" nowrap>����������   </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervvdn"  /></td>				 				
				 <td class="printtitle2left" nowrap>��������     </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervedt"  /></td>		      			 	 
			</tr><tr>
				 <td class="printtitle2left" nowrap>�ɹ�Ա      </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervbyr"  /></td>
    			 <td class="printtitle2left" nowrap>�����̵�ַ1 </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervva1"  /></td>			
    			 <td class="printtitle2left" nowrap>�ջ�����    </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervrdt"  /></td>		      			 	 

			</tr><tr>
				 <td class="printtitle2left" nowrap>�ɹ���    </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervbyn"  /></td>         
				 <td class="printtitle2left" nowrap>�����̵�ַ2    </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervva2"  /></td>				         
				 <td class="printtitle2left" nowrap>�ջ���״̬   </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervsts"  /></td>		      			 	 

			</tr><tr>
				 <td class="printtitle2left" nowrap>����    </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervdpt"  /></td>         
				 <td class="printtitle2left" nowrap>�����̵�ַ3    </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervva3"  /></td>	
		         <td class="printtitle2left" nowrap>��������  </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervcdt"  /></td>			
			
			</tr><tr>
				 <td class="printtitle2left" nowrap>������    </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervdpn"  /></td>			
				 <td class="printtitle2left" nowrap>�����̳���,�ʱ�</td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervvcy"  />��<bean:write name="homeworldErvhdrForm" property="ervvzp"  /></td>			
				 <td class="printtitle2left" nowrap>����ʱ��	</td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervctm"  /></td>				
				 
   				
			</tr><tr>			
				 <td class="printtitle2left" nowrap>�Ӳ��� 	 </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervsdpt"  /></td>
				 <td class="printtitle2left" nowrap>�����̹��� </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervvcn"  /></td>
				 <td class="printtitle2left" nowrap>�������</td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervpno"  /></td>
				 

			</tr><tr>
				 <td class="printtitle2left" nowrap>�Ӳ�����  </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervsdptn"  /></td>
				 <td class="printtitle2left" nowrap>������       </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervvct"  /></td>				 
				 <td class="printtitle2left" nowrap></td><td class="printtextleft" nowrap></td>
			 
			</tr><tr>				 
				 <td class="printtitle2left" nowrap>����     </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervfrc"  /></td>
				 <td class="printtitle2left" nowrap>Ʊ�ڴ��� 	 </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervtrm"  /></td>
				 <td class="printtitle2left" nowrap></td><td class="printtextleft" nowrap></td>

			</tr><tr>
				 <td class="printtitle2left" nowrap>�������� </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervfrn"  /></td>
				 <td class="printtitle2left" nowrap>Ʊ����	 </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervtmn"  /></td>
				 <td class="printtitle2left" nowrap></td><td class="printtextleft" nowrap></td>
			</tr>
	        </table>	

	    	<br>
	    	
		    <table  style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=972 border=1>
				<logic:present name="homeworldErvhdrForm" property="ervssq" >
				    <thead>
					    <th class="printtitle3center"  nowrap>���</th>
						<th class="printtitle3center"  nowrap>sku����</th>
						<th class="printtitle3center"  nowrap>����˵��</th>
						<th class="printtitle3center"  nowrap>�������ͺ�</th>
						<th class="printtitle3center"  nowrap>����<br>��λ</th>
						<th class="printtitle3center"  nowrap>�ɹ�<br>��λ</th>
						<th class="printtitle3center"  nowrap>upc��</th>
						<th class="printtitle3center"  nowrap>���</th>
						<th class="printtitle3center"  nowrap>������<br>������</th>
						<th class="printtitle3center"  nowrap>������<br>������</th>
						<th class="printtitle3center"  nowrap>�ջ���<br>������</th>
						<th class="printtitle3center"  nowrap>Ƿ����<br>������</th>
						<th class="printtitle3center"  nowrap>�ۿ۱�־</th>
						
						
					</thead>
					<tbody id="DynData">	
					  <logic:iterate id="seq" name="homeworldErvhdrForm" property="ervssq" indexId="index">
						  <logic:equal name="homeworldErvhdrForm" property='<%="ervsku[" + index + "]"%>' value='sumcount'>
							    <tr align="left">
									<td class="printtitle3centerw" nowrap  colspan=8 >�ϼơ�������</td>    
									<td class="printtextright" nowrap><bean:write name="homeworldErvhdrForm" property='<%="ervcas[" + index + "]"%>'/> </td>    
									<td class="printtextright" nowrap><bean:write name="homeworldErvhdrForm" property='<%="ervqty[" + index + "]"%>'/> </td>    
									<td class="printtextright" nowrap><bean:write name="homeworldErvhdrForm" property='<%="ervrqy[" + index + "]"%>'/> </td>    
									<td class="printtextright" nowrap><bean:write name="homeworldErvhdrForm" property='<%="ervnqy[" + index + "]"%>'/> </td>    
									<td class="printtextright" nowrap></td>    
								</tr>		    	
							</logic:equal>
							<logic:notEqual name="homeworldErvhdrForm" property='<%="ervsku[" + index + "]"%>' value='sumcount'>
							    <tr align="left">				
									<td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property='<%="ervssq[" + index + "]"%>'/> </td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property='<%="ervsku[" + index + "]"%>'/> </td>    
  
									<td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property='<%="ervskd[" + index + "]"%>'/> </td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property='<%="ervvds[" + index + "]"%>'/> </td>  
																		
																		   
									<td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property='<%="ervsum[" + index + "]"%>'/> </td>    
									<td class="printtextright"  nowrap><bean:write name="homeworldErvhdrForm" property='<%="ervbum[" + index + "]"%>'/> </td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property='<%="ervupc[" + index + "]"%>'/> </td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property='<%="ervmgn[" + index + "]"%>'/> </td> 
									
									
									<td class="printtextright"  nowrap><bean:write name="homeworldErvhdrForm" property='<%="ervcas[" + index + "]"%>'/> </td>    
									<td class="printtextright"  nowrap><bean:write name="homeworldErvhdrForm" property='<%="ervqty[" + index + "]"%>'/> </td>    
									<td class="printtextright"  nowrap><bean:write name="homeworldErvhdrForm" property='<%="ervrqy[" + index + "]"%>'/> </td>    
									<td class="printtextright"  nowrap><bean:write name="homeworldErvhdrForm" property='<%="ervnqy[" + index + "]"%>'/> </td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property='<%="ervsks[" + index + "]"%>'/> </td>    
								</tr>		    	
							</logic:notEqual>
					  </logic:iterate>
					</tbody>	
				</logic:present>
			</table>

			<br>
			
		    <table  style="BORDER-RIGHT: 1px solid; BORDER-TOP: 1px solid; BORDER-LEFT: 1px solid; BORDER-BOTTOM: 1px solid; BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 border=1 width=500>
		      <tr> 

				 <td class="printtitle3center" nowrap>ע��1</td><td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property="ervnot1"  /></td>
			  </tr><tr>				 
				 <td class="printtitle3center" nowrap>ע��2</td><td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property="ervnot2"  /></td>
			  </tr><tr>				 
				 <td class="printtitle3center" nowrap>ע��3</td><td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property="ervnot3"  /></td>
			  </tr><tr>				 
				 <td class="printtitle3center" nowrap>FOB-DESTINATION</td><td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property="ervfob"  /></td>
			  </tr><tr>
				 <td class="printtitle3center" nowrap>��������1</td><td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property="ervsp1"  /></td>
			  </tr><tr>
				 <td class="printtitle3center" nowrap>��������2</td><td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property="ervsp2"  /></td>
			  </tr><tr>
				 <td class="printtitle3center" nowrap>������--</td><td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property="ervspp"  /></td>
 			  </tr><tr>
				 <td class="printtitle3center" nowrap>������ע--</td><td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property="ervspc"  /></td>
 			  </tr>
			</table>	
			
			<br>
			
		    <table  style="BORDER-RIGHT: 1px solid; BORDER-TOP: 1px solid; BORDER-LEFT: 1px solid; BORDER-BOTTOM: 1px solid; BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 border=1 width=500>
		      <tr> 
   	  	         <td class="printtitle1" nowrap width=17  rowSpan=2>�ܼ�</td>
  		         <td class="printtitle3center" nowrap>�ۼ�--</td><td class="printtextright" nowrap><bean:write name="homeworldErvhdrForm" property="ervtrt"/></td>
				 <td class="printtitle3center" nowrap>SKU�ۿ�--</td><td class="printtextright" nowrap><bean:write name="homeworldErvhdrForm" property="ervsdt"/></td>
			  </tr><tr>				 
   			     <td class="printtitle3center" nowrap>�ɱ�--</td><td class="printtextright" nowrap><bean:write name="homeworldErvhdrForm" property="ervtct"/></td>
				 <td class="printtitle3center" nowrap>�������ۿ�--</td><td class="printtextright" nowrap><bean:write name="homeworldErvhdrForm" property="ervvdt"/></td>
 			  </tr>
			</table>	
						 
						 
<br>		<br>		
			<center>
			<logic:notEqual name="homeworldErvhdrForm" property='selectwhere' value=''>
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
				thisform.action="homeworldPrintErvhdr.do";
				thisform.submit();
			}
		</script>
	</BODY>
</html:html>
