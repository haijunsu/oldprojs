<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<%@page import="com.htyz.*"%>
<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="bgda" scope="request" class="com.htyz.beanGetdata"/>

<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<HTML>
<HEAD>
<TITLE>用户学习阅读</TITLE>
</HEAD>
<BODY>
<%
String userid = (String)session.getAttribute("userid");
String classid = request.getParameter("classid");
String lessonid = request.getParameter("lessonid");
String url = request.getParameter("url");

if (userid==null||userid.equals(""))
	response.sendRedirect("../login.jsp");
if (classid == null)
{
	out.println("<FONT color=red>错误：<p>没有指定课程</FONT>");
}
else if (lessonid == null)
{
	out.println("<FONT color=red>错误：<p>没有指定章节</FONT>");
}
else if (url == null)
{
	out.println("<FONT color=red>错误：<p>没有指定章节的URL</FONT>");
}
else
{
	session.setAttribute("classid",classid);
	session.setAttribute("lessonid",lessonid);
	String s_sql = "SELECT lesson_name FROM t_lesson WHERE class_id = '" + classid + "' AND lesson_id = '" + lessonid + "'";

	String sql = "SELECT lesson_count FROM t_lesson_log WHERE class_id = '" + classid + "' AND lesson_id = '" + lessonid + "' AND user_id = '" + userid + "'";
	bgd.executeSelect(sql);
	if (bgd.getRowCount() == 0)
	{
		sql = "INSERT INTO t_lesson_log (user_id, class_id, lesson_id, lesson_count) VALUES ('" + userid + "', '" + classid + "','" + lessonid + "', 1)";
		bgd.execute(sql);
	}
	else
	{
		String count = bgd.getFieldValue("lesson_count", 0).trim();
		if (count.equals(""))
			count = "0";
		int i = Integer.parseInt(count) + 1;
		sql = "UPDATE t_lesson_log set lesson_count = " + i + " WHERE class_id = '" + classid + "' AND lesson_id = '" + lessonid + "' AND user_id = '" + userid + "'";
		bgd.execute(sql);
	}
	bgd.executeSelect(s_sql);
	String treetitle=(String)session.getAttribute("treetitle");
	//去掉链接<a href='*.jsp'> </a>
	if(treetitle!=null&&treetitle.length()>0)
	{
		treetitle=treetitle.substring(treetitle.indexOf('>'));
		treetitle=treetitle.substring(1,treetitle.indexOf('<'));
	}
	String lesson_name=bgd.getFieldValue("lesson_name", 0);
	//String ip_address= java.net.InetAddress.getLocalHost().toString();
    //String ServerUrl=request.getRemoteAddr();
    String  ip_address=request.getRemoteHost();
	String s_log="",s_status="正在学习课程："+treetitle+"  章节："+lesson_name;
	String s_st="正在学习课程：";
    s_log="insert into t_user_online(user_id,login_time,ip_address,status) " ; s_log=s_log+"values('"+userid+"','"+bgd.getDbDate()+"','"+ip_address+"','"+s_status+"' )" ;
    bgda.execute(s_log);
	response.sendRedirect(url);
	
	//out.println("the classname===="+treetitle);
   
}
%>

</BODY>
</HTML>