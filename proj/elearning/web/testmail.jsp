<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="sendmail" scope="request" class="mail.Mail"/>
<%
sendmail.setHost("smtp.sina.com.cn");
sendmail.setUserId("fineabc");
sendmail.setPassword("fine123");
sendmail.setTo("itliu@sohu.com");
sendmail.setFrom("abc@2143.com");
sendmail.setSubject("asfdaf");
sendmail.setContent("asdfasdfdsafasdfa2324");
sendmail.attachFile("c:/IBM.txt");
sendmail.Send();
%>