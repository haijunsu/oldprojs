<%@page contentType="text/html;charset=GB2312"%>
<HTML>
<HEAD>
<TITLE>短信页面</TITLE>
<LINK rel="stylesheet" href="<%=request.getContextPath()%>/3.css" type="text/css">
</HEAD>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<SCRIPT language="JavaScript" type="text/JavaScript">
function selectAll(selectA)
{
	itemCount = document.detailForm.countInPage.value;
	if(selectA.checked)
	{
			for (i=0; i<itemCount; i++)
			{
				document.detailForm["select" + i].checked = true;
			}
	}
	else
	{
			for (i=0; i<itemCount; i++)
			{
				document.detailForm["select" + i].checked = false;
			}
	}
}
</SCRIPT>
<body bgcolor="#FFFFFF">
<NOSCRIPT> 您的浏览器不支持JavaScript，网页不能正常显示！ </NOSCRIPT> <BODY> 
<jsp:useBean id="beanGetdata" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="beanElearnTools" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="ust" scope="session" class="system.userStyle"/>
<jsp:useBean id="eds" scope="request" class="com.htyz.util.BeanDateFormat"/>
<%
String s_action, s_msg, s_Gnum, s_Gright, s_Mright, s_Checked, s_Group;
String s_senderid;
String s_senderTitle = "收件人";
String s_Msgaction = "read";
s_action = (String)request.getAttribute("action");
s_msg = (String)request.getAttribute("message");
s_Group =  (String)request.getAttribute("group");
s_msg = s_msg.trim();
if(s_Group.equals("inbox"))
	s_senderTitle = "发件人";
if(s_Group.equals("draftbox"))
	s_Msgaction = "modify";

%>
<FORM id="detailForm" name="detailForm">
<TABLE border="0" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="100%">
  <TR align="center"> 
    <TD height="20"> 
      <H2 align="left"><%=s_msg%></H2>
    </TD>
    <TD height="20"> 
        <DIV align="right">
		<A href='Messager?action=new<% %>'>写短信</A> 
          <% if(!s_Group.equals("inbox"))
		{
		 %>
          | <A href='Messager?action=list&group=inbox<% %>'>收件箱</A> 
          <% 
		}
		if(!s_Group.equals("outbox"))
		{
		 %>
		 | <A href='Messager?action=list&group=outbox<% %>'>发件箱</A>
          <% 
		}
		if(!s_Group.equals("draftbox"))
		{
		 %>
		 | <A href='Messager?action=list&group=draftbox<% %>'>草稿箱</A> 
          <% 
		}
		%>
        </DIV>
    </TD>
  </TR>
  <% 
  if (beanGetdata.getRowCount() > 0)
  {
   %>
  <TR align="center"> 
    <TD height="20">
      <DIV align="left">共有 <%=beanGetdata.getRowCount()%> 条短信</DIV>
    </TD>
    <TD height="20"> 
      <DIV align="right">
	<%
	String pageno = (String)request.getAttribute("pageno");
	if (pageno == null) pageno = "1";
	int listCount = Integer.parseInt(ust.getLines_in_page());
	int pageNum = Integer.parseInt(pageno);
	int maxCount = java.lang.Math.min(beanGetdata.getRowCount(), (pageNum-1)*listCount+listCount);
	int i_count = (int)java.lang.Math.ceil((double)beanGetdata.getRowCount()/listCount);
	out.print("共" + i_count + "页&nbsp;");
	for (int i=1; i<=i_count; i++)
	if (i == pageNum)
	{
		out.print(pageno);
	}
	else
	{
	%>
	<A href="Messager?group=<%=s_Group%>&pageno=<%=i%>"><%=i%></A>
	<%
	}
	%>
	</DIV>
    </TD>
  </TR>
</TABLE>
  <TABLE border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="100%">
    <TR align="center" bgcolor="#cccccc"> 
      <TD height="20"> 选择 </TD>
	<% if(s_Group.equals("inbox"))
	{
	 %>
      <TD height="20"> 状态 </TD>
	<% } %>
      <TD height="20"> <%=s_senderTitle%> </TD>
      <TD height="20"> 短信标题 </TD>
      <TD height="20"> 日期 </TD>
  </TR>
  <%
	if(beanGetdata.getRowCount()>0)
	{
		for (int i=(pageNum-1)*listCount; i<maxCount; i++)
		{
		%>
  <TR> 
    <TD height="20" align="center"> <INPUT name="select<%=i-(pageNum-1)*listCount%>" type="checkbox" id="select<%=i%>" value="<%=beanGetdata.getFieldValue("oc_id", i)%>"> </TD>
	<% if(s_Group.equals("inbox"))
	{
	 %>
    <TD height="20"> 
      <%
			if(beanGetdata.getFieldValue("oc_status", i).equals("1"))
			{
			%>
      <FONT color=red>新</FONT> 
      <%
			}
			else
			{
			%>
      &nbsp; 
      <%
			}
			%>
    </TD>
    <% } %>
    <TD height="20"> 
      <%
			s_senderid = beanGetdata.getFieldValue("oc_obj", i);
			if(s_Group.equals("inbox"))
				s_senderid = beanGetdata.getFieldValue("oc_name", i);
			if(s_senderid.equals(""))
				s_senderid = "无";
			out.print(s_senderid);
	  %>
    </TD>
    <TD height="20"> <A href='Messager?action=<%=s_Msgaction%>&group=<%=s_Group%>&msgid=<%=beanGetdata.getFieldValue("oc_id", i)%>'> 
      <%
		if(beanGetdata.getFieldValue("oc_title", i).equals(""))
			out.print("无");
		else
			out.print(beanElearnTools.stringToHtml(beanGetdata.getFieldValue("oc_title", i)));
	  %>
      </A> </TD>
    <TD height="20"> <%=eds.format(beanGetdata.getFieldValue("oc_datetime", i))%> 
    </TD>
  </TR>
  <%
		}
	}
	%>
</TABLE>
<TABLE border="0" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="100%">
  <TR align="center"> 
      <TD colspan="6" height="20"> 
	  	<INPUT name="group" type="hidden" id="group" value="<%=s_Group%>">
	  	<INPUT name="countInPage" type="hidden" id="countInPage" value="<%=	maxCount - (pageNum-1)*listCount%>">
		<INPUT id="action" name="action" type="hidden" value="delete">
        <INPUT type="checkbox" name="checkbox" value="checkbox" onClick="selectAll(this)">
        选择全部 
        <INPUT type="submit" name="Submit" value="删除">
      </TD>
  </TR>
  <%
  }
  else
  {
  	out.println("<TD clospan=2>没有短信</TD>");
	}
	%>
</TABLE>
</FORM>
</BODY>
</HTML>
	