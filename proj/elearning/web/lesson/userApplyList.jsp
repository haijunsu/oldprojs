<%@ page contentType="text/html;charset=GB2312" %>
<jsp:useBean id="userApplyListBgd" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="userApplyListEds" scope="request" class="com.htyz.util.BeanDateFormat"/>
<%
String userApplyListUserid = (String)session.getAttribute("userid");
if (userApplyListUserid==null||userApplyListUserid.equals(""))
	response.sendRedirect(request.getContextPath()+"/login.jsp");
String userApplyListSql = "SELECT uc.user_id AS user_id, uc.class_id AS class_id,uc.apply_time,uc.apply_reason,uc.approval_time, c.class_name AS class_name,c.pass_stander as pass_stander,o.code_namec As type FROM t_user_apply uc, t_user u, t_class c,t_code o WHERE uc.class_id = c.class_id  AND (uc.user_id = u.user_id OR uc.user_id = u.group_id) AND (o.code_id='0006' and o.code_value=uc.apply_status) AND u.user_id = '" + userApplyListUserid + "'";
userApplyListBgd.executeSelect(userApplyListSql);
%>
<TABLE border="0" width="100%"> 
	<TR>
		<TD><B>我所有申请的课程</B>================(共申请过<%=userApplyListBgd.getRowCount()%>门课程)</TD>
	</TR>
	<TR>
	<TD>
	<!-- 开始显示课程表 -->
	<TABLE width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#eeeeee">
	<TR bgcolor="#CCCC55" align="center" Height="25"><TD>课程名称</TD><TD>课程类型</TD><TD>申请日期</TD><TD>申请原因</TD><TD>目前状态</TD><TD>批准日期</TD></TR>
<%
	for (int i=0; i<userApplyListBgd.getRowCount(); i++)
	{%>
	<TR Height="20">
		<TD><A href=../lesson/lessonInfo.jsp?classid=<%=userApplyListBgd.getFieldValue("class_id", i)%> target=_blank><%=userApplyListBgd.getFieldValue("class_name", i)%></A>
		</TD>
		<TD>
			<%=userApplyListBgd.getFieldValue("user_id",i).trim().length()==2?"必修课":"选修课"%>
		</TD>
		<TD>
			<%=userApplyListEds.format(userApplyListBgd.getFieldValue("apply_time",i),3)%>
		</TD>
		<TD>
			<%=userApplyListBgd.getFieldValue("apply_reason",i)%>
		</TD>
		<TD>
			<%=userApplyListBgd.getFieldValue("type",i)%>
		</TD>
		<TD>
			<%=userApplyListEds.format(userApplyListBgd.getFieldValue("approval_time",i),3)%>
		</TD>
	</TR>
<% } %>
	</TABLE>
	</TD>
    </TR>
</TABLE>
