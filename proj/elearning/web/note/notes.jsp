
<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<%@page import="com.htyz.*, elearn.bean_Menus"%>
<%if (session.getAttribute("userid")==null||session.getAttribute("userid").equals("")){%>
<script>
alert("您还没有登录或者是已经连接超时，请重新登录！");
self.close();
</script>
<%}
else{
String id=request.getParameter("classid");
String lessonid=request.getParameter("lessonid");
%>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<frameset cols="144,*" frameborder="NO" border="0" framespacing="0" rows="*"> 
  <frame name="lhs" src="lessonCatalog.jsp?classid=<%=id%>" scrolling="AUTO">
  <frame name="rhs" src="lessonmade.jsp?classid=<%=id%>&lessonid=<%=lessonid%>" scrolling="AUTO" >
</frameset>
<noframes>
<body bgcolor="#FFFFFF" text="#000000">
</body>
</noframes> 
</html>
<%}%>
