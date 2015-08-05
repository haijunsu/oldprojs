/*
 * @(#)AccompanyAction.java  2005-2-11
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.paper;

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
import application.dao.ParticipantDao;
import application.entities.TpaperPresented;
import application.entities.Tparticipant;
import application.exception.BusinessServiceException;
import application.helper.BeansLocator;
import application.service.ApplicationServiceLocator;
import application.service.PaperPresentedService;
import application.util.ServicesUtil;
import application.viewdata.SmallPaperView;
import application.viewdata.SmallUserView;
import framework.exception.CannotFoundRequestParameterException;
import framework.util.StringUtil;
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
public class PaperPresentedAction extends BaseDispachAction {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(PaperPresentedAction.class);

    public ActionForward queryPresentedByPaperNumber(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryPresentedByPaperNumber(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("presentedInput");
        try {
            PaperPresentedService _service = ApplicationServiceLocator
                    .getPaperPresentedService();
            String _strPaperNumber = RequestUtil.getRequiredStringParameter(
                    request, "paperNumber");
            String _strPaperLNumber = RequestUtil.getRequiredStringParameter(
                    request, "paperLNumber");
            TpaperPresented _tpaperPresented = _service
                    .loadPaperPresentedByPaperNumber(_strPaperNumber);
            _tpaperPresented.setChvPaperNumber(_strPaperNumber);
            _tpaperPresented.setChvPaperLNumber(_strPaperLNumber);
            PaperPresentedForm _form = (PaperPresentedForm) form;
            _form.setPresented(_tpaperPresented);
            SmallPaperView _smallPaperView = ServicesUtil
                    .getSmallPaperViewByPaperNumber(_strPaperNumber);
            _form.setPaperNumber(_smallPaperView.getChvPaperLNumber());
            _form.setPaperTitle(_smallPaperView.getChvPaperTitle());

        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "queryPresentedByPaperNumber(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("presentedError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "queryPresentedByPaperNumber(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("presentedError",
                    new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (Exception e) {
            logger
                    .error(
                            "queryPresentedByPaperNumber(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages
                    .add("presentedError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryPresentedByPaperNumber(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward createPresented(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("createPresented(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("success");
        try {
            if (isCancelled(request)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("createPresented() - Cancelled end.");
                }
                cancelledAction(mapping, request, response);
                return null;
            }
            saveBackButton(request, response);
            PaperPresentedForm _form = (PaperPresentedForm) form;
            TpaperPresented _tpaperPresented = _form.getPresented();
            PaperPresentedService _service = ApplicationServiceLocator
                    .getPaperPresentedService();
            _service.createPaperPresented(_tpaperPresented);
            _messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "success.create.paper.presented.message"));
            saveMessages(request, _messages);
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "createPresented(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("presentedError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "createPresented(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("presentedError",
                    new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (Exception e) {
            logger
                    .error(
                            "createPresented(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages
                    .add("presentedError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("createPresented(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward updatePresented(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("updatePresented(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("success");
        try {
            if (isCancelled(request)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("updatePresented() - Cancelled end.");
                }
                cancelledAction(mapping, request, response);
                return null;
            }
            saveBackButton(request, response);
            PaperPresentedForm _form = (PaperPresentedForm) form;
            TpaperPresented _tpaperPresented = _form.getPresented();
            PaperPresentedService _service = ApplicationServiceLocator
                    .getPaperPresentedService();
            _service.updatePaperPresented(_tpaperPresented);
            _messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "success.modify.paper.presented.message"));
            saveMessages(request, _messages);
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "updatePresented(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("presentedError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "updatePresented(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("presentedError",
                    new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (Exception e) {
            logger
                    .error(
                            "updatePresented(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages
                    .add("presentedError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("updatePresented(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward removePresented(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("removePresented(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("success");
        try {
            saveBackButton(request, response);
            PaperPresentedForm _form = (PaperPresentedForm) form;
            TpaperPresented _tpaperPresented = _form.getPresented();
            PaperPresentedService _service = ApplicationServiceLocator
                    .getPaperPresentedService();
            _service.removePaperPresented(_tpaperPresented);
            _messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "success.remove.paper.presented.message"));
            saveMessages(request, _messages);
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "removePresented(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("presentedError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "removePresented(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("presentedError",
                    new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
        } catch (Exception e) {
            logger
                    .error(
                            "removePresented(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages
                    .add("presentedError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("removePresented(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    private void saveBackButton(HttpServletRequest request,
            HttpServletResponse response) {
        SingleHtmlButton _btnBack = new SingleHtmlButton(request, response);
        String _strPaperNumber = RequestUtil.getRequiredStringParameter(
                request, "presented.chvPaperNumber");
        _btnBack.setNameKey("form.button.back");
        _btnBack.setAction("/search.do");
        LabelValueBean[] _labelValueBeans = new LabelValueBean[2];
        _labelValueBeans[0] = new LabelValueBean("action", "queryByPaperNumber");
        _labelValueBeans[1] = new LabelValueBean("paperNumber", _strPaperNumber);
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

        String _strPaperNumber = RequestUtil.getRequiredStringParameter(
                request, "presented.chvPaperNumber");
        removeFormBean(mapping, request);
        request.getRequestDispatcher(
                "/search.do?action=queryByPaperNumber&paperNumber="
                        + _strPaperNumber).forward(request, response);

        if (logger.isDebugEnabled()) {
            logger
                    .debug("cancelledAction(ActionMapping, HttpServletRequest, HttpServletResponse) - end");
        }
    }

    public ActionForward queryUserByFirstName(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryUserByFirstName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("presentedInput");
        try {
            if (isCancelled(request)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("queryUserByFirstName() - Cancelled end.");
                }
                return _forward;
            }
            String _strPartiNo = RequestUtil.getStringParameter(request,
                    "partiNo", "");
            PaperPresentedForm _form = (PaperPresentedForm) form;
            if (StringUtil.isNotBlank(_strPartiNo)) {
                _form.getPresented().setChrPresenterPartiNo(_strPartiNo);
                _form.getPresented().setChvPresenterFirstName(
                        _form.getFirstName());
                _form.getPresented().setChvPresenterLastName(
                        _form.getLastName());
                _form.getPresented().setChvPresenterMiddleName(
                        _form.getMiddleName());
                if (logger.isDebugEnabled()) {
                    logger
                            .debug("queryUserByFirstName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end (settng)");
                }
                return _forward;
            }
            String _strFirstName = RequestUtil.getStringParameter(request,
                    "presented.chvPresenterFirstName", "");
            ParticipantDao _dao = (ParticipantDao) BeansLocator
                    .getBeanResource("participantDao");
            Tparticipant[] _tparticipants = null;
            if (StringUtil.isBlank(_strFirstName)) {
                _tparticipants = (Tparticipant[]) _dao.listEntities(null);
            } else {
                _tparticipants = _dao.findByFirstName(_strFirstName.trim());
            }
            if (_tparticipants.length == 1) {
                _form.getPresented().setChrPresenterPartiNo(
                        _tparticipants[0].getChrPartiNo());
                _form.getPresented().setChvPresenterFirstName(
                        _tparticipants[0].getChvPartiFirstName());
                _form.getPresented().setChvPresenterLastName(
                        _tparticipants[0].getChvPartiLastName());
                _form.getPresented().setChvPresenterMiddleName(
                        _tparticipants[0].getChvPartiMiddleName());
                if (logger.isDebugEnabled()) {
                    logger
                            .debug("queryUserByFirstName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end (only one)");
                }
                return _forward;
            }
            if (_tparticipants.length > 0) {
                SmallUserView[] _userViews = new SmallUserView[_tparticipants.length];
                for (int i = 0; i < _userViews.length; i++) {
                    _userViews[i] = new SmallUserView();
                    BeanUtils.copyProperties(_userViews[i], _tparticipants[i]);
                }
                request.setAttribute("users", _userViews);
            }

            _forward = mapping.findForward("userList");

        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "queryUserByFirstName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("presentedError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "queryUserByFirstName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("presentedError",
                    new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (Exception e) {
            logger
                    .error(
                            "queryUserByFirstName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages
                    .add("presentedError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryUserByFirstName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

}