/*
 * @(#)AccompanyForm.java  2005-3-6
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.paper;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import framework.util.StringUtil;

import application.entities.TpaperPresented;
import application.util.ServicesUtil;

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
public class PaperPresentedForm extends ActionForm {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(PaperPresentedForm.class);

    private TpaperPresented m_tpaperPresented;

    private String m_action;

    private Collection m_equipments;

    private String m_partiNo;

    private String m_firstName;

    private String m_middleName;

    private String m_lastName;

    private String m_paperNumber;

    private String m_paperTitle;

    /**
     * @return Returns the tableKey.
     */
    public String getTableKey() {
        if (getPresented().getIntIndex() == null
                || getPresented().getIntIndex().intValue() == 0) {
            return null;
        }
        return getPresented().getIntIndex().toString();
    }

    /**
     * @param tableKey The tableKey to set.
     */
    public void setTableKey(String tableKey) {
        if (StringUtil.isNotBlank(tableKey)) {
            getPresented().setIntIndex(new Integer(tableKey));
        }
    }

    public String getPaperNumber() {
        return m_paperNumber;
    }

    public void setPaperNumber(String paperNumber) {
        m_paperNumber = paperNumber;
    }

    public String getPaperTitle() {
        return m_paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        m_paperTitle = paperTitle;
    }

    public String getFirstName() {
        return m_firstName;
    }

    public void setFirstName(String firstName) {
        m_firstName = firstName;
    }

    public String getLastName() {
        return m_lastName;
    }

    public void setLastName(String lastName) {
        m_lastName = lastName;
    }

    public String getMiddleName() {
        return m_middleName;
    }

    public void setMiddleName(String middleName) {
        m_middleName = middleName;
    }

    public String getPartiNo() {
        return m_partiNo;
    }

    public void setPartiNo(String partiNo) {
        m_partiNo = partiNo;
    }

    public PaperPresentedForm() {
        super();
        this.m_tpaperPresented = new TpaperPresented();
    }

    public TpaperPresented getPresented() {
        return this.m_tpaperPresented;
    }

    public void setPresented(TpaperPresented paperPresented) {
        this.m_tpaperPresented = paperPresented;
    }

    public String getAction() {
        return this.m_action;
    }

    public void setAction(String action) {
        this.m_action = action;
    }

    public Collection getEquipments() {
        if (this.m_equipments == null || this.m_equipments.size() == 0) {
            this.m_equipments = ServicesUtil.getEquipmentLabelValueBeans();
        }
        return this.m_equipments;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.m_action = "";
        this.m_tpaperPresented = new TpaperPresented();
        this.m_equipments = null;
        this.m_partiNo = "";
        this.m_firstName = "";
        this.m_middleName = "";
        this.m_lastName = "";
    }
}