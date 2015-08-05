/*
 * @(#)ResourcesHelper.java  2005-1-18
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/Elearning/src/application/helper/ResourcesHelper.java,v 1.1 2005/06/14 10:29:22 navysu Exp $
 * $Log: ResourcesHelper.java,v $
 * Revision 1.1  2005/06/14 10:29:22  navysu
 * add application and framework etc.
 *
 * Revision 1.1  2005/01/20 03:14:27  navy
 * Create SMiRT 18 project
 *
 */
package application.helper;

import org.apache.struts.util.MessageResources;

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
public class ResourcesHelper {

    private static MessageResources m_resources;

    static {
        m_resources = MessageResources
                .getMessageResources("application.helper.MessagerList");
    }
    
    /**
     * get MenusResources
     * @return MenusResources
     */
    public static MessageResources getMenusResources() {
        return MessageResources.getMessageResources(m_resources
                .getMessage("menus.bundle"));
    }

    /**
     * get FormsResources
     * @return FormsResources
     */
    public static MessageResources getFormsResources() {
        return MessageResources.getMessageResources(m_resources
                .getMessage("forms.bundle"));
    }

    /**
     * get ErrorsResources
     * @return ErrorsResources
     */
    public static MessageResources getErrorsResources() {
        return MessageResources.getMessageResources(m_resources
                .getMessage("errors.bundle"));
    }

    /**
     * get MessagerResources
     * @return MessagerResources
     */
    public static MessageResources getMessagerResources() {
        return MessageResources.getMessageResources(m_resources
                .getMessage("messager.bundle"));
    }

    /**
     * get DaoSQLStatements
     * @return MessagerResources
     */
    public static MessageResources getDaoSQLStatements() {
        return MessageResources.getMessageResources(m_resources
                .getMessage("dao.sql.statements"));
    }

}