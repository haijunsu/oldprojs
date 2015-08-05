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

import application.entities.TpaperRegistation;
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
public class PaperFeeForm extends ActionForm {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(PaperFeeForm.class);

    private TpaperRegistation m_tpaperRegistation;

    private String m_action;

    private Collection m_moneyTypes;
    private Collection m_paymentMethods;

    public PaperFeeForm() {
        super();
        this.m_tpaperRegistation = new TpaperRegistation();
    }

    public TpaperRegistation getPaperFee() {
        return this.m_tpaperRegistation;
    }

    public void setPaperFee(TpaperRegistation tpaperRegistation) {
        this.m_tpaperRegistation = tpaperRegistation;
    }

    public String getAction() {
        return this.m_action;
    }

    public void setAction(String action) {
        this.m_action = action;
    }

    public Collection getMoneyTypes() {
        if (this.m_moneyTypes == null || this.m_moneyTypes.size() == 0) {
            this.m_moneyTypes = ServicesUtil.getMoneyTypeLabelValueBeans();
        }
        return this.m_moneyTypes;
    }

    public Collection getPaymentMethods() {
        if (this.m_paymentMethods == null || this.m_paymentMethods.size() == 0) {
            this.m_paymentMethods = ServicesUtil.getPaymentMethodLabelValueBeans();
        }
        return this.m_paymentMethods;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.m_action = "";
        this.m_tpaperRegistation = new TpaperRegistation();
    }
}