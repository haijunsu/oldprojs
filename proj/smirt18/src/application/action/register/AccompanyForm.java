/*
 * @(#)AccompanyForm.java  2005-3-6
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.register;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import application.entities.TaccompanyPerson;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class AccompanyForm extends ActionForm {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(RegisterForm.class);
    
    private TaccompanyPerson m_person;
    private String m_action;
    
    public AccompanyForm() {
        super();
        this.m_person = new TaccompanyPerson();
    }

    public TaccompanyPerson getPerson() {
        return this.m_person;
    }
    public void setPerson(TaccompanyPerson person) {
        this.m_person = person;
    }
    public String getAction() {
        return this.m_action;
    }
    public void setAction(String action) {
        this.m_action = action;
    }
    
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.m_action = "";
        this.m_person = new TaccompanyPerson();
    }
}
