<%@page contentType="text/html;charset=GB2312"%>
<%
session.setAttribute("userid", "eea");
%>
<%=(String)session.getAttribute("userid")%>