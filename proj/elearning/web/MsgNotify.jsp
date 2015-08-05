<jsp:useBean id="msgNotifyNews" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="msgNotifyEts" scope="request" class="com.htyz.beanElearnTools"/>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
String msgNotifySql;
String msgNotifyUserid = (String)session.getAttribute("userid");
if(!(msgNotifyUserid == null)&&!(msgNotifyUserid.equals("")))
{
	msgNotifySql = "SELECT * FROM t_onlinecall WHERE oc_obj = '" + msgNotifyUserid + "' AND oc_status = '1'";
	msgNotifyNews.executeSelect(msgNotifySql);
	if(msgNotifyNews.getRowCount() > 0)
	{
	%>
	<A href=<%=request.getContextPath()%>/servlet/onlinecall/Messager>您有<%=msgNotifyNews.getRowCount()%>个新短信</A>
	<%
	}
}
%>