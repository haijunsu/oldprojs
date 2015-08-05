/*
 * @(#)FunData.java  2003-06-20
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package system.fun;
/**
 * <P>��������غ���</P>
 * 
 * @version 1.0
 * @author IDN
 */
import java.text.*;
public class FunData  {
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(FunData.class);
	private String dformat = null;
	private String dround = null;
	public final static String DOUBLE_FORMAT_DEFAULT="#,###,##0.00";
	public FunData() {
		super();
		dformat = "";
		dround = "0";
	}
	/**
	 * <P>ת�����ݸ�ʽ<P>
	 * @param String double�͵��ַ���  ������������
	 * @param String double�͵��ַ����ĸ�ʽ 
	 * @return String double�͵��ַ���
	 */
	public String doubleFormat(String strdata,String s_format){
		if (getDround().trim().equals("")){
			setDround("0");
		}
		return doubleFormat(strdata,s_format,getDround());
	}
	/**
	 * <P>ת�����ݸ�ʽ<P>
	 * @param String double�͵��ַ���
	 * @param String double�͵��ַ����ĸ�ʽ 
	 * @param String double�͵��ַ����Ƿ�Ҫ��ROUND("1")
	 * @return String double�͵��ַ���
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
				//����һ��0.1 * e N�Ĵ���
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
			log.error("FunData:double������ת��ʧ��");
			e.printStackTrace();
			return "0.00";
		}
	}	
	/**
	 * <P>ת�����ݸ�ʽ<P>
	 * @param String double�͵��ַ���(Ĭ�ϵĸ�ʽΪ#,###,##0.00),����ROUND����
	 * @return String double�͵��ַ���
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
		return dformat;
	}

	/**
	 * @param string
	 */
	public void setDformat(String string) {
		dformat = string;
	}
	/**
	 * @return
	 */
	public String getDround() {
		return dround;
	}

	/**
	 * @param string
	 */
	public void setDround(String string) {
		dround = string;
	}

}

