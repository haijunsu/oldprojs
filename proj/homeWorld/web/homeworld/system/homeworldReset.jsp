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
				<bean:write name="homeworldResetForm" property='title'/>
		    	<logic:equal name="homeworldResetForm" property="se_up" value="1">		 	    
					管理		    
				</logic:equal>
			    <logic:equal name="homeworldResetForm" property="se_up" value="2">		 	    
					查询
				</logic:equal>
		</TITLE>
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
			<html:form action="/homeworldReset" >
			
			<html:hidden property="se_up" />
			<html:hidden property="flag" />
 		    <html:hidden property="page" />
 		    <html:hidden property="where" />
 		    
			<html:hidden property="currrow"/>
			<html:hidden property="count"/>
			<html:hidden property="title"/>
			
			<html:hidden property="id"/>
			<html:hidden property="show"/>
			
			<html:hidden property="key"/>
			<html:hidden property="message"/>
			<html:hidden property="currcolumn"/>
			
			<center><H2>			
				<html:errors property="testActionErrors"/>
				
				<bean:write name="homeworldResetForm" property='title'/>
		    	<logic:equal name="homeworldResetForm" property="se_up" value="1">		 	    
					管理		    
				</logic:equal>
			    <logic:equal name="homeworldResetForm" property="se_up" value="2">		 	    
					查询
				</logic:equal>
		    </H2></center>
		    
		    
		    
		    <br>
			<center>                                              
			<bean:write name="homeworldResetForm" property="show"/>
			：&nbsp;&nbsp;<html:text property="liketext" size="12"/>&nbsp;&nbsp;
			<html:button property="butselect" onclick ="selectform(this.form)">查询</html:button> 
			<br>			
			</center>
		<br>	<br>	
		
			<logic:equal name="homeworldResetForm" property="page" value="2">  
				<table  align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
					<logic:present name="homeworldResetForm" property="seq">
						<thead>
							<tr align="center">		
							<th  class="table2titletd" nowrap>用户id</th>
							<th  class="table2titletd" nowrap>用户姓名</th>
							<th  class="table2titletd" nowrap>生命周期起</th>
							<th  class="table2titletd" nowrap>生命周期止</th>
							<th  class="table2titletd" nowrap>共用名id</th>
							<th  class="table2titletd" nowrap>共用名中文</th>
							</tr>
						</thead>
						<tbody id="DynData">	
						<logic:iterate id="seq" name="homeworldResetForm" property="seq" indexId="index">					
					     <tr align="center">
					     
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="userid"  size="10" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldResetForm' property='<%="userid[" + index + "]"%>'/>" > 
						            <input type="hidden" name="rowstate" value='0'>
									<input type="hidden" name="seq" value='<%=seq%>'>
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="usershow"  size="15" class="n2centernone" readonly="true"
						            value="<bean:write name="homeworldResetForm" property='<%="usershow[" + index + "]"%>'/>" > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="lifebeg"  size="10" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldResetForm' property='<%="lifebeg[" + index + "]"%>'/>" > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="lifeend"  size="10" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldResetForm' property='<%="lifeend[" + index + "]"%>'/>" > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="useridgid"  size="10" class="n2centernone" onfocus="tdfocus(this);" readonly="true" 
						            value="<bean:write name='homeworldResetForm' property='<%="useridgid[" + index + "]"%>'/>" > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="useridgshow"  size="15" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldResetForm' property='<%="useridgshow[" + index + "]"%>'/>" > 
						        </td>
						        
					    		 <logic:notEqual name="homeworldResetForm" property='<%="useridgid[" + index + "]"%>' value="">  
						    		 <td class="table2textrighttd" nowrap><html:button property="butcommit" onclick ="subform(this)">重置</html:button></td>
					    		 </logic:notEqual>  
								
					   	 </tr>
						 </logic:iterate>

				</tbody>				
									</logic:present>
			</table>
			<br>
			<br>

		</logic:equal>	
		
		<br>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			
		<script language="JavaScript">	
		
	if(document.forms[0].message.value=="1"){
		alert("重置成功！！");
	}
	
			function subform(thiscell){	
			
				if (confirm("确定重置 "+thiscell.form.userid[thiscell.parentNode.parentNode.rowIndex - 1].value+" 么？")){
					thiscell.form.flag.value="cubmit";
					thiscell.form.key.value= thiscell.form.userid[thiscell.parentNode.parentNode.rowIndex - 1].value;
					thiscell.form.submit();
				}
				
			}
			
			//查询调用
			function selectform(thisform){	
			thisform.where.value = "(" + thisform.id.value+" = '" + thisform.liketext.value.toLowerCase() +"')"
					//thisform.where.value = "(" + thisform.id.value+" like '%" + thisform.liketext.value.toUpperCase()+"%' OR "+ thisform.id.value+" like '%" + thisform.liketext.value.toLowerCase() +"')"
					
				//if(saveChange(thisform,"select") != 1){
					thisform.flag.value="selectexic";
					thisform.submit();
				//	validateSubmit(thisform);
				//}
			}
			//校验提交函数
			function validateSubmit(form) {
				form.submit();			 	
				return;
			}
			
			
		</script>
		</html:form>
	</BODY>
</html:html>
