<%@ page contentType="text/html; charset=GB2312" %>

<html>
<script language = javascript>
var strna=""
function ok()
{

var str=""
//doucument.writeln("fafdfdf")
 //alert("hello!")
 b=document.form1.select2.value
//b=document.form1.select2.text

 str=b+"/"+strna


 window.returnValue=str
 window.close()
}
function cancel()
{

window.returnValue=""
window.close()

}
function setname()
{

  with(document.form1.select2)
	{
		for(loop_index=0;loop_index<length;loop_index++)
		{
			if (options[loop_index].selected)
			{
				strna=options[loop_index].text;
				break;
			}

		}

	}

}

</script>
<head>
<title>
test2
</title>
</head>
<body bgcolor="#ECECEC">
<%
String id = "";
id = request.getParameter("d");
if(id==null)
  out.println("this is null");
else
  out.println("this is id==="+id);


%>
<h1>

</h1>
<p><div align="center"><font face="�����п�" size='6'>ѡ��Ի�����</font></div></p>
<form name="form1" method="post" action="">
<br><br>
��Ʒ����:<select name=select2 onchange = "setname()">
  <option value="0">--��ѡ��--</option>

<option value="0">--��ѡ��--</option>
  <option value="1">book</option>
  <option value="2">bag</option>
</select>
<input type="button" name="gg" value="ȷ��" onclick="ok()">
&nbsp;&nbsp;&nbsp;&nbsp
<input type="button" name="can" value="ȡ��" onclick="cancel()">
</form>
</body>
</html>
