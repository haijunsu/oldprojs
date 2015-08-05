<%@ page contentType="text/html;charset=GB2312" language="java" %>

<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>

<html>

<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="11%">���⣺</td>
    <td width="89%"><bean:write name="dto" property="title"/></td>
  </tr>
  <tr>
    <td width="11%">���ӵ�ַ��</td>
    <td width="89%"><bean:write name="dto" property="link"/></td>
  </tr>
  <tr>
    <td width="11%">���ģ�</td>
    <td width="89%"><bean:write name="dto" property="description"/></td>
  </tr>
</table>
<hr>

<logic:iterate id="reply" name="dtos">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="11%">�ظ��ˣ�</td>
    <td width="89%">
		<a href="mailto:<bean:write name="reply" property="mail"/>">
			<bean:write name="reply" property="author"/>
		</a>
	</td>
  </tr>
  <tr>
    <td width="11%">�ظ����ģ�</td>
    <td width="89%"><bean:write name="reply" property="content"/></td>
  </tr>
  <tr>
	<td colspan="2">&nbsp;</td>
  <tr>
</table>
</logic:iterate>

<p><hr>
<form method="POST" action="post.do?method=reply&id=<bean:write name="dto" property="id"/>">
  <table border="0" width="100%" cellspacing="0" cellpadding="0">
    <tr>
      <td width="12%">�ظ��ˣ�</td>
      <td width="88%"><input type="text" name="author" size="30"></td>
    </tr>
    <tr>
      <td width="12%">E-Mail��</td>
      <td width="88%"><input type="text" name="mail" size="30"></td>
    </tr>
    <tr>
      <td width="12%">�ظ����ģ�</td>
      <td width="88%"><textarea rows="9" name="content" cols="46"></textarea></td>
    </tr>
  </table>
  <p>��</p>
  <p><input type="submit" value="�ύ" name="B1"><input type="reset" value="ȫ����д" name="B2"></p>
</form>

</html>