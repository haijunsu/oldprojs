/*
 * @(#)UserForm.java  2005-3-24
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.user;

import org.apache.struts.action.ActionForm;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class UserForm extends ActionForm {
    
    private Long m_id;
    
    private String m_userid;
    
    private String m_userPass;
    
    private String m_newPass;
    
    private String m_rePass;
    
    private String m_userName;
    
    private boolean m_isManager;
    

    /**
     * @return Returns the id.
     */
    public Long getId() {
        if (m_id != null && m_id.intValue() == 0) {
            return null;
        }
        return m_id;
    }
    /**
     * @param id The id to set.
     */
    public void setId(Long id) {
        m_id = id;
    }
    /**
     * @return Returns the isManager.
     */
    public boolean isManager() {
        return m_isManager;
    }
    /**
     * @param isManager The isManager to set.
     */
    public void setManager(boolean isManager) {
        m_isManager = isManager;
    }
    /**
     * @return Returns the newPass.
     */
    public String getNewPass() {
        return m_newPass;
    }
    /**
     * @param newPass The newPass to set.
     */
    public void setNewPass(String newPass) {
        m_newPass = newPass;
    }
    /**
     * @return Returns the rePass.
     */
    public String getRePass() {
        return m_rePass;
    }
    /**
     * @param rePass The rePass to set.
     */
    public void setRePass(String rePass) {
        m_rePass = rePass;
    }
    /**
     * @return Returns the userid.
     */
    public String getUserid() {
        return m_userid;
    }
    /**
     * @param userid The userid to set.
     */
    public void setUserid(String userid) {
        m_userid = userid;
    }
    /**
     * @return Returns the userName.
     */
    public String getUserName() {
        return m_userName;
    }
    /**
     * @param userName The userName to set.
     */
    public void setUserName(String userName) {
        m_userName = userName;
    }
    /**
     * @return Returns the userPass.
     */
    public String getUserPass() {
        return m_userPass;
    }
    /**
     * @param userPass The userPass to set.
     */
    public void setUserPass(String userPass) {
        m_userPass = userPass;
    }
}
