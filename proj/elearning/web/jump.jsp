<%@ page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>Untitled Document</title>
<link href="style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<form name="form1" method="post" action="savejump.jsp">
  <table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="100%">
    <tr bgcolor="#cccccc"> 
      <td height="20" colspan="2"><%=request.getParameter("year")%>年<%=request.getParameter("month")%>月<%=request.getParameter("date")%>日</td>
    </tr>
    <tr> 
      <td height="20" width="22%">事件标题:</td>
      <td height="20" width="78%"> 
        <input class="input1" type="text" name="title">
      </td>
    </tr>
    <tr> 
      <td height="20" width="22%">事件内容：</td>
      <td height="20" width="78%"> 
        <textarea name="content" cols="30" rows="5"></textarea>
      </td>
    </tr>
    <tr> 
      <td height="20" colspan="2">
        <input class="input2" type="submit" name="Submit" value="提交">
        <input class="input2" type="submit" name="Submit2" value="取消">
      </td>
    </tr>
  </table>
</form>
</body>
</html>
