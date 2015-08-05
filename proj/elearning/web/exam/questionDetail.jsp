<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="bqcTypes" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="bqcStatus" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="btq" scope="request" class="exam.BeanTopicQuestion"/>
<jsp:useBean id="ust" scope="session" class="system.userStyle"/>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%
bqcTypes.QueryCode("0011");
bqcStatus.QueryCode("0012");
String message = (String)request.getAttribute("message");
String className = (String)request.getAttribute("className");
String classId = (String)request.getAttribute("classId");
%>
<HTML>
<HEAD>
<TITLE>课程问题列表</TITLE>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
<SCRIPT language="JavaScript" type="text/JavaScript">
function submitForm(s_action)
{	
	document.detailForm.action.value = s_action;
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
	itemCount = document.detailForm.answersize.value;
	if(selectA.checked)
	{
			for (i=0; i<itemCount; i++)
			{
				document.detailForm["selectItem" + i].checked = true;
			}
	}
	else
	{
			for (i=0; i<itemCount; i++)
			{
				document.detailForm["selectItem" + i].checked = false;
				removeItemsValue = removeItemsValue + "|" + document.detailForm["selectItem" + i].value;
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
<CENTER><H2><%=className%>课程试题集</H2></CENTER>
<%=message%>
<FORM id="detailForm" name="detailForm" aciton="topicCreator">
<INPUT name="answersize" type="hidden" id="answersize" value="<%=btq.getAnswerCount()%>">
<INPUT name="classid" type="hidden" id="classid" value="<%=classId%>">
<INPUT name="topicid" type="hidden" id="topicid" value="<%=btq.getTopicId()%>">
<INPUT name="action" type="hidden" id="action" value="modify">
<TABLE border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" class="xbz" width="100%">
	<TR>
		<TD>题目描述</TD>
		<TD>
			
        <TEXTAREA name="topicdescription" cols="60" rows="2" id="topicdescription"><%=btq.getTopicDescription()%></TEXTAREA>
		</TD>
	</TR>
	<TR>
		<TD>题目类型</TD>
		<TD><SELECT name="topictype" size="1" id="topictype">
          <% 
          for (int i=0; i<bqcTypes.getCodeCount(); i++)
		  {
		  	if (bqcTypes.getCodeValue("code_value", i).equals(btq.getTopicType()))
		  		out.println("<OPTION selected value=\"" + bqcTypes.getCodeValue("code_value", i) + "\">" + bqcTypes.getCodeValue("code_namec", i) + "</OPTION>");
		  	else
		  		out.println("<OPTION value=\"" + bqcTypes.getCodeValue("code_value", i) + "\">" + bqcTypes.getCodeValue("code_namec", i) + "</OPTION>");
		  }
		   %>
        </SELECT>
		</TD>
	</TR>
	<TR>
		<TD>分值</TD>
		
      <TD>
        <INPUT type="text" name="topicmark" id="topicmark" value=<%=btq.getTopicMark()%>>
      </TD>
	</TR>
	<TR>
		<TD>状态</TD>
		
      <TD><SELECT name="topicstatus" size="1" id="topicstatus">
          <% for (int i=0; i<bqcStatus.getCodeCount(); i++)
		  {
		  	if (bqcStatus.getCodeValue("code_value", i).equals(btq.getTopicStatus()))
			  	out.println("<OPTION selected value=\"" + bqcStatus.getCodeValue("code_value", i) + "\">" + bqcStatus.getCodeValue("code_namec", i) + "</OPTION>");
			else
			  	out.println("<OPTION value=\"" + bqcStatus.getCodeValue("code_value", i) + "\">" + bqcStatus.getCodeValue("code_namec", i) + "</OPTION>");
		  }
		   %>
        </SELECT>
	  </TD>
	</TR>
</TABLE>
<%
if (btq.getAnswerCount() > 0)
{
%>
<TABLE class="table003">
	<TR>
		<TD>选择</TD>
		<TD>编号</TD>
		<TD>答案描述</TD>
		<TD>正确答案</TD>
	<%
	for (int i=0; i<btq.getAnswerCount(); i++)
	{
	%>
	<TR>
		
      <TD valign="top"> 
        <INPUT type="checkbox" id="selectItem<%=i%>" name="selectItem<%=i%>" value="<%=btq.getAnswer(i).getTopicAnswer()%>">&nbsp;
      </TD>
      <TD valign="top"> 
        <INPUT name="topicanswer<%=i%>" type="text" id="topicanswer<%=i%>" value="<%=btq.getAnswer(i).getTopicAnswer()%>" size="5" maxlength="1">&nbsp;
      </TD>
      <TD valign="top"> 
			<TEXTAREA name="answerdescription<%=i%>" cols="60" rows="2" id="answerdescription<%=i%>"><%=btq.getAnswer(i).getAnswerDescription()%></TEXTAREA>
      </TD>
        <%
        String check = "";
        if (btq.getAnswer(i).getIsAnswer().equals("1"))  check = "checked";
		%>
      <TD valign="top">
      	<INPUT type="checkbox" <%=check%> id="isanswer<%=i%>" name="isanswer<%=i%>" value="1">
 	  </TD>
	<%
	}
	%>
	</TR>
</TABLE>
<TABLE class="table003">
<TR>
	<TD colspan="4">
        <INPUT type="checkbox" name="check" value="" onClick="selectAll(this)">选择全部
      </TD>
</TR>
<TR>
	<TD colspan="4">
		<INPUT name="btnSubmit" type="button" id="btnSubmit" value="删除答案" onClick="javascript:submitForm('deleteAnswer')">
		<INPUT name="btnSubmit" type="button" id="btnSubmit" value="修改" onClick="javascript:submitForm('modify')">
	</TD>
</TR>
</TABLE>
<%
}
else
{
%>
<INPUT name="btnSubmit" type="button" id="btnSubmit" value="修改" onClick="javascript:submitForm('modify')">
<% } %>
</FORM>
<FORM id="newForm" name="newForm" aciton="topicCreator">
<TABLE class="table003">
	<TR>
		<TD>编号</TD>
		<TD>
			
        <INPUT name="topicanswer" type="text" id="topicanswer" value="" size="5" maxlength="1">
		</TD>
	</TR>
	<TR>
		<TD>答案描述</TD>
		<TD>
			<TEXTAREA name="answerdescription" cols="60" rows="2" id="answerdescription"></TEXTAREA>
		</TD>
	</TR>
	<TR>
		<TD>正确答案</TD>
		<TD>
      	<INPUT type="checkbox" id="isanswer" name="isanswer" value="1">
		</TD>
	</TR>
	<TR>
		<TD>
		<INPUT name="classid" type="hidden" id="classid" value="<%=classId%>">
		<INPUT name="topicid" type="hidden" id="topicid" value="<%=btq.getTopicId()%>">
		<INPUT name="action" type="hidden" id="action" value="newAnswer">
		<INPUT name="submitbtn" type="submit" value="添加">
		</TD>
	</TR>
</TABLE>
</FORM>
<A href="topicCreator?classid=<%= classId %>">返回</A>		
</BODY>
</HTML>
		
		
