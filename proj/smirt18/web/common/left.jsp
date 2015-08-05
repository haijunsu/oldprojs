<!-- page encoding -->
<%@ page 
language="java"
isErrorPage="false"
contentType="text/html; charset=utf-8"
pageEncoding="utf-8"
%>

<%@ include file="/common/include.jsp" %>
<table bgcolor='#CCCCCC' width='100%' height='100%' cellspacing='0' cellpadding='0' border='0'>
	<tr valign='top'  align='center' height='100' width='100%'>
		<td>
			<html:image page="/common/imgs/all_logo.jpg" alt="SMiRT 18 logo"></html:image>
		</td>
	</tr>
	<logic:present name="smirtMenus">
	<tr valign='top' align='center' width='100%'>
		<td>
			<table width='80%' cellspacing='2' cellpadding='2' border='0' bgcolor='#FFFFFF'>
				<logic:iterate id="menu" name="smirtMenus">
					<tr align='left'>
						<td bgcolor='#CCCCCC'>
							<a href='<bean:write name="menu" property="url" />'>
								<bean:write name="menu" property="menuName" /></a>
						</td>
					</tr>
				</logic:iterate>
	<logic:present name="querysMenu">
				<logic:iterate id="querys" name="querysMenu">
					<tr align='left'>
						<td bgcolor='#CCCCCC'>
							<a href='<bean:write name="rq" property="contextPath"/>/querys.do?action=query&queryid=<bean:write name="querys" property="id" />'>
								<bean:write name="querys" property="queryName" /></a>
						</td>
					</tr>
				</logic:iterate>
	</logic:present>

								<% 
									if (request.isUserInRole("manager")) {
								%>
					<tr align='left'>
						<td bgcolor='#CCCCCC'>
									<a href='<bean:write name="rq" property="contextPath"/>/adminUser.do?action=list'>Manage account</a>
						</td>
					</tr>
								<%
									}
									if (request.isUserInRole("manager")) {
								%>
					<tr align='left'>
						<td bgcolor='#CCCCCC'>
									<a href='<bean:write name="rq" property="contextPath"/>/executeSql.do?action=runSql'>Exceute Sql</a>
						</td>
					</tr>
								<%
									}
								%>
					<tr align='left'>
						<td bgcolor='#CCCCCC'>
									<a href='<bean:write name="rq" property="contextPath"/>/changepassword.do'>Change password</a>
						</td>
					</tr>
					<tr align='left'>
						<td bgcolor='#CCCCCC'>
									<a href='<bean:write name="rq" property="contextPath"/>/logoff'>Logout</a>
						</td>
					</tr>
									
			</table>
		</td>
	</tr>
	</logic:present>
</table>
