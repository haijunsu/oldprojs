<%@ page contentType="text/html;charset=GB2312"%>
<%@page language="java" import="com.htyz.*"%>
<html>
<head>
<jsp:useBean id="sqlbean" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="eds" scope="request" class="com.htyz.util.BeanDateFormat"/>


<title>保存</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body bgcolor="#FFFFFF" text="#000000">
<%
String title=ets.getGBKString(request.getParameter("title"));
String content=ets.getGBKString(request.getParameter("content"));
String contents=ets.stringToHtml(content);
String date=request.getParameter("date");
System.out.println("Date=");
System.out.println(date);
String date_y=date.substring(0,4);
String date_m=date.substring(5,7);
String date_d=date.substring(8,10);
//dates.append(date.substring(0,4)).append(date.substring(5,7)).append(date.substring(8,10));
String str_date=eds.parseDBDate(date_y,date_m,date_d);
StringBuffer nowdate=new StringBuffer();
nowdate.append(request.getParameter("year")).append(request.getParameter("month")).append(request.getParameter("day"));
System.out.print(request.getParameter("year"));
sqlbean.execute("insert into t_user_daily(user_id,user_day,wake_date,title,notes)values('"+session.getAttribute("userid")+"','"+nowdate.toString()+"','"+str_date+"','"+title+"','"+contents+"')");
out.print("添加成功！");
%>
</body>
</html>
