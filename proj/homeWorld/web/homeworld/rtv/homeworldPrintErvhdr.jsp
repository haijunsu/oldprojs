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
			<html:form action="/homeworldPrintErvhdr" >

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
			       <b>&nbsp收货单编号:</b><bean:write name="homeworldPrintErvhdrForm" property="ervnum"  />
			    </font>
			   </td>
			   <td width="1500"><H2 align="center">家世界连锁商业集团有限公司<BR>收货单 </H2></td>
	         <td width="400"><font size="2">
		      <b>日期:</b><bean:write name="homeworldPrintErvhdrForm" property="ervcdt" />
		      <br><b>时间:</b><bean:write name="homeworldPrintErvhdrForm" property="ervctm"  />
		      </font></td>
		    </table>  
		    
			<TABLE style="BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; BORDER-BOTTOM-WIDTH: 1px; BORDER-COLLAPSE: collapse; BORDER-RIGHT-WIDTH: 1px" borderColor=#111111 height=336 cellSpacing=0 borderColorDark=#000000 cellPadding=0 width=950 bgColor=#ffffff>
			  <tr>			 
			     <td class="printtitle1w" nowrap width=17 rowSpan=12>商<br>店</td>
				 <td class="printtitle2left" nowrap>商店       </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervstr"  /></td>	 
				 <td class="printtitle1w" nowrap  width=17 rowSpan=12>供<br>货<br>商<br>信<br>息</td>	
				 <td class="printtitle2left" nowrap>供货厂商   </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervvnd"  /></td>		      			 	 
				 <td class="printtitle1w" nowrap width=17 rowSpan=12>收<br>货<br>单<br>信<br>息</td>	
				 <td class="printtitle2left" nowrap>收货单号   </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervnum"  /></td>		      			 	 
	    	</tr><tr>
				 <td class="printtitle2left" nowrap>商店名称     </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervstn"  /></td>
				 <td class="printtitle2left" nowrap>供货商名称   </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervvdn"  /></td>				 				
				 <td class="printtitle2left" nowrap>输入日期     </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervedt"  /></td>		      			 	 
			</tr><tr>
				 <td class="printtitle2left" nowrap>采购员      </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervbyr"  /></td>
    			 <td class="printtitle2left" nowrap>供货商地址1 </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervva1"  /></td>			
    			 <td class="printtitle2left" nowrap>收货日期    </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervrdt"  /></td>		      			 	 

			</tr><tr>
				 <td class="printtitle2left" nowrap>采购名    </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervbyn"  /></td>         
				 <td class="printtitle2left" nowrap>供货商地址2    </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervva2"  /></td>				         
				 <td class="printtitle2left" nowrap>收货单状态   </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervsts"  /></td>		      			 	 

			</tr><tr>
				 <td class="printtitle2left" nowrap>部门    </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervdpt"  /></td>         
				 <td class="printtitle2left" nowrap>供货商地址3    </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervva3"  /></td>	
				 <td class="printtitle2left" nowrap>生成日期  </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervcdt"  /></td>			        
			
			</tr><tr>
				 <td class="printtitle2left" nowrap>部门名    </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervdpn"  /></td>			
				 <td class="printtitle2left" nowrap>供货商城市,邮编</td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervvcy"  />，<bean:write name="homeworldPrintErvhdrForm" property="ervvzp"  /></td>			
				 <td class="printtitle2left" nowrap>生成时间	</td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervctm"  /></td>				 
   				
			</tr><tr>			
				 <td class="printtitle2left" nowrap>子部门 	 </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervsdpt"  /></td>
				 <td class="printtitle2left" nowrap>供货商国家  </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervvcn"  /></td>
				 <td class="printtitle2left" nowrap>订单编号</td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervpno"  /></td>

			</tr><tr>
				 <td class="printtitle2left" nowrap>子部门名  </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervsdptn"  /></td>
				 <td class="printtitle2left" nowrap>联络人       </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervvct"  /></td>				 
				 <td class="printtitle2left" nowrap></td><td class="printtextleft" nowrap></td>
			 
			</tr><tr>				 
				 <td class="printtitle2left" nowrap>运输     </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervfrc"  /></td>
				 <td class="printtitle2left" nowrap>票期代码 	 </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervtrm"  /></td>
				 <td class="printtitle2left" nowrap></td><td class="printtextleft" nowrap></td>

			</tr><tr>
				 <td class="printtitle2left" nowrap>运输描述 </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervfrn"  /></td>
				 <td class="printtitle2left" nowrap>票期名	 </td><td class="printtextleft" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervtmn"  /></td>
				 <td class="printtitle2left" nowrap></td><td class="printtextleft" nowrap></td>
			</tr>
	        </table>	
	        
	        <br clear=all style='page-break-before:always'>
			<table width="100%">
			   <td width="400">
			   	<font size="2">
			       <b>&nbsp收货单编号:</b><bean:write name="homeworldPrintErvhdrForm" property="ervnum"  />
			    </font>
			   </td>
			   <td width="1500"><H2 align="center">家世界连锁商业集团有限公司<BR>收货单 </H2></td>
	         <td width="400"><font size="2">
		      <b>日期:</b><bean:write name="homeworldPrintErvhdrForm" property="ervcdt" />
		      <br><b>时间:</b><bean:write name="homeworldPrintErvhdrForm" property="ervctm"  />
		      </font></td>
		    </table>  
		    
	    	<br>
		    <table  style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=972 border=1>
				<logic:present name="homeworldPrintErvhdrForm" property="ervsku" >
				    <thead>
					    <th class="printtitle2centerw"  nowrap>序号</th>
						<th class="printtitle2centerw"  nowrap>sku编码</th>
						<th class="printtitle2centerw"  nowrap>描述说明</th>
						<th class="printtitle2centerw"  nowrap>供货商型号</th>
						<th class="printtitle2centerw"  nowrap>销售<br>单位</th>
						<th class="printtitle2centerw"  nowrap>采购<br>单位</th>
						<th class="printtitle2centerw"  nowrap>upc号</th>
						<th class="printtitle2centerw"  nowrap>规格</th>
						<th class="printtitle2centerw"  nowrap>订货数<br>（包）</th>
						<th class="printtitle2centerw"  nowrap>订货数<br>（件）</th>
						<th class="printtitle2centerw"  nowrap>收货数<br>（件）</th>
						<th class="printtitle2centerw"  nowrap>欠收数<br>（件）</th>
						<th class="printtitle2centerw"  nowrap>折扣标志</th>
					</thead>
					<tbody id="DynData">	
					  <logic:iterate id="seq" name="homeworldPrintErvhdrForm" property="ervsku" indexId="index">
						  <logic:equal name="homeworldPrintErvhdrForm" property='<%="ervsku[" + index + "]"%>' value='minsum'>
								  <%mark=1;%>
							    <tr align="left">
									<td class="printtitle3centerw" nowrap  colspan=8 >小计————</td>    
									<td class="printtextright" nowrap><bean:write name="homeworldPrintErvhdrForm" property='<%="ervcas[" + index + "]"%>'/> </td>    
									<td class="printtextright" nowrap><bean:write name="homeworldPrintErvhdrForm" property='<%="ervqty[" + index + "]"%>'/> </td>    
									<td class="printtextright" nowrap><bean:write name="homeworldPrintErvhdrForm" property='<%="ervrqy[" + index + "]"%>'/> </td>    
									<td class="printtextright" nowrap><bean:write name="homeworldPrintErvhdrForm" property='<%="ervnqy[" + index + "]"%>'/> </td>    
									<td class="printtextright" nowrap></td>    
								</tr>		
							</logic:equal>
							<logic:notEqual name="homeworldPrintErvhdrForm" property='<%="ervsku[" + index + "]"%>' value='minsum'>
								<%if(mark==1){%>
									</tbody>
								 </table>	
							   </table>
						        <br clear=all style='page-break-before:always'>
								<table width="100%">
								   <td width="400">
								   	<font size="2">
								       <b>&nbsp收货单编号:</b><bean:write name="homeworldPrintErvhdrForm" property="ervnum"  />
								    </font>
								   </td>
								   <td width="1500"><H2 align="center">家世界连锁商业集团有限公司<BR>收货单 </H2></td>
						         <td width="400"><font size="2">
							      <b>日期:</b><bean:write name="homeworldPrintErvhdrForm" property="ervcdt" />
							      <br><b>时间:</b><bean:write name="homeworldPrintErvhdrForm" property="ervctm"  />
							      </font></td>
							    </table>   
						    <table  style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=972 border=1>
								    <thead>
									    <th class="printtitle2centerw"  nowrap>序号</th>
										<th class="printtitle2centerw"  nowrap>sku编码</th>
										<th class="printtitle2centerw"  nowrap>描述说明</th>
										<th class="printtitle2centerw"  nowrap>供货商型号</th>
										<th class="printtitle2centerw"  nowrap>销售<br>单位</th>
										<th class="printtitle2centerw"  nowrap>采购<br>单位</th>
										<th class="printtitle2centerw"  nowrap>upc号</th>
										<th class="printtitle2centerw"  nowrap>规格</th>
										<th class="printtitle2centerw"  nowrap>订货数<br>（包）</th>
										<th class="printtitle2centerw"  nowrap>订货数<br>（件）</th>
										<th class="printtitle2centerw"  nowrap>收货数<br>（件）</th>
										<th class="printtitle2centerw"  nowrap>欠收数<br>（件）</th>
										<th class="printtitle2centerw"  nowrap>折扣标志</th>
									</thead>
									<tbody id="DynData">		
								<%}%>
								<%mark=0;%>
							    <tr align="left">				
									<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErvhdrForm" property='<%="ervssq[" + index + "]"%>'/> </td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErvhdrForm" property='<%="ervsku[" + index + "]"%>'/> </td>   
									  
									<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErvhdrForm" property='<%="ervskd[" + index + "]"%>'/> </td>   
									<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErvhdrForm" property='<%="ervvds[" + index + "]"%>'/> </td>   
									   
									<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErvhdrForm" property='<%="ervsum[" + index + "]"%>'/> </td>    
									<td class="printtextright"  nowrap><bean:write name="homeworldPrintErvhdrForm" property='<%="ervbum[" + index + "]"%>'/> </td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErvhdrForm" property='<%="ervupc[" + index + "]"%>'/> </td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErvhdrForm" property='<%="ervmgn[" + index + "]"%>'/> </td>  
									
									
									<td class="printtextright"  nowrap><bean:write name="homeworldPrintErvhdrForm" property='<%="ervcas[" + index + "]"%>'/> </td>    
									<td class="printtextright"  nowrap><bean:write name="homeworldPrintErvhdrForm" property='<%="ervqty[" + index + "]"%>'/> </td>    
									<td class="printtextright"  nowrap><bean:write name="homeworldPrintErvhdrForm" property='<%="ervrqy[" + index + "]"%>'/> </td>    
									<td class="printtextright"  nowrap><bean:write name="homeworldPrintErvhdrForm" property='<%="ervnqy[" + index + "]"%>'/> </td>    
									<td class="printtextcenter" nowrap><bean:write name="homeworldPrintErvhdrForm" property='<%="ervsks[" + index + "]"%>'/> </td>    
								</tr>		
							</logic:notEqual>
					  </logic:iterate>
				</logic:present>
	

			<logic:equal name="homeworldPrintErvhdrForm" property='endnew' value='true'>
									</tbody>
								 </table>	
							   </table>
		    <br clear=all style='page-break-before:always'>
				
			<table width="100%">
			   <td width="400">
			   	<font size="2">
			       <b>&nbsp收货单编号:</b><bean:write name="homeworldPrintErvhdrForm" property="ervnum"  />
			    </font>
			   </td>
			   <td width="1500"><H2 align="center">家世界连锁商业集团有限公司<BR>收货单 </H2></td>
	         <td width="400"><font size="2">
		      <b>日期:</b><bean:write name="homeworldPrintErvhdrForm" property="ervcdt" />
		      <br><b>时间:</b><bean:write name="homeworldPrintErvhdrForm" property="ervctm"  />
		      </font></td>
		    </table>  
		       
		    <table  style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=972 border=1>
				    <thead>
					    <th class="printtitle2centerw"  nowrap>序号</th>
						<th class="printtitle2centerw"  nowrap>sku编码</th>
						<th class="printtitle2centerw"  nowrap>描述说明</th>
						<th class="printtitle2centerw"  nowrap>供货商型号</th>
						<th class="printtitle2centerw"  nowrap>销售<br>单位</th>
						<th class="printtitle2centerw"  nowrap>采购<br>单位</th>
						<th class="printtitle2centerw"  nowrap>upc号</th>
						<th class="printtitle2centerw"  nowrap>规格</th>
						<th class="printtitle2centerw"  nowrap>订货数<br>（包）</th>
						<th class="printtitle2centerw"  nowrap>订货数<br>（件）</th>
						<th class="printtitle2centerw"  nowrap>收货数<br>（件）</th>
						<th class="printtitle2centerw"  nowrap>欠收数<br>（件）</th>
						<th class="printtitle2centerw"  nowrap>折扣标志</th>
					</thead>
					<tbody id="DynData">	
			</logic:equal>	  		
 		
				<tr> 
					<td class="printtitle3centerw" nowrap  colspan=8 >合计————</td>    
					<td class="printtextright" nowrap><bean:write name="homeworldPrintErvhdrForm" property="countcas"/> </td>    
					<td class="printtextright" nowrap><bean:write name="homeworldPrintErvhdrForm" property="countqty"/> </td>    
					<td class="printtextright" nowrap><bean:write name="homeworldPrintErvhdrForm" property="countrqy"/> </td>    
					<td class="printtextright" nowrap><bean:write name="homeworldPrintErvhdrForm" property="countnqy"/> </td>    
					<td class="printtextright" nowrap></td>  
				</tr>	 
				</tbody>
			 </table>	
 
			<br>
			
		    <table align="center" style="BORDER-RIGHT: 1px solid; BORDER-TOP: 1px solid; BORDER-LEFT: 1px solid; BORDER-BOTTOM: 1px solid; BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 border=1 width=500>
		      <tr> 
				 <td class="printtitle3centerw" nowrap>注释1</td><td class="printtextcenter" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervnot1"  /></td>
			  </tr><tr>				 
				 <td class="printtitle3centerw" nowrap>注释2</td><td class="printtextcenter" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervnot2"  /></td>
			  </tr><tr>				 
				 <td class="printtitle3centerw" nowrap>注释3</td><td class="printtextcenter" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervnot3"  /></td>
			  </tr><tr>				 
				 <td class="printtitle3centerw" nowrap>FOB-DESTINATION</td><td class="printtextcenter" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervfob"  /></td>
			  </tr><tr>
				 <td class="printtitle3centerw" nowrap>发货经由1</td><td class="printtextcenter" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervsp1"  /></td>
			  </tr><tr>
				 <td class="printtitle3centerw" nowrap>发货经由2</td><td class="printtextcenter" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervsp2"  /></td>
			  </tr><tr>
				 <td class="printtitle3centerw" nowrap>发货点--</td><td class="printtextcenter" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervspp"  /></td>
 			  </tr><tr>
				 <td class="printtitle3centerw" nowrap>发货备注--</td><td class="printtextcenter" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervspc"  /></td>
 			  </tr>
			</table>	
			
			<br>
			
		    <table align="center" style="BORDER-RIGHT: 1px solid; BORDER-TOP: 1px solid; BORDER-LEFT: 1px solid; BORDER-BOTTOM: 1px solid; BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 border=1 width=500>
		      <tr> 
   	  	         <td class="printtitle1w" nowrap width=17  rowSpan=2>总计</td>
  		         <td class="printtitle3centerw" nowrap>售价--</td><td class="printtextright" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervtrt"/></td>
				 <td class="printtitle3centerw" nowrap>SKU折扣--</td><td class="printtextright" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervsdt"/></td>
			  </tr><tr>				 
			     <td class="printtitle3centerw" nowrap>成本--</td><td class="printtextright" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervtct"/></td>
				 <td class="printtitle3centerw" nowrap>供货商折扣--</td><td class="printtextright" nowrap><bean:write name="homeworldPrintErvhdrForm" property="ervvdt"/></td>
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
	  thisform.selectwhere.value='<bean:write name="homeworldPrintErvhdrForm" property="selectwhere"/>'; 
	  thisform.submit();
	  return;
	}
	
	  function Jumping1(thisform){
	  thisform.selected.value=null;
	  thisform.selectwhere.value='<bean:write name="homeworldPrintErvhdrForm" property="selectwhere"/>'; 
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
