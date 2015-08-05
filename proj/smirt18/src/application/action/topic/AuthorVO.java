/*
 * @(#)AuthorVO.java  2005-1-20
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.topic;

import java.io.Serializable;

import application.action.topic.jo.HotelInfo;
import application.action.topic.jo.PaperInfo;
import application.action.topic.jo.RegisterInfo;
import application.action.topic.jo.UserProfile;
import application.action.topic.jo.VisaInfo;

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
public class AuthorVO implements Serializable {
    
    private UserProfile m_userProfile;
    
    private RegisterInfo m_register;
    
    private HotelInfo m_hotel;
    
    private VisaInfo m_visa;
    
    private PaperInfo[] m_paperInfos;
    
    public AuthorVO(){
        this.m_userProfile = new UserProfile();
        this.m_register = new RegisterInfo();
        this.m_hotel = new HotelInfo();
        this.m_visa = new VisaInfo();
        this.m_paperInfos = new PaperInfo[0];
    }

    public HotelInfo getHotel() {
        return this.m_hotel;
    }
    public void setHotel(HotelInfo hotel) {
        this.m_hotel = hotel;
    }
    public PaperInfo[] getPaperInfos() {
        return this.m_paperInfos;
    }
    public void setPaperInfos(PaperInfo[] paperInfos) {
        this.m_paperInfos = paperInfos;
    }
    public RegisterInfo getRegister() {
        return this.m_register;
    }
    public void setRegister(RegisterInfo register) {
        this.m_register = register;
    }
    public UserProfile getUserProfile() {
        return this.m_userProfile;
    }
    public void setUserProfile(UserProfile userProfile) {
        this.m_userProfile = userProfile;
    }
    public VisaInfo getVisa() {
        return this.m_visa;
    }
    public void setVisa(VisaInfo visa) {
        this.m_visa = visa;
    }
}