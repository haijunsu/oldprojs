<%@ page contentType="text/html;charset=GB2312" language="java" %>

<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
	<title>文章列表</title>
</head>

<body>
<table>
	<tr align="center"><strong>
		<td width="200">标题</td>
		<td width="200">发表日期</td>
		<td width="200">修改日期</td>
	</strong></tr>
<logic:iterate id="dto" name="dtos">
	<tr align="center">	
		<td>
			<a href="post.do?method=view&id=<bean:write name="dto" property="id"/>">
				<bean:write name="dto" property="title" />
			</a>
		</td>
		<td><bean:write name="dto" property="postTimeInString"/></td>
		<td><bean:write name="dto" property="updateTimeInString"/></td>
	</tr>
</logic:iterate>
</table>
</body>

</html>