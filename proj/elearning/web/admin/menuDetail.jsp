<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="bqc" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="bmd" scope="request" class="system.beanMenuDetail"/>
<%
//禁止Cache.
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
<TITLE>菜单详细信息</TITLE>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
<SCRIPT Language="JavaScript">
function check(){
if (document.detailForm.menuid.value.length==0){
alert("菜单id不能为空！！");
document.detailForm.menuid.focus();
return;
}
else if(document.detailForm.menunamec.value.length==0){
alert("菜单名称不能为空！！");
document.detailForm.menunamec.focus();
return;
}
else if(document.detailForm.menuurl.value.length==0){
alert("连接地址不能为空！！");
document.detailForm.menuurl.focus();
return;
}
document.detailForm.submit();
}


	function formSubmit(action)
	{
		if (document.detailForm.menuid.value.length==0){
alert("菜单id不能为空！！");
document.detailForm.menuid.focus();
return;
}
else if(document.detailForm.menunamec.value.length==0){
alert("菜单名称不能为空！！");
document.detailForm.menunamec.focus();
return;
}
else if(document.detailForm.menuurl.value.length==0){
alert("连接地址不能为空！！");
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
菜单详细信息
<FORM id="detailForm" name="detailForm" acton="menuList" method="get" target="left">
<TABLE  border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" class="xbz" width="600">
	<TR>
		<TD>菜单ID</TD>
		<TD><INPUT class="input1" type="text" id="menuid" name="menuid" value='<%=bmd.getMenuId()%>' size="50"></TD>
	</TR>
	<TR>
		<TD>菜单中文名称</TD>
		<TD><INPUT class="input1" type="text" id="menunamec" name="menunamec" value='<%=bmd.getMenuNamec()%>' size="50"></TD>
	</TR>
	<TR>
		<TD>菜单英文名称</TD>
		<TD><INPUT class="input1" type="text" id="menunamee" name="menunamee" value='<%=bmd.getMenuNamee()%>' size="50"></TD>
	</TR>
	<TR>
		<TD>菜单超链接</TD>
		<TD><INPUT class="input1" type="text" id="menuurl" name="menuurl" value='<%=bmd.getMenuUrl()%>' size="50"></TD>
	</TR>
	<TR>
		<TD colspan="2">菜单权限：<BR>
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
			<INPUT class="input2" type="button" value="提交" onclick=check()>
		<%
		}
		else
		{
		%>
		<INPUT class="input2" type="button" onClick=window.location.href="menuList?action=new" value="创建新菜单">
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
		
		
