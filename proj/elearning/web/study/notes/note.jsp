<%@page contentType="text/html;charset=GB2312"%>
<jsp:useBean id="sqlbean" scope="request" class="com.htyz.beanGetdata"/>
<%
String class_id,lesson_id,userid;
String Sql_statment;
class_id=(String)session.getAttribute("classid");
lesson_id=(String)session.getAttribute("lessonid");
userid=(String)session.getAttribute("userid");
if(userid==null){
	out.print("����û�е�¼�����Ѿ���ʱ�������µ�¼��");
}else{
	if(lesson_id==null||class_id==null){
		out.print("��������");
	}else{
		sqlbean.executeSelect("select notes from t_lesson_log where class_id='"+class_id+"' and lesson_id='"+lesson_id+"' and user_id='"+userid+"'");
%>
<html>

<head>
<title></title>
<style type=text/css>
<!--
A:link		{color:#333333;text-decoration:none;}
A:visited	{color:#333333;text-decoration:none;}
A:hover		{color:#333333;text-decoration:underline overline;}
A:active	{color:#333333;text-decoration:none;}
input.radio	{background: #EFF3F9; color:#000000}
font		{ FONT-size: 9pt; line-height: 13pt; FONT-FAMILY:����, Arial, Helvetica}
font_size	{ FONT-size: 9pt; line-height: 13pt; FONT-FAMILY:����, Arial, Helvetica}
SELECT		{ FONT-size: 9pt; line-height: 13pt; FONT-FAMILY:����, Arial, Helvetica; BACKGROUND-COLOR: #efefef}
table		{ FONT-size: 9pt; line-height: 13pt; FONT-FAMILY:����, Arial, Helvetica}
td		{ FONT-size: 9pt; line-height: 13pt; FONT-FAMILY:����, Arial, Helvetica}
textarea	{ BACKGROUND-COLOR: #efefef; BORDER-BOTTOM: 1px double; BORDER-LEFT: 1px double; BORDER-RIGHT: 1px double; BORDER-TOP: 1px double; COLOR: #000000; font-size: 9pt ;FONT-FAMILY:����, Arial, Helvetica}
.Coolinput	{ BACKGROUND-COLOR: #EFF3F9; BORDER-BOTTOM: 1px double; BORDER-LEFT: 1px double; BORDER-RIGHT: 1px double; BORDER-TOP: 1px double; COLOR: #000000; font-size: 9pt; FONT-FAMILY:����, Arial, Helvetica}
INPUT		{ BACKGROUND-COLOR: #EFF3F9; CURSOR: HAND; BORDER-TOP-WIDTH: 1px; PADDING-RIGHT: 1px; PADDING-LEFT: 1px; BORDER-LEFT-WIDTH: 1px; FONT-SIZE: 9pt; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 1px; BORDER-BOTTOM-COLOR: #cccccc; PADDING-BOTTOM: 1px; BORDER-TOP-COLOR: #cccccc; PADDING-TOP: 1px; HEIGHT: 18px; BORDER-RIGHT-WIDTH: 1px; BORDER-RIGHT-COLOR: #cccccc}
input2		{ font-family: "Arial", "Helvetica", "sans-serif", "����"; font-size: 12px; border: 1px #000000 dashed}
.input3		{ width: 72px; height: 19px; font-size: 10.5pt; vertical-align: bottom}
BODY		{ CURSOR: default; FONT-FAMILY: Verdana,Arial,Helvetica,sans-serif,����; FONT-SIZE: 9pt;
		  SCROLLBAR-BASE-COLOR: #A9B6CD;
		/*SCROLLBAR-ARROW-COLOR: #f0F3Fa; SCROLLBAR-HIGHLIGHT-COLOR: buttonface;
		  SCROLLBAR-SHADOW-COLOR: buttonface; SCROLLBAR-3DLIGHT-COLOR: buttonhighlight;
		  SCROLLBAR-TRACK-COLOR: #A9B6CD; SCROLLBAR-DARKSHADOW-COLOR: #f0F3Fa
		*/ }
option#red{color:red}
option#blue{color:blue}
//-->
</style>
</HEAD>

<body bgColor="#FFFFFF" background="images/bg.gif" topmargin="0" leftmargin="0" text="#333333" link="#333333" aLink="#333333" vLink="#333333" >


</TABLE>
<font face="����, Arial, Helvetica">


<p align="center"><font face="����, Arial, Helvetica" size="2"></font></p>
<table border="0" cellspacing="0" cellpadding="0" align=center width="95%">
  <tr>
    <td bgcolor="#60718B">
    <table border="0" cellspacing="1" cellpadding="4" width="100%">

<form action="save.jsp" method="post" name="PostTopic">
	<input name="lesson_id" type="hidden" value="<%=lesson_id%>">
	<input name="class_id" type="hidden" value="<%=class_id%>">
	<input name="userid" type="hidden" value="<%=userid%>">


<script>
	function HighlightAll(theField) {
		var tempval=eval("document."+theField)
		tempval.focus()
		tempval.select()
		therange=tempval.createTextRange()
		therange.execCommand("Copy")}
	function checklength(theform){alert("��������Ŀǰ�� "+theform.Message.value.length+" �ֽڡ�");}
</script>

      <tr>
        <td bgColor="#f0F3Fa" noWrap vAlign="top" align="right"><font face="����, Arial, Helvetica" size="2"><B>���ݣ�</B><br>
        </FONT>
        </td>
        <td bgColor="#f0F3Fa">
	&nbsp;<textarea cols="80" rows="12" name="Message" wrap="VIRTUAL"" title=���¼����ѧϰ�ʼ�><%=sqlbean.getFieldValue("notes",0)%></textarea>

	<BR>&nbsp;��Ҫ���Ǳ���...&nbsp;&nbsp; &lt;&lt; <a href=javascript:HighlightAll('PostTopic.Message')>���Ƶ�������</a> | <a href=javascript:checklength(document.PostTopic);>�鿴���³���</a>&gt;&gt;
	</td>
      </tr>
      <tr>
        <td bgColor="#A9B6CD" colspan="2" align="center"><input name="Submit" type="submit" value="����" onClick="return clckcntr();">

        &nbsp;<input name="Reset" type="reset" value="ȫ�����"></td>
      </tr>

    </table>
    </td>
  </tr>
</table>
</form>
</TABLE>
</TD>
</TR>
</TABLE>
</TD>
</TR>
</TABLE>

</div>

<TABLE width="95%" border=0 cellpadding="0" cellspacing="1" align="center">

<TR>
<TD bgcolor="#f0F3Fa" nowrap align="right"><a href="#top"><img src="images/icon_go_up.gif" border="0" align="right" alt="����"></A></FONT></TD>
</TR>
</TABLE>

</BODY>
</HTML>


<%}
}%>