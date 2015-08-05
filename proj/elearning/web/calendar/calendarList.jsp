<%@ page contentType="text/html;charset=GB2312"%>
<%@page language="java" import="com.htyz.*"%>
<html>
<head>
<jsp:useBean id="beanGetdata" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="fds" scope="request" class="com.htyz.util.BeanDateFormat"/>
<script language="javascript">
function clearBottom() {
	parent.calendarBottom.location.href="about:blank";
}
</script>
<head>
<title>Untitled Document</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body bgcolor="#FFFFFF" text="#000000" onload="clearBottom()">
<table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="100%">
  <tr bgcolor="#cccccc" align="right"> 
    <td colspan="5" height="20"><a href="<%=request.getContextPath()%>/servlet/calendar/Calendar?action=add&year=<%=request.getParameter("year")%>&month=<%=request.getParameter("month")%>&date=<%=request.getParameter("date")%>" target="calendarBottom">添加事件</a></td>
  </tr>
  <tr align="center"> 
    <td width="35%" height="20">标题</td>
    <td width="15%" height="20">提醒时间</td>
    <td width="50%" height="20" colspan="3">提醒内容</td>
  </tr>
  <%for (int i=0;i<beanGetdata.getRowCount();i++){
    if(beanGetdata.getRowCount()==0)
	{
	out.print("<tr><td>暂无记录！</td></tr>");
	}
	else{
   %>
  <tr> 
    <td width="35%" height="20"><a href="<%=request.getContextPath()%>/servlet/calendar/Calendar?action=show&daily_id=<%=beanGetdata.getFieldValue("daily_id",i)%>&year=<%=request.getParameter("year")%>&month=<%=request.getParameter("month")%>&date=<%=request.getParameter("date")%>" target="calendarBottom"><%=beanGetdata.getFieldValue("title",i)%></a></td>
    <td width="15%" height="20"><%=fds.format(beanGetdata.getFieldValue("wake_date",i),14)%>&nbsp;</td>
    <td width="40%" height="20"><%=beanGetdata.getFieldValue("notes",i)%></td>
    <td width="5%" height="20"><a href="<%=request.getContextPath()%>/servlet/calendar/Calendar?action=mod&daily_id=<%=beanGetdata.getFieldValue("daily_id",i)%>&year=<%=request.getParameter("year")%>&month=<%=request.getParameter("month")%>&date=<%=request.getParameter("date")%>" target="calendarBottom">修改</a></td>
     <td width="5%" height="20"><a href="<%=request.getContextPath()%>/servlet/calendar/Calendar?action=del&daily_id=<%=beanGetdata.getFieldValue("daily_id",i)%>&year=<%=request.getParameter("year")%>&month=<%=request.getParameter("month")%>&date=<%=request.getParameter("date")%>" onclick=return(confirm("确认删除本条记录?")) target="calendarBottom">删除</a></td>
  </tr>
  <%}
  }
  %>
</table>
</body>
</html>
