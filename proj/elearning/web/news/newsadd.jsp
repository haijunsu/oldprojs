<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<%@page import="com.htyz.*"%>
<jsp:useBean id="news" scope="request" class="com.htyz.beanGetdata"/>
<html>
<head>
<title>Untitled Document</title>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<script language="javascript">
function check(){
if (document.form1.news_title.value.length==0){
alert("����д���ű��⣡��");
return;
}

if (document.form1.news_content.value.length==0){
alert("����д�������ݣ�");
return;
}
document.form1.submit();
}
-->
</script>
<%
//��ֹCache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<body>
<form name="form1" method="post" action="<%=request.getContextPath()%>/servlet/news/newsadd?action=add">
  <table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="100%">
    <tr> 
      <td height="29">���ű��⣺</td>
      <td height="29"> 
        <input  class="input1" name="news_title" type="text" size="50">
      </td>
    </tr>
    <tr> 
      <td height="20">�������</td>
      <td height="20"> 
        <%news.executeSelect("select * from t_code where code_id='0008'");%>
        <select name="news_type">
          <%for (int i=0;i<news.getRowCount();i++)
		{
		%>
          <option value="<%=news.getFieldValue("code_value",i)%>"><%=news.getFieldValue("code_namec",i)%></option>
          <%}%>
        </select>
      </td>
    </tr>
    <tr> 
      <td height="20">�ؼ��ʣ�</td>
      <td height="20"> 
        <input class="input1" name="news_key" type="text" size="50">
      </td>
    </tr>
    <tr> 
      <td height="20">�ϴ�ͼƬ��</td>
      <td height="20"> 
        <select name="file1" style="width=310" size="1" multiple>
        </select>
        <input type="button" name="Button" value="�ϴ�" onClick=window.open("<%=request.getContextPath()%>/news/upload/up.htm","_blank","top=0,left=0,width=400,height=200,status=no") ENCTYPE="multipart/form-data">
      </td>
    </tr>
    <tr> 
      <td height="20">�Ƿ��ȵ㣺</td>
      <td height="20"> 
        <input type="radio" name="head" value="1">
        �� 
        <input type="radio" name="head" value="0" checked>
        ���� </td>
    </tr>
    <tr> 
      <td height="20">�������ݣ�</td>
      <td height="20"> 
        <textarea name="news_content" cols="50" rows="7"></textarea>
      </td>
    </tr>
    <tr> 
      <td height="20">�����ˣ�</td>
      <td height="20"> 
        <input class="input1" name="news_man" type="text" size="20" value="<%=session.getAttribute("userid")%>">
      </td>
    </tr>
    <tr> 
      <td height="20" colspan="2"> 
        <input class="input2" type="button" name="Submit" onClick="check()" value="ȷ��">
        <input class="input2" type="submit" name="Submit2" value="ȡ��">
      </td>
    </tr>
  </table>
</form>
</body>
</html>
