<%@page contentType="text/html;charset=GB2312"%>
<jsp:useBean id="lessonSearchEts" scope="request" class="com.htyz.beanElearnTools"/>
<%
String lessonSearchUserid = (String)session.getAttribute("userid");
String lessonSearchArg = (String)request.getAttribute("arg");
String lessonSearchArgValue = (String)request.getAttribute("argValue");
String applyType = (String)request.getAttribute("applyType");
if (applyType == null)
	applyType = "";
%>
<FORM id="queryForm" name="queryForm" method="get">
<TABLE class="table003">
	<TR>
		<TD>请输入查找内容：
		<INPUT type="text" name="queryid" id="queryid" value="" size="30">
		<INPUT type="hidden" name="action" id="action" value="query">
		<INPUT type="hidden" name="applyType" id="applyType" value="<%=applyType%>">
<%
		if (lessonSearchArg!=null)
		{
%>
		<INPUT type="hidden" name="<%=lessonSearchArg%>" id="<%=lessonSearchArg%>" value="<%=lessonSearchArgValue%>">
<%
		}
%>
		</TD>
		<TD>
		<SELECT name="querycoloum" id="querycoloum">
			<OPTION value="class_name">课程名称</OPTION>
			<OPTION value="class_type">课程类型</OPTION>
			<OPTION value="keywords">关键字</OPTION>
			<OPTION value="description">描述</OPTION>
			<%
				if(lessonSearchEts.isAdmin(lessonSearchUserid))
				{
				%>
				<OPTION value="class_status">课程状态</OPTION>
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