<%@page contentType="text/html;charset=GBK"%>
<%@page session="true"%>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="bqc" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="bcd" scope="request" class="system.beanCodeDetail"/>
<%
//禁止Cache.
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
<TITLE>菜单详细信息</TITLE>
<SCRIPT language="JavaScript">
	
	function check(){
	
	if(document.detailForm.codeid.value.length==0){
    alert("请输入代id！");
    document.detailForm.codeid.focus();
    return;
}

    else if(document.detailForm.codevalue.value.length==0){
    alert("请输入代码值！");
    document.detailForm.codevalue.focus();
    return;
}
 else if(document.detailForm.codenamec.value.length==0){
    alert("请输入代码名字！");
    document.detailForm.codenamec.focus();
    return;
}
	document.detailForm.submit();
	}
	
function formSubmit(action)
	{
    if(document.detailForm.codeid.value.length==0){
    alert("请输入代id！");
    document.detailForm.codeid.focus();
    return;
}

    else if(document.detailForm.codevalue.value.length==0){
    alert("请输入代码值！");
    document.detailForm.codevalue.focus();
    return;
}
 else if(document.detailForm.codenamec.value.length==0){
    alert("请输入代码名字！");
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
代码详细信息
<FORM id="detailForm" name="detailForm" acton="codeList" method="get" target="left">
<TABLE border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" class="xbz" width="98%" bgcolor="#ffffcc">
	<TR>
		<TD>代码ID</TD>
		<TD><INPUT type="text" id="codeid" name="codeid" value='<%=bcd.getCodeId()%>' size="50"></TD>
	</TR>
	<TR>
		<TD>代码值</TD>
		<TD><INPUT type="text" id="codevalue" name="codevalue" value='<%=bcd.getCodeValue()%>' size="50"></TD>
	</TR>
	<TR>
		<TD>代码中文名称</TD>
		<TD><INPUT type="text" id="codenamec" name="codenamec" value='<%=bcd.getCodeNamec()%>' size="50"></TD>
	</TR>
	<TR>
		<TD>代码英文名称</TD>
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
			<INPUT type="button" value="提交" onclick=check()>
		<%
		}
		else
		{
		%>
		<INPUT class="input2" type="button" onClick=window.location.href="codeList?action=new" value="创建新代码">
		<INPUT class="input2" type="button" onClick=javascript:formSubmit("update") value="修改">
		<INPUT class="input2" type="button" onClick=javascript:formSubmit("delete") value="删除">
		<%
		}
		%>
		</TD>
	</TR>
</TABLE>
</FORM>
</BODY>
</HTML>
		
		
