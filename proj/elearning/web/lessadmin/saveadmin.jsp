<%@page contentType="text/html;chatset=gb2312"%>
<html>
<head>
<%@page language="java" import="com.htyz.*"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="sqlbean" scope="request" class="com.htyz.beanGetdata"/>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body bgcolor="#FFFFFF" text="#000000">
<%String userid=(String)session.getAttribute("userid");
if(userid==null||userid=="")
{
out.print("您还没有登录，点击<a href='login.jsp'>这里</a>登陆！");
}
else
{

//String reason=new String(request.getParameter("reason").getBytes("ISO8859_1"));
   String str= request.getParameter("parm");
   System.out.print("this is"+str);
   int curPos=0;
   int prePos=0;
   String subStr=null;
   Vector comp=new Vector();
   boolean flag=false;
	if((str!=null)&&(str.length()!=0))
	{  
		while((curPos=str.indexOf(',',prePos))!=-1)
		{
			subStr=new String();
			subStr=str.substring(prePos,curPos);
			if(subStr==null)
			{
				subStr="";
			}
			prePos=curPos+1;
			comp.addElement(subStr);
		}
		subStr=new String();
		subStr=str.substring(prePos,str.length());
		comp.addElement(subStr);
	}
	for(int m=0;m<comp.size();m++)
	{
	 sqlbean.execute("update t_user_apply set approval_id='"+session.getAttribute("userid")+"',approval_time='"+sqlbean.getDbDate()+"',apply_status='1' where apply_id = '"+comp.elementAt(m)+"'");
	
//查看用户表里是不是已有该门课程了
  //String s_countPara = request.getParameter("countInPage");
  //int i_count = Integer.parseInt(s_countPara);
  //for(int i=0;i<i_count;i++)
 String classid=((String)comp.elementAt(m)).substring(((String)comp.elementAt(m)).length()-14);

 String useid=((String)comp.elementAt(m)).substring(0,((String)comp.elementAt(m)).length()-14);
 //System.out.print(useid);
 sqlbean.executeSelect("select * from t_user_class where user_id='"+useid+"' and class_id='"+classid+"' ");
 if(sqlbean.getRowCount()>0)
{
   if(!flag)
    out.print("课程已申请了！");
    
    flag=true;
}
else
{

sqlbean.execute("insert into t_user_class(user_id,class_id,start_time,status)values('"+useid+"','"+classid+"','"+sqlbean.getDbDate()+"','1')");
}
}
%>批准成功！请点击<a href='adminapply.jsp'>这里</a>返回！<%
}
%>
</body>
</html>
