<%@ page contentType="text/html; charset=gb2312" %>

<%@ page import="java.util.*"%>

<jsp:useBean id="cladel" scope="request" class="com.htyz.beanGetdata"/>

<html>
<head>
<title>
</title>
</head>
<body >
<%
String user_id=(String)session.getAttribute("userid");
String class_id=request.getParameter("classid");
String percent=request.getParameter("percent");


String s_sql="",s_sq="",s_insert="";
int count=0;

s_insert="insert  into t_userclass_del(user_id,class_id,start_time,end_time,status,cancel_time,reason,schedule) select user_id,class_id,start_time,end_time,status,'"+cladel.getDbDate()+"','1','"+percent+"' from t_user_class where user_id= '"+user_id+"' and class_id='"+class_id+"' ";

s_sql="delete from t_user_class where user_id= '"+user_id+"' and class_id='"+class_id+"' ";
s_sq="delete from t_lesson_log where user_id= '"+user_id+"' and class_id='"+class_id+"' ";
if(user_id!=null)
{	
	try
	{
		 
		count=cladel.execute(s_insert);
		if(count>0)
		{
			count=cladel.execute(s_sql);
			
			if(!(percent.equals("0")))
			   count=cladel.execute(s_sq);
			 if(count>0)
			 {
			  out.println("删除成功!");
			 }
			 else
			 {
			   out.println("删除失败!");
			 }
			
		 }
		else
		{
          out.println("备份失败!");
		}
	}
	catch(Exception e)
	{
	  e.printStackTrace();
	}
}
else
{
	out.println("用户名为空,请重新登陆！");
}

%>
</body>
</html>
