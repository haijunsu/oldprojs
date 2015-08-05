<%@page contentType="text/html;charset=GBK"%>
<%@page session="true"%>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="bqc" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%
String userid = (String)session.getAttribute("userid");
%>
<HTML>
<HEAD>
<TITLE>用户详细信息</TITLE>
<link href="/elearning/style.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
<%@ include file="/elearning/admin/userSearch.jsp" %>
<%=bgd.getFieldValue("user_id", 0)%>详细信息
<FORM id="detailForm" name="detailForm" acton="userDetail" method="get">
<TABLE class="table003">
	<TR>
		<TD>姓名</TD>
		<TD>&nbsp;<%=bgd.getFieldValue("user_name", 0)%>&nbsp;</TD>
		<TD>性别</TD>
		<TD>&nbsp;<%=bgd.getFieldValue("sex", 0).equalsIgnoreCase("M")?"男":"女"%>&nbsp;</TD>
		<TD>出生日期</TD>
		<TD>&nbsp;<%=ets.FormatDate(bgd.getFieldValue("birthday", 0))%>&nbsp;</TD>
	</TR>
	<TR>
		<TD>邮件地址</TD>
		<TD colspan="5">&nbsp;<A href=mailto:<%=bgd.getFieldValue("email", 0)%>><%=bgd.getFieldValue("email", 0)%></A>&nbsp;</TD>
	</TR>
	<TR>
		<TD>电话</TD>
		<TD colspan="5">&nbsp;<%=bgd.getFieldValue("phone", 0)%>&nbsp;</TD>
	</TR>
	<TR>
		<TD>单位</TD>
		<TD colspan="5">&nbsp;<%=bgd.getFieldValue("company", 0)%>&nbsp;</TD>
	</TR>
	<TR>
		<TD>用户部门</TD>
		<TD colspan="5">
		<%
		if (ets.isAdmin(userid))
		{
		%>
			<select id='group_id' name='group_id' size='1'>
				<%
				bqc.QueryCode("0002");
				for (int j=0; j<bqc.getCodeCount(); j++)
				{
				%>
				<option <%=bqc.getCodeValue("code_value", j).equals(bgd.getFieldValue("group_id", 0))?"SELECTED":""%> value='<%=bqc.getCodeValue("code_value", j)%>'><%=bqc.getCodeValue("code_namec", j)%></option>
				<%}%>
			</select>
		<%
		}
		else
		{
			bqc.QueryCode("0002", bgd.getFieldValue("group_id", 0));
			out.println(bqc.getCodeValue("code_namec", 0));
		}
		%>	
		</TD>
	</TR>
	<TR>
		<TD>注册时间</TD>
		<TD colspan="5">&nbsp;<%=ets.FormatDate(bgd.getFieldValue("register_time", 0))%>&nbsp;</TD>
	</TR>
	<%
	if (ets.isAdmin(userid))
	{
	%>
	<TR>
		<TD>用户状态</TD>
		<TD colspan="5">&nbsp;
		<select id='user_status' name='user_status' size='1'>
			<%
			bqc.QueryCode("0001");
			for (int j=0; j<bqc.getCodeCount(); j++)
			{
			%>
			<option <%=bqc.getCodeValue("code_value", j).equals(bgd.getFieldValue("user_status", 0))?"SELECTED":""%> value='<%=bqc.getCodeValue("code_value", j)%>'><%=bqc.getCodeValue("code_namec", j)%></option>
			<%}%>
		</select>
		</TD>
	</TR>
	<TR>
		<TD colspan="6">用户角色<BR>
			<%
			boolean isChecked;
			bqc.QueryCode("0003");
			for (int j=0; j<bqc.getCodeCount(); j++)
			{
				isChecked = ets.isMenuShow(ets.parseRight(bqc.getCodeValue("code_value", j)), bgd.getFieldValue("user_right", 0));
			%>
			<input id='userright<%=j%>' name='userright<%=j%>' type='checkbox' <%=isChecked?"CHECKED":""%> value='<%=bqc.getCodeValue("code_value", j)%>'><%=bqc.getCodeValue("code_namec", j)%>
			<%
			}
			%>
			<INPUT type="hidden" name="rightcount" id="rightcount" value="<%=bqc.getCodeCount()%>">
		</TD>
	</TR>
	<TR>
		<TD colspan="6" align="center">
		<INPUT type="hidden" name="action" id="action" value="update">
		<INPUT type="hidden" name="userid" id="userid" value="<%=bgd.getFieldValue("user_id", 0)%>">
		<INPUT type="button" onClick=window.location.href="userDetail?action=resetpass&userid=<%=bgd.getFieldValue("user_id", 0)%>" value="密码重置">
		<INPUT type="submit" value="确认修改">
		</TD>
	</TR>
	<%
	}
	%>
</TABLE>
</FORM>
</BODY>
</HTML>
		
		
