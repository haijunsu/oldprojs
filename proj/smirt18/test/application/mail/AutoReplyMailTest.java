/*
 * @(#)AutoReplyMailTest.java  2005-5-6
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.mail;

import application.helper.BeansLocator;
import junit.framework.TestCase;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author <a href=mailto:su_hj@126.com>Haijun Su</a>
 */
public class AutoReplyMailTest extends TestCase {

    private AutoReplyMail m_autoReplyMail;
    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        this.m_autoReplyMail = (AutoReplyMail) BeansLocator.getBeanResource("autoReplyMail");
    }

    public void testReply() {
        String[] to = new String[]{"navy1<navysu@126.com>", "navy2<navy_su@126.com>"}; 
        this.m_autoReplyMail.reply(to);
    }

}
