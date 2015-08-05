<%@page contentType="text/html;charset=GB2312"%>
<%@page language="java" import="com.htyz.*"%>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<jsp:useBean id="sqlbean" scope="request" class="com.htyz.beanGetdata"/>
<body bgcolor="#FFFFFF" text="#000000">
<%//添加
if (request.getParameter("act").equals("add")){
String name=new String(request.getParameter("name").getBytes("ISO8859_1"));
String di=new String(request.getParameter("di").getBytes("ISO8859_1"));
String key=new String(request.getParameter("key").getBytes("ISO8859_1"));
String desc=new String(request.getParameter("desc").getBytes("ISO8859_1"));
String biaozhun=new String(request.getParameter("biaozhun").getBytes("ISO8859_1"));
StringBuffer class_id=new StringBuffer();
class_id.append(request.getParameter("select1").trim()).append(request.getParameter("select2").trim());
sqlbean.executeSelect("select max(convert(numeric,right(Class_id,6))) as maxno from tclass");
String rightid="000000"+Integer.toString((Integer.parseInt(sqlbean.getFieldValue("maxno",0))+1));
sqlbean.execute("insert into tclass(class_id,class_name,class_type,keywords,detil,class_time,pass_stander,class_status) values('"+class_id+"'+right('"+rightid+"',6),'"+name+"','"+di+"','"+key+"','"+desc+"','"+request.getParameter("classtime")+"','"+biaozhun+"','1')");
response.sendRedirect("tclass.jsp");
}
//修改
if (request.getParameter("act").equals("mod")){
//out.print("update tclass set class_name='"+request.getParameter("name")+"',class_type='"+request.getParameter("di")+"',keywords='"+request.getParameter("key")+"',detil='"+request.getParameter("desc")+"',class_time='"+request.getParameter("classtime")+"',pass_stander='"+request.getParameter("biaozhun")+"' where class_id='"+request.getParameter("id")+"'");
String name=new String(request.getParameter("name").getBytes("ISO8859_1"));
String di=new String(request.getParameter("di").getBytes("ISO8859_1"));
String key=new String(request.getParameter("key").getBytes("ISO8859_1"));
String desc=new String(request.getParameter("desc").getBytes("ISO8859_1"));
String biaozhun=new String(request.getParameter("biaozhun").getBytes("ISO8859_1"));
sqlbean.execute("update tclass set class_name='"+name+"',class_type='"+di+"',keywords='"+key+"',detil='"+desc+"',class_time='"+request.getParameter("classtime")+"',pass_stander='"+biaozhun+"' where class_id='"+request.getParameter("id")+"'");
response.sendRedirect("tclass.jsp");
}
//删除
 if (request.getParameter("act").equals("del")){
 sqlbean.execute("update tclass set class_status='0' where class_id='"+request.getParameter("id")+"'");
 out.print("删除成功");
 response.sendRedirect("tclass.jsp");
 }
%>
</body>
</html>
