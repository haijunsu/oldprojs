<%@ page language="java"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*"%>
<%
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection conn=DriverManager.getConnection("jdbc:odbc:elearning","sa","");
Statement stmt=conn.createStatement();%>