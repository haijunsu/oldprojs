/*
 * @(#)RequestUtil.java  2005-1-6
 * Copyright (c) 2005. All rights reserved.
 *
 * $Header: /navysu/Smirt18/src/framework/util/http/RequestUtil.java,v 1.1 2005/01/20 03:14:28 navy Exp $
 * $Log: RequestUtil.java,v $
 * Revision 1.1  2005/01/20 03:14:28  navy
 * Create SMiRT 18 project
 *
 */
package framework.util.http;

import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.bind.ServletRequestBindingException;

import framework.exception.CannotFoundRequestParameterException;
import framework.util.StringUtil;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * Access HttpRequest parameters wrap.
 * </p>
 *
 * $Revision: 1.1 $
 *
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public class RequestUtil {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(RequestUtil.class);

    public static boolean getBooleanParameter(HttpServletRequest request,
            String name, boolean is) {
        return RequestUtils.getBooleanParameter(request, name, is);
    }

    public static boolean[] getBooleanParameters(HttpServletRequest request,
            String name) {
        return RequestUtils.getBooleanParameters(request, name);
    }

    public static double getDoubleParameter(HttpServletRequest request,
            String name, double num) {
        return RequestUtils.getDoubleParameter(request, name, num);
    }

    public static double[] getDoubleParameters(HttpServletRequest request,
            String name) {
        return RequestUtils.getDoubleParameters(request, name);
    }

    public static float getFloatParameter(HttpServletRequest request,
            String name, float num) {
        return RequestUtils.getFloatParameter(request, name, num);
    }

    public static float[] getFloatParameters(HttpServletRequest request,
            String name) {
        return RequestUtils.getFloatParameters(request, name);
    }

    public static int getIntParameter(HttpServletRequest request, String name,
            int num) {
        return RequestUtils.getIntParameter(request, name, num);
    }

    public static int[] getIntParameters(HttpServletRequest request, String name) {
        return RequestUtils.getIntParameters(request, name);
    }

    public static long getLongParameter(HttpServletRequest request,
            String name, long num) {
        return RequestUtils.getLongParameter(request, name, num);
    }

    public static long[] getLongParameters(HttpServletRequest request,
            String name) {
        return RequestUtils.getLongParameters(request, name);
    }

    public static String getStringParameter(HttpServletRequest request,
            String name, String str) {
        return RequestUtils.getStringParameter(request, name, str);
    }

    public static String[] getStringParameters(HttpServletRequest request,
            String name) {
        return RequestUtils.getStringParameters(request, name);
    }

    public static boolean getRequiredBooleanParameter(
            HttpServletRequest request, String name)
            throws CannotFoundRequestParameterException {
        try {
            return RequestUtils.getRequiredBooleanParameter(request, name);
        } catch (ServletRequestBindingException e) {
            throw new CannotFoundRequestParameterException(
                    " can't find parameter name: " + name);
        }
    }

    public static boolean[] getRequiredBooleanParameters(
            HttpServletRequest request, String name)
            throws CannotFoundRequestParameterException {
        try {
            return RequestUtils.getRequiredBooleanParameters(request, name);
        } catch (ServletRequestBindingException e) {
            throw new CannotFoundRequestParameterException(
                    " can't find parameter name: " + name);
        }
    }

    public static double getRequiredDoubleParameter(HttpServletRequest request,
            String name) throws CannotFoundRequestParameterException {
        try {
            return RequestUtils.getRequiredDoubleParameter(request, name);
        } catch (ServletRequestBindingException e) {
            throw new CannotFoundRequestParameterException(
                    " can't find parameter name: " + name);
        }
    }

    public static double[] getRequiredDoubleParameters(
            HttpServletRequest request, String name)
            throws CannotFoundRequestParameterException {
        try {
            return RequestUtils.getRequiredDoubleParameters(request, name);
        } catch (ServletRequestBindingException e) {
            throw new CannotFoundRequestParameterException(
                    " can't find parameter name: " + name);
        }
    }

    public static float getRequiredFloatParameter(HttpServletRequest request,
            String name) throws CannotFoundRequestParameterException {
        try {
            return RequestUtils.getRequiredFloatParameter(request, name);
        } catch (ServletRequestBindingException e) {
            throw new CannotFoundRequestParameterException(
                    " can't find parameter name: " + name);
        }
    }

    public static float[] getRequiredFloatParameters(
            HttpServletRequest request, String name)
            throws CannotFoundRequestParameterException {
        try {
            return RequestUtils.getRequiredFloatParameters(request, name);
        } catch (ServletRequestBindingException e) {
            throw new CannotFoundRequestParameterException(
                    " can't find parameter name: " + name);
        }
    }

    public static int getRequiredIntParameter(HttpServletRequest request,
            String name) throws CannotFoundRequestParameterException {
        try {
            return RequestUtils.getRequiredIntParameter(request, name);
        } catch (ServletRequestBindingException e) {
            throw new CannotFoundRequestParameterException(
                    " can't find parameter name: " + name);
        }
    }

    public static int[] getRequiredIntParameters(HttpServletRequest request,
            String name) throws CannotFoundRequestParameterException {
        try {
            return RequestUtils.getRequiredIntParameters(request, name);
        } catch (ServletRequestBindingException e) {
            throw new CannotFoundRequestParameterException(
                    " can't find parameter name: " + name);
        }
    }

    public static long getRequiredLongParameter(HttpServletRequest request,
            String name) throws CannotFoundRequestParameterException {
        try {
            return RequestUtils.getRequiredLongParameter(request, name);
        } catch (ServletRequestBindingException e) {
            throw new CannotFoundRequestParameterException(
                    " can't find parameter name: " + name);
        }
    }

    public static long[] getRequiredLongParameters(HttpServletRequest request,
            String name) throws CannotFoundRequestParameterException {
        try {
            return RequestUtils.getRequiredLongParameters(request, name);
        } catch (ServletRequestBindingException e) {
            throw new CannotFoundRequestParameterException(
                    " can't find parameter name: " + name);
        }
    }

    public static String getRequiredStringParameter(HttpServletRequest request,
            String name) throws CannotFoundRequestParameterException {
        try {
            return RequestUtils.getRequiredStringParameter(request, name);
        } catch (ServletRequestBindingException e) {
            throw new CannotFoundRequestParameterException(
                    " can't find parameter name: " + name);
        }
    }

    public static String[] getRequiredStringParameters(
            HttpServletRequest request, String name)
            throws CannotFoundRequestParameterException {
        try {
            return RequestUtils.getRequiredStringParameters(request, name);
        } catch (ServletRequestBindingException e) {
            throw new CannotFoundRequestParameterException(
                    " can't find parameter name: " + name);
        }
    }

    public static String requestToString(HttpServletRequest request) {
        StringBuffer _sb = new StringBuffer();
        _sb.append(request.getRequestURL().toString()).append("?");
        Enumeration _enumeration = request.getParameterNames();
        while (_enumeration.hasMoreElements()) {
			String _key = (String) _enumeration.nextElement();
			_sb.append(_key).append("=").append(request.getParameter(_key)).append("&");
		}
        _sb.deleteCharAt(_sb.length() - 1);
        return _sb.toString();
    }

    public static String encodeFullUrl(HttpServletRequest request, HttpServletResponse response, String url) {
        if (StringUtil.isBlank(url)) {
            return url;
        }
        String _strUrl = url;
        if (_strUrl.startsWith("/") && request != null) {
            _strUrl = request.getContextPath() + _strUrl;
        }
        _strUrl = response.encodeURL(_strUrl);
        return _strUrl;
    }

    public static String encodeUrl(HttpServletRequest request, HttpServletResponse response, String url) {
        if (StringUtil.isBlank(url)||response == null) {
            return url;
        }
        return response.encodeURL(url);
    }

    public static String encodeUrlParameterValue(String parameterValue) {
        if (logger.isDebugEnabled()) {
            logger.debug("encodeUrlParameterValue(String) - start");
        }

        try {
            if (StringUtil.isBlank(parameterValue)) {
                return parameterValue;
            }
            String _str = URLEncoder.encode(parameterValue, "UTF-8");

            if (logger.isDebugEnabled()) {
                logger.debug("encodeUrlParameterValue(String) - end");
            }
            return _str;
        } catch (Exception e) {
            logger.error("encodeUrlParameterValue(String)", e);

            if (logger.isDebugEnabled()) {
                logger.debug("encodeUrlParameterValue(String) - end");
            }
            return parameterValue;
        }
    }

}