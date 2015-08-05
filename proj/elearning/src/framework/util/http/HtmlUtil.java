/*
 * @(#)HtmlUtil.java  2005-1-6
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/Elearning/src/framework/util/http/HtmlUtil.java,v 1.1 2005/06/14 10:29:24 navysu Exp $
 * $Log: HtmlUtil.java,v $
 * Revision 1.1  2005/06/14 10:29:24  navysu
 * add application and framework etc.
 *
 * Revision 1.1  2005/01/20 03:14:28  navy
 * Create SMiRT 18 project
 *
 */
package framework.util.http;

import org.springframework.web.util.HtmlUtils;

/**
 * <p><b>Description</b></p>
 * <p>deal with html text</p>
 * 
 * $Revision: 1.1 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class HtmlUtil {

    public static String htmlEscape(String strHtml){
        return HtmlUtils.htmlEscape(strHtml);
    }

    public static String htmlUnEscape(String strHtml){
        return HtmlUtils.htmlUnescape(strHtml);
    }
    
    public static String htmlFormat(String strTxt) {
        String _strTemp = strTxt.replaceAll("\r\n", "<br>");
        _strTemp = _strTemp.replaceAll("\n", "<br>");
        _strTemp = _strTemp.replaceAll("\r", "<br>");
        return _strTemp;
    }

}
