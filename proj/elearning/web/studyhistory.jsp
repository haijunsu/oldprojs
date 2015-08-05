<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="history" scope="request" class="com.htyz.beanGetdata"/>
<%
int i,j=0;
String Sql_statment="";
String tmp_Field="";
String userid= (String)session.getAttribute("userid");
int Cur_page=1;
String Pre_Sql="select tlesson_log.study_time,tclass.class_name,tlesson.lesson_name from tlesson_log,tclass,tlesson where tclass.class_id=tlesson_log.class_id and tlesson.class_id=tlesson_log.class_id and tlesson.lesson_id=tlesson_log.lesson_id and User_id='"+userid+"' order by study_time";
Sql_statment=Pre_Sql;
if(request.getParameter("search")!=null){
	Sql_statment=Pre_Sql+" and tclass.class_name like '%"+request.getParameter("search")+"%'";
  }else	{
	  if(request.getParameter("class_type")!=null){
		  	Sql_statment=Pre_Sql+"  and left(class_id,4)='"+request.getParameter("class_type")+"'";
			if(request.getParameter("class_type2")!=null){
				Sql_statment=Pre_Sql+" and left(class_id,8)='"+request.getParameter("class_type")+request.getParameter("class_type2")+"'";
		  }
	  }else{
		  if(request.getParameter("Cur_page")!=null)
			  Cur_page=Integer.parseInt(request.getParameter("Cur_page"));
	  }
  }
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/3.css" type="text/css">
<form name=form1 action='studyhistory.jsp'>
		搜索：<input type=text name=search size=10>
		<input type="image" SRC="images/go.gif">
</form>
<form name=form2 action='studyhistory.jsp'>
		<%history.executeSelect(Sql_statment);
		if(history.getRowCount()<1){%>没有符合条件的记录！<%
		}else{
			out.println("<table Width='100%'><tr Height=25 bgcolor='#E0E0E0'><td colspan=3>"+userid+"的学习历史...</td></tr><tr Height=25 bgcolor='#E0E0E0' align=center><td>学习时间</td><td>课程名称</td><td>所学章节</td></tr>");
			for(i=((Cur_page-1)*10);i<history.getRowCount()&&i<((Cur_page-1)*10)+10;i++){
				if(i%2==1){
					out.println("<tr Height=25 bgcolor='#EEEEFF'>");
				}else{
					out.println("<tr Height=25 bgcolor='#FFEEEE'>");
				}
				for(j=1;j<4;j++){
					out.println("<td>"+history.getFieldValue(j,i)+"</td>");
				}
				out.println("</tr>");
			}
			out.println("<tr Height=25 bgcolor='#C0C0C0' align=right><td colspan=8>");
			if(Cur_page>1){
				out.print("<a href='studyhistory.jsp?Cur_page="+Integer.toString(Cur_page-1)+"'>上一页</a>&nbsp;&nbsp;&nbsp;");
			}else{
				out.print("上一页&nbsp;&nbsp;&nbsp;");
			}
			if(Cur_page*10<history.getRowCount()){
				out.print("<a href='studyhistory.jsp?Cur_page="+Integer.toString(Cur_page+1)+"'>下一页</a></td></tr></table>");
			}else{
				out.print("下一页</td></tr></table>");
			}
		}		
		%>
</form>
