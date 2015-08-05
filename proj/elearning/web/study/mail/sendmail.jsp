<%@ page import="java.util.*, javax.mail.*, javax.mail.internet.*" %>
<%
  Properties props = new Properties();
  props.put("mail.smtp.host", "mail.trainingstation.net");
  Session s = Session.getInstance(props,null);

  MimeMessage message = new MimeMessage(s);

  InternetAddress from = new InternetAddress("longlink@2911.net");
  message.setFrom(from);
  InternetAddress to = new InternetAddress("liulin@trainingstation.net");
  message.addRecipient(Message.RecipientType.TO, to);
  
  message.setSubject("Test from JavaMail.");
  message.setText("Hello from JavaMail!");

  Transport.send(message);
%>
<html>
<p align="center">A Message has been sent.<br>Check your inbox.</p>
<p align="center"><a href="sendmail.jsp">Click here to send another!</a></p>
</html>
