<%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="java.util.*, javax.mail.*, javax.mail.internet.*" %>
<%
		String host = "smtp.sina.com.cn";
        String from =  request.getParameter("from");
        String to = request.getParameter("to");
        String username = "fineabc";
        String password = "fine123";

        // Get system properties
        // Properties props = System.getProperties(); �ܶ��������������ģ���ʵ���������ã���������applet��
        Properties props = new Properties();

        // Setup mail server
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true"); //��������ͨ����֤

        // Get session
        Session sess = Session.getDefaultInstance(props);

        // watch the mail commands go by to the mail server
        sess.setDebug(true);

        // Define message
        MimeMessage message = new MimeMessage(sess);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
		 String ccAddresses = request.getParameter("cc");
		 message.setRecipients(Message.RecipientType.CC, ccAddresses);
		 String bccAddresses = request.getParameter("bcc");
		 message.setRecipients(Message.RecipientType.BCC, bccAddresses);
		 String subject = request.getParameter("subject");
		  message.setSubject(subject);
		  String text = request.getParameter("text");
		  message.setText(text);

        // Send message
        message.saveChanges();
        Transport transport = sess.getTransport("smtp");
        transport.connect(host, username, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
%>
<script language="javascript">
alert("�����ʼ��ѱ��ɹ����ͣ�");
history.back(-1);
</script>