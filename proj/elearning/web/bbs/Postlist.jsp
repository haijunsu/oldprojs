<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="ForumData" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="fds" scope="request" class="com.htyz.util.BeanDateFormat"/>
<html>
<body>
<%
int i=0;
int items=20;
//String tdStyle=" bgcolor='#F2F8FF' onmouseover=\"javascript:this.bgColor='#FFE0E0';this.style.cursor='default';\" onmouseout=\"javascript:this.bgColor='#F2F8FF';\" valign=\"center\" align=\"left\">";
String tdStyle="\"> ";
int Cur_page=1;
if(request.getParameter("pageno")!=null){
	Cur_page=Integer.parseInt(request.getParameter("pageno"));
}
%>
  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
	<form name=form1 action='<%=request.getContextPath()%>/servlet/bbs/Forum?action=search&catid=<%=request.getParameter("catid")%>'>
      <td><a href="<%=request.getContextPath()%>/servlet/bbs/Forum?action=newpost&catid=<%=request.getParameter("catid")%>"><img src="<%=request.getContextPath()%>/bbs/images/newthread.gif" border=0></a></td>
      <td><div align="right">搜 索： 
          <input type=text name=search size=10>
          <input name="image" type="image" src="<%=request.getContextPath()%>/bbs/images/go.gif">
        </div></td>
      </form>
      </tr>
  </table>

<link rel="stylesheet" href="<%=request.getContextPath()%>/3.css" type="text/css">
<form name=form2 action='<%=request.getContextPath()%>/servlet/bbs/Forum?action=show'>
		<%
		if(ForumData.getRowCount()<1){%>目前还没有任何发表！<%
		}else{ %>
			<table align="center" width="95%" cellspacing="0" cellpadding="3" border="1" id="AutoNumber1" style="border-left:1px solid #60718B; border-right:1px solid #60718B; border-top:1px solid #60718B; padding:0; border-collapse: collapse; border-bottom-width:1">
			<tr Height=25 bgcolor='#A9B6CD' align=center>
				<td>状态</td><td>主题</td><td>作者</td><td>回复</td><td>点击</td><td>最后更新 | 最后回复人</td>
			</tr>
			<%
			for(i=((Cur_page-1)*items);i<ForumData.getRowCount()&&i<((Cur_page-1)*items)+items;i++){%>
				 <tr  bgcolor="#D2FFD2" onMouseOver="this.style.backgroundColor='#EEEEEE';this.style.background='#a0b0dd';" onMouseOut="this.style.backgroundColor='D2FFD2';this.style.background='#D2FFD2';">				<%
				if(Integer.parseInt(ForumData.getFieldValue("Bi_hits",i))>9){
					if(Integer.parseInt(ForumData.getFieldValue("Bi_reply",i))>0){
						out.println("<TD align=center width='32'"+tdStyle+"<img src='"+request.getContextPath()+"/bbs/images/icon_folder_hot_new_1.gif' border=0 title='热贴/回复...'/></td>");
					}else {
						out.println("<TD align=center width='32'"+tdStyle+"<img src='"+request.getContextPath()+"/bbs/images/icon_folder_hot_1.gif' border=0 title='热贴...'/></td>");
					}
				}else {
					if(Integer.parseInt(ForumData.getFieldValue("Bi_reply",i))>0){
						out.println("<TD align=center width='32'"+tdStyle+"<img src='"+request.getContextPath()+"/bbs/images/icon_folder_new_1.gif' border=0 title='新贴/回复...'/></td>");
					}else {
						out.println("<TD align=center width='32'"+tdStyle+"<img src='"+request.getContextPath()+"/bbs/images/icon_folder_1.gif' border=0 title='新贴...'/></td>");
					}
				}
				out.println("<TD width=*"+tdStyle+"<a href='"+request.getContextPath()+"/servlet/bbs/Forum?action=show&catid="+ForumData.getFieldValue("Cat_id",i)+"&biid="+ForumData.getFieldValue("Bi_id",i)+"' title='"+ForumData.getFieldValue("Bi_content",i)+"'>"+ForumData.getFieldValue("Bi_title",i)+"</a></td>");
				out.println("<TD width='80'"+tdStyle+ForumData.getFieldValue("user_id",i)+"</td>");
				out.println("<TD width='30'"+tdStyle+ForumData.getFieldValue("Bi_reply",i)+"</td>");
				out.println("<TD width='30'"+tdStyle+ForumData.getFieldValue("Bi_hits",i)+"</td>");
				out.println("<TD width='220'"+tdStyle+fds.format(ForumData.getFieldValue("Bi_date",i),15)+" | "+ForumData.getFieldValue("Last_Poster",i)+"</td></tr>");
			}
			out.println("<tr Height=25 bgcolor='#A9B6CD' align=right>");
			out.println("<td colspan=5 align='left'>本论坛共有");
			Double aaa=new Double((ForumData.getRowCount()-1)/items+1);
			int bbb=aaa.intValue();
			out.println(bbb);
			out.print("页&nbsp;&nbsp;&nbsp;[&nbsp;");
			for(i=1;i<(bbb+1);i++){
				out.println("<a href=Forum?action=list&pageno="+Integer.toString(i)+"&catid="+request.getParameter("catid")+">"+Integer.toString(i)+"</a>");
			}
			out.println("]</td><td><font color='#FF3333'>");
			if(Cur_page>1){
				out.print("<a href='Forum?action=list&pageno="+Integer.toString(Cur_page-1)+"&catid="+request.getParameter("catid")+"'>上一页</font></a>&nbsp;&nbsp;&nbsp;");
			}else{
				out.print("上一页&nbsp;&nbsp;&nbsp;");
			}
			if(Cur_page*items<ForumData.getRowCount()){
				out.print("<a href='Forum?action=list&pageno="+Integer.toString(Cur_page+1)+"&catid="+request.getParameter("catid")+"'>下一页</a></td></tr></table>");
			}else{
				out.print("下一页</td></tr></font></table>");
			}
		}		
		%>
</form>

<BR>
<TABLE cellspacing=0 cellpadding=0 width="95%" bgcolor="#60718B" align=center border=0 nowrap valign="bottom">
<TR><TD>
<TABLE cellspacing=1 cellpadding=3 width=100% border=0>
<TR>
<TD width="77%" bgcolor="#A9B6CD"><font color="#333333"><b> -=> 论坛图例</b></font></TD>
<TD valign=right noWrap bgcolor="#A9B6CD" width="23%" align=center>所有时间均为 - 北京时间</TD>
</TR>
<TR><TD bgcolor="#f0F3Fa" colspan=3>
<TABLE cellspacing=0 cellpadding=0 width=98% border=0 align=center>
	<tr>
	 <td width="20%"><font color="#990000">打开的主题</font></td>
	 <td width="20%"><font color="#990000">回复超 10 次的热门贴</font></td>
	 <td width="20%"><font color="#990000">特殊贴子图标</font></td>
	 <TD width="20%"></TD>
        </tr>
       <TR>
	 <TD><img src="<%=request.getContextPath()%>/bbs/images/icon_folder_new_1.gif"> 有回复的新发表</TD>
	 <TD><img src="<%=request.getContextPath()%>/bbs/images/icon_folder_hot_new_1.gif"> 有回复的热门贴子</TD>
	 <TD><img src="<%=request.getContextPath()%>/bbs/images/icon_folder_locked_new_1.gif"> 关闭的热门主题</TD>
	 <TD></TD>
      </TR>
      <TR>
	 <TD><img src="<%=request.getContextPath()%>/bbs/images/icon_folder_1.gif"> 没有回复的新发表</TD>
	 <TD><img src="<%=request.getContextPath()%>/bbs/images/icon_folder_hot_1.gif"> 无回复的热门贴子</TD>
	 <TD><img src="<%=request.getContextPath()%>/bbs/images/icon_folder_locked_1.gif"> 关闭的主题，不接受回复</TD>
	 <TD><img src="<%=request.getContextPath()%>/bbs/images/icon_locktop.gif"> 被固定在顶端的主题</TD>
      </TR>
</TABLE>
</TD>
</TR>
</TABLE>
</TABLE>

<BR>
<TABLE width="95%" border=0 cellpadding="0" cellspacing="1" align="center">

<TR>
<TD bgcolor="#f0F3Fa" nowrap align="right"><a href="#top"><img src="<%=request.getContextPath()%>/bbs/images/icon_go_up.gif" border="0" align="right" alt="顶部"></A></FONT></TD>
</TR>
</TABLE>