/*
 * @(#)AutoReplyMail.java  2005-5-6
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.mail;

/**
 * <p><b>Description</b></p>
 * <p>Paper fee confirm mail.</p>
 * 
 * $Revision$
 * @author <a href=mailto:su_hj@126.com>Haijun Su</a>
 */
public interface AutoReplyMail {

    public void reply(String to);
    
    public void reply(String[] to);
   
}
