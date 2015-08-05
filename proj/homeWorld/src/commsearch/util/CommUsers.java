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
 * <P>公用取用户相关信息工具类</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class CommUsers {

	//利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommUsers.class);
	
	String userstablename[] = null;     //用户表的姓名;
	String usersfielduserid[] = null;   //用户ID的字段名()
	String usersfieldname[] = null;     //用户姓名的字段名

	Map mapusername = null;             //全部用户名

	/**
	* Constructor
	*/
	public CommUsers() {
		super();
		//先送进去默认值
		String[] tablename = {"VNDINFO","employee","associator"};
		String[] userid = {"VNDNUM","userid","userid"};
		String[] name = {"VNDNAM","namec","name"};
		setUserstablename(tablename);
		setUsersfielduserid(userid);
		setUsersfieldname(name);
	}
	/**
	 * 取出对应USERID的名字
	 * (如果需要一次性加载,可以先调用setUserName())
	 * 
	 * @param String userid 用户ID
	 * @return String 用户姓名
	 */
	public String getUserName(String userid) {
		String s_return = "";
		String s_temp[][];
		commsearch.CommSQL cs = new commsearch.CommSQL();
		StringBuffer s_sql = new StringBuffer("");
		
		//看一下是否一次性加载过了
		if (getMapusername()==null) {
			//没有就在数据库中找
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
	 * 一次性将用户姓名加载
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
						log.debug("map.put(" + s_temp[i_forj][0].trim()+":"+s_temp[i_forj][1].trim()+")有错!" + "\n" + e.getMessage());
					}
				}
			}		 						
		}
		this.setMapusername(this.mapusername);		
	}
	/**
	 * @param String 用户ID
	 * @return String 用户姓名
	 */
	public String getMapusername(String userid) {
		String s_return = "";
		try {
			s_return =(String) getMapusername().get(userid);
		} catch (Exception e){
			log.debug("map.get(" + userid+")有错!" +"\n" + e.getMessage());
			s_return = "";
		}
		return s_return;
	}
	
	/**
	 * 设定用户姓名字段的名称(一个)
	 * @param strings
	 */
	public void setUsersfieldname(String strings) {
		String[] s_temp = new String[1];
		s_temp[0] = strings;
		setUsersfieldname(s_temp[0]);
	}

	/**
	 * 设定用户ID字段的名称(一个)
	 * @param strings
	 */
	public void setUsersfielduserid(String strings) {
		String[] s_temp = new String[1];
		s_temp[0] = strings;
		setUsersfielduserid(s_temp[0]);

	}

	/**
	 * 设定用户表名的名称(一个)
	 * @param strings
	 */
	public void setUserstablename(String strings) {
		String[] s_temp = new String[1];
		s_temp[0] = strings;
		setUserstablename(s_temp[0]);

	}
	


	/**
	 * 取出用户姓名字段的名称数组
	 * @return
	 */
	public String[] getUsersfieldname() {
		return this.usersfieldname;
	}

	/**
	 * 取出用户ID字段的名称数组
	 * @return
	 */
	public String[] getUsersfielduserid() {
		return this.usersfielduserid;
	}

	/**
	 * 取出用户表名的名称数组
	 * @return
	 */
	public String[] getUserstablename() {
		return this.userstablename;
	}

	/**
	 * 设定用户姓名字段的名称数组
	 * @param strings
	 */
	public void setUsersfieldname(String[] strings) {
		this.usersfieldname = strings;
	}

	/**
	 * 设定用户ID字段的名称数组
	 * @param strings
	 */
	public void setUsersfielduserid(String[] strings) {
		this.usersfielduserid = strings;
	}

	/**
	 * 设定用户表名字段的名称数组
	 * @param strings
	 */
	public void setUserstablename(String[] strings) {
		this.userstablename = strings;
	}

	/**
	 * 取出一次性加载的全部用户姓名MAP
	 * @return MAP
	 */
	public Map getMapusername() {
		return this.mapusername;
	}

	/**
	 * 设定一次性加载的全部用户姓名MAP
	 * @param map
	 */
	public void setMapusername(Map map) {
		this.mapusername = map;
	}

}