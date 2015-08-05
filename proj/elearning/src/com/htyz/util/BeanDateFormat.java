/** 
 * 程序名：BeanDateFormat.java
 * 功能描述：用于将15位的数字字符串格式化日期显示
 *           并当指定年月日的数字字符串字符转换为15位的数字
 *			方便数据库的日期处理	
 *
 * @version ver 0.1
 * @author name: 苏海军
 * @copyright 2002, 版权所有
 *
 * 创建日期： 2002年7月31日
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


 	public final int LONG = 0; 					//yyyy年M月d日 H:mm:ss.S
 	public final int SHORT = 1; 				//yyyy-M-d H:mm:ss.S
 	public final int LONG_DATE_CN = 2; 			//yyyy年M月d日
	public final int SHORT_DATE = 3; 			//yyyy-M-d
	public final int LONG_TIME = 4; 			//H:mm:ss 24小时制
	public final int SHORT_TIME = 5; 			//K:mm:ss a 12小时制  
	public final int LONG_DATE_LONG_TIME = 6; 	//yyyy年M月d日 H:mm:ss 24小时制
	public final int LONG_DATE_SHORT_TIME = 7;	//yyyy年M月d日 K:mm:ss a 12小时制 
	public final int SHORT_DATE_LONG_TIME = 8; 	//yyyy-M-d H:mm:ss 24小时制
	public final int SHORT_DATE_SHORT_TIME = 9; //yyyy-M-d K:mm:ss a 12小时制 
	public final int LONG_TIME_Mill = 10; 		//H:mm:ss.S 24小时制
	public final int SHORT_TIME_Mill = 11; 		//K:mm:ss.S a 12小时制  
	public final int LONG_HOUR_SEC = 12; 		//H:mm 24小时制  
	public final int SHORT_HOUR_SEC = 13; 		//K:mm a 12小时制  
 	public final int LONG_DATE = 14; 			//yyyy-MM-dd
 	public final int LONG_DATE_LONG_TIME2 = 15; //yyyy/mm/dd HH:mm:ss
	
	private int intFormat = 0;
	private String strFormat = "yyyy'年'M'月'd'日' H:mm:ss.S";
	
	/** ************************************************************
     * 方法名：BeanDateFormat
     * 功能描述：初始化
     * 
     * @param 
     * 
     * @retrun 
     * 
     * @author name: 苏海军
     * 创建日期：2002-07-31 
     */
    public BeanDateFormat() {
   		this.intFormat = 0;
    }

	/** ************************************************************
     * 方法名：BeanDateFormat
     * 功能描述：初始化
     * 
     * @param intFormat: 初始化format类型
     * 
     * @retrun 
     * 
     * @author name: 苏海军
     * 创建日期：2002-07-31 
     */
    public BeanDateFormat(int intFormat) {
		this.intFormat = intFormat;
    }

	/** ************************************************************
     * 方法名：BeanDateFormat
     * 功能描述：初始化
     * 
     * @param intFormat: 初始化format类型
     * 
     * @retrun 
     * 
     * @author name: 苏海军
     * 创建日期：2002-07-31 
     */
    public BeanDateFormat(String strFormat) {
		this.strFormat = strFormat;
    }
     
    /** ************************************************************
	 * 方法名：format
	 * 功能描述：
	 * 
	 * @param date: 要格式化的日期 
	 * 
	 * @retrun String
	 * 
	 * @author name: 苏海军
	 * 创建日期： 2002年7月31日
	 */
	public String format(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(this.strFormat);
		return sdf.format(date);
	}

    /** ************************************************************
	 * 方法名：format
	 * 功能描述：
	 * 
	 * @param date: 要格式化的日期 
	 * @param intFormat: 格式化日期的类型 
	 * 
	 * @retrun String
	 * 
	 * @author name: 苏海军
	 * 创建日期：2002年7月31日 
	 */
	public String format(Date date, int intFormat) {
		String strFormat = "yyyy'年'M'月'd'日' H:mm:ss.S";
		switch (intFormat) {
			case LONG: 
				strFormat = "yyyy'年'M'月'd'日' H:mm:ss.S";
				break;
			case SHORT: 
				strFormat = "yyyy'-'M'-'d H:mm:ss.S";
				break;
			case LONG_DATE_CN: 
				strFormat = "yyyy'年'M'月'd'日'";
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
				this.strFormat = "yyyy'年'M'月'd'日' H:mm:ss";
				break;
			case LONG_DATE_SHORT_TIME: 
				strFormat = "yyyy'年'M'月'd'日' K:mm:ss a";
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
				strFormat = "yyyy'年'M'月'd'日' H:mm:ss.S";
				break;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
		return sdf.format(date);
	}

     /** ************************************************************
	  * 方法名：format
	  * 功能描述：
	  * 
	  * @param date: 要格式化的日期 
	  * @param strFormat: 格式化日期的类型 
	  * 
	  * @retrun String
	  * 
	  * @author name: 苏海军
	  * 创建日期： 2002年7月31日
	  */
     public String format(Date date, String strFormat) {
     	SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
     	return sdf.format(date);
     }
     
     /** ************************************************************
	  * 方法名：format
	  * 功能描述：
	  * 
	  * @param strDate: 要格式化的日期，该是由数字组成的
	  * @param strFormat: 格式化日期的类型 
	  * 
	  * @retrun String
	  * 
	  * @author name: 苏海军
	  * 创建日期：2002年7月31日 
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
	  * 方法名：format
	  * 功能描述：
	  * 
	  * @param strDate: 要格式化的日期，该是由数字组成的
	  * 
	  * @retrun String
	  * 
	  * @author name: 苏海军
	  * 创建日期：2002年7月31日 
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
	  * 方法名：format
	  * 功能描述：
	  * 
	  * @param strDate: 要格式化的日期，该是由数字组成的
	  * @param intFormat: 格式化日期的类型 
	  * 
	  * @retrun String
	  * 
	  * @author name: 苏海军
	  * 创建日期： 
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
	  * 方法名：parseDBDate
	  * 功能描述：将给定的字符串年月日转换为毫秒，返回15位的字符串
	  * 
	  * @param strYear: 指定的年
	  * @param strMonth: 指定的月
	  * @param strDay: 指定的日
	  * 
	  * @retrun String
	  * 
	  * @author name: 苏海军
	  * 创建日期： 2002年7月31日
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