<html>
<head>
<%@ page session="true"%>
<%@ page contentType="text/html;charset=GB2312" %>
<script language="javascript">
function defaultfocus()
{
	document.loginform.t_username.focus();
}
function check(){
	if(document.loginform.t_regname.value||document.loginform.username.value)
	return true;
	else alert("至少选择一种身份进入");
	return false;
	}
</script>
<title> Welcome 2 Javacat's chating room</title>
</head>
<body onload="defaultfocus()" bgcolor="#CCFFCC">
<%
	String err_msg(String)session.getAttribute("s_message");
	if(err_msg==null)
	err_mesg="";
	session.setAttribute("s_message");
	
%>
<table border="0" width="80%" align="center" bgcolor="#CCFFCC">
<tr>
<td width="52%" bgcolor="#ffFFCC">
<form method="post" name="loginform" action="login.jsp">
<%
if(err_msg!=null&&!err_msg.equals("")){%>
<center><font color=red><b>错误信息</b></font><br><font  color=blue><b><%=err_msg%></b></font></center>
<%}%>
<div align="center">
<p> <br><font color=red><b>管理员认为你在该聊天室的言行不当，给他人造成伤害，如果你保证改正，可以重新进入来聊天室</b></font></p>
<font size="4"><b>注册用户</b></font><br>

用户名<input type="text" name="t_regname" size="13"><br>
密码<input type="password" name="t_regpassword" size="13">
<br><br>
<b>游客</b><br>
呢称<inpute type="text" name="t_username" size="13">
<br><br>
<input type="submit" name="submit" value="进入" onclick="return check();">
<input type="reset" value="取消" name="reset">
</p>
</div>
</form>
</td>
</tr>
</table>
</body>
</html>
