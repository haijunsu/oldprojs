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
	s1.execute("Insert into t_lesson values('"+s0.getFieldValue(1,i)+"','001000','��һ�� E-learning','<%=request.getContextPath()%>/bookstore/test/test.html')");
	s1.execute("Insert into t_lesson values('"+s0.getFieldValue(1,i)+"','001001','��һ�� ǰ��','<%=request.getContextPath()%>/bookstore/test/test.html')");
	s1.execute("Insert into t_lesson values('"+s0.getFieldValue(1,i)+"','001002','�ڶ��� ϵͳ����','<%=request.getContextPath()%>/bookstore/test/test.html')");
	s1.execute("Insert into t_lesson values('"+s0.getFieldValue(1,i)+"','002000','�ڶ���','<%=request.getContextPath()%>/bookstore/test/test.html')");
	s1.execute("Insert into t_lesson values('"+s0.getFieldValue(1,i)+"','002001','��һ�� ʲô��E-learning','<%=request.getContextPath()%>/bookstore/test/test.html')");
	s1.execute("Insert into t_lesson values('"+s0.getFieldValue(1,i)+"','002002','�ڶ��� E-learning���ŵ�','<%=request.getContextPath()%>/bookstore/test/test.html')");
	s1.execute("Insert into t_lesson values('"+s0.getFieldValue(1,i)+"','003000','������ ��������E-learning','<%=request.getContextPath()%>/bookstore/test/test.html')");
	s1.execute("Insert into t_lesson values('"+s0.getFieldValue(1,i)+"','003001','��һ�� ��������E-learning���ص�','<%=request.getContextPath()%>/bookstore/test/test.html')");
	s1.execute("Insert into t_lesson values('"+s0.getFieldValue(1,i)+"','003002','�ڶ��� ��������E-learning�Ĺ���','<%=request.getContextPath()%>/bookstore/test/test.html')");
	s1.execute("Insert into t_lesson values('"+s0.getFieldValue(1,i)+"','003003','������ ���ǵķ���','<%=request.getContextPath()%>/bookstore/test/test.html')");
}


%>