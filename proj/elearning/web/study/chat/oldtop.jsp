<html>
<head>
<title>������</title>
<meta http-equiv="Content-Type" content="text/html;charset=gb2313">
<%@ page contentType="text/html; charset=gb2312"%>
<%@ page session="true"%>
<script language="javascript">
window.moveTo(0,0);
</script>
</head>
<frameset frameborder="0" broder="0" framespacing="5" rows="392*,83" cols="*" border="5"> 
  <frameset frameborder="0" border="1" framespacing="1" cols="117,*" rows="*"> 
    <frame name="userlistframe" src="<%=request.getContextPath()%>/study/chat/userlist.jsp">
    <frame name="mainframe" src="<%=request.getContextPath()%>/study/chat/main.jsp">
  </frameset>
  <frame name="bottomFrame" scrolling="NO" src="<%=request.getContextPath()%>/study/chat/input.jsp">
  <noframes> ��ʹ�ÿ��ÿ������� 
  </noframes> </frameset>
</html>
