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
				<bean:write name="homeworldUserLifeForm" property='title'/>
		    	<logic:equal name="homeworldUserLifeForm" property="se_up" value="1">		 	    
					����		    
				</logic:equal>
			    <logic:equal name="homeworldUserLifeForm" property="se_up" value="2">		 	    
					��ѯ
				</logic:equal>
		</TITLE>
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
			<html:form action="/homeworldUserLife" >
			
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
		    
		    <logic:present name="homeworldUserLifeForm" property="mpurviewshow">
		    	<logic:iterate id="mpurviewshow" name="homeworldUserLifeForm" property="mpurviewshow" indexId="indexa">					
				    <input type="hidden" name="mpurviewshow" value="<bean:write name='homeworldUserLifeForm' property='<%="mpurviewshow[" + indexa + "]"%>'/>">
				    <input type="hidden" name="mpurviewid"   value="<bean:write name='homeworldUserLifeForm' property='<%="mpurviewid[" + indexa + "]"%>'/>">
				</logic:iterate>
			</logic:present>
				
			<center><H2>			
				<html:errors property="testActionErrors"/>
				
				<bean:write name="homeworldUserLifeForm" property='title'/>
		    	<logic:equal name="homeworldUserLifeForm" property="se_up" value="1">		 	    
					����		    
				</logic:equal>
			    <logic:equal name="homeworldUserLifeForm" property="se_up" value="2">		 	    
					��ѯ
				</logic:equal>
		    </H2></center>
		    
		    
		    
			<center>                    
			<bean:write name="homeworldUserLifeForm" property="show"/>
			��&nbsp;&nbsp;<html:text property="liketext" size="12"/>&nbsp;&nbsp;
			<html:button property="butselect" onclick ="selectform(this.form)">��ѯ</html:button> 
			<br>			
			</center>
		
		<br>	
		<br>	
			<logic:equal name="homeworldUserLifeForm" property="page" value="2">  
				<table  align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
					<logic:present name="homeworldUserLifeForm" property="userid">
						<thead>
							<tr align="center">		
								<th  class="table2titletd" nowrap>�û�id</th>
								<th  class="table2titletd" nowrap>�û�����</th>
								<th  class="table2titletd" nowrap>����������</th>
								<th  class="table2titletd" nowrap>��������ֹ</th>
								<th  class="table2titletd" nowrap>������id</th>
								<th  class="table2titletd" nowrap>����������</th>
							</tr>
						</thead>
						<tbody id="DynData">	
						<logic:iterate id="userid" name="homeworldUserLifeForm" property="userid" indexId="index">					
					     <tr align="center">
					     

					     
					    	<logic:notEqual name="homeworldUserLifeForm" property="currrowshow" value='<%="" + index %>'>		
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="userid"  size="10" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldUserLifeForm' property='<%="userid[" + index + "]"%>'/>" > 
						            <input type="hidden" name="rowstate" value='0'>
						            <input type="hidden" name="hiddenbeg" value="<bean:write name='homeworldUserLifeForm' property='<%="hiddenbeg[" + index + "]"%>'/>" > 
						            <input type="hidden" name="hiddenend" value="<bean:write name='homeworldUserLifeForm' property='<%="hiddenend[" + index + "]"%>'/>" > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="usershow"  size="15" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldUserLifeForm' property='<%="usershow[" + index + "]"%>'/>" > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="lifebeg"  size="10" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldUserLifeForm' property='<%="lifebeg[" + index + "]"%>'/>" > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="lifeend"  size="10" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldUserLifeForm' property='<%="lifeend[" + index + "]"%>'/>" > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="useridgid"  size="10" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldUserLifeForm' property='<%="useridgid[" + index + "]"%>'/>" > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="useridgshow"  size="15" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldUserLifeForm' property='<%="useridgshow[" + index + "]"%>'/>" > 
						        </td>
						        <logic:notEqual name="userid" value="">
									<td><input type="button" name="butthis" value="�༭" onclick="changethisform(this)"></td> 
								</logic:notEqual>
					    	</logic:notEqual>	

							<logic:equal name="homeworldUserLifeForm" property="currrowshow" value='<%="" + index %>'>	
						    	<td colspan="6" align="center">
								    <br>
						    		<table width="50%" cellspacing="0" cellpadding="1" class="table1border">
 									<tr>
					    			  	<td class="table1title" nowrap>�û�����:</td>
									    <td class="table1text" nowrap>
							        		<input type="text" name="usershow"  size="14" class='n2centernone' readonly='true'
								               readonly="true" value="<bean:write name='homeworldUserLifeForm' property='<%="usershow[" + index + "]"%>'/>"> 				            
								            <input type="hidden" name="hiddenbeg" value="<bean:write name='homeworldUserLifeForm' property='<%="hiddenbeg[" + index + "]"%>'/>" > 
								            <input type="hidden" name="hiddenend" value="<bean:write name='homeworldUserLifeForm' property='<%="hiddenend[" + index + "]"%>'/>" > 
								         </td>
								          	
					    			 	<td class="table1title" nowrap>�û�id:</td>
									    <td class="table1text" nowrap>
									     <input type="text" name="userid" size="10" class="n2centernone" readonly='true'
									          readonly="true" value="<bean:write name='homeworldUserLifeForm' property='<%="userid[" + index + "]"%>'/>">
							        		<input type="hidden" name="rowstate" value="<bean:write name='homeworldUserLifeForm' property='<%="rowstate[" + index + "]"%>'/>">

									     </td>
								        
					    			  	<td class="table1title" nowrap> ����������:</td>
									    <td class="table1text" nowrap>
							        		<input type="text" name="lifebeg"   size="10" class='n1right'   onfocus="this.select();" 
								                value="<bean:write name='homeworldUserLifeForm' property='<%="lifebeg[" + index + "]"%>'/>"> 				            				            
								         </td>
								     </tr>
									 <tr>
									    <td class="table1title" nowrap>����������:</td>
										    <td class="table1text" nowrap>
								        		<input type="text" name="useridgshow"  size="14" class='n2centernone' 
									              readonly="true" value="<bean:write name='homeworldUserLifeForm' property='<%="useridgshow[" + index + "]"%>'/>"> 				            				            
								         </td>	
								         
										<td class="table1title" nowrap> ������id:</td>
									    <td class="table1text" nowrap>
									        <input type="text" name="useridgid"  size="10" class='n2centernone' 
									          readonly="true"   value="<bean:write name='homeworldUserLifeForm' property='<%="useridgid[" + index + "]"%>'/>"> 				            			            
									    </td>
									    
								         <td class="table1title" nowrap> ��������ֹ:</td>
									     <td class="table1text" nowrap>
								       	     	<input type="text" name="lifeend" size="10" class='n1right'  onfocus="this.select();" 
								            	  value="<bean:write name='homeworldUserLifeForm' property='<%="lifeend[" + index + "]"%>'/>"> 
								         </td>
									 </tr>
									
								</table>	
   						       <br>    
   						       <center>
							   <html:button property="butcommit" onclick ="subform(this.form)">�ύ</html:button>
							   </center>
							   </td>
							   
							   <td><input type="button" name="buthidden" value="����" onclick="hiddenthisform(this.form)"></td>
							</logic:equal>		    	
					   	


							
					   	 </tr>
						 </logic:iterate>
					</tbody>				
				</logic:present>
			</table>
		</logic:equal>	
		
		<br>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			
		<script language="JavaScript">	
			//��ѯ����
			function selectform(thisform){	
			
				thisform.where.value=thisform.id.value+ " ='"+thisform.liketext.value.toUpperCase()+"' ";

				//thisform.where.value=thisform.id.value+ " like '%"+thisform.liketext.value.toUpperCase()+"%' or ";
				//thisform.where.value=thisform.where.value + "vndnum like '%"+thisform.liketext.value+"%' ";

					thisform.flag.value="selectexic";
					thisform.submit();

			}
				

						
			//���ص���
			function hiddenthisform(thisform){
				
				thisform.currrowshowold.value=thisform.currrowshow.value;
				thisform.currrowshow.value="-1";
				

					thisform.flag.value="hiddenexic";
					thisform.submit();

			}
			
			//�༭����
			function changethisform(thiscell){
				var currrow=thiscell.parentNode.parentNode.rowIndex - 1;				
				thiscell.form.currrowshowold.value=thiscell.form.currrowshow.value;
				thiscell.form.currrowshow.value=currrow;
				thiscell.form.currrow.value=thiscell.form.currrowshow.value;


					thiscell.form.flag.value="changeexic";
					thiscell.form.submit();

			}
			
			//У���ύ����
			function subform(thisform) {
				var currow=thisform.currrowshow.value;
				
				if(trim(thisform.lifebeg[currow].value)==''){
					alert("������������Ϊ��");
					thisform.lifebeg[currow].focus();
					return;
				}
				if(trim(thisform.lifeend[currow].value)==''){
					alert("��������ֹ����Ϊ��");
					thisform.lifeend[currow].focus();
					return;
				}
				
				ss=validdate(thisform.lifebeg[currow].value);
				if( ss== "error"){
					alert("�����������ʽ����");
					thisform.lifebeg[currow].focus();
					return;
				}else{
					thisform.lifebeg[currow].value=ss;
				}
			
				
				ss=validdate(thisform.lifeend[currow].value);
				if( ss== "error"){
					alert("��������ֹ��ʽ����");
					thisform.lifeend[currow].focus();
					return;
				}else{
					thisform.lifeend[currow].value=ss;
				}
			
				thisform.flag.value="commit";
				thisform.submit();
				return 1;
				
			}
		</script>
		</html:form>
	</BODY>
</html:html>
