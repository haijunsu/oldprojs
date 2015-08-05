/**
 * @(#)CommUsers.java  2003-11-19
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch.homeworld;

//import java.util.*;
//import javax.servlet.http.*;

/**
 * <P>HomeWorld���û�����������ص���Ϣ</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class CommUsers {

	//���÷�װBean-com.idn.log.LogWrapper��������־��¼
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommUsers.class);
	
	/**
	* Constructor
	*/
	public CommUsers() {
		super();
	}

	/**
		 * ȡ����ӦGROUPID����������
		 * 
		 * 
		 * @param String groupid ��ID
		 * @return String �û����ִ����á������ָ��
		 */
	public String getGroupUser(String groupid) {
		
		String s_return="";
		String s_temp[][];
		commsearch.CommSQL cs = new commsearch.CommSQL();
		s_temp = cs.executeSelect("select users.userid from users,usersgro where users.userid = usersgro.userid and  usersgro.groupid= '" +groupid + "'");
		if (s_temp == null ) return s_return;
		if (s_temp.length == 0 ) return s_return;		
		for (int i_fori=0;i_fori<s_temp.length;i_fori++){
			if (s_return.equals("")){
				s_return = s_temp[i_fori][0];	
			} else {
				s_return = s_return + "," + s_temp[i_fori][0];
			} 
		}
		return s_return;
	
	}
	

	/**
		 * ȡ����ӦUSERID����������
		 * 
		 * 
		 * @param String userid �û�ID
		 * @return String ����ִ����á������ָ��
		 */
	public String getUserGroup(String userid) {
		
		String s_return="";
		String s_temp[][];
		commsearch.CommSQL cs = new commsearch.CommSQL();
		s_temp = cs.executeSelect("select usersgro.groupid from users,usersgro where users.userid = usersgro.userid and  users.userid= '" +userid + "'");
		if (s_temp == null ) return s_return;
		if (s_temp.length == 0 ) return s_return;		
		for (int i_fori=0;i_fori<s_temp.length;i_fori++){
			if (s_return.equals("")){
				s_return = s_temp[i_fori][0];	
			} else {
				s_return = s_return + "," + s_temp[i_fori][0];
			} 
		}
		return s_return;
	
	}
	
	
	/**
	 * ȡ����ӦUSERID������
	 * (�����Ҫһ���Լ���,�����ȵ���setUserName())
	 * 
	 * @param String userid �û�ID
	 * @return String �û�����
	 */
	public String getSameUserString(String userid) {
		String s_return = "";
		String s_temp[][];
		commsearch.CommSQL cs = new commsearch.CommSQL();
		s_temp = cs.executeSelect("SELECT USERID FROM USERS WHERE USERIDG = '" +userid + "'");
		if (s_temp == null ) return userid;
		if (s_temp.length == 0 ) return userid;
		for (int i_fori=0;i_fori<s_temp.length;i_fori++){
			if (s_return.equals("")){
				s_return = s_temp[i_fori][0];	
			} else {
				s_return = s_return + "," + s_temp[i_fori][0];
			} 
		}
		return s_return;
	}

}