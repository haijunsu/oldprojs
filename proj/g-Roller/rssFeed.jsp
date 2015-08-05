<%@ page contentType="text/xml;charset=GBK" language="java" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%
	String context = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<?xml version="1.0" encoding="GBK"?> 
<rss version="2.0">
     <channel>
          <title><bean:write name="dto" property="name"/>的weblog</title> 
          <link><%=context%>/post.do?id=<bean:write name="dto" property="userId"/>&amp;method=list</link> 
          <ttl>60</ttl> 
<logic:iterate id="post" name="dtos">
          <item>
               <title><bean:write name="post" property="title"/></title>
			   <link><%=context%>/post.do?method=view&amp;id=<bean:write name="post" property="postId"/></link>
               <description><bean:write name="post" property="description"/></description> 
               <guid><bean:write name="post" property="postId"/></guid> 
               <author><bean:write name="dto" property="name"/></author> 
               <comments><%=context%>/post.do?method=reply&amp;id=<bean:write name="post" property="postId"/></comments> 
          </item>
</logic:iterate>
     </channel>
</rss>
