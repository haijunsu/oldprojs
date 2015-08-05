/*
 * @(#)HotelInfo.java  2005-1-25
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.topic.jo;

import java.io.Serializable;

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
public class HotelInfo implements Serializable {
    private String m_hotelDestineTable;

    private String m_hotelCheckinDate;

    private String m_hotelCheckoutDate;

    private String m_hotelEarnestMoney;

    private String m_hotelEarnestMoneyType;

    public String getHotelCheckinDate() {
        return this.m_hotelCheckinDate;
    }

    public void setHotelCheckinDate(String hotelCheckinDate) {
        this.m_hotelCheckinDate = hotelCheckinDate;
    }

    public String getHotelCheckoutDate() {
        return this.m_hotelCheckoutDate;
    }

    public void setHotelCheckoutDate(String hotelCheckoutDate) {
        this.m_hotelCheckoutDate = hotelCheckoutDate;
    }

    public String getHotelDestineTable() {
        return this.m_hotelDestineTable;
    }

    public void setHotelDestineTable(String hotelDestineTable) {
        this.m_hotelDestineTable = hotelDestineTable;
    }

    public String getHotelEarnestMoney() {
        return this.m_hotelEarnestMoney;
    }

    public void setHotelEarnestMoney(String hotelEarnestMoney) {
        this.m_hotelEarnestMoney = hotelEarnestMoney;
    }

    public String getHotelEarnestMoneyType() {
        return this.m_hotelEarnestMoneyType;
    }

    public void setHotelEarnestMoneyType(String hotelEarnestMoneyType) {
        this.m_hotelEarnestMoneyType = hotelEarnestMoneyType;
    }
}