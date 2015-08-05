/**
 * @(#)CommActionLog.java  2003-11-19
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch.util;


//import java.util.*;
//import javax.servlet.http.*;

import commsearch.CommSQL;

//import com.idn.sql.DataBean;
//import javax.servlet.ServletException;
//import javax.servlet.http.*;

//import org.apache.struts.action.*;
//import org.apache.struts.util.MessageResources;
//import org.apache.commons.beanutils.PropertyUtils;
/**
 * <P>公用日志相关工具类</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class CommActionLog {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommActionLog.class);
	
	private String  act_datetime;         //操作时间
	private String 	act_user;              //操作人
	private String 	act_ip;                //IP地址
	private String 	act_from;              //来源
	private String 	act_do;                //用户操作
	private String 	act_key;                //用户操作
	private String 	act_table;             //操作表名
	private String 	act_tado;              //操作表操作
	private String 	act_memo;              //备注
	private String  act_me;                //其它
	/**
	 * 
	 */
	public CommActionLog() {
		super();
		setAct_datetime("");
		setAct_user("");    
		setAct_ip("");      
		setAct_from("");    
		setAct_do("");      
		setAct_key("");
		setAct_table("");   
		setAct_tado("");    
		setAct_memo("");    
		setAct_me("");      

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
							String s_from,String s_do,String s_key,String s_table,
							String s_tado,String s_memo,String s_me){
			setAct_datetime(s_datetime);
			setAct_user(s_user);
			setAct_ip(s_ip);
			setAct_from(s_from);
			setAct_do(s_do);
			setAct_key(s_key);
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
		String[][] temp = null;
		log.info("CommActionLog:setActionLog");
		try{
			
			//先查一下是否已经有了
//			cal.setAct_user(uname);
//			cal.setAct_from("homeworldErthdr");

			CommSQL sis = new CommSQL();

/*
			StringBuffer sb_select = new StringBuffer("");
			sb_select = sb_select.append("SELECT * FROM ACTIONLOG");
			sb_select = sb_select.append(" WHERE ACT_IP='");
			sb_select = sb_select.append(getAct_ip());
			sb_select = sb_select.append("' AND ACT_TABLE='");
			sb_select = sb_select.append(getAct_table());
			sb_select = sb_select.append("' AND ACT_KEY='");
			sb_select = sb_select.append(getAct_key());
			sb_select = sb_select.append("' AND ACT_DO='");
			sb_select = sb_select.append(getAct_do());
			sb_select = sb_select.append("' AND ACT_FROM='");
			sb_select = sb_select.append(getAct_from());
			sb_select = sb_select.append("'");
			log.debug("查一下是已经写过日志了：" + sb_select.toString());
			temp =sis.executeSelect(sb_select.toString()); 
			if (temp==null) {
				//无
			} else {
				//已经有
				log.debug("已经写过了！");
				return 0;
			}
*/			
			
			if (getAct_user().trim().equals("")) return 0;
			StringBuffer sb_sqlstr = new StringBuffer("");
			sb_sqlstr = sb_sqlstr.append("Insert Into ACTIONLOG ");
			sb_sqlstr = sb_sqlstr.append("(ACT_DATETIME, ACT_USER, ACT_IP, ACT_FROM, ACT_DO, ACT_KEY, ACT_TABLE, ACT_TADO, ACT_MEMO, ACT_ME)");
			sb_sqlstr = sb_sqlstr.append(" Values ( ");
//			sb_sqlstr = sb_sqlstr.append("GENERATE_UNIQUE()");
//			sb_sqlstr = sb_sqlstr.append(" , ");
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
			sb_sqlstr = sb_sqlstr.append("'" + getAct_key() + "'");
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
		String s_datetime="";
		CommDate cd = new CommDate();
		s_datetime = cd.getNow(cd.FORMAT_DATETIME_SIGN);
		return s_datetime;
	}

	/**
	 * @return
	 */
	public String getAct_datetime() {
		if (this.act_datetime.trim().equals("")) this.act_datetime = getDatetime();
		return this.act_datetime;
	}

	/**
	 * @return
	 */
	public String getAct_do() {
		return this.act_do;
	}

	/**
	 * @return
	 */
	public String getAct_from() {
		return this.act_from;
	}

	/**
	 * @return
	 */
	public String getAct_ip() {
		return this.act_ip;
	}

	/**
	 * @return
	 */
	public String getAct_me() {
		return this.act_me;
	}

	/**
	 * @return
	 */
	public String getAct_memo() {
		return this.act_memo;
	}

	/**
	 * @return
	 */
	public String getAct_table() {
		return this.act_table;
	}

	/**
	 * @return
	 */
	public String getAct_tado() {
		return this.act_tado;
	}

	/**
	 * @return
	 */
	public String getAct_user() {
		return this.act_user;
	}

	/**
	 * @param string
	 */
	public void setAct_datetime(String string) {
		this.act_datetime = string;
	}

	/**
	 * @param string
	 */
	public void setAct_do(String string) {
		this.act_do = string;
	}

	/**
	 * @param string
	 */
	public void setAct_from(String string) {
		this.act_from = string;
	}

	/**
	 * @param string
	 */
	public void setAct_ip(String string) {
		this.act_ip = string;
	}

	/**
	 * @param string
	 */
	public void setAct_me(String string) {
		this.act_me = string;
	}

	/**
	 * @param string
	 */
	public void setAct_memo(String string) {
		this.act_memo = string;
	}

	/**
	 * @param string
	 */
	public void setAct_table(String string) {
		this.act_table = string;
	}

	/**
	 * @param string
	 */
	public void setAct_tado(String string) {
		this.act_tado = string;
	}

	/**
	 * @param string
	 */
	public void setAct_user(String string) {
		this.act_user = string;
	}
	
	/**
	 * @return
	 */
	public String getAct_key() {
		return this.act_key;
	}

	/**
	 * @param string
	 */
	public void setAct_key(String string) {
		this.act_key = string;
	}
	public void setAll(){
		setAct_datetime("");
		setAct_user("");    
		setAct_ip("");      
		setAct_from("");    
		setAct_do("");      
		setAct_key("");
		setAct_table("");   
		setAct_tado("");    
		setAct_memo("");    
		setAct_me("");      			
	}
	public void clear(){

		setAct_datetime(null);
		setAct_user(null);    
		setAct_ip(null);      
		setAct_from(null);    
		setAct_do(null);      
		setAct_key(null);
		setAct_table(null);   
		setAct_tado(null);    
		setAct_memo(null);    
		setAct_me(null);      
		
	}
	
	
	/**
	 * 临时用来监测CLASS的程序,放在入口处,和所有的出口处.
	 * 所有的CLASS将会在监测数据库中出现两次才是正确的
	 *  @param  Long(20,0) ACT_DATETIME  开始执行程序的时间(YYYYMMDDHHMMSSXXX)
	 *                                    在一个CLASS中各处都是开始执行的时间
		@param  String(20) ACT_USER      登录的用户ID
		@param  String(32) ACT_SESSION   SESSION的session.getId()
		@param  String(30) ACT_FROM      监测的CLASS名
		@param  String(1)  ACT_TYPE      类型(B-开始,E-正常结束,C-Catch结束)
		@param  String(20) ACT_POCNO     本CLASS的监测点(自己编的号,或其它)
		@param  Long(10,0) ACT_RUNT      本CLASS的运行时间(0.001秒为1)
	 */
	public static void setTempLog(
			long ACT_DATETIME, 
			String ACT_USER, 
			String ACT_SESSION, 
			String ACT_FROM,
			String ACT_TYPE,
			String ACT_POCNO,
			long ACT_RUNT){
		CommSQL cs = new CommSQL();
		String s_sql="";
		s_sql = "INSERT INTO TEMPLOG ( ACT_DATETIME,ACT_USER,ACT_SESSION,ACT_FROM,ACT_TYPE,ACT_POCNO,ACT_RUNT ) VALUES (";
		s_sql = s_sql + Long.toString(ACT_DATETIME) + ",";
		s_sql = s_sql + "'" + ACT_USER + "',";
		s_sql = s_sql + "'" + ACT_SESSION + "',";
		s_sql = s_sql + "'" + ACT_FROM + "',";
		s_sql = s_sql + "'" + ACT_TYPE + "',";
		s_sql = s_sql + "'" + ACT_POCNO + "',";
		s_sql = s_sql + Long.toString(ACT_RUNT) ;
		s_sql = s_sql + ")";
		if (cs.executeBatchSQL(s_sql)==-1){
			log.error("CommActionLog:setTempLog ERROR");
		} else {
			log.debug("CommActionLog:setTempLog OK");
		}
		cs = null;
	}
}
