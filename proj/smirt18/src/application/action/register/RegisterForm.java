/*
 * @(#)RegisterForm.java  2005-3-6
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.register;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import application.entities.Tparticipant;
import application.util.ServicesUtil;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class RegisterForm extends ActionForm {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(RegisterForm.class);
    
    private Tparticipant m_participant;
    private String m_action;
    private Collection m_countries;
    
    public RegisterForm() {
        super();
        this.m_participant = new Tparticipant();
    }
    public String getAction() {
        return this.m_action;
    }
    public void setAction(String action) {
        this.m_action = action;
    }
    public Tparticipant getParticipant() {
        return this.m_participant;
    }
    public void setParticipant(Tparticipant participant) {
        this.m_participant = participant;
    }
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.m_action = "";
        this.m_participant = new Tparticipant();
        this.m_countries = null;
    }
    public Collection getCountries() {
        if (this.m_countries == null) {
            this.m_countries = ServicesUtil.getCountryLabelValueBeans();
        }
        return this.m_countries;
    }
}
