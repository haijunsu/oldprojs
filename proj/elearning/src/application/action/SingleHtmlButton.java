/*
 * @(#)SingleHtmlButton.java  2005-3-9
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/Elearning/src/application/action/SingleHtmlButton.java,v 1.1 2005/06/14 10:29:22 navysu Exp $
 * $Log: SingleHtmlButton.java,v $
 * Revision 1.1  2005/06/14 10:29:22  navysu
 * add application and framework etc.
 *
 */
package application.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.SystemUtils;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.util.MessageResources;

import application.helper.ResourcesHelper;
import framework.util.StringUtil;

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
public class SingleHtmlButton {
    private String m_action;

    private String m_method;

    private String m_nameKey;
    
    private String m_target;

    private LabelValueBean[] m_labelValueBeans;
    
    private HttpServletRequest m_request;
    
    private HttpServletResponse m_response;

    private MessageResources m_messageResources = ResourcesHelper
            .getFormsResources();

    public SingleHtmlButton(HttpServletRequest request, HttpServletResponse response) {
        this.m_request = request;
        this.m_response = response;
        this.m_action = "";
        this.m_method = "post";
        this.m_labelValueBeans = new LabelValueBean[0];
        this.m_nameKey = "";
        this.m_target = "";
    }

    public String getAction() {
        return this.m_action;
    }

    public void setAction(String action) {
        this.m_action = action;
    }

    public LabelValueBean[] getLabelValueBeans() {
        return this.m_labelValueBeans;
    }

    public void setLabelValueBeans(LabelValueBean[] labelValueBeans) {
        this.m_labelValueBeans = labelValueBeans;
    }

    public String getMethod() {
        return this.m_method;
    }

    public void setMethod(String method) {
        this.m_method = method;
    }

    public String getNameKey() {
        return this.m_nameKey;
    }

    public void setNameKey(String nameKey) {
        this.m_nameKey = nameKey;
    }

    public String getButtonName() {
        if (StringUtil.isNotBlank(getNameKey())) {
            return this.m_messageResources.getMessage(getNameKey());
        }
        return "";
    }

    public String getTarget() {
        return this.m_target;
    }
    public void setTarget(String target) {
        this.m_target = target;
    }

    public String getButton() {
        if (StringUtil.isBlank(getNameKey())) {
            return "";
        }
        StringBuffer _sbButton = new StringBuffer();
        _sbButton.append("<form method='").append(getMethod().trim()).append(
                "'");
        if (StringUtil.isNotBlank(getAction())) {
            _sbButton.append(" action='");
            if (getAction().startsWith("/")) {
                _sbButton.append(this.m_request.getContextPath());
            }
            _sbButton.append(getAction().trim())
                    .append("'");
        }
        if (StringUtil.isNotBlank(getTarget())) {
            _sbButton.append(" target='").append(getTarget().trim()).append("'");
        }
        _sbButton.append(">").append(SystemUtils.LINE_SEPARATOR);
        for (int i = 0; i < getLabelValueBeans().length; i++) {
            _sbButton.append("<input type='hidden' name='").append(
                    getLabelValueBeans()[i].getLabel().trim()).append(
                    "' value='");
            if (getLabelValueBeans()[i].getValue() != null && !getLabelValueBeans()[i].getValue().equals("null")) {
                _sbButton.append(
                        getLabelValueBeans()[i].getValue().trim());
            }
            _sbButton.append("'>")
                    .append(SystemUtils.LINE_SEPARATOR);
        }
        _sbButton.append("<input type='submit' value='").append(
                getButtonName().trim()).append("'>");
        _sbButton.append(
                SystemUtils.LINE_SEPARATOR).append("</form>").append(
                SystemUtils.LINE_SEPARATOR);

        return _sbButton.toString();
    }
    
    public String getHtmlLink() {
        if (StringUtil.isBlank(getNameKey())) {
            return "";
        }
        StringBuffer _sbLink = new StringBuffer();
        _sbLink.append("<a href='");
        if (StringUtil.isNotBlank(getAction())) {
            if (getAction().startsWith("/")) {
                _sbLink.append(this.m_request.getContextPath());
            }
            _sbLink.append(getAction().trim());
        }
        if (getLabelValueBeans().length > 0) {
            _sbLink.append("?");
        }
        for (int i = 0; i < getLabelValueBeans().length; i++) {
            _sbLink.append(getLabelValueBeans()[i].getLabel())
            .append("=");
            if (getLabelValueBeans()[i].getValue() != null && !getLabelValueBeans()[i].getValue().equals("null")) {
                _sbLink.append(
                        getLabelValueBeans()[i].getValue());
            }
            _sbLink.append("&");
        }
        _sbLink.deleteCharAt(_sbLink.length() - 1);
        if (StringUtil.isNotBlank(getTarget())) {
            _sbLink.append("' target='").append(getTarget().trim());
        }
        _sbLink.append("'>").append(getButtonName()).append("</a>");
        return  _sbLink.toString();
    }
}