<link rel="stylesheet" href="<%=request.getContextPath()%>/3.css" type="text/css">
<%@page session="true"%>

<%if (session.getAttribute("userid")==null||session.getAttribute("userid").equals("")){%>
<script>
alert("连接超时");
top.location="<%=request.getContextPath()%>/login.jsp";
</script>
<%}
else{%>
<%@ page contentType="text/html; charset=gb2312"%>
<form name="form1" method="post" action="multisendmail.jsp">
  <table table border="1" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="100%" bgcolor="#FFFFFF">
    <tr> 
      <td width="9%">收件人</td>
      <td width="91%"> 
        <input class="input1" type="text" name="to" size="50">
      </td>
    </tr>
    <tr> 
      <td width="9%">发件人</td>
      <td width="91%"> 
        <input class="input1" type="text" name="from" size="50">
      </td>
    </tr>
    <tr> 
      <td width="9%">抄送</td>
      <td width="91%"> 
        <input class="input1" type="text" name="cc" size="50">
      </td>
    </tr>
    <tr> 
      <td width="9%">暗送</td>
      <td width="91%"> 
        <input class="input1" type="text" name="bcc" size="50">
      </td>
    </tr>
    <tr> 
      <td width="9%">主题</td>
      <td width="91%"> 
        <input class="input1" type="text" name="subject" size="50">
      </td>
    </tr>
    <tr> 
      <td width="9%">邮件内容</td>
      <td width="91%"> 
        <textarea class="testarea1" name="text" rows="10" cols="80"></textarea>
      </td>
    </tr>
    <tr align=center> 
      <td colspan=2> 
        <input type="submit" name="Submit" value="发送">
        <input type="reset" name="Reset" value="取消">
      </td>
    </tr>
  </table>
  </form>
<%}%>
