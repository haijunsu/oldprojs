<%@page contentType="text/html;charset=GBK"%>
<%@page session="true"%>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%
java.util.Vector questions = (java.util.Vector) request.getAttribute("questions");
String className = (String)request.getAttribute("className");
String classId = (String)request.getAttribute("classId");
String startTime = (String)session.getAttribute(classId + ".startTime");
String passTime = (String)session.getAttribute(classId + ".passTime");
String totalTime = (String)session.getAttribute(classId + ".totalTime");
//System.out.println(startTime + "/" + passTime + "/" + totalTime);
%>
<HTML>
<HEAD>
<TITLE>课程测试</TITLE>
<link href="/elearning/style.css" rel="stylesheet" type="text/css">
<SCRIPT language="JavaScript" type="text/JavaScript">
	startTime=<%=startTime%>
	passTime=<%=passTime%> - 1000;
	totalTime = <%=totalTime%> * 60000;
	window.open("/elearning/exam/notice.htm","_blank","top=0,left=0,width=610,height=350,status=no");


function runclock(){
	passTime = passTime + 1000;
	if(passTime > totalTime)
	{
		document.detailForm.submit();
	}
	remainTime = totalTime - passTime;
	minutes=Math.floor(remainTime/60000);
	seconds = Math.floor((remainTime/60000 - minutes) * 60);
	document.timeForm.clock.value =  minutes + "分" + seconds + "秒";
	setTimeout('runclock()',1000);
}
</SCRIPT>
<NOSCRIPT>
你的浏览器不支持JAVASCRIPT，无法浏览本页动态效果！
</NOSCRIPT>
</HEAD>
<BODY onLoad="runclock()">
<CENTER><H2><%=className%>课程考试</H2></CENTER>
<FORM id="timeForm" name="timeForm" target="_parent">
<TABLE class="tableexam">
	<TR>

      <TD>剩余时间：
        <INPUT name="clock" type="text" id="clock" style="border:none;" size="10"></TD>
	</TR>

</TABLE>
</FORM>
<FORM id="detailForm" name="detailForm" method="post" target=_parent>
<TABLE border="0" class="tableexam" width="95%">
<%
if (questions.isEmpty())
{
	out.println("选择试题失败！");
	session.removeAttribute("startTime");
	session.removeAttribute("passTime");
	session.removeAttribute("totalTime");
}
else
{

	for (int i=0; i<questions.size(); i++)
	{
		exam.BeanTopicQuestion btq = (exam.BeanTopicQuestion) questions.elementAt(i);
%>
<TR height="20" bgcolor="#CCCCCC">
	<TD>
<%
		out.println(i + 1 + "、" + ets.stringToHtml(btq.getTopicDescription()) + "&nbsp;&nbsp;(" + btq.getTopicMark() + "分)<BR></td><tr><td>");
		String selectType = "checkbox";
		if (btq.getTopicType().equals("0"))
			selectType = "radio";
		for (int j=0; j<btq.getAnswerCount(); j++)
		{
			if (selectType.equals("checkbox"))
				out.println("<INPUT type=\"" + selectType + "\" name=\"select" + btq.getTopicId() + btq.getAnswer(j).getTopicAnswer() + "\" id=\"select" + btq.getTopicId() + btq.getAnswer(j).getTopicAnswer() + "\" value=\"1\">");
			else
				out.println("<INPUT type=\"" + selectType + "\" name=\"select" + btq.getTopicId() + "\" id=\"select" + btq.getTopicId() + btq.getAnswer(j).getTopicAnswer() + "\" value=\"" + btq.getAnswer(j).getIsAnswer() + "\">");
			out.println(btq.getAnswer(j).getTopicAnswer() + "、" + ets.stringToHtml(btq.getAnswer(j).getAnswerDescription()) + "<BR>");
		}
 %>
 	<br><br></TD>
 </TR>
 <%
 	}
%>
  <TR height="20">
  	<TD align="center">
		<INPUT name="classid" type="hidden" id="classid" value="<%=classId%>">
		<INPUT name="action" type="hidden" id="action" value="submit">
		<INPUT type="submit" value="交卷">
	</TD>
  </TR>
<%
}
%>
</TABLE>
</FORM>

</BODY>
</HTML>


