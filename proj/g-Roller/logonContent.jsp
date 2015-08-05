<%@ page contentType="text/html;charset=GB2312" language="java" %>

<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
		<title>�û���½</title>
	</head>
	
<body>

	<logic:notEmpty name="exception">
		<p><bean:write name="exception" property="rootCause.message"/></p>
	</logic:notEmpty>


	<table cellspacing="1" cellpadding="5" class="logon" align="center">
		<form method="POST" action="<%=request.getContextPath()%>/user.do?method=logon">
			<tr>
				<th class="logon"><a href="logon.jsp">��½Blog</a></th>
				<th class="logon"><a href="register.jsp" class="red">ע�����û�</a></th>
			</tr>
			<tr>
				<td class="logon">�û�����</td>
				<td class="logon"><input name="account" maxLength=12 size=12/></td>
			</tr>
			<tr>
				<td class="logon">���룺</td>
				<td class="logon"><input type="password" name="password" maxLength=12 size=12/></td>
			</tr>
			<tr>
				<td class="logon"></td>
				<td class="logon"><input type="submit" value="��½" /> <input type ="reset" value="ȡ��"></td>
			</tr>
	  </form>
	</table>



</html>