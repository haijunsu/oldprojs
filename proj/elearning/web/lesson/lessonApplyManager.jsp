<%@page contentType="text/html;charset=GB2312"%>
<HTML>
<HEAD>
<TITLE>øŒ≥Ã…Í«Î</TITLE>
</HEAD>
<%
String applyType = (String) request.getAttribute("applyType");
%>
<FRAMESET cols="250,*">
	<FRAME name="left" src='lessonApplyList?applyType=<%=applyType%>&reload=true'>
	<FRAME name="right" src="about:blank">
</FRAMESET>
</HTML>
