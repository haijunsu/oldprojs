<%@page contentType="text/html;charset=GBK"%>
<%@page session="true"%>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="bqc" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="bcd" scope="request" class="system.beanCodeDetail"/>
<%
//��ֹCache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%
String userid = (String)session.getAttribute("userid");
String action = (String)request.getAttribute("action");
%>
<HTML>
<HEAD>
<TITLE>�˵���ϸ��Ϣ</TITLE>
<SCRIPT language="JavaScript">
	
	function check(){
	
	if(document.detailForm.codeid.value.length==0){
    alert("�������id��");
    document.detailForm.codeid.focus();
    return;
}

    else if(document.detailForm.codevalue.value.length==0){
    alert("���������ֵ��");
    document.detailForm.codevalue.focus();
    return;
}
 else if(document.detailForm.codenamec.value.length==0){
    alert("������������֣�");
    document.detailForm.codenamec.focus();
    return;
}
	document.detailForm.submit();
	}
	
function formSubmit(action)
	{
    if(document.detailForm.codeid.value.length==0){
    alert("�������id��");
    document.detailForm.codeid.focus();
    return;
}

    else if(document.detailForm.codevalue.value.length==0){
    alert("���������ֵ��");
    document.detailForm.codevalue.focus();
    return;
}
 else if(document.detailForm.codenamec.value.length==0){
    alert("������������֣�");
    document.detailForm.codenamec.focus();
    return;
}
		document.detailForm.action.value = action;
		document.detailForm.submit();
		return true;
	}
</SCRIPT>
<link href="/elearning/style.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
������ϸ��Ϣ
<FORM id="detailForm" name="detailForm" acton="codeList" method="get" target="left">
<TABLE border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" class="xbz" width="98%" bgcolor="#ffffcc">
	<TR>
		<TD>����ID</TD>
		<TD><INPUT type="text" id="codeid" name="codeid" value='<%=bcd.getCodeId()%>' size="50"></TD>
	</TR>
	<TR>
		<TD>����ֵ</TD>
		<TD><INPUT type="text" id="codevalue" name="codevalue" value='<%=bcd.getCodeValue()%>' size="50"></TD>
	</TR>
	<TR>
		<TD>������������</TD>
		<TD><INPUT type="text" id="codenamec" name="codenamec" value='<%=bcd.getCodeNamec()%>' size="50"></TD>
	</TR>
	<TR>
		<TD>����Ӣ������</TD>
		<TD><INPUT type="text" id="codenamee" name="codenamee" value='<%=bcd.getCodeNamee()%>' size="50"></TD>
	</TR>
	<TR>
		<TD colspan="2" align="center">
		<INPUT type="hidden" name="action" id="action" value="<%=action%>">
		<INPUT type="hidden" name="reload" id="reload" value="true">
		<INPUT type="hidden" name="oldcodeid" id="oldcodeid" value="<%=bcd.getCodeId()%>">
		<INPUT type="hidden" name="oldcodevalue" id="oldcodevalue" value="<%=bcd.getCodeValue()%>">
		<%
		if (action.equals("add"))
		{
		%>
			<INPUT type="button" value="�ύ" onclick=check()>
		<%
		}
		else
		{
		%>
		<INPUT class="input2" type="button" onClick=window.location.href="codeList?action=new" value="�����´���">
		<INPUT class="input2" type="button" onClick=javascript:formSubmit("update") value="�޸�">
		<INPUT class="input2" type="button" onClick=javascript:formSubmit("delete") value="ɾ��">
		<%
		}
		%>
		</TD>
	</TR>
</TABLE>
</FORM>
</BODY>
</HTML>
		
		
