/*
 * @(#)DBUtil.java  2003-5-15
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.sql;

import sun.jdbc.rowset.CachedRowSet;

/**
 * <P> 对数据库操作的一些工具 </P>
 * 
 * @version 0.1
 * @author navy
 */
public class DBUtils {
	
	/**
	 * 将CachedRowSet转换为二维数组
	 * @param crs CachedRowSet
	 * @return 与结果集相同的数组
	 */
	public static String[][] CachedRowSetToArray(CachedRowSet crs) {
		DataBean db = new DataBean();
		db.setCrs(crs);
		if (db.getRowCount() == 0)
			return null;
		String[][] strRowSet = CachedRowSetToArray(db);
		db.release();
		return strRowSet;
	}
	/**
	 * 将DataBean中的CachedRowSet转换为二维数组
	 * @param db DataBean
	 * @return 与结果集相同的数组
	 * @deprecated
	 */
	public static String[][] CachedRowSetToArray(DataBean db) {
		if (db.getRowCount() == 0)
			return null;
		String[][] strRowSet = new String[db.getRowCount()][db.getColumnCount()];
		for (int i = 0; i < db.getRowCount(); i++) {
			for (int j = 0; j < db.getColumnCount(); j++) {
				strRowSet[i][j] = db.getFieldValue(j, i);
			}
		}
		return strRowSet;
	}

}
