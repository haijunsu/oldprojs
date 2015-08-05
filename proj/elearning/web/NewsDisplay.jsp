<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<%@page import="com.htyz.*"%>
<jsp:useBean id="news" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="newstype" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<html>
<head>
<title>新闻明细显示</title>
<link rel="stylesheet" href="3.css" type="text/css">
</head>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<body leftmargin="0" bgcolor="F7F3F7">
<jsp:include page="/MsgNotify.jsp"/>

  <table width="98%" border="0" cellspacing="1" cellpadding="1">
    <tr> 
	  <%
String s_newsid = request.getParameter("newsid");
if(s_newsid == null)
{
%>
                          <font color=red>没有新闻ID，不能显示新闻！</font> 
                          <%
}
else
{
	String s_Sql;
	s_Sql = "SELECT * FROM tnews WHERE news_id = '" + s_newsid + "'";
	news.executeSelect(s_Sql);
	if(news.getRowCount() > 0)
	{
	%>
      <td bgcolor="#000000"> 
        <table width="100%" border="0" cellspacing="0" cellpadding="1" >
          <tr> 
            <td bgcolor="#f7f3f7" class="jnfont6" align="center"> <%=news.getFieldValue("news_title", 0)%>[<%=ets.FormatDate(news.getFieldValue("news_date",0))%>] 
            </td>
          </tr>
          <tr bgcolor="#f7f3f7"> 
		

            <td class="jnfont3">
              <table width="95%" border="0" cellspacing="0" cellpadding="1" align="center">
                <tr> 
                  <td bgcolor="#000000"> 
                    <table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#FFFFFF">
                      <tr> 
                        <td>
                                                    
                          <p align=center>&nbsp;</p>
                          
                        <p><%=news.getFieldValue("news_content", 0)%></p>
                          <%
	}
	else
	{
	%>
                          <font color=red>没有找到第<%=s_newsid%>号新闻！</font> 
                          <%
	}
}
%>
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
          <tr bgcolor="#f7f3f7"> 
            
          <td class="jnfont3" height="10"></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
<div align="center">==<a href="javascript:window.close()">关闭窗口</a>== </div>
</body>
</html>
	