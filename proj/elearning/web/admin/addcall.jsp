<%@ page contentType="text/html;charset=GB2312" %>
<%@page language="java" import="com.htyz.*"%>
<%@ page import="java.io.*"%>

<html>
<head>
<title>Untitled Document</title>
<link rel="stylesheet" href="../3.css" type="text/css"
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<jsp:useBean id="sqlbean" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="beanTools" scope="request" class="com.htyz.beanElearnTools"/>

<body bgcolor="#FFFFFF" text="#000000">
<%
//String content=new String(request.getParameter("content").getBytes("ISO8859_1"));
String content=request.getParameter("content");
content=beanTools.stringToHtml(content);
String name="note.html";
//sqlbean.execute("insert into tboard (content) values('"+content+"')");
//out.print("添加成功！");
 try
  {

    byte[] c= content.getBytes("GBK");
 
	String path=request.getRealPath("callboard.jsp");//将相对路径换成绝对路径
	//out.println("the note path is==="+path);
	if(path.indexOf("AppServer")!=-1)
	   name="\\websphere\\AppServer/installedApps/elearning.ear/elearning.war/elearning/note.html";
	else
	   name="\\Tomcat\\webapps/ROOT/elearning/note.html";
		
	//path="\\websphere\\AppServer/installedApps/elearning.ear/elearning.war/elearning/";
	FileOutputStream file = new FileOutputStream(name);
   
    DataOutputStream outdata = new DataOutputStream(file);
    outdata.write(c);
    outdata.flush();
    outdata.close();
    file.close();


  }
  catch (java.io.IOException IOE)
  {
   
    System.out.println("save data is fail ");
  }
out.print("保存成功！");
%>
</body>
</html>
