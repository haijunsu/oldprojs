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
		<TITLE> �������</TITLE>
		<%
			response.setHeader("Pragma","No-cache"); 
			response.setHeader("Cache-Control","no-cache"); 
			response.setDateHeader("Expires", 0); 
		%>
	</HEAD>
	<BODY>
	<html:form action="/Createtable" >
		<html:hidden property="key" />
		<html:hidden property="count" />
		<html:hidden property="flag" />
		<html:hidden property="currrow"/>
		<html:hidden property="currcolumn"/>
		<html:hidden property="page" />
		<html:hidden property="currrowshow"/>
		<html:hidden property="currrowshowold"/>
		<logic:iterate name="CreatetableForm" id="ftypeeshow" property="ftypeeshow" indexId="indexkk">
			<input type="hidden" name="ftypeeshow" value='<%=ftypeeshow%>'>
			
		</logic:iterate>
		<logic:iterate name="CreatetableForm" id="ftypeeid" property="ftypeeid" indexId="indexkh">
			<input type="hidden" name="ftypeeid" value='<%=ftypeeid%>'>
			
		</logic:iterate>
		<center><H2>			
		<html:errors property="testActionErrors"/>
			 �������
		</H2></center>
		<%
		   	String strqwhere = "TABLE";
		   	int nseq = 1;
		%>	
		<table  align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
				<thead>
				<tr align="center">
					<logic:equal name="CreatetableForm" property="page" value="2">
						<th  class="table2titletd">���</th>
						<th  class="table2titletd">����</th>
						<th  class="table2titletd">�ֶ�</th>
						<th  class="table2titletd">����</th>
						<th  class="table2titletd">����</th>
						<th  class="table2titletd">С��λ</th>
						<th  class="table2titletd" nowrap>Ĭ��ֵ</th>
						<th  class="table2titletd" nowrap>����˵��</th>
					</logic:equal>			
					<logic:equal name="CreatetableForm" property="page" value="1">
						<th  class="table2titletd">����</th>
						<th  class="table2titletd">����������</th>
					</logic:equal>			
				</tr>
				</thead>
			<tbody id="DynData">
					<logic:present name="CreatetableForm" property="queryid" >
					  <logic:iterate id="queryid" name="CreatetableForm" property="queryid" indexId="index">
					    <%nseq = nseq + 1;%>
					    <tr align="center">
					       	<logic:notEqual name="CreatetableForm" property="currrowshow" value='<%="" + index %>'>		 				
								<logic:equal name="CreatetableForm" property="page" value='1'>	 
							    	<td class="table2textrighttd" nowrap> <input type="text" name="queryid" maxlength="16" size="8" class="n2centernone"
							             readonly="true"
							            value=<bean:write name="CreatetableForm" property='<%="queryid[" + index + "]"%>'/>> 
							            <input type="hidden" name="rowstate" value='0'> 
									</td>
							         <td class="table2textrighttd" nowrap> <input type="text" name="qnamec"  size="20" class="n2centernone"
							            readonly="true"
							            value=<bean:write name="CreatetableForm" property='<%="qnamec[" + index + "]"%>'/>> 
							        </td>
						        </logic:equal>
						        
						        <logic:equal name="CreatetableForm" property="page" value='2'>
						         <td class="table2textrighttd" nowrap> <input type="text" name="seq"  size="2" class="n2centernone"
						            readonly="true"
						            value=<bean:write name="CreatetableForm" property='<%="seq[" + index + "]"%>'/>> 
						            <input type="hidden" name="rowstate" value='0'> 
						            <input type="hidden" name="queryid" value=<bean:write name="CreatetableForm" property='<%="queryid[" + index + "]"%>'/>> 				            
						        </td>
						         <td class="table2textrighttd" nowrap> <input type="text" name="qkattri"  size="2" class="n2centernone"
						            readonly="true"
						            value=<bean:write name="CreatetableForm" property='<%="qkattri[" + index + "]"%>'/>> 				            
						        </td>
						         <td class="table2textrighttd" nowrap> <input type="text" name="fieldid"  size="20" class="n2centernone"
						            readonly="true"
						            value=<bean:write name="CreatetableForm" property='<%="fieldid[" + index + "]"%>'/>> 				            
						        </td>
						         <td class="table2textrighttd" nowrap> <input type="text" name="ftypeshow"  size="10" class="n2centernone"
						            readonly="true"
						            value=<bean:write name="CreatetableForm" property='<%="ftypeshow[" + index + "]"%>'/>> 	
						            <input type="hidden" name="ftypeid" value=<bean:write name="CreatetableForm" property='<%="ftypeid[" + index + "]"%>'/>> 
						        </td>
						        <td class="table2textrighttd" nowrap> <input type="text" name="flength"  size="2" class="n2centernone"
						            readonly="true"
						            value=<bean:write name="CreatetableForm" property='<%="flength[" + index + "]"%>'/>> 				            
						        </td>
						        <td class="table2textrighttd" nowrap> <input type="text" name="fdigits"  size="2" class="n2centernone"
						            readonly="true"
						            value=<bean:write name="CreatetableForm" property='<%="fdigits[" + index + "]"%>'/>> 				            
						        </td>
						        <td class="table2textrighttd" nowrap> <input type="text" name="qdefault"  size="10" class="n2centernone"
						            readonly="true"
						            value=<bean:write name="CreatetableForm" property='<%="qdefault[" + index + "]"%>'/>> 				            
						        </td>
						        <td class="table2textrighttd" nowrap> <input type="text" name="fheaderc"  size="10" class="n2centernone"
						            readonly="true"
						            value=<bean:write name="CreatetableForm" property='<%="fheaderc[" + index + "]"%>'/>> 				            
						        </td>
						   	</logic:equal>
						   		<logic:notEqual name="queryid" value="">
									<td><input type="button" name="butthis" value="�༭" onclick="changethisform(this)"></td> 
						    	</logic:notEqual>	
						  </logic:notEqual>
						  
						  
						  <logic:equal name="CreatetableForm" property="currrowshow" value='<%="" + index %>'>
						 	<logic:equal name="CreatetableForm" property="page" value="1">
								<td colspan="2">
							</logic:equal>
							<logic:equal name="CreatetableForm" property="page" value="2">
								<td colspan="8">
							</logic:equal>
							   <input type="hidden" name="id" value=<bean:write name="CreatetableForm" property='<%="queryid[" + index + "]"%>'/>>
							    <table width="50%" cellspacing="0" cellpadding="1" class="table1border">
								<logic:equal name="CreatetableForm" property="page" value='1'>	  
								  <tr>
								  		<td class="table1title" nowrap>����:</td>
									    <td class="table1text" nowrap>
								        	<input type="text" name="queryid" maxlength="20" size="20" class="n2centernone"
								        		readonly="true"
								        		value=<bean:write name="CreatetableForm" property='<%="queryid[" + index + "]"%>'/> > 								        
											<logic:equal name="CreatetableForm" property="currrowshow" value='-1' >
								        		<input type="hidden" name="rowstate" value='0'>
											</logic:equal> 
											<logic:notEqual name="CreatetableForm" property="currrowshow" value='-1'>
								        		<input type="hidden" name="rowstate" value=<bean:write name="CreatetableForm" property='<%="rowstate[" + index + "]"%>'/>>
											</logic:notEqual> 								
									     </td>
									     <td class="table1title" nowrap> ��������:</td>
									    <td class="table1text" nowrap>
								       			<input type="text" name="qnamec" maxlength="30" size="20" onchange="tdchange(this.form)" onfocus="this.select()" class="n1left"
								            value=<bean:write name="CreatetableForm" property='<%="qnamec[" + index + "]"%>'/> > 
								        </td>
									 </tr>
									 <tr>
									 	<td class="table1title" nowrap> ���:</td>
									    <td class="table1text" nowrap>
								       			<input type="text" name="qwhere" maxlength="100" size="15" onchange="tdchange(this.form)" onfocus="this.select()" class="n2centernone"
								             readonly="true"
								            value=<bean:write name="CreatetableForm" property='<%="qwhere[" + index + "]"%>'/> > 
								        </td>
									 	<td class="table1title" nowrap> ��Ӣ����:</td>
									    <td class="table1text" nowrap>
								       			<input type="text" name="qnamee" maxlength="30" size="20" onchange="tdchange(this.form)" onfocus="this.select()" class="n1left"
								            value=<bean:write name="CreatetableForm" property='<%="qnamee[" + index + "]"%>'/> > 
								        </td>
								        
									   </tr> 
								</logic:equal>
								<%
									String stronclick = "selectvalue(this.form," + index+",'ftype','ftypee')";
								%>
								<logic:equal name="CreatetableForm" property="page" value='2'> 
								       <tr> 
										  	<td class="table1title" nowrap> ���:</td>
										    <td class="table1text" nowrap>
								        		<input type="text" name="seq" maxlength="4" size="10" onchange="tdchange(this.form)" onfocus="this.select()" class="n1right"
									            value=<bean:write name="CreatetableForm" property='<%="seq[" + index + "]"%>'/> > 				            
									        	<input type="hidden" name="rowstate" value='0'>
									        	<input type="hidden" name="queryid" value=<bean:write name="CreatetableForm" property='<%="queryid[" + index + "]"%>'/>>
									        	<input type="hidden" name="seqhid" value=<bean:write name="CreatetableForm" property='<%="seq[" + index + "]"%>'/>>
									        	
									     	</td>
								      	  	<td class="table1title" nowrap> ����:</td>
										    <td class="table1text" nowrap>
										    	<input type="text" name="qkattri" maxlength="1" size="4" onchange="tdchange(this.form)" onfocus="this.select()" class="n1right"
									            value=<bean:write name="CreatetableForm" property='<%="qkattri[" + index + "]"%>'/> > 				            
									         </td>
										     <td class="table1title" nowrap> �ֶ�:</td>
										    <td class="table1text" nowrap>
								        		<input type="text" name="fieldid" maxlength="20" size="15" onchange="tdchange(this.form)" onfocus="this.select()" class="n1left"
									              value=<bean:write name="CreatetableForm" property='<%="fieldid[" + index + "]"%>'/> > 
									            <html:button property="butlr" onclick ="selects(this)">����</html:button>				            
									        	<input type="hidden" name="rowstate" value='0'>
									     	</td>
									     	<td class="table1title" nowrap> ����:</td>
										    <td class="table1text" nowrap>
									        	<input type="text" name="ftypeid" maxlength="20" size="15" onchange="tdchange(this.form)" onfocus="this.select()" class="n1left"
									            readonly=true
									            value=<bean:write name="CreatetableForm" property='<%="ftypeid[" + index + "]"%>'/> >
									            <input type="hidden" name="ftypeshow" value=<bean:write name="CreatetableForm" property='<%="ftypeshow[" + index + "]"%>'/>>  				            
										     </td>
									     </tr>
									     <tr>
									     	<td class="table1title" nowrap>Ĭ��ֵ:</td>
										    <td class="table1text" nowrap>
									        	<input type="text" name="qdefault" maxlength="50" size="10" onchange="tdchange(this.form)" onfocus="this.select()" class="n1left"
									             value=<bean:write name="CreatetableForm" property='<%="qdefault[" + index + "]"%>'/> > 				            
										     </td>
										     <td class="table1title" nowrap> ����:</td>
										    <td class="table1text" nowrap>
									        	<input type="text" name="flength" maxlength="4" size="4" onchange="tdchange(this.form)" onfocus="this.select()" class="n1right"
									            readonly=true
									            value=<bean:write name="CreatetableForm" property='<%="flength[" + index + "]"%>'/> > 				            
										     </td>
								       		<td class="table1title" nowrap> С��λ:</td>
										    <td class="table1text" nowrap>
									        	<input type="text" name="fdigits" maxlength="4" size="15" onchange="tdchange(this.form)" onfocus="this.select()" class="n1right"
									            readonly=true
									            value=<bean:write name="CreatetableForm" property='<%="fdigits[" + index + "]"%>'/> > 				            
										     </td>
										     <td class="table1title" nowrap> ������:</td>
										    <td colspan=2 class="table1text" nowrap>
									        	<input type="text" name="cclass" maxlength="30" size="15" onchange="tdchange(this.form)" onfocus="this.select()" class="n1left"
									            readonly=true
									            value=<bean:write name="CreatetableForm" property='<%="cclass[" + index + "]"%>'/> > 				            
										     </td>
										    </tr>
							        		<tr>
							        		 <td class="table1title" nowrap> ����˵��:</td>
										    <td colspan=4 class="table1text" nowrap>
									        	<input type="text" name="fheaderc" maxlength="30" size="32" onchange="tdchange(this.form)" onfocus="this.select()" class="n1left"
									            value=<bean:write name="CreatetableForm" property='<%="fheaderc[" + index + "]"%>'/> > 				            
										     </td>
						       				 <td class="table1title" nowrap> Ӣ��˵��:</td>
										    <td colspan=2 class="table1text" nowrap>
									        	<input type="text" name="fheadere" maxlength="30" size="24" onchange="tdchange(this.form)" onfocus="this.select()" class="n1left"
									            value=<bean:write name="CreatetableForm" property='<%="fheadere[" + index + "]"%>'/> > 				            
										     </td>
						        			
								        		
								       	</tr>
							        	
							        	
							        	
							        </logic:equal>
							        	
							       </table>
							       <br>
								<input type="button" name="butcommit" value="�ύ" onclick="submitform(this.form,'commit')">
								<logic:notEqual name="CreatetableForm" property='<%="rowstate[" + index + "]"%>' value="4">	
									<input type="button" name="butdel" value="ɾ��" onclick ="delform(this.form)">
								</logic:notEqual>
					    	</td>
					    	
								<logic:notEqual  name="CreatetableForm" property='<%="rowstate[" + index + "]"%>' value="3">
									<logic:notEqual name="CreatetableForm" property='<%="rowstate[" + index + "]"%>' value="4">
										<td><input type="button" name="buthidden" value="����" onclick="hiddenthisform(this.form)"></td> 
									</logic:notEqual>
								</logic:notEqual>
					    </logic:equal>
						  
						  
						  						  
						  
							
							
					
					    	<logic:equal name="CreatetableForm" property="page" value="1">		 				    		
								<logic:notEqual name="queryid" value="">
									<td><input type="button" name="butnext" value="�¼�" onclick="changenextform(this)"></td> 
								</logic:notEqual>
							</logic:equal>
				
					   	 </tr>
					  </logic:iterate>
					</logic:present>
					
					
					
					<logic:equal name="CreatetableForm" property="currrowshow" value="-1">		 					
						<tr align="center">
						<logic:equal name="CreatetableForm" property="page" value="1">
							<td colspan="2">
						</logic:equal>
						<logic:equal name="CreatetableForm" property="page" value="2">
							<td colspan="8">
						</logic:equal>
							<table width="50%" cellspacing="0" cellpadding="1" class="table1border">
							
							<logic:notEqual name="CreatetableForm" property="page" value='2'>	  
							  <tr>
							  	<td class="table1title" nowrap>����:</td>
							    <td class="table1text" nowrap>
						       	<input type="text" name="queryid" maxlength="20" size="15"  onfocus="this.select()" class="n1left" onchange="toup(this.form)"
						      		value='' > 								        
								<input type="hidden" name="rowstate" value='3'>
								</td>
							  	<td class="table1title" nowrap> ��������:</td>
							    <td class="table1text" nowrap>
						     <input type="text" name="qnamec" maxlength="30" size="20"  onfocus="this.select()" class="n1left" onchange="tdchange(this.form)"
						            value=''> 				               
							     </td>
							  </tr>
						    <tr>
						    <td class="table1title" nowrap> ���:</td>
						    <td class="table1text" nowrap>
						       	<input type="text" name="qwhere" maxlength="100" size="15" onchange="tdchange(this.form)" onfocus="this.select()" class="n2centernone"
								             readonly="true"
								            value='TABLE' > 			            
						   </td>
						  	<td class="table1title" nowrap> ��Ӣ����:</td>
						    <td class="table1text" nowrap>
									<input type="text" name="qnamee" maxlength="30" size="20" onchange="tdchange(this.form)" onfocus="this.select()" class="n1left"
							            value='' > 
						    </td>
						  	
						  </tr>
						</logic:notEqual>
						
						
						<logic:equal name="CreatetableForm" property="page" value='2'> 
						    <tr> 
							  	<td class="table1title" nowrap> ���:</td>
							    <td class="table1text" nowrap>
							    
							    
						   		<input type="text" name="seq" maxlength="4" size="10"  onfocus="this.select()" class="n1right" onchange="tdchange(this.form)"
						      		value='<%=nseq%>' > 								        
								<input type="hidden" name="rowstate" value='3'>
						     	</td>
						   	  	<td class="table1title" nowrap> ����:</td>
							    <td class="table1text" nowrap>
							    	<input type="text" name="qkattri" maxlength="1" size="4" onfocus="this.select()" class="n1right" onchange="tdchange(this.form)"
						            value='0'> 
						        </td>
							     <td class="table1title" nowrap> �ֶ�:</td>
							    <td class="table1text" nowrap>
						   		<input type="text" name="fieldid" maxlength="20" size="15" onfocus="this.select()" class="n1left" onchange="tdchange(this.form)"
						            value=''> 
						         <html:button property="butlr" onclick ="selects(this)">����</html:button>
						         </td>
						      	<td class="table1title" nowrap> ����:</td>
							    <td class="table1text" nowrap>
							    	<input type="hidden" name="ftypeshow" 
									            value='CHAR'  onchange="tdchange(this.form)" > 
						        	<input type="text" name="ftypeid" maxlength="20" size="15" class="n1left" onfocus="this.select()" onchange="tdchange(this.form)"
						            readonly=true
						            value=''> 				            
						     </td>
						      
						      </tr>
						      <tr>
						        <td class="table1title" nowrap>Ĭ��ֵ:</td>
							    <td class="table1text" nowrap>
						       	<input type="text" name="qdefault" maxlength="50" size="10" onfocus="this.select()" class="n1left" onchange="tdchange(this.form)"
							           value='' > 				            
							     </td>
								<td class="table1title" nowrap> ����:</td>
							    <td class="table1text" nowrap>
							    	<input type="text" name="flength" maxlength="4" size="4" onfocus="this.select()" class="n1right" onchange="tdchange(this.form)"
							           readonly=true
							           value='' > 				            
							    </td>
								<td class="table1title" nowrap> С��λ:</td>
							    <td class="table1text" nowrap>
							       	<input type="text" name="fdigits" maxlength="4" size="15" onfocus="this.select()" class="n1right" onchange="tdchange(this.form)"
							          readonly=true
							          value='' > 				            
							     </td>
							     <td class="table1title" nowrap> ������:</td>
						    	 <td class="table2textrighttd" nowrap> 
						    	 <input type="text" name="cclass"  maxlength="30" size="15" onfocus="this.select()" class="n1left" onchange="tdchange(this.form)"
						    			 readonly=true
						    			 value='no'> 				            
						    	 </td>
							  </tr>
							  <tr>
							     <td class="table1title" nowrap> ����˵��:</td>
							     <td colspan=4 class="table2textrighttd" nowrap> 
							     <input type="text" name="fheaderc"  maxlength="30" size="32" onfocus="this.select()" class="n1left" onchange="tdchange(this.form)"
						    			value=''> 				            
						    	 </td>
						    	 <td class="table1title" nowrap> Ӣ��˵��:</td>
						    	 <td colspan=2 class="table2textrighttd" nowrap> 
						    	 <input type="text" name="fheadere"  maxlength="30" size="23" onfocus="this.select()" class="n1left" onchange="tdchange(this.form)"
						    			 value=''> 				            
						    	 </td>
						    	
								      		
							</tr>
							<br>
							</logic:equal>
							
							
							
							
							
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
			    <logic:equal name="CreatetableForm" property="page" value="2">		 		      
		    		<td><html:button property="butesc" onclick ="escform(this.form,'return')">����</html:button></td> 
				</logic:equal>
				<logic:equal name="CreatetableForm" property="page" value="2">
					<logic:greaterThan name="CreatetableForm" property="count" value="0">		 		      
		    			<td><html:button property="ctable" onclick ="creatable(this.form)">����</html:button></td> 
					</logic:greaterThan>
				</logic:equal>
				<br>	
				<td><html:button property="butexit" onclick ="exitform(this.form,'exit')">�˳�</html:button></td> 
		
		      </tr>
			</table>	
		
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			
	</html:form>		
	<script language="JavaScript">
		//�ý���	
//		var focusshow=document.getElementById("SalaryUsersForm");
			
		if( document.getElementById("butcommit")!=null ){
			document.getElementById("butcommit").focus();
		}else{
//			focusshow.salaryno[0].focus();
			document.getElementById("DynData").childNodes(0).all("queryid").focus();	
				
		}	
		//��ǰ��
		//��ǰ��
		if(document.getElementById("currrowshow").value==-1){
			document.getElementById("currrow").value=document.getElementById("count").value;
			document.getElementById("currrowshow").value=document.getElementById("count").value;
			document.getElementById("currrowshowold").value="-1";
		}
		
		//rerowstate("queryid");
				
			
			
			//���ص���
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
			
			//�༭����
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
			
				
			
			//�¼�����
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
			
			//������
			function creatable(thisform){
				
				thisform.currrowshowold.value=thisform.currrowshow.value;
				
				if(saveChange(thisform,'crtable') == 0){
					//thisform.key.value=thisform.queryid[currrow].value;
					thisform.flag.value = 'crtable';
					if (confirm("ע�⣡������������Ѵ��ڵ�ͬ��ɾ�������½����Ƿ������")){
						
						thisform.submit();
					}
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
				
				//alert(document.SalaryMenuAllForm.key.value.substring(0,2));	
			
				if(saveChange(thisform,"exit") == 0){
					window.open("<bean:message key='Home.page'/>","_self","");
				}
			}
			
			//У���ύ����
			function validateSubmit(form) {
				if (validField(form,1) && validRepeat(form)
					<logic:equal name="CreatetableForm" property="page" value="2">
					 && validField(form,2)
					</logic:equal>
				)
					{
						form.submit();
					}
					
					return;
			}
			
			//��¼У�����(����,��ʾ����)
			function required () {
				<logic:equal name="CreatetableForm" property="page" value="1"> 
					this.aa = new Array("queryid", "���� ����Ϊ�գ�");
				</logic:equal>
				<logic:equal name="CreatetableForm" property="page" value="2">
					this.aa = new Array("seq","��� ����Ϊ��");
					this.ab = new Array("fieldid","�ֶ� ����Ϊ��");
					this.ac = new Array("ftypeid","���� ����Ϊ��");
				</logic:equal>
			}
			 
			//�ظ�У�����(������,��ʾ����)
			function repeat () {
				<logic:equal name="CreatetableForm" property="page" value="1"> 
					this.aa = new Array("queryid", "���� �ظ���");
				</logic:equal>
				<logic:equal name="CreatetableForm" property="page" value="2">
					this.aa = new Array("seq","��� �ظ�");
					this.ab = new Array("fieldid","�ֶ� �ظ�");
				</logic:equal>
			}
			
			//��ЧУ�����(����,��ʾ����,����)
			function masks () { 
				<logic:equal name="CreatetableForm" property="page" value="2">
					this.aa = new Array("seq", "��� ����Ϊ����","[0-9]$");
					this.ab = new Array("flength", "���� ����Ϊ����","[0-9]$");
					this.ac = new Array("fdigits", "С��λ ����Ϊ����","[0-9]$");
				</logic:equal>
			} 
			//��ЧУ�����(����,��ʾ����,����)
			function selects (thiscell) { 
				var tempfield;
				tempfield=thiscell.form.fieldid;
				if(thiscell.form.fieldid[thiscell.form.currrow.value]!=null)
					tempfield=thiscell.form.fieldid[thiscell.form.currrow.value];
				var ss=tempfield.value;
				selectfield(thiscell,'FIELDS','fieldid like \''+ss.toUpperCase()+'*\'','fieldid`ftypeid`flength`fdigits`fheaderc`fheadere`cclass',thiscell.form.currrow.value);
				//selectfield(thiscell,'LXDW', '1=1' ,'dwbh`c_dwmc`c_sjdw`c_yb`c_dz`asd')
			} 
			//ת����Сд
			function toup(form){
				var tempfield;
				tempfield=form.queryid;
				if(form.queryid[form.currrowshow.value]!=null)
				{
					tempfield=form.queryid[form.currrowshow.value];
					var temp;
					temp = tempfield.value;
					form.queryid[form.currrowshow.value].value = temp.toUpperCase();
				}
				else{
					var temp;
					temp = tempfield.value;
					form.queryid.value = temp.toUpperCase();
				}
				tdchange(form);
			}
			

		</script>	
	</BODY>
</html:html>
