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
		<style>
		.tablextitle1{
		   BORDER-RIGHT: 1px ridge; 
		   PADDING-RIGHT: 4px; 
		   BORDER-TOP: 1px ridge; 
		   PADDING-LEFT: 4px; 
		   PADDING-BOTTOM: 1px; 
		   BORDER-LEFT: 1px ridge; 
		   PADDING-TOP: 1px; 
		   BORDER-BOTTOM: 1px ridge;
		   BACKGROUND-COLOR:#ffffff;
		}
		</style>		
	</HEAD>
    <BODY onload="setVariables();checkLocation(); this.focus()">
	    <OBJECT id=IEWB height=0 width=0 classid=clsid:8856F961-340A-11D0-A96B-00C04FD705A2></OBJECT>
	    
        <html:form action="/homeworldPrintSend" method="post" >
        <html:errors property="errormessage"/>            

         <html:hidden property="printmark" />
         <html:hidden property="selectwhere" />
<%int mark=0;%>

        <DIV id=divPrint style="Z-INDEX: 5; RIGHT: 0px; VISIBILITY: visible; POSITION: absolute; TOP: 0px">
          <TABLE cellSpacing=0 cellPadding=0 bgColor=#3366ff border=1>
           <TR>
            <TD>
                <INPUT onclick="javaPrint(OLECMDID_PAGESETUP, OLECMDEXECOPT_DONTPROMPTUSER,this.form)" type=button value=ҳ������> 
                <INPUT onclick="javaPrint(OLECMDID_PRINTPREVIEW, OLECMDEXECOPT_PROMPTUSER,this.form)" type=button value=��ӡԤ��> 
                <INPUT onclick="javaPrint(OLECMDID_PRINT, OLECMDEXECOPT_DONTPROMPTUSER,this.form)" type=button value=��ӡ> 
            </TD>
          </TR></TABLE></DIV>
          
			 <H2 align="center">������������ҵ�������޹�˾<BR>�ͻ��� </H2>
  
		<TABLE class=table1border style="BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; BORDER-BOTTOM-WIDTH: 1px; BORDER-COLLAPSE: collapse; BORDER-RIGHT-WIDTH: 1px" borderColor=#111111 cellSpacing=0 borderColorDark=#000000 cellPadding=0 width=670 bgColor=#ffffff>
		<tr>	
			 <TD class="printtitle2centerw" noWrap >�������</TD>
		 	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="eponum"  /></TD> 
			
			 <TD class="printtitle2centerw" noWrap >��������</TD>
		  	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epovndz" /></TD> 
		</tr><tr>
			 <TD class="printtitle2centerw" noWrap >��&nbsp&nbsp&nbsp��</TD>
		 	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epostrz" /></TD> 
		 	 
		  	 <TD class="printtitle2centerw" noWrap >��������</TD>
		  	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epovdn" /></TD> 
		
		</tr><tr>
		 	 <TD class="printtitle2centerw" noWrap >��&nbsp��&nbsp��</TD>
		 	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epostn" /></TD> 
		 	 
		 	 <TD class="printtitle2centerw" noWrap >�ͻ�ʱ��</TD>
			 <TD class="printtextleft" noWrap ><bean:write name='homeworldPrintSendForm' property='senddatetime'/></TD> 	
		</tr><tr>
		
		 	 <TD class="printtitle2centerw" noWrap >��&nbsp&nbsp&nbsp��</TD>
			 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epodpt" />&nbsp&nbsp<FONT color=#000000 size=2><bean:write name="homeworldPrintSendForm" property="epodpn" /></TD> 
			 
		 	 <TD class="printtitle2centerw" noWrap >�ͻ�����</TD>
			 <TD class="printtextleft" noWrap ><input type="text" name="sendid"  size="30" style="border-style: solid; border-width: 1px; text-align: left; background-color: #FFFFCC;border:none"></TD> 
		</tr><tr>
		 	 <TD class="printtitle2centerw" noWrap >��������</TD>
		 	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epocdt" /></TD> 
		 	 
		 	 <TD class="printtitle2centerw" noWrap >ȡ������</TD>
		 	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epoqdt" /></TD> 
		 	 
			 
		</tr>
        </table>
       <br> 
	   
			<logic:present name="homeworldPrintSendForm" property="epostr"> 
		     <TABLE id=AutoNumber2 style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=670 border=1>
			    <thead>
			        <td class="printtitle2centerw" width=40 nowrap>���</td>
					<td class="printtitle2centerw" width=100 nowrap>upc</td>			        
					<td class="printtitle2centerw" width=70 nowrap>sku���</td>
					<td class="printtitle2centerw" width=60 nowrap>���</td>	
					<td class="printtitle2centerw" width=60 nowrap>������<br>�ͺ�</td>
					<td class="printtitle2centerw" width=40 nowrap>����<br>��λ</td>
					<td class="printtitle2centerw" width=80 nowrap>�����ɱ�</td>
					<td class="printtitle2centerw" width=70 nowrap>��������</td>
					<td class="printtitle2centerw" width=70 nowrap>�ջ�����</td>
					<td class="printtitle2centerw" width=80 nowrap>��ע</td>
				</thead>
				<tbody id="DynData">
					  <logic:iterate id="seq" name="homeworldPrintSendForm" property="epostr" indexId="index">
					   <logic:notEqual name="homeworldPrintSendForm" property='<%="eposku[" + index + "]"%>' value=''>
					   		<%if(mark==1){%>
									</tbody>
								 </table>	
							   </table>
							   
							<br clear=all style='page-break-before:always'>
							 <H2 align="center">������������ҵ�������޹�˾<BR>�ͻ���</H2>
				    
		<TABLE class=table1border style="BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; BORDER-BOTTOM-WIDTH: 1px; BORDER-COLLAPSE: collapse; BORDER-RIGHT-WIDTH: 1px" borderColor=#111111 cellSpacing=0 borderColorDark=#000000 cellPadding=0 width=670 bgColor=#ffffff>
		<tr>	
			 <TD class="printtitle2centerw" noWrap >�������</TD>
		 	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="eponum"  /></TD> 
			
			 <TD class="printtitle2centerw" noWrap >��������</TD>
		  	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epovndz" /></TD> 
		</tr><tr>
			 <TD class="printtitle2centerw" noWrap >��&nbsp&nbsp&nbsp��</TD>
		 	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epostrz" /></TD> 
		 	 
		  	 <TD class="printtitle2centerw" noWrap >��������</TD>
		  	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epovdn" /></TD> 
		
		</tr><tr>
		 	 <TD class="printtitle2centerw" noWrap >��&nbsp��&nbsp��</TD>
		 	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epostn" /></TD> 
		 	 
		 	 <TD class="printtitle2centerw" noWrap >�ͻ�ʱ��</TD>
			 <TD class="printtextleft" noWrap ><bean:write name='homeworldPrintSendForm' property='senddatetime'/></TD> 	
		</tr><tr>
		
		 	 <TD class="printtitle2centerw" noWrap >��&nbsp&nbsp&nbsp��</TD>
			 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epodpt" />&nbsp&nbsp<FONT color=#000000 size=2><bean:write name="homeworldPrintSendForm" property="epodpn" /></TD> 
			 
		 	 <TD class="printtitle2centerw" noWrap >�ͻ�����</TD>
			 <TD class="printtextleft" noWrap ><input type="text" name="sendid"  size="30" style="border-style: solid; border-width: 1px; text-align: left; background-color: #FFFFCC;border:none"></TD> 
		</tr><tr>
		 	 <TD class="printtitle2centerw" noWrap >��������</TD>
		 	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epocdt" /></TD> 
		 	 
		 	 <TD class="printtitle2centerw" noWrap >ȡ������</TD>
		 	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epoqdt" /></TD> 
		 	 
			 
		</tr>
        </table>
        <br>
							     <TABLE id=AutoNumber2 style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=670 border=1>
								    <thead>
			        <td class="printtitle2centerw" width=40 nowrap>���</td>
					<td class="printtitle2centerw" width=100 nowrap>upc</td>			        
					<td class="printtitle2centerw" width=70 nowrap>sku���</td>
					<td class="printtitle2centerw" width=60 nowrap>���</td>	
					<td class="printtitle2centerw" width=60 nowrap>������<br>�ͺ�</td>
					<td class="printtitle2centerw" width=40 nowrap>����<br>��λ</td>
					<td class="printtitle2centerw" width=80 nowrap>�����ɱ�</td>
					<td class="printtitle2centerw" width=70 nowrap>��������</td>
					<td class="printtitle2centerw" width=70 nowrap>�ջ�����</td>
					<td class="printtitle2centerw" width=80 nowrap>��ע</td>
									</thead>
									<tbody id="DynData">
					   		<%}%>
						   <%mark=0;%>
						    <tr height="25"> 
	                            <td class="printtextleft" rowspan=2 nowrap><bean:write name="homeworldPrintSendForm" property='<%="epossq[" + index + "]"%>'/></td>                                      					    		
          						<td class="printtextleft" rowspan=1 nowrap><bean:write name="homeworldPrintSendForm" property='<%="epoupc[" + index + "]"%>'/></td> 
	                            <td class="printtextleft" rowspan=1 nowrap><bean:write name="homeworldPrintSendForm" property='<%="eposku[" + index + "]"%>'/></td>    
								<td class="printtextleft" rowspan=1 nowrap><bean:write name="homeworldPrintSendForm" property='<%="epomgn[" + index + "]"%>'/></td>    
								<td class="printtextleft" rowspan=1 nowrap><bean:write name="homeworldPrintSendForm" property='<%="epovds[" + index + "]"%>'/></td>    
								<td class="printtextleft" rowspan=1 nowrap><bean:write name="homeworldPrintSendForm" property='<%="eposum[" + index + "]"%>'/></td>    
								<td class="printtextright" rowspan=1 nowrap><bean:write name="homeworldPrintSendForm" property='<%="eponct[" + index + "]"%>'/></td>
								<td class="printtextright" rowspan=1 nowrap><bean:write name="homeworldPrintSendForm" property='<%="epoqty[" + index + "]"%>'/></td>    
							    <td class="printtextleft" rowspan=1 nowrap></td>
								<td class="printtextleft" rowspan=2 nowrap></td>
							</tr><tr height="25"> 
								<td class="printtextleft" colspan=4 nowrap>Ʒ����<bean:write name="homeworldPrintSendForm" property='<%="eposkd[" + index + "]"%>'/></td>
								<td class="printtextleft" colspan=4 nowrap>�ʵأ�<bean:write name="homeworldPrintSendForm" property='<%="epoddd[" + index + "]"%>'/></td>
							</tr> 
							
						</logic:notEqual>	    	
						<logic:equal name="homeworldPrintSendForm" property='<%="eposku[" + index + "]"%>' value=''>
					   		<%if(mark==1){%>
									</tbody>
								 </table>	
							   </table>
							   
							<br clear=all style='page-break-before:always'>
							 <H2 align="center">������������ҵ�������޹�˾<BR>�ͻ���</H2>
				    
		<TABLE class=table1border style="BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; BORDER-BOTTOM-WIDTH: 1px; BORDER-COLLAPSE: collapse; BORDER-RIGHT-WIDTH: 1px" borderColor=#111111 cellSpacing=0 borderColorDark=#000000 cellPadding=0 width=670 bgColor=#ffffff>
		<tr>	
			 <TD class="printtitle2centerw" noWrap >�������</TD>
		 	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="eponum"  /></TD> 
			
			 <TD class="printtitle2centerw" noWrap >��������</TD>
		  	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epovndz" /></TD> 
		</tr><tr>
			 <TD class="printtitle2centerw" noWrap >��&nbsp&nbsp&nbsp��</TD>
		 	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epostrz" /></TD> 
		 	 
		  	 <TD class="printtitle2centerw" noWrap >��������</TD>
		  	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epovdn" /></TD> 
		
		</tr><tr>
		 	 <TD class="printtitle2centerw" noWrap >��&nbsp��&nbsp��</TD>
		 	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epostn" /></TD> 
		 	 
		 	 <TD class="printtitle2centerw" noWrap >�ͻ�ʱ��</TD>
			 <TD class="printtextleft" noWrap ><bean:write name='homeworldPrintSendForm' property='senddatetime'/></TD> 	
		</tr><tr>
		
		 	 <TD class="printtitle2centerw" noWrap >��&nbsp&nbsp&nbsp��</TD>
			 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epodpt" />&nbsp&nbsp<FONT color=#000000 size=2><bean:write name="homeworldPrintSendForm" property="epodpn" /></TD> 
			 
		 	 <TD class="printtitle2centerw" noWrap >�ͻ�����</TD>
			 <TD class="printtextleft" noWrap ><input type="text" name="sendid"  size="30" style="border-style: solid; border-width: 1px; text-align: left; background-color: #FFFFCC;border:none"></TD> 
		</tr><tr>
		 	 <TD class="printtitle2centerw" noWrap >��������</TD>
		 	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epocdt" /></TD> 
		 	 
		 	 <TD class="printtitle2centerw" noWrap >ȡ������</TD>
		 	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epoqdt" /></TD> 
		 	 
			 
		</tr>
        </table>
        <br>
							     <TABLE id=AutoNumber2 style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=670 border=1>
								    <thead>
			        <td class="printtitle2centerw" width=40 nowrap>���</td>
					<td class="printtitle2centerw" width=100 nowrap>upc</td>			        
					<td class="printtitle2centerw" width=70 nowrap>sku���</td>
					<td class="printtitle2centerw" width=60 nowrap>���</td>	
					<td class="printtitle2centerw" width=60 nowrap>������<br>�ͺ�</td>
					<td class="printtitle2centerw" width=40 nowrap>����<br>��λ</td>
					<td class="printtitle2centerw" width=80 nowrap>�����ɱ�</td>
					<td class="printtitle2centerw" width=70 nowrap>��������</td>
					<td class="printtitle2centerw" width=70 nowrap>�ջ�����</td>
					<td class="printtitle2centerw" width=80 nowrap>��ע</td>
									</thead>
									<tbody id="DynData">
					   		<%}%>
							<%mark=1;%>
							<tr height="25">
							<td colspan=5 align="right" nowrap><b><bean:write name="homeworldPrintSendForm" property='<%="epostr[" + index + "]"%>'/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</b></td>							 
							<td class="printtextleft" colspan=2 align="right" nowrap><bean:write name="homeworldPrintSendForm" property='<%="eponct[" + index + "]"%>'/></td>    
							<td colspan=2 class="printtextleft" nowrap><bean:write name="homeworldPrintSendForm" property='<%="epoqty[" + index + "]"%>'/></td>      
							<td ></td>  
							</tr>
						</logic:equal>	
				</logic:iterate>
			</logic:present>
					


			<logic:equal name="homeworldPrintSendForm" property='endnew' value='true'>
									</tbody>
								 </table>	
							   </table>
							   
		   		<br clear=all style='page-break-before:always'>          		
			
				
			 <H2 align="center">������������ҵ�������޹�˾<BR>�ͻ��� </H2>
			    
		<TABLE class=table1border style="BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; BORDER-BOTTOM-WIDTH: 1px; BORDER-COLLAPSE: collapse; BORDER-RIGHT-WIDTH: 1px" borderColor=#111111 cellSpacing=0 borderColorDark=#000000 cellPadding=0 width=670 bgColor=#ffffff>
		<tr>	
			 <TD class="printtitle2centerw" noWrap >�������</TD>
		 	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="eponum"  /></TD> 
			
			 <TD class="printtitle2centerw" noWrap >��������</TD>
		  	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epovndz" /></TD> 
		</tr><tr>
			 <TD class="printtitle2centerw" noWrap >��&nbsp&nbsp&nbsp��</TD>
		 	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epostrz" /></TD> 
		 	 
		  	 <TD class="printtitle2centerw" noWrap >��������</TD>
		  	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epovdn" /></TD> 
		
		</tr><tr>
		 	 <TD class="printtitle2centerw" noWrap >��&nbsp��&nbsp��</TD>
		 	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epostn" /></TD> 
		 	 
		 	 <TD class="printtitle2centerw" noWrap >�ͻ�ʱ��</TD>
			 <TD class="printtextleft" noWrap ><bean:write name='homeworldPrintSendForm' property='senddatetime'/></TD> 	
		</tr><tr>
		
		 	 <TD class="printtitle2centerw" noWrap >��&nbsp&nbsp&nbsp��</TD>
			 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epodpt" />&nbsp&nbsp<FONT color=#000000 size=2><bean:write name="homeworldPrintSendForm" property="epodpn" /></TD> 
			 
		 	 <TD class="printtitle2centerw" noWrap >�ͻ�����</TD>
			 <TD class="printtextleft" noWrap ><input type="text" name="sendid"  size="30" style="border-style: solid; border-width: 1px; text-align: left; background-color: #FFFFCC;border:none"></TD> 
		</tr><tr>
		 	 <TD class="printtitle2centerw" noWrap >��������</TD>
		 	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epocdt" /></TD> 
		 	 
		 	 <TD class="printtitle2centerw" noWrap >ȡ������</TD>
		 	 <TD class="printtextleft" noWrap ><bean:write name="homeworldPrintSendForm" property="epoqdt" /></TD> 
		 	 
			 
		</tr>
        </table>
        <br>
		     <TABLE id=AutoNumber2 style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=670 border=1>
			    <thead>
			        <td class="printtitle2centerw" width=40 nowrap>���</td>
					<td class="printtitle2centerw" width=100 nowrap>upc</td>			        
					<td class="printtitle2centerw" width=70 nowrap>sku���</td>
					<td class="printtitle2centerw" width=60 nowrap>���</td>	
					<td class="printtitle2centerw" width=60 nowrap>������<br>�ͺ�</td>
					<td class="printtitle2centerw" width=40 nowrap>����<br>��λ</td>
					<td class="printtitle2centerw" width=80 nowrap>�����ɱ�</td>
					<td class="printtitle2centerw" width=70 nowrap>��������</td>
					<td class="printtitle2centerw" width=70 nowrap>�ջ�����</td>
					<td class="printtitle2centerw" width=80 nowrap>��ע</td>
				</thead>
				<tbody id="DynData">
			</logic:equal>	  

				<tr height="25">
				<td  colspan=5 align="right" nowrap><b>�ϼ�--&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</b></td>
				<td colspan=2  class="printtextleft" align="right" nowrap><bean:write name="homeworldPrintSendForm" property="sumnct"/></td>
				<td colspan=2 class="printtextleft" align="right" nowrap><bean:write name="homeworldPrintSendForm" property="sumqty"/></td><td></td>

				</tbody>
			 </table>	

		   
	    <br>   
   			 <TABLE id=AutoNumber2 style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=670 border=1>
	    		<tr height="25">
				<td width=100 align="center" nowrap><font size="2"><b>������ע--</b></font></td>
				<td align="left" ><font size="2"><bean:write name="homeworldPrintSendForm" property="eponot1"  /><bean:write name="homeworldPrintSendForm" property="eponot2"  /><bean:write name="homeworldPrintSendForm" property="eponot3"  /></font></td>
				</tr>
				</tbody>
			 </table>	
				
			<br>   
   			 <TABLE  style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=670 border=1>
				<tr height="25">
				<td  width=100 align="center" nowrap><font size="2"><b>��ɱ���ע</b></font></td>
				<td            align="left" nowrap><font size="2"></font></td>
				</tr>
				</tbody>
			 </table>	
			<br>   
   			 <TABLE  style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=670 border=1>
				<tr height="25">
				<td  width=100 align="center" nowrap><font size="2"><b>�˻�����ע</b></font></td>
				<td  width=407  align="left" nowrap><font size="2"></font></td>
				<td  width=79 align="center" nowrap><font size="2"><b>��������</b></font></td>
				<td align="left" ><font size="2"><bean:write name="homeworldPrintSendForm" property="eposp1"  /></font></td>
				</tr>
				</tbody>
			 </table>	
			<br>   
			<TABLE class=table1border style="BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; BORDER-BOTTOM-WIDTH: 1px; BORDER-COLLAPSE: collapse; BORDER-RIGHT-WIDTH: 1px" borderColor=#111111 cellSpacing=0 borderColorDark=#000000 cellPadding=0 width=670 bgColor=#ffffff>
			<tr>
				 <TD class=tablextitle1 noWrap borderColor=#111111 width=100 height=30><FONT color=#000000 size=2><B>�ջ�����</B></FONT></TD>
				 <TD class=tablextitle1 noWrap borderColor=#111111 height=30><FONT color=#000000 size=2><B></B></FONT></TD>
			 	 <TD class=tablextitle1 noWrap borderColor=#111111 width=70 height=15><FONT color=#000000 size=2><B>�鵥��ǩ��</B></FONT></TD>
			 	 <TD class=tablextitle1 noWrap borderColor=#111111 height=30><FONT color=#000000 size=2><B></B></FONT></TD>
			 	 <TD class=tablextitle1 noWrap borderColor=#111111 width=70 height=15><FONT color=#000000 size=2><B>¼����ǩ��</B></FONT></TD>
			 	 <TD class=tablextitle1 noWrap borderColor=#111111 height=30><FONT color=#000000 size=2><B></B></FONT></TD>
			</tr><tr>
				 <TD class=tablextitle1 noWrap borderColor=#111111 width=100 height=15><FONT color=#000000 size=2><B>�ͻ���ǩ��</B></FONT></TD>
			 	 <TD class=tablextitle1 noWrap borderColor=#111111 height=30><FONT color=#000000 size=2><B></B></FONT></TD>
				 <TD class=tablextitle1 noWrap borderColor=#111111 width=70 height=15><FONT color=#000000 size=2><B>�ջ���ǩ��</B></FONT></TD>
			 	 <TD class=tablextitle1 noWrap borderColor=#111111 height=30><FONT color=#000000 size=2><B></B></FONT></TD>
			 	 <TD class=tablextitle1 noWrap borderColor=#111111 height=15><FONT color=#000000 size=2><B>������ǩ��</B></FONT></TD>
			 	 <TD class=tablextitle1 noWrap borderColor=#111111 width=70 height=30><FONT color=#000000 size=2><B></B></FONT></TD>
			</tr><tr>
				 <TD class=tablextitle1 noWrap borderColor=#111111 width=100  height=15><FONT color=#000000 size=2><B>���������ǩ��</B></FONT></TD>
			 	 <TD class=tablextitle1 colspan=5 noWrap borderColor=#111111 height=30><FONT color=#000000 size=2><B></B></FONT></TD>

			</tr>			
	        </table>
 		
 		
		</html:form>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>	
		<script language="javascript">
    OLECMDID_PRINT = 6
    OLECMDID_PRINTPREVIEW = 7
    OLECMDID_PAGESETUP = 8
    OLECMDEXECOPT_DONTPROMPTUSER = 2
    OLECMDEXECOPT_PROMPTUSER = 1
    
	function javaPrint(oleCmdId, oleCmdExecopt,form) {
	
		
		document.all.divPrint.style.visibility = "hidden";
		document.body.focus();
		IEWB.ExecWB(oleCmdId, oleCmdExecopt);
		document.all.divPrint.style.visibility = "visible";
	}
	
	function setVariables() {
		object = "divPrint";
		if (navigator.appname=="Netscape") {
			v=".top=";
			dS="document.";
			sD="";
			y="window.pageYOffset";
		} else {
			v=".pixelTop=";
			dS="";
			sD=".style";
			y="document.body.scrollTop";
		}
	}
	
	function checkLocation() {
		yy=eval(y);
		eval(dS + object + sD + v +yy);
		setTimeout("checkLocation()", 10);
		
	}

	  function Jumping(thisform){
	  thisform.selectwhere.value='<bean:write name="homeworldPrintSendForm" property="selectwhere"/>'; 
	  thisform.submit();
	  return;
	}
	
	  function Jumping1(thisform){
	  thisform.selected.value=null;
	  thisform.selectwhere.value='<bean:write name="homeworldPrintSendForm" property="selectwhere"/>'; 
	  thisform.submit();
	  return;
	  }

		//��ЧУ�����(����,��ʾ����,����)
		function floats () { 
			this.aa = new Array("mum", "�ͻ�����(��) ���������֣�","^\\d*$");
		} 
		
		
</script>
<script language="JavaScript" event=onbeforeprint for=window>
	biasLine.style.pixelTop = biasLine.style.pixelTop - 15 * 1
	biasLine.style.pixelLeft = biasLine.style.pixelLeft - 10 * 1
    document.all.divPrint.style.visibility = "hidden";
    return true;
</script>

<script language="JavaScript" event=onafterprint for=window>
 	biasLine.style.pixelTop = biasLine.style.pixelTop + 15 * 1
	biasLine.style.pixelLeft = biasLine.style.pixelLeft + 10 * 1
    document.all.divPrint.style.visibility = "visible";
    return true;
</script>

<DIV id=biasLine 
style="Z-INDEX: -1; LEFT: 10px; VISIBILITY: visible; POSITION: absolute; TOP: 210px"></DIV>

</BODY>
</html:html>
