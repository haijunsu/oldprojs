<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>登录页面</title>

<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="log.css" rel="stylesheet" type="text/css">
</head>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<body bgcolor="#eeeeee">
<%
String s_Msg;
s_Msg = (String)request.getAttribute("message");
if(s_Msg != null)
{
%>
<Font color="red"><%=s_Msg%></FONT> 
<%}%>
<form method="POST" action="/servlet/beanUserLogin">
  <input type="hidden" id="ver" name="ver" value="1">
  <table width="530" height="419" border="0" align="center" background="images/bj1.gif">
    <tr> 
      <td height="200" colspan="2" align="center">&nbsp; </td>
    </tr>
    <tr> 
      <td width="60%" align="right">&nbsp;</td>
      <td width="40%">&nbsp; </td>
    </tr>
    <tr> 
      <td width="60%" align="right">&nbsp; </td>
      <td width="40%">&nbsp; </td>
    </tr>
    <tr> 
      <td width="60%" align="right"><div align="left"><br>
          <font color="#000000"> </font> <br>
          <font color="#000000"> </font> </div>
      <td width="40%" rowspan="2">&nbsp; </td>
    </tr>
    <tr> 
      <td height="120" align="right"><font color="#000000">用户名：</font> 
          <input class="input1" type="text" id="userid3" name="userid" size="8" maxlength="20">
          <font color="#000000">
          口令：</font> <font color="#000000"> 
          <input class="input1" type="password" id="pass4" name="pass" size="8" maxlength="20">
          <input name="image" type="image" src="/elearning/images/btn_1.gif" alt="登陆" align="middle">
          <img src="/elearning/images/btn_2.gif" alt="注册" border="0" align="absmiddle" onClick=window.open("servlet/elearn.bean_UserProfiles?action=apply","_blank","top=0,left=0,width=400,height=450,status=no")> 
          </font></tr>
  </table>
</form>
</body>
</html>