<%@page contentType="text/html;charset=GBK"%>
<%@ page import="com.htyz.system.SystemProperties"%>

<HTML>
<HEAD>
<TITLE>Tree Pane</TITLE>
<link href="/elearning/style.css" rel="stylesheet" type="text/css">
<%
//½ûÖ¹Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
</HEAD>
<%
String flag = (String)request.getAttribute("flag");
String classid = (String)request.getAttribute("classid");
String classname = (String)request.getAttribute("classname");
String action = (String)request.getAttribute("action");
String message = (String)request.getAttribute("message");
String oldopen = (String)session.getAttribute("chapter.oldopen");
if (classid == null) classid = "90000001000001";
if (oldopen == null) oldopen = "0:";
if (flag == null) flag="-1";
String sql = "SELECT lesson_id, lesson_name, 'chapterList?action=showDetail&lessonid=' "+ SystemProperties.getProperty("db.addsign")+" lesson_id "+ SystemProperties.getProperty("db.addsign")+"'&classid=' "+ SystemProperties.getProperty("db.addsign")+" class_id  AS url FROM t_lesson WHERE class_id = '" + classid + "' ORDER BY lesson_id";

if (flag.equals("0"))
{
%>
<SCRIPT Language="JavaScript">
	parent.right.location.href = "chapterList?action=<%=action%>&message=<%=message%>&classid=<%=classid%>";
</SCRIPT>
<%
}
%>
<jsp:useBean id="tree" scope="request" class="com.htyz.treeBeans.CSTreeBean" />

  <jsp:setProperty name="tree" property="treePage" value="chapterList" />
  <jsp:setProperty name="tree" property="leafTarget" value="right" />
  <jsp:setProperty name="tree" property="treeType" value="separate" />
  <jsp:setProperty name="tree" property="sql" value="<%=sql%>" />
  <jsp:setProperty name="tree" property="treeIndex" value="lesson_id" />
  <jsp:setProperty name="tree" property="labelCols" value="lesson_name" />
  <jsp:setProperty name="tree" property="urlCols" value="url" />
  <jsp:setProperty name="tree" property="treeDepth" value="2" />
  <jsp:setProperty name="tree" property="interval" value="3" />
  <jsp:setProperty name="tree" property="treeName" value="chapterTree" />
  <jsp:setProperty name="tree" property="openImage" value="/elearning/tree/fldo.gif" />
  <jsp:setProperty name="tree" property="closedImage" value="/elearning/tree/fldc.gif" />
  <jsp:setProperty name="tree" property="leafImage" value="/elearning/tree/rgn.gif" />
  <jsp:setProperty name="tree" property="oldopen" value="<%=oldopen%>" />
    <jsp:setProperty name="tree" property="treeStyle" value="table003" />
  <jsp:setProperty name="tree" property="*" />

<BODY vlink="#0000ff">
<A href="chapterList?action=showDetail&classid=<%=classid%>" target=right><%=classname%></A>
<BR>
<BR>
<TABLE>
  <TBODY>
    <TR>
      <TD><%= tree.renderHTML() %>
      <%
      	session.setAttribute("chapter.oldopen", tree.getOldopen());
      %>
      </TD>
    </TR>
  </TBODY>
</TABLE>
</BODY>
</HTML>
