/*
 * @(#)MenuService.java  2005-1-18
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/Elearning/src/application/service/MenuService.java,v 1.1 2005/06/14 10:29:22 navysu Exp $
 * $Log: MenuService.java,v $
 * Revision 1.1  2005/06/14 10:29:22  navysu
 * add application and framework etc.
 *
 * Revision 1.1  2005/01/20 03:14:27  navy
 * Create SMiRT 18 project
 *
 */
package application.service;

import application.entity.Menu;
import application.exception.BusinessServiceException;
import framework.service.IService;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * Business service for menu action. All business motheds should be defined in
 * here.<p>
 * </p>
 * 
 * $Revision: 1.1 $
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public interface MenuService extends IService {
    public Menu createNewMenu(Menu menuEntity) throws BusinessServiceException;

    public Menu loadMenuById(Long id) throws BusinessServiceException;

    public Menu updateMenu(Menu menuEntity) throws BusinessServiceException;

    public Menu findMenuByMenuid(String menuid)
            throws BusinessServiceException;

    public Menu[] findMenuByParentid(String parentid)
            throws BusinessServiceException;

    public Menu[] listAll() throws BusinessServiceException;

    public void removeMenu(Long id) throws BusinessServiceException;

}