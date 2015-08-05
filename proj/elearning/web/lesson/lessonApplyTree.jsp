<%@page contentType="text/html;charset=GB2312"%>
<HTML>
<HEAD>
<TITLE>Tree Pane</TITLE>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
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
String message = (String)request.getAttribute("message");
String applyType = (String)request.getAttribute("applyType");
String treePage = "lessonApplyList?applyType=" + applyType;
String oldopen = (String)session.getAttribute(applyType + "tree.oldopen");
String strFldoGif = request.getContextPath() + "/tree/fldo.gif";
String strFldcGif = request.getContextPath() + "/tree/fldc.gif";
String strRgnGif = request.getContextPath() + "/tree/rgn.gif";
String strAddsign = com.htyz.system.SystemProperties.getProperty("db.addsign");
String strSubstr = com.htyz.system.SystemProperties.getProperty("db.substr");
String sql = "SELECT code_id, code_value, code_namec, code_namee, 'lessonApplyList?applyType=" + applyType + "&action=showDetail&codeid='" + strAddsign + "rtrim(code_id)" + strAddsign + "'&codevalue='" + strAddsign + "rtrim(code_value) AS url FROM t_code WHERE code_id = '0013' OR code_id in (SELECT code_value FROM t_code WHERE code_id = '0013')";
if (codeid == null) codeid = "0013";
if (codevalue == null) codevalue = "9000";	
if (oldopen == null) oldopen = "0:";
if (flag == null) flag = "-1";
if (action == null) action = "showDetail";
if (message == null) message = "";
if (flag.equals("0"))
{
%>
<SCRIPT Language="JavaScript">
	parent.right.location.href = "<%=request.getContextPath()%>/servlet/lesson/lessonApplyList?applyType=<%=applyType%>&action=<%=action%>&codeid=<%=codeid%>&codevalue=<%=codevalue%>&message=<%=message%>";
</SCRIPT>
<%
}
%>

<jsp:useBean id="tree" scope="request" class="com.htyz.treeBeans.CSTreeBean" />

  <jsp:setProperty name="tree" property="treePage" value="<%=treePage%>" />
  <jsp:setProperty name="tree" property="leafTarget" value="right" />
  <jsp:setProperty name="tree" property="treeType" value="code" />
  <jsp:setProperty name="tree" property="sql" value="<%=sql%>" />
  <jsp:setProperty name="tree" property="treeIndex" value="code_id" />
  <jsp:setProperty name="tree" property="treeSubIndex" value="code_value" />
  <jsp:setProperty name="tree" property="labelCols" value="code_namec" />
  <jsp:setProperty name="tree" property="urlCols" value="url" />
  <jsp:setProperty name="tree" property="treeRoot" value="0013" />
  <jsp:setProperty name="tree" property="treeName" value="lessonApplyTree" />
  <jsp:setProperty name="tree" property="openImage" value="<%=strFldoGif%>"/>
  <jsp:setProperty name="tree" property="closedImage" value="<%=strFldcGif%>"/>
  <jsp:setProperty name="tree" property="leafImage" value="<%=strRgnGif%>"/>
  <jsp:setProperty name="tree" property="oldopen" value="<%=oldopen%>" />
  <jsp:setProperty name="tree" property="treeStyle" value="table003" />
  <jsp:setProperty name="tree" property="reload" value="true" />
  <jsp:setProperty name="tree" property="*" />

<BODY vlink="#0000ff" background="<%=request.getContextPath()%>/images/2.gif">
øŒ≥Ã∑÷¿‡
<BR>
<%= tree.renderHTML() %>
<%
	session.setAttribute(applyType + "tree.oldopen", tree.getOldopen());
%>
</BODY>
</HTML>
