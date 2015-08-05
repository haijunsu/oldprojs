/*
 * @(#)GenKeyService.java  2005-2-15
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao.genkey;

import java.util.HashMap;

import application.helper.BeansLocator;
import framework.util.StringUtil;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * </p>
 * 
 * $Revision$
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public class GenKeyService {

    private static HashMap m_mapSerials = new HashMap();

    private GenKeyService() {

    }

    public static String genStringkey(String tableName, String tableSchema,
            String keyVol, int keyLength) {
        String _str = tableName;
        if (StringUtil.isNotBlank(tableSchema)) {
            _str = tableSchema + "." + tableName;
        }
        if (m_mapSerials.get(_str) == null) {
            m_mapSerials.put(_str, ((RetrieveMaxKeyFromDB)BeansLocator.getBeanResource("retrieveMaxKeyFromDB"))
                    .getStringMaxKey(tableName, tableSchema, keyVol));
        }
        String _strKey = stringIncrease((String) m_mapSerials.get(_str),
                keyLength);
        m_mapSerials.put(_str, _strKey);
        return _strKey;
    }

    public static Long genLongkey(String tableName, String tableSchema,
            String keyVol) {
        String _str = tableName;
        if (StringUtil.isNotBlank(tableSchema)) {
            _str = tableSchema + "." + tableName;
        }
        if (m_mapSerials.get(_str) == null) {
            m_mapSerials.put(_str, ((RetrieveMaxKeyFromDB)BeansLocator.getBeanResource("retrieveMaxKeyFromDB"))
                    .getLongMaxKey(tableName, tableSchema, keyVol));
        }
        Long _llTemp = new Long(((Long) m_mapSerials.get(_str))
                .longValue() + 1L);
        m_mapSerials.put(_str, _llTemp);
        return _llTemp;
    }

    private static String stringIncrease(String stringAsLong, int stringLength) {
        long _llTemp = Long.parseLong(stringAsLong);
        _llTemp++;
        String _strReturn = String.valueOf(_llTemp);
        _strReturn = StringUtil.leftPad(_strReturn, stringLength, "0");
        return _strReturn;
    }

}