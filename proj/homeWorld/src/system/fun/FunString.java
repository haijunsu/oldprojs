/*
 * @(#)FunString.java  2003-07-07
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package system.fun;
/**
 * <P>与字符串处理相关的函数</P>
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
	 * <P>在字符串中替换<P>
	 * @param String 要处理的字符串
	 * @param String 要被替换的原内容
	 * @param String 要替换的内容 
	 * @return String double型的字符串
	 */	
	public String replaceAll(String s_thing,String s_old,String s_new){
		int i_indexof;
		if (s_old.equals(s_new)){
			//替换与被替换的相同
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
		 * <P>右截函数<P>
		 * @param String 要处理的字符串
		 * @param int    右截位数
		 * @return String 截后的字符串
		 */	
		public String right(String s_thing,int i_len){
			int i_lenall;
			i_lenall = s_thing.length();
			s_thing = s_thing.substring(i_lenall - i_len,i_lenall);
			return s_thing;
		}
	
}