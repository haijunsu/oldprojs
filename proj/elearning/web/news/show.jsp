<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<%@page import="com.htyz.*"%>
<jsp:useBean id="news" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="code" scope="request" class="com.htyz.beanGetdata"/>
<html>
<head>
<title>Untitled Document</title>
<link href="../style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<form name="form1" method="post" action="<%=request.getContextPath()%>/servlet/news/newsadd?action=update">
  <table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="100%">
    <%String s_checked1,s_checked2 ;
	
	news.executeSelect("select * from t_news where news_id='"+request.getParameter("newsid")+"'");
		
		s_checked1 = "checked";
		s_checked2 = "";
		if(news.getFieldValue("news_head",0).equals("0"))
		{
			s_checked1 = "";
			s_checked2 = "checked";
		}
		%>

    <tr> 
      <td height="20" width="12%">新闻标题：</td>
      <td height="20" width="88%"> 
        <input class="input1" type="text" name="title" size="50" value="<%=news.getFieldValue("news_title",0)%>">
        <input class="input1" type="hidden" name="newsid" value="<%=request.getParameter("newsid")%>">
      </td>
    </tr>
    <tr> 
      <td height="20" width="12%">新闻类别：</td>
      <td height="20" width="88%"> 
        <%code.executeSelect("select * from t_code where code_id='0008'");%>
        <select name="type">
          <%for (int i=0;i<code.getRowCount();i++)
		{
		%>
          <option value="<%=code.getFieldValue("code_value",i)%>" <%if (code.getFieldValue("code_value",i).equals(news.getFieldValue("news_type",0))){ out.print(" selected");}%>><%=code.getFieldValue("code_namec",i)%></option>
          <%}%>
        </select>
      </td>
    </tr>
    <tr> 
      <td height="20" width="12%">关键词：</td>
      <td height="20" width="88%"> 
        <input class="input1" type="text" name="key" size="50" value="<%=news.getFieldValue("news_key",0)%>">
      </td>
    </tr>
    <tr>
      <td height="20" width="12%">上传图片：</td>
      <td height="20" width="88%">
        <select name="file1" style="width=310" size="2" multiple>
		<option name="file1" value="<%=news.getFieldValue("news_img",0)%>" selected><%=news.getFieldValue("news_img",0)%><option>
        </select>
        <input type="button" name="Button" value="上传" onClick=window.open("upload/up.htm","_blank","top=0,left=0,width=400,height=200,status=no") enctype="multipart/form-data">
      </td>
    </tr>
    <tr> 
      <td height="20" width="12%">是否热点：</td>
      <td height="20" width="88%">
	  
        <input type="radio" name="head" <%=s_checked1%> value="1" >
        是
     
		<input type="radio" name="head" <%=s_checked2%>  value="0" >
	
        不是</td>
    </tr>
    <tr> 
      <td height="20" width="12%">内容：</td>
      <td height="20" width="88%"> 
        <textarea name="content" cols="50" rows="8"><%=news.getFieldValue("news_content",0)%></textarea>
      </td>
    </tr>
    <tr> 
      <td height="20" width="12%"> 更新人：</td>
      <td height="20" width="88%"> 
        <input class="input1" type="text" name="man" size="20" value="<%=news.getFieldValue("news_man",0)%>">
      </td>
    </tr>
	<%//news.execute("update t_news set news_count=news_count+1 where news_id='"+request.getParameter("newsid")+"'");%>
    <tr> 
      <td   height="20" colspan="2"> 
        <input type="submit" name="Submit" value="修改">
        <input type="submit" name="Submit2" value="取消">
      </td>
    </tr>
	
  </table>
</form>
</body>
</html>
