/*
 * @(#)TopicAction.java  2005-1-19
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /navysu/Smirt18/src/application/action/topic/TopicAction.java,v 1.1 2005/01/20 03:14:22 navy Exp $
 * $Log: TopicAction.java,v $
 * Revision 1.1  2005/01/20 03:14:22  navy
 * Create SMiRT 18 project
 *
 */
package application.action.topic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import application.action.BaseDispachAction;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision: 1.1 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class TopicAction extends BaseDispachAction {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(TopicAction.class);
    
    public ActionForward queryTopicByKey(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("search");
    }

    public ActionForward updateTopic(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionMessages _messages = new ActionMessages();
        _messages.add("testmsg", new ActionMessage("errors.code.97"));
        saveErrors(request, _messages);
        //request.setAttribute(Globals.MESSAGE_KEY, _messages);
        return mapping.findForward("error");
    }

}
