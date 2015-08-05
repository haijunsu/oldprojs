<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="Postsave" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>

<%
String lesson_id,class_id,userid,notes;
String Sql_statment;
class_id=request.getParameter("class_id");
lesson_id=request.getParameter("lesson_id");
userid=ets.getGBKString(request.getParameter("userid"));
notes=ets.getGBKString(request.getParameter("Message"));
Postsave.executeSelect("select * from t_lesson_log where user_id='"+userid+"' and class_id='"+class_id+"' and lesson_id='"+lesson_id+"'");

if(Postsave.getRowCount()<1){
	Sql_statment="Insert Into t_lesson_log (class_id,lesson_id,user_id,notes) Values('"+class_id+"','"+lesson_id+"','"+userid+"','"+notes+"')";
}else{
	Sql_statment="Update t_lesson_log set notes='"+notes+"' where class_id='"+class_id+"' and lesson_id='"+lesson_id+"' and user_id='"+userid+"'";
}
Postsave.execute(Sql_statment);
response.sendRedirect("notelist.jsp");
%>
