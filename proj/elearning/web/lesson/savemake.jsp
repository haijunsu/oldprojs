<%@ page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page language="java" import="com.htyz.*"%>

<html>
<body>
<jsp:useBean id="beanTool" scope="request" class="com.htyz.beanElearnTools"/>
<%Date IssueTime=new Date(); 
String vv=new String(IssueTime.toLocaleString());
//Integer vvv=Integer.parseInt(vv)
String filename=vv.substring(0,8);
session.setAttribute("class_id","99");
String st=request.getParameter("name");
String stt=FormatChar(request.getParameter("content"));
String firstchar="<table width='100%' border='0' cellspacing='0' cellpadding='0'><tr><td align='center'>";
String secondchar="</td></tr><tr><td>";
String thirdchar="</td></tr></table>";
String inchar=firstchar+st+secondchar+stt+thirdchar;
String s_msg;
if (beanTool.MakeDir("e:\\tomcat\\webapps\\elearning\\"+session.getAttribute("class_id")+""))
	{
		s_msg="创建成功";
	}
else
	{
	s_msg="创建失败！";
	}

File nameOfTextFile=new File("e:/tomcat/webapps/elearning/"+session.getAttribute("class_id").toString().trim()+"/"+filename+".html");

try {
	PrintWriter pw=new PrintWriter(new FileOutputStream(nameOfTextFile));
	pw.println(inchar);
	pw.close();
	}catch(IOException e){
		out.println(e.getMessage());
	}
out.println(st);
out.print("<br>");
out.println("<textarea name=content cols=60 rows=8>"+request.getParameter("content")+"</textarea>");
%>
</body>
</html>