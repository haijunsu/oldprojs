<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<%@ page import="com.htyz.system.SystemProperties"%>
<%@ page import="com.htyz.notice.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.htyz.*"%>

<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="notbean" scope="session" class="com.htyz.notice.noticeBean" />
<jsp:useBean id="online" scope="request" class="com.htyz.beanGetdata"/>

<%
//��ֹCache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%

String codeid = request.getParameter("bigclass");
String codevalue = request.getParameter("smallclass");
String queryid = request.getParameter("content");
String queryclumn = request.getParameter("querycoloum");
String querytime = request.getParameter("time");

if(codeid==null) codeid="";
if(codevalue==null) codevalue="";
if(queryid==null) queryid="";
if(queryclumn==null) queryclumn="";
String name = request.getParameter("name");

String strSubstr = com.htyz.system.SystemProperties.getProperty("db.substr");

String s_sql="";
String tmp="" ,querid_tmp="",code_namec="",strdate="";

int lenSys=0,lendate=0;
long intsysdate=0,intsys=0,intdate=0,inttime=0;
tmp=(online.getDbDate()).trim();

inttime= Integer.parseInt(querytime);
intsysdate=Long.parseLong(tmp);
//intdate=intsysdate-259200000;//С�ڵ�ǰ��ǰ����3��
intdate=intsysdate-(86400000*inttime);//С�ڵ�ǰ��ǰ����inttime��(1��=86400000)

if(inttime==0)
   intdate=0;
strdate=(new Long(intdate)).toString();

lendate=strdate.length();

for(int j=0;j<15-lendate;j++)
{
strdate="0"+strdate;
}
%>

<HTML>
<HEAD>

<TITLE>�γ��б�</TITLE>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
 
<%
int totalRowNum=0,pageRowNum=10,totalPageNum=1,curPageNum=1,nextPageNum=2;
int startRow=0,endRow=1,nextstaRow=1;

if(request.getParameter("page")!=null)
{
  curPageNum=Integer.parseInt(request.getParameter("page"));
  nextPageNum=curPageNum+1 ;
}
if((queryid.trim()).length()==0)
{
	if (codeid.equals(""))
	{
				
		s_sql = "SELECT * FROM t_class  WHERE  set_time >= '" + strdate + "' and class_status='1' order by  class_id   ";
	}
	else
	{
		if(codevalue.equals(""))
		{			
			
			tmp=codeid;
			s_sql = "SELECT * FROM t_class WHERE " + strSubstr + "(class_id, 1, 4) = '" + tmp + "'  and set_time >= '" + strdate + "' and class_status='1' ORDER BY  class_id ";
			
		}
		else
		{
			tmp=codeid+codevalue;								
			s_sql = "SELECT * FROM t_class WHERE " + strSubstr + "(class_id, 1, 8) = '" + tmp + "'   and set_time >= '" + strdate + "'  and class_status='1' ORDER BY class_id";
			
		}
	}
}
else
{
	//queryid = new String(queryid.getBytes("ISO8859_1"));
	if(queryclumn.equals("class_type"))
	{
       if(queryid.equals("����")||queryid.equals("��")||queryid.equals("��"))
          queryid="0";
	   else if(queryid.equals("ע��")||queryid.equals("ע")||queryid.equals("��"))
		   queryid="1";
	}
	querid_tmp="%"+queryid+"%";
	if (codeid.equals(""))
	{
		s_sql = "SELECT * FROM t_class WHERE   set_time >= '" + strdate + "' and "+queryclumn+" like '"+querid_tmp+"'  and class_status='1' order by class_id";	
		
	}
	else
	{
		if(codevalue.equals(""))
		{
			tmp=codeid;
			s_sql = "SELECT * FROM t_class WHERE " + strSubstr + "(class_id, 1, 4) = '" + tmp + "' and "+queryclumn+" like '"+querid_tmp+"' and set_time >= '" + strdate + "' and class_status='1' ORDER BY class_id";
		}
		else
		{
			tmp=codeid+codevalue;
			s_sql = "SELECT * FROM t_class WHERE " + strSubstr + "(class_id, 1, 8) = '" + tmp + "' and "+queryclumn+" like '"+querid_tmp+"' and set_time >= '" + strdate + "' and class_status='1' ORDER BY class_id";

		}
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
&nbsp;�γ��б�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���<a href='<%=request.getContextPath()%>/search.jsp'><font color="red">����</font></a>����

<form method="post" name="form2" action="show.jsp">

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
	out.println("û�з��������Ŀγ̣�");
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
      <td align='center'><a href="searchdetail.jsp?page=<%=curPageNum-1%>&bigclass=<%=codeid%>&smallclass=<%=codevalue%>&content=<%=queryid%>&queryclumn=<%=queryclumn%>&time=<%=querytime%>" >��ҳ</a></td>
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
      <td align='center'><a href="searchdetail.jsp?page=<%=curPageNum+1%>&bigclass=<%=codeid%>&smallclass=<%=codevalue%>&content=<%=queryid%>&queryclumn=<%=queryclumn%>&time=<%=querytime%>" >��ҳ</a></td>
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
 
</form>
</BODY>
</HTML>
		
		