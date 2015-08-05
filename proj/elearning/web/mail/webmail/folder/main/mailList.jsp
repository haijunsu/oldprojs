<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="eds" scope="request" class="com.htyz.util.BeanDateFormat"/>
<jsp:useBean id="beanGetdata" scope="request" class="com.htyz.beanGetdata"/>
<%
String isChecked = "";
String navigation = (String)request.getAttribute("navigation");
String s_msg = (String)request.getAttribute("message");
String folder = (String)request.getAttribute("folder");
String action = (String)request.getAttribute("action");
%>

<HTML>
<HEAD>
<TITLE></TITLE>
<SCRIPT language="JavaScript" type="text/JavaScript">
function selectAll(selectA)
{
	itemCount = document.mailListForm.countInPage.value;
	if(selectA.checked)
	{
			for (i=0; i<itemCount; i++)
			{
				document.mailListForm["select" + i].checked = true;
			}
	}
	else
	{
			for (i=0; i<itemCount; i++)
			{
				document.mailListForm["select" + i].checked = false;
			}
	}
}
</SCRIPT>

<STYLE type=text/css>A:link {
	COLOR: black; TEXT-DECORATION: none
}
A:visited {
	COLOR: black; TEXT-DECORATION: none
}
A:active {
	COLOR: black; TEXT-DECORATION: none
}
A:hover {
	COLOR: black; TEXT-DECORATION: none
}
TD {
	FONT-FAMILY: Verdana; FONT-SIZE: 9pt
}
TH {
	FONT-FAMILY: Verdana; FONT-SIZE: 9pt
}
INPUT {
	FONT-FAMILY: Verdana; FONT-SIZE: 9pt
}
SELECT {
	FONT-FAMILY: Verdana; FONT-SIZE: 9pt
}
.menuStyle {
	FONT-FAMILY: Arial,Geneva,Verdana,Helvetica; FONT-SIZE: 9pt; FONT-WEIGHT: bold; TEXT-DECORATION: none
}
.padd {  padding-left: 10px}
</STYLE>
</HEAD>
<BODY bgColor=#ffffff leftMargin=0 topmargin="0">
<DIV id=menuDiv > 
<form name="mailListForm" method="post" action="/elearning/mail/Mail" target="_parent"> 
    
  <TABLE bgColor=#f6f6f6 border=0 borderColorDark=#808080 borderColorLight=#ffffff 
cellPadding=0 cellSpacing=1 height=20 width="100%">
    <TBODY> 
    <TR bgcolor="#FFFFFF">
        <TD noWrap width="20"> 
          <table bgcolor=#ECEAE6 border=1 bordercolordark=#808080 bordercolorlight=#ffffff cellpadding=0 cellspacing=0 height=20 onMouseDown="javascript:this.borderColorDark='#ffffff';this.borderColorLight='#808080'"       onMouseOut="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';"       onMouseOver="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';"       onMouseUp="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff'" style="CURSOR: hand" width="100%">
            <tbody> 
            <tr> 
              <td bgcolor=#ECEAE6 bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6 
          noWrap align="center">新</td>
            </tr>
            </tbody> 
          </table>
        </TD>
        <TD noWrap width="40"> 
          <TABLE bgColor=#ECEAE6 border=1 borderColorDark=#808080 
      borderColorLight=#ffffff cellPadding=0 cellSpacing=0 height=20 
      onmousedown="javascript:this.borderColorDark='#ffffff';this.borderColorLight='#808080'" 
      onmouseout="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';" 
      onmouseover="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';" 
      onmouseup="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff'" 
      style="CURSOR: hand" width="100%">
            <TBODY> 
            <TR> 
              <TD bgColor=#ECEAE6 borderColorDark=#ECEAE6 borderColorLight=#ECEAE6 
          noWrap align="center">选项</TD>
            </TR>
            </TBODY> 
          </TABLE>
        </TD>
        <TD noWrap width="150"> 
          <TABLE bgColor=#ECEAE6 border=1 borderColorDark=#808080 
      borderColorLight=#ffffff cellPadding=0 cellSpacing=0 height=20 
      onmousedown="javascript:this.borderColorDark='#ffffff';this.borderColorLight='#808080'" 
      onmouseout="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';" 
      onmouseover="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';" 
      onmouseup="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff'" 
      style="CURSOR: hand" width="100%">
            <TBODY> 
            <TR> 
              <TD bgColor=#ECEAE6 borderColorDark=#ECEAE6 borderColorLight=#ECEAE6 
          noWrap align="center" class="padd">主　题</TD>
            </TR>
            </TBODY> 
          </TABLE>
        </TD>
        <TD noWrap> 
          <TABLE bgColor=#ECEAE6 border=1 borderColorDark=#808080 
      borderColorLight=#ffffff cellPadding=0 cellSpacing=0 height=20 
      onmousedown="javascript:this.borderColorDark='#ffffff';this.borderColorLight='#808080'" 
      onmouseout="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';" 
      onmouseover="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';" 
      onmouseup="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff'" 
      style="CURSOR: hand" width="100%">
            <TBODY> 
            <TR> 
              <TD bgColor=#ECEAE6 borderColorDark=#ECEAE6 borderColorLight=#ECEAE6 
          noWrap align="center" class="padd">发件人</TD>
            </TR>
            </TBODY> 
          </TABLE>
        </TD>
        <TD noWrap> 
          <TABLE bgColor=#ECEAE6 border=1 borderColorDark=#808080 
      borderColorLight=#ffffff cellPadding=0 cellSpacing=0 height=20 
      onmousedown="javascript:this.borderColorDark='#ffffff';this.borderColorLight='#808080'" 
      onmouseout="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';" 
      onmouseover="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';" 
      onmouseup="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff'" 
      style="CURSOR: hand" width="100%">
            <TBODY> 
            <TR> 
              <TD bgColor=#ECEAE6 borderColorDark=#ECEAE6 borderColorLight=#ECEAE6 
          noWrap align="center" class="padd">日期</TD>
            </TR>
            </TBODY> 
          </TABLE>
        </TD>
        <TD noWrap width="75"> 
          <TABLE bgColor=#ECEAE6 border=1 borderColorDark=#808080 
      borderColorLight=#ffffff cellPadding=0 cellSpacing=0 height=20 
      onmousedown="javascript:this.borderColorDark='#ffffff';this.borderColorLight='#808080'" 
      onmouseout="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';" 
      onmouseover="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';" 
      onmouseup="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff'" 
      style="CURSOR: hand" width="100%">
            <TBODY> 
            <TR> 
              <TD bgColor=#ECEAE6 borderColorDark=#ECEAE6 borderColorLight=#ECEAE6 
          noWrap align="center">大小</TD>
            </TR>
            </TBODY> 
          </TABLE>
        </TD>
        <TD noWrap width="40"> 
          <TABLE bgColor=#ECEAE6 border=1 borderColorDark=#808080 
      borderColorLight=#ffffff cellPadding=0 cellSpacing=0 height=20 
      onmousedown="javascript:this.borderColorDark='#ffffff';this.borderColorLight='#808080'" 
      onmouseout="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';" 
      onmouseover="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';" 
      onmouseup="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff'" 
      style="CURSOR: hand" width="100%">
            <TBODY> 
            <TR> 
              <TD bgColor=#ECEAE6 borderColorDark=#ECEAE6 borderColorLight=#ECEAE6 
          noWrap align="center">附件</TD>
            </TR>
            </TBODY> 
          </TABLE>
        </TD>
      </TR>
	<%
	for (int i=0; i<beanGetdata.getRowCount(); i++)
	{
	%>

      <TR onmouseout='this.style.backgroundColor="#ffffff"' 
  onmouseover='this.style.backgroundColor="#EFEFEF"' style="CURSOR: hand" bgcolor="#FFFFFF"> 
        <TD noWrap align="center"><%=(beanGetdata.getFieldValue("status",0)=="1")?"<img src=\"/elearning/mail/image/new.gif\"":"&nbsp;"%></TD>
        <TD noWrap width="30" align="center"> 
          <input type="checkbox" name="select<%=i%>" value="<%=beanGetdata.getFieldValue("mail_id",i)%>">
        </TD>
        <TD noWrap class="padd" bgcolor="#FFFFFF"><a href="/elearning/mail/Mail?action=read&mailid=<%=beanGetdata.getFieldValue("mail_id",i)%>" target="readwindow"><%=beanGetdata.getFieldValue("subject",i)%>&nbsp;</a></TD>
        <TD noWrap class="padd"><a href="/elearning/mail/Mail?action=read&mailid=<%=beanGetdata.getFieldValue("mail_id",i)%>" target="readwindow"><%=beanGetdata.getFieldValue("frommail",i)%>&nbsp;</a></TD>
        <TD noWrap class="padd"><%=eds.format(beanGetdata.getFieldValue("mailtime",i),8)%>&nbsp;</TD>
        <TD noWrap width="75" align="center">10kb</TD>
        <TD noWrap width="40" align="center">&nbsp;</TD>
	</TR>
<%}%>
</TABLE>
<INPUT name="action" type="hidden" id="action" value="<%= action%>">
<INPUT name="folder" type="hidden" id="folder" value="<%= folder%>">
<TABLE width="100%" class="table003">
<TR>
	<TD>
        <INPUT type="checkbox" name="check" value="" onClick="selectAll(this)">选择全部
      </TD>
<TD align="right"><%=s_msg%>中共有<%=beanGetdata.getRowCount()%>封邮件</TD>
</TR>
</TABLE>
<INPUT name="countInPage" type="hidden" id="countInPage" value="<%=	beanGetdata.getRowCount()%>">
<INPUT name="mailid" type="hidden" id="mailid" value="<%= beanGetdata.getFieldValue("mail_id",0)%>">
</FORM>

</DIV>
</BODY></HTML>
