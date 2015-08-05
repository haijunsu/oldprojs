/*
 * @(#)Querys.java  2005-1-15
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /navysu/Smirt18/src/application/entity/Menu.java,v 1.1 2005/01/20 03:14:26 navy Exp $
 * $Log: Menu.java,v $
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
 * Application's query entity
 * </p>
 * 
 * $Revision: 1.1 $
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 * @hibernate.class table="T_QUERYS" schema="SMIRT18"
 */
public class MyQuerys implements Serializable {

    private Long m_id;
    
    private String m_queryName;
    
    private String m_querySql;

    /**
     *  
     */
    public MyQuerys() {
        super();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof MyQuerys)) {
            return false;
        }
        return ((MyQuerys) obj).getId().equals(getId());
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
     * @return Returns the queryname.
     * @hibernate.property column="QUERYNAME" not-null="true" length="255"
     */
    public String getQueryName() {
        return this.m_queryName;
    }

    /**
     * @param queryname The queryname to set.
     */
    public void setQueryName(String queryname) {
        this.m_queryName = queryname;
    }

    /**
     * @return Returns the querySql.
     * @hibernate.property column="QUERYSQL" length="8000"
     */
    public String getQuerySql() {
        return this.m_querySql;
    }
    /**
     * @param querySql The querySql to set.
     */
    public void setQuerySql(String querySql) {
        this.m_querySql = querySql;
    }
}