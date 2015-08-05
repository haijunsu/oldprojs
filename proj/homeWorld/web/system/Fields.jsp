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
		<TITLE> 字段管理</TITLE>
		<%
			response.setHeader("Pragma","No-cache"); 
			response.setHeader("Cache-Control","no-cache"); 
			response.setDateHeader("Expires", 0); 
		%>
	</HEAD>
	<BODY>
	<html:form action="/Fields" >
		<html:hidden property="key" />
		<html:hidden property="count" />
		<html:hidden property="flag" />
		<html:hidden property="currrow"/>
		<html:hidden property="currcolumn"/>
		<html:hidden property="page" />
		<html:hidden property="currrowshow"/>
		<html:hidden property="currrowshowold"/>
		<logic:iterate name="FieldsForm" id="ftypeeshow" property="ftypeeshow" indexId="indexkk">
			<input type="hidden" name="ftypeeshow" value='<%=ftypeeshow%>'>
			
		</logic:iterate>
		<logic:iterate name="FieldsForm" id="ftypeeid" property="ftypeeid" indexId="indexkh">
			<input type="hidden" name="ftypeeid" value='<%=ftypeeid%>'>
			
		</logic:iterate>
		<center><H2>			
		<html:errors property="testActionErrors"/>
			 字段管理
		</H2></center>
		<%
		   	String strqwhere = "TABLE";
		%>	
		<table  align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
				<thead>
				<tr align="center">
						<th  class="table2titletd">字段</th>
						<th  class="table2titletd">类型</th>
						<th  class="table2titletd">长度</th>
						<th  class="table2titletd" nowrap>小数位</th>
						<th  class="table2titletd" nowrap>中文说明</th>
						<th  class="table2titletd" nowrap>英文说明</th>
						<th  class="table2titletd">下拉窗</th>
				</tr>
				</thead>
			<tbody id="DynData">
					<logic:present name="FieldsForm" property="fieldid" >
					  <logic:iterate id="fieldid" name="FieldsForm" property="fieldid" indexId="index">
					    <tr align="center">
					       	<logic:notEqual name="FieldsForm" property="currrowshow" value='<%="" + index %>'>		 				
								 <td class="table2textrighttd" nowrap> <input type="text" name="fieldid" maxlength="16" size="8" class="n2centernone"
							           readonly="true"
							           value=<bean:write name="FieldsForm" property='<%="fieldid[" + index + "]"%>'/>> 
							           <input type="hidden" name="rowstate" value='0'>
							           <input type="hidden" name="fieldidkey" value=<bean:write name="FieldsForm" property='<%="fieldid[" + index + "]"%>'/>> 
								</td>
							    <td class="table2textrighttd" nowrap> <input type="text" name="ftypeshow"  size="10" class="n2centernone"
						            readonly="true"
						            value=<bean:write name="FieldsForm" property='<%="ftypeshow[" + index + "]"%>'/>> 	
						            <input type="hidden" name="ftypeid" value=<bean:write name="FieldsForm" property='<%="ftypeid[" + index + "]"%>'/>> 
						        </td>
						        <td class="table2textrighttd" nowrap> <input type="text" name="flength"  size="2" class="n2centernone"
						            readonly="true"
						            value=<bean:write name="FieldsForm" property='<%="flength[" + index + "]"%>'/>> 				            
						        </td>
						        <td class="table2textrighttd" nowrap> <input type="text" name="fdigits"  size="2" class="n2centernone"
						            readonly="true"
						            value=<bean:write name="FieldsForm" property='<%="fdigits[" + index + "]"%>'/>> 				            
						        </td>
						        <td class="table2textrighttd" nowrap> <input type="text" name="fheaderc"  size="10" class="n2centernone"
						            readonly="true"
						            value=<bean:write name="FieldsForm" property='<%="fheaderc[" + index + "]"%>'/>> 				            
						        </td>
						        <td class="table2textrighttd" nowrap> <input type="text" name="fheadere"  size="10" class="n2centernone"
						            readonly="true"
						            value=<bean:write name="FieldsForm" property='<%="fheadere[" + index + "]"%>'/>> 				            
						        </td>
						        <td class="table2textrighttd" nowrap> <input type="text" name="cclass"  size="10" class="n2centernone"
						            readonly="true"
						            value=<bean:write name="FieldsForm" property='<%="cclass[" + index + "]"%>'/>> 				            
						        </td>
						   
						   		<logic:notEqual name="fieldid" value="">
									<td><input type="button" name="butthis" value="编辑" onclick="changethisform(this)"></td> 
						    	</logic:notEqual>	
						  </logic:notEqual>
						  
						  
						  <logic:equal name="FieldsForm" property="currrowshow" value='<%="" + index %>'>
						 		<td colspan="7">
							   <input type="hidden" name="id" value=<bean:write name="FieldsForm" property='<%="fieldid[" + index + "]"%>'/>>
							    <table width="50%" cellspacing="0" cellpadding="1" class="table1border">
								<%
								String stronclick = "selectvalue(this.form," + index+",'ftype','ftypee')";
								%>
								       <tr> 
										  	<td class="table1title" nowrap> 字段:</td>
										    <td class="table1text" nowrap>
								        		<input type="text" name="fieldid" maxlength="20" size="15" onchange="toup(this.form)" onfocus="this.select()" class="n1left"
									                  value=<bean:write name="FieldsForm" property='<%="fieldid[" + index + "]"%>'/> > 
									            <input type="hidden" name="rowstate" value='0'>
									            <input type="hidden" name="fieldidkey" value=<bean:write name="FieldsForm" property='<%="fieldid[" + index + "]"%>'/>> 
									     	</td>
									     	<td class="table1title" nowrap> 类型:</td>
										    <td class="table1text" nowrap>
									        	<input type="text" name="ftypeshow" maxlength="20" size="15" onchange="tdchange(this.form)" onclick="<%=stronclick%>" class="n1left"
									             value=<bean:write name="FieldsForm" property='<%="ftypeshow[" + index + "]"%>'/> >
									            <input type="hidden" name="ftypeid" value=<bean:write name="FieldsForm" property='<%="ftypeid[" + index + "]"%>'/>>  				            
										     </td>
										     <td class="table1title" nowrap> 长度:</td>
										    <td class="table1text" nowrap>
									        	<input type="text" name="flength" maxlength="4" size="4" onchange="tdchange(this.form)" onfocus="this.select()" class="n1right"
									            value=<bean:write name="FieldsForm" property='<%="flength[" + index + "]"%>'/> > 				            
										     </td>
										     <td class="table1title" nowrap> 小数位:</td>
										    <td class="table1text" nowrap>
									        	<input type="text" name="fdigits" maxlength="4" size="15" onchange="tdchange(this.form)" onfocus="this.select()" class="n1right"
									             value=<bean:write name="FieldsForm" property='<%="fdigits[" + index + "]"%>'/> > 				            
										     </td>
									     </tr>
									     <tr>
							        		 <td class="table1title" nowrap> 中文说明:</td>
										    <td colspan=2 class="table1text" nowrap>
									        	<input type="text" name="fheaderc" maxlength="30" size="22" onchange="tdchange(this.form)" onfocus="this.select()" class="n1left"
									            value=<bean:write name="FieldsForm" property='<%="fheaderc[" + index + "]"%>'/> > 				            
										     </td>
						       				 <td class="table1title" nowrap> 英文说明:</td>
										    <td colspan=2 class="table1text" nowrap>
									        	<input type="text" name="fheadere" maxlength="30" size="18" onchange="tdchange(this.form)" onfocus="this.select()" class="n1left"
									            value=<bean:write name="FieldsForm" property='<%="fheadere[" + index + "]"%>'/> > 				            
										     </td>
										     <td class="table1title" nowrap> 下拉窗:</td>
										    <td class="table1text" nowrap>
									        	<input type="text" name="cclass" maxlength="30" size="15" onchange="tdchange(this.form)" onfocus="this.select()" class="n1left"
									             value=<bean:write name="FieldsForm" property='<%="cclass[" + index + "]"%>'/> > 				            
										     </td>
						        			
								        		
								       	</tr>
							        	
							        	
							        	
							       
							        	
							       </table>
							       <br>
								<input type="button" name="butcommit" value="提交" onclick="submitform(this.form,'commit')">
								<logic:notEqual name="FieldsForm" property='<%="rowstate[" + index + "]"%>' value="4">	
									<input type="button" name="butdel" value="删除" onclick ="delform(this.form)">
								</logic:notEqual>
					    	</td>
					    	
								<logic:notEqual  name="FieldsForm" property='<%="rowstate[" + index + "]"%>' value="3">
									<logic:notEqual name="FieldsForm" property='<%="rowstate[" + index + "]"%>' value="4">
										<td><input type="button" name="buthidden" value="隐藏" onclick="hiddenthisform(this.form)"></td> 
									</logic:notEqual>
								</logic:notEqual>
					    </logic:equal>
						  
						  
						  						  
						  
							
							
					
					    	
				
					   	 </tr>
					  </logic:iterate>
					</logic:present>
					
					
					
					<logic:equal name="FieldsForm" property="currrowshow" value="-1">		 					
						<tr align="center">
						<td colspan="7">
						<table width="50%" cellspacing="0" cellpadding="1" class="table1border">
						<tr> 
						  	<td class="table1title" nowrap> 字段:</td>
							<td class="table1text" nowrap>
						   	<input type="text" name="fieldid" maxlength="20" size="15" onfocus="this.select()" class="n1left" onchange="toup(this.form)"
						           value=''> 
						    	<input type="hidden" name="fieldidkey" value=''> 
						    	<input type="hidden" name="rowstate" value='3'>
						     </td>
						     <td class="table1title" nowrap> 类型:</td>
							 <td class="table1text" nowrap>
							    	<input type="hidden" name="ftypeid" 
									            value='CHAR'  onchange="tdchange(this.form)" > 
						        	<input type="text" name="ftypeshow" maxlength="20" size="15" class="n1left" onclick="selectvalue(this.form,<bean:write name='FieldsForm' property='count'/>,'ftype','ftypee')" onchange="tdchange(this.form)"
						            value='字符型'> 				            
						     </td>
						     <td class="table1title" nowrap> 长度:</td>
							 <td class="table1text" nowrap>
							   	<input type="text" name="flength" maxlength="4" size="4" onfocus="this.select()" class="n1right" onchange="tdchange(this.form)"
							         value='10' > 				            
							 </td>
							<td class="table1title" nowrap> 小数位:</td>
							   <td class="table1text" nowrap>
							   	<input type="text" name="fdigits" maxlength="4" size="15" onfocus="this.select()" class="n1right" onchange="tdchange(this.form)"
							        value='0' > 				            
							   </td>
						      
						      </tr>
						      <tr>
							     <td class="table1title" nowrap> 中文说明:</td>
							     <td colspan=2 class="table2textrighttd" nowrap> 
							     <input type="text" name="fheaderc"  maxlength="30" size="22" onfocus="this.select()" class="n1left" onchange="tdchange(this.form)"
						    			value=''> 				            
						    	 </td>
						    	 <td class="table1title" nowrap> 英文说明:</td>
						    	 <td colspan=2 class="table2textrighttd" nowrap> 
						    	 <input type="text" name="fheadere"  maxlength="30" size="18" onfocus="this.select()" class="n1left" onchange="tdchange(this.form)"
						    			 value=''> 				            
						    	 </td>
						    	
							     <td class="table1title" nowrap> 下拉窗:</td>
						    	 <td class="table2textrighttd" nowrap> 
						    	 <input type="text" name="cclass"  maxlength="30" size="15" onfocus="this.select()" class="n1left" onchange="tdchange(this.form)"
						    			 value='no'> 				            
						    	 </td>
								      		
							</tr>
							<br>
							
							
							
							
							
							
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
			    
				<br>	
				<td><html:button property="butexit" onclick ="exitform(this.form,'exit')">退出</html:button></td> 
		
		      </tr>
			</table>	
		
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			
	</html:form>		
	<script language="JavaScript">
		//置焦点	
//		var focusshow=document.getElementById("SalaryUsersForm");
			
		if( document.getElementById("butcommit")!=null ){
			document.getElementById("butcommit").focus();
		}else{
//			focusshow.salaryno[0].focus();
			document.getElementById("DynData").childNodes(0).all("queryid").focus();	
				
		}	
		//当前行
		//当前行
		if(document.getElementById("currrowshow").value==-1){
			document.getElementById("currrow").value=document.getElementById("count").value;
			document.getElementById("currrowshow").value=document.getElementById("count").value;
			document.getElementById("currrowshowold").value="-1";
		}
		
		//rerowstate("queryid");
				
			
			
			//隐藏调用
			function hiddenthisform(thisform){
				thisform.currrowshowold.value=thisform.currrowshow.value;
				thisform.currrowshow.value="-1";
				thisform.currrow.value=thisform.count.value;
				
				//savechange
				if(saveChange(thisform,"hidden") == 0){
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
				if(saveChange(thiscell.form,"changethis") == 0){
					thiscell.form.flag.value="changethisxx";
					thiscell.form.submit();
				}
			}
			
				
			
			//下级调用
			function changenextform(thiscell){
				var currrow=thiscell.parentNode.parentNode.rowIndex - 1;
				
				thiscell.form.key.value=thiscell.form.queryid[currrow].value;
				thiscell.form.currrowshowold.value=thiscell.form.currrowshow.value;
				thiscell.form.currrowshow.value="-1";
				thiscell.form.page.value="2";
				if(saveChange(thiscell.form,"changenext") == 0){
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
				
				//alert(document.SalaryMenuAllForm.key.value.substring(0,2));	
			
				if(saveChange(thisform,"exit") == 0){
					window.open("<bean:message key='Home.page'/>","_self","");
				}
			}
			
			//校验提交函数
			function validateSubmit(form) {
				if (validField(form,1) && validRepeat(form) && validField(form,2))
					{
						form.submit();
					}
					
					return;
			}
			
			//必录校验参数(列名,提示内容)
			function required () {
				this.aa = new Array("fieldid","字段 不能为空");
			}
			 
			//重复校验参数(列名串,提示内容)
			function repeat () {
				this.ab = new Array("fieldid","字段 重复");
				
			}
			
			//有效校验参数(列名,提示内容,规则)
			function masks () { 
				this.ab = new Array("flength", "长度 必须为数字","[0-9]$");
				this.ac = new Array("fdigits", "小数位 必须为数字","[0-9]$");
			} 
			//转换大小写
			function toup(form){
				var temp;
				temp = form.fieldid[form.currrowshow.value].value;
				form.fieldid[form.currrowshow.value].value = temp.toUpperCase();
				tdchange(form);
			}

		</script>	
	</BODY>
</html:html>
