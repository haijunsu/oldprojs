/*
 * @(#)VisaView.java  2005-3-9
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.viewdata;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.struts.util.LabelValueBean;

import application.action.SingleHtmlButton;
import application.entities.Tvisa;
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
public class VisaView extends Tvisa {

    private String m_title;

    private String m_passportNumber;

    private String m_firstName;

    private String m_middleName;

    private String m_lastName;

    private HttpServletRequest m_request;

    private HttpServletResponse m_response;
    
    public void setUserName(String name) {
        
    }

    public String getUserName() {

        String _str = "";
        if (getFirstName() != null) {
            _str = getFirstName().trim() + " ";
        }

        if (getMiddleName() != null) {
            _str = _str + getMiddleName().trim() + " ";
        }
        if (getLastName() != null) {
            _str = _str + getLastName().trim() + " ";
        }
        return _str.trim();
    }

    public String getCountryName() {
        String _str = "";
        try {
            _str = ServicesUtil.getCountryName(getChvVisaNationality());
        } catch (Exception e) {

        }
        return _str;
    }

    public String getFirstName() {
        return this.m_firstName;
    }

    public void setFirstName(String firstName) {
        this.m_firstName = firstName;
    }

    public String getLastName() {
        return this.m_lastName;
    }

    public void setLastName(String lastName) {
        this.m_lastName = lastName;
    }

    public String getMiddleName() {
        return this.m_middleName;
    }

    public void setMiddleName(String middleName) {
        this.m_middleName = middleName;
    }

    public String getPassportNumber() {
        return this.m_passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.m_passportNumber = passportNumber;
    }

    public void setRequest(HttpServletRequest request) {
        this.m_request = request;
    }

    public void setResponse(HttpServletResponse response) {
        this.m_response = response;
    }

    public String getTitle() {
        return this.m_title;
    }

    public void setTitle(String title) {
        this.m_title = title;
    }

    public String getUpdateButton() {
        if (this.m_request == null || this.m_response == null) {
            return "";
        }

        SingleHtmlButton _btnUpdate = new SingleHtmlButton(this.m_request,
                this.m_response);
        _btnUpdate.setNameKey("form.button.modify");
        _btnUpdate.setAction("/visa.do");
        LabelValueBean[] _labelValueBeans = new LabelValueBean[3];
        _labelValueBeans[0] = new LabelValueBean("action", "queryVisaByVisaNo");
        _labelValueBeans[1] = new LabelValueBean("visa.chrVisaNo",
                getChrVisaNo());
        _labelValueBeans[2] = new LabelValueBean("visa.chrPartiNo",
                getChrPartiNo());
        _btnUpdate.setLabelValueBeans(_labelValueBeans);
        return _btnUpdate.getHtmlLink();
    }

    public String getRemoveButton() {
        if (this.m_request == null || this.m_response == null) {
            return "";
        }
        SingleHtmlButton _btnUpdate = new SingleHtmlButton(this.m_request,
                this.m_response);
        _btnUpdate.setNameKey("form.button.remove");
        _btnUpdate.setAction("/visa.do");
        LabelValueBean[] _labelValueBeans = new LabelValueBean[3];
        _labelValueBeans[0] = new LabelValueBean("action", "removeVisa");
        _labelValueBeans[1] = new LabelValueBean("visa.chrVisaNo",
                getChrVisaNo());
        _labelValueBeans[2] = new LabelValueBean("visa.chrPartiNo",
                getChrPartiNo());
        _btnUpdate.setLabelValueBeans(_labelValueBeans);
        return _btnUpdate.getHtmlLink();
    }

    public String getAddButton() {
        if (this.m_request == null || this.m_response == null) {
            return "";
        }
        SingleHtmlButton _btnUpdate = new SingleHtmlButton(this.m_request,
                this.m_response);
        _btnUpdate.setNameKey("form.button.add");
        _btnUpdate.setAction("/visa.do");
        LabelValueBean[] _labelValueBeans = new LabelValueBean[3];
        _labelValueBeans[0] = new LabelValueBean("action", "newVisa");
        _labelValueBeans[1] = new LabelValueBean("visa.chrPartiNo",
                getChrPartiNo());
        _labelValueBeans[2] = new LabelValueBean("visa.chrAccPersonNo",
                getChrAccPersonNo());
        _btnUpdate.setLabelValueBeans(_labelValueBeans);
        return _btnUpdate.getHtmlLink();
    }

    public boolean equals(Object other) {
        if (!(other instanceof VisaView))
            return false;
        VisaView castOther = (VisaView) other;
        return new EqualsBuilder()
        .append(this.getChrAccPersonNo(), castOther.getChrAccPersonNo())
        .append(this.getChrPartiNo(), castOther.getChrPartiNo())
        .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getChrVisaNo()).append(
                getChrPartiNo()).toHashCode();
    }

}