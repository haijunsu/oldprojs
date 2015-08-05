<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.io.File.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.io.FileFilter"%>
<jsp:useBean id="beanTools" scope="session" class="com.htyz.beanElearnTools" />

<html>
<head>
<title>公告板的管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../3.css" type="text/css">
</head>
<%
//String lessonname= request.getParameter("bz");
String lessonname=request.getParameter("bz");
int i,ind=0;
String content="",tempstr="",openname="";

if(lessonname!=null)
{
    lessonname=new String(lessonname.getBytes("ISO8859_1"));
   
    byte[] b = new byte[1000];

    FileInputStream file = new FileInputStream(lessonname);
   // FileInputStream file = new FileInputStream("D:\\liu\\lesson\\lesson0005.html");
    
    DataInputStream indata = new DataInputStream(file);
    //outdata.write(c);
    try
        {
            do
            {

              content=indata.readLine();
              if(content!=null)
                 tempstr=tempstr+content+"\n";

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
	//ind=lessonname.lastIndexOf("\\");
	//int len=lessonname.length() ;
	//openname =lessonname.substring(ind+1,len-5);
}

%>



<body bgcolor="#FFFFFF" text="#000000">
<form name="form1" method="post" action="addcall.jsp">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td></td>
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
