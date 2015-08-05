/*
 * @(#)AutoReplyMailImpl.java  2005-5-6
 * Copyright (c) 2005. All rights reserved.
 *
 * $Header: /project/Elearning/src/application/mail/impl/AutoReplyMailImpl.java,v 1.1 2005/06/14 10:29:25 navysu Exp $
 * $Log: AutoReplyMailImpl.java,v $
 * Revision 1.1  2005/06/14 10:29:25  navysu
 * add application and framework etc.
 *
 */
package application.mail.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;

import application.mail.AutoReplyMail;
import framework.mail.impl.BaseMailImpl;

/**
 * <p><b>Description</b></p>
 * <p></p>
 *
 * $Revision: 1.1 $
 * @author <a href=mailto:su_hj@126.com>Haijun Su</a>
 */
public class AutoReplyMailImpl extends BaseMailImpl implements AutoReplyMail {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(AutoReplyMailImpl.class);

    /* (non-Javadoc)
     * @see application.mail.AutoReplyMail#reply(java.lang.String)
     */
    public void reply(String to) {
        reply(new String[]{to});
    }

    /* (non-Javadoc)
     * @see application.mail.AutoReplyMail#reply(java.lang.String[])
     */
    public void reply(String[] to) {
        SimpleMailMessage msg = new SimpleMailMessage(getMessage());
        msg.setTo(to);
        try{
            getMailSender().send(msg);
        } catch (MailException e) {
			logger.error("reply() - ReplyMailError", e);
        }
    }

}
