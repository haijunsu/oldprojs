<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>菜单管理页面</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/xbz.css" type="text/css">
</head>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<Script language="javascript">
<!--
function menuSubmit(s_action, s_change, s_menuid)
{
	document.menuForm.action.value = s_action;
	document.menuForm.changenum.value = s_change;
	if(s_action == "delete")
	{
		if(!confirm("确认在删除ID为“" + s_menuid + "”的菜单吗？"))
		{
			return;
		}
	}
	if(s_menuid == "group")
	{
		document.menuForm.group.value = document.menuForm.groupvalue.value;
	}

	document.menuForm.submit();
	return false;
}
-->
</Script>
<body>
<jsp:useBean id="beanGetdata" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="beanQueryCodes" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="beanElearnTools" scope="request" class="com.htyz.beanElearnTools"/>

<%@ include file="/MsgNotify.jsp"%>
<%
String s_msg, s_Gnum, s_Gright, s_Mright, s_Checked, s_Group;
s_msg = (String)request.getAttribute("message");
s_Group =  (String)request.getAttribute("group");
beanQueryCodes.QueryCode("0003");
%>
<span class="xbz">菜单管理页面 </span> 
<%
if(s_msg != null)
{
%>
<Font color="red"><%=s_msg%></Font> 
<%}%>
<p><span class="xbz">数据库中共有 <%=beanGetdata.getRowCount()%> 个菜单</span><br>

<FORM id="menuForm" name="menuForm" method="Get" action="elearn.bean_ManagerMenus">
<input type="hidden" id="action" name="action" value="list">
<input type="hidden" id="rightnum" name="rightnum" value="<%=beanQueryCodes.getCodeCount()%>">
<input type="hidden" id="changenum" name="changenum" value="">
<input type="hidden" id="group" name="group" value="<%=s_Group%>">
  <table border="0" align="center" BORDERCOLOR="blue" class="xbz">
    <tr align="center">
		<td> 菜单编号 </td>
		<td> 菜单中文名称 </td>
		<td> 菜单英文名称 </td>
		<td> 菜单超链接 </td>
		<td> 操作 </td>
	</tr>
	<%
	if(beanGetdata.getRowCount()>0)
	{
		for (int i=0; i<beanGetdata.getRowCount(); i++)
		{
		%>
		<tr align="center">
			<td rowspan='2'> <input id='MenuId<%=i%>' name='MenuId<%=i%>' type='hidden' value='<%=beanGetdata.getFieldValue("menuid", i)%>' size='4' maxlength='4'>
			<%=beanGetdata.getFieldValue("menuid", i)%>
			<input id='oldMenuId<%=i%>' name='oldMenuId<%=i%>' type='hidden' value='<%=beanGetdata.getFieldValue("menuid", i)%>'></td>
			<td> <input id='MnameC<%=i%>' name='MnameC<%=i%>' type='text' value='<%=beanGetdata.getFieldValue("mnamec", i)%>' size="20" maxlength='50'> </td>
			<td> <input id='MnameE<%=i%>' name='MnameE<%=i%>' type='text' value='<%=beanGetdata.getFieldValue("mnamee", i)%>' size="20" maxlength='50'> </td>
			<td> <input id='Murl<%=i%>' name='Murl<%=i%>' type='text' value='<%=beanGetdata.getFieldValue("murl", i)%>' size="30"  maxlength='100'> </td>
			<td rowspan='2' nowrap> 
				<A href=javascript:menuSubmit('update','<%=i%>','<%=beanGetdata.getFieldValue("menuid", i)%>')>修改</A>  
				<A href=javascript:menuSubmit('delete','<%=i%>','<%=beanGetdata.getFieldValue("menuid", i)%>')>删除</A>
			</td>
		</tr>
		<tr align="center">
			<td colspan="3">
			<%
			s_Mright = beanGetdata.getFieldValue("mright", i);
			if(beanQueryCodes.getCodeCount()>0)
			{
				for (int j=0; j<beanQueryCodes.getCodeCount(); j++)
				{
					s_Gnum = beanQueryCodes.getCodeValue(2, j);
					s_Gright = beanElearnTools.parseRight(s_Gnum);
					s_Checked = "";
					if(beanElearnTools.isMenuShow(s_Gright, s_Mright))
						s_Checked = "checked";
				%>
				<input id='Mright<%=i%><%=j%>' name='Mright<%=i%><%=j%>' type='checkbox' <%=s_Checked%> value='<%=s_Gnum%>'><%=beanQueryCodes.getCodeValue(3, j)%>
				<%
				}
			}
			%>
			</td>
		</tr>
		<tr align="center">
			<td colspan="5">  <hr>  </td>
		</tr>
		<%
		}
	}
	%>
	<tr align="center">
		<td rowspan="2">
		<input type="text" id="MenuIdadd" name="MenuIdadd" size="4" maxlength="4">
		</td>
		<td>
		<input type="text" id="MnameCadd" name="MnameCadd" size="20" maxlength="50">
		</td>
		<td>
		<input type="text" id="MnameEadd" name="MnameEadd" size="20" maxlength="50">
		</td>
		<td>
		<input type="text" id="Murladd" name="Murladd"  size="30" maxlength="100">
		</td>
		<td rowspan="2">
			<A href=javascript:menuSubmit('add','add','')> 添加菜单 </A>  &nbsp;
		</td>
	</tr>
	<tr align="center">
		<td colspan="3" nowrap>
		<%
		if(beanQueryCodes.getCodeCount()>0)
		{
			for (int j=0; j<beanQueryCodes.getCodeCount(); j++)
			{
				s_Gnum = beanQueryCodes.getCodeValue(2, j);
			%>
			<input id='Mrightadd<%=j%>' name='Mrightadd<%=j%>' type='checkbox' value='<%=s_Gnum%>'><%=beanQueryCodes.getCodeValue(3, j)%>
			<%
			}
		}
		%>
	</tr>
	<tr align="center">
		<td colspan="5">  
        <hr class="xbz">
          </td>
	</tr>
	<tr align="center">
		<td colspan="5"> <input type='text' size='4' maxlength='4' id='groupvalue' name='groupvalue'> 			<A href=javascript:menuSubmit('list','','group')> 按输入的前几位分组显示 </A>  &nbsp;
</td>
	</tr>
</table>
</FORM>
</table>
</FORM>
</body>
</html>
	