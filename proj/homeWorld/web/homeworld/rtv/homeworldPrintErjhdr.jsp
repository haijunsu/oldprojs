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
			<html:form action="/homeworldPrintErjhdr">
			<html:errors property="errormessage"/>
		
			<html:hidden property="selectwhere"/>
			<html:hidden property="queryid"/>
			
			<%int mark=0;%>
			
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
          
          
          
          
			<table width="100%">
			   <td width="400">
			   	<font size="2">
			       <b>&nbsp收货单编号:</b><bean:write name="homeworldPrintErjhdrForm" property="erjrnm"  />
			    </font>
			   </td>
			   <td width="1500"><H2 align="center">家世界连锁商业集团有限公司<BR>收货调整单</H2></td>
	         <td width="400"><font size="2">
		      <b>日期:</b><bean:write name="homeworldPrintErjhdrForm" property="erjcdt" />
		      <br><b>时间:</b><bean:write name="homeworldPrintErjhdrForm" property="erjctm"  />
		      </font></td>
		    </table>   
		     
			 	 		
			<TABLE style="BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; BORDER-BOTTOM-WIDTH: 1px; BORDER-COLLAPSE: collapse; BORDER-RIGHT-WIDTH: 1px" borderColor=#111111 cellSpacing=0 borderColorDark=#000000 cellPadding=0 width=950 bgColor=#ffffff>
			  <tr>			 
			     <td class="printtitle1w" nowrap width=17 rowSpan=12>商<br>店</td>
				 <td class="printtitle2left" nowrap width=100 height="15" >商店       </td><td class="printtextleft" nowrap width=200><bean:write name="homeworldPrintErjhdrForm" property="erjstr"  /></td>	 
				 <td class="printtitle1w" nowrap  width=17 rowSpan=12>供<br>货<br>商<br>信<br>息</td>	
				 <td class="printtitle2left" nowrap width=100 >供货厂商   </td><td class="printtextleft" nowrap width=200><bean:write name="homeworldPrintErjhdrForm" property="erjvnd"  /></td>		      			 	 
				 <td class="printtitle1w" nowrap width=17 rowSpan=12>调<br>整<br>单<br>信<br>息</td>	
				 <td class="printtitle2left" nowrap width=100 >调整单号   </td><td class="printtextleft" nowrap width=200><bean:write name="homeworldPrintErjhdrForm" property="erjnum"  /></td>		      			 	 
	    	</tr><tr>
				 <td class="printtitle2left" nowrap height="15" >商店名称     </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErjhdrForm" property="erjstn"  /></td>
				 <td class="printtitle2left" nowrap>供货商名称   </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErjhdrForm" property="erjvdn"  /></td>				 				
				 <td class="printtitle2left" nowrap>输入日期     </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErjhdrForm" property="erjjdt"  /></td>
			</tr>
	        </table>	
	    	<br>
	        
		    <table  style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=972 border=1>
				 <logic:present name="homeworldPrintErjhdrForm" property="erjssq" >
				    <thead>
					    <th class="printtitle2centerw"  nowrap>序号</th>
						<th class="printtitle2centerw"  nowrap>sku编码</th>
						<th class="printtitle2centerw"  nowrap>描述说明</th>
						<th class="printtitle2centerw"  nowrap>供货商型号</th>
						<th class="printtitle2centerw"  nowrap>规格</th>
						<th class="printtitle2centerw"  nowrap>数量</th>
						<th class="printtitle2centerw"  nowrap>售价</th>
						<th class="printtitle2centerw"  nowrap>成本</th>
						<th class="printtitle2centerw"  nowrap>售价金额</th>
						<th class="printtitle2centerw"  nowrap>成本金额</th>
					</thead>
					<tbody id="DynData">	
					  <logic:iterate id="seq" name="homeworldPrintErjhdrForm" property="erjsku" indexId="index">
						  <logic:equal name="homeworldPrintErjhdrForm" property='<%="erjsku[" + index + "]"%>' value='minsum'>
								  <%mark=1;%>
							    <tr align="left">
							    	<td class="printtitle3centerw" nowrap  colspan=5 >小计————</td>    
									<td class="printtextright" nowrap><bean:write name="homeworldPrintErjhdrForm" property='<%="erjrqy[" + index + "]"%>'/> </td>    
									<td class="printtextright" nowrap></td>    
									<td class="printtextright" nowrap></td>    
									<td class="printtextright" nowrap><bean:write name="homeworldPrintErjhdrForm" property='<%="erjtrt[" + index + "]"%>'/> </td>    
									<td class="printtextright" nowrap><bean:write name="homeworldPrintErjhdrForm" property='<%="erjtct[" + index + "]"%>'/> </td>    
								</tr>		
							</logic:equal>
							<logic:notEqual name="homeworldPrintErjhdrForm" property='<%="erjsku[" + index + "]"%>' value='minsum'>
							
								<%if(mark==1){%>
									</tbody>
								 </table>	
							   </table>
						       <br clear=all style='page-break-before:always'>
									
							<table width="100%">
							   <td width="400">
							   	<font size="2">
							       <b>&nbsp收货单编号:</b><bean:write name="homeworldPrintErjhdrForm" property="erjrnm"  />
							    </font>
							   </td>
							   <td width="1500"><H2 align="center">家世界连锁商业集团有限公司<BR>收货调整单</H2></td>
					         <td width="400"><font size="2">
						      <b>日期:</b><bean:write name="homeworldPrintErjhdrForm" property="erjcdt" />
						      <br><b>时间:</b><bean:write name="homeworldPrintErjhdrForm" property="erjctm"  />
						      </font></td>
						    </table>   
							    
					   		 <table  style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=972 border=1>
							    <thead>
								    <th class="printtitle2centerw"  nowrap>序号</th>
									<th class="printtitle2centerw"  nowrap>sku编码</th>
									<th class="printtitle2centerw"  nowrap>描述说明</th>
									<th class="printtitle2centerw"  nowrap>供货商型号</th>
									<th class="printtitle2centerw"  nowrap>规格</th>
									<th class="printtitle2centerw"  nowrap>数量</th>
									<th class="printtitle2centerw"  nowrap>售价</th>
									<th class="printtitle2centerw"  nowrap>成本</th>
									<th class="printtitle2centerw"  nowrap>售价金额</th>
									<th class="printtitle2centerw"  nowrap>成本金额</th>
								</thead>
								<tbody id="DynData">	
									<%}%>
									<%mark=0;%>	
								    <tr align="left">				
										<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErjhdrForm" property='<%="erjssq[" + index + "]"%>'/> </td>    
										<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErjhdrForm" property='<%="erjsku[" + index + "]"%>'/> </td>    
										<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErjhdrForm" property='<%="erjskd[" + index + "]"%>'/> </td>    
										<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErjhdrForm" property='<%="erjvds[" + index + "]"%>'/> </td>    
										<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErjhdrForm" property='<%="erjmgn[" + index + "]"%>'/> </td>    
										<td class="printtextright" nowrap><bean:write name="homeworldPrintErjhdrForm" property='<%="erjrqy[" + index + "]"%>'/> </td>    
										<td class="printtextright" nowrap><bean:write name="homeworldPrintErjhdrForm" property='<%="erjret[" + index + "]"%>'/> </td>    
										<td class="printtextright"  nowrap><bean:write name="homeworldPrintErjhdrForm" property='<%="erjcst[" + index + "]"%>'/> </td>    
										<td class="printtextright"  nowrap><bean:write name="homeworldPrintErjhdrForm" property='<%="erjtrt[" + index + "]"%>'/> </td>    
										<td class="printtextright"  nowrap><bean:write name="homeworldPrintErjhdrForm" property='<%="erjtct[" + index + "]"%>'/> </td>    
									</tr>		    	
							</logic:notEqual>
					  </logic:iterate>
				</logic:present>
	

				<logic:equal name="homeworldPrintErjhdrForm" property='endnew' value='true'>
												</tbody>
											 </table>	
										   </table>
					    <br clear=all style='page-break-before:always'>
							
						<table width="100%">
						   <td width="400">
						   	<font size="2">
						       <b>&nbsp收货单编号:</b><bean:write name="homeworldPrintErjhdrForm" property="erjrnm"  />
						    </font>
						   </td>
						   <td width="1500"><H2 align="center">家世界连锁商业集团有限公司<BR>收货调整单</H2></td>
				         <td width="400"><font size="2">
					      <b>日期:</b><bean:write name="homeworldPrintErjhdrForm" property="erjcdt" />
					      <br><b>时间:</b><bean:write name="homeworldPrintErjhdrForm" property="erjctm"  />
					      </font></td>
					    </table>   
				    
						 <table  style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=972 border=1>
						    <thead>
							    <th class="printtitle2centerw"  nowrap>序号</th>
								<th class="printtitle2centerw"  nowrap>sku编码</th>
								<th class="printtitle2centerw"  nowrap>描述说明</th>
								<th class="printtitle2centerw"  nowrap>供货商型号</th>
								<th class="printtitle2centerw"  nowrap>规格</th>
								<th class="printtitle2centerw"  nowrap>数量</th>
								<th class="printtitle2centerw"  nowrap>售价</th>
								<th class="printtitle2centerw"  nowrap>成本</th>
								<th class="printtitle2centerw"  nowrap>售价金额</th>
								<th class="printtitle2centerw"  nowrap>成本金额</th>
							</thead>
							<tbody id="DynData">	
				</logic:equal>	  		
				    <tr>
						<td class="printtitle3centerw" nowrap  colspan=5 >合计————</td>    
						<td class="printtextright" nowrap><bean:write name="homeworldPrintErjhdrForm" property="countrqy"/> </td>    
						<td class="printtextright" nowrap></td>    
						<td class="printtextright" nowrap></td>    
						<td class="printtextright" nowrap><bean:write name="homeworldPrintErjhdrForm" property="counttrt"/> </td>    
						<td class="printtextright" nowrap><bean:write name="homeworldPrintErjhdrForm" property="counttct"/> </td>    
					</tr>		
				</tr>	 
				</tbody>
			 </table>	
 
 
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
	  thisform.selectwhere.value='<bean:write name="homeworldPrintErjhdrForm" property="selectwhere"/>'; 
	  thisform.submit();
	  return;
	}
	
	  function Jumping1(thisform){
	  thisform.selected.value=null;
	  thisform.selectwhere.value='<bean:write name="homeworldPrintErjhdrForm" property="selectwhere"/>'; 
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
