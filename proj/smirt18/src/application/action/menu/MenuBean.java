/*
 * @(#)MenuBean.java  2005-1-18
 * Copyright (c) 2005. All rights reserved.
 *
 * $Header: /navysu/Smirt18/src/application/action/menu/MenuBean.java,v 1.1 2005/01/20 03:14:23 navy Exp $
 * $Log: MenuBean.java,v $
 * Revision 1.1  2005/01/20 03:14:23  navy
 * Create SMiRT 18 project
 *
 */
package application.action.menu;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.MessageResources;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import application.helper.ResourcesHelper;
import application.entity.Menu;
import application.exception.BusinessServiceException;
import application.service.ApplicationServiceLocator;
import application.service.MenuService;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * </p>
 *
 * $Revision: 1.1 $
 *
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public class MenuBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3252969445269221348L;

	/**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(MenuBean.class);

    /**
     * Service for menu actions.
     */
    private MenuService m_menuService;

    /**
     * Default constructor
     */
    public MenuBean() {
        super();
        try {
            this.m_menuService = ApplicationServiceLocator.getMenuService();
        } catch (Exception e) {
            logger.error("MenuAction() - fatal error!", e);
            throw new BusinessServiceException(98, "Lookup menuService fail!");
        }
    }

    public MenuVO[] leftMenuList(String str) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("leftMenuList() - start");
        }

        MessageResources _resources = ResourcesHelper.getMenusResources();
        Menu[] _menus = this.m_menuService.listAll();
        if (_menus == null || _menus.length < 1) {
            return null;
        }
        MenuVO[] _menuVOs = new MenuVO[_menus.length];
        for (int i = 0; i < _menus.length; i++) {
            _menuVOs[i] = new MenuVO();
            try {
                BeanUtils.copyProperties(_menuVOs[i], _menus[i]);
            } catch (IllegalAccessException e) {
                logger.error("leftMenuList()", e);
            } catch (InvocationTargetException e) {
                logger.error("leftMenuList()", e);
             }
            //_menuBeans[i].setMenuid(_menus[i].getMenuid());
            _menuVOs[i].setMenuName(_resources
                    .getMessage(_menus[i].getMenuid()));
        }

        ArrayList _menuList = new ArrayList();
        if (str.equalsIgnoreCase("top")) {
            for (int i = 0; i < _menuVOs.length; i++) {
                if (_menuVOs[i].getMenuid().startsWith("12"))
                    _menuList.add(_menuVOs[i]);
            }
        }
        if (str.equalsIgnoreCase("left")) {
            for (int i = 0; i < _menuVOs.length; i++) {
                if (_menuVOs[i].getMenuid().startsWith("11"))
                    _menuList.add(_menuVOs[i]);
            }
        }
        _menuVOs = new MenuVO[_menuList.size()];
        _menuList.toArray(_menuVOs);
        if (logger.isDebugEnabled()) {
            logger.debug("leftMenuList() - end");
        }
        return _menuVOs;
    }

}