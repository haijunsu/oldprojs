/*
 * @(#)PaperReceivedDetailAction.java  2005-3-20
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.paper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.LabelValueBean;

import application.action.BaseDispachAction;
import application.action.SingleHtmlButton;
import application.dao.PaperDao;
import application.entities.Tpaper;
import application.exception.BusinessServiceException;
import application.helper.BeansLocator;
import framework.exception.CannotFoundRequestParameterException;
import framework.util.http.RequestUtil;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * </p>
 * 
 * $Revision$
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public class PaperReceivedDetailAction extends BaseDispachAction {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(PaperReceivedDetailAction.class);

    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("update(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("success");
        try {
            saveBackButton(request, response);
            String _strPaperNumber = RequestUtil.getRequiredStringParameter(
                    request, "chvPaperNumber");
            Boolean _isMailOrgAbstract = new Boolean(RequestUtil
                    .getRequiredStringParameter(request,
                            "smallPaperView.bitIsMailOrgAbstract"));
            Boolean _isMailOrgPaper = new Boolean(RequestUtil
                    .getRequiredStringParameter(request,
                            "smallPaperView.bitIsMailOrgPaper"));
            Boolean _isMailCopyRight = new Boolean(RequestUtil
                    .getRequiredStringParameter(request,
                            "smallPaperView.bitIsMailCopyright"));
            Tpaper _tpaper = new Tpaper();
            _tpaper.setChvPaperNumber(_strPaperNumber);
            _tpaper.setBitIsMailCopyright(_isMailCopyRight);
            _tpaper.setBitIsMailOrgPaper(_isMailOrgPaper);
            _tpaper.setBitIsMailOrgAbstract(_isMailOrgAbstract);
            PaperDao _paperDao = (PaperDao) BeansLocator
                    .getBeanResource("paperDao");
            _paperDao.updateIsPaperRecivedDetail(_tpaper);
            _messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "success.modify.paper.message"));
            saveMessages(request, _messages);
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "update(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("paper", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "update(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("paper", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (Exception e) {
            logger
                    .error(
                            "update(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("paper", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("update(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    private void saveBackButton(HttpServletRequest request,
            HttpServletResponse response) {
        SingleHtmlButton _btnBack = new SingleHtmlButton(request, response);
        String _strPaperNumber = RequestUtil.getRequiredStringParameter(
                request, "chvPaperNumber");
        _btnBack.setNameKey("form.button.back");
        _btnBack.setAction("/search.do");
        LabelValueBean[] _labelValueBeans = new LabelValueBean[2];
        _labelValueBeans[0] = new LabelValueBean("action", "queryByPaperNumber");
        _labelValueBeans[1] = new LabelValueBean("paperNumber", _strPaperNumber);
        _btnBack.setLabelValueBeans(_labelValueBeans);
        request.setAttribute("backBtn", _btnBack);
    }

}