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
				<bean:write name="homeworldUsersForm" property='title'/>
		    	<logic:equal name="homeworldUsersForm" property="se_up" value="1">		 	    
					管理		    
				</logic:equal>
			    <logic:equal name="homeworldUsersForm" property="se_up" value="2">		 	    
					查询
				</logic:equal>
		</TITLE>
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
			<html:form action="/homeworldUsers" >
			
			<html:hidden property="se_up" />
			<html:hidden property="flag" />
 		    <html:hidden property="page" />
 		    <html:hidden property="where" />
 		    
			<html:hidden property="count"/>
			<html:hidden property="title"/>
			
			<html:hidden property="id"/>
			<html:hidden property="show"/>
			
			<html:hidden property="currrow"/>
			<html:hidden property="currcolumn"/>
			
   		   <html:hidden property="currrowshowold"/>
		    <html:hidden property="currrowshow"/>
		    
				
			<center><H2>			
				<html:errors property="testActionErrors"/>
				
				<bean:write name="homeworldUsersForm" property='title'/>
		    	<logic:equal name="homeworldUsersForm" property="se_up" value="1">		 	    
					管理		    
				</logic:equal>
			    <logic:equal name="homeworldUsersForm" property="se_up" value="2">		 	    
					查询
				</logic:equal>
		    </H2></center>
		    
		    
		    
			<center>                    
			<bean:write name="homeworldUsersForm" property="show"/>
			：&nbsp;&nbsp;<html:text property="liketext" size="12"/>&nbsp;&nbsp;
			<html:button property="butselect" onclick ="selectform(this.form)">查询</html:button> 
			<br>			
			</center>
		
		<br>	
		<br>	
			<logic:equal name="homeworldUsersForm" property="page" value="2">  
				<table  align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
					<logic:present name="homeworldUsersForm" property="vndnum">
						<thead>
							<tr align="center">		
								<th  class="table2titletd" nowrap>用户id</th>
								<th  class="table2titletd" nowrap>用户姓名</th>
								<th  class="table2titletd" nowrap>用户地址1</th>
								<th  class="table2titletd" nowrap>用户地址2</th>
								<th  class="table2titletd" nowrap>用户地址3</th>
							</tr>
						</thead>
						<tbody id="DynData">	
						<logic:iterate id="vndnum" name="homeworldUsersForm" property="vndnum" indexId="index">					
					     <tr align="center">
					     
					    	<logic:notEqual name="homeworldUsersForm" property="currrowshow" value='<%="" + index %>'>		
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="vndnum"  size="10" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldUsersForm' property='<%="vndnum[" + index + "]"%>'/>" > 
						            <input type="hidden" name="rowstate" value='0'>
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="vndnam"  size="15" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldUsersForm' property='<%="vndnam[" + index + "]"%>'/>" > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="vndnt1"  size="15" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldUsersForm' property='<%="vndnt1[" + index + "]"%>'/>" > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="vndnt2"  size="15" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldUsersForm' property='<%="vndnt2[" + index + "]"%>'/>" > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="vndnt3"  size="15" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldUsersForm' property='<%="vndnt3[" + index + "]"%>'/>" > 
						        </td>
						        <logic:notEqual name="vndnum" value="">
									<td><input type="button" name="butthis" value="编辑" onclick="changethisform(this)"></td> 
								</logic:notEqual>
					    	</logic:notEqual>	

							<logic:equal name="homeworldUsersForm" property="currrowshow" value='<%="" + index %>'>	
						    	<td colspan="5" align="center">
								    <br>
						    		<table width="50%" cellspacing="0" cellpadding="1" class="table1border">
 									<tr>
					    			 	<td class="table1title" nowrap>用户id:</td>
									    <td class="table1text" nowrap>
									     <input type="text" name="vndnum" size="10" class="n2centernone" readonly='true'
									          value="<bean:write name='homeworldUsersForm' property='<%="vndnum[" + index + "]"%>'/>">
							        		<input type="hidden" name="rowstate" value="0">
									     </td>									     
					    			  	<td class="table1title" nowrap>用户姓名:</td>
									    <td class="table1text" nowrap>
							        		<input type="text" name="vndnam"  size="39" class='n1left'  onfocus="this.select();" onchange="tdchange(this.form)"
								               value="<bean:write name='homeworldUsersForm' property='<%="vndnam[" + index + "]"%>'/>"> 				      
								         </td>
								     </tr>
									 <tr>
									    <td class="table1title" nowrap>用户地址1:</td>
										    <td class="table1text" colspan="3" nowrap>
								        		<input type="text" name="vndnt1"  size="60" class='n1left'   onfocus="this.select();" onchange="tdchange(this.form)"
									              value="<bean:write name='homeworldUsersForm' property='<%="vndnt1[" + index + "]"%>'/>"> 				            				            
								         </td>	
									 </tr> 
									 <tr>
										<td class="table1title" nowrap> 用户地址2:</td>
									    <td class="table1text" colspan="3" nowrap>
									        <input type="text" name="vndnt2"  size="60" class='n1left'   onfocus="this.select();" onchange="tdchange(this.form)"
									          value="<bean:write name='homeworldUsersForm' property='<%="vndnt2[" + index + "]"%>'/>"> 				            			            
									    </td>
									 </tr> 
									 <tr>
								         <td class="table1title" nowrap> 用户地址3:</td>
									     <td class="table1text" colspan="3" nowrap>
								       	     	<input type="text" name="vndnt3" size="60" class='n1left'  onfocus="this.select();" onchange="tdchange(this.form)"
								            	  value="<bean:write name='homeworldUsersForm' property='<%="vndnt3[" + index + "]"%>'/>"> 
								         </td>
									 </tr>
									
								</table>	
   						       <br>    
   						       <center>
							   <html:button property="butcommit" onclick ="subform(this.form)">提交</html:button>
							   </center>
							   </td>
							   
							   <td><input type="button" name="buthidden" value="隐藏" onclick="hiddenthisform(this.form)"></td>
							</logic:equal>		    	
					   	
					   	 </tr>
						 </logic:iterate>			
				</logic:present>
				
				<logic:equal name="homeworldUsersForm" property="currrowshow" value='-1'>	
				    	<td colspan="5" align="center">
						    <br>
				    		<table width="50%" cellspacing="0" cellpadding="1" class="table1border">
							<tr>
			    			 	<td class="table1title" nowrap>用户id:</td>
							    <td class="table1text" nowrap>
							     <input type="text" name="vndnum" size="10"  maxlength="6"  class="n1left"
							          value="">
					        		<input type="hidden" name="rowstate" value="3">
							     </td>									     
			    			  	<td class="table1title" nowrap>用户姓名:</td>
							    <td class="table1text" nowrap>
					        		<input type="text" name="vndnam"  size="39" class='n1left'  maxlength="40"  onfocus="this.select();" onchange="tdchange(this.form)"
						               value=""> 				      
						         </td>
						     </tr>
							 <tr>
							    <td class="table1title" nowrap>用户地址1:</td>
								    <td class="table1text" colspan="3" nowrap>
						        		<input type="text" name="vndnt1"  size="60"  maxlength="50"  class='n1left'   onfocus="this.select();" onchange="tdchange(this.form)"
							              value=""> 				            				            
						         </td>	
							 </tr> 
							 <tr>
								<td class="table1title" nowrap> 用户地址2:</td>
							    <td class="table1text" colspan="3" nowrap>
							        <input type="text" name="vndnt2"  size="60" maxlength="50" class='n1left' onfocus="this.select();" onchange="tdchange(this.form)"
							          value=""> 				            			            
							    </td>
							 </tr> 
							 <tr>
						         <td class="table1title" nowrap> 用户地址3:</td>
							     <td class="table1text" colspan="3" nowrap>
						       	     	<input type="text" name="vndnt3" size="60" maxlength="50" class='n1left'  onfocus="this.select();" onchange="tdchange(this.form)"
						            	  value=""> 
						         </td>
							 </tr>			
						</table>	
				       <br>    
				       <center>
					   <html:button property="butcommit" onclick ="subform(this.form)">提交</html:button>
					   </center>
					   </td>
					  
					</logic:equal>		    	
			   	
			   	 </tr>
				
				
				</tbody>	
			</table>
		</logic:equal>	
		
		<br>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			
		<script language="JavaScript">	
		if (document.forms[0].currrowshow.value=="-1"){
			document.forms[0].currrowshow.value=document.forms(0).count.value;
			document.forms[0].currrow.value=document.forms(0).count.value;
		}
		
			//查询调用
			function selectform(thisform){	
			
				thisform.where.value=thisform.id.value+ " = '"+thisform.liketext.value.toUpperCase()+"'  ";

				//thisform.where.value=thisform.id.value+ " like '%"+thisform.liketext.value.toUpperCase()+"%' or ";
				//thisform.where.value=thisform.where.value + "vndnum like '%"+thisform.liketext.value+"%' ";

				if(saveChange(thisform,"select")!= 1){
					thisform.flag.value="selectexic";
					//validateSubmit(thisform);
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
			function subform(thisform) {
				var currow=thisform.currrowshow.value;
				thisform.flag.value="commit";
				thisform.submit();
				return 1;
				
			}
		</script>
		</html:form>
	</BODY>
</html:html>
