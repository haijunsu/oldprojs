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
    <BODY onload="setVariables();checkLocation(); this.focus()">
	    <OBJECT id=IEWB height=0 width=0 classid=clsid:8856F961-340A-11D0-A96B-00C04FD705A2></OBJECT>
		<html:errors property="errormessage"/>
			<html:form action="/homeworldPrintErthdr" >

			<html:hidden property="selectwhere"/>
			<html:hidden property="queryid"/>
			
			<%int mark=0;%>
		
        <DIV id=divPrint style="Z-INDEX: 5; RIGHT: 0px; VISIBILITY: visible; POSITION: absolute; TOP: 0px">
          <TABLE cellSpacing=0 cellPadding=0 bgColor=#3366ff border=1>
           <TR>
            <TD>
                <INPUT onclick="javaPrint(OLECMDID_PAGESETUP, OLECMDEXECOPT_DONTPROMPTUSER)" type=button value=ҳ������> 
                <INPUT onclick="javaPrint(OLECMDID_PRINTPREVIEW, OLECMDEXECOPT_PROMPTUSER)" type=button value=��ӡԤ��> 
                <INPUT onclick="javaPrint(OLECMDID_PRINT, OLECMDEXECOPT_DONTPROMPTUSER)" type=button value=��ӡ> 
            </TD>
          </TR></TABLE></DIV>
          
          
			<table width=620>
			   <td ><font size="2">
			       <b>&nbsp��������:</b><bean:write name="homeworldPrintErthdrForm" property="ertnum"  />
			    </font>
			   </td>
			   <td ><H2 align="center">������������ҵ�������޹�˾<BR>������Ȩ�� </H2></td>
	         <td ><font size="2">
		      <b>����:</b><bean:write name="homeworldPrintErthdrForm" property="ertcdt" />
		      <br><b>ʱ��:</b><bean:write name="homeworldPrintErthdrForm" property="ertctm"  />
		      </font></td>
		    </table>   
		    
			 	 		
			<TABLE style="BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; BORDER-BOTTOM-WIDTH: 1px; BORDER-COLLAPSE: collapse; BORDER-RIGHT-WIDTH: 1px" borderColor=#111111 height=336 cellSpacing=0 borderColorDark=#000000 cellPadding=0 width=620 bgColor=#ffffff>
			  <tr>			 
			     <td class="printtitle1w" nowrap rowSpan=12>��<br>��</td>
				 <td class="printtitle2left" nowrap  >�̵�       </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertstr"  /></td>	 
				 <td class="printtitle1w" nowrap  rowSpan=12>��<br>��<br>��<br>��<br>Ϣ</td>	
				 <td class="printtitle2left" nowrap >��Ӧ<br>�̺�    </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertvnd"  /></td>		      			 	 
			 	 <td class="printtitle1w" nowrap  rowSpan=12>��<br>��<br>��<br>��<br>Ϣ</td>	
				 <td class="printtitle2left"  nowrap >����<br>����    </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertnum"  /></td>		      			 
	    	</tr><tr>
				 <td class="printtitle2left"  nowrap >�̵�<br>����     </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertstn"  /></td>
				 <td class="printtitle2left"  nowrap >������<br>����   </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertnam"  /></td>				 				
				 <td class="printtitle2left"  nowrap >���<br>����     </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertvra"  /></td>
			</tr><tr>
				 <td class="printtitle2left"  nowrap >�̵�<br>��ַ1     </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertsa1"  /></td>
    			 <td class="printtitle2left"  nowrap >������<br>��ַ1     </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertad1"  /></td>			
				 <td class="printtitle2left"  nowrap >��Ȩ��       </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertaut"  /></td>
			</tr><tr>
				 <td class="printtitle2left"  nowrap >�̵�<br>��ַ2    </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertsa2"  /></td>         
				 <td class="printtitle2left"  nowrap >������<br>��ַ2    </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertad2"  /></td>				         
				 <td class="printtitle2left"  nowrap >WITH<br>DRAWAL   </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertvwd" /></td>
			</tr><tr>
				 <td class="printtitle2left"  nowrap >�̵�<br>��ַ3    </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertsa3"  /></td>         
				 <td class="printtitle2left"  nowrap >������<br>��ַ3    </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertad3"  /></td>				         
				 <td class="printtitle2left"  nowrap >����<br>����     </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertsdt" /></td>
			
			
			</tr><tr>
				 <td class="printtitle2left"  nowrap >����<br>�ʱ�    </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertsct"  />��<bean:write name="homeworldPrintErthdrForm" property="ertszp"  /></td>			
				 <td class="printtitle2left"  nowrap >������<br>����,�ʱ�</td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertvct"  />��<bean:write name="homeworldPrintErthdrForm" property="ertvzp"  /></td>			
   				 <td class="printtitle2left"  nowrap >����<br>ԭ��     </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertrsn"  /></td>
				
			</tr><tr>			
				 <td class="printtitle2left"  nowrap >�̵�<br>���� 	 </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertscn"  /></td>
				 <td class="printtitle2left"  nowrap >������<br>����  </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertcnt"  /></td>
			     <td class="printtitle2left" nowrap  >����<br>ָ��     </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertins"  /></td>
			</tr><tr>
				 <td class="printtitle2left" nowrap  >��ϵ��       </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertsmg"  /></td>
				 <td class="printtitle2left" nowrap  >������       </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertacn"  /></td>				 
   				 <td class="printtitle2left" nowrap  ></td><td class="printtextleft" ></td>				 
			</tr><tr>				 
				 <td class="printtitle2left"  nowrap >�̵�<br>�绰     </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertsph"  /></td>
				 <td class="printtitle2left"  nowrap >����<br>�绰     </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertphn"  /></td>
				 <td class="printtitle2left"  nowrap ></td><td class="printtextleft" ></td>				 
		    </tr><tr>
				 <td class="printtitle2left"  nowrap >�̵�<br>����   	 </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertsfx"  /></td>
				 <td class="printtitle2left"  nowrap >����<br>����	 </td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertfax"  /></td>
				 <td class="printtitle2left"  nowrap ></td><td class="printtextleft" ></td>				 
			</tr><tr>
				 <td class="printtitle2left"  nowrap ></td><td class="printtextleft" ></td>
				 <td class="printtitle2left"  nowrap ></td><td class="printtextleft" ></td>
				 <td class="printtitle2left"  nowrap ></td><td class="printtextleft" ></td>				 
			</tr><tr>
				 <td class="printtitle2left"  nowrap ></td><td class="printtextleft" ></td>
				 <td class="printtitle2left"  nowrap ></td><td class="printtextleft" ><bean:write name="homeworldPrintErthdrForm" property="ertfax"  /></td>
				 <td class="printtitle2left"  nowrap ></td><td class="printtextleft" ></td>				 
			</tr>
	        </table>	
	        <br>      
	        <logic:present name="homeworldPrintErthdrForm" property="oertsku" >
		        <table  style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=620 border=1>
				    <thead>
						<th class="printtitle2centerw"  nowrap>sku</th>
						<th class="printtitle2centerw"  nowrap>����</th>
						<th class="printtitle2centerw"  nowrap>����</th>
						<th class="printtitle2centerw"  nowrap>����ͺ�</th>
						<th class="printtitle2centerw"  nowrap>�����ͺ�</th>
						<th class="printtitle2centerw"  nowrap>����λ</th>
						<th class="printtitle2centerw"  nowrap>���۵�λ</th>
						<th class="printtitle2centerw"  nowrap>�˻��ɱ�</th>
						<th class="printtitle2centerw"  nowrap>�˻�����</th>
					</thead>
					<tbody id="DynData">	
					<logic:iterate id="seq" name="homeworldPrintErthdrForm" property="oertsku" indexId="index">
						<logic:notEqual name="homeworldPrintErthdrForm" property='<%="oertsku[" + index + "]"%>' value='minsum'>
						    <tr align="left">				
								<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErthdrForm" property='<%="oertsku[" + index + "]"%>'/> </td>    
								<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErthdrForm" property='<%="oertskd[" + index + "]"%>'/> </td>    
								<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErthdrForm" property='<%="oertupc[" + index + "]"%>'/> </td>    
								<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErthdrForm" property='<%="oertmfg[" + index + "]"%>'/> </td>    
								<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErthdrForm" property='<%="oertvdn[" + index + "]"%>'/> </td>    
								<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErthdrForm" property='<%="oertbum[" + index + "]"%>'/> </td>    
								<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErthdrForm" property='<%="oertsum[" + index + "]"%>'/> </td>    
								<td class="printtextright" nowrap><bean:write name="homeworldPrintErthdrForm" property='<%="oertcst[" + index + "]"%>'/> </td>    
								<td class="printtextright" nowrap><bean:write name="homeworldPrintErthdrForm" property='<%="oertshq[" + index + "]"%>'/> </td>    
							</tr>	
						</logic:notEqual>
					    <logic:equal name="homeworldPrintErthdrForm" property='<%="oertsku[" + index + "]"%>' value='minsum'>
							    <tr align="left">
									<td class="printtitle3centerw" nowrap  colspan=7 >С�ơ�������</td>    
									<td class="printtextright" nowrap><bean:write name="homeworldPrintErthdrForm" property='<%="oertcst[" + index + "]"%>'/> </td>    
									<td class="printtextright" nowrap><bean:write name="homeworldPrintErthdrForm" property='<%="oertshq[" + index + "]"%>'/> </td>    
								</tr>	
						</logic:equal>
					  </logic:iterate>
	        </logic:present>

		<logic:notEqual name="homeworldPrintErthdrForm" property='onlyone' value='true'>
								
					</tbody>
				 </table>	
				<br clear=all style='page-break-before:always'>
				<table width=620>
				   <td><font size="2">
				       <b>&nbsp��������:</b><bean:write name="homeworldPrintErthdrForm" property="ertnum"  />
				    </font>
				   </td>
				   <td><H2 align="center">������������ҵ�������޹�˾<BR>������Ȩ�� </H2></td>
		         <td><font size="2">
			      <b>����:</b><bean:write name="homeworldPrintErthdrForm" property="ertcdt" />
			      <br><b>ʱ��:</b><bean:write name="homeworldPrintErthdrForm" property="ertctm"  />
			      </font></td>
			    </table>   
		    	<br>	
			</logic:notEqual>	  	
			
			



	  		<logic:present name="homeworldPrintErthdrForm" property="ertsku" >
			    <table  style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=620 border=1>
				
					    <thead>
							<th class="printtitle2centerw"  nowrap>sku</th>
							<th class="printtitle2centerw"  nowrap>����</th>
							<th class="printtitle2centerw"  nowrap>����</th>
							<th class="printtitle2centerw"  nowrap>����ͺ�</th>
							<th class="printtitle2centerw"  nowrap>�����ͺ�</th>
							<th class="printtitle2centerw"  nowrap>����λ</th>
							<th class="printtitle2centerw"  nowrap>���۵�λ</th>
							<th class="printtitle2centerw"  nowrap>�˻��ɱ�</th>
							<th class="printtitle2centerw"  nowrap>�˻�����</th>
						</thead>
						<tbody id="DynData">	
						  <logic:iterate id="seq" name="homeworldPrintErthdrForm" property="ertsku" indexId="index">
							  <logic:equal name="homeworldPrintErthdrForm" property='<%="ertsku[" + index + "]"%>' value='minsum'>
									  <%mark=1;%>
									    <tr align="left">
											<td class="printtitle3centerw" nowrap  colspan=7 >С�ơ�������</td>    
											<td class="printtextright" nowrap><bean:write name="homeworldPrintErthdrForm" property='<%="ertcst[" + index + "]"%>'/> </td>    
											<td class="printtextright" nowrap><bean:write name="homeworldPrintErthdrForm" property='<%="ertshq[" + index + "]"%>'/> </td>    
										</tr>	
								</logic:equal>
								<logic:notEqual name="homeworldPrintErthdrForm" property='<%="ertsku[" + index + "]"%>' value='minsum'>
									<%if(mark==1){%>
										</tbody>
									 </table>	
								   </table>
							        <br clear=all style='page-break-before:always'>
									<table width=620>
									   <td><font size="2">
									       <b>&nbsp��������:</b><bean:write name="homeworldPrintErthdrForm" property="ertnum"  />
									    </font>
									   </td>
									   <td><H2 align="center">������������ҵ�������޹�˾<BR>������Ȩ�� </H2></td>
							         <td><font size="2">
								      <b>����:</b><bean:write name="homeworldPrintErthdrForm" property="ertcdt" />
								      <br><b>ʱ��:</b><bean:write name="homeworldPrintErthdrForm" property="ertctm"  />
								      </font></td>
								    </table>   
								    <table  style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=620 border=1>
										    <thead>
												<th class="printtitle2centerw"  nowrap>sku</th>
												<th class="printtitle2centerw"  nowrap>����</th>
												<th class="printtitle2centerw"  nowrap>����</th>
												<th class="printtitle2centerw"  nowrap>����ͺ�</th>
												<th class="printtitle2centerw"  nowrap>�����ͺ�</th>
												<th class="printtitle2centerw"  nowrap>����λ</th>
												<th class="printtitle2centerw"  nowrap>���۵�λ</th>
												<th class="printtitle2centerw"  nowrap>�˻��ɱ�</th>
												<th class="printtitle2centerw"  nowrap>�˻�����</th>
											</thead>
											<tbody id="DynData">	
									<%}%>
									<%mark=0;%>
								    <tr align="left">				
										<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErthdrForm" property='<%="ertsku[" + index + "]"%>'/> </td>    
										<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErthdrForm" property='<%="ertskd[" + index + "]"%>'/> </td>    
										<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErthdrForm" property='<%="ertupc[" + index + "]"%>'/> </td>    
										<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErthdrForm" property='<%="ertmfg[" + index + "]"%>'/> </td>    
										<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErthdrForm" property='<%="ertvdn[" + index + "]"%>'/> </td>    
										<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErthdrForm" property='<%="ertbum[" + index + "]"%>'/> </td>    
										<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErthdrForm" property='<%="ertsum[" + index + "]"%>'/> </td>    
										<td class="printtextright" nowrap><bean:write name="homeworldPrintErthdrForm" property='<%="ertcst[" + index + "]"%>'/> </td>    
										<td class="printtextright" nowrap><bean:write name="homeworldPrintErthdrForm" property='<%="ertshq[" + index + "]"%>'/> </td>    
									</tr>		
								</logic:notEqual>
						  </logic:iterate>
					</logic:present>
	

			<logic:equal name="homeworldPrintErthdrForm" property='endnew' value='true'>
									</tbody>
								 </table>	
							  
		        <br clear=all style='page-break-before:always'>
				<table width=620>
				   <td><font size="2">
				       <b>&nbsp��������:</b><bean:write name="homeworldPrintErthdrForm" property="ertnum"  />
				    </font>
				   </td>
				   <td><H2 align="center">������������ҵ�������޹�˾<BR>������Ȩ�� </H2></td>
		         <td><font size="2">
			      <b>����:</b><bean:write name="homeworldPrintErthdrForm" property="ertcdt" />
			      <br><b>ʱ��:</b><bean:write name="homeworldPrintErthdrForm" property="ertctm"  />
			      </font></td>
			    </table>   
			    <table  style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=620 border=1>
					    <thead>
							<th class="printtitle2centerw"  nowrap>sku</th>
							<th class="printtitle2centerw"  nowrap>����</th>
							<th class="printtitle2centerw"  nowrap>����</th>
							<th class="printtitle2centerw"  nowrap>����ͺ�</th>
							<th class="printtitle2centerw"  nowrap>�����ͺ�</th>
							<th class="printtitle2centerw"  nowrap>����λ</th>
							<th class="printtitle2centerw"  nowrap>���۵�λ</th>
							<th class="printtitle2centerw"  nowrap>�˻��ɱ�</th>
							<th class="printtitle2centerw"  nowrap>�˻�����</th>
						</thead>
						<tbody id="DynData">	
			</logic:equal>	  		
				
				<tr> 
					<td class="printtitle3centerw" nowrap  colspan=7 >�ϼơ�������</td>    
					<td class="printtextright" nowrap><bean:write name="homeworldPrintErthdrForm" property='sumcst'/> </td>    
					<td class="printtextright" nowrap><bean:write name="homeworldPrintErthdrForm" property='sumshq'/> </td>    
				</tr>	 
				</tbody>
			 </table>	
 
		   <br>
		    <table  align="left" style="BORDER-RIGHT: 1px solid; BORDER-TOP: 1px solid; BORDER-LEFT: 1px solid; BORDER-BOTTOM: 1px solid; BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 border=1 width=500>
		      <tr> 
				 <td class="printtitle3centerw" nowrap>������</td><td class="printtextcenter" nowrap><bean:write name="homeworldPrintErthdrForm" property="ertshb"  /></td>
			  </tr><tr>				 
				 <td class="printtitle3centerw" nowrap>������</td><td class="printtextcenter" nowrap><bean:write name="homeworldPrintErthdrForm" property="ertcar"  /></td>
			  </tr><tr>
				 <td class="printtitle3centerw" nowrap>��ע</td><td class="printtextcenter" nowrap><bean:write name="homeworldPrintErthdrForm" property="ertcom"  /></td>
			  </tr><tr>
				 <td class="printtitle3centerw" nowrap>REQUESTED BY</td><td class="printtextcenter" nowrap><bean:write name="homeworldPrintErthdrForm" property="ertreq"  /></td>
 			  </tr>
			</table>	
						 
<br>		
		</html:form>
		
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>	
		<script language=javascript>
    OLECMDID_PRINT = 6
    OLECMDID_PRINTPREVIEW = 7
    OLECMDID_PAGESETUP = 8
    OLECMDEXECOPT_DONTPROMPTUSER = 2
    OLECMDEXECOPT_PROMPTUSER = 1
    
	function javaPrint(oleCmdId, oleCmdExecopt) {
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
	  thisform.selectwhere.value='<bean:write name="homeworldPrintErthdrForm" property="selectwhere"/>'; 
	  thisform.submit();
	  return;
	}
	
	  function Jumping1(thisform){
	  thisform.selected.value=null;
	  thisform.selectwhere.value='<bean:write name="homeworldPrintErthdrForm" property="selectwhere"/>'; 
	  thisform.submit();
	  return;
	  }

		
</script>
<script language=JavaScript event=onbeforeprint for=window>
	biasLine.style.pixelTop = biasLine.style.pixelTop - 15 * 1
	biasLine.style.pixelLeft = biasLine.style.pixelLeft - 10 * 1
    document.all.divPrint.style.visibility = "hidden";
    return true;
</script>

<script language=JavaScript event=onafterprint for=window>
 	biasLine.style.pixelTop = biasLine.style.pixelTop + 15 * 1
	biasLine.style.pixelLeft = biasLine.style.pixelLeft + 10 * 1
    document.all.divPrint.style.visibility = "visible";
    return true;
</script>

<DIV id=biasLine 
style="Z-INDEX: -1; LEFT: 10px; VISIBILITY: visible; POSITION: absolute; TOP: 210px"></DIV>

</BODY>
</html:html>
