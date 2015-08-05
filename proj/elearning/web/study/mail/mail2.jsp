<%@ page contentType="text/html; charset=gb2312"%>
<html>
<head>
<title>邮件</title>
<link rel="stylesheet" href="file:///ma.css" type="text/css">
</head>
<!-- 全选 -->
<script language="javascript">
function selectall(){
  for (var i=0;i<document.forms["form2"].elements.length;i++)
    {
    var e=document.forms["form2"].elements[i];
    if (e.name=="check")
      e.checked=document.forms["form2"].allbox.checked;
    }
}

function addmail(){
	var e=document.forms["form2"];
	var sss="";
	for(i=0;i<e.elements.length;i++)
		if(e.elements[i].checked){sss=sss+e.elements[i].value+",";}
	document.form1.to.value=document.form1.to.value+sss;
}
</script>

<jsp:useBean id="list" scope="session" class="com.elearning.ListCheck" />
  <jsp:setProperty name="list" property="jdbcDriver" value="sun.jdbc.odbc.JdbcOdbcDriver" />
  <jsp:setProperty name="list" property="jdbcURL" value="jdbc:odbc:ma" />
  <jsp:setProperty name="list" property="jdbcUser" value="sa" />
  <jsp:setProperty name="list" property="jdbcPassword" value="" />

  <jsp:setProperty name="list" property="tableName" value="tuser" />
  <jsp:setProperty name="list" property="labelCols" value="user_name,sex,Email,Company,Area,user_id,Email as aEmail" />
  <jsp:setProperty name="list" property="titleCols" value="姓名,性别,邮件地址,公司,地区" />
  <jsp:setProperty name="list" property="urlCol" value="1" />
  <jsp:setProperty name="list" property="goUrl" value="TreeFrames.jsp" />
  <jsp:setProperty name="list" property="orderStyle" value="Order BY user_id" />
  <jsp:setProperty name="list" property="tableStyle" value="table1" />

  <jsp:setProperty name="list" property="listPage" value="mail.jsp" />
  <jsp:setProperty name="list" property="leafTarget" value="rhs" />
  <jsp:setProperty name="list" property="rec_per" value="2" />
  
  <jsp:setProperty name="list" property="*" />

<BODY vlink="#0000ff">
<form name="form1" method="post" action="file:///multisendmail.jsp">
  <table width="100%" border="1" cellspacing="0" cellpadding="0">
  <tr align=center><td colspan=2>邮件</td></tr>
    <tr> 
      <td width="9%">收件人</td>
      <td width="91%"> 
        <input type="text" name="to" size="50">
      </td>
    </tr>
    <tr> 
      <td width="9%">发件人</td>
      <td width="91%"> 
        <input type="text" name="from" size="50">
      </td>
    </tr>
    <tr> 
      <td width="9%">抄送</td>
      <td width="91%"> 
        <input type="text" name="cc" size="50">
      </td>
    </tr>
    <tr> 
      <td width="9%">暗送</td>
      <td width="91%"> 
        <input type="text" name="bcc" size="50">
      </td>
    </tr>
    <tr> 
      <td width="9%">主题</td>
      <td width="91%"> 
        <input type="text" name="subject" size="50">
      </td>
    </tr>
    <tr> 
      <td width="9%">邮件内容</td>
      <td width="91%"> 
        <textarea name="text" rows="10" cols="80"></textarea>
      </td>
    </tr>
    <tr align=center>
      <td colspan=2>
        <input type="reset" name="Reset" value="取消">
        <input type="submit" name="Submit" value="发送">
      </td>
    </tr>
  </table>
  </form>
</body>
</html>
