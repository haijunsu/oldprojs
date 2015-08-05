<%@ page contentType="text/html;charset=GB2312" %>
<jsp:useBean id="lessonScheduleBgd" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="lessonScheduleBgdclass" scope="request" class="com.htyz.beanGetdata"/>
<SCRIPT LANGUAGE=javascript>
function confirmer()
{
 
 if(confirm("确定删除么?"))
 {
    return true ;

 }
 else
 {
  return false ;
 }

}

</SCRIPT>
<%
String lessonScheduleUserid = (String)session.getAttribute("userid");
if (lessonScheduleUserid==null||lessonScheduleUserid.equals(""))
	response.sendRedirect("../login.jsp");
String lessonScheduleSql = "SELECT uc.user_id AS user_id, uc.class_id AS class_id, c.class_name AS class_name,c.pass_stander as pass_stander FROM t_user_class uc, t_user u, t_class c WHERE uc.class_id = c.class_id AND uc.status = '1' AND (uc.user_id = u.user_id OR uc.user_id = u.group_id) AND u.user_id = '" + lessonScheduleUserid + "'";
lessonScheduleBgd.executeSelect(lessonScheduleSql);
%>
<TABLE border="0" width="100%"> 
	<TR>
		<TD><B>我的课程表</B>================(共<%=lessonScheduleBgd.getRowCount()%>门课程真在学习)</TD>
	</TR>
	<TR>
	<TD>
	<!-- 开始显示课程表 -->
	<TABLE width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#eeeeee">
	<TR bgcolor="#55CCCC" align="center" Height="25"><TD>课程名称</TD><TD>课程类型</TD><TD>学习进度</TD><TD>开始学习</TD><TD>删除</TD></TR>
<%
	for (int i=0; i<lessonScheduleBgd.getRowCount(); i++)
	{
		//计算学习进度
		String percent, remain;
		lessonScheduleSql = "SELECT count(lesson_id) FROM t_lesson WHERE class_id = '" + lessonScheduleBgd.getFieldValue("class_id", i) + "'";
		lessonScheduleBgdclass.executeSelect(lessonScheduleSql);
		String totalSection = lessonScheduleBgdclass.getFieldValue(1, 0);
		lessonScheduleSql = "SELECT count(lesson_id) FROM t_lesson_log WHERE class_id = '" + lessonScheduleBgd.getFieldValue("class_id", i) + "' AND user_id = '" + lessonScheduleUserid + "'";
		lessonScheduleBgdclass.executeSelect(lessonScheduleSql);
		String userSection = lessonScheduleBgdclass.getFieldValue(1, 0);
		float a = Float.parseFloat(totalSection);
		float b = Float.parseFloat(userSection);
		if ((int)a == 0)
		{
			percent = "0";
			remain = "100";
		}
		else
		{
			a = b/a*100;
			percent = new java.text.DecimalFormat("0").format(a);
			a = 100 - a;
			remain = new java.text.DecimalFormat("0").format(a);
		}
			
%>
	<TR>
		<TD><A href=../lesson/lessonInfo.jsp?classid=<%=lessonScheduleBgd.getFieldValue("class_id", i)%> target=_blank><%=lessonScheduleBgd.getFieldValue("class_name", i)%></A>
		</TD>
		<TD>
			<%=lessonScheduleBgd.getFieldValue("user_id",i).trim().length()==2?"必修课":"选修课"%>
		</TD>
		<TD>
			<table width='100' border='0' cellspacing='0' cellpadding='0'><tr><td background='<%=request.getContextPath()%>/images/Line001.gif' height='5' width='<%=percent%>%'></td><td background='<%=request.getContextPath()%>/images/Line002.gif' height='5' width='<%=remain%>%'></td></tr></table>
			<%=percent%>%
		</TD>
		<TD>
			<A href=study.jsp?classid=<%=lessonScheduleBgd.getFieldValue("class_id", i)%> target=_parent>继续学习</A>			
		</TD>
		<TD>
			<%if(lessonScheduleBgd.getFieldValue("user_id",i).trim().length()!=2){%><A
			 href=../lesson/deleteclass.jsp?percent=<%=percent%>&classid=<%=lessonScheduleBgd.getFieldValue("class_id", i)%> onclick="return confirmer()">删除</A>	
			<%}else{out.print("&nbsp;");}%>
		</TD>
	</TR>
<% } %>
	</TABLE>
	</TD>
    </TR>
</TABLE>
