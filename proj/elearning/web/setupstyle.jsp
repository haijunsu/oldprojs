<%@page contentType="text/html;charset=GB2312"%>

<jsp:useBean id="ust" scope="request" class="system.userStyle" />
<%//out.print(request.getParameter("text"));%>
<%String text=(String)request.getParameter("text");
String fontcolor=(String)request.getParameter("text1");
String text2=(String)request.getParameter("text2");
String text3=(String)request.getParameter("text3");
String text4=(String)request.getParameter("text4");
String text5=(String)request.getParameter("text5");
String text6=(String)request.getParameter("text6");
String text7=(String)request.getParameter("text7");
String text8=(String)request.getParameter("text8");
String text11=(String)request.getParameter("text11");
String text12=(String)request.getParameter("text12");
String tablewidth=(String)request.getParameter("select10");
String defaultfont=(String)request.getParameter("dfont");
String titlefont=(String)request.getParameter("titlefont");
String headfont=(String)request.getParameter("headfont");
String bbslinkcolor=(String)request.getParameter("select11");

String font=(String)request.getParameter("font");
String stressfont=(String)request.getParameter("stress");
String lines_in_page=(String)request.getParameter("lines_in_page");
%>
 <jsp:setProperty name="ust" property="bgcolor" value="<%=text%>" />
 <jsp:setProperty name="ust" property="fontcolor" value="<%=fontcolor%>" />
 <jsp:setProperty name="ust" property="onmousecolor" value="<%=text2%>" />
 <jsp:setProperty name="ust" property="linkcolor" value="<%=text3%>" />
 <jsp:setProperty name="ust" property="linkstyle" value="<%=text4%>" />
 <jsp:setProperty name="ust" property="titlecolor" value="<%=text5%>" />
 <jsp:setProperty name="ust" property="tablecolor" value="<%=text6%>" />
 <jsp:setProperty name="ust" property="firstrowcolor" value="<%=text7%>" />
 <jsp:setProperty name="ust" property="bbscolor" value="<%=text11%>" />
 <jsp:setProperty name="ust" property="tablebordercolor" value="<%=text12%>" />
 <jsp:setProperty name="ust" property="tabelwidth" value="<%=tablewidth%>" />
  <jsp:setProperty name="ust" property="defaultfont" value="<%=defaultfont%>" />
   <jsp:setProperty name="ust" property="stressfont" value="<%=stressfont%>" />
  <jsp:setProperty name="ust" property="defaulttitlefont" value="<%=titlefont%>" />
   <jsp:setProperty name="ust" property="headerfont" value="<%=headfont%>" />
   <jsp:setProperty name="ust" property="font" value="<%=font%>" />
     <jsp:setProperty name="ust" property="bbslinkcolor" value="<%=bbslinkcolor%>" />
   
   <jsp:setProperty name="ust" property="user_id" value="gg" />
<%//ust.setBgcolor("request.getParameter('text')");%>
<%//ust.setFontcolor(request.getParameter("text1"));%>
<%//ust.setOnmousecolor(request.getParameter("text2"));%>
<%//ust.setLinkcolor(request.getParameter("text3"));%>
<%//ust.setLinkstyle(request.getParameter("text4"));%>
<%//ust.setTitlecolor(request.getParameter("text5"));%>
<%//ust.setTablecolor(request.getParameter("text6"));%>
<%//ust.setFirstrowcolor(request.getParameter("text7"));%>
<%//ust.setBbscolor(request.getParameter("text8"));%>
<%//ust.setBbslinkcolor(request.getParameter("text11"));%>
<%//ust.setTablebordercolor(request.getParameter("text12"));%>
<%//ust.setTabelwidth (request.getParameter("text10"));%>
<%//ust.setDefaultfont (request.getParameter("dfont"));%>
<%//ust.setDefaulttitlefont (request.getParameter("titlefont"));%>
<%//ust.setHeaderfont (request.getParameter("headfont"));%>
<%//ust.setFont (request.getParameter("font"));%>
<%//ust.setStressfont (request.getParameter("stresst"));%>
<%//ust.setLines_in_page (request.getParameter("row"));%>
<%//ust.setUser_id ("xbz");%>
<html>
<%ust.runsql();
//out.print(ust.runsql());
%>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body>
</body>
</html>
