<jsp:useBean id="userSearchEts" scope="request" class="com.htyz.beanElearnTools"/>
<%
String userSearchUserid = (String)session.getAttribute("userid");
%>
<FORM id="queryForm" name="queryForm" method="get" action="/servlet/admin/userList">
<TABLE class="table003">
	<TR>
		<TD>������������ݣ�
		<INPUT type="text" name="queryid" id="queryid" value="" size="30">
		</TD>
		<TD>
		<SELECT name="querycoloum" id="querycoloum">
			<OPTION value="user_id">�û�ID</OPTION>
			<OPTION value="user_name">����</OPTION>
			<OPTION value="email">�����ʼ�</OPTION>
			<OPTION value="company">��λ</OPTION>
			<OPTION value="group_id">����</OPTION>
			<%
				if(userSearchEts.isAdmin(userSearchUserid))
				{
				%>
				<OPTION value="user_user_status">�û�״̬</OPTION>
				<%
				}
			%>
		</SELECT>
		</TD>
		<TD>
		<INPUT type="submit" value="����">
		</TD>
	</TR>
</TABLE>
</FORM>