<%@page contentType="text/html;charset=GB2312"%>
<%@page language="java" import="com.htyz.*"%>
<html>
<head>
<jsp:useBean id="sqlbean" scope="request" class="com.htyz.beanGetdata"/>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="3.css" type="text/css">
</head>

<%String userid=(String)session.getAttribute("userid");
if(userid==null||userid==""){
out.print("����û�е�¼�����<a href='" + request.getContextPath()+ "/index.jsp'>����</a>��½��");
}
else{
%>

<body bgcolor="#FFFFFF" text="#000000">
<form name="form1" method="post" action="saveapply.jsp">
  <table width="100%" border="0" cellspacing="0" cellpadding="1">
    <tr> 
      <td bgcolor="#ffffff"> 
        <table width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr bgcolor="#eeeeee"> 
            <td colspan="2" height="20" bgcolor="#cccccc"> ���γ����룺</td>
          </tr>
          <tr bgcolor="#eeeeee"> 
            <td height="20" width="17%">�û�id��</td>
            <td height="20" width="83%"><%=userid%></td>
          </tr>
          <tr bgcolor="#eeeeee"> 
            <td height="20" width="17%">����γ̣� </td>
            <td height="20" width="83%"> 
              <select name="select1" onchange="javascript:document.form1.select2.value=this.value">
                <%sqlbean.executeSelect("select * from tclass where class_status='1'");%>
                <option value="0">ѡ��γ�</option>
                <%for (int i=0;i<sqlbean.getRowCount();i++){%>
                <option value="<%=sqlbean.getFieldValue("class_id",i)%>"><%=sqlbean.getFieldValue("class_name",i)%></option>
                <%}%>
              </select>
              <input class="input1" type="text" name="select2" size="15">
            </td>
          </tr>
          <tr bgcolor="#eeeeee"> 
            <td height="20" width="17%">����ԭ��</td>
            <td height="20" width="83%"> 
              <textarea class=testarea1 name="reason" cols="30" rows="5"></textarea>
            </td>
          </tr>
          <tr bgcolor="#eeeeee" align="center"> 
            <td height="20" colspan="2"> 
              <input class=input2 type="submit" name="Submit" value="�ύ">
              <input class=input2 type="reset" name="Submit2" value="ȡ��">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
<%}%>
