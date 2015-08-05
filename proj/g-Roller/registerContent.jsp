<%@ page contentType="text/html;charset=GB2312" language="java" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
	<title>用户注册</title>
</head>

<body>


	<table cellspacing="1" cellpadding="5" class="logon" align="center">
		<form method="POST" action="<%=request.getContextPath()%>/user.do?method=register">
			<tr>
				<th class="logon"><a href="register.jsp">用户注册</a></th>
				<th class="logon"><a href="<%=request.getContextPath()%>/user.do?method=listAll">所有用户</a></th>
			</tr>
			<tr>
				<td class="logon">用户名：</td>
				<td class="logon"><input name="account" /></td>
			</tr>
			<tr>
				<td class="logon">密码：</td>
				<td class="logon"><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td class="logon">姓名：</td>
				<td class="logon"><input name="name" /></td>
			</tr>
			<tr>
				<td class="logon">邮件：</td>
				<td class="logon"><input name="mail" /></td>
			</tr>

			<tr>
				<td class="logon"></td>
				<td class="logon"><input type="submit" value="注册" /> <input type ="reset" value="取消"></td>
			</tr>
	  </form>
	</table>

</body>

</html>