<%@page contentType="text/html;charset=GB2312"%>
<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>

<html>
<head>
<title>�γ��������</title>
</head>
<%
String classid = (String)request.getParameter("classid");
if (classid == null)
{
	out.println("<FONT color=red>������û���ṩҪ���ԵĿγ�ID��</FONT>");
}
{
//String s_sql="delete from t_result where user_id='user'";
//bgd.execute(s_sql);
%>
<frameset rows="0,*">
  <frame name="refresh" scrolling="no" noresize src="refresh.jsp" border = "0">
  <frame name="exam" src="<%=request.getContextPath()%>/servlet/exam/topicExam?classid=<%=classid%>">
  <noframes>
  <body>

  <p>����ҳʹ���˿�ܣ��������������֧�ֿ�ܡ�</p>

  </body>
  </noframes>
</frameset>
<%}%>
</html>
