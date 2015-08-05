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
String codeid = (String)request.getAttribute("codeid");
String codevalue = (String)request.getAttribute("codevalue");
String action = (String)request.getAttribute("action");
%>
<HTML>
<HEAD>
<TITLE>课程列表</TITLE>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
<%=navigation%>
<%@ include file="/lessadmin/lessonSearch.jsp" %>

课程列表： 
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
		
    <TD> <A href='topicCreator?classid=<%=bgd.getFieldValue("class_id", i)%>' title='试题库管理'><%=ets.stringToHtml(bgd.getFieldValue("class_name", i))%></A>&nbsp; 
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
		
    <TD><%=ets.stringToHtml(bgd.getFieldValue("keywords",i))%> </TD>
		
    <TD width="250"> 
<%
			String title = bgd.getFieldValue("description", i).trim();
			title = ets.stringToHtml(title);
			if (title.indexOf("<") > 0)
				title = title.substring(0, title.indexOf("<"));
			if (title.indexOf(" ") > 0)
				title = title.substring(0, title.indexOf(" ") < 50?title.indexOf(" "):50);
			else
				title = title.substring(0, title.length() > 50?50:title.length());
%>
      	<A href=../lesson/lessonInfo.jsp?showback=show&classid=<%=bgd.getFieldValue("class_id", i)%> title="显示课程详细信息"><%=title%></A>&nbsp;
    </TD>
		
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
			<A href="topicList?action=<%=action%>&pageno=<%=i%>&codeid=<%=codeid%>&codevalue=<%=codevalue%>"><%=i%></A>
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
%>
</BODY>
</HTML>
		
		
