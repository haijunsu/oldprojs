<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="bqc" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="ust" scope="session" class="system.userStyle"/>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%
String userid = (String)session.getAttribute("userid");
String menuid = (String)request.getAttribute("menuid");
String pageno = (String)request.getAttribute("pageno");
String action = (String)request.getAttribute("action");
String message = (String)request.getAttribute("message");
int listCount = Integer.parseInt(ust.getLines_in_page());
int pageNum = Integer.parseInt(pageno);
int maxCount = java.lang.Math.min(bgd.getRowCount(), (pageNum-1)*listCount+listCount);
if (menuid == null||menuid.equals(""))
	menuid = bgd.getFieldValue("menu_id", (pageNum-1)*listCount);
%>
<HTML>
<HEAD>
<TITLE>菜单列表</TITLE>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
<%@ include file="/admin/menuSearch.jsp" %>
<SCRIPT Language="JavaScript">
	parent.right.location.href = "menuList?action=<%=action%>&menuid=<%=menuid%>&message=<%=message%>"
</SCRIPT>

菜单列表<br>
<TABLE border="0">
	<%
	for (int i=(pageNum-1)*listCount; i<maxCount; i++)
	{
	%>
	<TR bgcolor="#EEEEEE">
		<TD>
			<A href='menuList?action=showDetail&menuid=<%=bgd.getFieldValue("menu_id", i)%>' target='right'><%=bgd.getFieldValue("menu_namec", i)%></A>
		</TD>
	</TR>
	<%
	}
	%>
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
			<A href="menuList?pageno=<%=i%>"><%=i%></A>
			<%
			}
			%>
	</TD>
</TR>
</TABLE>
</BODY>
</HTML>
		
		
