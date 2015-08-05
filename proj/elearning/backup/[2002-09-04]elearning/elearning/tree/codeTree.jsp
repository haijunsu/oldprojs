<%@page contentType="text/html;charset=GBK"%>
<HTML>
<HEAD>
<TITLE>Tree Pane</TITLE>
<link href="/elearning/style.css" rel="stylesheet" type="text/css">
<%
//Ω˚÷πCache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
</HEAD>
<%
String flag = (String)request.getAttribute("flag");
String codeid = (String)request.getAttribute("codeid");
String codevalue = (String)request.getAttribute("codevalue");
String action = (String)request.getAttribute("action");
String treename = (String)request.getAttribute("treename");
String treepage = (String)request.getAttribute("treepage");
String sql = (String)request.getAttribute("sql");
String message = (String)request.getAttribute("message");
String treeroot = (String)request.getAttribute("treeroot");
String oldopen = (String)session.getAttribute(treename + ".oldopen");
if (oldopen == null) oldopen = "0:";
if (flag == null) flag = "-1";
if (action == null) action = "list";
if (message == null) message = "";
if (flag.equals("0"))
{
%>
<SCRIPT Language="JavaScript">
	parent.right.location.href = "topicList?action=<%=action%>&codeid=<%=codeid%>&codevalue=<%=codevalue%>&message=<%=message%>";
</SCRIPT>
<%
}
%>

<jsp:useBean id="tree" scope="request" class="com.htyz.treeBeans.CSTreeBean" />

  <jsp:setProperty name="tree" property="treePage" value="<%=treepage%>" />
  <jsp:setProperty name="tree" property="leafTarget" value="right" />
  <jsp:setProperty name="tree" property="treeType" value="code" />
  <jsp:setProperty name="tree" property="sql" value="<%=sql%>" />
  <jsp:setProperty name="tree" property="treeIndex" value="code_id" />
  <jsp:setProperty name="tree" property="treeSubIndex" value="code_value" />
  <jsp:setProperty name="tree" property="labelCols" value="code_namec" />
  <jsp:setProperty name="tree" property="urlCols" value="url" />
  <jsp:setProperty name="tree" property="treeRoot" value="<%=treeroot%>" />
  <jsp:setProperty name="tree" property="treeName" value="<%=treename%>" />
  <jsp:setProperty name="tree" property="openImage" value="/elearning/tree/fldo.gif" />
  <jsp:setProperty name="tree" property="closedImage" value="/elearning/tree/fldc.gif" />
  <jsp:setProperty name="tree" property="leafImage" value="/elearning/tree/rgn.gif" />
  <jsp:setProperty name="tree" property="oldopen" value="<%=oldopen%>" />
  <jsp:setProperty name="tree" property="treeStyle" value="table003" />
  <jsp:setProperty name="tree" property="*" />

<BODY vlink="#0000ff">
øŒ≥Ã∑÷¿‡
<BR>
<%= tree.renderHTML() %>
<%
	session.setAttribute(treename + ".oldopen", tree.getOldopen());
%>
</BODY>
</HTML>
