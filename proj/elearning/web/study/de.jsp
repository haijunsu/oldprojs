<%@ page contentType="text/html;charset=GB2312" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body bgcolor="#FFFFFF" text="#000000" topmargin="0">
<table border=0 width="400">
  <tr>
<td>
<a onclick="if(parent.statecatalog == 0) {parent.statecatalog = 1;if(parent.chatState == 0){parent.c.cols='0%,*,15%';}else{parent.c.cols='0%,*,0%';}document.catalog.src='hide.jpg';}else{parent.statecatalog=0;if(parent.chatState == 0){parent.c.cols='20%,*,15%';}else{parent.c.cols='20%,*,0%';}document.catalog.src='hide1.jpg';}" ><img src='hide1.jpg' name='catalog' border=0 ></a>
</td>

    <td > <a href="<%=request.getContextPath()%>/study/mail/mail.jsp" target="bottomFrame" onclick="if(parent.mailState==0) {parent.mailState=1;parent.f.rows='70%,*';document.mail.src='kech_mail.gif'; }else{parent.mailState=0; parent.f.rows='100%,*';document.mail.src='kech_mail2.gif';}"><img src='kech_mail2.gif' name='mail' border=0 alt='邮件'></a> 
    </td>
<td >
<a href="<%=request.getContextPath()%>/study/notes/note.jsp" target="bottomFrame" onclick="if(parent.bbsState==0) {parent.bbsState=1;parent.f.rows='60%,*';document.note.src='note.jpg'; }else{parent.bbsState=0; parent.f.rows='100%,*';document.note.src='note2.jpg';}"><img src='note2.jpg' name='note' border=0 alt='笔记'></a>
</td>
    <td><a onclick="if(parent.chatState == 0) {parent.chatState = 1;if(parent.statecatalog == 0){parent.c.cols='20%,*,0%';}else{parent.c.cols='0%,*,0%';}document.chat.src='chat1.jpg';}else{parent.chatState=0;if(parent.statecatalog == 0){parent.c.cols='20%,*,15%';}else{parent.c.cols='0%,*,15%';}document.chat.src='chat.jpg';}" ><img src="chat1.jpg" name='chat' width="66" height="23" border="0" alt='聊天室'></a> 
    </td>
	<td>
      <a href="<%=request.getContextPath()%>/mainframe.htm" target="_parent"><img src="exit.gif" name='chat'  border="0" alt='返回'></a>
	</td>
</tr>
</table>
</body>
</html>
