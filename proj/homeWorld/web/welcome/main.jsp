<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page 
language="java"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>
<%
//��ֹCache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<html:html locale="true">

<head>
	<meta name="GENERATOR" content="�պ���">
	<meta http-equiv="Content-Style-Type" content="text/css">
	<link href="<%=request.getContextPath()%>/theme/homeworld.css" rel="stylesheet" type="text/css">
	<script  src='<%=request.getContextPath()%>/commjs/homeworld.js'></script>
	<title>
	<bean:message key="logon.title"/>
	</title>
</head>
<body onmouseover="self.status='�����缯�Ź�����ҵ��ϵͳ http://vendor.thehomeworld.com';return true" 
	vLink=#333333 
	aLink=#333333 
	link=#333333 
	bgColor=#fefeff 
	leftMargin=0 
	background="" 
	topMargin=0>
<TABLE cellSpacing=0 cellPadding=1 width="97%" align=center border=0>
  <TBODY>
	  <TR>
	    <TD vAlign=top align=right width="15%">
		    <IMG src="<%=request.getContextPath()%>/img/announce.gif"> <FONT class=cau>���棺</FONT><BR></TD>
	    <TD vAlign=bottom width="40%">
	      <MARQUEE onmouseover=stop() onmouseout=start() scrollAmount=3>hello</MARQUEE></TD>
	    <TD vAlign=bottom align=middle width="40%">	    </TD>
	   </TR>
   </TBODY>
</TABLE>
<TABLE height=210 cellSpacing=0 cellPadding=0 width=771 align=center border=0>
  <TBODY>
  <TR>
    <TD vAlign=top align=middle width=740 height=210><IMG height=210 
      src="<%=request.getContextPath()%>/img/zhu.gif" width=740 border=1></TD></TR></TBODY></TABLE>
<TABLE height=65 cellSpacing=0 cellPadding=0 width=770 align=center 
background=<%=request.getContextPath()%>/img/di.gif border=0>
  <TBODY>
  <TR>
    <TD vAlign=center align=middle width="50%" height=65>
      <TABLE height="70%" cellSpacing=0 cellPadding=0 width="60%" align=center 
      border=0>
        <TBODY>
        <TR background="<%=request.getContextPath()%>/img/di.GIF">
          <TD vAlign=top width="100%"><FONT size=2><A 
            href="http://vendor.thehomeworld.com/?s=#">�� ����֪ͨ</A></FONT></TD></TR>
        <TR background="<%=request.getContextPath()%>/img/di.GIF">
          <TD vAlign=top><FONT size=2><A 
            href="http://vendor.thehomeworld.com/?s=#">�� 
            ���ڽ��ɵ��������Ա����ѵ�֪ͨ</A></FONT></TD></TR>
        <TR background="<%=request.getContextPath()%>/img/di.GIF">
          <TD vAlign=top><FONT size=2><A 
            href="http://vendor.thehomeworld.com/?s=#">�� 
        ��ѵ֪ͨ</A></FONT></TD></TR></TBODY></TABLE></TD>
    <TD vAlign=center align=middle width="50%" height=65>
      <TABLE height="70%" cellSpacing=0 cellPadding=0 width="60%" align=center 
      border=0>
        <TBODY>
        <TR background="<%=request.getContextPath()%>/img/di.GIF">
          <TD vAlign=top><FONT size=2><A 
            href="http://vendor.thehomeworld.com/?s=#">�� 
        �����������ȷ����</A></FONT></TD></TR>
        <TR background="<%=request.getContextPath()%>/img/di.GIF">
          <TD vAlign=top><FONT size=2><A 
            href="http://vendor.thehomeworld.com/?s=#">�� ���������Ա����ʺ�</A> 
          </FONT></TD></TR>
        <TR background="<%=request.getContextPath()%>/img/di.GIF">
          <TD vAlign=top height=13><FONT size=2><A 
            href="http://vendor.thehomeworld.com/?s=#">�� ��ϵ����</A> 
        </FONT></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE><!--footer begin --></TD></TR></TABLE>
</body>
</html:html>
