<%@page contentType="text/html;charset=GB2312"%>
<%@page language="java" import="com.htyz.*"%>
<html>
<head>
<jsp:useBean id="sqlbean" scope="request" class="com.htyz.beanGetdata"/>
<title>�γ�������׼</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../3.css" type="text/css">
</head>


<%String userid=(String)session.getAttribute("userid");
if(userid==null||userid==""){
out.print("����û�е�¼�����<a href='login.jsp'>����</a>��½��");
}
else{
%>

<body bgcolor="#FFFFFF" text="#000000">
<%sqlbean.executeSelect("select * from t_user_apply where apply_status='0'");%>
 <%for (int i=0;i<sqlbean.getRowCount();i++){%>
<form name="form1" method="post" action="saveadmin.jsp">
  <table width="100%" border="0" cellspacing="0" cellpadding="1">
    <tr> 
      <td bgcolor="#ffffff"> 
        <table width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr bgcolor="#eeeeee"> 
            <td colspan="2" height="20" bgcolor="#cccccc"> ���γ�������׼��</td>
          </tr>
          <tr bgcolor="#eeeeee"> 
		
            <td height="20" width="17%">�����ˣ�</td>
            <td height="20" width="83%"> 
              <input class=input2 type="text" name="name" size="20" value="<%=sqlbean.getFieldValue("user_id",i)%>">
            </td>
          </tr>
          <tr bgcolor="#eeeeee"> 
            <td height="20" width="17%">����Ŀγ̣�</td>
            <td height="20" width="83%"> 
              <input class=input2 type="text" name="classcode" size="20" value="<%=sqlbean.getFieldValue("class_id",i)%>">
            </td>
          </tr>
          <tr bgcolor="#eeeeee"> 
            <td height="20" width="17%">����ԭ��</td>
            <td height="20" width="83%"> 
              <textarea class=testarea1 name="reason" cols="30" rows="5"><%=sqlbean.getFieldValue("apply_reason",i)%></textarea>
            </td>
          </tr>
		 
          <tr bgcolor="#eeeeee" align="center"> 
            <td height="20" colspan="2"> 
              <input class=input2 type="submit" name="Submit" value="��׼">
              <input class=input2 type="reset" name="Submit2" value="ȡ��">
            </td>
          </tr>
	
        </table>
      </td>
    </tr>
  </table>
</form>
<%}%>
</body>
</html>
<%}%>
