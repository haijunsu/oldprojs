<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>�˵�ҳ��</title>
</head>
<%
//��ֹCache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<body>
<jsp:useBean id="beanGetdataUser" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="beanGetdataMenu" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="beanElearnTools" scope="request" class="com.htyz.beanElearnTools"/>
<p>�˵��б�<br>

<table border="1" align="center">
	<tr align="center">
	<%
	if(beanGetdataMenu.getRowCount()>0)
	{
		for(int i=0; i<beanGetdataMenu.getRowCount(); i++)
		{
			if(beanElearnTools.isMenuShow(beanGetdataMenu.getFieldValue("mright", i), beanGetdataUser.getFieldValue("user_right", 0)))
			{
			%>
		<td nowrap> <A href="<%=beanGetdataMenu.getFieldValue("murl", i)%>"><%=beanGetdataMenu.getFieldValue("mnamec", i)%></A></td>
			<%
			}
		}
	}
	%>
	</tr>
</table>
</FORM>
</body>
</html>
	