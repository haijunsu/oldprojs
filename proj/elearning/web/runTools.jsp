<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>beanElearnTools</title>
</head>
<body>
<jsp:useBean id="beanTool" scope="request" class="com.htyz.beanElearnTools"/>
<h2>beanElearnToolsʾ�� </h2>

<p>����2001-30-02������� <%=beanTool.check_date("2001-30-02")%> 

<p>����2000-05-06 00:00:00.000��ʾ����� <%=beanTool.FormatDate("2000-05-06 00:00:00.000")%> 

<p>�Ե������滻��ʾ����� <%=beanTool.check_quote("sdfs'sdf'sdfsdffd")%> 

<p>Ȩ�޼������
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
	