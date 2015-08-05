/*
 * @(#)VisaInfo.java  2005-1-25
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.topic.jo;

import java.io.Serializable;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class VisaInfo implements Serializable {

    private String m_visaRequire;

    private String m_visaTable;

    private String m_visaPostDate;

    private String m_visaId;

    private String m_inChinaDate;

    public String getInChinaDate() {
        return this.m_inChinaDate;
    }
    public void setInChinaDate(String inChinaDate) {
        this.m_inChinaDate = inChinaDate;
    }
    public String getVisaId() {
        return this.m_visaId;
    }
    public void setVisaId(String visaId) {
        this.m_visaId = visaId;
    }
    public String getVisaPostDate() {
        return this.m_visaPostDate;
    }
    public void setVisaPostDate(String visaPostDate) {
        this.m_visaPostDate = visaPostDate;
    }
    public String getVisaRequire() {
        return this.m_visaRequire;
    }
    public void setVisaRequire(String visaRequire) {
        this.m_visaRequire = visaRequire;
    }
    public String getVisaTable() {
        return this.m_visaTable;
    }
    public void setVisaTable(String visaTable) {
        this.m_visaTable = visaTable;
    }
}
