<%@ page contentType="text/html;charset=GB2312" %>
<%
String user_id=(String)session.getAttribute("userid");
String group_id=(String)session.getAttribute("groupid");
if (user_id==null) user_id="test";
if (group_id==null) group_id="01";
%><html>
<head>
<title><%=user_id%>的个人桌面</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<jsp:useBean id="beanGetdata" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="notbean" scope="session" class="com.htyz.notice.noticeBean" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/3.css" type="text/css">
</head>
<body bgcolor="#FFFFFF" text="#000000">
<div style="filter:shadow(color=#cccccc,direction=120);width:98%;padding-right:10;padding-bottom:10">
<%@ include file="/lesson/lessonSchedule.jsp" %>
<br>
<%@ include file="/lesson/userApplyList.jsp" %>
<br>
  <table width="100%" border="0" cellspacing="1" cellpadding="0">
    <tr bgcolor="#cccccc"> 
      <td height="20"><b>我的短信</b></td>
    </tr>
    <tr bgcolor="#eeeeee"> 
      <%beanGetdata.executeSelect("select * from t_onlinecall where (oc_status='1' or oc_status='2') and oc_obj='"+session.getAttribute("userid")+"'");
  if(beanGetdata.getRowCount()<1){%>
      <td height="20">没有短信 ....</td>
      <%}else{%>
      <td height="20"><a href="<%=request.getContextPath()%>/servlet/onlinecall/Messager">您共有<%=beanGetdata.getRowCount()%>条短信，其中有
        <%beanGetdata.executeSelect("select * from t_onlinecall where oc_status='1' and oc_obj='"+session.getAttribute("userid")+"'");
	out.print(beanGetdata.getRowCount());
	%>
        条新短信....</a>
        <%if(beanGetdata.getRowCount()>0){out.println("<img src='" + request.getContextPath()+ "/images/newmsg.gif'></img>");}%>
      </td>
      <%}%>
    </tr>
  </table>
  <br>
  <table width="100%" border="0" cellspacing="1" cellpadding="0">
    <tr bgcolor="#cccccc"> 
      <td height="20"><b>我的通知</b></td>
    </tr>
    <%
 // notbean.connectdatabase();
  beanGetdata.executeSelect("Select * from t_notice where rtrim(user_name) like '%" + user_id + "%' or rtrim(user_name) like '%" + group_id + "%' ");
  %>
    <tr bgcolor="#eeeeee"> 
      <td height="20"><a href="../notice/noticefront.jsp">您共有<%=beanGetdata.getRowCount()%>条通知,其中新通知<%=notbean.getRowCount(user_id,group_id)%>条</a>
	          <%if(notbean.getRowCount(user_id,group_id)>0){out.println("<img src='" + request.getContextPath()+ "/images/newmsg.gif'></img>");}%>
	  </td>
    </tr>
  </table>
  <br>
  <table width="100%" border="0" cellspacing="1" cellpadding="0">
  <tr bgcolor="#cccccc"> 
    <td height="20"><b>我的收件箱</b></td>
  </tr>
  <tr bgcolor="#eeeeee"> 
    <td height="20">没有邮件...</td>
  </tr>
</table>
</div>
</body>
</html>
