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
			<bean:write name="homeworldActionLogForm" property="title"  />
		</TITLE>
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
			<html:form action="/homeworldActionLog" >
			<center><H2>			
				<html:errors property="testActionErrors"/>
				<bean:write name="homeworldActionLogForm" property="title"  />
		    </H2></center>
		    <html:hidden property="qtable" />
		    <html:hidden property="title" />
		    <html:hidden property="flag" />
		    <html:hidden property="hiddenshow" />
		    <center>
				用户：&nbsp;&nbsp;<html:text property="liketext" size="12"/>&nbsp;&nbsp;
				<html:button property="butselect" onclick ="selectform(this.form)">查询</html:button> 
			</center>
			
			<logic:equal name="homeworldActionLogForm" property="hiddenshow" value='1'>	
			    <center>
				    年份：<input type="text" name="date" maxlength="4" size="6" class='n1left' value='<bean:write name="homeworldActionLogForm" property="date"/>'> 		
				    <A href=javascript:change('01')>1月</A>
				    <A href=javascript:change('02')>2月</A>
				    <A href=javascript:change('03')>3月</A>
				    <A href=javascript:change('04')>4月</A>
				    <A href=javascript:change('05')>5月</A>
				    <A href=javascript:change('06')>6月</A>
				    <A href=javascript:change('07')>7月</A>
				    <A href=javascript:change('08')>8月</A>
				    <A href=javascript:change('09')>9月</A>
				    <A href=javascript:change('10')>10月</A>
				    <A href=javascript:change('11')>11月</A>
				    <A href=javascript:change('12')>12月</A>
			    </center>            
		         <br>   
	         </logic:equal>	
	        
				
			<logic:notPresent name="homeworldActionLogForm" property="datetime" >
				<logic:notEqual name="homeworldActionLogForm" property="flag" value=''>	
					<br><center>查询无结果</center> 
				</logic:notEqual>
			</logic:notPresent>
				
			 
			  <logic:present name="homeworldActionLogForm" property="datetime" >
			    <table  align="center" cellspacing="0" cellpadding="1" class="table1border">
				    <thead>
						<th  class="table2titletd" nowrap>日期</th>
						<th  class="table2titletd" nowrap>操作人</th>
						<th  class="table2titletd" nowrap>操作</th>
					</thead>
					<tbody id="DynData">
					<logic:iterate id="datetime" name="homeworldActionLogForm" property="datetime" indexId="index">
					     <tr align="center">			
							<td class="table2textcentertd" nowrap><bean:write name="homeworldActionLogForm" property='<%="datetime[" + index + "]"%>'/> </td>    
							<td class="table2textcentertd" nowrap><bean:write name="homeworldActionLogForm" property='<%="user[" + index + "]"%>'/> </td>    
							<td class="table2textcentertd" nowrap><bean:write name="homeworldActionLogForm" property='<%="memo[" + index + "]"%>'/> </td>    
						</tr>		
					  </logic:iterate>
					</tbody>	
				</table>    	

		</logic:present>
		</html:form>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>	
		<script language="JavaScript">	
		
		
		function selectform(thisform){	
			thisform.flag.value="";
			thisform.flag.value="(act_user = '"+thisform.liketext.value.toUpperCase()+"' or ";
			thisform.flag.value=thisform.flag.value + "vndnam = '"+thisform.liketext.value+"')";
			thisform.submit();
				
		}
			
		function change(arg){
		
			document.forms[0].flag.value="(act_user = '"+document.forms[0].liketext.value.toUpperCase()+"' or ";
			document.forms[0].flag.value=document.forms[0].flag.value + "vndnam = '"+document.forms[0].liketext.value+"')";
				
			document.forms[0].flag.value=document.forms[0].flag.value + " and act_datetime like '" + document.forms[0].date.value+"-"+arg+"%'";
			document.forms[0].submit();
		}
		
		</script>
	</BODY>
</html:html>
