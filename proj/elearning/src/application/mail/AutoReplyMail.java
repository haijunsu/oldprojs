/*
 * @(#)AutoReplyMail.java  2005-5-6
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/Elearning/src/application/mail/AutoReplyMail.java,v 1.1 2005/06/14 10:29:25 navysu Exp $
 * $Log: AutoReplyMail.java,v $
 * Revision 1.1  2005/06/14 10:29:25  navysu
 * add application and framework etc.
 *
 */
package application.mail;

/**
 * <p><b>Description</b></p>
 * <p>Paper fee confirm mail.</p>
 * 
 * $Revision: 1.1 $
 * @author <a href=mailto:su_hj@126.com>Haijun Su</a>
 */
public interface AutoReplyMail {

    public void reply(String to);
    
    public void reply(String[] to);
   
}
