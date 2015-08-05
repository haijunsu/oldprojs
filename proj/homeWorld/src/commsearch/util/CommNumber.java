/**
 * @(#)CommNumber.java  2003-11-19
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */

package commsearch.util;

//import java.util.*;
//import javax.servlet.http.*;
import java.text.*;
//import com.idn.sql.DataBean;
//import javax.servlet.ServletException;
//import javax.servlet.http.*;

//import org.apache.struts.action.*;
//import org.apache.struts.util.MessageResources;
//import org.apache.commons.beanutils.PropertyUtils;
/**
 * <P>公用数字相关工具类</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class CommNumber {

	//利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommNumber.class);
	private String dformat = null;
	private String dround = null;
	public final static String DOUBLE_FORMAT_DEFAULT="#,###,##0.00";
	
	/**
	* Constructor
	*/
	public CommNumber() {
		super();
		setDformat("");
		setDround("0");
	}
	/**
	 * 判断是不是数字型
	 * 
	 * @param String 字符型内容
	 * @return int   1、是，-1、不是
	 */	
	public int isNumber(String s_thing){
		int i_return = 1;
		double d_temp;
		try{
			d_temp = Double.parseDouble(s_thing);
			
		} catch (NumberFormatException e){
			i_return = -1;
		}
		return i_return;
	}	
	/**
	 * <P>转换数据格式<P>
	 * @param String double型的字符串  不作四舍五入
	 * @param String double型的字符串的格式 
	 * @return String double型的字符串
	 */
	public String doubleFormat(String strdata,String s_format){
		if (getDround().trim().equals("")){
			setDround("0");
		}
		return doubleFormat(strdata,s_format,getDround());
	}
	/**
	 * <P>转换数据格式<P>
	 * @param String double型的字符串
	 * @param String double型的字符串的格式 
	 * @param String double型的字符串是否要作ROUND("1")
	 * @return String double型的字符串
	 */
	public String doubleFormat(String strdata,String s_format,String s_round){
		try{
			double Dtos = 0.00; 
			int i_index,i_len,i_fori;
			double d_add;
			if (strdata==null) return "";
			if (strdata.equals("")) return "";
			if (s_format.trim().equals("")){
				s_format =DOUBLE_FORMAT_DEFAULT;
			}
			d_add = 0.1;
			i_index = s_format.indexOf(".");
			
			if (s_round.equals("1")){
				//作加一个0.1 * e N的处理
				if (i_index > 0 ){
					i_len = s_format.length() - i_index - 1;
					for(i_fori=0;i_fori<i_len;i_fori++){
						d_add = d_add * 0.1;
					}
				}
				System.out.println(d_add);
				Dtos = Double.parseDouble(strdata);
				Dtos = Dtos + d_add;
				System.out.println(Dtos);
			} else {
				Dtos = Double.parseDouble(strdata);
			}
			//DecimalFormat 
			DecimalFormat dnum = new DecimalFormat(s_format);
			String strr = dnum.format(Dtos);
			return strr;
		} catch (Exception e){
			log.error("FunData:double型数据转换失败");
			e.printStackTrace();
			return "0.00";
		}
	}	
	/**
	 * <P>转换数据格式<P>
	 * @param String double型的字符串(默认的格式为#,###,##0.00),不作ROUND处理
	 * @return String double型的字符串
	 */
	public String doubleFormat(String strdata){
		if (getDformat().trim().equals("")){
			setDformat(DOUBLE_FORMAT_DEFAULT);
		}
		return doubleFormat(strdata,getDformat());
	}
	/**
	 * @return
	 */
	public String getDformat() {
		return this.dformat;
	}

	/**
	 * @param string
	 */
	public void setDformat(String string) {
		this.dformat = string;
	}
	/**
	 * @return
	 */
	public String getDround() {
		return this.dround;
	}

	/**
	 * @param string
	 */
	public void setDround(String string) {
		this.dround = string;
	}	
}