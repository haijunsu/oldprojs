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

  <p>����ҳʹ���˿�ܣ��������������֧�ֿ�ܡ�</p>

  </body>
  </noframes>
</frameset>

</html>
