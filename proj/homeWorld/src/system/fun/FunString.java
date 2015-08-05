/*
 * @(#)FunString.java  2003-07-07
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package system.fun;
/**
 * <P>���ַ���������صĺ���</P>
 * 
 * @version 1.0
 * @author IDN
 */public class FunString  {
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(FunString.class);

	public FunString() {
		super();
	}
	/**
	 * <P>���ַ������滻<P>
	 * @param String Ҫ������ַ���
	 * @param String Ҫ���滻��ԭ����
	 * @param String Ҫ�滻������ 
	 * @return String double�͵��ַ���
	 */	
	public String replaceAll(String s_thing,String s_old,String s_new){
		int i_indexof;
		if (s_old.equals(s_new)){
			//�滻�뱻�滻����ͬ
			return s_thing;
		}
		i_indexof = s_thing.indexOf(s_old);
		while (i_indexof != -1) {
			s_thing = s_thing.substring(0, i_indexof) + s_new 
			        + s_thing.substring(i_indexof + s_old.length());
			i_indexof = s_thing.indexOf(s_old);
		}
		return s_thing;
	}
	/**
		 * <P>�ҽغ���<P>
		 * @param String Ҫ������ַ���
		 * @param int    �ҽ�λ��
		 * @return String �غ���ַ���
		 */	
		public String right(String s_thing,int i_len){
			int i_lenall;
			i_lenall = s_thing.length();
			s_thing = s_thing.substring(i_lenall - i_len,i_lenall);
			return s_thing;
		}
	
}