/*
 * @(#)SearchJO.java  2005-1-25
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.topic.jo;

/**
 * <p><b>Description</b></p>
 * <p>serach page proerties</p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class SearchInfo {
    
    private String m_searchKey;
    
    private String m_searchType;

    private String m_url;

    private String m_urlName;

    public String getSearchKey() {
        return this.m_searchKey;
    }
    public void setSearchKey(String searchKey) {
        this.m_searchKey = searchKey;
    }
    public String getSearchType() {
        return this.m_searchType;
    }
    public void setSearchType(String searchType) {
        this.m_searchType = searchType;
    }
    public String getUrl() {
        return this.m_url;
    }
    public void setUrl(String url) {
        this.m_url = url;
    }
    public String getUrlName() {
        return this.m_urlName;
    }
    public void setUrlName(String urlName) {
        this.m_urlName = urlName;
    }
}
