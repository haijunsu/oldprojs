<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<LINK rel="stylesheet" href="style.css" type="text/css">
</head>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<body bgcolor="#FFFFFF" text="#000000">
<form name="form1" method="post" action="beanUserProfiles?action=add">
  <table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="90%">
    <tr> 
      <td height="20" width="17%">用户id：</td>
      <td height="20" width="83%"> 
        <input type="text" name="userid" size="20">
      </td>
    </tr>
    <tr> 
      <td height="20" width="17%">姓名：</td>
      <td height="20" width="83%"> 
        <input type="text" name="username" size="20">
      </td>
    </tr>
    <tr> 
      <td height="20" width="17%">密码：</td>
      <td height="20" width="83%"> 
        <input type="password" name="pass" size="15">
      </td>
    </tr>
    <tr> 
      <td height="20" width="17%">确认密码：</td>
      <td height="20" width="83%"> 
        <input type="password" name="repass" size="15">
      </td>
    </tr>
    <tr> 
      <td height="20" width="17%">出生日期：</td>
      <td height="20" width="83%"> 
        <input type="text" name="birthday" size="40">
        格式：yyyy-mm-dd</td>
    </tr>
    <tr> 
      <td height="20" width="17%">姓别：</td>
      <td height="20" width="83%"> 
        <input type="radio" name="sex" value="1">
        男 
        <input type="radio" name="sex" value="2">
        女</td>
    </tr>
    <tr> 
      <td height="20" width="17%">邮件地址：</td>
      <td height="20" width="83%"> 
        <input type="text" name="email" size="60" value="">
      </td>
    </tr>
    <tr> 
      <td height="20" width="17%">电话：</td>
      <td height="20" width="83%"> 
        <input type="text" name="phone" size="60">
      </td>
    </tr>
    <tr> 
      <td height="20" width="17%">单位：</td>
      <td height="20" width="83%"> 
        <input type="text" name="company" size="60">
      </td>
    </tr>
    <tr> 
      <td height="20" width="17%">地区：</td>
      <td height="20" width="83%"> 
        <input type="text" name="area" size="60">
      </td>
    </tr>
    <tr> 
      <td height="20" width="17%">签名：</td>
      <td height="20" width="83%"> 
        <textarea name="signature" cols="60" rows="4"></textarea>
      </td>
    </tr>
    <tr> 
      <td height="20" colspan="2">
        <input type="submit" name="Submit" value="提交">
        <input type="submit" name="Submit2" value="重写">
      </td>
    </tr>
  </table>
</form>
</body>
</html>
