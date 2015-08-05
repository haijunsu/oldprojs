/*
 * @(#)UserProfile.java  2005-1-25
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
public class UserProfile implements Serializable {

    private String m_userName;

    private String m_userKey;

    private String m_firstName;

    private String m_middleName;

    private String m_lastName;

    private String m_jobTitle;

    private String m_birthday;

    private String m_country;

    private String m_company;

    private String m_email;

    private String m_tel;

    private String m_fax;

    public String getBirthday() {
        return this.m_birthday;
    }
    public void setBirthday(String birthday) {
        this.m_birthday = birthday;
    }
    public String getCompany() {
        return this.m_company;
    }
    public void setCompany(String company) {
        this.m_company = company;
    }
    public String getCountry() {
        return this.m_country;
    }
    public void setCountry(String country) {
        this.m_country = country;
    }
    public String getEmail() {
        return this.m_email;
    }
    public void setEmail(String email) {
        this.m_email = email;
    }
    public String getFax() {
        return this.m_fax;
    }
    public void setFax(String fax) {
        this.m_fax = fax;
    }
    public String getFirstName() {
        return this.m_firstName;
    }
    public void setFirstName(String firstName) {
        this.m_firstName = firstName;
    }
    public String getJobTitle() {
        return this.m_jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.m_jobTitle = jobTitle;
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
    public String getTel() {
        return this.m_tel;
    }
    public void setTel(String tel) {
        this.m_tel = tel;
    }
    public String getUserKey() {
        return this.m_userKey;
    }
    public void setUserKey(String userKey) {
        this.m_userKey = userKey;
    }
    public String getUserName() {
        if (this.m_userName == null) {
            this.m_userName = "";
            if(this.m_firstName != null)
                this.m_userName += this.m_firstName + " ";
            if(this.m_middleName != null)
                this.m_userName += this.m_middleName + " ";
            if(this.m_lastName != null)
                this.m_userName += this.m_lastName;
        }
        return this.m_userName;
    }
    public void setUserName(String userName) {
        this.m_userName = userName;
    }
}
