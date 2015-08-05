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
			<html:messages message="true" id="title" property="title">
				<bean:write name="title"/>
			</html:messages>
		</TITLE>
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
			<html:form action="/DeptView">
			 <html:hidden property="key" />
		    <html:hidden property="program" />
		    <html:hidden property="focusnow" />
			<html:hidden property="visib"/>
		    
			<center><H2>
				<html:messages message="true" id="title" property="title">
					<bean:write name="title"/>
				</html:messages>
			</H2></center>
					    
		    <table align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
				<thead>
					<tr>
						<th class="table2titletd">部门代码</th>
						<th class="table2titletd">部门</th>
						<th class="table2titletd">人员</th>
					</tr>
				</thead>
				
				<tbody id="DynData">
					<logic:present name="DeptViewForm" property="rowno" >
					  <logic:iterate id="rowno" name="DeptViewForm" property="rowno" indexId="index">
					    <tr align="center">
					    
					        <td class="table2textrighttd"> <input type="text" name="rowno" maxlength="20" size="20" class="nright"
					            value='<%= rowno%>'
					            readonly='true' style="border:none;text-align: center;background-color: #ffffe5;"> 
					        </td>
					        
					        <td class="table2textrighttd"> <input type="text" name="columnname" maxlength="20" size="20" class="nright"
					            value='<bean:write name="DeptViewForm" property='<%="columnname[" + index + "]"%>'/>'
					            readonly='true' style="border:none;text-align: center;background-color: #ffffe5;"> 
					            
					        </td>
					        <td class="table2textrighttd"> <input type="text" name="columncount" maxlength="20" size="20" class="nright"
					            value='<bean:write name="DeptViewForm" property='<%="columncount[" + index + "]"%>'/>'
					            readonly='true' style="border:none;text-align: center;background-color: #ffffe5;"> 
					        </td>
					        
					        
							<logic:equal name="DeptViewForm" property="visib" value="0">
								<logic:notEqual name="DeptViewForm" property='<%="columncount[" + index + "]"%>' value="0">
									    <td> <input type="button" name="butnext" value="编辑" onclick ="window.open('<bean:write name='DeptViewForm' property='program'/>&arg1=<%=rowno%>','_self','')"> </td>					   
					    		</logic:notEqual>
							</logic:equal>  
							
					        <logic:equal name="DeptViewForm" property="visib" value="1">
							    <td> <input type="button" name="butnext" value="编辑" onclick ="window.open('<bean:write name='DeptViewForm' property='program'/>&arg1=<%=rowno%>','_self','')"> </td>					   
					        </logic:equal>  
					        		   		
							
					   	 </tr>					        	
					  </logic:iterate>
					</logic:present>
		 		</tbody>
			</table>
			<br>
			<br>
			<center><html:button property="butlast" onclick ="lastform(this.form)">返回主页</html:button> </center>
		      
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			
		<script language="JavaScript">
			//置焦点
			document.getElementById("DynData").childNodes(document.getElementById("focusnow").value).all("columnname").focus();
			document.getElementById("DynData").childNodes(document.getElementById("focusnow").value).all("columnname").select();
			
			function lastform(thisform){
				window.open("<bean:message key='Home.page'/>","_self","")
			}
		</script>
		</html:form>
		
	</BODY>
</html:html>
