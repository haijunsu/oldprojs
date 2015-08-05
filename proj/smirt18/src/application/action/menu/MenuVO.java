/*
 * @(#)MenuVO.java  2005-1-18
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /navysu/Smirt18/src/application/action/menu/MenuVO.java,v 1.1 2005/01/20 03:14:23 navy Exp $
 * $Log: MenuVO.java,v $
 * Revision 1.1  2005/01/20 03:14:23  navy
 * Create SMiRT 18 project
 *
 */
package application.action.menu;

import java.io.Serializable;

import application.entity.Menu;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision: 1.1 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class MenuVO implements Serializable {
    
    private Long m_id;

    private String m_menuid;
    
    private String m_parentid;

    private String m_url;

    private int m_purview;
    
    private String m_menuName;

    /**
     *  
     */
    public MenuVO() {
        super();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Menu)) {
            return false;
        }
        return ((Menu) obj).getId().equals(getId());
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
     * @return Returns the menuid.
     * @hibernate.property column="MENUID" not-null="true" length="10"
     *                     unique="true"
     */
    public String getMenuid() {
        return this.m_menuid;
    }

    /**
     * @param menuid The menuid to set.
     */
    public void setMenuid(String menuid) {
        this.m_menuid = menuid;
    }

    /**
     * @return Returns the parentid.
      * @hibernate.property column="PARENTID" length="10"
     *                     unique="true"
    */
    public String getParentid() {
        return this.m_parentid;
    }
    /**
     * @param parendid The parentid to set.
     */
    public void setParentid(String parentid) {
        this.m_parentid = parentid;
    }
    /**
     * @return Returns the purview.
     * @hibernate.property column="PURVIEW"
     */
    public int getPurview() {
        return this.m_purview;
    }

    /**
     * @param permit The purview to set.
     */
    public void setPurview(int purview) {
        this.m_purview = purview;
    }

    /**
     * @return Returns the url.
     * @hibernate.property column="HTTPURL"
     */
    public String getUrl() {
        return this.m_url;
    }

    /**
     * @param url The url to set.
     */
    public void setUrl(String url) {
        this.m_url = url;
    }


    /**
     * @return Returns the menuName.
     */
    public String getMenuName() {
        return this.m_menuName;
    }
    /**
     * @param menuName The menuName to set.
     */
    public void setMenuName(String menuName) {
        this.m_menuName = menuName;
    }
}
