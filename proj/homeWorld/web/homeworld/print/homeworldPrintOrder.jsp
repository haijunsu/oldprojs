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
        <html:form action="/homeworldPrintOrder" >
        <DIV id=divPrint style="Z-INDEX: 5; RIGHT: 0px; VISIBILITY: visible; POSITION: absolute; TOP: 0px">
          <TABLE cellSpacing=0 cellPadding=0 bgColor=#3366ff border=1>
           <TR>
            <TD>
                <html:select  property="selected" onchange="Jumping(this.form)" >
                <html:options name="homeworldOrderForm" property="values" labelProperty="labels"/>  
                </html:select> 
                <input onclick="Jumping1(this.form)" type=button value=打印所有页>
                <INPUT onclick="javaPrint(OLECMDID_PAGESETUP, OLECMDEXECOPT_DONTPROMPTUSER)" type=button value=页面设置> 
                <INPUT onclick="javaPrint(OLECMDID_PRINTPREVIEW, OLECMDEXECOPT_PROMPTUSER)" type=button value=打印预览> 
                <INPUT onclick="javaPrint(OLECMDID_PRINT, OLECMDEXECOPT_DONTPROMPTUSER)" type=button value=打印> 
            </TD>
          </TR></TABLE></DIV>
        <%int i=0;%>    
	    <logic:iterate id="pageseq" name="homeworldOrderForm" property="length" indexId="index0" >
	    <logic:notEqual name="homeworldOrderForm" property="sumpage" value='<%=""+(i+1)+""%>'>
	    <div style="page-break-after:always">       
	    </logic:notEqual>
            <html:errors property="errormessage"/>            
			<table width="100%">
			   <td width="400"><font size="2">
			       <b>&nbsp定单编号:</b><bean:write name="homeworldOrderForm" property="eponum"  />
			       <br><b>&nbsp释放用户:</b><bean:write name="homeworldOrderForm" property="epousr"  /></font>
			   </td><td width="1500"><H2 align="center">家世界连锁商业集团有限公司<BR>采购订单 </H2></td>
	         <td width="400"><font size="2">
		      <b>生成日期:</b><bean:write name="homeworldOrderForm" property="epocdt" />
		     <br><b>生成时间:</b><bean:write name="homeworldOrderForm" property="epoctm" />
		       </font></td>
		    </table>     
		<TABLE class=table1border style="BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; BORDER-BOTTOM-WIDTH: 1px; BORDER-COLLAPSE: collapse; BORDER-RIGHT-WIDTH: 1px" borderColor=#111111 height=336 cellSpacing=0 borderColorDark=#000000 cellPadding=0 width=950 bgColor=#ffffff>
		 <TR>
          <TD class=tablextitle1 noWrap borderColor=#111111 width=17 height=147 rowSpan=7><B><FONT color=#000000>签到</FONT></B></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=72 height=15><FONT color=#000000 size=2><B>商店</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epostrz" /></FONT></TD> 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=17 height=253 rowSpan=15><B><FONT color=#000000>供货商信息</FONT></B></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=80 height=15><FONT color=#000000 size=2><B>供货厂商</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epovndz" /></FONT></TD> 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=17 height=253 rowSpan=15><B><FONT color=#000000>订单信息</FONT></B></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=85 height=15><FONT color=#000000 size=2><B>输入日期</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=100 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epoedt" /></FONT></TD> 		
			</tr><tr>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=72 height=15><FONT color=#000000 size=2><B>商店名</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epostn" /></FONT></TD> 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=80 height=15><FONT color=#000000 size=2><B>供货商名</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epovdn" /></FONT></TD> 				 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=85 height=15><FONT color=#000000 size=2><B>期待收货日期</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=100 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="eposdt" /></FONT></TD> 			 				
		    </tr><tr>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=72 height=15><FONT color=#000000 size=2><B>商店地址1</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="eposa1" /></FONT></TD> 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=80 height=15><FONT color=#000000 size=2><B>供货商地址1</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epova1" /></FONT></TD> 				 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=85 height=15><FONT color=#000000 size=2><B>取消日期</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=100 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epoqdt" /></FONT></TD> 
            </tr><tr>
          <TD class=tablextitle1 noWrap borderColor=#111111 width=72 height=15><FONT color=#000000 size=2><B>商店地址2</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="eposa2" /></FONT></TD> 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=80 height=15><FONT color=#000000 size=2><B>供货商地址2</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epova2" /></FONT></TD> 				 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=85 height=15><FONT color=#000000 size=2><B>收货日期</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=100 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epoedt" /></FONT></TD> 
            </tr><tr>
          <TD class=tablextitle1 noWrap borderColor=#111111 width=72 height=15><FONT color=#000000 size=2><B>商店地址3</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="eposa3" /></FONT></TD> 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=80 height=15><FONT color=#000000 size=2><B>供货商地址3</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epova3" /></FONT></TD> 				 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=85 height=15><FONT color=#000000 size=2><B>收货单号</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=100 height=15><FONT color=#000000 size=2></FONT></TD> 
            </tr><tr>
          <TD class=tablextitle1 noWrap borderColor=#111111 width=72 height=15><FONT color=#000000 size=2><B>城市,邮编</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="eposcy" />，<bean:write name="homeworldOrderForm" property="eposzp"/></FONT></TD> 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=80 height=15><FONT color=#000000 size=2><B>供货商城市</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epovcy" /></FONT></TD> 				 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=85 height=15><FONT color=#000000 size=2><B>定单状态</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=100 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="eposts" /></FONT></TD> 
            </tr><tr>
          <TD class=tablextitle1 noWrap borderColor=#111111 width=72 height=15><FONT color=#000000 size=2><B>商店国家</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="eposcn" /></FONT></TD> 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=80 height=15><FONT color=#000000 size=2><B>供货商邮编</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epovzp" /></FONT></TD> 				 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=85 height=15><FONT color=#000000 size=2><B>状态描述</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=100 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epostd" /></FONT></TD> 
            </tr><tr>
          <TD class=tablextitle1 noWrap borderColor=#111111 width=17 height=154 rowSpan=8><B><FONT color=#000000>发往</FONT></B></TD>          		    
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=72 height=15><FONT color=#000000 size=2><B>商店</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2>数据库没有</FONT></TD> 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=80 height=15><FONT color=#000000 size=2><B>供货商国家</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epovcn" /></FONT></TD> 				 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=85 height=15><FONT color=#000000 size=2><B>采购</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=100 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epobyr" /></FONT></TD> 			 				
		    </tr><tr>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=72 height=15><FONT color=#000000 size=2><B>商店名</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epostn" /></FONT></TD> 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=80 height=15><FONT color=#000000 size=2><B>联络人</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epovct" /></FONT></TD> 				 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=85 height=15><FONT color=#000000 size=2><B>部门</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=100 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epodpt" /></FONT></TD> 			 				
		    </tr><tr>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=72 height=15><FONT color=#000000 size=2><B>商店地址1</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="eposa1" /></FONT></TD> 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=80 height=15><FONT color=#000000 size=2><B>票期代码</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epotrm" /></FONT></TD> 				 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=85 height=15><FONT color=#000000 size=2><B>采购名</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=100 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epobyn" /></FONT></TD> 			 				
		    </tr><tr>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=72 height=15><FONT color=#000000 size=2><B>商店地址2</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="eposa2" /></FONT></TD> 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=80 height=15><FONT color=#000000 size=2><B>票期名</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epotmn" /></FONT></TD> 				 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=85 height=15><FONT color=#000000 size=2><B>部门名</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=100 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epodpn" /></FONT></TD> 			 				
		    </tr><tr>						 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=72 height=15><FONT color=#000000 size=2><B>商店地址3</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="eposa3" /></FONT></TD> 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=80 height=15><FONT color=#000000 size=2><B>厂商电话</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epovpn" /></FONT></TD> 				 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=85 height=15><FONT color=#000000 size=2><B>子部</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=100 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="eposdpt" /></FONT></TD> 			 				
		    </tr><tr>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=72 height=15><FONT color=#000000 size=2><B>商店电话</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epospn" /></FONT></TD> 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=80 height=15><FONT color=#000000 size=2><B>厂商传真</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epovfx" /></FONT></TD> 				 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=85 height=15><FONT color=#000000 size=2><B>子部名</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=100 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="eposdptn" /></FONT></TD> 			 				
		    </tr><tr>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=72 height=15><FONT color=#000000 size=2><B>商店传真</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="eposfx" /></FONT></TD> 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=80 height=15><FONT color=#000000 size=2><B></B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2></FONT></TD> 				 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=85 height=15><FONT color=#000000 size=2><B>货币</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=100 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epocur" /></FONT></TD> 			 				
		    </tr><tr>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=72 height=15><FONT color=#000000 size=2><B>城市,邮编</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2>天津,300190</FONT></TD> 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=80 height=15><FONT color=#000000 size=2><B></B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=230 height=15><FONT color=#000000 size=2></FONT></TD> 				 
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=85 height=15><FONT color=#000000 size=2><B>货币描述</B></FONT></TD>
		  <TD class=tablextitle1 noWrap borderColor=#111111 width=100 height=15><FONT color=#000000 size=2><bean:write name="homeworldOrderForm" property="epocrd" /></FONT></TD> 			 				
	        </table><br>	
		     <TABLE id=AutoNumber2 style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=972 border=1>
			    <thead>
			        <td width=40><font size="2"><center>商店</center></font></td>
			        <td width=60><font size="2"><center>供货商</center></font></td>
			        <td width=40><font size="2"><center>序号</center></font></td>
					<td width=122><font size="2"><center>sku编号</center></font></td>
					<td width=420><font size="2"><center>说明</center></font></td>
					<td width=120><font size="2"><center>供货商<br>型号</center></font></td>
					<td width=100><font size="2"><center>销售<br>单位</center></font></td>
					<td width=120><font size="2"><center>采购<br>单位</center></font></td>
					<td width=200><font size="2"><center>upc</center></font></td>
					<td width=120><font size="2"><center>订货数量<br>(包)</center></font></td>
					<td width=120><font size="2"><center>订货数量<br>(件)</center></font></td>
					<td width=140><font size="2"><center>规格</center></font></td>
					<td width=120><font size="2"><center>二包<br>数量</center></font></td>
					<td width=120><font size="2"><center>净单位<br>成本</center></font></td>
					<td width=60><font size="2"><center>折扣<br>标志</center></font></td>
				</thead>
				<tbody id="DynData">
					<logic:present name="homeworldOrderForm" property="epostr" > 
					  <logic:iterate id="seq" name="homeworldOrderForm" property="epostr" indexId="index" offset='<%=i*3+""%>' length="3" >
					    <tr align="left">
                            <td width=40><font size="2">&nbsp<bean:write name="homeworldOrderForm" property='<%="epostr[" + index + "]"%>'/></font> </td> 
                            <td width=60><font size="2">&nbsp<bean:write name="homeworldOrderForm" property='<%="epovnd[" + index + "]"%>'/></font> </td> 
                            <td width=40><font size="2">&nbsp<bean:write name="homeworldOrderForm" property='<%="epossq[" + index + "]"%>'/></font> </td>                                      					    		
                            <td width=122><font size="2">&nbsp<bean:write name="homeworldOrderForm" property='<%="eposku[" + index + "]"%>'/></font> </td>    
							<td width=420><font size="2">&nbsp<bean:write name="homeworldOrderForm" property='<%="eposkd[" + index + "]"%>'/></font> </td>    
							<td width=120><font size="2">&nbsp&nbsp<bean:write name="homeworldOrderForm" property='<%="epovds[" + index + "]"%>'/></font> </td>    
							<td width=100><font size="2">&nbsp&nbsp<bean:write name="homeworldOrderForm" property='<%="eposum[" + index + "]"%>'/></font> </td>    
							<td width=120><font size="2">&nbsp&nbsp<bean:write name="homeworldOrderForm" property='<%="epobum[" + index + "]"%>'/></font> </td>    
							<td width=200><font size="2">&nbsp&nbsp<bean:write name="homeworldOrderForm" property='<%="epoupc[" + index + "]"%>'/></font> </td>    
							<td width=120><font size="2">&nbsp&nbsp<bean:write name="homeworldOrderForm" property='<%="epocas[" + index + "]"%>'/></font> </td>    
							<td width=120><font size="2">&nbsp&nbsp<bean:write name="homeworldOrderForm" property='<%="epoqty[" + index + "]"%>'/></font> </td>    
							<td width=140><font size="2">&nbsp&nbsp<bean:write name="homeworldOrderForm" property='<%="epomgn[" + index + "]"%>'/></font> </td>    
							<td width=120><font size="2">&nbsp&nbsp<bean:write name="homeworldOrderForm" property='<%="epobts[" + index + "]"%>'/></font> </td>    
							<td width=120><font size="2">&nbsp&nbsp<bean:write name="homeworldOrderForm" property='<%="eponct[" + index + "]"%>'/></font> </td>    
							<td width=60> <font size="2">&nbsp&nbsp<bean:write name="homeworldOrderForm" property='<%="eposks[" + index + "]"%>'/></font> </td>    
						</tr>		    	
					  </logic:iterate><tr>
					        <td width=1322 colspan=9 align="right"><b>小计--&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</b></td><td width=120 align="right"><font size="2"><bean:write name="homeworldOrderForm" property='<%="sumperpagecas["+i+"]"%>'/></font>&nbsp&nbsp</td><td width=120 align="right"><font size="2"><bean:write name="homeworldOrderForm" property='<%="sumperpageqty["+i+"]"%>'/></font>&nbsp&nbsp</td><td></td><td></td><td width=120 align="right"><font size="2"><bean:write name="homeworldOrderForm" property='<%="sumperpagenct["+i+"]"%>'/></font>&nbsp&nbsp</td><td></td></tr><tr> 
					        <td width=1322 colspan=9 align="right"><b>合计--&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</b></td><td width=120></td><td width=120></td><td width=140></td><td width=120></td><td width=120 align="right"><font size="2"><bean:write name="homeworldOrderForm" property="strsumall"/></font>&nbsp&nbsp</td><td width=60></td></tr><tr> 					         
					        <td width=1322 colspan=9 align="right"><b>SKU折扣--&nbsp&nbsp&nbsp&nbsp&nbsp</b></td><td width=120></td><td width=120></td><td width=140></td><td width=120></td><td width=120></td><td width=60></td></tr><tr> 
					        <td width=1322 colspan=9 align="right"><b>其他折扣--&nbsp&nbsp&nbsp&nbsp</b></td><td width=120></td><td width=120></td><td width=140></td><td width=120></td><td width=120></td><td width=60></td></tr>
					</logic:present>
				</tbody>
			 </table>	
		   </table>
	        <%i=i+1;%><br><center>
              <TABLE class=table1border style="BORDER-RIGHT: 1px solid; BORDER-TOP: 1px solid; BORDER-LEFT: 1px solid; BORDER-BOTTOM: 1px solid; BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 border=1 width=600 >
	          <tr>
		      <td width=250 align="center"><font size="2"><b>备注----手传</b></font></td><td><font size="2"><bean:write name="homeworldOrderForm" property="eponot1" /></font></td><tr>
			  <td width=250 align="center"><font size="2"><b>FOB----DESTINATION</b></font></td><td><font size="2"><bean:write name="homeworldOrderForm" property="epofob"  /></font></td></tr><tr>			 
		      <td width=250 align="center"><font size="2"><b>发货经由--电子订单</b></font></td><td><font size="2"><bean:write name="homeworldOrderForm" property="eposp1"  /></font></td></tr><tr>
			  <td width=250 align="center"><font size="2"><b>发货点</b></font></td><td><font size="2"><bean:write name="homeworldOrderForm" property="epospp"  /></font></td></tr><tr>
			  <td width=250 align="center"><font size="2"><b>发货备注</b></font></td><td><font size="2"><bean:write name="homeworldOrderForm" property="epospc"  /></font></td></tr> 			  
 		</table></center>
 	    <logic:notEqual name="homeworldOrderForm" property="sumpage" value='<%=""+(i+1)+""%>'>
 	    </div>
 	    </logic:notEqual>
        </logic:iterate>
         <html:hidden property="selectwhere" />
		</html:form>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>	
		<script language="JavaScript">	
		
		</script>
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
	  thisform.selectwhere.value="<bean:write name="homeworldOrderForm" property="selectwhere"/>"; 
	  thisform.submit();
	  return;
	}
	
	  function Jumping1(thisform){
	  thisform.selected.value=null;
	  thisform.selectwhere.value="<bean:write name="homeworldOrderForm" property="selectwhere"/>"; 
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
