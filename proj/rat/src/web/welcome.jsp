
<%@include file="/common/include.jsp"%>
<html:base />
<html>
<title><bean:message key="index.title" /></title>
<body>
<bean:message key="label.login.welcome"/>
<bean:write name="userInfo" property="userName"/>. You are from <bean:write name="userInfo" property="userIP"/>.
</body>
</html>
