<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<%@page import="com.htyz.*"%>
<jsp:useBean id="news" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="ust" scope="session" class="system.userStyle"/>
<jsp:useBean id="beanElearnTools" scope="request" class="com.htyz.beanElearnTools"/>
<html>
<head>
<title>Untitled Document</title>
<link href="../style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<script language="javascript">
function shan(){
if (confirm("您真的要删除选定的信息吗？")){
     document.form1.submit();
  }
  }

function CA(){
  for (var i=0;i<document.forms[0].elements.length;i++)
    {
    var e=document.forms[0].elements[i];
    if (e.name!="allbox")
      e.checked=document.forms[0].allbox.checked;
    }
}
//-->
</script>
<body bgcolor="#FFFFFF" text="#000000">
 
<form name="form1" method="post" action="newsadd?action=del">
 <DIV align="right">
	<%
	news.executeSelect("select * from t_news where news_satuts='1' order by news_date desc");
	String pageno = (String)request.getParameter("pageno");
	if (pageno == null) pageno = "1";
	int listCount = Integer.parseInt(ust.getLines_in_page());
	int pageNum = Integer.parseInt(pageno);
	int maxCount = java.lang.Math.min(news.getRowCount(), (pageNum-1)*listCount+listCount);
	int i_count = (int)java.lang.Math.ceil((double)news.getRowCount()/listCount);
	out.print("共" + i_count + "页&nbsp;");
	for (int i=1; i<=i_count; i++)
	if (i == pageNum)
	{
		out.print(pageno);
	}
	else
	{
	%>
	<A href="newslist.jsp?pageno=<%=i%>"><%=i%></A>
	<%
	}
	%>
	</DIV>
  <table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="90%">
    <tr align="center"> 
      <td height="20">新闻标题</td>
      <td>类别</td>
      <td>发表时间</td>
      <td>浏览数</td>
      <td>更新人</td>
    </tr>
    <%//news.executeSelect("select* from t_news where news_satuts='0'");
	//for(int i=0;i<news.getRowCount();i++)
	//{
	%>
	<%
	if( news.getRowCount()>0)
	{
		for (int i=(pageNum-1)*listCount; i<maxCount; i++)
		{
		%>
    <tr> 
      <td height="20"> <a href="newsin.jsp?newsid=<%=news.getFieldValue("news_id",i)%>" target="newsshow"> 
        <%=news.getFieldValue("news_title",i)%> </a></td>
      <td align="center"><%=news.getFieldValue("news_type",i)%></td>
      <td align="center"><%=beanElearnTools.FormatDate(news.getFieldValue("news_date",i))%></td>
      <td align="center"><%=news.getFieldValue("news_count",i)%></td>
      <td align="center"><%=news.getFieldValue("news_man",i)%></td>
    </tr>
    <%}
	}%>
  </table>
  <div align="center"></div>
</form>
</body>
</html>
