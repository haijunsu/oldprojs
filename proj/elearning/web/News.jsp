<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<%@page import="com.htyz.*"%>
<jsp:useBean id="news" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="newstype" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<html>
<head>
<title>新闻页面</title>
<link rel="stylesheet" href="/learning/jsp/xbz.css" type="text/css">
</head>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<body>

<p><span class="xbz">新闻分类列表</span><br>

<%
String s_type, s_typeName;
String s_Sql;
String userid = (String)session.getAttribute("userid");
newstype.QueryCode("0006");


for(int i=0; i<newstype.getCodeCount(); i++)
{
	s_type = newstype.getCodeValue("code_value", i);
	s_typeName = newstype.getCodeValue("code_namec", i);
	if(userid == null||userid.equals(""))
	{
		if(s_type.equals("1"))
			continue;
	}
	s_Sql = "SELECT * FROM tnews WHERE type_id = '" + s_type + "' AND news_status = '1' ORDER BY news_date DESC";
	news.executeSelect(s_Sql);
	if(news.getRowCount() > 0)
	{
	%>
	
<table width="100%" border="0" cellspacing="0" cellpadding="1">
  <tr>
    <td bgcolor="#ffffff"> 
      <table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#FFFFFF" class="xbz">
        <tr> 
          <td bgcolor="#eeeeee" height="20"><%=s_typeName%></td>
        </tr>
        <%
		for(int j=0; j<news.getRowCount(); j++)
		{
		%>
        <tr> 
          <td bgcolor="#eeeeee" height="20"><a href=/NewsDisplay.jsp?newsid=<%=news.getFieldValue("news_id",j)%>><%=news.getFieldValue("news_title",j)%></a>[<%=ets.FormatDate(news.getFieldValue("news_date",j))%>]</td>
        </tr>
        <%
		}
		%>
      </table>
      
    </td>
  </tr>
</table>
<%
	}
}
%>
</body>
</html>
	