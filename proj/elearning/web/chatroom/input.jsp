<%@page contentType="text/html;charset=GB2312"%>
<HTML>
<HEAD>
<TITLE> 输入页面 </TITLE>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=GB2312">
<SCRIPT language="javascript">

function refreshList()//刷新在线名单
{
	parent.reloadOnlineList();
	return;
}
function logout()//退出聊天室
{
	return;
}
function talkSubmit()//发表谈话
{
	if (document.talkForm.contentvalue.value == "")
	{
		alert("请输入谈话内容!");
		document.talkForm.contentvalue.focus();
		return false;
	}
	document.talkForm.submit();
	document.talkForm.contentvalue.focus();
	document.talkForm.contentvalue.value = "";
	return false;
}
</SCRIPT>

</HEAD>
<BODY topmargin="0" leftmargin="0">
<CENTER>
<FORM name="talkForm" method="get" action="<%=request.getContextPath()%>/servlet/chatroom/chat" onSubmit="return talkSubmit();" target="show">
  <INPUT type="text" name="contentvalue" id="contentvalue" size="20"><br>
  悄悄地<INPUT type="checkbox" name="stealthy" id="stealthy" value="1">对
  <SELECT name="talkto" id="talkto" size="1">
  </SELECT><br>
  <INPUT type="button" name="Listbtn" id="Listbtn" onClick="refreshList()" value="刷新名单">
  <INPUT type="button" name="clearChatbtn" id="clearChatbtn" onClick="parent.clearChatContent()" value="清屏">
  <INPUT type="hidden" name="action" id="action" value="speak">
  <INPUT type="hidden" name="room" id="room" value="">
</FORM>
</CENTER>  
<SCRIPT language="javascript">
	parent.clearChatContent();
</SCRIPT>


</BODY>
</HTML>