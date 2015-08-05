<%@ page contentType="text/html;charset=GB2312"%>
<%@page language="java" import="com.htyz.*"%>
<html>
<head>
<jsp:useBean id="beanGetdata" scope="request" class="com.htyz.beanGetdata"/>

<head>
<title>Untitled Document</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="100%">
  <tr bgcolor="#cccccc" align="right"> 
    <td colspan="4" height="20"><a href="<%=request.getContextPath()%>/servlet/calendar.beanCalendar?action=add&year=<%=request.getParameter("year")%>&month=<%=request.getParameter("month")%>&date=<%=request.getParameter("date")%>">添加事件</a></td>
  </tr>
  <tr> 
    <td width="28%" height="20">标题</td>
    <td width="36%" height="20">提醒时间</td>
    <td width="36%" height="20" colspan="2">提醒内容</td>
  </tr>
  <%for (int i=0;i<beanGetdata.getRowCount();i++){
    if(beanGetdata.getRowCount()==0)
	{
	out.print("<tr><td>暂无记录！</td></tr>");
	}
	else{
   %>
  <tr> 
    <td width="28%" height="20"><a href="<%=request.getContextPath()%>/servlet/calendar.beanCalendar?action=show&daily_id=<%=beanGetdata.getFieldValue("daily_id",i)%>"><%=beanGetdata.getFieldValue("title",i)%></a></td>
    <td width="36%" height="20"><%=beanGetdata.getFieldValue("wake_date",i)%></td>
    <td width="18%" height="20"><%=beanGetdata.getFieldValue("notes",i)%></td>
    <td width="18%" height="20"><a href="<%=request.getContextPath()%>/servlet/calendar.beanCalendar?action=mod&daily_id=<%=beanGetdata.getFieldValue("daily_id",i)%>">修改</a> 
     <a href="<%=request.getContextPath()%>/servlet/calendar.beanCalendar?action=del&daily_id=<%=beanGetdata.getFieldValue("daily_id",i)%>">删除</a></td>
  </tr>
  <%}
  }
  %>
</table>
</body>
</html>
