/*
 * @(#)HotelView.java  2005-3-9
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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

import application.action.SingleHtmlButton;
import application.entities.Taccommodation;
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
public class AccommodationView extends Taccommodation {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(AccommodationView.class);

    private HttpServletRequest m_request;

    private HttpServletResponse m_response;

    public String getRoomTypeName() {
        return ServicesUtil.getHotelRoomTypeName(getChvRoomtype());
    }

    public void setRequest(HttpServletRequest request) {
        this.m_request = request;
    }

    public void setResponse(HttpServletResponse response) {
        this.m_response = response;
    }

    public String getUpdateButton() {
        if (this.m_request == null || this.m_response == null) {
            return "";
        }

        SingleHtmlButton _btnUpdate = new SingleHtmlButton(this.m_request,
                this.m_response);
        _btnUpdate.setNameKey("form.button.modify");
        _btnUpdate.setAction("/hotel.do");
        LabelValueBean[] _labelValueBeans = new LabelValueBean[3];
        _labelValueBeans[0] = new LabelValueBean("action",
                "queryHotelByHotelNo");
        _labelValueBeans[1] = new LabelValueBean("hotel.chrAccommodationNo",
                getChrAccommodationNo());
        _labelValueBeans[2] = new LabelValueBean("hotel.chrPartiNo",
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
        _btnUpdate.setAction("/hotel.do");
        LabelValueBean[] _labelValueBeans = new LabelValueBean[3];
        _labelValueBeans[0] = new LabelValueBean("action", "removeHotel");
        _labelValueBeans[1] = new LabelValueBean("hotel.chrAccommodationNo",
                getChrAccommodationNo());
        _labelValueBeans[2] = new LabelValueBean("hotel.chrPartiNo",
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
        _btnUpdate.setAction("/hotel.do");
        LabelValueBean[] _labelValueBeans = new LabelValueBean[2];
        _labelValueBeans[0] = new LabelValueBean("action", "newHotel");
        _labelValueBeans[1] = new LabelValueBean("hotel.chrPartiNo",
                getChrPartiNo());
        _btnUpdate.setLabelValueBeans(_labelValueBeans);
        return _btnUpdate.getHtmlLink();
    }

    public boolean equals(Object other) {
        if (!(other instanceof AccommodationView))
            return false;
        AccommodationView castOther = (AccommodationView) other;
        return new EqualsBuilder().append(this.getChrAccommodationNo(),
                castOther.getChrAccommodationNo()).append(this.getChrPartiNo(),
                castOther.getChrPartiNo()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getChrAccommodationNo()).append(
                getChrPartiNo()).toHashCode();
    }

}