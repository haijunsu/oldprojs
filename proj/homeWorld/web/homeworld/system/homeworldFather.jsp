<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312" %>
<html:html locale="true">
	
	<HEAD>
		<META HTTP-EQUIV="Cache-Control" CONTENT="No-cache">
		<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
		<META HTTP-EQUIV="Content-Type" content="text/html; charset=GB2312">
		<META HTTP-EQUIV="Content-Style-Type" content="text/css">
		<META name="GENERATOR" content="IBM WebSphere Studio">
		<LINK href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css">
		<TITLE>
				<bean:write name="SalaryClothingForm" property='title'/>
		    	<logic:equal name="SalaryClothingForm" property="se_up" value="1">		 	    
					管理		    
				</logic:equal>
			    <logic:equal name="SalaryClothingForm" property="se_up" value="2">		 	    
					查询
				</logic:equal>
		</TITLE>
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
			<html:form action="/SalaryClothing" >
			
			<html:hidden property="se_up" />
			<html:hidden property="flag" />
 		    <html:hidden property="page" />
 		    <html:hidden property="where" />
 		    
			<html:hidden property="currrow"/>
			<html:hidden property="count"/>
			<html:hidden property="title"/>
			
			<html:hidden property="id"/>
			<html:hidden property="show"/>
			
			<center><H2>			
				<html:errors property="testActionErrors"/>
				
				<bean:write name="SalaryClothingForm" property='title'/>
		    	<logic:equal name="SalaryClothingForm" property="se_up" value="1">		 	    
					管理		    
				</logic:equal>
			    <logic:equal name="SalaryClothingForm" property="se_up" value="2">		 	    
					查询
				</logic:equal>
		    </H2></center>
		    
		    
		    
		    <br>
			<center>                                              
			<bean:write name="SalaryClothingForm" property="show"/>
			：&nbsp;&nbsp;<html:text property="liketext" size="12"/>&nbsp;&nbsp;
			<html:button property="butselect" onclick ="selectform(this.form)">查询</html:button> 
			<br>			
			</center>
		
		
			<logic:equal name="SalaryClothingForm" property="page" value="2">  
				<table  align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
					<logic:present name="SalaryClothingForm" property="seq">
						<thead>
							<tr align="center">		
	
	
	
							</tr>
						</thead>
						<tbody id="DynData">	
						<logic:iterate id="seq" name="SalaryClothingForm" property="seq" indexId="index">					
					     <tr align="center">
					     



							<td><input type="button" name="butthis" value="选择" onclick="changeform(this)"></td> 
					   	 </tr>
						 </logic:iterate>
					</logic:present>
				</tbody>				
			</table>
		</logic:equal>	
		
		<br>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			
		<script language="JavaScript">	
			//查询调用
			function selectform(thisform){	
				if(saveChange(thisform,"select") != 1){
					thisform.flag.value="selectexic";
					validateSubmit(thisform);
				}
			}

		</script>
		</html:form>
	</BODY>
</html:html>
