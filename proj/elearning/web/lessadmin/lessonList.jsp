<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="bqcTypes" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="bqcStatus" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="bld" scope="request" class="lessadmin.beanLessonDetail"/>
<jsp:useBean id="ust" scope="session" class="system.userStyle"/>
<%
//��ֹCache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%
String navigation = (String)request.getAttribute("navigation");
String catalog = (String)request.getAttribute("catalog");
String codeid = (String)request.getAttribute("codeid");
String codevalue = (String)request.getAttribute("codevalue");
String action = (String)request.getAttribute("action");
%>
<HTML>
<HEAD>
<Script language="javascript">
<!--
function check(){
if (document.addForm.classname.value.length==0){
alert("�γ����Ʋ���Ϊ�գ���");
document.addForm.classname.focus();
return;
}
document.addForm.submit();
}

-->
</Script>
<TITLE>�γ��б�</TITLE>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
<%=navigation%>
<%@ include file="/lessadmin/lessonSearch.jsp" %>
<%=catalog%> �γ��б�
<%
if (bgd.getRowCount()>0)
{
%>
<TABLE  border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" class="xbz" width="100%">
	<TR bgcolor="#CCCCCC" align="center" Height="25">
		<TD>�γ�����</TD>
		<TD>�γ�<br>����</TD>
		<TD>�ؼ���</TD>
		<TD width="250">����</TD>
		<TD>��ʱ</TD>
		<TD>ͨ��<br>��׼</TD>
		<TD>�γ�<br>״̬</TD>
	</TR>
	<%
	String pageno = (String)request.getAttribute("pageno");
	if (pageno == null) pageno = "1";
	int listCount = Integer.parseInt(ust.getLines_in_page());
	int pageNum = Integer.parseInt(pageno);
	int maxCount = java.lang.Math.min(bgd.getRowCount(), (pageNum-1)*listCount+listCount);
	for (int i=(pageNum-1)*listCount; i<maxCount; i++)
	{
	%>
	<TR>
		
    <TD> <A href='chapterList?reload=true&classid=<%=bgd.getFieldValue("class_id", i)%>' target="left"><%=bgd.getFieldValue("class_name", i)%></A>&nbsp; 
    </TD>
		
    <TD> 
      <%
			for (int j=0; j<bqcTypes.getCodeCount(); j++)
			{
				if (bqcTypes.getCodeValue("code_value", j).equals(bgd.getFieldValue("class_type", i)))
				{
					out.print(bqcTypes.getCodeValue("code_namec", j));
					break;
				}
			}
			%>
      &nbsp; </TD>
		
    <TD><%=bgd.getFieldValue("keywords",i)+"&nbsp;"%> </TD>
		
    <TD width="250"> <%=bgd.getFieldValue("description", i)+"&nbsp;"%> </TD>
		
    <TD> <%=bgd.getFieldValue("class_time", i)%> </TD>
		
    <TD> <%=bgd.getFieldValue("pass_stander", i)%> </TD>
		
    <TD> 
      <%
			for (int j=0; j<bqcStatus.getCodeCount(); j++)
			{
				if (bqcStatus.getCodeValue("code_value", j).equals(bgd.getFieldValue("class_Status", i)))
				{
					out.print(bqcStatus.getCodeValue("code_namec", j));
					break;
				}
			}
			%>
    </TD>
	<%
	}
	%>
	</TR>
</TABLE>
<TABLE>
<TR>
	<TD>
		<%
			int i_count = (int)java.lang.Math.ceil((double)bgd.getRowCount()/listCount);
		%>
		��<%=i_count%>ҳ&nbsp;
		<%
			for (int i=1; i<=i_count; i++)
			if (i == pageNum)
			{
				out.print(pageno);
			}
			else
			{
			%>
			<A href="lessonList?action=<%=action%>&pageno=<%=i%>&codeid=<%=codeid%>&codevalue=<%=codevalue%>"><%=i%></A>
			<%
			}
			%>
	</TD>
</TR>
</TABLE>
<%
}
else
{
	out.println("û�м�¼��");
	
}
if (action.equals("showDetail"))
{
%>
<FORM id="addForm" name="addForm" method="get" action="lessonList">
<TABLE>
	<TR>
		<TD>�γ�����</TD>
		<TD><INPUT type="text" id="classname" name="classname" value='<%=bld.getClassName()%>' size="50"></TD>
	</TR>
	<TR>
		<TD>�γ�����</TD>
		<TD>
			<%
			for (int i=0; i<bqcTypes.getCodeCount(); i++)
			{
				if (bld.getClassType().equals(bqcTypes.getCodeValue("code_value", i)))
					out.println("<INPUT type=\"radio\" id=\"classtype\" name=\"classtype\" checked value=\"" + bqcTypes.getCodeValue("code_value", i) +"\">" + bqcTypes.getCodeValue("code_namec", i));
				else
					out.println("<INPUT type=\"radio\" id=\"classtype\" name=\"classtype\" value=\"" + bqcTypes.getCodeValue("code_value", i) +"\">" + bqcTypes.getCodeValue("code_namec", i));
			}
			%>
		</TD>
	</TR>
	<TR>
		<TD>�ؼ���</TD>
		<TD><INPUT type="text" id="keywords" name="keywords" value='<%=bld.getKeywords()%>' size="50"></TD>
	</TR>
	<TR>
		<TD>����</TD>
		<TD>
        <TEXTAREA name="description" cols="50" id="description"><%=bld.getDescription()%></TEXTAREA>
      </TD>
	</TR>
	<TR>
		<TD>��ʱ</TD>
		
      <TD>
        <INPUT name="classtime" type="text" id="classtime" value="<%=bld.getClassTime()%>" size="50">
      </TD>
	</TR>
	<TR>
		<TD>ͨ����׼</TD>
		<TD><INPUT type="text" id="passstander" name="passstander" value='<%=bld.getPassStander()%>' size="50"></TD>
	</TR>
	<TR>
		<TD colspan="2">
			<INPUT type="hidden" id="action" name="action" value="add">
			<INPUT type="hidden" id="codeid" name="codeid" value="<%=codeid%>">
			<INPUT type="hidden" id="codevalue" name="codevalue" value="<%=codevalue%>">
			<INPUT type="button" value="��ӿγ�" onclick=check()>
		</TD>
	</TR>
</TABLE>
</FORM>
<%
}
%>
</BODY>
</HTML>
		
		
