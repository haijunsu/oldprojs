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
			<html:form action="/homeworldErthdr" >

			<html:hidden property="selectwhere"/>
			<html:hidden property="queryid"/>
			<html:hidden property="flag"/>
			<html:hidden property="ertsts"/>

			<table width="100%">
			   <td width="400">
			   	<font size="2">
			       <b>&nbsp��������:</b><bean:write name="homeworldErthdrForm" property="ertnum"  />
			    </font>
			   </td>
			   <td width="1500"><H2 align="center">������������ҵ�������޹�˾<BR>������Ȩ�� </H2></td>
	         <td width="400"><font size="2">
		      <b>����:</b><bean:write name="homeworldErthdrForm" property="ertcdt" />
		      <br><b>ʱ��:</b><bean:write name="homeworldErthdrForm" property="ertctm"  />
		      </font></td>
		    </table>   
		    
			 	 		
			<TABLE style="BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; BORDER-BOTTOM-WIDTH: 1px; BORDER-COLLAPSE: collapse; BORDER-RIGHT-WIDTH: 1px" borderColor=#111111 height=336 cellSpacing=0 borderColorDark=#000000 cellPadding=0 width=950 bgColor=#ffffff>
			  <tr>			 
			     <td class="printtitle1" nowrap width=17 rowSpan=12>��<br>��</td>
				 <td class="printtitle2left" nowrap>�̵�       </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertstr"  /></td>	 
				 <td class="printtitle1" nowrap  width=17 rowSpan=12>��<br>��<br>��<br>��<br>Ϣ</td>	
				 <td class="printtitle2left" nowrap>��Ӧ�̺�    </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertvnd"  /></td>		      			 	 
			 	 <td class="printtitle1" nowrap width=17 rowSpan=12>��<br>��<br>��<br>��<br>Ϣ</td>	
				 <td class="printtitle2left" nowrap>��������    </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertnum"  /></td>		      			 
	    	</tr><tr>
				 <td class="printtitle2left" nowrap>�̵�����     </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertstn"  /></td>
				 <td class="printtitle2left" nowrap>����������   </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertnam"  /></td>				 				
				 <td class="printtitle2left" nowrap>�������     </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertvra"  /></td>
			</tr><tr>
				 <td class="printtitle2left" nowrap>�̵��ַ1     </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertsa1"  /></td>
    			 <td class="printtitle2left" nowrap>�����̵�ַ1     </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertad1"  /></td>			
				 <td class="printtitle2left" nowrap>��Ȩ��       </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertaut"  /></td>
			</tr><tr>
				 <td class="printtitle2left" nowrap>�̵��ַ2    </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertsa2"  /></td>         
				 <td class="printtitle2left" nowrap>�����̵�ַ2    </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertad2"  /></td>				         
				 <td class="printtitle2left" nowrap>WITHDRAWAL   </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertvwd" /></td>
			</tr><tr>
				 <td class="printtitle2left" nowrap>�̵��ַ3    </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertsa3"  /></td>         
				 <td class="printtitle2left" nowrap>�����̵�ַ3    </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertad3"  /></td>				         
				 <td class="printtitle2left" nowrap>��������     </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertsdt" /></td>
			
			
			</tr><tr>
				 <td class="printtitle2left" nowrap>����,�ʱ�    </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertsct"  />��<bean:write name="homeworldErthdrForm" property="ertszp"  /></td>			
				 <td class="printtitle2left" nowrap>�����̳���,�ʱ�</td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertvct"  />��<bean:write name="homeworldErthdrForm" property="ertvzp"  /></td>			
   				 <td class="printtitle2left" nowrap>����ԭ��     </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertrsn"  /></td>
				
			</tr><tr>			
				 <td class="printtitle2left" nowrap>�̵���� 	 </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertscn"  /></td>
				 <td class="printtitle2left" nowrap>�����̹���  </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertcnt"  /></td>
			     <td class="printtitle2left" nowrap>����ָ��     </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertins"  /></td>
			</tr><tr>
				 <td class="printtitle2left" nowrap>��ϵ��       </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertsmg"  /></td>
				 <td class="printtitle2left" nowrap>������       </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertacn"  /></td>				 
   				 <td class="printtitle2left" nowrap></td><td class="printtextleft" nowrap></td>				 
			</tr><tr>				 
				 <td class="printtitle2left" nowrap>�̵�绰     </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertsph"  /></td>
				 <td class="printtitle2left" nowrap>���̵绰     </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertphn"  /></td>
				 <td class="printtitle2left" nowrap></td><td class="printtextleft" nowrap></td>				 
		    </tr><tr>
				 <td class="printtitle2left" nowrap>�̵괫��   	 </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertsfx"  /></td>
				 <td class="printtitle2left" nowrap>���̴���	 </td><td class="printtextleft" nowrap><bean:write name="homeworldErthdrForm" property="ertfax"  /></td>
				 <td class="printtitle2left" nowrap></td><td class="printtextleft" nowrap></td>				 
			</tr><tr>
				 <td class="printtitle2left" nowrap></td><td class="printtextleft" nowrap></td>
				 <td class="printtitle2left" nowrap></td><td class="printtextleft" nowrap></td>
				 <td class="printtitle2left" nowrap></td><td class="printtextleft" nowrap></td>				 
			</tr><tr>
				 <td class="printtitle2left" nowrap></td><td class="printtextleft" nowrap></td>
				 <td class="printtitle2left" nowrap></td><td class="printtextleft" nowrap></td>
				 <td class="printtitle2left" nowrap></td><td class="printtextleft" nowrap></td>				 
			</tr>
	        </table>	
	        
	    	<br>
		    <table  style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=972 border=1>
				<logic:present name="homeworldErthdrForm" property="ertsku" >
				    <thead>
						<th class="printtitle3center"  nowrap>sku</th>
						<th class="printtitle3center"  nowrap>����</th>
						<th class="printtitle3center"  nowrap>����</th>
						<th class="printtitle3center"  nowrap>����ͺ�</th>
						<th class="printtitle3center"  nowrap>�����ͺ�</th>
						<th class="printtitle3center"  nowrap>����λ</th>
						<th class="printtitle3center"  nowrap>���۵�λ</th>
						<th class="printtitle3center"  nowrap>�˻��ɱ�</th>
						<th class="printtitle3center"  nowrap>�˻�����</th>
					</thead>
					<tbody id="DynData">	
					  <logic:iterate id="seq" name="homeworldErthdrForm" property="ertsku" indexId="index">
						  <logic:equal name="homeworldErthdrForm" property='<%="ertsku[" + index + "]"%>' value='sumcount'>
							    <tr align="left">
									<td class="printtitle3centerw" nowrap  colspan=7 >�ϼơ�������</td>    
									<td class="printtextright" nowrap><bean:write name="homeworldErthdrForm" property='<%="ertcst[" + index + "]"%>'/> </td>    
									<td class="printtextright" nowrap><bean:write name="homeworldErthdrForm" property='<%="ertshq[" + index + "]"%>'/> </td>    
								</tr>		    	
							</logic:equal>
							<logic:notEqual name="homeworldErthdrForm" property='<%="ertsku[" + index + "]"%>' value='sumcount'>
							    <tr align="left">				
									<td class="printtextcenter" nowrap><bean:write name="homeworldErthdrForm" property='<%="ertsku[" + index + "]"%>'/> </td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldErthdrForm" property='<%="ertskd[" + index + "]"%>'/> </td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldErthdrForm" property='<%="ertupc[" + index + "]"%>'/> </td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldErthdrForm" property='<%="ertmfg[" + index + "]"%>'/> </td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldErthdrForm" property='<%="ertvdn[" + index + "]"%>'/> </td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldErthdrForm" property='<%="ertbum[" + index + "]"%>'/> </td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldErthdrForm" property='<%="ertsum[" + index + "]"%>'/> </td>    
									<td class="printtextright" nowrap><bean:write name="homeworldErthdrForm" property='<%="ertcst[" + index + "]"%>'/> </td>    
									<td class="printtextright" nowrap><bean:write name="homeworldErthdrForm" property='<%="ertshq[" + index + "]"%>'/> </td>    
								</tr>		    	
							</logic:notEqual>
					  </logic:iterate>
					</tbody>	
				</logic:present>
			</table>

			<br>
			
		    <table  style="BORDER-RIGHT: 1px solid; BORDER-TOP: 1px solid; BORDER-LEFT: 1px solid; BORDER-BOTTOM: 1px solid; BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 border=1 width=500>
		      <tr> 
				 <td class="printtitle3center" nowrap>������</td><td class="printtextcenter" nowrap><bean:write name="homeworldErthdrForm" property="ertshb"  /></td>
			  </tr><tr>				 
				 <td class="printtitle3center" nowrap>������</td><td class="printtextcenter" nowrap><bean:write name="homeworldErthdrForm" property="ertcar"  /></td>
			  </tr><tr>
				 <td class="printtitle3center" nowrap>��ע</td><td class="printtextcenter" nowrap><bean:write name="homeworldErthdrForm" property="ertcom"  /></td>
			  </tr><tr>
				 <td class="printtitle3center" nowrap>REQUESTED BY</td><td class="printtextcenter" nowrap><bean:write name="homeworldErthdrForm" property="ertreq"  /></td>
 			  </tr>
			</table>	
						 
<br>			
			<center>
			
			�Ѵ����־��
			
			<input type="checkbox" name="aa" value="Z" onclick="commit(this.form)" >		
			<br><br><br><br>	
			<logic:notEqual name="homeworldErthdrForm" property='selectwhere' value=''>
				<html:button property="butcreatexml" onclick ="createxml(this.form)">����xml�ļ�</html:button> 
				<html:button property="butprint" onclick ="printform(this.form)">��ӡ</html:button> 		
			</logic:notEqual>	
			</center>
		
		</html:form>
		
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>	
		<script language="JavaScript">	
		
			if(document.forms[0].ertsts.value=="Z"){
				document.forms[0].aa.checked="true";
				document.forms[0].aa.disabled="true";
			}

			
			function commit(thisform){
				if (confirm("����ı䱾����¼��״̬�𣨱����̲����棩��")){	
					thisform.flag.value="change";
					thisform.submit();
				}
				thisform.aa.checked=false;
			}
			
			
			function createxml(thisform){		
				var url="homeworldXml.do?queryid="+thisform.queryid.value+"&selectwhere="+thisform.selectwhere.value;
				//var select =  window.open(url);	
				window.location=url;
			}
		
			function printform(thisform){	
				thisform.action="homeworldPrintErthdr.do";
				thisform.submit();
			}
		</script>
	</BODY>
</html:html>
