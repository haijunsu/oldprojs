<%@page contentType="text/html;charset=GB2312"%>
<%@page language="java" import="com.htyz.*"%>
<html>
<head>
<title>testSql1</title>
</head>
<body>
<jsp:useBean id="sqlbean" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="eds" scope="request" class="com.htyz.util.BeanDateFormat"/>

<h2>用户表</h2>
<% 
sqlbean.executeSelect("select count(*) as num from t_user");
%>

<p>数据库中共有<%=sqlbean.getFieldValue("num", 0)%>个用户

<table>
	<tr>
		<td> 用户ID </td>
		<td> 用户名称 </td>
	<tr>
	<%
	sqlbean.executeSelect("select * from t_user");
	for (int i=0; i<sqlbean.getRowCount(); i++)
	{
	%>
	<tr>
		<td> <%=sqlbean.getFieldValue(1, i)%> </td>
		<td> <%=sqlbean.getFieldValue(2, i)%> </td>
	<tr>
	<%
	}
	%>
</table>


<br>2002-1-1=<%=eds.parseDBDate("2002","1","1")%>
<br>2002-2-1=<%=eds.parseDBDate("2002","2","1")%>
<br>2002-3-1=<%=eds.parseDBDate("2002","3","1")%>
<br>2000-12-31=<%=eds.parseDBDate("2000","12","31")%>
<br>1992-12-31=<%=eds.format(eds.parseDBDate("1992","01","09"),14)%>


<br>=System Date: <%=eds.getYear(sqlbean.getDbDate())%>年 <%=eds.getMonth(sqlbean.getDbDate())%>月 <%=eds.getDay(sqlbean.getDbDate())%>日
</body>
</html>
	