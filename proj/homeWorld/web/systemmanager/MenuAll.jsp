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
		<TITLE> �˵�����</TITLE>
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
				 �˵�����
		    </H2></center>
		    <table  align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
				<thead>
					<tr align="center">
						<th  class="table2titletd">�˵�id</th>
						<th  class="table2titletd">���Ĳ˵�����</th>
						<th  class="table2titletd">���Ĳ˵�ִ����</th>
						<th  class="table2titletd">Ӣ�Ĳ˵�����</th>
						<th  class="table2titletd">Ӣ�Ĳ˵�ִ����</th>
						<th  class="table2titletd">ϵͳ</th>
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
									<td><input type="button" name="butthis" value="�༭" onclick="changethisform(this)"></td> 
						    	</logic:notEqual>	
					    	</logic:notEqual>	
					    	
					    	<logic:equal name="MenuAllForm" property="currrowshow" value='<%="" + index %>'>		 					
					    		<td colspan="6">
								    <input type="hidden" name="id" value=<bean:write name="MenuAllForm" property='<%="menuid[" + index + "]"%>'/>>
								    
									
					    			<table width="50%" cellspacing="0" cellpadding="1" class="table1border">
									  <tr>
									  	<td class="table1title" nowrap>�˵�id:</td>
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
									  	<td class="table1title" nowrap> Ӧ��ϵͳ:</td>
									    <td class="table1text" nowrap>
									     <input type="text" name="appsysshow" maxlength="50" size="20"  onclick ="selectfield(this.form,<%=index%>,'appsys','soft_idn.appsyscode','ϵͳ')" onchange="tdchange(this.form)" readonly="true" class="n1right"
									            value=<bean:write name="MenuAllForm" property='<%="appsysshow[" + index + "]"%>'/>> 				               
									     </td>
									  </tr>
									  
								      <tr>
									  	<td class="table1title" nowrap> ���Ĳ˵�����:</td>
									    <td class="table1text" nowrap>
								       			<input type="text" name="mnamec" maxlength="20" size="20" onchange="tdchange(this.form)" onfocus="this.select()" class="n1right"
								            value=<bean:write name="MenuAllForm" property='<%="mnamec[" + index + "]"%>'/> > 
								        </td>
									  	<td class="table1title" nowrap> ���Ĳ˵�ִ����:</td>
									    <td class="table1text" nowrap>
									        	<input type="text" name="mfile" maxlength="200" size="50"  onchange="tdchange(this.form)" onfocus="this.select()" class="n1right"
									            value=<bean:write name="MenuAllForm" property='<%="mfile[" + index + "]"%>'/> > 				            
									    </td>
									   </tr>
									  
								       <tr> 
										  	<td class="table1title" nowrap> Ӣ�Ĳ˵�����:</td>
										    <td class="table1text" nowrap>
								        		<input type="text" name="mnamee" maxlength="20" size="20" onchange="tdchange(this.form)" onfocus="this.select()" class="n1right"
									            value=<bean:write name="MenuAllForm" property='<%="mnamee[" + index + "]"%>'/> > 				            
									         </td>
								      
										  	<td class="table1title" nowrap> Ӣ�Ĳ˵�ִ����:</td>
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
									<input type="button" name="butcommit" value="�ύ" onclick="submitform(this.form,'commit')">
									<logic:notEqual name="MenuAllForm" property='<%="rowstate[" + index + "]"%>' value="4">	
										<input type="button" name="butdel" value="ɾ��" onclick ="delform(this.form)">
									</logic:notEqual>
					    		</td>
					    		
								<logic:notEqual  name="MenuAllForm" property='<%="rowstate[" + index + "]"%>' value="3">
									<logic:notEqual name="MenuAllForm" property='<%="rowstate[" + index + "]"%>' value="4">
										<td><input type="button" name="buthidden" value="����" onclick="hiddenthisform(this.form)"></td> 
									</logic:notEqual>
								</logic:notEqual>
					    	</logic:equal>
					    	<logic:equal name="MenuAllForm" property="page" value="1">		 				    		
								<logic:notEqual name="menuid" value="">
									<td><input type="button" name="butnext" value="�¼�" onclick="changenextform(this)"></td> 
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
									  	<td class="table1title" nowrap>�˵�id:</td>
									    <td class="table1text" nowrap>
											<input type="text" name="menuid" maxlength="4" size="20" onchange="tdchange(this.form)"  onfocus="this.select()" class="n1right"> 								        
											<input type="hidden" name="rowstate" value='3'>	
											<input type="hidden" name="appsysid" value=''>
							        	</td>
									  	<td class="table1title" nowrap> Ӧ��ϵͳ:</td>
									    <td class="table1text" nowrap>
										     <input type="text" name="appsysshow"  maxlength="50" size="50" onclick ="selectfield(this.form,-1,'appsys','soft_idn.appsyscode','ϵͳ')" onchange="tdchange(this.form)" readonly="true" class="n1right"> 				               
									     </td>
									  </tr>
									  
								      <tr>
									  	<td class="table1title" nowrap> ���Ĳ˵�����:</td>
									    <td class="table1text" nowrap>
								       			<input type="text" name="mnamec" maxlength="20" size="20" onchange="tdchange(this.form)" onfocus="this.select()" class="n1right">
								        </td>
									  	<td class="table1title" nowrap> ���Ĳ˵�ִ����:</td>
									    <td class="table1text" nowrap>
									        	<input type="text" name="mfile" maxlength="200" size="50" onchange="tdchange(this.form)" onfocus="this.select()" class="n1right">
									    </td>
									   </tr>
									  
								       <tr> 
										  	<td class="table1title" nowrap> Ӣ�Ĳ˵�����:</td>
										    <td class="table1text" nowrap>
								        		<input type="text" name="mnamee" maxlength="20" size="20" onchange="tdchange(this.form)" onfocus="this.select()" class="n1right">
									         </td>
								      
										  	<td class="table1title" nowrap> Ӣ�Ĳ˵�ִ����:</td>
										    <td class="table1text" nowrap>
												<input type="text" name="mfilee" maxlength="200" size="50" onchange="tdchange(this.form)" onfocus="this.select()" class="n1right">
										     </td>
							        	</tr><br>
							        </table>
							        <br>
								<input type="button" name="butcommit" value="�ύ" onclick="submitform(this.form,'commit')">
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
		    		<td><html:button property="butesc" onclick ="escform(this.form,'return')">����</html:button></td> 
				</logic:equal>
				<br>	
				
		
		      </tr>
			</table>	
			
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			
		</html:form>
		<script language="JavaScript">	
		//��ǰ��
		if(document.getElementById("currrowshow").value==-1){
			document.getElementById("currrow").value=document.getElementById("count").value;
			document.getElementById("currrowshow").value=document.getElementById("count").value;
		}
		
		rerowstate("menuid");
				
			//ѡ����뺯��
			function selectfield(thisform,currrow,currcol,code,cshow){
				var select;
				if(selectone(thisform,currrow,currcol,code,cshow)=="1"){
					thisform.flag.value="cyl";
					thisform.submit();				
				}
			}
			
			//���ص���
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
			
			//�༭����
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
			
			//�¼�����
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
						
			//���غ���
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
						
			//ɾ������
			function delform(thisform){
				if (confirm("�Ƿ������ɾ�����У�")){
					thisform.currrowshowold.value=thisform.currrowshow.value;
					thisform.currrowshow.value="-1";
					thisform.currrow.value="-1";
					
					thisform.flag.value="del";
					thisform.submit();
				} 
			}
						
			//�˳�����
			function exitform(thisform,aflag){
				
				//alert(document.MenuAllForm.key.value.substring(0,2));	
			
				if(saveChange(thisform,"exit") == 0){
					window.open("<bean:message key='Home.page'/>","_self","");
				}
			}
			
			//У���ύ����
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
			
			//�Ƚ�У�����(����,��ʾ����,����)
			function compares () { 
				this.aa = new Array("menuid","�˵�id ����λ������00",'<bean:write name="MenuAllForm" property="key"/>00');
			} 
			
			//��ЧУ�����(����,��ʾ����,����)
			function masks () { 
				<logic:notEqual name="MenuAllForm" property="page" value="2">
					this.aa = new Array("menuid", "�˵�id ����λ������00ǰ��λ���������֣�","^\\d\\d00$");
				</logic:notEqual>
				<logic:equal name="MenuAllForm" property="page" value="2">
					this.aa = new Array("menuid", "�˵�id ǰ��λ������<bean:write name="MenuAllForm" property="key"/>����λ�����Ƿ������֣�","^<bean:write name="MenuAllForm" property="key"/>[0-9]{2}$");
				</logic:equal>
			} 
			
			//��¼У�����(����,��ʾ����)
			function required () { 
				this.aa = new Array("menuid", "�˵�id ����Ϊ�գ�");
				this.ab = new Array("appsysshow", "ϵͳ ����Ϊ�գ�");
			}
			 
			//�ظ�У�����(������,��ʾ����)
			function repeat () { 
				this.aa = new Array("menuid", "�˵�id �ظ���");
			}

		</script>
	</BODY>
</html:html>
