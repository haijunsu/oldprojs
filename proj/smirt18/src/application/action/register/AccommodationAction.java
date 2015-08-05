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
import application.entities.Taccommodation;
import application.exception.BusinessServiceException;
import application.service.AccommodationService;
import application.service.ApplicationServiceLocator;
import application.viewdata.AccommodationView;
import application.viewdata.SmallUserView;
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
public class AccommodationAction extends BaseDispachAction {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(AccommodationAction.class);

    public ActionForward newHotel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("newHotel(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("hotelInput");
        try {
            String _strPartiNo = RequestUtil.getRequiredStringParameter(
                    request, "hotel.chrPartiNo");
            AccommodationService _service = ApplicationServiceLocator
                    .getAccommodationService();
            SmallUserView _smallUserView = _service
                    .loadOwnerByPartiNo(_strPartiNo);
            request.setAttribute("userView", _smallUserView);

        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "newHotel(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("hotelError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "newHotel(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("hotelError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (Exception e) {
            logger
                    .error(
                            "newHotel(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("hotelError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }
        if (logger.isDebugEnabled()) {
            logger
                    .debug("newHotel(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward queryHotelByParticipantNo(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryHotelByParticipantNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("hotelList");
        try {
            saveParentButten(request, response);
            saveNewButton(request, response);
            AccommodationService _service = ApplicationServiceLocator
                    .getAccommodationService();
            String _strPartiNo = RequestUtil.getRequiredStringParameter(
                    request, "partiNo");
            AccommodationView[] _accommodationViews = _service
                    .loadAccommodationByPartiNo(_strPartiNo);
            for (int i = 0; i < _accommodationViews.length; i++) {
                _accommodationViews[i].setRequest(request);
                _accommodationViews[i].setResponse(response);
            }
            SmallUserView _smallUserView = _service
                    .loadOwnerByPartiNo(_strPartiNo);
            if (_accommodationViews.length > 0) {
                request.setAttribute("hotels", _accommodationViews);
            }
            request.setAttribute("userView", _smallUserView);

        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "queryHotelByParticipantNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("hotelError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "queryHotelByParticipantNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("hotelError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (Exception e) {
            logger
                    .error(
                            "queryHotelByParticipantNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("hotelError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryHotelByParticipantNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward queryHotelByHotelNo(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryHotelByHotelNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("hotelInput");
        try {
            AccommodationService _service = ApplicationServiceLocator
                    .getAccommodationService();
            String _strHotelNo = RequestUtil.getRequiredStringParameter(
                    request, "hotel.chrAccommodationNo");
            String _strPartiNo = RequestUtil.getRequiredStringParameter(
                    request, "hotel.chrPartiNo");

            AccommodationView _accommodationView = _service
                    .loadAccommodationByAccommodationNo(_strHotelNo);
            SmallUserView _smallUserView = _service
                    .loadOwnerByPartiNo(_strPartiNo);
            AccommodationForm _form = (AccommodationForm) form;
            _form.setHotel(_accommodationView);
            request.setAttribute("userView", _smallUserView);

        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "queryHotelByHotelNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("hotelError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "queryHotelByHotelNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("hotelError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
        } catch (Exception e) {
            logger
                    .error(
                            "queryHotelByHotelNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("hotelError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryHotelByHotelNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward createHotel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("createHotel(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("success");
        try {
            if (isCancelled(request)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("createHotel() - Cancelled end.");
                }
                cancelledAction(mapping, request, response);
                return null;
            }
            saveBackButton(request, response);
            AccommodationForm _form = (AccommodationForm) form;
            Taccommodation _taccommodation = _form.getHotel();
            AccommodationService _service = ApplicationServiceLocator
                    .getAccommodationService();
            _service.createAccommodation(_taccommodation);
            _messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "success.create.hotel.message"));
            saveMessages(request, _messages);
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "createHotel(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("hotelError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "createHotel(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("hotelError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (Exception e) {
            logger
                    .error(
                            "createHotel(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("hotelError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("createHotel(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward updateHotel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("updateHotel(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("success");
        try {
            if (isCancelled(request)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("updateHotel() - Cancelled end.");
                }
                cancelledAction(mapping, request, response);
                return null;
            }
            saveBackButton(request, response);
            AccommodationForm _form = (AccommodationForm) form;
            Taccommodation _taccommodation = _form.getHotel();
            AccommodationService _service = ApplicationServiceLocator
                    .getAccommodationService();
            _service.updateAccommodation(_taccommodation);
            _messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "success.modify.hotel.message"));
            saveMessages(request, _messages);
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "updateHotel(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("hotelError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "updateHotel(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("hotelError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (Exception e) {
            logger
                    .error(
                            "updateHotel(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("hotelError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("updateHotel(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward removeHotel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("removeHotel(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("success");
        try {
            saveBackButton(request, response);
            AccommodationForm _form = (AccommodationForm) form;
            Taccommodation _taccommodation = _form.getHotel();
            AccommodationService _service = ApplicationServiceLocator
                    .getAccommodationService();
            _service.removeAccommodation(_taccommodation);
            _messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "success.remove.hotel.message"));
            saveMessages(request, _messages);
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "removeHotel(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("hotelError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "removeHotel(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("hotelError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (Exception e) {
            logger
                    .error(
                            "removeHotel(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("hotelError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("removeHotel(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    private void saveNewButton(HttpServletRequest request,
            HttpServletResponse response) {
        SingleHtmlButton _btnBack = new SingleHtmlButton(request, response);
        String _strPartiNo = RequestUtil.getRequiredStringParameter(request,
                "partiNo");
        _btnBack.setNameKey("form.button.add");
        _btnBack.setAction("/hotel.do");
        LabelValueBean[] _labelValueBeans = new LabelValueBean[2];
        _labelValueBeans[0] = new LabelValueBean("action", "newHotel");
        _labelValueBeans[1] = new LabelValueBean("hotel.chrPartiNo",
                _strPartiNo);
        _btnBack.setLabelValueBeans(_labelValueBeans);
        request.setAttribute("newBtn", _btnBack);
    }

    private void saveBackButton(HttpServletRequest request,
            HttpServletResponse response) {
        SingleHtmlButton _btnBack = new SingleHtmlButton(request, response);
        String _strPartiNo = RequestUtil.getRequiredStringParameter(request,
                "hotel.chrPartiNo");
        _btnBack.setNameKey("form.button.back");
        _btnBack.setAction("/hotel.do");
        LabelValueBean[] _labelValueBeans = new LabelValueBean[2];
        _labelValueBeans[0] = new LabelValueBean("action",
                "queryHotelByParticipantNo");
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
                "hotel.chrPartiNo");
        removeFormBean(mapping, request);
        request.getRequestDispatcher(
                "/hotel.do?action=queryHotelByParticipantNo&partiNo="
                        + _strPartiNo).forward(request, response);

        if (logger.isDebugEnabled()) {
            logger
                    .debug("cancelledAction(ActionMapping, HttpServletRequest, HttpServletResponse) - end");
        }
    }

    private void saveParentButten(HttpServletRequest request,
            HttpServletResponse response) {
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