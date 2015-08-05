<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="Postsave" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<%
String Bi_id,Br_id,Cat_id,user_id,Bi_title,Bi_content,Bi_date,Bi_emotion,method;
String Sql_statment;
method=request.getParameter("method");
Cat_id=request.getParameter("Cat_id");
Bi_id=request.getParameter("Bi_id");
user_id=(String)session.getAttribute("userid");
Bi_title=ets.getGBKString(request.getParameter("Bi_title"));
Bi_content=ets.getGBKString(request.getParameter("Message"));
Bi_emotion=request.getParameter("usericon");

if(method.equals("Reply")){
	Postsave.executeSelect("select max(Br_id) from t_bbs_reply");
	Br_id=ets.AutoNum(Postsave.getFieldValue(1,0));
	Sql_statment="Insert Into t_bbs_reply (Br_id,Bi_id,user_id,Br_emotion,Br_content,Br_date) Values("+Br_id+","+Bi_id+",'"+user_id+"','"+Bi_emotion+"','"+Bi_content+"',getdate())";
	Postsave.execute(Sql_statment);
	Sql_statment="Update t_bbs_info set Bi_reply=Bi_reply+1,Last_time=getdate(),Last_Poster='"+user_id+"' where Cat_id="+Cat_id+" and Bi_id="+Bi_id;
	Postsave.execute(Sql_statment);
}else{
	Postsave.executeSelect("select max(Bi_id) from t_bbs_info");
	Bi_id=ets.AutoNum(Postsave.getFieldValue(1,0));
	Sql_statment="Insert Into t_bbs_info (Bi_id,Cat_id,user_id,Bi_title,Bi_content,Bi_date,Bi_emotion,Bi_hits,Bi_reply,last_time,status) Values("+Bi_id+","+Cat_id+",'"+user_id+"','"+Bi_title+"','"+Bi_content+"',getdate(),'"+Bi_emotion+"',0,0,getdate(),'1')";
	Postsave.execute(Sql_statment);
}
response.sendRedirect("Postlist.jsp?Cat_id="+Cat_id);

%>
