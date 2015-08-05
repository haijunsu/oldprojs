<%@page contentType="text/html;charset=GB2312"%>
<%@page language="java" import="com.htyz.*"%>
<html>
<head>
<jsp:useBean id="sqlbean" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="sqlbean1" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="ust" scope="session" class="system.userStyle"/>
<jsp:useBean id="beanElearnTools" scope="request" class="com.htyz.beanElearnTools"/>
<title>课程申请批准</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../style.css" type="text/css">
</head>
<script language="javascript">
function shan(){
if (confirm("您真的要删除选定的信息吗？")){
     document.form1.submit();
  }
  }

function CA(){
  for (var i=0;i<document.forms[1].elements.length;i++)
    {
    var e=document.forms[1].elements[i];
    if (e.name!="allbox")
      e.checked=document.forms[1].allbox.checked;
    }
}
//-->
</script>

<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>

<%String userid=(String)session.getAttribute("userid");
if(userid==null||userid==""){
out.print("您还没有登录，点击<a href='../login.jsp'>这里</a>登陆！");
}
else{
%>

<body bgcolor="#FFFFFF" text="#000000">
<form name="form2" method="post" action="applysearch.jsp">
  <input class="input1" type="text" name="search" size="15">
  <select class="testarea1" name="select">
    <option value=1 selected>按课程</option>
    <option value=2>按用户</option>
  </select>
  <input class="input2" type="submit" name="Submit3" value="搜索">
</form>
	<%sqlbean.executeSelect("select * from t_user_apply where apply_status='0'");
	
	String pageno = (String)request.getParameter("pageno");
	if (pageno == null) pageno = "1";
	System.out.print(pageno);
	int listCount = Integer.parseInt(ust.getLines_in_page());
	int pageNum = Integer.parseInt(pageno);
	int maxCount = java.lang.Math.min(sqlbean.getRowCount(), (pageNum-1)*listCount+listCount);
	int i_count = (int)java.lang.Math.ceil((double)sqlbean.getRowCount()/listCount);
	out.print("共" + i_count + "页&nbsp;");
	for (int i=1; i<=i_count; i++)
	if (i == pageNum)
	{
		out.print(pageno);
	}
	else
	{
	%>
	<A href="adminapply.jsp?pageno=<%=i%>"><%=i%></A>
	<%
	}
	%>
<form name="form1" method="post" action="saveadmin.jsp">
  　课程申请批准：<br>
  <table width="100%" border="0" cellspacing="0" cellpadding="1">
    <tr> 
      <td bgcolor="#ffffff"> 
        <table width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr bgcolor="#eeeeee"> 
            <td height="20" bgcolor="#cccccc" width="20%" align="center">申请人</td>
            <td height="20" bgcolor="#cccccc" width="27%" align="center">申请的课程</td>
			<td height="20" bgcolor="#cccccc" width="23%" align="center">申请时间</td>
            <td height="20" bgcolor="#cccccc" width="30%" align="center">申请原因</td>
          </tr>
          <%for (int i=(pageNum-1)*listCount;i<maxCount;i++){%>
          <tr bgcolor="#eeeeee"> 
            <td height="20" width="20%" align="left"> 
              <input type="checkbox" name="select<%=i-(pageNum-1)*listCount%>" value="<%=sqlbean.getFieldValue("apply_id",i)%>">
              <%=sqlbean.getFieldValue("user_id",i)%> </td>
            
			<%sqlbean1.executeSelect("select * from t_class where class_id='"+sqlbean.getFieldValue("class_id",i)+"'");%>
            <td height="20" width="27%"><%=sqlbean1.getFieldValue("class_name",0)%></td>
			<td height="20" width="23%" align="center"><%=beanElearnTools.FormatDate(sqlbean.getFieldValue("apply_time",i))%></td>
            <td height="20" width="30%"><%=sqlbean.getFieldValue("apply_reason",i)%></td>
          </tr>
		  
              <input type="hidden" name="name" value=<%=sqlbean.getFieldValue("user_id",0)%>>
			   <input type="hidden" name="classcode" value=<%=sqlbean.getFieldValue("class_id",0)%>>
		  <%}%>
          <tr bgcolor="#eeeeee"> 
            <td height="20" colspan="4"> 
              <input name="countInPage" type="hidden" id="countInPage" value="<%=maxCount-(pageNum-1)*listCount%>">
              <p align="center">
<input type="checkbox" name="allbox" onClick="CA();">
              选中所有 
              <input class="input2" type="submit" name="Submit" value="批准" >
              </p>
            </td>
          </tr>
          </table>
      </td>
    </tr>
</table>
  <div align="center"></div>
</form>
</body>
</html>
<%}%>
