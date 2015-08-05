<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>

<%@ page import="java.util.*"%>
<%@ page import="java.net.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.htyz.system.SystemProperties"%>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body bgcolor="#FFFFFF" text="#000000">
<script language=javascript>

</script>
<%
String content;
String lessonname;
if(SystemProperties.getProperty("processURLChinese").equalsIgnoreCase("true")){
	content=new String(request.getParameter("MText").getBytes("ISO8859_1"));
	lessonname=new String(request.getParameter("lessonname").getBytes("ISO8859_1"));
}else{
	content=request.getParameter("MText");
	lessonname=request.getParameter("lessonname");
}
lessonname=lessonname+".html";

  try
  {
    byte[] c= content.getBytes("GBK");

    //FileOutputStream file = new FileOutputStream("/elearning/lessonmade/"+lessonname);
  
	FileOutputStream file = new //FileOutputStream("\\Tomcat/webapps/elearning/booksmaking/"+lessonname);
	FileOutputStream("\\websphere\\AppServer/installedApps/elearning.ear/elearning.war/elearning/booksmaking/"+lessonname);
    //ObjectOutputStream outdata = new ObjectOutputStream(file);
    DataOutputStream outdata = new DataOutputStream(file);
    outdata.write(c);
    //outdata.writeBytes(newstr);
    outdata.flush();
    outdata.close();
    file.close();


  }
  catch (java.io.IOException IOE)
  {
   
    System.out.println("export data is fail ");
  }


%>
保存成功！点击<a href='lessonmade.jsp'>这里</a>返回
</body>
</html>

