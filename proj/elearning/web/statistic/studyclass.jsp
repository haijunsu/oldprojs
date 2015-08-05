<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<%@ page import="com.htyz.notice.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.htyz.*"%>

<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="notbean" scope="session" class="com.htyz.notice.noticeBean" />

<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%

String userid = (String)session.getAttribute("userid");

String s_sql="",code_namec="";

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

s_sql="select distinct a.class_id,a.class_name,a.class_type,a.description,a.class_time,a.pass_stander,a.peoples from t_class a,t_lesson_log b where a.class_id=b.class_id and b.user_id='"+userid+"'";
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
已学课程列表：
<form method="post" name="form2" action="show.jsp">

<TABLE  border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" class="xbz" width="100%">
	<TR bgcolor="#CCCCCC" align="center" Height="25">
		<TD>课程名称</TD>
		<TD>课程<br>类型</TD>
		<TD>课程类</TD>
		<TD width="250">描述</TD>
		<TD>课时</TD>
		<TD>通过<br>标准</TD>
		<TD>点击<br>数</TD>
	</TR>
	
	<%
	//for (int i=0;i<bgd.getRowCount(); i++)
	String class_type="",class_typename="",class_id="";
	Vector vec=new Vector();
	userDO urdo = null;
	vec=notbean.getcode();
	
	for(int i=startRow;i<endRow;i++)
	{
	  class_id=(bgd.getFieldValue("class_id",i)).substring(0,8);
	  //System.out.println("the class_id======="+class_id);
	  for(int j=0;j<vec.size();j++)
	  {
         urdo = new userDO();
         urdo = (userDO)vec.elementAt(j);
		 
		 if((urdo.getUser_id()).equals(class_id))
		 {
			 code_namec=urdo.getUser_name();
			
			 break;
		 }
	   
	   }
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
			
		<TD><%=code_namec+"&nbsp;"%> </TD>
			
		<TD width="250"> <%=bgd.getFieldValue("description", i)+"&nbsp;"%> </TD>
			
		<TD> <%=bgd.getFieldValue("class_time", i)%> </TD>
			
		<TD> <%=bgd.getFieldValue("pass_stander", i)%> </TD>
		<TD> <%=bgd.getFieldValue("peoples", i)%> </TD>
		</TR>
<%
    }
	
}
else
{
	out.println("没有课程！");
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
		
		