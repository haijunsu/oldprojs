<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="bqcTypes" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="bqcStatus" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="bld" scope="request" class="lessadmin.beanLessonDetail"/>
<jsp:useBean id="ust" scope="session" class="system.userStyle"/>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%
String navigation = (String)request.getAttribute("navigation");
String catalog = (String)request.getAttribute("catalog");
String codeid = (String)request.getAttribute("codeid");
String codevalue = (String)request.getAttribute("codevalue");
String action = (String)request.getAttribute("action");
%>
<HTML>
<HEAD>
<Script language="javascript">
<!--
function check(){
if (document.addForm.classname.value.length==0){
alert("课程名称不能为空！！");
document.addForm.classname.focus();
return;
}
document.addForm.submit();
}

-->
</Script>
<TITLE>课程列表</TITLE>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
<%=navigation%>
<%@ include file="/lessadmin/lessonSearch.jsp" %>
<%=catalog%> 课程列表：
<%
if (bgd.getRowCount()>0)
{
%>
<TABLE  border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" class="xbz" width="100%">
	<TR bgcolor="#CCCCCC" align="center" Height="25">
		<TD>课程名称</TD>
		<TD>课程<br>类型</TD>
		<TD>关键字</TD>
		<TD width="250">描述</TD>
		<TD>课时</TD>
		<TD>通过<br>标准</TD>
		<TD>课程<br>状态</TD>
	</TR>
	<%
	String pageno = (String)request.getAttribute("pageno");
	if (pageno == null) pageno = "1";
	int listCount = Integer.parseInt(ust.getLines_in_page());
	int pageNum = Integer.parseInt(pageno);
	int maxCount = java.lang.Math.min(bgd.getRowCount(), (pageNum-1)*listCount+listCount);
	for (int i=(pageNum-1)*listCount; i<maxCount; i++)
	{
	%>
	<TR>
		
    <TD> <A href='chapterList?reload=true&classid=<%=bgd.getFieldValue("class_id", i)%>' target="left"><%=bgd.getFieldValue("class_name", i)%></A>&nbsp; 
    </TD>
		
    <TD> 
      <%
			for (int j=0; j<bqcTypes.getCodeCount(); j++)
			{
				if (bqcTypes.getCodeValue("code_value", j).equals(bgd.getFieldValue("class_type", i)))
				{
					out.print(bqcTypes.getCodeValue("code_namec", j));
					break;
				}
			}
			%>
      &nbsp; </TD>
		
    <TD><%=bgd.getFieldValue("keywords",i)+"&nbsp;"%> </TD>
		
    <TD width="250"> <%=bgd.getFieldValue("description", i)+"&nbsp;"%> </TD>
		
    <TD> <%=bgd.getFieldValue("class_time", i)%> </TD>
		
    <TD> <%=bgd.getFieldValue("pass_stander", i)%> </TD>
		
    <TD> 
      <%
			for (int j=0; j<bqcStatus.getCodeCount(); j++)
			{
				if (bqcStatus.getCodeValue("code_value", j).equals(bgd.getFieldValue("class_Status", i)))
				{
					out.print(bqcStatus.getCodeValue("code_namec", j));
					break;
				}
			}
			%>
    </TD>
	<%
	}
	%>
	</TR>
</TABLE>
<TABLE>
<TR>
	<TD>
		<%
			int i_count = (int)java.lang.Math.ceil((double)bgd.getRowCount()/listCount);
		%>
		共<%=i_count%>页&nbsp;
		<%
			for (int i=1; i<=i_count; i++)
			if (i == pageNum)
			{
				out.print(pageno);
			}
			else
			{
			%>
			<A href="lessonList?action=<%=action%>&pageno=<%=i%>&codeid=<%=codeid%>&codevalue=<%=codevalue%>"><%=i%></A>
			<%
			}
			%>
	</TD>
</TR>
</TABLE>
<%
}
else
{
	out.println("没有记录！");
	
}
if (action.equals("showDetail"))
{
%>
<FORM id="addForm" name="addForm" method="get" action="lessonList">
<TABLE>
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
		
      <TD>
        <INPUT name="classtime" type="text" id="classtime" value="<%=bld.getClassTime()%>" size="50">
      </TD>
	</TR>
	<TR>
		<TD>通过标准</TD>
		<TD><INPUT type="text" id="passstander" name="passstander" value='<%=bld.getPassStander()%>' size="50"></TD>
	</TR>
	<TR>
		<TD colspan="2">
			<INPUT type="hidden" id="action" name="action" value="add">
			<INPUT type="hidden" id="codeid" name="codeid" value="<%=codeid%>">
			<INPUT type="hidden" id="codevalue" name="codevalue" value="<%=codevalue%>">
			<INPUT type="button" value="添加课程" onclick=check()>
		</TD>
	</TR>
</TABLE>
</FORM>
<%
}
%>
</BODY>
</HTML>
		
		
