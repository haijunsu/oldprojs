<%@ page import="com.htyz.system.SystemProperties"%>
<%
out.println("db.type="+SystemProperties.getProperty("db.type"));
out.println("\n");
out.println("db.addsign="+SystemProperties.getProperty("db.addsign"));

%>
