/*
 * @(#)ResourcesUtil.java  2005-3-16
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package util;

import java.util.ResourceBundle;

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
public class ResourcesUtil {
    private static ResourceBundle m_configFile = null;

    public static String getProperty(String name) {
        try {
            String _string = getConfigFile().getString(name);
            return _string.trim();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static ResourceBundle getConfigFile() {
        if (m_configFile == null) {
            m_configFile = ResourceBundle.getBundle("myApp");
        }
        return m_configFile;
    }
}