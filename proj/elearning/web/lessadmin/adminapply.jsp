<%@page contentType="text/html;charset=GB2312"%>
<%@page language="java" import="com.htyz.*"%>
<html>
<head>
<jsp:useBean id="sqlbean" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="sqlbean1" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="ust" scope="session" class="system.userStyle"/>
<jsp:useBean id="eds" scope="request" class="com.htyz.util.BeanDateFormat"/>

<title>�γ�������׼</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../3.css" type="text/css">
</head>
<script language="javascript">
function shan(){
if (confirm("�����Ҫɾ��ѡ������Ϣ��")){
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

function ok()
{
var str="";
var len;
len=document.form1.select1.length;
//alert("len=="+len);
if(len >0)
{
	for(i=0;i<len;i++)
	{
	  if(document.form1.select1[i].checked)
	  {
	   if(str.length==0)
		 str=document.form1.select1[i].value;
		
	   else
		 str=str+","+document.form1.select1[i].value;
	  }

	}
}
else
{
	 if(document.form1.select1.checked)
		 str=document.form1.select1.value;
}
 if(str.length==0)
 {
  //alert("str=="+str);
  alert("ע�⣺����ѡ������׼");
  return false
 }
 document.form1.parm.value=str; 
  
  //alert("str=="+document.form1.parm.value);
 


}

//-->
</script>

<%
//��ֹCache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>

<%String userid=(String)session.getAttribute("userid");
if(userid==null||userid==""){
out.print("����û�е�¼�����<a href=" + request.getContextPath() + "'/login.jsp'>����</a>��½��");
}
else{
%>
<body bgcolor="#FFFFFF" text="#000000">
<form name="form2" method="post" action="applysearch.jsp">
  <input class="input1" type="text" name="search" size="15">
  <select class="testarea1" name="select">
    <option value="1">���γ�</option>
    <option value="2">���û�</option>
 </select>
  <input class="input2" type="submit" name="Submit3" value="����">
  
</form>
	<%sqlbean.executeSelect("select * from t_user_apply where apply_status='0'");
	String pageno = (String)request.getParameter("pageno");
	if (pageno == null) pageno = "1";
	int listCount = Integer.parseInt(ust.getLines_in_page());
	int pageNum = Integer.parseInt(pageno);
	int maxCount = java.lang.Math.min(sqlbean.getRowCount(), (pageNum-1)*listCount+listCount);
	int i_count = (int)java.lang.Math.ceil((double)sqlbean.getRowCount()/listCount);
	out.print("��" + i_count + "ҳ&nbsp;");
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
  ���γ�������׼��<br>
  <table width="100%" border="0" cellspacing="0" cellpadding="1">
    <tr> 
      <td bgcolor="#ffffff"> 
        <table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" class="xbz" width="95%">
          <tr  bgcolor="#CCCCCC" align="center" Height="25"> 
            <td width="20%" align="center" height="20">������</td>
            <td width="27%" align="center" height="20">����Ŀγ�</td>
			<td width="23%" align="center" height="20">����ʱ��</td>
            <td width="30%" align="center" height="20">����ԭ��</td>
          </tr>
          <%for (int i=(pageNum-1)*listCount;i<maxCount;i++){%>
          <tr bgcolor="#eeeeee"> 
            <td height="20" width="20%" align="left"> 
		     <input type="checkbox" name="select1" value="<%=sqlbean.getFieldValue("apply_id",i)%>">
             <%=sqlbean.getFieldValue("user_id",i)%> </td>
            <%sqlbean1.executeSelect("select * from t_class where class_id='"+sqlbean.getFieldValue("class_id",i)+"'");%>
            <td height="20" width="27%"><%=sqlbean1.getFieldValue("class_name",0)+"&nbsp;"%></td>
			<td height="20" width="23%" align="center"><%=eds.format(sqlbean.getFieldValue("apply_time",i),3)+"&nbsp;"%></td>
            <td height="20" width="30%"><%=sqlbean.getFieldValue("apply_reason",i)+"&nbsp;"%></td>
          </tr>
		  <input type="hidden" name="name<%=i%>" value=<%=sqlbean.getFieldValue("user_id",i)%>>
		  <input type="hidden" name="classid<%=i%>" value=<%=sqlbean.getFieldValue("class_id",i)%>>
		  <%}%>
          <tr bgcolor="#eeeeee" align="center"> 
            <td height="20" colspan="4"> 
              <input name="countInPage" type="hidden" id="countInPage" value="<%=maxCount-(pageNum-1)*listCount%>">
              <input type="checkbox" name="allbox" onClick="CA();">
              ѡ������ 
              <input type="submit" name="Submit" value="��׼" onclick="return ok()" >
				
            </td>
			 
          </tr>
		  <input type="hidden" name="parm">
          </table>
      </td>
    </tr>
	
</table>
</form>
</body>
</html>
<%}%>
