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
alert("����Ҫѡ��һ����ݽ���");
return false;
}
</script>
<title>��½</title>
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
	<h2 align="center"><font color=blue>�������������</font></h2>
	</td>
</tr>
<tr>
	<td width="52%" bgcolor="#ffFFcc">
	<form method="post" name="loginform" action="login.jsp">
	<%
	if(err_msg!=null&&!err_msg.equals("")){	%>
	<center><font color=red><b>������Ϣ</b></font><br><font color=blue><b>
	<%=err_msg	%></b></font></center>
	<%}%>
	<div align="center"><font size="4"><b>ע���û�</b></font><br>
	�û���<input type="text" name="t_regname" size="13"><br>
          �� ��
<input type="password" name="t_regpassword" size="13"><br><br>
	<b>�ο� </b><br>
          �� ��
<input type="text" name="t_username" size="13"><br><br>
	<input type="submit" name="b_ok" value="����" onclick="return check();">
	<input type="reset" name="t_cancel" value="ȡ��">
	</p>
	</div>
	</form>
	</td>
	</tr>
	</table>
	<p align="center"><a href="http://java.sun.com" target="_blank">
	-==��������==-<br></a>CopyRihgt &copy; 2002 HuanTian.
	</p>
	</body>
	</html>
