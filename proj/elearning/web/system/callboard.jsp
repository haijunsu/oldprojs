<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body>
<form name="form1" method="post" action="callboard">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td height="20">公告标题：</td>
      <td height="20"><input name="title" type="text" id="title"></td>
    </tr>
    <tr> 
      <td height="20">公告内容：</td>
      <td height="20"><textarea name="content" cols="30" rows="6" id="content"></textarea></td>
    </tr>
    <tr> 
      <td height="20" colspan="2"><input type="submit" name="Submit" value="确定">
        <input type="submit" name="Submit2" value="取消"></td>
    </tr>
  </table>
</form>
</body>
</html>
