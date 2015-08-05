<%@page contentType="text/html;chatset=gb2312"%>
<html>
<head>
<%@page language="java" import="com.htyz.*"%>
<jsp:useBean id="sqlbean" scope="request" class="com.htyz.beanGetdata"/>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<%//String reason=new String(request.getParameter("reason").getBytes("ISO8859_1"));
String s_countPara = request.getParameter("countInPage");
	   	int i_count = Integer.parseInt(s_countPara);
	   	String s_MsgId = "";
	for (int i=0;i<i_count;i++)
	   	{
	   	String s_selectedItem =request.getParameter("select" + Integer.toString(i));
		
		s_MsgId = s_MsgId + ",'" + s_selectedItem + "'";
		}
	   if (!s_MsgId.equals(""))
				{
					s_MsgId = s_MsgId.substring(1, s_MsgId.length());
					System.out.println(s_MsgId);
sqlbean.execute("update t_user_apply set approval_id='"+session.getAttribute("userid")+"',approval_time=getdate(),apply_status='1' where apply_id in ("+s_MsgId+")");
sqlbean.execute("insert into t_user_class(user_id,class_id,start_time,status)values('"+request.getParameter("name")+"','"+request.getParameter("classcode")+"',getdate(),'1')");
//System.out.print("update t_user_apply set approval_id='"+session.getAttribute("userid")+"',approval_time=getdate(),apply_status='1' where apply_id in ("+s_MsgId+")");
}
out.print("批准成功！请点击<a href='adminapply.jsp'>这里</a>返回！");
%>
</body>
</html>
