<%@ page contentType="text/html;charset=GB2312" language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>

<html>
	<head><title>����ҳ��</title></head>
	<body>
		<bean:write name="exception" property="rootCause.message" />
	</body>
</html>