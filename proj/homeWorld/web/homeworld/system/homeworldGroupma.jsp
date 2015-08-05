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
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
			<html:form action="/homeworldGroupma" >
						
			<html:hidden property="key" />
			<html:hidden property="count" />
			
			<html:hidden property="userid" />
			<html:hidden property="usershow" />
			<html:hidden property="datenow" />
			
			<html:hidden property="flag" />
			<html:hidden property="currrow"/>
			<html:hidden property="currcolumn"/>
			
			<html:hidden property="currrowshow"/>
			<html:hidden property="currrowold"/>
			
			<center><H2>			
				<html:errors property="testActionErrors"/>
				 �������
		    </H2></center>
		    
		    
			<table  align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
				<logic:present name="homeworldGroupmaForm" property="groupid" >
					<thead>
						<tr align="center">				                                        
							<th  class="table2titletd" nowrap>��ID  </th>
							<th  class="table2titletd" nowrap>������  </th>
							<th  class="table2titletd" nowrap>������  </th>
						</tr>
						</thead>
					<tbody id="DynData">
					  <logic:iterate id="groupid" name="homeworldGroupmaForm" property="groupid" indexId="index">
					    <tr align="center">
					    	<logic:notEqual name="homeworldGroupmaForm" property="currrowshow" value='<%="" + index %>'>		 
						    	<td class="table2textrighttd" nowrap> 
						    	<input type="text" name="groupid" size="10" class="n2centernone"
						             readonly="true"
						            value="<bean:write name='homeworldGroupmaForm' property='<%="groupid[" + index + "]"%>'/>">
						            
					             	<input type="hidden" name="rowid" value="<bean:write name='homeworldGroupmaForm' property='<%="rowid[" + index + "]"%>'/>"> 
									<input type="hidden" name="rowstate" value='0'> 
									
									<input type="hidden" name="operatorid"  
						            value="<bean:write name='homeworldGroupmaForm' property='<%="operatorid[" + index + "]"%>'/>"> 
						            
									<input type="hidden" name="operatorshow"  
						            value="<bean:write name='homeworldGroupmaForm' property='<%="operatorshow[" + index + "]"%>'/>"> 
						            
									<input type="hidden" name="operdate"  
						            value="<bean:write name='homeworldGroupmaForm' property='<%="operdate[" + index + "]"%>'/>"> 
						        </td>
						        
						         <td class="table2textrighttd" nowrap> <input type="text" name="groupnam"  size="30" class="n2centernone"
						            readonly="true"
						            value="<bean:write name='homeworldGroupmaForm' property='<%="groupnam[" + index + "]"%>'/>"> 
						        </td>
						        
						         <td class="table2textrighttd" nowrap> <input type="text" name="groupdes"  size="30" class="n2centernone"
						            readonly="true"
						            value="<bean:write name='homeworldGroupmaForm' property='<%="groupdes[" + index + "]"%>'/>"> 				            
						        </td>
						        
								<logic:notEqual name="groupid" value="">
									<td><input type="button" name="butthis" value="�༭" onclick="changethisform(this)"></td> 
						    	</logic:notEqual>	
					    	</logic:notEqual>	
					    	    	
					    	<logic:equal name="homeworldGroupmaForm" property="currrowshow" value='<%="" + index %>'>	
					    	 <br>	 					
					    		<td colspan="3">
								    <table width="50%" cellspacing="0" cellpadding="1" class="table1border">
									  <tr>
									  	<td class="table1title" nowrap>��ID:</td>
									    <td class="table1text" nowrap>
								        	<input type="text" name="groupid" size="15"  maxlength="10" onchange="tdchange(this.form)" onfocus="this.select()" class="n1right"
								        		value="<bean:write name='homeworldGroupmaForm' property='<%="groupid[" + index + "]"%>'/>" > 								        
								        		<input type="hidden" name="rowid" value="<bean:write name='homeworldGroupmaForm' property='<%="rowid[" + index + "]"%>'/>"> 
								        		<input type="hidden" name="rowstate" value='0'>
								        </td>
								        
									  	<td class="table1title" nowrap> ������:</td>
									    <td class="table1text" nowrap>
									        	<input type="text" name="operatorshow" size="15"  class="n1right" readonly="true"
									            value="<bean:write name='homeworldGroupmaForm' property='<%="operatorshow[" + index + "]"%>'/>" > 				            
									        	<input type="hidden" name="operatorid" value="<bean:write name='homeworldGroupmaForm' property='<%="operatorid[" + index + "]"%>'/>" > 				            
									    </td>
									    
										<td class="table1title" nowrap> ��������:</td>
										<td class="table1text" nowrap>
								        		<input type="text" name="operdate" size="15" class="n1right" readonly="true"
									            value="<bean:write name='homeworldGroupmaForm' property='<%="operdate[" + index + "]"%>'/>" > 				            
									    </td>
									    
									  </tr>								        
								      <tr>  
									  	<td class="table1title" nowrap> ������:</td>
									    <td class="table1text" nowrap colspan=5>
									     <input type="text" name="groupnam" maxlength="80" size="75"  onchange="tdchange(this.form)" class="n1right" onfocus="this.select()" 
									            value="<bean:write name='homeworldGroupmaForm' property='<%="groupnam[" + index + "]"%>'/>"> 				               
									     </td>
									  </tr>								        
								      <tr> 
									  	<td class="table1title" nowrap> ������:</td>
									    <td class="table1text" nowrap colspan=5>
								       			<input type="text" name="groupdes" maxlength="80" size="75" onchange="tdchange(this.form)" onfocus="this.select()" class="n1right"
								            value="<bean:write name='homeworldGroupmaForm' property='<%="groupdes[" + index + "]"%>'/>" > 
								        </td>
									   </tr>
									<br>
							        	
							        </table>
							        <br>
									<input type="button" name="butcommit" value="�ύ" onclick="submitform(this.form,'commit')">
									<input type="button" name="butdel" value="ɾ��" onclick ="delform(this.form)">									
					    		</td>
					    		
								<td><input type="button" name="buthidden" value="����" onclick="hiddenthisform(this.form)"></td> 
								
					    	</logic:equal>
					    	
					   	 </tr>
					  </logic:iterate>
					</logic:present>
					
					<logic:equal name="homeworldGroupmaForm" property="currrowshow" value="-1">		 					
						<tr align="center">
							<td colspan="6">
								<br>
								    <table width="50%" cellspacing="0" cellpadding="1" class="table1border">
									  <tr>
										  	<td class="table1title" nowrap>��ID:</td>
										    <td class="table1text" nowrap>
									        	<input type="text" name="groupid" size="15" maxlength="10" onchange="tdchange(this.form)" onfocus="this.select()" class="n1right" value=''> 								        
									        	<input type="hidden" name="rowid" value=""> 
								        		<input type="hidden" name="rowstate" value='3'>
									        </td>
										  	<td class="table1title" nowrap> ������:</td>
										    <td class="table1text" nowrap>
										        	<input type="text" name="operatorshow" size="15"  readonly="true"  class="n1right"  value="<bean:write name='homeworldGroupmaForm' property='usershow'/>"> 				            
										        	<input type="hidden" name="operatorid" value="<bean:write name='homeworldGroupmaForm' property='userid'/>"> 				            
										    </td>
										  	<td class="table1title" nowrap> ��������:</td>
										    <td class="table1text" nowrap>
								        		<input type="text" name="operdate" size="15" readonly="true" class="n1right" value="<bean:write name='homeworldGroupmaForm' property='datenow'/>"> 				            
									        </td>
									  </tr>
								      <tr> 									         
										  	<td class="table1title" nowrap> ������:</td>
										    <td class="table1text" nowrap colspan=5>
										     <input type="text" name="groupnam" maxlength="80" size="75"  onchange="tdchange(this.form)" onfocus="this.select()" class="n1right"   value=''> 				               
										     </td>
									  </tr>
								      <tr> 	 
										  	<td class="table1title" nowrap> ������:</td>
										    <td class="table1text" nowrap colspan=5>
									       			<input type="text" name="groupdes" maxlength="80" size="75" onchange="tdchange(this.form)" onfocus="this.select()" class="n1right" value=''> 
									        </td>
									  </tr>

							        </table>
							        
							        <br>
								<input type="button" name="butcommit" value="�ύ" onclick="submitform(this.form,'commit')">
							</td>
						</tr>
				  	</logic:equal>
				  	
					
		 		</tbody>	
			</table>
			
		</html:form>
					
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			

		<script language="JavaScript">	
			//��ǰ��
			if(document.getElementById("currrowshow").value==-1){
				document.getElementById("currrow").value=document.getElementById("count").value;
				//document.getElementById("currrowshow").value=document.getElementById("count").value;
			}

			//���ص���
			function hiddenthisform(thisform){
				thisform.currrowold.value=thisform.currrowshow.value;
				thisform.currrowshow.value="-1";
				thisform.currrow.value=thisform.count.value;
				
				//savechange
				if(saveChange(thisform,"hidden") !=1 ){
					thisform.flag.value="hiddenxx";
					thisform.submit();
				}
			}
				
			//�༭����
			function changethisform(thiscell){
				var currrow=thiscell.parentNode.parentNode.rowIndex - 1;
				thiscell.form.currrowold.value=thiscell.form.currrowshow.value;
				thiscell.form.currrowshow.value=currrow;
				thiscell.form.currrow.value=currrow;				
				
				//savechange
				if(saveChange(thiscell.form,"changethis") !=1){
					thiscell.form.flag.value="changethisxx";
					thiscell.form.submit();
				}
			}
						
			//ɾ������
			function delform(thisform){
				if (confirm("�Ƿ������ɾ�����У�")){
					thisform.currrowold.value=thisform.currrowshow.value;
					thisform.currrowshow.value="-1";
					thisform.currrow.value="-1";
					
					thisform.flag.value="del";
					thisform.submit();
				} 
			}
			
			//У���ύ����
			function validateSubmit(form) {
				if ( validField(form,1) &&validRepeat(form)){
					form.submit();
				}				
				return;
			}
			
			//��¼У�����(����,��ʾ����)
			function required () { 
				this.aa = new Array("groupid", "����id ����Ϊ�գ�");
				this.ab = new Array("groupnam", "������ ����Ϊ�գ�");
			}
			 
			//�ظ�У�����(������,��ʾ����)
			function repeat () { 
				this.aa = new Array("groupid", "����id �ظ���");
				this.ab = new Array("groupnam", "������ �ظ���");
			}
		</script>
	</BODY>
</html:html>
