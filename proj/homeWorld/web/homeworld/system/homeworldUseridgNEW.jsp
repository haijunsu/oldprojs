
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
				<bean:write name="homeworldUseridgNEWForm" property='title'/>
		    	<logic:equal name="homeworldUseridgNEWForm" property="se_up" value="1">		 	    
					管理		    
				</logic:equal>
			    <logic:equal name="homeworldUseridgNEWForm" property="se_up" value="2">		 	    
					查询
				</logic:equal>
		</TITLE>
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
			<html:form action="/homeworldUseridgNEW" >
			
			<html:hidden property="se_up" />
			<html:hidden property="flag" />
 		    <html:hidden property="page" />
 		    <html:hidden property="where" />
 		    
			<html:hidden property="currrow"/>
			<html:hidden property="count"/>
			<html:hidden property="title"/>
			
			<html:hidden property="id"/>
			<html:hidden property="show"/>
			
			<html:hidden property="currcolumn"/>
			
			<center><H2>			
				<html:errors property="testActionErrors"/>
				
				<bean:write name="homeworldUseridgNEWForm" property='title'/>
		    	<logic:equal name="homeworldUseridgNEWForm" property="se_up" value="1">		 	    
					管理		    
				</logic:equal>
			    <logic:equal name="homeworldUseridgNEWForm" property="se_up" value="2">		 	    
					查询
				</logic:equal>
		    </H2></center>
		    
		    
		    
		    <br>
			<center>                                              
			<bean:write name="homeworldUseridgNEWForm" property="show"/>
			：&nbsp;&nbsp;<html:text property="liketext" size="12"  onkeypress="check(this.form)"/>&nbsp;&nbsp;
			<html:button property="butselect" onclick ="selectform(this.form)">查询</html:button> 
			<br>			
			</center>
		<br>	<br>	
								        <%String ss="";%>
			<logic:equal name="homeworldUseridgNEWForm" property="page" value="2">  
				<table  align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
					<logic:present name="homeworldUseridgNEWForm" property="seq">
						<thead>
							<tr align="center">		
							<th  class="table2titletd" nowrap>用户id</th>
							<th  class="table2titletd" nowrap>用户姓名</th>
							<th  class="table2titletd" nowrap>生命周期起</th>
							<th  class="table2titletd" nowrap>生命周期止</th>
							<th  class="table2titletd" nowrap>共用名id</th>
							<th  class="table2titletd" nowrap>共用名中文</th>
							</tr>
						</thead>
						<tbody id="DynData">	
						<logic:iterate id="temp" name="homeworldUseridgNEWForm" property="useridgid" indexId="index">					
					     <tr align="center">
					     
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="userid"  size="10" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldUseridgNEWForm' property='<%="userid[" + index + "]"%>'/>" > 
						            <input type="hidden" name="rowstate" 
						             value="<bean:write name='homeworldUseridgNEWForm' property='<%="rowstate[" + index + "]"%>'/>" > 
									<input type="hidden" name="seq" value="<bean:write name='homeworldUseridgNEWForm' property='<%="seq[" + index + "]"%>'/>">
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="usershow"  size="15" class="n2centernone" readonly="true"
						            value="<bean:write name="homeworldUseridgNEWForm" property='<%="usershow[" + index + "]"%>'/>" > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="lifebeg"  size="10" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldUseridgNEWForm' property='<%="lifebeg[" + index + "]"%>'/>" > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="lifeend"  size="10" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldUseridgNEWForm' property='<%="lifeend[" + index + "]"%>'/>" > 
						        </td>
						        
								<%ss=(String) temp;%>
								
						        <logic:equal name="temp" value=''> 						        
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="useridgid"  maxlength="10" size="10" class="n1right" 
						      		  readonly="true"  value="" > 						            
 						         </td>
       					        </logic:equal>	
						        
						        
						        <logic:notEqual name="temp" value=''> 						        
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="useridgid"  maxlength="10" size="10" class="n1right" 
						      		  	onfocus="tdfocus(this);" onchange="tdchange(this.form)" value="<bean:write name='homeworldUseridgNEWForm' property='<%="useridgid[" + index + "]"%>'/>" > 						            
 						         </td>
       					        </logic:notEqual>	
       					        
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="useridgshow"  size="15" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldUseridgNEWForm' property='<%="useridgshow[" + index + "]"%>'/>" > 
						        </td>

					   	 </tr>
						 </logic:iterate>

				</tbody>				
									</logic:present>
			</table>
			<br>
			<br>
			<center><html:button property="butcommit" onclick ="submitform(this.form,'commit')">提交</html:button></center>			
		</logic:equal>	
		
		<br>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			
		<script language="JavaScript">	
		if(document.forms[0].page.value!="1"){
			for (var i=0;i<2 ;i++){
				if(document.forms[0].userid[i].value==''){
					document.forms[0].useridgid[i].value='';
					document.forms[0].useridgid[i].readonly='true';
				}
			}
		}
			document.forms[0].liketext.focus();
			function  check(thisform){
				if(window.event.keyCode == 13){
					selectform(thisform);
				}
			}
			
			//查询调用
			function selectform(thisform){	
			    		thisform.where.value = "(" + thisform.id.value+" ='" + thisform.liketext.value.toUpperCase()+"' )" 
					//thisform.where.value = "(" + thisform.id.value+" like '%" + thisform.liketext.value.toUpperCase()+"%' OR " + thisform.id.value+" like '%" + thisform.liketext.value.toLowerCase()+"%' " +")" 
					
				if(saveChange(thisform,"select") != 1){
					thisform.flag.value="selectexic";
					validateSubmit(thisform);
				}
			}

			//校验提交函数
			function validateSubmit(form) {
	        	
			//		if (validField(form,1)&&validField(form,4)) {
						form.submit();
			//		}
			 	
				return;
			}
			
			//必录校验参数(列名,提示内容)
			function required(){ 
				this.aa = new Array("namec", "用户姓名 不能为空！");
			}
			
			//日期校验参数(列名,提示内容)
			function dates () { 
				this.aa = new Array("birthday", "生日 格式不正确！");
			} 
			
			
		</script>
		</html:form>
	</BODY>
</html:html>
