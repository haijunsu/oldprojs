<%@page contentType="text/html;charset=GB2312"%>
<%@page import="com.htyz.*"%>
<jsp:useBean id="news" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<html>
<head>
<title><%=request.getParameter("title")%></title>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<table width="90%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td align="center" height="1" bgcolor="#000000"></td>
  </tr>
  <%news.executeSelect("select * from t_news where news_id='"+request.getParameter("newsid")+"'");
  String news_img=news.getFieldValue("news_img",0);
 String content=news.getFieldValue("news_content",0);
 %>
  <tr> 
    <td align="center" height="20" bgcolor="#CCCCCC"><b><%=news.getFieldValue("news_title",0)%></b></td>
  </tr>
  <tr> 
    <td bgcolor="#000000" height="1"></td>
  </tr>
  <tr> 
    <td bgcolor="#FFFFFF"><br>
		  <%if(!news_img.equals("")){%>
			  <img src="<%=news_img%>" width="200" height="200" align="left" border="1">
		  <%}%>
      <%=ets.strFormat(content)%> </td>
  </tr>
  <%int count=Integer.parseInt(news.getFieldValue("news_count",0));
  int count1=count+1;
  news.execute("update t_news set news_count="+count1+" where news_id='"+request.getParameter("newsid")+"'");%>
</table>
<br><br><br>
<table bgcolor="#EEEEEE" width="90%" border="0" cellspacing="0" cellpadding="0" align="center">
<tr><td align="center"><a href=javascript:window.close();>--== ¹Ø±Õ±¾Ò³ ==--</a></td></tr>
</table>
</body>
</html>
