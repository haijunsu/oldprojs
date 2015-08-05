/** 
 * ��������BeanDateFormat.java
 * �������������ڽ�15λ�������ַ�����ʽ��������ʾ
 *           ����ָ�������յ������ַ����ַ�ת��Ϊ15λ������
 *			�������ݿ�����ڴ���	
 *
 * @version ver 0.1
 * @author name: �պ���
 * @copyright 2002, ��Ȩ����
 *
 * �������ڣ� 2002��7��31��
 */
 package com.htyz.util;
 
 import java.util.Date;
 import java.util.GregorianCalendar;
 import java.text.DateFormat;
 import java.text.SimpleDateFormat;
 
 public class BeanDateFormat {
 
//	G  Era designator  				Text  AD  
//	y  Year  						Year  1996; 96  
//	M  Month in year  				Month  July; Jul; 07  
//	w  Week in year  				Number  27  
//	W  Week in month  				Number  2  
//	D  Day in year  				Number  189  
//	d  Day in month  				Number  10  
//	F  Day of week in month  		Number  2  
//	E  Day in week  				Text  Tuesday; Tue  
//	a  Am/pm marker  				Text  PM  
//	H  Hour in day (0-23)  			Number  0  
//	k  Hour in day (1-24)  			Number  24  
//	K  Hour in am/pm (0-11)  		Number  0  
//	h  Hour in am/pm (1-12)  		Number  12  
//	m  Minute in hour  				Number  30  
//	s  Second in minute  			Number  55  
//	S  Millisecond  				Number  978  
//	z  Time zone  General time zone  Pacific Standard Time; PST; GMT-08:00  
//	Z  Time zone  RFC 822 time zone  -0800  


 	public final int LONG = 0; 					//yyyy��M��d�� H:mm:ss.S
 	public final int SHORT = 1; 				//yyyy-M-d H:mm:ss.S
 	public final int LONG_DATE_CN = 2; 			//yyyy��M��d��
	public final int SHORT_DATE = 3; 			//yyyy-M-d
	public final int LONG_TIME = 4; 			//H:mm:ss 24Сʱ��
	public final int SHORT_TIME = 5; 			//K:mm:ss a 12Сʱ��  
	public final int LONG_DATE_LONG_TIME = 6; 	//yyyy��M��d�� H:mm:ss 24Сʱ��
	public final int LONG_DATE_SHORT_TIME = 7;	//yyyy��M��d�� K:mm:ss a 12Сʱ�� 
	public final int SHORT_DATE_LONG_TIME = 8; 	//yyyy-M-d H:mm:ss 24Сʱ��
	public final int SHORT_DATE_SHORT_TIME = 9; //yyyy-M-d K:mm:ss a 12Сʱ�� 
	public final int LONG_TIME_Mill = 10; 		//H:mm:ss.S 24Сʱ��
	public final int SHORT_TIME_Mill = 11; 		//K:mm:ss.S a 12Сʱ��  
	public final int LONG_HOUR_SEC = 12; 		//H:mm 24Сʱ��  
	public final int SHORT_HOUR_SEC = 13; 		//K:mm a 12Сʱ��  
 	public final int LONG_DATE = 14; 			//yyyy-MM-dd
 	public final int LONG_DATE_LONG_TIME2 = 15; //yyyy/mm/dd HH:mm:ss
	
	private int intFormat = 0;
	private String strFormat = "yyyy'��'M'��'d'��' H:mm:ss.S";
	
	/** ************************************************************
     * ��������BeanDateFormat
     * ������������ʼ��
     * 
     * @param 
     * 
     * @retrun 
     * 
     * @author name: �պ���
     * �������ڣ�2002-07-31 
     */
    public BeanDateFormat() {
   		this.intFormat = 0;
    }

	/** ************************************************************
     * ��������BeanDateFormat
     * ������������ʼ��
     * 
     * @param intFormat: ��ʼ��format����
     * 
     * @retrun 
     * 
     * @author name: �պ���
     * �������ڣ�2002-07-31 
     */
    public BeanDateFormat(int intFormat) {
		this.intFormat = intFormat;
    }

	/** ************************************************************
     * ��������BeanDateFormat
     * ������������ʼ��
     * 
     * @param intFormat: ��ʼ��format����
     * 
     * @retrun 
     * 
     * @author name: �պ���
     * �������ڣ�2002-07-31 
     */
    public BeanDateFormat(String strFormat) {
		this.strFormat = strFormat;
    }
     
    /** ************************************************************
	 * ��������format
	 * ����������
	 * 
	 * @param date: Ҫ��ʽ�������� 
	 * 
	 * @retrun String
	 * 
	 * @author name: �պ���
	 * �������ڣ� 2002��7��31��
	 */
	public String format(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(this.strFormat);
		return sdf.format(date);
	}

    /** ************************************************************
	 * ��������format
	 * ����������
	 * 
	 * @param date: Ҫ��ʽ�������� 
	 * @param intFormat: ��ʽ�����ڵ����� 
	 * 
	 * @retrun String
	 * 
	 * @author name: �պ���
	 * �������ڣ�2002��7��31�� 
	 */
	public String format(Date date, int intFormat) {
		String strFormat = "yyyy'��'M'��'d'��' H:mm:ss.S";
		switch (intFormat) {
			case LONG: 
				strFormat = "yyyy'��'M'��'d'��' H:mm:ss.S";
				break;
			case SHORT: 
				strFormat = "yyyy'-'M'-'d H:mm:ss.S";
				break;
			case LONG_DATE_CN: 
				strFormat = "yyyy'��'M'��'d'��'";
				break;
			case LONG_DATE: 
				strFormat = "yyyy'-'MM'-'dd";
				break;
			case SHORT_DATE: 
				strFormat = "yyyy'-'M'-'d";
				break;
			case LONG_TIME: 
				this.strFormat = "H:mm:ss";
				break;
			case SHORT_TIME: 
				strFormat = "K:mm:ss a";
				break;
			case LONG_DATE_LONG_TIME: 
				this.strFormat = "yyyy'��'M'��'d'��' H:mm:ss";
				break;
			case LONG_DATE_SHORT_TIME: 
				strFormat = "yyyy'��'M'��'d'��' K:mm:ss a";
				break;
			case SHORT_DATE_LONG_TIME: 
				strFormat = "yyyy-M-d H:mm:ss";
				break;
			case SHORT_DATE_SHORT_TIME: 
				strFormat = "yyyy-M-d K:mm:ss a";
				break;
			case LONG_TIME_Mill: 
				strFormat = "H:mm:ss.S";
				break;
			case SHORT_TIME_Mill: 
				strFormat = "K:mm:ss.S a";
				break;
			case LONG_HOUR_SEC: 
				strFormat = "H:mm";
				break;
			case SHORT_HOUR_SEC: 
				strFormat = "K:mm a";
				break;
			case LONG_DATE_LONG_TIME2:
				strFormat = "yyyy'-'mm'-'dd hh:mm:ss";
				break;
			default :
				strFormat = "yyyy'��'M'��'d'��' H:mm:ss.S";
				break;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
		return sdf.format(date);
	}

     /** ************************************************************
	  * ��������format
	  * ����������
	  * 
	  * @param date: Ҫ��ʽ�������� 
	  * @param strFormat: ��ʽ�����ڵ����� 
	  * 
	  * @retrun String
	  * 
	  * @author name: �պ���
	  * �������ڣ� 2002��7��31��
	  */
     public String format(Date date, String strFormat) {
     	SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
     	return sdf.format(date);
     }
     
     /** ************************************************************
	  * ��������format
	  * ����������
	  * 
	  * @param strDate: Ҫ��ʽ�������ڣ�������������ɵ�
	  * @param strFormat: ��ʽ�����ڵ����� 
	  * 
	  * @retrun String
	  * 
	  * @author name: �պ���
	  * �������ڣ�2002��7��31�� 
	  */
     public String format(String strDate, String strFormat) {
     	if(strDate==null||strDate.equals("")){
     		return "";
     	}
     	long longDate = Long.parseLong(strDate);
     	Date date = new Date(longDate);
     	return this.format(date, strFormat);
     }
     

     /** ************************************************************
	  * ��������format
	  * ����������
	  * 
	  * @param strDate: Ҫ��ʽ�������ڣ�������������ɵ�
	  * 
	  * @retrun String
	  * 
	  * @author name: �պ���
	  * �������ڣ�2002��7��31�� 
	  */
     public String format(String strDate) {
     	if(strDate==null||strDate.equals("")){
     		return "";
     	}
     	long longDate = Long.parseLong(strDate);
     	Date date = new Date(longDate);
     	return this.format(date);
     }
     
     /** ************************************************************
	  * ��������format
	  * ����������
	  * 
	  * @param strDate: Ҫ��ʽ�������ڣ�������������ɵ�
	  * @param intFormat: ��ʽ�����ڵ����� 
	  * 
	  * @retrun String
	  * 
	  * @author name: �պ���
	  * �������ڣ� 
	  */
     public String format(String strDate, int intFormat) {
     	if(strDate==null||strDate.equals("")){
     		return "";
     	}
     	long longDate = Long.parseLong(strDate);
     	Date date = new Date(longDate);
     	return this.format(date, intFormat);
     }
     
     /** ************************************************************
	  * ��������parseDBDate
	  * �������������������ַ���������ת��Ϊ���룬����15λ���ַ���
	  * 
	  * @param strYear: ָ������
	  * @param strMonth: ָ������
	  * @param strDay: ָ������
	  * 
	  * @retrun String
	  * 
	  * @author name: �պ���
	  * �������ڣ� 2002��7��31��
	  */
	  public String parseDBDate(String strYear, String strMonth, String strDay) {
		String strRtn = "000000000000000";
		GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(strYear), Integer.parseInt(strMonth)-1, Integer.parseInt(strDay));
		Date newdate = gc.getTime();
		long longDate = newdate.getTime();
		strRtn = strRtn + Long.toString(longDate);
		return strRtn.substring(strRtn.length() - 15, strRtn.length());
	  }

     public String getYear(String strDate) {
     	if(strDate.equals(null)||strDate==""){
     		return "    ";
     	}
     	long longDate = Long.parseLong(strDate);
     	Date date = new Date(longDate);
     	return this.format(date).substring(0,4);
     }
     public String getMonth(String strDate) {
     	if(strDate.equals(null)||strDate==""){
     		return "  ";
     	}
     	long longDate = Long.parseLong(strDate);
     	Date date = new Date(longDate);
     	return this.format(date,14).substring(5,7);
     }
	
     public String getDay(String strDate) {
     	if(strDate.equals(null)||strDate==""){
     		return "  ";
     	}
     	long longDate = Long.parseLong(strDate);
     	Date date = new Date(longDate);
     	return this.format(date,14).substring(8,10);
     }
     
     
	public int getIntFormat() {
		return (this.intFormat); 
	}

	public void setIntFormat(int intFormat) {
		this.intFormat = intFormat; 
	}

	public String getStrFormat() {
		return (this.strFormat); 
	}

	public void setStrFormat(String strFormat) {
		this.strFormat = strFormat; 
	}
	  
 }