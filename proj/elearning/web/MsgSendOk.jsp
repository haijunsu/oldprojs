<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>�ɹ�ҳ��</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/3.css" type="text/css">
</head>
<body>
<Center>

<%@ include file="/MsgNotify.jsp"%>

<br>
<table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="100%">
  <tr align="center"> 
    <td colspan="5"> <A href='elearn.bean_Messager?action=new'>д����</A>&nbsp;&nbsp;|&nbsp;&nbsp; 
      <A href='elearn.bean_Messager?action=list&group=inbox'>�ռ���</A>&nbsp;&nbsp;|&nbsp;&nbsp; 
      <A href='elearn.bean_Messager?action=list&group=outbox'>������</A>&nbsp;&nbsp;|&nbsp;&nbsp; 
      <A href='elearn.bean_Messager?action=list&group=draftbox'>�ݸ���</A> </td>
  </tr>
</TABLE>

<p>
<%String message = (String) request.getAttribute("message");%>
<%=message%>
</body>
</html>
	