/**
 * @(#)CommUsers.java  2003-11-19
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch.util;

import java.util.*;
//import javax.servlet.http.*;

/**
 * <P>����ȡ�û������Ϣ������</P>
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
	
	String userstablename[] = null;     //�û��������;
	String usersfielduserid[] = null;   //�û�ID���ֶ���()
	String usersfieldname[] = null;     //�û��������ֶ���

	Map mapusername = null;             //ȫ���û���

	/**
	* Constructor
	*/
	public CommUsers() {
		super();
		//���ͽ�ȥĬ��ֵ
		String[] tablename = {"VNDINFO","employee","associator"};
		String[] userid = {"VNDNUM","userid","userid"};
		String[] name = {"VNDNAM","namec","name"};
		setUserstablename(tablename);
		setUsersfielduserid(userid);
		setUsersfieldname(name);
	}
	/**
	 * ȡ����ӦUSERID������
	 * (�����Ҫһ���Լ���,�����ȵ���setUserName())
	 * 
	 * @param String userid �û�ID
	 * @return String �û�����
	 */
	public String getUserName(String userid) {
		String s_return = "";
		String s_temp[][];
		commsearch.CommSQL cs = new commsearch.CommSQL();
		StringBuffer s_sql = new StringBuffer("");
		
		//��һ���Ƿ�һ���Լ��ع���
		if (getMapusername()==null) {
			//û�о������ݿ�����
			for (int i_fori=0;i_fori<getUserstablename().length;i_fori++){
				s_temp = null;
				s_sql.append("select " + getUsersfieldname()[i_fori]); 
				s_sql.append(" from " + getUserstablename()[i_fori]);
				s_sql.append(" where " + getUsersfielduserid()[i_fori] + "='"+userid+"'");
				s_temp = cs.executeSelect(s_sql.toString());
				if (s_temp==null){
					s_return = "";
				} else {
					s_return = s_temp[0][0].trim();
					break;
				}		 						
			}
		}else {
			s_return = 	getMapusername(userid);
		} 
		return s_return;
	}
	/**
	 * һ���Խ��û���������
	 * 
	 * @param 
	 * @return 
	 */
	public void setUserName() {
		this.mapusername = new TreeMap();
		String s_temp[][];
		commsearch.CommSQL cs = new commsearch.CommSQL();
		StringBuffer s_sql;

		for (int i_fori=getUserstablename().length - 1;i_fori>=0;i_fori--){
			s_temp = null;
			s_sql = new StringBuffer("");
			s_sql.append("select "+ getUsersfielduserid()[i_fori] +" , " + getUsersfieldname()[i_fori]); 
			s_sql.append(" from " + getUserstablename()[i_fori]);
			s_temp = cs.executeSelect(s_sql.toString());
			if (s_temp!=null){
				for (int i_forj=0;i_forj<s_temp.length;i_forj++){
					try {
						this.mapusername.put(s_temp[i_forj][0].trim(),s_temp[i_forj][1].trim());
					} catch (Exception e){
						log.debug("map.put(" + s_temp[i_forj][0].trim()+":"+s_temp[i_forj][1].trim()+")�д�!" + "\n" + e.getMessage());
					}
				}
			}		 						
		}
		this.setMapusername(this.mapusername);		
	}
	/**
	 * @param String �û�ID
	 * @return String �û�����
	 */
	public String getMapusername(String userid) {
		String s_return = "";
		try {
			s_return =(String) getMapusername().get(userid);
		} catch (Exception e){
			log.debug("map.get(" + userid+")�д�!" +"\n" + e.getMessage());
			s_return = "";
		}
		return s_return;
	}
	
	/**
	 * �趨�û������ֶε�����(һ��)
	 * @param strings
	 */
	public void setUsersfieldname(String strings) {
		String[] s_temp = new String[1];
		s_temp[0] = strings;
		setUsersfieldname(s_temp[0]);
	}

	/**
	 * �趨�û�ID�ֶε�����(һ��)
	 * @param strings
	 */
	public void setUsersfielduserid(String strings) {
		String[] s_temp = new String[1];
		s_temp[0] = strings;
		setUsersfielduserid(s_temp[0]);

	}

	/**
	 * �趨�û�����������(һ��)
	 * @param strings
	 */
	public void setUserstablename(String strings) {
		String[] s_temp = new String[1];
		s_temp[0] = strings;
		setUserstablename(s_temp[0]);

	}
	


	/**
	 * ȡ���û������ֶε���������
	 * @return
	 */
	public String[] getUsersfieldname() {
		return this.usersfieldname;
	}

	/**
	 * ȡ���û�ID�ֶε���������
	 * @return
	 */
	public String[] getUsersfielduserid() {
		return this.usersfielduserid;
	}

	/**
	 * ȡ���û���������������
	 * @return
	 */
	public String[] getUserstablename() {
		return this.userstablename;
	}

	/**
	 * �趨�û������ֶε���������
	 * @param strings
	 */
	public void setUsersfieldname(String[] strings) {
		this.usersfieldname = strings;
	}

	/**
	 * �趨�û�ID�ֶε���������
	 * @param strings
	 */
	public void setUsersfielduserid(String[] strings) {
		this.usersfielduserid = strings;
	}

	/**
	 * �趨�û������ֶε���������
	 * @param strings
	 */
	public void setUserstablename(String[] strings) {
		this.userstablename = strings;
	}

	/**
	 * ȡ��һ���Լ��ص�ȫ���û�����MAP
	 * @return MAP
	 */
	public Map getMapusername() {
		return this.mapusername;
	}

	/**
	 * �趨һ���Լ��ص�ȫ���û�����MAP
	 * @param map
	 */
	public void setMapusername(Map map) {
		this.mapusername = map;
	}

}