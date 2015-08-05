<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<%@ page import="com.htyz.system.SystemProperties"%>
<%@ page import="com.htyz.notice.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.htyz.*"%>

<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="notbean" scope="session" class="com.htyz.notice.noticeBean" />

<%
//��ֹCache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%

String codeid = request.getParameter("codeid");
String codevalue = request.getParameter("codevalue");
String name = request.getParameter("name");
//name=new String(name.getBytes("ISO8859_1"));
String strSubstr = com.htyz.system.SystemProperties.getProperty("db.substr");
String queryid = request.getParameter("queryid");
if(queryid==null) queryid="";
String queryclumn = request.getParameter("querycoloum");

String s_sql="";
String tmp="" ,querid_tmp="",code_namec="";

%>

<HTML>
<HEAD>

<TITLE>�γ��б�</TITLE>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>

 
<%
int totalRowNum=0,pageRowNum=8,totalPageNum=1,curPageNum=1,nextPageNum=2;
int startRow=0,endRow=1,nextstaRow=1;

if(request.getParameter("page")!=null)
{
  System.out.println("the page====="+page);
  curPageNum=Integer.parseInt(request.getParameter("page"));
  nextPageNum=curPageNum+1 ;
}
if((queryid.trim()).length()==0)
{
	if (codeid.equals("0013"))
	{
		s_sql = "SELECT * FROM t_class  WHERE   " + strSubstr + "(class_id, 1, 4) = '" + codevalue + "' order by class_id";	
		
	}
	else
	{
		tmp=codeid+codevalue;
		s_sql = "SELECT * FROM t_class WHERE " + strSubstr + "(class_id, 1, 8) = '" + tmp + "' ORDER BY class_id";
	}
}
else
{
	//queryid = new String(queryid.getBytes("ISO8859_1"));
	querid_tmp="%"+queryid+"%";
	if (codeid.equals("0013"))
	{
		s_sql = "SELECT * FROM t_class WHERE  " + strSubstr + "(class_id, 1, 4) = '" + codevalue + "' and "+queryclumn+" like '"+querid_tmp+"'  order by class_id";	
		
	}
	else
	{
		tmp=codeid+codevalue;
		s_sql = "SELECT * FROM t_class WHERE " + strSubstr + "(class_id, 1, 8) = '" + tmp + "' and "+queryclumn+" like '"+querid_tmp+"' ORDER BY class_id";
	}

}
bgd.executeSelect(s_sql);
totalRowNum=bgd.getRowCount();
//totalRowNum=vect.size();
if(totalRowNum%pageRowNum==0)
{
 totalPageNum=totalRowNum/pageRowNum ;
}
else
{
  totalPageNum=(totalRowNum/pageRowNum) +1 ;
}
startRow=(curPageNum-1)*pageRowNum ;
endRow=curPageNum*pageRowNum ;
nextstaRow=(curPageNum)*pageRowNum ;
if(endRow>totalRowNum)
   endRow=totalRowNum;

if (totalRowNum>0)
{
%>
<%=name%>&nbsp;�γ��б�
<form method="post" name="form2" action="show.jsp">
<TABLE class=table003>
	<TR>
		<TD>������������ݣ�
			<INPUT type="text" name="queryid" id="queryid" value="<%=queryid%>" size="30">
			<INPUT type="hidden" name="action" id="action" value="query">
		</TD>
		<TD>
		  <SELECT name="querycoloum" id="querycoloum">
			<OPTION value="class_name">�γ�����</OPTION>
			<OPTION value="class_type">�γ�����</OPTION>
			<OPTION value="description">����</OPTION>			
		</SELECT>
		</TD>
		<TD>
		<INPUT type="submit" value="����">
		</TD>
	</TR>
</TABLE>
</P>
<TABLE  border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" class="xbz" width="100%">
	<TR bgcolor="#CCCCCC" align="center" Height="25">
		<TD>�γ�����</TD>
		<TD>�γ�<br>����</TD>
		<TD>�γ���</TD>
		<TD width="250">����</TD>
		<TD>��ʱ</TD>
		<TD>ͨ��<br>��׼</TD>
		<TD>���<br>��</TD>
	</TR>
	
	<%
	//for (int i=0;i<bgd.getRowCount(); i++)
	String class_type="",class_typename="",class_id="";
	Vector vec=new Vector();
	userDO urdo = null;
	vec=notbean.getcode();
	
	for(int i=startRow;i<endRow;i++)
	{
	  class_id=(bgd.getFieldValue("class_id",i)).substring(0,8);
	  //System.out.println("the class_id======="+class_id);
	  for(int j=0;j<vec.size();j++)
	  {
         urdo = new userDO();
         urdo = (userDO)vec.elementAt(j);
		 
		 if((urdo.getUser_id()).equals(class_id))
		 {
			 code_namec=urdo.getUser_name();
			
			 break;
		 }
	   
	   }
	  class_type=bgd.getFieldValue("class_type",i);
	  if(class_type.equals("0"))
		class_typename="����";
	  else if(class_type.equals("1"))
		class_typename="ע��";
	  else
		class_typename="�շ�";
	%>
		<TR>
			
		<TD> <%=bgd.getFieldValue("class_name", i)+"&nbsp;"%></TD>
		
		 <TD><%=class_typename+"&nbsp;"%> </TD>
			
		<TD><%=code_namec+"&nbsp;"%> </TD>
			
		<TD width="250"> <%=bgd.getFieldValue("description", i)+"&nbsp;"%> </TD>
			
		<TD> <%=bgd.getFieldValue("class_time", i)%> </TD>
			
		<TD> <%=bgd.getFieldValue("pass_stander", i)%> </TD>
		<TD> <%=bgd.getFieldValue("peoples", i)%> </TD>
		</TR>
<%
    }
	
}
else
{
	out.println("û�пγ̣�");
	return ;
}
%>
</TABLE>
<table width="90%" border="0">
    <tr>
    <%
    if(curPageNum>1)
    {
    %>
      <td align='center'><a href="show.jsp?page=<%=curPageNum-1%>&codeid=<%=codeid%>&codevalue=<%=codevalue%>&name=<%=name%>" >��ҳ</a></td>
    <%
    }
    else
    {
    %>
      <td align='center'><font color="a9a9a9">��ҳ</font></td>
    <%
    }
    %>

     <%
     if(nextstaRow<totalRowNum)
     {
     %>
      <td align='center'><a href="show.jsp?page=<%=curPageNum+1%>&codeid=<%=codeid%>&codevalue=<%=codevalue%>&name=<%=name%>" >��ҳ</a></td>
     <%
     }
     else
     {
     %>
      <td align='center'><font color="a9a9a9">��ҳ</font></td>
     <%
     }
     %>

      <td align='center'> ��ǰҳ:<%=curPageNum%>/<%=totalPageNum%></td>
      <td align='center'> ��<%=totalPageNum%>ҳ</td>
    </tr>
  </table>
  <input type="hidden" value="<%=codeid%>" name="codeid">
  <input type="hidden" value="<%=codevalue%>" name="codevalue">
  <input type="hidden" value="<%=name%>" name="name">
</form>
</BODY>
</HTML>
		
		