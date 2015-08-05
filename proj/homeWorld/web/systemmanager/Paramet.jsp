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
				参数管理
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
				已生效参数
			</logic:equal>
			<logic:notEqual name="ParametForm" property="page" value="1">
				未生效参数
			</logic:notEqual>
		 </H2></center>
		
		<table  align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
			<thead>
				<tr align="center">
					<th  class="table2titletd">政策参数</th>
					<th  class="table2titletd">分段点</th>
					<th  class="table2titletd">参数值1</th>
					<th  class="table2titletd">参数值2</th>
					<th  class="table2titletd">生效日期</th>
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
			    	<td><html:button property="butfast" onclick ="changeform(this.form,'change')">编辑</html:button></td> 
				</logic:equal>			
				<logic:notEqual name="ParametForm" property="page" value="1">		 
					<td align="center" ><html:button property="butlast" onclick ="changeform(this.form,'change')">返回</html:button></td> 
					<td align="center" ><html:button property="butins" onclick ="insline(this.form)">增加</html:button> </td>
			        <td align="center" ><html:button property="butdel" onclick ="delline(this.form)">删除</html:button> </td>
			        <td align="center" ><html:button property="butsubmit" onclick ="submitform(this.form,'commit')">提交</html:button></td> 
				</logic:notEqual>
			    <td align="center" ><html:button property="butesc" onclick ="escform(this.form)">退出</html:button></td>
		      </tr>
			</table>	
			<br>
			
			<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
			<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			
			<script language="JavaScript">
					
			rerowstate("rowid");
				
			//选择代码函数
			function selectfield(thisform,currrow,currcol,code){
				selectone(thisform,currrow,currcol,code,"");
				
			}
			
			//换页函数：提示保存。确定：提交换页；取消：不提交换页
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
			
			//退出函数
			function escform(thisform){
				if(saveChange(thisform,"esc") == 0){
					window.open("<bean:message key='Home.page'/>","_self","");
				}
			}
			
			//校验提交函数
			function validateSubmit(form) {
				//if (validField(form,1) && validField(form,2) && validField(form,4)) {
				if (validField(form,1) && validField(form,2) && validField(form,4)&& 
				    validField(form,6) && validLongness(form)&& validRepeat(form)) {
					form.submit();
				}
				return;
			}
			
			//重复校验参数(列名串,提示内容)
			function repeat () { 
				this.aa = new Array("policyid,subsect,effedate", "政策参数+分段点+生效日期 重复！");
			}	 
			
			//日期校验参数(列名,提示内容)
			function dates () { 
				this.aa = new Array("effedate", "生效日期 格式不正确！");
			} 

			//必录校验参数(列名,提示内容)
			function required () { 
				this.aa = new Array("policyid", "政策参数 不能为空！");
				this.ab = new Array("subsect", "分段点 不能为空！");
				this.ad = new Array("paramet", "参数1 不能为空！");
				this.ac = new Array("effedate", "生效日期 不能为空！");
			} 

			//有效校验参数(列名,提示内容,规则)
			function masks () { 
				this.aa = new Array("subsect", "分段点 必须是数字！","^\\d*$");
			} 
			
			//长度校验参数(列名,提示内容,要求,长度)
			//要求--等于：1；大于：2；小于：3
			//如果长度为0，则区第一个有效字段的数据长度为标准
			function longness () { 
				this.aa = new Array("subsect","分段点 长短不可大于10位！",3,10);
				this.ab = new Array("paramet","参数1 长短不可大于10位！",3,10);
				this.ac = new Array("paramet2","参数2 长短不可大于10位！",3,10);
			} 
			
			//比较校验参数(列名,提示内容,参数)
			function compares () { 
				this.aa = new Array("effedate","生效日期 应大于当前日期！",'<bean:write name="ParametForm" property="nowdate"/>');
			} 
			
			//增行列属性参数定义：(tdclass,列名,type,size,value,readonly,class,event)
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
