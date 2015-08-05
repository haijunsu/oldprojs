/*
 * @(#)SearchAction.java  2005-2-27
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.search;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import framework.exception.CannotFoundRequestParameterException;
import framework.util.http.RequestUtil;

import application.action.BaseDispachAction;
import application.exception.BusinessServiceException;
import application.service.ApplicationServiceLocator;
import application.service.SearchService;
import application.viewdata.PaperView;
import application.viewdata.SmallPaperView;
import application.viewdata.SmallUserView;

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
public class SearchAction extends BaseDispachAction {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(SearchAction.class);

    public ActionForward queryByEmail(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryByEmail(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("userList");
        try {
            SearchService _searchService = ApplicationServiceLocator
                    .getSearchService();
            String _strSearchKey = RequestUtil.getRequiredStringParameter(
                    request, "searchKey");
            SmallUserView[] _smallUserView = _searchService
                    .searchByEmail(_strSearchKey);
            if (_smallUserView == null || _smallUserView.length == 0) {
                throw new BusinessServiceException(130, "Reuslt is Empty.");
            }
            request.setAttribute("smallUserView", _smallUserView);
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "queryByEmail(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - "
                                    + RequestUtil.requestToString(request), e);

            _messages.add("searchError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);

        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "queryByEmail(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - "
                                    + RequestUtil.requestToString(request), e);

            _messages.add("searchError", new ActionMessage("errors.code.131",
                    "searchKey"));
            saveErrors(request, _messages);

        } catch (Exception e) {
            logger
                    .error(
                            "queryByEmail(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - Uncatched Exception!",
                            e);

        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryByEmail(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;

    }

    public ActionForward queryByFirstName(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryByFirstName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("userList");
        try {
            SearchService _searchService = ApplicationServiceLocator
                    .getSearchService();
            String _strSearchKey = RequestUtil.getRequiredStringParameter(
                    request, "searchKey");
            SmallUserView[] _smallUserView = _searchService.searchByFirstName(_strSearchKey);
            if (_smallUserView == null || _smallUserView.length == 0) {
                throw new BusinessServiceException(130, "Reuslt is Empty.");
            }
            request.setAttribute("smallUserView", _smallUserView);
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "queryByFirstName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - "
                                    + RequestUtil.requestToString(request), e);

            _messages.add("searchError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);

        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "queryByFirstName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - "
                                    + RequestUtil.requestToString(request), e);

            _messages.add("searchError", new ActionMessage("errors.code.131",
                    "searchKey"));
            saveErrors(request, _messages);

        } catch (Exception e) {
            logger
                    .error(
                            "queryByFirstName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - Uncatched Exception!",
                            e);

        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryByFirstName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;

    }

    public ActionForward queryByLastName(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryByLastName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("userList");
        try {
            SearchService _searchService = ApplicationServiceLocator
                    .getSearchService();
            String _strSearchKey = RequestUtil.getRequiredStringParameter(
                    request, "searchKey");
            SmallUserView[] _smallUserView = _searchService.searchByLastName(_strSearchKey);
            if (_smallUserView == null || _smallUserView.length == 0) {
                throw new BusinessServiceException(130, "Reuslt is Empty.");
            }
            request.setAttribute("smallUserView", _smallUserView);
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "queryByLastName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - "
                                    + RequestUtil.requestToString(request), e);

            _messages.add("searchError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);

        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "queryByLastName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - "
                                    + RequestUtil.requestToString(request), e);

            _messages.add("searchError", new ActionMessage("errors.code.131",
                    "searchKey"));
            saveErrors(request, _messages);

        } catch (Exception e) {
            logger
                    .error(
                            "queryByLastName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - Uncatched Exception!",
                            e);

        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryByLastName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;

    }
    
    public ActionForward queryByPaperTitle(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryByPaperTitle(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("paperList");
        try {
            SearchService _searchService = ApplicationServiceLocator
                    .getSearchService();
            String _strSearchKey = RequestUtil.getRequiredStringParameter(
                    request, "searchKey");
            SmallPaperView[] _smallPaperView = _searchService.searchByPaperTitle(_strSearchKey);
            if (_smallPaperView == null || _smallPaperView.length == 0) {
                throw new BusinessServiceException(130, "Reuslt is Empty.");
            }
            request.setAttribute("papers", _smallPaperView);
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "queryByPaperTitle(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - "
                                    + RequestUtil.requestToString(request), e);

            _messages.add("searchError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);

        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "queryByPaperTitle(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - "
                                    + RequestUtil.requestToString(request), e);

            _messages.add("searchError", new ActionMessage("errors.code.131",
                    "searchKey"));
            saveErrors(request, _messages);

        } catch (Exception e) {
            logger
                    .error(
                            "queryByPaperTitle(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - Uncatched Exception!",
                            e);

        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryByPaperTitle(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;

    }
    
    public ActionForward queryByPaperNumber(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryByPaperNumber(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("paperViewList");
        try {
            SearchService _searchService = ApplicationServiceLocator
                    .getSearchService();
            String _strSearchKey = RequestUtil.getRequiredStringParameter(
                    request, "paperNumber");
            PaperView _paperView = _searchService.serachByPaperNumber(_strSearchKey);
            if (_paperView == null) {
                throw new BusinessServiceException(130, "Reuslt is Empty.");
            }
            request.setAttribute("paperView", _paperView);
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "queryByPaperNumber(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - "
                                    + RequestUtil.requestToString(request), e);

            _messages.add("searchError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);

        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "queryByPaperNumber(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - "
                                    + RequestUtil.requestToString(request), e);

            _messages.add("searchError", new ActionMessage("errors.code.131",
                    "searchKey"));
            saveErrors(request, _messages);

        } catch (Exception e) {
            logger
                    .error(
                            "queryByPaperNumber(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - Uncatched Exception!",
                            e);

        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryByPaperNumber(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;

    }

    public ActionForward queryByPaperLNumber(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryByPaperLNumber(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("paperViewList");
        try {
            SearchService _searchService = ApplicationServiceLocator
                    .getSearchService();
            String _strSearchKey = RequestUtil.getRequiredStringParameter(
                    request, "searchKey");
            PaperView _paperView = _searchService.serachByPaperLNumber(_strSearchKey);
            if (_paperView == null) {
                throw new BusinessServiceException(130, "Reuslt is Empty.");
            }
            request.setAttribute("paperView", _paperView);
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "queryByPaperLNumber(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - "
                                    + RequestUtil.requestToString(request), e);

            _messages.add("searchError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);

        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "queryByPaperLNumber(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - "
                                    + RequestUtil.requestToString(request), e);

            _messages.add("searchError", new ActionMessage("errors.code.131",
                    "searchKey"));
            saveErrors(request, _messages);

        } catch (Exception e) {
            logger
                    .error(
                            "queryByPaperLNumber(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - Uncatched Exception!",
                            e);

        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryByPaperLNumber(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;

    }

    public ActionForward queryRegisterByEmail(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryRegisterByEmail(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("registerList");
        try {
            SearchService _searchService = ApplicationServiceLocator
                    .getSearchService();
            String _strSearchKey = RequestUtil.getRequiredStringParameter(
                    request, "searchKey");
            SmallUserView[] _smallUserView = _searchService
                    .searchRegisterByEmail(_strSearchKey);
            if (_smallUserView == null || _smallUserView.length == 0) {
                throw new BusinessServiceException(130, "Reuslt is Empty.");
            }
            request.setAttribute("smallUserView", _smallUserView);
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "queryRegisterByEmail(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - "
                                    + RequestUtil.requestToString(request), e);

            _messages.add("searchError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);

        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "queryRegisterByEmail(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - "
                                    + RequestUtil.requestToString(request), e);

            _messages.add("searchError", new ActionMessage("errors.code.131",
                    "searchKey"));
            saveErrors(request, _messages);

        } catch (Exception e) {
            logger
                    .error(
                            "queryRegisterByEmail(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - Uncatched Exception!",
                            e);

        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryRegisterByEmail(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;

    }

    public ActionForward queryRegisterByFirstName(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryRegisterByFirstName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("registerList");
        try {
            SearchService _searchService = ApplicationServiceLocator
                    .getSearchService();
            String _strSearchKey = RequestUtil.getRequiredStringParameter(
                    request, "searchKey");
            SmallUserView[] _smallUserView = _searchService
                    .searchRegisterByFirstName(_strSearchKey);
            if (_smallUserView == null || _smallUserView.length == 0) {
                throw new BusinessServiceException(130, "Reuslt is Empty.");
            }
            request.setAttribute("smallUserView", _smallUserView);
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "queryRegisterByFirstName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - "
                                    + RequestUtil.requestToString(request), e);

            _messages.add("searchError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);

        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "queryRegisterByFirstName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - "
                                    + RequestUtil.requestToString(request), e);

            _messages.add("searchError", new ActionMessage("errors.code.131",
                    "searchKey"));
            saveErrors(request, _messages);

        } catch (Exception e) {
            logger
                    .error(
                            "queryRegisterByFirstName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - Uncatched Exception!",
                            e);

        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryRegisterByFirstName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;

    }

    public ActionForward queryRegisterByLastName(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryRegisterByLastName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("registerList");
        try {
            SearchService _searchService = ApplicationServiceLocator
                    .getSearchService();
            String _strSearchKey = RequestUtil.getRequiredStringParameter(
                    request, "searchKey");
            SmallUserView[] _smallUserView = _searchService
                    .searchRegisterByLastName(_strSearchKey);
            if (_smallUserView == null || _smallUserView.length == 0) {
                throw new BusinessServiceException(130, "Reuslt is Empty.");
            }
            request.setAttribute("smallUserView", _smallUserView);
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "queryRegisterByLastName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - "
                                    + RequestUtil.requestToString(request), e);

            _messages.add("searchError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);

        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "queryRegisterByLastName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - "
                                    + RequestUtil.requestToString(request), e);

            _messages.add("searchError", new ActionMessage("errors.code.131",
                    "searchKey"));
            saveErrors(request, _messages);

        } catch (Exception e) {
            logger
                    .error(
                            "queryRegisterByLastName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - Uncatched Exception!",
                            e);

        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryRegisterByLastName(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;

    }

    public ActionForward queryByAffiliation(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryByAffiliation(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        ActionMessages _messages = new ActionMessages();
        ActionForward _forward = mapping.findForward("userList");
        try {
            SearchService _searchService = ApplicationServiceLocator
                    .getSearchService();
            String _strSearchKey = RequestUtil.getRequiredStringParameter(
                    request, "searchKey");
            SmallUserView[] _smallUserView = _searchService
                    .searchByAffiliation(_strSearchKey);
            if (_smallUserView == null || _smallUserView.length == 0) {
                throw new BusinessServiceException(130, "Reuslt is Empty.");
            }
            request.setAttribute("smallUserView", _smallUserView);
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "queryByAffiliation(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - "
                                    + RequestUtil.requestToString(request), e);

            _messages.add("searchError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _messages);

        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "queryByAffiliation(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - "
                                    + RequestUtil.requestToString(request), e);

            _messages.add("searchError", new ActionMessage("errors.code.131",
                    "searchKey"));
            saveErrors(request, _messages);

        } catch (Exception e) {
            logger
                    .error(
                            "queryByAffiliation(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - Uncatched Exception!",
                            e);

        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("queryByAffiliation(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;

    }

}