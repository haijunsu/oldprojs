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
				 人员权限管理
		</TITLE>
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
			<html:form action="/homeworldPurview" >

			<html:hidden property="flag" />
			
   		    <html:hidden property="currrowshowold"/>
		    <html:hidden property="currrowshow"/>
			<html:hidden property="currrow"/>
			
			<center><H2>			
				<html:errors property="testActionErrors"/>
					 人员权限管理 
		    </H2></center>
		    <table  align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
		    
				<logic:present name="homeworldPurviewForm" property="userid" >
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
					  <logic:iterate id="userid" name="homeworldPurviewForm" property="userid" indexId="index">
					     <tr align="center">
					     
					    	<logic:notEqual name="homeworldPurviewForm" property="currrowshow" value='<%="" + index %>'>		
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="userid"  size="10" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldPurviewForm' property='<%="userid[" + index + "]"%>'/>" > 
						            <input type="hidden" name="rowstate" value='0'>
						            <input type="hidden" name="appsysid" value="<bean:write name='homeworldPurviewForm' property='<%="appsysid[" + index + "]"%>'/>">
						            <input type="hidden" name="appsysshow" value="<bean:write name='homeworldPurviewForm' property='<%="appsysshow[" + index + "]"%>'/>">
						            <input type="hidden" name="mpurview" value="<bean:write name='homeworldPurviewForm' property='<%="mpurview[" + index + "]"%>'/>">

						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="usershow"  size="15" class="n2centernone" readonly="true"
						            value="<bean:write name="homeworldPurviewForm" property='<%="usershow[" + index + "]"%>'/>" > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="lifebeg"  size="10" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldPurviewForm' property='<%="lifebeg[" + index + "]"%>'/>" > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="lifeend"  size="10" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldPurviewForm' property='<%="lifeend[" + index + "]"%>'/>" > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="useridgid"  size="10" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldPurviewForm' property='<%="useridgid[" + index + "]"%>'/>" > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="useridgshow"  size="15" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldPurviewForm' property='<%="useridgshow[" + index + "]"%>'/>" > 
						        </td>
						        <logic:notEqual name="userid" value="">
									<td><input type="button" name="butthis" value="编辑" onclick="changethisform(this)"></td> 
								</logic:notEqual>
					    	</logic:notEqual>	

							<logic:equal name="homeworldPurviewForm" property="currrowshow" value='<%="" + index %>'>	
						    	<td colspan="6" align="center">
								    <br>
						    		<table width="50%" cellspacing="0" cellpadding="1" class="table1border">
 									<tr>
					    			  	<td class="table1title" nowrap>用户姓名:</td>
									    <td class="table1text" nowrap>
							        		<input type="text" name="usershow"  size="14" class='n2centernone' readonly='true'
								               readonly="true" value="<bean:write name='homeworldPurviewForm' property='<%="usershow[" + index + "]"%>'/>"> 				            
								         </td>
								          	
					    			 	<td class="table1title" nowrap>用户id:</td>
									    <td class="table1text" nowrap>
									     <input type="text" name="userid" size="10" class="n2centernone" readonly='true'
									          readonly="true" value="<bean:write name='homeworldPurviewForm' property='<%="userid[" + index + "]"%>'/>">
							        		<input type="hidden" name="rowstate" value="<bean:write name='homeworldPurviewForm' property='<%="rowstate[" + index + "]"%>'/>">
							        		 <input type="hidden" name="mpurview" value="<bean:write name='homeworldPurviewForm' property='<%="mpurview[" + index + "]"%>'/>">
									     </td>
								        
					    			  	<td class="table1title" nowrap> 生命周期起:</td>
									    <td class="table1text" nowrap>
							        		<input type="text" name="lifebeg"   size="10" class='n2centernone'  readonly='true'
								               readonly="true" value="<bean:write name='homeworldPurviewForm' property='<%="lifebeg[" + index + "]"%>'/>"> 				            				            
								         </td>
								     </tr>
									 <tr>
									    <td class="table1title" nowrap>共用名中文:</td>
										    <td class="table1text" nowrap>
								        		<input type="text" name="useridgshow"  size="10" class='n2centernone' readonly='true'
									              readonly="true" value="<bean:write name='homeworldPurviewForm' property='<%="useridgshow[" + index + "]"%>'/>"> 				            				            
								         </td>	
								         
										<td class="table1title" nowrap> 共用名id:</td>
									    <td class="table1text" nowrap>
									        <input type="text" name="useridgid"  size="10" class='n2centernone'  readonly='true'
									          readonly="true"   value="<bean:write name='homeworldPurviewForm' property='<%="useridgid[" + index + "]"%>'/>"> 				            			            
									    </td>
									    
								         <td class="table1title" nowrap> 生命周期止:</td>
									     <td class="table1text" nowrap>
								       	     	<input type="text" name="lifeend" size="10" class='n2centernone' readonly='true'
								            	  value="<bean:write name='homeworldPurviewForm' property='<%="lifeend[" + index + "]"%>'/>"> 
								         </td>
									 </tr>
									 <tr>
								    	<td class="table1title" nowrap> 应用系统:</td>
									    <td class="table1text" nowrap colspan="3" >
									        <input type="text" name="appsysshow"  size="37"  class="n1right" onclick="selectfields(this.form,'<%=index%>','appsys','appsyscode','系统')";
										        readonly="true" value="<bean:write name='homeworldPurviewForm' property='<%="appsysshow[" + index + "]"%>'/>"> 				            
									        <input type="hidden" name="appsysid"  value="<bean:write name='homeworldPurviewForm' property='<%="appsysid[" + index + "]"%>'/>">
									     </td> 
									</tr>	
									
									 	<logic:present name="homeworldPurviewForm" property="mpurviewid" >
									        	<tr><td colspan="6" nowrap>&nbsp;
										        	<logic:iterate id="mpurviewshow" name="homeworldPurviewForm" property="mpurviewshow" indexId="indexrow">
										        		<logic:equal name="indexrow" value="6">										
										    				&nbsp;&nbsp;</td></tr>    								
									    					<tr><td  colspan="6" nowrap>&nbsp;
														</logic:equal>
												        &nbsp;&nbsp;<%=mpurviewshow%>
												        <html:multibox property="mpurviewid" onclick="tdchange(this.form)"> <%= "" + indexrow%> </html:multibox>						
													</logic:iterate>     
									        	</td></tr>
											</logic:present>
									
								</table>	
   						       <br>    
   						       <center>
							   <html:button property="butcommit" onclick ="submitform(this.form,'commit')">提交</html:button>
							   </center>
							   </td>
							   
							   <td><input type="button" name="buthidden" value="隐藏" onclick="hiddenthisform(this.form)"></td>
							</logic:equal>		    	
					   	
					   	
					   	
					   	
						</tr>
					  </logic:iterate>
					</logic:present>
		 		</tbody>	
			</table>

		</html:form>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			
		<script language="JavaScript">	
				
			//选择代码函数
			function selectfields(thisform,currrow,currcol,code,cshow){
				var select;
				if(selectone(thisform,currrow,currcol,code,cshow)=="1"){
					thisform.flag.value="circle";
					thisform.submit();			
				}
 			}
						
			//隐藏调用
			function hiddenthisform(thisform){
				
				thisform.currrowshowold.value=thisform.currrowshow.value;
				thisform.currrowshow.value="-1";
				
				if(saveChange(thisform,"hidden") != 1){
					thisform.flag.value="hiddenexic";
					thisform.submit();
				}
			}
			
			//编辑调用
			function changethisform(thiscell){
				var currrow=thiscell.parentNode.parentNode.rowIndex - 1;				
				thiscell.form.currrowshowold.value=thiscell.form.currrowshow.value;
				thiscell.form.currrowshow.value=currrow;
				thiscell.form.currrow.value=thiscell.form.currrowshow.value;

				if(saveChange(thiscell.form,"change") != 1){
					thiscell.form.flag.value="changeexic";
					thiscell.form.submit();
				}
			}
			
			//校验提交函数
			function validateSubmit(form) {
	    		form.submit();
				return;
			}
		
		</script>
	</BODY>
</html:html>

