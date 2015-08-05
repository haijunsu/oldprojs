<%@page contentType="text/html;charset=GB2312"%>

<html>
<head>
<title>用户在线列表</title>
<jsp:useBean id="chatroom" scope="request" class="chatroom.BeanChatRoom"/>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%
String room = (String)request.getAttribute("room");
%>
<META http-equiv="refresh" content="900; <%=request.getContextPath()%>/servlet/chatroom/chat?action=onlineList&room=<%=room%>">
</head>
<SCRIPT language="javascript">
parent.userList.length=1;
<%
for (int i=0; i<chatroom.getOnlineCount(); i++)
{
	out.println("parent.userList[" + Integer.toString(i) + "] = \"" + chatroom.getOnlineName(i) + "\";");
}
%>
parent.setChatRoom("<%=room%>");
parent.setChatUserList();
</SCRIPT>
</html>
