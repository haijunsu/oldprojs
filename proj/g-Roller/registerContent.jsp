<%@ page contentType="text/html;charset=GB2312" language="java" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
	<title>�û�ע��</title>
</head>

<body>


	<table cellspacing="1" cellpadding="5" class="logon" align="center">
		<form method="POST" action="<%=request.getContextPath()%>/user.do?method=register">
			<tr>
				<th class="logon"><a href="register.jsp">�û�ע��</a></th>
				<th class="logon"><a href="<%=request.getContextPath()%>/user.do?method=listAll">�����û�</a></th>
			</tr>
			<tr>
				<td class="logon">�û�����</td>
				<td class="logon"><input name="account" /></td>
			</tr>
			<tr>
				<td class="logon">���룺</td>
				<td class="logon"><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td class="logon">������</td>
				<td class="logon"><input name="name" /></td>
			</tr>
			<tr>
				<td class="logon">�ʼ���</td>
				<td class="logon"><input name="mail" /></td>
			</tr>

			<tr>
				<td class="logon"></td>
				<td class="logon"><input type="submit" value="ע��" /> <input type ="reset" value="ȡ��"></td>
			</tr>
	  </form>
	</table>

</body>

</html>