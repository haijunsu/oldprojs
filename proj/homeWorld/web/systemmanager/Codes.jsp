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
		<TITLE><bean:message key="Codes.title"/></TITLE>
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
		<html:form action="/Codes">
			<html:hidden property="flag" value=""/>
			<html:hidden property="currrow"/>
			<html:hidden property="currcolumn"/>
			<html:hidden property="cclass"/>
			<center><H2>
				<bean:write name="CodesForm" property="cclassshowc"/>
		    </H2></center>
		    <table align="center" width="50%" cellspacing="0" cellpadding="1" class="table2border">
				<thead>
					<tr>
						<th class="table2titletd"><bean:message key="Codes.ccode"/></th>
						<th class="table2titletd"><bean:message key="Codes.cshowc"/></th>
					</tr>
				</thead>
				<tbody id="DynData">
					<logic:present name="CodesForm" property="ccode" >
					  <logic:iterate id="ccode" name="CodesForm" property="ccode" indexId="index">
					    <tr align="center">
					        <td class="table2textcentertd"> <input type="text" name="ccode" maxlength="16" size="16" class="ncenter"
					            value='<%=ccode%>' 
					            onfocus="tdfocus(this,<%=index%>,'ccode')" onchange="tdchange(this.form)"> 
					        	<input type="hidden" name="rowstate" value="0">
					        	<input type="hidden" name="rowid" value=<bean:write name="CodesForm" property='<%="ccode[" + index + "]"%>'/>>
					        </td>
					        <td class="table2textcentertd"> <input type="text" name="cshowc" maxlength="16" size="16" class="ncenter"
					            onfocus="tdfocus(this,<%=index%>,'cshowc')" onchange="tdchange(this.form)"
					            value=<bean:write name="CodesForm" property='<%="cshowc[" + index + "]"%>'/> 
					            > 
					        </td>
					        <logic:equal name="CodesForm" property="cclass" value="00">
						        <td> <input type="button" name="butdel" value="删除" onfocus="tdfocus(this,<%=index%>,'butdel')" onclick ="delline(this.form)"> </td>
						        <td> <input type="button" name="butnext" value="编辑" onclick ="changeform(this.form,<%=index%>)"> </td>
							</logic:equal>
					    </tr>
					    
					  </logic:iterate>
					</logic:present>
		 		</tbody>
			</table>
			<br>
			<table align="center" width="50%">
		      <tr align="center"> 
		        <td> <html:button property="butins" onclick ="insline(this.form)">增加</html:button> </td>
		        <logic:notEqual name="CodesForm" property="cclass" value="00">
			        <td> <html:button property="butdel" onclick ="delline(this.form)">删除</html:button> </td>
			        <td> <html:button property="butlast" onclick ="changeform(this.form,'last')">代码管理</html:button> </td>
				</logic:notEqual>
		        <td> <html:button property="butsubmit" onclick ="submitform(this.form,'commit')">提交</html:button> </td>
		        <td> <html:button property="butesc" onclick ="escform(this.form,'commit')">退出</html:button> </td>
		      </tr>
			</table>
		</html:form>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>
		<script language="JavaScript">
			//给表格的第一行指定单元置焦点
			var focusControl =document.getElementById("DynData").childNodes(0).all("ccode");
			if (focusControl.type != "hidden") {
				focusControl.focus();
			}			
			//修复rowstate状态
			var rowstateField = document.getElementsByName("rowstate");
			var rowidField = document.getElementsByName("rowid");
			for (var intFor = 0; intFor < 2;intFor++) { 
				if (rowidField[intFor].value == "") {
					rowstateField[intFor].value ="3";
				}
			}

			//退出函数
			function escform(thisform,aflag){
				if (saveChange(thisform,aflag) == 0){
					window.open("<bean:message key='Home.page'/>","_self","")
				}
			}

			//校验提交函数
			function validateSubmit(form) {
			//	if (validField(form,1) && validField(form,2) && validLongness(form) && validRepeat(form)) {
				if (validField(form,1) && validLongness(form) && validRepeat(form)) {
					form.submit();
				}
				return;
			}

			//换页函数：提示保存。确定：提交换页；取消：不提交换页
			function changeform(thisform,aflag){
				var strCclass = "00";
				if (aflag != "last"){
					if (thisform.rowstate.length == null) {
						strCclass=thisform.ccode.value;
					} else {
						strCclass=thisform.ccode[aflag].value;
					}
				}
				if (saveChange(thisform,strCclass)==0){
					thisform.cclass.value=strCclass;
					thisform.flag.value="esc";
					thisform.submit();
				}
			}
			
			//增行列属性参数(tdclass,列名,type,size,value,readonly,class,event)
			function fields () { 
				this.aa = new Array("table2textcentertd","ccode","text","16","","false","nright","onfocus='tdfocus(this,<intMaxrow>,\"ccode\")' onchange='tdchange(this.form)'");
				this.ab = new Array("table2textcentertd","cshowc","text","16","","false","nright","onfocus='tdfocus(this,<intMaxrow>,\"cshowc\")' onchange='tdchange(this.form)'");
				this.ac = new Array("","butdel","button","","删除","false","","onfocus='tdfocus(this,<intMaxrow>,\"butdel\")' onclick ='delline(this.form)'");
				this.ad = new Array("","butnext","button","","编辑","false","","onclick='changeform(this.form,<intMaxrow>)'");
			} 

			//必录校验参数(列名,提示内容)
			function required () { 
				this.aa = new Array("ccode", "代码 不能为空！");
				this.ab = new Array("cshowc", "代码说明 不能为空！");
			} 

			//有效校验参数(列名,提示内容,规则)
			//function masks () { 
			//	this.aa = new Array("ccode", "代码 必须是数字！","^\\d*$");
			//} 
			
			//长度校验参数(列名,提示内容,要求,长度)
			//要求--等于：1；大于：2；小于：3
			//如果长度为0，则区第一个有效字段的数据长度为标准
			function longness () { 
				this.aa = new Array("ccode","代码 长短不一致！",1,0);
			} 
			
			//重复校验参数(列名,提示内容)
			function repeat () { 
				this.aa = new Array("ccode", "代码 重复！");
			} 
		</script>
	</BODY>
</html:html>
