/*
 * @(#)AccompanyAction.java  2005-2-11
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.register;

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
import application.entities.TaccompanyPerson;
import application.exception.BusinessServiceException;
import application.service.AccompanyPersonService;
import application.service.ApplicationServiceLocator;
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
public class AccompanyAction extends BaseDispachAction {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(RegisterAction.class);

    public ActionForward newAccompanyPerson(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("newAccompanyPerson(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("accompany");
        try {
            String _strPartiNo = RequestUtil.getRequiredStringParameter(
                    request, "partiNo");
            TaccompanyPerson _person = new TaccompanyPerson();
            _person.setChrPartiNo(_strPartiNo);
            AccompanyForm _form = (AccompanyForm) form;
            _form.setPerson(_person);
            form = _form;
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "newAccompanyPerson(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("accompanyError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "newAccompanyPerson(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("accompanyError",
                    new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
        } catch (Exception e) {
            logger
                    .error(
                            "newAccompanyPerson(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages
                    .add("accompanyError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("newAccompanyPerson(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward loadByAccPersonNo(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("loadByAccPersonNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("accompany");
        try {
            AccompanyPersonService _service = ApplicationServiceLocator
                    .getAccompanyPersonService();
            String _strAccPersonNo = RequestUtil.getRequiredStringParameter(
                    request, "accPersonNo");
            TaccompanyPerson _person = _service
                    .loadAccompanyPersonByAccPersonNo(_strAccPersonNo);
            AccompanyForm _form = (AccompanyForm) form;
            _form.setPerson(_person);
            form = _form;
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "loadByAccPersonNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("accompanyError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "loadByAccPersonNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("accompanyError",
                    new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
        } catch (Exception e) {
            logger
                    .error(
                            "loadByAccPersonNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages
                    .add("accompanyError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("loadByAccPersonNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward createAccompanyPerson(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("createAccompanyPerson(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("success");
        try {
            AccompanyForm _form = (AccompanyForm) form;
            String _partiNo = _form.getPerson().getChrPartiNo();
            if (isCancelled(request)) {
                if (logger.isDebugEnabled()) {
                    logger
                            .debug("createAccompanyPerson(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - Cancelled end");
                }
                cancelledAction(mapping, request, response);
                return null;
            }
            saveBackButton(request, response);
            AccompanyPersonService _service = ApplicationServiceLocator
                    .getAccompanyPersonService();
            _service.createAccompanyPerson(_form.getPerson());
            _messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "success.create.accompany.person.message"));
            saveMessages(request, _messages);

        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "createAccompanyPerson(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("accompanyError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
            _forward = mapping.findForward("register");
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "createAccompanyPerson(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("accompanyError",
                    new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("register");
        } catch (Exception e) {
            logger
                    .error(
                            "createAccompanyPerson(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages
                    .add("accompanyError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("createAccompanyPerson(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward updateAccompanyPerson(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("updateAccompanyPerson(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("success");
        try {
            AccompanyForm _form = (AccompanyForm) form;
            if (isCancelled(request)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("updateAccompanyPerson() - Cancelled end.");
                }

                cancelledAction(mapping, request, response);
                return null;
           }
            saveBackButton(request, response);
            AccompanyPersonService _service = ApplicationServiceLocator
                    .getAccompanyPersonService();
            _service.updateAccompanyPerson(_form.getPerson());
            _messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "success.modify.accompany.person.message"));
            saveMessages(request, _messages);

        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "updateAccompanyPerson(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("accompanyError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
            _forward = mapping.findForward("register");
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "updateAccompanyPerson(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("accompanyError",
                    new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("register");
        } catch (Exception e) {
            logger
                    .error(
                            "updateAccompanyPerson(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages
                    .add("accompanyError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("updateAccompanyPerson(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward removeAccompanyPerson(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("removeAccompanyPerson(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("success");
        try {
            saveBackButton(request, response);
            AccompanyForm _form = (AccompanyForm) form;
            AccompanyPersonService _service = ApplicationServiceLocator
                    .getAccompanyPersonService();
            _service.removeAccompanyPerson(_form.getPerson());
            _messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "success.remove.accompany.person.message"));
            saveMessages(request, _messages);

        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "removeAccompanyPerson(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("accompanyError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "removeAccompanyPerson(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("accompanyError",
                    new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (Exception e) {
            logger
                    .error(
                            "removeAccompanyPerson(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages
                    .add("accompanyError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("removeAccompanyPerson(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    private void saveBackButton(HttpServletRequest request, HttpServletResponse response) {
        SingleHtmlButton _btnBack = new SingleHtmlButton(request, response);
        String _strPartiNo = RequestUtil.getRequiredStringParameter(request,
                "person.chrPartiNo");
        _btnBack.setNameKey("form.button.back");
        _btnBack.setAction("/register.do");
        LabelValueBean[] _labelValueBeans = new LabelValueBean[2];
        _labelValueBeans[0] = new LabelValueBean("action", "loadUserByPartiNo");
        _labelValueBeans[1] = new LabelValueBean("partiNo", _strPartiNo);
        _btnBack.setLabelValueBeans(_labelValueBeans);
        request.setAttribute("backBtn", _btnBack);
    }

    private void cancelledAction(ActionMapping mapping,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String _strPartiNo = RequestUtil.getRequiredStringParameter(request,
                "person.chrPartiNo");
        removeFormBean(mapping, request);
        request.getRequestDispatcher(
                "/register.do?action=loadUserByPartiNo&partiNo=" + _strPartiNo)
                .forward(request, response);

    }
}