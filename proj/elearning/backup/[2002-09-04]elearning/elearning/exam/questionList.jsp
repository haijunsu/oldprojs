<%@page contentType="text/html;charset=GBK"%>
<%@page session="true"%>
<jsp:useBean id="bep" scope="request" class="exam.BeanExamProfile"/>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="bqcTypes" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="bqcStatus" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>
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
String pageno = (String)request.getAttribute("pageno");
if (pageno == null) pageno = "1";
%>
<HTML>
<HEAD>
<TITLE>课程问题列表</TITLE>
<link href="/elearning/style.css" rel="stylesheet" type="text/css">
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
function examSum()
{
	var examCount = 0;
	var markSum = 0;
	for (i=0; i<<%=bep.getTypesCount() - 1%>; i++)
	{
		examCount  = examCount + document.setupForm["examNum" + i].value * 1;
		markSum = markSum + document.setupForm["examNum" + i].value * document.setupForm["mark" + i].value;
	} 
	document.setupForm.mark.value = examCount;
	document.setupForm.examNum.value = markSum;
	return;
}
</SCRIPT>
<NOSCRIPT>
你的浏览器不支持JAVASCRIPT，无法浏览本页动态效果！ 
</NOSCRIPT>
</HEAD>
<BODY>
<CENTER><H2><%=className%>-考试设置</H2></CENTER>
<%=message%> 
<% if (pageno.equals("1"))
	{
%>

<FORM action="topicCreator" method="get" name="setupForm" id="setupForm">
	<TABLE border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" class="xbz" width="100%">
<%
		for ( int i=0; i<bep.getTypesCount() - 1; i++)
		{
%>
	  <TR>
		<TD><%=bep.getMark(i)%>分值题的题目个数：</TD>
		<TD><INPUT name="examNum<%=i%>" type="text" id="examNum<%=i%>" onChange="javascript:examSum();" value="<%=bep.getExamNum(i)%>">
			<INPUT name="mark<%=i%>" type="hidden" id="mark<%=i%>" value="<%=bep.getMark(i)%>">
		</TD>
	  </TR>
<%
		}
%>
	  <TR>
		<TD>考试时间：</TD>
		<TD><INPUT name="examNum<%=bep.getTypesCount() - 1%>" type="text" id="examNum<%=bep.getTypesCount() - 1%>" onChange="javascript:examSum();" value="<%=bep.getExamNum(bep.getTypesCount() - 1)%>">分钟
			<INPUT name="mark<%=bep.getTypesCount() - 1%>" type="hidden" id="mark<%=bep.getTypesCount() - 1%>" value="time">
		</TD>
	  </TR>
	  <TR>
		<TD colspan="2" align="center">
		题目总合：
		<INPUT name="mark" type="text" id="mark" style="border:none;" size="10">
		总分：
		<INPUT name="examNum" type="text" id="examNum" style="border:none;" size="10">
		</TD>
	  </TR>
	  <TR>
		<TD colspan="2" align="center">
		<INPUT name="classid" type="hidden" id="classid" value="<%=classId%>">
		<INPUT name="action" type="hidden" id="action" value="setup">
		<INPUT name="submit" type="submit" value="设定">
		</TD>
	  </TR>
	</TABLE>
</FORM>
<SCRIPT language="JavaScript" type="text/JavaScript">
examSum();
</SCRIPT>
<% } %>
<CENTER><H2><%=className%>-课程试题集</H2></CENTER>
<%
if (bgd.getRowCount()>0)
{
%>
<FORM id="detailForm" name="detailForm" aciton="topicCreator">
<TABLE border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" class="xbz" width="100%">
	<TR bgcolor="#CCCCCC" align="center" Height="25">
		<TD>选择</TD>
		<TD>题目</TD>
		<TD>题目类型</TD>
		<TD>分值</TD>
		<TD>状态</TD>
		<TD>出题人</TD>
		<TD>时间</TD>
	</TR>
	<%
	int listCount = Integer.parseInt(ust.getLines_in_page());
	int pageNum = Integer.parseInt(pageno);
	int maxCount = java.lang.Math.min(bgd.getRowCount(), (pageNum-1)*listCount+listCount);
	for (int i=0; i<maxCount-(pageNum-1)*listCount; i++)
	{
	%>
	<TR>
		
      <TD> 
        <INPUT type="checkbox" id="selectItem<%=i%>" name="selectItem<%=i%>" value='<%=bgd.getFieldValue("topic_id", i+(pageNum-1)*listCount)%>'">&nbsp;
      </TD>
		
      <TD> <A href=topicCreator?action=showQuestion&classid=<%=bgd.getFieldValue("class_id", i+(pageNum-1)*listCount)%>&topicid=<%=bgd.getFieldValue("topic_id", i+(pageNum-1)*listCount)%>><%=bgd.getFieldValue("topic_description", i+(pageNum-1)*listCount)%></A>&nbsp; 
      </TD>
		
      <TD> 
        <%
		for (int j=0; j<bqcTypes.getCodeCount(); j++)
		{
			if (bqcTypes.getCodeValue("code_value", j).equals(bgd.getFieldValue("topic_type", i+(pageNum-1)*listCount)))
			{
				out.print(bqcTypes.getCodeValue("code_namec", j));
				break;
			}
		}
		%>
        &nbsp; </TD>
		
      <TD> <%=bgd.getFieldValue("topic_mark",i+(pageNum-1)*listCount)%>&nbsp; </TD>
		
      <TD> 
        <%
		for (int j=0; j<bqcStatus.getCodeCount(); j++)
		{
			if (bqcStatus.getCodeValue("code_value", j).equals(bgd.getFieldValue("topic_status", i+(pageNum-1)*listCount)))
			{
				out.print(bqcStatus.getCodeValue("code_namec", j));
				break;
			}
		}
		%>
        &nbsp; 
      
      </TD>
		
      <TD> <%=bgd.getFieldValue("user_id", i+(pageNum-1)*listCount)%>&nbsp;  </TD>
		
      <TD> <%=ets.FormatDate(bgd.getFieldValue("topic_date", i+(pageNum-1)*listCount))%>&nbsp;  </TD>
	<%
	}
	%>
	</TR>
</TABLE>
<INPUT name="countInPage" type="hidden" id="countInPage" value="<%= maxCount-(pageNum-1)*listCount%>">
<INPUT name="classid" type="hidden" id="classid" value="<%=classId%>">
<INPUT name="action" type="hidden" id="action" value="deleteQuestion">
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
			<A href="topicCreator?pageno=<%=i%>&classid=<%=bgd.getFieldValue("class_id", 0)%>"><%=i%></A>
			<%
			}
			%>
	</TD>
</TR>
<TR>
	<TD>
		<INPUT name="btnSubmit" type="button" id="btnSubmit" value="删除" onClick="javascript:submitForm(<%= pageno %>)">
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
<CENTER><H2><%=className%>-增加试题</H2></CENTER>
<FORM id="newForm" name="newForm" aciton="topicCreator">
<TABLE border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" class="xbz" width="100%">
	<TR>
		<TD>题目描述</TD>
		<TD>
			<TEXTAREA name="topicdescription" cols="60" rows="3" id="topicdescription"></TEXTAREA>
		</TD>
	</TR>
	<TR>
		<TD>题目类型</TD>
		<TD><SELECT name="topictype" size="1" id="topictype">
          <% for (int i=0; i<bqcTypes.getCodeCount(); i++)
		  {
		  	out.println("<OPTION value=\"" + bqcTypes.getCodeValue("code_value", i) + "\">" + bqcTypes.getCodeValue("code_namec", i) + "</OPTION>");
		  }
		   %>
        </SELECT>
		</TD>
	</TR>
	<TR>
		<TD>分值</TD>
		
      <TD>
        <INPUT type="text" name="topicmark" id="topicmark">
      </TD>
	</TR>
	<TR>
		<TD>状态</TD>
		
      <TD><SELECT name="topicstatus" size="1" id="topicstatus">
          <% for (int i=0; i<bqcStatus.getCodeCount(); i++)
		  {
		  	out.println("<OPTION value=\"" + bqcStatus.getCodeValue("code_value", i) + "\">" + bqcStatus.getCodeValue("code_namec", i) + "</OPTION>");
		  }
		   %>
        </SELECT>
	  </TD>
	</TR>
	<TR>
		<TD>
		<INPUT name="classid" type="hidden" id="classid" value="<%=classId%>">
		<INPUT name="pageno" type="hidden" id="pageno" value="<%=(int)java.lang.Math.ceil((double)bgd.getRowCount()/Integer.parseInt(ust.getLines_in_page()))%>">
		<INPUT name="action" type="hidden" id="action" value="newQuestion">
		</TD>
	</TR>
</TABLE>
<INPUT name="submitbtn" type="submit" value="添加">
</FORM>
<A href="topicList?action=showDetail&codeid=<%= classId.substring(0, 4) %>&codevalue=<%= classId.substring(4, 8) %>">返回</A>		
</BODY>
</HTML>
		
		
