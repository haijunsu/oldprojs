<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<%@ page import="java.util.*"%>
<%@ page import="com.htyz.*"%>

<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="beanDate" scope="session" class="com.htyz.util.BeanDateFormat" />

<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%

String userid = (String)session.getAttribute("userid");

String s_sql="";

%>

<HTML>
<HEAD>

<TITLE>课程列表</TITLE>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>

 
<%
int totalRowNum=0,pageRowNum=8,totalPageNum=1,curPageNum=1,nextPageNum=2;
int startRow=0,endRow=1,nextstaRow=1;

if(request.getParameter("page")!=null)
{
 // System.out.println("the page====="+page);
  curPageNum=Integer.parseInt(request.getParameter("page"));
  nextPageNum=curPageNum+1 ;
}

s_sql="select distinct a.class_id classid,a.class_name,a.class_type,b.schedule,b.cancel_time,b.reason from t_class a,t_userclass_del b where a.class_id=b.class_id and b.user_id='"+userid+"'";
bgd.executeSelect(s_sql);
totalRowNum=bgd.getRowCount();
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

if (totalRowNum>0)
{
%>
已取消课程列表：
<form method="post" name="form2" action="show.jsp">

<TABLE  border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" class="xbz" width="100%">
	<TR bgcolor="#CCCCCC" align="center" Height="25">
		<TD>课程名称</TD>
		<TD>课程<br>类型</TD>
		<TD>学习进度</TD>
		<TD>取消时间</TD>
		<TD>取消原因</TD>
		
	</TR>
	
	<%
	//for (int i=0;i<bgd.getRowCount(); i++)
	String class_type="",class_typename="",class_id="";
	
	for(int i=startRow;i<endRow;i++)
	{
	  
	  class_type=bgd.getFieldValue("class_type",i);
	  if(class_type.equals("0"))
		class_typename="公开";
	  else if(class_type.equals("1"))
		class_typename="注册";
	  else
		class_typename="收费";
	%>
		<TR>
			
		<TD> <%=bgd.getFieldValue("class_name", i)+"&nbsp;"%></TD>
		
		 <TD><%=class_typename+"&nbsp;"%> </TD>
			
		<TD><%=bgd.getFieldValue("schedule", i)+"%"%> </TD>
			
		<TD ><%=beanDate.format(bgd.getFieldValue("cancel_time",i),8)%> </TD>
			
		<TD> <%=bgd.getFieldValue("reason", i).equals("1")?"学员认为不需要学":"管理员认为不需要学"%> </TD>
				
		</TR>
<%
    }
	
}
else
{
	out.println("你没有取消的课程！");
	return ;
}
%>
</TABLE>
<table width="90%" border="0">
    <tr>
    <%
    if(curPageNum>1)
    {
    %>
      <td align='center'><a href="show.jsp?page=<%=curPageNum-1%>" >上页</a></td>
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
      <td align='center'><a href="show.jsp?page=<%=curPageNum+1%>" >下页</a></td>
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
</BODY>
</HTML>
		
		