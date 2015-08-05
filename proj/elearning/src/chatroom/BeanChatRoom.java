package chatroom;

import com.htyz.beanGetdata;
import com.htyz.beanElearnTools;

public class BeanChatRoom {
	private String chatroom;
	private String userId;
	private String lifeCycle;
	private long life;
	private beanGetdata bgd = new beanGetdata();


	public BeanChatRoom() {
		this.chatroom = "";
		this.userId = "";
		this.lifeCycle = "";
		this.life = 3000000;
	}
	public BeanChatRoom(String chatroom, String userId, String lifeCycle, long life) {
		this.chatroom = chatroom;
		this.userId = userId;
		this.lifeCycle = lifeCycle;
		this.life = life;
	}

	//插入用户
	public void addUser() throws Exception {
		StringBuffer sql = new StringBuffer();
		beanElearnTools bts = new beanElearnTools();
		long lifeCycle = System.currentTimeMillis();
		sql.append("DELETE FROM t_chatroom WHERE chatroom = '");
		sql.append(bts.check_quote(chatroom));
		sql.append("' AND user_id = '");
		sql.append(bts.check_quote(userId));
		sql.append("'");
		bgd.execute(sql.toString());
		sql.delete(0, sql.length());
		sql.append("INSERT INTO t_chatroom (chatroom, user_id, life_cycle) VALUES ('");
		sql.append(bts.check_quote(chatroom));
		sql.append("', '");
		sql.append(bts.check_quote(userId));
		sql.append("', ");
		sql.append(lifeCycle).append(")");
		bgd.execute(sql.toString());
		this.refreshOnline();
		return;
	}
	
	//更新用户的生命周期
	public void updateLife() throws Exception {
		this.addUser();
		return;
	}

	//删除用户
	public void removeUser() throws Exception {
		StringBuffer sql = new StringBuffer();
		beanElearnTools bts = new beanElearnTools();
		sql.append("DELETE FROM t_chatroom WHERE chatroom = '");
		sql.append(bts.check_quote(chatroom));
		sql.append("' AND user_id = '");
		sql.append(bts.check_quote(userId));
		sql.append("'");
		bgd.execute(sql.toString());
		this.refreshOnline();
		return;
	}

	//删除过期用户
	public void removeDeadUser() throws Exception {
		StringBuffer sql = new StringBuffer();
		beanElearnTools bts = new beanElearnTools();
		long deadlife = System.currentTimeMillis() - this.life;
		//System.out.println("deadUser life: " + deadlife);
		sql.append("DELETE FROM t_chatroom WHERE life_cycle < ");
		sql.append(deadlife);
		bgd.execute(sql.toString());
		return;
		
	}
	
	//刷新在线列表
	public void refreshOnline() throws Exception {
		StringBuffer sql = new StringBuffer();
		beanElearnTools bts = new beanElearnTools();
		this.removeDeadUser();
		sql.append("SELECT * FROM t_chatroom WHERE chatroom = '");
		sql.append(bts.check_quote(chatroom));
		sql.append("'");
		bgd.executeSelect(sql.toString());
		return;
	}
	
	//获取在线名单
	public String getOnlineName(int i) {
		return bgd.getFieldValue("user_id", i);
	}
	
	//获取在线人数
	public int getOnlineCount() {
		return bgd.getRowCount();
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

	public String getLifeCycle() {
		return (this.lifeCycle); 
	}

	public void setLifeCycle(String lifeCycle) {
		this.lifeCycle = lifeCycle; 
	}
	
	public long getLife() {
		return (this.life); 
	}

	public void setLife(long life) {
		this.life = life; 
	}

	public String toString() {
		String ret = null;
		ret = "chatroom = " + chatroom + "\n";
		ret += "userId = " + userId + "\n";
		ret += "lifeCycle = " + lifeCycle + "\n";
		return ret;
	}

}

