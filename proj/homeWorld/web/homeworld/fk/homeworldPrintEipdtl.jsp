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
	    
        <html:form action="/homeworldPrintEipdtl">
        <html:errors property="errormessage"/>            

         <html:hidden property="pagerow" />
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

<br><br><br><br><br><br>			
  			<H2 align="center">������������ҵ�������޹�˾<BR>��Ʊ��Ϣ��</H2>
  
			<logic:present name="homeworldPrintEipdtlForm" property="seq"> 
		     <TABLE id=AutoNumber2 style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=972 border=1>
			    <thead>
			        <th class="printtitle2centerw">���</th>
					<th class="printtitle2centerw">�̵�</th>
					<th class="printtitle2centerw">���׵����</th>
					<th class="printtitle2centerw">��������</th>
					<th class="printtitle2centerw">���</th>
					<th class="printtitle2centerw">��������</th>
					<th class="printtitle2centerw">Ӧ������</th>
					<th class="printtitle2centerw">��Ʊ�ֹ�</th>
					<th class="printtitle2centerw">��Ʊ��</th>
					<th class="printtitle2centerw">���</th>
					<th class="printtitle2centerw">˰��(%)</th>
				</thead>
				<tbody id="DynData">
				  <logic:iterate id="seq" name="homeworldPrintEipdtlForm" property="seq" indexId="index">
				    <tr align="left"> 
                        <td class="printtextcenter" ><bean:write name="homeworldPrintEipdtlForm" property='<%="seq[" + index + "]"%>'/></td>                                      					    		
                        <td class="printtextcenter" ><bean:write name="homeworldPrintEipdtlForm" property='<%="eipstr[" + index + "]"%>'/></td>    
						<td class="printtextcenter" ><bean:write name="homeworldPrintEipdtlForm" property='<%="eipnum[" + index + "]"%>'/></td>    
						<td class="printtextcenter" ><bean:write name="homeworldPrintEipdtlForm" property='<%="eiptyp[" + index + "]"%>'/></td>    
						<td class="printtextright" ><bean:write name="homeworldPrintEipdtlForm" property='<%="eipamt[" + index + "]"%>'/></td>    
						<td class="printtextcenter" ><bean:write name="homeworldPrintEipdtlForm" property='<%="eipdta[" + index + "]"%>'/></td>    
						<td class="printtextcenter" ><bean:write name="homeworldPrintEipdtlForm" property='<%="eipyta[" + index + "]"%>'/></td>    
						<td class="printtextcenter" ><bean:write name="homeworldPrintEipdtlForm" property='<%="eivtrk[" + index + "]"%>'/></td>    
						<td class="printtextcenter" ><bean:write name="homeworldPrintEipdtlForm" property='<%="eivivn[" + index + "]"%>'/></td>    
						<td class="printtextright" ><bean:write name="homeworldPrintEipdtlForm" property='<%="eivamt[" + index + "]"%>'/></td>    
						<td class="printtextcenter" ><bean:write name="homeworldPrintEipdtlForm" property='<%="eivtax[" + index + "]"%>'/></td>    
					</tr>	
					</logic:iterate>
				</logic:present>
				</tbody>
			 </table>	
		   </table>
		   
		   <br><br>
		   
		   
			<logic:present name="homeworldPrintEipdtlForm" property="seqmx"> 
			<H2 align="center">��Ʊ��Ϣ��ϸ</H2>
		     <TABLE id=AutoNumber2 style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=972 border=1>
			    <thead>
					<th class="printtitle2centerw">���</th>
			        <th class="printtitle2centerw">��Ʊ�ֹ�</th>
					<th class="printtitle2centerw">��Ʊ��</th>
					<th class="printtitle2centerw">���</th>
					<th class="printtitle2centerw">˰��(%)</th>			
					<th class="printtitle2centerw">��Ʊ����</th>	
				</thead>
				<tbody id="DynData">
				
				  <logic:iterate id="seqmx" name="homeworldPrintEipdtlForm" property="seqmx" indexId="index">
					  <logic:notEqual name="homeworldPrintEipdtlForm" property='<%="seqmx[" + index + "]"%>' value=''>
					  
					   <%if(mark==1){%>
									</tbody>
								 </table>	
							   </table>
				   
							<br><br>
							
						     <TABLE id=AutoNumber2 style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=972 border=1>
							    <thead>
									<th class="printtitle2centerw">���</th>
							        <th class="printtitle2centerw">��Ʊ�ֹ�</th>
									<th class="printtitle2centerw">��Ʊ��</th>
									<th class="printtitle2centerw">���</th>
									<th class="printtitle2centerw">˰��(%)</th>	
									<th class="printtitle2centerw">��Ʊ����</th>	
								</thead>
								<tbody id="DynData">
							<%}%>	
							<%mark=0;%>	
						    <tr align="left"> 
		                        <td class="printtextcenter" ><bean:write name="homeworldPrintEipdtlForm" property='<%="seqmx[" + index + "]"%>'/></td>                                      					    		
		                        <td class="printtextcenter" ><bean:write name="homeworldPrintEipdtlForm" property='<%="eivtrkmx[" + index + "]"%>'/></td>    
								<td class="printtextcenter" ><bean:write name="homeworldPrintEipdtlForm" property='<%="eivivnmx[" + index + "]"%>'/></td>    
								<td class="printtextright"  ><bean:write name="homeworldPrintEipdtlForm" property='<%="eivamtmx[" + index + "]"%>'/></td>    
								<td class="printtextcenter" ><bean:write name="homeworldPrintEipdtlForm" property='<%="eivtaxmx[" + index + "]"%>'/></td> 
								<td class="printtextcenter" ><bean:write name="homeworldPrintEipdtlForm" property='<%="eivdat[" + index + "]"%>'/></td> 
							</tr>	
						
						
						</logic:notEqual>
						<logic:equal name="homeworldPrintEipdtlForm" property='<%="seqmx[" + index + "]"%>' value=''>
							<%mark=1;%>
						</logic:equal>	
						
					</logic:iterate>
				</logic:present>
				</tbody>
			 </table>	
		   </table>
		   
		   
		   
		   
		   
		<br clear=all style='page-break-before:always'>
		
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
	  thisform.selectwhere.value='<bean:write name="homeworldPrintEipdtlForm" property="selectwhere"/>'; 
	  thisform.submit();
	  return;
	}
	
	  function Jumping1(thisform){
	  thisform.selected.value=null;
	  thisform.selectwhere.value='<bean:write name="homeworldPrintEipdtlForm" property="selectwhere"/>'; 
	  thisform.submit();
	  return;
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
