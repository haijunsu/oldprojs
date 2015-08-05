/*
 * @(#)BaseMailImpl.java  2005-5-6
 * Copyright (c) 2005. All rights reserved.
 *
 * $Header$
 * $Log$
 */
package com.navy.framework.mail.impl;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.navy.framework.mail.IMail;

/**
 * <p><b>Description</b></p>
 * <p></p>
 *
 * $Revision$
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
