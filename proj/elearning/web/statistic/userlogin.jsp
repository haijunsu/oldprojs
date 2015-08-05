<%@ page contentType="text/html; charset=GB2312" %>

<jsp:useBean id="online" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="beanDate" scope="session" class="com.htyz.util.BeanDateFormat" />
<jsp:useBean id="notbean" scope="session" class="com.htyz.notice.noticeBean" />

<%@ page import="com.htyz.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.htyz.notice.*"%>

<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
</head>

<body >

<%

String userid=(String)session.getAttribute("userid");
if(userid==null) userid="";
%>
<form name = formdata method="post" action="">
  

   <table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="100%">
    <tr bgcolor ="#cccccc">
      <td width="7%">用户</td>
      <td width="17%">登陆时间</td>
      <td width="17%">IP地址</td>
      <td width="20%">状态</td>
    </tr>
    <%
	int totalRowNum=0,pageRowNum=20,totalPageNum=1,curPageNum=1,nextPageNum=2;
	int startRow=0,endRow=1,nextstaRow=1;

    try
    {
      
	  if(request.getParameter("page")!=null)
	  {
		// System.out.println("the page====="+page);
		 curPageNum=Integer.parseInt(request.getParameter("page"));
		 nextPageNum=curPageNum+1 ;
	  }

      String s_Sql="";
	
      Vector vect = new Vector();	
      
      s_Sql = "select * from t_user_online where user_id='"+userid+"' order by login_time desc ";
	     
	
      online.executeSelect(s_Sql);

	  totalRowNum=online.getRowCount();
		//totalRowNum=vect.size();
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
	  if(endRow>totalRowNum)
		  endRow=totalRowNum;
	 
      //for(int j=0; j<online.getRowCount(); j++)
	  for(int j=startRow; j<endRow; j++)
      {

        %>
        <tr>
        <td width="7%"><%=online.getFieldValue("user_id",j)%></td>
        <td width="17%"><%=beanDate.format(online.getFieldValue("login_time",j),8)%></td>
        <td width="17%"><%=online.getFieldValue("ip_address",j)%></td>
        <td width="20%"><%=online.getFieldValue("status",j)%></td>
        </tr>
    <%
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    %>

  </table>
  <table width="90%" border="0">
    <tr>
    <%
    if(curPageNum>1)
    {
    %>
      <td align='center'><a href="userlogin.jsp?page=<%=curPageNum-1%>" >上页</a></td>
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
	  <td align='center'><a href="userlogin.jsp?page=<%=curPageNum+1%>" >下页</a></td>
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
