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
	else alert("����ѡ��һ����ݽ���");
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
<center><font color=red><b>������Ϣ</b></font><br><font  color=blue><b><%=err_msg%></b></font></center>
<%}%>
<div align="center">
<p> <br><font color=red><b>����Ա��Ϊ���ڸ������ҵ����в���������������˺�������㱣֤�������������½�����������</b></font></p>
<font size="4"><b>ע���û�</b></font><br>

�û���<input type="text" name="t_regname" size="13"><br>
����<input type="password" name="t_regpassword" size="13">
<br><br>
<b>�ο�</b><br>
�س�<inpute type="text" name="t_username" size="13">
<br><br>
<input type="submit" name="submit" value="����" onclick="return check();">
<input type="reset" value="ȡ��" name="reset">
</p>
</div>
</form>
</td>
</tr>
</table>
</body>
</html>
