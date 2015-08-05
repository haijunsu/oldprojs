<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="classlist" scope="request" class="com.htyz.beanGetdata"/>
<html>
<body background="images/bg-book2.gif" leftmargin="50">
<%
int i,j=0;
String Sql_statment="";
String tmp_Field="";
String itemType="所有分类";
int Cur_page=1;
String Pre_Sql="select t_class.class_id,t_class.class_name,t_class.keywords,b.code_namec,a.code_namec,convert(char(12),t_class.set_time,101),t_class.peoples from t_class,(select * from t_code where code_id in(select code_value from t_code where code_id='0013')) a ,(select * from t_code where code_id='0013') b where t_class.class_type='0' and  left(t_class.class_id,8)=a.code_id+a.code_value and a.code_id=b.code_value and t_class.class_status='1'";
Sql_statment=Pre_Sql;
if(request.getParameter("search")!=null){
	Sql_statment=Pre_Sql+" and t_class.class_name like '%"+request.getParameter("search")+"%'";
  }else	{
	  if(request.getParameter("class_type")!=null){
		  itemType=new String(request.getParameter("itemType").getBytes("ISO8859_1"));
		  	Sql_statment=Pre_Sql+"  and left(class_id,4)='"+request.getParameter("class_type")+"'";
			if(request.getParameter("class_type2")!=null){
				Sql_statment=Pre_Sql+" and left(class_id,8)='"+request.getParameter("class_type")+request.getParameter("class_type2")+"'";
		  }
	  }else{
		  itemType="所有分类";
		  if(request.getParameter("Cur_page")!=null)
			  Cur_page=Integer.parseInt(request.getParameter("Cur_page"));
	  }
  }
classlist.executeSelect(Sql_statment);
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/3.css" type="text/css">
<form name=form1 action='ShowClassList.jsp'>
		搜索：<input type=text name=search size=10>
		<input type="image" SRC="images/go.gif">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在<%=itemType%>中共有<%=classlist.getRowCount()%>门课程...
</form>
<form name=form2 action='ShowClassList.jsp'>
		<%
		if(classlist.getRowCount()<1){%>没有符合条件的记录！<%
		}else{
			out.println("<table Width='100%'><tr Height=35 bgcolor='#E0E0E0' align=center><td>课程编号</td><td>课程名称</td><td>关键字</td><td>类别</td><td>二级类别</td><td>加入时间</td><td>学习人数</td></tr>");
			for(i=((Cur_page-1)*10);i<classlist.getRowCount()&&i<((Cur_page-1)*10)+10;i++){
				if(i%2==1){
					out.println("<tr Height=25 bgcolor='#EEEEFF'>");
				}else{
					out.println("<tr Height=25 bgcolor='#FFEEEE'>");
				}
				for(j=1;j<8;j++){
					if(j==2){
						out.println("<td><a href='selectclass.jsp?class_id="+tmp_Field+"'>"+classlist.getFieldValue(j,i)+"</a></td>");
					}else{
						tmp_Field=classlist.getFieldValue(j,i);
						out.println("<td>"+tmp_Field+"</td>");
					}
				}
				out.println("</tr>");
			}
			out.println("<tr Height=25 bgcolor='#C0C0C0' align=right><td colspan=7><font color='#FF3333'>");
			if(Cur_page>1){
				out.print("<a href='ShowClassList.jsp?Cur_page="+Integer.toString(Cur_page-1)+"'>上一页</font></a>&nbsp;&nbsp;&nbsp;");
			}else{
				out.print("上一页&nbsp;&nbsp;&nbsp;");
			}
			if(Cur_page*10<classlist.getRowCount()){
				out.print("<a href='ShowClassList.jsp?Cur_page="+Integer.toString(Cur_page+1)+"'>下一页</a></td></tr></table>");
			}else{
				out.print("下一页</td></tr></font></table>");
			}
		}		
		%>
</form>
