<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>聊天室</title>
<%
String room = (String)request.getAttribute("room");
String userid = (String)session.getAttribute("userid");
%>
<script language="JavaScript">
var userList = new Array();
var initHtml = "<HTML><HEAD><TITLE>聊天内容</TITLE>";
initHtml = initHtml + "<link href=\"<%=request.getContextPath()%>/style.css\" rel=\"stylesheet\" type=\"text/css\">";
initHtml = initHtml + "<META HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset=GB2312\">";
initHtml = initHtml + "<BODY>";

function initChatRoom()
{
	this.writeChatContent(initHtml);
}

function writeChatContent(content)
{
	this.chatcontent.document.writeln(content);
	this.chatcontent.document.writeln("<" + "script>self.scrollBy(0,100)</" + "script>");
}
function clearChatContent() 
{
	this.chatcontent.document.close();
	this.chatcontent.document.open();
	initChatRoom();
}
function setChatRoom(chatroom)
{
	this.input.document.talkForm.room.value = chatroom;
	//alert(this.input.document.talkForm.room.value);
}

function setChatUserList()
{
	chatUserList = this.input.document.talkForm.talkto;
	chatUserList.options.length = 1;
	chatUserList.options[0].value = "所有人";
	chatUserList.options[0].text = "所有人";
 	chatUserList.selectedIndex = 0;
  	for(i = 0;i <userList.length; i++)
  	{
      chatUserList.options.length ++;
      chatUserList.options[chatUserList.options.length-1].value = userList[i];
      chatUserList.options[chatUserList.options.length-1].text  = userList[i];
    }
}
function reloadOnlineList()
{
	this.onlinelist.location.reload();
}
</script>

</head>
<frameset rows="*, 0, 0" border="0" framespacing="0" frameborder="NO">
	<%--<frameset rows="*, 75" border="1" framespacing="0" frameborder="YES">--%>
	<frameset rows="*, 85" border="1" framespacing="0" frameborder="YES">
	   <frame name="chatcontent" src="">
	   <frame name="input" src="<%=request.getContextPath()%>/chatroom/input.jsp" scrolling="NO">
	</frameset>
   <frame name="show" src="<%=request.getContextPath()%>/servlet/chatroom/chat?action=show&room=<%=room%>">
   <frame name="onlinelist" src="<%=request.getContextPath()%>/servlet/chatroom/chat?action=onlineList&room=<%=room%>">
</frameset>
</html>