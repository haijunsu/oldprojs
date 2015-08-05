/**
 * @(#)SecureAction.java  2003-1-28
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.secure;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.idn.menu.MenuItem;
import com.idn.menu.MenusBean;
import com.idn.property.PropertyManager;
import com.idn.util.DecimalTools;
import com.idn.util.FormatDate;
import com.idn.util.MissingParameterException;
import com.idn.util.ServletTools;

/**
 * <P>
 * 对于需要验证的资源，继承该类，实现SecureAction方法即可
 * </P>
 * 
 * @version 0.2
 * @author 苏海军
 */
public abstract class SecureAction extends Action {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(SecureAction.class);

    /**
     * Constructor
     */
    public SecureAction() {
        super();
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        org.apache.struts.action.ActionErrors _errors = new org.apache.struts.action.ActionErrors();
        long _llTime = System.currentTimeMillis();
        String _strLifeid = "";
        HttpSession _session = null;
        String _userid = null;
        String _purview = null;

        try {
            _strLifeid = FormatDate.format(_llTime, "yyyyMMddHHmmssS") + " - ";
            if (logger.isDebugEnabled()) {
                logger.debug(_strLifeid + "execute() - "
                        + request.getRequestURL());

                logger.debug(_strLifeid + "execute() - Memory status: "
                        + getMemUsage());

                logger.debug(_strLifeid
                        + "execute() - Parameters are "
                        + ServletTools.RequestParametersToHashtable(request)
                                .toString());
                logTraceDB(_llTime, request, 'B');
            }
            //判断应用程序有没有初始化系统菜单
            if (MenusBean.getContextPath() == null) {
                //初始化菜单

                if (logger.isDebugEnabled()) {
                    logger.debug(_strLifeid
                            + "execute() - checking MenusBean context path...");
                }
                MenusBean.setContextPath(request.getContextPath());
                if (logger.isDebugEnabled()) {
                    logger.debug(_strLifeid
                            + "execute() - init MenusBean context path ok");
                }
            }

            if (logger.isDebugEnabled()) {
                logger.debug(_strLifeid + "execute() - Checking session ...");
            }
            _session = request.getSession();

            //检查session，如果存在，则删除“userid”，否则创建session
            if (_session != null) {
                _userid = (String) _session.getAttribute("userid");
                _purview = (String) _session.getAttribute("purview");
            }

            if (_userid == null) {
                if (logger.isDebugEnabled()) {
                    logger.debug(_strLifeid + "execute() - Can't find userid");
                }
                if (logger.isDebugEnabled()) {
                    logger.debug(_strLifeid + "execute() - session id: "
                            + _session.getId() + "/session create time: "
                            + _session.getCreationTime() + ". User ip: "
                            + request.getRemoteAddr() + "/"
                            + request.getRemoteHost() + "/request session id: "
                            + request.getRequestedSessionId());
                }
                //				if (_session == null) {
                //					if (logger.isDebugEnabled()) {
                //						logger.debug("创建Session");
                //						_session = request.getSession(true);
                //					}
                //				}
                ActionError _error = new ActionError("error.logonRequired");
                _errors.add("system.logon.error", _error);
                if (request.getMethod().equals("GET")) {
                    String strFailUrl = request.getRequestURL().toString();
                    if (request.getQueryString() != null
                            && request.getQueryString().length() > 2)
                        strFailUrl = strFailUrl + "?"
                                + request.getQueryString();
                    if (logger.isDebugEnabled()) {
                        logger.debug(_strLifeid
                                + "execute() - save fail URL to session.");
                    }
                    _session.setAttribute("failUrl", strFailUrl);
                }
                if (logger.isDebugEnabled()) {
                    logger.debug(_strLifeid + "execute() - not logon.");
                }
                //没有登录，调出登录画面
                saveErrors(request, _errors);
                return mapping.findForward("logon");
            }
            if (logger.isDebugEnabled()) {
                logger.debug(_strLifeid + "execute() - user id: " + _userid);
            }

            MenuItem _mi = SecureUtil.findMenuItemByRequest(request);
            if (_mi != null) {
                if (!SecureUtil.isPermited(_mi, _purview)) {
                    ActionError error = new ActionError("error.not.permit");
                    _errors.add("system.logon.error", error);
                    if (logger.isDebugEnabled()) {
                        logger.debug(_strLifeid + "execute() - " + _userid
                                + " has't permit.");
                    }
                }
            } else {

                if (PropertyManager.getBooleanProperty("ignore.not.set.permit")) {
                    logger
                            .warn(_strLifeid
                                    + "execute() - User is accessing url without permit. Url is '"
                                    + request.getRequestURL() + "'");
                } else {
                    if (logger.isDebugEnabled()) {
                        logger
                                .debug(_strLifeid
                                        + "execute() - User can't access Url without permit. url is '"
                                        + request.getRequestURL() + "'");
                    }
                    ActionError error = new ActionError("error.not.permit");
                    _errors.add("system.logon.error", error);
                    if (logger.isDebugEnabled()) {
                        logger.debug(_strLifeid + "execute() - " + _userid
                                + " has't permit.");
                    }

                }

            }

            if (_errors.isEmpty()) {
                if (_mi != null)
                    _session.setAttribute("urlname", _mi.getName());
                return executeSecurely(mapping, form, request, response);
            } else {
                saveErrors(request, _errors);
                return mapping.findForward("failure");
            }
        } catch (Exception e) {
            logger.error(_strLifeid + "execute() error: " + e.getMessage(), e);
            _errors.add("system.logon.error",
                    new org.apache.struts.action.ActionError("error.system"));
            saveErrors(request, _errors);
            logTraceDB(_llTime, request, 'C');
            return mapping.findForward("failure");
        } finally {
            _errors = null;

            if (logger.isDebugEnabled()) {
                logger.debug(_strLifeid + "execute() total time(ns): "
                        + (System.currentTimeMillis() - _llTime));
                logger.debug(_strLifeid + "execute() - Memory status:"
                        + getMemUsage());
                try {
                    logger.debug(_strLifeid
                            + "execute() - finished! Parameters are "
                            + ServletTools
                                    .RequestParametersToHashtable(request)
                                    .toString());
                } catch (MissingParameterException e1) {
                    logger.error(_strLifeid + "execute() error parameter: "
                            + e1.getMessage());
                }
                logTraceDB(_llTime, request, 'E');
            }
            _strLifeid = null;
            _session = null;
            _userid = null;
            _purview = null;
        }

    }

    /**
     * 执行Action的主要业务逻辑
     */

    public abstract ActionForward executeSecurely(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException;

    /**
     * @return MemUsage
     */
    private String getMemUsage() {
        StringBuffer _sbMemUsage = new StringBuffer();
        long _llTotal = Runtime.getRuntime().totalMemory();
        long _llFree = Runtime.getRuntime().freeMemory();
        double _llUseage = (_llTotal - _llFree) * 100.00 / _llTotal;
        String _strUsed = DecimalTools.format(_llUseage, "#.00");
        _sbMemUsage.append("Current available Processors number: ").append(
                Runtime.getRuntime().availableProcessors()).append("; ");
        _sbMemUsage.append("MAX memory attemp to use: ").append(
                Runtime.getRuntime().maxMemory()).append("; ");
        _sbMemUsage.append("Total memory: ").append(_llTotal).append("; ");
        _sbMemUsage.append("Free memory: ").append(_llFree).append("; ");
        _sbMemUsage.append("Memory useage: ").append(_strUsed).append("%. ");
        return _sbMemUsage.toString();
    }

    /**
     * save log message to database
     * 
     * @param strStartTime
     * @param request
     * @param actType
     */
    private void logTraceDB(long strStartTime, HttpServletRequest request,
            char actType) {
        StringBuffer _sbMemUsage = new StringBuffer();
        long _llTotal = Runtime.getRuntime().totalMemory();
        long _llFree = Runtime.getRuntime().freeMemory();
        double _llUseage = (_llTotal - _llFree) * 100.00 / _llTotal;
        _sbMemUsage.append(_llTotal).append("/").append(_llFree).append("/")
                .append(DecimalTools.format(_llUseage, "#.00")).append("%");
        Trace _trace = new Trace();
        _trace.setDateTime(Long.parseLong(FormatDate.format(strStartTime,
                "yyyyMMddHHmmssS")));
        _trace.setPeriod(System.currentTimeMillis() - strStartTime);
        _trace.setSessionid(request.getSession().getId());
        _trace.setUser((String) request.getSession().getAttribute("userid"));
        _trace.setMemUsage(_sbMemUsage.toString());
        _trace.setUrl(request.getRequestURI());
        _trace.setType(actType);

        TraceBean tb = new TraceBean();
        tb.saveTrace(_trace);

    }
}