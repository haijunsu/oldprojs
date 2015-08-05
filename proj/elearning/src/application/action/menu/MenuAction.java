/*
 * @(#)MenuAction.java  2005-1-18
 * Copyright (c) 2005. All rights reserved.
 *
 * $Header: /project/Elearning/src/application/action/menu/MenuAction.java,v 1.1 2005/06/14 10:29:22 navysu Exp $
 * $Log: MenuAction.java,v $
 * Revision 1.1  2005/06/14 10:29:22  navysu
 * add application and framework etc.
 *
 * Revision 1.1  2005/01/20 03:14:23  navy
 * Create SMiRT 18 project
 *
 */
package application.action.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import application.action.BaseDispachAction;
import application.exception.BusinessServiceException;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * Menu actions
 * </p>
 *
 * $Revision: 1.1 $
 *
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public class MenuAction extends BaseDispachAction {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(MenuAction.class);

    /**
     * Default constructor
     */
    public MenuAction() {
        super();
    }

    /**
     * query menus and desplay
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward leftMenuList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("leftMenuList(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }
        MenuBean _mb = null;
        ActionForward _forward = mapping.findForward("left");
        ActionMessages _errors = new ActionMessages();
        try {
            _mb = new MenuBean();
            MenuVO[] _menuVOs = _mb.leftMenuList("left");
            if (_menuVOs != null && _menuVOs.length > 0) {
                for (int i = 0; i < _menuVOs.length; i++) {
                    if (_menuVOs[i].getUrl().startsWith("/")) {
                        _menuVOs[i].setUrl(request.getContextPath() + "/" + _menuVOs[i].getUrl());
                    }
                }
                request.setAttribute("smirtMenus", _menuVOs);
            }

        } catch (BusinessServiceException e) {
            logger.error("leftMenuList() - BusinesServiceException", e);
            _errors.add("BusinessError", new ActionMessage("errors.code." + e.getErrCode()));
            this.saveErrors(request, _errors);
        } catch (Exception e) {
            logger.error("leftMenuList() - uncatched exception", e);
            _forward = mapping.findForward("left");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("leftMenuList(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward topMenuList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("topMenuList(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }
        MenuBean _mb = null;
        ActionForward _forward = mapping.findForward("top");
        ActionErrors _errors = new ActionErrors();
        try {
            _mb = new MenuBean();
            MenuVO[] _menuVOs = _mb.leftMenuList("top");
            if (_menuVOs != null && _menuVOs.length > 0) {
                request.setAttribute("smirtMenus", _menuVOs);
            }
        } catch (BusinessServiceException e) {
            logger.error("topMenuList() - BusinesServiceException", e);
        } catch (Exception e) {
            logger.error("topMenuList() - uncatched exception", e);
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("topMenuList(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward addMenu(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("add(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }
        if (logger.isDebugEnabled()) {
            logger
                    .debug("add(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return null;
    }

}