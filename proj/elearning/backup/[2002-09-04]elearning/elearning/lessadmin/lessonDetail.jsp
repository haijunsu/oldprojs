<%@page contentType="text/html;charset=GBK"%>
<%@page session="true"%>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="bqcTypes" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="bqcStatus" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="bld" scope="request" class="lessadmin.beanLessonDetail"/>
<%
//��ֹCache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%
String action = (String)request.getAttribute("action");
String navigation = (String)request.getAttribute("navigation");
String classid = (String)request.getAttribute("classid");
String lessonid = (String)request.getAttribute("lessonid");
String addtype = (String)request.getAttribute("addtype");
%>
<HTML>
<HEAD>
<TITLE>�γ���ϸ��Ϣ</TITLE>
<link href="/elearning/style.css" rel="stylesheet" type="text/css">
<SCRIPT language="JavaScript">
	function formSubmit(action)
	{
	if (document.detailForm.classname.value.length==0){
   alert("�γ����Ʋ���Ϊ�գ���");
   document.detailForm.classname.focus();
   return;
}
		document.detailForm.action.value = action;
		if(action=="delete")
		{
			if(!confirm("ȷ��Ҫɾ���γ���\nע�⣺ɾ���γ̽�ɾ�������е��½ڣ����޷��ָ���"))
				return false;
		}
		document.detailForm.submit();
		return true;
	}
</SCRIPT>
</HEAD>
<BODY>
<%=navigation%>
<%@ include file="/elearning/lessadmin/lessonSearch.jsp" %>
<%=bld.getClassName()%>��ϸ��Ϣ
<FORM id="detailForm" name="detailForm" method="get" target="left">
<TABLE class="table003">
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
		<TD><INPUT type="text" id="classtime" name="classtime" value='<%=bld.getClassTime()%>' size="50"></TD>
	</TR>
	<TR>
		<TD>ͨ����׼</TD>
		<TD><INPUT type="text" id="passstander" name="passstander" value='<%=bld.getPassStander()%>' size="50"></TD>
	</TR>
	<TR>
		<TD>�γ�״̬</TD>
		<TD>
			<SELECT id="classstatus" name="classstatus">
			<%
				for (int i=0; i<bqcStatus.getCodeCount(); i++)
				{
					if (bld.getClassStatus().equals(bqcStatus.getCodeValue("code_value", i)))
						out.print("<OPTION SELECTED ");
					else
						out.print("<OPTION ");
					out.println(" value=" + bqcStatus.getCodeValue("code_value", i) +">" + bqcStatus.getCodeValue("code_namec", i) + "</OPTION>");
				}
			%>
			</SELECT>
	</TR>
	<TR>
		<TD colspan="2" align="center">
		<INPUT type="hidden" name="action" id="action" value="update">
		<INPUT type="hidden" name="object" id="object" value="class">
		<INPUT type="hidden" name="reload" id="reload" value="true">
		<INPUT type="hidden" name="classid" id="classid" value="<%=bld.getClassId()%>">
		<INPUT type="button" onClick=window.location.href="chapterList?action=new&addtype=chapter&classid=<%=bld.getClassId()%>" value="�����">
		<INPUT type="button" onClick=javascript:formSubmit("update") value="�޸�">
		<INPUT type="button" onClick=javascript:formSubmit("delete") value="ɾ��">
		</TD>
	</TR>
</TABLE>
</FORM>
</BODY>
</HTML>
		
		
