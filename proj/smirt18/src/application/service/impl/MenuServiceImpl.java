/*
 * @(#)MenuServiceImpl.java  2005-1-18
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /navysu/Smirt18/src/application/service/impl/MenuServiceImpl.java,v 1.1 2005/01/20 03:14:20 navy Exp $
 * $Log: MenuServiceImpl.java,v $
 * Revision 1.1  2005/01/20 03:14:20  navy
 * Create SMiRT 18 project
 *
 */
package application.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.dao.MenuDao;
import application.entity.Menu;
import application.exception.BusinessServiceException;
import application.service.MenuService;
import framework.exception.DaoException;
import framework.service.impl.BaseServiceImpl;

/**
 * <p><b>Description</b></p>
 * <p>Implement menu business action.</p>
 * 
 * $Revision: 1.1 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class MenuServiceImpl extends BaseServiceImpl implements MenuService {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(MenuServiceImpl.class);
    
    private MenuDao m_menuDao;

    /* (non-Javadoc)
     * @see application.service.MenuService#createNewMenu(application.entity.Menu)
     */
    public Menu createNewMenu(Menu menuEntity) throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("createNewMenu(Menu) - start");
        }

        try {
            Menu _menu = this.m_menuDao.findByMenuid(menuEntity.getMenuid());
            if (_menu == null) {
                try {
                    this.m_menuDao.create(menuEntity);
                } catch (DaoException ex) {
                    logger.error("createNewMenu(Menu)", ex);

                    throw new BusinessServiceException(110, "create new menu fail.");
                }
            }else {
            throw new BusinessServiceException(112, "Menu is existed. Menuid = " + menuEntity.getMenuid());
            }
        } catch (DaoException e) {
            logger.error("createNewMenu(Menu)", e);

            throw new BusinessServiceException(99, "Access database error.");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("createNewMenu(Menu) - end");
        }
        return menuEntity;
    }

    /* (non-Javadoc)
     * @see application.service.MenuService#loadMenuById(java.lang.Long)
     */
    public Menu loadMenuById(Long id) throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("loadMenuById(Long) - start");
        }

        Menu _menu = null;
        try {
            _menu = this.m_menuDao.load(id);
            if (_menu == null) {
            throw new BusinessServiceException(111,
                    "Cannot find menu. Menu serial = " + id);
            }
        } catch (DaoException e) {
            logger.error("loadMenuById(Long)", e);

            throw new BusinessServiceException(99, "Access database error.");
        }

 
        if (logger.isDebugEnabled()) {
            logger.debug("loadMenuById(Long) - end");
        }
        return _menu;
    }

    /* (non-Javadoc)
     * @see application.service.MenuService#updateMenu(application.entity.Menu)
     */
    public Menu updateMenu(Menu menuEntity) throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("updateMenu(Menu) - start");
        }

        try {
            this.m_menuDao.update(menuEntity);
        } catch (DaoException e) {
            logger.error("updateMenu(Menu)", e);

            throw new BusinessServiceException(99, "Access database error.");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("updateMenu(Menu) - end");
        }
        return menuEntity;
    }

    /* (non-Javadoc)
     * @see application.service.MenuService#findMenuByMenuid(java.lang.String)
     */
    public Menu findMenuByMenuid(String menuid)
            throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("findMenuByMenuid(String) - start");
        }

        Menu _menu = null;
        try {
            _menu = this.m_menuDao.findByMenuid(menuid);
        	if (_menu == null) {
            throw new BusinessServiceException(111,
                    "Cannot find menu. menuid = " + menuid);
        	}
        } catch (DaoException e) {
            logger.error("findMenuByMenuid(String)", e);

            throw new BusinessServiceException(99, "Access database error.");
        }


        if (logger.isDebugEnabled()) {
            logger.debug("findMenuByMenuid(String) - end");
        }
        return _menu;
    }

    /* (non-Javadoc)
     * @see application.service.MenuService#findMenuByParentid(java.lang.String)
     */
    public Menu[] findMenuByParentid(String parentid)
            throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("findMenuByParentid(String) - start");
        }

        Menu[] _menu = null;
        try {
            _menu = this.m_menuDao.findByParentid(parentid);
        	if(_menu == null) {
            throw new BusinessServiceException(111,
                    "Cannot find menu. parentid = " + parentid);
        	}
        } catch (DaoException e) {
            logger.error("findMenuByMenuid(String)", e);

            throw new BusinessServiceException(99, "Access database error.");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("findMenuByParentid(String) - end");
        }
        return _menu;
    }

    /* (non-Javadoc)
     * @see application.service.MenuService#listAll()
     */
    public Menu[] listAll() throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("listAll() - start");
        }

        Menu[] _menu = null;
        try {
            _menu = this.m_menuDao.listAll();
        if (_menu == null) {
            throw new BusinessServiceException(111,
                    "Cannot find menu. ");
        }
        } catch (DaoException e) {
            logger.error("findMenuByMenuid(String)", e);

            throw new BusinessServiceException(99, "Access database error.");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("listAll() - end");
        }
        return _menu;
    }

    /* (non-Javadoc)
     * @see application.service.MenuService#removeMenu(java.lang.Long)
     */
    public void removeMenu(Long id) throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("removeMenu(Long) - start");
        }

        try {
            Menu _menu = this.m_menuDao.load(id);
            this.m_menuDao.remove(_menu);

        } catch (DaoException e) {
            logger.error("removeMenu(Long)", e);

            throw new BusinessServiceException(99, "Access database error.");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("removeMenu(Long) - end");
        }
    }

    /**
     * @param menuDao The menuDao to set.
     */
    public void setMenuDao(MenuDao menuDao) {
        this.m_menuDao = menuDao;
    }
}
