<%@page contentType="text/html;charset=GB2312"%>
<%@page language="java" import="sun.jdbc.rowset.*"%>
<html>
<head>
<title>testSql</title>
</head>
<body>
<jsp:useBean id="sqlbean" scope="request" class="com.htyz.bean_SQL"/>
<h2>�û���</h2>
<% int i = 0, i_rtn;
java.math.BigDecimal user_id;
String user_name, str;
CachedRowSet RScount = sqlbean.executeQuery("select count(*) as num from tlessontype1");
RScount.absolute(1);
str = RScount.getObject(1).toString();
RScount.close();
sqlbean.executeSQL("delete from tlessontype1 where type1_id = 100");
sqlbean.executeSQL("delete from tlessontype1 where type1_id = 101");
sqlbean.executeSQL("insert into tlessontype1 values (100, '������Ա')");
sqlbean.executeSQL("insert into tlessontype1 values (101, '������Ա')");
CachedRowSet RS = sqlbean.executeQuery("select * from tlessontype1");
while(RS.next())
{
	i += 1;
}
//RScount.close();
%>

<p>���ݿ��й���<%=i%>���û�<%=str%>

<table>
	<tr>
		<td> �û�ID </td>
		<td> �û����� </td>
	<tr>
	<%
//	CachedRowSet RS = sqlbean.executeQuery("select type1_id, type1_name from tlessontype1");
	RS.absolute(1);
		str = RS.getObject("type1_id").toString();
		user_id = RS.getBigDecimal("type1_id");
		user_name = RS.getString("type1_name");
	%>
	<tr>
		<td> <strong><%=str%> </strong> <%=user_id%> </td>
		<td> <%=user_name%> </td>
	<tr>
	<%
	while(RS.next())
	{
		str = RS.getObject("type1_id").toString();
		user_id = RS.getBigDecimal("type1_id");
		user_name = RS.getString("type1_name");
	%>
	<tr>
		<td> <strong><%=str%> </strong> <%=user_id%> </td>
		<td> <%=user_name%> </td>
	<tr>
	<%
	}
	RS.close();
	%>
</table>
</body>
</html>
	