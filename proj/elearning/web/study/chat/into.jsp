<%@ page contentType="text/html; charset=gb2312"%>
<%@ page session="true"%>
<html>

<head>
<script language="JavaScript">
function defaultfocus(){
document.loginform.t_username.focus();
}
function check(){
if(document.loginform.t_regname.value||document.loginform.t_username.value)
return true;
else
alert("至少要选择一种身份进入");
return false;
}
</script>
<title>登陆</title>
<link rel="stylesheet" href="mystyles.css">
</head>
<body onload="defaultfocus()" bgcolor="#ccffcc">
<%
String err_msg=(String)session.getValue("message");
if(err_msg==null) 
	err_msg="";
%>
<table border="0" width="80%" aligh="center" bgcolor="#ccffcc" align="center">
  <tr>
	<td width="52%" bgcolor="#ccffcc">
	<h2 align="center"><font color=blue>海阔天空聊天室</font></h2>
	</td>
</tr>
<tr>
	<td width="52%" bgcolor="#ffFFcc">
	<form method="post" name="loginform" action="login.jsp">
	<%
	if(err_msg!=null&&!err_msg.equals("")){	%>
	<center><font color=red><b>错误信息</b></font><br><font color=blue><b>
	<%=err_msg	%></b></font></center>
	<%}%>
	<div align="center"><font size="4"><b>注册用户</b></font><br>
	用户名<input type="text" name="t_regname" size="13"><br>
          密 码
<input type="password" name="t_regpassword" size="13"><br><br>
	<b>游客 </b><br>
          昵 称
<input type="text" name="t_username" size="13"><br><br>
	<input type="submit" name="b_ok" value="进入" onclick="return check();">
	<input type="reset" name="t_cancel" value="取消">
	</p>
	</div>
	</form>
	</td>
	</tr>
	</table>
	<p align="center"><a href="http://java.sun.com" target="_blank">
	-==环天宇正==-<br></a>CopyRihgt &copy; 2002 HuanTian.
	</p>
	</body>
	</html>
