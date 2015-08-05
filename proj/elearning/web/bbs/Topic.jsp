<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="Sql_topic" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="ForumData" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="fds" scope="request" class="com.htyz.util.BeanDateFormat"/>
<jsp:useBean id="userinfo" scope="request" class="system.beanUserInfo"/>
<html>

<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">

<style type=text/css>
<!--
A:link		{color:#333333;text-decoration:none;}
A:visited	{color:#333333;text-decoration:none;}
A:hover		{color:#333333;text-decoration:underline overline;}
A:active	{color:#333333;text-decoration:none;}
input.radio	{background: #EFF3F9; color:#000000}
font		{ FONT-size: 9pt; line-height: 13pt; FONT-FAMILY:宋体, Arial, Helvetica}
font_size	{ FONT-size: 9pt; line-height: 13pt; FONT-FAMILY:宋体, Arial, Helvetica}
SELECT		{ FONT-size: 9pt; line-height: 13pt; FONT-FAMILY:宋体, Arial, Helvetica; BACKGROUND-COLOR: #efefef}
table		{ FONT-size: 9pt; line-height: 13pt; FONT-FAMILY:宋体, Arial, Helvetica}
td		{ FONT-size: 9pt; line-height: 13pt; FONT-FAMILY:宋体, Arial, Helvetica}
textarea	{ BACKGROUND-COLOR: #efefef; BORDER-BOTTOM: 1px double; BORDER-LEFT: 1px double; BORDER-RIGHT: 1px double; BORDER-TOP: 1px double; COLOR: #000000; font-size: 9pt ;FONT-FAMILY:宋体, Arial, Helvetica}
.Coolinput	{ BACKGROUND-COLOR: #EFF3F9; BORDER-BOTTOM: 1px double; BORDER-LEFT: 1px double; BORDER-RIGHT: 1px double; BORDER-TOP: 1px double; COLOR: #000000; font-size: 9pt; FONT-FAMILY:宋体, Arial, Helvetica}
INPUT		{ BACKGROUND-COLOR: #EFF3F9; CURSOR: HAND; BORDER-TOP-WIDTH: 1px; PADDING-RIGHT: 1px; PADDING-LEFT: 1px; BORDER-LEFT-WIDTH: 1px; FONT-SIZE: 9pt; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 1px; BORDER-BOTTOM-COLOR: #cccccc; PADDING-BOTTOM: 1px; BORDER-TOP-COLOR: #cccccc; PADDING-TOP: 1px; HEIGHT: 18px; BORDER-RIGHT-WIDTH: 1px; BORDER-RIGHT-COLOR: #cccccc}
input2		{ font-family: "Arial", "Helvetica", "sans-serif", "宋体"; font-size: 12px; border: 1px #000000 dashed}
.input3		{ width: 72px; height: 19px; font-size: 10.5pt; vertical-align: bottom}
BODY		{ CURSOR: default; FONT-FAMILY: Verdana,Arial,Helvetica,sans-serif,宋体; FONT-SIZE: 9pt;
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
<%
String catid,biid,Bi_title;
catid=request.getParameter("catid");
biid=request.getParameter("biid");

Bi_title=ForumData.getFieldValue("Bi_title",0);

%>
<body bgColor="#FFFFFF" topmargin="0" leftmargin="0" text="#333333" link="#333333" aLink="#333333" vLink="#333333" >

<font face="宋体, Arial, Helvetica">
<TABLE border="0" width="95%" cellspacing="0" cellpadding="3" align="center">
	<TR><TD align="left" height="26"><a href="<%=request.getContextPath()%>/servlet/bbs/Forum?action=newpost&catid=<%=catid%>"><img src="<%=request.getContextPath()%>/bbs/images/newthread.gif" alt="发表新主题" border=0></a><a href="<%=request.getContextPath()%>/servlet/bbs/Forum?action=Reply&catid=<%=catid%>&biid=<%=biid%>"><IMG src="<%=request.getContextPath()%>/bbs/images/newreply.gif" border=0 alt="回复主题"></a></TD><TD align="right">您是本帖第 <B><%=ForumData.getFieldValue("Bi_hits",0)%></B> 个阅读者</TD></TR>
</TABLE>

<TABLE width="95%" bgcolor="#60718B" cellpadding=0 cellspacing=0 border=0 align=center>
	<TR>
		<TD height=1><img src="" height=1></TD>
	</TR>
</TABLE>

<TABLE cellpadding=0 cellspacing=0 border=0 width="95%" align=center>
	<TR><TD bgcolor="#60718B" valign=middle width=1 height=24><img src="" width=1></TD><TD bgcolor="#A9B6CD" colspan=2 align="left" valign="middle" width=*>
		<TABLE cellpadding=0 cellspacing=1 width="95%" border=0 Align="center">
			<TR><TD bgcolor="#A9B6CD" align="left" valign="middle"><font face="宋体, Arial, Helvetica" color="#333333"><b>贴子主题：<font face="宋体, Arial, Helvetica" size="2" color="#333333"><%=Bi_title%></font></B></font>	</TD></TR>
		</TABLE>
		</TD>
	</TR>
</TABLE>

<TABLE cellpadding=0 cellspacing=0 border=0 width="95%" bgcolor="#60718B" align=center>
  <TR>
    <TD height=1></TD>
  </TR>
</TABLE>

<TABLE cellpadding=0 cellspacing=0 border=0 width="95%" align="center">
	<TR><TD bgcolor="#60718B" valign=middle width=1 height=24></TD>
	<TD bgcolor="#f0F3Fa">
		<TABLE width=100% cellpadding=4 cellspacing=0 bgcolor="#f0F3Fa">
			<TR>
			    <TD bgcolor="#f0F3Fa" valign="top" width="175"  rowspan=2>
<%userinfo.setUserInfo(ForumData.getFieldValue("user_id",0));%>
&nbsp;&nbsp;&nbsp;
<%if(userinfo.getSelfimg().equals("")) {
	if(userinfo.getUserimg().equals("")) {
		out.println("<IMG src='"+request.getContextPath()+"/images/face/nophoto.gif' border=0>");
	}else {
		out.println("<IMG src='"+request.getContextPath()+"/images/face/"+userinfo.getUserimg()+".gif' border=0>");
	}
}else {
	out.println("<IMG src='"+userinfo.getSelfimg()+"' border=0>");
}
%>
<br><BR>
<TABLE style="filter:glow(color=#cccccc,direction=135)"><font color="#0000ff" face="宋体, Arial, Helvetica"><B><%=ForumData.getFieldValue("user_id",0)%></B></FONT></TABLE>
<%
if(userinfo.getSex().equals("m")) {
	out.println("<img src='"+request.getContextPath()+"/images/male.gif' title='帅哥'/>");
}else if(userinfo.getSex().equals("f")) {
	out.println("<img src='"+request.getContextPath()+"/images/female.gif' title='美女耶...'/>");
}else {
	out.println("<img src='"+request.getContextPath()+"/images/maleorfemale.gif' title='性别保密....'/>");
}%>
<%=userinfo.getShengxiao().equals("")?"":"<IMG src='"+request.getContextPath()+"/images/sx/"+userinfo.getShengxiao()+"s.gif' border=0>"%>
<%=userinfo.getStars().equals("")?"":"<IMG src='"+request.getContextPath()+"/images/star/"+userinfo.getStars()+".gif' border=0>"%>
<br>				
<br>&nbsp;公司：<%=userinfo.getCompany()%>
<br>&nbsp;部门：<%=userinfo.getDepartment()%>
<br>&nbsp;来自：<%=userinfo.getPravency()%>&nbsp;&nbsp;
<%=userinfo.getCountry().equals("")?"":"<img src="+request.getContextPath()+"/images/flags/"+userinfo.getCountry()+".gif align=middle />"%>
<br>&nbsp;注册日期：<%=userinfo.getRegisterTime()%>
<br>
				</TD>	
			<TD bgcolor="#f0F3Fa" width=1 height=100% rowspan=2>
					<TABLE width=1 height=100% cellpadding=0 cellspacing=0 bgcolor="#60718B">
						<TR><TD width=1></TD></TR>
					</TABLE>
			</TD>
				
          <TD bgcolor="#f0F3Fa" width=* height="100%" valign=top> 
            <HR noshade size="1" color="#60718B">
					<TABLE cellpadding=0 cellspacing=0 width="100%" height="50" style="TABLE-LAYOUT: fixed; word-break:break-all">
						<TR><TD width=24 align=left valign=top><IMG src="<%=request.getContextPath()%>/bbs/images/Face/<%=ForumData.getFieldValue("Bi_emotion",0)%>.gif" width="13" height="13"></TD><TD style="LEFT: 0px; WIDTH: 100%; WORD-WRAP: break-word" valign=top><font color="#333333" face="宋体, Arial, Helvetica"><%=ets.strFormat(ForumData.getFieldValue("Bi_content",0))%><%=ets.getSignature(ForumData.getFieldValue("user_id",0))%></font></TD><TD width=16></TD>
						</TR>
					</TABLE>
				</td>
			</TR>
			<TR><TD class="bottomline" bgcolor="#f0F3Fa" valign="bottom"><HR noshade size="1" color="#60718B">
				<table width=100% cellpadding=0 cellspacing=0>
					<tr>
                <td valign=bottom><font color="#333333" face="宋体, Arial, Helvetica"><IMG src="<%=request.getContextPath()%>/bbs/images/posttime.gif" alt="发贴时间" border="0" align="absmiddle">&nbsp;<%=fds.format(ForumData.getFieldValue("Bi_date",0))%></font></td>
					</tr>
				</table>
				<IMG src="" width=0 height=4>
				</TD>
			</TR>
		</TABLE>
	</TD>
	<TD bgcolor="#60718B" valign=middle width=1 height=24></TD>
    </TR>
</TABLE>
<TABLE cellpadding=0 cellspacing=0 border=0 width="95%" bgcolor="#60718B" align=center>
	<TR>
		<TD height=1></TD>
	</TR>
</TABLE>

<%
for(int i=0;i<Sql_topic.getRowCount();i++){%>
<TABLE cellpadding=0 cellspacing=0 border=0 width="95%" align="center">
	<TR><TD bgcolor="#60718B" valign=middle width=1 height=24></TD>
	<TD bgcolor="#f0F3Fa">
		<TABLE width=100% cellpadding=4 cellspacing=0 bgcolor="#f0F3Fa">
			<TR>
			<TD bgcolor="#f0F3Fa" valign="top" width="175"  rowspan=2 align="left">
<%userinfo.setUserInfo(Sql_topic.getFieldValue("user_id",i));%>
&nbsp;&nbsp;&nbsp;
<%if(userinfo.getSelfimg().equals("")) {
	if(userinfo.getUserimg().equals("")) {
		out.println("<IMG src='"+request.getContextPath()+"/images/face/nophoto.gif' border=0>");
	}else {
		out.println("<IMG src='"+request.getContextPath()+"/images/face/"+userinfo.getUserimg()+".gif' border=0>");
	}
}else {
	out.println("<IMG src='"+userinfo.getSelfimg()+"' border=0>");
}
%>
<br><BR>
<TABLE style="filter:glow(color=#cccccc,direction=135)"><font color="#0000ff" face="宋体, Arial, Helvetica"><B><%=Sql_topic.getFieldValue("user_id",i)%></B></FONT></TABLE>
<%
if(userinfo.getSex().equals("m")) {
	out.println("<img src='"+request.getContextPath()+"/images/male.gif' title='帅哥'/>");
}else if(userinfo.getSex().equals("f")) {
	out.println("<img src='"+request.getContextPath()+"/images/female.gif' title='美女耶...'/>");
}else {
	out.println("<img src='/"+request.getContextPath()+"/images/maleorfemale.gif' title='性别保密....'/>");
}%>
<%=userinfo.getShengxiao().equals("")?"":"<IMG src='"+request.getContextPath()+"/images/sx/"+userinfo.getShengxiao()+"s.gif' border=0>"%>
<%=userinfo.getStars().equals("")?"":"<IMG src='"+request.getContextPath()+"/images/star/"+userinfo.getStars()+".gif' border=0>"%>
<br>				
<br>&nbsp;公司：<%=userinfo.getCompany()%>
<br>&nbsp;部门：<%=userinfo.getDepartment()%>
<br>&nbsp;来自：<%=userinfo.getPravency()%>&nbsp;&nbsp;
<%=userinfo.getCountry().equals("")?"":"<img src="+request.getContextPath()+"/images/flags/"+userinfo.getCountry()+".gif  align=middle />"%>
<br>&nbsp;注册日期：<%=userinfo.getRegisterTime()%>
<br>


</TD><TD bgcolor="#f0F3Fa" width=1 height=100% rowspan=2>


					<TABLE width=1 height=100% cellpadding=0 cellspacing=0 bgcolor="#60718B">
						<TR><TD width=1></TD></TR>
					</TABLE>
				</TD>
				
          <TD bgcolor="#f0F3Fa" width=* height="100%" valign=top> &nbsp;
<HR noshade size="1" color="#60718B">
					<TABLE cellpadding=0 cellspacing=0 width="100%" height="50" style="TABLE-LAYOUT: fixed; word-break:break-all">
						<TR><TD width=24 align=left valign=top><%if(Sql_topic.getFieldValue("Br_emotion",i).trim().length()>0){%><IMG src="<%=request.getContextPath()%>/bbs/images/Face/<%=Sql_topic.getFieldValue("Br_emotion",i)%>.gif" width="13" height="13"><%}%></TD><TD style="LEFT: 0px; WIDTH: 100%; WORD-WRAP: break-word" valign=top><font color="#333333" face="宋体, Arial, Helvetica"><%=ets.strFormat(Sql_topic.getFieldValue("Br_content",i))%><%=ets.getSignature(Sql_topic.getFieldValue("user_id",i))%></font></TD><TD width=16></TD>
						</TR>
					</TABLE>
				</td>
			</TR>
			<TR><TD class="bottomline" bgcolor="#f0F3Fa" valign="bottom"><HR noshade size="1" color="#60718B">
				<table width=100% cellpadding=0 cellspacing=0>
					<tr>
                <td valign=bottom><font color="#333333" face="宋体, Arial, Helvetica"><IMG src="<%=request.getContextPath()%>/bbs/images/posttime.gif" alt="发贴时间" border="0" align="absmiddle">&nbsp;<%=fds.format(Sql_topic.getFieldValue("Br_date",i))%></font></td>
					</tr>
				</table>
				<IMG src="" width=0 height=4>
				</TD>
			</TR>
		</TABLE>
	</TD>
	<TD bgcolor="#60718B" valign=middle width=1 height=24></TD>
    </TR>
</TABLE>


<TABLE cellpadding=0 cellspacing=0 border=0 width="95%" bgcolor="#60718B" align=center>
	<TR>
		<TD height=1></TD>
	</TR>
</TABLE>
<%}%>
<tr>
<td align="right" nowrap colspan="2">
	<form action="<%=request.getContextPath()%>/servlet/bbs/Forum?action=quickreply" method=post name="PostForm">
		<input name="method" type="hidden" value="Reply">
		<input name="biid" type="hidden" value="<%=request.getParameter("biid")%>">
		<input name="catid" type="hidden" value="<%=request.getParameter("catid")%>">
		<input name="userid" type="hidden" value="<%=session.getAttribute("useid")%>">
		<table border=0 cellPadding=5 cellSpacing=1 width="95%" bgcolor="#60718B" align=center>
			<tr>
				<td bgcolor="#A9B6CD" width=178><font color="#333333"><b>快速回复主题:</b></font></td>
				<td bgcolor="#A9B6CD" colspan="2" width=*> <font color="#333333"><%=Bi_title%></font></td>
			</tr>
			<tr>
				<td bgcolor="#f0F3Fa"><font color="#333333"><b>输入用户名和密码:</b></font></td>
				<td bgcolor="#f0F3Fa">
					<font color="#333333"><b>用户名</b>: <input type=text name="user_id" value=""> <a href="policy.asp">您没有注册？</a>　 <b>密码:</b> <input type=password name="Password" value=""> <a href="javascript:openWindow('pop_pword.asp')">忘记密码？</a></font>
				</td>
			</tr>
			<tr>
				<td bgcolor="#F2F8FF" valign=top width=178><font color="#333333"><b>回复内容:</b></font></td>
				<td bgcolor="#F2F8FF" colspan="2" width=*>
					<textarea cols=80 name=Message rows=8 style="WIDTH: 97%"></textarea><br><input name=Submit  type=submit value="发 表">　<input name=Clear type=reset value="清 除">　
				</td>
			</tr>
		</table>
	</form>
</td>
</tr>
</table>
</div>
</BODY>
</HTML>
