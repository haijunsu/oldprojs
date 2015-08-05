<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<%
String userright= (String)session.getAttribute("right");
if((String)session.getAttribute("userid")==null){
response.sendRedirect("login.jsp");
	%>
<script>
function wqp()
{
    window.open(top.location,'RUNMIT_ELS','resizable=yes,scrollbars=auto,toolbar=no,location=0,menubar=no,status=yes,fullscreen=1');
    window.focus();
    //document.closes.Click();
}
if(window.name != "RUNMIT_ELS")	wqp();
</script>
<%}else{%>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<frameset rows="80,*" frameborder="NO" border="0" framespacing="0">
  <frame src="<%=request.getContextPath()%>/maintop.htm" name="topFrame" scrolling="NO" noresize >
  <frame src="<%=request.getContextPath()%>/mainmenu.jsp" name="mainFrame">
</frameset>
<noframes><body>

</body></noframes>
</html>
<%}%>
