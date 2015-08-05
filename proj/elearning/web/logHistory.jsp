<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="eds" scope="request" class="com.htyz.util.BeanDateFormat"/>
<link href="../style.css" rel="stylesheet" type="text/css">
<%
int i,j=0;
String Sql_statment="";
String tmp_Field="";
String userid= (String)session.getAttribute("userid");
int Cur_page=1;
String Pre_Sql="select * from t_user_log where User_id='"+userid+"' order by login_time desc";
Sql_statment=Pre_Sql;
if(request.getParameter("Cur_page")!=null)
		  Cur_page=Integer.parseInt(request.getParameter("Cur_page"));

%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/3.css" type="text/css">
<%=userid%>的登录统计...
<form name=form2 action='logHistory.jsp'>
		<%history.executeSelect(Sql_statment);
		if(history.getRowCount()<1){%>没有符合条件的记录！<%
		}else{
			out.println("<table bgcolor=\"#EEEEEE\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" bordercolorlight=\"#000000\" bordercolordark=\"#FFFFFF\" class=\"xbz\" width=\"95%\"><tr Height=25 bgcolor='#E0E0E0' align=center><td>登录用户</td><td>登录时间</td><td>登录IP</td></tr>");
			for(i=((Cur_page-1)*10);i<history.getRowCount()&&i<((Cur_page-1)*10)+10;i++){
				if(i%2==1){
					out.println("<tr Height=25 bgcolor='#DDDDFF'>");
				}else{
					out.println("<tr Height=25 bgcolor='#FFFFFF'>");
				}%>
				<td><%=history.getFieldValue("user_id",i)%></td>
				<td><%=eds.format(history.getFieldValue("login_time",i))%></td>
				<td><%=history.getFieldValue("ip_address",i)%></td>
				</tr>
			<%}
			out.println("<tr Height=25 bgcolor='#C0C0C0' align=right><td colspan=8>");
			if(Cur_page>1){
				out.print("<a href='logHistory.jsp?Cur_page="+Integer.toString(Cur_page-1)+"'>上一页</a>&nbsp;&nbsp;&nbsp;");
			}else{
				out.print("上一页&nbsp;&nbsp;&nbsp;");
			}
			if(Cur_page*10<history.getRowCount()){
				out.print("<a href='logHistory.jsp?Cur_page="+Integer.toString(Cur_page+1)+"'>下一页</a></td></tr></table>");
			}else{
				out.print("下一页</td></tr></table>");
			}
		}		
		%>
</form>
