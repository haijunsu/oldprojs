/*
 * @(#)BaseMailImpl.java  2005-5-6
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/Elearning/src/framework/mail/impl/BaseMailImpl.java,v 1.1 2005/06/14 10:29:23 navysu Exp $
 * $Log: BaseMailImpl.java,v $
 * Revision 1.1  2005/06/14 10:29:23  navysu
 * add application and framework etc.
 *
 */
package framework.mail.impl;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import framework.mail.IMail;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision: 1.1 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class BaseMailImpl implements IMail {
    private MailSender mailSender;
    
    private SimpleMailMessage message;
    /**
     * @param mailSender The mailSender to set.
     */
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
    /**
     * @param message The message to set.
     */
    public void setMessage(SimpleMailMessage message) {
        this.message = message;
    }
    /**
     * @return Returns the mailSender.
     */
    protected MailSender getMailSender() {
        return this.mailSender;
    }
    /**
     * @return Returns the message.
     */
    protected SimpleMailMessage getMessage() {
        return this.message;
    }
}
