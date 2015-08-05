/*
 * @(#)Trace.java  2005-1-10
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/HomeWorld/src/com/idn/secure/Trace.java,v 1.2 2005/01/10 05:08:37 navy Exp $
 * $Log: Trace.java,v $
 * Revision 1.2  2005/01/10 05:08:37  navy
 * add 'navy/' before user
 *
 * Revision 1.1  2005/01/10 05:01:02  navy
 * add log into templog to database
 *
 */
package com.idn.secure;

/**
 * <p><b>Description</b></p>
 * <p>Recode action items</p>
 * 
 * $Revision: 1.2 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class Trace {
    /**
     * save start time.
     */
    private long m_dateTime;
    /**
     * user id/url.
     */
    private String m_user;
    /**
     * session id.
     */
    private String m_sessionid;
    /**
     * mem useage
     */
    private String m_memUsage;
    /**
     * Begin is 'b'. End is 'E', Interrupt is 'C'
     */
    private char m_type;
    /**
     * What is user accessing.
     */
    private String m_url;
    /**
     * execute time period.
     */
    private long m_period;
    /**
     * @return Returns the dateTime.
     */
    public long getDateTime() {
        return this.m_dateTime;
    }
    /**
     * @param dateTime The dateTime to set.
     */
    public void setDateTime(long dateTime) {
        this.m_dateTime = dateTime;
    }
    /**
     * @return Returns the memUsage.
     */
    public String getMemUsage() {
        return this.m_memUsage;
    }
    /**
     * @param memUsage The memUsage to set.
     */
    public void setMemUsage(String memUsage) {
        if(memUsage.length() > 30)
            memUsage = memUsage.substring(0, 30);
        this.m_memUsage = memUsage;
    }
    /**
     * @return Returns the period.
     */
    public long getPeriod() {
        return this.m_period;
    }
    /**
     * @param period The period to set.
     */
    public void setPeriod(long period) {
        this.m_period = period;
    }
    /**
     * @return Returns the sessionid.
     */
    public String getSessionid() {
        return this.m_sessionid;
    }
    /**
     * @param sessionid The sessionid to set.
     */
    public void setSessionid(String sessionid) {
        this.m_sessionid = sessionid;
    }
    /**
     * @return Returns the type.
     */
    public char getType() {
        return this.m_type;
    }
    /**
     * @param type The type to set.
     */
    public void setType(char type) {
        this.m_type = type;
    }
    /**
     * @return Returns the url.
     */
    public String getUrl() {
        return this.m_url;
    }
    /**
     * @param url The url to set.
     */
    public void setUrl(String url) {
        if (url.length() > 20) {
            url = url.substring(0, 20);
        }
        this.m_url = url;
    }
    /**
     * @return Returns the user.
     */
    public String getUser() {
        return this.m_user;
    }
    /**
     * @param user The user to set.
     */
    public void setUser(String user) {
        if (user.length() > 15) {
            user = user.substring(0, 15);
        }
        user = "navy/" + user;
        this.m_user = user;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer _sb = new StringBuffer();
        _sb.append("[m_dateTime=").append(this.m_dateTime)
        .append("; m_user=").append(this.m_user)
        .append("; m_sessionid=").append(this.m_sessionid)
        .append("; m_memUsage=").append(this.m_memUsage)
        .append("; m_type=").append(this.m_type)
        .append("; m_period=").append(this.m_period)
        .append("; m_url=").append(this.m_url);


        return _sb.toString();
    }
    
}
