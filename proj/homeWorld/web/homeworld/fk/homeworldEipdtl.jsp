<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312" %>
<html:html locale="true">
	
	<%
	response.setHeader("Pragma","No-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0); 
	%>
	<HEAD>
		<META HTTP-EQUIV="Cache-Control" CONTENT="No-cache">
		<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
		<META HTTP-EQUIV="Content-Type" content="text/html; charset=GB2312">
		<META HTTP-EQUIV="Content-Style-Type" content="text/css">
		<META name="GENERATOR" content="IBM WebSphere Studio">
		<LINK href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css">
		<TITLE> 
			������������ҵ�������޹�˾
		</TITLE>
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
			<html:form action="/homeworldEipdtl" >

			<html:hidden property="selectwhere"/>
			<html:hidden property="flag"/>
			<html:hidden property="nowdate"/>
			<html:hidden property="currrow"/>
			<html:hidden property="currcolumn"/>
			<html:hidden property="msgbox"/>
			<html:hidden property="showhide"/>
						

			<H2 align="center">������������ҵ�������޹�˾<BR>��Ʊ��Ϣ����</H2>
		
		    <table  align="center" style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 border=1>
				<logic:present name="homeworldEipdtlForm" property="seq" >
				    <thead>
					    <th class="printtitle3center"  nowrap>��Ʊ״̬</th>
					    <th class="printtitle3center"  nowrap>��Ʊ<input type="checkbox" name="all" onclick="allc(this.form)"></th>
						<th class="printtitle3center"  nowrap>���</th>
						<th class="printtitle3center"  nowrap>�̵�</th>
						<th class="printtitle3center"  nowrap>���׵����</th>
						<th class="printtitle3center"  nowrap>��������</th>
						<th class="printtitle3center"  nowrap>���</th>
						<th class="printtitle3center"  nowrap>��������</th>
						<th class="printtitle3center"  nowrap>Ӧ������</th>					
					</thead>
					<tbody>	
					  <logic:iterate id="seq" name="homeworldEipdtlForm" property="seq" indexId="index">
							    <tr align="left">
							    	 <logic:equal name="homeworldEipdtlForm" property='<%="eipflg[" + index + "]"%>' value='2'>
							        	<td class="tablextitle1" nowrap><center>�ѿ�</center></td>    
							        </logic:equal>
							    	 <logic:notEqual name="homeworldEipdtlForm" property='<%="eipflg[" + index + "]"%>' value='2'>
							        	<td class="tablextitle1" nowrap><center>δ��</center></td>    
							        </logic:notEqual>
							        	
							        <td class="printtextcenter" nowrap><html:multibox property="chacked" onclick="sumc(this)" > <%=index%></html:multibox></td>    
							         <input type="hidden" name="eipflg"  value="<bean:write name='homeworldEipdtlForm' property='<%="eipflg[" + index + "]"%>'/>">
							         								         
								    <td class="printtextcenter" nowrap><input type="text" name="seq" size="5"  style="border-width: 1px; text-align: center;border:none"
							            value='<bean:write name="homeworldEipdtlForm" property='<%="seq[" + index + "]"%>'/>' readonly="true"></td>    
									<td class="printtextcenter" nowrap><input type="text" name="eipstr" size="10"  style="border-width: 1px; text-align: center;border:none"
							            value='<bean:write name="homeworldEipdtlForm" property='<%="eipstr[" + index + "]"%>'/>' readonly="true"></td>    
									<td class="printtextcenter" nowrap><input type="text" name="eipnum" size="15"  style="border-width: 1px; text-align: center;border:none"
							            value='<bean:write name="homeworldEipdtlForm" property='<%="eipnum[" + index + "]"%>'/>' readonly="true"> </td>    
									<td class="printtextcenter" nowrap><input type="text" name="show" size="8"  style="border-width: 1px; text-align: center;border:none"
							            value='<bean:write name="homeworldEipdtlForm" property='<%="show[" + index + "]"%>'/>' readonly="true"> 
							            <input type="hidden" name="eiptyp" value='<bean:write name="homeworldEipdtlForm" property='<%="eiptyp[" + index + "]"%>'/>'> 
							            </td>    			
									<td class="printtextcenter" nowrap><input type="text" name="eipamt" size="8"  style="border-width: 1px; text-align: center;border:none"
							            value='<bean:write name="homeworldEipdtlForm" property='<%="eipamt[" + index + "]"%>'/>' readonly="true"> </td>    
									<td class="printtextcenter" nowrap><input type="text" name="eipdta" size="10"  style="border-width: 1px; text-align: center;border:none"
							            value='<bean:write name="homeworldEipdtlForm" property='<%="eipdta[" + index + "]"%>'/>' readonly="true"></td>    
									<td class="printtextcenter" nowrap><input type="text" name="eipyta" size="10"  style="border-width: 1px; text-align: center;border:none"
							            value='<bean:write name="homeworldEipdtlForm" property='<%="eipyta[" + index + "]"%>'/>' readonly="true"></td>    
							       
							            
								</tr>		    	
					  </logic:iterate>
					</tbody>	
				</logic:present>
			</table>

<br>	
			<center>�ϼƣ�<input type="text" name="sum" value="0" readonly="true">
<br><br>	
			
<logic:notEqual name="homeworldEipdtlForm" property='msgbox' value='1'>

			 <table  align="center" style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 border=1>
				    <thead>
					    <th class="printtitle3center"  nowrap>��Ʊ�ֹ�</th>
						<th class="printtitle3center"  nowrap>��Ʊ��</th>
						<th class="printtitle3center"  nowrap>���</th>
						<th class="printtitle3center"  nowrap>˰��(%)</th>				
						<th class="printtitle3center"  nowrap>��Ʊ����</th>			
					</thead>
					<tbody id="DynData">	
							    <tr align="left">		
									<td class="printtextcenter" nowrap><input type="text" name="eivtrk" value='' maxlength="10" size="10" class="nright" onfocus='tdfocus(this)' onchange='tdchange(this.form)'>
										<input type="hidden" name="rowstate" value='3'></td>    
									<td class="printtextcenter" nowrap><input type="text" name="eivivn" value='' maxlength="8" size="8" class="nright"  onfocus='tdfocus(this)' onchange='tdchange(this.form)'> </td>    
									<td class="printtextcenter" nowrap><input type="text" name="eivamt" value='' maxlength="10" size="10"  class="nright" onfocus='tdfocus(this)' onchange='tdchange(this.form)'></td>    
									<td class="printtextcenter" nowrap><input type="text" name="eivtax" value='' maxlength="3" size="3"  class="nright" onfocus='tdfocus(this)' onchange='tdchange(this.form)'></td>    
									<td class="printtextcenter" nowrap><input type="text" name="eivdat" readonly="true" size="10" value='<bean:write name="homeworldEipdtlForm" property="nowdate"/>'  class="nright" onfocus='tdfocus(this)' onclick='change(this)'></td>    
								</tr>	
							    <tr align="left">		
									<td class="printtextcenter" nowrap><input type="text" name="eivtrk" value='' maxlength="10" size="10"  class="nright" onfocus='tdfocus(this)' onchange='tdchange(this.form)'>
										<input type="hidden" name="rowstate" value='3'></td>    
									<td class="printtextcenter" nowrap><input type="text" name="eivivn" value='' maxlength="8" size="8"  class="nright" onfocus='tdfocus(this)' onchange='tdchange(this.form)'> </td>    
									<td class="printtextcenter" nowrap><input type="text" name="eivamt" value='' maxlength="10" size="10"  class="nright" onfocus='tdfocus(this)' onchange='tdchange(this.form)'></td>    
									<td class="printtextcenter" nowrap><input type="text" name="eivtax" value='' maxlength="3" size="3"  class="nright" onfocus='tdfocus(this)' onchange='tdchange(this.form)'></td>    
									<td class="printtextcenter" nowrap><input type="text" name="eivdat" readonly="true" size="10" value='<bean:write name="homeworldEipdtlForm" property="nowdate"/>'    class="nright" onfocus='tdfocus(this)' onclick='change(this)'></td>    
								</tr>
					</tbody>	
			</table>
			
			
		
				<br>	<br>
				
				ע:�ύ���޷���������.���д���,������Ʊʱ��֪��Ʊ��Ա!
				<br>	<br>	

			<logic:notEqual name="homeworldEipdtlForm" property='selectwhere' value=''>
				<input type="button" value="����" name="B4" onclick ="insline(this.form)">	
				<input type="button" value="ɾ��" name="B5" onclick ="delline(this.form)">	
				<input type="button" value="�ύ" name="B1" onclick ="commit(this.form)">	
				<input type="reset"  value="����" name="B2">	
			</logic:notEqual>
</logic:notEqual>
			<logic:notEqual name="homeworldEipdtlForm" property='selectwhere' value=''>
				<input type="button" value="��ӡ" name="B3" onclick ="printform(this.form)">	
			</logic:notEqual>
			</center>
		
		</html:form>
		
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>	
		<script language="JavaScript">
		
		if(document.forms[0].eipflg!=null){
			if(document.forms[0].eipflg.value=='2'){
				document.forms[0].chacked.disabled="true";
			}
			if(document.forms[0].eipflg!=null){
				for(var i=0;i<document.forms[0].eipflg.length;i++){
					if(document.forms[0].eipflg[i].value=='2'){
						document.forms[0].chacked[i].disabled="true";
					}
				}
			}
		}
		
			if(document.forms[0].msgbox.value=="1"){
				alert("����ɹ�����")
			}
			
			function change(thiscell){
		
				var select=window.showModalDialog("<%=request.getContextPath()%>/system/calendar.jsp","","dialogHeight:300px;dialogWidth:400px " );
				if (select!=""){
					thiscell.value=select;
					tdchange(thiscell.form);
				}			
			}
			
			function allc(form){			
			var sum=0;	
				if(form.chacked==null){
					return;
				}
				if(document.forms[0].eipflg!=null){
					for(var i=0 ;i<form.chacked.length;i++){
						if(form.eipflg[i].value=='1'){
							if(form.chacked[i].checked!=form.all.checked){
								form.chacked[i].checked=form.all.checked;
								sumc(form.chacked[i]);
								form.chacked[i].focus();
							}
						}
					}
					if(form.chacked[0]==null){
						if(form.chacked.checked!=form.all.checked){
							form.chacked.checked=form.all.checked;
							sumc(form.chacked);
						}
					}

				}
				
			}
		
			
			
			function sumc(thisob){	
				//if(thisform.chacked.length==null){
				//	if(thisform.chacked.checked){		
				//		sum = sum  + thisform.eipamt.value * 1 ;
				//	}
					
				//}
			
				//for(var i=0 ;i<thisform.chacked.length;i++){
				//	if(thisform.chacked[i].checked){		
				//		sum = sum  + thisform.eipamt[i].value * 1 ;
				//	}
				//}
			//	alert(sum);
				var sum=thisob.form.sum.value;			
				var ss;
				var i=thisob.parentNode.parentNode.rowIndex - 1;
				
				if(thisob.form.eipamt[i]==null){
					if(thisob.checked){	
						thisob.form.sum.value=Math.round((thisob.form.eipamt.value * 1+sum * 1 )*100)/100;
					}else{
						thisob.form.sum.value=Math.round((sum * 1 - thisob.form.eipamt.value * 1 )*100)/100;
					}
					ss=String(thisob.form.sum.value);
					if(ss.indexOf(".")==-1){
						thisob.form.sum.value=ss + ".00";
						return;
					}
					switch(ss.length-ss.indexOf(".")){

						case 2:
							thisob.form.sum.value=ss + "0";
							break;
					}
					return;
				}
				
				if(thisob.checked){	
					thisob.form.sum.value=Math.round((thisob.form.eipamt[i].value * 1+sum * 1 )*100)/100;
				}else{
					thisob.form.sum.value=Math.round((sum * 1 - thisob.form.eipamt[i].value * 1 )*100)/100;
				}
				
					ss=String(thisob.form.sum.value);
					if(ss.indexOf(".")==-1){
						thisob.form.sum.value=ss + ".00";
						return;
					}
					switch(ss.length-ss.indexOf(".")){

						case 2:
							thisob.form.sum.value=ss + "0";
							break;
					}
				
			}
			

			
			function commit(thisform){	
				if(thisform.selectwhere.value==''){
					alert("û�п��ύ����");
					return ;
				}
							
				var sum=0;
				var sum1=0;

				if(thisform.chacked.length==null){
					if(thisform.chacked.checked){		
						sum = sum  + thisform.eipamt.value * 1 ;
					}					
				}
				for(var i=0 ;i<thisform.chacked.length;i++){
					if(thisform.chacked[i].checked){		
						sum = sum  + thisform.eipamt[i].value * 1 ;
					}
				}				
				for(var j=0 ;j<thisform.eivamt.length;j++){
					if(thisform.rowstate[j].value=="4")
						sum1 = sum1  + thisform.eivamt[j].value * 1 ;
				}
				if(sum!=sum1){
//					alert("��ѡ������������");
//					return;
				}
			
				thisform.action="<%=request.getContextPath()%>/homeworldEipdtl.do";
				submitform(thisform,"commit");
			}
			
		
			function printform(thisform){				
				if(thisform.B1!=null){
					alert("û�пɴ�ӡ����");
					return ;
				}
					
				if(thisform.selectwhere.value==''){
					alert("û�пɴ�ӡ����");
					return ;
				}
				thisform.action="<%=request.getContextPath()%>/homeworldPrintEipdtl.do";
				thisform.submit();
			}
			
			
			//���������Բ���(����,type,size,value,readonly,class)
			function fields () { 
				this.aa = new Array("printtextcenter","eivtrk","text","10","","","nright","onfocus='tdfocus(this)' onchange='tdchange(this.form)'");
				this.ab = new Array("printtextcenter","eivivn","text","8","","","nright","onfocus='tdfocus(this)' onchange='tdchange(this.form)'");
				this.ac = new Array("printtextcenter","eivamt","text","10","","","nright","onfocus='tdfocus(this)' onchange='tdchange(this.form)'");
				this.ad = new Array("printtextcenter","eivtax","text","3","","","nright","onfocus='tdfocus(this)' onchange='tdchange(this.form)'");
				this.ae = new Array("printtextcenter","eivdat","text","10","<bean:write name='homeworldEipdtlForm' property='nowdate'/>","true","nright","onfocus='tdfocus(this)' onclick='change(this)'");
			} 
			
			
			//��ЧУ�����(����,��ʾ����,����)
			function masks () { 
				this.aa = new Array("eivtrk", "��Ʊ�ֹ� ���������֣�","^\\d*$");
				this.ab = new Array("eivivn", "��Ʊ�� ���������֣�","^\\d*$");
				this.ac = new Array("eivtax", "˰�� ���������֣�","^\\d*$");
			} 

			//�ظ�У�����(������,��ʾ����)
			function repeat () { 
				this.aa = new Array("eivtrk,eivivn", "��Ʊ�ֹ�+��Ʊ�� �ظ���");
			}	 
			
				
		//��¼У�����(����,��ʾ����)
		function required () { 
			this.aa = new Array("eivtrk", "��Ʊ�ֹ� ����Ϊ�գ�");
			this.ab = new Array("eivivn", "��Ʊ�� ����Ϊ�գ�");
			this.ac = new Array("eivamt", "��� ����Ϊ�գ�");
			this.ad = new Array("eivtax", "˰�� ����Ϊ�գ�");
		}
			
			//У���ύ����
			function validateSubmit(form) {
			
				for(var i=0 ;i<form.eivamt.length;i++){
					if(form.eivamt[i].value!=""){
						if(validfloat(form.eivamt[i].value)=="error"){
							form.eivamt[i].focus();
							form.eivamt[i].select();
							alert("��� ��ʽ����ȷ!");
							return;
						}
					}
				}
				
				
				if ( validField(form,1) && validField(form,2)&& validRepeat(form)) {
					form.submit();
				}
				return;
			}
			
			
		</script>
	</BODY>
</html:html>
