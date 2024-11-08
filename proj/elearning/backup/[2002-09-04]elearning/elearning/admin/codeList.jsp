<%@page contentType="text/html;charset=GBK"%>
<%@ page import="com.htyz.system.SystemProperties"%>
<HTML>
<HEAD>
<TITLE>Tree Pane</TITLE>
<link href="/elearning/style.css" rel="stylesheet" type="text/css">
<%
//��ֹCache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
</HEAD>
<%
String sql = "SELECT code_id, code_value, code_namec, code_namee, 'codeList?action=showDetail&codeid='"+ SystemProperties.getProperty("db.addsign")+" rtrim(code_id) "+ SystemProperties.getProperty("db.addsign")+" '&codevalue=' "+ SystemProperties.getProperty("db.addsign")+" code_value AS url FROM t_code";
String flag = (String)request.getAttribute("flag");
String codeid = (String)request.getAttribute("codeid");
String codevalue = (String)request.getAttribute("codevalue");
String action = (String)request.getAttribute("action");
String message = (String)request.getAttribute("message");
String oldopen = (String)session.getAttribute("oldopen");
String treeroot = (String)request.getAttribute("treeroot");
if (codeid == null) codeid = "0000";
if (codevalue == null) codevalue = "0001";	
if (treeroot == null) treeroot = "0000";
if (oldopen == null) oldopen = "0:";

if (flag.equals("0"))
{
%>
<SCRIPT Language="JavaScript">
	parent.right.location.href = "codeList?action=<%=action%>&codeid=<%=codeid%>&codevalue=<%=codevalue%>&message=<%=message%>";
</SCRIPT>
<%
}
%>
<jsp:useBean id="tree" scope="request" class="com.htyz.treeBeans.CSTreeBean" />

  <jsp:setProperty name="tree" property="treePage" value="codeList" />
  <jsp:setProperty name="tree" property="leafTarget" value="right" />
  <jsp:setProperty name="tree" property="treeType" value="code" />
  <jsp:setProperty name="tree" property="sql" value="<%=sql%>" />
  <jsp:setProperty name="tree" property="treeIndex" value="code_id" />
  <jsp:setProperty name="tree" property="treeSubIndex" value="code_value" />
  <jsp:setProperty name="tree" property="labelCols" value="code_namec" />
  <jsp:setProperty name="tree" property="urlCols" value="url" />
  <jsp:setProperty name="tree" property="openImage" value="/elearning/tree/fldo.gif" />
  <jsp:setProperty name="tree" property="closedImage" value="/elearning/tree/fldc.gif" />
  <jsp:setProperty name="tree" property="leafImage" value="/elearning/tree/rgn.gif" />
  <jsp:setProperty name="tree" property="treeRoot" value="<%=treeroot%>" />
  <jsp:setProperty name="tree" property="treeName" value="codeTree" />
  <jsp:setProperty name="tree" property="treeStyle" value="table003" />
  <jsp:setProperty name="tree" property="oldopen" value="<%=oldopen%>" />
  <jsp:setProperty name="tree" property="*" />

<BODY vlink="#0000ff">
������
<BR>
<%= tree.renderHTML() %>
<%
	session.setAttribute("oldopen", tree.getOldopen());
%>
</BODY>
</HTML>
