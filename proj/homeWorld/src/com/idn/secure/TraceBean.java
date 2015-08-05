/*
 * @(#)TraceBean.java  2005-1-10
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/HomeWorld/src/com/idn/secure/TraceBean.java,v 1.1 2005/01/10 05:01:02 navy Exp $
 * $Log: TraceBean.java,v $
 * Revision 1.1  2005/01/10 05:01:02  navy
 * add log into templog to database
 *
 */
package com.idn.secure;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idn.sql.DynaSqlBean;

/**
 * <p><b>Description</b></p>
 * <p>Record action to database</p>
 * 
 * $Revision: 1.1 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class TraceBean {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(TraceBean.class);
    /**
     * insert log to db sql statement
     */
    private final String m_insertSql = "INSERT INTO TEMPLOG (ACT_DATETIME," +
    		" ACT_USER, ACT_SESSION, ACT_FROM, ACT_TYPE, ACT_POCNO, ACT_RUNT) VALUES (?,?,?,?,?,?,?)";
    
    /**
     * Constructor TraceBean
     */
    public TraceBean() {
    }
    
    /**
     * insert log message to database
     * @param myTrace
     */
    public void saveTrace(Trace myTrace){
        if (logger.isDebugEnabled()) {
            logger.debug("saveTrace(Trace) - start");
        }

        String[] _strParams = new String[7];
        _strParams[0] = Long.toString(myTrace.getDateTime());
        _strParams[1] = myTrace.getUser();
        _strParams[2] = myTrace.getSessionid();
        _strParams[3] = myTrace.getMemUsage();
        _strParams[4] = String.valueOf(myTrace.getType());
        _strParams[5] = myTrace.getUrl();
        _strParams[6] = Long.toString(myTrace.getPeriod());
        
        DynaSqlBean dsb = new DynaSqlBean();
        dsb.setSql(this.m_insertSql);
        dsb.setParam(_strParams);
        try {
            dsb.execute();
        } catch (SQLException e) {
            logger.error("saveTrace(Trace)", e);
            
        }
        
        if (logger.isDebugEnabled()) {
            logger.debug("saveTrace(Trace) - end");
        }
    }
}
