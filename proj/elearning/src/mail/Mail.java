package mail;
import java.util.* ;
import java.io.* ;
import javax.mail.* ;
import javax.mail.internet.* ;
import javax.activation.* ;

public class Mail {
	//���巢���ˡ��ռ��ˡ������
	String to="";
	String from="";
	String host="";
	String filename="";
	String subject="";
	String content="";
	String userid="";
	String password="";
	//���ڱ��淢�͸������ļ����ļ���
	Vector file = new Vector();

	//��һ�����Դ������˵Ȳ����Ĺ���
	public void setTo (String to){
		this.to=to;
	}
	public void setFrom (String from){
		this.from=from;
	}
	public void setSubject(String subject){
		this.subject=subject;
	}
	public void setContent (String content) {
		this.content=content;
	}
	public void setHost (String smtpServer){
		this.host=smtpServer;
	}
	public void setUserId (String userid) {
		this.userid=userid;
	}
	public void setPassword (String password) {
		this.password=password;
	}
	
	//�÷��������ռ�������
	public void attachFile(String fname){
		file.addElement(fname);
	}

	//��ʼ�����ż��ķ���
	public boolean Send(){
		//����Properties����
		Properties props = System.getProperties();


		//�����ż�������
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true"); //��������ͨ����֤

		//�õ�Ĭ�ϵĶԻ�����
		Session session=Session.getDefaultInstance(props); 
		session.setDebug(true);
		try {
			//����һ����Ϣ������ʼ������Ϣ�ĸ���Ԫ��
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address={new InternetAddress(to)};
			msg.setRecipients(Message.RecipientType.TO,address);
			msg.setSubject(subject);
			//�����BodyPart�����뵽�˴�������Multipart��
			Multipart mp = new MimeMultipart();
			//����ö��������ı�������
			Enumeration efile=file.elements();
			//����������Ƿ��и���Ķ���
			while(efile.hasMoreElements())
			{
				MimeBodyPart mbp=new MimeBodyPart();
				//ѡ���ÿһ��������
				filename=efile .nextElement().toString();
				//�õ�����Դ
				FileDataSource fds=new FileDataSource(filename);
				//�õ�������������BodyPart
				mbp.setDataHandler(new DataHandler(fds));
				//�õ��ļ���ͬ������BodyPart
				mbp.setFileName(fds.getName());
				mp.addBodyPart(mbp);
			}
			MimeBodyPart mbp=new MimeBodyPart();
			mbp.setText(content);
			mp.addBodyPart(mbp);
		
			//���߼����е�����Ԫ��
			file.removeAllElements();
			//Multipart���뵽�ż�
			msg.setContent(mp);
			//�����ż�ͷ�ķ�������
			msg.setSentDate(new Date());

			//�����ż�
       		msg.saveChanges();
       		Transport transport = session.getTransport("smtp");
   	  	 	transport.connect(host,userid, password);
       		transport.sendMessage(msg, msg.getAllRecipients());
       		transport.close();
		} catch (MessagingException mex)
		{
			mex.printStackTrace();
			Exception ex = null;
			if ((ex=mex.getNextException())!=null)
				ex.printStackTrace();
			return false;
		}
		return true;
	}
	//��ʼ�����ż��ķ���
	public boolean Send(String host,String user,String pass,String from,String to,String subject,String content)
	{
		setHost(host);
		setUserId(user);
		setPassword(pass);
		setFrom(from);
		setTo(to);
		setSubject(subject);
		setContent(content);
		return Send();
	}
	
}