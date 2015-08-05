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
 * <P>������־��ع�����</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class CommActionLog {

	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommActionLog.class);
	
	private String  act_datetime;         //����ʱ��
	private String 	act_user;              //������
	private String 	act_ip;                //IP��ַ
	private String 	act_from;              //��Դ
	private String 	act_do;                //�û�����
	private String 	act_key;                //�û�����
	private String 	act_table;             //��������
	private String 	act_tado;              //���������
	private String 	act_memo;              //��ע
	private String  act_me;                //����
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
	 * <P>д���ݿ���־<P>
	 * @param String  ʱ��,�Զ�ȡʱ���Դ���""
	 * @param String  ������ ��¼���û�
	 * @param String  �����˵�IP��ַ
	 * @param String  ����ģ��
	 * @param String  ����(Search,Update,Insert,Delete�ȵ�)
	 * @param String  �������ݿ����
	 * @param String  �����ݱ�Ĳ���(INS,UPD,DEL�ȵ�)
	 * @param String  ��ע
	 * @param String  ��չ��
	 * @return 0��δ��������1���ɹ���-1��ʧ��
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
	 * <P>д���ݿ���־<P>
	 * @param ��;�����ȵ��� setAct_datetime(String);setAct_user(String);
	 * setAct_ip(String);setAct_from(String);setAct_do(String);setAct_table(String);
	 * setAct_tado(String);setAct_memo(String);setAct_me(String);
	 * @return 0��δ��������1���ɹ���-1��ʧ��
	 */
	public int setActionLog(){
		int i_return = 0;
		String[][] temp = null;
		log.info("CommActionLog:setActionLog");
		try{
			
			//�Ȳ�һ���Ƿ��Ѿ�����
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
			log.debug("��һ�����Ѿ�д����־�ˣ�" + sb_select.toString());
			temp =sis.executeSelect(sb_select.toString()); 
			if (temp==null) {
				//��
			} else {
				//�Ѿ���
				log.debug("�Ѿ�д���ˣ�");
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
				//�ɹ�
				i_return = 1;
			} else{
				//����
				i_return = -1;
				log.error("д��־��SQL���������⣺"+sb_sqlstr.toString());
			}
		} catch (Exception e){
			i_return = -1;
			e.printStackTrace();
			log.error("д��־�������쳣����");
		}
		return i_return;
	}
	/**
	 * <P>ȡ����ǰ���ݿ��ʱ��<P>
	 * @param  
	 * @return String ʱ�䴮(YYYY-MM-DD HH:MM:SS)
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
	 * ��ʱ�������CLASS�ĳ���,������ڴ�,�����еĳ��ڴ�.
	 * ���е�CLASS�����ڼ�����ݿ��г������β�����ȷ��
	 *  @param  Long(20,0) ACT_DATETIME  ��ʼִ�г����ʱ��(YYYYMMDDHHMMSSXXX)
	 *                                    ��һ��CLASS�и������ǿ�ʼִ�е�ʱ��
		@param  String(20) ACT_USER      ��¼���û�ID
		@param  String(32) ACT_SESSION   SESSION��session.getId()
		@param  String(30) ACT_FROM      ����CLASS��
		@param  String(1)  ACT_TYPE      ����(B-��ʼ,E-��������,C-Catch����)
		@param  String(20) ACT_POCNO     ��CLASS�ļ���(�Լ���ĺ�,������)
		@param  Long(10,0) ACT_RUNT      ��CLASS������ʱ��(0.001��Ϊ1)
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
