<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="bqc" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="bmd" scope="request" class="system.beanMenuDetail"/>
<%
//��ֹCache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%
String userid = (String)session.getAttribute("userid");
String action = (String)request.getAttribute("action");
String pageno = (String)request.getAttribute("pageno");
%>
<HTML>
<HEAD>
<TITLE>�˵���ϸ��Ϣ</TITLE>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
<SCRIPT Language="JavaScript">
function check(){
if (document.detailForm.menuid.value.length==0){
alert("�˵�id����Ϊ�գ���");
document.detailForm.menuid.focus();
return;
}
else if(document.detailForm.menunamec.value.length==0){
alert("�˵����Ʋ���Ϊ�գ���");
document.detailForm.menunamec.focus();
return;
}
else if(document.detailForm.menuurl.value.length==0){
alert("���ӵ�ַ����Ϊ�գ���");
document.detailForm.menuurl.focus();
return;
}
document.detailForm.submit();
}


	function formSubmit(action)
	{
		if (document.detailForm.menuid.value.length==0){
alert("�˵�id����Ϊ�գ���");
document.detailForm.menuid.focus();
return;
}
else if(document.detailForm.menunamec.value.length==0){
alert("�˵����Ʋ���Ϊ�գ���");
document.detailForm.menunamec.focus();
return;
}
else if(document.detailForm.menuurl.value.length==0){
alert("���ӵ�ַ����Ϊ�գ���");
document.detailForm.menuurl.focus();
return;
}
		
		document.detailForm.action.value = action;
		document.detailForm.submit();
		return true;
	}
</SCRIPT>
</HEAD>
<BODY>
�˵���ϸ��Ϣ
<FORM id="detailForm" name="detailForm" acton="menuList" method="get" target="left">
<TABLE  border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" class="xbz" width="600">
	<TR>
		<TD>�˵�ID</TD>
		<TD><INPUT class="input1" type="text" id="menuid" name="menuid" value='<%=bmd.getMenuId()%>' size="50"></TD>
	</TR>
	<TR>
		<TD>�˵���������</TD>
		<TD><INPUT class="input1" type="text" id="menunamec" name="menunamec" value='<%=bmd.getMenuNamec()%>' size="50"></TD>
	</TR>
	<TR>
		<TD>�˵�Ӣ������</TD>
		<TD><INPUT class="input1" type="text" id="menunamee" name="menunamee" value='<%=bmd.getMenuNamee()%>' size="50"></TD>
	</TR>
	<TR>
		<TD>�˵�������</TD>
		<TD><INPUT class="input1" type="text" id="menuurl" name="menuurl" value='<%=bmd.getMenuUrl()%>' size="50"></TD>
	</TR>
	<TR>
		<TD colspan="2">�˵�Ȩ�ޣ�<BR>
			<%
			boolean isChecked;
			bqc.QueryCode("0003");
			for (int j=0; j<bqc.getCodeCount(); j++)
			{
				isChecked = ets.isMenuShow(ets.parseRight(bqc.getCodeValue("code_value", j)), bmd.getMenuRight());
			%>
			<input id='menuright<%=j%>' name='menuright<%=j%>' type='checkbox' <%=isChecked?"CHECKED":""%> value='<%=bqc.getCodeValue("code_value", j)%>'><%=bqc.getCodeValue("code_namec", j)%>
			<%
			}
			%>
			<INPUT type="hidden" name="rightcount" id="rightcount" value="<%=bqc.getCodeCount()%>">
		</TD>
	</TR>
	<TR>
		<TD colspan="2" align="center">
		<INPUT type="hidden" id="oldmenuid" name="oldmenuid" value='<%=bmd.getMenuId()%>' size="50">
		<INPUT type="hidden" name="action" id="action" value="<%=action%>">
		<INPUT type="hidden" name="pageno" id="pageno" value="<%=pageno%>">
		<%
		if (action.equals("add"))
		{
		%>
			<INPUT class="input2" type="button" value="�ύ" onclick=check()>
		<%
		}
		else
		{
		%>
		<INPUT class="input2" type="button" onClick=window.location.href="menuList?action=new" value="�����²˵�">
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
		
		
