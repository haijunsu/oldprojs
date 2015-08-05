<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="com.htyz.notice.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import ="java.net.InetAddress.*"%>

<jsp:useBean id="online" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="beanDate" scope="session" class="com.htyz.util.BeanDateFormat" />

<html>
<head>
<title>
notice
</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../3.css" type="text/css">
<link href="../style.css" rel="stylesheet" type="text/css">
</head>
<jsp:useBean id="notbean" scope="session" class="com.htyz.notice.noticeBean" />
<jsp:setProperty name="noticeBeanId" property="*" />
<%
String user_id=(String)session.getAttribute("userid");
String group_id=(String)session.getAttribute("groupid");
//if (user_id==null) user_id="invaliduser";
//if (group_id==null) group_id="invalidgroupid";

//String ip_address=(String)session.getAttribute("ipaddress");
String ip_address= java.net.InetAddress.getLocalHost().toString();

String s_sql="",s_status="正在读通知";
s_sql="insert into t_user_online(user_id,login_time,ip_address,status) " ; s_sql=s_sql+"values('"+user_id+"','"+online.getDbDate()+"','"+ip_address+"','"+s_status+"' )" ;
if(user_id!=null)
{
	try
	{
		 online.execute(s_sql);
		//beantool.connectdatabase();
		//beantool.SaveUserOnline(user_id,ip_address,"正在读通知");
	}
	catch(Exception e)
	{
	  e.printStackTrace();
	}
}

%>
<body text="#000000" bgcolor="#FFFFFF">
<br>
<%
    try
    {
      String notice_name=" ",to_time = " ",valid_time = " ",notice_conent="",notice_id="";
	  String notice_time="";
       Vector vect = new Vector();
       //notbean.connectdatabase();
       vect = notbean.getDataByUser(user_id,group_id);
       //System.out.println("the size is ==="+vect.size());
	   if(vect.size()==0)
       {
         out.println("该用户无通知！");
         return ;

       }
%>
	<TABLE width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#eeeeee">

		<TR align="center" bgcolor="#cccccc"> 		 
		  <td height="20" align="center" >通知名称</td>
		  <td height="20" align="center" >通知时间</td>
		  <td height="20" align="center">生效时间</td>
		  <td height="20" align="center">截止时间</td>
		</tr>
<%
      for(int i=0;i<vect.size();i++)
      {
        noticeDO ntcDO = new noticeDO();
        ntcDO = (noticeDO)vect.elementAt(i);
		notice_id=ntcDO.getNotice_id();
        notice_name=ntcDO.getNotice_name();
		//notice_time =(ntcDO.getNotice_time()).substring(0,10);
        //valid_time =(ntcDO.getValid_time()).substring(0,10);
        //to_time =(ntcDO.getTo_time()).substring(0,10);

		notice_time=beanDate.format(ntcDO.getNotice_time(),14);
		valid_time=beanDate.format(ntcDO.getValid_time(),14);
		to_time=beanDate.format(ntcDO.getTo_time(),14);
        notice_conent=ntcDO.getNotice_content();
 %>
       
        <tr >
		  
		  <td height="20" align="center"><a href="notdetail.jsp?parm=<%=notice_id%>"><%=notice_name%></a></td>
		  <td height="20" align="center"><%=notice_time%></td>
		  <td height="20" align="center"><%=valid_time%></td>
		  <td height="20" align="center"><%=to_time%></td>
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
</body>
</html>
