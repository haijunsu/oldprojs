<%@page contentType="text/html;charset=GB2312"%>
<%@ page  language="java" import="com.htyz.*" %>
<jsp:useBean id="beanGetdata" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="Getdata" scope="request" class="com.htyz.beanGetdata"/>
<%String classid=request.getParameter("class_id");
session.setAttribute("id",classid);
String s_UserId = (String)session.getAttribute("userid");

	
	 String sql1="select * from t_user_class where Class_id="+classid+" and User_id='"+s_UserId+"' and Status='1'";
     String sql="select * from t_class where Class_id="+classid;
	 beanGetdata.executeSelect(sql);
	//System.out.println(beanGetdata.getRowCount());
	%>
<link rel="stylesheet" href="3.css" type="text/css">
<table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" class="<%=request.getContextPath()%>/xbz" width="100%" bgcolor="#FFFFFF">
  <tr> 
    <td width="13%" height="20" bgcolor="#eeeeee">�γ�����</td>
    <td colspan="3" height="20"><%=beanGetdata.getFieldValue("class_name",0)%></td>
  </tr>
  <tr> 
    <td width="13%" height="20" bgcolor="#eeeeee">�γ�����</td>
    <td colspan="3" height="20"><%=beanGetdata.getFieldValue("class_type",0)%></td>
  </tr>
  <tr> 
    <td width="13%" height="20" bgcolor="#eeeeee">�γ�����</td>
    <td colspan="3" height="20"><%=beanGetdata.getFieldValue("Description",0)%></td>
  </tr>
  <tr> 
    <td width="13%" height="20" bgcolor="#eeeeee">��ʱ</td>
    <td colspan="3" height="20"><%=beanGetdata.getFieldValue("class_time",0)%>��</td>
  </tr>
  <tr> 
    <td width="13%" height="20" bgcolor="#eeeeee">ͨ����׼</td>
    <td colspan="3" height="20"><%=beanGetdata.getFieldValue("Pass_stander",0)%></td>
  </tr>
  <tr> 
    <td width="13%" height="20" bgcolor="#eeeeee">����ʱ��</td>
    <td colspan="3" height="20"><%=beanGetdata.getFieldValue("Set_time",0)%></td>
  </tr>
  <tr> 
    <td width="13%" height="20" bgcolor="#eeeeee">ѧϰ����</td>
    <td width="37%" height="20"><%=beanGetdata.getFieldValue("class_time",0)%></td>
    <td width="13%" height="20">ͨ������</td>
    <td width="41%" height="20"><%=beanGetdata.getFieldValue("Pass_stander",0)%></td>
  </tr>
  <tr> 
  <% if(s_UserId!=null&&s_UserId!=""){
	// out.print("����û�е�¼������ѡ�Σ��뷵�ص�¼��");
	%>
    <td colspan="4" height="20" valign="middle" align="center"> 
	
    <% Getdata.executeSelect(sql1);
	if(Getdata.getRowCount()==1){
	%>
      <form name="form1" method="post" action="updateclass.jsp">
        <div align="center"><br>
          <input class="input2" type="submit" name="Submit" value="ȡ�����Ź���">
        </div>
		</form>
	  <%}
	  else{%>
	  <form name="form1" method="post" action="saveclass.jsp">
         
        <div align="center"><br>
          <input class="input2" type="submit" name="Submit" value=��Ӵ��Ź���>
        </div>
	</form>

    </td>
  </tr>
</table>
<%}
}
%>

