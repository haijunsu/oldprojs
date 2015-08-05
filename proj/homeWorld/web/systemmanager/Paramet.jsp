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
				��������
		</TITLE>
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
		<html:form action="/Paramet" >
		    <html:hidden property="flag"/>
		    <html:hidden property="page"/>
			<html:hidden property="currrow"/>
			<html:hidden property="currcolumn"/>
			
		    
		<center><H2>
			<logic:equal name="ParametForm" property="page" value="1">
				����Ч����
			</logic:equal>
			<logic:notEqual name="ParametForm" property="page" value="1">
				δ��Ч����
			</logic:notEqual>
		 </H2></center>
		
		<table  align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
			<thead>
				<tr align="center">
					<th  class="table2titletd">���߲���</th>
					<th  class="table2titletd">�ֶε�</th>
					<th  class="table2titletd">����ֵ1</th>
					<th  class="table2titletd">����ֵ2</th>
					<th  class="table2titletd">��Ч����</th>
				</tr>
				</thead>
				<tbody id="DynData">
					<logic:present name="ParametForm" property="effedate" >
					  <logic:iterate id="policyshow" name="ParametForm" property="policyshow" indexId="index">
					    <tr align="center">
					    	<%
				    		String strStyle="n2centernone";
				    		
			    			String strFocus="";
			    			String strChange="";
			    			String strClick="";
			    			String strReadonly="readonly=\"true\"";
					    	%>
							<logic:equal name="ParametForm" property="page" value="2">		 	    
								<%
								strStyle="n1right";
					    		strFocus="tdfocus(this)";
				    			strChange="tdchange(this.form)";
				    			strClick="selectfield(this.form,"+String.valueOf(index)+",'policy','21')";
				    			strReadonly="";
				    			%>
					    	</logic:equal>
				    		<td class="table2textrighttd" nowrap> <input type="text" name="policyshow" maxlength="16" size="16" class="<%=strStyle%>" readonly="true"
					            onfocus="<%=strFocus%>" onchange="<%=strChange%>"
					            onclick ="<%=strClick%>" 
					            value='<%=policyshow%>' > 
						            
					        	<input type="hidden" name="rowid"
					        	value=<bean:write name="ParametForm" property='<%="rowid[" + index + "]"%>'/>>
						        	
					        	<input type="hidden" name="rowstate" value='0'>
						        	
					        	<input type="hidden" name="policyid" 
					        	value=<bean:write name="ParametForm" property='<%="policyid[" + index + "]"%>'/>>
						        	
					        </td>
					         <td class="table2textrighttd" nowrap> <input type="text" name="subsect" maxlength="16" size="16" class="<%=strStyle%>"
					            onfocus="<%=strFocus%>" onchange="<%=strChange%>"
					            <%=strReadonly%> 
					            value=<bean:write name="ParametForm" property='<%="subsect[" + index + "]"%>'/>> 
					        </td>
						        
					         <td class="table2textrighttd" nowrap> <input type="text" name="paramet" maxlength="16" size="16" class="<%=strStyle%>"
					            onfocus="<%=strFocus%>" onchange="<%=strChange%>"
					            <%=strReadonly%> 
					            value=<bean:write name="ParametForm" property='<%="paramet[" + index + "]"%>'/>> 				            
					        </td>
						        
					         <td class="table2textrighttd" nowrap> <input type="text" name="paramet2" maxlength="16" size="16" class="<%=strStyle%>"
					            onfocus="<%=strFocus%>" onchange="<%=strChange%>"
					            <%=strReadonly%>
					            value=<bean:write name="ParametForm" property='<%="paramet2[" + index + "]"%>'/>> 				            
					        </td>
						        
					        <td class="table2textrighttd" nowrap> <input type="text" name="effedate" maxlength="16" size="16" class="<%=strStyle%>"
					            onfocus="<%=strFocus%>" onchange="<%=strChange%>"
					            <%=strReadonly%> 
					            value=<bean:write name="ParametForm" property='<%="effedate[" + index + "]"%>'/>> 				            
					        </td>		
							
					   	 </tr>
					  </logic:iterate>
					</logic:present>
		 		</tbody>
			</table>    
			<br><br>
			<table align="center" width="50%">
		      <tr align="center"> 
				<logic:equal name="ParametForm" property="page" value="1">		 
			    	<td><html:button property="butfast" onclick ="changeform(this.form,'change')">�༭</html:button></td> 
				</logic:equal>			
				<logic:notEqual name="ParametForm" property="page" value="1">		 
					<td align="center" ><html:button property="butlast" onclick ="changeform(this.form,'change')">����</html:button></td> 
					<td align="center" ><html:button property="butins" onclick ="insline(this.form)">����</html:button> </td>
			        <td align="center" ><html:button property="butdel" onclick ="delline(this.form)">ɾ��</html:button> </td>
			        <td align="center" ><html:button property="butsubmit" onclick ="submitform(this.form,'commit')">�ύ</html:button></td> 
				</logic:notEqual>
			    <td align="center" ><html:button property="butesc" onclick ="escform(this.form)">�˳�</html:button></td>
		      </tr>
			</table>	
			<br>
			
			<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
			<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			
			<script language="JavaScript">
					
			rerowstate("rowid");
				
			//ѡ����뺯��
			function selectfield(thisform,currrow,currcol,code){
				selectone(thisform,currrow,currcol,code,"");
				
			}
			
			//��ҳ��������ʾ���档ȷ�����ύ��ҳ��ȡ�������ύ��ҳ
			function changeform(thisform,flag){
				if(thisform.page.value!="1"){
					thisform.page.value="1";
				}else{
					thisform.page.value="2";
				}
				thisform.flag.value="change";
				if (saveChange(thisform,flag)==0) {				
					thisform.flag.value="changexx";
					thisform.submit();
		    	 }
			}
			
			//�˳�����
			function escform(thisform){
				if(saveChange(thisform,"esc") == 0){
					window.open("<bean:message key='Home.page'/>","_self","");
				}
			}
			
			//У���ύ����
			function validateSubmit(form) {
				//if (validField(form,1) && validField(form,2) && validField(form,4)) {
				if (validField(form,1) && validField(form,2) && validField(form,4)&& 
				    validField(form,6) && validLongness(form)&& validRepeat(form)) {
					form.submit();
				}
				return;
			}
			
			//�ظ�У�����(������,��ʾ����)
			function repeat () { 
				this.aa = new Array("policyid,subsect,effedate", "���߲���+�ֶε�+��Ч���� �ظ���");
			}	 
			
			//����У�����(����,��ʾ����)
			function dates () { 
				this.aa = new Array("effedate", "��Ч���� ��ʽ����ȷ��");
			} 

			//��¼У�����(����,��ʾ����)
			function required () { 
				this.aa = new Array("policyid", "���߲��� ����Ϊ�գ�");
				this.ab = new Array("subsect", "�ֶε� ����Ϊ�գ�");
				this.ad = new Array("paramet", "����1 ����Ϊ�գ�");
				this.ac = new Array("effedate", "��Ч���� ����Ϊ�գ�");
			} 

			//��ЧУ�����(����,��ʾ����,����)
			function masks () { 
				this.aa = new Array("subsect", "�ֶε� ���������֣�","^\\d*$");
			} 
			
			//����У�����(����,��ʾ����,Ҫ��,����)
			//Ҫ��--���ڣ�1�����ڣ�2��С�ڣ�3
			//�������Ϊ0��������һ����Ч�ֶε����ݳ���Ϊ��׼
			function longness () { 
				this.aa = new Array("subsect","�ֶε� ���̲��ɴ���10λ��",3,10);
				this.ab = new Array("paramet","����1 ���̲��ɴ���10λ��",3,10);
				this.ac = new Array("paramet2","����2 ���̲��ɴ���10λ��",3,10);
			} 
			
			//�Ƚ�У�����(����,��ʾ����,����)
			function compares () { 
				this.aa = new Array("effedate","��Ч���� Ӧ���ڵ�ǰ���ڣ�",'<bean:write name="ParametForm" property="nowdate"/>');
			} 
			
			//���������Բ������壺(tdclass,����,type,size,value,readonly,class,event)
			function fields () { 
				this.aa = new Array("table2textrighttd","policyshow","text","16","","true","TEXTCENTER","onfocus='tdfocus(this)' onchange='tdchange(this.form)' onclick =\"selectfield(this.form,<intMaxrow>,'policy','21')\"");
				this.ab = new Array("table2textrighttd","policyid","hidden","16","","","nright","onfocus='tdfocus(this)' onchange='tdchange(this.form)'");
				
				this.ac = new Array("table2textrighttd","subsect","text","16","","","TEXTCENTER","onfocus='tdfocus(this)' onchange='tdchange(this.form)'");
				this.ad = new Array("table2textrighttd","paramet","text","16","","","TEXTCENTER","onfocus='tdfocus(this)' onchange='tdchange(this.form)'");
				this.ae = new Array("table2textrighttd","paramet2","text","16","","","TEXTCENTER","onfocus='tdfocus(this)' onchange='tdchange(this.form)'");
				this.af = new Array("table2textrighttd","effedate","text","16","","","TEXTCENTER","onfocus='tdfocus(this)' onchange='tdchange(this.form)'");
			} 
				
			</script>
		</html:form>
	</BODY>
</html:html>
