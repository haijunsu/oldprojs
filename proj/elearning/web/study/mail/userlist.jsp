<%@ page contentType="text/html; charset=gb2312"%>
<%@page session="true"%>
<%if (session.getAttribute("userid")==null||session.getAttribute("userid").equals("")){%>
<script>
alert("���ӳ�ʱ");
top.location="<%=request.getContextPath()%>/login.jsp";
</script>
<%}
else{%>
<html>
<head>
<title>�ʼ�</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/3.css" type="text/css">
</head>
<!-- ȫѡ -->
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
	window.opener.document.form1.to.value=opener.document.form1.to.value+sss;
    //alert("��ӳɹ���");
    window.close();
}
</script>

<jsp:useBean id="list" scope="session" class="com.elearning.ListCheck" />
  <jsp:setProperty name="list" property="jdbcDriver" value="sun.jdbc.odbc.JdbcOdbcDriver" />
  <jsp:setProperty name="list" property="jdbcURL" value="jdbc:odbc:elearning" />
  <jsp:setProperty name="list" property="jdbcUser" value="sa" />
  <jsp:setProperty name="list" property="jdbcPassword" value="" />

  <jsp:setProperty name="list" property="tableName" value="tuser" />
  <jsp:setProperty name="list" property="labelCols" value="user_name,sex,Email,Company,Area,user_id,Email as aEmail" />
  <jsp:setProperty name="list" property="titleCols" value="����,�Ա�,�ʼ���ַ,��˾,����" />
  <jsp:setProperty name="list" property="urlCol" value="1" />
  <jsp:setProperty name="list" property="goUrl" value="TreeFrames.jsp" />
  <jsp:setProperty name="list" property="orderStyle" value="Order BY user_id" />
  <jsp:setProperty name="list" property="tableStyle" value="table1" />

  <jsp:setProperty name="list" property="listPage" value="userlist.jsp" />
  <jsp:setProperty name="list" property="rec_per" value="2" />
  
  <jsp:setProperty name="list" property="*" />

<BODY vlink="#0000ff"><b><strong>��Ա�б�</strong></b>
<BR>
<BR>
<form name=form2 method=post>
  <%
//list.setGopage_On("0");
list.setPages_On(0);
//list.setNext_On("0");

if(request.getParameter("cur_page")!=null)
	{
		list.setcur_page(Integer.parseInt(request.getParameter("cur_page")));
}
//list.setDateColNum(2);
out.print(list.ListTable());
%>
<input type="checkbox" name="allbox" onclick="selectall();">
  ȫѡ 
  <input type="button" name="Submit3" value="���ӵ��ռ����б�" onClick=addmail()>
</form>
</body>
</html>
<%}%>