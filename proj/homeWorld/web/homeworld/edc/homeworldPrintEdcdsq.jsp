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
    <BODY onload="setVariables();checkLocation(); this.focus()">
	    <OBJECT id=IEWB height=0 width=0 classid=clsid:8856F961-340A-11D0-A96B-00C04FD705A2></OBJECT>
		<html:errors property="errormessage"/>
			<html:form action="/homeworldPrintEdcdsq" >

			<html:hidden property="selectwhere"/>
			<html:hidden property="queryid"/>
			
		<BR><br>
        <DIV id=divPrint style="Z-INDEX: 5; RIGHT: 0px; VISIBILITY: visible; POSITION: absolute; TOP: 0px">
          <TABLE cellSpacing=0 cellPadding=0 bgColor=#3366ff border=1>
           <TR>
            <TD>
                <INPUT onclick="javaPrint(OLECMDID_PAGESETUP, OLECMDEXECOPT_DONTPROMPTUSER)" type=button value=页面设置> 
                <INPUT onclick="javaPrint(OLECMDID_PRINTPREVIEW, OLECMDEXECOPT_PROMPTUSER)" type=button value=打印预览> 
                <INPUT onclick="javaPrint(OLECMDID_PRINT, OLECMDEXECOPT_DONTPROMPTUSER)" type=button value=打印> 
            </TD>
          </TR></TABLE></DIV>
          
          <logic:equal name="homeworldPrintEdcdsqForm" property='endnew' value='0'>  
          	<center>表中无记录</center>
          </logic:equal>
          
        <logic:notEqual name="homeworldPrintEdcdsqForm" property='endnew' value='0'>  
				<table width=620>
				   <td ><font size="2">
			    	   <b>订单号:</b><bean:write name="homeworldPrintEdcdsqForm" property='<%="dqponm[0]"%>'/> 
			    	   <br><b>订单地点:</b><bean:write name="homeworldPrintEdcdsqForm" property='<%="dqpstr[0]"%>'/> 
				    </font>
				   </td>
				   <td ><H2 align="center">家世界连锁商业集团有限公司<BR>商品分配清单 </H2></td>
			        <td ><font size="2">
			      <b>订单日期:</b><bean:write name="homeworldPrintEdcdsqForm" property='<%="dqpdat[0]"%>'/>
			      <br><b></b>
			      </font></td>
			    </table>   	
    
	    	<br>
		    <table width=620>
				<logic:present name="homeworldPrintEdcdsqForm" property="dqdstr" >
				    <thead>
						<th nowrap>商店</th>
						<th nowrap>SKU</th>
						<th nowrap>品名</th>
						<th nowrap>货号</th>
						<th nowrap>分配数量/</th>
						<th nowrap>箱数</th>
						<th nowrap>实际分配数量</th>
					</thead>
					<tbody id="DynData">	
					  <logic:iterate id="seq" name="homeworldPrintEdcdsqForm" property="dqdstr" indexId="index">
					  
						  <logic:equal name="homeworldPrintEdcdsqForm" property='<%="dqdstr[" + index + "]"%>' value='pagenew'>
							</tbody>
								 </table>	
							   </table>
						        <br clear=all style='page-break-before:always'>
						        
							
								<table width=620>
								   <td ><font size="2">
							    	   <b>订单号:</b><bean:write name="homeworldPrintEdcdsqForm" property='<%="dqponm[0]"%>'/> 
							    	   <br><b>订单地点:</b><bean:write name="homeworldPrintEdcdsqForm" property='<%="dqpstr[0]"%>'/> 
								    </font>
								   </td>
								   <td ><H2 align="center">家世界连锁商业集团有限公司<BR>商品分配清单 </H2></td>
							        <td ><font size="2">
							      <b>订单日期:</b><bean:write name="homeworldPrintEdcdsqForm" property='<%="dqpdat[0]"%>'/>
							      <br><b></b>
							      </font></td>
							    </table> 
							    
							    <table width=620>
								    <thead>
										<th nowrap>商店</th>
										<th nowrap>SKU</th>
										<th nowrap>品名</th>
										<th nowrap>货号</th>
										<th nowrap>分配数量/</th>
										<th nowrap>箱数</th>
										<th nowrap>实际分配数量</th>
									</thead>
									<tbody id="DynData">	
							</logic:equal>
							
							<logic:notEqual name="homeworldPrintEdcdsqForm" property='<%="dqdstr[" + index + "]"%>' value='pagenew'>
							    <tr align="center">				
									<td nowrap><bean:write name="homeworldPrintEdcdsqForm" property='<%="dqdstr[" + index + "]"%>'/> </td>    
									<td nowrap><bean:write name="homeworldPrintEdcdsqForm" property='<%="dqsku[" + index + "]"%>'/> </td>    
									<td nowrap><bean:write name="homeworldPrintEdcdsqForm" property='<%="dqskud[" + index + "]"%>'/> </td>    
									<td nowrap><bean:write name="homeworldPrintEdcdsqForm" property='<%="dqvndn[" + index + "]"%>'/> </td>    
									<td nowrap><bean:write name="homeworldPrintEdcdsqForm" property='<%="dqdqty[" + index + "]"%>'/> </td>    
									<td nowrap><bean:write name="homeworldPrintEdcdsqForm" property='<%="dqdcas[" + index + "]"%>'/> </td>    
									<td nowrap>------</td>    
									
								</tr>		
							
									<logic:equal name="homeworldPrintEdcdsqForm" property='<%="dqsku[" + index + "]"%>' value=''>
										<tr><td colspan=7><hr></td></tr>			
			  						</logic:equal>
							</logic:notEqual>
					  </logic:iterate>
				</logic:present>

				</tbody>
			 </table>	
</logic:notEqual>
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
	  thisform.selectwhere.value='<bean:write name="homeworldPrintEdcdsqForm" property="selectwhere"/>'; 
	  thisform.submit();
	  return;
	}
	
	  function Jumping1(thisform){
	  thisform.selected.value=null;
	  thisform.selectwhere.value='<bean:write name="homeworldPrintEdcdsqForm" property="selectwhere"/>'; 
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
