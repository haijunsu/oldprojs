<%@ page contentType="text/html; charset=GBK" %>
<html>
<script language = javascript>
function msg()
{
var str,code,name,len
 //alert("hello!")
 b=document.form1.select1.value
 //document.writeln("id===="+b)
  //document.form1.name.value=window.showModalDialog("test2.jsp?d="+b+"")
 str=window.showModalDialog("test2.jsp?d="+b+"")
	 return
 if(str.length!=0)
 {
    len=str.indexOf("/")
    code = str.substring(0,len)
    name= str.substring(len+1)
    document.form1.id.value=code
    document.form1.name.value=name
 }
 else
 {
    document.form1.id.value=""
    document.form1.name.value=""
 }
  //document.writeln("return value===="+c)
}

</script>
<head>
<title>

</title>
</head>
<body bgcolor="#ECECEC">
<%
String s=new String();
s="0000001";
out.print(s);
%>
<h1>

</h1>
<p><div align="center"><font face="�����п�" size='6'>�칫��Ʒ�Ǽ�</font></div></p>
<form name="form1" method="post">
 ����:&nbsp<select name=select1>
<option value="001">ֽ��</option>
<option value="002">��</option>
<option value="002">�Ĳ�</option>
</select>
<br><br>
 ����:<input type="text" name="id" value="" readonly>
 <input type="button" name="ok" value="..." onclick="msg()">
 <p>
 ����:<input type="text" name="name" value="" readonly>
<p>

</form>
</body>
</html>
