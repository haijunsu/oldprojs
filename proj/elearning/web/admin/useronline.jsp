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
String userid=request.getParameter("yhh");
if(userid==null) userid="";
String querytime=request.getParameter("time");
if(querytime==null) querytime="0";
String str_ip=request.getParameter("ip");
if(str_ip==null) str_ip="";
String str_status=request.getParameter("status");
if(str_status==null) str_status="";

String s_ip="%"+str_ip+"%";
String s_status="%"+str_status+"%";
String parm=request.getParameter("parm");

if(parm.equals("online"))
{
%>
   <p><div align="center"><font face="华文行楷" size='5'>在线监控</font></div></p>
<%
}

else
{
%>
   <p><div align="center"><font face="华文行楷" size='5'>操作日志</font></div></p>
<%
}
%>


<form name = formdata method="post" action="">
  

<%
if(!(parm.equals("online")))
{

%>
   <TABLE class=table003>
	<TR>		
		<TD>用户名：
		   <%
				  Vector vec=new Vector();
				  Vector ve=new Vector();
				  userDO urdo = null;
				  
				  vec = notbean.getuserData();
				 
				 
				  out.println("<select name='yhh' >");	
				  out.println(" <OPTION value='' >-----所有------</OPTION>");
				 
				
				  for(int i=0;i<vec.size();i++)
				  {
					urdo = new userDO();
					urdo = (userDO)vec.elementAt(i);
                    if(userid.equals(urdo.getUser_id()))
					  {
						out.println("<option selected value="+urdo.getUser_id()+">"+urdo.getUser_name()+"</option>");
					  }
					  else
					  {
						  out.println("<option  value="+urdo.getUser_id()+">"+urdo.getUser_name()+"</option>");
					  }
				  }
				 
				  %>			
		</SELECT>
		</TD>
		<TD>时间：
			<SELECT style="WIDTH: 150px"  size=1 name="time"> 
			<OPTION value="0" >------所有------</OPTION>
			<OPTION value="7" <%=querytime.equals("7")?"selected":" "%> >近一周 </OPTION>
			<OPTION value="30" <%=querytime.equals("30")?"selected":" "%> >近一月 </OPTION>	
			<OPTION value="90" <%=querytime.equals("90")?"selected":" "%> >近三月 </OPTION>	
			<OPTION value="180" <%=querytime.equals("180")?"selected":" "%> >近半年 </OPTION>
			<OPTION value="365" <%=querytime.equals("365")?"selected":" "%> >近一年 </OPTION>
			<OPTION value="730" <%=querytime.equals("730")?"selected":" "%> >近二年 </OPTION>
			<OPTION value="1095" <%=querytime.equals("1095")?"selected":" "%> >近三年 </OPTION>
													
		 </SELECT>
		</TD>
		
		
	</TR>
	<TR>		
		<TD>IP地址：
		  <INPUT type="text" name="ip" id="ip" value="<%=str_ip%>" size="15">
		</TD>
		<TD colspan="3">状态：
			<INPUT type="text" name="status" id="status" value="<%=str_status%>" size="25">
		</TD>
		
		<TD>
		<INPUT type="submit" value="查找">
		</TD>
	</TR>
</TABLE>
</P>
<%
}
%>
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

	  String login_time=" ",ip_address = " ",status=" ";
      String s_Sql="",str="";
	  String tmp="";
	  String strSys="",strdate="";
      Vector vect = new Vector();
	  int lenSys=0,lendate=0;
	  long intsysdate=0,intsys=0,intdate=0,intsearch_date=0;
	  tmp=(online.getDbDate()).trim();
	  intsysdate=Long.parseLong(tmp);
      intsys=intsysdate-180000; //小于当前日前日期3分钟
	  intdate=intsysdate-259200000;//小于当前日前日期3天
	 
	  strSys=(new Long(intsys)).toString();
      strdate=(new Long(intdate)).toString();

	  lenSys=strSys.length();
	  lendate=strdate.length();
	  for(int i=0;i<15-lenSys;i++)
	  {
       strSys="0"+strSys;
	  }
	  for(int j=0;j<15-lendate;j++)
	  {
       strdate="0"+strdate;
	  }
	   //System.out.println("the tmp====="+tmp);
	 
	  int inttime= Integer.parseInt(querytime);
	 
	  long daytime=0,oneday=86400000;
	 
	  daytime=oneday*inttime;
	  long longdate=intsysdate-daytime;//小于当前日前日期inttime天(1天=86400000)
	  if(inttime==0) longdate=0;
	
	  String searchdate=(new Long(longdate)).toString();
	  int ints=searchdate.length();
      for(int m=0;m<15-ints;m++)
	  {
        searchdate="0"+searchdate;
	  }
	  if(parm.equals("online"))
	  {
      
        s_Sql = "select * from t_user_online where login_time  >= '"+strSys+"' order by login_time desc ";
	    
	  }
	  else	  
	  {
	   //str = "delete  from t_user_online where login_time <= '"+strdate+"' ";
	   if(userid.equals(""))
	    	 s_Sql = "select * from t_user_online where  login_time>='"+searchdate+"' and  ip_address like '"+s_ip+"' and status like '"+s_status+"' order by login_time desc ";
	   else
		 s_Sql = "select * from t_user_online where user_id='"+userid+"' and login_time>='"+searchdate+"' and  ip_address like '"+s_ip+"' and status like '"+s_status+"' order by login_time desc ";
	   //online.execute(str);
	  }
	
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
      <td align='center'><a href="useronline.jsp?page=<%=curPageNum-1%>&parm=<%=parm%>&yhh=<%=userid%>&time=<%=querytime%>&ip=<%=str_ip%>&status=<%=str_status%>" >上页</a></td>
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
	  <td align='center'><a href="useronline.jsp?page=<%=curPageNum+1%>&parm=<%=parm%>&yhh=<%=userid%>&time=<%=querytime%>&ip=<%=str_ip%>&status=<%=str_status%>" >下页</a></td>
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
   <input type="hidden" value="<%=parm%>" name="parm">


</form>
<p>&nbsp; </p>
</body>
</html>
