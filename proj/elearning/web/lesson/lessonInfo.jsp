<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>
<%
//��ֹCache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<HTML>
<HEAD>
<TITLE>�γ���ϸ��Ϣ</TITLE>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
<%
String classid = request.getParameter("classid");
String showback = request.getParameter("showback");
if (showback == null) showback = "";
if(classid == null)
{
	out.println("<FONT color=red>����<p>û��ָ���γ�</FONT>");
}
else
{
	String sql = "SELECT * FROM t_class WHERE class_id = '" + classid + "'";
	bgd.executeSelect(sql);
%>
<B><%=ets.stringToHtml(bgd.getFieldValue("class_name", 0))%></B>---��ϸ��Ϣ
<center>
<TABLE border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" class="xbz" width="95%">
	<TR>
		<TD width="100" align="center" bgcolor="#EEEEEE">�γ�����</TD>
		<TD><%=ets.stringToHtml(bgd.getFieldValue("class_name", 0))+"&nbsp;"%>
		</TD>
	</TR>
	<TR>
		<TD width="100" align="center" bgcolor="#EEEEEE">�ؼ���</TD>
		<TD><%=ets.stringToHtml(bgd.getFieldValue("keywords", 0))+"&nbsp;"%></TD>
	</TR>
	<TR>
		<TD width="100" align="center" bgcolor="#EEEEEE">����</TD>
		<TD>
        <%=ets.stringToHtml(bgd.getFieldValue("description", 0))+"&nbsp;"%>
      </TD>
	</TR>
</TABLE>
<% } %>
<%
	if (showback.equals("show")){
		out.println("<A href='javascript:history.go(-1)'>����</A>");
	}
%>
</center>
</BODY>
</HTML>
