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
			<logic:equal name="UserForm" property="page" value="1">
				 ��Ա����
			</logic:equal>
			<logic:equal name="UserForm" property="page" value="2">
				 Ȩ�޹���
			</logic:equal>
		</TITLE>
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
			<html:form action="/User" >
			
			<html:hidden property="count" />		
			<html:hidden property="flag" />
			
		    <html:hidden property="page" />
			
			<html:hidden property="nowdeptid"/>
			<html:hidden property="nowdeptshow"/>
			
   		   <html:hidden property="currrowshowold"/>
		   <html:hidden property="currrowshow"/>
			<html:hidden property="currrow"/>
			
			<html:hidden property="returnurl" />
			
			<center><H2>			
				<html:errors property="testActionErrors"/>
					<logic:equal name="UserForm" property="page" value="1">
					 ��Ա���� 
					</logic:equal>
					<logic:equal name="UserForm" property="page" value="2">
					 Ȩ�޹��� 
					</logic:equal>
					&nbsp;&nbsp;
					<bean:write name="UserForm" property="nowdeptshow"/>
		    </H2></center>
		    <table  align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
				<thead>
					<tr align="center">
						<th  class="table2titletd">�û�id</th>
						<th  class="table2titletd">�û�����</th>
						<th  class="table2titletd">�Ա�</th>
						<th  class="table2titletd">ְ��</th>
						<th  class="table2titletd">�绰</th>
						<th  class="table2titletd">�ֻ�</th>
						<th  class="table2titletd">״̬</th>
 						<th  class="table2titletd">����</th>
					</tr>
				</thead>
				<tbody id="DynData">
				<logic:present name="UserForm" property="userid" >
					  <logic:iterate id="userid" name="UserForm" property="userid" indexId="index">
					     <tr align="center">
					     
					    	<logic:notEqual name="UserForm" property="currrowshow" value='<%="" + index %>'>		
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="userid"  size="10" class="n2centernone" readonly="true"
						            value=<bean:write name="UserForm" property='<%="userid[" + index + "]"%>'/>
						            > 
						            <input type="hidden" name="rowstate" value='0'>
						        	<input type="hidden" name="deptshow" value=<bean:write name="UserForm" property='<%="deptshow[" + index + "]"%>'/> >
						        	<input type="hidden" name="deptid" value=<bean:write name="UserForm" property='<%="deptid[" + index + "]"%>'/> >
						        	
						        	<input type="hidden" name="birthday" value=<bean:write name="UserForm" property='<%="birthday[" + index + "]"%>'/> >
						        	<input type="hidden" name="address" value=<bean:write name="UserForm" property='<%="address[" + index + "]"%>'/> >
						        	
						        	<input type="hidden" name="bp" value=<bean:write name="UserForm" property='<%="bp[" + index + "]"%>'/> >
						        	
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="namec"  size="20" class="n2centernone" readonly="true"
						            value=<bean:write name="UserForm" property='<%="namec[" + index + "]"%>'/>
						            > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="gendershow"  size="4" class="n2centernone" readonly="true"
						            value=<bean:write name="UserForm" property='<%="gendershow[" + index + "]"%>'/>
						            > 
						            <input type="hidden" name="genderid" value=<bean:write name="UserForm" property='<%="genderid[" + index + "]"%>'/> >
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="jobshow"  size="8" class="n2centernone" readonly="true"
						            value=<bean:write name="UserForm" property='<%="jobshow[" + index + "]"%>'/>
						            > 
						            <input type="hidden" name="jobid" value=<bean:write name="UserForm" property='<%="jobid[" + index + "]"%>'/> >
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="tel"  size="20" class="n2centernone" readonly="true"
						            value=<bean:write name="UserForm" property='<%="tel[" + index + "]"%>'/>
						            > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="handset"  size="20" class="n2centernone" readonly="true"
						            value=<bean:write name="UserForm" property='<%="handset[" + index + "]"%>'/>
						            > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="ustateshow"  size="8" class="n2centernone" readonly="true"
						            value=<bean:write name="UserForm" property='<%="ustateshow[" + index + "]"%>'/>
						            > 
						            <input type="hidden" name="ustateid" value=<bean:write name="UserForm" property='<%="ustateid[" + index + "]"%>'/> >
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="levelshow"  size="8" class="n2centernone" readonly="true"
						            value=<bean:write name="UserForm" property='<%="levelshow[" + index + "]"%>'/>
						            > 
						            <input type="hidden" name="levelid" value=<bean:write name="UserForm" property='<%="levelid[" + index + "]"%>'/> >
						        </td>
						        	
								<td><input type="button" name="butthis" value="�༭" onclick="changethisform(this)"></td> 
					    	</logic:notEqual>	
					    	
					    	<logic:equal name="UserForm" property="currrowshow" value='<%="" + index %>'>	
								<%
					    		String strStyle="n1right";
					    		String strApp="";
					    		String read="";
					    		
					    		String strGen="selectfields(this.form,"+String.valueOf(index)+",'gender','01','')";
					    		String strJob="selectfields(this.form,"+String.valueOf(index)+",'job','02','')";
					    		String strDep="selectfields(this.form,"+String.valueOf(index)+",'dept','08','')";
					    		String strUst="selectfields(this.form,"+String.valueOf(index)+",'ustate','09','')";
					    		String strLev="selectfields(this.form,"+String.valueOf(index)+",'level','13','')";
					    		%>
					    	<logic:equal name="UserForm" property="page" value="2">
					    		<%
					    		strStyle="n2centernone";
					    		strApp="selectfields(this.form,"+String.valueOf(index)+",'appsys','appsyscode','ϵͳ')";
					    		strGen="";
					    		strJob="";
					    		strDep="";
					    		strUst="";
					    		strLev="";
					    		read="readonly='true'";
					    		%>
					    	</logic:equal>
					    	<td colspan="8" align="center">
							    <br>
							   
					    		<table width="50%" cellspacing="0" cellpadding="1" class="table1border">
										 <tr> 	
					    			 	<td class="table1title" nowrap>�û�id:</td>
									    <td class="table1text" nowrap>
									     <input type="text" name="userid" maxlength="10" size="20" class="n2centernone"
									          readonly="true" value=<bean:write name="UserForm" property='<%="userid[" + index + "]"%>'/>>
							        		<input type="hidden" name="rowstate" value=<bean:write name="UserForm" property='<%="rowstate[" + index + "]"%>'/>>

									     </td>
								        
					    			  	<td class="table1title" nowrap>����:</td>
									    <td class="table1text" nowrap>
							        		<input type="text" name="namec"  maxlength="20" size="20" class='<%=strStyle%>'  onfocus="this.select()"
								               readonly="true" value=<bean:write name="UserForm" property='<%="namec[" + index + "]"%>'/>> 				            
								         </td>
								         
					    			  	<td class="table1title" nowrap> ְ��:</td>
									    <td class="table1text" nowrap>
							        		<input type="text" name="jobshow"   size="20" onclick="<%=strJob%>" class='<%=strStyle%>' 
								               readonly="true" value=<bean:write name="UserForm" property='<%="jobshow[" + index + "]"%>'/>> 				            
								            <input type="hidden" name="jobid"  
									            value=<bean:write name="UserForm" property='<%="jobid[" + index + "]"%>'/>> 				            
								         </td>
								     </tr>
									 <tr>
										<td class="table1title" nowrap> �Ա�:</td>
									    <td class="table1text" nowrap>
									        <input type="text" name="gendershow"  size="20"   onclick="<%=strGen%>" class='<%=strStyle%>'  
									          readonly="true"   value=<bean:write name="UserForm" property='<%="gendershow[" + index + "]"%>'/>> 				            
									        <input type="hidden" name="genderid"  
									            value=<bean:write name="UserForm" property='<%="genderid[" + index + "]"%>'/>> 				            
									    </td>
									    <td class="table1title" nowrap> ����:</td>
										    <td class="table1text" nowrap>
								        		<input type="text" name="deptshow"  size="20"  onclick="<%=strDep%>" class='<%=strStyle%>'
									              readonly="true" value=<bean:write name="UserForm" property='<%="deptshow[" + index + "]"%>'/>> 				            
									            <input type="hidden" name="deptid"  
										            value=<bean:write name="UserForm" property='<%="deptid[" + index + "]"%>'/>> 				            
								         </td>	
								         <td class="table1title" nowrap> ����:</td>
									     <td class="table1text" nowrap>
								       	     	<input type="text" name="birthday"  maxlength="10" size="20" onchange="tdchange(this.form)" class='<%=strStyle%>'  onfocus="this.select()"
								            	 <%=read%> value=<bean:write name="UserForm" property='<%="birthday[" + index + "]"%>'/>> 
								         </td>
									  </tr>
									  
									   <tr>  
							         	<td class="table1title" nowrap>�绰:</td>
									    <td class="table1text" nowrap>
								       		<input type="text" name="tel" maxlength="20" size="20" class='<%=strStyle%>' onchange="tdchange(this.form)" onfocus="this.select()"
								             <%=read%> value=<bean:write name="UserForm" property='<%="tel[" + index + "]"%>'/>> 
								        </td>	
							         	<td class="table1title" nowrap>�ֻ�:</td>
									    <td class="table1text" nowrap>
								       		<input type="text" name="handset" maxlength="20" size="20" class='<%=strStyle%>' onchange="tdchange(this.form)" onfocus="this.select()"
								             <%=read%> value=<bean:write name="UserForm" property='<%="handset[" + index + "]"%>'/>> 
								        </td>	
							         	<td class="table1title" nowrap>BP��:</td>
									    <td class="table1text" nowrap>
								       		<input type="text" name="bp" maxlength="20" size="20" class='<%=strStyle%>' onchange="tdchange(this.form)" onfocus="this.select()"
								             <%=read%> value=<bean:write name="UserForm" property='<%="bp[" + index + "]"%>'/>> 
								        </td>	
           					         </tr>  
           					         
									  <tr>
								        <td class="table1title" nowrap> �û�״̬:</td>
										<td class="table1text" nowrap>
									    <input type="text" name="ustateshow"  size="20"  onclick="<%=strUst%>" class='<%=strStyle%>'
										     readonly="true"  value=<bean:write name="UserForm" property='<%="ustateshow[" + index + "]"%>'/>> 				            
									    <input type="hidden" name="ustateid"  
										     value=<bean:write name="UserForm" property='<%="ustateid[" + index + "]"%>'/>> 				            
										</td>  
										
								        <td class="table1title" nowrap>��ַ:</td>
										<td class="table1text" nowrap colspan="3">
									    <input type="text" name="address"  maxlength="100" size="50"  class='<%=strStyle%>'  onfocus="this.select()"
										  <%=read%>  value=<bean:write name="UserForm" property='<%="address[" + index + "]"%>'/>> 				
									</tr>  
           					         
									  <tr>
									  	<td class="table1title" nowrap>����:</td>
									    <td class="table1text" nowrap>
									        <input type="text" name="levelshow"  size="20"  onclick="<%=strLev%>" class='<%=strStyle%>'  onfocus="this.select()"
									           readonly="true"  value=<bean:write name="UserForm" property='<%="levelshow[" + index + "]"%>'/>> 				            
								            <input type="hidden" name="levelid"
									            value=<bean:write name="UserForm" property='<%="levelid[" + index + "]"%>'/>> 				            
									    </td>
							        	<logic:equal name="UserForm" property="page" value="2">		    	
									    	<td class="table1title" nowrap> Ӧ��ϵͳ:</td>
										    <td class="table1text" nowrap>
										        <input type="text" name="appsysshow"  size="20" onclick="<%=strApp%>" class="n1right"
											        readonly="true" value=<bean:write name="UserForm" property="appsysshow"/>> 				            
										        <input type="hidden" name="appsysid"
											        value=<bean:write name="UserForm" property="appsysid"/>>
										     </td> 
									 	</logic:equal>	
										</tr>	
							    		<logic:equal name="UserForm" property="page" value="2">		    	
								        	<logic:present name="UserForm" property="mpurviewid" >
									        	<tr><td colspan="6" nowrap>&nbsp;
										        	<logic:iterate id="mpurviewshow" name="UserForm" property="mpurviewshow" indexId="indexrow">
										        		<logic:equal name="indexrow" value="8">										
										    				&nbsp;&nbsp;</td></tr>    								
									    					<tr><td  colspan="6" nowrap>&nbsp;
														</logic:equal>
												        &nbsp;&nbsp;<%=mpurviewshow%>
												        <html:multibox property="mpurviewid" onclick="tdchange(this.form)"> <%= "" + indexrow%> </html:multibox>						
													</logic:iterate>     
									        	</td></tr>
											</logic:present>
								    	</logic:equal>
							    </table>
   						       <br>    
   						       <center>
							   <html:button property="butcommit" onclick ="submitform(this.form,'commit')">�ύ</html:button>
							   </center>
							   </td>
							   <td><input type="button" name="buthidden" value="����" onclick="hiddenthisform(this.form)"></td>
					    	</logic:equal>
					   	 </tr>
					  </logic:iterate>
					</logic:present>
					 
		    		<logic:equal name="UserForm" property="page" value="1">
						<logic:equal name="UserForm" property="currrowshow" value="-1">
					    	<td colspan="8" align="center">
   						       <br>    
					    		<table align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
									<tr> 	
					    			 	<td class="table1title" nowrap>�û�id:</td>
									    <td class="table1text" nowrap>
									     <input type="text" name="userid" maxlength="10" size="20" class="n2centernone"
									          readonly="true" value=''>
 						        		 <input type="hidden" name="rowstate" value='3'>
									     </td>
								        
					    			  	<td class="table1title" nowrap>����:</td>
									    <td class="table1text" nowrap>
							        		<input type="text" name="namec"  maxlength="20" size="20" class='n1right' onchange="tdchange(this.form)" onfocus="this.select()"
								               value=''> 				            
								         </td>
								         
					    			  	<td class="table1title" nowrap> ְ��:</td>
									    <td class="table1text" nowrap>
							        		<input type="text" name="jobshow"   size="20" class='n1right' onclick="selectfields(this.form,<bean:write name='UserForm' property='count'/>,'job','02','')"
								               readonly="true" value='��ְ��'> 				            
								            <input type="hidden" name="jobid" 
									            value='99'> 				            
								         </td>
								    </tr>
									<tr>
										<td class="table1title" nowrap> �Ա�:</td>
									    <td class="table1text" nowrap>
									        <input type="text" name="gendershow"  size="20" class='n1right'  onclick="selectfields(this.form,<bean:write name='UserForm' property='count'/>,'gender','01','')"
									          readonly="true"   value='��'> 				            
									        <input type="hidden" name="genderid"  
									            value='1'> 				            
									    </td>
									    <td class="table1title" nowrap> ����:</td>
										    <td class="table1text" nowrap>
								        		<input type="text" name="deptshow"  size="20" class='n1right'  onclick="selectfields(this.form,<bean:write name='UserForm' property='count'/>,'dept','08','')"
									              readonly="true" value=<bean:write name="UserForm" property="nowdeptshow"/>> 				            
									            <input type="hidden" name="deptid"  
										            value=<bean:write name="UserForm" property="nowdeptid"/>> 				            
								         </td>	
								         <td class="table1title" nowrap> ����:</td>
									     <td class="table1text" nowrap>
								       	    <input type="text" name="birthday"  maxlength="10" size="20" onchange="tdchange(this.form)" class='n1right'  onfocus="this.select()"
								             value=''> 
								         </td>
									</tr>  
									<tr>  
							         	<td class="table1title" nowrap>�绰:</td>
									    <td class="table1text" nowrap>
								       		<input type="text" name="tel" maxlength="20" size="20" class='n1right' onchange="tdchange(this.form)"  onfocus="this.select()"
								              value=''> 
								        </td>	
							         	<td class="table1title" nowrap>�ֻ�:</td>
									    <td class="table1text" nowrap>
								       		<input type="text" name="handset" maxlength="20" size="20" class='n1right' onchange="tdchange(this.form)"  onfocus="this.select()"
								              value=''> 
								        </td>	
							         	<td class="table1title" nowrap>BP��:</td>
									    <td class="table1text" nowrap>
								       		<input type="text" name="bp" maxlength="20" size="20" class='n1right' onchange="tdchange(this.form)"   onfocus="this.select()"
								              value=''> 
								        </td>	
           					        </tr>
									<tr>
								        <td class="table1title" nowrap> �û�״̬:</td>
										<td class="table1text" nowrap>
										    <input type="text" name="ustateshow"  size="20" class='n1right'  onclick="selectfields(this.form,<bean:write name='UserForm' property='count'/>,'ustate','09','')"
											     readonly="true"  value='����'> 				            
										    <input type="hidden" name="ustateid" 
											     value='1'> 				            
									
									    <td class="table1title" nowrap>��ַ:</td>
										<td class="table1text" nowrap colspan="3">
										    <input type="text" name="address"  maxlength="100" size="50"  class='n1right' onchange="tdchange(this.form)"  onfocus="this.select()"
											   value=''> 				
										 </td>  
									</tr>
									<tr>
									  	<td class="table1title" nowrap>����:</td>
									    <td class="table1text" nowrap>
									        <input type="text" name="levelshow"  size="20" class='n1right'  onclick="selectfields(this.form,<bean:write name='UserForm' property='count'/>,'level','13','')"
									           readonly="true"  value='0��'> 				            
								            <input type="hidden" name="levelid"  
									           value='0'> 				            
									    </td>
									</tr>	
							    </table>
							    <br><center>
							    <html:button property="butcommit" onclick ="submitform(this.form,'commit')">�ύ</html:button>
							    <html:button property="butdel" onclick ="delform(this.form)">ɾ��</html:button>
							    </center>
							    <br>
							 </td>
				    	</logic:equal>  
			    	</logic:equal>
		 		</tbody>	
			</table>
			
			<br>
			
		    <table align="center" width="50%">
		      <tr align="center"> 
	    		<td><html:button property="butesc" onclick ="escform(this.form,'return')">����</html:button></td> 
				<td><html:button property="butexit" onclick ="exitform(this.form,'exit')" >�˳�</html:button></td> 
		      </tr>
			</table>	
		</html:form>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			
		<script language="JavaScript">	
			//��ǰ��
			if((document.getElementById("currrowshow").value==-1)&&(document.getElementById("page").value==1)){
				document.getElementById("currrow").value=document.getElementById("count").value;
				document.getElementById("currrowshow").value=document.getElementById("count").value;
				document.getElementById("currrowshowold").value="-1";
			}
			
			//ѡ����뺯��
			function selectfields(thisform,currrow,currcol,code,cshow){
				var select;
				if(selectone(thisform,currrow,currcol,code,cshow)=="1"){
					if(thisform.page.value=="2"){
						thisform.flag.value="circle";
						thisform.submit();			
					}
				}
 			}
			
			
			//���ص���
			function hiddenthisform(thisform){
				
				thisform.currrowshowold.value=thisform.currrowshow.value;
				thisform.currrowshow.value="-1";
				
				if(saveChange(thisform,"hidden") == 0){
					thisform.flag.value="hiddenexic";
					thisform.submit();
				}
			}
			
			//�༭����
			function changethisform(thiscell){
				var currrow=thiscell.parentNode.parentNode.rowIndex - 1;				
				thiscell.form.currrowshowold.value=thiscell.form.currrowshow.value;
				thiscell.form.currrowshow.value=currrow;
				thiscell.form.currrow.value=thiscell.form.currrowshow.value;

				if(saveChange(thiscell.form,"change") == 0){
					thiscell.form.flag.value="changeexic";
					thiscell.form.submit();
				}
			}
									
			//���غ���
			function escform(thisform,flag){
				thisform.currrowshowold.value=thisform.currrowshow.value;
				thisform.currrowshow.value="-1";
				if(saveChange(thisform,"commit") == 0){
					window.open('<bean:write name="UserForm" property="returnurl"/>',"_self","");
				}
			}
						
			//ɾ������
			function delform(thisform){
				if (confirm("�Ƿ������ɾ�����У�")){
					thisform.rowstate.value="5";
					thisform.submit();
				} 
			}
			
			//�˳�����
			function exitform(thisform,aflag){
				if(saveChange(thisform,"exit") == 0){
					window.open("<bean:message key='Home.page'/>","_self","");
				}
			}
			
			//У���ύ����
			function validateSubmit(form) {
	        	<logic:equal name="UserForm" property="page" value="1">		    	
					if (validField(form,1)&&validField(form,4)) {
						form.submit();
					}
			 	</logic:equal>	
	        	<logic:equal name="UserForm" property="page" value="2">		    	
						form.submit();
			 	</logic:equal>	
				return;
			}
			
			//��¼У�����(����,��ʾ����)
			function required(){ 
				this.aa = new Array("namec", "�û����� ����Ϊ�գ�");
			}
			
			//����У�����(����,��ʾ����)
			function dates () { 
				this.aa = new Array("birthday", "���� ��ʽ����ȷ��");
			} 
			
		</script>
	</BODY>
</html:html>
