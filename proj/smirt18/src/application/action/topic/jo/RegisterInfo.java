/*
 * @(#)RegisterJO.java  2005-1-25
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.topic.jo;

import java.io.Serializable;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class RegisterInfo implements Serializable {

    private String m_registerTable;

    private String m_registerState;

    private String m_payMode;

    private String m_acceptRegisterFee;
    
    private String m_acceptRegisterFeeType;

    private String m_drawData;

    private String m_attendDinner;

    private String m_attendReception;

    private String m_attendTechnologyVisit;

    private String m_familyMember;

    public String getAcceptRegisterFee() {
        return this.m_acceptRegisterFee;
    }
    public void setAcceptRegisterFee(String acceptRegisterFee) {
        this.m_acceptRegisterFee = acceptRegisterFee;
    }
    public String getAcceptRegisterFeeType() {
        return this.m_acceptRegisterFeeType;
    }
    public void setAcceptRegisterFeeType(String acceptRegisterFeeType) {
        this.m_acceptRegisterFeeType = acceptRegisterFeeType;
    }
    public String getAttendDinner() {
        return this.m_attendDinner;
    }
    public void setAttendDinner(String attendDinner) {
        this.m_attendDinner = attendDinner;
    }
    public String getAttendReception() {
        return this.m_attendReception;
    }
    public void setAttendReception(String attendReception) {
        this.m_attendReception = attendReception;
    }
    public String getAttendTechnologyVisit() {
        return this.m_attendTechnologyVisit;
    }
    public void setAttendTechnologyVisit(String attendTechnologyVisit) {
        this.m_attendTechnologyVisit = attendTechnologyVisit;
    }
    public String getDrawData() {
        return this.m_drawData;
    }
    public void setDrawData(String drawData) {
        this.m_drawData = drawData;
    }
    public String getFamilyMember() {
        return this.m_familyMember;
    }
    public void setFamilyMember(String familyMember) {
        this.m_familyMember = familyMember;
    }
    public String getPayMode() {
        return this.m_payMode;
    }
    public void setPayMode(String payMode) {
        this.m_payMode = payMode;
    }
    public String getRegisterState() {
        return this.m_registerState;
    }
    public void setRegisterState(String registerState) {
        this.m_registerState = registerState;
    }
    public String getRegisterTable() {
        return this.m_registerTable;
    }
    public void setRegisterTable(String registerTable) {
        this.m_registerTable = registerTable;
    }
}
