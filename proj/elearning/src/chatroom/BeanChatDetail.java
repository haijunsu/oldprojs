package chatroom;

import com.htyz.beanGetdata;
import com.htyz.beanElearnTools;

public class BeanChatDetail {
	private String serial;
	private String chatroom;
	private String userId;
	private String talkto;
	private String stealthy;
	private String content;
	private String talkTime;
	private String lastTime;
	private beanGetdata bgd = new beanGetdata();

	public BeanChatDetail() {
		this.serial = "";
		this.chatroom = "";
		this.userId = "";
		this.talkto = "";
		this.stealthy = "0";
		this.content = "";
		this.talkTime = "";
	}
	
	public BeanChatDetail(String serial, String chatroom, String userId, 
				String talkto, String stealthy, String content, String talkTime) {
		this.serial = serial;
		this.chatroom = chatroom;
		this.userId = userId;
		this.talkto = talkto;
		this.stealthy = stealthy;
		this.content = content;
		this.talkTime = bgd.getDbDate();
	}
	
	public void insert() throws Exception {
		String sql = "SELECT max(serial) AS serial FROM t_chat_detail";
		beanElearnTools bet = new beanElearnTools();
		bgd.executeSelect(sql);
		if (bgd.getFieldValue("serial", 0).trim().equals(""))
			this.serial = "00000000000000000000";
		else
			this.serial = bet.AutoNum(bgd.getFieldValue("serial", 0));
		sql = "INSERT INTO t_chat_detail (serial, chatroom, user_id, talkto, stealthy, content, talk_time) VALUES ("
			+ "'" + serial + "', "
			+ "'" + bet.check_quote(chatroom) + "', "
			+ "'" + bet.check_quote(userId) + "', "
			+ "'" + bet.check_quote(talkto) + "', "
			+ "'" + stealthy + "', "
			+ "'" + bet.check_quote(content) + "', "
			+ "'" + bgd.getDbDate() + "')";
		//System.out.println(sql);
		bgd.execute(sql);
		return;
	}
	
	public void queryContent() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM t_chat_detail WHERE (stealthy = '0' OR (stealthy = '1' AND (talkto = '");
		sql.append(this.userId).append("' OR user_id = '");
		sql.append(this.userId).append("'))) AND talk_time > '").append( this.lastTime).append("'");
		sql.append(" AND chatroom = '");
		sql.append(this.chatroom);
		sql.append("'");
		//System.out.println(sql);
		bgd.executeSelect(sql.toString());
		if (bgd.getRowCount() > 0)
			this.setLastTime(bgd.getDbDate());
		return;
	}

	//获取聊天内容
	public String getTalkContent() throws Exception {
		StringBuffer content = new StringBuffer();
		beanElearnTools bet = new beanElearnTools();
		queryContent();
		for (int i=0; i<bgd.getRowCount(); i++)
		{
			String stealthy = "悄悄地";
			content.append("<FONT class=jnfont2>");
			content.append(bgd.getFieldValue("user_id", i).trim());
			if (bgd.getFieldValue("stealthy", i).trim().equals("1"))
				content.append(stealthy);
			if (!bgd.getFieldValue("talkto", i).trim().equals(""))
			{
				if (bgd.getFieldValue("talkto", i).trim().equals(this.userId))
					content.append("对你");
				else
					content.append("对").append(bgd.getFieldValue("talkto", i).trim());
			}
			content.append("说：</FONT>").append(bet.stringToHtml(bgd.getFieldValue("content", i)));
			content.append("<BR>");
		}
		
		return content.toString(); 
	}
	
	public String getSerial() {
		return (this.serial); 
	}

	public void setSerial(String serial) {
		this.serial = serial; 
	}

	public String getChatroom() {
		return (this.chatroom); 
	}

	public void setChatroom(String chatroom) {
		this.chatroom = chatroom; 
	}

	public String getUserId() {
		return (this.userId); 
	}

	public void setUserId(String userId) {
		this.userId = userId; 
	}

	public String getTalkto() {
		return (this.talkto); 
	}

	public void setTalkto(String talkto) {
		this.talkto = talkto; 
	}

	public String getStealthy() {
		return (this.stealthy); 
	}

	public void setStealthy(String stealthy) {
		this.stealthy = stealthy; 
	}

	public String getContent() {
		return (this.content); 
	}

	public void setContent(String content) {
		this.content = content; 
	}

	public String getTalkTime() {
		return (this.talkTime); 
	}

	public void setTalkTime(String talkTime) {
		this.talkTime = talkTime; 
	}
	public String getLastTime() {
		return (this.lastTime); 
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime; 
	}
	
	public String toString() {
		String ret = null;
		ret = "serial = " + serial + "\n";
		ret += "chatroom = " + chatroom + "\n";
		ret += "userId = " + userId + "\n";
		ret += "talkto = " + talkto + "\n";
		ret += "stealthy = " + stealthy + "\n";
		ret += "content = " + content + "\n";
		ret += "talkTime = " + talkTime + "\n";
		return ret;
	}
}