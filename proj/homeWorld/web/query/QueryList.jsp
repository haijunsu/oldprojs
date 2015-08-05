<!-- 通过数组来显示查询结果 -->
<%@ page language="java"
 contentType="text/html; charset=GBK"
 pageEncoding="GBK"%>
<% 
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
%>  
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<jsp:useBean id="qc" scope="request" class="com.idn.query.QueryContents"/>


<html:html locale="true">
	<HEAD>

<META http-equiv="Content-Type" content="text/html; charset=GBK">
<META HTTP-EQUIV="Cache-Control" CONTENT="No-cache">
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<LINK href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css">
		
		<TITLE><bean:write name="qc" property="htmlHeader" filter="false"/></TITLE>
	</HEAD>

	<BODY>
		<H2><bean:write name="qc" property="htmlTitle" filter="false"/></H2>
		<table border="1">
			<tbody>
			<!--写表头-->
			<tr>
				<% String str; %>
				<logic:iterate id="fieldlabel" name="qc"  property="fieldLabel" indexId="index">
				<% str = "formula.column[" + index + "].display"; %>
					<logic:match name="qc" property='<%=str%>' value="true">
						<td><bean:write name="fieldlabel" filter="false"/></td>
					</logic:match>
				</logic:iterate>
			</tr>
			<!--写表头结束-->
			<!--写表体-->
			<logic:iterate id="rowHtml" name="qc" property="columnsHtmlCode" indexId="row">
			<tr>
				<logic:iterate id="cellHtml" name="rowHtml" indexId="col">
					<% str = "formula.column[" + col + "].display"; %>
					<logic:match name="qc" property='<%=str%>' value="true">
						<td><bean:write name="cellHtml" filter="false"/></td>
					</logic:match>
					
				</logic:iterate>
			</tr>
			</logic:iterate>
			<!--写表体结束-->
			</tbody>
		</table>
			<!--写分页代码-->
			<logic:greaterThan name="qc" property="pagesCtrl.rowsPerPage" value="1">
				<bean:write name="qc" property="pagesCtrl.controlPageCodes" filter="false"/>
			</logic:greaterThan>
			<!--写分页代码结束-->
	</BODY>
</html:html>
