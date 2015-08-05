<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.io.File.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.io.FileFilter"%>
<jsp:useBean id="beanTools" scope="session" class="com.htyz.beanElearnTools" />

<html>
<head>
<title>公告板的管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=request.getContextPath()%>/3.css" type="text/css">
</head>
<%
String content="",tempstr="",openname="";
String path=request.getRealPath("callboard.jsp");//将相对路径换成绝对路径
	//out.println("the note path is==="+path);
if(path.indexOf("AppServer")!=-1)
   openname= application.getRealPath("/") + "/note.html";
else
   openname=application.getRealPath("/") + "/note.html";
//openname="\\websphere\\AppServer/installedApps/elearning.ear/elearning.war/elearning/note.html";

byte[] b = new byte[1000];

FileInputStream file = new FileInputStream(openname);

DataInputStream indata = new DataInputStream(file);

try
	{
		do
		{

		  content=indata.readLine();
		  if(content!=null)
			 tempstr=tempstr+content;

		}
		while(content!= null);
	}
	catch(IOException e)
	{
		e.printStackTrace();
		System.exit(1);
	}
indata.close();
file.close();
tempstr=new String(tempstr.getBytes("ISO8859_1"));
tempstr=beanTools.strReplace(tempstr,"&lt;","<");
tempstr=beanTools.strReplace(tempstr,"&gt;",">");

tempstr=beanTools.strReplace(tempstr,"&nbsp;"," ");
tempstr=beanTools.strReplace(tempstr,"&quot;","\"");
tempstr=beanTools.strReplace(tempstr,"<BR>","\n");

		
%>

<body bgcolor="#FFFFFF" text="#000000">
<form name="form1" method="post" action="addcall.jsp">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td>输入公告板内容：</td>
    </tr>
    <tr> 
      <td > 
        <textarea class="testarea1" name="content"  cols="80" rows="15" ><%=tempstr%></textarea>
      </td>
    </tr>
    <tr> 
      <td> 
        <input class="input1" type="submit" name="Submit" value="提交">
        <input class="input1" type="reset" name="Submit2" value="取消">
      </td>
    </tr>
  </table>
</form>
<br>
</body>
</html>
