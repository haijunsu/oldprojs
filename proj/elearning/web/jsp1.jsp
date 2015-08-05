<%@ page contentType="text/html; charset=GB2312" %>
<html>
<head>
<title>
Jsp1
</title>
</head>
<script language=javascript>
function ok()
{
document.form1.action="jsp1.jsp";
document.form1.submit();
}
</script>

<body>
<form method="post" name="form1" action="">
<input name="a" type="button" value="<%out.print("bbbb");%>">

</form>

<%
  String str=request.getParameter("a");
  if(str==null)
  {
%>
<script language=javascript>
ok();
</script>
<%

  }
   out.println("the value======"+str);

%>


</body>
</html>
