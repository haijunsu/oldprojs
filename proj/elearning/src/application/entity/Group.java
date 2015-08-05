/*
 * @(#)Group.java  2005-1-15
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/Elearning/src/application/entity/Group.java,v 1.1 2005/06/14 10:29:21 navysu Exp $
 * $Log: Group.java,v $
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
 * User group.
 * </p>
 * 
 * $Revision: 1.1 $
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 * @hibernate.class table="T_GROUPS" schema="SMIRT18"
 */
public class Group implements Serializable {

    private Long m_id;

    private String m_account;

    private String m_group;

    public Group() {

    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Group)) {
            return false;
        }
        return ((Group) obj).getId().equals(getId());
    }

    /**
     * @return Returns the account.
     * @hibernate.property column="USERID" length="30" not-null="true"
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
     * @return Returns the group.
     * @hibernate.property column="GROUPNAME" length="30"
     */
    public String getGroup() {
        return this.m_group;
    }

    /**
     * @param group The group to set.
     */
    public void setGroup(String group) {
        this.m_group = group;
    }

    /**
     * @return Returns the id.
     * @hibernate.id generator-class="native" column="SERIAL"
     *  
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


}