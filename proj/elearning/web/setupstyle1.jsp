<%@page contentType="text/html;charset=GB2312"%>

<jsp:useBean id="ust" scope="request" class="system.userStyle" />
<%//out.print(request.getParameter("text"));%>
<%ust.setBgcolor("+request.getParameter('text')+");%>

<%ust.setFontcolor(request.getParameter("text1"));%>
<%ust.setOnmousecolor("+request.getParameter('text2')+");%>
<%ust.setLinkcolor("+request.getParameter('text3')+");%>
<%ust.setLinkstyle("+request.getParameter('text4')+");%>
<%ust.setTitlecolor("+request.getParameter('text5')+");%>
<%ust.setTablecolor("+request.getParameter('text6')+");%>
<%ust.setFirstrowcolor("+request.getParameter('text7')+");%>
<%ust.setBbscolor("+request.getParameter('text8')+");%>
<%ust.setBbslinkcolor("+request.getParameter('text11')+");%>
<%ust.setTablebordercolor("+request.getParameter('text12')+");%>
<%ust.setTabelwidth ("+request.getParameter('text10')+");%>
<%ust.setDefaultfont ("+request.getParameter('font')+");%>
<%ust.setDefaulttitlefont ("+request.getParameter('titlefont')+");%>
<%ust.setHeaderfont ("+request.getParameter('headfont')+");%>
<%ust.setFont ("+request.getParameter('font')+");%>
<%ust.setStressfont ("+request.getParameter('stresst')+");%>
<%ust.setLines_in_page ("+request.getParameter('row')+");%>
<%ust.setUser_id ("xbz");%>


<html>
<%ust.runsql();%>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">

</head>

<body>

</body>
</html>
