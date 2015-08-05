<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<%@page language="java" import="com.htyz.*"%>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="s0" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="s1" scope="request" class="com.htyz.beanGetdata"/>
<%
s0.executeSelect("select class_id from t_class where class_status='1'");
for(int i=0;i<s0.getRowCount();i++){
	//out.println(s0.getFieldValue(1,i));
	s1.execute("Insert into t_lesson values('"+s0.getFieldValue(1,i)+"','001000','第一章 E-learning','<%=request.getContextPath()%>/bookstore/test/test.html')");
	s1.execute("Insert into t_lesson values('"+s0.getFieldValue(1,i)+"','001001','第一节 前言','<%=request.getContextPath()%>/bookstore/test/test.html')");
	s1.execute("Insert into t_lesson values('"+s0.getFieldValue(1,i)+"','001002','第二节 系统概述','<%=request.getContextPath()%>/bookstore/test/test.html')");
	s1.execute("Insert into t_lesson values('"+s0.getFieldValue(1,i)+"','002000','第二章','<%=request.getContextPath()%>/bookstore/test/test.html')");
	s1.execute("Insert into t_lesson values('"+s0.getFieldValue(1,i)+"','002001','第一节 什么是E-learning','<%=request.getContextPath()%>/bookstore/test/test.html')");
	s1.execute("Insert into t_lesson values('"+s0.getFieldValue(1,i)+"','002002','第二节 E-learning的优点','<%=request.getContextPath()%>/bookstore/test/test.html')");
	s1.execute("Insert into t_lesson values('"+s0.getFieldValue(1,i)+"','003000','第三章 环天宇正E-learning','<%=request.getContextPath()%>/bookstore/test/test.html')");
	s1.execute("Insert into t_lesson values('"+s0.getFieldValue(1,i)+"','003001','第一节 环天宇正E-learning的特点','<%=request.getContextPath()%>/bookstore/test/test.html')");
	s1.execute("Insert into t_lesson values('"+s0.getFieldValue(1,i)+"','003002','第二节 环天宇正E-learning的功能','<%=request.getContextPath()%>/bookstore/test/test.html')");
	s1.execute("Insert into t_lesson values('"+s0.getFieldValue(1,i)+"','003003','第三节 我们的服务','<%=request.getContextPath()%>/bookstore/test/test.html')");
}


%>