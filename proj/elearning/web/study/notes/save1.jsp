<%@page language="java" import="com.htyz.*"%>
<%@ page import="java.io.*"%>
<%@page session="true"%>
<%@page import="com.htyz.*, elearn.bean_Menus"%>

<jsp:useBean id="sqlbean" scope="request" class="com.htyz.beanGetdata"/>
<% String st=request.getParameter("MText");
/*String nameOfTextFile="forwrite.htm";
try {
	PrintWriter pw=new PrintWriter(new FileOutputStream(nameOfTextFile));
	pw.println(st);
	pw.close();
}catch(IOException e){
	out.println(e.getMessage());
}
out.println(st);*/
//String u_id=session.getAttribute("userid");
//String sql="select * from tnotes where section_id=1 and user_id=2";
sqlbean.execute("insert into tlesson_log(user_id,class_id,lesson_id.notes)values('"+session.getAttribute("userid")+"','1','2','"+request.getParameter("MText")+"')");
//out.print(session.getAttribute("userid"));
//else{
//stmt.executeUpdate("update tnotes set section_id=2,user_id=2 where note_id='"+rs.getString("note_id")+"'");
//out.print("±£´æ³É¹¦£¡");
//}
%>


