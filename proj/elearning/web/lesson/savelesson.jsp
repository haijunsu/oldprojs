<%@page contentType="text/html;charset=GB2312"%>
<%@page language="java" import="com.htyz.*"%>
<html>
<head>
<title>Untitled Document</title>
<link rel="stylesheet" href="xbz.css" type="text/css">
<jsp:useBean id="sqlbean" scope="request" class="com.htyz.beanGetdata"/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body bgcolor="#FFFFFF" text="#000000">
<%
if(request.getParameter("act").equals("add")){
String  name=new String(request.getParameter("name").getBytes("ISO8859_1"));
String  no=request.getParameter("no");
String class_id=request.getParameter("select2");
//System.out.print(class_id);
StringBuffer noall=new StringBuffer(); //�����½�
 noall.append("00").append(request.getParameter("no")).append("000");
  if ("".equals(name)||"".equals(no)||request.getParameter("select2").equals("0")){%>
   <script>
   alert("�������ݲ�������");
   history.back();
   </script>
   <%}
   else{
   if (request.getParameter("select3").equals(""))
	{
	 sqlbean.executeSelect("select lesson_id from tlesson where lesson_id='"+noall+"' and class_id='"+class_id+"'");

    if(sqlbean.getRowCount()>=1){
	 %>
	 <script>
	 alert("�º��ظ�,���������룡");
	 history.back();
	</script>
	<%}
 else{
sqlbean.execute("insert into tlesson (class_id,lesson_id,lesson_name,lesson_url,lesson_status)values('"+class_id+"','"+noall+"','"+name+"','"+request.getParameter("file1")+"','1')");
//����tclass��
sqlbean.execute("update tclass set class_status='1' where class_id='"+request.getParameter("select2")+"'");
 out.print("��ӳɹ������<a href='lesson.jsp'>����</a>����");
  //out.pring(ss);
 }
 }
 else {
 //if (request.getParameter("no").length()<2){
String s_temp=new String("000"+request.getParameter("no"));
//}
// else{
 //String s_temp=new String("00"+request.getParameter("no"));
 //}
StringBuffer jie=new StringBuffer();
jie.append(request.getParameter("select3").substring(0,3)).append(s_temp.substring(s_temp.length()-3,s_temp.length()));
//System.out.print(jie.toString());
sqlbean.executeSelect("select lesson_id from tlesson where lesson_id='"+jie+"' and class_id='"+class_id+"'");
if(sqlbean.getRowCount()>=1){%>
<script>
alert("�ں��ظ���");
history.back();
</script>
<%}
else{
sqlbean.execute("insert into tlesson(class_id,lesson_id,lesson_name,lesson_url,lesson_status)values('"+class_id+"','"+jie+"','"+name+"','"+request.getParameter("file1")+"','1')");
//out.print("insert into tlesson(class_id,lesson_id,lesson_name,lesson_url,lesson_status)values('tet',"+jie.toString()+",'"+name+"','"+request.getParameter("file1")+"','1')");
out.print("��ӳɹ������<a href='lesson.jsp'>����</a>����");
 }
 }
 }
 }
//�����μ�
%>
</body>
</html>