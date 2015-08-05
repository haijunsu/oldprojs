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
 * <P>Ȩ�޺���</P>
 * 
 * @version 1.0
 * @author ����
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
	 * <P>Ȩ�޽��뺯��</P>
	 * 
	 * @param int��Ȩ��ֵ
	 * @param String��Ȩ�ޱ���
	 * @return boolean���ɹ�true��ʧ��false
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
	 * <P>ȡȨ�ޱ��⺯��</P>
	 * 
	 * @return String[]��Ȩ�ޱ�������
	 */
	public String[] getCshowc() {
		return strCshowc;
	}

	/**
	 * <P>ȡȨ�޽��뺯��</P>
	 * 
	 * @return String[]��Ȩ������
	 */
	public String[] getPurview() {
		return strPurview;
	}

	/**
	 * <P>Ȩ�ޱ��뺯��</P>
	 * 
	 * @param String[]��Ȩ������
	 * @return int��Ȩ��ֵ
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

