<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<%@ page import="com.htyz.notice.*"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="bgdclass" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="notbean" scope="session" class="com.htyz.notice.noticeBean" />

<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<HTML>
<HEAD>
<TITLE>用户课程表</TITLE>
<link href="../style.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
<%
String userid="",temUser="";
temUser = (String)session.getAttribute("userid");
userid =  request.getParameter("yhh") ;
if(userid==null)   
   userid=temUser;

if (userid==null||userid.equals(""))
	response.sendRedirect("../login.jsp");
String sql = "SELECT uc.user_id AS user_id, uc.class_id AS class_id, c.class_name AS class_name,c.pass_stander as pass_stander FROM t_user_class uc, t_user u, t_class c WHERE uc.class_id = c.class_id AND uc.status = '1' AND (uc.user_id = u.user_id OR uc.user_id = u.group_id) AND u.user_id = '" + userid + "'";
bgd.executeSelect(sql);
%>
<form method="post" name="form1" action="lessonSchedule.jsp">

<TABLE class=table002 width="100%" bgcolor="#FF9966"> 
	<TR>
		<TD>
          <table class=table0002 width="100%" bgcolor="#FF9966"> 
			<tr>
			  <td><B>我的学习进度报告</B>=====(共<%=bgd.getRowCount()%>门课程真在学习) </td>
			  <td  color="#0000FF" >用户名</td>
			  <td >		  
				
				  <%
				  Vector vec=new Vector();
				  Vector ve=new Vector();
				  userDO urdo = null;
				  String tmpUser="";
				  //groupDO grdo = null;
				  vec = notbean.getuserData();
				 // ve = notbean.getGroupData();
				 // session.setAttribute("user_name",ve);
				 if(temUser.equals("test"))
				
					out.println("<select name='yhh'  onchange='form1.submit()'>");	
				 else	
				    out.println("<select disabled name=yhh'  onchange='form1.submit()'>");	
				  for(int i=0;i<vec.size();i++)
				  {
					urdo = new userDO();
					urdo = (userDO)vec.elementAt(i);
                    if(userid.equals(urdo.getUser_id()))
					  {
						out.println("<option selected value="+urdo.getUser_id()+">"+urdo.getUser_name()+"</option>");
					  }
					  else
					  {
						  out.println("<option  value="+urdo.getUser_id()+">"+urdo.getUser_name()+"</option>");
					  }
				  }
				 
				  %>
				</select>
			  </td>
			</tr>
		   </table>
		</TD>
	
	</TR>
	<TR>
	<TD>
	<!-- 开始显示课程表 -->
	<TABLE   border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="100%">
<%
	for (int i=0; i<bgd.getRowCount(); i++)
	{
		//计算学习进度
		String percent, remain;
		sql = "SELECT count(lesson_id) FROM t_lesson WHERE class_id = '" + bgd.getFieldValue("class_id", i) + "'";
		bgdclass.executeSelect(sql);
		String totalSection = bgdclass.getFieldValue(1, 0);
		sql = "SELECT count(lesson_id) FROM t_lesson_log WHERE class_id = '" + bgd.getFieldValue("class_id", i) + "' AND user_id = '" + userid + "'";
		bgdclass.executeSelect(sql);
		String userSection = bgdclass.getFieldValue(1, 0);
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
		<TD><A href=../lesson/lessonInfo.jsp?classid=<%=bgd.getFieldValue("class_id", i)%> target=_blank><%=bgd.getFieldValue("class_name", i)%></A>
		</TD>
		<TD>
			<%=bgd.getFieldValue("user_id",i).trim().length()==2?"必修课":"选修课"%>
		</TD>
		<TD>
			<table width='100' border='0' cellspacing='0' cellpadding='0'><tr><td background='<%=request.getContextPath()%>/images/Line001.gif' height='5' width='<%=percent%>%'></td><td background='<%=request.getContextPath()%>/images/Line002.gif' height='5' width='<%=remain%>%'></td></tr></table>
			<%=percent%>%
		</TD>
	
	</TR>
<% } %>
	</TABLE>
	</TD>
    </TR>
</TABLE>
</form>
</BODY>
</HTML>