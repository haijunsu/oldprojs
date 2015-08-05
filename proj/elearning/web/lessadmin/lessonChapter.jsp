<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="bqc" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="bcd" scope="request" class="lessadmin.beanChapterDetail"/>
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
java.util.Vector filesVector = (java.util.Vector) request.getAttribute("fileList");
int count;
if (filesVector == null) 
	count = 0;
else
	count = filesVector.size();
%>
<HTML>
<HEAD>
<TITLE>�½���ϸ��Ϣ</TITLE>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
<SCRIPT language="JavaScript">

function check(){
if (document.detailForm.chapterno.value.length==0){
alert("��Ų���Ϊ�գ���");
document.detailForm.chapterno.focus();
return;
}
else if(document.detailForm.lessonname.value.length==0){
alert("���Ʋ���Ϊ�գ���");
document.detailForm.lessonname.focus();
return;
}
document.detailForm.submit();
}

function formSubmit(action)
	{
	if (document.detailForm.chapterno.value.length==0){
alert("��Ų���Ϊ�գ���");
document.detailForm.chapterno.focus();
return;
}
else if(document.detailForm.lessonname.value.length==0){
alert("���Ʋ���Ϊ�գ���");
document.detailForm.lessonname.focus();
return;
}
		
		document.detailForm.action.value = action;
		if(action=="delete"){
		    if(!confirm("ȷ��Ҫɾ����"));
			return false;
		}
		document.detailForm.submit();
		return true;
	}
function deleteFiles()
	{
		if (confirm("ȷ��Ҫɾ��ѡ����ļ���"))
		{
			document.fileForm.submit();
			return true;
		}
		return false;
	}
function uploadFiles()
	{
			document.uploadForm.submit();
	}
function selectAll(selectA)
{
	removeItemsValue = "";
	itemCount = document.fileForm.countInPage.value;
	if(selectA.checked)
	{
			for (i=0; i<itemCount; i++)
			{
				document.fileForm["select" + i].checked = true;
			}
	}
	else
	{
			for (i=0; i<itemCount; i++)
			{
				document.fileForm["select" + i].checked = false;
			}
	}
	
}
</SCRIPT>
</HEAD>
<BODY>
<%=navigation%>
<%@ include file="/lessadmin/lessonSearch.jsp" %>
<FORM id="detailForm" name="detailForm"  method="post" target="left" action="chapterList">
<TABLE border="1">
	<TR>
	<%
		String chapterNo = "";
		if(lessonid.endsWith("000")&&action.equals("update")&&addtype.equals("section")||(lessonid.equals("")&&action.equals("add")))
		{
			if (!bcd.getLessonId().equals(""))
				chapterNo = bcd.getLessonId().substring(0, 3);
		%>
		<TD>�±��</TD>
		<TD><INPUT type="text" id="chapterno" name="chapterno" value='<%=chapterNo%>' size="5"></TD>
		<%
		}
		else
		{
			if (!bcd.getLessonId().equals(""))
				chapterNo = bcd.getLessonId().substring(3, 6);
		%>
		<TD>�ڱ��</TD>
		<TD><INPUT type="text" id="chapterno" name="chapterno" value='<%=chapterNo%>' size="5"></TD>
		<%
		}
	%>
	</TR>
	<TR>
		<TD>
		<%
		if(lessonid.endsWith("000")&&action.equals("update")&&addtype.equals("section")||(lessonid.equals("")&&action.equals("add")))
			out.print("������");
		else
			out.print("������");
		%>
		</TD>
		<TD><INPUT type="text" id="lessonname" name="lessonname" value='<%=bcd.getLessonName()%>' size="50"></TD>
	</TR>
	<%
	if (!action.equals("add"))
	{
		String prefixUrl = (String)request.getAttribute("prefixUrl");
	%>
	<TR>
		<TD>���ļ�</TD>
		<TD>
		<SELECT name="lessonurl" size="1" id="lessonurl">
		<%
		for (int j=0; j<count; j++)
		{
			java.io.File file = (java.io.File) filesVector.elementAt(j);
			String filename = file.getName();
			String isChecked = "";
			if (bcd.getLessonUrl().endsWith(filename))
				isChecked = "selected";
		%>
          <OPTION <%=isChecked%> value="<%=prefixUrl+filename%>"><%=filename%></OPTION>
		<%
		}
		%>
        </SELECT>
	</TR>
	<%
	}
	%>
	<TR>
		<TD colspan="2" align="center">
		<INPUT type="hidden" name="action" id="action" value="<%=action%>">
		<INPUT type="hidden" name="reload" id="reload" value="true">
		<INPUT type="hidden" name="classid" id="classid" value="<%=classid%>">
		<INPUT type="hidden" name="lessonid" id="lessonid" value="<%=lessonid%>">
		<INPUT type="hidden" name="addtype" id="addtype" value="<%=addtype%>">
		<%
		if (action.equals("add"))
		{
		%>
			<INPUT type="button" value="�ύ" onclick=check()>
		<%
		}
		else if (bcd.getLessonId().endsWith("000"))
		{
		%>
		<INPUT type="button" onClick=window.location.href="chapterList?action=new&classid=<%=classid%>&lessonid=<%=lessonid%>&addtype=<%=addtype%>" value="��ӽ�">
		<INPUT type="button" onClick=javascript:formSubmit("update") value="�޸���">
		<INPUT type="button" onClick=javascript:formSubmit("delete") value="ɾ����">
		<%
		}
		else
		{
		%>
		<INPUT type="button" onClick=javascript:formSubmit("update") value="�޸Ľ�">
		<INPUT type="button" onClick=javascript:formSubmit("delete") value="ɾ����">
		<%
		}
		%>
		</TD>
	</TR>
</TABLE>
<%
if (!action.equals("add"))
{
%>
</FORM>
<FORM id="fileForm" name="fileForm" method="post" action="chapterList">
<HR>
<%=bcd.getLessonName()%>�ļ��б�
<%
   if (filesVector==null||filesVector.isEmpty())
   {
   	out.println("û���ļ���");
   }
   else
   {
%>
<TABLE>
	<TR>
		<TD>ѡ��</TD>
		<TD>�ļ�����</TD>
		<TD>�ļ�����</TD>
		<TD>�ļ���С</TD>
	</TR>
<%
   for (int i=0; i< count; i++)
   {
	java.io.File file = (java.io.File) filesVector.elementAt(i);
    java.util.Date fileTime = new java.util.Date(file.lastModified());
	java.lang.Long fileSize = new java.lang.Long(file.length());
	String fileName = file.getName();
%>
    <TR>
      <TD align="center">
	  <INPUT type="checkbox" name="select<%=i%>" value="<%= fileName %>"></TD>
      <TD align="left" bgcolor="#cccccc"><%= fileName %></TD>
      <TD align="left" bgcolor="#cccccc"><%= fileTime %></TD>
      <TD align="right" bgcolor="#cccccc"><%= fileSize %></TD>
    </TR>
<% } %>
	<TR>
	  <TD colspan="2" align="right"> 
		<INPUT type="hidden" name="action" id="action" value="showDetail">
		<INPUT type="hidden" name="classid" id="classid" value="<%=classid%>">
		<INPUT type="hidden" name="lessonid" id="lessonid" value="<%=lessonid%>">
        <INPUT type="hidden" name="option" id ="option" value="deletefiles">
        <INPUT type="hidden" name="countInPage" value="<%=count%>">
        <INPUT type="checkbox" name="check" value="" onClick="selectAll(this)">
        ѡ��ȫ�� </TD><TD colspan="2"> 
		<INPUT name="btndelete" type="button" id="btndetele" onClick=javascript:deleteFiles() value="ɾ���ļ�">
	</TD></TR>
</TABLE>
	<% 
	}
	%>
</FORM>
<FORM action="chapterList?action=showDetail&classid=<%=classid%>&lessonid=<%=lessonid%>&option=upload" id="uploadForm" name="uploadForm" method="post" enctype="multipart/form-data">
<HR>
  �ϴ��ļ��� 
  <TABLE>
    <TR>
      <TD>
        <INPUT name="OverWriteOption" type="checkbox" id="OverWriteOption" value="true">
        �ļ�����ʱ����</TD>
    </TR>
    <TR> 
      <TD align="center"> ѡ���ļ��� 
        <INPUT type=FILE name="file" size="40">
        
      </TD>
    </TR>
    <TD align="center"> ѡ���ļ��� 
      <INPUT type=FILE name="file" size="40">
      
    </TD>
    </TR>
    <TD align="center"> ѡ���ļ��� 
      <INPUT type=FILE name="file" size="40">
      
    </TD>
    </TR>
    <TR> 
      <TD align="center"> 
        <INPUT type="button" name="upload" onClick=javascript:uploadFiles() value="����">
      </TD>
  </TABLE>
<%
}
%>
</FORM>
</BODY>
</HTML>
		
		
