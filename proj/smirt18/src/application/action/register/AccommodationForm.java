/*
 * @(#)AccompanyForm.java  2005-3-6
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.register;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import application.entities.Taccommodation;
import application.util.ServicesUtil;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class AccommodationForm extends ActionForm {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(AccommodationForm.class);
    
    private Taccommodation m_hotel;
    private String m_action;
    private Collection m_roomTypes;
    
    public AccommodationForm() {
        super();
        this.m_hotel = new Taccommodation();
        this.m_hotel.setMnyBookFee(new BigDecimal(0.0));
    }

    public Taccommodation getHotel() {
        return this.m_hotel;
    }
    public void setHotel(Taccommodation hotel) {
        this.m_hotel = hotel;
    }
    public String getAction() {
        return this.m_action;
    }
    public void setAction(String action) {
        this.m_action = action;
    }
    
    public Collection getRoomTypes() {
        if (this.m_roomTypes == null || this.m_roomTypes.size() ==0) {
            this.m_roomTypes = ServicesUtil.getHotelRoomTypeLabelValueBeans();
        }
        return this.m_roomTypes;
    }
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.m_action = "";
        this.m_hotel = new Taccommodation();
        this.m_hotel.setMnyBookFee(new BigDecimal(0.0));
        this.m_roomTypes = null;
    }
}
