/**
 * @(#)CommDate.java  2003-11-19
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch.util;

import java.util.*;
import java.text.*;
//import javax.servlet.http.*;

//import com.idn.sql.DataBean;
//import javax.servlet.ServletException;
//import javax.servlet.http.*;

//import org.apache.struts.action.*;
//import org.apache.struts.util.MessageResources;
//import org.apache.commons.beanutils.PropertyUtils;
/**
 * <P>����������ع�����</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class CommDate {


	//���÷�װBean-com.idn.log.LogWrapper��������־��¼
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommDate.class);
	
	
	//ʱ��ĸ�ʽ
	public String FORMAT_ALL_SIGN = "YYYY-MM-DD HH:MM:SS.XXX";
	public String FORMAT_ALL_NOSIGN = "YYYYMMDDHHMMSSXXX";
	public String FORMAT_DATE_SIGN = "YYYY-MM-DD";
	public String FORMAT_DATE_NOSIGN = "YYYYMMDD";
	public String FORMAT_DATETIME_SIGN = "YYYY-MM-DD HH:MM:SS";
	public String FORMAT_DATETIME_NOSIGN = "YYYYMMDDHHMMSS";
	public String FORMAT_TIME_SIGN = "HH:MM:SS";
	public String FORMAT_TIME_NOSIGN = "HHMMSS";

	
	//��,��,��,ʱ,����,΢��
	private String year,month,day,hour,minute,second,millisecond;
	//ʱ��
	private Calendar c;
	/**
	* Constructor
	*/
	public CommDate() {
		super();
		this.c = java.util.Calendar.getInstance();
	}


	/**
	 * ת����ָ���ĸ�ʽ
	 * 
	 * @param String ����
	 * @param String ����ĸ�ʽ��L�������ͣ�C���ַ��ͣ�
	 * 
	 * @return String  ����(YYYY-MM-DD)
	 */	
	public String dateFormat(String s_date,String s_flag){
		String s_return="";
		if (s_date==null) return "";
		s_date = s_date.trim();
		if (s_flag.equals("L")){
			if (s_date.length()>=5 && s_date.length()<=6){
				if (s_date.length()==5){
					s_date = "0" + s_date;
				}
				s_return = "20" + s_date.substring(0,2) + "-" + s_date.substring(2,4) + "-" +  s_date.substring(4,6); 
			}
		}
		
		return s_return;
	}

	/**
	 * ת����ָ���ĸ�ʽ
	 * 
	 * @param String ʱ��
	 * @param String ����ĸ�ʽ��L�������ͣ�C���ַ��ͣ�
	 * 
	 * @return String  ʱ��(HH:MM:SS)
	 */	
	public String timeFormat(String s_time,String s_flag){
		String s_return="";
		if (s_time==null) return "";
		s_time = s_time.trim();
		if (s_flag.equals("L")){
			if (s_time.length()>=5 && s_time.length()<=6){
				if (s_time.length()==5){
					s_time = "0" + s_time;
				}
				s_return = s_time.substring(0,2) + ":" + s_time.substring(2,4) + ":" +  s_time.substring(4,6); 
			}
			if (s_time.length()>=3 && s_time.length()<=4){
				if (s_time.length()==3){
					s_time = "0" + s_time;
				}
				s_return = s_time.substring(0,2) + ":" + s_time.substring(2,4); 
			}
			
		}
		
		return s_return;
	}	
	/**
	 * ȡ����ǰʱ��
	 * 
	 * @param String ����
	 * 
	 * @return boolean true  ��   false   ����
	 */	
	public boolean IsDate(String s_date){
		boolean b_return = false;
		try {
			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			ParsePosition pos = new ParsePosition(0);
			Date date = bartDateFormat.parse(s_date,pos);
			if (date == null) {
				b_return = false;	
			} else {
				log.debug("������:"+date.toString());
				b_return = true;
			}
		}
		catch (Exception ex) {
			log.error("CommDate:IsDate �Ĵ���Ĳ�����������" + ex.getMessage());
		}
		return b_return;
	}
	/**
	 * ȡ����ǰʱ��
	 * 
	 * @param String ʱ��ĸ�ʽ
	 * @return String ���
	 */	
	public String getNow(String s_format){
		StringBuffer s_now = new StringBuffer("");
		String s_year,s_month,s_day,s_hour,s_minute,s_second,s_millisecond;
		try {
			s_year = getYear();
			s_month = getMonth();
			s_day = getDay();
			s_hour = getHour();
			s_minute = getMinute();
			s_second = getSecond();
			s_millisecond = getMillisecond();
			if (s_format.equals(this.FORMAT_ALL_SIGN)){
				s_now.append(s_year);
				s_now.append("-");
				s_now.append(s_month);
				s_now.append("-");
				s_now.append(s_day);
				s_now.append(" ");
				s_now.append(s_hour);
				s_now.append(":");
				s_now.append(s_minute);
				s_now.append(":");
				s_now.append(s_second);
				s_now.append(".");
				s_now.append(s_millisecond);
			}		
			if (s_format.equals(this.FORMAT_ALL_NOSIGN)){
				s_now.append(s_year);
				s_now.append(s_month);
				s_now.append(s_day);
				s_now.append(s_hour);
				s_now.append(s_minute);
				s_now.append(s_second);
				s_now.append(s_millisecond);
			}
			if (s_format.equals(this.FORMAT_DATE_SIGN)){
				s_now.append(s_year);
				s_now.append("-");
				s_now.append(s_month);
				s_now.append("-");
				s_now.append(s_day);
				s_now.append(" ");
			}		
			if (s_format.equals(this.FORMAT_DATE_NOSIGN)){
				s_now.append(s_year);
				s_now.append(s_month);
				s_now.append(s_day);
			}			
			if (s_format.equals(this.FORMAT_DATETIME_SIGN)){
				s_now.append(s_year);
				s_now.append("-");
				s_now.append(s_month);
				s_now.append("-");
				s_now.append(s_day);
				s_now.append(" ");
				s_now.append(s_hour);
				s_now.append(":");
				s_now.append(s_minute);
				s_now.append(":");
				s_now.append(s_second);
			}		
			if (s_format.equals(this.FORMAT_DATETIME_NOSIGN)){
				s_now.append(s_year);
				s_now.append(s_month);
				s_now.append(s_day);
				s_now.append(s_hour);
				s_now.append(s_minute);
				s_now.append(s_second);
			}

		} catch (Exception e){
			e.printStackTrace();
			log.error("CommDate:getNow�����쳣!");
			s_now = new StringBuffer("");				
		}

		return s_now.toString();
	}	

	/**
	 * ȡ����ǰ  ��
	 * @return String
	 */
	public String getYear() {
		String s_year="";
		s_year = Integer.toString(this.c.get(java.util.Calendar.YEAR));
		return s_year;
	}

	/**
	 * ȡ����ǰ  ��
	 * @return String
	 */
	public String getMonth() {
		String s_month="";
		s_month = Integer.toString(this.c.get(java.util.Calendar.MONTH) + 1);
		if (s_month.length()<2) s_month = "0" + s_month;		
		return s_month;
	}

	/**
	 * ȡ����ǰ  ��
	 * @return String
	 */
	public String getDay() {
		String s_day="";
		s_day = Integer.toString(this.c.get(java.util.Calendar.DATE));
		if (s_day.length()<2) s_day = "0" + s_day;		
		return s_day;
	}

	/**
	 * ȡ����ǰ  Сʱ
	 * @return String
	 */
	public String getHour() {
		String s_hour="";
		s_hour = Integer.toString(this.c.get(java.util.Calendar.HOUR_OF_DAY));
		if (s_hour.length()<2) s_hour = "0" + s_hour;		
		return s_hour;
	}

	/**
	 * ȡ����ǰ  ����
	 * @return String
	 */
	public String getMinute() {
		String s_minute="";
		s_minute = Integer.toString(this.c.get(java.util.Calendar.MINUTE));
		if (s_minute.length()<2) s_minute = "0" + s_minute;		
		return s_minute;
	}


	/**
	 * ȡ����ǰ  ��
	 * @return String
	 */
	public String getSecond() {
		String s_second="";
		s_second = Integer.toString(this.c.get(java.util.Calendar.SECOND));
		if (s_second.length()<2) s_second = "0" + s_second;
		
		return s_second;
	}

	/**
	 * ȡ����ǰ  ΢��
	 * @return String
	 */
	public String getMillisecond() {
		String s_millisecond="";
		s_millisecond = Integer.toString(this.c.get(java.util.Calendar.MILLISECOND));
		if (s_millisecond.length()==0) s_millisecond="000";
		if (s_millisecond.length()==1) s_millisecond="00" + s_millisecond;
		if (s_millisecond.length()==2) s_millisecond="0" + s_millisecond;
		
		return s_millisecond;
	}


}