<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<LINK rel="stylesheet" href="style.css" type="text/css">
</head>
<%
//��ֹCache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<body bgcolor="#FFFFFF" text="#000000">
<form name="form1" method="post" action="beanUserProfiles?action=add">
  <table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="90%">
    <tr> 
      <td height="20" width="17%">�û�id��</td>
      <td height="20" width="83%"> 
        <input type="text" name="userid" size="20">
      </td>
    </tr>
    <tr> 
      <td height="20" width="17%">������</td>
      <td height="20" width="83%"> 
        <input type="text" name="username" size="20">
      </td>
    </tr>
    <tr> 
      <td height="20" width="17%">���룺</td>
      <td height="20" width="83%"> 
        <input type="password" name="pass" size="15">
      </td>
    </tr>
    <tr> 
      <td height="20" width="17%">ȷ�����룺</td>
      <td height="20" width="83%"> 
        <input type="password" name="repass" size="15">
      </td>
    </tr>
    <tr> 
      <td height="20" width="17%">�������ڣ�</td>
      <td height="20" width="83%"> 
        <input type="text" name="birthday" size="40">
        ��ʽ��yyyy-mm-dd</td>
    </tr>
    <tr> 
      <td height="20" width="17%">�ձ�</td>
      <td height="20" width="83%"> 
        <input type="radio" name="sex" value="1">
        �� 
        <input type="radio" name="sex" value="2">
        Ů</td>
    </tr>
    <tr> 
      <td height="20" width="17%">�ʼ���ַ��</td>
      <td height="20" width="83%"> 
        <input type="text" name="email" size="60" value="">
      </td>
    </tr>
    <tr> 
      <td height="20" width="17%">�绰��</td>
      <td height="20" width="83%"> 
        <input type="text" name="phone" size="60">
      </td>
    </tr>
    <tr> 
      <td height="20" width="17%">��λ��</td>
      <td height="20" width="83%"> 
        <input type="text" name="company" size="60">
      </td>
    </tr>
    <tr> 
      <td height="20" width="17%">������</td>
      <td height="20" width="83%"> 
        <input type="text" name="area" size="60">
      </td>
    </tr>
    <tr> 
      <td height="20" width="17%">ǩ����</td>
      <td height="20" width="83%"> 
        <textarea name="signature" cols="60" rows="4"></textarea>
      </td>
    </tr>
    <tr> 
      <td height="20" colspan="2">
        <input type="submit" name="Submit" value="�ύ">
        <input type="submit" name="Submit2" value="��д">
      </td>
    </tr>
  </table>
</form>
</body>
</html>
