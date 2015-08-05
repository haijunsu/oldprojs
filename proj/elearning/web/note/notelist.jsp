<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="notelist" scope="request" class="com.htyz.beanGetdata"/>
<html>
<body>
<%
String userid=(String)session.getAttribute("userid");
if(userid==null){
	out.print("您还没有登录或是已经超时，请重新登录！");
}else{
int i=0;
int items=10;
String Sql_statment="";
String tdStyle=" bgcolor='#F2F8FF' onmouseover=\"javascript:this.bgColor='#f0F3Fa';this.style.cursor='default';\" onmouseout=\"javascript:this.bgColor='#F2F8FF';\" valign=\"center\" align=\"left\">";
int Cur_page=1;
String Pre_Sql="select t.class_id,t.lesson_id,c.class_name,l.lesson_name,t.notes from t_lesson_log t,t_class c,t_lesson l where c.class_id=t.class_id and l.class_id=t.class_id and l.lesson_id=t.lesson_id and t.user_id='"+userid+"'";
Sql_statment=Pre_Sql;
if(request.getParameter("search")!=null){
	Pre_Sql=Pre_Sql+" and notes like '%"+request.getParameter("search")+"%'";
	}else{
	  if(request.getParameter("Cur_page")!=null){
			  Cur_page=Integer.parseInt(request.getParameter("Cur_page"));
	  }
}
Sql_statment=Pre_Sql+" order by class_name,lesson_name desc";
//System.out.println(Sql_statment);
%>
  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
	<form name=form1 action='notelist.jsp'>
      <td><div align="right">搜 索： 
          <input type=text name=search size=10>
          <input name="image" type="image" src="../bbs/images/go.gif">
        </div></td>
      </form>
      </tr>
  </table>

<link rel="stylesheet" href="<%=request.getContextPath()%>/3.css" type="text/css">
<form name=form2 action='note.jsp'>
		<%notelist.executeSelect(Sql_statment);
		if(notelist.getRowCount()<1){%>目前还没有任何发表！<%
		}else{
			out.println("<table align=\"center\" width=\"95%\" cellspacing=\"0\" cellpadding=\"3\" border=\"1\" id=\"AutoNumber1\" style=\"border-left:1px solid #60718B; border-right:1px solid #60718B; border-top:1px solid #60718B; padding:0; border-collapse: collapse; border-bottom-width:1\"><tr Height=25 bgcolor='#A9B6CD' align=center><td>课程名称</td><td>章节名称</td><td>学习笔记</td></tr>");
			for(i=((Cur_page-1)*items);i<notelist.getRowCount()&&i<((Cur_page-1)*items)+items;i++){
				out.println("<tr Height=25>");
				out.println("<TD width=200"+tdStyle+"<a href='note.jsp?Class_id="+notelist.getFieldValue("class_id",i)+"&lesson_id="+notelist.getFieldValue("lesson_id",i)+"'>"+notelist.getFieldValue("class_name",i)+"</a></td>");
				out.println("<TD width='200'"+tdStyle+notelist.getFieldValue("lesson_name",i)+"</td>");
				out.println("<TD width='*'"+tdStyle+notelist.getFieldValue("notes",i)+"</td></tr>");
			}
			out.println("<tr Height=25 bgcolor='#FFFFFF' align=right>");
			out.println("<td colspan=2 align='left'>本论坛共有");
			Double aaa=new Double((notelist.getRowCount()-1)/items+1);
			int bbb=aaa.intValue();
			out.println(bbb);
			out.print("页&nbsp;&nbsp;&nbsp;[&nbsp;");
			for(i=1;i<(bbb+1);i++){
				out.println("<a href='notelist.jsp?Cur_page="+Integer.toString(i)+"'>"+Integer.toString(i)+"</a>");
			}
			out.println("]</td><td><font color='#FF3333'>");
			if(Cur_page>1){
				out.print("<a href='notelist.jsp?Cur_page="+Integer.toString(Cur_page-1)+"'>上一页</font></a>&nbsp;&nbsp;&nbsp;");
			}else{
				out.print("上一页&nbsp;&nbsp;&nbsp;");
			}
			if(Cur_page*items<notelist.getRowCount()){
				out.print("<a href='notelist.jsp?Cur_page="+Integer.toString(Cur_page+1)+"'>下一页</a></td></tr></table>");
			}else{
				out.print("下一页</td></tr></font></table>");
			}
		}		
		%>
</form>
<%}%>