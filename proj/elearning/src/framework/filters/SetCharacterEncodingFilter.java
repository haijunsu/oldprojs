/*
 * @(#)SetCharacterEncodingFilter.java  2005-3-8
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/Elearning/src/framework/filters/SetCharacterEncodingFilter.java,v 1.2 2005/07/03 11:30:39 navysu Exp $
 * $Log: SetCharacterEncodingFilter.java,v $
 * Revision 1.2  2005/07/03 11:30:39  navysu
 * fix all bugs
 *
 * Revision 1.1  2005/06/14 10:29:24  navysu
 * add application and framework etc.
 *
 */
package framework.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision: 1.2 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class SetCharacterEncodingFilter implements Filter {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(SetCharacterEncodingFilter.class);

	// ----------------------------------------------------- Instance Variables

	/**
	 * The default character encoding to set for requests that pass through this
	 * filter.
	 */
	protected String m_encoding = null;

	/**
	 * The filter configuration object we are associated with. If this value is
	 * null, this filter instance is not currently configured.
	 */
	protected FilterConfig m_filterConfig = null;

	/**
	 * Should a character encoding specified by the client be ignored?
	 */
	protected boolean m_ignore = true;

	// --------------------------------------------------------- Public Methods

	/**
	 * Take this filter out of service.
	 */
	public void destroy() {

		this.m_encoding = null;
		this.m_filterConfig = null;

	}

	/**
	 * Select and set (if specified) the character encoding to be used to
	 * interpret request parameters for this request.
	 * 
	 * @param request
	 *            The servlet request we are processing
	 * @param result
	 *            The servlet response we are creating
	 * @param chain
	 *            The filter chain we are processing
	 * 
	 * @exception IOException
	 *                if an input/output error occurs
	 * @exception ServletException
	 *                if a servlet error occurs
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
	    
		// Conditionally select and set the character encoding to be used
		if (this.m_ignore || (request.getCharacterEncoding() == null)) {
			String _encoding = selectEncoding((ServletRequest) request);
			if (_encoding != null) {
				request.setCharacterEncoding(_encoding);
				//				response.setContentType("\"text/html;charset=\"" + encoding +
				// "\"");
			}
		}
		// Pass control on to the next filter
		chain.doFilter(request, response);

	}

	/**
	 * Place this filter into service.
	 * 
	 * @param filterConfig
	 *            The filter configuration object
	 */
	public void init(FilterConfig filterConfig) throws ServletException {

		this.m_filterConfig = filterConfig;
		this.m_encoding = filterConfig.getInitParameter("encoding");
		String _value = filterConfig.getInitParameter("ignore");
		if (_value == null)
			this.m_ignore = true;
		else if (_value.equalsIgnoreCase("true"))
			this.m_ignore = true;
		else if (_value.equalsIgnoreCase("yes"))
			this.m_ignore = true;
		else
			this.m_ignore = false;

	}

	// ------------------------------------------------------ Protected Methods

	/**
	 * Select an appropriate character encoding to be used, based on the
	 * characteristics of the current request and/or filter initialization
	 * parameters. If no character encoding should be set, return
	 * <code>null</code>.
	 * <p>
	 * The default implementation unconditionally returns the value configured
	 * by the <strong>encoding </strong> initialization parameter for this
	 * filter.
	 * 
	 * @param request
	 *            The servlet request we are processing
	 */
	protected String selectEncoding(ServletRequest request) {

		return (this.m_encoding);

	}
}