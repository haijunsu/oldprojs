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
      <td height="20" colspan="2"><%=request.getParameter("year")%>��<%=request.getParameter("month")%>��<%=request.getParameter("date")%>��</td>
    </tr>
    <tr> 
      <td height="20" width="22%">�¼�����:</td>
      <td height="20" width="78%"> 
        <input class="input1" type="text" name="title">
      </td>
    </tr>
    <tr> 
      <td height="20" width="22%">�¼����ݣ�</td>
      <td height="20" width="78%"> 
        <textarea name="content" cols="30" rows="5"></textarea>
      </td>
    </tr>
    <tr> 
      <td height="20" colspan="2">
        <input class="input2" type="submit" name="Submit" value="�ύ">
        <input class="input2" type="submit" name="Submit2" value="ȡ��">
      </td>
    </tr>
  </table>
</form>
</body>
</html>
