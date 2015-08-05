<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<%@page import="com.htyz.*"%>
<jsp:useBean id="news" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="newstype" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<html>
<head>
<title>新闻页面</title>
</head>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<body>
<TABLE>
<%
String s_type, s_typeName;
String s_Sql;
String userid = (String)session.getAttribute("userid");
int listnum;
newstype.QueryCode("0006");


s_Sql = "SELECT * FROM tnews WHERE type_id <> '1' AND news_status = '1' ORDER BY news_date DESC";
news.executeSelect(s_Sql);
if(news.getRowCount() > 0)
{
	if(news.getRowCount()>5)
	{
		listnum = 5;
	}
	else
	{
		listnum = news.getRowCount();
	}

	for(int j=0; j<listnum; j++)
	{
	%>
	<TR>
		<TD><img height=7 src="<%=request.getContextPath()%>/images/ra.gif" width=7>
		<%
		for(int i=0; i<newstype.getCodeCount(); i++)
		{
			s_type = newstype.getCodeValue("code_value", i);
			s_typeName = newstype.getCodeValue("code_namec", i);
			if(s_type.equals(news.getFieldValue("type_id", j)))
			{
			%>
			[<%=s_typeName%>]
			<%
			}
		}
		%>
		<A href=/NewsDisplay.jsp?newsid=<%=news.getFieldValue("news_id",j)%>><%=news.getFieldValue("news_title",j)%></A>[<%=ets.FormatDate(news.getFieldValue("news_date",j))%>]
		</TD>
	</TR>
	<%
	}
}
%>
	<TR>
		<TD align="right"><A href=News.jsp>更多新闻>>></A></TD>
	</TR>
</TABLE>
</body>
</html>
	