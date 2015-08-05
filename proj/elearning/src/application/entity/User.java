/*
 * @(#)ApplicationServiceLocator.java  2005-1-6
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/Elearning/src/application/entity/User.java,v 1.1 2005/06/14 10:29:21 navysu Exp $
 * $Log: User.java,v $
 * Revision 1.1  2005/06/14 10:29:21  navysu
 * add application and framework etc.
 *
 * Revision 1.1  2005/01/20 03:14:26  navy
 * Create SMiRT 18 project
 *
 */

package application.entity;

import java.io.Serializable;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * User Entity. User table is t_users.
 * </p>
 * 
 * $Revision: 1.1 $
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 * @hibernate.class table="T_USERS" schema="SMIRT18"
 */
public class User implements Serializable {
    private Long m_id;

    private String m_account;

    private String m_password;

    private String m_name;

    public User() {
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof User)) {
            return false;
        }
        return ((User) obj).getId().equals(getId());
    }

    /**
     * @return Returns the id.
     * @hibernate.id column="SERIAL" generator-class="native"
     */
    public Long getId() {
        return this.m_id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(Long id) {
        this.m_id = id;
    }

    /**
     * @return Returns the account.
     * @hibernate.property column="USERID" not-null="true" unique="true"
     *                     length="30"
     */
    public String getAccount() {
        return this.m_account;
    }

    /**
     * @param account The account to set.
     */
    public void setAccount(String account) {
        this.m_account = account;
    }

    /**
     * @return Returns the password.
     * @hibernate.property column="PASS" length="32"
     */
    public String getPassword() {
        return this.m_password;
    }

    /**
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.m_password = password;
    }

    /**
     * @return Returns the username.
     * @hibernate.property column="USERNAME" length="100"
     */
    public String getName() {
        return this.m_name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.m_name = name;
    }

}