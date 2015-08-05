<%@page contentType="text/html;charset=GB2312"%>
<jsp:useBean id="chatDetail" scope="request" class="chatroom.BeanChatDetail"/>

<html>
<head>
<title>ÏÔÊ¾ÁÄÌìÄÚÈİ</title>
<%
//½ûÖ¹Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%
String room = (String)request.getAttribute("room");
%>
</head>
<SCRIPT language="javaScript">
	parent.writeChatContent("<%=chatDetail.getTalkContent()%>");
	function refreshShow()
	{
		window.location="<%=request.getContextPath()%>/servlet/chatroom/chat?action=show&room=<%=room%>";
	}
	
	setTimeout('refreshShow();', 5000);

</SCRIPT>
</html>
