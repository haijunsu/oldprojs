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
	<BODY>
		<html:errors property="errormessage"/>
			<html:form action="/homeworldErvhdr" >

			<html:hidden property="selectwhere"/>
			<html:hidden property="queryid"/>

			<table width="100%">
			   <td width="400">
			   	<font size="2">
			       <b>&nbsp收货单编号:</b><bean:write name="homeworldErvhdrForm" property="ervnum"  />
			    </font>
			   </td>
			   <td width="1500"><H2 align="center">家世界连锁商业集团有限公司<BR>收货单 </H2></td>
	         <td width="400"><font size="2">
		      <b>日期:</b><bean:write name="homeworldErvhdrForm" property="ervcdt" />
		      <br><b>时间:</b><bean:write name="homeworldErvhdrForm" property="ervctm"  />
		      </font></td>
		    </table>   
		    
			 	 		
			<TABLE style="BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; BORDER-BOTTOM-WIDTH: 1px; BORDER-COLLAPSE: collapse; BORDER-RIGHT-WIDTH: 1px" borderColor=#111111 height=336 cellSpacing=0 borderColorDark=#000000 cellPadding=0 width=950 bgColor=#ffffff>
			  <tr>			 
			     <td class="printtitle1" nowrap width=17 rowSpan=12>商<br>店</td>
				 <td class="printtitle2left" nowrap>商店       </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervstr"  /></td>	 
				 <td class="printtitle1" nowrap  width=17 rowSpan=12>供<br>货<br>商<br>信<br>息</td>	
				 <td class="printtitle2left" nowrap>供货厂商   </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervvnd"  /></td>		      			 	 
				 <td class="printtitle1" nowrap width=17 rowSpan=12>收<br>货<br>单<br>信<br>息</td>	
				 <td class="printtitle2left" nowrap>收货单号   </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervnum"  /></td>		      			 	 
	    	</tr><tr>
				 <td class="printtitle2left" nowrap>商店名称     </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervstn"  /></td>
				 <td class="printtitle2left" nowrap>供货商名称   </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervvdn"  /></td>				 				
				 <td class="printtitle2left" nowrap>输入日期     </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervedt"  /></td>		      			 	 
			</tr><tr>
				 <td class="printtitle2left" nowrap>采购员      </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervbyr"  /></td>
    			 <td class="printtitle2left" nowrap>供货商地址1 </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervva1"  /></td>			
    			 <td class="printtitle2left" nowrap>收货日期    </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervrdt"  /></td>		      			 	 

			</tr><tr>
				 <td class="printtitle2left" nowrap>采购名    </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervbyn"  /></td>         
				 <td class="printtitle2left" nowrap>供货商地址2    </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervva2"  /></td>				         
				 <td class="printtitle2left" nowrap>收货单状态   </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervsts"  /></td>		      			 	 

			</tr><tr>
				 <td class="printtitle2left" nowrap>部门    </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervdpt"  /></td>         
				 <td class="printtitle2left" nowrap>供货商地址3    </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervva3"  /></td>	
		         <td class="printtitle2left" nowrap>生成日期  </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervcdt"  /></td>			
			
			</tr><tr>
				 <td class="printtitle2left" nowrap>部门名    </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervdpn"  /></td>			
				 <td class="printtitle2left" nowrap>供货商城市,邮编</td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervvcy"  />，<bean:write name="homeworldErvhdrForm" property="ervvzp"  /></td>			
				 <td class="printtitle2left" nowrap>生成时间	</td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervctm"  /></td>				
				 
   				
			</tr><tr>			
				 <td class="printtitle2left" nowrap>子部门 	 </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervsdpt"  /></td>
				 <td class="printtitle2left" nowrap>供货商国家 </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervvcn"  /></td>
				 <td class="printtitle2left" nowrap>订单编号</td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervpno"  /></td>
				 

			</tr><tr>
				 <td class="printtitle2left" nowrap>子部门名  </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervsdptn"  /></td>
				 <td class="printtitle2left" nowrap>联络人       </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervvct"  /></td>				 
				 <td class="printtitle2left" nowrap></td><td class="printtextleft" nowrap></td>
			 
			</tr><tr>				 
				 <td class="printtitle2left" nowrap>运输     </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervfrc"  /></td>
				 <td class="printtitle2left" nowrap>票期代码 	 </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervtrm"  /></td>
				 <td class="printtitle2left" nowrap></td><td class="printtextleft" nowrap></td>

			</tr><tr>
				 <td class="printtitle2left" nowrap>运输描述 </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervfrn"  /></td>
				 <td class="printtitle2left" nowrap>票期名	 </td><td class="printtextleft" nowrap><bean:write name="homeworldErvhdrForm" property="ervtmn"  /></td>
				 <td class="printtitle2left" nowrap></td><td class="printtextleft" nowrap></td>
			</tr>
	        </table>	

	    	<br>
	    	
		    <table  style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=972 border=1>
				<logic:present name="homeworldErvhdrForm" property="ervssq" >
				    <thead>
					    <th class="printtitle3center"  nowrap>序号</th>
						<th class="printtitle3center"  nowrap>sku编码</th>
						<th class="printtitle3center"  nowrap>描述说明</th>
						<th class="printtitle3center"  nowrap>供货商型号</th>
						<th class="printtitle3center"  nowrap>销售<br>单位</th>
						<th class="printtitle3center"  nowrap>采购<br>单位</th>
						<th class="printtitle3center"  nowrap>upc号</th>
						<th class="printtitle3center"  nowrap>规格</th>
						<th class="printtitle3center"  nowrap>订货数<br>（包）</th>
						<th class="printtitle3center"  nowrap>订货数<br>（件）</th>
						<th class="printtitle3center"  nowrap>收货数<br>（件）</th>
						<th class="printtitle3center"  nowrap>欠收数<br>（件）</th>
						<th class="printtitle3center"  nowrap>折扣标志</th>
						
						
					</thead>
					<tbody id="DynData">	
					  <logic:iterate id="seq" name="homeworldErvhdrForm" property="ervssq" indexId="index">
						  <logic:equal name="homeworldErvhdrForm" property='<%="ervsku[" + index + "]"%>' value='sumcount'>
							    <tr align="left">
									<td class="printtitle3centerw" nowrap  colspan=8 >合计————</td>    
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

				 <td class="printtitle3center" nowrap>注释1</td><td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property="ervnot1"  /></td>
			  </tr><tr>				 
				 <td class="printtitle3center" nowrap>注释2</td><td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property="ervnot2"  /></td>
			  </tr><tr>				 
				 <td class="printtitle3center" nowrap>注释3</td><td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property="ervnot3"  /></td>
			  </tr><tr>				 
				 <td class="printtitle3center" nowrap>FOB-DESTINATION</td><td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property="ervfob"  /></td>
			  </tr><tr>
				 <td class="printtitle3center" nowrap>发货经由1</td><td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property="ervsp1"  /></td>
			  </tr><tr>
				 <td class="printtitle3center" nowrap>发货经由2</td><td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property="ervsp2"  /></td>
			  </tr><tr>
				 <td class="printtitle3center" nowrap>发货点--</td><td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property="ervspp"  /></td>
 			  </tr><tr>
				 <td class="printtitle3center" nowrap>发货备注--</td><td class="printtextcenter" nowrap><bean:write name="homeworldErvhdrForm" property="ervspc"  /></td>
 			  </tr>
			</table>	
			
			<br>
			
		    <table  style="BORDER-RIGHT: 1px solid; BORDER-TOP: 1px solid; BORDER-LEFT: 1px solid; BORDER-BOTTOM: 1px solid; BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 border=1 width=500>
		      <tr> 
   	  	         <td class="printtitle1" nowrap width=17  rowSpan=2>总计</td>
  		         <td class="printtitle3center" nowrap>售价--</td><td class="printtextright" nowrap><bean:write name="homeworldErvhdrForm" property="ervtrt"/></td>
				 <td class="printtitle3center" nowrap>SKU折扣--</td><td class="printtextright" nowrap><bean:write name="homeworldErvhdrForm" property="ervsdt"/></td>
			  </tr><tr>				 
   			     <td class="printtitle3center" nowrap>成本--</td><td class="printtextright" nowrap><bean:write name="homeworldErvhdrForm" property="ervtct"/></td>
				 <td class="printtitle3center" nowrap>供货商折扣--</td><td class="printtextright" nowrap><bean:write name="homeworldErvhdrForm" property="ervvdt"/></td>
 			  </tr>
			</table>	
						 
						 
<br>		<br>		
			<center>
			<logic:notEqual name="homeworldErvhdrForm" property='selectwhere' value=''>
				<html:button property="butcreatexml" onclick ="createxml(this.form)">下载xml文件</html:button> 
				<html:button property="butprint" onclick ="printform(this.form)">打印</html:button>
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
