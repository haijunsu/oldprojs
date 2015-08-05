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
 * <P> �û���־ </P>
 * 
 * @version 0.1
 * @author XIAOAI
 */
public class FunActionLog {
	
	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(FunActionLog.class);
	private CommSQL sis = new CommSQL();
	private String  act_datetime;         //����ʱ��
	private String 	act_user;              //������
	private String 	act_ip;                //IP��ַ
	private String 	act_from;              //��Դ
	private String 	act_do;                //�û�����

	private String 	act_table;             //��������
	private String 	act_tado;              //���������
	private String 	act_memo;              //��ע
	private String  act_me;                //����
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
	 * <P>д���ݿ���־<P>
	 * @param ��;�����ȵ��� setAct_datetime(String);setAct_user(String);
	 * setAct_ip(String);setAct_from(String);setAct_do(String);setAct_table(String);
	 * setAct_tado(String);setAct_memo(String);setAct_me(String);
	 * @return 0��δ��������1���ɹ���-1��ʧ��
	 */
	public int setActionLog(){
		int i_return = 0;
		log.info("д��־����setActionLog");
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
		DataBean db = new DataBean();
		String strdate = "";
		String strtime = "";
		Calendar now;
		if (!act_datetime.equals("")) {
			//�Ѿ���ֵ
			return act_datetime;
		} 
		try {
			//ȡ���ݿ��ֵ
			db.executeSelect("values (current date,current time)");
			strdate = db.getElementValue(0,0).trim();
			strtime = db.getElementValue(0,1).trim();
		} catch (SQLException e) {
			try{
				//ȡAPPLICATION��ֵ
				now = Calendar.getInstance();
				//ȡ��
				strdate = Integer.toString(now.get(Calendar.YEAR));
				//ȡ��
				int ntemp = now.get(Calendar.MONTH) + 1;
				String strtemp = Integer.toString(ntemp).trim();
				if(strtemp.length() == 1) strtemp = "0" + strtemp;
				strdate = strdate + "-" + strtemp;
				//ȡ��
				ntemp = now.get(Calendar.DAY_OF_MONTH);
				strtemp = Integer.toString(ntemp).trim();
				if(strtemp.length() == 1) strtemp = "0" + strtemp;
				strdate = strdate + "-" + strtemp;
				//ȡСʱ
				strtime = Integer.toString(now.get(Calendar.HOUR_OF_DAY));
				if(strtime.length() == 1) strtime = "0" + strtime;
				//ȡ��
				strtemp = Integer.toString(now.get(Calendar.MINUTE)).trim();
				if(strtemp.length() == 1) strtemp = "0" + strtemp;
				strtime = strtime + "-" + strtemp;
				//ȡ��
				strtemp = Integer.toString(now.get(Calendar.SECOND)).trim();
				if(strtemp.length() == 1) strtemp = "0" + strtemp;
				strtime = strtime + "-" + strtemp;
			}catch(Exception ex){
				strdate = "";
				strtime = "";
				log.debug("�������" + ex.getMessage());
			}
			e.printStackTrace();
			log.debug("ȡʱ���SQL������" + e.getMessage());
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
