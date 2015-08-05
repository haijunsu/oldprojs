<html>
<head>
<title>聊天室</title>
<meta http-equiv="Content-Type" content="text/html;charset=gb2313">
<%@ page contentType="text/html; charset=gb2312"%>
<%@ page session="true"%>
<script language="javascript">
window.moveTo(0,0);
</script>
</head>
<frameset frameborder="0" broder="0" framespacing="5" rows="80,*,115" cols="*" border="5"> 
  <frame name="top" src="top.jsp" scrolling="no" noresize>
  <frameset frameborder="0" border="0" framespacing="0" cols="153,*" rows="*"> 
    <frame name="userlistframe" src="userlist.jsp">
    <frame name="mainframe" src="main.jsp">
  </frameset>
  <frame name="bottomFrame" scrolling="NO" noresize src="input.jsp">
  <frameset frameborder="0" border="0" framespaceing="0" rows="0,*" cols="*"> 
    <frame name="hiddenframe" src="hidden.jsp" scrolling="no" >
  </frameset>
  <noframes> 请使用可用框架浏览器 
  </noframes> </frameset>
</html>
