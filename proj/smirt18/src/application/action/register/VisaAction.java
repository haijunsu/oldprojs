/*
 * @(#)VisaAction.java  2005-3-9
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
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
import application.entities.Tvisa;
import application.exception.BusinessServiceException;
import application.service.ApplicationServiceLocator;
import application.service.VisaService;
import application.viewdata.VisaView;
import framework.exception.CannotFoundRequestParameterException;
import framework.util.http.RequestUtil;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class VisaAction extends BaseDispachAction {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(VisaAction.class);
    
    public ActionForward newVisa(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("newVisa(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("visaInput");
        try {
            VisaService _service = ApplicationServiceLocator.getVisaService();
            String _strPartiNo = RequestUtil.getRequiredStringParameter(request,
            "visa.chrPartiNo");
            String _strAccPersonNo = RequestUtil.getStringParameter(request,
            "visa.chrAccPersonNo", "");

            VisaView _visaView = _service.loadVisaByPartiNoAndAccPersonNo(_strPartiNo, _strAccPersonNo);
            VisaForm _form = (VisaForm)form;
            _form.setVisa(_visaView);
           
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "newVisa(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("visaError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "newVisa(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("visaError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (Exception e) {
            logger
                    .error(
                            "newVisa(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("visaError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }
        if (logger.isDebugEnabled()) {
            logger
                    .debug("newVisa(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward queryVisaByParticipantNo(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryVisaByParticipantNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("visaList");
        try {
            saveParentButten(request, response);
            VisaService _service = ApplicationServiceLocator.getVisaService();
            String _strPartiNo = RequestUtil.getRequiredStringParameter(request,
            "partiNo");
            VisaView[] _visaViews = _service.loadVisaByPartiNo(_strPartiNo);
            for (int i = 0; i < _visaViews.length; i++) {
                _visaViews[i].setRequest(request);
                _visaViews[i].setResponse(response);
            }
            request.setAttribute("visas", _visaViews);
           
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "queryVisaByParticipantNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("visaError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "queryVisaByParticipantNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("visaError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (Exception e) {
            logger
                    .error(
                            "queryVisaByParticipantNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("visaError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryVisaByParticipantNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    
    public ActionForward queryVisaByVisaNo(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryVisaByVisaNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("visaInput");
        try {
            VisaService _service = ApplicationServiceLocator.getVisaService();
            String _strVisaNo = RequestUtil.getRequiredStringParameter(request,
            "visa.chrVisaNo");
            VisaView _visaView = _service.loadVisaByVisaNo(_strVisaNo);
            VisaForm _form = (VisaForm)form;
            _form.setVisa(_visaView);
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "queryVisaByVisaNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("visaError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "queryVisaByVisaNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("visaError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
        } catch (Exception e) {
            logger
                    .error(
                            "queryVisaByVisaNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("visaError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryVisaByVisaNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward createVisa(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("createVisa(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("success");
        try {
            if(isCancelled(request)){
                if (logger.isDebugEnabled()) {
                    logger.debug("createVisa() - Cancelled end.");
                }
                cancelledAction(mapping, request, response);
                return null;
            }
            saveBackButton(request, response);
            VisaForm _form = (VisaForm)form;
            VisaView _visaView = _form.getVisa();
            VisaService _service = ApplicationServiceLocator.getVisaService();
            Tvisa _tvisa = new Tvisa();
            BeanUtils.copyProperties(_tvisa, _visaView);
            _service.createVisa(_tvisa);
            _messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.create.visa.message"));
            saveMessages(request, _messages);
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "createVisa(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("visaError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "createVisa(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("visaError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
        } catch (Exception e) {
            logger
                    .error(
                            "createVisa(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("visaError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("createVisa(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward updateVisa(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("updateVisa(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("success");
        try {
            if(isCancelled(request)){
                if (logger.isDebugEnabled()) {
                    logger.debug("updateVisa() - Cancelled end.");
                }
                cancelledAction(mapping, request, response);
                return null;
            }
            saveBackButton(request, response);
            VisaForm _form = (VisaForm)form;
            VisaView _visaView = _form.getVisa();
            VisaService _service = ApplicationServiceLocator.getVisaService();
            Tvisa _tvisa = new Tvisa();
            BeanUtils.copyProperties(_tvisa, _visaView);
            _service.updateVisa(_tvisa);
            _messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.modify.visa.message"));
            saveMessages(request, _messages);
       } catch (BusinessServiceException e) {
            logger
                    .error(
                            "updateVisa(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("visaError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "updateVisa(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("visaError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
        } catch (Exception e) {
            logger
                    .error(
                            "updateVisa(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("visaError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("updateVisa(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward removeVisa(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("removeVisa(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("success");
        try {
            saveBackButton(request, response);
            VisaForm _form = (VisaForm)form;
            VisaView _visaView = _form.getVisa();
            VisaService _service = ApplicationServiceLocator.getVisaService();
            Tvisa _tvisa = new Tvisa();
            BeanUtils.copyProperties(_tvisa, _visaView);
            _service.removeVisa(_tvisa);
            _messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.remove.visa.message"));
            saveMessages(request, _messages);
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "removeVisa(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("visaError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "removeVisa(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("visaError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
        } catch (Exception e) {
            logger
                    .error(
                            "removeVisa(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("visaError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("removeVisa(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }
    
    private void saveBackButton(HttpServletRequest request, HttpServletResponse response) {
        SingleHtmlButton _btnBack = new SingleHtmlButton(request, response);
        String _strPartiNo = RequestUtil.getRequiredStringParameter(request,
                "visa.chrPartiNo");
        _btnBack.setNameKey("form.button.back");
        _btnBack.setAction("/visa.do");
        LabelValueBean[] _labelValueBeans = new LabelValueBean[2];
        _labelValueBeans[0] = new LabelValueBean("action", "queryVisaByParticipantNo");
        _labelValueBeans[1] = new LabelValueBean("partiNo", _strPartiNo);
        _btnBack.setLabelValueBeans(_labelValueBeans);
        request.setAttribute("backBtn", _btnBack);
    }

    private void cancelledAction(ActionMapping mapping,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("cancelledAction(ActionMapping, HttpServletRequest, HttpServletResponse) - start");
        }

        String _strPartiNo = RequestUtil.getRequiredStringParameter(request,
                "visa.chrPartiNo");
        removeFormBean(mapping, request);
        request.getRequestDispatcher(
                "/visa.do?action=queryVisaByParticipantNo&partiNo=" + _strPartiNo)
                .forward(request, response);

        if (logger.isDebugEnabled()) {
            logger
                    .debug("cancelledAction(ActionMapping, HttpServletRequest, HttpServletResponse) - end");
        }
    }
    
    private void saveParentButten(HttpServletRequest request, HttpServletResponse response) {
        SingleHtmlButton _btnBack = new SingleHtmlButton(request, response);
        String _strPartiNo = RequestUtil.getRequiredStringParameter(request,
                "partiNo");
        _btnBack.setNameKey("form.button.back");
        _btnBack.setAction("/register.do");
        LabelValueBean[] _labelValueBeans = new LabelValueBean[2];
        _labelValueBeans[0] = new LabelValueBean("action", "loadUserByPartiNo");
        _labelValueBeans[1] = new LabelValueBean("partiNo", _strPartiNo);
        _btnBack.setLabelValueBeans(_labelValueBeans);
        request.setAttribute("backBtn", _btnBack);
    }
}
