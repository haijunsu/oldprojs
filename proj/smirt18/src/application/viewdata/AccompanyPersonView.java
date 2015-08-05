/*
 * @(#)AccompanyPersonView.java  2005-3-9
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.viewdata;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.util.LabelValueBean;

import framework.util.StringUtil;

import application.action.SingleHtmlButton;
import application.entities.TaccompanyPerson;
import application.helper.ResourcesHelper;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class AccompanyPersonView extends TaccompanyPerson {
    
    private HttpServletRequest m_request;
    private HttpServletResponse m_response;
    
    private String m_monyType;
    
    private final String RECEPTION_FEE_PREFIX = "form.acc.person.reception.fee.";
    private final String BANQUET_FEE_PREFIX = "form.acc.person.banquet.fee.";
    private final String TECH_TOUR_FEE_PREFIX = "form.acc.person.tech.tour.fee.";
    
    private String getBanquetFee() {
        String _strName = "0";
        try {
                _strName = ResourcesHelper.getFormsResources().getMessage(
                        BANQUET_FEE_PREFIX + m_monyType.trim());
        } catch (Exception e) {
        }
        if (StringUtil.isBlank(_strName)) {
            _strName = "0";
        }
        return _strName;
    }

    private String getTechTourFee() {
        String _strName = "0";
        try {
                _strName = ResourcesHelper.getFormsResources().getMessage(
                        TECH_TOUR_FEE_PREFIX + m_monyType.trim());
        } catch (Exception e) {
        }
        if (StringUtil.isBlank(_strName)) {
            _strName = "0";
        }
        return _strName;
    }

    private String getReceptionFee() {
        String _strName = "0";
        try {
                _strName = ResourcesHelper.getFormsResources().getMessage(
                        RECEPTION_FEE_PREFIX + m_monyType.trim());
        } catch (Exception e) {
        }
        if (StringUtil.isBlank(_strName)) {
            _strName = "0";
        }
        return _strName;
    }

    
    public void setMonyType(String monyType) {
        m_monyType = monyType;
    }
    public void setRequest(HttpServletRequest request) {
        this.m_request = request;
    }
    
    public void setResponse(HttpServletResponse response){
        this.m_response = response;
    }
    
    public String getUpdateButton() {
        if (this.m_request == null || this.m_response == null) {
            return "";
        }

        SingleHtmlButton _btnUpdate = new SingleHtmlButton(this.m_request, this.m_response);
        _btnUpdate.setNameKey("form.button.modify");
        _btnUpdate.setAction("/accompany.do");
        LabelValueBean[] _labelValueBeans = new LabelValueBean[2];
        _labelValueBeans[0] = new LabelValueBean("action", "loadByAccPersonNo");
        _labelValueBeans[1] = new LabelValueBean("accPersonNo", getChrAccPersonNo());
        _btnUpdate.setLabelValueBeans(_labelValueBeans);
        return _btnUpdate.getHtmlLink();
    }

    public String getRemoveButton() {
        if (this.m_request == null || this.m_response == null) {
            return "";
        }
        SingleHtmlButton _btnUpdate = new SingleHtmlButton(this.m_request, this.m_response);
        _btnUpdate.setNameKey("form.button.remove");
        _btnUpdate.setAction("/accompany.do");
        LabelValueBean[] _labelValueBeans = new LabelValueBean[3];
        _labelValueBeans[0] = new LabelValueBean("action", "removeAccompanyPerson");
        _labelValueBeans[1] = new LabelValueBean("person.chrAccPersonNo", getChrAccPersonNo());
        _labelValueBeans[2] = new LabelValueBean("person.chrPartiNo", getChrPartiNo());
        _btnUpdate.setLabelValueBeans(_labelValueBeans);
        return _btnUpdate.getHtmlLink();
    }
    
    
    public int getAccFee() {
        int _nFee = 0;
        if (getBitIsAttendBanquet().booleanValue()) {
            _nFee += Integer.parseInt(getBanquetFee());
        }
        if (getBitIsAttendReception().booleanValue()) {
            _nFee += Integer.parseInt(getReceptionFee());
        }
        if (getBitIsAttendTechTour().booleanValue()) {
            _nFee += Integer.parseInt(getTechTourFee());
        }
        if (getBitIsCheckin().booleanValue()) {
            _nFee += Integer.parseInt(getTechTourFee());
        }
        return _nFee;
    }
    
    public String getUserName() {

        String _str = "";
        if (StringUtil.isNotBlank(getChvAccPersonTitle())) {
            _str = getChvAccPersonTitle().trim() + " ";
        }
        if (StringUtil.isNotBlank(getChvAccPersonFirstName())) {
            _str = _str + getChvAccPersonFirstName().trim() + " ";
        }

        if (StringUtil.isNotBlank(getChvAccPersonMiddleName())) {
            _str = _str + getChvAccPersonMiddleName().trim() + " ";
        }
        if (StringUtil.isNotBlank(getChvAccPersonLastName())) {
            _str = _str + getChvAccPersonLastName().trim() + " ";
        }
        return _str.trim();
    }

}
