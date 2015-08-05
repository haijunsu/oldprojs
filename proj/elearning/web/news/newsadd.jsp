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
alert("请填写新闻标题！！");
return;
}

if (document.form1.news_content.value.length==0){
alert("请填写新闻内容！");
return;
}
document.form1.submit();
}
-->
</script>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>
<body>
<form name="form1" method="post" action="<%=request.getContextPath()%>/servlet/news/newsadd?action=add">
  <table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="100%">
    <tr> 
      <td height="29">新闻标题：</td>
      <td height="29"> 
        <input  class="input1" name="news_title" type="text" size="50">
      </td>
    </tr>
    <tr> 
      <td height="20">新闻类别：</td>
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
      <td height="20">关键词：</td>
      <td height="20"> 
        <input class="input1" name="news_key" type="text" size="50">
      </td>
    </tr>
    <tr> 
      <td height="20">上传图片：</td>
      <td height="20"> 
        <select name="file1" style="width=310" size="1" multiple>
        </select>
        <input type="button" name="Button" value="上传" onClick=window.open("<%=request.getContextPath()%>/news/upload/up.htm","_blank","top=0,left=0,width=400,height=200,status=no") ENCTYPE="multipart/form-data">
      </td>
    </tr>
    <tr> 
      <td height="20">是否热点：</td>
      <td height="20"> 
        <input type="radio" name="head" value="1">
        是 
        <input type="radio" name="head" value="0" checked>
        不是 </td>
    </tr>
    <tr> 
      <td height="20">新闻内容：</td>
      <td height="20"> 
        <textarea name="news_content" cols="50" rows="7"></textarea>
      </td>
    </tr>
    <tr> 
      <td height="20">更新人：</td>
      <td height="20"> 
        <input class="input1" name="news_man" type="text" size="20" value="<%=session.getAttribute("userid")%>">
      </td>
    </tr>
    <tr> 
      <td height="20" colspan="2"> 
        <input class="input2" type="button" name="Submit" onClick="check()" value="确定">
        <input class="input2" type="submit" name="Submit2" value="取消">
      </td>
    </tr>
  </table>
</form>
</body>
</html>
