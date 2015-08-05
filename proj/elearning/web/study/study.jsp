<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<%@page import="com.htyz.*"%>
<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>

<%if (session.getAttribute("userid")==null||session.getAttribute("userid").equals("")){%>
<script>
alert("您还没有登录或者是已经连接超时，请重新登录！");
self.close();
</script>
<%}
else{

String id=request.getParameter("classid");
String sql="";

 if (id!=null&&!id.equals(""))
{
	
	sql = "UPDATE t_class set peoples = peoples+1 where class_id='"+id+"'" ;
	bgd.execute(sql);
}

%>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">


</head>
<script language="javascript">
state = 0 ;
statecatalog = 0 ;
mailState = 0;
noteState = 0;
bbsState = 0;
chatState = 0;
</script>
<%--<frameset rows="20,459" cols="*" border="1" framespacing="1" frameborder="NO"> --%>
<frameset rows="475,1" cols="*" border="1" framespacing="1" frameborder="NO"> 
  <%--<frame src="top.jsp" noresize frameborder="NO" scrolling="NO">--%>
 <%-- <frameset rows="330,39" cols="*" border="0" framespacing="0" frameborder="YES"> --%>
  <frameset rows="335,34" cols="*" border="0" framespacing="0" frameborder="YES"> 
    <frameset cols="20%,*,15%" name="c" frameborder="YES" border="0" framespacing="1" rows="*"> 
      <frame name="lhs" src="<%=request.getContextPath()%>/lesson/lessonCatalog.jsp?classid=<%=id%>" scrolling="AUTO" >
      <frameset rows="*,2" name="f" border="1" framespacing="1" cols="*"> 
        <frame name="rhs" src="<%=request.getContextPath()%>/lesson/lessonInfo.jsp?classid=<%=id%>" scrolling="AUTO" >
        <frame name="bottomFrame" scrolling="AUTO"  src="bom.jsp" frameborder="YES">
      </frameset>
      <frame name="chat" src="<%=request.getContextPath()%>/servlet/chatroom/chat?room=<%=id%>&action=login">
    </frameset>
    <frame name="" src="<%=request.getContextPath()%>/study/de.jsp">
  </frameset>
</frameset>
<noframes> 
</noframes> 
</html>
<%}%>
