<jsp:useBean id="lessonSearchEts" scope="request" class="com.htyz.beanElearnTools"/>
<link href="/elearning/style.css" rel="stylesheet" type="text/css">
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%
String lessonSearchUserid = (String)session.getAttribute("userid");
String lessonSearchApplyType = (String) request.getAttribute("applyType");
%>
<FORM id="queryForm" name="queryForm" method="get">
<TABLE>
	<TR>
		<TD>请输入查找内容：
		<INPUT type="text" name="queryid" id="queryid" value="" size="30">
		<INPUT type="hidden" name="action" id="action" value="query">
		<INPUT type="hidden" name="applyType" id="applyType" value="<%=lessonSearchApplyType%>">
		</TD>
		<TD>
		<SELECT name="querycoloum" id="querycoloum">
			<OPTION value="class_name">课程名称</OPTION>
			<OPTION value="class_type">课程类型</OPTION>
			<OPTION value="keywords">关键字</OPTION>
			<OPTION value="description">描述</OPTION>
		</SELECT>
		</TD>
		<TD>
		<INPUT class="input1" type="submit" value="查找">
		</TD>
	</TR>
</TABLE>
</FORM>