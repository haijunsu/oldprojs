<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="java.io.File.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.io.FileFilter"%>

<jsp:useBean id="beanDate" scope="session" class="com.htyz.util.BeanDateFormat" />

<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">

</head>

<body >
<p><div align="center"><font face="华文行楷" size='5'>课件文件</font></div></p>

<form method="post" name="form2" action="lessonback.jsp">

  <table border="1" cellspacing="0" width="100%" cellpadding="0" bgcolor="#eeeeee">
    <tr >
      <td width="30%">文件名称</td>
      <td width="13%">大小(字节)</td>
      <td width="17%">最后修改时间</td>

    </tr>
    <%
    int totalRowNum=0,pageRowNum=8,totalPageNum=1,curPageNum=1,nextPageNum=2;
    int startRow=0,endRow=1,nextstaRow=1;
    if(request.getParameter("page")!=null)
    {
      curPageNum=Integer.parseInt(request.getParameter("page"));
      nextPageNum=curPageNum+1 ;
    }
    %>
    <%
    try
    {
      String user_id = " ",notice_name=" ",notice_time = " ",valid_time = " ";
      String notice_id=" ",to_time=" ";
      
	   //File fi=new File("\\Tomcat\\webapps\\elearning\\booksmaking");
	   File fi=new File("\\websphere\\AppServer\\installedApps\\elearning.ear\\elearning.war\\elearning\\lessadmin\\lessonimport");
       File[] aa=fi.listFiles();
	   String tmpname="";
	   int ind=0;
       //String[] aa=fi.list();
       totalRowNum=aa.length;
       if(totalRowNum%pageRowNum==0)
       {
         totalPageNum=totalRowNum/pageRowNum ;
       }
       else
       {
          totalPageNum=(totalRowNum/pageRowNum) +1 ;
       }
      startRow=(curPageNum-1)*pageRowNum ;
      endRow=curPageNum*pageRowNum ;
      nextstaRow=(curPageNum)*pageRowNum ;
      if(endRow>aa.length)
        endRow=aa.length;

     // for(int i=0;i<aa.length;i++)
      for(int i=startRow;i<endRow;i++)
       {
        ind=(String.valueOf(aa[i])).lastIndexOf("\\");
		//System.out.println("the str ind====="+ind);
		tmpname =(String.valueOf(aa[i])).substring(ind+1);
		
    %>
    <tr >
      <td width="30%"><a href="readfile.jsp?bz=<%=aa[i]%>" ><%=tmpname%></a></td>
      <td width="10%"><%=aa[i].length()%></td>
      <td width="20%"><%=beanDate.format(String.valueOf(aa[i].lastModified()),8)%></td>

    </tr>
    <% } }
    catch(Exception e)
    {
      e.printStackTrace();
    }%>

  </table>

  <table width="90%" border="0">
    <tr>
    <%
    if(curPageNum>1)
    {
    %>
      <td align='center'><a href="openLesson.jsp?page=<%=curPageNum-1%>" >上页</a></td>
    <%
    }
    else
    {
    %>
      <td align='center'><font color="a9a9a9">上页</font></td>
    <%
    }
    %>

     <%
     if(nextstaRow<totalRowNum)
     {
     %>
      <td align='center'><a href="openLesson.jsp?page=<%=curPageNum+1%>" >下页</a></td>
     <%
     }
     else
     {
     %>
      <td align='center'><font color="a9a9a9">下页</font></td>
     <%
     }
     %>

      <td align='center'> 当前页:<%=curPageNum%>/<%=totalPageNum%></td>
      <td align='center'> 共<%=totalPageNum%>页</td>
    </tr>
  </table>

</form>
<p>&nbsp; </p>
</body>
</html>

