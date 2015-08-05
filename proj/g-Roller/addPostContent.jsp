<%@ page contentType="text/html;charset=GB2312" language="java" %>

<html>

<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>New Page 1</title>
</head>

<body>

<form method="POST" action="post.do?method=publish">
  <table border="0" width="60%" cellspacing="0" cellpadding="0">
    <tr>
      <td width="50%">标题：</td>
      <td width="50%"><input type="text" name="title" size="56"></td>
    </tr>
    <tr>
      <td width="50%">链接：</td>
      <td width="50%"><input type="text" name="link" size="56"></td>
    </tr>
    <tr>
      <td width="50%">正文：</td>
      <td width="50%"><textarea rows="9" name="description" cols="54"></textarea></td>
    </tr>
    <tr>
      <td width="50%"></td>
      <td width="50%"></td>
    </tr>
  </table>
  <p>　</p>
  <p><input type="submit" value="提交" name="B1"><input type="reset" value="全部重写" name="B2"></p>
</form>

</body>

</html>
