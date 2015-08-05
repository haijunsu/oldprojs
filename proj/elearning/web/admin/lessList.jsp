<%@page contentType="text/html;charset=GB2312"%>
<HTML>
<HEAD>
<TITLE>Tree Pane</TITLE>
<link href="../style.css" rel="stylesheet" type="text/css">
<%
//½ûÖ¹Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
String strFldoGif = request.getContextPath() + "/tree/fldo.gif";
String strFldcGif = request.getContextPath() + "/tree/fldc.gif";
String strRgnGif = request.getContextPath() + "/tree/rgn.gif";
%>
</HEAD>
<%
String sql = "SELECT lesson_url as lesson_id FROM tlesson order by lesson_id";

%>
<jsp:useBean id="tree" scope="request" class="com.htyz.treeBeans.CSTreeBean" />

  <jsp:setProperty name="tree" property="treePage" value="lessList.jsp" />
  <jsp:setProperty name="tree" property="leafTarget" value="right" />
  <jsp:setProperty name="tree" property="treeType" value="separate" />
  <jsp:setProperty name="tree" property="sql" value="<%=sql%>" />
  <jsp:setProperty name="tree" property="treeIndex" value="lesson_id" />
  <jsp:setProperty name="tree" property="labelCols" value="lesson_id" />
  <jsp:setProperty name="tree" property="urlCols" value="lesson_id" />
  <jsp:setProperty name="tree" property="treeDepth" value="6" />
  <jsp:setProperty name="tree" property="interval" value="1" />
  <jsp:setProperty name="tree" property="treeName" value="lessonTree" />
  <jsp:setProperty name="tree" property="reload" value="true" />
  <jsp:setProperty name="tree" property="openImage" value="<%=strFldoGif%>"/>
  <jsp:setProperty name="tree" property="closedImage" value="<%=strFldcGif%>"/>
  <jsp:setProperty name="tree" property="leafImage" value="<%=strRgnGif%>"/>
  <jsp:setProperty name="tree" property="treeStyle" value="table003" />
  <jsp:setProperty name="tree" property="*" />

<BODY vlink="#0000ff">
´úÂëÊ÷
<BR>
<%= tree.renderHTML() %>
<%
	session.setAttribute("oldopen", tree.getOldopen());
%>
</BODY>
</HTML>
