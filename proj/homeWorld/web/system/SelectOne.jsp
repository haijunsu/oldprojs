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
				代码说明
		</TITLE>
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
		<html:form action="/SelectOne" >
		<center><H2>
		<br>
		<bean:write name="SelectOneForm" property="cshowc"/>

					         
		</H2></center>
		
		
		    <table align="center" width="50%" cellspacing="0" cellpadding="1" class="table2border">
				<tbody id="DynData">
				
					<logic:present name="SelectOneForm" property="rowshow" >
					  <logic:iterate id="rowshow" name="SelectOneForm" property="rowshow" indexId="indexrow">
					    <tr align="center">
					        <td class="table2textcentertd"> <input type="text" name="rowshow" maxlength="16" size="16" class="n2centernone"
					            value='<%=rowshow%>' 
					            readonly="true" style="border:none;text-align: center;background-color: #ffffe5;"> 
					        	<input type="hidden" name="rowid" value=<bean:write name="SelectOneForm" property='<%="rowid[" + indexrow + "]"%>'/>>
					        </td>
					        
					        <logic:present name="SelectOneForm" property="row" >
					        	  <logic:iterate id="col" name="SelectOneForm" property='<%="row[" + indexrow + "]"%>' indexId="indexcol">
								        <td class="table2textcentertd"> <input type="text" name="row" maxlength="16" size="16" class="n2centernone"
									            readonly="true" style="border:none;text-align: center;background-color: #ffffe5;"
									            value='<%=col%>'>
									        </td>
								  </logic:iterate>
							</logic:present>					        
					        
					         <td> <input type="button" name="butselect" value="选择" onclick ="exitform(this.form,<%=indexrow%>)"> </td>
					    </tr>
					  </logic:iterate>
					</logic:present>
					
		 		</tbody>
			</table>
			<br>
		    <center><html:button property="butesc" onclick ="window.close()">退出</html:button></center>
			<br>
			<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
			<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>	
					
			<script language="JavaScript">
			//表格的第一项置焦点
			var ControlField=document.getElementById("DynData").childNodes(0);	
			window.returnValue="";
			
		
			if (document.getElementById("DynData").all("rowid").length==null){
				window.returnValue=document.getElementById("rowshow").value + "&" +document.getElementById("rowid").value;
				window.close();
			}
				
						
			//选择函数
			function exitform(thisform,index){
				window.returnValue=thisform.rowshow[index].value + "&" +thisform.rowid[index].value;
				window.close();
				return;
			}				
			</script>			
		</html:form>
	</BODY>
</html:html>
