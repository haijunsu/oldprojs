<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<%@page import="com.htyz.*"%>
<jsp:useBean id="menu" scope="request" class="system.beanMenuShow"/>
<link rel="stylesheet" href="style.css" type="text/css">
<%
//½ûÖ¹Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
String userright;
String userid = (String)session.getAttribute("userid");
%>

<table border="0" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#6699cc" bordercolordark="#FFFFFF" class="xbz">
<tbody>
  <tr>
  	<td nowrap>
   	<%
	menu.SelectMenus(userid);
	if(userid == null||userid.equals(""))
	{
		userright = "1";
	}
	else
	{
		userright = menu.getUser("user_right", 0);
	}
	if(menu.getMenuCount()>0)
	{
		for(int i=0; i<menu.getMenuCount(); i++)
		{
			if(menu.isShow(menu.getMenu("menu_right", i), userright))
				{
				%>
  	<td nowrap>
			 		<a href="<%=menu.getMenu("menu_url", i)%>" target="_blank">¡ó<%=menu.getMenu("menu_namec", i)%></a>
	<td>
				<%
				}
		}
	}
	%>
  </tr>
 </tbody>
</table>