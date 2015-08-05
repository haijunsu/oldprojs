/**
 * @(#)CommArrary.java  2003-11-19
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch.util;

import java.util.Map;
import java.util.TreeMap;

//import java.util.*;
//import javax.servlet.http.*;

//import com.idn.sql.DataBean;
//import javax.servlet.ServletException;
//import javax.servlet.http.*;

//import org.apache.struts.action.*;
//import org.apache.struts.util.MessageResources;
//import org.apache.commons.beanutils.PropertyUtils;
/**
 * <P>公用数组相关工具类</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class CommArrary {

	//利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommArrary.class);
	public static String ALL_SUM = "allsum";
	public static String GROUP_SUM = "sum";
	public static String ALL_COUNT = "allcount";		
	public static String GROUP_COUNT = "count";
	public static String ALL_MAX = "allmax";
	
	/**
	* Constructor
	*/
	public CommArrary() {
		super();

	}
	/**
	 * 在数组中查询序号
	 * 
	 * @param String[] 查询数组
	 * @param String 查询内容
	 * @return int   查询内容在数组中的序号
	 */	
	public int searchInArray(String[] s_arrary,String s_search){
		int i_return = -1;
		int i_fori;
		for (i_fori=0;i_fori<s_arrary.length;i_fori++){
			if (s_arrary[i_fori].trim().equals(s_search.trim())){
				i_return = i_fori;
				break;
			}
		}
		
		return i_return;
	}
	/**
	 * 数组前加一个
	 * 
	 * @param String[] 原数组
	 * @param String 新的元素
	 * @return stirng[] 新的数据
	 */	
	public String[] addArray(String[] s_add,String s_new){
		String[] s_return = null;
		int i_fori;
		s_return = new String[s_add.length + 1];
		s_return[0] = s_new;
		for(i_fori=0;i_fori<s_add.length;i_fori++){
			s_return[i_fori + 1] = s_add[i_fori];
		}
		return s_return;
	}
	
	/**
	 * 
	 * <P> </P>
	 * 
	 * @version 0.1
	 * @author haizhou
	 */
	public String[][] makeArrayCalculate(String[][] strdata,int i_col,int i_colsum,String flag){
		String strreturn[][] = null;
		Map map = new TreeMap();
		int i_hor = 0,i_loll = 0,i_culculate = 0,i_add = 0,i_size = 0;
		String strcul = "";
		try{
		
			strreturn = strdata;
			if(strreturn == null) return null;
			
			//取数组的下标
			
			i_hor = strreturn.length;
			//i_loll = strreturn[0].length;
			//向MAP置数据,操作SUM
			if(flag.equalsIgnoreCase(GROUP_SUM)){
				String strtemp = "0";
				for(int i_for = 0;i_for < i_hor;i_for++){
					if(strreturn[i_for][i_col] != null){
						if(i_for == 0){
							if(strreturn[i_for][i_colsum] == null){
								 strtemp = "0";
							 }else{
								if(strreturn[i_for][i_colsum].trim().length() > 0){
									strtemp = strreturn[i_for][i_colsum].trim();
								}else{
									strtemp = "0";
								}
							 }
							map.put(strreturn[i_for][i_col].trim(),strtemp);
						}else{
							if(map.get(strreturn[i_for][i_col].trim()) == null){
								if(strreturn[i_for][i_colsum] == null){
									 strtemp = "0";
								 }else{
									if(strreturn[i_for][i_colsum].trim().length() > 0){
										strtemp = strreturn[i_for][i_colsum].trim();
									}else{
										strtemp = "0";
									}
								 }
								map.put(strreturn[i_for][i_col].trim(),strtemp);
							}else{
								i_culculate = 0;
								String strvalue = map.get(strreturn[i_for][i_col].trim()).toString();
								if(strvalue == null) strvalue = "0";
								strvalue = strvalue.trim();
								if(strvalue.length() > 0){
									i_culculate = Integer.parseInt(strvalue);
								}else{
									i_culculate = 0;
								}
								i_add = 0;
								strcul = "";
								if(strreturn[i_for][i_colsum] == null){
									i_add = 0;
								}else{
									if(strreturn[i_for][i_colsum].trim().length() > 0){
										i_add = Integer.parseInt(strreturn[i_for][i_colsum].trim());
									}else{
										i_add = 0;
									}
								}
								
								i_culculate = i_culculate + i_add;
								map.put(strreturn[i_for][i_col].trim(),Integer.toString(i_culculate));
								
							}
						}
					}
				}
				//取映射的长度
				i_size = map.size();
				strreturn = new String[i_size][2];
				for(int nf = 0;nf<i_size; nf++){
					Object strkey = map.keySet().toArray()[nf];
					strreturn[nf][1] = map.get(strkey).toString();
					strreturn[nf][0] = strkey.toString();
				}
				
			}
			//操作ALLSUM类型
			if(flag.equalsIgnoreCase(ALL_SUM)){
				i_add = 0;
				int i_temp = 0;
				for(int nf = 0;nf<i_hor;nf++){
					if(strreturn[nf][i_colsum] == null){
						i_temp = 0;
					}else{
						if(strreturn[nf][i_colsum].trim().length() > 0){
							i_temp = Integer.parseInt(strreturn[nf][i_colsum].trim());
						}else{
							i_temp = 0;
						}
					}
					
					i_add = i_add + i_temp;
				}
				strreturn = new String[1][2];
				strreturn[0][0] = "总合计";
				strreturn[0][1] = Integer.toString(i_add);
			}
			
		}catch(Exception e){
			return null;
		}
		
		
		return strreturn;
	}
	
}