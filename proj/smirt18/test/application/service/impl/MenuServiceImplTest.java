/*
 * @(#)MenuServiceImplTest.java  2005-2-27
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.service.impl;

import application.service.ApplicationServiceLocator;
import application.service.MenuService;
import junit.framework.TestCase;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class MenuServiceImplTest extends TestCase {

    MenuService m_service;
    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        this.m_service = ApplicationServiceLocator.getMenuService();
    }

    public void testCreateNewMenu() {
        //TODO Implement createNewMenu().
    }

    public void testLoadMenuById() {
        //TODO Implement loadMenuById().
    }

    public void testUpdateMenu() {
        //TODO Implement updateMenu().
    }

    public void testFindMenuByMenuid() {
        //TODO Implement findMenuByMenuid().
    }

    public void testFindMenuByParentid() {
        //TODO Implement findMenuByParentid().
    }

    public void testListAll() {
        //TODO Implement listAll().
    }

    public void testRemoveMenu() {
        //TODO Implement removeMenu().
    }

}
