<%@page contentType="text/html;charset=GB2312"%>
<HTML>

<HEAD>
<TITLE>µÇÂ¼Ò³Ãæ</TITLE>
<LINK rel="stylesheet" href="/elearning/3.css" type="text/css">
</HEAD>
<%
//½ûÖ¹Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<BODY>
<jsp:include page="Menus.jsp"/>
<%
String s_Msg;
s_Msg = (String)request.getAttribute("message");
session.setAttribute("userid", "eea");
if(s_Msg != null)
{
%>
<FONT color="red"><%=s_Msg%></FONT>
<%}%>
<FORM method="POST" action="/servlet/elearn.bean_UserLogin">
  <INPUT type="hidden" id="ver" name="ver" value="1">
  <TABLE border="0" width="100%">
    <TR>
    <TD width="100%" colspan="2" align="center">
      ÓÃ»§µÇÂ¼</TD>
  </TR>
  <TR>
    <TD width="50%" align="right">
      µÇÂ¼Ãû³Æ£º</TD>
    <TD width="50%">
        <INPUT class="input1" type="text" id="userid" name="userid" size="20" maxlength="20">
    </TD>
  </TR>
  <TR>
    <TD width="50%" align="right">
      ¿Ú&nbsp;&nbsp;&nbsp; Áî£º</TD>
    <TD width="50%">
        <INPUT class="input1" type="password" id="pass" name="pass" size="20" maxlength="20">
    </TD>
  </TR>
  <TR>
    <TD width="50%" align="right">
        <INPUT class="input2" type="submit" name="submitbtn" value="µÇÂ¼" >
    <TD width="50%">
        <A href="/elearn.bean_UserProfiles?action=apply">ÉêÇë</A>
    </TD>
  </TR>
</TABLE>
</FORM>
</BODY>
</HTML>
