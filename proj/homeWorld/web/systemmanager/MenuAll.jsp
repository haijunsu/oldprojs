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
		<TITLE> 菜单管理</TITLE>
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
			<html:form action="/MenuAll" >
			
			<html:hidden property="key" />
			
			<html:hidden property="count" />
			<html:hidden property="flag" />
			<html:hidden property="currrow"/>
			<html:hidden property="currcolumn"/>
		    <html:hidden property="page" />
		    <html:hidden property="currrowshow"/>
			<html:hidden property="currrowshowold"/>
			
			<center><H2>			
				<html:errors property="testActionErrors"/>
				 菜单管理
		    </H2></center>
		    <table  align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
				<thead>
					<tr align="center">
						<th  class="table2titletd">菜单id</th>
						<th  class="table2titletd">中文菜单名称</th>
						<th  class="table2titletd">中文菜单执行名</th>
						<th  class="table2titletd">英文菜单名称</th>
						<th  class="table2titletd">英文菜单执行名</th>
						<th  class="table2titletd">系统</th>
					</tr>
				</thead>
				<tbody id="DynData">
					<logic:present name="MenuAllForm" property="menuid" >
					  <logic:iterate id="menuid" name="MenuAllForm" property="menuid" indexId="index">
					    <tr align="center">
					    
					    	<logic:notEqual name="MenuAllForm" property="currrowshow" value='<%="" + index %>'>		 
						    	<td class="table2textrighttd" nowrap> <input type="text" name="menuid" maxlength="16" size="8" class="n2centernone"
						             readonly="true"
						            value=<bean:write name="MenuAllForm" property='<%="menuid[" + index + "]"%>'/>> 
						             
									<input type="hidden" name="rowstate" value='0'> 
									     
						        	<input type="hidden" name="appsysid" value=<bean:write name="MenuAllForm" property='<%="appsysid[" + index + "]"%>'/>>
						        	
						        </td>
						         <td class="table2textrighttd" nowrap> <input type="text" name="mnamec"  size="10" class="n2centernone"
						            readonly="true"
						            value=<bean:write name="MenuAllForm" property='<%="mnamec[" + index + "]"%>'/>> 
						        </td>
						         <td class="table2textrighttd" nowrap> <input type="text" name="mfile"  size="15" class="n2centernone"
						            readonly="true"
						            value=<bean:write name="MenuAllForm" property='<%="mfile[" + index + "]"%>'/>> 				            
						        </td>
						         <td class="table2textrighttd" nowrap> <input type="text" name="mnamee"  size="10" class="n2centernone"
						            readonly="true"
						            value=<bean:write name="MenuAllForm" property='<%="mnamee[" + index + "]"%>'/>> 				            
						        </td>
						         <td class="table2textrighttd" nowrap> <input type="text" name="mfilee"  size="15" class="n2centernone"
						            readonly="true"
						            value=<bean:write name="MenuAllForm" property='<%="mfilee[" + index + "]"%>'/>> 				            
						        </td>
						         <td class="table2textrighttd" nowrap> <input type="text" name="appsysshow"  size="10" class="n2centernone"
						            readonly="true"
						            value=<bean:write name="MenuAllForm" property='<%="appsysshow[" + index + "]"%>'/>> 				            
						        </td>
						        
								<logic:notEqual name="menuid" value="">
									<td><input type="button" name="butthis" value="编辑" onclick="changethisform(this)"></td> 
						    	</logic:notEqual>	
					    	</logic:notEqual>	
					    	
					    	<logic:equal name="MenuAllForm" property="currrowshow" value='<%="" + index %>'>		 					
					    		<td colspan="6">
								    <input type="hidden" name="id" value=<bean:write name="MenuAllForm" property='<%="menuid[" + index + "]"%>'/>>
								    
									
					    			<table width="50%" cellspacing="0" cellpadding="1" class="table1border">
									  <tr>
									  	<td class="table1title" nowrap>菜单id:</td>
									    <td class="table1text" nowrap>
								        	<input type="text" name="menuid" maxlength="4" size="20"  onfocus="this.select()" class="n1right" onchange="tdchange(this.form)"
								        		value=<bean:write name="MenuAllForm" property='<%="menuid[" + index + "]"%>'/> > 								        
											<logic:equal name="MenuAllForm" property="currrowshow" value='-1' >
								        		<input type="hidden" name="rowstate" value='0'>
											</logic:equal> 
											<logic:notEqual name="MenuAllForm" property="currrowshow" value='-1'>
								        		<input type="hidden" name="rowstate" value=<bean:write name="MenuAllForm" property='<%="rowstate[" + index + "]"%>'/>>
											</logic:notEqual> 								
									        <input type="hidden" name="appsysid" value=<bean:write name="MenuAllForm" property='<%="appsysid[" + index + "]"%>'/>>
								        </td>
									  	<td class="table1title" nowrap> 应用系统:</td>
									    <td class="table1text" nowrap>
									     <input type="text" name="appsysshow" maxlength="50" size="20"  onclick ="selectfield(this.form,<%=index%>,'appsys','soft_idn.appsyscode','系统')" onchange="tdchange(this.form)" readonly="true" class="n1right"
									            value=<bean:write name="MenuAllForm" property='<%="appsysshow[" + index + "]"%>'/>> 				               
									     </td>
									  </tr>
									  
								      <tr>
									  	<td class="table1title" nowrap> 中文菜单名称:</td>
									    <td class="table1text" nowrap>
								       			<input type="text" name="mnamec" maxlength="20" size="20" onchange="tdchange(this.form)" onfocus="this.select()" class="n1right"
								            value=<bean:write name="MenuAllForm" property='<%="mnamec[" + index + "]"%>'/> > 
								        </td>
									  	<td class="table1title" nowrap> 中文菜单执行名:</td>
									    <td class="table1text" nowrap>
									        	<input type="text" name="mfile" maxlength="200" size="50"  onchange="tdchange(this.form)" onfocus="this.select()" class="n1right"
									            value=<bean:write name="MenuAllForm" property='<%="mfile[" + index + "]"%>'/> > 				            
									    </td>
									   </tr>
									  
								       <tr> 
										  	<td class="table1title" nowrap> 英文菜单名称:</td>
										    <td class="table1text" nowrap>
								        		<input type="text" name="mnamee" maxlength="20" size="20" onchange="tdchange(this.form)" onfocus="this.select()" class="n1right"
									            value=<bean:write name="MenuAllForm" property='<%="mnamee[" + index + "]"%>'/> > 				            
									         </td>
								      
										  	<td class="table1title" nowrap> 英文菜单执行名:</td>
										    <td class="table1text" nowrap>
									        	<input type="text" name="mfilee" maxlength="200" size="50" onchange="tdchange(this.form)" onfocus="this.select()" class="n1right"
									            value=<bean:write name="MenuAllForm" property='<%="mfilee[" + index + "]"%>'/> > 				            
										     </td>
							        	</tr><br>
							        	
							        	
							        	<logic:present name="MenuAllForm" property="mpurviewid" >
								        	<tr><td colspan="4" nowrap>&nbsp;&nbsp;
									        	<logic:iterate id="mpurviewshow" name="MenuAllForm" property="mpurviewshow" indexId="indexrow">
									        		<logic:equal name="indexrow" value="8">										
									    				</td></tr>    								
								    					<tr><td  colspan="4" nowrap>&nbsp;&nbsp;
													</logic:equal>
											        &nbsp;&nbsp;<%=mpurviewshow%>
											        <html:multibox property="mpurviewid" onclick="tdchange(this.form)"> <%= "" + indexrow%> </html:multibox>						
												</logic:iterate>     
								        	</td></tr>
										</logic:present>
							        	
							        	
							        </table>
							        <br>
									<input type="button" name="butcommit" value="提交" onclick="submitform(this.form,'commit')">
									<logic:notEqual name="MenuAllForm" property='<%="rowstate[" + index + "]"%>' value="4">	
										<input type="button" name="butdel" value="删除" onclick ="delform(this.form)">
									</logic:notEqual>
					    		</td>
					    		
								<logic:notEqual  name="MenuAllForm" property='<%="rowstate[" + index + "]"%>' value="3">
									<logic:notEqual name="MenuAllForm" property='<%="rowstate[" + index + "]"%>' value="4">
										<td><input type="button" name="buthidden" value="隐藏" onclick="hiddenthisform(this.form)"></td> 
									</logic:notEqual>
								</logic:notEqual>
					    	</logic:equal>
					    	<logic:equal name="MenuAllForm" property="page" value="1">		 				    		
								<logic:notEqual name="menuid" value="">
									<td><input type="button" name="butnext" value="下级" onclick="changenextform(this)"></td> 
								</logic:notEqual>
							</logic:equal>
				
					   	 </tr>
					  </logic:iterate>
					</logic:present>
		
					<logic:equal name="MenuAllForm" property="currrowshow" value="-1">		 					
						<tr align="center">
							<td colspan="6">
							
							
								<table width="50%" cellspacing="0" cellpadding="1" class="table1border">
									  <tr>
									  	<td class="table1title" nowrap>菜单id:</td>
									    <td class="table1text" nowrap>
											<input type="text" name="menuid" maxlength="4" size="20" onchange="tdchange(this.form)"  onfocus="this.select()" class="n1right"> 								        
											<input type="hidden" name="rowstate" value='3'>	
											<input type="hidden" name="appsysid" value=''>
							        	</td>
									  	<td class="table1title" nowrap> 应用系统:</td>
									    <td class="table1text" nowrap>
										     <input type="text" name="appsysshow"  maxlength="50" size="50" onclick ="selectfield(this.form,-1,'appsys','soft_idn.appsyscode','系统')" onchange="tdchange(this.form)" readonly="true" class="n1right"> 				               
									     </td>
									  </tr>
									  
								      <tr>
									  	<td class="table1title" nowrap> 中文菜单名称:</td>
									    <td class="table1text" nowrap>
								       			<input type="text" name="mnamec" maxlength="20" size="20" onchange="tdchange(this.form)" onfocus="this.select()" class="n1right">
								        </td>
									  	<td class="table1title" nowrap> 中文菜单执行名:</td>
									    <td class="table1text" nowrap>
									        	<input type="text" name="mfile" maxlength="200" size="50" onchange="tdchange(this.form)" onfocus="this.select()" class="n1right">
									    </td>
									   </tr>
									  
								       <tr> 
										  	<td class="table1title" nowrap> 英文菜单名称:</td>
										    <td class="table1text" nowrap>
								        		<input type="text" name="mnamee" maxlength="20" size="20" onchange="tdchange(this.form)" onfocus="this.select()" class="n1right">
									         </td>
								      
										  	<td class="table1title" nowrap> 英文菜单执行名:</td>
										    <td class="table1text" nowrap>
												<input type="text" name="mfilee" maxlength="200" size="50" onchange="tdchange(this.form)" onfocus="this.select()" class="n1right">
										     </td>
							        	</tr><br>
							        </table>
							        <br>
								<input type="button" name="butcommit" value="提交" onclick="submitform(this.form,'commit')">
							</td>
						</tr>
				  	</logic:equal>
		 		</tbody>	
			</table>
			
			<br>
			<br>
		    <table align="center" width="50%">
		      <tr align="center"> 
			    <logic:equal name="MenuAllForm" property="page" value="2">		 		      
		    		<td><html:button property="butesc" onclick ="escform(this.form,'return')">返回</html:button></td> 
				</logic:equal>
				<br>	
				
		
		      </tr>
			</table>	
			
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			
		</html:form>
		<script language="JavaScript">	
		//当前行
		if(document.getElementById("currrowshow").value==-1){
			document.getElementById("currrow").value=document.getElementById("count").value;
			document.getElementById("currrowshow").value=document.getElementById("count").value;
		}
		
		rerowstate("menuid");
				
			//选择代码函数
			function selectfield(thisform,currrow,currcol,code,cshow){
				var select;
				if(selectone(thisform,currrow,currcol,code,cshow)=="1"){
					thisform.flag.value="cyl";
					thisform.submit();				
				}
			}
			
			//隐藏调用
			function hiddenthisform(thisform){
				thisform.currrowshowold.value=thisform.currrowshow.value;
				thisform.currrowshow.value="-1";
				thisform.currrow.value=thisform.count.value;
				
				//savechange
				if(saveChange(thisform,"hidden") !=1){
					thisform.flag.value="hiddenxx";
					thisform.submit();
				}
			}
			
			//编辑调用
			function changethisform(thiscell){
			
				var currrow=thiscell.parentNode.parentNode.rowIndex - 1;
				
				thiscell.form.currrowshowold.value=thiscell.form.currrowshow.value;
				thiscell.form.currrowshow.value=currrow;
				thiscell.form.currrow.value=currrow;
				
				//savechange
				if(saveChange(thiscell.form,"changethis") !=1){
					thiscell.form.flag.value="changethisxx";
					thiscell.form.submit();
				}
			}
			
			//下级调用
			function changenextform(thiscell){
				var currrow=thiscell.parentNode.parentNode.rowIndex - 1;
				
				thiscell.form.key.value=thiscell.form.menuid[currrow].value.substring(0,2);
				thiscell.form.currrowshowold.value=thiscell.form.currrowshow.value;
				thiscell.form.currrowshow.value="-1";
				thiscell.form.page.value="2";
				
				//savechange
				if(saveChange(thiscell.form,"changenext") !=1){
					thiscell.form.flag.value="changenextxx";
					thiscell.form.submit();
				}
			}
						
			//返回函数
			function escform(thisform,flag){
					thisform.currrowshowold.value=thisform.currrowshow.value;
					thisform.currrowshow.value="-1";
					//thisform.currrow.value="-1";
					thisform.page.value="1";
					
				if(saveChange(thisform,"return") == 0){
					thisform.flag.value="returnxx";
					thisform.submit();
				}
			}
						
			//删除函数
			function delform(thisform){
				if (confirm("是否真的想删除本行？")){
					thisform.currrowshowold.value=thisform.currrowshow.value;
					thisform.currrowshow.value="-1";
					thisform.currrow.value="-1";
					
					thisform.flag.value="del";
					thisform.submit();
				} 
			}
						
			//退出函数
			function exitform(thisform,aflag){
				
				//alert(document.MenuAllForm.key.value.substring(0,2));	
			
				if(saveChange(thisform,"exit") == 0){
					window.open("<bean:message key='Home.page'/>","_self","");
				}
			}
			
			//校验提交函数
			function validateSubmit(form) {
				if (validField(form,1) && validField(form,2) && validRepeat(form)
				<logic:equal name="MenuAllForm" property="page" value="2">
					 && validField(form,6)
				</logic:equal>			
				)
				{
					form.submit();
				}
				
				return;
			}
			
			//比较校验参数(列名,提示内容,参数)
			function compares () { 
				this.aa = new Array("menuid","菜单id 后两位不可是00",'<bean:write name="MenuAllForm" property="key"/>00');
			} 
			
			//有效校验参数(列名,提示内容,规则)
			function masks () { 
				<logic:notEqual name="MenuAllForm" property="page" value="2">
					this.aa = new Array("menuid", "菜单id 后两位必须是00前两位必须是数字！","^\\d\\d00$");
				</logic:notEqual>
				<logic:equal name="MenuAllForm" property="page" value="2">
					this.aa = new Array("menuid", "菜单id 前两位必须是<bean:write name="MenuAllForm" property="key"/>后两位必须是非零数字！","^<bean:write name="MenuAllForm" property="key"/>[0-9]{2}$");
				</logic:equal>
			} 
			
			//必录校验参数(列名,提示内容)
			function required () { 
				this.aa = new Array("menuid", "菜单id 不能为空！");
				this.ab = new Array("appsysshow", "系统 不能为空！");
			}
			 
			//重复校验参数(列名串,提示内容)
			function repeat () { 
				this.aa = new Array("menuid", "菜单id 重复！");
			}

		</script>
	</BODY>
</html:html>
