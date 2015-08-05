<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="bqc" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="bld" scope="request" class="lessadmin.beanLessonDetail"/>
<jsp:useBean id="ust" scope="session" class="system.userStyle"/>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%
String isChecked = "";
String action = (String)request.getAttribute("action");
java.util.Vector selectedItems = (java.util.Vector) session.getAttribute("selectedItems");
java.util.Vector selectedGroups = (java.util.Vector) session.getAttribute("selectedGroups");
%>
<HTML>
<HEAD>
<TITLE>指定用户组列表</TITLE>
<link href="../style.css" rel="stylesheet" type="text/css">
<SCRIPT language="JavaScript" type="text/JavaScript">
function submitForm(pageNo)
{	
	document.detailForm.pageno.value = pageNo;
	document.detailForm.submit();
	return true;
}
function checkRemoveItem(clickItem)
{
	removeItemsValue = document.detailForm.removeItems.value;
	if(clickItem.checked)
	{
			sp = removeItemsValue.indexOf(clickItem.value);
			if (sp == 0)
			{
				document.detailForm.removeItems.value = removeItemsValue.substring(15, removeItemsValue.length);
			}
			else
			{
				document.detailForm.removeItems.value = removeItemsValue.substring(0, sp - 1) + removeItemsValue.substring(sp + 14, removeItemsValue.length);
			
			}
	}
	else
	{
		if (removeItemsValue.length==0)
		{
			document.detailForm.removeItems.value = clickItem.value;
		}
		else
		{
			document.detailForm.removeItems.value = removeItemsValue + "|" + clickItem.value;
		}
	}
//	alert(document.detailForm.removeItems.value);
}
function selectAll(selectA)
{
	removeItemsValue = "";
	itemCount = document.detailForm.countInPage.value;
	if(selectA.checked)
	{
			for (i=0; i<itemCount; i++)
			{
				document.detailForm["selectGroup" + i].checked = true;
			}
	}
	else
	{
			for (i=0; i<itemCount; i++)
			{
				document.detailForm["selectGroup" + i].checked = false;
				removeItemsValue = removeItemsValue + "|" + document.detailForm["selectGroup" + i].value;
			}
	}
	document.detailForm.removeItems.value = removeItemsValue;
}
</SCRIPT>
<NOSCRIPT>
你的浏览器不支持JAVASCRIPT，无法浏览本页动态效果！ 
</NOSCRIPT>

</HEAD>
<BODY>
<A href="<%=request.getContextPath()%>/" target=_top>首页</A>><A href="lessonReqManager<% %>" target=_top>必修课指定</A>&gt;指定用户组 
<%@ include file="/lessadmin/lessonSearch.jsp" %>
<% 
if (!selectedItems.isEmpty())
{
%>
<P>您已经选择了<%= selectedItems.size() %>门课程：<BR>
<% 
	for (int i=0; i<selectedItems.size(); i++)
	{
		String selectedItem = (String)selectedItems.elementAt(i);
		out.print(selectedItem.substring(15, selectedItem.length()));
		if (i < selectedItems.size()-1)
			out.print("、");
		else
			out.println("<BR>");
	}
}
 %>
<% 
if (!selectedGroups.isEmpty())
{
%>
<P>您已经选择了<%= selectedGroups.size() %>个用户组：<BR>
<% 
	for (int i=0; i<selectedGroups.size(); i++)
	{
		String selectedItem = (String)selectedGroups.elementAt(i);
		out.print(selectedItem.substring(selectedItem.indexOf(",") + 1, selectedItem.length()));
		if (i < selectedGroups.size()-1)
			out.print("、");
		else
			out.println("<BR>");
	}
}
 %>


用户组列表：
<%
if (bgd.getRowCount()>0)
{
%>
<FORM id="detailForm" name="detailForm" aciton="lessonReqList">
<TABLE class="table003">
	<TR>
		<TD>选择</TD>
		<TD>用户组名称</TD>
	</TR>
	<%
	String pageno = (String)request.getAttribute("pageno");
	if (pageno == null) pageno = "1";
	int listCount = Integer.parseInt(ust.getLines_in_page());
	int pageNum = Integer.parseInt(pageno);
	int maxCount = java.lang.Math.min(bgd.getRowCount(), (pageNum-1)*listCount+listCount);
	for (int i=0; i<maxCount-(pageNum-1)*listCount; i++)
	{
	%>
	<TR>
		
      <TD>&nbsp; 
        <% 
			if (selectedGroups.contains(bgd.getFieldValue("code_value", i+(pageNum-1)*listCount) + "," + bgd.getFieldValue("code_namec", i+(pageNum-1)*listCount)))
				isChecked = "checked";
			else
				isChecked = "";
			%>
        <INPUT type="checkbox" <%= isChecked %> id="selectGroup<%=i%>" name="selectGroup<%=i%>" value='<%=bgd.getFieldValue("code_value", i+(pageNum-1)*listCount)%>,<%=bgd.getFieldValue("code_namec", i+(pageNum-1)*listCount)%>' onClick="checkRemoveItem(this)">&nbsp;
		</TD>
		<TD>&nbsp;
			<%=bgd.getFieldValue("code_namec", i+(pageNum-1)*listCount)%>&nbsp;
		</TD>
	<%
	}
	%>
	</TR>
</TABLE>
<INPUT name="countInPage" type="hidden" id="countInPage" value="<%= maxCount-(pageNum-1)*listCount%>">
<INPUT name="removeItems" type="hidden" id="removeItems" value="">
<INPUT name="action" type="hidden" id="action" value="<%= action%>">
<INPUT name="pageno" type="hidden" id="pageno" value="<%= pageno%>">
<TABLE class="table003">
<TR>
	<TD>
        <INPUT type="checkbox" name="check" value="" onClick="selectAll(this)">选择全部
      </TD>
	<TD>
		<%
			int i_count = (int)java.lang.Math.ceil((double)bgd.getRowCount()/listCount);
		%>
		共<%=i_count%>页&nbsp;
		<%
			for (int i=1; i<=i_count; i++)
			if (i == pageNum)
			{
				out.print(pageno);
			}
			else
			{
			%>
			<A href="javascript:submitForm(<%=i%>)"><%=i%></A>
			<%
			}
			%>
	</TD>
</TR>
<TR>
	<TD>
		<INPUT name="btnSubmit" type="button" id="btnSubmit" value="确认选择" onClick="javascript:submitForm(<%= pageno %>)">
		<INPUT name="selectedok" type="button" id="selectedok" value="完成指定" onClick="javascript:submitForm(0)">
	</TD>
</TR>
</TABLE>
</FORM>
<%
}
else
{
	out.println("没有记录！");
	
}
%>
</BODY>
</HTML>
		
		
