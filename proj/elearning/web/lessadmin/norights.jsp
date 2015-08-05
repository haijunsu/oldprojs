<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
</head>
<%
String treeroot = (String)request.getAttribute("treeroot");
%>
<frameset rows="25,*" border="0">
  <frame name="banner" scrolling="no" noresize target="contents" src="../Menus.jsp">
  <frameset cols="180,*">
    <frame name="left" src='about:blank'>
    <frame name="right" src="<%=request.getContextPath()%>/noright.jsp">
  </frameset>
  <noframes>
  <body>

  <p>此网页使用了框架，但您的浏览器不支持框架。</p>

  </body>
  </noframes>
</frameset>

</html>
