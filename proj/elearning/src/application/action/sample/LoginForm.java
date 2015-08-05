/*
 * @(#)LoginForm.java  2005-6-14
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/Elearning/src/application/action/sample/LoginForm.java,v 1.1 2005/06/14 12:33:58 navysu Exp $
 * $Log: LoginForm.java,v $
 * Revision 1.1  2005/06/14 12:33:58  navysu
 * add login sample
 *
 */
package application.action.sample;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision: 1.1 $
 * @author <a href=mailto:su_hj@126.com>su_haijun</a>
 */
public class LoginForm extends ActionForm {
    
    private String m_username;
    private String m_password;
    

    /**
     * @return Returns the password.
     */
    public String getPassword() {
        return m_password;
    }
    /**
     * @param password The password to set.
     */
    public void setPassword(String password) {
        m_password = password;
    }
    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return m_username;
    }
    /**
     * @param username The username to set.
     */
    public void setUsername(String username) {
        m_username = username;
    }
    /* (non-Javadoc)
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        // TODO Auto-generated method stub
        super.reset(arg0, arg1);
        this.m_password = null;
        this.m_username = null;
    }
}
