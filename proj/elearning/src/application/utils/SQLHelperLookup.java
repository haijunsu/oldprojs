/*
 * @(#)SQLHelperLookup.java  2005-6-14
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/Elearning/src/application/utils/SQLHelperLookup.java,v 1.1 2005/06/14 12:33:59 navysu Exp $
 * $Log: SQLHelperLookup.java,v $
 * Revision 1.1  2005/06/14 12:33:59  navysu
 * add login sample
 *
 */
package application.utils;

import application.helper.BeansLocator;
import framework.jdbc.SQLHelper;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision: 1.1 $
 * @author <a href=mailto:su_hj@126.com>su_haijun</a>
 */
public class SQLHelperLookup {
    
    public static SQLHelper getSQLHelper() {
        return (SQLHelper)BeansLocator.getBeanResource("sqlHelper");
    }

}
