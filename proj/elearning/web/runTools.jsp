<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>beanElearnTools</title>
</head>
<body>
<jsp:useBean id="beanTool" scope="request" class="com.htyz.beanElearnTools"/>
<h2>beanElearnTools示例 </h2>

<p>日期2001-30-02检查结果： <%=beanTool.check_date("2001-30-02")%> 

<p>日期2000-05-06 00:00:00.000显示结果： <%=beanTool.FormatDate("2000-05-06 00:00:00.000")%> 

<p>对单引号替换显示结果： <%=beanTool.check_quote("sdfs'sdf'sdfsdffd")%> 

<p>权限检查结果：
<%
if(beanTool.isMenuShow("5", "366"))
{
%>
OK
<%
}
else
{
%>
False
<%
}
%>
</body>
</html>
	