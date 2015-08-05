package mail;
import java.util.* ;
import java.io.* ;
import javax.mail.* ;
import javax.mail.internet.* ;
import javax.activation.* ;

public class Mail {
	//定义发件人、收件人、主题等
	String to="";
	String from="";
	String host="";
	String filename="";
	String subject="";
	String content="";
	String userid="";
	String password="";
	//用于保存发送附件的文件名的集合
	Vector file = new Vector();

	//做一个可以传发件人等参数的构造
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
	
	//该方法用于收集附件名
	public void attachFile(String fname){
		file.addElement(fname);
	}

	//开始发送信件的方法
	public boolean Send(){
		//创建Properties对象
		Properties props = System.getProperties();


		//创建信件服务器
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true"); //这样才能通过验证

		//得到默认的对话对象
		Session session=Session.getDefaultInstance(props); 
		session.setDebug(true);
		try {
			//创建一个消息，并初始化该消息的各项元素
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address={new InternetAddress(to)};
			msg.setRecipients(Message.RecipientType.TO,address);
			msg.setSubject(subject);
			//后面的BodyPart将加入到此处创建的Multipart中
			Multipart mp = new MimeMultipart();
			//利用枚举器方便的遍历集合
			Enumeration efile=file.elements();
			//检查序列中是否还有更多的对象
			while(efile.hasMoreElements())
			{
				MimeBodyPart mbp=new MimeBodyPart();
				//选择出每一个附件名
				filename=efile .nextElement().toString();
				//得到数据源
				FileDataSource fds=new FileDataSource(filename);
				//得到附件本身并至入BodyPart
				mbp.setDataHandler(new DataHandler(fds));
				//得到文件名同样至入BodyPart
				mbp.setFileName(fds.getName());
				mp.addBodyPart(mbp);
			}
			MimeBodyPart mbp=new MimeBodyPart();
			mbp.setText(content);
			mp.addBodyPart(mbp);
		
			//移走集合中的所有元素
			file.removeAllElements();
			//Multipart加入到信件
			msg.setContent(mp);
			//设置信件头的发送日期
			msg.setSentDate(new Date());

			//发送信件
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
	//开始发送信件的方法
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