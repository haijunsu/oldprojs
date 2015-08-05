/*
 * @(#)RegisterAction.java  2005-2-11
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.register;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.LabelValueBean;

import application.action.BaseDispachAction;
import application.action.SingleHtmlButton;
import application.exception.BusinessServiceException;
import application.service.ApplicationServiceLocator;
import application.service.RegisterService;
import application.viewdata.RegistrationView;
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
public class RegisterAction extends BaseDispachAction {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(RegisterAction.class);

    public ActionForward loadUserByPartiNo(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("loadUserByPartiNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("register");
        try {
            RegisterService _service = ApplicationServiceLocator
                    .getRegisterService();
            String _strPartiNo = RequestUtil.getRequiredStringParameter(
                    request, "partiNo");
            RegistrationView _registrationView = _service
                    .loadRegistrationByParticipantNo(_strPartiNo);
            RegisterForm _form = (RegisterForm) form;
            _form.setParticipant(_registrationView.getParticipant());
            form = _form;
            for (int i = 0; i < _registrationView.getAccompanyPerson().length; i++) {
                _registrationView.getAccompanyPerson()[i].setRequest(request);
                _registrationView.getAccompanyPerson()[i].setResponse(response);
            }
            request.setAttribute("accompanyPerson", _registrationView
                    .getAccompanyPerson());
            request.setAttribute("papers", _registrationView.getPapers());

        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "loadUserByPartiNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("registerError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "loadUserByPartiNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("registerError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
        } catch (Exception e) {
            logger
                    .error(
                            "loadUserByPartiNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("registerError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("loadUserByPartiNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward loadUserByUserid(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("loadUserByUserid(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("register");
        try {
            RegisterService _service = ApplicationServiceLocator
                    .getRegisterService();
            int _nUserid = RequestUtil.getRequiredIntParameter(request,
                    "userid");
            RegistrationView _registrationView = _service
                    .loadRegistrationByUserID(new Integer(_nUserid));
            RegisterForm _form = (RegisterForm) form;
            _form.setParticipant(_registrationView.getParticipant());
            form = _form;
            request.setAttribute("accompanyPerson", _registrationView
                    .getAccompanyPerson());
            request.setAttribute("papers", _registrationView.getPapers());

        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "loadUserByUserid(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("registerError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "loadUserByUserid(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("registerError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
        } catch (Exception e) {
            logger
                    .error(
                            "loadUserByUserid(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("registerError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("loadUserByUserid(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward loadUserByAuthorNo(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("loadUserByAuthorNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("register");
        try {
            RegisterService _service = ApplicationServiceLocator
                    .getRegisterService();
            String _strAuthorNo = RequestUtil.getRequiredStringParameter(request,
                    "authorNo");
            RegistrationView _registrationView = _service
                    .loadRegistrationByAuthorNo(_strAuthorNo);
            RegisterForm _form = (RegisterForm) form;
            _form.setParticipant(_registrationView.getParticipant());
            form = _form;
            request.setAttribute("accompanyPerson", _registrationView
                    .getAccompanyPerson());
            request.setAttribute("papers", _registrationView.getPapers());

        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "loadUserByAuthorNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("registerError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "loadUserByAuthorNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("registerError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
        } catch (Exception e) {
            logger
                    .error(
                            "loadUserByAuthorNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("registerError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("loadUserByAuthorNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward loadUserByCoauthor(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("loadUserByCoauthor(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("register");
        try {
            RegisterService _service = ApplicationServiceLocator
                    .getRegisterService();
            String _strPaperNumber = RequestUtil.getRequiredStringParameter(
                    request, "paperNumber");
            String _strEmail = RequestUtil.getRequiredStringParameter(request,
                    "eamil");
            RegistrationView _registrationView = _service
                    .loadRegistrationByPaperNumberAndEmail(_strPaperNumber,
                            _strEmail);

            RegisterForm _form = (RegisterForm) form;
            _form.setParticipant(_registrationView.getParticipant());
            form = _form;
            request.setAttribute("accompanyPerson", _registrationView
                    .getAccompanyPerson());
            request.setAttribute("papers", _registrationView.getPapers());
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "loadUserByCoauthor(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("registerError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "loadUserByCoauthor(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("registerError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (Exception e) {
            logger
                    .error(
                            "loadUserByCoauthor(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("registerError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("loadUserByCoauthor(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward createParticipant(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("createParticipant(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("success");
        try {
            if (isCancelled(request)) {
                removeFormBean(mapping, request);
                if (logger.isDebugEnabled()) {
                    logger
                            .debug("createParticipant(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - Cancelled end");
                }
               return mapping.findForward("home");
            }
            RegisterService _service = ApplicationServiceLocator
                    .getRegisterService();
            RegisterForm _form = (RegisterForm) form;
            _service.createParticipant(_form.getParticipant());
            saveBackButton(request, response, form);
            _messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.create.participant.message"));
            saveMessages(request, _messages);
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "createParticipant(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("registerError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
            _forward = mapping.findForward("register");
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "createParticipant(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("registerError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("register");
        } catch (Exception e) {
            logger
                    .error(
                            "createParticipant(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("registerError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("createParticipant(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    /**
     * @param request
     * @param response
     * @param form
     */
    private void saveBackButton(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
        SingleHtmlButton _btnBack = new SingleHtmlButton(request, response);
        String _strPartiNo = ((RegisterForm)form).getParticipant().getChrPartiNo();
        _btnBack.setNameKey("form.button.back");
        _btnBack.setAction("/register.do");
        LabelValueBean[] _labelValueBeans = new LabelValueBean[2];
        _labelValueBeans[0] = new LabelValueBean("action", "loadUserByPartiNo");
        _labelValueBeans[1] = new LabelValueBean("partiNo", _strPartiNo);
        _btnBack.setLabelValueBeans(_labelValueBeans);
        request.setAttribute("backBtn", _btnBack);
    }

    public ActionForward updateParticipant(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("updateParticipant(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("success");
        try {
            if (isCancelled(request)) {
                removeFormBean(mapping, request);
                if (logger.isDebugEnabled()) {
                    logger
                            .debug("updateParticipant(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - Cancelled end");
                }
               return mapping.findForward("home");
            }
            RegisterService _service = ApplicationServiceLocator
                    .getRegisterService();
            RegisterForm _form = (RegisterForm) form;
            saveBackButton(request, response);
            _service.updateParticipant(_form.getParticipant());
            _messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.modify.participant.message"));
            saveMessages(request, _messages);
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "updateParticipant(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("registerError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
            _forward = mapping.findForward("register");
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "updateParticipant(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("registerError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("register");
        } catch (Exception e) {
            logger
                    .error(
                            "updateParticipant(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("registerError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("updateParticipant(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward removeParticipant(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("removeParticipant(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("success");
        try {
            if (isCancelled(request)) {
                removeFormBean(mapping, request);
                if (logger.isDebugEnabled()) {
                    logger
                            .debug("removeParticipant(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - Cancelled end");
                }
               return mapping.findForward("home");
            }
            saveHomeButton(request, response);
            RegisterService _service = ApplicationServiceLocator
                    .getRegisterService();
            RegisterForm _form = (RegisterForm) form;
            _service.removeParticipant(_form.getParticipant());
            _messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.remove.participant.message"));
            saveMessages(request, _messages);
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "removeParticipant(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("registerError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "removeParticipant(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("registerError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (Exception e) {
            logger
                    .error(
                            "removeParticipant(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("registerError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("removeParticipant(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }
    
    private void saveBackButton(HttpServletRequest request, HttpServletResponse response) {
        SingleHtmlButton _btnBack = new SingleHtmlButton(request, response);
        String _strPartiNo = RequestUtil.getRequiredStringParameter(
                request, "participant.chrPartiNo");
        _btnBack.setNameKey("form.button.back");
        _btnBack.setAction("/register.do");
        LabelValueBean[] _labelValueBeans = new LabelValueBean[2];
        _labelValueBeans[0] = new LabelValueBean("action", "loadUserByPartiNo");
        _labelValueBeans[1] = new LabelValueBean("partiNo", _strPartiNo);
        _btnBack.setLabelValueBeans(_labelValueBeans);
        request.setAttribute("backBtn", _btnBack);
    }
}