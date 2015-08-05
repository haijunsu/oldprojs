<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>Untitled Document</title>
<link rel="stylesheet" href="../style.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<jsp:useBean id="sqlbean" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="sqlbean1" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="beanElearnTools" scope="request" class="com.htyz.beanElearnTools"/>
</head>
<body bgcolor="#FFFFFF" text="#000000">
<% if (request.getParameter("search").equals("")){ %>
<script>
alert("请输入查询内容！！");
history.back();
</script>
<%}
else
{
String stm=request.getParameter("select");
int str=(new Integer(stm)).intValue();
switch (str)
{
 case 1:
 sqlbean.executeSelect("select * from t_class where class_name like '%"+request.getParameter("search")+"%' and  apply_status='0'");
 //sqlbean.executeSelect("select * from t_user_apply where class_id='"+sqlbean.getFieldVaule("class_id",0)+"' and apply_status='0' ");
  break;
  case 2:
  sqlbean.executeSelect("select * from t_user_apply where user_id='"+request.getParameter("search")+"' and apply_status='0'");
  break;
  }
  %>
<br>
<form name="form1" method="post" action="savesearch.jsp">
  <table width="100%" border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#6699cc" bordercolordark="#FFFFFF" class="xbz">
    <tr bgcolor="#F0F4FB">
	<td height="20" colspan="5">查询的结果： 
        <%if (sqlbean.getRowCount()==0)
  {
  out.print("暂无纪录！！");
  } 
  else{
  
  %>
      </td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
      <td height="20">申请人</td>
      <td height="20">申请课程</td>
      <td height="20">申请时间</td>
      <td height="20">申请原因</td>
    </tr>
<%for (int i=0;i<sqlbean.getRowCount();i++)
  {%>
    <tr bgcolor="#FFFFFF"> 

      <td height="20"> 
        <input type="checkbox" name="checkbox" value="<%=sqlbean.getFieldValue("apply_id",i)%>">
        <%=sqlbean.getFieldValue("user_id",i)%> </td>
      <td height="20"><%=sqlbean.getFieldValue("class_id",i)%></td>
      <td height="20"><%=beanElearnTools.FormatDate(sqlbean.getFieldValue("apply_time",i))%></td>
      <td height="20"><%=sqlbean.getFieldValue("apply_reason",i)%></td>
    </tr>
    <input type="hidden" name="name" value="<%=sqlbean.getFieldValue("user_id",i)%>"> 
	<input type="hidden" name="classcode" value="<%=sqlbean.getFieldValue("class_id",i)%>">
	  <%}%>
    <tr bgcolor="#FFFFFF"> 
	<td height="18" colspan="4"> 
        <input class="input2" type="submit" name="Submit" value="批准">
		</td>
    </tr>
	</table>
</form>
<%}
%>
</body>
</html>
<%}%>
