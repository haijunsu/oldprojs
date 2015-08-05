/*
 * @(#)AuthorAction.java  2005-1-20
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /navysu/Smirt18/src/application/action/topic/AuthorAction.java,v 1.1 2005/01/20 03:14:22 navy Exp $
 * $Log: AuthorAction.java,v $
 * Revision 1.1  2005/01/20 03:14:22  navy
 * Create SMiRT 18 project
 *
 */
package application.action.topic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import framework.util.http.RequestUtil;

import application.action.BaseDispachAction;
import application.action.topic.jo.SearchInfo;
import application.exception.BusinessServiceException;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * </p>
 * 
 * $Revision: 1.1 $
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public class AuthorAction extends BaseDispachAction {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(AuthorAction.class);

    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("update(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }
         if (isCancelled(request)) {
            removeFormBean(mapping, request);
            if (logger.isDebugEnabled()) {
                logger
                        .debug("update(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)"
                                + " - cancelled end");
            }
            return mapping.findForward("home");
        }

        ActionForward returnActionForward = mapping.findForward("authorPage");
        if (logger.isDebugEnabled()) {
            logger
                    .debug("update(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return returnActionForward;
    }

    public ActionForward query(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("query(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        AuthorVO[] _authors = new AuthorVO[1];
        AuthorBean _ab = new AuthorBean();
        ActionMessages _errors = new ActionMessages();
        ActionForward _formward = null;
        try {
            //according searchType witch to branches business.
            int _nSearchType = RequestUtil.getRequiredIntParameter(request,
                    "searchType");
            String _searchKey = RequestUtil.getRequiredStringParameter(request,
                    "searchKey");
            if (logger.isDebugEnabled()) {
                logger
                        .debug("query(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - searchType = '"
                                + _nSearchType
                                + "', searchKey = '"
                                + _searchKey + "'");
            }
            switch (_nSearchType) {
            case 0:
                //topic no
                _authors[0] = _ab.findByPaperNumber(_searchKey);
                break;

            case 1:
                //first name
                _authors = _ab.findByFirstName(_searchKey);
                break;
            case 2:
                //last name
                _authors = _ab.findByLastName(_searchKey);
                break;
            case 3:
                //email
                _authors = _ab.findByEmail(_searchKey);
                break;
            case 4:
                //visa id
                _authors = _ab.findByVisa(_searchKey);
                break;
            case 5:
                //topic title
                _authors = _ab.findByPaperName(_searchKey);
                break;

            case 11:
            case 12:
            case 13:
                //user table key
                _authors[0] = _ab.findByUserTableKey(_searchKey);
                break;

            default:
                break;
            }

            if (_authors[0] == null) {
                throw new BusinessServiceException(120, "Can't find anything.");
            }
            if (_authors.length > 1) {
                String _strUrl = request.getRequestURI()
                        + "?action=query&searchType="
                        + (_nSearchType + 10) + "&searchKey=";
                String _strTemp = "";
                String _strTempTitle = "";
                SearchInfo[] _searchs = new SearchInfo[_authors.length];
                for (int i = 0; i < _authors.length; i++) {
                    _searchs[i] = new SearchInfo();
                    switch (_nSearchType) {
                    case 0:
                        //topic no
                        //                    _strTemp = _authors[i].getPapers()[i].getTopicNo();
                        //                    _strTempTitle = _authors[i].getTopicTitle();
                        break;

                    case 1:
                    case 2:
                    case 3:
                        //first name
                        _strTemp = _authors[i].getUserProfile().getUserKey();
                        _strTempTitle = _authors[i].getUserProfile()
                                .getUserName();
                        break;
                    case 4:
                        //visa id
                        //                    _strTemp = _authors[i].getVisaId();
                        //                    _strTempTitle = _authors[i].getVisaId();
                        break;
                    case 5:
                        //topic title
                        //                    _strTemp = _authors[i].getTopicTitle();
                        //                    _strTempTitle = _authors[i].getTopicTitle();
                        break;
                    default:
                        break;
                    }
                    _strTemp = response.encodeURL(_strUrl + _strTemp);
                    _searchs[i].setUrl(_strTemp);
                    _searchs[i].setUrlName(_strTempTitle);
                }
                request.setAttribute("resultlist", _searchs);
                _formward = mapping.findForward("searchlist");
                _searchs = null;
                _authors = null;
                if (logger.isDebugEnabled()) {
                    logger
                            .debug("query(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
                }
                return _formward;
            } else {
                BeanUtils.copyProperties(form, _authors[0]);
                request.setAttribute("", _authors[0]);
                _formward = mapping.findForward("authorPage");
            }
            if (logger.isDebugEnabled()) {
                logger
                        .debug("query(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
            }
        } catch (BusinessServiceException e) {
            logger
                    .error(
                            "query(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);
            logger.error(RequestUtil.requestToString(request).toString());
            _errors.add("BusinessError", new ActionMessage("errors.code."
                    + e.getErrCode()));
            this.saveErrors(request, _errors);
            _formward = mapping.findForward("searchlist");

        }
        if (logger.isDebugEnabled()) {
            logger
                    .debug("query(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _formward;
    }

}