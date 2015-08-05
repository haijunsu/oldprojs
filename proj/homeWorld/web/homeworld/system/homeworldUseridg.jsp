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
				 人员共用名管理
		</TITLE>
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
			<html:form action="/homeworldUseridg" >

			<html:hidden property="flag" />
			
			<html:hidden property="currrow"/>
			<html:hidden property="currcolumn"/>
			<center><H2>			
				<html:errors property="testActionErrors"/>
					 人员共用名管理 
		    </H2></center>
		    <table  align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
		    
				<logic:present name="homeworldUseridgForm" property="userid" >
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
					  <logic:iterate id="userid" name="homeworldUseridgForm" property="userid" indexId="index">
					     <tr align="center">
					     
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="userid"  size="10" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldUseridgForm' property='<%="userid[" + index + "]"%>'/>" > 
						            <input type="hidden" name="rowstate" value='0'>

						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="usershow"  size="15" class="n2centernone" readonly="true"
						            value="<bean:write name="homeworldUseridgForm" property='<%="usershow[" + index + "]"%>'/>" > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="lifebeg"  size="10" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldUseridgForm' property='<%="lifebeg[" + index + "]"%>'/>" > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="lifeend"  size="10" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldUseridgForm' property='<%="lifeend[" + index + "]"%>'/>" > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="useridgid"  size="10" class="n1right" onfocus="tdfocus(this);" readonly="true" onclick="selectfield(this,'VNDINFO','','useridgid`useridgshow','<%=index%>')" 
						            value="<bean:write name='homeworldUseridgForm' property='<%="useridgid[" + index + "]"%>'/>" > 
						        </td>
					    		 <td class="table2textrighttd" nowrap> <input type="text" name="useridgshow"  size="15" class="n2centernone" readonly="true"
						            value="<bean:write name='homeworldUseridgForm' property='<%="useridgshow[" + index + "]"%>'/>" > 
						        </td>
						
						</tr>
					  </logic:iterate>
					</logic:present>
		 		</tbody>	
			</table>
			<br>
			<center><html:button property="butcommit" onclick ="submitform(this.form,'commit')">提交</html:button></center>	
		</html:form>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			
		<script language="JavaScript">	
			
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
	</BODY>
</html:html>

