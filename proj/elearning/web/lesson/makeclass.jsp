<%@page contentType="text/html;charset=GB2312"%>
<%@page language="java" import="com.htyz.*"%>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../xbz.css" type="text/css">
</head>
<jsp:useBean id="sqlbean" scope="request" class="com.htyz.beanGetdata"/>
<body bgcolor="#FFFFFF" text="#000000">
<form name="form1" method="post" action="savemake.jsp">
  <table width="100%" border="0" cellspacing="0" cellpadding="1">
    <tr> 
      <td> 
        <table width="100%" border="0" cellspacing="1" cellpadding="1" class="xbz">
          <tr bgcolor="#cccccc"> 
            <td height="20" colspan="2">章节的制作：</td>
          </tr>
          <tr bgcolor="#eeeeee"> 
            <td height="20">章节名称：</td>
            <td height="20"> 章节序号： 
              <input type="text" name="name22" size="2" maxlength="50">
              　章节名称： 
              <input type="text" name="name" size="15" maxlength="50">
              选择章： 
              <select name="select">
                <option  value="" selected>添加新章</option>
                <%sqlbean.executeSelect("select * from tlesson");
	for (int i=0; i<sqlbean.getRowCount(); i++)
	{
	%>
                <option value="<%=sqlbean.getFieldValue("lesson_id",i)%>"><%=sqlbean.getFieldValue("lesson_name",i)%></option>
                <%}%>
              </select>
            </td>
          </tr>
          <tr bgcolor="#eeeeee"> 
            <td height="20" bgcolor="#eeeeee">章节内容：</td>
            <td height="20"> 
              <textarea name="content" cols="60" rows="10"></textarea>
            </td>
          </tr>
          <tr bgcolor="#eeeeee"> 
            <td height="20"> 
              <input type="submit" name="Submit22" value="添加 ">
            </td>
            <td height="20">&nbsp; </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
