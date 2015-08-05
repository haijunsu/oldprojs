/*
 * @(#)FunPurview.java  2003-06-20
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package system.fun;

import java.util.Vector;
import com.idn.sql.DataBean;

/**
 * <P>权限函数</P>
 * 
 * @version 1.0
 * @author 刘坚
 */
public class FunPurview  {
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(FunPurview.class);
	private int intA = 7;
	private int intB = 177;
	private String[] strCshowc = null;
	private String[] strPurview = null;

	public FunPurview() {
		super();
	}

	/**
	 * <P>权限解码函数</P>
	 * 
	 * @param int：权限值
	 * @param String：权限标题
	 * @return boolean：成功true；失败false
	 */
	public boolean decodePurview(int intPurview, String strAppsys) {
		DataBean dbBean = new DataBean();
		Vector vecPurview = new Vector();
		try {
			dbBean.executeSelect("select cshowc from codes where cclass='"+strAppsys+"'");
			if (dbBean.getRowCount() < 1 || dbBean.getRowCount() > 15) { return false; }
			strCshowc = new String[dbBean.getRowCount()];
			if (intPurview != 0){ intPurview = (intPurview - intB)/intA; }
			int intTemp = 1;
			for (int intRow = 0; intRow < strCshowc.length;intRow++){
				strCshowc[intRow]=dbBean.getElementValue(intRow,"cshowc").trim();
				if ((intPurview & intTemp)!= 0 ) { 
					vecPurview.addElement(Integer.toString(intRow));
				} 
				intTemp = intTemp * 2;
			}
			strPurview = (String[]) vecPurview.toArray(new String[0]);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * <P>取权限标题函数</P>
	 * 
	 * @return String[]：权限标题数组
	 */
	public String[] getCshowc() {
		return strCshowc;
	}

	/**
	 * <P>取权限解码函数</P>
	 * 
	 * @return String[]：权限数组
	 */
	public String[] getPurview() {
		return strPurview;
	}

	/**
	 * <P>权限编码函数</P>
	 * 
	 * @param String[]：权限数组
	 * @return int：权限值
	 */
	public int codingPurview(String[] strTemp) {
		int intPurview = 0;
		if (strTemp != null){
			for (int intRow = 0; intRow < strTemp.length;intRow++){
				intPurview = intPurview | (1 << Integer.parseInt(strTemp[intRow]));
			}
		}
		intPurview = intPurview * intA + intB;
		return intPurview;
	}

}

