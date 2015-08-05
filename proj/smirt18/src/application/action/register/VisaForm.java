/*
 * @(#)VisaForm.java  2005-3-9
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.register;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import application.entities.Tcountry;
import application.util.ServicesUtil;
import application.viewdata.VisaView;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class VisaForm extends ActionForm {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(VisaForm.class);

    private VisaView m_visa;
    
    private String m_action;
    
    private Collection m_countries;
    
    /**
     * 
     */
    public VisaForm() {
        super();
        this.m_action = "";
        this.m_visa = new VisaView();
    }
    
    public String getAction() {
        return this.m_action;
    }
    public void setAction(String action) {
        this.m_action = action;
    }
    public VisaView getVisa() {
        return this.m_visa;
    }
    public void setVisa(VisaView visa) {
        this.m_visa = visa;
    }
    
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
         super.reset(mapping, request);
         this.m_action = "";
         this.m_visa = new VisaView();
         this.m_countries = null;
    }
    
    public Collection getCountries() {
        if (logger.isDebugEnabled()) {
            logger.debug("getCountries() - start");
        }

        if (this.m_countries == null) {
            try {
            this.m_countries = new ArrayList();
            Tcountry[] _arrayCountry = ServicesUtil.getAllCountry();
            for (int i = 0; i < _arrayCountry.length; i++) {
                this.m_countries.add(new LabelValueBean(_arrayCountry[i].getChvCountryName(), _arrayCountry[i].getChrCountryNo()));
            }
            } catch (Exception e) {
                logger.error("getCountries()", e);
             }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("getCountries() - end");
        }
        return this.m_countries;
    }
}
