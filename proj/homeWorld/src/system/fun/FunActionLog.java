/*
 * @(#)FunActinLog.java  2003-6-20
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package system.fun;

import java.sql.SQLException;
import java.util.*;
import com.idn.sql.*;
import commsearch.CommSQL;
/**
 * <P> 用户日志 </P>
 * 
 * @version 0.1
 * @author XIAOAI
 */
public class FunActionLog {
	
	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(FunActionLog.class);
	private CommSQL sis = new CommSQL();
	private String  act_datetime;         //操作时间
	private String 	act_user;              //操作人
	private String 	act_ip;                //IP地址
	private String 	act_from;              //来源
	private String 	act_do;                //用户操作

	private String 	act_table;             //操作表名
	private String 	act_tado;              //操作表操作
	private String 	act_memo;              //备注
	private String  act_me;                //其它
	/**
	 * 
	 */
	public FunActionLog() {
		super();
		act_datetime = "";
		act_user = "";    
		act_ip = "";      
		act_from = "";    
		act_do = "";      
             
		act_table = "";   
		act_tado = "";    
		act_memo = "";    
		act_me = "";      

	}
	/**
	 * <P>写数据库日志<P>
	 * @param String  时间,自动取时可以传入""
	 * @param String  操作人 登录的用户
	 * @param String  操作人的IP地址
	 * @param String  调用模块
	 * @param String  操作(Search,Update,Insert,Delete等等)
	 * @param String  操作数据库表名
	 * @param String  对数据表的操作(INS,UPD,DEL等等)
	 * @param String  备注
	 * @param String  扩展用
	 * @return 0、未作操作；1、成功；-1、失败
	 */
	public int setActionLog(String s_datetime,String s_user,String s_ip,
	                        String s_from,String s_do,String s_table,
	                        String s_tado,String s_memo,String s_me){
			setAct_datetime(s_datetime);
			setAct_user(s_user);
			setAct_ip(s_ip);
			setAct_from(s_from);
			setAct_do(s_do);
			setAct_table(s_table);
			setAct_tado(s_tado);
			setAct_memo(s_memo);
			setAct_me(s_me);
			return setActionLog();
		}
	/**
	 * <P>写数据库日志<P>
	 * @param 无;必须先调用 setAct_datetime(String);setAct_user(String);
	 * setAct_ip(String);setAct_from(String);setAct_do(String);setAct_table(String);
	 * setAct_tado(String);setAct_memo(String);setAct_me(String);
	 * @return 0、未作操作；1、成功；-1、失败
	 */
	public int setActionLog(){
		int i_return = 0;
		log.info("写日志程序setActionLog");
		try{
			if (getAct_user().trim().equals("")) return 0;
			StringBuffer sb_sqlstr = new StringBuffer("");
			sb_sqlstr = sb_sqlstr.append("Insert Into ACTIONLOG Values ( ");
			sb_sqlstr = sb_sqlstr.append("GENERATE_UNIQUE()");
			sb_sqlstr = sb_sqlstr.append(" , ");
			sb_sqlstr = sb_sqlstr.append("'" + getAct_datetime() + "'");
			sb_sqlstr = sb_sqlstr.append(" , ");
			sb_sqlstr = sb_sqlstr.append("'" + getAct_user() + "'");
			sb_sqlstr = sb_sqlstr.append(" , ");
			sb_sqlstr = sb_sqlstr.append("'" + getAct_ip() + "'");		
			sb_sqlstr = sb_sqlstr.append(" , ");
			sb_sqlstr = sb_sqlstr.append("'" + getAct_from() + "'");
			sb_sqlstr = sb_sqlstr.append(" , ");
			sb_sqlstr = sb_sqlstr.append("'" + getAct_do() + "'");
			sb_sqlstr = sb_sqlstr.append(" , ");
			sb_sqlstr = sb_sqlstr.append("'" + getAct_table() + "'");		
			sb_sqlstr = sb_sqlstr.append(" , ");
			sb_sqlstr = sb_sqlstr.append("'" + getAct_tado() + "'");		
			sb_sqlstr = sb_sqlstr.append(" , ");
			sb_sqlstr = sb_sqlstr.append("'" + getAct_memo() + "'");		
			sb_sqlstr = sb_sqlstr.append(" , ");
			sb_sqlstr = sb_sqlstr.append("'" + getAct_me() + "'");		
			sb_sqlstr = sb_sqlstr.append(")");
			if (sis.executeBatchSQL(sb_sqlstr.toString())==1){
				//成功
				i_return = 1;
			} else{
				//出错
				i_return = -1;
				log.error("写日志：SQL语句出现问题："+sb_sqlstr.toString());
			}
		} catch (Exception e){
			i_return = -1;
			e.printStackTrace();
			log.error("写日志：出现异常错误");
		}
		return i_return;
	}
	/**
	 * <P>取出当前数据库的时间<P>
	 * @param  
	 * @return String 时间串(YYYY-MM-DD HH:MM:SS)
	 */
	
	public String getDatetime(){
		DataBean db = new DataBean();
		String strdate = "";
		String strtime = "";
		Calendar now;
		if (!act_datetime.equals("")) {
			//已经有值
			return act_datetime;
		} 
		try {
			//取数据库的值
			db.executeSelect("values (current date,current time)");
			strdate = db.getElementValue(0,0).trim();
			strtime = db.getElementValue(0,1).trim();
		} catch (SQLException e) {
			try{
				//取APPLICATION的值
				now = Calendar.getInstance();
				//取年
				strdate = Integer.toString(now.get(Calendar.YEAR));
				//取月
				int ntemp = now.get(Calendar.MONTH) + 1;
				String strtemp = Integer.toString(ntemp).trim();
				if(strtemp.length() == 1) strtemp = "0" + strtemp;
				strdate = strdate + "-" + strtemp;
				//取日
				ntemp = now.get(Calendar.DAY_OF_MONTH);
				strtemp = Integer.toString(ntemp).trim();
				if(strtemp.length() == 1) strtemp = "0" + strtemp;
				strdate = strdate + "-" + strtemp;
				//取小时
				strtime = Integer.toString(now.get(Calendar.HOUR_OF_DAY));
				if(strtime.length() == 1) strtime = "0" + strtime;
				//取分
				strtemp = Integer.toString(now.get(Calendar.MINUTE)).trim();
				if(strtemp.length() == 1) strtemp = "0" + strtemp;
				strtime = strtime + "-" + strtemp;
				//取秒
				strtemp = Integer.toString(now.get(Calendar.SECOND)).trim();
				if(strtemp.length() == 1) strtemp = "0" + strtemp;
				strtime = strtime + "-" + strtemp;
			}catch(Exception ex){
				strdate = "";
				strtime = "";
				log.debug("程序出错：" + ex.getMessage());
			}
			e.printStackTrace();
			log.debug("取时间的SQL语句出错：" + e.getMessage());
		}
		
		return strdate + " " + strtime;
	}

	/**
	 * @return
	 */
	public String getAct_datetime() {
		if (act_datetime.trim().equals("")) act_datetime = getDatetime();
		return act_datetime;
	}

	/**
	 * @return
	 */
	public String getAct_do() {
		return act_do;
	}

	/**
	 * @return
	 */
	public String getAct_from() {
		return act_from;
	}

	/**
	 * @return
	 */
	public String getAct_ip() {
		return act_ip;
	}

	/**
	 * @return
	 */
	public String getAct_me() {
		return act_me;
	}

	/**
	 * @return
	 */
	public String getAct_memo() {
		return act_memo;
	}

	/**
	 * @return
	 */
	public String getAct_table() {
		return act_table;
	}

	/**
	 * @return
	 */
	public String getAct_tado() {
		return act_tado;
	}

	/**
	 * @return
	 */
	public String getAct_user() {
		return act_user;
	}

	/**
	 * @param string
	 */
	public void setAct_datetime(String string) {
		act_datetime = string;
	}

	/**
	 * @param string
	 */
	public void setAct_do(String string) {
		act_do = string;
	}

	/**
	 * @param string
	 */
	public void setAct_from(String string) {
		act_from = string;
	}

	/**
	 * @param string
	 */
	public void setAct_ip(String string) {
		act_ip = string;
	}

	/**
	 * @param string
	 */
	public void setAct_me(String string) {
		act_me = string;
	}

	/**
	 * @param string
	 */
	public void setAct_memo(String string) {
		act_memo = string;
	}

	/**
	 * @param string
	 */
	public void setAct_table(String string) {
		act_table = string;
	}

	/**
	 * @param string
	 */
	public void setAct_tado(String string) {
		act_tado = string;
	}

	/**
	 * @param string
	 */
	public void setAct_user(String string) {
		act_user = string;
	}

}
