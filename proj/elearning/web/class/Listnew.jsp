<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<%@ page contentType="text/html; charset=gb2312"%>
<HTML>
<HEAD>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">

<TITLE>Tree Pane</TITLE>
<link rel="stylesheet" href="td.css" type="text/css">
</HEAD>

<jsp:useBean id="list" scope="session" class="com.elearning.ListBean" />
  <jsp:setProperty name="list" property="jdbcDriver" value="sun.jdbc.odbc.JdbcOdbcDriver" />
  <jsp:setProperty name="list" property="jdbcURL" value="jdbc:odbc:elearning" />
  <jsp:setProperty name="list" property="jdbcUser" value="sa" />
  <jsp:setProperty name="list" property="jdbcPassword" value="" />

  <jsp:setProperty name="list" property="tableName" value="tclass" />
  <jsp:setProperty name="list" property="labelCols" value="Class_name,Set_time,Class_type,keywords,detil,Peoples,Class_id" />
  <jsp:setProperty name="list" property="titleCols" value="课程名称,加入时间,课程类型,关键字,描述,学习人数" />
  <jsp:setProperty name="list" property="urlCol" value="1" />
  <jsp:setProperty name="list" property="orderStyle" value="Order BY set_time" />
  <jsp:setProperty name="list" property="tableStyle" value="da" />

  <jsp:setProperty name="list" property="listPage" value="List.jsp" />
  <jsp:setProperty name="list" property="leafTarget" value="rhs" />
  <jsp:setProperty name="list" property="rec_per" value="11" />
  <jsp:setProperty name="list" property="topnum" value="10" />
  <jsp:setProperty name="list" property="toporder" value=" order by set_time " />
  <jsp:setProperty name="list" property="*" />

<BODY vlink="#0000ff"><b><strong>课程列表</strong></b>
<BR>
<BR>
<%
//list.setGopage_On("0");
//list.setPages_On(0);
//list.setNext_On("0");

if(request.getParameter("cur_page")!=null)
	{
		list.setcur_page(Integer.parseInt(request.getParameter("cur_page")));
}
list.setDateColNum(2);
out.print(list.ListTable());

%>
 
