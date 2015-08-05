<%@page contentType="text/html;charset=GB2312"%>
<HTML>
<HEAD>
<TITLE>Tree Pane</TITLE>
<link rel="stylesheet" href="../3.css" type="text/css">
<%
//½ûÖ¹Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
</HEAD>

<jsp:useBean id="tree" scope="request" class="com.coolservlets.beans.CSTreeBean" />

  <jsp:setProperty name="tree" property="treePage" value="TreePane.jsp" />
  <jsp:setProperty name="tree" property="leafTarget" value="rhs" />
  <jsp:setProperty name="tree" property="*" />

<BODY vlink="#0000ff">
<%

String id=((String)session.getAttribute("class_id")).trim();
//System.out.println("");
//System.out.println("after read frop session:"+id);

//	tree.setClassId();

	tree.setClassId(id);

	tree.setReload(true);
%>
<BR>
<BR>
<TABLE>
  <TBODY>
    <TR>
      <TD><B><%=tree.getClassName(id)%></B></TD>
    </TR>
    <TR>
      <TD><%= tree.renderHTML()%></TD>
    </TR>
  </TBODY>
</TABLE>
</BODY>
</HTML>
