
<%@ page contentType="text/html; charset=GB2312" %>
<jsp:useBean id="notbean" scope="session" class="com.htyz.notice.noticeBean" />
<%@ page import="com.htyz.notice.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.htyz.*"%>

<html>
<head>
<title>�γ�����t</title>

<meta http-equiv="Content-Type" content="text/html; charset=gb2312">

</head>
<SCRIPT language=javascript>
function changeclass()
{
document.formsearch.action="search.jsp";
document.formsearch.submit();
}

function mycheck()
{
    c = document.formsearch.content.value;
	c1 = document.formsearch.querycoloum.value;
    if (c.length != 0&&c1.length == 0)
    {
      alert("ѡ���������")
      return false
    }     
}
</SCRIPT>

<%
String bigclass = request.getParameter("bigclass");
String smallclass = request.getParameter("smallclass");
String queryid = request.getParameter("content");
String queryclumn = request.getParameter("querycoloum");
String querytime = request.getParameter("time");

if(bigclass==null) bigclass="";
if(smallclass==null) smallclass="";
if(queryid==null) queryid="";
if(queryclumn==null) queryclumn="";
if(querytime==null) querytime="0";

%>
<body>
<br>
<DIV align=center>
 <TABLE cellSpacing=1 cellPadding=0 width=560 border=0  bgColor=#ff9966>
        
      <TR bgColor=#ff8d62>                         
         <TD colSpan=3 height=20>&nbsp;&nbsp;<B><FONT 
                   color=#ffffff>����</FONT></B>
		 </TD>
	  </TR>
      <TR>
         <TD bgColor=#ffffff colSpan=3 height=17>
		    <FORM name ="formsearch" action="class/searchdetail.jsp" method=post>
			    <TABLE cellSpacing=0 cellPadding=0 width=530 border=0>
				  <TR>
                      <TD bgColor=#ffffff colSpan=3 height=17></TD>
				  </TR>
				  <TR>
                      <TD width=20 bgColor=#ffffff>&nbsp;</TD>
                      <TD align=middle width=482 bgColor=#ffffff><FONT 
                        size='2' color=#ff6600>����γ����ݣ�</FONT> <INPUT name="content" value="<%=queryid%>"> 
                        
					  </TD>
                      <TD width=28 bgColor=#ffffff>&nbsp;</TD>
				  </TR>
				  <TR>
                      <TD width=20 bgColor=#ffffff></TD>
                      <TD width=482 bgColor=#ffffff>
                        <DIV align=center></DIV>
					  </TD>
                      <TD width=28 bgColor=#ffffff>&nbsp;</TD>
				  </TR>
				  <TR>
                    <TD width=20 bgColor=#ffffff>&nbsp;</TD>
					<TD align=middle >
					<FONT size='2' color=#ff6600>ѡ���������</FONT>
					 <SELECT name="querycoloum" id="querycoloum" style="WIDTH: 150px" >
					    <OPTION value="" >------��ѡ��------</OPTION>
					    <OPTION value="class_name" <%=queryclumn.equals("class_name")?"selected":" "%> >�γ����� </OPTION>
						
						 <OPTION value="class_type" <%=queryclumn.equals("class_type")?"selected":" "%> >�γ����� </OPTION>
						 <OPTION value="description" <%=queryclumn.equals("description")?"selected":" "%> >���� </OPTION>
								
					 </SELECT>
					</TD> 				
				  </TR>
				  <TR>
                      <TD width=20 bgColor=#ffffff></TD>
                      <TD width=482 bgColor=#ffffff>
                        <DIV align=center></DIV>
					  </TD>
                      <TD width=28 bgColor=#ffffff>&nbsp;</TD>
				  </TR>
				  <TR>
                    <TD width=20 bgColor=#ffffff>&nbsp;</TD>
					<TD align=middle >
					<FONT size='2' color=#ff6600>�γ�����ʱ�䣺</FONT>
					  <SELECT style="WIDTH: 150px"  size=1 name="time"> 
					    <OPTION value=0 >------��ѡ��------</OPTION>
						<OPTION value="7" <%=querytime.equals("7")?"selected":" "%> >��һ�� </OPTION>
					    <OPTION value="30" <%=querytime.equals("30")?"selected":" "%> >��һ�� </OPTION>	<OPTION value="90" <%=querytime.equals("90")?"selected":" "%> >������ </OPTION>	<OPTION value="180" <%=querytime.equals("180")?"selected":" "%> >������ </OPTION>
						<OPTION value="365" <%=querytime.equals("365")?"selected":" "%> >��һ�� </OPTION>
						<OPTION value="730" <%=querytime.equals("730")?"selected":" "%> >������ </OPTION>
						<OPTION value="1095" <%=querytime.equals("1095")?"selected":" "%> >������ </OPTION>
																
					 </SELECT>
					</TD> 				
				  </TR>
				   <TR>
                      <TD width=20 bgColor=#ffffff></TD>
                      <TD width=482 bgColor=#ffffff>
                        <DIV align=center></DIV>
					  </TD>
                      <TD width=28 bgColor=#ffffff>&nbsp;</TD>
				  </TR>
				  <TR>
                    <TD width=20 bgColor=#ffffff>&nbsp;</TD>
					<TD align=middle >
					<FONT size='2' color=#ff6600>ѡ��γ̴��ࣺ</FONT>
					  <SELECT style="WIDTH: 150px"  size=1 name="bigclass" onchange="changeclass()"> 
					    <OPTION value="" >------��ѡ��------</OPTION>
					    <%
						  Vector vec=new Vector();
						  userDO urdo = null;
						  vec = notbean.getclasscode("0013");
						  for(int i=0;i<vec.size();i++)
						  {
							urdo = new userDO();
							urdo = (userDO)vec.elementAt(i);
							if(bigclass.length()>=1)
							{
							  if(bigclass.equals(urdo.getUser_id()))
							  {
								out.println("<option selected value="+urdo.getUser_id()+">"+urdo.getUser_name()+"</option>");
							  }
							  else
							  {
								out.println("<option  value="+urdo.getUser_id()+">"+urdo.getUser_name()+"</option>");
							  }
							}
							else
							{
							  out.println("<option  value="+urdo.getUser_id()+">"+urdo.getUser_name()+"</option>");
							}
						  }
						  %> 										
					 </SELECT>
					</TD> 				
				  </TR>
				  <TR>
                      <TD width=20 bgColor=#ffffff></TD>
                      <TD width=482 bgColor=#ffffff>
                        <DIV align=center></DIV>
					  </TD>
                      <TD width=28 bgColor=#ffffff>&nbsp;</TD>
				  </TR>
				  <TR>
                    <TD width=20 bgColor=#ffffff>&nbsp;</TD>
					<TD align=middle >
					<FONT size='2' color=#ff6600>ѡ��γ�С�ࣺ</FONT>
					  <SELECT style="WIDTH: 150px"  size=1 name="smallclass"> 
					    <OPTION value="" >------��ѡ��------</OPTION>
					     <%
						  Vector ve=new Vector();
						  userDO userdo = null;
						  ve = notbean.getclasscode(bigclass);
						  for(int i=0;i<ve.size();i++)
						  {
							userdo = new userDO();
							userdo = (userDO)ve.elementAt(i);							
							out.println("<option  value="+userdo.getUser_id()+">"+userdo.getUser_name()+"</option>");
						  }
						  
						 %> 										
					 </SELECT>
					</TD> 				
				  </TR>
				  <TR>
                      <TD width=20 bgColor=#ffffff height=10></TD>
                      <TD width=482 bgColor=#ffffff height=10>
                        <DIV align=right><INPUT type=image height=20 width=60 
                        src="images/serach.gif" onclick="return mycheck()"> </DIV>
					  </TD>
                      <TD width=28 bgColor=#ffffff height=10></TD>
				  </TR>
				</TABLE>
			</FORM>
		 </TD>
	  </TR>
 </TABLE>                   
 </DIV >                  
</body>
</html>

 
