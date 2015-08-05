<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="notelist" scope="request" class="com.htyz.beanGetdata"/>
<html>
<head>
<title>Untitled Document</title>
<link rel="stylesheet" href="../style.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF"  width="600" height="20">
  <%notelist.executeSelect("select t.user_id,t.class_id,t.start_time,t.end_time,c.class_name from t_user_class t,t_class c where c.class_id=t.class_id and t.user_id='"+session.getAttribute("userid")+"'");%>
  <tr bgcolor="#cccccc"> 
    <td height="20" bgcolor="#cccccc"> 
      <div align="center">课程名称</div>
    </td>
    <td height="20" bgcolor="#cccccc" align="center">开始时间</td>
    <td height="20" bgcolor="#cccccc" align="center">结束时间</td>
  </tr>
  <%for (int i=0; i<notelist.getRowCount(); i++)
	{
	%>
  <tr> 
    <td height="20"><a href="notes.jsp?classid=<%=notelist.getFieldValue("class_id",i)%>"><%=notelist.getFieldValue("class_name",i)%></a></td>
    <td height="20" align="center"><%=ets.FormatDate(notelist.getFieldValue("start_time",i))%></td>
    <td height="20" align="center"><%=(notelist.getFieldValue("end_time",i)==null||notelist.getFieldValue("end_time",i)=="")?"正在学习中...":ets.FormatDate(notelist.getFieldValue("end_time",i))%> 
    </td>
  </tr>
  <%}%>
</table>
</body>
</html>
