<%@page contentType="text/html;charset=GBK"%>
<%@page session="true"%>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="bqcTypes" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="bqcStatus" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="bld" scope="request" class="lessadmin.beanLessonDetail"/>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%
String action = (String)request.getAttribute("action");
String navigation = (String)request.getAttribute("navigation");
String classid = (String)request.getAttribute("classid");
String lessonid = (String)request.getAttribute("lessonid");
String addtype = (String)request.getAttribute("addtype");
%>
<HTML>
<HEAD>
<TITLE>课程详细信息</TITLE>
<link href="/elearning/style.css" rel="stylesheet" type="text/css">
<SCRIPT language="JavaScript">
	function formSubmit(action)
	{
	if (document.detailForm.classname.value.length==0){
   alert("课程名称不能为空！！");
   document.detailForm.classname.focus();
   return;
}
		document.detailForm.action.value = action;
		if(action=="delete")
		{
			if(!confirm("确认要删除课程吗？\n注意：删除课程将删除其所有的章节，且无法恢复！"))
				return false;
		}
		document.detailForm.submit();
		return true;
	}
</SCRIPT>
</HEAD>
<BODY>
<%=navigation%>
<%@ include file="/elearning/lessadmin/lessonSearch.jsp" %>
<%=bld.getClassName()%>详细信息
<FORM id="detailForm" name="detailForm" method="get" target="left">
<TABLE class="table003">
	<TR>
		<TD>课程名称</TD>
		<TD><INPUT type="text" id="classname" name="classname" value='<%=bld.getClassName()%>' size="50"></TD>
	</TR>
	<TR>
		<TD>课程类型</TD>
		<TD>
			<%
				for (int i=0; i<bqcTypes.getCodeCount(); i++)
				{
					if (bld.getClassType().equals(bqcTypes.getCodeValue("code_value", i)))
						out.println("<INPUT type=\"radio\" id=\"classtype\" name=\"classtype\" checked value=\"" + bqcTypes.getCodeValue("code_value", i) +"\">" + bqcTypes.getCodeValue("code_namec", i));
					else
						out.println("<INPUT type=\"radio\" id=\"classtype\" name=\"classtype\" value=\"" + bqcTypes.getCodeValue("code_value", i) +"\">" + bqcTypes.getCodeValue("code_namec", i));
				}
			%>
		</TD>
	</TR>
	<TR>
		<TD>关键字</TD>
		<TD><INPUT type="text" id="keywords" name="keywords" value='<%=bld.getKeywords()%>' size="50"></TD>
	</TR>
	<TR>
		<TD>描述</TD>
		<TD>
        <TEXTAREA name="description" cols="50" id="description"><%=bld.getDescription()%></TEXTAREA>
      </TD>
	</TR>
	<TR>
		<TD>课时</TD>
		<TD><INPUT type="text" id="classtime" name="classtime" value='<%=bld.getClassTime()%>' size="50"></TD>
	</TR>
	<TR>
		<TD>通过标准</TD>
		<TD><INPUT type="text" id="passstander" name="passstander" value='<%=bld.getPassStander()%>' size="50"></TD>
	</TR>
	<TR>
		<TD>课程状态</TD>
		<TD>
			<SELECT id="classstatus" name="classstatus">
			<%
				for (int i=0; i<bqcStatus.getCodeCount(); i++)
				{
					if (bld.getClassStatus().equals(bqcStatus.getCodeValue("code_value", i)))
						out.print("<OPTION SELECTED ");
					else
						out.print("<OPTION ");
					out.println(" value=" + bqcStatus.getCodeValue("code_value", i) +">" + bqcStatus.getCodeValue("code_namec", i) + "</OPTION>");
				}
			%>
			</SELECT>
	</TR>
	<TR>
		<TD colspan="2" align="center">
		<INPUT type="hidden" name="action" id="action" value="update">
		<INPUT type="hidden" name="object" id="object" value="class">
		<INPUT type="hidden" name="reload" id="reload" value="true">
		<INPUT type="hidden" name="classid" id="classid" value="<%=bld.getClassId()%>">
		<INPUT type="button" onClick=window.location.href="chapterList?action=new&addtype=chapter&classid=<%=bld.getClassId()%>" value="添加章">
		<INPUT type="button" onClick=javascript:formSubmit("update") value="修改">
		<INPUT type="button" onClick=javascript:formSubmit("delete") value="删除">
		</TD>
	</TR>
</TABLE>
</FORM>
</BODY>
</HTML>
		
		
