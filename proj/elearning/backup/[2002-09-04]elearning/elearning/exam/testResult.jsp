<%@page contentType="text/html;charset=GBK"%>
<%@page session="true"%>
<%
//��ֹCache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%
String className = (String)request.getAttribute("className");
String pass = (String)request.getAttribute("pass");
String grade = (String)request.getAttribute("grade");
String stander = (String)request.getAttribute("stander");
String passPercent = (String)request.getAttribute("passPercent");
String standerPercent = (String)request.getAttribute("standerPercent");
String percent = (String)request.getAttribute("percent");
%>
<HTML>
<HEAD>
<TITLE>���Խ��</TITLE>
<link href="/elearning/style.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
<CENTER><H2><%=className%>�γ̿��Գɼ���</H2></CENTER>
<%
if (pass.equals("true"))
	out.println("<P>��ϲ�㣡���Ѿ��ɹ���ͨ���˱��γ̵Ŀ��ԣ���ĳɼ��ǣ�" + grade + "��");
else
	out.println("<P>���ź�����δ��ͨ�����γ̵Ŀ��ԣ���ĳɼ��ǣ�" + grade + "��");
%>
<TABLE width="98%" bgcolor="#EEEEEE" border="1" cellspacing="0" cellpadding="0" class=table01>
  <TR>
    <TD width="11%" nowrap>���������</TD>
    <TD width="85%">
      <TABLE width="<%=standerPercent%>%" border="0" cellspacing="2" cellpadding="2">
        <TR>
          <TD bgcolor="green">&nbsp;</TD>
        </TR>
      </TABLE>
    </TD>
    <TD width="4%"><%=stander%>&nbsp;</TD>
  </TR>
  <TR>
    <TD>���Ŀ��Գɼ�</TD>
    <TD>
	  <TABLE width="<%=passPercent%>%" border="0" cellspacing="2" cellpadding="2">
        <TR>
          <TD bgcolor="green">&nbsp;</TD>
        </TR>
      </TABLE>
</TD>
    <TD><%=grade%>&nbsp;</TD>
  </TR>
</TABLE>
<P>���Խ��������<BR>
<%=percent%>

</BODY>
</HTML>
		
		
