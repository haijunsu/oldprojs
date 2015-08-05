<%@page contentType="text/html;charset=GB2312"%>
<HTML>
<HEAD>
<TITLE>课程树</TITLE>
<link href="/elearning/style.css" rel="stylesheet" type="text/css">
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
</HEAD>
<BODY vlink="#0000ff">
<jsp:useBean id="tree" scope="request" class="com.htyz.treeBeans.CSTreeBean" />

  <jsp:setProperty name="tree" property="treePage" value="lessonCatalog.jsp" />
  <jsp:setProperty name="tree" property="leafTarget" value="rhs" />
  <jsp:setProperty name="tree" property="treeType" value="separate" />
  <jsp:setProperty name="tree" property="treeIndex" value="lesson_id" />
  <jsp:setProperty name="tree" property="labelCols" value="lesson_name" />
  <jsp:setProperty name="tree" property="urlCols" value="lesson_url" />
  <jsp:setProperty name="tree" property="treeDepth" value="2" />
  <jsp:setProperty name="tree" property="interval" value="3" />
  <jsp:setProperty name="tree" property="treeName" value="lessonCatalog" />
  <jsp:setProperty name="tree" property="openImage" value="../tree/fldo.gif" />
  <jsp:setProperty name="tree" property="treeStyle" value="table003" />
  <jsp:setProperty name="tree" property="closedImage" value="../tree/fldc.gif" />
  <jsp:setProperty name="tree" property="leafImage" value="../tree/rgn.gif" />
  <jsp:setProperty name="tree" property="*" />
<%
String classid = request.getParameter("classid");
String open = request.getParameter("open");
String strAddsign = com.htyz.system.SystemProperties.getProperty("db.addsign");
String strSubstr = com.htyz.system.SystemProperties.getProperty("db.substr");

if (open == null && classid == null)
{
	out.println("<FONT color=red>错误：<p>没有指定课程</FONT>");
}
else
{
	String sql;
	if (!(classid==null))
	{
		sql = "SELECT lesson_id, lesson_name, 'read.jsp?classid=" + classid + "&lessonid='" + strAddsign + "lesson_id" + strAddsign + "'&url='" + strAddsign + "lesson_url AS lesson_url FROM t_lesson WHERE class_id = '" + classid + "' ORDER BY lesson_id";
		tree.setSql(sql);
		session.setAttribute("lessonCatalog.classid", classid);
		tree.setReload(true);
	}
	classid = (String) session.getAttribute("lessonCatalog.classid");
	sql = "SELECT class_name, 'lessonInfo.jsp?classid=" + classid + " target=right' AS URL FROM t_class WHERE class_id = '" + classid + "'";
	tree.setTreeTitle(sql);

%>
<%=tree.getTreeTitle()%>
<BR>
<BR>
 <%= tree.renderHTML() %>

<% } %>
</BODY>
</HTML>
