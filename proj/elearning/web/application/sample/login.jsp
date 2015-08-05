<%@page pageEncoding="GBK" contentType="text/html; charset=GBK" %>
<%@ include file="/application/common/include.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
<title></title>
</head>
<body>
<html:form action="login" >
<html:errors property="loginerror"/><br>
	username:<html:text property="username" ></html:text><br>
	password:<html:password property="password" ></html:password></br>
	<html:submit >submit</html:submit>
</html:form>
</body>
</html>
