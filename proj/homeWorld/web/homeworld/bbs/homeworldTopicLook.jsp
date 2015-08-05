
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
				通告
		</TITLE>
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
			<html:form action="/homeworldTopicLook">
			<html:hidden property="nowpage"/>
			<html:hidden property="nowoncepage"/>
			<html:hidden property="first"/>
			<center><H2>
					通告
			</H2></center>

		    <table align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
				<tbody >
					<tr align="center">
						<td class="table1title" nowrap>标题</td>
						<td class="table1text" nowrap>
							 <bean:write name='homeworldTopicLookForm' property='topicc'/>
						</td>
					</tr><tr>  
						<td class="table1title" nowrap>通告类型</td>
						<td>
						<img src="<%=request.getContextPath()%>/img/BBS/mood<bean:write name='homeworldTopicLookForm' property='bbskind'/>.gif " WIDTH=19 HEIGHT=19 >
	
						</td>
					</tr><tr>  
						<td class="table1title" nowrap>内容</td>
						<td class="table1text" nowrap >
						
						<textarea  name="contentc" COLS="80" ROWS="20" readonly="true" ><bean:write name='homeworldTopicLookForm' property='contentc'/></textarea>
						
						</td>
					</tr><tr>  
						<td class="table1title" nowrap>操作人</td>
						<td class="table1text" nowrap >
							<bean:write name='homeworldTopicLookForm' property='operatorshow'/>
						</td>	
					</tr><tr>  
						<td class="table1title" nowrap>操作日期</td>
						<td class="table1text" nowrap >
							<bean:write name='homeworldTopicLookForm' property='operdate'/> 				            				            
						</td>	
					</tr>
		 		</tbody>
			</table>
			<br>
			
			<center>
			<html:button property="butcommit" onclick ="returnform(this.form)">返回</html:button> 
			</center>
			

		</html:form>
				      
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			
		<script language="JavaScript">

			//修改
			function returnform(thisform){
				if (thisform.first.value=="0"){
					window.location="searchbbs.do?queryid=DQ_TOPIC&actiondo=homeworldTopicLook.do&nowoncepage="+thisform.nowoncepage.value+"&nowpage="+thisform.nowpage.value;
				} else {
					window.location="searchbbsFirst.do?queryid=DQ_TOPIC&actiondo=homeworldTopicLook.do&nowoncepage="+thisform.nowoncepage.value+"&nowpage="+thisform.nowpage.value;
				}
			}
			
		</script>

		
	</BODY>
</html:html>
