/*
 * @(#)LoginAction.java  2005-6-14
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/Elearning/src/application/action/sample/LoginAction.java,v 1.1 2005/06/14 12:33:58 navysu Exp $
 * $Log: LoginAction.java,v $
 * Revision 1.1  2005/06/14 12:33:58  navysu
 * add login sample
 *
 */
package application.action.sample;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import application.action.BaseAction;
import application.utils.SQLHelperLookup;
import framework.jdbc.SQLHelper;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision: 1.1 $
 * @author <a href=mailto:su_hj@126.com>su_haijun</a>
 */
public class LoginAction extends BaseAction {

    /* (non-Javadoc)
     * @see application.action.BaseAction#performBusiness(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected ActionForward performBusiness(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        LoginForm _form = (LoginForm)form;
        String _strForward = "loginok";
        ActionMessages _errors = new ActionMessages();
        try {
            String _strUsername = _form.getUsername();
            String _strPassword = _form.getPassword();
            String _strSQL = "select * from t_user where USER_ID='" + _strUsername + "'";
            SQLHelper _helper = SQLHelperLookup.getSQLHelper();
            
            List _list = _helper.query(_strSQL);
            if (_list == null || _list.isEmpty()) {
                _strForward = "input";
                _errors.add("loginerror",new ActionMessage("errors.code.121"));
                saveErrors(request, _errors);
            } else {
                String _string = _helper.queryForString("select USER_NAME from t_user where USER_ID='" + _strUsername + "'");
                request.setAttribute("message", _string);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            _strForward = "input";
        }
        return mapping.findForward(_strForward);
    }

}
