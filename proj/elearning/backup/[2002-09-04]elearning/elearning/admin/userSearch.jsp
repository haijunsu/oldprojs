<jsp:useBean id="userSearchEts" scope="request" class="com.htyz.beanElearnTools"/>
<%
String userSearchUserid = (String)session.getAttribute("userid");
%>
<FORM id="queryForm" name="queryForm" method="get" action="/servlet/admin/userList">
<TABLE class="table003">
	<TR>
		<TD>请输入查找内容：
		<INPUT type="text" name="queryid" id="queryid" value="" size="30">
		</TD>
		<TD>
		<SELECT name="querycoloum" id="querycoloum">
			<OPTION value="user_id">用户ID</OPTION>
			<OPTION value="user_name">姓名</OPTION>
			<OPTION value="email">电子邮件</OPTION>
			<OPTION value="company">单位</OPTION>
			<OPTION value="group_id">部门</OPTION>
			<%
				if(userSearchEts.isAdmin(userSearchUserid))
				{
				%>
				<OPTION value="user_user_status">用户状态</OPTION>
				<%
				}
			%>
		</SELECT>
		</TD>
		<TD>
		<INPUT type="submit" value="查找">
		</TD>
	</TR>
</TABLE>
</FORM>