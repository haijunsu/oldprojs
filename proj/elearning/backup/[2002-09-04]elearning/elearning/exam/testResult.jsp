<%@page contentType="text/html;charset=GBK"%>
<%@page session="true"%>
<%
//禁止Cache.
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
<TITLE>测试结果</TITLE>
<link href="/elearning/style.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
<CENTER><H2><%=className%>课程考试成绩单</H2></CENTER>
<%
if (pass.equals("true"))
	out.println("<P>恭喜你！你已经成功地通过了本课程的考试，你的成绩是：" + grade + "分");
else
	out.println("<P>很遗憾！你未能通过本课程的考试，你的成绩是：" + grade + "分");
%>
<TABLE width="98%" bgcolor="#EEEEEE" border="1" cellspacing="0" cellpadding="0" class=table01>
  <TR>
    <TD width="11%" nowrap>及格分数线</TD>
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
    <TD>您的考试成绩</TD>
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
<P>考试结果分析：<BR>
<%=percent%>

</BODY>
</HTML>
		
		
